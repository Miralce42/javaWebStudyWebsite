package WebDB;


import java.sql.*;

/**
 * Created by Vove on 2017/3/21.
 *
 * 数据库连接、执行管理
 */
public class DB_Manager {
   public Connection getConnection() {
      return connection;
   }

   private Connection connection = null;
   private static final String driverStr = "com.mysql.jdbc.Driver";//驱动
   private static final String DB_Url = "jdbc:mysql://localhost:3306/javawebcourseresources?characterEncoding=utf8&useSSL=false";//连接字符串
/*   private static final String db_User = "webuser";//数据库登录名
   private static final String password = "iamuser";//登录密码*/
   private static final String db_User = "root";//数据库登录名
   private static final String password = "123456";//登录密码
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
      if(connection!=null){
         return true;
      }
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

   protected void finalize() {
      if (connection != null)
         try {
            connection.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
   }


   //事务流程写在外部
   public void beginAffair() throws SQLException {//事务开始
      if (!connect()) {
         System.out.println("连接失败");
      }
      connection.setAutoCommit(false);
   }
   public void Commit() throws SQLException{//事务提交
      if (!connect()) {
         System.out.println("连接失败");
      }
      connection.commit();
      connection.setAutoCommit(true);//恢复自动提交
   }
   public void rollbackAffair() throws SQLException{//回滚事务
      if (!connect()) {
         System.out.println("连接失败");
      }
      connection.rollback();
      connection.setAutoCommit(true);//恢复自动提交
   }
}
