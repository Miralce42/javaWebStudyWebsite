package actions;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Vove on 2017/5/17.
 */
public class UploadImage extends ActionSupport {
    private File upload;  //文件
    private String uploadContentType;  //文件类型
    private String uploadFileName;   //文件名

    public String uploadImage() throws Exception {

        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        PrintWriter out = response.getWriter();

        // CKEditor提交的很重要的一个参数
        String callback = request.getParameter("CKEditorFuncNum");

        String expandedName;  //文件扩展名
        switch (uploadContentType) {
            case "image/pjpeg":
            case "image/jpeg":
                //IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg
                expandedName = ".jpg";
                break;
            case "image/png":
            case "image/x-png":
                //IE6上传的png图片的headimageContentType是"image/x-png"
                expandedName = ".png";
                break;
            case "image/gif":
                expandedName = ".gif";
                break;
            case "image/bmp":
                expandedName = ".bmp";
                break;
            default:
                out.println("<script type=\"text/javascript\">");
                out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");
                out.println("</script>");
                return null;
        }

        if (upload.length() > 600 * 1024) {
            out.println("<script type=\"text/javascript\">");
            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'文件大小不得大于600k');");
            out.println("</script>");
            return null;
        }


        InputStream is = new FileInputStream(upload);
        String uploadPath = ServletActionContext.getServletContext()
                .getRealPath("/uploadImages");

        String fileName = java.util.UUID.randomUUID().toString();  //采用时间+UUID的方式随即命名
        fileName += expandedName;
        File toFile = new File(uploadPath, fileName);
        OutputStream os = new FileOutputStream(toFile);
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        is.close();
        os.close();

        // 返回“图像”选项卡并显示图片
        out.println("<script type=\"text/javascript\">");
        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + "../uploadImages/" + fileName + "','')");
        out.println("</script>");

        return null;

    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
}
