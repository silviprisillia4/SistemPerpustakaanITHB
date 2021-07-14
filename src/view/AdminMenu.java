package view;

import model.Admin;
import model.UserManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class AdminMenu {
    JFrame frame;
    JPanel background;
    JButton approval, changeListBooks, borrowingList, userHistory, branchCash, exit;
    
    public AdminMenu() {
        Admin admin = UserManager.getInstance().getAdmin();
        
        //declare
        frame = new DefaultFrameSetting().defaultFrame();
        background = new DefaultFrameSetting().defaultPanel();
        approval = new JButton("Persetujuan");
        changeListBooks = new JButton("Update Data Buku");
        borrowingList = new JButton("List Pengembalian");
        userHistory = new JButton("User History");
        branchCash = new JButton("Cek Dana Perpustakaan");
        exit = new JButton("Log Out");

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
                new ApprovalBookReturn();
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
                frame.setVisible(false);
                new OutputInfo().infoLogOut();
                UserManager.getInstance().logOut();
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
        
        //frame set
        frame.setTitle("Perpustakaan ITHB - Menu Admin");
        
        //set frame size
        frame.setSize(380, 700);
        frame.setLocationRelativeTo(null);
    }
}

