USE diarydb;

CREATE TABLE USERS
(
    UserName        VARCHAR(255)    PRIMARY KEY,
    UserPassword    VARCHAR(255)    NOT NULL
);

INSERT INTO USERS(UserName,UserPassword) VALUES('drew','123456');


