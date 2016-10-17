package multigold.testservice;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Test {
	public static void main(String[] args) {
		
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
//		calendar.add(Calendar.MONTH, -2);
//		calendar.set(Calendar.DAY_OF_MONTH, 1);
//		calendar.set(Calendar.HOUR_OF_DAY, 0);
//		calendar.set(Calendar.MINUTE, 0);
//		calendar.set(Calendar.SECOND, 0);
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String endTime = sdf.format(calendar.getTime());
		System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
	}
}
