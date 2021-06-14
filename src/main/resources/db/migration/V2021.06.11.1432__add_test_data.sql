insert into brands (`name`)
values
('Apple'),
('Samsung'),
('Xiaomi'),
('Huawei');

insert into product (`name`, `description`, `type`, `imagePath`, `price`, `brand`)
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
('QCY T1', 'Xiaomi Headsets', 'HEADSET', 'qcyt1.jpg', 17.99, 1),
('Haylou GT2S', 'Xiaomi\'s headset (super cool)', 'HEADSET', 'haylouGT2S.jpg', 23.01, 1),