package view;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;

public class MemberMenu {
    JFrame frame;
    JPanel panel1;
    JButton btnBorrow, btnProfile;
    
    public MemberMenu() {
        //Frame
        frame = new DefaultFrameSetting().defaultFrame();
        frame.setSize(300, 250);
        frame.setLocationRelativeTo(null);
        
        //Panel
        panel1 = new DefaultFrameSetting().defaultPanel();
        panel1.setSize(300, 250);
        panel1.setBackground(new Color(255, 234, 202));
        panel1.setVisible(true);
        
        //Button
        btnBorrow = new JButton("Pinjam Buku");
        btnBorrow.setBounds(75, 40, 120, 50);
        btnBorrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new BorrowBook();
            }
        });
        
        btnProfile = new JButton("Lihat Profil");
        btnProfile.setBounds(75, 110, 120, 50);
        btnProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Profile();
            }
        });
        
        //Add
        panel1.add(btnBorrow);
        panel1.add(btnProfile);
        frame.add(panel1);
        
    }
}
