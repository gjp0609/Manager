package com.gjp0609.Utils;

/**
 * @author ���� gjp0609:
 * @version ����ʱ�䣺2016��12��22�� ����2:53:31 ��˵��:
 */
public class OperTypeConvert {
	public static String typeIdToName(int i) {
		String str = null;
		switch (i) {
		case 1:
			str = "����";
			break;
		case 2:
			str = "���";
			break;
		case 3:
			str = "ȡ��";
			break;
		case 4:
			str = "ת��";
			break;
		case 5:
			str = "��ѯ";
			break;
		case 6:
			str = "�޸�����";
			break;
		case 7:
			str = "����";
			break;
		}
		return str;
	}
}