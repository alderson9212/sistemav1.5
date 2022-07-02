/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taecel.conexionservicio;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.LookAndFeel;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 *
 * @author Elliot
 */
public class MyComboBoxUI extends BasicComboBoxUI {

    Color c = new Color(0, 0, 0);

    public static ComboBoxUI createUI(JComponent com) {
        return new MyComboBoxUI();
    }

    @Override
    protected JButton createArrowButton() {
        JButton button = new JButton();
        button.setIcon(new ImageIcon(getClass().getResource("/Imagenes/icons8-pago-de-recarga-30.png")));
        button.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,10),5));
        button.setContentAreaFilled(false);
        // button.setName("ComboBox.arrowButton"); //Mandatory, as per BasicComboBoxUI#createArrowButton().
        return button;
    }
   
    @Override
    public void paintCurrentValueBackground(Graphics g,Rectangle bounds,boolean hasFocus){
      g.setColor(new Color(0,0,0,10));
      g.fillRect(bounds.x,bounds.y,bounds.width,bounds.height);  
     
    }
    
    @Override
    protected ListCellRenderer createRenderer(){
        return new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); //To change body of generated methods, choose Tools | Templates.
                if(index != -1){
                    list.setSelectionBackground(c); 
                    setPreferredSize(new Dimension(200,50));
                    setForeground(Color.GREEN);
                    //setIcon(new ImageIcon(getClass().getResource("/Imagenes/oki.png")));
                }
                return this;
            }
          
        };
    }
    
}
