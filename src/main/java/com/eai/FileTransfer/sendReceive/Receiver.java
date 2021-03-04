package com.eai.FileTransfer.sendReceive;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.eai.FileTransfer.service.FileService;
import com.eai.FileTransfer.vo.QueueVO;
import com.eai.FileTransfer.vo.TranVO;

import ch.qos.logback.classic.Logger;

@Component
public class Receiver {
	
	private static Logger log = (Logger) LoggerFactory.getLogger(Receiver.class);
	
	@Autowired(required=true)
    private Sender sender;
	
	@Autowired(required=true)
    private FileService service;
	
	// server1 snd측 큐를 모니터링 한다.  
	@JmsListener(destination = "snd-queue-server1", containerFactory="jsaFactory")
	public void receiveMessage(Message msg) throws Exception{
		log.info("Received Src");
		log.info(msg.toString());
		sendTargetMessage(msg);
	}

	// server2 snd측 큐를 모니터링 한다.
	@JmsListener(destination = "snd-queue-server2", containerFactory="jsaFactory")
	public void receiveMessage2y(Message msg) throws Exception{
		log.info("Received Dst");
		log.info(msg.toString());
		sendTargetMessage(msg);
	}
	
	// server3 snd측 큐를 모니터링 한다.
	@JmsListener(destination = "snd-queue-server3", containerFactory="jsaFactory")
	public void receiveMessage3(Message msg) throws Exception{
		log.info("Received Dst");
		log.info(msg.toString());
		sendTargetMessage(msg);
	}
	
	private void sendTargetMessage(Message msg) throws Exception {
		
		String sndQueue = "";
		List<QueueVO>  list = service.selectQueue();
		for(int i =0 ; i<list.size(); i++) {
			if(msg.getTgtSverName().equals(list.get(i).getSvr_name())) {
				sndQueue = list.get(i).getQueue_name();
			}
		}
		log.info(msg.toString());
		
		try {
			TranVO vo = new TranVO();
			vo.setTranData(msg.toString());
			service.insertTranData(vo);
		}catch(Exception e) {
			
		}
		
		sender.sndDstMsg(msg, sndQueue);
	}
}