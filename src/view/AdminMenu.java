package view;

import model.Admin;
import model.UserManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class AdminMenu {
    
    public AdminMenu() {
        Admin admin = UserManager.getInstance().getAdmin();
        
        //declare
        JFrame frame = new DefaultFrameSetting().defaultFrame();
        JPanel background = new DefaultFrameSetting().defaultPanel();
        JButton approval = new JButton("Penyetujuan");
        JButton changeListBooks = new JButton("Update Data Buku");
        JButton borrowingList = new JButton("List Pengembalian");
        JButton userHistory = new JButton("User History");
        JButton branchCash = new JButton("Cek Dana Perpustakaan");
        JButton exit = new JButton("Keluar");

        //set position
        approval.setBounds(50, 50, 250, 50);
        changeListBooks.setBounds(50, 150, 250, 50);
        borrowingList.setBounds(50, 250, 250, 50);
        userHistory.setBounds(50, 350, 250, 50);
        branchCash.setBounds(50, 450, 250, 50);
        exit.setBounds(50, 550, 250, 50);

        //button action listener
        approval.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new ApprovalMenu();
            }
        });
        changeListBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new UpdateListBook();
            }
        });
        borrowingList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new ApproveBookReturn();
            }
        });
        userHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new UserHistory();
            }
        });
        branchCash.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().infoDanaCabang(admin.selectBranchCity(admin.getIdBranch()));
                new CheckMoney().showBranchIncome();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OutputInfo().infoLogOut();
                frame.setVisible(false);
                new MainScreen();
            }

        });
        
        //set backgrounf panel
        background.setSize(380, 700);

        //add to panel
        background.add(approval);
        background.add(changeListBooks);
        background.add(borrowingList);
        background.add(userHistory);
        background.add(branchCash);
        background.add(exit);

        //add panel to frame
        frame.add(background);

        //set frame size
        frame.setSize(380, 700);
        frame.setLocationRelativeTo(null);
    }
}

