USE farmDB;

CREATE TABLE IF NOT EXISTS work(
    workID BIGINT PRIMARY KEY AUTO_INCREMENT,
    workDate VARCHAR(10) NOT NULL,
    cropName VARCHAR(10) NOT NULL,
	workload INT NOT NULL,
    id BIGINT NOT NULL,
	updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id) REFERENCES member(id)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS member (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE
)DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    role VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO roles (role) values('ROLE_ADMIN');	
INSERT INTO roles (role) values('ROLE_WORKER');	
INSERT INTO roles (role) values('ROLE_TEMP');

CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES member(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);