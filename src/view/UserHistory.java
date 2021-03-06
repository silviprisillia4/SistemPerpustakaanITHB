package view;
import controller.*;
import model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserHistory {
    
    Controller c = new Controller();
    JFrame frame;
    JPanel background, panel;
    JButton exit;
    DefaultTableModel tableModel;
    JTable table;
    JScrollPane sp;
    JLabel info, horror, fantasi, sciFi, roman, komedi, misteri, drama, biografi, ensiklopedia, pengetahuan, kamus, filsafat, sejarah, psikologi, others;
    
    public UserHistory() {
        showUserHistory();
    }
    
    public void showUserHistory() {        
        //declare components
        frame = new DefaultFrameSetting().defaultFrame();
        background = new DefaultFrameSetting().defaultPanel();
        exit = new JButton("Kembali");
        
        //set components position
        exit.setBounds(50, 580, 800, 20);
        
        //button action listener
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().backToPreviousMenu();
                new AdminMenu();
            }

        });
        
        //add components to panel
        background.add(getUserHistory());
        background.add(exit);
        
        //set background panel
        background.setSize(920,700);
        
        //add panel to frame
        frame.add(background);
        
        //frame set
        frame.setTitle("Perpustakaan ITHB - Riwayat Peminjaman User");
        
        //set frame size
        frame.setSize(920, 700);
        frame.setLocationRelativeTo(null);
    }

    public JPanel getUserHistory() {
        
        Admin admin = UserManager.getInstance().getAdmin();
        
        //declare components
        int[] counterGenre = new int[15];
        panel = new DefaultFrameSetting().defaultPanel();
        ArrayList<Member>listMember = admin.getMembers();
        String[] column = {"Peminjam", "Buku", "Lama Peminjaman", "Harga Pinjam Buku", "Denda", "Status"};
        tableModel = new DefaultTableModel(column, 0);
        table = new JTable(tableModel);
        sp = new JScrollPane();
        info = new JLabel("Banyak Peminjaman Berdasar Genre : ");
        horror = new JLabel();
        fantasi = new JLabel();
        sciFi = new JLabel();
        roman = new JLabel();
        komedi = new JLabel();
        misteri = new JLabel();
        drama = new JLabel();
        biografi = new JLabel();
        ensiklopedia = new JLabel();
        pengetahuan = new JLabel();
        kamus = new JLabel();
        filsafat = new JLabel();
        sejarah = new JLabel();
        psikologi = new JLabel();
        others = new JLabel();



        for (int i = 0; i < listMember.size(); i++) {
            for (int j = 0; j < listMember.get(i).getBorrows().size(); j++) {
                Object[] datum = new Object[6];
                Borrowing borrow = listMember.get(i).getBorrows().get(j);
                datum[0] = listMember.get(i).getFirstName() + " " + listMember.get(i).getLastName();
                datum[1] = borrow.getBook().getTitle();
                datum[2] = borrow.getBorrowDays();
                datum[3] = borrow.getPriceTotal();
                datum[4] = borrow.getMoneyFine();
                datum[5] = borrow.selectBookState(borrow.getStatus());
                tableModel = (DefaultTableModel) table.getModel();
                tableModel.addRow(datum);
                counterGenre = new controller.CounterGenre().genreCount(counterGenre, borrow.getBook().getGenre());
            }
        }

        //set components position
        table.setBounds(50, 50, 800, 300);
        sp.setBounds(50, 50, 800, 300);
        info.setBounds(50, 380, 250, 30);
        horror.setBounds(50, 430, 140, 30);
        fantasi.setBounds(50, 460, 140, 30);
        sciFi.setBounds(50, 490, 140, 30);
        roman.setBounds(210, 430, 140, 30);
        komedi.setBounds(210, 460, 140, 30);
        misteri.setBounds(210, 490, 140, 30);
        drama.setBounds(370, 430, 140, 30);
        biografi.setBounds(370, 460, 140, 30);
        ensiklopedia.setBounds(370, 490, 140, 30);
        pengetahuan.setBounds(530, 430, 140, 30);
        kamus.setBounds(530, 460, 140, 30);
        filsafat.setBounds(530, 490, 140, 30);
        sejarah.setBounds(690, 430, 140, 30);
        psikologi.setBounds(690, 460, 140, 30);
        others.setBounds(690, 490, 140, 30);

        //set components
        table.setEnabled(false);
        sp.setViewportView(table);

        //set label text
        horror.setText("Horror : " + counterGenre[0]);
        fantasi.setText("Fantasi : " + counterGenre[1]);
        sciFi.setText("Sci-Fi : " + counterGenre[2]);
        roman.setText("Romantis : " + counterGenre[3]);
        komedi.setText("Komedi : " + counterGenre[4]);
        misteri.setText("Misteri : " + counterGenre[5]);
        drama.setText("Drama : " + counterGenre[6]);
        biografi.setText("Biografi : " + counterGenre[7]);
        ensiklopedia.setText("Ensiklopedia : " + counterGenre[8]);
        pengetahuan.setText("Pengetahuan : " + counterGenre[9]);
        kamus.setText("Kamus : " + counterGenre[10]);
        filsafat.setText("Filsafat : " + counterGenre[11]);
        sejarah.setText("Sejarah : " + counterGenre[12]);
        psikologi.setText("Psikologi : " + counterGenre[13]);
        others.setText("Lainnya : " + counterGenre[14]);

        //add components to panel
        panel.add(horror);
        panel.add(fantasi);
        panel.add(sciFi);
        panel.add(roman);
        panel.add(komedi);
        panel.add(misteri);
        panel.add(drama);
        panel.add(biografi);
        panel.add(ensiklopedia);
        panel.add(pengetahuan);
        panel.add(kamus);
        panel.add(filsafat);
        panel.add(sejarah);
        panel.add(psikologi);
        panel.add(others);
        panel.add(sp);
        panel.add(info);

        //set panel size
        panel.setSize(850, 520);
        return panel;
    }

    
}
