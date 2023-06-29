create table if not exists `ssia`.`user`
(
    `user_id`   bigint      not null auto_increment,
    `username`  varchar(45) not null,
    `password`  text        not null,
    primary key (`user_id`),
    unique index `ux_username` (`username`)
);

create table if not exists `ssia`.`authority`
(
    `authority_id` bigint      not null auto_increment,
    `user`         bigint      not null,
    `name`         varchar(45) not null,
    primary key (`authority_id`),
    unique index `ux_user_name` (`user`, `name`),
    constraint `fk_authority_user_user` foreign key (`user`) references `ssia`.`user` (user_id) on delete cascade
);

create table if not exists `ssia`.`product`
(
    `product_id` bigint      not null auto_increment,
    `name`       varchar(45) not null,
    `price`      bigint      not null,
    `currency`   varchar(45) not null,
    primary key (`product_id`),
    index `ix_name` (`name`)
);
