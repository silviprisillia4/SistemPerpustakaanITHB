package view;
import controller.*;
import controller.LoginHandler;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;

public class MainScreen {
    
    Controller c = new Controller();
    
    public MainScreen() {
        createMainScreen();
    }
    
    private void createMainScreen() {
        JFrame mainFrame = new DefaultFrameSetting().defaultFrame();
        
        JComboBox branchComboBox = new JComboBox();
        branchComboBox.setBounds(100, 10, 200, 20);
        
        ArrayList<String> branchesCity = c.getBranchesCity();
        for (int i = 0; i < branchesCity.size(); i++) {
            branchComboBox.addItem(branchesCity.get(i));
        }
        
        JLabel branchLabel = new JLabel("Pilih Cabang :");
        branchLabel.setBounds(10, 10, 90, 20);
        branchLabel.add(branchComboBox);
        
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(10, 40, 140, 20);
        registerButton.addActionListener((ActionEvent e) -> {
            String selectedBranch = branchComboBox.getSelectedItem().toString();
            mainFrame.dispose();
            createRegisterScreen(selectedBranch);
        });
        
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(160, 40, 140, 20);
        loginButton.addActionListener((ActionEvent e) -> {
            String selectedBranch = branchComboBox.getSelectedItem().toString();
            mainFrame.setVisible(false);
            createLoginScreen(selectedBranch);
        });
        
        mainFrame.add(branchLabel); mainFrame.add(branchComboBox);
        mainFrame.add(registerButton); mainFrame.add(loginButton);
        
        mainFrame.setTitle("Perpustakaan ITHB");
        mainFrame.setSize(330, 110);
        mainFrame.getContentPane().setBackground(new Color(255, 228, 189));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void createRegisterScreen(String selectedBranch) {
        JFrame registerFrame = new DefaultFrameSetting().defaultFrame();
        
        JTextField fnameFieldRegister = new JTextField();
        fnameFieldRegister.setBounds(130, 10, 200, 20);
        
        JLabel fnameLabelRegister = new JLabel("First Name *");
        fnameLabelRegister.setBounds(10, 10, 110, 20);
        fnameLabelRegister.add(fnameFieldRegister);
        
        JTextField lnameFieldRegister = new JTextField();
        lnameFieldRegister.setBounds(130, 40, 200, 20);
        
        JLabel lnameLabelRegister = new JLabel("Last Name");
        lnameLabelRegister.setBounds(10, 40, 110, 20);
        lnameLabelRegister.add(lnameFieldRegister);
        
        JTextField addressFieldRegister = new JTextField();
        addressFieldRegister.setBounds(130, 70, 200, 20);
        
        JLabel addressLabelRegister = new JLabel("Address *");
        addressLabelRegister.setBounds(10, 70, 110, 20);
        addressLabelRegister.add(addressFieldRegister);
        
        JTextField phoneFieldRegister = new JTextField();
        phoneFieldRegister.setBounds(130, 100, 200, 20);
        
        JLabel phoneLabelRegister = new JLabel("Phone Number *");
        phoneLabelRegister.setBounds(10, 100, 110, 20);
        phoneLabelRegister.add(phoneFieldRegister);
        
        JTextField emailFieldRegister = new JTextField();
        emailFieldRegister.setBounds(130, 130, 200, 20);
        
        JLabel emailLabelRegister = new JLabel("Email *");
        emailLabelRegister.setBounds(10, 130, 110, 20);
        emailLabelRegister.add(emailFieldRegister);
        
        JPasswordField newPassFieldRegister = new JPasswordField();
        newPassFieldRegister.setBounds(130, 160, 200, 20);
        
        JLabel newPassLabelRegister = new JLabel("New Password *");
        newPassLabelRegister.setBounds(10, 160, 110, 20);
        newPassLabelRegister.add(newPassFieldRegister);
        
        JPasswordField rePassFieldRegister = new JPasswordField();
        rePassFieldRegister.setBounds(130, 190, 200, 20);
        
        JLabel rePassLabelRegister = new JLabel("Re-type Password");
        rePassLabelRegister.setBounds(10, 190, 110, 20);
        rePassLabelRegister.add(rePassFieldRegister);
        
        JButton registerButton2 = new JButton("REGISTER");
        registerButton2.setBounds(10, 220, 320, 20);
        registerButton2.addActionListener((ActionEvent e) -> {
            boolean available = c.userRegisterAvailability(selectedBranch, emailFieldRegister.getText());
            if (available) {
                boolean isSuccess = c.processingRegistration(
                        c.getBranchIDByCity(selectedBranch),
                        fnameFieldRegister.getText(),
                        lnameFieldRegister.getText(),
                        addressFieldRegister.getText(),
                        phoneFieldRegister.getText(),
                        emailFieldRegister.getText(),
                        newPassFieldRegister.getText(),
                        rePassFieldRegister.getText());
                if (isSuccess) {
                    registerFrame.dispose();
                    new OutputInfo().infoRegistrationSuccessed();
                    createMainScreen();
                } else {
                    registerFrame.setVisible(false);
                    new ErrorMessages().showErrorRegistrationFailed();
                    registerFrame.setVisible(true);
                }
            } else {
                registerFrame.setVisible(false);
                new ErrorMessages().showErrorEmailRegistered();
                registerFrame.setVisible(true);
            }
        });
        
        JButton backButton = new JButton("BACK");
        backButton.setBounds(10, 250, 320, 20);
        backButton.addActionListener((ActionEvent e) -> {
            registerFrame.dispose();
            createMainScreen();
        });
        
        registerFrame.add(fnameFieldRegister); registerFrame.add(fnameLabelRegister);
        registerFrame.add(lnameFieldRegister); registerFrame.add(lnameLabelRegister);
        registerFrame.add(emailFieldRegister); registerFrame.add(emailLabelRegister);
        registerFrame.add(newPassFieldRegister); registerFrame.add(newPassLabelRegister);
        registerFrame.add(rePassFieldRegister); registerFrame.add(rePassLabelRegister);
        registerFrame.add(addressFieldRegister); registerFrame.add(addressLabelRegister);
        registerFrame.add(phoneFieldRegister); registerFrame.add(phoneLabelRegister);
        registerFrame.add(registerButton2); registerFrame.add(backButton);
        
        registerFrame.setTitle("Perpustakaan ITHB - Register");
        registerFrame.setSize(360, 320);
        registerFrame.getContentPane().setBackground(new Color(255, 228, 189));
        registerFrame.setLocationRelativeTo(null);
    }
    
    public void createLoginScreen(String selectedBranch) {
        JFrame loginFrame = new DefaultFrameSetting().defaultFrame();
        
        JTextField emailFieldLogin = new JTextField();
        emailFieldLogin.setBounds(100, 10, 200, 20);
        
        JLabel emailLabelLogin = new JLabel("Email");
        emailLabelLogin.setBounds(10, 10, 90, 20);
        emailLabelLogin.add(emailFieldLogin);
        
        JPasswordField passFieldLogin = new JPasswordField();
        passFieldLogin.setBounds(100, 40, 200, 20);
        
        JLabel passLabelLogin = new JLabel("Password");
        passLabelLogin.setBounds(10, 40, 110, 20);
        passLabelLogin.add(passFieldLogin);
        
        JButton loginButton2 = new JButton("LOGIN");
        loginButton2.setBounds(10, 70, 290, 20);
        loginButton2.addActionListener((ActionEvent e) -> {
            Controller c = new Controller();
            boolean valid = c.userLoginAvailability(selectedBranch, emailFieldLogin.getText(), c.getMD5(passFieldLogin.getText()));
            if (valid) {
                loginFrame.dispose();
                new OutputInfo().infoLoginSuccessed();
                new LoginHandler(emailFieldLogin.getText(), selectedBranch);
            } else {
                loginFrame.setVisible(false);
                new ErrorMessages().showErrorLoginFailed();
                loginFrame.setVisible(true);
            }
        });
        
        JButton backButton2 = new JButton("BACK");
        backButton2.setBounds(10, 100, 290, 20);
        backButton2.addActionListener((ActionEvent e) -> {
            loginFrame.dispose();
            createMainScreen();              
        });
        
        loginFrame.add(emailFieldLogin); loginFrame.add(emailLabelLogin);
        loginFrame.add(passFieldLogin); loginFrame.add(passLabelLogin);
        loginFrame.add(loginButton2); loginFrame.add(backButton2);
        loginFrame.getRootPane().setDefaultButton(loginButton2);
        loginFrame.setTitle("Perpustakaan ITHB - Login");
        loginFrame.setSize(330, 170);
        loginFrame.getContentPane().setBackground(new Color(255, 228, 189));
        loginFrame.setLocationRelativeTo(null);
    }
}
