package view;

import controller.TableCheckBoxBorrowing;
import controller.databaseChange;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Admin;
import model.Borrowing;
import model.Member;
import model.PaidBook;

/**
 *
 * @author Feliciana Gunadi
 */
public class AdminMenu {

    public void adminMenu(Admin admin) {
        new OutputInfo().welcomeToMenuAdmin((admin.getFirstName() + " " + admin.getLastName()), admin.selectBranchCity(admin.getIdBranch()));
        JFrame frame = new JFrame();

        JButton approval = new JButton("Penyetujuan");
        approval.setBounds(100, 50, 250, 50);
        approval.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //nanti yen di sini ya hehe <3
            }
        });
        JButton bookListUpdate = new JButton("Update Data Buku");
        bookListUpdate.setBounds(100, 150, 250, 50);
        bookListUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                bookListUpdate(admin.getIdBranch());
            }
        });
        JButton borrowingList = new JButton("List Pengembalian");
        borrowingList.setBounds(100, 250, 250, 50);
        borrowingList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                returnBorrow(admin.getIdBranch());
            }
        });
        JButton userHistory = new JButton("User History");
        userHistory.setBounds(100, 350, 250, 50);
        userHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                userHistory(admin.getIdBranch());
            }
        });
        JButton branchCash = new JButton("Cek Dana Perpustakaan");
        branchCash.setBounds(100, 450, 250, 50);
        branchCash.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().changeShowDanaCabang(admin.selectBranchCity(admin.getIdBranch()));
                printDanaSatuCabang(admin.getIdBranch());
            }
        });

        JButton exit = new JButton("Keluar");
        exit.setBounds(100, 550, 250, 50);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OutputInfo().exitFromOwnerMenu();
                System.exit(0);
            }

        });

        frame.add(approval);
        frame.add(bookListUpdate);
        frame.add(borrowingList);
        frame.add(userHistory);
        frame.add(branchCash);
        frame.add(exit);

        frame.setSize(500, 800);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void returnBorrow(int idBranch) {
        JFrame frame = new JFrame();
        TableCheckBoxBorrowing table;
        JScrollPane scroll;
        JPanel panel1 = new JPanel();

        String[] column = {"ID Pinjam", "Nama Peminjam", "ID User", "Judul Buku", "Lama Peminjaman", "Tanggal Pinjam", "Tanggal Kembali", "Denda", "Approval"};
        Object[][] data = new Object[new databaseChange().getAllBorrowList(idBranch, false).size()][9];
        for (int j = 0; j < new databaseChange().getAllBorrowList(idBranch, false).size(); j++) {
            Borrowing borrow = new databaseChange().getAllBorrowList(idBranch, false).get(j);
            Member member = new databaseChange().getAMember(borrow.getIdUser());
            data[j][0] = borrow.getIdBorrow();
            data[j][1] = member.getFirstName() + " " + member.getLastName();
            data[j][2] = member.getIdUser();
            data[j][3] = new databaseChange().getABook(borrow.getIdBook()).getTitle();
            data[j][4] = borrow.getBorrowDays();
            Format formatter = new SimpleDateFormat("dd/MM/yyyy");
            data[j][5] = formatter.format(borrow.getDate());
            data[j][6] = formatter.format(new Date());
            int diffDays = (int) ((new Date().getTime() - borrow.getDate().getTime()) / (24 * 60 * 60 * 1000));
            System.out.println(diffDays);
            if (diffDays <= borrow.getBorrowDays()) {
                data[j][7] = 0;
            } else {
                diffDays -= borrow.getBorrowDays();
                data[j][7] = diffDays * 2000;
            }
            data[j][8] = Boolean.FALSE;
        }
        DefaultTableModel model = new DefaultTableModel(data, column);
        table = new controller.TableCheckBoxBorrowing();
        table.setModel(model);
        table.setBounds(50, 50, 1100, 300);

        scroll = new JScrollPane(table);
        scroll.setBounds(50, 50, 1100, 300);

        panel1.add(scroll);
        panel1.setSize(1150, 1150);
        panel1.setLayout(null);
        panel1.setVisible(true);

        JButton button = new JButton("Approve");
        button.setBounds(50, 400, 1100, 30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tModel = (DefaultTableModel) table.getModel();
                for (int i = 0; i < tModel.getRowCount(); i++) {
                    Boolean checked = (Boolean) model.getValueAt(i, 8);
                    if (checked) {
                        new controller.databaseChange().updateBorrowing((int) model.getValueAt(i, 0), (int) model.getValueAt(i, 7));
                        if ((int) model.getValueAt(i, 7) != 0) {
                            new controller.databaseChange().updateUser((int) model.getValueAt(i, 2), (int) model.getValueAt(i, 7));
                        }
                    }
                }
                new OutputInfo().infoApprovePengembalian(true);
                frame.setVisible(false);
                adminMenu(new controller.databaseChange().getAdmin(1));
            }

        });
        JButton exit = new JButton("Keluar");
        exit.setBounds(50, 450, 1100, 30);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().exit();
                adminMenu(new controller.databaseChange().getAdmin(1));
            }

        });

        frame.add(button);
        frame.add(exit);
        frame.add(panel1);
        frame.setSize(1200, 600);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void bookListUpdate(int idBranch) {
        JFrame frame = new JFrame();

        JButton updateBook = new JButton("Update Data Buku");
        updateBook.setBounds(100, 50, 200, 50);
        updateBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().infoUpdateListBook(true);
                changeBookData(new controller.databaseChange().getABook(1));
            }
        });
        JButton addBook = new JButton("Tambah Buku Baru");
        addBook.setBounds(100, 150, 200, 50);
        addBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().infoUpdateListBook(false);
                addBookData(idBranch);
            }
        });
        JButton exit = new JButton("Back to Admin Menu");
        exit.setBounds(100, 250, 200, 50);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().exit();
                adminMenu(new controller.databaseChange().getAdmin(1));
            }

        });
        frame.add(exit);
        frame.add(updateBook);
        frame.add(addBook);
        frame.setSize(750, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void changeBookData(PaidBook book) {
        JFrame frame = new JFrame();
        JLabel paid = new JLabel("Harga Pinjam Buku : ");
        paid.setBounds(30, 450, 130, 30);
        paid.setVisible(false);
        JTextField inputPaid = new JTextField();
        inputPaid.setBounds(200, 450, 250, 30);
        inputPaid.setText(String.valueOf(book.getBorrowPrice()));
        inputPaid.setVisible(false);

        JLabel title = new JLabel("Judul : ");
        title.setBounds(30, 30, 130, 30);
        JLabel author = new JLabel("Penulis : ");
        author.setBounds(30, 90, 130, 30);
        JLabel publisher = new JLabel("Penerbit : ");
        publisher.setBounds(30, 150, 130, 30);
        JLabel genre = new JLabel("Genre : ");
        genre.setBounds(30, 210, 130, 30);
        JLabel pages = new JLabel("Jumlah Halaman : ");
        pages.setBounds(30, 270, 130, 30);
        JLabel year = new JLabel("Tahun Terbit : ");
        year.setBounds(30, 330, 130, 30);

        JTextField inputTitle = new JTextField();
        inputTitle.setBounds(200, 30, 250, 30);
        inputTitle.setText(book.getTitle());
        inputTitle.setEnabled(false);
        JTextField inputAuthor = new JTextField();
        inputAuthor.setBounds(200, 90, 250, 30);
        inputAuthor.setText(book.getAuthor());
        inputAuthor.setEnabled(false);
        JTextField inputPublisher = new JTextField();
        inputPublisher.setBounds(200, 150, 250, 30);
        inputPublisher.setText(book.getPublisher());
        inputPublisher.setEnabled(false);
        JTextField inputGenre = new JTextField();
        inputGenre.setBounds(200, 210, 250, 30);
        inputGenre.setText(book.getGenre());
        inputGenre.setEnabled(false);
        JTextField inputPages = new JTextField();
        inputPages.setBounds(200, 270, 250, 30);
        inputPages.setText(String.valueOf(book.getPages()));
        inputPages.setEnabled(false);
        JTextField inputYear = new JTextField();
        inputYear.setBounds(200, 330, 250, 30);
        inputYear.setText(String.valueOf(book.getYear()));
        inputYear.setEnabled(false);

        JCheckBox checkPaid = new JCheckBox("Buku berbayar");
        checkPaid.setBounds(30, 390, 150, 30);

        frame.add(title);
        frame.add(author);
        frame.add(publisher);
        frame.add(genre);
        frame.add(pages);
        frame.add(year);
        frame.add(checkPaid);
        frame.add(inputTitle);
        frame.add(inputAuthor);
        frame.add(inputPublisher);
        frame.add(inputGenre);
        frame.add(inputPages);
        frame.add(inputYear);

        checkPaid.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (checkPaid.isSelected()) {
                    paid.setVisible(true);
                    inputPaid.setVisible(true);
                } else {
                    paid.setVisible(false);
                    inputPaid.setVisible(false);
                }
            }
        });

        frame.add(paid);
        frame.add(inputPaid);
        JButton addBook = new JButton("Update");
        addBook.setBounds(30, 510, 150, 30);
        addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (book.getBorrowPrice() != Integer.parseInt(inputPaid.getText()) && Integer.parseInt(inputPaid.getText()) >= 0) {
                    if (!"".equals(inputPaid.getText())) {
                        book.setBorrowPrice(Integer.parseInt(inputPaid.getText()));
                    } else {
                        book.setBorrowPrice(0);
                    }
                    boolean state = new controller.databaseChange().updateBook(book);
                    if (state) {
                        new OutputInfo().successAddNewBook(book.getTitle(), state);
                        frame.setVisible(false);
                        adminMenu(new controller.databaseChange().getAdmin(1));
                    } else {
                        new OutputInfo().successAddNewBook(book.getTitle(), state);
                    }
                } else {
                    new OutputInfo().cantUpdateBecauseNoChange();
                }
            }
        });
        JButton exit = new JButton("Back");
        exit.setBounds(300, 510, 150, 30);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().exit();
                bookListUpdate(new controller.databaseChange().getAdmin(1).getIdBranch());
            }

        });
        frame.add(exit);
        frame.add(addBook);
        frame.setSize(550, 650);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void addBookData(int idBranch) {
        JFrame frame = new JFrame();
        JLabel paid = new JLabel("Harga Pinjam Buku : ");
        paid.setBounds(30, 540, 130, 30);
        paid.setVisible(false);
        JTextField inputPaid = new JTextField();
        inputPaid.setBounds(200, 540, 330, 30);
        inputPaid.setVisible(false);

        JLabel title = new JLabel("Judul : ");
        title.setBounds(30, 30, 130, 30);
        JLabel author = new JLabel("Penulis : ");
        author.setBounds(30, 80, 130, 30);
        JLabel publisher = new JLabel("Penerbit : ");
        publisher.setBounds(30, 130, 130, 30);
        JLabel genre = new JLabel("Genre : ");
        genre.setBounds(30, 180, 130, 30);
        JLabel pages = new JLabel("Jumlah Halaman : ");
        pages.setBounds(30, 390, 130, 30);
        JLabel year = new JLabel("Tahun Terbit : ");
        year.setBounds(30, 440, 130, 30);

        JTextField inputTitle = new JTextField();
        inputTitle.setBounds(200, 30, 330, 30);
        JTextField inputAuthor = new JTextField();
        inputAuthor.setBounds(200, 80, 330, 30);
        JTextField inputPublisher = new JTextField();
        inputPublisher.setBounds(200, 140, 330, 30);

        JRadioButton r1 = new JRadioButton("Horror");
        r1.setBounds(0, 0, 100, 30);
        JRadioButton r2 = new JRadioButton("Fantasi");
        r2.setBounds(0, 35, 100, 30);
        JRadioButton r3 = new JRadioButton("Sci-Fi");
        r3.setBounds(0, 70, 100, 30);
        JRadioButton r4 = new JRadioButton("Romantis");
        r4.setBounds(0, 105, 100, 30);
        JRadioButton r5 = new JRadioButton("Komedi");
        r5.setBounds(0, 140, 100, 30);
        JRadioButton r6 = new JRadioButton("Misteri");
        r6.setBounds(120, 0, 100, 30);
        JRadioButton r7 = new JRadioButton("Petualangan");
        r7.setBounds(120, 35, 100, 30);
        JRadioButton r8 = new JRadioButton("Biografi");
        r8.setBounds(120, 70, 100, 30);
        JRadioButton r9 = new JRadioButton("Ensiklopedia");
        r9.setBounds(120, 105, 100, 30);
        JRadioButton r10 = new JRadioButton("Jurnal");
        r10.setBounds(120, 140, 100, 30);
        JRadioButton r11 = new JRadioButton("Kamus");
        r11.setBounds(240, 0, 100, 30);
        JRadioButton r12 = new JRadioButton("Filsafat");
        r12.setBounds(240, 35, 100, 30);
        JRadioButton r13 = new JRadioButton("Sejarah");
        r13.setBounds(240, 70, 100, 30);
        JRadioButton r14 = new JRadioButton("Psikologi");
        r14.setBounds(240, 105, 100, 30);
        JRadioButton r15 = new JRadioButton("Lainnya");
        r15.setBounds(240, 140, 100, 30);

        JPanel panel = new JPanel();
        panel.add(r1);
        panel.add(r2);
        panel.add(r3);
        panel.add(r4);
        panel.add(r5);
        panel.add(r6);
        panel.add(r7);
        panel.add(r8);
        panel.add(r9);
        panel.add(r10);
        panel.add(r11);
        panel.add(r12);
        panel.add(r13);
        panel.add(r14);
        panel.add(r15);

        panel.setLayout(null);

        JPanel inputGenre = panel;
        inputGenre.setBounds(200, 180, 600, 200);
        JTextField inputPages = new JTextField();
        inputPages.setBounds(200, 390, 330, 30);
        JTextField inputYear = new JTextField();
        inputYear.setBounds(200, 440, 330, 30);

        JCheckBox checkPaid = new JCheckBox("Buku berbayar");
        checkPaid.setBounds(30, 490, 150, 30);

        frame.add(title);
        frame.add(author);
        frame.add(publisher);
        frame.add(genre);
        frame.add(pages);
        frame.add(year);
        frame.add(checkPaid);
        frame.add(inputTitle);
        frame.add(inputAuthor);
        frame.add(inputPublisher);
        frame.add(inputGenre);
        frame.add(inputPages);
        frame.add(inputYear);

        checkPaid.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (checkPaid.isSelected()) {
                    paid.setVisible(true);
                    inputPaid.setVisible(true);
                } else {
                    paid.setVisible(false);
                    inputPaid.setVisible(false);
                }
            }
        });

        frame.add(paid);
        frame.add(inputPaid);
        JButton addBook = new JButton("Add");
        addBook.setBounds(30, 600, 150, 30);
        addBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PaidBook book = new PaidBook();

                book.setTitle(inputTitle.getText());
                book.setAuthor(inputAuthor.getText());
                book.setPublisher(inputPublisher.getText());
                if (r1.isSelected()) {
                    book.setGenre("Horror");
                } else if (r2.isSelected()) {
                    book.setGenre("Fantasi");
                } else if (r3.isSelected()) {
                    book.setGenre("Sci-Fi");
                } else if (r4.isSelected()) {
                    book.setGenre("Romantis");
                } else if (r5.isSelected()) {
                    book.setGenre("Komedi");
                } else if (r6.isSelected()) {
                    book.setGenre("Misteri");
                } else if (r7.isSelected()) {
                    book.setGenre("Petualangan");
                } else if (r8.isSelected()) {
                    book.setGenre("Biografi");
                } else if (r9.isSelected()) {
                    book.setGenre("Ensiklopedia");
                } else if (r10.isSelected()) {
                    book.setGenre("Jurnal");
                } else if (r11.isSelected()) {
                    book.setGenre("Kamus");
                } else if (r12.isSelected()) {
                    book.setGenre("Filsafat");
                } else if (r13.isSelected()) {
                    book.setGenre("Sejarah");
                } else if (r14.isSelected()) {
                    book.setGenre("Psikologi");
                } else if (r15.isSelected()) {
                    book.setGenre("Lainnya");
                }
                if (!"".equals(inputTitle.getText()) && !"".equals(inputAuthor.getText()) && !"".equals(inputPublisher.getText()) && !"".equals(inputPages.getText()) && !"".equals(inputYear.getText()) && book.getGenre() != null) {

                    if (!"".equals(inputPaid.getText())) {
                        book.setBorrowPrice(Integer.parseInt(inputPaid.getText()));
                    } else {
                        book.setBorrowPrice(0);
                    }
                    book.setPages(Integer.parseInt(inputPages.getText()));
                    book.setYear(Integer.parseInt(inputYear.getText()));
                    book.setIdBranch(idBranch);

                    boolean state = new controller.databaseChange().insertNewBook(book);
                    if (state) {
                        new OutputInfo().successAddNewBook(book.getTitle(), state);
                        frame.setVisible(false);
                        adminMenu(new controller.databaseChange().getAdmin(1));
                    } else {
                        new OutputInfo().successAddNewBook(book.getTitle(), state);
                    }
                } else {
                    new OutputInfo().infoFormValidate();
                }
            }
        });
        JButton exit = new JButton("Back");
        exit.setBounds(380, 600, 150, 30);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().exit();
                bookListUpdate(new controller.databaseChange().getAdmin(1).getIdBranch());
            }

        });
        frame.add(exit);
        frame.add(addBook);
        frame.setSize(600, 800);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void printDanaSatuCabang(int idBranch) {
        JFrame f = new JFrame();
        JButton exit = new JButton("Exit");
        exit.setBounds(50, 470, 800, 20);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new OutputInfo().exit();
                adminMenu(new controller.databaseChange().getAdmin(1));
            }

        });
        f.add(exit);
        f.add(danaPerpus(idBranch));
        f.setSize(950, 660);
        f.setLayout(null);
        f.setVisible(true);

    }

    public JPanel danaPerpus(int idBranch) {
        ArrayList<Member> listMember = new controller.databaseChange().getAllMember(idBranch);
        int pendapatanByMemberRegister = 0;
        int pendapatanByBorrowing = 0;
        int pendapatanByMoneyFine = 0;
        String[] column = {"Keterangan", "Saldo", "Tanggal"};
        DefaultTableModel tableModel = new DefaultTableModel(column, 0);
        JTable table = new JTable(tableModel);
        for (int i = 0; i < listMember.size(); i++) {
            Object[] datum = new Object[3];
            datum[0] = "Pendaftaran dari " + listMember.get(i).getFirstName() + " " + listMember.get(i).getLastName();
            datum[1] = "(+) " + 50000;
            datum[2] = "-";
            tableModel = (DefaultTableModel) table.getModel();
            tableModel.addRow(datum);
            pendapatanByMemberRegister += 50000;
            for (int j = 0; j < new databaseChange().getAllBorrowList(listMember.get(i).getIdUser(), true).size(); j++) {
                Borrowing borrow = new databaseChange().getAllBorrowList(listMember.get(i).getIdUser(), true).get(j);
                if (borrow.getPriceTotal() != 0) {
                    datum = new Object[3];
                    datum[0] = "Peminjaman  buku " + new databaseChange().getABook(borrow.getIdBook()).getTitle() + " selama " + borrow.getBorrowDays() + " hari";
                    datum[1] = "(+)" + borrow.getPriceTotal();
                    datum[2] = borrow.getDate();
                    tableModel = (DefaultTableModel) table.getModel();
                    tableModel.addRow(datum);
                    pendapatanByBorrowing += borrow.getPriceTotal();
                }
                if (borrow.getMoneyFine() != 0) {
                    datum = new Object[3];
                    datum[0] = "Denda dari peminjaman buku " + new databaseChange().getABook(borrow.getIdBook()).getTitle();
                    datum[1] = "(+)" + borrow.getMoneyFine();
                    datum[2] = borrow.getDate();
                    tableModel = (DefaultTableModel) table.getModel();
                    tableModel.addRow(datum);
                    pendapatanByMoneyFine += borrow.getMoneyFine();
                }
            }
        }
        table.setBounds(50, 50, 800, 300);
        table.setEnabled(false);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(50, 50, 800, 300);

        JPanel panel = new JPanel();
        panel.add(sp);

        JLabel label1 = new JLabel("Pendapatan dari Pendaftaran : Rp " + pendapatanByMemberRegister);
        label1.setBounds(50, 370, 300, 20);
        JLabel label2 = new JLabel("Pendapatan dari Peminjaman Buku :  Rp " + pendapatanByBorrowing);
        label2.setBounds(50, 390, 300, 20);
        JLabel label3 = new JLabel("Pendapatan dari Denda Buku :  Rp " + pendapatanByMoneyFine);
        label2.setBounds(50, 410, 300, 20);
        JLabel label4 = new JLabel("Total Pendapatan : Rp " + (pendapatanByMemberRegister + pendapatanByBorrowing));
        label3.setBounds(50, 430, 300, 20);

        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.setSize(850, 450);
        panel.setLayout(null);
        panel.setVisible(true);
        return panel;
    }

    public JPanel getUserHistory(int idBranch) {
        int[] counterGenre = new int[15];
        JPanel panel = new JPanel();
        ArrayList<Member> listMember = new controller.databaseChange().getAllMember(idBranch);
        String[] column = {"Peminjam", "Buku", "Lama Peminjaman", "Harga Pinjam Buku", "Denda", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(column, 0);
        JTable table = new JTable(tableModel);
        for (int i = 0; i < listMember.size(); i++) {
            for (int j = 0; j < new databaseChange().getAllBorrowList(listMember.get(i).getIdUser(), true).size(); j++) {
                Object[] datum = new Object[6];
                Borrowing borrow = new databaseChange().getAllBorrowList(listMember.get(i).getIdUser(), true).get(j);
                datum[0] = new databaseChange().getAMember(borrow.getIdUser()).getFirstName() + " " + new databaseChange().getAMember(borrow.getIdUser()).getLastName();
                datum[1] = new databaseChange().getABook(borrow.getIdBook()).getTitle();
                datum[2] = borrow.getBorrowDays();
                datum[3] = borrow.getPriceTotal();
                datum[4] = borrow.getMoneyFine();
                datum[5] = borrow.selectBookState(borrow.getStatus());
                tableModel = (DefaultTableModel) table.getModel();
                tableModel.addRow(datum);
                counterGenre = genreCount(counterGenre, new databaseChange().getABook(borrow.getIdBook()).getGenre());
            }
        }
        table.setBounds(50, 50, 800, 300);
        table.setEnabled(false);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(50, 50, 800, 300);
        JLabel info = new JLabel("Banyak Peminjaman Berdasar Genre : ");
        info.setBounds(50, 380, 250, 30);
        JLabel horror = new JLabel("Horror : " + counterGenre[0]);
        horror.setBounds(50, 430, 140, 30);
        JLabel fantasi = new JLabel("Fantasi : " + counterGenre[1]);
        fantasi.setBounds(50, 460, 140, 30);
        JLabel sciFi = new JLabel("Sci-Fi : " + counterGenre[2]);
        sciFi.setBounds(50, 490, 140, 30);
        JLabel roman = new JLabel("Romantis : " + counterGenre[3]);
        roman.setBounds(210, 430, 140, 30);
        JLabel komedi = new JLabel("Komedi : " + counterGenre[4]);
        komedi.setBounds(210, 460, 140, 30);
        JLabel misteri = new JLabel("Misteri : " + counterGenre[5]);
        misteri.setBounds(210, 490, 140, 30);
        JLabel petualangan = new JLabel("Petualangan : " + counterGenre[6]);
        petualangan.setBounds(370, 430, 140, 30);
        JLabel biografi = new JLabel("Biografi : " + counterGenre[7]);
        biografi.setBounds(370, 460, 140, 30);
        JLabel ensiklopedia = new JLabel("Ensiklopedia : " + counterGenre[8]);
        ensiklopedia.setBounds(370, 490, 140, 30);
        JLabel jurnal = new JLabel("Jurnal : " + counterGenre[9]);
        jurnal.setBounds(530, 430, 140, 30);
        JLabel kamus = new JLabel("Kamus : " + counterGenre[10]);
        kamus.setBounds(530, 460, 140, 30);
        JLabel filsafat = new JLabel("Filsafat : " + counterGenre[11]);
        filsafat.setBounds(530, 590, 140, 30);
        JLabel sejarah = new JLabel("Sejarah : " + counterGenre[12]);
        sejarah.setBounds(690, 430, 140, 30);
        JLabel psikologi = new JLabel("Psikologi : " + counterGenre[13]);
        psikologi.setBounds(690, 460, 140, 30);
        JLabel others = new JLabel("Lainnya : " + counterGenre[14]);
        others.setBounds(690, 490, 140, 30);
        panel.add(horror);
        panel.add(fantasi);
        panel.add(sciFi);
        panel.add(roman);
        panel.add(komedi);
        panel.add(misteri);
        panel.add(petualangan);
        panel.add(biografi);
        panel.add(ensiklopedia);
        panel.add(jurnal);
        panel.add(kamus);
        panel.add(filsafat);
        panel.add(sejarah);
        panel.add(psikologi);
        panel.add(others);
        panel.add(sp);
        panel.add(info);
        panel.setSize(850, 520);
        panel.setLayout(null);
        panel.setVisible(true);
        return panel;
    }

    public void userHistory(int idBranch) {
        JFrame frame = new JFrame();
        frame.add(getUserHistory(idBranch));
        JButton exit = new JButton("Exit");
        exit.setBounds(50, 580, 800, 20);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().exit();
                adminMenu(new controller.databaseChange().getAdmin(1));
            }

        });
        frame.add(exit);
        frame.setSize(950, 700);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public int[] genreCount(int[] counterGenre, String genre) {

        switch (genre) {
            case "Horror":
                counterGenre[0]++;
                break;
            case "Fantasi":
                counterGenre[1]++;
                break;
            case "Sci-Fi":
                counterGenre[2]++;
                break;
            case "Romantis":
                counterGenre[3]++;
                break;
            case "Komedi":
                counterGenre[4]++;
                break;
            case "Misteri":
                counterGenre[5]++;
                break;
            case "Petualangan":
                counterGenre[6]++;
                break;
            case "Biografi":
                counterGenre[7]++;
                break;
            case "Ensiklopedia":
                counterGenre[8]++;
                break;
            case "Jurnal":
                counterGenre[9]++;
                break;
            case "Kamus":
                counterGenre[10]++;
                break;
            case "Filsafat":
                counterGenre[11]++;
                break;
            case "Sejarah":
                counterGenre[12]++;
                break;
            case "Psikologi":
                counterGenre[13]++;
                break;
            case "Lainnya":
                counterGenre[14]++;
                break;
            default:
                break;
        }
        return counterGenre;
    }
}
