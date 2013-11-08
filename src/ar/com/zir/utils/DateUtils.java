/*
    Copyright 2012 Juan Martín Runge
    
    jmrunge@gmail.com
    http://www.zirsi.com.ar
    
    This file is part of ZirUtils.

    ZirUtils is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    ZirUtils is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with ZirUtils.  If not, see <http://www.gnu.org/licenses/>.
*/
package ar.com.zir.utils;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.*;

/**
 * Utility class for performing common operations on dates
 * 
 * @author jmrunge
 * @version 1.00
 */
public class DateUtils {
    /**
     * Equivalent to Calendar.DAY_OF_MONTH
     * @see java.util.Calendar
     */
    public static final int DATE_PART_DAY = Calendar.DAY_OF_MONTH;
    /**
     * Equivalent to Calendar.MONTH
     * @see java.util.Calendar
     */
    public static final int DATE_PART_MONTH = Calendar.MONTH;
    /**
     * Equivalent to Calendar.YEAR
     * @see java.util.Calendar
     */
    public static final int DATE_PART_YEAR = Calendar.YEAR;
    /**
     * Equivalent to Calendar.HOUR_OF_DAY
     * @see java.util.Calendar
     */
    public static final int DATE_PART_HOUR = Calendar.HOUR_OF_DAY;
    /**
     * Equivalent to Calendar.MINUTE
     * @see java.util.Calendar
     */
    public static final int DATE_PART_MINUTE = Calendar.MINUTE;
    /**
     * Equivalent to Calendar.SECOND
     * @see java.util.Calendar
     */
    public static final int DATE_PART_SECOND = Calendar.SECOND;

    /**
     * Method for parsing a String into a date with default format (dd/MM/yyyy)
     * 
     * @param date String to be parsed
     * @return resultant Date Object
     * @throws ParseException inherited from SimpleDateFormat.parse(String)
     * @see java.text.SimpleDateFormat
     */
    public static Date parse(String date) throws ParseException {
        return parse(date, null);
    }

    /**
     * Method for parsing a String into a Date with specified format
     * 
     * @param date String to be parsed
     * @param format the format
     * @return resultant Date Object
     * @throws ParseException inherited from SimpleDateFormat.parse(String)
     * @see java.text.SimpleDateFormat
     */
    public static Date parse(String date, String format) throws ParseException {
        if (date == null) return null;
        if (format == null) format = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(date);
    }

    /**
     * Method for formatting a Date into a String with the default format (dd/MM/yyyy)
     * 
     * @param date Date to be formatted
     * @return resultant String object
     * @see java.text.SimpleDateFormat
     */
    public static String format(Date date) {
        return format(date, null);
    }

    /**
     * Method for formatting a Date into a String with the specified format
     * 
     * @param date Date to be formatted
     * @param format the format
     * @return resultant String object
     * @see java.text.SimpleDateFormat
     */
    public static String format(Date date, String format) {
        if (date == null) return null;
        if (format == null) format = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * Method that uses Joda Library to obtain the difference between two dates
     * in the specified date part
     * 
     * @param datePart the datePart to use in the calculation
     * @param date1 first Date object
     * @param date2 second Date object
     * @return the result from date1 - date2 or -1 if specified datePart is not supported
     * @see java.util.Calendar
     */
    public static int dateDiff(int datePart, Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        switch (datePart) {
            case DATE_PART_SECOND:
                return Seconds.secondsBetween(new DateTime(cal2.getTimeInMillis()), new DateTime(cal1.getTimeInMillis())).getSeconds();
            case DATE_PART_MINUTE:
                return Minutes.minutesBetween(new DateTime(cal2.getTimeInMillis()), new DateTime(cal1.getTimeInMillis())).getMinutes();
            case DATE_PART_HOUR:
                return Hours.hoursBetween(new DateTime(cal2.getTimeInMillis()), new DateTime(cal1.getTimeInMillis())).getHours();
            case DATE_PART_DAY:
                return Days.daysBetween(new DateTime(cal2.getTimeInMillis()), new DateTime(cal1.getTimeInMillis())).getDays();
            case DATE_PART_MONTH:
                return Months.monthsBetween(new DateTime(cal2.getTimeInMillis()), new DateTime(cal1.getTimeInMillis())).getMonths();
            case DATE_PART_YEAR:
                return Years.yearsBetween(new DateTime(cal2.getTimeInMillis()), new DateTime(cal1.getTimeInMillis())).getYears();
            default:
                return -1;
        }
        
        
    }

    /**
     * Method that returns a Date object constructed from the Date object received for 
     * day, month and year and adds 0 hour, 0 minute and 0 seconds to it
     * 
     * @param date Date object to take as basis
     * @return the resultant Date
     * @see java.util.Calendar
     */
    public static Date firstHourOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(DATE_PART_HOUR, 0);
        cal.set(DATE_PART_MINUTE, 0);
        cal.set(DATE_PART_SECOND, 0);
        return cal.getTime();
    }

