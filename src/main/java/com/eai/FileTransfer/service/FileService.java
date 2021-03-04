package com.eai.FileTransfer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eai.FileTransfer.mapper.FileMapper;
import com.eai.FileTransfer.vo.QueueVO;
import com.eai.FileTransfer.vo.TranVO;

@Service
public class FileService {

	@Autowired 
	public FileMapper mapper;

	
	public int insertTranData(TranVO vo) throws Exception {
		return mapper.insertTranData(vo);
	}
	
	public List<QueueVO> selectQueue() throws Exception {
		return mapper.selectQueue();
	}
	
	public int insertQueue(QueueVO vo) throws Exception {
		return mapper.insertQueue(vo);
	}

//	public int updateQueue() {
//		
//	}
//	
//	public int updateQueue() {
//		
//	}
//	
//	public int insertTrData() {
//		
//	}
//	
//	public int selectTrData() {
//		
//	}


}
