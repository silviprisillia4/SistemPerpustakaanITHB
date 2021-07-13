package view;

import controller.*;
import javax.swing.JOptionPane;

public class TopUpApproval {

    Controller c = new Controller();

    public TopUpApproval() {
        createApprovalScreen();
    }

    private void createApprovalScreen() {
        int idUser = Integer.parseInt(JOptionPane.showInputDialog(null, "Input ID User :", "Perpustakaan ITHB - Admin Top Up", JOptionPane.QUESTION_MESSAGE));
        int saldo = Integer.parseInt(JOptionPane.showInputDialog(null, "Jumlah Saldo Top-Up:", "Sistem Perpustakaan ITHB - Admin Top Up", JOptionPane.QUESTION_MESSAGE));
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
}
