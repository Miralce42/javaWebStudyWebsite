package com.dream.FileDownload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Servlet implementation class ServletDownload
 */
public class FileDownload extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownload() {
        super();
        // TODO Auto-generated constructor stub  
    }//构造函数

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub  

        //获得请求文件名
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String filename = request.getParameter("filename");//包含目录信息的文件名
      //  filename = new String(filename.getBytes("UTF-8"),"ISO-8859-1");

        System.out.println(filename);

        String truename=filename.substring(filename.lastIndexOf("/")+1);//文件名
        System.out.println(truename);
        truename = truename.replaceAll(" ","");//除文件名空格
        System.out.println(truename);
        System.out.println(getServletContext().getMimeType(truename));

        //设置文件MIME类型
        response.setContentType(getServletContext().getMimeType(truename));
        System.out.println(response.getContentType());
        //Content-Disposition中指定的类型是文件的扩展名，并且弹出的下载对话框中的文件类型图片是按照文件的扩展名显示的，点保存后，文件以filename的值命名，保存类型以Content中设置的为准。注意：在设置Content-Disposition头字段之前，一定要设置Content-Type头字段。
        response.setHeader("Content-Disposition", "attachment;filename="+truename);
//        读取目标文件，通过response将目标文件写到客户端
//        获取目标文件的绝对路径
        String fullFileName = getServletContext().getRealPath(filename);
        System.out.println("fullFileName="+fullFileName);
        //读取文件
        InputStream in = new FileInputStream(fullFileName);
        OutputStream out = response.getOutputStream();

        //写文件
        int b;
        while((b=in.read())!= -1)
        {
            out.write(b);
        }

        in.close();
        out.close();

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub  
    doGet(request,response);
    }

}  