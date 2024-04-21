CREATE DATABASE IF NOT EXISTS dinner;
USE dinner;

CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    passwd VARCHAR(255) NOT NULL,
    sex ENUM('M', 'F', 'O') DEFAULT 'O',
    age INT,
    image_url VARCHAR(2048),
    email VARCHAR(255) NOT NULL UNIQUE,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    status TINYINT
);

CREATE TABLE IF NOT EXISTS friends (
    user_id INT,
    friend_id INT,
    status TINYINT,
    PRIMARY KEY (user_id, friend_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (friend_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS favorite_restaurants (
    user_id INT,
    rest_id INT,
    PRIMARY KEY (user_id, rest_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (rest_id) REFERENCES restaurants(rest_id)
);

CREATE TABLE IF NOT EXISTS restaurants (
    rest_id INT AUTO_INCREMENT PRIMARY KEY,
    rest_name VARCHAR(255) NOT NULL,
    rest_rating DECIMAL(3,2),
    image_url VARCHAR(2048),
    address TEXT
);

CREATE TABLE IF NOT EXISTS status_want_to_eats (
    rest_id INT,
    user_id INT,
    PRIMARY KEY (rest_id, user_id),
    FOREIGN KEY (rest_id) REFERENCES restaurants(rest_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS pairs (
    user_id_self INT,
    user_id_other INT,
    PRIMARY KEY (user_id_self, user_id_other),
    FOREIGN KEY (user_id_self) REFERENCES users(user_id),
    FOREIGN KEY (user_id_other) REFERENCES users(user_id)
);
