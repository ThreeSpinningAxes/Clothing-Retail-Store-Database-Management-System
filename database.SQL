CREATE TABLE Store
(	
	name			Varchar(255);
        Address			Varchar(255);
        primary key store_ID	Varchar(20);
);

CREATE TABLE Store_Address
(
	phone			Varchar(20);
	foreign key Address;
);

CREATE TABLE Employee
(	
	primary key Employee_ID 	Varchar(20);
        phone				Varchar(20);
        foreign key Store_ID;
);

CREATE TABLE Employee_Phone
(	
        name			Varchar(255);
	Address 		Varchar(255);
	foreign key phone;
);

CREATE TABLE Shipping_type
(
        primary key Shipping_type_ID	Varchar(20);
	Shipping_Courier		Varchar(255);
	Shipping_Cost			Int;
);

CREATE TABLE Shipping
(
        Shipping_TrackingNo	Int;
        Shipping_status		Varchar(255);
        primary key Shipping_ID	Varchar(255);
        foreign key Shipping_Type_ID
);

CREATE TABLE Shipping_Tracking
(
	Shipping_dest_address			Varchar(255);
	Shipping_origin_address			Varchar(255);
        primary key Shipping_Tracking_No	Int;
);


CREATE TABLE Account
(
	password		Varchar(255);
	card_number		Int;
        primary key Account_ID	Varchar(20);   
);

CREATE TABLE Card
(
	primary key card_number	Int;
        card_expiration		date;
	name			Varchar(255);
	DOB			date;
	email			Varchar(255);
	CVV			Int;
);


CREATE TABLE Payment_Method
(
	Payment_Method			Varchar(255);
        primary key Payment_Method_ID;	Varchar(20);
);


CREATE TABLE Customer
(
        foreign key Account_ID, Payment_Method_ID
);

CREATE TABLE Customer_AccountID
(
	Customer_Name		Varchar(255);
        foreign key Account_ID	Varchar(20);
);


CREATE TABLE Orders
(
	Order_Price		Int;	
        primary key Order_ID	Varchar(20);
        foreign key Account_ID, Shipping_ID;
);

CREATE TABLE Order_Shipping
(
	Bill_ID			Varchar(20);
        foreign key Shipping_ID;
);

CREATE TABLE OrderHistory
(
	foreign key Account_ID, Order_ID;
);

CREATE TABLE Supplier
(
	Supplier_Email			Varchar(255);
	Supplier_Phone			Varchar(20); 
        Supplier_Address		Varchar(255); 
	Supplier_firstTransaction 	date;
	Supplier_lastTransaction	date;
        primary key Supplier_ID 	Varchar(20);
);

CREATE TABLE Item_Category
(
	Category_Name			Varchar(255);
        primary key Category_ID		Varchar(20);
);

CREATE TABLE Item
(
	Item_Name		Varchar(255);
	Item_TotalSupplied	Int;
        primary key Item_ID	Varchar(20);
        foreign key Category_ID;
);

CREATE TABLE Inventory
(
	Total_Value		Int;
	Total_Items		Int;
        primary key Inventory_ID;
        foreign key Item_ID, Store_ID;
);

Insert Into Store("Best Jeans", "345 Lake Shore Avenue", "ty723098");
Insert Into Store_Address("345 Lake Shore Avenue", "416-750-4580");
Insert Into Employee("Jake", "647-457-2319", "Best Jeans");
Insert Into Employee_Address("Jake", "43 ShiWong Avenue", "647-457-2319");

Insert Into Shipping_type("7879", "Express", 90);
Insert Into Shipping(6723895, "Shipped", "yu7754", "7879");
Insert Into Shipping_Tracking("56 Penthouse Avenue", "345 Lake Shore Avenue", 459236);

Insert Into Account("rt5678fg", 653892409, "bl9045");
Insert Into Card(653892409, "2025-06-09", "Nori", "2000-06-30", "Nori@gmail.com", 378);

Insert Into Payment_Method("Card", "567ac91");
Insert Into Customer("rt5678fg", "567ac91");
Insert Into Customer_AccountID("Fili Wang", "567ac91");

Insert Into Orders(45, "89456HRT", "rt5678fg", "yu7754");
Insert Into Order_Shipping("80n6f7", "yu7754");
Insert Into OrderHistory("rt5678fg", "89456HRT");

Insert Into Supplier("Wang@gmail.com", "68346790", "70 Falls Avenue", "2015-04-15", "2018-06-07", "wang9023");
Insert Into Item_Category("Pants", "32kifo89");
Insert Into Item("Winter Pants", "40", "yk746io", "32kifo89");
Insert Into Inventory(350, 100, "evb5q789u", "yk746io", "ty723098");
 