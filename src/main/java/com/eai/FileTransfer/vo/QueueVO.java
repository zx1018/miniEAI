package com.eai.FileTransfer.vo;

public class QueueVO {

	private String svr_name;       // 서버명 
	private String queue_name;     // dst 큐명  
	
	public String getSvr_name() {
		return svr_name;
	}

	public void setSvr_name(String svr_name) {
		this.svr_name = svr_name;
	}

	public String getQueue_name() {
		return queue_name;
	}

	public void setQueue_name(String queue_name) {
		this.queue_name = queue_name;
	}
	
	// toString 생성 
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("svr_name:" +this.svr_name);
		sb.append(" queue_name:" +this.queue_name);
		
		return sb.toString();
	}

}
