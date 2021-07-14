package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.*;
import controller.*;
import java.awt.Color;

public class UpdateBookMenu {

    Controller c = new Controller();
    JFrame frame;
    JPanel panel;

    public UpdateBookMenu() {
        updateABook();
    }

    public void updateABook() {
        Admin admin = UserManager.getInstance().getAdmin();

        //declare components
        frame = new DefaultFrameSetting().defaultFrame();
        JPanel background = new DefaultFrameSetting().defaultPanel();
        panel = new DefaultFrameSetting().defaultPanel();
        panel.setSize(500, 650);
        JButton exit = new JButton("Kembali");
        JComboBox comboTitle = comboBooksTitle();
        JLabel label = new JLabel("Judul : ");

        //add components to panel
        panel = changeBookData(admin.getBooks().get(0), 0);

        //set components position
        comboTitle.setBounds(200, 50, 250, 30);
        label.setBounds(30, 50, 150, 30);
        panel.setBounds(0, 30, 600, 650);
        exit.setBounds(30, 580, 430, 30);

        //components action listener
        comboTitle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                panel.setVisible(false);
                panel = changeBookData(admin.getBooks().get(comboTitle.getSelectedIndex()), comboTitle.getSelectedIndex());
                panel.setVisible(true);
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
        background.setSize(500, 680);

        //add components to frame
        background.add(comboTitle);
        background.add(label);
        background.add(exit);
        background.add(panel);

        //add panel to frame
        frame.add(background);

        //frame set
        frame.setTitle("Perpustakaan ITHB - Update Harga Buku");

        //set frame size
        frame.setSize(500, 680);
        frame.setLocationRelativeTo(null);

    }

    public JPanel changeBookData(PaidBook book, int index) {
        //declare components
        panel = book.printBookData(panel);
        JLabel paid = new JLabel("Harga Pinjam Buku : ");
        JTextField inputPaid = new JTextField();
        JCheckBox checkPaid = new JCheckBox("Buku berbayar");
        JButton addBook = new JButton("Update");
        
        //set component size
        paid.setBounds(30, 440, 130, 30);
        inputPaid.setBounds(200, 440, 250, 30);
        checkPaid.setBounds(30, 380, 150, 30);
        addBook.setBounds(30, 500, 430, 30);

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
                    checkPaid.setEnabled(false);
                    paid.setVisible(true);
                    inputPaid.setVisible(true);
                }
            }
        });
        addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!"".equals(inputPaid.getText())) {
                    if (book.getBorrowPrice() != Integer.parseInt(inputPaid.getText()) && Integer.parseInt(inputPaid.getText()) >= 0) {
                        book.setBorrowPrice(Integer.parseInt(inputPaid.getText()));
                        boolean state = c.updateBook(book);
                        if (state) {
                            book.setBorrowPrice(Integer.parseInt(inputPaid.getText()));
                            new OutputInfo().infoUpdateABook(book.getTitle(), state);
                            frame.setVisible(false);
                            new AdminMenu();
                        } else {
                            new OutputInfo().infoUpdateABook(book.getTitle(), state);
                        }
                    } else {
                        new ErrorMessages().showErrorCantUpdateBecauseNoChange();
                    }
                } else {
                    new ErrorMessages().showErrorEmptyDataInputPrice();
                }
            }
        });

        //add components
        panel.add(checkPaid);
        panel.add(paid);
        panel.add(inputPaid);
        panel.add(addBook);
        
        //set frame size
        return panel;
    }

    public JComboBox comboBooksTitle() {
        Admin admin = UserManager.getInstance().getAdmin();
        //create list book at a library
        String[] title = new String[admin.getBooks().size()];
        for (int i = 0; i < title.length; i++) {
            title[i] = ((Book) c.getABook(admin.getBooks().get(i).getIdBook())).getTitle();
        }

        //create combobox for list title
        JComboBox comboTitle = new JComboBox(title);

        return comboTitle;
    }
}
