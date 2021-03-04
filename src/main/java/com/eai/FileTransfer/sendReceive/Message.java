package com.eai.FileTransfer.sendReceive;

public class Message {

	private String fileName;       // ���ϸ�
	private String secretKey;      // ���� ��ȣȭ Ű 
	private String transactionKey; // ���� �ŷ� ��ȣ 
	private String sndType;        // ���۹��
	private String fileSize;       // ���� ������ 
	private String srcPath;        // ���� ���� ��� 
	private String dstPath;        // ���� ���� ��� 
	private String dstFileName;    // ���� ���ϸ� 
	private String rcvip;          // ������ ip ����
	private String rcvPort;        // ������ Port ����  
	private String sndRst;         // ���� �۽� ��� 
	private String fileHash;       // ������ �ؽ� �� 
	private String messageType;    // �������� 1 ���� ��û, 2 ���� �㰡 3 ���� ���� 4 ���� �Ϸ� 
	private String tgtSverName;    // ������ ���� ������
	private String srcSverName;    // ������ ������ ������ 

	
	public Message() {
		
	}
	
	// getter, setter
	public String getTgtSverName() {
		return tgtSverName;
	}

	public String getSrcSverName() {
		return srcSverName;
	}

	public void setSrcSverName(String srcSverName) {
		this.srcSverName = srcSverName;
	}

	public void setTgtSverName(String tgtSverName) {
		this.tgtSverName = tgtSverName;
	}

	public String getMessageType() {
		return messageType;
	}


	public String getDstFileName() {
		return dstFileName;
	}



	public void setDstFileName(String dstFileName) {
		this.dstFileName = dstFileName;
	}



	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getRcvip() {
		return rcvip;
	}

	public void setRcvip(String rcvip) {
		this.rcvip = rcvip;
	}


	public String getSecretKey() {
		return secretKey;
	}


	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}


	public String getTransactionKey() {
		return transactionKey;
	}


	public void setTransactionKey(String transactionKey) {
		this.transactionKey = transactionKey;
	}


	public String getSndType() {
		return sndType;
	}


	public void setSndType(String sndType) {
		this.sndType = sndType;
	}


	public String getFileSize() {
		return fileSize;
	}


	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}


	public String getSrcPath() {
		return srcPath;
	}


	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}


	public String getDstPath() {
		return dstPath;
	}


	public void setDstPath(String dstPath) {
		this.dstPath = dstPath;
	}


	public String getRcvPort() {
		return rcvPort;
	}


	public void setRcvPort(String rcvPort) {
		this.rcvPort = rcvPort;
	}


	public String getSndRst() {
		return sndRst;
	}


	public void setSndRst(String sndRst) {
		this.sndRst = sndRst;
	}


	public String getFileHash() {
		return fileHash;
	}


	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}
	
	// toString ���� 
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("dstPath:" +this.dstPath);
		sb.append(" fileHash:" +this.fileHash);
		sb.append(" fileName:" +this.fileName);
		sb.append(" fileSize:" +this.fileSize);
		sb.append(" secretKey:" +this.secretKey);
		sb.append(" sndPort:" +this.rcvPort);
		sb.append(" rcvip:" +this.rcvip);
		sb.append(" sndRst:" +this.sndRst);
		sb.append(" sndType:" +this.sndType);
		sb.append(" srcPath:" +this.srcPath);
		sb.append(" transactionKey:" +this.transactionKey);
		sb.append(" messageType:" +this.messageType);
		sb.append(" dstFileName:" +this.dstFileName);
		sb.append(" tgtSverName:" +this.tgtSverName);
		
		return sb.toString();
	}

}
