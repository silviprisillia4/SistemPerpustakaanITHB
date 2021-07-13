package view;

import java.awt.Color;
import javax.swing.*;

public class DefaultFrameSetting {
    public JFrame defaultFrame() {
        //declare frame
        JFrame frame = new JFrame("Perpustakaan ITHB");

        //initialize frame
        frame.setLayout(null);
        frame.setVisible(true);
        return frame;
    }

    public JPanel defaultPanel() {
        //declare panel
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 234, 202));

        //initialize panel
        panel.setLayout(null);
        panel.setVisible(true);
        return panel; 
    }
}