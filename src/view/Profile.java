package view;
import controller.*;
import model.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class Profile {
    JFrame frame;
    JPanel panel1, panel2;
    JTabbedPane tp;
    JButton btnEditProfile, btnChangePassword, btnTopUp, btnBack;
    JTable table;
    
    public Profile() {
        Member member = UserManager.getInstance().getMember(); 
        
        //Frame
        frame = new DefaultFrameSetting().defaultFrame();
        frame.setSize(600, 540);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Perpustakaan ITHB - Profile");
        
        //Panel
        panel1 = new DefaultFrameSetting().defaultPanel();
        panel1 = member.printMemberData();
        panel1.setBackground(new Color(255, 234, 202));
        panel2 = new DefaultFrameSetting().defaultPanel();
        panel2.setSize(500, 450);
        panel2.setBackground(new Color(255, 234, 202));
        
        //Tabbed Panes
        tp = new JTabbedPane();  
        tp.setBounds(20, 20, 500, 450);  
        
        //Button
        btnEditProfile = new JButton("Edit Profil");
        btnEditProfile.setBounds(30, 310, 130, 30);
        btnEditProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new EditProfile();
            }
        });
        
        btnChangePassword = new JButton("Ubah Password");
        btnChangePassword.setBounds(170, 310, 130, 30);
        btnChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new ChangePassword();
            }
        });
        
        btnTopUp = new JButton("Top-Up Saldo");
        btnTopUp.setBounds(310, 310, 130, 30);
        btnTopUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new TopUp();
            }
        });
        
        btnBack = new JButton("Kembali");
        btnBack.setBounds(30, 350, 410, 30);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().backToPreviousMenu();
                new MemberMenu();
            }
        });
        
        //Table
        Controller c = new Controller();
        ArrayList<Borrowing> borrows = c.getAllBorrowList(member.getIdUser(), 2);
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
        table.getColumnModel().getColumn(0).setPreferredWidth(500);
        table.getColumnModel().getColumn(1).setPreferredWidth(500);
        table.getColumnModel().getColumn(2).setPreferredWidth(3000);
        table.getColumnModel().getColumn(3).setPreferredWidth(500);
        table.getColumnModel().getColumn(4).setPreferredWidth(1000);
        table.getColumnModel().getColumn(5).setPreferredWidth(1000);
        
        table.setBounds(0, 0, 500, 450); 
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 0, 500, 450);
        
        //Add
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