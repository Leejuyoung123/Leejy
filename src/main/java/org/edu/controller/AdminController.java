package org.edu.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.edu.service.IF_BoardService;
import org.edu.service.IF_MemberService;
import org.edu.vo.BoardVO;
import org.edu.vo.MemberVO;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {
	/* 
	 *  웹에서 처리해야할 데이터를 받고 이 데이터를 담당할 서비스를 호출
	 *  처리한 데이터는 다음페이지에서 볼수있게 셋팅 이동할페이지 리턴
	 *  service 는 > dao <> vo 와  상호<>
	 *  dao는 db에 테이블 컬럼값을 자바에서 객체로 다루기위해 사용	
	 * https://melonpeach.tistory.com/16?category=806570
	 */
	@Inject
	private IF_BoardService boardService;
	
	@Inject
	private IF_MemberService memberService;
	//첨부파일 업로드 경로 변수값으로 가져옴 servlet-context.xml
	@Resource(name="uploadPath")
	private String uploadPath;
	
	/**
	 * 게시물 상세보기에서 첨부파일 다운로드 메서드 구현
	 */
	@RequestMapping(value="/download", method=RequestMethod.GET)
	@ResponseBody
	public FileSystemResource fileDownload(@RequestParam("filename") String fileName, HttpServletResponse response) {
		File file = new File(uploadPath + "/" + fileName);
		response.setContentType("application/download; utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		return new FileSystemResource(file);
	}
	/**
	 * 파일 업로드 메서드(공통)
	 * @throws IOException 
	 */
	public String[] fileUpload(MultipartFile file) throws IOException {
		String originalName = file.getOriginalFilename();//jsp에서 전송받은 파일의 이름 가져옴
		UUID uid = UUID.randomUUID();//랜덤문자 구하기
		String saveName = uid.toString() + "." + originalName.split("\\.")[1];//한글 파일명 처리 때문에...
		String[] files = new String[] {saveName};//형변환
		byte[] fileData = file.getBytes();
		File target = new File(uploadPath, saveName);
		FileCopyUtils.copy(fileData, target);
		return files;
		/*      
		한글 파일명 처리 떄문에 
		img[] images.jsp (.) 을기준으로해서 분리 
		img[1] images 
		img[2] jpg  확장자
		.을 분리하려면 "\\." 
		== FilecopyUtils.copy ==
		파일 전송 순서
		1.JSP 게시판 입력폼 첨부파일을 포함 입력버튼 클릭
		2.서버로 전송 /tmp 폴더 이동
		3.copy 명령어가 실행되면 서버에 /tmp 내용이 c:/egov/workspace/upload 폴더 저장.
		 */
	}
	
	/**
	 * 게시물관리 리스트 입니다.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/admin/board/list", method = RequestMethod.GET)
	public String boardList(Locale locale, Model model) throws Exception {
		List<BoardVO> list = boardService.selectBoard();
		//모델클래스로 jsp화면으로 boardService에서 셀렉트한 list값을 boardList변수명으로 보낸다.
		//model { list -> boardList -> jsp }
		model.addAttribute("boardList", list);
		return "admin/board/board_list";
	}
	/**
	 * 게시물관리 상세보기 입니다.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/admin/board/view", method = RequestMethod.GET)
	public String boardView(@RequestParam("bno") Integer bno,Locale locale, Model model) throws Exception {
		BoardVO boardVO = boardService.viewBoard(bno);
		//여기서 부터 첨부파일명 때문에 추가
		List<String> files = boardService.selectAttach(bno);
		String[] filenames = new String[files.size()];
		int cnt = 0;
		for(String fileName : files) {
			filenames[cnt++] = fileName;
		}
		//여러개 파일에서 1개 파일만 받는 것으로 변경
		//String[] filenames = new String[] {files};
		boardVO.setFiles(filenames);//String[]
		//여기까지 첨부파일때문에 추가
		model.addAttribute("boardVO", boardVO);
		return "admin/board/board_view";
	}
	
	/**
	 * 게시물관리 > 등록 입니다.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/admin/board/write", method = RequestMethod.GET)
	public String boardWrite(Locale locale, Model model) throws Exception {
		
		return "admin/board/board_write";
	}
	@RequestMapping(value = "/admin/board/write", method = RequestMethod.POST)
	public String boardWrite(MultipartFile file,BoardVO boardVO,Locale locale, RedirectAttributes rdat) throws Exception {
		//System.out.println("========첨부파일없이 저장===" + file.getOriginalFilename());
		if(file.getOriginalFilename() == "") {
			//첨부파일 없이 저장
			boardService.insertBoard(boardVO);
		}else {
			String[] files = fileUpload(file);
			boardVO.setFiles(files);
			boardService.insertBoard(boardVO);			
		}
		rdat.addFlashAttribute("msg", "입력");
		return "redirect:/admin/board/list";
	}
	
	/**
	 * 게시물관리 > 수정 입니다.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/admin/board/update", method = RequestMethod.GET)
	public String boardUpdate(@RequestParam("bno") Integer bno, Locale locale, Model model) throws Exception {
		BoardVO boardVO = boardService.viewBoard(bno);
		model.addAttribute("boardVO", boardVO);
		return "admin/board/board_update";
	}
	@RequestMapping(value = "/admin/board/update", method = RequestMethod.POST)
	public String boardUpdate(MultipartFile file,BoardVO boardVO,Locale locale, RedirectAttributes rdat) throws Exception {
		if(file.getOriginalFilename() == "") {
			boardService.updateBoard(boardVO);
		}else{
			// 기존 등록 된  첨부파일 삭제처리(아래)
			List<String> delFiles = boardService.selectAttach(boardVO.getBno());
			//String[] filenames = new String[delFiles.size()];//삭제할 파일명 뽑아오기
			for(String fileName : delFiles) {
				//삭제 명령문 추가(아래)
				File target = new File(uploadPath, fileName);
				if(target.exists()) { // 조건: 해당 경로에 파일이 존재하면
					target.delete(); // 파일 삭제
				
			}
			}//end for
			//아래에서 부터 신규 파일 업로드
			String[] files = fileUpload(file);//실제 파일 업로드 후 파일명 배열로 리턴	
			boardVO.setFiles(files); // 데이터베이스 <-> VO(get,set) <->dao 클래스
			boardService.updateBoard(boardVO); 
		}
		
		rdat.addFlashAttribute("msg", "수정");
		return "redirect:/admin/board/view?bno=" + boardVO.getBno();
	}
	
	/**
	 * 게시물관리 > 삭제 입니다.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/admin/board/delete", method = RequestMethod.POST)
	public String boardDelete(@RequestParam("bno") Integer bno, Locale locale, RedirectAttributes rdat) throws Exception {
		List<String> files = boardService.selectAttach(bno);
		
		boardService.deleteBoard(bno);
		
		//첨부파일 삭제(아래)
		for(String fileName : files) {
			//삭제 명령문 추가(아래)
			File target = new File(uploadPath, fileName);
			if(target.exists()) {
				target.delete();
			}
		}		
		
		rdat.addFlashAttribute("msg", "삭제");
		return "redirect:/admin/board/list";
	}
	
	
	/**
	 * 회원관리 리스트 입니다.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/admin/member/list", method = RequestMethod.GET)
	public String memberList(Locale locale, Model model) throws Exception {
		List<MemberVO> list = memberService.selectMember();
		//모델클래스로 jsp화면으로 memberService에서 셀렉트한 list값을 memberList변수명으로 보낸다.
		//model { list -> memberList -> jsp }
		model.addAttribute("memberList", list);
		return "admin/member/member_list";
	}
	
	/**
	 * 회원관리 상세보기 입니다.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/admin/member/view", method = RequestMethod.GET)
	public String memberView(@RequestParam("user_id") String user_id, Locale locale, Model model) throws Exception {
		MemberVO memberVO = memberService.viewMember(user_id);
		model.addAttribute("memberVO", memberVO);
		return "admin/member/member_view";
	}
	
	/**
	 * 회원관리 > 등록 입니다.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/admin/member/write", method = RequestMethod.GET)
	public String memberWrite(Locale locale, Model model) throws Exception {
		
		return "admin/member/member_write";
	}
	@RequestMapping(value = "/admin/member/write", method = RequestMethod.POST)
	public String memberWrite(MemberVO memberVO, Locale locale, RedirectAttributes rdat) throws Exception {
		memberService.insertMember(memberVO);
		rdat.addFlashAttribute("msg", "입력");
		return "redirect:/admin/member/list";
	}
	
	/**
	 * 회원관리 > 수정 입니다.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/admin/member/update", method = RequestMethod.GET)
	public String memberUpdate(@RequestParam("user_id") String user_id, Locale locale, Model model) throws Exception {
		MemberVO memberVO = memberService.viewMember(user_id);
		model.addAttribute("memberVO", memberVO);
		return "admin/member/member_update";
	}
	@RequestMapping(value = "/admin/member/update", method = RequestMethod.POST)
	public String memberUpdate(MemberVO memberVO, Locale locale, RedirectAttributes rdat) throws Exception {
		memberService.updateMember(memberVO);
		rdat.addFlashAttribute("msg", "수정");
		return "redirect:/admin/member/view?user_id=" + memberVO.getUser_id();
	}
	
	/**
	 * 회원관리 > 삭제 입니다.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/admin/member/delete", method = RequestMethod.POST)
	public String memberDelete(@RequestParam("user_id") String user_id, Locale locale, RedirectAttributes rdat) throws Exception {
		memberService.deleteMember(user_id);
		rdat.addFlashAttribute("msg", "삭제");
		return "redirect:/admin/member/list";
	}
	
	/**
	 * 관리자 홈 입니다.
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "admin/home";
	}
	
}