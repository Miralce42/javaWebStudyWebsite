package actions;

import beans.InteractionTopic;
import beans.Users;
import WebDB.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by 韩壮 on 2017/5/17.
 */
public class Action extends ActionSupport {
    private Users user = new Users();
    private InteractionTopic topic = new InteractionTopic();
    private StudentDAO stuDao = new StudentDAO();
    private Dao dao = new Dao();;
    private HttpServletRequest request = ServletActionContext.getRequest();
    private HttpServletResponse response = ServletActionContext.getResponse();
    private HttpSession session = request.getSession();

    public String Login ()throws Exception{
        int state = dao.Login(user);
        if (state==1){
            session.setAttribute("user",user);
            String userType = user.getUser_type();
            if("STUDENT".equals(userType)) {
                return "student";
            }
            else{
                return "teacher";
            }
        }
        else{
            this.addActionMessage("用户名或密码错误！");
            return ERROR;
        }
    }

    public String ChgPw()throws Exception{//更换密码
        int states = dao.changePassword(user);
        if(states == 1){
            return SUCCESS;
        }
        else {
            this.addActionMessage("用户名或密码错误！");
            return ERROR;
        }
    }

    public String ChgPh()throws Exception{//更换手机号
        user = (Users)session.getAttribute("user");
        user.setPhone(request.getParameter("user.phone"));
        int states = dao.ChangePhoneNum(user);
        if(states == 1){
            return SUCCESS;
        }
        else{
            return ERROR;
        }
    }

    public String CrtTopic(){//创建话题
        //先从表单中获取topicType，然后转为TopicType类型
        InteractionTopic.TopicType topicType = InteractionTopic.TopicType.valueOf( request.getParameter("topicType"));
        Users student = (Users)session.getAttribute("user");
        topic.setTopicType(topicType);
        topic.setUsername(student.getUsername());
        int states = stuDao.createTopic(topic);
        if(states == 1){
            return SUCCESS;
        }
        else{
            return ERROR;
        }
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Users getUser(){
        return this.user;
    }

    public InteractionTopic getTopic() {
        return topic;
    }

    public void setTopic(InteractionTopic topic) {
        this.topic = topic;
    }
}
