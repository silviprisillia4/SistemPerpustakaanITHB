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
                new UpdateSelectionBook().updateBookData();
            }
        });
        addBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().infoUpdateListBook(false);
                new AddNewBook().addBookData();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().backToPreviousMenu();
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
}
