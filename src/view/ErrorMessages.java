package view;
import javax.swing.JOptionPane;

public class ErrorMessages {
    public void showError() {
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan.", "Sistem Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE); 
    }
    
    public void showErrorRegistrationFailed() {
        JOptionPane.showMessageDialog(null, "Registrasi gagal!\nHarap periksa kembali data Anda.", "Sistem Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE); 
    }
    
    public void showErrorLoginFailed() {
        JOptionPane.showMessageDialog(null, "Login gagal!", "Sistem Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showErrorEmailRegistered() {
        JOptionPane.showMessageDialog(null, "Email Anda sudah terdaftar!!", "Sistem Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE); 
    }
    
    public void showErrorCantSave() {
        JOptionPane.showMessageDialog(null, "Perubahan gagal disimpan!", "Sistem Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE); 
    }
    
    public void showErrorNoSelectedBook() {
        JOptionPane.showMessageDialog(null, "Anda belum memilih buku!", "Sistem Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE); 
    }
    
    public void showErrorPasswordNotValid() {
        JOptionPane.showMessageDialog(null, "Password tidak memenuhi syarat!", "Sistem Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showErrorPasswordNotEquals() {
        JOptionPane.showMessageDialog(null, "Password Anda tidak sesuai!", "Sistem Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showErrorFailedTopUp() {
        JOptionPane.showMessageDialog(null, "Top-Up gagal!", "Sistem Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showErrorCantUpdateBecauseNoChange() {
        JOptionPane.showMessageDialog(null,"Lakukan update terlebih dahulu!", "Sistem Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showErrorFailedReturnBook() {
        JOptionPane.showMessageDialog(null, "Pengembalian buku gagal!", "Sistem Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showErrorFailedAddBook(String title) {
        JOptionPane.showMessageDialog(null, "Buku " + title + " gagal ditambahkan!", "Sistem Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showErrorFailedUpdateBook(String title) {
        JOptionPane.showMessageDialog(null, "Buku " + title + " gagal diperbarui!", "Sistem Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showErrorFormValidate() {
        JOptionPane.showMessageDialog(null, "Form belum terisi lengkap!!\nPeriksa kembali form!", "Sistem Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showErrorNoBorrowList() {
        JOptionPane.showMessageDialog(null,"Belum ada list peminjaman di cabang ini.", "Sistem Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }

    public void showErrorNoMoney() {
        JOptionPane.showMessageDialog(null,"Belum ada pemasukkan di cabang ini.", "Sistem Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }
}