insert ignore into `ssia`.`user` (`user_id`, `username`, `password`)
values (1, 'user', /* password = 12345 */ '{bcrypt}$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG');

insert ignore into `ssia`.`authority` (`authority_id`, `user`, `name`)
values (1, 1, 'READ');
insert ignore into `ssia`.`authority` (`authority_id`, `user`, `name`)
values (1, 1, 'WRITE');

insert ignore into `ssia`.`product` (`product_id`, `name`, `price`, `currency`)
values (1, 'Chocolate', 10, 'USD');
