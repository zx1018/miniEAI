package com.eai.FileTransfer.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.eai.FileTransfer.sendReceive.Message;
import com.eai.FileTransfer.sendReceive.Sender;
import ch.qos.logback.classic.Logger;
 
@Component
public class FileTransferReceiver {
	
	private static Logger log = (Logger) LoggerFactory.getLogger(FileTransferReceiver.class);
	
    public static final int DEFAULT_BUFFER_SIZE = 10000;
    
	@Autowired(required=true)
    private Sender sender;

  
	/**
     * ���� ������ ���� Port�� �����Ѵ�. 
     * @param Message msg ActiveMq���� ������ �޼��� ��ü
     * @throws Exception
     */
    public int open(Message msg) {
        try {
        	// ���� �� Port ���Ƿ� 10344�� ���Ѵ�. 
        	int port = 10344;
            ServerSocket server = new ServerSocket(port);
            log.info("This server is listening... (Port: " + port  + ")");            
            Socket socket = server.accept();  													//���ο� ���� ���� ���� �� accept���
                       
            // ������ ���������� ����Ǹ� 
            InetSocketAddress isaClient = (InetSocketAddress) socket.getRemoteSocketAddress();
            log.info("A client("+isaClient.getAddress().getHostAddress()+" is connected. (Port: " +isaClient.getPort() + ")");
             
            // ���� ���� ���� 
            FileOutputStream fos = new FileOutputStream(msg.getDstPath()+".enc");
            InputStream is = socket.getInputStream();
             
            double startTime = System.currentTimeMillis(); 
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int readBytes;
            while ((readBytes = is.read(buffer)) != -1) {
                fos.write(buffer, 0, readBytes);
 
            }      
            double endTime = System.currentTimeMillis();
            double diffTime = (endTime - startTime)/ 1000;;
 
            log.info("time: " + diffTime+ " second(s)");
             
            is.close();
            fos.close();
            socket.close();
            server.close();
            // ���� ���� �Ϸ� 
            
            // ��ȣȭ ó�� 
            File src = new File(msg.getDstPath()+".enc");
            File dest = new File(msg.getDstPath());
            
            FileCoder coder = new FileCoder(msg.getSecretKey());
            try {
    			coder.decrypt(src, dest);
    		} catch (Exception e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
            
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1; 
        }
    }
}