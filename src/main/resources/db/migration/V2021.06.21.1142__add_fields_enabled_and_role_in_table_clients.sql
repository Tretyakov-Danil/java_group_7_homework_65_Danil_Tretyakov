alter table `clients`
add column `enabled` int not null default 1 after `password`,
add column `role` varchar(20) not null default 'USER' after `enabled`;