package com.gjp0609.Utils;

import java.io.IOException;
import java.util.Random;

public class SecurityCode {

	public static String sendGet(String aim, String text) throws IOException {
		String num = makeNum();
		return num;
	}

	private static String makeNum() {
		Random r = new Random();
		String num = r.nextInt(9999) + "";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 4 - num.length(); i++)
			sb.append("0"); // ������λ���á�0������
		num = sb.toString() + num; // �ַ����ϲ�
		return num;
	}

}