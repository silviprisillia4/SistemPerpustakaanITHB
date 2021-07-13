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
    
    public UserHistory() {
        showUserHistory();
    }
    
    public void showUserHistory() {        
        //declare components
        JFrame frame = new DefaultFrameSetting().defaultFrame();
        JPanel background = new DefaultFrameSetting().defaultPanel();
        JButton exit = new JButton("Exit");
        
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
        
        //set frame size
        frame.setSize(920, 700);
        frame.setLocationRelativeTo(null);
    }

    public JPanel getUserHistory() {
        
        Admin admin = UserManager.getInstance().getAdmin();
        
        //declare components
        int[] counterGenre = new int[15];
        JPanel panel = new DefaultFrameSetting().defaultPanel();
        ArrayList<Member> listMember = admin.getMembers();
        String[] column = {"Peminjam", "Buku", "Lama Peminjaman", "Harga Pinjam Buku", "Denda", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(column, 0);
        JTable table = new JTable(tableModel);
        JScrollPane sp = new JScrollPane();
        JLabel info = new JLabel("Banyak Peminjaman Berdasar Genre : ");
        JLabel horror = new JLabel();
        JLabel fantasi = new JLabel();
        JLabel sciFi = new JLabel();
        JLabel roman = new JLabel();
        JLabel komedi = new JLabel();
        JLabel misteri = new JLabel();
        JLabel petualangan = new JLabel();
        JLabel biografi = new JLabel();
        JLabel ensiklopedia = new JLabel();
        JLabel jurnal = new JLabel();
        JLabel kamus = new JLabel();
        JLabel filsafat = new JLabel();
        JLabel sejarah = new JLabel();
        JLabel psikologi = new JLabel();
        JLabel others = new JLabel();



        for (int i = 0; i < listMember.size(); i++) {
            for (int j = 0; j < c.getAllBorrowList(listMember.get(i).getIdUser(), 0).size(); j++) {
                Object[] datum = new Object[6];
                Borrowing borrow = c.getAllBorrowList(listMember.get(i).getIdUser(), 0).get(j);
                datum[0] = listMember.get(i).getFirstName() + " " + listMember.get(i).getLastName();
                datum[1] = borrow.getBook().getTitle();
                datum[2] = borrow.getBorrowDays();
                datum[3] = borrow.getPriceTotal();
                datum[4] = borrow.getMoneyFine();
                datum[5] = borrow.selectBookState(borrow.getStatus());
                tableModel = (DefaultTableModel) table.getModel();
                tableModel.addRow(datum);
                counterGenre = genreCount(counterGenre, borrow.getBook().getGenre());
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
        petualangan.setBounds(370, 430, 140, 30);
        biografi.setBounds(370, 460, 140, 30);
        ensiklopedia.setBounds(370, 490, 140, 30);
        jurnal.setBounds(530, 430, 140, 30);
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
        petualangan.setText("Petualangan : " + counterGenre[6]);
        biografi.setText("Biografi : " + counterGenre[7]);
        ensiklopedia.setText("Ensiklopedia : " + counterGenre[8]);
        jurnal.setText("Jurnal : " + counterGenre[9]);
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

        //set panel size
        panel.setSize(850, 520);
        return panel;
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
            case "Drama":
                counterGenre[6]++;
                break;
            case "Biografi":
                counterGenre[7]++;
                break;
            case "Ensiklopedia":
                counterGenre[8]++;
                break;
            case "Pengetahuan":
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
