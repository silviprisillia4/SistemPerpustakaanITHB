package view;
import controller.*;
import model.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ChangePassword {
    JFrame frame;
    JPanel panel1;
    JLabel labelOldPassword, labelNewPassword, labelValidatePassword;
    JPasswordField textFieldOldPassword, textFieldNewPassword, textFieldValidatePassword;
    JButton btnSave, btnBack;
    
    ErrorMessages em = new ErrorMessages();
    
    public ChangePassword() {
        Member member = UserManager.getInstance().getMember();

        //Frame
        frame = new DefaultFrameSetting().defaultFrame();
        frame.setSize(350, 230);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Perpustakaan ITHB - Ubah Password");
        
        //Panel
        panel1 = new DefaultFrameSetting().defaultPanel();
        panel1.setSize(350, 230);
        panel1.setBackground(new Color(255, 234, 202));
        panel1.setVisible(true);
        
        //Label
        labelOldPassword = new JLabel("Old Password");
        labelOldPassword.setBounds(30, 30, 100, 20);
        labelNewPassword = new JLabel("New Password");
        labelNewPassword.setBounds(30, 60, 100, 20);
        labelValidatePassword = new JLabel("Validate New Password");
        labelValidatePassword.setBounds(30, 90, 200, 20);
        
        //Text Field
        textFieldOldPassword = new JPasswordField();
        textFieldOldPassword.setBounds(210, 30, 100, 20);
        textFieldNewPassword = new JPasswordField();
        textFieldNewPassword.setBounds(210, 60, 100, 20);
        textFieldValidatePassword = new JPasswordField();
        textFieldValidatePassword.setBounds(210, 90, 100, 20);
        
        //Button
        btnSave = new JButton("Simpan");
        btnSave.setBounds(30, 130, 80, 30);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller c = new Controller();
                controller.RegexController r = new controller.RegexController();
                boolean updated = false;
                
                String oldPassword = member.getPassword(); //oldPassword dari database
                String hashedOldPassword = c.getMD5(String.valueOf(textFieldOldPassword.getPassword())); //oldPassword dari input dihash
                
                if(hashedOldPassword.equals(oldPassword)) {
                    String newPassword = String.valueOf(textFieldNewPassword.getPassword());
                    String validatePassword = String.valueOf(textFieldValidatePassword.getPassword());
                    if(newPassword.equals(validatePassword)) {
                        String hashedNewPassword = c.getMD5(String.valueOf(textFieldNewPassword.getPassword())); //newPassword dari input dihash
                        boolean isValid = r.passValidation(newPassword);
                        if(isValid) {
                            updated = c.updatePassword(member, hashedNewPassword);
                        } else {
                            em.showErrorPasswordNotValid();
                        }
                    } else {
                        em.showErrorPasswordNotEquals();
                    }                    
                } else {
                    em.showErrorPasswordNotEquals();
                }
                
                if(updated) {
                    frame.setVisible(false);
                    new Profile();
                }
            }
        });
        btnBack = new JButton("Kembali");
        btnBack.setBounds(120, 130, 80, 30);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().backToPreviousMenu();
                new Profile();
            }
        });
        
        //Add
        panel1.add(labelOldPassword);
        panel1.add(labelNewPassword);
        panel1.add(labelValidatePassword);
        panel1.add(textFieldOldPassword);
        panel1.add(textFieldNewPassword);
        panel1.add(textFieldValidatePassword);
        panel1.add(btnSave);
        panel1.add(btnBack);
        frame.add(panel1);
        
        //Initialize
        panel1.setLayout(null);
        frame.setLayout(null);
        frame.setVisible(true);   
    }
}