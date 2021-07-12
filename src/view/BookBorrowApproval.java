/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import controller.DatabaseHandler;
import controller.TableHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

/**
 *
 * @author yen
 */
public class BookBorrowApproval {
    
    public BookBorrowApproval(int id) {
        createApprovalScreen(id);
    }
    
    private void createApprovalScreen(int id) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        
        TableHandler h = new TableHandler();        
        String[][] data = h.getBorrowsData(id);
        
        // Create Label
        JLabel idBorrowLabel = new JLabel("Borrow ID");
        idBorrowLabel.setBounds(20, 10, 150, 20);
        idBorrowLabel.setBackground(new Color(240, 240, 240));
        idBorrowLabel.setOpaque(true);
        JLabel idBookLabel = new JLabel("Book ID");
        idBookLabel.setBounds(170, 10, 150, 20);
        idBookLabel.setBackground(new Color(240, 240, 240));
        idBookLabel.setOpaque(true);
        JLabel idUserLabel = new JLabel("Borrower ID");
        idUserLabel.setBounds(320, 10, 150, 20);
        idUserLabel.setBackground(new Color(240, 240, 240));
        idUserLabel.setOpaque(true);
        JLabel dateLabel = new JLabel("Date");
        dateLabel.setBounds(470, 10, 150, 20);
        dateLabel.setBackground(new Color(240, 240, 240));
        dateLabel.setOpaque(true);
        JLabel daysLabel = new JLabel("Borrow Days");
        daysLabel.setBounds(620, 10, 150, 20);
        daysLabel.setBackground(new Color(240, 240, 240));
        daysLabel.setOpaque(true);
        JLabel priceLabel = new JLabel("Total Price");
        priceLabel.setBounds(770, 10, 150, 20);
        priceLabel.setBackground(new Color(240, 240, 240));
        priceLabel.setOpaque(true);
        JLabel approveLabel = new JLabel("Approve");
        approveLabel.setBounds(920, 10, 150, 20);
        approveLabel.setBackground(new Color(240, 240, 240));
        approveLabel.setOpaque(true);
        
        // Create Border
        Border boldBorder = BorderFactory.createLineBorder(Color.GRAY, 2);
        Border lightBorder = BorderFactory.createLineBorder(Color.GRAY, 1);
        idBorrowLabel.setBorder(boldBorder);
        idBookLabel.setBorder(boldBorder);
        idUserLabel.setBorder(boldBorder);
        dateLabel.setBorder(boldBorder);
        daysLabel.setBorder(boldBorder);
        priceLabel.setBorder(boldBorder);
        approveLabel.setBorder(boldBorder);
        
        frame.add(idBorrowLabel); frame.add(idBookLabel);
        frame.add(idUserLabel); frame.add(dateLabel);
        frame.add(daysLabel); frame.add(priceLabel);
        frame.add(approveLabel);
        
        int y = 0;
        JButton[] buttons = new JButton[data.length];
        
        for (int i = 0; i < data.length; i++) {
            int index = i;
            JLabel idBorrow = new JLabel(data[i][0]);
            idBorrow.setBounds(0, y, 150, 20);
            idBorrow.setBorder(lightBorder);
            idBorrow.setBackground(new Color(240, 240, 240));
            idBorrow.setOpaque(true);
            JLabel idBook = new JLabel(data[i][1]);
            idBook.setBounds(150, y, 150, 20);
            idBook.setBorder(lightBorder);
            idBook.setBackground(new Color(240, 240, 240));
            idBook.setOpaque(true);
            JLabel idUser = new JLabel(data[i][2]);
            idUser.setBounds(300, y, 150, 20);
            idUser.setBorder(lightBorder);
            idUser.setBackground(new Color(240, 240, 240));
            idUser.setOpaque(true);
            JLabel date = new JLabel(data[i][3]);
            date.setBounds(450, y, 150, 20);
            date.setBorder(lightBorder);
            date.setBackground(new Color(240, 240, 240));
            date.setOpaque(true);
            JLabel days = new JLabel(data[i][4]);
            days.setBounds(600, y, 150, 20);
            days.setBorder(lightBorder);
            days.setBackground(new Color(240, 240, 240));
            days.setOpaque(true);
            JLabel price = new JLabel(data[i][5]);
            price.setBounds(750, y, 150, 20);
            price.setBorder(lightBorder);
            price.setBackground(new Color(240, 240, 240));
            price.setOpaque(true);
            DatabaseHandler conn = new DatabaseHandler();
            conn.connect();
            if (data[i][6].equals("0")) {
                buttons[i] = new JButton("Approve");
                buttons[i].setBounds(900, y, 150, 20);
                buttons[i].addActionListener((ActionEvent event) -> {
                    String query = "UPDATE borrows SET status = '1' WHERE idBorrow = '" + data[index][0] + "'";
                    try {
                        Statement stmt = conn.con.createStatement();
                        int rs = stmt.executeUpdate(query);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Approved!");
                    frame.dispose();
                    createApprovalScreen(id);
                });
                panel.add(buttons[i]);
            } else if (data[i][6].equals("1")){
                buttons[i] = new JButton("Unapprove");
                buttons[i].setBounds(900, y, 150, 20);
                buttons[i].addActionListener((ActionEvent event) -> {
                    String query = "UPDATE borrows SET status = '0' WHERE idBorrow = '" + data[index][0] + "'";
                    try {
                        Statement stmt = conn.con.createStatement();
                        int rs = stmt.executeUpdate(query);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Unapproved!");
                    frame.dispose();
                    createApprovalScreen(id);
                });
                panel.add(buttons[i]);
            }
            y += 20;
            panel.add(idBorrow); panel.add(idBook);
            panel.add(idUser); panel.add(date);
            panel.add(days); panel.add(price);
        }
        
        // Set JScrollPane
        panel.setPreferredSize(new Dimension(1055, y));
        panel.setLayout(null);
        panel.setBackground(new Color(255, 228, 189));
        JScrollPane sp = new JScrollPane(panel);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setBounds(20, 35, 1075, 480);
        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY);
        sp.setBorder(bottomBorder);
        frame.add(sp);
        
        // Set OK Button
        JButton ok = new JButton("OK");
        ok.setBounds(920, 530, 150, 20);
        ok.addActionListener((ActionEvent event) -> {
            frame.dispose();
            new ApprovalMenu();
        });
        frame.add(ok);
        
        frame.setTitle("Penyetujuan Peminjaman - Sistem Perpustakaan");
        frame.setSize(1120, 600);
        frame.getContentPane().setBackground(new Color(255, 228, 189));
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);    
        frame.setVisible(true);
    }
}

