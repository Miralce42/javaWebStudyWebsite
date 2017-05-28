package com.dream.FileDelete;

import com.dream.FileClasses.FileClasses;
import com.dream.FileClasses.FileDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Dreamer on 2017/5/21.
 */
public class FileDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
       String filename=request.getParameter("filename");
     //  System.out.println(filename);
     FileClasses fileClasses=setFileClasses(filename);
       new FileDAO().deleteFile(fileClasses);//删除数据库数据记录
      if(deleteFiles(filename)) {//删除磁盘文件
          response.setContentType("text/html; charset=GBK");
          PrintWriter out = response.getWriter();
          out.println("删除成功"+"<a href='backDesk/theCoursewareManagement.jsp'>返回</a>");
      }


    }
        public FileClasses setFileClasses(String strs){
            String filename=strs.substring(strs.lastIndexOf("/")+1);//文件名
            String[] list=strs.split("/");
            for(String item: list){
                System.out.println(item);
            }//监视
            return new FileClasses(list[2],list[0],list[1]);
        }
        public  boolean deleteFiles(String filename) {
          String truename=filename.substring(filename.lastIndexOf("/")+1);//文件名
          String formerpath=filename.substring(0,filename.lastIndexOf("/")+1);//无文件路径
          String filePath=getServletContext().getRealPath("upload/"+formerpath);
         // System.out.println(filePath);
          File root = new File(filePath);
          File[] files = root.listFiles();
          for (int i = 0; i < files.length; i++) {
              File file = files[i];
              if(truename.equals(file.getName()))
              {
                  file.delete();
                  return true;
              }
          }
          return  false;
      }
}