    /**
     * Method that returns a Date object constructed from the Date object received for 
     * day, month and year and adds 23 hour, 59 minute and 59 seconds to it
     * 
     * @param date Date object to take as basis
     * @return the resultant Date
     * @see java.util.Calendar
     */
    public static Date lastHourOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(DATE_PART_HOUR, 23);
        cal.set(DATE_PART_MINUTE, 59);
        cal.set(DATE_PART_SECOND, 59);
        return cal.getTime();
    }

    /**
     * Method that returns the result of date + amount for the specified datePart
     * using Calendar.get(int) for obtaining it
     * 
     * @param datePart the datePart to use in the calculation
     * @param amount  amount to add to date
     * @param date date for adding amount to
     * @return the result from date + amount
     * @see java.util.Calendar
     */
    public static Date dateAdd(int datePart, int amount, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(datePart, cal.get(datePart) + amount);
        return cal.getTime();
    }

    /**
     * Method for obtaining the value of the specified datePart for the specified Date
     * 
     * @param datePart the datePart to obtain
     * @param date the date from obtaining it
     * @return the value od datePart on date
     * @see java.util.Calendar
     */
    public static int datePart(int datePart, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(datePart);
    }

    /**
     * Method to check if two specified date ranges (from1-to1 and from2-to2) are
     * overlaped in time.  If to1 or to2 should be Null, a maximum Date of (2099/12/31 23:59:59)
     * will be assigned for comparissions to work (assumed as ongoing range)
     * 
     * @param from1 start date of range 1
     * @param to1 end date of range 1 (may be null)
     * @param from2 start date of range 2
     * @param to2 end date of range 2 (may be null)
     * @return true if ranges overlap or the ranges are malformed or ranges have same start or end, false otherwise
     * @see java.util.Date
     */
    public static boolean crossedDates(Date from1, Date to1, Date from2, Date to2) {
        if (to1 == null)
            to1 = getMaximumDate();
        if (to2 == null)
            to2 = getMaximumDate();
        if (to1.after(from2) && (from1.before(to2)))
            return true;
        else if (from1.equals(from2) || to1.equals(to2) || from1.equals(to2) || from2.equals(to1))
            return true;
        else if (from1.after(to1) || from2.after(to2))
            return true;
        else
            return false;
    }

    /**
     * Method that returns an insane max date to be used in comparissions where nulls
     * cant be accepted
     * 
     * @return a Date representing 2099/12/31 23:59:59
     */
    public static Date getMaximumDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2099);
        c.set(Calendar.MONTH, 12);
        c.set(Calendar.DAY_OF_MONTH, 31);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    /**
     * Method that returns the lastHourOfDay value for the day before to fromDate
     * 
     * @param fromDate the date to use as basis
     * @return the lastHourOfDay of the day before fromDate
     * @see lastHourOfDay(java.util.Date)
     */
    public static Date getToDate(Date fromDate) {
        return lastHourOfDay(dateAdd(DATE_PART_DAY, -1, fromDate));
    }
    
    /**
     * Method that returns a Comparator Object to be used in List Ordering
     * 
     * @param getDateMethod the method to call via Reflaction for obtaining a Date from the object to compare
     * @param asc ascendant or descendant
     * @return the Comparator Object
     * @see java.lang.reflect.Method
     * @see java.util.Comparator
     */
    public static Comparator getDateComparator(final String getDateMethod, final boolean asc) {
        return new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Date d1 = getDate(o1);
                if (d1 == null) return 0;
                Date d2 = getDate(o2);
                if (d2 == null) return 0;
                if (asc && d1.before(d2))
                    return -1;
                else if (asc && d1.after(d2))
                    return 1;
                else if (!asc && d1.before(d2))
                    return 1;
                else if (!asc && d1.after(d2))
                    return -1;
                else
                    return 0;
            }
            private Date getDate(Object o) {
                try {
                    Method m = o.getClass().getMethod(getDateMethod, (Class<?>[]) null);
                    return (Date) m.invoke(o, (Object[]) null);
                } catch (Exception ex) {
                    Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
                    return null;
                }
            }
        };
    }

}
