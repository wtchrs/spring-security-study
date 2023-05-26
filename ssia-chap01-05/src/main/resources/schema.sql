create table if not exists `ssia`.`users`
(
    `id`       int         not null auto_increment,
    `username` varchar(45) not null,
    `password` varchar(45) not null,
    `enabled`  int         not null,
    primary key (`id`),
    unique index `ux_username` (`username`)
);

create table if not exists `ssia`.`authorities`
(
    `id`        int         not null auto_increment,
    `username`  varchar(45) not null,
    `authority` varchar(45) not null,
    primary key (`id`),
    unique index `ux_username_authority` (`username`, `authority`),
    constraint `fk_authorities_users_username`
        foreign key (`username`) references `ssia`.`users` (`username`) on delete cascade on update cascade
);