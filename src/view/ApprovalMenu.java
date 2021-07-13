/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import model.*;

/**
 *
 * @author yen
 */
public class ApprovalMenu {
    
    public ApprovalMenu() {
        Admin admin = new UserManager().getInstance().getAdmin();
        
        JFrame mainFrame = new DefaultFrameSetting().defaultFrame();
        mainFrame.setSize(335, 330);
        mainFrame.getContentPane().setBackground(new Color(255, 228, 189));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(null);        
        mainFrame.setVisible(true);
        
        // ------
        JButton menu1 = new JButton("Penyetujuan Registrasi");
        menu1.setBounds(10, 10, 300, 60);
        menu1.addActionListener((ActionEvent e) -> {
            mainFrame.setVisible(false);
            new RegistrationApproval(admin.getIdBranch());
        });
        
        JButton menu2 = new JButton("Penyetujuan Peminjaman Buku");
        menu2.setBounds(10, 80, 300, 60);
        menu2.addActionListener((ActionEvent e) -> {
            mainFrame.setVisible(false);
            new BookBorrowApproval(admin.getIdBranch());
        });
        
        JButton menu3 = new JButton("Penyetujuan Top Up");
        menu3.setBounds(10, 150, 300, 60);
        menu3.addActionListener((ActionEvent e) -> {
            mainFrame.setVisible(false);
            new TopUpApproval();
        });
        
        JButton back1 = new JButton("Kembali");
        back1.setBounds(10, 220, 300, 60);
        back1.addActionListener((ActionEvent e) -> {
            mainFrame.setVisible(false);
            new AdminMenu();
        });
        // ------
        
        mainFrame.add(menu1); mainFrame.add(menu2);
        mainFrame.add(menu3); mainFrame.add(back1);
        
        //frame set
        mainFrame.setTitle("Perpustakaan ITHB - Menu Penyetujuan");
        
    }
}
