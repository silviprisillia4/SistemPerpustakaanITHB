/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.DataController;
import javax.swing.JOptionPane;

/**
 *
 * @author yen
 */
public class TopUpApproval {
    
    DataController c = new DataController();
    
    public TopUpApproval() {
        createApprovalScreen();
    }
    
    private void createApprovalScreen() {
        int idUser = Integer.parseInt(JOptionPane.showInputDialog("Input ID User:"));
        int saldo = Integer.parseInt(JOptionPane.showInputDialog("Jumlah Saldo Top-Up:"));
        boolean isSuccess = c.topUpByAdmin(idUser, saldo);
        if (isSuccess) {
            JOptionPane.showMessageDialog(null, "Top-Up berhasil!");
        } else {
            JOptionPane.showMessageDialog(null, "Top-Up gagal!");
        }
        new ApprovalMenu();
    }
}
