package com.gjp0609.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** 
* @author ���� gjp0609: 
* @version ����ʱ�䣺2016��12��22�� ����11:02:53 
* ��˵��: 
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
	 * ����JDBC��ض���
	 */
	protected static Statement s=null;
	protected static ResultSet rs = null;
	private static Connection conn = null;
	/**
	 * �������ݿ�����
	 * @return conn
	 */
	public static Connection getConnection(){
	    try {
	    	Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:" + "thin:@127.0.0.1:1521:orcl";
			try {
				conn = DriverManager.getConnection(url, "scott", "123456");// ��ȡ����
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}	
		return conn;
	}
	/**
	 * ִ��INSERT/UPDATE/DELETE SQL���
	 * @param sql SQL��䣬�ַ�������
	 * @return ִ�н����int����
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
	 * ִ��SELECT SQL���
	 * @param sql SQL��䣬�ַ�������
	 * @return ResultSet�����
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
	 * ִ�ж�̬SQL���
	 * @param sql ���в����Ķ�̬SQL��䡣 
	 * @return ����PreparedStatement����
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
	 * ����ع�
	 */
	public static void rollback() {
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	/**
	 * �ر����ݿ����Ӷ���
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
