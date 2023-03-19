# 블로그 프로젝트 2
> ## 기술 스택
- JDK 11
- Spring Boot 2.7.8
- MyBatis
- 테스트 h2 DB
- 프로덕션 MySql DB
- JSP


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
- 검색

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

```
create table user_tb (
    id int auto_increment primary key,
    username varchar not null unique,
    password varchar not null,
    email varchar not null,
    profile varchar,
    role varchar,
    created_at timestamp not null
);

create table board_tb (
    id int auto_increment primary key,
    title varchar(100) not null,
    content longtext not null,
    user_id int not null,
    thumbnail longtext not null,
    love int ,
    created_at timestamp not null
);

create table reply_tb (
    id int auto_increment primary key,
    comment varchar(100) not null,
    user_id int not null,
    board_id int not null,
    love int ,
    created_at timestamp not null
);

create table love_tb (
    id int auto_increment primary key,
    user_id int not null,
    board_id int not null,
    state number(1),
    created_at timestamp not null
);
```

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

<br>

> ## 이슈
- 좋아요 기능을 구현하려고 할때 테이블에서 삭제하지 않고 상태값만 변하게 만들고 싶었는데 여러 환경마다 방법이 달라서 시행착오 끝에 지금 환경에서는 useGenerateKey를 사용해서 id를 리턴하면 손쉽게 좋아요상태를 update할 수 있었다.

<br>

> ## 기술 블로그
- https://velog.io/@merci/series/%EB%B8%94%EB%A1%9C%EA%B7%B8-%EC%A0%9C%EC%9E%91-V1
