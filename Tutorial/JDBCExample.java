package Tutorial;
import java.sql.*;
import java.util.Scanner;

public class JDBCExample {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/";

	static Scanner user_input = new Scanner( System.in );
			
	static String USER;
	static String PASS;
	static String DBNAME;
	
	public static void main(String[] args) {
		
		System.out.print("Username: ");
		USER = user_input.next();
		
		System.out.print("Password: ");
		PASS = user_input.next();
		
		System.out.print("Database Name: ");
		DBNAME =  user_input.next();
		
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("Connecting to MySQL...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			System.out.println("Creating database...");
			stmt = conn.createStatement();
			
			String dropexisting = String.format("DROP DATABASE IF EXISTS %s", DBNAME);
			stmt.executeUpdate(dropexisting);
			
			String dbcreate = String.format("CREATE DATABASE %s", DBNAME);
			stmt.executeUpdate(dbcreate);
			System.out.println(String.format("DATABASE %s CREATED", DBNAME));
			
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}	
	}
}
