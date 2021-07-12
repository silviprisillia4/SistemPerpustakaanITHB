package view;

import javax.swing.JOptionPane;
import model.UserManager;
import model.UserTypeEnum;

public class OutputInfo {

    public void welcomeToMenuAdmin() {
        JOptionPane.showMessageDialog(null, "Selamat datang "+ UserTypeEnum.ADMIN + " " + UserManager.getInstance().getAdmin().getFirstName() + " " + UserManager.getInstance().getAdmin().getLastName() + " cabang " + UserManager.getInstance().getAdmin().selectBranchCity(UserManager.getInstance().getAdmin().getIdBranch()));
    }

    public void welcomeToOwnerMenu() {
        JOptionPane.showMessageDialog(null, "Selamat datang "+ UserTypeEnum.OWNER + " " + UserManager.getInstance().getAdmin().getFirstName() + " " + UserManager.getInstance().getAdmin().getLastName());
    }

    public void changeShowDanaCabang(String branch) {
        JOptionPane.showMessageDialog(null, "Berikut akan di tampilkan pendapatan dari cabang " + branch);
    }

    public void logOutInfo() {
        JOptionPane.showMessageDialog(null, "Anda akan keluar dari menu");
    }

    public void infoUpdateListBook(boolean update) {
        if (update) {
            JOptionPane.showMessageDialog(null, "Silahkan update harga buku");
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan isi form untuk menambah buku baru");
        }
    }

    public void infoApprovePengembalian(boolean success) {
        if (success) {
            JOptionPane.showMessageDialog(null, "Pengembalian buku sukses!!");
        } else {
            JOptionPane.showMessageDialog(null, "Pengembalian buku gagal!!");
        }
    }

    public void backToPreviousMenu() {
        JOptionPane.showMessageDialog(null, "Anda akan kembali ke menu sebelumnya");
    }

    public void successAddNewBook(String title, boolean addBook) {
        if (addBook) {
            JOptionPane.showMessageDialog(null, "Buku " + title + " berhasil ditambahkan!!");
        } else {
            JOptionPane.showMessageDialog(null, "Buku " + title + " gagal ditambahkan!!");
        }
    }
    
    public void cantUpdateBecauseNoChange() {
        JOptionPane.showMessageDialog(null,"Lakukan update terlebih dahulu!!");
    }

    public void infoUpdateABook(String title, boolean update) {
        if (update) {
            JOptionPane.showMessageDialog(null, "Buku " + title + " berhasil diperbarui!!");
        } else {
            JOptionPane.showMessageDialog(null, "Buku " + title + " gagal diperbarui!!");
        }
    }

    public void infoFormValidate() {
        JOptionPane.showMessageDialog(null, "Form belum terisi lengkap!!\nPeriksa kembali form!!");
    }
    
    public void infoNoBorrowList() {
        JOptionPane.showMessageDialog(null,"Belum ada list peminjaman di cabang ini");
    }

    public void infoNoMoney() {
        JOptionPane.showMessageDialog(null,"Belum ada pemasukkan di cabang ini");
    }
}
