package view;

import javax.swing.JOptionPane;
import model.UserTypeEnum;

public class OutputInfo {

    public void welcomeToMenuAdmin(String name, String branch) {
        JOptionPane.showMessageDialog(null, "Selamat datang admin " + name + " cabang " + branch);
    }

    public void welcomeToOwnerMenu() {
        JOptionPane.showMessageDialog(null, "Selamat datang "+ UserTypeEnum.OWNER);
    }

    public void changeShowDanaCabang(String branch) {
        JOptionPane.showMessageDialog(null, "Berikut akan di tampilkan pendapatan dari cabang " + branch);
    }

    public void exitFromOwnerMenu() {
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

    public void exit() {
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
}
