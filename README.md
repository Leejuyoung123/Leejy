## 전자정부 표준프레임워크 커스터마이징 
### 파스타 클라우드 활용(공통)
--
Procfile = heroku 설정파일 / manifest.yml = Cloud Foundry 파스타 설정파일 / 아마존 ?
1. 스프링프로젝트 Leejy_mysql 변경.
2. 이클립스에서 Leejy_mysql 프로젝트를 파스타에 배포.(Hsql용)
3. Leejy_mysql 로컬 mysql서버와 연동처리.
4. 파스타 클라우드에서 Mysql서비스 생성.(원격접속이름과 암호를 확인가능)
5. 원격 phpmyadmin 툴(워크벤치와 비슷)을 파스타 클라우드에 PHP앱 생성 후 배포.
6. Leejy_mysql 프로젝트를 클라우드용 DB사용으로 변경 후 파스타에 	재배포.
   http://kimilguk_mysql.paas-ta.org 
7. egov_sht 프로젝트 이름 변경: Leejy_egov 파스타에 	배포(Mysql클라우드사용).
   http://Leejy_egov.paas-ta.org
***
### 20200812(수) 작업내역 (아래)
- 4. 스프링 Mysql 프로젝트를 로컬Mysql 설정 -> 클라우드 파스타 용으로 변경

```

- 1-0).서비스 생성이후 binding작업하고 푸시
```


```
- 3).작업결과 확인 이클립스에서 파스타 서버 더블 클릭 -> Application and Service 탭에서
	-> 오른쪽에 Update and Restart 버튼을 클릭해서 클라우드 배포  수정사항 적용 
- 2).root-context.xml 파일에 DB 커넥션 설정을 클라우드용으로 추가  spring 관련 설정 파일은 내 프로젝트 수정후 업데이트
- 1).pom.xml 메이븐 파일에 클라우드용 모듈 추가.
```

- 3. 로컬에서 테스트 ok 된후 클라우드에 Mysql 서비스를 생성


```
-4).http://leejy-myadmin.paas-ta.org 접속 후 edu 데이터베이스 자동생성 및 더미 데이터 입력.
-3).아래 php 프로젝트를 파스타에 푸시 (manifest.yml)사용: 클라우드 파운더리cf cli(커맨드라인 인터페이스)설치.
	https://github.com/cloudfoundry/cli#downloads
	터미널 상태에서 phpmyadmin 프로젝트로 이동
	>cf login
	API endpoint: https://api.paas-ta.org
	Email> boramcom@daum.net (본인 파스타 이메일)
	Password> (본인 파스타 패스워드)
	Authenticating...
OK
>cf push 또는 cf push -s cflinuxfs3 (cflinuxfs3으로 스택을 지정해서 배포)
-2).생성된 Mysql 서비스를 웹으로 제어하는 php 프로젝트를 이클립스로 임포트.(Leejy-myadmin 프로젝트명).
-1).온라인 파스타 클라우드에서 Leejy-mysql-db 이름의 Mysql 서비스를 생성
```

-2.Leejy-mysql 프로젝트를 Hsql - > Mysql 변경(아래)

```
- 2). Wamp 실행.후 프로젝트를 톰캣 서버로 확인
- 1). root-context.xml Hsql 주석처리 > mysql 주석해제
```


-1.어제한 내용 리뷰(아래)
-파스타 클라우드에 접속할떄 https://api.paas-ta.org 주소사용
-RestAPI 서버가 htts://api.paas-ta.org
-RestAPI 서버에 Json데이터를 보내서 앱을 생성+ 실행 하게됨

```
manifest.yml (매니페스트 야믈파일)
---
applications:
- name: Leejy-mysql
  memory: 1024M
  disk_quota: 1024M
  instances: 1
  host: leejy-mysql
  domain: paas-ta.org

```


### 20200811(화) 작업내역(아래)
-Junit test로 DAO의 selectMember 실행하기.

