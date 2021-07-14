package view;
import model.*;
import controller.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TopUpApproval {

    Controller c = new Controller();
    JFrame frame;
    JPanel panel1;
    JLabel labelName, labelCash;
    JTextField textFieldCash;
    JComboBox comboMembers;
    int idUser = 0;

    public TopUpApproval() {
        createApprovalScreen();
    }

    private void createApprovalScreen() {
        Admin admin = UserManager.getInstance().getAdmin();
        
        frame = new DefaultFrameSetting().defaultFrame();
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Perpustakaan ITHB - Admin Top Up");
        
        panel1 = new DefaultFrameSetting().defaultPanel();
        panel1 = new DefaultFrameSetting().defaultPanel();
        panel1.setSize(300, 200);
        panel1.setBackground(new Color(255, 234, 202));
        panel1.setVisible(true);
        
        labelName = new JLabel("User : ");
        labelName.setBounds(20, 20, 100, 20);
        
        comboMembers = comboMembers();
        comboMembers.setBounds(20, 60, 250, 20);
        comboMembers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idUser = admin.getMembers().get(comboMembers.getSelectedIndex()).getIdUser();
            }

        });
        
        labelCash = new JLabel("Jumlah saldo : ");
        labelCash.setBounds(20, 90, 100, 30);
        
        textFieldCash = new JTextField();
        textFieldCash.setBounds(130, 90, 100, 30);
        
        JButton btnSave = new JButton("Simpan");
        btnSave.setBounds(20, 130, 100, 30);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int saldo = Integer.valueOf(textFieldCash.getText());
                if (saldo > 0) {
                    boolean isSuccess = c.topUpByAdmin(idUser, saldo);
                    if (isSuccess) {
                        new OutputInfo().infoTopUpSuccessed();
                    } else {
                        new ErrorMessages().showErrorFailedTopUp();
                    }
                } else {
                    new ErrorMessages().showErrorFailedTopUp();
                }
                new ApprovalMenu();
            }
        });
        
        panel1.add(labelName);
        panel1.add(comboMembers);
        panel1.add(labelCash);
        panel1.add(textFieldCash);
        panel1.add(btnSave);
        frame.add(panel1);
    }
    
    public JComboBox comboMembers() {
        Admin admin = UserManager.getInstance().getAdmin();
        //create list book at a library
        String[] members = new String[admin.getMembers().size()];
        for (int i = 0; i < members.length; i++) {
            members[i] = c.getAllMembers(admin.getIdBranch()).get(i).getFirstName()+" "+c.getAllMembers(admin.getIdBranch()).get(i).getLastName();
        }

        //create combobox for list title
        JComboBox comboMembers = new JComboBox(members);

        return comboMembers;
    }
}
