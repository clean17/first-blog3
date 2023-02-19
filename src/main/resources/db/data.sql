insert into user_tb (username, password, email, PROFILE, role, created_at) values ('admin','admin','admin@nate.com', '/images/default_profile.png', 'ADMIN', now());
insert into user_tb (username, password, email, PROFILE, role, created_at) values ('ssar','1234','ssar@nate.com', '/images/default_profile.png', 'USER', now());
insert into user_tb (username, password, email, PROFILE, role, created_at) values ('love1','1234','ssar@nate.com', '/images/default_profile.png', 'USER', now());
insert into user_tb (username, password, email, PROFILE, role, created_at) values ('love2','1234','love@nate.com', '/images/default_profile.png', 'USER', now());
insert into user_tb (username, password, email, PROFILE, role, created_at) values ('love3','1234','love@nate.com', '/images/default_profile.png', 'USER', now());
insert into user_tb (username, password, email, PROFILE, role, created_at) values ('love4','1234','love@nate.com', '/images/default_profile.png', 'USER', now());
insert into user_tb (username, password, email, PROFILE, role, created_at) values ('love5','1234','love@nate.com', '/images/default_profile.png', 'USER', now());
insert into user_tb (username, password, email, PROFILE, role, created_at) values ('love6','1234','love@nate.com', '/images/default_profile.png', 'USER', now());
insert into user_tb (username, password, email, PROFILE, role, created_at) values ('love7','1234','love@nate.com', '/images/default_profile.png', 'USER', now());

insert into board_tb (title, content, user_id, thumbnail, love, created_at) values ('첫번째 제목 입니다.','첫번째 내용입니다.', 3, '/images/dora1.png', 0, now());
insert into board_tb (title, content, user_id, thumbnail, love, created_at) values ('두번째 제목 입니다.','두번째 내용입니다.', 2, '/images/dora1.png', 0, now());
insert into board_tb (title, content, user_id, thumbnail, love, created_at) values ('세번째 제목 입니다.','세번째 내용입니다.', 3, '/images/dora1.png', 0, now());
insert into board_tb (title, content, user_id, thumbnail, love, created_at) values ('네번째 제목 입니다.','네번째 내용입니다.', 3, '/images/dora1.png', 0, now());
insert into board_tb (title, content, user_id, thumbnail, love, created_at) values ('다섯번째 제목 입니다.','다섯번째 내용입니다.', 2, '/images/dora1.png', 0, now());
insert into board_tb (title, content, user_id, thumbnail, love, created_at) values ('여섯번째 제목 입니다.','여섯번째 내용입니다.', 2, '/images/dora1.png', 0, now());
insert into board_tb (title, content, user_id, thumbnail, love, created_at) values ('일곱번째 제목 입니다.','일곱번째 내용입니다.', 2, '/images/dora1.png', 0, now());

insert into reply_tb (comment, user_id, board_id, love, created_at) values ('1등 ㅋㅋ', 2, 3, 0, now());
insert into reply_tb (comment, user_id, board_id, love, created_at) values ('댓글 테스트 !!', 1, 2,0, now());
insert into reply_tb (comment, user_id, board_id, love, created_at) values ('좋은 글이네요', 1, 3,0, now());
insert into reply_tb (comment, user_id, board_id, love, created_at) values ('ㅎ1ㅎ', 2, 2,0, now());
insert into reply_tb (comment, user_id, board_id, love, created_at) values ('ㅎ2ㅎ', 2, 2,0, now());
insert into reply_tb (comment, user_id, board_id, love, created_at) values ('ㅎ3ㅎ', 2, 2,0, now());
insert into reply_tb (comment, user_id, board_id, love, created_at) values ('ㅎ4ㅎ', 2, 2,0, now());
insert into reply_tb (comment, user_id, board_id, love, created_at) values ('ㅎ5ㅎ', 2, 2,0, now());
insert into reply_tb (comment, user_id, board_id, love, created_at) values ('ㅎ6ㅎ', 2, 2,0, now());
insert into reply_tb (comment, user_id, board_id, love, created_at) values ('ㅎ677ㅎ', 2, 2,0, now());

insert into love_tb (user_id, board_id, state) values (2, 2, 1);
insert into love_tb (user_id, board_id, state) values (2, 1, 1);
insert into love_tb (user_id, board_id, state) values (2, 3, 1);
insert into love_tb (user_id, board_id, state) values (3, 4, 1);
insert into love_tb (user_id, board_id, state) values (4, 2, 1);
insert into love_tb (user_id, board_id, state) values (5, 2, 1);

commit;
