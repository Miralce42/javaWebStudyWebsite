package actions;

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
    private Dao dao = new Dao();;
    private HttpServletRequest request = ServletActionContext.getRequest();
    private HttpServletResponse response = ServletActionContext.getResponse();
    private HttpSession session = request.getSession();

    public String Login ()throws Exception{
        int state = dao.Login(user);
        if (state==1){
            session.setAttribute("username",user.getUsername());
            return SUCCESS;
        }
        else{
            this.addActionMessage("用户名或密码错误！");
            return "Fail";
        }
    }

    public String ChgPw(){
        int states = dao.changePassword(user);
        if(states == 1){
            return SUCCESS;
        }
        else {
            this.addActionMessage("用户名或密码错误！");
            return "Fail";
        }
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Users getUser(){
        return this.user;
    }
}
