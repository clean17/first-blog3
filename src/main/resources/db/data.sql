insert into user_tb (username, password, email, PROFILE, role, created_at) values ('admin','admin','admin@nate.com', '/images/default_profile.png', 'ADMIN', now());
insert into user_tb (username, password, email, PROFILE, role, created_at) values ('ssar','1234','ssar@nate.com', '/images/default_profile.png', 'USER', now());
insert into user_tb (username, password, email, PROFILE, role, created_at) values ('love','1234','love@nate.com', '/images/default_profile.png', 'USER', now());

insert into board_tb (title, content, user_id, thumbnail, created_at) values ('첫번째 제목 입니다.','첫번째 내용입니다.', 1, '/images/dora1.png', now());
insert into board_tb (title, content, user_id, thumbnail, created_at) values ('두번째 제목 입니다.','두번째 내용입니다.', 2, '/images/dora1.png', now());
insert into board_tb (title, content, user_id, thumbnail, created_at) values ('세번째 제목 입니다.','세번째 내용입니다.', 1, '/images/dora1.png', now());
insert into board_tb (title, content, user_id, thumbnail, created_at) values ('네번째 제목 입니다.','네번째 내용입니다.', 1, '/images/dora1.png', now());
insert into board_tb (title, content, user_id, thumbnail, created_at) values ('다섯번째 제목 입니다.','다섯번째 내용입니다.', 2, '/images/dora1.png', now());
insert into board_tb (title, content, user_id, thumbnail, created_at) values ('여섯번째 제목 입니다.','여섯번째 내용입니다.', 2, '/images/dora1.png', now());
insert into board_tb (title, content, user_id, thumbnail, created_at) values ('일곱번째 제목 입니다.','일곱번째 내용입니다.', 2, '/images/dora1.png', now());

insert into reply_tb (comment, user_id, board_id, created_at) values ('1등 ㅋㅋ', 2, 1, now());
insert into reply_tb (comment, user_id, board_id, created_at) values ('댓글 테스트 !!', 1, 2,now());
insert into reply_tb (comment, user_id, board_id, created_at) values ('좋은 글이네요', 1, 1,now());
insert into reply_tb (comment, user_id, board_id, created_at) values ('ㅎㅎ', 2, 2,now());

insert into love_tb (count, user_id) values (1, 1);

commit;
