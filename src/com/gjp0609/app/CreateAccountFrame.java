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

import com.gjp0609.Utils.SecurityCode;

/**
 * @author ���� gjp0609:
 * @version ����ʱ�䣺2016��12��22�� ����10:49:13 ��˵��: ��������
 */
@SuppressWarnings("serial")
public class CreateAccountFrame extends JFrame implements ActionListener {

	JLabel lb_name;
	JTextField tf_name;
	JLabel lb_amount;
	JTextField tf_amount;
	JLabel lb_IDcard;
	JTextField tf_IDcard;
	JLabel lb_phone;
	JTextField tf_phone;
	JTextField tf_SecurityCode;
	JButton bt_SecurityCode;
	JButton bt_OK;
	JButton bt_reset;
	JScrollPane sp_message;
	JTextArea ta_message;
	String s = null;

	public CreateAccountFrame(String title) {
		super(title);
		init();
	}

	private void init() {
		setBounds(300, 300, 350, 500);
		setLayout(new FlowLayout());
		lb_name = new JLabel("�������û���:");
		tf_name = new JTextField(15);
		lb_amount = new JLabel("�������ʼ��:");
		tf_amount = new JTextField(15);
		lb_IDcard = new JLabel("���������֤��:");
		tf_IDcard = new JTextField(15);
		lb_phone = new JLabel("�������ֻ���:");
		tf_phone = new JTextField(15);
		tf_SecurityCode = new JTextField(12);
		bt_SecurityCode = new JButton("���һ�ȡ��֤��");
		bt_OK = new JButton("ȷ��");
		bt_reset = new JButton("����");
		ta_message = new JTextArea(5, 25);
		ta_message.setLineWrap(true);
		sp_message = new JScrollPane(ta_message);

		add(lb_name);
		add(tf_name);
		add(lb_amount);
		add(tf_amount);
		add(lb_IDcard);
		add(tf_IDcard);
		add(lb_phone);
		add(tf_phone);
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
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String Scode = tf_SecurityCode.getText();
		String phone = tf_phone.getText();
		String text = "������ʾ����������֤��Ϊ��";
		try {
			String phoneNum = tf_phone.getText();
			switch (e.getActionCommand()) {
			case "���һ�ȡ��֤��":
				if (phoneNum == null || "".equals(phoneNum))
					ta_message.setText("�Բ������벻��Ϊ�գ�");
				else {
					s = SecurityCode.sendGet(phone, text);
					ta_message.setText("��֤��Ϊ: " + s);
					bt_OK.setEnabled(true);
				}
				break;
			case "ȷ��":
				String name = tf_name.getText();
				String IDcard = tf_IDcard.getText();
				String amount = tf_amount.getText();
				if (name == null || "".equals(name) || phoneNum == null || "".equals(phoneNum) || IDcard == null
						|| "".equals(IDcard) || amount == null || "".equals(amount))
					ta_message.setText("�Բ������벻��Ϊ�գ�");
				else {
					float initAmount = Float.parseFloat(amount);
					if (initAmount > 0)
						// �����˻�
						if (Scode.equals(s)) {
						AccountBiz accountBiz = new AccountBiz();
						String strReturn = accountBiz.createAccount(name, initAmount, phone, IDcard);
						ta_message.setText(strReturn);
						setDefault();
						} else
						ta_message.setText("�Բ�����֤�����");
					else
						ta_message.setText("�Բ�������Ľ��Ϸ�");
				}
				break;
			case "����":
				setDefault();
				ta_message.setText("");
				break;
			default:
				System.exit(0);
				break;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void setDefault() {
		tf_name.setText("");
		tf_amount.setText("");
		tf_IDcard.setText("");
		tf_phone.setText("");
		tf_SecurityCode.setText("");
		// ta_message.setText("");
	}

}
