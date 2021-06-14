alter table `products`
drop column `amount`;


delete from `orders`;
alter table `orders`
    AUTO_INCREMENT = 1;

alter table `orders`
add column `amount` int default 0;

SET FOREIGN_KEY_CHECKS=0;

insert into orders (`date_of_order`, `client_id`, `product_id`, `is_paid`, `amount`)
values
('2021-01-01 10:10:10', '1', '1', false, 1),
('2021-03-01 10:10:10', '1', '2', false, 2),
('2021-02-01 10:10:10', '1', '4', false, 1),
('2021-04-01 10:10:10', '1', '2', true, 1),
('2021-05-01 10:10:10', '1', '6', true, 1),
('2021-01-19 10:10:10', '1', '3', false, 3),
('2021-03-24 10:10:10', '2', '3', false, 2),
('2021-05-17 10:10:10', '2', '12', true, 4),
('2021-02-09 10:10:10', '2', '8', true, 3),
('2021-03-16 10:10:10', '2', '9', false, 4),
('2021-03-17 10:10:10', '2', '3', false, 2);