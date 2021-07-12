package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Book;
import model.PaidBook;
import controller.databaseChange;
import java.awt.Color;
import model.Admin;
import model.UserManager;

public class UpdateSelectionBook {
    JFrame frame;
    public void updateBookData() {
        Admin admin = UserManager.getInstance().getAdmin();
        
        //declare components
        frame = new DefaultFrameSetting().defaultFrame();
        JPanel background = new DefaultFrameSetting().defaultPanel();
        JPanel panel = new DefaultFrameSetting().defaultPanel();
        JButton exit = new JButton("Back");
        JComboBox comboTitle = comboBooksTitle();
        JLabel label = new JLabel("Judul : ");
        
        //add components to panel
        panel.add(changeBookData(admin.getBooks().get(0), 0));
        
        //set components position
        comboTitle.setBounds(200,50,250,30);
        label.setBounds(30,50,150,30);
        panel.setBounds(0,30,600,650);
        exit.setBounds(30, 580, 430, 30);
        
        //components action listener
        comboTitle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                panel.setVisible(false);
                panel.add(changeBookData(admin.getBooks().get(comboTitle.getSelectedIndex()), comboTitle.getSelectedIndex()));
                panel.setVisible(true);
            }

        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().backToPreviousMenu();
                new ChangeListBook().bookListUpdate();
            }
        });
        
        //set background panel
        background.setSize(500,680);

        //add components to frame
        background.add(comboTitle);
        background.add(label);
        background.add(exit);
        background.add(panel);

        //add panel to frame
        frame.add(background);
        
        //set frame size
        frame.setSize(500,680);

    }

    public JPanel changeBookData(PaidBook book,int index) {
        //declare components
        JPanel panel = new DefaultFrameSetting().defaultPanel();
        JLabel author = new JLabel("Penulis : ");
        JLabel publisher = new JLabel("Penerbit : ");
        JLabel genre = new JLabel("Genre : ");
        JLabel pages = new JLabel("Jumlah Halaman : ");
        JLabel year = new JLabel("Tahun Terbit : ");
        JLabel paid = new JLabel("Harga Pinjam Buku : ");
        JTextField inputAuthor = new JTextField();
        JTextField inputPublisher = new JTextField();
        JTextField inputGenre = new JTextField();
        JTextField inputPages = new JTextField();
        JTextField inputYear = new JTextField();
        JTextField inputPaid = new JTextField();
        JCheckBox checkPaid = new JCheckBox("Buku berbayar");
        JButton addBook = new JButton("Update");

        //set components position
        author.setBounds(30, 80, 130, 30);
        publisher.setBounds(30, 140, 130, 30);
        genre.setBounds(30, 200, 130, 30);
        pages.setBounds(30, 260, 130, 30);
        year.setBounds(30, 320, 130, 30);
        paid.setBounds(30, 440, 130, 30);
        inputAuthor.setBounds(200, 80, 250, 30);
        inputPublisher.setBounds(200, 140, 250, 30);
        inputGenre.setBounds(200, 200, 250, 30);
        inputPages.setBounds(200, 260, 250, 30);
        inputYear.setBounds(200, 320, 250, 30);
        checkPaid.setBounds(30, 380, 150, 30);
        inputPaid.setBounds(200, 440, 250, 30);
        addBook.setBounds(30, 500, 430, 30);

        //set components
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
        inputPaid.setText(String.valueOf(((PaidBook) book).getBorrowPrice()));
        inputPaid.setVisible(false);
        
        //set component background
        checkPaid.setBackground(new Color(255, 234, 202));
        
        //button action listener
        checkPaid.addActionListener(new ActionListener() {
            @Override
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
                if (book.getBorrowPrice() != Integer.parseInt(inputPaid.getText()) && Integer.parseInt(inputPaid.getText()) >= 0) {
                    if (!"".equals(inputPaid.getText())) {
                        book.setBorrowPrice(Integer.parseInt(inputPaid.getText()));
                    } else {
                        book.setBorrowPrice(0);
                    }
                    boolean state = new databaseChange().updateBook(book);
                    if (state) {
                        book.setBorrowPrice(Integer.parseInt(inputPaid.getText()));
                        new OutputInfo().infoUpdateABook(book.getTitle(), state);
                        frame.setVisible(false);
                        new AdminMenu().adminMenu();
                    } else {
                        new OutputInfo().infoUpdateABook(book.getTitle(), state);
                    }
                } else {
                    new OutputInfo().cantUpdateBecauseNoChange();
                }
            }
        });


        //add components to frame
        panel.add(author);
        panel.add(publisher);
        panel.add(genre);
        panel.add(pages);
        panel.add(year);
        panel.add(checkPaid);
        panel.add(inputAuthor);
        panel.add(inputPublisher);
        panel.add(inputGenre);
        panel.add(inputPages);
        panel.add(inputYear);
        panel.add(paid);
        panel.add(inputPaid);
        panel.add(addBook);

        //set frame size
        panel.setSize(500, 650);
        return panel;
    }

    

    public JComboBox comboBooksTitle() {
        Admin admin = UserManager.getInstance().getAdmin();
        //create list book at a library
        String[] title = new String[admin.getBooks().size()];
        for (int i = 0; i < title.length; i++) {
            title[i] = ((Book) new controller.databaseChange().getABook(admin.getBooks().get(i).getIdBook())).getTitle();
        }
        
        //create combobox for list title
        JComboBox comboTitle = new JComboBox(title);
        
        return comboTitle;
    }
}
