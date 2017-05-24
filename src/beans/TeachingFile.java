package beans;

/**
 * Created by Vove on 2017/5/19.
 *
 * 教学文件
 */

public class TeachingFile {
    private FileType fileType;
    private String fileName;
    private String uploadDate="now()";
    private int chapter;

    public TeachingFile(FileType fileType, String fileName, String uploadDate, int chapter) {
        this.fileType = fileType;
        this.fileName = fileName;
        this.uploadDate = uploadDate;
        this.chapter = chapter;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {this.chapter =  chapter;
    }

    public enum FileType{
        TECH_OUTLINE,//教学大纲
        TECH_PLAN,//教案
        COURSEWARE,//教学课件
        REFERENCE_MATERIAL,//参考资料
        RESOURCE//教学资源，环境下载
    }
}
