package com.hiekn.demo.test.java8;

import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;

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
        System.out.println( clock.instant() );
        System.out.println( clock.millis() );
        System.out.println(Clock.systemUTC().millis());


        Instant timestamp = Instant.now();
        System.out.println(timestamp);

        final LocalDateTime datetime = LocalDateTime.now();
        final LocalDateTime datetimeFromClock = LocalDateTime.now( clock );

        System.out.println( datetime);
        System.out.println( datetimeFromClock );

        final LocalDate date = LocalDate.of(2020,2,3);
        System.out.println(date.isLeapYear());

        String text = "dd";
        System.out.println(Base64.getEncoder().encodeToString(text.getBytes()));
    }

}
