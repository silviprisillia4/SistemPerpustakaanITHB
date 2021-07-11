package view;

import model.Admin;
import model.UserManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;


public class AdminMenu {
    public void adminMenu() {
        Admin admin = UserManager.getInstance().getAdmin();

        new OutputInfo().welcomeToMenuAdmin();

        //declare
        JFrame frame = new DefaultFrameSetting().defaultFrame();
        JButton approval = new JButton("Penyetujuan");
        JButton changeListBooks = new JButton("Update Data Buku");
        JButton borrowingList = new JButton("List Pengembalian");
        JButton userHistory = new JButton("User History");
        JButton branchCash = new JButton("Cek Dana Perpustakaan");
        JButton exit = new JButton("Keluar");

        //set position
        approval.setBounds(100, 50, 250, 50);
        changeListBooks.setBounds(100, 150, 250, 50);
        borrowingList.setBounds(100, 250, 250, 50);
        userHistory.setBounds(100, 350, 250, 50);
        branchCash.setBounds(100, 450, 250, 50);
        exit.setBounds(100, 550, 250, 50);

        //button action listener
        approval.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //nanti yen di sini ya hehe <3
            }
        });
        changeListBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new ChangeListBook().bookListUpdate();
            }
        });
        borrowingList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new ApproveBookReturn().returnBorrow();
            }
        });
        userHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new ShowUserHistory().userHistory();
            }
        });
        branchCash.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().changeShowDanaCabang(admin.selectBranchCity(admin.getIdBranch()));
                new CheckMoney().printDanaSatuCabang();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OutputInfo().logOutInfo();
                System.exit(0);
            }

        });

        //add to frame
        frame.add(approval);
        frame.add(changeListBooks);
        frame.add(borrowingList);
        frame.add(userHistory);
        frame.add(branchCash);
        frame.add(exit);

        //set frame size
        frame.setSize(500, 800);
    }
}

