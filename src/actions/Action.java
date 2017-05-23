package actions;

import beans.InteractionTopic;
import beans.Users;
import WebDB.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by 韩壮 on 2017/5/17.
 */
public class Action extends ActionSupport {
    private Users user = new Users();
    private InteractionTopic topic = new InteractionTopic();
    private StudentDAO stuDao = new StudentDAO();
    private TeacherDAO teacherDAO = new TeacherDAO();
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
        Users student = (Users)session.getAttribute("user");
        topic.setTopicType(request.getParameter("topicType"));
        topic.setUsername(student.getUsername());
        int states = stuDao.createTopic(topic);
        if(states == 1){
            return SUCCESS;
        }
        else{
            return ERROR;
        }
    }

    public String SelectAllStudents(){
        ArrayList<Users> Students = new ArrayList<Users>();
        dao.selectStudent(Students,0,null);
        session.setAttribute("Students",Students);
        return SUCCESS;
    }

    public String SelectStudentByClass(){
        ArrayList<Users> Students = new ArrayList<Users>();
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String className = request.getParameter("className");
        System.out.println("className=" + className);
        dao.selectStudent(Students, 1, className);
        session.setAttribute("Students",Students);
        return SUCCESS;
    }

    public String SelectStudentBySearch(){
        ArrayList<Users> Students = new ArrayList<Users>();
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String searchCondition = request.getParameter("searchCondition");
        System.out.println("searchCondition=" + searchCondition);
        dao.selectStudent(Students, 2, searchCondition);
        session.setAttribute("Students",Students);
        return SUCCESS;
    }

    public String UpdateStudentInfo(){
        int state = teacherDAO.updateStudentInfo(user);
        session.setAttribute("updatedStuUsername",user.getUsername());
        if (state ==1){
            return SUCCESS;
        }
        else{
            return ERROR;
        }
    }

    public String DeleteStudentInfo(){
        int state = teacherDAO.deleteStudentInfo(user);
        if (state ==1){
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
