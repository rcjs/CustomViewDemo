package rcjs.com.customviewdemo.entitiy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 仁昌居士 on 2017/4/26.
 * Description:生成某年所有的日期
 */

public class DateFactory {

    //LinkedHashMap 是HashMap的一个子类，保存了记录的插入顺序
    /**
     * 平年，月份和天数相对应
     **/
    private static HashMap<Integer, Integer> nonLeapYear = new LinkedHashMap<>(12);
    /**
     * 闰年，月份和天数相对应
     **/
    private static HashMap<Integer, Integer> leapYear = new LinkedHashMap<>(12);

    static  {
        //初始化,只有2月份不同
        nonLeapYear.put(1, 31);
        leapYear.put(1, 31);
        nonLeapYear.put(2, 28);
        leapYear.put(2, 29);
        nonLeapYear.put(3, 31);
        leapYear.put(3, 31);
        nonLeapYear.put(4, 30);
        leapYear.put(4, 30);
        nonLeapYear.put(5, 31);
        leapYear.put(5, 31);
        nonLeapYear.put(6, 30);
        leapYear.put(6, 30);
        nonLeapYear.put(7, 31);
        leapYear.put(7, 31);
        nonLeapYear.put(8, 31);
        leapYear.put(8, 31);
        nonLeapYear.put(9, 30);
        leapYear.put(9, 30);
        nonLeapYear.put(10, 31);
        leapYear.put(10, 31);
        nonLeapYear.put(11, 30);
        leapYear.put(11, 30);
        nonLeapYear.put(12, 31);
        leapYear.put(12, 31);
    }

    /**
     * 输入年份和1月1日是周几
     *
     * @param year    年份
     * @param weekday 该年1月1日为周几
     * @return 该年1月1日到12月31日所有天数的相关信息
     */
    public static List<DayInfo> getDays(int year, int weekday) {
        List<DayInfo> dayInfoList = new ArrayList<>();
        boolean isLeapYear = isLeapYear(year);
        int dayNum = isLeapYear ? 366 : 365; //天数：闰年为366天,平年为365天
        DayInfo dayInfo;
        int realWeekday = weekday;//从1月1日开始起的周期性重复
        for (int i = 1; i <= dayNum; i++) {
            dayInfo = new DayInfo();
            dayInfo.year = year;
            //计算当天为周几,如果大于7就回到周一，即1
            dayInfo.week = realWeekday <= 7 ? realWeekday : 1;
            //计算当天为几月几号
            int[] monthAndDay = getMonthAndDay(isLeapYear, i);
            dayInfo.month = monthAndDay[0];
            dayInfo.date = monthAndDay[1];
            //重新赋值，并为了明天的计算而+1
            realWeekday = dayInfo.week;
            realWeekday++;
            dayInfoList.add(dayInfo);
        }
        checkDays(dayInfoList);
        return dayInfoList;
    }


    /**
     * 获取月和日
     *
     * @param isLeapYear 是否闰年
     * @param currentDay 当前天数
     * @return 包含月和天的数组
     */
    public static int[] getMonthAndDay(boolean isLeapYear, int currentDay) {
        HashMap<Integer, Integer> maps = isLeapYear ? leapYear : nonLeapYear;
        Set<Map.Entry<Integer, Integer>> set = maps.entrySet();
        int count = 0;
        Map.Entry<Integer, Integer> month = null;
        for (Map.Entry<Integer, Integer> entry : set) {
            count += entry.getValue();//每个月的天数相加，以用于比较得到是属于哪个月
            if (currentDay <= count) {
                month = entry;
                break;
            }
        }
        if (month == null) {
            throw new IllegalStateException("未找到所在的月份");
        }
        //前面所有月+一个月的完整天数 减去当前总天数为当月当天之后有几天， 再用当月总天数去减，可得是当月第几天。
        int day = month.getValue() - (count - currentDay);
        return new int[]{month.getKey(), day};
    }


    /**
     * 判断是闰年还是平年
     *
     * @param year 年份
     * @return true 为闰年
     */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * 检测生成的天数是否正常
     *
     * @param dayInfoList
     */
    private static void checkDays(List<DayInfo> dayInfoList) {
        if (dayInfoList == null) {
            throw new IllegalArgumentException("天数为空");
        }
        if (dayInfoList.size() != 365 && dayInfoList.size() != 366) {
            throw new IllegalArgumentException("天数异常:" + dayInfoList.size());
        }
    }

   /* public static void main(String[] args){
        //test
        List<DayInfo> days = DateFactory.getDays(2016, 5);
        for (int i = 0; i < days.size(); i++) {
            System.out.println(days.get(i).toString());
        }
    }*/
}
