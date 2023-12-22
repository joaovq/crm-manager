CREATE TABLE user_tb (
  id VARCHAR(255) NOT NULL,
   username VARCHAR(30) NOT NULL,
   password VARCHAR(18) NOT NULL,
   user_role VARCHAR(255) NOT NULL,
   user_active BOOLEAN,
   CONSTRAINT pk_user_tb PRIMARY KEY (id)
);

ALTER TABLE user_tb ADD CONSTRAINT uc_user_tb_username UNIQUE (username);