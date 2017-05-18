package WebDB;

import beans.*;

import java.sql.ResultSet;
import java.sql.SQLException;
public class Dao {
	DBConnection con = new DBConnection();
		
	public int Login(Users user){
		String ssql = "select * from [WebDB].[dbo].[users]  ";
		ssql += "where username = '" +user.getUsername()+"' and password = '"+user.getPassword()+"'";
		ResultSet rs = null;//声明ResulteSet
		try {
			rs = con.stat.executeQuery(ssql);//执行用户信息查询语句
			if(rs.next())//查询到该用户
			{
				return 1;
			}
			else {//无该用户
				return 0;
			}
		} catch (SQLException e) {//查询过程中出现错误
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	public int changePassword(Users user){
		int loginStat = Login(user);//用户测试是否存在此用户
		if(loginStat == 1){
			String ssql = "update [WebDB].[dbo].[users] set password = '"+user.getUserPasswordOne()+"' where username = '"+user.getUsername()+"'";
			try {
				con.stat.executeUpdate(ssql);
				return 1;//用户存在，且更改密码成功
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
		}
		else if(loginStat == 0){//密码不存在
			return 0;
		}
		else {
			return -1;
		}
	}
}
