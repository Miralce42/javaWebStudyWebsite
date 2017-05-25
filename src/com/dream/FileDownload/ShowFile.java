package com.dream.FileDownload;



import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Dreamer on 2017/5/21.
 */
public class ShowFile {
    private static ArrayList<String> filelist = new ArrayList<String>();
    /*
     * 通过递归得到某一路径下所有的目录及其文件
     */
    public void getFiles(String filePath) {

      //  filePath = ServletActionContext.getServletContext().getRealPath("/upload/第一章/");
        System.out.println(filePath);
        File root = new File(filePath);
        File[] files = root.listFiles();
        for (File file : files) {//等价于for(int i =0;i<fs.length;i++){File f= fs[i];......}
            if (!file.isDirectory())
                System.out.println("显示" + filePath + "下所有子目录" + file.getAbsolutePath());
            else {
      /*
       * 递归调用
       */

                getFiles(file.getAbsolutePath());
                filelist.add(file.getAbsolutePath());
                System.out.println("显示" + filePath + "下所有子目录及其文件" + file.getAbsolutePath());
            }
        }
    }
    public File[] getAllFiles(String section) {

        String filePath = ServletActionContext.getServletContext().getRealPath(section);
       // System.out.println(filePath);
        File root = new File(filePath);
        File[] files = root.listFiles();
        return  files;
    }

}
