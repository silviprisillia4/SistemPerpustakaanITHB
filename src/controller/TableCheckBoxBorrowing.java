package controller;
import javax.swing.JTable;

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
