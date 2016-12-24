package com.gjp0609.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gjp0609.Utils.DBConnect;
import com.gjp0609.Utils.DateFormat;

/**
 * @author ���� gjp0609:
 * @version ����ʱ�䣺2016��12��22�� ����12:16:36 ��˵��:
 */

public class AccountDao {
	Connection con = DBConnect.getConnection();

	/*************** ���ݿ���� *********************/
	public long addAccount(AccountBean account) {
		// ���˻���������һ����¼��ÿ���ֶε�ֵȡ�Դ������ķ�װ����
		// �����ʺ�
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
			pst = con.prepareStatement(sqlInsert); /* ʵ����PreparedStatement�ӿڵ�ʵ�����ʵ�� */
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

	/*************** ���ݿ�ɾ�� *********************/
	public int delAccount(int accountId) {
		// �����˺�ɾ����¼
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

	/*************** ���ݿ���� *********************/
	public int modPassword(int accountId, String newPassword) {
		// ���ʺ�ΪaccountId�ļ�¼���������ΪnewPassword
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

	/*************** ���ݿ��ѯ *********************/
	public AccountBean selectById(int accountId) {
		// �����˺�id�õ��˻���ϸ��Ϣ
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
		// ���ʺ�ΪaccountId�ļ�¼�Ľ�����ΪnewPassword
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
		// �����˺�id������õ��˻���ϸ��Ϣ
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
