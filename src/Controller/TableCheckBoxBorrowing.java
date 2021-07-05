/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.swing.JTable;

/**
 *
 * @author Feliciana Gunadi
 */
public class TableCheckBoxBorrowing extends JTable {
    @Override
    public Class getColumnClass(int column) {
        switch(column) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return Integer.class;
            case 3:
                return String.class;
            case 4:
                return Integer.class;
            case 5:
                return String.class;
            case 6:
                return String.class;
            case 7:
                return Integer.class;
            default:
                return Boolean.class;
        }
    }
}
