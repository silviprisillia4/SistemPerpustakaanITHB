package view;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import model.*;

public class MemberMenu {
    JFrame frame;
    JPanel panel1;
    JButton btnBorrow, btnProfile, btnExit;
    
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
        btnBorrow.setBounds(75, 40, 120, 40);
        btnBorrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new BorrowBook();
            }
        });
        
        btnProfile = new JButton("Lihat Profil");
        btnProfile.setBounds(75, 90, 120, 40);
        btnProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Profile();
            }
        });
        
        btnExit = new JButton("Keluar");
        btnExit.setBounds(75, 140, 120, 40);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Terima kasih sudah menggunakan layanan Sistem Perpustakaan ITHB!", "Sistem Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
                UserManager.getInstance().logOut();
                frame.setVisible(false);
                new MainScreen();
            }
        });
        
        //Add
        panel1.add(btnBorrow);
        panel1.add(btnProfile);
        panel1.add(btnExit);
        frame.add(panel1);
        
    }
}