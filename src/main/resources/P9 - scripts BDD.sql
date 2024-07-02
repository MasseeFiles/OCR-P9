
-- Creation des tables de la BDD
CREATE TABLE patient (
    patient_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
	birth_date DATE NOT NULL,
    genre VARCHAR(1) NOT NULL,
    address VARCHAR(100),
    phone_number VARCHAR(20)
    );

-- Remplissage table patient
INSERT INTO patient (first_name , last_name , birth_date , genre , address, phone_number)
    VALUES
    ('TestNone' , 'Test' , '1966-12-31' , 'F' , '1 Brookside St' , '100-222-3333'),
    ('TestBorderline' , 'Test' , '1945-06-24' , 'M' , '2 High St ' , '200-333-4444'),
    ('TestInDanger' , 'Test' , '2004-06-18' , 'M' , '3 Club Road' , '300-444-5555'),
    ('TestEarlyOnset' , 'Test' , '2002-06-28' , 'F' , '4 Valley Dr' , '400-555-6666');


















CREATE TABLE bid_list (
    bid_list_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    account VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    bid_quantity DOUBLE NOT NULL,
    ask_quantity DOUBLE,
    bid DOUBLE,
    ask DOUBLE,
    benchmark VARCHAR(50),
    bid_list_date TIMESTAMP,
    commentary VARCHAR(200),
    security VARCHAR(50),
    status VARCHAR(50),
    trader VARCHAR(50),
    book VARCHAR(50),
    creation_name VARCHAR(50),
    creation_date TIMESTAMP,
    revision_name VARCHAR(200),
    revision_date TIMESTAMP,
    deal_name VARCHAR(200),
    deal_type VARCHAR(200),
    source_list_id VARCHAR(200),
    side VARCHAR(200),
    );

CREATE TABLE curve_point (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    curve_id INT,
    as_of_date TIMESTAMP,
    term DOUBLE NOT NULL,
    curve_value DOUBLE NOT NULL,
    creation_date TIMESTAMP
    );

CREATE TABLE rule (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL,
    description VARCHAR(200) NOT NULL,
    json VARCHAR(200) NOT NULL,
    template VARCHAR(200) NOT NULL,
    sql_str VARCHAR(200) NOT NULL,
    sql_part VARCHAR(200) NOT NULL
    );

CREATE TABLE trade (
    trade_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    account VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    buy_quantity DOUBLE NOT NULL,
    sell_quantity DOUBLE,
    buy_price DOUBLE,
    sell_price DOUBLE,
    benchmark VARCHAR(50),
    trade_date TIMESTAMP,
    security VARCHAR(50),
    status VARCHAR(50),
    trader VARCHAR(50),
    book VARCHAR(50),
    creation_name VARCHAR(50),
    creation_date TIMESTAMP,
    revision_name VARCHAR(200),
    revision_date TIMESTAMP,
    deal_name VARCHAR(200),
    deal_type VARCHAR(200),
    source_list_id VARCHAR(200),
    side VARCHAR(200),
    );

CREATE TABLE _user (
    user_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(200) NOT NULL,
    password VARCHAR(200) NOT NULL,
    full_name VARCHAR(200) NOT NULL,
    role VARCHAR(200) NOT NULL,
    );

-- Remplissage table rating
INSERT INTO rating (moodys_rating , sandp_rating , fitch_rating , order_number)
    VALUES
    ('AA' , 'BB' , 'CC' , '1'),
    ('DD' , 'EE' , 'FF' , '2'),
    ('GG' , 'HH' , 'II' , '3');

-- Remplissage table bid_list
INSERT INTO bid_list (account, type, bid_quantity)
    VALUES
    ('account1' , 'type1' , '1.00'),
    ('account2' , 'type2' , '1.00'),
    ('account3' , 'type3' , '1.00');

-- Remplissage table curve_point
INSERT INTO curve_point (term, curve_value)
    VALUES
    ('1.00' , '1.00'),
    ('2.00' , '2.00'),
    ('3.00' , '3.00');

-- Remplissage table rule
INSERT INTO rule (name, description, json, template, sql_str, sql_part)
    VALUES
    ('name1' , 'description1' , 'json1' , 'template1' , 'sqlStr1', 'sqlPart1' ),
    ('name2' , 'description2' , 'json2' , 'template2' , 'sqlStr2', 'sqlPart2'),
    ('name3' , 'description3' , 'json3' , 'template3' , 'sqlStr3', 'sqlPart3');

-- Remplissage table trade
INSERT INTO trade (account, type, buy_quantity)
    VALUES
    ('account1' , 'type1' , '1.00'),
    ('account2' , 'type2' , '2.00'),
    ('account3' , 'type3' , '3.00');

-- Remplissage table user
INSERT INTO _user (user_name, password, full_name, role)
    VALUES
    ('user1' , 'pasS123?' , 'tomName' , 'admin'),
    ('user2' , 'pasS123?' , 'jackName' , 'user'),
    ('user3' , 'pasS123?' , 'lilyName' , 'admin');
