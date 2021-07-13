package view;
import javax.swing.JOptionPane;
import model.*;

public class OutputInfo {

    ErrorMessages em = new ErrorMessages();
    
    public void infoRegistrationSuccessed() {
        JOptionPane.showMessageDialog(null, "Registrasi berhasil!", "Sistem Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void infoLoginSuccessed() {
        JOptionPane.showMessageDialog(null, "Login berhasil!", "Sistem Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void welcomeToMenuAdmin() {
        JOptionPane.showMessageDialog(null, "Selamat datang "+ UserTypeEnum.ADMIN + " " + UserManager.getInstance().getAdmin().getFirstName() + " " + UserManager.getInstance().getAdmin().getLastName() + " cabang " + UserManager.getInstance().getAdmin().selectBranchCity(UserManager.getInstance().getAdmin().getIdBranch()), "Sistem Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
    }

    public void welcomeToOwnerMenu() {
        JOptionPane.showMessageDialog(null, "Selamat datang "+ UserTypeEnum.OWNER + " " + UserManager.getInstance().getAdmin().getFirstName() + " " + UserManager.getInstance().getAdmin().getLastName());
    }

    public void infoDanaCabang(String branch) {
        JOptionPane.showMessageDialog(null, "Berikut akan di tampilkan pendapatan dari cabang " + branch, "Sistem Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
    }

    public void infoLogOut() {
        JOptionPane.showMessageDialog(null, "Terima kasih sudah menggunakan layanan Sistem Perpustakaan ITHB!", "Sistem Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
    }

    public void infoUpdateListBook(boolean update) {
        if (update) {
            JOptionPane.showMessageDialog(null, "Silahkan update harga buku.", "Sistem Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan isi form untuk menambah buku baru.", "Sistem Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void infoApprovePengembalian(boolean success) {
        if (success) {
            JOptionPane.showMessageDialog(null, "Pengembalian buku sukses!", "Sistem Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
        } else {
            em.showErrorFailedReturnBook();
        }
    }

    public void backToPreviousMenu() {
        JOptionPane.showMessageDialog(null, "Anda akan kembali ke menu sebelumnya.", "Sistem Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
    }

    public void successAddNewBook(String title, boolean addBook) {
        if (addBook) {
            JOptionPane.showMessageDialog(null, "Buku " + title + " berhasil ditambahkan!", "Sistem Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
        } else {
            em.showErrorFailedAddBook(title);
        }
    }

    public void infoUpdateABook(String title, boolean update) {
        if (update) {
            JOptionPane.showMessageDialog(null, "Buku " + title + " berhasil diperbarui!");
        } else {
            em.showErrorFailedUpdateBook(title);
        }
    }
    
    public void infoTopUpSuccessed() {
        JOptionPane.showMessageDialog(null, "Top-Up berhasil!", "Sistem Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void infoBorrowed() {
        JOptionPane.showMessageDialog(null, "Buku berhasil dipinjam! Silakan ambil buku di meja pengambilan.", "Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
    }
}
