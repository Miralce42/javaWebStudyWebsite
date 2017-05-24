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
		ArrayList<TopicComments> Comments = studentDAO.getAllComment("7");
		TopicComments comment = Comments.get(0);
		System.out.println(comment.getContent());
	}
}
