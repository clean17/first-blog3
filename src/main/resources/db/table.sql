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