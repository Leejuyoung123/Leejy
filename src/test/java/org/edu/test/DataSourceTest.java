package org.edu.test;


import java.sql.Connection;		//testconnection
import java.util.List;			//List
import javax.inject.Inject;		//inject
import javax.sql.DataSource; //ds

import org.edu.service.IF_MemberService;    //service IF_MemberService 인터페이스 호출 현재 패키지는 org.edu.test
import org.edu.vo.MemberVO;	//vo 패키지 memberVO 호출
import org.junit.Test;		
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@WebAppConfiguration
public class DataSourceTest {
	@Inject
	private DataSource ds;
	@Inject
	private IF_MemberService memberService;
	
	@Test
	public void testConnection() throws Exception {

		try (Connection con = ds.getConnection()) {
			System.out.println("=========================================================");
			System.out.println("데이터베이스 커넥션 성공:" + con);
			System.out.println("=========================================================");
		}
	}

	@Test
	public void testSelectMember() throws Exception{
		System.out.println("회원 리스트 입니다");
		List<MemberVO> list = memberService.selectMember();
		
		for(MemberVO vo:list) {
			System.out.println("사용자 아이디"+ vo.getUser_id());
			System.out.println("사용자 이메일"+ vo.getEmail());
		}
	}
}