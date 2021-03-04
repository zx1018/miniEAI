package com.eai.FileTransfer.vo;

public class TranVO {

	private String tranData;
	
	
	public TranVO() {
		
	}

	// getter, setter
	public String getTranData() {
		return tranData;
	}

	public void setTranData(String tranData) {
		this.tranData = tranData;
	}

	// toString »ý¼º 
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("tranData:" +this.tranData);
		
		return sb.toString();
	}


}
