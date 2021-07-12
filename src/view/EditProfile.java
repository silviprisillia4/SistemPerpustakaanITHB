package view;
import controller.*;
import model.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EditProfile {
    JFrame frame;
    JPanel panel1;
    JLabel labelId, labelName, labelEmail, labelPassword, labelAddress, labelPhoneNumber, labelCash, labelDebt, labelBranch;
    JTextField textFieldId, textFieldName, textFieldEmail, textFieldPassword, textFieldAddress, textFieldPhoneNumber, textFieldCash, textFieldDebt, textFieldBranch;
    JButton btnSave, btnBack;
    
    ErrorMessages em = new ErrorMessages();
    
    public EditProfile() { 
        Member member = UserManager.getInstance().getMember();
        
        //Frame
        frame = new DefaultFrameSetting().defaultFrame();
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        
        //Panel
        panel1 = new DefaultFrameSetting().defaultPanel();
        panel1.setSize(400, 400);
        panel1.setBackground(new Color(255, 234, 202));
        panel1.setVisible(true);
        
        //Label
        labelId = new JLabel("ID");
        labelId.setBounds(30, 30, 100, 20);
        labelName = new JLabel("Name");
        labelName.setBounds(30, 60, 100, 20);
        labelEmail = new JLabel("Email");
        labelEmail.setBounds(30, 90, 100, 20);
        labelAddress = new JLabel("Address");
        labelAddress.setBounds(30, 120, 100, 20);
        labelPhoneNumber = new JLabel("Phone Number");
        labelPhoneNumber.setBounds(30, 150, 100, 20);
        labelCash = new JLabel("Cash");
        labelCash.setBounds(30, 180, 100, 20);
        labelDebt = new JLabel("Debt");
        labelDebt.setBounds(30, 210, 100, 20);
        labelBranch = new JLabel("Branch");
        labelBranch.setBounds(30, 240, 100, 20);
        
        //Text Field
        Controller c = new Controller();
        textFieldId = new JTextField(String.valueOf(member.getIdUser()));
        textFieldId.setBounds(140, 30, 100, 20);
        textFieldId.setEditable(false);
        textFieldName = new JTextField(member.getFirstName()+" "+member.getLastName());
        textFieldName.setBounds(140, 60, 150, 20);
        textFieldEmail = new JTextField(member.getEmail());
        textFieldEmail.setBounds(140, 90, 150, 20);
        textFieldAddress = new JTextField(member.getAddress());
        textFieldAddress.setBounds(140, 120, 150, 20);
        textFieldPhoneNumber = new JTextField(member.getPhoneNumber());
        textFieldPhoneNumber.setBounds(140, 150, 100, 20);
        textFieldCash = new JTextField(String.valueOf(member.getCash()));
        textFieldCash.setBounds(140, 180, 100, 20);
        textFieldCash.setEditable(false);
        textFieldDebt = new JTextField(String.valueOf(member.getDebt()));
        textFieldDebt.setBounds(140, 210, 100, 20);
        textFieldDebt.setEditable(false);
        textFieldBranch = new JTextField(String.valueOf(member.selectBranchCity(member.getIdBranch())));
        textFieldBranch.setBounds(140, 240, 100, 20);
        textFieldBranch.setEditable(false);
        
        //Button
        btnSave = new JButton("Simpan");
        btnSave.setBounds(30, 280, 80, 30);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Yakin simpan perubahan?", "Perpustakaan ITHB", JOptionPane.YES_NO_OPTION);
                if(confirm==JOptionPane.YES_OPTION) {
                    String[] name = textFieldName.getText().split(" ");
                    String firstName = "";
                    for (int i=0; i<name.length-1; i++) {
                        if(i==name.length-2) {
                            firstName += name[i];
                        } else {
                            firstName += name[i]+" ";
                        }
                    }
                    String lastName = name[name.length-1];
                    member.setFirstName(firstName);
                    member.setLastName(lastName);
                    member.setEmail(textFieldEmail.getText());
                    member.setAddress(textFieldAddress.getText());
                    member.setPhoneNumber(textFieldPhoneNumber.getText());
                    boolean success = c.updateProfile(member);
                    if(success) {
                        JOptionPane.showMessageDialog(null, "Perubahan berhasil disimpan!", "Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
                        frame.setVisible(false);
                        new MemberMenu();
                    } else {
                        em.showErrorCantSave();
                    }                     
                } else {
                    em.showErrorCantSave();
                }
            }
        });
        btnBack = new JButton("Kembali");
        btnBack.setBounds(120, 280, 80, 30);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Profile();
            }
        });
        
        //Add
        panel1.add(labelId);
        panel1.add(labelName);
        panel1.add(labelEmail);
        panel1.add(labelAddress);
        panel1.add(labelPhoneNumber);
        panel1.add(labelCash);
        panel1.add(labelDebt);
        panel1.add(labelBranch);
        
        panel1.add(textFieldId);
        panel1.add(textFieldName);
        panel1.add(textFieldEmail);
        panel1.add(textFieldAddress);
        panel1.add(textFieldPhoneNumber);
        panel1.add(textFieldCash);
        panel1.add(textFieldDebt);
        panel1.add(textFieldBranch);
        
        panel1.add(btnSave);
        panel1.add(btnBack);
        
        frame.add(panel1);
        
        //Initialize
        panel1.setLayout(null);
        frame.setLayout(null);
        frame.setVisible(true);
        
    }
}
