package com.gjp0609.app;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author ���� gjp0609:
 * @version ����ʱ�䣺2016��12��22�� ����1:01:16 ��˵��:
 */
@SuppressWarnings("serial")
public class SelectAccountFrame extends JFrame implements ActionListener {
	JLabel lb_accountId;
	JTextField tf_accountId;
	JLabel lb_password;
	JTextField tf_password;
	JButton bt_OK;
	JButton bt_reset;
	JScrollPane sp_message;
	JTextArea ta_message;

	public SelectAccountFrame(String title) {
		super(title);
		init();
	}

	private void init() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(300, 300, 400, 400);
		setLayout(new FlowLayout());
		lb_accountId = new JLabel("�������û��˺�:");
		tf_accountId = new JTextField(15);
		lb_password = new JLabel("�������û�����:");
		tf_password = new JTextField(15);
		bt_OK = new JButton("ȷ��");
		bt_reset = new JButton("����");
		ta_message = new JTextArea(10, 35);
		ta_message.setLineWrap(true);
		sp_message = new JScrollPane(ta_message);
		JPanel p1 = new JPanel();
		p1.add(lb_accountId);
		p1.add(tf_accountId);
		add(p1);
		JPanel p2 = new JPanel();
		p2.add(lb_password);
		p2.add(tf_password);
		add(p2);
		JPanel p3 = new JPanel();
		p3.add(bt_OK);
		p3.add(bt_reset);
		add(p3);
		add(sp_message);
		setVisible(true);
		bt_OK.addActionListener(this);
		bt_reset.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "ȷ��") {
			String str_accountId = tf_accountId.getText();
			String str_password = tf_password.getText();
			if (str_accountId == null || "".equals(str_accountId) || str_password == null || "".equals(str_password))
				ta_message.setText("�û��������벻��Ϊ��");
			else
				try {
					int accountId = Integer.parseInt(str_accountId);
					AccountBiz accountBiz = new AccountBiz();
					String strReturn = accountBiz.selectAccount(accountId, str_password);
					ta_message.setText(strReturn);
				} catch (NumberFormatException e1) {
					ta_message.setText("�˺����벻��ȷ������������");
					tf_accountId.setText("");
				}
		} else if (e.getActionCommand() == "����") {
			tf_accountId.setText("");
			tf_password.setText("");
			ta_message.setText("");
		}
	}

}
