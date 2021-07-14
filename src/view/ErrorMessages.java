package view;
import javax.swing.JOptionPane;

public class ErrorMessages {
    public void showError() {
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan.", "Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE); 
    }
    
    public void showErrorEmptyDataInputPrice() {
        JOptionPane.showMessageDialog(null, "Periksa kembali input harga buku.", "Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE); 
    }
    
    public void showErrorRegistrationFailed() {
        JOptionPane.showMessageDialog(null, "Registrasi gagal!\nHarap periksa kembali data Anda.", "Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE); 
    }
    
    public void showErrorLoginFailed() {
        JOptionPane.showMessageDialog(null, "Login gagal!", "Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showErrorEmailRegistered() {
        JOptionPane.showMessageDialog(null, "Email Anda sudah terdaftar!", "Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE); 
    }
    
    public void showErrorCantSave() {
        JOptionPane.showMessageDialog(null, "Perubahan gagal disimpan!", "Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE); 
    }
    
    public void showErrorNoSelectedBook() {
        JOptionPane.showMessageDialog(null, "Anda belum memilih buku!", "Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE); 
    }
    
    public void showErrorPasswordNotValid() {
        JOptionPane.showMessageDialog(null, "Password tidak memenuhi syarat!", "Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showErrorPasswordNotEquals() {
        JOptionPane.showMessageDialog(null, "Password Anda tidak sesuai!", "Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showErrorFailedTopUp() {
        JOptionPane.showMessageDialog(null, "Top-Up gagal!", "Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showErrorCantUpdateBecauseNoChange() {
        JOptionPane.showMessageDialog(null,"Lakukan update terlebih dahulu!", "Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showErrorFailedReturnBook() {
        JOptionPane.showMessageDialog(null, "Pengembalian buku gagal!", "Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showErrorFailedAddBook(String title) {
        JOptionPane.showMessageDialog(null, "Buku " + title + " gagal ditambahkan!", "Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showErrorFailedUpdateBook(String title) {
        JOptionPane.showMessageDialog(null, "Buku " + title + " gagal diperbarui!", "Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showErrorFormValidate() {
        JOptionPane.showMessageDialog(null, "Form belum terisi lengkap!\nPeriksa kembali form!", "Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showErrorNoBorrowList() {
        JOptionPane.showMessageDialog(null,"Belum ada list peminjaman di cabang ini.", "Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }

    public void showErrorNoMoney() {
        JOptionPane.showMessageDialog(null,"Belum ada pemasukkan di cabang ini.", "Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showErrorNoReturnSelected() {
        JOptionPane.showMessageDialog(null,"Belum ada pengembalian yang dipilih.", "Perpustakaan ITHB", JOptionPane.ERROR_MESSAGE);
    }
}