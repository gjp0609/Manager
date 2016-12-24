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
 * @version ����ʱ�䣺2016��12��22�� ����2:32:20 ��˵��:
 */
@SuppressWarnings("serial")
public class TurnAccountFrame extends JFrame implements ActionListener {

	JLabel lb_outAccountId;
	JTextField tf_outAccountId;
	JLabel lb_password;
	JTextField tf_password;
	JLabel lb_inAccountId;
	JTextField tf_inAccountId;
	JLabel lb_amount;
	JTextField tf_amount;
	JTextField tf_SecurityCode;
	JButton bt_SecurityCode;
	JButton bt_OK;
	JButton bt_reset;
	JScrollPane sp_message;
	JTextArea ta_message;

	String s = null;

	public TurnAccountFrame(String title) {
		super(title);
		init();
	}

	private void init() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(300, 300, 320, 300);
		setLayout(new FlowLayout());
		lb_outAccountId = new JLabel("������ת���˺�:");
		tf_outAccountId = new JTextField(16);
		lb_password = new JLabel("�������û�����:");
		tf_password = new JTextField(16);
		lb_inAccountId = new JLabel("������ת���˺�:");
		tf_inAccountId = new JTextField(16);
		lb_amount = new JLabel("������ת�˽��:");
		tf_amount = new JTextField(16);
		tf_SecurityCode = new JTextField(12);
		bt_SecurityCode = new JButton("���һ�ȡ��֤��");
		bt_OK = new JButton("ȷ��");
		bt_reset = new JButton("����");
		ta_message = new JTextArea(8, 28);
		ta_message.setLineWrap(true);
		sp_message = new JScrollPane(ta_message);

		add(lb_outAccountId);
		add(tf_outAccountId);
		add(lb_password);
		add(tf_password);
		add(lb_inAccountId);
		add(tf_inAccountId);
		add(lb_amount);
		add(tf_amount);
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
		String str_outAccountId = tf_outAccountId.getText();
		String str_password = tf_password.getText();
		String str_inAccountId = tf_inAccountId.getText();
		String amount = tf_amount.getText();
		String Scode = tf_SecurityCode.getText();
		switch (e.getActionCommand()) {
		case "���һ�ȡ��֤��":
			if (str_outAccountId == null || "".equals(str_outAccountId) || str_inAccountId == null
					|| "".equals(str_inAccountId) || str_password == null || "".equals(str_password) || amount == null
					|| "".equals(amount)) {

				ta_message.setText("�û���,���롢��������Ϊ��");
				return;

			} else {
				int accountId = Integer.parseInt(str_outAccountId);
				 AccountBiz accountBiz = new AccountBiz();
				 String strReturn = accountBiz.getScodeById(accountId);
				 if(strReturn.equals("�û������ڣ�")){
				 ta_message.setText("�˺Ż������������");
				 return;
				 }else{
				 s = strReturn;
				 ta_message.setText("��֤���ѷ�����ע�����");
				 }
				bt_OK.setEnabled(true);
				bt_SecurityCode.setEnabled(false);
			}
			break;
		case "ȷ��":
			if (str_outAccountId == null || "".equals(str_outAccountId) || str_inAccountId == null
					|| "".equals(str_inAccountId) || str_password == null || "".equals(str_password) || amount == null
					|| "".equals(amount) || Scode == null || "".equals(Scode)) {
				ta_message.setText("�Բ����������ת���˺š����롢�����˺š���֤���п�ֵ");
			} else if (Scode.equals(s))
				try {
					int outAccountId = Integer.parseInt(str_outAccountId);
					int inAccountId = Integer.parseInt(str_inAccountId);
					float turnAmount = Float.parseFloat(amount);
					 AccountBiz accountBiz=new AccountBiz();
					 String strReturn = accountBiz.turnAccount(outAccountId,
					 str_password, inAccountId, turnAmount);
					 ta_message.setText(strReturn);
				} catch (NumberFormatException e1) {
					ta_message.setText("�Բ����������ת���˺š������˺š�ת�˽���зǷ��ַ�");
				}
			else {
				ta_message.setText("��֤�����벻��ȷ������������");
				bt_SecurityCode.setEnabled(true);
			}
			break;
		case "����":
			tf_outAccountId.setText("");
			tf_amount.setText("");
			tf_outAccountId.setText("");
			tf_password.setText("");
			tf_SecurityCode.setText("");
			tf_inAccountId.setText("");
			ta_message.setText("");
			break;
		default:
			break;
		}
	}

}
