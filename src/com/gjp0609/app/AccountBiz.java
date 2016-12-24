package com.gjp0609.app;

import java.io.IOException;
import java.util.Date;

import com.gjp0609.Utils.DateFormat;
import com.gjp0609.Utils.SecurityCode;

/**
 * @author ���� gjp0609:
 * @version ����ʱ�䣺2016��12��22�� ����2:41:02 ��˵��:
 */
public class AccountBiz {
	// ���ݿ�� ���˻����Ĳ�������
	AccountDao accountDao = new AccountDao();

	/*
	 * ���� ��1������һ�ʻ�account���������������������ڣ���ǰʱ�䣩������ʼ���루666666���� ��2����Account�������Ӽ�¼��
	 * ��3���������־��������һ����¼�� ��4�������ַ������󣨿�������˺ţ���ʼ���루��ʾ��Ϣ�����޸ĳ���Ϊ6λ�����������������ڣ���
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
		String strReturn = "��������˺�:" + accountId + "\n��ʼ����Ϊ��" + initPassword + ",�뾡���޸ĳ���Ϊ6λ������\n" + "�������:" + initAmount
				+ "\n�������ڣ�" + strDate;
		return strReturn;
	}

	// ���
	/*
	 * ��1�����˻����и���������˺��ҵ��˻����� ��2��ȡ���ö���Ľ�����money��ֵ ��3�������ʻ���¼
	 * ��4�������ַ������󣨴洢�Ľ��˺������н�����ʱ�䣩
	 */

	public String saveMoney(int accountId, float money) {
		AccountBean account = accountDao.selectById(accountId);
		String strReturn = null;
		if (account != null) {
			float initMoney = account.getAmount();
			float newMoney = initMoney + money;
			accountDao.modMoney(accountId, newMoney);
			Date date = new java.util.Date();
			strReturn = "����������" + account.getName() + "\nԭ�н��:" + initMoney + "\n�洢���:" + money + "\n" + "�˺������н��:"
					+ newMoney + "\n����ʱ��:" + DateFormat.YMDDate(date);
		} else
			strReturn = "�Բ�����������˺Ų���ȷ�����޴��ˣ�";
		return strReturn;
	}
	// ȡ��
	/*
	 * ��1�����˻����и���������˺ź������ҵ��˻����� ��2��ȡ���ö���Ľ���ȥmoney��ֵ ��3�������ʻ���¼
	 * ��4�������ַ���������ȡ�Ľ��˺������еĽ�����ʱ�䣩��
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
				strReturn = "����������" + account.getName() + "\nԭ�н��:" + initMoney + "\nȡ����:" + money + "\n" + "�˺������н��:"
						+ newMoney + "\n����ʱ��:" + DateFormat.YMDDate(date);
			} else
				strReturn = "�Բ���������Ľ���������������룡";
		} else
			strReturn = "�Բ�����������˺ź����벻��ȷ�����޴��ˣ�";
		return strReturn;
	}

	// ת��
	/*
	 * ��1�����˻����и���������˺ź������ҵ�ת���˻����� ��2������������˺��ҵ�ת���˻����� ��3��ת���˺��еĽ���ȥת�ʽ��
	 * ��4��ת���˺��еĽ�����ת�ʽ�� ��5�� ����2��������־��¼���ֱ��¼ת����ת�������
	 * ��6�������ַ�������ת�ʵĽ�ת���˺����еĽ����β�����ʱ�䣩
	 */

