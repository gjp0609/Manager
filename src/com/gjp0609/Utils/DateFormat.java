package com.gjp0609.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
	public static String YMDDate(Date date) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy��MM��dd��HHʱmm��ss��");
		return sdf.format(date);
	}
}
