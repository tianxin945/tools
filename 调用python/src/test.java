import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author tianxin
 * @Date 2019/2/20
 * @Description:
 */
public class test {
    public static void main(String[] args) throws ParseException {
      /*  String time2 = "20100714";
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date date = df.parse(time2);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,-1);
        String format = df.format(calendar.getTime());
        System.out.println(format);*/
        double danger = 5;
        double total = 11;


        String s = (String.valueOf((danger / (total > 0 ? total : 1)) * 100) + "0000").substring(0, 5) + "%";

        System.out.println(s);
    }
}
