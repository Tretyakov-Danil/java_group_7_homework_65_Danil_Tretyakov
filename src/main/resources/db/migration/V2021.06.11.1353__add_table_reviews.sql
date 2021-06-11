CREATE TABLE `reviews` (
    `id` int NOT NULL AUTO_INCREMENT,
    `date_of_review` datetime(6) DEFAULT NULL,
    `review` varchar(255) DEFAULT NULL,
    `client_id` int DEFAULT NULL,
    `product_id` int DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKo2cmyvyjrvumg4b3de9dcvfxa` (`client_id`),
    KEY `FKpl51cejpw4gy5swfar8br9ngi` (`product_id`),
    CONSTRAINT `FKo2cmyvyjrvumg4b3de9dcvfxa` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`),
    CONSTRAINT `FKpl51cejpw4gy5swfar8br9ngi` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;