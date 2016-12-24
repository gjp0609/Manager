package com.gjp0609.Utils;

/**
 * @author 作者 gjp0609:
 * @version 创建时间：2016年12月22日 下午2:53:31 类说明:
 */
public class OperTypeConvert {
	public static String typeIdToName(int i) {
		String str = null;
		switch (i) {
		case 1:
			str = "开户";
			break;
		case 2:
			str = "存款";
			break;
		case 3:
			str = "取款";
			break;
		case 4:
			str = "转账";
			break;
		case 5:
			str = "查询";
			break;
		case 6:
			str = "修改密码";
			break;
		case 7:
			str = "销户";
			break;
		}
		return str;
	}
}
