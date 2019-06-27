import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @Author tianxin
 * @Date 2019/3/21
 * @Description:
 */
public class et {
    @Test
    public void fun12() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.getDayOfMonth());

        LocalDate temp = LocalDate.of(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());

        LocalDate.now().plusMonths(-1).with(TemporalAdjusters.firstDayOfMonth());
        LocalDate firstday = temp.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDay = temp.with(TemporalAdjusters.lastDayOfMonth());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = simpleDateFormat.format(localDate);
        System.out.println(time);

    }

    @Test
    public void fun2() throws ParseException {
        Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        String time = "2019-03-27 02:03:01";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parse = format.parse(time);
        long time1 = parse.getTime();
        System.out.println(time1);
        System.out.println(milliSecond);

    }

    @Test
    public void fun3() throws ParseException {
        String time = "2019-03-27 02:03:01";

        System.out.println( LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    }


}
