/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JOptionPane;

/**
 *
 * @author Yen
 */
public class TopUpApproval {
    
    public TopUpApproval() {
        createApprovalScreen();
    }
    
    private void createApprovalScreen() {
        String kode = JOptionPane.showInputDialog("Masukkan kode Top Up:");
        if (kode == null) {
            new ApprovalMenu();
        } else {
            
        }
    }
}
