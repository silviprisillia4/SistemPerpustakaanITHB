package view;

import controller.databaseChange;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CheckMoney {
    public void printDanaSatuCabang() {
        Admin admin = UserManager.getInstance().getAdmin();

        //declare components
        JFrame frame = new DefaultFrameSetting().defaultFrame();
        JButton exit = new JButton("Exit");

        //set components position
        exit.setBounds(50, 470, 800, 20);

        //button action listener
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().exit();
                new AdminMenu().adminMenu();
            }
        });

        //add components to frame
        frame.add(exit);
        frame.add(danaPerpus(admin.getIdBranch()));

        //set frame size
        frame.setSize(950, 660);
    }

    public JPanel danaPerpus(int idBranch) {
        //declare components
        ArrayList<Member> listMember = new controller.databaseChange().getAllMember(idBranch);
        int pendapatanByMemberRegister = 0;
        int pendapatanByBorrowing = 0;
        int pendapatanByMoneyFine = 0;
        String[] column = {"Keterangan", "Saldo", "Tanggal"};
        DefaultTableModel tableModel = new DefaultTableModel(column, 0);
        JTable table = new JTable(tableModel);
        JScrollPane sp = new JScrollPane();
        JPanel panel = new DefaultFrameSetting().defaultPanel();
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JLabel label4 = new JLabel();

        //make table data
        for (int i = 0; i < listMember.size(); i++) {
            Object[] datum = new Object[3];
            datum[0] = "Pendaftaran dari " + listMember.get(i).getFirstName() + " " + listMember.get(i).getLastName();
            datum[1] = "(+) " + 50000;
            datum[2] = "-";
            tableModel = (DefaultTableModel) table.getModel();
            tableModel.addRow(datum);
            pendapatanByMemberRegister += 50000;
            for (int j = 0; j < new databaseChange().getAllBorrowList(listMember.get(i).getIdUser(), true).size(); j++) {
                Borrowing borrow = new databaseChange().getAllBorrowList(listMember.get(i).getIdUser(), true).get(j);
                if (borrow.getPriceTotal() != 0) {
                    datum = new Object[3];
                    datum[0] = "Peminjaman  buku " + ((Book)new databaseChange().getABook(borrow.getIdBook())).getTitle() + " selama " + borrow.getBorrowDays() + " hari";
                    datum[1] = "(+)" + borrow.getPriceTotal();
                    datum[2] = borrow.getDate();
                    tableModel = (DefaultTableModel) table.getModel();
                    tableModel.addRow(datum);
                    pendapatanByBorrowing += borrow.getPriceTotal();
                }
                if (borrow.getMoneyFine() != 0) {
                    datum = new Object[3];
                    datum[0] = "Denda dari peminjaman buku " + ((Book)new databaseChange().getABook(borrow.getIdBook())).getTitle();
                    datum[1] = "(+)" + borrow.getMoneyFine();
                    datum[2] = borrow.getDate();
                    tableModel = (DefaultTableModel) table.getModel();
                    tableModel.addRow(datum);
                    pendapatanByMoneyFine += borrow.getMoneyFine();
                }
            }
        }

        //set components position
        table.setBounds(50, 50, 800, 300);
        sp.setBounds(50, 50, 800, 300);
        label1.setBounds(50, 370, 300, 20);
        label2.setBounds(50, 390, 300, 20);
        label3.setBounds(50, 410, 300, 20);
        label4.setBounds(50, 430, 300, 20);

        //set components
        table.setEnabled(false);
        sp.setViewportView(table);
        label1.setText("Pendapatan dari Pendaftaran : Rp " + pendapatanByMemberRegister);
        label2.setText("Pendapatan dari Peminjaman Buku :  Rp " + pendapatanByBorrowing);
        label3.setText("Pendapatan dari Denda Buku :  Rp " + pendapatanByMoneyFine);
        label4.setText("Total Pendapatan : Rp " + (pendapatanByMemberRegister + pendapatanByBorrowing + pendapatanByMoneyFine));

        //add components to panel
        panel.add(sp);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);

        //set panel size
        panel.setSize(850, 480);
        return panel;
    }
}
