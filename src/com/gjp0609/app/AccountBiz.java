package com.gjp0609.app;

import java.io.IOException;
import java.util.Date;

import com.gjp0609.Utils.DateFormat;
import com.gjp0609.Utils.SecurityCode;

/**
 * @author 作者 gjp0609:
 * @version 创建时间：2016年12月22日 下午2:41:02 类说明:
 */
public class AccountBiz {
	// 数据库表 “账户”的操作对象
	AccountDao accountDao = new AccountDao();

	/*
	 * 开户 （1）创建一帐户account，设置其姓名，创建日期（当前时间），金额，初始密码（666666）； （2）向Account表中增加记录；
	 * （3）向操作日志表中增加一条记录； （4）返回字符串对象（开户后的账号，初始密码（提示信息尽快修改长度为6位），开户金额，开户日期）。
	 */
	public String createAccount(String name, float initAmount, String phoneNum, String IDcard) {
		AccountBean account = new AccountBean();
		account.setName(name);
		account.setAmount(initAmount);
		account.setPhoneNum(phoneNum);
		account.setIDcard(IDcard);
		String initPassword =Integer.toString((int) (Math.random() * 999999));
		account.setPassword(initPassword);
		long accountId = accountDao.addAccount(account);
		Date date = new java.util.Date();
		String strDate = DateFormat.YMDDate(date);
		String strReturn = "开户后的账号:" + accountId + "\n初始密码为：" + initPassword + ",请尽快修改长度为6位的密码\n" + "开户金额:" + initAmount
				+ "\n开户日期：" + strDate;
		return strReturn;
	}

	// 存款
	/*
	 * （1）在账户表中根据输入的账号找到账户对象 （2）取出该对象的金额并加上money的值 （3）更新帐户记录
	 * （4）返回字符串对象（存储的金额，账号中现有金额，操作时间）
	 */

	public String saveMoney(int accountId, float money) {
		AccountBean account = accountDao.selectById(accountId);
		String strReturn = null;
		if (account != null) {
			float initMoney = account.getAmount();
			float newMoney = initMoney + money;
			accountDao.modMoney(accountId, newMoney);
			Date date = new java.util.Date();
			strReturn = "储户姓名：" + account.getName() + "\n原有金额:" + initMoney + "\n存储金额:" + money + "\n" + "账号中现有金额:"
					+ newMoney + "\n操作时间:" + DateFormat.YMDDate(date);
		} else
			strReturn = "对不起，您输入的账号不正确，查无此人！";
		return strReturn;
	}
	// 取款
	/*
	 * （1）在账户表中根据输入的账号和密码找到账户对象 （2）取出该对象的金额并减去money的值 （3）更新帐户记录
	 * （4）返回字符串对象（提取的金额，账号中现有的金额，操作时间）。
	 */

	public String getMoney(int accountId, String password, float money) {
		AccountBean account = accountDao.selectByIdAndPassword(accountId, password);
		String strReturn = null;
		float newMoney;
		if (account != null) {
			float initMoney = account.getAmount();
			if (money <= initMoney) {
				newMoney = initMoney - money;
				accountDao.modMoney(accountId, newMoney);
				Date date = new java.util.Date();
				strReturn = "储户姓名：" + account.getName() + "\n原有金额:" + initMoney + "\n取款金额:" + money + "\n" + "账号中现有金额:"
						+ newMoney + "\n操作时间:" + DateFormat.YMDDate(date);
			} else
				strReturn = "对不起，您输入的金额有误，请重新输入！";
		} else
			strReturn = "对不起，您输入的账号和密码不正确，查无此人！";
		return strReturn;
	}

	// 转帐
	/*
	 * （1）在账户表中根据输入的账号和密码找到转出账户对象 （2）根据输入的账号找到转入账户对象 （3）转出账号中的金额减去转帐金额
	 * （4）转入账号中的金额加上转帐金额 （5） 增加2条操作日志记录（分别记录转出和转入操作）
	 * （6）返回字符串对象（转帐的金额，转出账号现有的金额，本次操作的时间）
	 */

