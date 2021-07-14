package view;
import controller.Controller;
import controller.DatabaseHandler;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Member;
import model.UserManager;

public class RegistrationApproval {
    
    JFrame frame;
    JPanel panel;
    JTable table;
    DefaultTableModel model;
    JScrollPane sp;
    
    public RegistrationApproval(int id) {
        createApprovalScreen(id);
    }
    
    private void createApprovalScreen(int id) {
        
        // Frame
        frame = new DefaultFrameSetting().defaultFrame();
        frame.setTitle("Perpustakaan ITHB - Penyetujuan Pendaftaran");
        frame.setSize(1260, 600);
        frame.setLocationRelativeTo(null);
        
        // Panel
        panel = new DefaultFrameSetting().defaultPanel();
        panel.setSize(1260, 600);
        panel.setVisible(true);
        
        //Table
        model = new DefaultTableModel() {
            @Override
            public Class getColumnClass(int columnIndex) {
                switch(columnIndex) {
                    case 0 :
                    case 1 :
                        return Integer.class;
                    case 7:
                        return Boolean.class;
                    default :
                        return String.class;
                }
            }
        };
        model.addColumn("ID User");
        model.addColumn("ID Cabang");
        model.addColumn("Nama Depan");
        model.addColumn("Nama Belakang");
        model.addColumn("Email");
        model.addColumn("Alamat");
        model.addColumn("No. Telp");
        model.addColumn("Approval");
        table = new JTable(model);
        
        //ArrayList
        Controller c = new Controller();
        ArrayList<Member> members = c.getMemberArrayList(id);
        
        //Looping Data to Table
        for (int i = 0; i < members.size(); i++) {
            Member current = members.get(i);
            Object[] addMembers = new Object[8];
            addMembers[0] = current.getIdUser();
            addMembers[1] = current.getIdBranch();
            addMembers[2] = current.getFirstName();
            addMembers[3] = current.getLastName();
            addMembers[4] = current.getEmail();
            addMembers[5] = current.getAddress();
            addMembers[6] = current.getPhoneNumber();
            if (current.getApproved() == 0) {
                addMembers[7] = false;
            } else if (current.getApproved() == 1) {
                addMembers[7] = true;
            }
            model = (DefaultTableModel)table.getModel();
            model.addRow(addMembers);
        }
        
        //Set Column Size
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(20);
        table.getColumnModel().getColumn(2).setPreferredWidth(60);
        table.getColumnModel().getColumn(3).setPreferredWidth(60);
        table.getColumnModel().getColumn(4).setPreferredWidth(160);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);
        table.getColumnModel().getColumn(6).setPreferredWidth(80);
        table.getColumnModel().getColumn(7).setPreferredWidth(30);
        
        table.setBounds(20, 20, 1205, 500);
        sp = new JScrollPane(table);
        sp.setBounds(20, 20, 1205, 500);
        
        // Set OK Button
        JButton ok = new JButton("Update");
        ok.setBounds(1070, 530, 150, 20);
        ok.addActionListener((ActionEvent event) -> {
            DatabaseHandler conn = new DatabaseHandler();
            conn.connect();
            for (int i = 0; i < table.getRowCount(); i++) {
                Boolean status = (Boolean) table.getValueAt(i, 7);
                if (status) {
                    String query = "UPDATE users SET approved = '1' WHERE idUser = '" + (int) table.getValueAt(i, 0) + "'";
                    try {
                        Statement stmt = conn.con.createStatement();
                        int rs = stmt.executeUpdate(query);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    String query = "UPDATE users SET approved = '0' WHERE idUser = '" + (int) table.getValueAt(i, 0) + "'";
                    try {
                        Statement stmt = conn.con.createStatement();
                        int rs = stmt.executeUpdate(query);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                UserManager.getInstance().getAdmin().setMembers(c.getAllMembers(UserManager.getInstance().getAdmin().getIdBranch()));
            }
            JOptionPane.showMessageDialog(null, "Updated!", "Sistem Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
            new ApprovalMenu();
        });
        frame.add(ok);
        
        // Set Back Button
        JButton back = new JButton("Kembali");
        back.setBounds(900, 530, 150, 20);
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