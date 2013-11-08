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

/**
 * Utility class for performing operations on Strings
 * 
 * @author jmrunge
 * @version 1.00
 */
public class StringUtils {

    /**
     * Method that checks if a Object is null and returns a String or Null if specified
     * 
     * @param o the object to check
     * @param returnNull if true, the method will return a Null object if the object is null, 
     * otherwise, will return an empty String
     * @return null if the object is null and returnNull was true, an empty String
     * if the object is null and returnNull was false and the toString() result of
     * the object if the object was not null
     */
    public static String checkNullToString(Object o, boolean returnNull) {
        if (o == null)
            return returnNull ? null : "";
        else
            return o.toString();
    }

    /**
     * Method that checks if a Boolean is null and returns a String or Null if specified
     * 
     * @param o the object to check
     * @param returnNull if true, the method will return a Null object if the object is null, 
     * otherwise, will return a String with value "No"
     * @return null if the object is null and returnNull was true, a String with value "No"
     * if the object is null and returnNull was false and the booleanString(boolean) result 
     * if the object was not null
     * @see booleanString(boolean)
     */
    public static String booleanString(Boolean bool, boolean returnNull) {
        if (bool == null)
            return returnNull ? null : "No";
        else
            return booleanString(bool.booleanValue());
    }
    
    /**
     * Method that returns a String with value "Sí" if bool is true and 
     * a String with value "No" if bool is false
     * 
     * @param bool the value to evaluate
     * @return a String with value "Sí" if bool is true and 
     * a String with value "No" if bool is false
     */
    public static String booleanString(boolean bool) {
        if (bool)
            return "Sí";
        else
            return "No";
    }
    
    /**
     * Method that returns a String whith the value str repeated the specified times
     * 
     * @param str the String to repeat
     * @param times the amount of times to repeat it
     * @return the resultant String (for example if str was "abc" and times was "3", 
     * the resultant String would be "abcabcabc")
     */
    public static String repeat(String str, int times) {
        String result = "";
        for (int i = 0; i < times; i++) {
            result = result + str;
        }
        return result;
    }
    
    /**
     * Method that returns the concatenation of all the elements of an array of Strings
     * 
     * @param array the array of Strings
     * @return the resultant String
     */
    public static String arrayToString(String[] array) {
        String result = "";
        for (int i = 0; i < array.length; i++) {
            result = result + array[i] + " ";
        }
        return result.trim();
    }
    
    /**
     * Method that returns the received string with its first letter in UpperCase
     * 
     * @param s the String to process
     * @return the resultant String
     */
    public static String setFirstLetterUpperCase(String s) {
        String first = s.substring(0, 1);
        return first.toUpperCase() + s.substring(1);
    }
}
