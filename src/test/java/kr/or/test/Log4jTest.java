package kr.or.test;

import org.apache.log4j.Logger;

public class Log4jTest {

	private void test() {
	MemberVO memberVO =new MemberVO();
	memberVO.setName("홍길동");
	memberVO.setAge(30);
	
	private Logger log = Logger.getLogger(Log4jTest.class);
	log.debug(memberVO);
	log.info("info");
	log.warn("warn");
	log.error("error");
	log.fatal("fatal");
	// * logger level : debug < info < warn < error < fatal
	}

	public static void main(String[] args) {
		new Log4jTest().test();
		
	}
}
