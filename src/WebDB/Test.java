package WebDB;

import beans.InteractionTopic;
import beans.Users;

import java.util.ArrayList;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StudentDAO studentDAO = new StudentDAO();
		InteractionTopic topic = studentDAO.getOneTopic("1");
		String name = studentDAO.getName(topic.getUsername());
		System.out.println("name="+name);
	}
}
