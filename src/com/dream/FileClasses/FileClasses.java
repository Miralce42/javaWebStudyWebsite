package com.dream.FileClasses;

/**
 * Created by Dreamer on 2017/5/22.
 */
public class FileClasses {
    private String fileName;
    private String fileType;
    private String section;

    public FileClasses(String courseName,String fileType,String section){
        this.fileName =courseName;
        this.fileType=fileType;
        this.section=section;
    }
    public FileClasses(){

    }
    public String getSection() {
        return section;
    }
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName)  {this.fileName = fileName;
    }
    public enum FileType{
        TECH_OUTLINE,//教学大纲
        TECH_PLAN,//教案
        COURSEWARE,//教学课件
        REFERENCE_MATERIAL,//参考资料
        RESOURCE,//教学资源，环境下载
        教学课件资料,
        教学资源环境资料,
        实践教学资料,
        其他资料,
    }
}
