alter table `clients`
drop column `password`,
add column `password` varchar(80) default null;