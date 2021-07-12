package view;
import javax.swing.JOptionPane;

public class ErrorMessages {
    
    public void showErrorCantSave() {
        JOptionPane.showMessageDialog(null, "Perubahan gagal disimpan!", "Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE); 
    }
    
    public void showErrorPasswordNotValid() {
        JOptionPane.showMessageDialog(null, "Password harus terdiri dari 8-20 huruf!", "Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showErrorPasswordNotEquals() {
        JOptionPane.showMessageDialog(null, "Password anda tidak sesuai!", "Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }
}