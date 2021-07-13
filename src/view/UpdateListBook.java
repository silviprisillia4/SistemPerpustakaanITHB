package view;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateListBook {
    
    public UpdateListBook() {
        //declare components
        JFrame frame = new DefaultFrameSetting().defaultFrame();
        JPanel background = new DefaultFrameSetting().defaultPanel();
        JButton updateBook = new JButton("Update Data Buku");
        JButton addBook = new JButton("Tambah Buku Baru");
        JButton exit = new JButton("Back to Admin Menu");

        //set components position
        updateBook.setBounds(50, 50, 200, 50);
        addBook.setBounds(50, 150, 200, 50);
        exit.setBounds(50, 250, 200, 50);

        //buttone action listener
        updateBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().infoUpdateListBook(true);
                new UpdateBookMenu();
            }
        });
        addBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().infoUpdateListBook(false);
                new AddABook();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().backToPreviousMenu();
                new AdminMenu();
            }

        });
        //set background panel
        background.setSize(330,380);

        //add components to frame
        background.add(exit);
        background.add(updateBook);
        background.add(addBook);

        //add panel to frame
        frame.add(background);
        
        //set frame size
        frame.setSize(330, 380);
        frame.setLocationRelativeTo(null);
    }
}
