package com.foxit.pdfviewer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.foxit.pdfviewer.pdfcore.FPV_Structs.CommunicateResult;
import com.foxit.util.Util;

public class FPV_NetSupportImpl implements IFPV_NetSupport {
	
	private String serviceUrl;
	// 保持session
	private static String sessionId = null;

	@Override
	public int beginSession(String serviceUrl) {
		this.serviceUrl = serviceUrl;
		return 1;
	}

	@Override
	public int sendAndReceive(int session, int type, String data,
			CommunicateResult result) {
		StringBuffer parameter = new StringBuffer();
		parameter.append(serviceUrl).append("?");
		parameter.append("XmlContent=").append(Util.encodeParam(data));
		
		BufferedReader br = null;
		try {
			URL url = new URL(parameter.toString());
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setRequestProperty("Connection", "close");
			if (null != sessionId) {
				urlConn.setRequestProperty("Cookie", sessionId);
			}
			
			InputStream input = urlConn.getInputStream();
			
			if (null == sessionId) {
				String cookie = urlConn.getHeaderField("Set-Cookie");
				sessionId = cookie.substring(0, cookie.indexOf(";"));
			}
			
			if (null == input) {
				return 0;
			}
			
			br = new BufferedReader(new InputStreamReader(input));
			StringBuffer sb = new StringBuffer(2048);
			String line = null;			
			while (null != br && null != (line = br.readLine())) {
				sb.append(line).append("\n");
			}
			
			return 1;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
		finally {
			if (null != br) {
				try {
					br.close();
				}
				catch (IOException e) {
				}
			}
		}
	}

	@Override
	public int endSession(int session) {
		this.serviceUrl = null;
		sessionId = null;
		
		return 1;
	}

}