```
-Junit code
package Leejy_egov;

import javax.inject.Inject; //inject
import javax.sql.DataSource; //ds
import java.sql.Connection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.human.com.member.service.impl.MemberDAO;
import edu.human.org.member.service.EmployerInfoVO;
import edu.human.org.member.service.MemberService;


/*@RunWith(SpringJUnit4ClassRunner.class)

이 애노테이션을 붙여줘야 스프링 테스트를 Junit으로 돌릴 수 있음.
@ContextConfiguration(classes = { RootContextConfig.class }, loader = AnnotationConfigWebContextLoader.class)

RootContextConfig.class를 spring context의 빈 설정 파일로 사용한다는 의미.
@WebAppConfiguration

이 애너테이션을 붙이면 Controller및 web환경에 사용되는 빈들을 자동으로 생성하여 등록하게됨.*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/config/egovframework/springmvc/egov-com-servlet.xml",
		"file:src/main/resources/egovframework/spring/com/*.xml" })
@WebAppConfiguration
public class TestMember {
	@Inject
	private MemberDAO dao;
	
	@Inject
	private DataSource ds;
	
	@Test
	public void testInsertMember() throws Exception{
		EmployerInfoVO vo = new EmployerInfoVO();
	}
	@Test
	public void testSelectMember() throws Exception{
		List <EmployerInfoVO> list = dao.selectMember(); 
		//dao 에있는  쿼리메서드를 리스트에 넣어주고 List VO에있는 데이터 list 형을 
		// 출력하기위에 향상된 for문을 해줌
		for (EmployerInfoVO vo:list) {
			System.out.println("회원아이디:"+vo.getEmplyr_id());
			System.out.println("회원이름 :"+vo.getUser_nm());
		}
	}
	@Test
	public void testDbConnect() throws Exception {
		Connection con = ds.getConnection();
		System.out.println("데이터 베이스 커넥션 결과" + con);
		// 커넥션 클래스를 사용  
	}
	// junit No such Bean 파일 등록이 안되는이유는 
	// 루트를 잡아주는 경로 xml 파일이 exclude 가 되어있기떄문에 include로 경로를 
	// 바꾸면서 빈등록과동시에 error처리 해결
	@Test
	public void test() throws Exception {
		System.out.println("Junit 테스트 확인");
	}
}
3. // junit No such Bean 파일 등록이 안되는이유는 
	// 루트를 잡아주는 경로 egov-com-servlet.xml 파일이 exclude 가 되어있기떄문에 include로 경로를 
	// 바꾸면서 빈등록과동시에 error처리 해결
	junit test 중 select member에 bean이 등록 
	egov-com-servlet.xml 파일에서 component-scan 부분에서 제외한 (exclude)를 > 포함시킴(include)
2. src/test/java~ TestMember.java 추가함 @ContextConfiguration 경로 2개 추가
1.전자정부 프로젝트는 기본 junit 이 없기 떄문에 테스트 환경 만들어야함 pox.xml에 junit 모듈 추가하기
- maven update 4.3.22 jar 파일  offline으로 다운받아줘야함
<!-- Test 참조 https://offbyone.tistory.com/155 -->
<dependency>
	<groupId>junit</groupId>
	<artifactId>junit</artifactId>
	<version>4.12</version>
	<scope>test</scope>
</dependency>
<dependency>
	<groupId>javax.servlet</groupId>
	<artifactId>javax.servlet-api</artifactId>
	<version>3.0.1</version>
	<scope>test</scope>
</dependency>
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-core</artifactId>
	<version>4.3.22.RELEASE</version>
 </dependency>
 <dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-test</artifactId>
	<version>4.3.22.RELEASE</version>
</dependency>
<dependency>
	<groupId>javax.inject</groupId>
	<artifactId>javax.inject</artifactId>
	<version>1</version>
</dependency>

```


-DAO(@Repostiory), Service(@service)만들기

```
3.MemberServiceImpl.java ( 구현 클래스 ) @Resource()> @Inject 사용
2.MemberService.java ( 인터 페이스 )
1.MemberDAO.java (추상클래스를 사용 , extends EgovAbstract Mapper 추가)
```


- 프로젝트에서 Mybatis 사용하기 

