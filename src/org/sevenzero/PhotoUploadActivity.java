package org.sevenzero;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.sevenzero.http.SendFileUtil;

import util.FilePathResolver;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.foxit.util.Util;

/**
 * 
 * @author linger
 *
 * @since 2015-7-23
 * 
 * 测试拍照上传 
 *
 */
public class PhotoUploadActivity extends Activity {
	
	private static final String TAG = PhotoUploadActivity.class.getSimpleName();
	
	private Button btnCallCamera, btnUploadPhoto, btnSelectPic;
	private ProgressBar uploadPhotoProgressBar;
	
//	ImageView mImageView;
//	CropParams mCropParams;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_upload);
		
		btnCallCamera = (Button) this.findViewById(R.id.btnCallCamera);
		btnCallCamera.setOnClickListener(listener);
		
		btnSelectPic = (Button) findViewById(R.id.btnSelectPic);
		btnSelectPic.setOnClickListener(listener);
		
		btnUploadPhoto = (Button) this.findViewById(R.id.btnUploadPhoto);
		btnUploadPhoto.setOnClickListener(listener);
		
		uploadPhotoProgressBar = (ProgressBar) this.findViewById(R.id.uploadPhotoProgressBar);
		uploadPhotoProgressBar.setVisibility(View.GONE);

//		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
	}
	
	// 拍照
	public static final int CAMERA_REQUEST_CODE = 200;
	
	// 图库选择
	public static final int SELECT_REQUEST_CODE = 201;
	
	// 剪裁图片
	public static final int PIC_ZOOM_REQUEST_CODE = 202;
	
	private Bitmap photo = null;
	private String saveImgPath = null;

	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Util.printLog(TAG, "onActivityResult " + requestCode + ", " + resultCode);
		
        if (requestCode == CAMERA_REQUEST_CODE) {
//        	if (null != photo && !photo.isRecycled()) {
//        		Util.printLog(TAG, "回收图片资源");
//        		photo.recycle();
//        		photo = null;
//        	}
//        	
//        	Uri uri = intent.getData();
//        	if (null != uri) {
//        		Util.printLog(TAG, uri.getPath());
//        		photo = BitmapFactory.decodeFile(uri.getPath());
//        	}
//        	if (null == photo) {
//				Bundle bundle = intent.getExtras();
//				if (bundle != null) {
//					photo = (Bitmap) bundle.get("data");
//				}
//				else {
//					Toast.makeText(PhotoUploadActivity.this,
//							getString(R.string.common_msg_get_photo_failure),
//							Toast.LENGTH_LONG).show();
//				}
//        	}
//        	
//        	saveImgPath = this.saveBitmap();
//        	if (null == saveImgPath) {
//        		Util.printLog(TAG, "保存失败");
//        		Toast.makeText(PhotoUploadActivity.this,
//						"保存失败",
//						Toast.LENGTH_SHORT).show();
//        	}
//        	else {
//        		Util.printLog(TAG, "已保存到手机");
//        		Toast.makeText(PhotoUploadActivity.this,
//						"已保存到手机",
//						Toast.LENGTH_SHORT).show();
//        	}
        	
        	File tmp = new File(imgPath);
        	startPhotoZoom(Uri.fromFile(tmp));
        	
        }
        else if (requestCode == SELECT_REQUEST_CODE) {
        	if (null != data) {
	        	Uri uri = data.getData();
				String path = FilePathResolver.getPath(this, uri);
	//        	startPhotoZoom(data.getData());
				startPhotoZoom(Uri.fromFile(new File(path)));
        	}
        }
        else if (requestCode == PIC_ZOOM_REQUEST_CODE) {
        	if (null != data) {
				if (null != photo && !photo.isRecycled()) {
					Util.printLog(TAG, "回收图片资源");
					photo.recycle();
					photo = null;
				}
        		Uri uri = data.getData();
        		if (uri != null) {
        			photo = BitmapFactory.decodeFile(uri.getPath());
        			saveBitmap();
        			return;
        		}
        		
        		Bundle bundle = data.getExtras();
        		if (null != bundle) {
        			photo = (Bitmap) bundle.get("data");
        			photo = (Bitmap) bundle.getParcelable("data");
        			saveBitmap();
        		}
        	}
        }
    }
	
	/**
	 * 保存照片 
	 * 
	 * @return true save success, false failure
	 */
	String saveBitmap() {
		if (null != photo) {
//			DateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
//			String filename = df.format(new Date());
//			Util.printLog(TAG, filename);
			
			String saveDir = Environment.getExternalStorageDirectory() + "/2images";
			File dir = new File(saveDir);
			if (!dir.exists()) {
				dir.mkdir();
			}
			
			String imgPath = saveDir + File.separator + "temp.jpg";
			Util.printLog(TAG, imgPath);
			File file = new File(imgPath);
			if (file.exists()) {
				file.delete();
			}
			
			ByteArrayOutputStream baos = null;
			BufferedOutputStream bos = null;
			FileOutputStream fos = null;
			try {
				baos = new ByteArrayOutputStream();
				photo.compress(Bitmap.CompressFormat.JPEG, 80, baos);
				byte[] byteArray = baos.toByteArray();
				
				fos = new FileOutputStream(file);
				bos = new BufferedOutputStream(fos);
				bos.write(byteArray);
				bos.flush();
				
				Util.printLog(TAG, file.getPath());
				return file.getPath();
			}
			catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
			finally {
				if (null != baos) {
					try {
						baos.close();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (null != bos) {
					try {
						bos.close();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (null != fos) {
					try {
						fos.close();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}
	
	// 剪裁
	public void startPhotoZoom(Uri uri) {
		/*
		 * 至于下面这个Intent的ACTION是怎么知道的，大家可以看下自己路径下的如下网页
		 * yourself_sdk_path/docs/reference/android/content/Intent.html
		 * 直接在里面Ctrl+F搜：CROP ，之前没仔细看过，其实安卓系统早已经有自带图片裁剪功能, 是直接调本地库的
		 */
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 2);
		intent.putExtra("aspectY", 1);
		intent.putExtra("scale", true);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 90);
		intent.putExtra("outputY", 90);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("return-data", false);
		intent.putExtra("noFaceDetection", true);
		startActivityForResult(intent, PIC_ZOOM_REQUEST_CODE);
	}
	
	// 拍照
	public static final String imgPath = Environment.getExternalStorageDirectory() + File.separator
			+ "tmp.jpg";
	
	// 相册
	public static final String imgPath2 = Environment.getExternalStorageDirectory() + File.separator
			+ "tmp2.jpg";
	
	/*
	 * 按钮事件处理
	 */
	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			final int id = v.getId();
			// 拍照按钮
			if (id == btnCallCamera.getId()) {
				Util.printLog(TAG, "call camera ... ");
				
				String state = Environment.getExternalStorageState();
				if (state.equals(Environment.MEDIA_MOUNTED)) {
					Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
//					Intent i = new Intent(Intent.ACTION_CAMERA_BUTTON, null);
					i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(imgPath)));
					
					startActivityForResult(i, CAMERA_REQUEST_CODE);
				}
				else {
					Toast.makeText(PhotoUploadActivity.this, 
							R.string.common_msg_nosdcard, Toast.LENGTH_LONG)
							.show();
				}
				
				
			}
			// 图库选择 
			else if (id == btnSelectPic.getId()) {
				Intent i2 = new Intent();
				i2.setType("image/*");
				i2.setAction(Intent.ACTION_PICK);
				i2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(imgPath2)));
//				Intent i3 = Intent.createChooser(i2, null);
//				i3.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(imgPath)));
				i2.setDataAndType( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
				startActivityForResult(i2, SELECT_REQUEST_CODE);
			}
			// 上传按钮
			else if (id == btnUploadPhoto.getId()) {
				Util.printLog(TAG, "upload photo ... ");
				
				if (null == saveImgPath) {
					Util.printLog(TAG, "路径为空");
					Toast.makeText(PhotoUploadActivity.this, 
							"路径为空", Toast.LENGTH_SHORT)
							.show();
//					throw new NullPointerException();
					
					return;
				}
				
				File file = new File(saveImgPath);
				if (!file.exists()) {
					Util.printLog(TAG, "该图片不存在");
					Toast.makeText(PhotoUploadActivity.this, 
							"该图片不存在", Toast.LENGTH_LONG)
							.show();
					return;
				}
				
				
//				String url = "http://192.168.1.141:8087/webstudy/fileUpload2Svlt";
//				AsyncUploadImageTask task = new AsyncUploadImageTask(url, saveImgPath, uploadPhotoProgressBar, btnUploadPhoto);
//				task.execute("TestTest");
				
//				while (!task.isComplete()) {
//					Util.printLog(TAG, "image upload ... " + task.getStatus().name());
//					try {
//						Thread.sleep(1000L);
//					}
//					catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//				Util.printLog(TAG, "image upload complete");
				
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.photo_upload, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_photo_upload,
					container, false);
			return rootView;
		}
	}

	

}

