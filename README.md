# 블로그 프로젝트 
![blogmain](https://user-images.githubusercontent.com/118657689/236416188-deddb0b0-081a-4ec8-b76d-d6a07ff99793.jpg)


> ## 개발 목표

- DB를 설계하고 연관관계를 파악해서 기본적인 CRUD 쿼리를 작성해본다
- 블로그의 로직을 이해하고 필요한 기능 및 추가되면 좋을 기능을 생각해서 추가해본다.
- 단위 테스트를 하는것에 익숙해진다.

<br>

> ## 개발 환경

- Visual Studio Code
- Java 11


<br>

> ## 기술 스택

- JDK 11
- Spring Boot 2.7.8
- MyBatis
- 테스트 h2 DB
- 프로덕션 MySql DB
- JSP
- JUnit


<br>

> ## 기능정리

### 1단계
- 회원 가입, 로그인, 글 쓰기, 글 목록보기 (썸네일 제외), 글 상세보기, 글 삭제 , 글 수정, 썸네일
### 2단계
- 댓글 쓰기, 댓글 목록보기, 댓글 삭제, 프로픽 사진 추가
### 3단계
- 좋아요 버튼, 좋아요 갯수
### 4단계
- 아이디 중복체크, 회원수정하기
### 5단계
- 관리자 추가, 검색, 이메일

<br>

> ## 의존성 주입

	implementation 'org.springframework.boot:spring-boot-starter-mail'
	implementation 'org.jsoup:jsoup:1.15.3'
	implementation group: 'com.google.code.gson', name: 'gson', version: '2.8.9'
	implementation 'javax.servlet:jstl'
    implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.springframework.boot:spring-boot-starter-aop'
	implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:2.3.0'
	compileOnly 'org.projectlombok:lombok'
	developmentOnly 'org.springframework.boot:spring-boot-devtools'
	runtimeOnly 'com.h2database:h2'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation group: 'org.mybatis.spring.boot', name: 'mybatis-spring-boot-starter-test', version: '2.2.2'


<br>

> ## 테이블 모델링

![블로그 DB 설계](https://user-images.githubusercontent.com/118657689/236416233-ac5417f1-c25e-4f35-82be-c18e9c9719b8.jpg)

<br>

> ## 적용된 기능

- 아이디 중복체크기능
- 비밀번호 중복확인 기능
- 글 쓰기 수정 삭제 기능
- 자신이 썼던 글에 들어갈 경우만 수정 삭제 버튼 보임
- 글을 작성하면 메인화면에 작성한 글목록에 보이고 첫번째 사진이 썸네일로 사용
- ajax 비동기 통신으로 댓글 작성 삭제 기능
- 글과 댓글에 좋아요 기능 및 좋아요수 확인기능
- 프로필사진 수정 기능
- 관리자 계정 및 관리자 페이지 추가 후 작성된 글, 댓글 검색기능 및 삭제기능
- 이메일 api를 이용해서 관리자가 메일을 보내는 기능
- Sha256을 이용한 비밀번호 암호화
- Junit을 이용한 단위 테스트
- 인터셉터를 추가해 인증처리

<br>

> ## 이슈

- 좋아요 기능을 구현하려고 할때 테이블에서 삭제하지 않고 상태값만 변하게 만들고 싶었는데 여러 환경마다 방법이 달라서 시행착오 끝에 지금 환경에서는 useGenerateKey를 사용해서 id를 리턴하면 손쉽게 좋아요상태를 update할 수 있었다.

<br>

> ## 기술 블로그


- <a href="https://velog.io/@merci/series/%EB%B8%94%EB%A1%9C%EA%B7%B8-%EC%A0%9C%EC%9E%91-V1"> 블로그 링크 </a>

<br>

> ## Object Mapper 의존성 주입 방법
<br>

일반적으로 스프링부트가 실행되면 스프링 컨텍스트에 Object Mapper가 자동으로 빈으로 등록됩니다. <br>
하지만 테스트할 때는 테스트 범위에 따라 컨텍스트에 생성되는 빈들의 범위가 달라져 필요한 빈이 없을수도 있습니다. <br>
예를 들면 통합테스트를 하는 `@SpringBootTest` 는 테스트에 필요한 모든 빈들을 컨텍스트에 등록해 테스트를 진행하므로 <br>
`@Autowired`를 통해서 의존성을 가져올 수 있습니다. <br>
![image](https://github.com/clean17/first-blog3/assets/118657689/c0a03161-52be-4b13-840a-25d7ac9e2791) <br>
<br>
하지만 단위 테스트인 `@MybatisTest`일 경우 컨텍스트에 Mybatis와 관련된 빈들만 등록되어 Object Mapper 빈이 없기 때문에<br>
`@Autowired` 통해서 빈을 주입한다면 테스트 시 `UnsatisfiedDependencyException` 익셉션이 발생합니다. <br>
![image](https://github.com/clean17/first-blog3/assets/118657689/57681f9d-3167-43cf-aff0-0a290f6f2ec3) <br>
![image](https://github.com/clean17/first-blog3/assets/118657689/c0a03161-52be-4b13-840a-25d7ac9e2791) <br>
![image](https://github.com/clean17/first-blog3/assets/118657689/4a55398c-ed22-4ffd-b307-4a4daa6b143f) <br>
 <br>
따라서 `@MybatisTest` 같은 단위 테스트 시에는 다른 방법으로 의존성을 주입해야 합니다.<br>
- 첫번째 방법으로는 `@MockBean` + Mockito라이브러리의 조합으로 메소드의 동작을 정의해서 테스트를 하는 방법이 있습니다.<br>
- 두번째 방법으로는 `@TestConfiguration`를 이용해서 테스트 환경에서만 등록될 빈을 직접 등록하는 방법입니다. <br>
![image](https://github.com/clean17/first-blog3/assets/118657689/1c68cb24-cd15-4001-9c79-f8c9ec4a7538) <br>
- 세번째 방법으로는 수동으로 인스턴스를 직접 생성해서 사용하는 방법입니다.  <br>
![image](https://github.com/clean17/first-blog3/assets/118657689/19891b0a-5b85-4fba-be37-d1151c59aa22)<br>

<br>

> ## 기타 메모

단위테스트에서 세션이 필요한 경우는 `MockHttpSession` 을 직접 생성합니다. <br>
`@Autowired`를 해봤자 컨텍스트에 없기 때문에 가져오지 못합니다. <br>
![image](https://github.com/clean17/first-blog3/assets/118657689/b7df0079-d82c-4f40-a05a-2408cefb0e2c) <br>