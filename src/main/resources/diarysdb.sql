-- 建立日记表
USE diarydb;

CREATE TABLE diarys
(
    Title       VARCHAR(64) PRIMARY KEY,
    CreateDate  VARCHAR(64) NOT NULL,
    Content     MEDIUMTEXT
);