package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class DataBase {
	


	public static Connection con() {
		try {
			 String JDBC_Driver = "com.mysql.jdbc.Driver";
			 String urlDB = "jdbc:mysql://localhost:3306/DBname";
			 String user = "dineshkanna";
			 String pass = "123456";
			 Class.forName(JDBC_Driver);
			 Connection connect=DriverManager.getConnection(urlDB, user, pass);
			 return connect;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	public static ArrayList<String> select() throws Exception  {
		Connection conn = con();
		PreparedStatement statement = conn.prepareStatement("select * from tables");
		
		ResultSet result = statement.executeQuery();
		ArrayList<String> array = new ArrayList<String>();
		while (result.next()) {
			array.add(result.getString("url"));
		}
		return array;
		
	}
	
//	public static void main(String[] args) throws Exception {
//		con();
//		ArrayList<String> array = select();
//	}
	

}
