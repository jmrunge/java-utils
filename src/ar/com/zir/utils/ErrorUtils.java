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

import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Legacy class for logging and showing errors.  Its being replaced by LogService
 * 
 * @author jmrunge
 * @version 1.00
 * @deprecated 
 * @see LogService
 */
@Deprecated
public class ErrorUtils {

    /**
     * Legacy method for log an error and show a dialog on screen
     * 
     * @param className String representing the className for obtaining the logger
     * @param message String to log and to show in the dialog
     * @param parent Parent component if any (may be null)
     * @param ex Exception to log if any (may be null)
     * @deprecated
     */
    @Deprecated
    public static void logAndShowError(String className, String message, Component parent, Exception ex) {
        logError(className, message, ex);
        MessageUtils.showErrorMessage(parent, message);
    }

    /**
     * Legacy method for log an error
     * 
     * @param className String representing the className for obtaining the logger
     * @param message String to log and to show in the dialog
     * @param ex Exception to log if any (may be null)
     * @deprecated
     */
    @Deprecated
    public static void logError(String className, String message, Exception ex) {
        Logger.getLogger(className).log(Level.SEVERE, message, ex);
    }
}
