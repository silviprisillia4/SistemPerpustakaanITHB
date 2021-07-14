package view;

import controller.*;
import model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApprovalBookReturn {
    JFrame frame;
    JPanel background, panel1;
    TableCheckBoxBorrowing table;
    DefaultTableModel model;
    JScrollPane scroll;
    JButton button, exit;
    
    Controller c = new Controller();

    public ApprovalBookReturn() {
        showBorrowingList();
    }

    public void showBorrowingList() {
        Admin admin = UserManager.getInstance().getAdmin();
        //check ada yang dipinjam atau ga

        if (c.getAllBorrowList(admin.getIdBranch(), 1).size() == 0) {
            new ErrorMessages().showErrorNoBorrowList();
            new AdminMenu();
        } else {
            //declare
            frame = new DefaultFrameSetting().defaultFrame();
            background = new DefaultFrameSetting().defaultPanel();
            panel1 = new DefaultFrameSetting().defaultPanel();
            table = new controller.TableCheckBoxBorrowing();
            model = new DefaultTableModel(getTableData(), getColumnData());
            scroll = new JScrollPane();
            button = new JButton("Approve");
            exit = new JButton("Kembali");

            //set position
            table.setBounds(50, 50, 1100, 300);
            scroll.setBounds(50, 50, 1100, 300);
            panel1.setSize(1150, 1150);
            button.setBounds(50, 400, 1100, 30);
            exit.setBounds(50, 450, 1100, 30);

            //set component
            table.setModel(model);
            scroll.setViewportView(table);

            //add component to panel
            panel1.add(scroll);

            //buttons action listener
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DefaultTableModel tModel = (DefaultTableModel) table.getModel();
                    int counterNotChecked = 0;
                    for (int i = 0; i < tModel.getRowCount(); i++) {
                        Boolean checked = (Boolean) model.getValueAt(i, 8);
                        if (checked) {
                            c.updateBorrowing((int) model.getValueAt(i, 0), (int) model.getValueAt(i, 7));
                            if ((int) model.getValueAt(i, 7) != 0) {
                                c.updateUserMoney((int) model.getValueAt(i, 2), (int) model.getValueAt(i, 7));
                            }
                        } else {
                            counterNotChecked++;
                        }
                    }
                    if (counterNotChecked == tModel.getRowCount()) {
                        new ErrorMessages().showErrorNoReturnSelected();
                    } else {
                        UserManager.getInstance().getAdmin().setMembers(c.getAllMembers(UserManager.getInstance().getAdmin().getIdBranch()));
                        new OutputInfo().infoApprovePengembalian(true);
                        frame.setVisible(false);
                        new AdminMenu();
                    }
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
            background.setSize(1200, 550);

            //add component frame
            background.add(button);
            background.add(exit);
            background.add(panel1);

            //add panel to frame
            frame.add(background);

            //set frame size
            frame.setSize(1200, 550);

            //frame set
            frame.setTitle("Perpustakaan ITHB - Pengembalian Pinjaman");

            //set frame to center
            frame.setLocationRelativeTo(null);
        }
    }

    public String[] getColumnData() {
        String[] column = {"ID Pinjam", "Nama Peminjam", "ID User", "Judul Buku", "Lama Peminjaman", "Tanggal Pinjam", "Tanggal Kembali", "Denda", "Approval"};
        return column;
    }

    public Object[][] getTableData() {
        Admin admin = UserManager.getInstance().getAdmin();
        Object[][] data = new Object[c.getAllBorrowList(admin.getIdBranch(), 1).size()][9];
        int user = 0;
        for (int i = 0; i < admin.getMembers().size(); i++) {
            for (int j = 0; j < admin.getMembers().get(i).getBorrows().size(); j++) {
                Borrowing borrow = admin.getMembers().get(i).getBorrows().get(j);
                if (borrow.getStatus() == 0) {
                    Member member = c.getAMember(borrow.getIdUser());
                    data[user][0] = borrow.getIdBorrow();
                    data[user][1] = member.getFirstName() + " " + member.getLastName();
                    data[user][2] = member.getIdUser();
                    data[user][3] = ((PaidBook) c.getABook(borrow.getIdBook())).getTitle();
                    data[user][4] = borrow.getBorrowDays();
                    Format formatter = new SimpleDateFormat("dd/MM/yyyy");
                    data[user][5] = formatter.format(borrow.getDate());
                    data[user][6] = formatter.format(new Date());
                    int diffDays = (int) ((new Date().getTime() - borrow.getDate().getTime()) / (24 * 60 * 60 * 1000));
                    if (diffDays <= borrow.getBorrowDays()) {
                        data[user][7] = 0;
                    } else {
                        diffDays -= borrow.getBorrowDays();
                        data[user][7] = diffDays * 2000;
                    }
                    data[user][8] = Boolean.FALSE;
                    user++;
                }
            }
        }
        return data;
    }
}
