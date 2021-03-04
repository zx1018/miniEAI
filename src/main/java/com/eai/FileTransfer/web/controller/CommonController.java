package com.eai.FileTransfer.web.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eai.FileTransfer.service.FileService;
import com.eai.FileTransfer.vo.QueueVO;

import ch.qos.logback.classic.Logger;
 
@Controller
public class CommonController {
	
	private static Logger log = (Logger) LoggerFactory.getLogger(CommonController.class);
	
	@Autowired(required=true)
    private FileService service;
    
    @RequestMapping("/insertQueue")
    public ModelAndView sndFile(HttpServletRequest request) throws Exception{
    	
    	ModelAndView mav = new ModelAndView("list");
    	
    	log.info("큐 리스트 인서트 진입 ! ");
    	
    	QueueVO vo = new QueueVO();
    	vo.setSvr_name(request.getParameter("svr_name"));
    	vo.setQueue_name(request.getParameter("queue_name"));
    	
    	
    	int x = service.insertQueue(vo);
    	
    	List<QueueVO>  list = service.selectQueue();
    	
		mav.addObject("list", list);
		
		return mav;    
    }
 
    @RequestMapping("/selectSvrQ")
    public ModelAndView demo_test() throws Exception{
    	
    	ModelAndView mav = new ModelAndView("list");
    	
    	log.info("큐 리스트 파일 진입 ! ");
    	
    	List<QueueVO>  list = service.selectQueue();
    	
		mav.addObject("list", list);
		
		return mav;    
    } 
}

