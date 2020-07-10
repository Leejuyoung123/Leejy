package org.edu.controller;

import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.edu.service.IF_MemberService;
import org.edu.vo.MemberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication; //인증 //아이디 암호체크
import org.springframework.security.core.GrantedAuthority; //권한체크 /ROLE_ADMIN /ROLE_USER
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails; // 스프링에서 사용자정보 임포트
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*
 * https://all-record.tistory.com/167?category=733072
 * Handles requests for the application home page.
 * @RequestMapping에 지정된 URL로 브라우저의 요청 URL이 매핑되게 된다 만약 
 * <annotation-driven />를 지우면 URL을 찾을 수 없다는 오류가 발생한다.
 															*/
@Controller
public class HomeController {
	@Inject
	private IF_MemberService memberService;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/login_success", method = RequestMethod.GET)
	public String login_success(Locale locale, HttpServletRequest request, RedirectAttributes rdat) throws Exception {
		logger.info("Welcome login_success! The client locale is {}.", locale);
		// 스프링에서 인증값을 가져옴 
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userid = "";// id
		String levels = "";// ROLE_ANONYMOUS
		Boolean enabled = false;
		Object principal = authentication.getPrincipal();
		if (principal instanceof UserDetails) {
			//인증이 처리되는 로직(아이디,암호 스프링 시큐리티에 던져주고  인증은 스프링에서 알아서 해줌)
			enabled = ((UserDetails) principal).isEnabled();
		}
		HttpSession session = request.getSession(); // 세션이 없으면 세션을 초기화
		if (enabled) { //인증처리가 완료된 사용자의 권한 체크	( 아래 ) 
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			if (authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ANONYMOUS")).findAny().isPresent()) {
				levels = "ROLE_ANONYMOUS";
			}
			if (authorities.stream().filter(o -> o.getAuthority().equals("ROLE_USER,")).findAny().isPresent()) {
				levels = "ROLE_USER,";
			}
			if (authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent()) {
				levels = "ROLE_ADMIN";
			}
			userid=((UserDetails)principal).getUsername();
			// 로그인 세션 저장
			session.setAttribute("session_enabled", enabled);// 인증확인
			session.setAttribute("session_userid", userid); // 사용자아이디
			session.setAttribute("session_levels", levels);// 사용자권한
			// ======= 상단은 스프링 시큐리티에서 기본제공하는 세션 변수 처리

			// ======= 하단은 우리가 추가하는 세션 변수 처리
			// 회원이름 구하기 추가
			MemberVO memberVO = memberService.viewMember(userid);
			session.setAttribute("session_username", memberVO.getUser_name());// 사용자명
		}
		rdat.addFlashAttribute("msg", "로그인");// result 데이터를 숨겨서 전송
		return "redirect:/";// 새로고침 자동 등록 방지를 위해서 아래처럼 처리
		}

	/**
	 * 로그인 페이지 파일 입니다.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		return "/login";
	}

	/**
	 * SLIDE 페이지 파일 입니다.
	 */
	@RequestMapping(value = "/sample/slide", method = RequestMethod.GET)
	public String slide(Locale locale, Model model) {

		return "sample/slide";
	}

	/**
	 * CONTACT US 페이지 파일 입니다.
	 */
	@RequestMapping(value = "/sample/contactus", method = RequestMethod.GET)
	public String contactus(Locale locale, Model model) {

		return "sample/contactus";

	}

	/**
	 * BLOG 페이지 파일 입니다.
	 */
	@RequestMapping(value = "/sample/blog", method = RequestMethod.GET)
	public String blog(Locale locale, Model model) {

		return "sample/blog";
	}

	/**
	 * WORK 페이지 파일 입니다.
	 */
	@RequestMapping(value = "/sample/work", method = RequestMethod.GET)
	public String work(Locale locale, Model model) {

		return "sample/work";
	}

	/**
	 * we are 페이지 파일 입니다.
	 */
	@RequestMapping(value = "/sample/weare", method = RequestMethod.GET)
	public String weare(Locale locale, Model model) {

		return "sample/weare";
	}

	/**
	 * html5 테스트용 파일 입니다.
	 */
	@RequestMapping(value = "/sample/htmltest", method = RequestMethod.GET)
	public String htmltest(Locale locale, Model model) {

		return "sample/htmltest";
	}

	/*
	 * 샘플 홈파일입니다
	 * 
	 */

	@RequestMapping(value = "/sample", method = RequestMethod.GET)
	public String sample(Locale locale, Model model) {

		/*
		 * @Controller는 Sample 클래스를 컨트롤러로 설정하는 애노테이션이다.
		 */

		return "sample/home";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	/*
	 * servlet-context에 의해 브라우저의 요청은 HomeController로 넘어가게 된다. 
	 * 여기에서 home( ) 를 보면 현재날짜와 시간을 가져온 후 이것을 문자열을 만든다. 
	 * 이 후 작업의 결과를 model에 담고 home 라는 뷰 이름을 반환한다.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

}