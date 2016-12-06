package main;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.YEARS;

/**
 * JDK 8 下的时间日期相关工具类
 * java.time包关键类：
 * Instant：时间戳
 * LocalDate：不包含具体时间的日期（yyyy-MM-dd）
 * LocalTime：不含日期的时间
 * LocalDateTime：日期+时间 ，没有时区
 * ZonedDateTime：日期+时间+时区
 * <p>
 * Created by androidjp on 2016/11/27.
 */
public class DateTimeUtils {
    /**
     * 获取今天的日期信息
     */
    public static LocalDate showToday() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        System.out.println("Today is : " + today);
        System.out.printf("Year:%d Month:%d day:%d\n", year, month, day);
        return today;
    }

    /**
     * 获取自定义日期的LocalDate类型变量
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return LocalDate类型的对象
     */
    public static LocalDate setBirthday(int year, int month, int day) {
        LocalDate dateOfBirth = LocalDate.of(year, month, day);
        System.out.println("生日是：" + dateOfBirth);
        return dateOfBirth;
    }

    /**
     * 比较日期是否相等
     */
    public static boolean isDateEquals(LocalDate date1, LocalDate date2) {
        return date1.equals(date2);
    }

    /**
     * "月-日"部分匹配
     */
    public static boolean isMonthDateEquals(LocalDate date1, LocalDate date2) {
        MonthDay monthDay1 = MonthDay.of(date1.getMonth(), date1.getDayOfMonth());
        MonthDay monthDay2 = MonthDay.from(date2);
        return (monthDay1.equals(monthDay2));
    }

    /**
     * "年-月"部分匹配
     * 如：信用卡还款日，定期存款到期日，options到期日这类的日期
     */
    public static boolean isYearMonthEquals(LocalDate date1, LocalDate date2) {
        YearMonth yearMonth1 = YearMonth.of(date1.getYear(), date1.getMonth());
        YearMonth yearMonth2 = YearMonth.from(date2);
        return (yearMonth1.equals(yearMonth2));
    }

    /**
     * 输出当前时间
     */
    public static LocalTime currentTime() {
        LocalTime time = LocalTime.now();
        System.out.println("当前时刻： " + time);
        return time;
    }

    /**
     * 未来的时刻
     */
    public static LocalTime futureTime(long plusHours, long plusMin, long plusSeconds) {
        LocalTime time = LocalTime.now();
        return time.plusHours(plusHours).plusMinutes(plusMin).plusSeconds(plusSeconds);
    }


    /**
     * 一周后
     */
    public static LocalDate nextWeek() {
        return LocalDate.now().plus(1, ChronoUnit.WEEKS);
    }

    /**
     * 一年前后的日期
     */
    public static void yearBeforeAndFuture() {
        LocalDate today = showToday();
        LocalDate previousYear = today.minus(1, YEARS);
        System.out.println("Date before 1 year : " + previousYear);
        LocalDate nextYear = today.plus(1, YEARS);
        System.out.println("Date after 1 year : " + nextYear);
    }

    /**
     * 时钟 的 用法
     */
    public static void clock() {
        // Returns the current time based on your system clock and set to UTC.
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock);

// Returns time based on system clock zone Clock defaultClock =
        Clock.systemDefaultZone();
        System.out.println("Clock : " + clock);

        ///可以通过传入指定日期来与时钟做比较：
//        public class MyClass {
//            private Clock clock; // dependency inject ...
//
//            public void process(LocalDate eventDate) {
//
//                if(eventDate.isBefore(LocalDate.now(clock)) {
//                 ...
//                }
//            }
//        }

    }

    ///比较两个日期的先后顺序
    public static int compareDate(LocalDate date1, LocalDate date2) {
        if (date1.isBefore(date2)) {
            return -1;
        } else if (date1.isAfter(date2)) {
            return 1;
        } else
            return 0;
    }


    //处理不同时区
    public static void dealWithZone() {
        // Date and time with timezone in Java 8
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localtDateAndTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america);
        System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);

    }

    ///检查闰年
    public static boolean checkLeapYear(LocalDate date) {
        return date.isLeapYear();
    }

    ///获取两个时间点之间相隔了？月？天
    public static void getPeriodDays(LocalDate from, LocalDate to) {
        Period periodToNextJavaRelease =
                Period.between(from, to);
        System.out.println("Months left between today and Java 8 release : " + periodToNextJavaRelease.getMonths() + "，天数是：" + periodToNextJavaRelease.getDays());
    }

    ///例子：获取带时区偏移量的日期与时间
    public static OffsetDateTime getOffsetDateTime() {
        LocalDateTime datetime = LocalDateTime.of(2014, Month.JANUARY, 14, 19, 30);
        ZoneOffset offset = ZoneOffset.of("+05:30");
        OffsetDateTime date = OffsetDateTime.of(datetime, offset);
        System.out.println("Date and Time with timezone offset in Java : " + date);
        return date;
    }

    public static void curTimeStamp(){
        Instant timestamp = Instant.now();
        System.out.println("当前时间戳： "+ timestamp);
    }

    ////格式化（类似于：DateFormat）
    public static void formatDate(){
        String dayAfterTommorrow = "20140116";
        LocalDate formatted = LocalDate.parse(dayAfterTommorrow,
                DateTimeFormatter.BASIC_ISO_DATE);
        System.out.printf("Date generated from String %s is %s %n", dayAfterTommorrow, formatted);
    }

    ///自定义格式化 日期
    public static void curstomFormatDate(){
        String goodFriday = "Apr 18 2014";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            LocalDate holiday = LocalDate.parse(goodFriday, formatter);
            System.out.printf("Successfully parsed String %s, date is %s%n", goodFriday, holiday);
        } catch (DateTimeParseException ex) {
            System.out.printf("%s is not parsable!%n", goodFriday);
            ex.printStackTrace();
        }
    }

    ///格式化日期，生成字符串
    public static void dateFormatString(){
        LocalDateTime arrivalDate = LocalDateTime.now();
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
            String landing = arrivalDate.format(format);
            System.out.printf("Arriving at : %s %n", landing);
        } catch (DateTimeException ex) {
            System.out.printf("%s can't be formatted!%n", arrivalDate);
            ex.printStackTrace();
        }
    }

}
