CREATE TABLE customer_tb (
  id UUID NOT NULL,
   customer_first_name VARCHAR(255) NOT NULL,
   customer_last_name VARCHAR(255) NOT NULL,
   customer_cpf VARCHAR(255) NOT NULL,
   CONSTRAINT pk_customer_tb PRIMARY KEY (id)
);

ALTER TABLE customer_tb ADD CONSTRAINT uc_customer_tb_customer_cpf UNIQUE (customer_cpf);