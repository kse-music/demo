package com.hiekn.demo.test.java.java8;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

/**
 * Instant 它代表的是时间戳，比如2014-01-14T02:20:13.592Z，这可以从java.time.Clock类中获取，像这样： Instant current = Clock.system(ZoneId.of(“Asia/Tokyo”)).instant();
 LocalDate 它表示的是不带时间的日期，比如2014-01-14。它可以用来存储生日，周年纪念日，入职日期等。
 LocalTime – 它表示的是不带日期的时间
 LocalDateTime – 它包含了时间与日期，不过没有带时区的偏移量
 ZonedDateTime – 这是一个带时区的完整时间，它根据UTC/格林威治时间来进行时区调整
 */
public class Java8Time {

    public static void main(String[] args) {

        //第一个是Clock类，它通过指定一个时区，然后就可以获取到当前的时刻，日期与时间。Clock可以替换System.currentTimeMillis()与TimeZone.getDefault()。

        // Get the system clock as UTC offset
        final Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.instant());
        System.out.println(clock.millis());
        System.out.println(Clock.systemUTC().millis());

        Instant timestamp = Instant.now();
        System.out.println(timestamp);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println(currentZone);
        System.out.println("Local date: " + LocalDateTime.ofInstant(timestamp,currentZone));

        final LocalDateTime datetime = LocalDateTime.now().withNano(0);
        final LocalDateTime datetimeFromClock = LocalDateTime.now( clock );

        System.out.println(datetime);
        System.out.println(datetimeFromClock );

        final LocalDate date = LocalDate.of(2020,2,3);
        System.out.println(date.isLeapYear());

        LocalDate today = LocalDate.now();
        // 取本月第1天：
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstDayOfThisMonth);
        // 取本月第2天：
        LocalDate secondDayOfThisMonth = today.withDayOfMonth(2);
        System.out.println(secondDayOfThisMonth);

        // 取本月最后一天，再也不用计算是28，29，30还是31：
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDayOfThisMonth);

        // 取下一天：
        LocalDate firstDayOf2018 = lastDayOfThisMonth.plusDays(1);
        System.out.println(firstDayOf2018);

        // 取2018年1月第一个周一，这个计算用Calendar要死掉很多脑细胞：
        LocalDate firstMondayOf2018 = LocalDate.parse("2018-01-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)); // 2018-01-01
        System.out.println(firstMondayOf2018);

    }

}
