create table if not exists `ssia`.`user`
(
    `user_id`  bigint       not null auto_increment,
    `username` varchar(45)  not null,
    `password` text         not null,
    `email`    varchar(320) not null,
    primary key (`user_id`),
    unique index ux_username (`username`)
);

create table if not exists `ssia`.`otp`
(
    `username` varchar(45) not null,
    `code`     varchar(45) not null,
    primary key (`username`),
    constraint fk_otp_user_username
        foreign key (`username`) references `ssia`.`user` (`username`)
);