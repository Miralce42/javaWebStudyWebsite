package WebDB;

import beans.InteractionTopic;
import beans.TopicComments;
import beans.Users;

import java.util.ArrayList;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StudentDAO studentDAO = new StudentDAO();
		ArrayList<InteractionTopic> Topics = new ArrayList<>();
		Topics = studentDAO.selectSearchTopic("JSP");
		InteractionTopic topic = Topics.get(0);
		System.out.println(topic.getTitle());
	}
}
