package com.dream.Fileupload;

import com.dream.FileClasses.FileClasses;
import com.dream.FileClasses.FileDAO;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.*;

/**
 * Created by Dreamer on 2017/5/20.
 */
public class UploadAction extends ActionSupport {
    //注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
    private File file;
    private String Section;
    private String file_type;

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }

    //提交过来的file的名字
    private String fileFileName;

    //提交过来的file的MIME类型
    private String fileContentType;
    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public File getFile()
    {
        return file;
    }

    public void setFile(File file)
    {
        this.file = file;
    }

    public String getFileFileName()
    {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName)
    {
        this.fileFileName = fileFileName;
    }

    public String getFileContentType()
    {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType)
    {
        this.fileContentType = fileContentType;
    }

    @Override
    public String execute() throws Exception
    {
      //  System.out.println(getFile_type());
      /*   System.out.println("section:"+getSection());
         System.out.println("file:"+getFile());
         System.out.println("filename:"+getFileFileName());
         System.out.println("filepath:"+getFileContentType());*/
try {
    String root = ServletActionContext.getServletContext().getRealPath("/upload/" + getFile_type()+"/"+getSection());
    //文件上传路径
   // System.out.println("root:"+root);
    FileClasses fileClasses = new FileClasses(getFileFileName(),getFile_type() , getSection());
    FileDAO fileDAO = new FileDAO();
    if (fileDAO.addDataBaseInfo(fileClasses)) {
       // System.out.println("插入数据库成功！");
        File rootpath = new File(root);
        if (!rootpath.exists())
            rootpath.mkdirs();
        // 如果指定的路径没有就创建

        //*****************************流方式上传*****************************8
        InputStream is = new FileInputStream(file);
        OutputStream os = new FileOutputStream(new File(root, fileFileName));
        //System.out.println("fileFileName: " + fileFileName);
        // 因为file是存放在临时文件夹的文件，我们可以将其文件名和文件路径打印出来，看和之前的fileFileName是否相同
        //  System.out.println("filename: " + file.getName());
        //  System.out.println("filepath: " + file.getPath());
        byte[] buffer = new byte[500];
        int length = 0;
        while (-1 != (length = is.read(buffer, 0, buffer.length))) {
            os.write(buffer);
        }
        os.close();
        is.close();
        return "success";
    }//数据库插入后执行的
    }//try
catch(Exception e){
        return "fail";
}
        return "fail";
    //*************************copy方式*****************************
/*
    try {

        FileUtils.copyFile(file, new File(rootpath, fileFileName));
        return "success";
    } // small try
    catch (IOException e) {

        // TODO Auto-generated catch block
        e.printStackTrace();
        return "fail";
    }
    }//big try
catch (Exception e) {
    return "fail";
}
    */

}//execute

}//class
