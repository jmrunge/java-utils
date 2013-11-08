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

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Legacy class for use in JRE 6 enviroments for performing operations on files
 * Should not be used in JRE 7 enviroments, use native class helpers instead
 * 
 * @author jmrunge
 * @version 1.00
 */
public class FileUtils {

    /**
     * Legacy method to copy a file from one location to another
     * 
     * @param fromFile File to be copied
     * @param toFile Destination File
     * @throws FileNotFoundException if fromFile was not found
     * @throws IOException if operation fails
     * @deprecated
     */
    @Deprecated
    public static void copyFile(File fromFile, File toFile) throws FileNotFoundException, IOException {
        FileOutputStream to;
        try (FileInputStream from = new FileInputStream(fromFile)) {
            to = new FileOutputStream(toFile);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = from.read(buffer)) > 0)
                to.write(buffer, 0, bytesRead);
        }
        to.close();
    }
    
    /**
     * Method for copying an InputStream to an OutputStream
     * 
     * @param in the InputStream to be read
     * @param out the OutputStream to write to
     * @throws IOException inherited from InputStream.read(byte[]) and OutputStream.write(byte[], int, int)
     * @see InputStream#read(byte[]) 
     * @see OutputStream#write(byte[], int, int) 
     */
    public static void copyInputStream(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;

        while ((len = in.read(buffer)) >= 0)
            out.write(buffer, 0, len);

        in.close();
        out.close();
    }
    
    /**
     * Method for unziping a file to a specified directory
     * 
     * @param zipFileName the zip file name
     * @param destDirName the directory name
     * @throws IOException inherited from ZipFile, FileOutputStream and copyInputStream
     * @see ZipFile#ZipFile(java.io.File) 
     * @see FileOutputStream#FileOutputStream(java.lang.String) 
     * @see FileUtils#copyInputStream(java.io.InputStream, java.io.OutputStream) 
     */
    public static void unzipFile(String zipFileName, String destDirName) throws IOException {
        if (!destDirName.endsWith("/"))
            destDirName = destDirName + "/";
        
        try (ZipFile zipFile = new ZipFile(zipFileName)) {
            Enumeration entries = zipFile.entries();

            while(entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry)entries.nextElement();

                if(entry.isDirectory()) {
                    File dir = new File(destDirName + entry.getName());
                    dir.mkdir();
                } else {
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destDirName + entry.getName()));
                    copyInputStream(zipFile.getInputStream(entry), bos);
                }
            }
        }
    }

}
