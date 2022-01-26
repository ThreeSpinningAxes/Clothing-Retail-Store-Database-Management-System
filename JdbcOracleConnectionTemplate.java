import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Properties;
import java.util.Scanner;
 
/**
 * This program demonstrates how to make database connection with Oracle
 
 *
 */
public class JdbcOracleConnectionTemplate {
 
    public static void main(String[] args) {
 
        Connection conn1 = null;

        try {
            // registers Oracle JDBC driver - though this is no longer required
            // since JDBC 4.0, but added here for backward compatibility
            Class.forName("oracle.jdbc.OracleDriver");
 
           
           String dbURL1 = "jdbc:oracle:thin:dchudasa/07125539@oracle.scs.ryerson.ca:1521:orcl";  // that is school Oracle database and you can only use it in the labs
																						
         	
             //String dbURL1 = "jdbc:oracle:thin:username/password@localhost:1521:xe";
			/* This XE or local database that you installed on your laptop. 1521 is the default port for database, change according to what you used during installation. 
			xe is the sid, change according to what you setup during installation. */
			
			conn1 = DriverManager.getConnection(dbURL1);
            if (conn1 != null) {
                System.out.println("Connected with connection #1");
            }
 
            //While loop for UI to be constantly running in console
            int x = 1;
			while(x == 1)
			{
				// scanner for user input
				Scanner s = new Scanner(System.in);
				
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("=================================================================");
			    System.out.println("|                  Clothing Retail Store Database               |");
			    System.out.println("|             Main Menu - Select Desired Operation(s):          |");
			    System.out.println("-----------------------------------------------------------------");
			    System.out.println(" Press 1 to Create Tables ");
			    System.out.println();
			    System.out.println(" Press 2 to Drop Tables");
			    System.out.println();
			    System.out.println(" Press 3 to Populate Tables");
			    System.out.println();
			    System.out.println(" Press 4 to Query Tables");
			    System.out.println();
			    System.out.println(" Press E to Exit");
			    
			    String choice = s.nextLine();
			    
			    // Exit program if E is input
			    if (choice.equals("E"))
			    {
			    	System.exit(0);
			    }
			    
			    // Create tables if 1 is input
			    else if (choice.equals("1"))
			    {

			    	try (Statement stmt1 = conn1.createStatement()) {
			    		
			    		String q1 = "CREATE TABLE Store_Address\r\n" + 
			    				"(\r\n" + 
			    				"   Address	Varchar(255),\r\n" + 
			    				"   primary key(Address),\r\n" + 
			    				"   phone Varchar(12)\r\n" + 
			    				") ";
			    		stmt1.executeUpdate(q1);
			    		System.out.println("CREATED TABLE Store_Address");
			    		
			    		
			    		String q2 = "CREATE TABLE Store\r\n" + 
			    				"(	\r\n" + 
			    				"	store_ID        Varchar(20),\r\n" + 
			    				"    name			Varchar(255),\r\n" + 
			    				"    Address			Varchar(255),\r\n" + 
			    				"    primary key (store_ID),\r\n" + 
			    				"    foreign key(Address) REFERENCES Store_Address(Address)\r\n" + 
			    				")";
			    		stmt1.executeUpdate(q2);
			    		System.out.println("CREATED TABLE Store");
			    		
						
			    		String q3 = "CREATE TABLE Employee_Phone\r\n" + 
			    				"(\r\n" + 
			    				"Employee_name Varchar(255),\r\n" + 
			    				"Address Varchar(255),\r\n" + 
			    				"phone Varchar(20),\r\n" + 
			    				"primary key(phone)\r\n" + 
			    				")";
			    		stmt1.executeUpdate(q3);
			    		System.out.println("CREATED TABLE Employee_Phone");
			    		
			    		
			    		String q4 = "CREATE TABLE Employee\r\n" + 
			    				"(\r\n" + 
			    				"Employee_ID     Varchar(20),\r\n" + 
			    				"phone           Varchar(20),\r\n" + 
			    				"store_ID        Varchar(20),\r\n" + 
			    				"primary key (Employee_ID),\r\n" + 
			    				"foreign key (phone) REFERENCES Employee_Phone(phone),\r\n" + 
			    				"foreign key (store_ID) REFERENCES Store(store_ID)\r\n" + 
			    				")";
			    		stmt1.executeUpdate(q4);
			    		System.out.println("CREATED TABLE Employee");
			    		
			    		
			    		String q5 = "CREATE TABLE Shipping_type\r\n" + 
			    				"(\r\n" + 
			    				"    Shipping_type_ID	Varchar(20),\r\n" + 
			    				"    primary key (Shipping_type_ID),\r\n" + 
			    				"	Shipping_Courier		Varchar(255),\r\n" + 
			    				"	Shipping_Cost			Int\r\n" + 
			    				")";
			    		stmt1.executeUpdate(q5);
			    		System.out.println("CREATED TABLE Shipping_type");
			    		
			    		
			    		String q6 = "CREATE TABLE Shipping\r\n" + 
			    				"(\r\n" + 
			    				"        Shipping_TrackingNo	Int,\r\n" + 
			    				"        Shipping_status		Varchar(255),\r\n" + 
			    				"        Shipping_ID	Varchar(255),\r\n" + 
			    				"        Shipping_Type_ID Varchar(20),\r\n" + 
			    				"        primary key(Shipping_ID),\r\n" + 
			    				"        foreign key (Shipping_Type_ID) REFERENCES Shipping_Type(Shipping_type_ID)\r\n" + 
			    				")";
			    		stmt1.executeUpdate(q6);
			    		System.out.println("CREATED TABLE Shipping");
			    		
			    		
			    		String q7 = "CREATE TABLE Card\r\n" + 
			    				"(\r\n" + 
			    				"	card_number	Int,\r\n" + 
			    				"    primary key (card_number),\r\n" + 
			    				"    card_expiration		date,\r\n" + 
			    				"	card_name			Varchar(255),\r\n" + 
			    				"	DOB			date,\r\n" + 
			    				"	email		Varchar(255),\r\n" + 
			    				"	CVV			Int\r\n" + 
			    				")";
			    		stmt1.executeUpdate(q7);
			    		System.out.println("CREATED TABLE Card");
			    		
			    		
			    		String q8 = "CREATE TABLE Account\r\n" + 
			    				"(\r\n" + 
			    				"	Account_ID Varchar(20),\r\n" + 
			    				"    Account_password		Varchar(255),\r\n" + 
			    				"    card_number		Int,\r\n" + 
			    				"    primary key (Account_ID),\r\n" + 
			    				"    foreign key(card_number) REFERENCES Card(card_number)\r\n" + 
			    				")";
			    		stmt1.executeUpdate(q8);
			    		System.out.println("CREATED TABLE Account");
			    		
			    		
			    		String q9 = "CREATE TABLE Bill (\r\n" + 
			    				"        	Bill_ID             number primary key,\r\n" + 
			    				"        	Order_Price	    	number(19, 2),\r\n" + 
			    				"            Bill_Total	        number(19, 2),\r\n" + 
			    				"        	Bill_PaymentStatus	varchar2(10),\r\n" + 
			    				"        	Bill_Date           date\r\n" + 
			    				")";			    		
			    		stmt1.executeUpdate(q9);
			    		System.out.println("CREATED TABLE Bill");
			    		
			    		
			    		String q10 = "CREATE TABLE Orders (\r\n" + 
			    				"		Order_ID	        varchar2(100) primary key,\r\n" + 
			    				"		Order_Price	        number(19, 2),\r\n" + 
			    				"		Order_Date	        date,\r\n" + 
			    				"		Account_ID          varchar2(100),\r\n" + 
			    				"		Shipping_ID	        varchar2(100),\r\n" + 
			    				"		Bill_ID		        number,\r\n" + 
			    				"		Constraint fk_order_shipping    FOREIGN KEY(Shipping_ID)    REFERENCES Shipping(Shipping_ID),\r\n" + 
			    				"		Constraint fk_order_customer    FOREIGN KEY(Account_ID)    REFERENCES Account(Account_ID),\r\n" + 
			    				"        Constraint fk_order_bill        foreign key(Bill_ID)        references Bill(Bill_ID)\r\n" + 
			    				")";
			    		stmt1.executeUpdate(q10);
			    		System.out.println("CREATED TABLE Orders");
			    		
			    		
			    		String q11 = "CREATE TABLE Payment_Method (\r\n" + 
			    				"            Payment_Method_ID  	number primary key,\r\n" + 
			    				"	     	Payment_Method 	    varchar2(5) CHECK(Payment_Method IN ('cash','card'))\r\n" + 
			    				")";
			    		stmt1.executeUpdate(q11);
			    		System.out.println("CREATED TABLE Payment_Method");
			    		
			    		
			    		String q12 = "CREATE TABLE Customer (\r\n" + 
			    				"		Customer_ID     	varchar2(100) primary key,\r\n" + 
			    				"		Account_ID			varchar2(100),\r\n" + 
			    				"		Customer_Name		varchar2(100),\r\n" + 
			    				"		Payment_Method_ID	number,\r\n" + 
			    				"		Constraint fk_customer_account  FOREIGN KEY(Account_ID)         REFERENCES Account(Account_ID),\r\n" + 
			    				"		Constraint fk_customer_payment  FOREIGN KEY(Payment_Method_ID)  REFERENCES Payment_Method(Payment_Method_ID)\r\n" + 
			    				")";
			    		stmt1.executeUpdate(q12);
			    		System.out.println("CREATED TABLE Customer");
			    		
			    		
			    		String q13 = "CREATE TABLE Order_History (\r\n" + 
			    				"	Account_ID	         varchar2(100),\r\n" + 
			    				"	Order_ID	         varchar2(100),\r\n" + 
			    				"	Order_Date			 date,\r\n" + 
			    				"	Order_Price			 number(19, 2),\r\n" + 
			    				"	Constraint fk_history_Account   FOREIGN KEY(Account_ID)     REFERENCES Account(Account_ID),\r\n" + 
			    				"	Constraint fk_order_history   FOREIGN KEY(Order_ID)     REFERENCES Orders(Order_ID)\r\n" + 
			    				")";
			    		stmt1.executeUpdate(q13);
			    		System.out.println("CREATED TABLE Order_History");
			    		
			    		
			    		String q14 = "CREATE TABLE Supplier (\r\n" + 
			    				"		Supplier_ID 	             number  primary key,\r\n" + 
			    				"		Supplier_Name	             varchar2(100) not null,\r\n" + 
			    				"		Supplier_Address	         varchar2(255),\r\n" + 
			    				"        Supplier_Phone               varchar2(50),\r\n" + 
			    				"        Supplier_Email               varchar2(100),\r\n" + 
			    				"        Supplier_FirstTransaction    date,\r\n" + 
			    				"        Supplier_LastTransaction     date\r\n" + 
			    				")";
			    		stmt1.executeUpdate(q14);
			    		System.out.println("CREATED TABLE Supplier");
			    		
			    		
			    		String q15 = "CREATE TABLE Item_Category (\r\n" + 
			    				"       		Category_ID                 number primary key,\r\n" + 
			    				"            Category_Name         		varchar2(100) not null\r\n" + 
			    				"\r\n" + 
			    				")";
			    		stmt1.executeUpdate(q15);
			    		System.out.println("CREATED TABLE Item_Category");		
			    		
			    		String q16 = "CREATE TABLE Item (\r\n" + 
			    				"        Item_ID                     number(15) primary key,\r\n" + 
			    				"		Item_Name        		    varchar2(100) not null,\r\n" + 
			    				"        Category_ID                 number,\r\n" + 
			    				"		Item_TotalSupplied	        number(20),\r\n" + 
			    				"		Item_TotalSold	            number(20),\r\n" + 
			    				"		Item_SupplierPrice	        number(19, 2),\r\n" + 
			    				"        Item_Price                  number(19, 2),\r\n" + 
			    				"		Supplier_ID	                number,\r\n" + 
			    				"		Constraint fk_item_supplier FOREIGN KEY(Supplier_ID) 		REFERENCES Supplier(Supplier_ID),\r\n" + 
			    				"		Constraint fk_item_category FOREIGN KEY(Category_ID) 	    REFERENCES Item_Category(Category_ID)\r\n" + 
			    				")";
			    		stmt1.executeUpdate(q16);
			    		System.out.println("CREATED TABLE Item");	
			    		
			    		
			    		String q17 = "CREATE TABLE Inventory (\r\n" + 
			    				"        ID   		    number primary key,\r\n" + 
			    				"		Total_Value	    number(13, 2),\r\n" + 
			    				"		Total_Items	    number(10),\r\n" + 
			    				"		Store_ID   	    Varchar(20),\r\n" + 
			    				"		Item_ID		    number(15),\r\n" + 
			    				"        FOREIGN KEY(Store_ID)   REFERENCES Store(Store_ID),\r\n" + 
			    				"        FOREIGN KEY(Item_ID)    REFERENCES Item(Item_ID)\r\n" + 
			    				")";
			    		stmt1.executeUpdate(q17);
			    		System.out.println("CREATED TABLE Inventory");	
			    		
			    		
			    		System.out.println("Succesfully Created Tables");
						stmt1.close();
						
						} catch (SQLException e) {
							System.out.println(e.getErrorCode());
						}
			    	
			    }
			    // Drop tables if 2 is input
			    else if (choice.equals("2"))
			    {
			    	try (Statement stmt1 = conn1.createStatement()) {
			    		
			    		String d1 = "DROP TABLE Employee CASCADE CONSTRAINTS";	
			    		stmt1.executeUpdate(d1);
			    		System.out.println("DROPPED Employee");
			    		
			    		d1 = "DROP TABLE EMPLOYEE_PHONE CASCADE CONSTRAINTS";	
			    		stmt1.executeUpdate(d1);
			    		System.out.println("DROPPED EMPLOYEE_PHONE");
			    		
			    		d1 = "DROP TABLE STORE_ADDRESS CASCADE CONSTRAINTS";	
			    		stmt1.executeUpdate(d1);
			    		System.out.println("DROPPED STORE_ADDRESS");
			    		
			    		d1 = "DROP TABLE ORDERS CASCADE CONSTRAINTS";	
			    		stmt1.executeUpdate(d1);
			    		System.out.println("DROPPED ORDERS");
			    		
			    		d1 = "DROP TABLE Shipping_Type CASCADE CONSTRAINTS";	
			    		stmt1.executeUpdate(d1);
			    		System.out.println("DROPPED Shipping_Type");
			    		
			    		d1 = "DROP TABLE Shipping CASCADE CONSTRAINTS";	
			    		stmt1.executeUpdate(d1);
			    		System.out.println("DROPPED Shipping");
			    		
			    		d1 = "DROP TABLE Customer CASCADE CONSTRAINTS";	
			    		stmt1.executeUpdate(d1);
			    		System.out.println("DROPPED Customer");
			    		
			    		d1 = "DROP TABLE Order_History CASCADE CONSTRAINTS";	
			    		stmt1.executeUpdate(d1);
			    		System.out.println("DROPPED Order_History");
			    		
			    		d1 = "DROP TABLE Item CASCADE CONSTRAINTS";	
			    		stmt1.executeUpdate(d1);
			    		System.out.println("DROPPED Item");
			    		
			    		d1 = "DROP TABLE Inventory CASCADE CONSTRAINTS";	
			    		stmt1.executeUpdate(d1);
			    		System.out.println("DROPPED Inventory");
			    		
			    		d1 = "DROP TABLE Store CASCADE CONSTRAINTS";	
			    		stmt1.executeUpdate(d1);
			    		System.out.println("DROPPED Store");
			    		
			    		d1 = "DROP TABLE Bill CASCADE CONSTRAINTS";	
			    		stmt1.executeUpdate(d1);
			    		System.out.println("DROPPED Bill");
			    		
			    		d1 = "DROP TABLE Account CASCADE CONSTRAINTS";	
			    		stmt1.executeUpdate(d1);
			    		System.out.println("DROPPED Account");
			    		
			    		d1 = "DROP TABLE Card CASCADE CONSTRAINTS";	
			    		stmt1.executeUpdate(d1);
			    		System.out.println("DROPPED Card");
			    		
			    		d1 = "DROP TABLE Payment_Method CASCADE CONSTRAINTS";	
			    		stmt1.executeUpdate(d1);
			    		System.out.println("DROPPED Payment_Method");
			    		
			    		d1 = "DROP TABLE Supplier CASCADE CONSTRAINTS";	
			    		stmt1.executeUpdate(d1);
			    		System.out.println("DROPPED Supplier");
			    		
			    		d1 = "DROP TABLE Item_Category CASCADE CONSTRAINTS";	
			    		stmt1.executeUpdate(d1);
			    		System.out.println("DROPPED Item_Category");
			    		
			    		
			    		

			    		
			    		
			    		
			    		System.out.println("Succesfully Dropped Tables");
						stmt1.close();
						
						} catch (SQLException e) {
							System.out.println(e.getErrorCode());
						}
			    		
			    	
			    }
			    
			    // populate tables if 3 is input
			    else if (choice.equals("3"))
			    {
			    	try (Statement stmt1 = conn1.createStatement()) {
			    	String d1;
			    	d1 = "Insert into Store_Address Values ('123 alp street', '647-457-2319')";	
		    		stmt1.executeUpdate(d1);
		    		System.out.println("INSERTED ROW");
		    		
		    		d1 = "Insert into Store_Address Values ('12345 aoe street', '647-111-2999')";	
		    		stmt1.executeUpdate(d1);
		    		System.out.println("INSERTED ROW");
		    		
		    		d1 = "Insert into Store_Address Values ('1800 kennedy dr.', '888-888-8888')";	
		    		stmt1.executeUpdate(d1);
		    		System.out.println("INSERTED ROW");
			    	}
			    	catch (SQLException e) {
						System.out.println(e.getErrorCode());
					}
			    	
			    }
			    
			    // query tables if 4 is input
			    
			    else if (choice.equals("4"))
			    {
			    	Scanner s2 = new Scanner(System.in);
			    	System.out.println("");
			    	System.out.println("Type in a query");
			    	String query = s2.nextLine();
			    	System.out.println("");
			    	
			    	try (Statement stmt = conn1.createStatement()) {

						ResultSet rs = stmt.executeQuery(query);

	
						//prints lines in result set with proper format
						
						ResultSetMetaData md = rs.getMetaData();
						int cNumber = md.getColumnCount();
						while (rs.next() == true) 
						{
						    for (int j = 1; j <= cNumber; j+=1) 
						    {
						        if (j > 1) 
						        {
						        	System.out.print(",  ");
						        }

						        String cValue = rs.getString(j);
						        System.out.print(cValue + " " + md.getColumnName(j));
						    }
						    System.out.println("");
						}
						while (rs.next() == true) {
							String n = rs.getString("NAME");
							int number = rs.getInt("NUM");
							System.out.println(n + ", " + number);
						}
						} catch (SQLException e) {
							System.out.println();
						}
			    	
			    }
			    
		        } 
        }

        //error handeling
 
         catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn1 != null && !conn1.isClosed()) {
                    conn1.close();
                }
     
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
			

    }
}