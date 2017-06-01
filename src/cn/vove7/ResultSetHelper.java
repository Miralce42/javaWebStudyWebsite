package cn.vove7;


import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Vove on 2017/6/1.
 * //
 */
public class ResultSetHelper {
    //一行数据
    public static String getString(ResultSet resultSet,String columnLabel){
        try {
            if(resultSet.next()){
                return resultSet.getString(columnLabel);
            }else {
                return null;//未批改
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static ArrayList<String> getString(ResultSet resultSet,String[] cols){
        ArrayList<String> arrayList=new ArrayList<>();
        try {
            if(resultSet.next()){
                for(String col:cols){
                    arrayList.add(resultSet.getString(col));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return arrayList;
    }
}
