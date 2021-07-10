package view;

import model.Admin;
import model.Book;
import model.PaidBook;
import model.UserManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeListBook {
    public void bookListUpdate() {
        //declare components
        JFrame frame = new DefaultFrameSetting().defaultFrame();
        JButton updateBook = new JButton("Update Data Buku");
        JButton addBook = new JButton("Tambah Buku Baru");
        JButton exit = new JButton("Back to Admin Menu");


        //set components position
        updateBook.setBounds(100, 50, 200, 50);
        addBook.setBounds(100, 150, 200, 50);
        exit.setBounds(100, 250, 200, 50);

        //buttone action listener
        updateBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().infoUpdateListBook(true);
                changeBookData((Book) new controller.databaseChange().getABook(1));
            }
        });
        addBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().infoUpdateListBook(false);
                addBookData();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().exit();
                new AdminMenu().adminMenu();
            }

        });

        //add components to frame
        frame.add(exit);
        frame.add(updateBook);
        frame.add(addBook);

        //set frame size
        frame.setSize(750, 500);
    }

    public void changeBookData(Book book) {
        //declare components
        JFrame frame = new DefaultFrameSetting().defaultFrame();
        JLabel title = new JLabel("Judul : ");
        JLabel author = new JLabel("Penulis : ");
        JLabel publisher = new JLabel("Penerbit : ");
        JLabel genre = new JLabel("Genre : ");
        JLabel pages = new JLabel("Jumlah Halaman : ");
        JLabel year = new JLabel("Tahun Terbit : ");
        JLabel paid = new JLabel("Harga Pinjam Buku : ");
        JTextField inputTitle = new JTextField();
        JTextField inputAuthor = new JTextField();
        JTextField inputPublisher = new JTextField();
        JTextField inputGenre = new JTextField();
        JTextField inputPages = new JTextField();
        JTextField inputYear = new JTextField();
        JTextField inputPaid = new JTextField();
        JCheckBox checkPaid = new JCheckBox("Buku berbayar");
        JButton addBook = new JButton("Update");
        JButton exit = new JButton("Back");

        //set components position
        title.setBounds(30, 30, 130, 30);
        author.setBounds(30, 90, 130, 30);
        publisher.setBounds(30, 150, 130, 30);
        genre.setBounds(30, 210, 130, 30);
        pages.setBounds(30, 270, 130, 30);
        year.setBounds(30, 330, 130, 30);
        paid.setBounds(30, 450, 130, 30);
        inputTitle.setBounds(200, 30, 250, 30);
        inputAuthor.setBounds(200, 90, 250, 30);
        inputPublisher.setBounds(200, 150, 250, 30);
        inputGenre.setBounds(200, 210, 250, 30);
        inputPages.setBounds(200, 270, 250, 30);
        inputYear.setBounds(200, 330, 250, 30);
        checkPaid.setBounds(30, 390, 150, 30);
        addBook.setBounds(30, 510, 150, 30);
        exit.setBounds(300, 510, 150, 30);
        inputPaid.setBounds(200, 450, 250, 30);

        //set components
        inputTitle.setText(book.getTitle());
        inputTitle.setEnabled(false);
        inputAuthor.setText(book.getAuthor());
        inputAuthor.setEnabled(false);
        inputPublisher.setText(book.getPublisher());
        inputPublisher.setEnabled(false);
        inputGenre.setText(book.getGenre());
        inputGenre.setEnabled(false);
        inputPages.setText(String.valueOf(book.getPages()));
        inputPages.setEnabled(false);
        inputYear.setText(String.valueOf(book.getYear()));
        inputYear.setEnabled(false);
        paid.setVisible(false);
        if (book instanceof PaidBook) {
            inputPaid.setText(String.valueOf(((PaidBook) book).getBorrowPrice()));
        } else {
            inputPaid.setText(String.valueOf(0));
        }
        inputPaid.setVisible(false);

        //button action listener
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
        addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((PaidBook)book).getBorrowPrice() != Integer.parseInt(inputPaid.getText()) && Integer.parseInt(inputPaid.getText()) >= 0) {
                    if (!"".equals(inputPaid.getText())) {
                        ((PaidBook) book).setBorrowPrice(Integer.parseInt(inputPaid.getText()));
                    } else {
                        ((PaidBook)book).setBorrowPrice(0);
                    }
                    boolean state = new controller.databaseChange().updateBook((PaidBook)book);
                    if (state) {
                        new OutputInfo().successAddNewBook(book.getTitle(), state);
                        frame.setVisible(false);
                        new AdminMenu().adminMenu();
                    } else {
                        new OutputInfo().successAddNewBook(book.getTitle(), state);
                    }
                } else {
                    new OutputInfo().cantUpdateBecauseNoChange();
                }
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().exit();
                bookListUpdate();
            }
        });

        //add components to frame
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
        frame.add(paid);
        frame.add(inputPaid);
        frame.add(exit);
        frame.add(addBook);

        //set frame size
        frame.setSize(550, 650);
    }

    public void addBookData() {
        Admin admin = UserManager.getInstance().getAdmin();
        int idBranch = admin.getIdBranch();

        //declare components
        JFrame frame = new DefaultFrameSetting().defaultFrame();
        JLabel paid = new JLabel("Harga Pinjam Buku : ");
        JTextField inputPaid = new JTextField();
        JLabel title = new JLabel("Judul : ");
        JLabel author = new JLabel("Penulis : ");
        JLabel publisher = new JLabel("Penerbit : ");
        JLabel genre = new JLabel("Genre : ");
        JLabel pages = new JLabel("Jumlah Halaman : ");
        JLabel year = new JLabel("Tahun Terbit : ");
        JTextField inputTitle = new JTextField();
        JTextField inputAuthor = new JTextField();
        JTextField inputPublisher = new JTextField();
        JRadioButton r1 = new JRadioButton("Horror");
        JRadioButton r2 = new JRadioButton("Fantasi");
        JRadioButton r3 = new JRadioButton("Sci-Fi");
        JRadioButton r4 = new JRadioButton("Romantis");
        JRadioButton r5 = new JRadioButton("Komedi");
        JRadioButton r6 = new JRadioButton("Misteri");
        JRadioButton r7 = new JRadioButton("Petualangan");
        JRadioButton r8 = new JRadioButton("Biografi");
        JRadioButton r9 = new JRadioButton("Ensiklopedia");
        JRadioButton r10 = new JRadioButton("Jurnal");
        JRadioButton r11 = new JRadioButton("Kamus");
        JRadioButton r12 = new JRadioButton("Filsafat");
        JRadioButton r13 = new JRadioButton("Sejarah");
        JRadioButton r14 = new JRadioButton("Psikologi");
        JRadioButton r15 = new JRadioButton("Lainnya");
        JPanel inputGenre = new DefaultFrameSetting().defaultPanel();
        JTextField inputPages = new JTextField();
        JTextField inputYear = new JTextField();
        JCheckBox checkPaid = new JCheckBox("Buku berbayar");
        JButton addBook = new JButton("Add");
        JButton exit = new JButton("Back");

        //set components position
        title.setBounds(30, 30, 130, 30);
        author.setBounds(30, 80, 130, 30);
        publisher.setBounds(30, 130, 130, 30);
        genre.setBounds(30, 180, 130, 30);
        pages.setBounds(30, 390, 130, 30);
        year.setBounds(30, 440, 130, 30);
        inputTitle.setBounds(200, 30, 330, 30);
        inputAuthor.setBounds(200, 80, 330, 30);
        inputPublisher.setBounds(200, 140, 330, 30);
        inputGenre.setBounds(200, 180, 600, 200);
        inputPages.setBounds(200, 390, 330, 30);
        inputYear.setBounds(200, 440, 330, 30);
        checkPaid.setBounds(30, 490, 150, 30);
        paid.setBounds(30, 540, 130, 30);
        inputPaid.setBounds(200, 540, 330, 30);
        r1.setBounds(0, 0, 100, 30);
        r2.setBounds(0, 35, 100, 30);
        r3.setBounds(0, 70, 100, 30);
        r4.setBounds(0, 105, 100, 30);
        r5.setBounds(0, 140, 100, 30);
        r6.setBounds(120, 0, 100, 30);
        r7.setBounds(120, 35, 100, 30);
        r8.setBounds(120, 70, 100, 30);
        r9.setBounds(120, 105, 100, 30);
        r10.setBounds(120, 140, 100, 30);
        r11.setBounds(240, 0, 100, 30);
        r12.setBounds(240, 35, 100, 30);
        r13.setBounds(240, 70, 100, 30);
        r14.setBounds(240, 105, 100, 30);
        r15.setBounds(240, 140, 100, 30);
        addBook.setBounds(30, 600, 150, 30);
        exit.setBounds(380, 600, 150, 30);

        //add radio button to panel
        inputGenre.add(r1);
        inputGenre.add(r2);
        inputGenre.add(r3);
        inputGenre.add(r4);
        inputGenre.add(r5);
        inputGenre.add(r6);
        inputGenre.add(r7);
        inputGenre.add(r8);
        inputGenre.add(r9);
        inputGenre.add(r10);
        inputGenre.add(r11);
        inputGenre.add(r12);
        inputGenre.add(r13);
        inputGenre.add(r14);
        inputGenre.add(r15);

        //components setting
        paid.setVisible(false);
        inputPaid.setVisible(false);

        //button actin listener
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
                        new AdminMenu().adminMenu();
                    } else {
                        new OutputInfo().successAddNewBook(book.getTitle(), state);
                    }
                } else {
                    new OutputInfo().infoFormValidate();
                }
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().exit();
                bookListUpdate();
            }
        });

        //add components to frame
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
        frame.add(paid);
        frame.add(inputPaid);
        frame.add(exit);
        frame.add(addBook);

        //set frame size
        frame.setSize(600, 800);
    }
}
