package view;
import model.UserManager;
import controller.*;
import model.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;

public class MemberMenu {
    JFrame frame;
    JPanel panel1;
    JButton btnMenu1, btnMenu2;
    
    public MemberMenu() {
        Controller c = new Controller();
        Member member = c.getSelectedMember(1);
        UserManager.getInstance().setUser(member);
        
        //Frame
        frame = new JFrame("Perpustakaan ITHB");
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        
        //Panel
        panel1 = new JPanel();
        panel1.setSize(400, 400);
        panel1.setBackground(Color.GRAY);
        panel1.setVisible(true);
        
        //Button
        btnMenu1 = new JButton("Pinjam Buku");
        btnMenu1.setBounds(50, 50, 120, 50);
        btnMenu1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new PanelBorrowBook();
            }
        });
        
        btnMenu2 = new JButton("Lihat Profil");
        btnMenu2.setBounds(50, 110, 120, 50);
        btnMenu2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new PanelProfile();
            }
        });
        
        //Add
        panel1.add(btnMenu1);
        panel1.add(btnMenu2);
        frame.add(panel1);
        
        //Initialize
        panel1.setLayout(null);
        frame.setLayout(null);
        frame.setVisible(true);
        
    }
}
