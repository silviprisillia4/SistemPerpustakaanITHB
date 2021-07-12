package view;
import model.UserManager;
import controller.Controller;
import model.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class PanelProfile {
    JFrame frame;
    JPanel panel1, panel2;
    JTabbedPane tp;
    JLabel labelId, labelName, labelEmail, labelPassword, labelAddress, labelPhoneNumber, labelCash, labelDebt, labelBranch;
    JLabel labelDataId, labelDataName, labelDataEmail, labelDataPassword, labelDataAddress, labelDataPhoneNumber, labelDataCash, labelDataDebt, labelDataBranch;
    JButton btnEditProfile, btnChangePassword, btnTopUp, btnBack;
    JTable table;
    
    public PanelProfile() {
        Member member = UserManager.getInstance().getMember();
        
        //Frame
        frame = new JFrame("Perpustakaan ITHB");
        frame.setSize(600, 540);
        frame.setLocationRelativeTo(null);
        
        //Panel
        panel1 = new JPanel();
        panel1.setSize(500, 450);
        panel1.setBackground(Color.CYAN);
        panel2 = new JPanel();
        panel2.setSize(500, 450);
        panel2.setBackground(Color.MAGENTA);
        
        
        //Tabbed Panes
        tp = new JTabbedPane();  
        tp.setBounds(20, 20, 500, 450);  
        
        //Label
        labelId = new JLabel("ID");
        labelId.setBounds(30, 30, 100, 20);
        labelName = new JLabel("Nama");
        labelName.setBounds(30, 60, 100, 20);
        labelEmail = new JLabel("Email");
        labelEmail.setBounds(30, 90, 100, 20);
        labelPassword = new JLabel("Password");
        labelPassword.setBounds(30, 120, 100, 20);
        labelAddress = new JLabel("Alamat");
        labelAddress.setBounds(30, 150, 100, 20);
        labelPhoneNumber = new JLabel("Telepon");
        labelPhoneNumber.setBounds(30, 180, 100, 20);
        labelCash = new JLabel("Saldo");
        labelCash.setBounds(30, 210, 100, 20);
        labelDebt = new JLabel("Hutang");
        labelDebt.setBounds(30, 240, 100, 20);
        labelBranch = new JLabel("Cabang");
        labelBranch.setBounds(30, 270, 100, 20);
        
        //Label Data
        Controller c = new Controller();
        int idUser = member.getIdUser();
        member = c.getSelectedMember(idUser);
        labelDataId = new JLabel(String.valueOf(idUser));
        labelDataId.setBounds(140, 30, 100, 20);
        labelDataName = new JLabel(member.getFirstName()+" "+member.getLastName());
        labelDataName.setBounds(140, 60, 150, 20);
        labelDataEmail = new JLabel(member.getEmail());
        labelDataEmail.setBounds(140, 90, 150, 20);
        labelDataPassword = new JLabel("********");
        labelDataPassword.setBounds(140, 120, 100, 20);
        labelDataAddress = new JLabel(member.getAddress());
        labelDataAddress.setBounds(140, 150, 150, 20);
        labelDataPhoneNumber = new JLabel(member.getPhoneNumber());
        labelDataPhoneNumber.setBounds(140, 180, 100, 20);
        labelDataCash = new JLabel(String.valueOf(member.getCash()));
        labelDataCash.setBounds(140, 210, 100, 20);
        labelDataDebt = new JLabel(String.valueOf(member.getDebt()));
        labelDataDebt.setBounds(140, 240, 100, 20);
        labelDataBranch = new JLabel(member.selectBranchCity(member.getIdBranch()));
        labelDataBranch.setBounds(140, 270, 100, 20);
        
        //Button
        btnEditProfile = new JButton("Edit Profil");
        btnEditProfile.setBounds(30, 310, 130, 30);
        btnEditProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new PanelEditProfile();
            }
        });
        
        btnChangePassword = new JButton("Ubah Password");
        btnChangePassword.setBounds(170, 310, 130, 30);
        btnChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new PanelChangePassword();
            }
        });
        
        btnTopUp = new JButton("Top-Up Saldo");
        btnTopUp.setBounds(310, 310, 130, 30);
        btnTopUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new PanelTopUp();
            }
        });
        
        btnBack = new JButton("Kembali");
        btnBack.setBounds(30, 350, 410, 30);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MemberMenu();
            }
        });
        
        //Table
        ArrayList<Borrowing> borrows = c.getAllBorrowingHistory(member.getIdUser());
        member.setBorrows(borrows); 
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Pinjam");
        model.addColumn("ID Buku");
        model.addColumn("Judul");
        model.addColumn("Hari Pinjam");
        model.addColumn("Tanggal");
        model.addColumn("Status");
        table = new JTable(model);
        
        //Looping Data to Table
        for (int i=0; i<borrows.size(); i++) {
            Borrowing current = borrows.get(i);
            Object[] addBorrowing = new Object[6];
            addBorrowing[0] = current.getIdBorrow();
            addBorrowing[1] = current.getIdBook();
            addBorrowing[2] = c.getTitleSelectedBook(current.getIdBook());
            addBorrowing[3] = current.getBorrowDays();
            addBorrowing[4] = current.getDate();
            addBorrowing[5] = current.selectBookState(current.getStatus());
            model = (DefaultTableModel)table.getModel();
            model.addRow(addBorrowing);
        }
        
        //Set Column Size
        table.getColumnModel().getColumn(0).setPreferredWidth(1000);
        table.getColumnModel().getColumn(1).setPreferredWidth(1000);
        table.getColumnModel().getColumn(2).setPreferredWidth(2000);
        table.getColumnModel().getColumn(3).setPreferredWidth(3000);
        table.getColumnModel().getColumn(4).setPreferredWidth(5000);
        table.getColumnModel().getColumn(5).setPreferredWidth(5000);
        
        table.setBounds(0, 0, 500, 170); 
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 0, 500, 170);
        
        //Add
        panel1.add(labelId);
        panel1.add(labelName);
        panel1.add(labelEmail);
        panel1.add(labelPassword);
        panel1.add(labelAddress);
        panel1.add(labelPhoneNumber);
        panel1.add(labelCash);
        panel1.add(labelDebt);
        panel1.add(labelBranch);
        
        panel1.add(labelDataId);
        panel1.add(labelDataName);
        panel1.add(labelDataEmail);
        panel1.add(labelDataPassword);
        panel1.add(labelDataAddress);
        panel1.add(labelDataPhoneNumber);
        panel1.add(labelDataCash);
        panel1.add(labelDataDebt);
        panel1.add(labelDataBranch);
        
        panel1.add(btnEditProfile);
        panel1.add(btnChangePassword);
        panel1.add(btnTopUp);
        panel1.add(btnBack);
        
        panel2.add(sp);
        
        //Add
        tp.add("Profil", panel1);
        tp.add("Riwayat Peminjaman", panel2);
        frame.add(tp);
        
        //Initialize
        panel1.setLayout(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
