/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.Borrowing;
import model.Member;
import Controller.databaseChange;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Feliciana Gunadi
 */
public class OwnerMenu {

    public void ownerMenu() {
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
        JFrame frame = new JFrame();
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
                    panel3.add(new AdminMenu().danaPerpus(1));
                } else if (cb.getItemAt(cb.getSelectedIndex()) == "Jakarta") {
                    panel3.add(new AdminMenu().danaPerpus(2));
                } else {
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
                ownerMenu();
            }
            
        });
        frame.add(header);
        frame.add(exit);
        frame.setSize(1000, 680);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    

    public JComboBox chooseBranchMenu() {
        String[] branch = {"Bandung", "Jakarta", "Surabaya"};
        JComboBox comboBranch = new JComboBox(branch);
        comboBranch.setBounds(30, 30, 90, 20);

        return comboBranch;
    }

    public JPanel cekAllCabang() {
        ArrayList<Member> listMember = new Controller.databaseChange().getAllMember(0);
        int pendapatanByMemberRegister = 0, pendapatanByBorrowing = 0;
        String[] column = {"Keterangan", "Saldo", "Tanggal"};
        DefaultTableModel tableModel = new DefaultTableModel(column, 0);
        JTable histSaldo = new JTable(tableModel);
        for (int i = 0; i < listMember.size(); i++) {
            Object[] datum = new Object[3];
            datum[0] = "Pendaftaran dari " + listMember.get(i).getFirstName() + " " + listMember.get(i).getLastName();
            datum[1] = "(+) " + 50000;
            datum[2] = "-";
            tableModel = (DefaultTableModel) histSaldo.getModel();
            tableModel.addRow(datum);
            pendapatanByMemberRegister += 50000;
            for (int j = 0; j < new databaseChange().getAllBorrowList(listMember.get(i).getIdUser(), true).size(); j++) {
                Borrowing borrow = new databaseChange().getAllBorrowList(listMember.get(i).getIdUser(), true).get(j);
                if (borrow.getPriceTotal() != 0) {
                    datum = new Object[3];
                    datum[0] = "Peminjaman  buku " + new databaseChange().getABook(borrow.getIdBook()).getTitle() + "selama " + borrow.getBorrowDays();
                    datum[1] = "(+)" + borrow.getPriceTotal();
                    datum[2] = borrow.getDate();
                    tableModel = (DefaultTableModel) histSaldo.getModel();
                    tableModel.addRow(datum);
                    pendapatanByBorrowing += borrow.getPriceTotal();
                    if (borrow.getMoneyFine() != 0) {
                        datum = new Object[3];
                        datum[0] = "Denda dari peminjaman buku " + new databaseChange().getABook(borrow.getIdBook()).getTitle();
                        datum[1] = "(+)" + borrow.getMoneyFine();
                        datum[2] = borrow.getDate();
                        tableModel = (DefaultTableModel) histSaldo.getModel();
                        tableModel.addRow(datum);
                        pendapatanByBorrowing += borrow.getMoneyFine();
                    }
                }
            }
        }
        histSaldo.setBounds(50, 50, 800, 300);
        JScrollPane tabel = new JScrollPane(histSaldo);
        tabel.setBounds(50, 50, 800, 300);
        JPanel panel = new JPanel();
        panel.add(tabel);
        
        JLabel label1 = new JLabel("Pendapatan dari Pendaftaran : Rp " + pendapatanByMemberRegister);
        label1.setBounds(50, 370, 300, 20);
        JLabel label2 = new JLabel("Pendapatan dari Peminjaman Buku :  Rp " + pendapatanByBorrowing);
        label2.setBounds(50, 390, 300, 20);
        JLabel label3 = new JLabel("Total Pendapatan : Rp " + (pendapatanByMemberRegister + pendapatanByBorrowing));
        label3.setBounds(50, 410, 300, 20);
        
        
        panel.setSize(850, 600);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.setSize(850, 600);
        panel.setLayout(null);
        panel.setVisible(true);
        return panel;
    }
}
