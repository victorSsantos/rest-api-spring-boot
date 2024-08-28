CREATE TABLE IF NOT EXISTS `person` (
  `id` bigint AUTO_INCREMENT PRIMARY KEY,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `gender` tinyint NOT NULL
) AUTO_INCREMENT = 1;
