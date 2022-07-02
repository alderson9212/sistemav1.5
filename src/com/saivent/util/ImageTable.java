/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saivent.util;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author wilmer
 */
public class ImageTable extends DefaultTableCellRenderer{
    

public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row,int col) {
    
    if(value instanceof JLabel){
     JLabel lbl = (JLabel)value;   
      return lbl;
    }
    
    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
    
}


}
