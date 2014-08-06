package demos;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CloneDemo {
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		month = month + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		System.out.println(year + " " + month + " " + day + " " + hour + " " + minute + " " + second);
		
		
		SimpleDateFormat s=new SimpleDateFormat("yyyyMMddHHmmss");
		String curDate = s.format(calendar.getTime());  //当前日期
		System.out.println(curDate);
		 
		 
		 
		PPoint p0 = new PPoint(11 ,22);
		PPoint p1 = new PPoint(p0);
		PPoint p2 = new PPoint(5, 6);
		System.out.println(p1);
		System.out.println(p2);
		p2 = CloneUtils.clone(p1);
		p1.next.setX(22);
		p1.next.setY(33);
		p1.setX(55);
		p1.setY(66);
		
		System.out.println("P1:" + p1 + " P2：" + p2) ;
		System.out.println(p2 == p1);
	}
}
