import java.util.Calendar;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class MainClass{

	private static  Date getDate(String pattern,String _dt) throws ParseException {
		SimpleDateFormat date_f = new SimpleDateFormat(pattern);
		Date date2 = null;
 		date2 = date_f.parse(_dt);
		return date2;
	}
public static String validTime(String datetime,String time){
try{ 
  	 
     Calendar cal = Calendar.getInstance();
     
     cal.setTime(getDate("HH:mm:ss",time));
   //  cal1.setTime(t1); 
     int hh =cal.get(Calendar.HOUR_OF_DAY);
     int mm =cal.get(Calendar.MINUTE);
     int ss =cal.get(Calendar.SECOND);
    
     cal.setTime( getDate("dd-MM-yyyy HH:mm:ss",datetime));
     cal.set(Calendar.HOUR_OF_DAY,hh);
     cal.set(Calendar.MINUTE,mm);
     cal.set(Calendar.SECOND,ss);

     java.util.Date utilDate = cal.getTime();
     SimpleDateFormat dp=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
     String res = dp.format(utilDate);

      return res;

}
catch (Exception e){ e.getMessage();
System.out.println("error "+e.getMessage());

}
return time;
}

public static void main(String[] args) {
String date=validTime("30-01-2018 00:00:00","03:60:22");
System.out.println("day is"+date);
}
}