package com.dream.FileClasses;


import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by Dreamer on 2017/5/25.
 */
public class PracticeAction extends ActionSupport {
    private String Section;
    private String file_type;
    private String file_name;

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String execute()throws Exception{//信息插入
      //  System.out.println("执行");
        FileClasses fileClasses = new FileClasses(getFile_name(),getFile_type() , getSection());
        FileDAO fileDAO = new FileDAO();
        if (fileDAO.addDataBaseInfo(fileClasses)) {
          //  System.out.println("插入成功！");
            return "success";
        }
        else
        return "error";
    }

    public String deleteInfor ()throws Exception{
           HttpServletRequest request= ServletActionContext.getRequest();
           String info=request.getParameter("info");
           System.out.println(info);
           int[] num={} ;//   /个数
         //  System.out.println(num.length);
           for(int i=0;i<info.length();i++)//遍历所有字符/
            if(info.charAt(i)=='/')
            {
                num= Arrays.copyOf(num,num.length+1);
                num[num.length-1]=i;//数组复制追加
                System.out.println("i="+i);
            }
          String[] list={info.substring(0,num[0]),info.substring(num[0]+1,num[1]),info.substring(num[1]+1,info.length())};
          /*System.out.println(info.substring(0,num[0]));//type
          System.out.println(info.substring(num[0]+1,num[1]));//section
          System.out.println(info.substring(num[1]+1,info.length()));//name*/
           for(int i=0;i<list.length;i++)
               System.out.println(list[i]);
           FileClasses fileClasses=new FileClasses(list[2],list[0],list[1]);
           FileDAO dao= new FileDAO();
           if(dao.deleteFile(fileClasses))
             return "success";
           else
               return "error";
    }
}
