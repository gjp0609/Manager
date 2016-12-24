package com.gjp0609.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gjp0609.Utils.DBConnect;
import com.gjp0609.Utils.DateFormat;

/**
 * @author 作者 gjp0609:
 * @version 创建时间：2016年12月22日 下午12:16:36 类说明:
 */

public class AccountDao {
	Connection con = DBConnect.getConnection();

	/*************** 数据库插入 *********************/
	public long addAccount(AccountBean account) {
		// 向账户表中增加一条记录，每个字段的值取自传进来的封装参数
		// 返回帐号
		String sqlInsert = "insert into account(name,amount,phoneNum,IDcard,password,id) values(?,?,?,?,?,?)";
		String sqlSelect = "select max(id) from account";
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		long countId = 0;
		try {
			pst2 = con.prepareStatement(sqlSelect);
			ResultSet rs = pst2.executeQuery();
			if (rs.next()) {
				account = new AccountBean();
				account.setId(rs.getLong(1) + 1);
				countId = rs.getLong(1) + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pst2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		try {
			pst = con.prepareStatement(sqlInsert); /* 实现了PreparedStatement接口的实现类的实例 */
			pst.setString(1, account.getName());
			pst.setFloat(2, account.getAmount());
			pst.setString(3, account.getPhoneNum());
			pst.setString(4, account.getIDcard());
			pst.setString(5, account.getPassword());
			pst.setLong(6, account.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e2) {
				e2.getMessage();
			}
		}
		return countId;
	}

	/*************** 数据库删除 *********************/
	public int delAccount(int accountId) {
		// 根据账号删除记录
		String sqlDelete = "delete from account where id=?";
		PreparedStatement pst;
		int count = 0;
		try {
			pst = con.prepareStatement(sqlDelete);
			pst.setInt(1, accountId);
			count = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/*************** 数据库更新 *********************/
	public int modPassword(int accountId, String newPassword) {
		// 把帐号为accountId的记录的密码更新为newPassword
		String sqlUpdate = "update account set isLock='n',password=? where id=?";
		PreparedStatement pst;
		int count = 0;
		try {
			pst = con.prepareStatement(sqlUpdate);
			pst.setString(1, newPassword);
			pst.setInt(2, accountId);
			count = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/*************** 数据库查询 *********************/
	public AccountBean selectById(int accountId) {
		// 根据账号id得到账户详细信息
		String sqlSelect = "select * from  account where id=?";
		AccountBean account = null;
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(sqlSelect);
			pst.setInt(1, accountId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				account = new AccountBean();
				account.setName(rs.getString(1));
				account.setAmount(rs.getFloat(2));
				account.setPhoneNum(rs.getString(3));
				account.setIDcard(rs.getString(4));
				account.setPassword(rs.getString(5));
				account.setId(rs.getInt(6));
				account.setCreateDate(DateFormat.YMDDate(rs.getTimestamp(7)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

	public int modMoney(int accountId, float newAmount) {
		// 把帐号为accountId的记录的金额更新为newPassword
		String sqlUpdate = "update account set amount=? where id=?";
		PreparedStatement pst;
		int count = 0;
		try {
			pst = con.prepareStatement(sqlUpdate);
			pst.setFloat(1, newAmount);
			pst.setInt(2, accountId);
			count = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public AccountBean selectByIdAndPassword(int accountId, String password) {
		// 根据账号id和密码得到账户详细信息
		String sqlSelect = "select * from  account where id=? and password=?";
		AccountBean account = null;
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(sqlSelect);
			pst.setInt(1, accountId);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				account = new AccountBean();
				account.setName(rs.getString(1));
				account.setAmount(rs.getFloat(2));
				account.setPhoneNum(rs.getString(3));
				account.setIDcard(rs.getString(4));
				account.setPassword(rs.getString(5));
				account.setId(rs.getInt(6));
				account.setCreateDate(DateFormat.YMDDate(rs.getTimestamp(7)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

}
