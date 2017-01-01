CREATE TABLE person (
	Person_Id int AUTO_INCREMENT,
    Last_Name varchar(255) NOT NULL,
    First_Name varchar(255) NOT NULL,
    Rank varchar(20),
    Dependent_Status varchar(1),
    Military_Id_Exp_Date date,
    Email_Address varchar(255),
    Work_Phone varchar(12),
    Cell_Phone varchar(12),
    Birth_Date date,
    Organization varchar(255),
    PRIMARY KEY(Person_Id)
);

CREATE TABLE person_link (
	Sponsor_Id int NOT NULL,
    Dependent_Id int NOT NULL,
    FOREIGN KEY(Sponsor_Id) REFERENCES person(Person_Id),
    FOREIGN KEY(Dependent_Id) REFERENCES person(Person_Id)
);

CREATE TABLE sign_in_history (
	Person_Id int NOT NULL,
    Time_In datetime,
    Time_Out datetime,
    Role varchar(50),
    FOREIGN KEY(Person_Id) REFERENCES person(Person_Id)
);

CREATE TABLE item_type (
	Type_Id int AUTO_INCREMENT,
    Type_Name varchar(50),
    PRIMARY KEY(Type_Id)
);

CREATE TABLE item (
	Item_Id int AUTO_INCREMENT,
    Name varchar(50),
    Type_Id int,
    PRIMARY KEY(Item_Id),
    FOREIGN KEY(Type_Id) REFERENCES item_type(Type_Id)
);

CREATE TABLE checkout_history (
	Person_Id int NOT NULL,
    Item_Id int,
    Raffle_Id int,
    Quantity int,
    Checkout_Date datetime,
    FOREIGN KEY(Person_Id) REFERENCES person(Person_Id),
    FOREIGN KEY(Item_Id) REFERENCES item(Item_Id),
    FOREIGN KEY(Raffle_Id) REFERENCES raffle_item(Raffle_Id)
);

CREATE TABLE raffle_item (
	Raffle_Id int AUTO_INCREMENT,
    Name varchar(50),
    Description varchar(100),
    Type_Id int,
    Status varchar(20),
    Date_In datetime,
    Date_Raffled datetime,
    FOREIGN KEY(Type_Id) REFERENCES item_type(Type_Id),
    PRIMARY KEY(Raffle_Id)
);

CREATE TABLE system_values (
	Category varchar(50) NOT NULL,
    Value varchar(50) NOT NULL
);

INSERT INTO system_values (Category, Value)
Values ('position', 'E1'), 
('position', 'E2'), ('position', 'E3'), 
('position', 'E4'), ('position', 'E5'), 
('position', 'E6'), ('position', 'E7'), 
('position', 'E8'), ('position', 'E9'),
('position', 'O1'), ('position', 'O2'), 
('position', 'O3'), ('position', 'O4'), 
('position', 'O5'), ('position', 'O6'), 
('position', 'O7'), ('position', 'O8'), 
('position', 'O9'), ('position', 'O10'),
('position', 'GS'), ('position', 'Civilian'), 
('position', 'Spouse'), ('position', 'Dependent'),
('position', 'Other');

INSERT INTO system_values (Category, Value)
Values ('required field', 'firstName'),
('required field', 'lastName'),
('required field', 'cellPhone'),
('required field', 'rank');

CREATE TABLE admin_accounts (
	Person_Id int NOT NULL,
    FOREIGN KEY(Person_Id) REFERENCES person(Person_Id)
);
