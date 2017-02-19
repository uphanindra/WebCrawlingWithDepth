/**
* The WebCrawlDBUtil is an utility program for WebCrawler application
* that inserts, read and delete rows into MySql table.
*
* @author  Phanindra Uppalapati
* @version 1.0
* @since   2017-02-20 
*/

import java.sql.*;

public class WebCrawlDBUtil {
 // JDBC driver name and database URL
 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
 static final String DB_URL = "jdbc:mysql://localhost/webcrawldb";

 //  Database credentials
 static final String USER = "root";
 static final String PASS = "";
 // Table Syntax in MySql
 //CREATE TABLE `WebCrawlDb`.`CrawlLinks` ( `link` TEXT NULL DEFAULT NULL , `depth` INT NULL DEFAULT NULL ) ENGINE = InnoDB;
 
 public void read() {
 Connection conn = null;
 Statement stmt = null;
 try{
    //STEP 2: Register JDBC driver
    Class.forName("com.mysql.jdbc.Driver");

    //STEP 3: Open a connection
    //System.out.println("Connecting to database...");
    conn = DriverManager.getConnection(DB_URL,USER,PASS);

    //STEP 4: Execute a query
    //System.out.println("Creating statement...");
    stmt = conn.createStatement();
    String sql;
    sql = "SELECT * FROM crawllinks";
    ResultSet rs = stmt.executeQuery(sql);

    //STEP 5: Extract data from result set
    while(rs.next()){
       //Retrieve by column index
       String link  = rs.getString(1);
       int depth = rs.getInt(2);

       //Display values
       System.out.print("\nlink: " + link);
       System.out.print(", depth: " + depth);
       
    }
    //STEP 6: Clean-up environment
    rs.close();
    stmt.close();
    conn.close();
 }catch(SQLException se){
    //Handle errors for JDBC
    se.printStackTrace();
 }catch(Exception e){
    //Handle errors for Class.forName
    e.printStackTrace();
 }finally{
    //finally block used to close resources
    try{
       if(stmt!=null)
          stmt.close();
    }catch(SQLException se2){
    }// nothing we can do
    try{
       if(conn!=null)
          conn.close();
    }catch(SQLException se){
       se.printStackTrace();
    }//end finally try
 }//end try
 
}//end read

 
 public void deleteAll() {
	 Connection conn = null;
	 Statement stmt = null;
	 try{
	    //STEP 2: Register JDBC driver
	    Class.forName("com.mysql.jdbc.Driver");

	    //STEP 3: Open a connection
	    //System.out.println("Connecting to database...");
	    conn = DriverManager.getConnection(DB_URL,USER,PASS);

	    //STEP 4: Execute a query
	   // System.out.println("Creating statement...");
	    stmt = conn.createStatement();
	    String sql;
	    sql = "DELETE FROM crawllinks";
	    //System.out.println("Execute statement...");
		   
	    stmt.execute(sql);
	    //STEP 5: Clean-up environment
	    stmt.close();
	    conn.close();
	 }catch(SQLException se){
	    //Handle errors for JDBC
	    se.printStackTrace();
	 }catch(Exception e){
	    //Handle errors for Class.forName
	    e.printStackTrace();
	 }finally{
	    //finally block used to close resources
	    try{
	       if(stmt!=null)
	          stmt.close();
	    }catch(SQLException se2){
	    }// nothing we can do
	    try{
	       if(conn!=null)
	          conn.close();
	    }catch(SQLException se){
	       se.printStackTrace();
	    }//end finally try
	 }//end try
	 
	}//end 



 public void insertRow(String link,int depth) {
	 Connection conn = null;
	 Statement stmt = null;
	 try{
	    //STEP 2: Register JDBC driver
	    Class.forName("com.mysql.jdbc.Driver");

	    //STEP 3: Open a connection
	    //System.out.println("Connecting to database...");
	    conn = DriverManager.getConnection(DB_URL,USER,PASS);

	    //STEP 4: Execute a query
	    //System.out.println("Creating statement...");
	    stmt = conn.createStatement();
	    String sql;
	    sql = "INSERT INTO crawllinks VALUES ('"+link.trim()+"',"+depth+")";
	    //System.out.println("Execute statement..."+sql);
		   
	    stmt.execute(sql);
	    //STEP 5: Clean-up environment
	    stmt.close();
	    conn.close();
	 }catch(SQLException se){
	    //Handle errors for JDBC
	    se.printStackTrace();
	 }catch(Exception e){
	    //Handle errors for Class.forName
	    e.printStackTrace();
	 }finally{
	    //finally block used to close resources
	    try{
	       if(stmt!=null)
	          stmt.close();
	    }catch(SQLException se2){
	    }// nothing we can do
	    try{
	       if(conn!=null)
	          conn.close();
	    }catch(SQLException se){
	       se.printStackTrace();
	    }//end finally try
	 }//end try
	 
	}//end read


}

