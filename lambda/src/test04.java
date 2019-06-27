import org.junit.Test;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author tianxin
 * @Date 2019/2/12
 * @Description:
 */
public class test04 {
    @Test
    public void fun1() {
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        setEndTime(1, calendar);
        Timestamp ts = new Timestamp(calendar.getTime().getTime());
        System.out.println(ts);
    }

    public Calendar setEndTime(int type, Calendar calendar) {
        switch (type) {
            case 1:
                calendar.add(Calendar.MONTH, 1);
                return calendar;
            case 2:
                calendar.add(Calendar.MONTH, 3);
                return calendar;
            case 3:
                calendar.add(Calendar.MONTH, 6);
                return calendar;
            case 4:
                calendar.add(Calendar.YEAR, 1);
                return calendar;
        }
        return calendar;
    }
}
