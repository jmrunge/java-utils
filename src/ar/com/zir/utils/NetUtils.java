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

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;

/**
 * Utility class for performing some networking operations
 * 
 * @author jmrunge
 * @version 1.00
 */
public class NetUtils {

    /**
     * Method for obtaining the Mac Address of the first Networking card that has 
     * an IP starting with "192"
     * 
     * @return the String representation of the Mac Address
     * @throws Exception if any error happened
     * @deprecated 
     */
    @Deprecated
    public static String getMacAddress() throws Exception {
        String macAddress = "";
        try {
            InetAddress address = null;
            for (InetAddress ia : InetAddress.getAllByName(getLocalHostName())) {
                if (ia.getHostAddress().startsWith("192")) {
                    address = ia;
                    break;
                }
            }
            if (address == null)
                throw new Exception("No se encontró la placa de red local");
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            byte[] mac = ni.getHardwareAddress();
            for (int i = 0; i < mac.length; i++) {
                macAddress += String.format("%02X%s", mac[i], "");
            }
        } catch (Exception ex) {
            throw new Exception("No se puedo determinar la dirección IP local", ex);
        }
        return macAddress;
    }

    /**
     * Method to obtain the local host name
     * 
     * @return the hostname
     * @throws Exception if InetAddress.getLocalHost().getHostName() throws UnknownHostException
     * @see InetAddress#getLocalHost()
     * @see InetAddress#getHostName() 
     */
    public static String getLocalHostName() throws Exception {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            throw new Exception("No se encontró el nombre del Host", ex);
        }
    }
}
