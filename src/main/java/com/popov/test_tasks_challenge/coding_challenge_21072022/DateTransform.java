package com.popov.test_tasks_challenge.coding_challenge_21072022;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An application requires different date formats to be converted into one common date format.
 * Implement the function transformDateFormat which accepts a list of dates as strings,
 * and return a new list of strings representing dates in the format of YYYYMMDD
 * All incoming dates will be valid dates, but those in one of the following formats:
 * YYYY/MM/DD, DD/MM/YYYY and MM-DD-YYYY should be included in the returning list!
 * For example:
 * ["2010/02/20", "19/12/2016", "11-18-2012", "20130720"] ->
 * [20100220, 20161219, 20121118]
 */
public class DateTransform {

    public static void main(String[] args) {
        List<String> dates =
                transformDateFormat(Arrays.asList("2010/02/20", "19/12/2016", "11-18-2012", "20130720"));
        System.out.println(dates);
    }

    public static List<String> transformDateFormat(List<String> dates) {
        return dates.stream()
                .map(DateTransform::reformatDate)
                .filter(d->!d.equals(""))
                .collect(Collectors.toList());
    }
    private static String reformatDate(String date) {
        int year;
        int month;
        int day;

        try {
            if (date.contains("/")) {
                String[] parse = date.split("/");
                if (parse[0].length() > 2) {
                    year = Integer.valueOf(parse[0]);
                    month = Integer.valueOf(parse[1]);
                    day = Integer.valueOf(parse[2]);
                } else {
                    year = Integer.valueOf(parse[2]);
                    month = Integer.valueOf(parse[1]);
                    day = Integer.valueOf(parse[0]);
                }
            } else if (date.contains("-")) {
                String[] parse = date.split("-");
                year = Integer.valueOf(parse[2]);
                month = Integer.valueOf(parse[0]);
                day = Integer.valueOf(parse[1]);
            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }

        if (year >= 1000 && year <= 9999 && month >= 1 && month <= 12 && day >= 1 && day <= 31) {
            return String.format("%04d%02d%02d",year, month, day);
        }
        return "";
    }
}
