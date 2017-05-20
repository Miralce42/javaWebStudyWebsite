package WebDB;

import beans.*;

import java.sql.ResultSet;
import java.sql.SQLException;
public class Dao {
	private DB_Manager db_manager=new DB_Manager();
		
	public int Login(Users user){
		String ssql = "select * from javawebcourseresources.users  ";
		ssql += "where user_id = ? and password = ?";

		ResultSet rs = null;//声明ResulteSet
		try{
			rs = db_manager.executeQuery(ssql,new String[]{user.getUsername(),user.getPassword()});//执行用户信息查询语句
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
			String ssql = "update [WebDB].[dbo].[users] set password = ? where username = ?";
				int state = db_manager.executeUpdate(ssql,new String[]{user.getUserPasswordOne(),user.getUsername()});
				if(state == 1)
					return 1;//用户存在，且更改密码成功
				else
					return -1;
		}
		else if(loginStat == 0){//账户或密码不正确
			return 0;
		}
		else {
			return -1;
		}
	}
	public int ChangePhoneNum(Users user) {
		String ssql = "update [WebDB].[dbo].[users] set phone = ? where user_id = ?";
		int state = db_manager.executeUpdate(ssql, new String[]{user.getPhone(), user.getUsername()});
		if (state == 1) {
			return 1;//修改手机号称
		} else {
			return 0;//修改失败
		}
	}

}
