package actions;

import WebDB.TeacherDAO;
import beans.Users;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

/**
 * Created by Vove on 2017/5/22.
 *
 */
public class DeleteHomeworkAction extends ActionSupport {
    private String homeworkId;

    public String getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(String homeworkId) {
        this.homeworkId = homeworkId;
    }
    public String deleteHomework() throws Exception{
        HttpSession session= ServletActionContext.getRequest().getSession();
        Users teacher=(Users)session.getAttribute("user");

        String pageTitle;//执行消息页面title
        String pageContent;
        if(new TeacherDAO(teacher).deleteHomework(homeworkId)){
            pageTitle="删除成功";
            pageContent = pageTitle+",<a href=homeworkManager.jsp>返回作业管理</a>";
            session.setAttribute("title",pageTitle);
            session.setAttribute("pageContent",pageContent);
            return SUCCESS;
        }else {
            pageTitle="删除失败";
            pageContent = pageTitle+",<a href=homeworkManager.jsp>返回作业管理</a>";
            session.setAttribute("title",pageTitle);
            session.setAttribute("pageContent",pageContent);
            return ERROR;
        }
    }
}
