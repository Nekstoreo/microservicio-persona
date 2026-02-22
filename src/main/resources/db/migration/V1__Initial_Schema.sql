-- Initial table for Persons
CREATE TABLE IF NOT EXISTS persons (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Unique person identifier',
    name VARCHAR(150) NOT NULL COMMENT 'Person full name',
    email VARCHAR(150) NOT NULL UNIQUE COMMENT 'Person email (unique)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Persons';
