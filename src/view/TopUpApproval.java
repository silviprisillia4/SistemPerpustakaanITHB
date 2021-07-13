package view;
import controller.*;
import javax.swing.JOptionPane;

public class TopUpApproval {
    
    Controller c = new Controller();
    
    public TopUpApproval() {
        createApprovalScreen();
    }
    
    private void createApprovalScreen() {
        int idUser = Integer.parseInt(JOptionPane.showInputDialog(null, "Input ID User :", "Sistem Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE));
        int saldo = Integer.parseInt(JOptionPane.showInputDialog(null, "Jumlah Saldo Top-Up:", "Sistem Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE));
        boolean isSuccess = c.topUpByAdmin(idUser, saldo);
        if (isSuccess) {
            new OutputInfo().infoTopUpSuccessed();
        } else {
            new ErrorMessages().showErrorFailedTopUp();
        }
        new ApprovalMenu();
    }
}
