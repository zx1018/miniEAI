package com.eai.FileTransfer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@ComponentScan("com.eai.FileTransfer")  // ��ĵ ���� ����
public class FileTransferPrjApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(FileTransferPrjApplication.class, args);
	}

}
