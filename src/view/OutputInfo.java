package view;
import javax.swing.JOptionPane;
import model.*;

public class OutputInfo {

    ErrorMessages em = new ErrorMessages();
    
    public void infoRegistrationSuccessed() {
        JOptionPane.showMessageDialog(null, "Registrasi berhasil!", "Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void infoLoginSuccessed() {
        JOptionPane.showMessageDialog(null, "Login berhasil!", "Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void welcomeToMenuAdmin() {
        JOptionPane.showMessageDialog(null, "Selamat datang "+ UserTypeEnum.ADMIN + " " + UserManager.getInstance().getAdmin().getFirstName() + " " + UserManager.getInstance().getAdmin().getLastName() + " cabang " + UserManager.getInstance().getAdmin().selectBranchCity(UserManager.getInstance().getAdmin().getIdBranch()), "Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
    }

    public void welcomeToOwnerMenu() {
        JOptionPane.showMessageDialog(null, "Selamat datang "+ UserTypeEnum.OWNER + " " + UserManager.getInstance().getOwner().getFirstName() + " " + UserManager.getInstance().getOwner().getLastName(), "Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void welcomeToMemberMenu() {
        JOptionPane.showMessageDialog(null, "Selamat datang " + UserManager.getInstance().getMember().getFirstName() + " " + UserManager.getInstance().getMember().getLastName() + " di Perpustakaan ITHB", "Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void infoDanaCabang(String branch) {
        JOptionPane.showMessageDialog(null, "Berikut akan ditampilkan pendapatan dari cabang " + branch, "Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
    }

    public void infoLogOut() {
        JOptionPane.showMessageDialog(null, "Terima kasih sudah menggunakan layanan Perpustakaan ITHB!", "Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
    }

    public void infoUpdateListBook(boolean update) {
        if (update) {
            JOptionPane.showMessageDialog(null, "Silahkan update harga buku.", "Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan isi form untuk menambah buku baru.", "Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void infoApprovePengembalian(boolean success) {
        if (success) {
            JOptionPane.showMessageDialog(null, "Pengembalian buku sukses!", "Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
        } else {
            em.showErrorFailedReturnBook();
        }
    }

    public void backToPreviousMenu() {
        JOptionPane.showMessageDialog(null, "Anda akan kembali ke menu sebelumnya.", "Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
    }

    public void successAddNewBook(String title, boolean addBook) {
        if (addBook) {
            JOptionPane.showMessageDialog(null, "Buku " + title + " berhasil ditambahkan!", "Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
        } else {
            em.showErrorFailedAddBook(title);
        }
    }

    public void infoUpdateABook(String title, boolean update) {
        if (update) {
            JOptionPane.showMessageDialog(null, "Buku " + title + " berhasil diperbarui!", "Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
        } else {
            em.showErrorFailedUpdateBook(title);
        }
    }
    
    public void infoTopUpSuccessed() {
        JOptionPane.showMessageDialog(null, "Top-Up berhasil!", "Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void infoBorrowed() {
        JOptionPane.showMessageDialog(null, "Buku berhasil dipinjam! Silakan ambil buku di meja pengambilan.", "Perpustakaan ITHB", JOptionPane.INFORMATION_MESSAGE);
    }
}
