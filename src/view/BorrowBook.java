package view;
import controller.*;
import model.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class BorrowBook {
    JFrame frame;
    JPanel panel1;
    JTable table;
    DefaultTableModel model;
    JScrollPane sp;
    JLabel labelBorrowDays, labelSearch;
    JTextField textFieldSearch;
    JSpinner spinner;
    JButton btnBorrow, btnAscending, btnDescending, btnBack, btnTitle, btnAuthor, btnGenre, btnYear;
    ArrayList<Integer> listCheckedBook = new ArrayList<>();
    ArrayList<PaidBook> booksByTitle = new ArrayList<>();
    ArrayList<PaidBook> booksByAuthor = new ArrayList<>();
    ArrayList<PaidBook> booksByGenre = new ArrayList<>();
    ArrayList<PaidBook> booksByYear = new ArrayList<>();
    boolean normal = true;
    boolean ascending, descending, title, author, genre, year = false;
    
    public BorrowBook() {
        Member member = UserManager.getInstance().getMember();

        //Frame
        frame = new DefaultFrameSetting().defaultFrame();
        frame.setSize(800, 530);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Perpustakaan ITHB - Pinjam Buku");
        
        //Panel
        panel1 = new DefaultFrameSetting().defaultPanel();
        panel1.setSize(800, 530);
        panel1.setBackground(new Color(255, 234, 202));
        panel1.setVisible(true);
        
        //ArrayList
        Controller c = new Controller();
        ArrayList<PaidBook> books = c.getAllBooks(member.getIdBranch());
        ArrayList<PaidBook> booksAscending = c.getAllBooksOrdered(member.getIdBranch(), "ASC");
        ArrayList<PaidBook> booksDescending = c.getAllBooksOrdered(member.getIdBranch(), "DESC");
        
        //Table
        model = new DefaultTableModel() {
            @Override
            public Class getColumnClass(int columnIndex) {
                switch(columnIndex) {
                    case 0 :
                    case 5 :
                    case 6 :
                    case 7 :
                        return Integer.class;
                    case 8 :
                        return Boolean.class;
                    default :
                        return String.class;
                }
            }
        };
        model.addColumn("ID");
        model.addColumn("Judul");
        model.addColumn("Penulis");
        model.addColumn("Genre");
        model.addColumn("Penerbit");
        model.addColumn("Hlm");
        model.addColumn("Tahun");
        model.addColumn("Harga");
        model.addColumn("+");
        table = new JTable(model);
        
        //Looping Data to Table
        for (int i=0; i<books.size(); i++) {
            PaidBook current = books.get(i);
            Object[] addBooks = new Object[9];
            addBooks[0] = current.getIdBook();
            addBooks[1] = current.getTitle();
            addBooks[2] = current.getAuthor();
            addBooks[3] = current.getGenre();
            addBooks[4] = current.getPublisher();
            addBooks[5] = current.getPages();
            addBooks[6] = current.getYear();
            addBooks[7] = current.getBorrowPrice();
            addBooks[8] = false;
            model = (DefaultTableModel)table.getModel();
            model.addRow(addBooks);
        }

        //Set Column Size
        table.getColumnModel().getColumn(0).setPreferredWidth(2);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(60);
        table.getColumnModel().getColumn(3).setPreferredWidth(60);
        table.getColumnModel().getColumn(4).setPreferredWidth(60);
        table.getColumnModel().getColumn(5).setPreferredWidth(5);
        table.getColumnModel().getColumn(6).setPreferredWidth(5);
        table.getColumnModel().getColumn(7).setPreferredWidth(10);
        table.getColumnModel().getColumn(8).setPreferredWidth(2);
        
        table.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                boolean added = false;
                for(int i=0;i<table.getModel().getRowCount();i++) {
                    if ((Boolean) table.getModel().getValueAt(i, 8)) {
                        if(normal) {
                            listCheckedBook.add(books.get(table.getSelectedRow()).getIdBook());
                        } else if(ascending) {
                            listCheckedBook.add(booksAscending.get(table.getSelectedRow()).getIdBook());
                        } else if(descending) {
                            listCheckedBook.add(booksDescending.get(table.getSelectedRow()).getIdBook());
                        } else if(title) {
                            listCheckedBook.add(booksByTitle.get(table.getSelectedRow()).getIdBook());
                        } else if(author) {
                            listCheckedBook.add(booksByAuthor.get(table.getSelectedRow()).getIdBook());
                        } else if(genre) {
                            listCheckedBook.add(booksByGenre.get(table.getSelectedRow()).getIdBook());
                        } else {
                            listCheckedBook.add(booksByYear.get(table.getSelectedRow()).getIdBook());
                        }
                        added = true;
                    }
                    if(added) {
                        break;
                    }
                }     
            }
        });
        
        table.setBounds(20, 20, 750, 300);
        sp = new JScrollPane(table);
        sp.setBounds(20, 20, 750, 300);
        
        //Text Field
        textFieldSearch = new JTextField("");
        textFieldSearch.setBounds(570, 330, 200, 20);
        
        //Label
        labelBorrowDays = new JLabel("Lama pinjam :                             hari");
        labelBorrowDays.setBounds(20, 330, 200, 20);
        labelSearch = new JLabel("Filter :");
        labelSearch.setBounds(520, 330, 40, 20);
        
        //Spinner
        SpinnerModel value = new SpinnerNumberModel(1, 1, 7, 1);
        spinner = new JSpinner(value);   
        spinner.setBounds(120, 330, 50, 20);      
        
        //Button
        btnBorrow = new JButton("Pinjam");
        btnBorrow.setBounds(20, 400, 750, 30);
        btnBorrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String allBooks = "";
                if(listCheckedBook.size()!=0) {
                    for (int i=0; i<listCheckedBook.size(); i++) {
                        allBooks += "- "+c.getTitleSelectedBook(listCheckedBook.get(i))+"\n";
                    }
                    int borrowDays = (Integer) spinner.getValue();
                    int confirm = JOptionPane.showConfirmDialog(null, "Buku yang anda pinjam adalah :\n"+allBooks+"\nWaktu peminjaman : "+borrowDays+" hari\n\nYakin pinjam?", "Perpustakaan ITHB", JOptionPane.YES_NO_OPTION);
                    if(confirm==JOptionPane.YES_OPTION) {
                        boolean success = false;
                        for (int i=0; i<listCheckedBook.size(); i++) {
                            PaidBook book = c.getABook(listCheckedBook.get(i));
                            Borrowing borrowing = new Borrowing(0, book.getIdBook(), member.getIdUser(), member.getIdBranch(), borrowDays, book.getBorrowPrice(), new Date(), 2, 0, book);
                            success = c.insertBorrowing(borrowing) && c.updateBookStatusAfterBorrowed(book.getIdBook());
                        }
                        if(success) {
                            new OutputInfo().infoBorrowed();
                            frame.setVisible(false);
                            new MemberMenu();
                        } else {
                            new ErrorMessages().showError();
                        }
                    }
                } else {
                    new ErrorMessages().showErrorNoSelectedBook();
                }
            }
        });
        
        btnAscending = new JButton("Ascending");
        btnAscending.setBounds(20, 360, 150, 30);
        btnAscending.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                panel1.remove(sp);
                listCheckedBook.clear();
                ascending = true;
                normal = false;
                descending = false;
                title = false;
                author = false;
                genre = false;
                year = false;
                for (int i=0; i<booksAscending.size(); i++) {
                    PaidBook current = booksAscending.get(i);
                    Object[] addBooks = new Object[9];
                    addBooks[0] = current.getIdBook();
                    addBooks[1] = current.getTitle();
                    addBooks[2] = current.getAuthor();
                    addBooks[3] = current.getGenre();
                    addBooks[4] = current.getPublisher();
                    addBooks[5] = current.getPages();
                    addBooks[6] = current.getYear();
                    addBooks[7] = current.getBorrowPrice();
                    addBooks[8] = false;
                    model = (DefaultTableModel)table.getModel();
                    model.addRow(addBooks);
                }
                table.setBounds(20, 20, 750, 300);
                sp = new JScrollPane(table);
                sp.setBounds(20, 20, 750, 300);
                panel1.add(sp);
            }
        });
        
        btnDescending = new JButton("Descending");
        btnDescending.setBounds(180, 360, 150, 30);
        btnDescending.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                panel1.remove(sp);
                listCheckedBook.clear();
                descending = true;
                normal = false;
                ascending = false;
                title = false;
                author = false;
                genre = false;
                year = false;
                for (int i=0; i<booksDescending.size(); i++) {
                    PaidBook current = booksDescending.get(i);
                    Object[] addBooks = new Object[9];
                    addBooks[0] = current.getIdBook();
                    addBooks[1] = current.getTitle();
                    addBooks[2] = current.getAuthor();
                    addBooks[3] = current.getGenre();
                    addBooks[4] = current.getPublisher();
                    addBooks[5] = current.getPages();
                    addBooks[6] = current.getYear();
                    addBooks[7] = current.getBorrowPrice();
                    addBooks[8] = false;
                    model = (DefaultTableModel)table.getModel();
                    model.addRow(addBooks);
                }
                table.setBounds(20, 20, 750, 300);
                sp = new JScrollPane(table);
                sp.setBounds(20, 20, 750, 300);
                panel1.add(sp);
            } 
        });
        
        btnBack = new JButton("Kembali");
        btnBack.setBounds(20, 440, 750, 30);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().backToPreviousMenu();
                new MemberMenu();
            }
        });
        
        btnYear = new JButton("Tahun");
        btnYear.setBounds(690, 360, 80, 30);
        btnYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                panel1.remove(sp);
                listCheckedBook.clear();
                year = true;
                normal = false;
                ascending = false;
                descending = false;
                title = false;
                author = false;
                genre = false;
                booksByYear = c.getAllBooksByFilter(member.getIdBranch(), "year", textFieldSearch.getText());
                for (int i=0; i<booksByYear.size(); i++) {
                    PaidBook current = booksByYear.get(i);
                    Object[] addBooks = new Object[9];
                    addBooks[0] = current.getIdBook();
                    addBooks[1] = current.getTitle();
                    addBooks[2] = current.getAuthor();
                    addBooks[3] = current.getGenre();
                    addBooks[4] = current.getPublisher();
                    addBooks[5] = current.getPages();
                    addBooks[6] = current.getYear();
                    addBooks[7] = current.getBorrowPrice();
                    addBooks[8] = false;
                    model = (DefaultTableModel)table.getModel();
                    model.addRow(addBooks);
                }
                table.setBounds(20, 20, 750, 300);
                sp = new JScrollPane(table);
                sp.setBounds(20, 20, 750, 300);
                panel1.add(sp);
            }
        });
        
        btnGenre = new JButton("Genre");
        btnGenre.setBounds(600, 360, 80, 30);
        btnGenre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                panel1.remove(sp);
                listCheckedBook.clear();
                genre = true;
                normal = false;
                ascending = false;
                descending = false;
                title = false;
                author = false;
                year = false;
                booksByGenre = c.getAllBooksByFilter(member.getIdBranch(), "genre", textFieldSearch.getText());
                for (int i=0; i<booksByGenre.size(); i++) {
                    PaidBook current = booksByGenre.get(i);
                    Object[] addBooks = new Object[9];
                    addBooks[0] = current.getIdBook();
                    addBooks[1] = current.getTitle();
                    addBooks[2] = current.getAuthor();
                    addBooks[3] = current.getGenre();
                    addBooks[4] = current.getPublisher();
                    addBooks[5] = current.getPages();
                    addBooks[6] = current.getYear();
                    addBooks[7] = current.getBorrowPrice();
                    addBooks[8] = false;
                    model = (DefaultTableModel)table.getModel();
                    model.addRow(addBooks);
                }
                table.setBounds(20, 20, 750, 300);
                sp = new JScrollPane(table);
                sp.setBounds(20, 20, 750, 300);
                panel1.add(sp);
            }
        });
        
        btnAuthor = new JButton("Penulis");
        btnAuthor.setBounds(510, 360, 80, 30);
        btnAuthor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                panel1.remove(sp);
                listCheckedBook.clear();
                author = true;
                normal = false;
                ascending = false;
                descending = false;
                title = false;
                genre = false;
                year = false;
                booksByAuthor = c.getAllBooksByFilter(member.getIdBranch(), "author", textFieldSearch.getText());
                for (int i=0; i<booksByAuthor.size(); i++) {
                    PaidBook current = booksByAuthor.get(i);
                    Object[] addBooks = new Object[9];
                    addBooks[0] = current.getIdBook();
                    addBooks[1] = current.getTitle();
                    addBooks[2] = current.getAuthor();
                    addBooks[3] = current.getGenre();
                    addBooks[4] = current.getPublisher();
                    addBooks[5] = current.getPages();
                    addBooks[6] = current.getYear();
                    addBooks[7] = current.getBorrowPrice();
                    addBooks[8] = false;
                    model = (DefaultTableModel)table.getModel();
                    model.addRow(addBooks);
                }
                table.setBounds(20, 20, 750, 300);
                sp = new JScrollPane(table);
                sp.setBounds(20, 20, 750, 300);
                panel1.add(sp);
            }
        });
        
        btnTitle = new JButton("Judul");
        btnTitle.setBounds(420, 360, 80, 30);
        btnTitle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                panel1.remove(sp);
                listCheckedBook.clear();
                title = true;
                normal = false;
                ascending = false;
                descending = false;
                author = false;
                genre = false;
                year = false;
                booksByTitle = c.getAllBooksByFilter(member.getIdBranch(), "title", textFieldSearch.getText());
                for (int i=0; i<booksByTitle.size(); i++) {
                    PaidBook current = booksByTitle.get(i);
                    Object[] addBooks = new Object[9];
                    addBooks[0] = current.getIdBook();
                    addBooks[1] = current.getTitle();
                    addBooks[2] = current.getAuthor();
                    addBooks[3] = current.getGenre();
                    addBooks[4] = current.getPublisher();
                    addBooks[5] = current.getPages();
                    addBooks[6] = current.getYear();
                    addBooks[7] = current.getBorrowPrice();
                    addBooks[8] = false;
                    model = (DefaultTableModel)table.getModel();
                    model.addRow(addBooks);
                }
                table.setBounds(20, 20, 750, 300);
                sp = new JScrollPane(table);
                sp.setBounds(20, 20, 750, 300);
                panel1.add(sp);
            }
        });
        
        //Add
        panel1.add(sp);
        panel1.add(textFieldSearch);
        panel1.add(labelBorrowDays);
        panel1.add(labelSearch);
        panel1.add(spinner);
        panel1.add(btnBorrow);
        panel1.add(btnAscending);
        panel1.add(btnDescending);
        panel1.add(btnBack);
        panel1.add(btnTitle);
        panel1.add(btnAuthor);
        panel1.add(btnGenre);
        panel1.add(btnYear);
        frame.add(panel1);
    }
    
}
