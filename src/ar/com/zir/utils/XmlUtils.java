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

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Utility class for reading on XML files
 * 
 * @author jmrunge
 * @version 1.00
 */
public class XmlUtils {
    
    /**
     * Method that returns a only the Element childs of a Node
     * 
     * @param node the Node to inspect
     * @return a list of the Element childs of the node
     * @see #getNodeList(NodeList)
     */
    public static List<Element> getChildNodes(Node node) {
        return getNodeList(node.getChildNodes());
    }
    
    /**
     * Method that returns only the Element objects of the specified NodeList
     * 
     * @param nodeList the NodeList to inspect
     * @return a List containing only the Element objects of the NodeList
     */
    public static List<Element> getNodeList(NodeList nodeList) {
        List<Element> nodes = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element)
                nodes.add((Element)node);
        }
        return nodes;        
    }
}
