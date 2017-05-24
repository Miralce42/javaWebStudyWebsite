package com.dream.Fileupload;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Dreamer on 2017/5/20.
 */
public class MultiUploadAction extends ActionSupport {
    //注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
    private List<File> file;
    private String Section;

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }

    /* 提交过来的file的名字 */
    private List<String> fileFileName;

    //提交过来的file的MIME类型
    private List<String> fileContentType;

    public List<File> getFile() {
        return file;
    }

    public void setFile(List<File> file) {
        this.file = file;
    }

    public List<String> getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(List<String> fileFileName) {
        this.fileFileName = fileFileName;
    }

    public List<String> getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(List<String> fileContentType) {
        this.fileContentType = fileContentType;
    }

    @Override
    public String execute() throws Exception
    {
try {
    String root = ServletActionContext.getServletContext().getRealPath("/upload/" + getSection());
    //文件上传路径

    File rootpath = new File(root);
    if (!rootpath.exists())
        rootpath.mkdirs();
    // 如果指定的路径没有就创建
    //*************************copy方式*****************************
    for (int i = 0; i < file.size(); i++) {
        try {
            FileUtils.copyFile(file.get(i), new File(rootpath, fileFileName.get(i)));
          //  return "success";
        } // small try
        catch (IOException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
            return "fail";
        }
    }//for
    }//big try
catch (Exception e) {
    return "fail";
}
return  "success";
}//execute

}//class
