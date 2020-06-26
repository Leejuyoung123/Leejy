package org.edu.controller;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.edu.service.IF_BoardService;
import org.edu.service.IF_MemberService;
import org.edu.vo.BoardVO;
import org.edu.vo.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class AdminController {
	@Inject
	private IF_BoardService boardService;
	@Inject
	private IF_MemberService memberService;
	/*
	 * 게시물 관리 리스트 입니다.
	 * 
	 */
	
	@RequestMapping(value = "/admin/board/list", method = RequestMethod.GET)
	public String boardList(Locale locale, Model model) throws Exception {
	/*	@Controller는 Sample
	 *  locale // 다국어 프로그램 만들 떄 필요함
	 *클래스를 컨트롤러로 설정하는 애노테이션이다.
	 								*/
		List<BoardVO> list = boardService.selectBoard();
		//모델 클래스로 JSP 화면으로 list memberService.select list 한값을  memberList 변수명으로 보낸다.
		//model { list > boardList > jsp }
		model.addAttribute("boardList",list);
		return "admin/board/board_list";
	}
	
	/*
	 * 회원관리 리스트 입니다.
	 * 
	 */

	@RequestMapping(value = "/admin/member/list", method = RequestMethod.GET)
	public String memberList(Locale locale, Model model) throws Exception {
		List<MemberVO> list = memberService.selectMember();
		//모델 클래스로 JSP 화면으로 list memberService.select list 한값을  memberList 변수명으로 보낸다.
		//model { list > memberList > jsp }
		model.addAttribute("memberList",list);
		/*	@Controller는 Sample
		 *  locale // 다국어 프로그램 만들 떄 필요함
		 *클래스를 컨트롤러로 설정하는 애노테이션이다.
		 								*/
		
		return "admin/member/member_list";
	}
	/*
	 * 회원관리 상세보기 입니다.
	 * 
	 */
	@RequestMapping(value = "/admin/member/view", method = RequestMethod.GET)
	public String memberview(Locale locale, Model model) throws Exception {
		
		return "admin/member/member_view";
	}
	/*
	 * 샘플 홈파일입니다
	 * 
	 */

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		/*	@Controller는 Sample
		 *  locale // 다국어 프로그램 만들 떄 필요함
		 *클래스를 컨트롤러로 설정하는 애노테이션이다.
		 								*/

		return "admin/home";
	}
}
