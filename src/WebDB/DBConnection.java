package WebDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	public Connection conn = null;
	public Statement stat = null;

	public  DBConnection(){
		try {
			//数据库驱动
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\LEON:55352;DatabaseName=WebDB","WebUser","123");
			stat = conn.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void finalize()
	{
		try {
			stat.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		DBConnection con = new DBConnection();
		String ssql = "insert into [WebDB].[dbo].[users] values('123','123','123')";
		try {
			con.stat.executeUpdate(ssql);
			con.conn.close();
			con.stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
