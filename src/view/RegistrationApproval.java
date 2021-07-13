package view;
import controller.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

public class RegistrationApproval {
    
    public RegistrationApproval(int id) {
        createApprovalScreen(id);
    }
    
    private void createApprovalScreen(int id) {
        JFrame frame = new DefaultFrameSetting().defaultFrame();
        JPanel panel = new DefaultFrameSetting().defaultPanel();
        
        TableHandler h = new TableHandler();
        String[][] data = h.getMembersData(id);
        
        // Create Label
        JLabel idUserLabel = new JLabel("User ID");
        idUserLabel.setBounds(20, 10, 150, 20);
        idUserLabel.setBackground(new Color(240, 240, 240));
        idUserLabel.setOpaque(true);
        JLabel idBranchLabel = new JLabel("Branch ID");
        idBranchLabel.setBounds(170, 10, 150, 20);
        idBranchLabel.setBackground(new Color(240, 240, 240));
        idBranchLabel.setOpaque(true);
        JLabel fnameLabel = new JLabel("First Name");
        fnameLabel.setBounds(320, 10, 150, 20);
        fnameLabel.setBackground(new Color(240, 240, 240));
        fnameLabel.setOpaque(true);
        JLabel lnameLabel = new JLabel("Last Name");
        lnameLabel.setBounds(470, 10, 150, 20);
        lnameLabel.setBackground(new Color(240, 240, 240));
        lnameLabel.setOpaque(true);
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(620, 10, 150, 20);
        emailLabel.setBackground(new Color(240, 240, 240));
        emailLabel.setOpaque(true);
        JLabel addressLabel = new JLabel("Address");
        addressLabel.setBounds(770, 10, 150, 20);
        addressLabel.setBackground(new Color(240, 240, 240));
        addressLabel.setOpaque(true);
        JLabel phoneLabel = new JLabel("Phone Number");
        phoneLabel.setBounds(920, 10, 150, 20);
        phoneLabel.setBackground(new Color(240, 240, 240));
        phoneLabel.setOpaque(true);
        JLabel approveLabel = new JLabel("Approve");
        approveLabel.setBounds(1070, 10, 150, 20);
        approveLabel.setBackground(new Color(240, 240, 240));
        approveLabel.setOpaque(true);
        
        // Create Border
        Border boldBorder = BorderFactory.createLineBorder(Color.GRAY, 2);
        Border lightBorder = BorderFactory.createLineBorder(Color.GRAY, 1);
        idUserLabel.setBorder(boldBorder);
        idBranchLabel.setBorder(boldBorder);
        fnameLabel.setBorder(boldBorder);
        lnameLabel.setBorder(boldBorder);
        emailLabel.setBorder(boldBorder);
        addressLabel.setBorder(boldBorder);
        phoneLabel.setBorder(boldBorder);
        approveLabel.setBorder(boldBorder);
        
        frame.add(idUserLabel); frame.add(idBranchLabel);
        frame.add(fnameLabel); frame.add(lnameLabel);
        frame.add(emailLabel); frame.add(addressLabel);
        frame.add(phoneLabel); frame.add(approveLabel);
        
        int y = 0;
        JButton[] buttons = new JButton[data.length];
        
        for (int i = 0; i < data.length; i++) {
            int index = i;
            JLabel idUser = new JLabel(data[i][0]);
            idUser.setBounds(0, y, 150, 20);
            idUser.setBorder(lightBorder);
            idUser.setBackground(new Color(240, 240, 240));
            idUser.setOpaque(true);
            JLabel idBranch = new JLabel(data[i][1]);
            idBranch.setBounds(150, y, 150, 20);
            idBranch.setBorder(lightBorder);
            idBranch.setBackground(new Color(240, 240, 240));
            idBranch.setOpaque(true);
            JLabel fname = new JLabel(data[i][2]);
            fname.setBounds(300, y, 150, 20);
            fname.setBorder(lightBorder);
            fname.setBackground(new Color(240, 240, 240));
            fname.setOpaque(true);
            JLabel lname = new JLabel(data[i][3]);
            lname.setBounds(450, y, 150, 20);
            lname.setBorder(lightBorder);
            lname.setBackground(new Color(240, 240, 240));
            lname.setOpaque(true);
            JLabel email = new JLabel(data[i][4]);
            email.setBounds(600, y, 150, 20);
            email.setBorder(lightBorder);
            email.setBackground(new Color(240, 240, 240));
            email.setOpaque(true);
            JLabel address = new JLabel(data[i][5]);
            address.setBounds(750, y, 150, 20);
            address.setBorder(lightBorder);
            address.setBackground(new Color(240, 240, 240));
            address.setOpaque(true);
            JLabel phone = new JLabel(data[i][6]);
            phone.setBounds(900, y, 150, 20);
            phone.setBorder(lightBorder);
            phone.setBackground(new Color(240, 240, 240));
            phone.setOpaque(true);
            DatabaseHandler conn = new DatabaseHandler();
            conn.connect();
            if (data[i][7].equals("0")) {
                buttons[i] = new JButton("Approve");
                buttons[i].setBounds(1050, y, 150, 20);
                buttons[i].addActionListener((ActionEvent event) -> {
                    String query = "UPDATE users SET approved = '1' WHERE idUser = '" + data[index][0] + "'";
                    try {
                        Statement stmt = conn.con.createStatement();
                        int rs = stmt.executeUpdate(query);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Approved!", "Sistem Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    createApprovalScreen(id);
                });
                panel.add(buttons[i]);
            } else if (data[i][7].equals("1")){
                buttons[i] = new JButton("Unapprove");
                buttons[i].setBounds(1050, y, 150, 20);
                buttons[i].addActionListener((ActionEvent event) -> {
                    String query = "UPDATE users SET approved = '0' WHERE idUser = '" + data[index][0] + "'";
                    try {
                        Statement stmt = conn.con.createStatement();
                        int rs = stmt.executeUpdate(query);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Unapproved!", "Sistem Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    createApprovalScreen(id);
                });
                panel.add(buttons[i]);
            }
            panel.add(idUser); panel.add(idBranch);
            panel.add(fname); panel.add(lname);
            panel.add(email); panel.add(address);
            panel.add(phone);
            y += 20;
        }
        
        // Set JScrollPane
        panel.setPreferredSize(new Dimension(1195, y));
        panel.setLayout(null);
        panel.setBackground(new Color(255, 228, 189));
        JScrollPane sp = new JScrollPane(panel);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setBounds(20, 35, 1215, 480);
        Border bottomBorder = BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY);
        sp.setBorder(bottomBorder);
        frame.add(sp);
        
        // Set OK Button
        JButton ok = new JButton("OK");
        ok.setBounds(1070, 530, 150, 20);
        ok.addActionListener((ActionEvent event) -> {
            frame.dispose();
            new ApprovalMenu();
        });
        frame.add(ok);
        
        frame.setTitle("Penyetujuan Pendaftaran - Sistem Perpustakaan");
        frame.setSize(1260, 600);
        frame.getContentPane().setBackground(new Color(255, 228, 189));
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);    
        frame.setVisible(true);
    }
}
