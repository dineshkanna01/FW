package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBTest {
	
	public void database(String data) {
		final String JDBC_Driver = "com.mysql.jdbc.Driver";
		final String urlDB = "jdbc:mysql:";
		
		final String user = "dineshkanna";
		final String pass = "123456";
		
		Connection con= null;
		Statement stmt = null;
		
		try {
			Class.forName(JDBC_Driver);
			try {
				con=DriverManager.getConnection(urlDB, user, pass);
				
				stmt = con.createStatement();
				String Sql= "select*from Employees "+data;
				ResultSet executeQuery = stmt.executeQuery(Sql);
				
				while (executeQuery.next()) {
					String name = executeQuery.getString("username");
					String pass1 = executeQuery.getString("password");
					System.out.println(name);
					System.out.println(pass);
				}
				
				executeQuery.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	
	
	ArrayList<String> array = new ArrayList<String>();

//	public static void main(String[] args) throws Exception {
//		Class.forName("com.mysql.jdbc.Driver");
//		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sample","root","8080");
//		
//		Statement st = con.createStatement();
//		ResultSet rs = st.executeQuery("select*from Employees");
//		
//		while (rs.next()) {
//			int sNo = rs.getInt("S.no");
//			String name = rs.getString("username");
//			String pass = rs.getString("password");
//			System.out.println(name);
//			System.out.println(pass);
//		}
//		st.close();
//		con.close();
//	}
}
