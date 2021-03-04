package com.eai.FileTransfer.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.LoggerFactory;
import com.eai.FileTransfer.sendReceive.Message;
import ch.qos.logback.classic.Logger;

public class FileTransferSender {

	private static Logger log = (Logger) LoggerFactory.getLogger(FileTransferSender.class);

	public static final int DEFAULT_BUFFER_SIZE = 10000;

	public void start(Message msg) {

		File file = new File(msg.getSrcPath()); // ������ �����ϴ��� ���ϴ���
		File fileEnc = new File(msg.getSrcPath() + ".enc"); // ��ȣȭ ���� ����
		if (!file.exists()) {
			log.info("File not Exist.");
			return;
		}
		// ��ȣȭ ó��
		FileCoder coder = new FileCoder(msg.getSecretKey());
		try {
			coder.encrypt(file, fileEnc);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if ("socket".equals(msg.getSndType())) {
			socketSnd(file, fileEnc, msg);
		} else {
			// �ϴ� �⺻ FTP�� ���� �Ѵ�.
			ftpSnd(file, fileEnc, msg);
		}

	}

	// FTP ���� ���
	private void ftpSnd(File file, File fileEnc, Message msg) {
		// FTP�� �����ϱ� ���� Ŭ������ �����Ѵ�.
		FTPClient client = new FTPClient();
		try {
			// connection ȯ�濡�� UTF-8�� ���ڵ� Ÿ���� ����Ѵ�.
			client.setControlEncoding("UTF-8");
			// ftp://localhost�� �����Ѵ�.
			client.connect(msg.getRcvip(), Integer.parseInt(msg.getRcvPort()));
			// ������ Ȯ�ݳ���.
			int resultCode = client.getReplyCode();
			// ���ӽ� ������ ������ �ֿܼ� ���� �޽����� ǥ���ϰ� ���α׷��� �����Ѵ�.
			if (!FTPReply.isPositiveCompletion(resultCode)) {
				log.info("FTP server refused connection.!");
				return;
			} else {
				// ���� ���۰� ���� ������ ���� (1ms ������ ������ 1000�̸� 1��)
				client.setSoTimeout(1000);
				// �α����� �Ѵ�.
				if (!client.login("ftp", "1234qwer")) { // Ű ������ ���߿� ������ �ʿ��ϴ�.
					// �α����� �����ϸ� ���α׷��� �����Ѵ�.
					log.info("Login Error!");
					return;
				}
				client.setBufferSize(1000);
				client.enterLocalPassiveMode();
				client.setFileType(FTP.BINARY_FILE_TYPE);

				InputStream input = new FileInputStream(fileEnc);
				String remoteFilePath = msg.getDstPath();

				if (!client.storeFile(remoteFilePath, input)) {   // ���� ���� 
					log.info("File Send Failed");
					throw new Exception("File store failed");
				}
			}
		} catch (Exception e) {

		}

	}

	// ���� ���� ���
	private void socketSnd(File file, File fileEnc, Message msg) {
		long fileSize = file.length();
		long totalReadBytes = 0;
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		int readBytes;
		double startTime = 0;

		try {
			FileInputStream fis = new FileInputStream(fileEnc);
			Socket socket = new Socket(msg.getRcvip(), Integer.parseInt(msg.getRcvPort()));
			if (!socket.isConnected()) {
				System.out.println("Socket Connect Error.");
				System.exit(0);
			}

			startTime = System.currentTimeMillis();
			OutputStream os = socket.getOutputStream();
			while ((readBytes = fis.read(buffer)) > 0) {
				os.write(buffer, 0, readBytes);
				totalReadBytes += readBytes;
				log.info("In progress: " + totalReadBytes + "/" + fileSize + " Byte(s) ("
						+ (totalReadBytes * 100 / fileSize) + " %)");
			}
			log.info("File transfer completed.");
			fis.close();
			os.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		double endTime = System.currentTimeMillis();
		double diffTime = (endTime - startTime) / 1000;
		;
		double transferSpeed = (fileSize / 1000) / diffTime;

		log.info("time: " + diffTime + " second(s)");
		log.info("Average transfer speed: " + transferSpeed + " KB/s");

	}
}
