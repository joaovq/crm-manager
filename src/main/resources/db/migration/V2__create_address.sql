CREATE TABLE address_tb (
  address_id UUID NOT NULL,
   cep VARCHAR(8) NOT NULL,
   public_place_address VARCHAR(255),
   complement_address VARCHAR(255),
   neighborhood_address VARCHAR(255),
   locality_address VARCHAR(255),
   uf VARCHAR(255),
   ibge_address VARCHAR(255),
   gia_address VARCHAR(255),
   ddd_address VARCHAR(255),
   siafi_address VARCHAR(255),
   customer_id UUID,
   CONSTRAINT pk_address_tb PRIMARY KEY (address_id)
);

ALTER TABLE address_tb ADD CONSTRAINT FK_ADDRESS_TB_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer_tb (id);