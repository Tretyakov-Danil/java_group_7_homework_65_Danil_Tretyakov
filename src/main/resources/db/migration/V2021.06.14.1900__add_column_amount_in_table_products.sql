alter table `products`
add column `amount` int default null after `price`;

update `products`
set amount = FLOOR(RAND()*20 + 10);