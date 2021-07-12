package view;

import java.awt.Color;
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
        //declare components
        JFrame frame = new DefaultFrameSetting().defaultFrame();
        JPanel background = new DefaultFrameSetting().defaultPanel();
        JPanel panel1 = new DefaultFrameSetting().defaultPanel();
        JLabel label1 = new JLabel("PilihCabang : ");
        JPanel panel3 = new DefaultFrameSetting().defaultPanel();
        JPanel panel2 = new DefaultFrameSetting().defaultPanel();
        JTabbedPane header = new JTabbedPane();
        JButton exit = new JButton("Keluar");
        JComboBox cb = chooseBranchMenu();

        //set components position
        label1.setBounds(50,20,90,20);
        cb.setBounds(150,20,90,20);
        header.setBounds(50, 50, 900, 500);
        exit.setBounds(50, 580, 900, 20);

        //create tabbedpane
        //create panel to show branch money
        panel3.add(new CheckMoney().danaPerpus(1));
        //set check money table size
        panel3.setSize(850,500);
        //add panel3 to panel1
        panel1.add(label1);
        panel1.add(cb);
        panel1.add(panel3);
        //create panel to show library money
        panel2.add(new CheckMoney().danaPerpus(0));
        //add panels to tabbedpane
        header.add("Dana Perpusatakaan", panel2);
        header.add("Dana Cabang", panel1);

        //components action listener
        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel3.setVisible(false);
                panel3.removeAll();
                if (cb.getItemAt(cb.getSelectedIndex()) == "Bandung") {
                    new OutputInfo().changeShowDanaCabang("Bandung");
                    panel3.add(new CheckMoney().danaPerpus(1));
                } else if (cb.getItemAt(cb.getSelectedIndex()) == "Jakarta") {
                    new OutputInfo().changeShowDanaCabang("Jakarta");
                    panel3.add(new CheckMoney().danaPerpus(2));
                } else {
                    new OutputInfo().changeShowDanaCabang("Surabaya");
                    panel3.add(new CheckMoney().danaPerpus(3));
                }
                panel3.setVisible(true);
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().logOutInfo();
                System.exit(0);
            }
            
        });
        //set background panel
        background.setSize(1000,680);
        background.setBackground(new Color(255, 234, 202));

        //add tabbedpane and button exit to frame
        background.add(header);
        background.add(exit);

        //add panel to frame
        frame.add(background);
        
        //set frame size
        frame.setSize(1000, 680);
    }

    public JComboBox chooseBranchMenu() {
        //declare array branch
        String[] branch = {"Bandung", "Jakarta", "Surabaya"};

        //declare combobox
        JComboBox comboBranch = new JComboBox(branch);

        //declare combobox position
        comboBranch.setBounds(30, 30, 90, 20);
        return comboBranch;
    }
}
