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
import javax.swing.JOptionPane;

/**
 * Legacy class used to show messages on the screen
 * 
 * @author jmrunge
 * @version 1.00
 * @deprecated 
 */
@Deprecated
public class MessageUtils {

    /**
     * Method used to show an error dialog on the screen
     * 
     * @param parent the parent component of the dialog (if any)
     * @param message the message to show inside the dialog
     * @deprecated
     */
    @Deprecated
    public static void showErrorMessage(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Method used to show a success dialog on the screen
     * 
     * @param parent the parent component of the dialog (if any)
     * @param message the message to show inside the dialog
     * @deprecated
     */
    @Deprecated
    public static void showSuccessMessage(Component parent, String message) {
        SuccessIcon icon = new SuccessIcon("/ar/com/zir/utils/resources/successIcon.png");
        JOptionPane.showMessageDialog(parent, message, "Operación exitosa", JOptionPane.INFORMATION_MESSAGE, icon.getIcon());
    }
}
