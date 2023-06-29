create table if not exists `ssia`.`token`
(
    `id`         bigint         not null auto_increment,
    `identifier` varchar(45) null,
    `token`      text        null,
    primary key (`id`),
    unique index ux_identifier (`identifier`)
);
