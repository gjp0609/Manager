package com.gjp0609.app;

/**
 * @author ���� gjp0609:
 * @version ����ʱ�䣺2016��12��22�� ����12:25:30 ��˵��:
 */
public class AccountBean {
	private long id;
	private String name;
	private String createDate;
	private float amount;
	private String phoneNum;// �绰����
	private String IDcard;// ���֤��
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long l) {
		this.id = l;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getIDcard() {
		return IDcard;
	}

	public void setIDcard(String iDcard) {
		this.IDcard = iDcard;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
