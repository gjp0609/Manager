package com.gjp0609.app;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import com.gjp0609.Utils.BlueishButtonUI;
import com.gjp0609.Utils.RButton;

/**
 * @author gjp0609
 * @version 2016-12-22 ������
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {

	JLabel label_info;
	RButton bt_createAccount=new RButton();
	RButton bt_saveMoney=new RButton();
	RButton bt_getMoney=new RButton();
	RButton bt_turnAccount=new RButton();
	RButton bt_select=new RButton();
	RButton bt_updatePassword=new RButton();
	RButton bt_destroyAccount=new RButton();
	RButton bt_exit=new RButton();
	RButton bt_test=new RButton();
	public MainFrame(String title) {
		super(title);
		init();
	}

	private void init() {
		label_info = new JLabel("��ӭʹ�����й���ϵͳ");
		label_info.setHorizontalAlignment(JLabel.CENTER);
		bt_createAccount.setText("����");
		bt_saveMoney.setText("���");
		bt_getMoney.setText("ȡ��");
		bt_turnAccount.setText("ת��");
		bt_select.setText("��ѯ");
		bt_updatePassword.setText("�޸�����");
		bt_destroyAccount.setText("ע���˻�");
		bt_exit.setText("�˳�");
		add(label_info);
		add(bt_createAccount);
		add(bt_saveMoney);
		add(bt_getMoney);
		add(bt_turnAccount);
		add(bt_select);
		add(bt_updatePassword);
		add(bt_destroyAccount);
		add(bt_exit);
		bt_createAccount.addActionListener(this);
		bt_saveMoney.addActionListener(this);
		bt_getMoney.addActionListener(this);
		bt_turnAccount.addActionListener(this);
		bt_select.addActionListener(this);
		bt_updatePassword.addActionListener(this);
		bt_destroyAccount.addActionListener(this);
		bt_exit.addActionListener(this);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(500, 300, 400, 600);
		setLayout(new GridLayout(9, 1));
		setBackground(Color.decode("#F2F2F2"));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new MainFrame("��ӭ�������й���ϵͳ");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "����":
			new CreateAccountFrame("����");
			break;
		case "���":
			new SaveMoneyFrame("���");
			break;
		case "ȡ��":
			new GetMoneyFrame("ȡ��");
			break;
		case "ת��":
			new TurnAccountFrame("ת��");
			break;
		case "��ѯ":
			new SelectAccountFrame("��ѯ");
			break;
		case "�޸�����":
			new ModPasswordFrame("�޸�����");
			break;
		case "����":
			new DestroyAccountFrame("ע���˻�");
			break;
		case "�˳�":
			System.exit(0);
			break;
		case "Test":
			System.out.println("Test");
			break;
		default:
			System.exit(0);
			break;
		}

	}

}
