# Intro To Databases Final Project (WishList)

![alt text](https://github.com/teymourk/Intro-To-Databases/blob/master/Store%20Database/Screen%20Shot%202017-07-18%20at%206.57.29%20AM.png "WishList")

[WishList](http://web.engr.oregonstate.edu/~teymourk/WishList/WishList.php)

WishList is a simple PHP/MySQL web app.

* Check Availability of an Item in a store
* Create/Remove Items
* Create Different Item Types
* Create Different Stores
* Create Different Locations

### Database table creations

```
CREATE TABLE item (
  item_id INT AUTO_INCREMENT,
  type_id INT,
  company_id INT,
  price FLOAT,
  name VARCHAR(50) NOT NULL,
  CONSTRAINT item_unique_name UNIQUE (name),
  PRIMARY KEY(item_id, type_id, company_id)

)ENGINE=InnoDB;

```

```
CREATE TABLE type (
  type_id INT AUTO_INCREMENT PRIMARY KEY,
  type VARCHAR(50) NOT NULL,
  CONSTRAINT type_unique_name UNIQUE (type) 
)ENGINE=InnoDB;

```

```
CREATE TABLE stores (
  store_id INT AUTO_INCREMENT PRIMARY KEY,
  company_id INT,
  name VARCHAR(50) NOT NULL,
  location VARCHAR(100) NOT NULL,
  CONSTRAINT stores_unique_name UNIQUE (name),
  FOREIGN KEY(company_id) REFERENCES company(company_id)
)ENGINE=InnoDBl;

```

```
CREATE TABLE item_stores (
  item_id INT,
  store_id INT,
  description VARCHAR(500),
  PRIMARY KEY(item_id,store_id) 
)ENGINE=InnoDBl;
