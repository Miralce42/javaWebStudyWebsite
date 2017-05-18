package WebDB;


import java.sql.*;

/**
 * Created by Vove on 2017/3/21.
 */
public class DB_Manager {
    private Connection connection = null;
    private static final String driverStr="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动
    private static final String DB_Url = "jdbc:sqlserver://localhost\\LEON:55352;DatabaseName=WebDB";//连接字符串
    private static final String db_User = "WebUser";//数据库登录名
    private static final String password = "123";//登录密码
    private PreparedStatement pStatement = null;


    public int executeUpdate(String sql, String[] strs) {
        if (!connect()) {
            System.out.println("连接失败");
            return 0;
        }
        try {
            pStatement = connection.prepareStatement(sql);
            for (int i = 1; i <= strs.length; i++) {
                pStatement.setString(i, strs[i - 1]);
            }
            return pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ResultSet executeQuery(String sql, String[] strs) {
        if (!connect()) {
            System.out.println("连接失败");
            return null;
        }
        try {
            pStatement = connection.prepareStatement(sql);
            if (strs != null)
                for (int i = 1; i <= strs.length; i++) {
                    pStatement.setString(i, strs[i - 1]);
                }
            return pStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean connect() {
        try {
            try {
                Class.forName(driverStr);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            connection = DriverManager.getConnection(DB_Url, db_User, password);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void finalize()
    {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
