package com.eai.FileTransfer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.eai.FileTransfer.vo.QueueVO;
import com.eai.FileTransfer.vo.TranVO;

@Repository 
@Mapper 
public interface FileMapper 
{
	public int insertQueue(QueueVO vo) throws Exception;
		
	
	public List<QueueVO> selectQueue() throws Exception;


	public int insertTranData(TranVO vo) throws Exception;

		

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

