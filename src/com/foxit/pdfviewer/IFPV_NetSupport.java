package com.foxit.pdfviewer;

import com.foxit.pdfviewer.pdfcore.FPV_Structs.CommunicateResult;

public interface IFPV_NetSupport {
	public int				beginSession(String serviceUrl);
	public int				sendAndReceive(int session, int type, String data, CommunicateResult result);
	public int				endSession(int session);
}
