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
 * @version 2016-12-22 主界面
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
		label_info = new JLabel("欢迎使用银行管理系统");
		label_info.setHorizontalAlignment(JLabel.CENTER);
		bt_createAccount.setText("开户");
		bt_saveMoney.setText("存款");
		bt_getMoney.setText("取款");
		bt_turnAccount.setText("转账");
		bt_select.setText("查询");
		bt_updatePassword.setText("修改密码");
		bt_destroyAccount.setText("注销账户");
		bt_exit.setText("退出");
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
		new MainFrame("欢迎进入银行管理系统");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "开户":
			new CreateAccountFrame("开户");
			break;
		case "存款":
			new SaveMoneyFrame("存款");
			break;
		case "取款":
			new GetMoneyFrame("取款");
			break;
		case "转账":
			new TurnAccountFrame("转账");
			break;
		case "查询":
			new SelectAccountFrame("查询");
			break;
		case "修改密码":
			new ModPasswordFrame("修改密码");
			break;
		case "销户":
			new DestroyAccountFrame("注销账户");
			break;
		case "退出":
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
