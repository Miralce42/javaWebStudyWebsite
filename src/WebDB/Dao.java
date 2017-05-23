package WebDB;

import beans.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
				user.setUser_type(rs.getString("user_type"));
				user.setName(rs.getString("name"));
				user.setSex(rs.getString("sex"));
				user.setPhone(rs.getString("phone"));
				user.setMajor(rs.getString("major"));
				user.setClassNum(rs.getString("class"));
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
			String ssql = "update javawebcourseresources.users set password = ? where user_id = ?";
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
		String ssql = "update javawebcourseresources.users set phone = ? where user_id = ?";
		int state = db_manager.executeUpdate(ssql, new String[]{user.getPhone(), user.getUsername()});
		if (state == 1) {
			return 1;//修改手机号称
		} else {
			return 0;//修改失败
		}
	}

	public ResultSet chooseSelectMethod(int method,String condition){
		//0:查询全部学生，1:按班级查询，2:模糊查询
		String ssql = null;
		if(method == 0){
			//查询全部
			ssql = "select * from javawebcourseresources.users where user_type = 'student'";
			return db_manager.executeQuery(ssql,null);
		}
		else if(method == 1){
			//按专业班级查询
			ssql = "select * from javawebcourseresources.users where user_type = 'student' and CONCAT(major,class) = ?";
			return db_manager.executeQuery(ssql,new String[]{condition});
		}
		else if(method == 3){
			ssql = "select * from javawebcourseresources.users where user_id = ?";
			return db_manager.executeQuery(ssql,new String[]{condition});
		}
		else{//method==2
			//万能模糊查询语句
			String likeCondition = "%"+condition+"%";
			ssql = "select * from javawebcourseresources.users" +
					" where user_type = 'student' and " +
					"user_id like ? or name like ? or " +
					"phone like ? or major like ? or " +
					"class like ?";
			return db_manager.executeQuery(ssql,new String[]{likeCondition,likeCondition,likeCondition,likeCondition,likeCondition});
		}
	}

	public int selectStudent(ArrayList<Users> Students,int method,String condition){
		ResultSet rs = chooseSelectMethod(method,condition);
		try {
			while (rs.next()){
                Users stu = new Users();
                stu.setUsername(rs.getString("user_id"));
                stu.setName(rs.getString("name"));
				stu.setSex(rs.getString("sex"));
				stu.setPhone(rs.getString("phone"));
				stu.setMajor(rs.getString("major"));
				stu.setClassNum(rs.getString("class"));
				Students.add(stu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Students.size();
	}

	public int selectClassName(ArrayList<String> classNames){
		String ssql = "SELECT CONCAT(major,class) from javawebcourseresources.users ORDER BY major;";
		ResultSet rs = db_manager.executeQuery(ssql,null);
		try {
			while(rs.next()){
				String className = rs.getString(1);
				classNames.add(className);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classNames.size();
	}
}
