package com.dream.FileClasses;
import WebDB.DB_Manager;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Created by Dreamer on 2017/5/22.
 */
public class FileDAO {
    private DB_Manager DB=new DB_Manager();

    public boolean addDataBaseInfo(FileClasses fileClasses){
        boolean result=false;//默认
         String[] sql=new String[]{fileClasses.getFileName(),fileClasses.getFileType(),fileClasses.getSection()};
        int num=0;
        try {
            /*String filename=fileClasses.getFileName();
            String filetype=fileClasses.getFileType();
            String createtie=fileClasses.getCreateTime();
            String section=fileClasses.getSection();
            System.out.println(filename);
            System.out.println(filetype);
            System.out.println(createtie);
            System.out.println(section);
            System.out.println("开始执行插入语句！");*/

            String stringSQL="insert teachingfile(file_name,file_type,upload_date,chapter) values(?,?,now(),?)";
             try {
                 num=DB.executeUpdate(stringSQL,sql);

                 if(num!=0){
                     result=true;
                 }
             }
               catch (Exception e){
                 e.printStackTrace();
               }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        dbClose();//关闭数据库链接
        return result;
    }
    public ResultSet getResultSet(String sql,String[] strs) {
        return DB.executeQuery(sql, strs);
    }

    public boolean deleteFile(FileClasses fileClasses){
        boolean result=false;
        String[] temps=new String[]{fileClasses.getFileType(),fileClasses.getSection(),fileClasses.getFileName()+"%"};
        System.out.println(fileClasses.getFileType());
        System.out.println(fileClasses.getSection());
        System.out.println(fileClasses.getFileName());
        try {
             int num=0;
           // System.out.println("here");
             /*DB.executeQuery("update set sql_safe_updates=0;",null);
             System.out.println("here");*/
             num=DB.executeUpdate("delete from teachingfile where file_type=? and chapter=? and file_name like ?;", temps);//text字段影响用like
           // System.out.println("here");
             if(num!=0)result=true;
         }
         catch (Exception e) {
             e.printStackTrace();
         }
        System.out.println(result);
         return result;
    }
    public void dbClose() {
        if (DB.getConnection() != null)
            try {
                DB.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
