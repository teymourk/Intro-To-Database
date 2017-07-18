-- This need to be uncommented to remove the primary keys before 
-- Dropping the tables could work

-- ALTER TABLE item_stores DROP FOREIGN KEY item_stores_ibfk_1;
-- ALTER TABLE item_stores DROP FOREIGN KEY item_stores_ibfk_2;
-- ALTER TABLE stores DROP FOREIGN KEY stores_ibfk_1;
-- ALTER TABLE company DROP FOREIGN KEY company_ibfk_1;


-- Drop Tables if they exist to create new ones if any changes
-- Need to be applies
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS company;
DROP TABLE IF EXISTS type;
DROP TABLE IF EXISTS item_stores;
DROP TABLE IF EXISTS stores;


-- Creating the Item table with 40 items in there
CREATE TABLE item (
	item_id INT AUTO_INCREMENT,
	type_id INT,
	company_id INT,
	price FLOAT,
	name VARCHAR(50) NOT NULL,
	CONSTRAINT item_unique_name UNIQUE (name),
	PRIMARY KEY(item_id, type_id, company_id)

)ENGINE=InnoDB;

-- Item Type table to distunguish between different types
CREATE TABLE type (
	type_id INT AUTO_INCREMENT PRIMARY KEY,
	type VARCHAR(50) NOT NULL,
	CONSTRAINT type_unique_name UNIQUE (type)	
)ENGINE=InnoDB;

-- Comapny Table for every company that makes the Item
CREATE TABLE company (
	company_id INT AUTO_INCREMENT PRIMARY KEY,
	type_id INT,
	name VARCHAR(50) NOT NULL,
	CONSTRAINT company_unique_name UNIQUE (name),
	FOREIGN KEY(type_id) REFERENCES type(type_id)
)ENGINE=InnoDB;

-- Store table for stores that have those itesm
CREATE TABLE stores (
	store_id INT AUTO_INCREMENT PRIMARY KEY,
	company_id INT,
	name VARCHAR(50) NOT NULL,
	location VARCHAR(100) NOT NULL,
	CONSTRAINT stores_unique_name UNIQUE (name),
	FOREIGN KEY(company_id) REFERENCES company(company_id)
)ENGINE=InnoDBl;

-- Includes the relationship between stores and the Items
CREATE TABLE item_stores (
	item_id INT,
	store_id INT,
	description VARCHAR(500),
	PRIMARY KEY(item_id,store_id)	
)ENGINE=InnoDBl;
	
-- Insering different types for the items in our store	
insert type(type)
	values
	('Gaming'),
	('Appliances'),
	('TV'),
	('Camera'),
	('Phone'),
	('Computer'),
	('Watch'),
	('Office'),
	('GPS');

-- Insering different Companies for the items in our store	
insert company(name)
	values
	('Microsoft'),
	('Samsung'),
	('Canon'),
	('Apple'),
	('HD'),
	('Garmin');

insert into stores(name, location) 
	values
	('Best Buy', 'USA'),
	('Costco', 'USA'),
	('Walmart', 'USA'),
	('Frys', 'USA'),
	('Circuit City', 'USA'),
	('Kmart', 'USA'),
	('Office Depot', 'USA'),
	('OfficeMax', 'USA'),
	('Sears', 'USA'),
	('Apple', 'USA'),
	('Staples', 'USA'),
	('Macys', 'USA'),
	('HomeGoods', 'USA'),
	('Target', 'USA');

-- Inserting the Items to the table for users to pick for wish list

-- Only USED FOR TESTING PURPOSES 

