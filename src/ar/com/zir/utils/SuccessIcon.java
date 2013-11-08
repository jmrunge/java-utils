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

import javax.swing.ImageIcon;

/**
 * Legacy class for showing a success icon on dialogs
 * 
 * @author jmrunge
 * @version 1.00
 * @deprecated
 */
@Deprecated
public class SuccessIcon {
    private ImageIcon icon;
    private String path;

    /**
     * Constructor that receives the path to the icon file
     * 
     * @param path the path where the icon file is
     * @deprecated
     */
    @Deprecated
    public SuccessIcon(String path) {
        this.path = path;
    }

    /**
     * Method that returns an ImageIcon whith the icon file specified in the constructor
     * 
     * @return the resultant ImageIcon
     * @deprecated
     * @see SuccessIcon#SuccessIcon(java.lang.String) 
     * @see ImageIcon#ImageIcon(java.net.URL) 
     * @see Class#getResource(java.lang.String) 
     */
    @Deprecated
    public ImageIcon getIcon() {
        icon = new ImageIcon(getClass().getResource(path));
        return icon;
    }
}
