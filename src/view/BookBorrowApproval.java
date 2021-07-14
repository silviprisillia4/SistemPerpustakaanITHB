package view;

import controller.Controller;
import controller.DatabaseHandler;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Borrowing;
import model.Member;

public class BookBorrowApproval {
    
    JFrame frame;
    JPanel panel;
    JTable table;
    DefaultTableModel model;
    JScrollPane sp;
    
    public BookBorrowApproval(int id) {
        createApprovalScreen(id);
    }
    
    private void createApprovalScreen(int id) {
        
        // Frame
        frame = new DefaultFrameSetting().defaultFrame();
        frame.setTitle("Perpustakaan ITHB - Persetujuan Pinjaman");
        frame.setSize(1120, 600);
        frame.setLocationRelativeTo(null);
        
        // Panel
        panel = new DefaultFrameSetting().defaultPanel();
        panel.setSize(1120, 600);
        panel.setVisible(true);
        
        //Table
        model = new DefaultTableModel() {
            @Override
            public Class getColumnClass(int columnIndex) {
                switch(columnIndex) {
                    case 3 :
                        return Date.class;
                    case 6:
                        return Boolean.class;
                    default :
                        return Integer.class;
                }
            }
        };
        model.addColumn("ID Pinjaman");
        model.addColumn("ID Buku");
        model.addColumn("ID Peminjam");
        model.addColumn("Tanggal");
        model.addColumn("Lama Pinjam");
        model.addColumn("Total Harga");
        model.addColumn("Approval");
        table = new JTable(model);
        
        //ArrayList
        Controller c = new Controller();
        ArrayList<Borrowing> borrows = c.getBorrowArrayList(id);
        int[] statusTemp = new int[borrows.size()];
        
        //Looping Data to Table
        for (int i = 0; i < borrows.size(); i++) {
            Borrowing current = borrows.get(i);
            Object[] addBorrows = new Object[7];
            addBorrows[0] = current.getIdBorrow();
            addBorrows[1] = current.getIdBook();
            addBorrows[2] = current.getIdUser();
            addBorrows[3] = current.getDate();
            addBorrows[4] = current.getBorrowDays();
            addBorrows[5] = current.getPriceTotal();
            if (current.getStatus()== 2) {
                statusTemp[i] = 2;
                addBorrows[6] = false;
            } else if (current.getStatus() == 0) {
                statusTemp[i] = 0;
                addBorrows[6] = true;
            }
            model = (DefaultTableModel)table.getModel();
            model.addRow(addBorrows);
        }
        
        //Set Column Size
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(300);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        table.getColumnModel().getColumn(5).setPreferredWidth(300);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        
        table.setBounds(20, 20, 1065, 500);
        sp = new JScrollPane(table);
        sp.setBounds(20, 20, 1065, 500);
        
        // Set OK Button
        JButton ok = new JButton("Update");
        ok.setBounds(920, 530, 150, 20);
        ok.addActionListener((ActionEvent event) -> {
            DatabaseHandler conn = new DatabaseHandler();
            conn.connect();
            for (int i = 0; i < table.getRowCount(); i++) {
                Boolean status = (Boolean) table.getValueAt(i, 6);
                Member member = c.getAMember((int) table.getValueAt(i, 2));
                int borrowPrice = c.getABook((int) table.getValueAt(i, 1)).getBorrowPrice();
                if (statusTemp[i] == 0) {
                    if (table.getValueAt(i, 6).equals(false)) {
                        c.updateCashMemberAfterApprovalBorrowing(member, borrowPrice, status);
                    }
                } else if (statusTemp[i] == 2) {
                    if (table.getValueAt(i, 6).equals(true)) {
                        c.updateCashMemberAfterApprovalBorrowing(member, borrowPrice, status);
                    }
                }
                c.updateBorrowStatus((int) table.getValueAt(i, 0), status);
            }
            JOptionPane.showMessageDialog(null, "Updated!", "Sistem Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
            new ApprovalMenu();
        });
        frame.add(ok);
        
        // Set Back Button
        JButton back = new JButton("Kembali");
        back.setBounds(750, 530, 150, 20);
        back.addActionListener((ActionEvent event) -> {
            frame.dispose();
            new ApprovalMenu();
        });
        frame.add(back);
        
        //Add
        panel.add(sp);
        frame.add(panel);
    }
}