```
5.
-mapper folder 생성 , config folder 생성
-Leejy2_egov/src/main/resources/egovframework/mapper/config
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<!--Mybatis 설정 -->
	<settings>
		<!-- 전통적인 데이터베이스 컬럼명 형태인 A_COLUMN을 CamelCase형태의 자바 프로퍼티명 형태인 aColumn으로 자동으로 매핑하도록 함 -->
		<setting name="mapUnderscoreToCamelCase" value="true"></setting>
		<!--  파라미터에 Null 값이 있을 경우 에러 처리 -->
		<setting name="jdbcTypeForNull" value="VARCHAR"></setting>
	</settings>
</configuration>
4.Spring-Mybatis 설정파일 context.mapper.xml
- configLoaction: 마이바티스 설정파일 위치 mapper-config.xml 추가
- mapperLocation: 쿼리가 존재하는 폴더 위치 : member_mysql.xml 추가
- Leejy2_egov/src/main/resources/egovframework/spring/com 경로에 
- context-mapper.xml 생성
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd">
    <!-- 실행환경에서 빈이름 참조(EgovAbstractDAO) -->
	<bean id="egov.lobHandler" class="org.springframework.jdbc.support.lob.DefaultLobHandler" lazy-init="true" />
	<!-- Mybatis setup for Mybatis Database Layer -->
	<bean id="egov.sqlSession" class="org.mybatis.spring.SqlSessionFactoryBean">		
		<property name="dataSource" ref="egov.dataSource"/>
		<property name="configLocation" value="classpath:/egovframework/mapper/config/mapper-config.xml" />
		<property name="mapperLocations">
			<list>
				<value>classpath:/egovframework/mapper/com/**/*_${Globals.DbType}.xml</value>
			</list>
		</property>
	</bean>
	<!-- Mybatis Session Template -->
	<bean id="egov.sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
		<constructor-arg ref="egov.sqlSession"/>
	</bean>
</beans>

3.관리자 관리 테이블과 get,set하는 VO 만들기 : EmployerinfoVO.java
  -테이블 생성 쿼리에서 필드명 복사 VO 자바파일에서 사용, 특이사항: 대 >소  ctrl shift+y 단축키 소문자로 변경
2.관리자 관리에 사용되는 테이블 확인 :emplyrinfo
1.Pom.xml 메이븐 모듈추가(아래)
<!-- 마이바티스 사용 -->
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis</artifactId>
			<version>3.2.8</version>
		</dependency>
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis-spring</artifactId>
			<version>1.2.2</version>
		</dependency>
```


### 20200810(월) 작업내역(아래)
- context-datasource.xml: Hsql 데이터베이스 사용 주석처리

```
<!-- hsql -->
<!-- 여기만 주석처리
<jdbc:embedded-database id="dataSource-hsql" type="HSQL">
	<jdbc:script location= "classpath:/db/shtdb.sql"/>
</jdbc:embedded-database>
-->
```

- globals.properties :(주,유니코드 에디터로 수정) DB에 관련된 전역변수 지정(아래)

```
# DB서버 타입(mysql,oracle,altibase,tibero) - datasource 및 sqlMap 파일 지정에 사용됨
Globals.DbType = mysql
Globals.UserName=root
Globals.Password=apmsetup
# mysql
Globals.DriverClassName=net.sf.log4jdbc.DriverSpy
Globals.Url=jdbc:mysql://127.0.0.1:3306/sht
#Hsql - local hssql 사용시에 적용
#Globals.DriverClassName=net.sf.log4jdbc.DriverSpy
#Globals.Url=jdbc:log4jdbc:hsqldb:hsql://127.0.0.1/sampledb
```
- web.xml : 톰캣(WAS)가 실행될때 불러들이는 xml설정들 확인.

```
egov-com-serlet.xml(아래) 
- DispatcherServlet(서블렛배치=콤포넌트-scan:@Controller,@Service,@Repository에 관련된 설정 수정)
- <context:component-scan base-package="egovframework,edu">
- 위에서 ,edu 추가: edu.human.com패키지추가로 해당패키지로 시작하는 콤포넌트를 빈(실행가능한 클래스)으로 자동등록하게 처리
```
- pom.xml : 메이븐 설정 파일중 Hsql DB를 Mysql DB사용으로 변경(아래)

```
<!-- 주석처리
<dependency>
	<groupId>org.hsqldb</groupId>
	<artifactId>hsqldb</artifactId>
	<version>2.3.2</version>
</dependency>
 -->
<!-- mysql driver 주석해제 -->	
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.31</version>
</dependency>

<!-- log4jdbc driver 주석해제. 기능:Console창에 쿼리보이기 -->        
<dependency>
    <groupId>com.googlecode.log4jdbc</groupId>
    <artifactId>log4jdbc</artifactId>
    <version>1.2</version>
    <exclusions>
        <exclusion>
            <artifactId>slf4j-api</artifactId>
            <groupId>org.slf4j</groupId>
        </exclusion>
    </exclusions>
</dependency>
```