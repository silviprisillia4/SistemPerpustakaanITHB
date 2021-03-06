package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import model.*;
import controller.*;

public class AddABook {
    JFrame frame;
    JPanel background, inputGenre;
    JLabel paid;
    JTextField inputPaid, inputYear;
    JLabel title, author, publisher, genre, pages, year;
    JTextField inputTitle, inputAuthor, inputPublisher, inputPages;
    JRadioButton r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15;
    JCheckBox checkPaid;
    JButton addBook, exit;
    
    public AddABook() {
        addNewBookData();
    }

    public void addNewBookData() {
        Admin admin = UserManager.getInstance().getAdmin();

        //declare components
        frame = new DefaultFrameSetting().defaultFrame();
        background = new DefaultFrameSetting().defaultPanel();
        paid = new JLabel("Harga Pinjam Buku : ");
        inputPaid = new JTextField();
        title = new JLabel("Judul : ");
        author = new JLabel("Penulis : ");
        publisher = new JLabel("Penerbit : ");
        genre = new JLabel("Genre : ");
        pages = new JLabel("Jumlah Halaman : ");
        year = new JLabel("Tahun Terbit : ");
        inputTitle = new JTextField();
        inputAuthor = new JTextField();
        inputPublisher = new JTextField();
        r1 = new JRadioButton("Horror");
        r2 = new JRadioButton("Fantasi");
        r3 = new JRadioButton("Sci-Fi");
        r4 = new JRadioButton("Romantis");
        r5 = new JRadioButton("Komedi");
        r6 = new JRadioButton("Misteri");
        r7 = new JRadioButton("Drama");
        r8 = new JRadioButton("Biografi");
        r9 = new JRadioButton("Ensiklopedia");
        r10 = new JRadioButton("Pengetahuan");
        r11 = new JRadioButton("Kamus");
        r12 = new JRadioButton("Filsafat");
        r13 = new JRadioButton("Sejarah");
        r14 = new JRadioButton("Psikologi");
        r15 = new JRadioButton("Lainnya");
        inputGenre = new DefaultFrameSetting().defaultPanel();
        inputPages = new JTextField();
        inputYear = new JTextField();
        checkPaid = new JCheckBox("Buku berbayar");
        addBook = new JButton("Add");
        exit = new JButton("Kembali");

        //set components position
        title.setBounds(30, 30, 130, 30);
        author.setBounds(30, 80, 130, 30);
        publisher.setBounds(30, 130, 130, 30);
        genre.setBounds(30, 180, 130, 30);
        pages.setBounds(30, 390, 130, 30);
        year.setBounds(30, 440, 130, 30);
        inputTitle.setBounds(200, 30, 330, 30);
        inputAuthor.setBounds(200, 80, 330, 30);
        inputPublisher.setBounds(200, 130, 330, 30);
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

        //set components background color
        r1.setBackground(new Color(255, 234, 202));
        r2.setBackground(new Color(255, 234, 202));
        r3.setBackground(new Color(255, 234, 202));
        r4.setBackground(new Color(255, 234, 202));
        r5.setBackground(new Color(255, 234, 202));
        r6.setBackground(new Color(255, 234, 202));
        r7.setBackground(new Color(255, 234, 202));
        r8.setBackground(new Color(255, 234, 202));
        r9.setBackground(new Color(255, 234, 202));
        r10.setBackground(new Color(255, 234, 202));
        r11.setBackground(new Color(255, 234, 202));
        r12.setBackground(new Color(255, 234, 202));
        r13.setBackground(new Color(255, 234, 202));
        r14.setBackground(new Color(255, 234, 202));
        r15.setBackground(new Color(255, 234, 202));
        checkPaid.setBackground(new Color(255, 234, 202));

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
                    inputPaid.setText("");
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
                    book.setGenre("Drama");
                } else if (r8.isSelected()) {
                    book.setGenre("Biografi");
                } else if (r9.isSelected()) {
                    book.setGenre("Ensiklopedia");
                } else if (r10.isSelected()) {
                    book.setGenre("Pengetahuan");
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
                    if (checkPaid.isSelected() && !"".equals(inputPaid.getText())) {
                        if (!"".equals(inputPaid.getText())) {
                            book.setBorrowPrice(Integer.parseInt(inputPaid.getText()));
                        } else {
                            book.setBorrowPrice(0);
                        }
                        book.setPages(Integer.parseInt(inputPages.getText()));
                        book.setYear(Integer.parseInt(inputYear.getText()));
                        book.setIdBranch(admin.getIdBranch());
                        book.setStatus(1);

                        Controller c = new Controller();
                        boolean state = c.insertNewBook(book);
                        if (state) {
                            ArrayList<PaidBook> books = c.getAllBooks(admin.getIdBranch());
                            admin.getBooks().add(books.get(books.size() - 1));
                            new OutputInfo().successAddNewBook(book.getTitle(), state);
                            frame.setVisible(false);
                            new AdminMenu();
                        } else {
                            new OutputInfo().successAddNewBook(book.getTitle(), state);
                        }
                    } else if ((!checkPaid.isSelected())) {
                        book.setBorrowPrice(0);
                        book.setPages(Integer.parseInt(inputPages.getText()));
                        book.setYear(Integer.parseInt(inputYear.getText()));
                        book.setIdBranch(admin.getIdBranch());
                        book.setStatus(1);

                        Controller c = new Controller();
                        boolean state = c.insertNewBook(book);
                        if (state) {
                            ArrayList<PaidBook> books = c.getAllBooks(admin.getIdBranch());
                            admin.getBooks().add(books.get(books.size() - 1));
                            new OutputInfo().successAddNewBook(book.getTitle(), state);
                            frame.setVisible(false);
                            new AdminMenu();
                        } else {
                            new OutputInfo().successAddNewBook(book.getTitle(), state);
                        }
                    } else {
                        new ErrorMessages().showErrorFormValidate();
                    }
                } else {
                    new ErrorMessages().showErrorFormValidate();
                }
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().backToPreviousMenu();
                new UpdateListBook();
            }
        });
        //set background panel
        background.setSize(600, 700);

        //add components to panel
        background.add(title);
        background.add(author);
        background.add(publisher);
        background.add(genre);
        background.add(pages);
        background.add(year);
        background.add(checkPaid);
        background.add(inputTitle);
        background.add(inputAuthor);
        background.add(inputPublisher);
        background.add(inputGenre);
        background.add(inputPages);
        background.add(inputYear);
        background.add(paid);
        background.add(inputPaid);
        background.add(exit);
        background.add(addBook);

        //add panel to frame
        frame.add(background);

        //frame set
        frame.setTitle("Perpustakaan ITHB - Tambah Buku Baru");

        //set frame size
        frame.setSize(600, 700);
        frame.setLocationRelativeTo(null);
    }
}
