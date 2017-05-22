package WebDB;

import beans.Users;

import java.util.ArrayList;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Dao dao = new Dao();
		ArrayList<Users> Students = new ArrayList<>();
		int count = dao.selectStudent(Students,2,"啦啦啦");
		for (int i = 0; i < count; i++) {
			Users stu = Students.get(i);
			System.out.println("用户名为=" + stu.getUsername());
		}
	}
}
