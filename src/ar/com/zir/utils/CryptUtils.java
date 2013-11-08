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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Util class for encrypting and decrypting Strings
 * 
 * @author jmrunge
 * @version 1.00
 */
public class CryptUtils {
    private final static String C_ALFABETO_N = " &(),.-/0123456789:;ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz©áäéëíïÑñóöúü";
    private static final char[] HEXADECIMAL = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     * Encrypts a String using MD5 and Hexadecimal formula
     * 
     * @param text the String to encrypt
     * @return the ecrypted String
     * @throws NoSuchAlgorithmException inherited from MessageDigest.getInstance(String)
     * @see java.security.MessageDigest
     */
    public static String encrypt(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.reset();

        byte[] bytes = md.digest(text.getBytes());
        StringBuilder sb = new StringBuilder(2 * bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            int low = (int)(bytes[i] & 0x0f);
            int high = (int)((bytes[i] & 0xf0) >> 4);
            sb.append(HEXADECIMAL[high]);
            sb.append(HEXADECIMAL[low]);
        }
        return sb.toString();
    }

    /**
     * Old method used for decrypt pwds encrypted the old way
     * 
     * @param text String to be decrypted
     * @return decrypted String
     * @deprecated
     */
    @Deprecated
    public static String oldDecrypt(String text) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            int ascii = (int)text.charAt(i);
            int end = (i + 1) % 3;
            int init = (ascii - end) / 3;
            init--;
            String character = C_ALFABETO_N.substring(init, init + 1);
            result = result.concat(character);
        }
        return result;
    }

    /**
     * Old method used for encrypting pwds
     * 
     * @param text String to be encrypted
     * @return encrypted String
     * @deprecated
     */
    @Deprecated
    public static String oldEncrypt(String text) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            String chr = text.substring(i, i + 1);
            int pos = C_ALFABETO_N.indexOf(chr);
            pos++;
            int end = (i + 1) % 3;
            int ascii = (pos * 3) + end;
            String character = String.valueOf((char)ascii);
            result = result.concat(character);
        }
        return result;
    }

}
