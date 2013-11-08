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

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Utility class for doing operations on numbers
 * 
 * @author jmrunge
 * @version 1.00
 */
public class NumberUtils {
    
    /**
     * Method that formats the given BigDecimal into a String with the specified format
     * 
     * @param number the BigDecimal to format
     * @param format the format to use
     * @return the resultant String
     * @see DecimalFormat#format(double) 
     */
    public static String format(BigDecimal number, String format) {
        if (number == null)
            return null;
        DecimalFormat snf = new DecimalFormat(format);
        return snf.format(number.doubleValue());
    }

    /**
     * Method that formats the given int into a String with the specified format
     * 
     * @param number the int to format
     * @param format the format to use
     * @return the resultant String
     * @see format(long, String)
     */
    public static String format(int number, String format) {
        return format(new Long(number), format);
    }

    /**
     * Method that formats the given long into a String with the specified format
     * 
     * @param number the long to format
     * @param format the format to use
     * @return the resultant String
     * @see DecimalFormat#format(long) 
     */
    public static String format(long number, String format) {
        DecimalFormat snf = new DecimalFormat(format);
        return snf.format(number);
    }
    
    /**
     * Method for comparing ints in order to use it from inside a Comparator
     * @param i1 int 1
     * @param i2 int 2
     * @return if i1 less than i2, -1. if i1 more than i2, 1.  otherwise 0
     * @see java.util.Comparator
     */
    public static int compareInt(int i1, int i2) {
        return (i1 < i2 ? -1 : (i1 == i2 ? 0 : 1));
    }
}
