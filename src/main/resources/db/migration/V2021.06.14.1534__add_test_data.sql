SET FOREIGN_KEY_CHECKS=0;

insert into brands (`name`)
values
('Apple'),
('Samsung'),
('Xiaomi'),
('Huawei');

insert into products (`name`, `description`, `type`, `image_path`, `price`, `brand_id`)
values
('Iphone 12', 'There is an Iphone!!', 'SMARTPHONE', 'iphone12.jpg', 799.99, 1),
('Iphone 12 Pro Max', 'There is an Iphone Pro Max!!', 'SMARTPHONE', 'iphone12promax.jpg', 1099.99, 1),
('Iphone 12 Mini', 'There is an Iphone Mini!!', 'SMARTPHONE', 'iphone12mini.jpg', 599.99, 1),
('Samsung Galaxy S20 Ultra', 'S20 Ultra', 'SMARTPHONE', 's20ultra.jpg', 999.99, 2),
('Redmi Note 10 Pro', 'Top for its own money', 'SMARTPHONE', 'note10pro.jpg', 459.99, 3),
('Redmi Note 9', 'Top for its own money', 'SMARTPHONE', 'note9.jpg', 199.99, 3),
('Redmi Note 8', 'Top for its own money', 'SMARTPHONE', 'note8.jpg', 199.99, 3),
('AirPods', 'There is an AirPods first gen', 'HEADSET', 'airpods1.jpg', 199.99, 1),
('AirPods Pro', 'There is an AirPods Pro', 'HEADSET', 'airpodspro.jpg', 299.99, 1),
('QCY T1', 'Xiaomi Headsets', 'HEADSET', 'qcyt1.jpg', 17.99, 3),
('Haylou GT2S', 'Xiaomi\'s headset (super cool)', 'HEADSET', 'haylouGT2S.jpg', 23.01, 3),
('Iphone 11 silicon case', 'Original silicon case', 'CASE', 'siliconcaseiphone11.jpg', 2.99, 1),
('Iphone 12 silicon case', 'Original silicon case', 'CASE', 'siliconcaseiphone12.jpg', 2.99, 1),
('Iphone SE 2gen silicon case', 'Original silicon case', 'CASE', 'siliconcaseiphoneSE2gen.jpg', 2.99, 1);

insert into orders (`date_of_order`, `client_id`, `product_id`, `is_paid`)
values
('2021-01-01 10:10:10', '1', '1', false),
('2021-03-01 10:10:10', '1', '2', false),
('2021-02-01 10:10:10', '1', '4', false),
('2021-04-01 10:10:10', '1', '2', true),
('2021-05-01 10:10:10', '1', '6', true),
('2021-01-19 10:10:10', '1', '3', false),
('2021-03-24 10:10:10', '2', '3', false),
('2021-05-17 10:10:10', '2', '12', true),
('2021-02-09 10:10:10', '2', '8', true),
('2021-03-16 10:10:10', '2', '9', false),
('2021-03-17 10:10:10', '2', '3', false);