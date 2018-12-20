package com.wallaw.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 日期转换工具类
 *
 * @since 2018-8-21 16:54:19
 */

public class DateUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    public static final SimpleDateFormat yyyyMMdd             = new SimpleDateFormat("yyyyMMdd");
    public static final SimpleDateFormat yyyy_MM_dd           = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat yyyyDOTMMDOTdd       = new SimpleDateFormat("yyyy.MM.dd");
    public static final SimpleDateFormat yyyy_MM_ddHHmmss     = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat yyyyMMddHHmmss       = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final SimpleDateFormat yyyy_MM_ddHHmm       = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final SimpleDateFormat yyyyLineMMLineddHHmm = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    public static final SimpleDateFormat HHmm                 = new SimpleDateFormat("HH.mm");
    public static final Object           lock                 = new Object();

    public static String formateDateyyyyMMdd(Date date) {
        synchronized (lock) {
            return yyyyMMdd.format(date);
        }
    }

    public static String formateDateyyyyMMddHHmm(Date date) {
        synchronized (lock) {
            return yyyy_MM_ddHHmm.format(date);
        }
    }

    public static String formateyyyyLineMMLineddHHmm(Date date) {
        synchronized (lock) {
            return yyyyLineMMLineddHHmm.format(date);
        }
    }

    public static String formateyyyyDOTMMDOTdd(Date date) {
        synchronized (lock) {
            return yyyyDOTMMDOTdd.format(date);
        }
    }

    public static String formateDateyyyy_MM_ddHHmmss(Date date) {
        synchronized (lock) {
            return yyyy_MM_ddHHmmss.format(date);
        }
    }

    public static String formatHHmm(Date date) {
        synchronized (lock) {
            return HHmm.format(date);
        }
    }

    public static String formateDateyyyy_MM_dd(Date date) {
        synchronized (lock) {
            return yyyy_MM_dd.format(date);
        }
    }

    public static String formateDateyyyyMMddHHmmss(Date date) {
        synchronized (lock) {
            return yyyyMMddHHmmss.format(date);
        }
    }

    /**
     *
     * @param dateStr 时间串
     * @return 转化结果，无法转化则new date
     */
    public static Date parseDate(String dateStr) {
        synchronized (lock) {
            try {
                return yyyy_MM_ddHHmmss.parse(dateStr);
            } catch (Exception e) {
                try {
                    return yyyy_MM_dd.parse(dateStr);
                } catch (Exception e2) {
                    try {
                        return yyyyMMdd.parse(dateStr);
                    } catch (Exception e3) {
                        LOGGER.error("can't parse to Date :{}", dateStr);
                        return new Date();
                    }
                }
            }
        }
    }

    /**
     * 日期转化，精度到分钟
     * @param dateStr
     * @return
     */
    public static Date parseDateMinutes(String dateStr) {
        Date date = null;
        synchronized (lock) {
            try {
                date = yyyy_MM_ddHHmm.parse(dateStr);
            } catch (ParseException e) {
                LOGGER.error("can't parse to Date :{}", dateStr);
            }
        }
        return date;
    }

    /**
     * 获取下一天时间串
     *
     * @param dateStr
     * @return 下一天的时间串
     */
    public static String getNextDayString(String dateStr) {
        Date date = null;
        synchronized (lock) {
            try {
                date = yyyy_MM_ddHHmmss.parse(dateStr);
                date.setTime(date.getTime() + 24 * 60 * 60 * 1000);
                return formateDateyyyy_MM_ddHHmmss(date);
            } catch (Exception e) {
                try {
                    date = yyyy_MM_dd.parse(dateStr);
                    date.setTime(date.getTime() + 24 * 60 * 60 * 1000);
                    return formateDateyyyy_MM_dd(date);
                } catch (Exception e2) {
                    try {
                        date = yyyyMMdd.parse(dateStr);
                        date.setTime(date.getTime() + 24 * 60 * 60 * 1000);
                        return formateDateyyyyMMdd(date);
                    } catch (Exception e3) {
                        LOGGER.error("cant getNextDayString :{}", dateStr, e3);
                        return "";
                    }
                }
            }
        }
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间 串
     * @param bdate  较大的时间 串
     * @return 相差天数
     */
    public static int daysBetween(String smdate, String bdate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(parseDate(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(parseDate(bdate));
        long time2 = cal.getTimeInMillis();
        long betweenDays = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(betweenDays));
    }

    /**
     * 两个时间之间的时间差
     * @param startTime 较小时间
     * @param endTime 较大时间
     * @return
     */
    public static String betweenTime(Date startTime, Date endTime) {
        long nd = 1000 * 24 * 60 * 60; //一天的毫秒数
        long nh = 1000 * 60 * 60; //一小时的毫秒数
        long nm = 1000 * 60; //一分钟的毫秒数
        long ns = 1000; //一秒钟的毫秒数

        //获得两个时间的毫秒时间差异
        long diff = endTime.getTime() - startTime.getTime();
        diff = Math.abs(diff);  //取绝对值
        long day = diff / nd;
        long hour = diff % nd / nh;
        long min = diff % nd % nh / nm;
        long sec = diff % nd % nh % nm / ns;
        String result = "";
        if (day != 0) {
            result = day + "天";
        }
        if (hour != 0) {
            result = result + hour + "小时";
        }
        if (min != 0) {
            result = result + min + "分钟";
        }
        //return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
        return result;
    }

    /**
     * 获取两个时间之间的小时差值
     * @param startTime
     * @param endTime
     * @return
     */
    public static Integer betweenHour(Date startTime, Date endTime) {
        long nh = 1000 * 60 * 60; //一小时的毫秒数
        long diff = endTime.getTime() - startTime.getTime();
        Long hour = diff / nh;
        return hour.intValue();
    }

    /**
     * 得到之后的时间.
     * @param d 具体时间
     * @param day 几天之后
     * @return
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 获取指定当天的时间起止，终止的时间段
     *
     * @param date 指定时间
     * @return 返回map类型key：beginDate 当天的起止时间，endDate 当天的最后时间（下一天的凌晨时间）
     * @author ganbo
     * @date 2016年8月25日
     */
    public static Map<String, String> getDateTime(Date date) {
        Map<String, String> map = new HashMap<String, String>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        map.put("beginDate", calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
                + calendar.get(Calendar.DAY_OF_MONTH));

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        map.put("endDate", calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
                + calendar.get(Calendar.DAY_OF_MONTH));

        return map;
    }

    /**
     * 获取月日
     * 例如11月23日
     * @param date
     * @return
     */
    public static String getMonthDay(Date date) {
        StringBuilder sb = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        sb.append(calendar.get(Calendar.MONTH) + 1).append("月");
        sb.append(calendar.get(Calendar.DAY_OF_MONTH)).append("日");
        return sb.toString();
    }

    /**
     * 获取时分
     * 例如11:23
     * @param date
     * @return
     */
    public static String getHourMinutes(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(date);
    }

}
