package view;
import model.UserManager;
import controller.*;
import model.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PanelChangePassword {
    JFrame frame;
    JPanel panel1;
    JLabel labelOldPassword, labelNewPassword, labelValidatePassword;
    JPasswordField textFieldOldPassword, textFieldNewPassword, textFieldValidatePassword;
    JButton btnSave, btnBack;
    
    ErrorMessages em = new ErrorMessages();
    
    public PanelChangePassword() {
        Member member = UserManager.getInstance().getMember();

        //Frame
        frame = new JFrame("Perpustakaan ITHB");
        frame.setSize(350, 230);
        frame.setLocationRelativeTo(null);
        
        //Panel
        panel1 = new JPanel();
        panel1.setSize(350, 230);
        panel1.setBackground(Color.ORANGE);
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
                Regex r = new Regex();
                Member memberSelected = c.getSelectedMember(member.getIdUser());
                boolean updated = false;
                
                String oldPassword = c.getSelectedPassword(memberSelected.getIdUser()); //oldPassword dari database
                String hashedOldPassword = c.getMD5(String.valueOf(textFieldOldPassword.getPassword())); //input oldPassword dihash
                
                if(hashedOldPassword.equals(oldPassword)) {
                    String newPassword = String.valueOf(textFieldNewPassword.getPassword());
                    String validatePassword = String.valueOf(textFieldValidatePassword.getPassword());
                    if(newPassword.equals(validatePassword)) {
                        String hashedNewPassword = c.getMD5(String.valueOf(textFieldNewPassword.getPassword()));
                        boolean isValid = r.passValidation(newPassword);
                        if(isValid) {
                            updated = c.updatePassword(memberSelected, hashedNewPassword);
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
                    //kasih notif berhasil
                    frame.setVisible(false);
                    new PanelProfile();
                }
            }
        });
        btnBack = new JButton("Kembali");
        btnBack.setBounds(120, 130, 80, 30);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new PanelProfile();
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