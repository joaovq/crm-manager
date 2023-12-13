ALTER TABLE customer_tb ADD COLUMN address_id UUID null;

ALTER TABLE customer_tb ADD CONSTRAINT FK_CUSTOMER_TB_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES address_tb (address_id);