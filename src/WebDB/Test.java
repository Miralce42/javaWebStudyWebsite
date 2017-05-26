package WebDB;

import beans.InteractionTopic;
import beans.TopicComments;
import beans.Users;
import beans.deleteHTMLTag;

import java.util.ArrayList;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String htmlStr = "<<h3 style=\"color:#aaaaaa;font-style:italic;\">不会出BUG的内容</h3>\n";
		String str = deleteHTMLTag.delHTMLTag(htmlStr);
		System.out.println(str);
	}
}
