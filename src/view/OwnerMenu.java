package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class OwnerMenu {

    public void ownerMenu() {
        new OutputInfo().welcomeToOwnerMenu();
        JFrame frame = new JFrame();
        
        JButton danaSeluruh = new JButton("Dana Perpustakaan");
        danaSeluruh.setBounds(50, 50, 250, 50);
        danaSeluruh.addActionListener((ActionEvent e) -> {
            frame.setVisible(false);
            cekDana();
        });
        
        JButton exit = new JButton("Keluar");
        exit.setBounds(50, 150, 250, 50);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
            
        });
        
        frame.add(danaSeluruh);
        frame.add(exit);
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void cekDana() {
        JFrame frame = defaultFrame();
        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("PilihCabang : ");
        label1.setBounds(50,20,90,20);
        JComboBox cb = chooseBranchMenu();
        cb.setBounds(150,20,90,20);
        JPanel panel3 = new JPanel();
        panel3.add(new AdminMenu().danaPerpus(1));
        panel3.setSize(850,500);
        panel3.setLayout(null);
        panel3.setVisible(true);
        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel3.setVisible(false);
                panel3.removeAll();
                if (cb.getItemAt(cb.getSelectedIndex()) == "Bandung") {
                    new OutputInfo().changeShowDanaCabang("Bandung");
                    panel3.add(new AdminMenu().danaPerpus(1));
                } else if (cb.getItemAt(cb.getSelectedIndex()) == "Jakarta") {
                    new OutputInfo().changeShowDanaCabang("Jakarta");
                    panel3.add(new AdminMenu().danaPerpus(2));
                } else {
                    new OutputInfo().changeShowDanaCabang("Surabaya");
                    panel3.add(new AdminMenu().danaPerpus(3));
                }
                panel3.setVisible(true);
            }
        });
        
        panel1.add(label1);
        panel1.add(cb);
        panel1.add(panel3);
        
        
        panel1.setLayout(null);
        panel1.setVisible(true);

        JPanel panel2 = new JPanel();
        panel2.add(new AdminMenu().danaPerpus(0));
        panel2.setLayout(null);
        panel2.setVisible(true);
        
        JTabbedPane header = new JTabbedPane();
        header.setBounds(50, 50, 900, 500);
        header.add("Dana Perpusatakaan", panel2);
        header.add("Dana Cabang", panel1);
        JButton exit = new JButton("Keluar");
        exit.setBounds(50, 580, 900, 20);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().exit();
                ownerMenu();
            }
            
        });
        frame.add(header);
        frame.add(exit);
        frame.setSize(1000, 680);
    }

    
    public JFrame defaultFrame() {
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setVisible(true);
        return frame;
    }
    public JComboBox chooseBranchMenu() {
        String[] branch = {"Bandung", "Jakarta", "Surabaya"};
        JComboBox comboBranch = new JComboBox(branch);
        comboBranch.setBounds(30, 30, 90, 20);

        return comboBranch;
    }
}
