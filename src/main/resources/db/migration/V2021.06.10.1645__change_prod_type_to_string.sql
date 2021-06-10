alter table products
    drop column type,
    add column type varchar(20) default null;