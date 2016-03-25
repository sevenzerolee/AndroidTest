package com.foxit.pdfviewer.pdfcore;

import java.util.ArrayList;

import android.graphics.RectF;

public class FPV_Structs {
	public static class CommunicateResult {
		public String			mResult;
		public int				mLength;
	}
	
	public static class OutlineItem {
		public String			mTitle;
		public int				mPageIndex;
		public float			mX;
		public float			mY;
		public int				mLevel;
		public boolean			mHaveChild;
		public ArrayList<OutlineItem>	mChildren;
		public boolean 			mIsExpanded;
		public int				mNdkAddr;
		
		public OutlineItem() {
			mTitle = null;
			mPageIndex = 0;
			mX = 0;
			mY = 0;
			mLevel = -1;
			mHaveChild = true;
			mChildren = new ArrayList<FPV_Structs.OutlineItem>();
			mIsExpanded = false;
			mNdkAddr = 0;
		}
		
		public OutlineItem(String title, int pageIndex, float x, float y, int level, boolean haveChild, int ndkAddr) {
			mTitle = title;
			mPageIndex = pageIndex;
			mX = x;
			mY = y;
			mLevel = level;
			mHaveChild = haveChild;
			mChildren = new ArrayList<FPV_Structs.OutlineItem>();
			mIsExpanded = false;
			mNdkAddr = ndkAddr;
		}
		
		public boolean haveChild() {
			return mHaveChild;
		}
	}
	
	public static class SearchResult {
		public int				mPageIndex;
		public String			mSentence;
		public int				mPatternStart;
		public ArrayList<RectF>	mRects;
		
		public SearchResult(int pageIndex, String sentence, int patternStart) {
			mPageIndex = pageIndex;
			mSentence = sentence;
			mPatternStart = patternStart;
			mRects = new ArrayList<RectF>();
		}
	}
	
	public static class DocumentInfo {
		public String			mAuthor;
		public String			mSubject;
		public String			mCreateTime;
		public String			mModTime;
	}
	
	public static class LinkInfo {
		public RectF			mRect;
		public boolean			mIsDest;
		public String			mURI;
		public int				mPageIndex;
		public float			mX;
		public float			mY;
		
		public LinkInfo(RectF rect, boolean isDest, String uri, int pageIndex, float x, float y) {
			mRect = rect;
			mIsDest = isDest;
			mURI = uri;
			mPageIndex = pageIndex;
			mX = x;
			mY = y;
		}
	}
}