/**
 * 上传相片的任务
 * 
 * @author linger
 *
 * @since 2015-7-24
 *
 */
class AsyncUploadImageTask extends AsyncTask<String, String, String> {
	
	private static final String TAG = AsyncUploadImageTask.class.getSimpleName();
	
	private String addr, filepath;
	private ProgressBar pb;
	private Button upload;
	private boolean complete = false;
	
	AsyncUploadImageTask(String addr, String filepath, ProgressBar pb, Button upload) {
		this.addr = addr;
		this.filepath = filepath;
		this.pb = pb;
		this.upload = upload;
		
		pb.setVisibility(View.VISIBLE);
		upload.setEnabled(false);
		complete = false;
	}

	public boolean isComplete() {
		return complete;
	}

	@Override
	protected String doInBackground(String... params) {
		Util.printLog(TAG, "photo doInBackground ...");
//		publishProgress(params[0]);
		try {
			SendFileUtil.send(addr, filepath);
			Thread.sleep(5000);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return params[0];
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		Util.printLog(TAG, "photo onPostExecute ...");
		
		pb.setVisibility(View.GONE);
		upload.setEnabled(true);
		this.complete = true;
	}

	@Override
	protected void onProgressUpdate(String... values) {
		super.onProgressUpdate(values);
		Util.printLog(TAG, "photo onProgressUpdate ...");
	}
	
}


