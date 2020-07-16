package org.edu.controller;

import java.util.List;

import javax.inject.Inject;

import org.edu.service.IF_ReplyService;
import org.edu.vo.ReplyVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/* -리 프레젠테이션 (재가공)*/

/**
 * REST API 서비스 (댓글 서비스) == Rest full 서비스
 * @author hu-25
 *
 */
@RestController //(앱 제공 인터페이스) 서비스 (댓글 서비스)
@RequestMapping("/reply")
	public class ReplyController {
	@Inject
	private IF_ReplyService replyService;
	/**
	 *  댓글 입력 서비스
	 */
	@RequestMapping(value ="/insert", method = RequestMethod.POST)
	public ResponseEntity<String> insertReply(@RequestBody ReplyVO replyVO) {
		ResponseEntity<String> entity;
		try {
			replyService.insertReply(replyVO);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity; // JSON 데이터를 리턴 (JSP 페이지로)
	}
	/**
	 *  댓글 리스트 서비스
	 */
	
	@RequestMapping(value ="/select/{bno}",method=RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> selectReply(@PathVariable("bno")Integer bno){
		ResponseEntity<List<ReplyVO>> entity = null;
		try {
			entity = new ResponseEntity<>(replyService.selectReply(bno),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