	public String turnAccount(int outAccountId, String password, int inAccountId, float money) {
		AccountBean outAccount = accountDao.selectByIdAndPassword(outAccountId, password);
		AccountBean inAccount = accountDao.selectById(inAccountId);
		String strReturn = null;

		if (outAccount != null && inAccount != null) {
			float outInitMoney = outAccount.getAmount();
			float inInitMoney = inAccount.getAmount();// 修正错误
			if (money <= outInitMoney) {
				outAccount.setAmount(outInitMoney - money);
				inAccount.setAmount(inInitMoney + money);
				accountDao.modMoney(outAccountId, outAccount.getAmount());
				accountDao.modMoney(inAccountId, inAccount.getAmount());
				Date date = new java.util.Date();
				strReturn = "转出账号:" + outAccountId + " 账户姓名:" + outAccount.getName() + "\n原有金额：" + outInitMoney
						+ " 转出的金额:" + money + " 现有金额:" + outAccount.getAmount() + "\n转入账号:" + inAccountId + " 账户姓名:"
						+ inAccount.getName() + "\n原有金额：" + inInitMoney + " 转入的金额:" + money + " 现有金额:"
						+ inAccount.getAmount() + "\n本次操作的时间:" + DateFormat.YMDDate(date);
			} else
				strReturn = "对不起，您输入的金额有误，请重新输入！";
		} else
			strReturn = "对不起，您输入的账号和密码不正确，查无此人！";
		return strReturn;
	}

	// 查询
	/*
	 * （1）在账户表中根据输入的账号和密码查询账户对象，能查到的话，继续；否则，返回null （2）根据账号查询操作日志表，读取链表信息
	 * （3）返回字符串对象（账户信息和日志信息——账号，姓名，开户日期，现有金额，{操作类型，操作金额，操作时间，旧密码，新密码}）
	 */
	public String selectAccount(int accountId, String password) {
		AccountBean account = accountDao.selectByIdAndPassword(accountId, password);
		if (account != null) {
			StringBuffer sbuf = new StringBuffer();
			sbuf.append("账号:" + accountId + "   姓名:" + account.getName() + "\n");
			sbuf.append("开户日期：" + account.getCreateDate() + "\n");
			sbuf.append(" 现有金额：" + account.getAmount() + "\n操作记录：\n");
			sbuf.append("操作类型  金额\t\t日期\t        备注\n");
			sbuf.append("-----------------------------\n");
			return sbuf.toString();
		} else
			return "用户名和密码不正确";
	}

	// 修改密码
	/*
	 * （1）在账户表中根据输入的账号和密码查询账户对象，能查到的话，继续；否则，返回false （2）更新账户对象的密码
	 * （3）在操作日志表中增加一条记录 （4）返回字符串对象，显示操作结果true或false
	 */
	public String updatePassword(int accountId, String oldPassword, String newPassword) {
		AccountBean account = accountDao.selectByIdAndPassword(accountId, oldPassword);
		String strReturn = null;
		if (account != null) {
			int count = accountDao.modPassword(accountId, newPassword);
			if (count > 0) {
				strReturn = "密码修改成功！";
			} else
				strReturn = "密码修改不成功！";
		} else
			strReturn = "用户名或密码不正确！";
		return strReturn;
	}

	// 销户
	/*
	 * （1）在账户表中根据输入的账号和密码查询账户对象，能查到的话，继续；否则，返回null （2）将账户的钱全部取出； （3）将该账户从表中删除
	 * （4）操作日志表增加2条记录 （5）返回操作的结果
	 */

	public String destroyAccount(int accountId, String password) {
		AccountBean account = accountDao.selectByIdAndPassword(accountId, password);
		String strReturn = null;
		if (account != null) {
			float amount = account.getAmount(); // 得到账户中的金额
			accountDao.delAccount(accountId); // 删除账户
			strReturn = "帐号：" + accountId + "取出现金：" + amount + "，销户成功！";
		} else
			strReturn = "用户名和密码不正确！";
		return strReturn;
	}

	public String getScodeById(int accountId) {
		AccountBean account = accountDao.selectById(accountId);
		String strReturn = null;
		if (account != null) {
			String phone = account.getPhoneNum();
			try {
				strReturn = SecurityCode.sendGet(phone, "银行提示您，您的验证码是：");
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		} else
			strReturn = "用户不存在！";
		return strReturn;
	}

}