	public String turnAccount(int outAccountId, String password, int inAccountId, float money) {
		AccountBean outAccount = accountDao.selectByIdAndPassword(outAccountId, password);
		AccountBean inAccount = accountDao.selectById(inAccountId);
		String strReturn = null;

		if (outAccount != null && inAccount != null) {
			float outInitMoney = outAccount.getAmount();
			float inInitMoney = inAccount.getAmount();// ��������
			if (money <= outInitMoney) {
				outAccount.setAmount(outInitMoney - money);
				inAccount.setAmount(inInitMoney + money);
				accountDao.modMoney(outAccountId, outAccount.getAmount());
				accountDao.modMoney(inAccountId, inAccount.getAmount());
				Date date = new java.util.Date();
				strReturn = "ת���˺�:" + outAccountId + " �˻�����:" + outAccount.getName() + "\nԭ�н�" + outInitMoney
						+ " ת���Ľ��:" + money + " ���н��:" + outAccount.getAmount() + "\nת���˺�:" + inAccountId + " �˻�����:"
						+ inAccount.getName() + "\nԭ�н�" + inInitMoney + " ת��Ľ��:" + money + " ���н��:"
						+ inAccount.getAmount() + "\n���β�����ʱ��:" + DateFormat.YMDDate(date);
			} else
				strReturn = "�Բ���������Ľ���������������룡";
		} else
			strReturn = "�Բ�����������˺ź����벻��ȷ�����޴��ˣ�";
		return strReturn;
	}

	// ��ѯ
	/*
	 * ��1�����˻����и���������˺ź������ѯ�˻������ܲ鵽�Ļ������������򣬷���null ��2�������˺Ų�ѯ������־����ȡ������Ϣ
	 * ��3�������ַ��������˻���Ϣ����־��Ϣ�����˺ţ��������������ڣ����н�{�������ͣ�����������ʱ�䣬�����룬������}��
	 */
	public String selectAccount(int accountId, String password) {
		AccountBean account = accountDao.selectByIdAndPassword(accountId, password);
		if (account != null) {
			StringBuffer sbuf = new StringBuffer();
			sbuf.append("�˺�:" + accountId + "   ����:" + account.getName() + "\n");
			sbuf.append("�������ڣ�" + account.getCreateDate() + "\n");
			sbuf.append(" ���н�" + account.getAmount() + "\n������¼��\n");
			sbuf.append("��������  ���\t\t����\t        ��ע\n");
			sbuf.append("-----------------------------\n");
			return sbuf.toString();
		} else
			return "�û��������벻��ȷ";
	}

	// �޸�����
	/*
	 * ��1�����˻����и���������˺ź������ѯ�˻������ܲ鵽�Ļ������������򣬷���false ��2�������˻����������
	 * ��3���ڲ�����־��������һ����¼ ��4�������ַ���������ʾ�������true��false
	 */
	public String updatePassword(int accountId, String oldPassword, String newPassword) {
		AccountBean account = accountDao.selectByIdAndPassword(accountId, oldPassword);
		String strReturn = null;
		if (account != null) {
			int count = accountDao.modPassword(accountId, newPassword);
			if (count > 0) {
				strReturn = "�����޸ĳɹ���";
			} else
				strReturn = "�����޸Ĳ��ɹ���";
		} else
			strReturn = "�û��������벻��ȷ��";
		return strReturn;
	}

	// ����
	/*
	 * ��1�����˻����и���������˺ź������ѯ�˻������ܲ鵽�Ļ������������򣬷���null ��2�����˻���Ǯȫ��ȡ���� ��3�������˻��ӱ���ɾ��
	 * ��4��������־������2����¼ ��5�����ز����Ľ��
	 */

	public String destroyAccount(int accountId, String password) {
		AccountBean account = accountDao.selectByIdAndPassword(accountId, password);
		String strReturn = null;
		if (account != null) {
			float amount = account.getAmount(); // �õ��˻��еĽ��
			accountDao.delAccount(accountId); // ɾ���˻�
			strReturn = "�ʺţ�" + accountId + "ȡ���ֽ�" + amount + "�������ɹ���";
		} else
			strReturn = "�û��������벻��ȷ��";
		return strReturn;
	}

	public String getScodeById(int accountId) {
		AccountBean account = accountDao.selectById(accountId);
		String strReturn = null;
		if (account != null) {
			String phone = account.getPhoneNum();
			try {
				strReturn = SecurityCode.sendGet(phone, "������ʾ����������֤���ǣ�");
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		} else
			strReturn = "�û������ڣ�";
		return strReturn;
	}

}
