package com.gjp0609.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** 
* @author 作者 gjp0609: 
* @version 创建时间：2016年12月22日 上午11:02:53 
* 类说明: 
*/

public class DBConnect {
	
	 public static void main(String[] args) {
		System.out.println(DBConnect.getConnection());
	       String sql = "select ename from emp";
		    System.out.println(sql);
			ResultSet set =   DBConnect.executeQuery(sql);
			try {
				while(set.next()){
					System.out.println(set.getString("ename"));
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		  }
	
	/**
	 * 声明JDBC相关对象
	 */
	protected static Statement s=null;
	protected static ResultSet rs = null;
	private static Connection conn = null;
	/**
	 * 创建数据库连接
	 * @return conn
	 */
	public static Connection getConnection(){
	    try {
	    	Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:" + "thin:@127.0.0.1:1521:orcl";
			try {
				conn = DriverManager.getConnection(url, "scott", "123456");// 获取连接
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}	
		return conn;
	}
	/**
	 * 执行INSERT/UPDATE/DELETE SQL语句
	 * @param sql SQL语句，字符串类型
	 * @return 执行结果，int类型
	 */
	public static int executeUpdate(String sql)
	{
		int result = 0;
		try {
			s = getConnection().createStatement();
			result = s.executeUpdate(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 执行SELECT SQL语句
	 * @param sql SQL语句，字符串类型
	 * @return ResultSet结果集
	 */
	public static ResultSet executeQuery(String sql)
	{
		
		try {
			s = getConnection().createStatement();
			rs = s.executeQuery(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * 执行动态SQL语句
	 * @param sql 含有参数的动态SQL语句。 
	 * @return 返回PreparedStatement对象
	 */
	public static PreparedStatement executePreparedStatement(String sql)
	{
		PreparedStatement ps = null;
		try
		{
			ps = getConnection().prepareStatement(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return ps;
	}
	/**
	 * 事务回滚
	 */
	public static void rollback() {
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	/**
	 * 关闭数据库连接对象
	 */
	public static void close()
	{
		try
		{
			if(rs!=null)
				rs.close();
			if(s!= null)
				s.close();
			if(conn!=null)
				conn.close();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
}
