package com.gjp0609.app;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author ���� gjp0609:
 * @version ����ʱ�䣺2016��12��22�� ����4:23:37 ��˵��:
 */
@SuppressWarnings("serial")
public class ModPasswordFrame extends JFrame implements ActionListener {
	JLabel lb_accountId;
	JTextField tf_accountId;
	JLabel lb_oldPassword;
	JTextField tf_oldPassword;
	JLabel lb_confirmPassword;
	JTextField tf_confirmPassword;
	JLabel lb_newPassword;
	JTextField tf_newPassword;
	JTextField tf_SecurityCode;
	JButton bt_SecurityCode;
	JButton bt_OK;
	JButton bt_reset;
	JScrollPane sp_message;
	JTextArea ta_message;
	String s = null;

	public ModPasswordFrame(String title) {
		super(title);
		init();
	}

	private void init() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(300, 300, 300, 300);
		setLayout(new FlowLayout());
		lb_accountId = new JLabel("�������û��˺�:");
		tf_accountId = new JTextField(15);
		lb_oldPassword = new JLabel("������ԭ������:");
		tf_oldPassword = new JTextField(15);
		lb_newPassword = new JLabel("������������:");
		tf_newPassword = new JTextField(15);
		lb_confirmPassword = new JLabel("���ٴ���������:");
		tf_confirmPassword = new JTextField(15);
		tf_SecurityCode = new JTextField(12);
		bt_SecurityCode = new JButton("���һ�ȡ��֤��");
		bt_OK = new JButton("ȷ��");
		bt_reset = new JButton("����");
		ta_message = new JTextArea(5, 25);
		ta_message.setLineWrap(true);
		sp_message = new JScrollPane(ta_message);
		add(lb_accountId);
		add(tf_accountId);
		add(lb_oldPassword);
		add(tf_oldPassword);
		add(lb_newPassword);
		add(tf_newPassword);
		add(lb_confirmPassword);
		add(tf_confirmPassword);
		add(tf_SecurityCode);
		add(bt_SecurityCode);
		add(bt_OK);
		add(bt_reset);
		add(sp_message);
		setVisible(true);
		bt_OK.addActionListener(this);
		bt_reset.addActionListener(this);
		bt_SecurityCode.addActionListener(this);
		bt_OK.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String Scode = tf_SecurityCode.getText();
		String str_accountId = tf_accountId.getText();
		String str_oldPassword = tf_oldPassword.getText();
		String str_newPassword = tf_newPassword.getText();
		String str_confirmPassword = tf_confirmPassword.getText();
		switch (e.getActionCommand()) {
		case "���һ�ȡ��֤��":
			if (str_accountId == null || "".equals(str_accountId) || str_oldPassword == null
					|| "".equals(str_oldPassword) || str_confirmPassword == null || "".equals(str_confirmPassword)
					|| str_newPassword == null || "".equals(str_newPassword))
				ta_message.setText("�û���,���������Ϊ��");
			else {
				int accountId = Integer.parseInt(str_accountId);
				AccountBiz accountBiz = new AccountBiz();
				String strReturn = accountBiz.getScodeById(accountId);
				if (strReturn.equals("�û������ڣ�")) {
					ta_message.setText("�˺Ż������������");
					return;
				} else {
					s = strReturn;
					ta_message.setText("��֤���ѷ�����ע�����");
				}
				bt_OK.setEnabled(true);
				bt_SecurityCode.setEnabled(false);
			}
			break;
		case "ȷ��":
			if (str_accountId == null || "".equals(str_accountId) || str_oldPassword == null
					|| "".equals(str_oldPassword) || str_confirmPassword == null || "".equals(str_confirmPassword)
					|| str_newPassword == null || "".equals(str_newPassword) || Scode == null || "".equals(Scode))
				ta_message.setText("�˺š����롢ȷ�����롢��֤�������Ϊ�գ�");
			else if (Scode.equals(s))
				if (str_newPassword.equals(str_confirmPassword)) {
					AccountBiz accountBiz = new AccountBiz();
					int accountId = Integer.parseInt(str_accountId);
					String strReturn = accountBiz.updatePassword(accountId, str_oldPassword, str_newPassword);
					ta_message.setText(strReturn);
				} else
					ta_message.setText("������������벻һ�£�");
			else {
				ta_message.setText("��֤�����벻��ȷ������������");
				bt_SecurityCode.setEnabled(true);
			}
			break;
		case "����":
			tf_accountId.setText("");
			tf_oldPassword.setText("");
			tf_confirmPassword.setText("");
			tf_newPassword.setText("");
			tf_SecurityCode.setText("");
			ta_message.setText("");
			break;
		default:
			break;
		}
	}

}
