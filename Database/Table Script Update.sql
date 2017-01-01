CREATE TABLE admin_levels (
	Admin_Level int AUTO_INCREMENT,
    Admin_Level_Name Varchar(50) NOT NULL,
    PRIMARY KEY(Admin_Level)
);

INSERT INTO admin_levels (Admin_Level, Admin_Level_Name) 
Values (null, 'Admin'),
	(null, 'Key Holder');

ALTER TABLE admin_accounts
ADD COLUMN Admin_Level int,
ADD CONSTRAINT fk_admin_level
	FOREIGN KEY Admin_Level(Admin_Level)
	REFERENCES admin_levels(Admin_Level),
ADD COLUMN Username Varchar(50),
ADD COLUMN Password Varchar(50);

ALTER TABLE raffle_item
ADD COLUMN Date_To_Raffle DATE;

CREATE TABLE people_in_raffle (
	Raffle_Id int NOT NULL,
    Person_Id int NOT NULL,
    Raffle_Status Varchar(50),
    FOREIGN KEY(Raffle_Id) REFERENCES raffle_item(Raffle_Id),
    FOREIGN KEY(Person_Id) REFERENCES person(Person_Id)
);

INSERT INTO system_values(Category, Value)
VALUES ('Item recency days', '30');