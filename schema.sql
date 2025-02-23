CREATE DATABASE tododb;
USE tododb;
gr
CREATE TABLE todos (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       description TEXT,
                       completed BOOLEAN DEFAULT FALSE
);
