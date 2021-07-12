package view;

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

        //initialize panel
        panel.setLayout(null);
        panel.setVisible(true);
        return panel;
    }
}