/*
insert into item(type_id, company_id, price, name)
	values
	((SELECT type_id FROM type WHERE type = 'Gaming'),(SELECT company_id FROM company WHERE name = 'Microsoft'), 60, 'Gears of War 4'),
	((SELECT type_id FROM type WHERE type = 'Gaming'), (SELECT company_id FROM company WHERE name = 'Microsoft'), 399.99, 'Xbox one'),
	((SELECT type_id FROM type WHERE type = 'Appliances'), (SELECT company_id FROM company WHERE name = 'Samsung'), 1529.99, 'Side-by-Side Refrigerator'),
	((SELECT type_id FROM type WHERE type = 'TV'), (SELECT company_id FROM company WHERE name = 'Samsung'), 1499.99,'4K Ultra HD'),
	((SELECT type_id FROM type WHERE type = 'Camera'), (SELECT company_id FROM company WHERE name = 'Canon'), 549.99,'EOS Rebel T6i DSLR'),
	((SELECT type_id FROM type WHERE type = 'Phone'), (SELECT company_id FROM company WHERE name = 'Apple'), 799, 'Iphone'),
	((SELECT type_id FROM type WHERE type = 'Computer'), (SELECT company_id FROM company WHERE name = 'Apple'), 2399.99, 'Macbook Pro'),
	((SELECT type_id FROM type WHERE type = 'Watch'), (SELECT company_id FROM company WHERE name = 'Apple'), 399,'Apple Watch Nike+'),
	((SELECT type_id FROM type WHERE type = 'Office'), (SELECT company_id FROM company WHERE name = 'HP'), 199.99, 'Officejet Pro 8710'),
	((SELECT type_id FROM type WHERE type = 'GPS'), (SELECT company_id FROM company WHERE name = 'Garmin'), 96.99, 'nuvi 57LM 5');

	
insert into item_stores(item_id, store_id, description)
	values
	-- Stores For Xbox One
	((SELECT item_id FROM item WHERE name = 'Xbox one'), (SELECT store_id FROM stores WHERE name = 'Best Buy'), "Geat Stuff"),
	((SELECT item_id FROM item WHERE name = 'Xbox one'), (SELECT store_id FROM stores WHERE name = 'Walmart'), "WOW"),
	((SELECT item_id FROM item WHERE name = 'Xbox one'), (SELECT store_id FROM stores WHERE name = 'Target'), "NICE"),
	((SELECT item_id FROM item WHERE name = 'Xbox one'), (SELECT store_id FROM stores WHERE name = 'Frys'), "AWESOME"),

	-- Stores For Macbook Pro
	((SELECT item_id FROM item WHERE name = 'Macbook Pro'), (SELECT store_id FROM stores WHERE name = 'Best Buy'), "Cool"),
	((SELECT item_id FROM item WHERE name = 'Macbook Pro'), (SELECT store_id FROM stores WHERE name = 'Apple'), "WOW"),
	((SELECT item_id FROM item WHERE name = 'Macbook Pro'), (SELECT store_id FROM stores WHERE name = 'Staples'), "KIAAAA"),
	((SELECT item_id FROM item WHERE name = 'Macbook Pro'), (SELECT store_id FROM stores WHERE name = 'Costco'), "SO NICE"),
	((SELECT item_id FROM item WHERE name = 'Macbook Pro'), (SELECT store_id FROM stores WHERE name = 'Circuit City'), "GOOD QUALITY"),

	-- Stores For Apple Watch Nike+
	((SELECT item_id FROM item WHERE name = 'Apple Watch Nike+'), (SELECT store_id FROM stores WHERE name = 'Macys'), "GREAT"),
	((SELECT item_id FROM item WHERE name = 'Apple Watch Nike+'), (SELECT store_id FROM stores WHERE name = 'Apple'), "WOWWW"),
	((SELECT item_id FROM item WHERE name = 'Apple Watch Nike+'), (SELECT store_id FROM stores WHERE name = 'Best Buy'), "CANT"),

	-- Stores For Officejet Pro 8710 (Printer)
	((SELECT item_id FROM item WHERE name = 'Officejet Pro 8710'), (SELECT store_id FROM stores WHERE name = 'OfficeMax'), "ME"),
	((SELECT item_id FROM item WHERE name = 'Officejet Pro 8710'), (SELECT store_id FROM stores WHERE name = 'Office Depot'), "YOU"),

	-- Stores For Side-by-Side Refrigerator
	((SELECT item_id FROM item WHERE name = 'Side-by-Side Refrigerator'), (SELECT store_id FROM stores WHERE name = 'Sears'), "KLL"),
	((SELECT item_id FROM item WHERE name = 'Side-by-Side Refrigerator'), (SELECT store_id FROM stores WHERE name = 'Target'), "OOO"),
	((SELECT item_id FROM item WHERE name = 'Side-by-Side Refrigerator'), (SELECT store_id FROM stores WHERE name = 'Best Buy'), "HAHAHA"),

	-- Stores For 4K Ultra HD	
	((SELECT item_id FROM item WHERE name = '4K Ultra HD'), (SELECT store_id FROM stores WHERE name = 'Best Buy'), "FORTO"),
	((SELECT item_id FROM item WHERE name = '4K Ultra HD'), (SELECT store_id FROM stores WHERE name = 'OfficeMax'), "BOOM"),
	((SELECT item_id FROM item WHERE name = '4K Ultra HD'), (SELECT store_id FROM stores WHERE name = 'Office Depot'), "MISSS"),
	((SELECT item_id FROM item WHERE name = '4K Ultra HD'), (SELECT store_id FROM stores WHERE name = 'Kmart'), "ISJDDJ"),
	((SELECT item_id FROM item WHERE name = '4K Ultra HD'), (SELECT store_id FROM stores WHERE name = 'Costco'), "WOW SO COOL"),

	-- Stores For Gears of War 4
	((SELECT item_id FROM item WHERE name = 'Gears of War 4'), (SELECT store_id FROM stores WHERE name = 'Staples'), "HAAHAH"),
	((SELECT item_id FROM item WHERE name = 'Gears of War 4'), (SELECT store_id FROM stores WHERE name = 'Frys'), "KIAAA"),


	-- Stores For nuvi 57LM 5 (GPS)	
	((SELECT item_id FROM item WHERE name = 'nuvi 57LM 5'), (SELECT store_id FROM stores WHERE name = 'HomeGoods'), "JJWDJWJ"),
	
	-- Stores For Iphone
	((SELECT item_id FROM item WHERE name = 'Iphone'), (SELECT store_id FROM stores WHERE name = 'Apple'), "dwjwdk"),
	((SELECT item_id FROM item WHERE name = 'Iphone'), (SELECT store_id FROM stores WHERE name = 'Best Buy'), "dfjeiejfi"),
	((SELECT item_id FROM item WHERE name = 'Iphone'), (SELECT store_id FROM stores WHERE name = 'Target'), "fjefefnjen"),
	((SELECT item_id FROM item WHERE name = 'Iphone'), (SELECT store_id FROM stores WHERE name = 'Frys'), "efiefij"),

	-- Stores For EOS Rebel T6i DSLR (Camera)
	((SELECT item_id FROM item WHERE name = 'Xbox one'), (SELECT store_id FROM stores WHERE name = 'OfficeMax'), "ekfeokfe"),
	((SELECT item_id FROM item WHERE name = 'Xbox one'), (SELECT store_id FROM stores WHERE name = 'Costco'), "efjeiej"),
	((SELECT item_id FROM item WHERE name = 'Xbox one'), (SELECT store_id FROM stores WHERE name = 'Circuit City'), "ekfeifeij");
*/








