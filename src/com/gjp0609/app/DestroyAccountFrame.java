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
 * @author 作者 gjp0609:
 * @version 创建时间：2016年12月22日 下午4:30:29 类说明:
 */
@SuppressWarnings("serial")
public class DestroyAccountFrame extends JFrame implements ActionListener {

	JLabel lb_accountId;
	JTextField tf_accountId;
	JLabel lb_password;
	JTextField tf_password;
	JTextField tf_SecurityCode;
	JButton bt_SecurityCode;
	JButton bt_OK;
	JButton bt_reset;
	JScrollPane sp_message;
	JTextArea ta_message;
	String s = null;

	public DestroyAccountFrame(String title) {
		super(title);
		init();
	}

	private void init() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(300, 300, 300, 300);
		setLayout(new FlowLayout());
		lb_accountId = new JLabel("请输入用户账号:");
		tf_accountId = new JTextField(15);
		lb_password = new JLabel("请输入用户密码:");
		tf_password = new JTextField(15);
		tf_SecurityCode = new JTextField(12);
		bt_SecurityCode = new JButton("点我获取验证码");
		bt_OK = new JButton("确定");
		bt_reset = new JButton("重置");
		ta_message = new JTextArea(5, 25);
		ta_message.setLineWrap(true);
		sp_message = new JScrollPane(ta_message);
		add(lb_accountId);
		add(tf_accountId);
		add(lb_password);
		add(tf_password);
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
		String str_password = tf_password.getText();
		switch (e.getActionCommand()) {
		case "点我获取验证码":
			if (str_accountId == null || "".equals(str_accountId) || str_password == null || "".equals(str_password))
				ta_message.setText("用户名,密码均不能为空");
			else {
				int accountId = Integer.parseInt(str_accountId);
				AccountBiz accountBiz = new AccountBiz();
				String strReturn = accountBiz.getScodeById(accountId);
				if (strReturn.equals("用户不存在！")) {
					ta_message.setText("账号或密码输入错误");
					return;
				} else {
					s = strReturn;
					ta_message.setText("验证码已发，请注意查收");
				}
				bt_OK.setEnabled(true);
				bt_SecurityCode.setEnabled(false);
			}
			break;
		case "确定":
			if (str_accountId == null || "".equals(str_accountId) || str_password == null || "".equals(str_password)
					|| Scode == null || "".equals(Scode))
				ta_message.setText("用户名,密码,验证码均不能为空");
			else if (Scode.equals(s))
				try {
					int accountId = Integer.parseInt(str_accountId);
					AccountBiz accountBiz = new AccountBiz();
					String strReturn = accountBiz.destroyAccount(accountId, str_password);
					ta_message.setText(strReturn);
				} catch (NumberFormatException e1) {
					ta_message.setText("密码输入不正确，请重新输入");
					tf_accountId.setText("");
					tf_password.setText("");
				}
			else {
				ta_message.setText("验证码输入不正确，请重新输入");
				bt_SecurityCode.setEnabled(true);
			}
			break;
		case "重置":
			tf_accountId.setText("");
			tf_password.setText("");
			tf_SecurityCode.setText("");
			ta_message.setText("");
			break;
		default:
			break;
		}
	}

}
