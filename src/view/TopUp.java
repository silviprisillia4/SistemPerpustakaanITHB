package view;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import javax.swing.*;

public class TopUp {
    JFrame frame;
    JPanel panel1;
    JLabel labelInfo;
    JButton btnCopyText, btnBack;
    
    public TopUp() {
        //Frame
        frame = new DefaultFrameSetting().defaultFrame(); 
        frame.setSize(350, 230);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Perpustakaan ITHB - Member Top Up");
        
        //Panel
        panel1 = new DefaultFrameSetting().defaultPanel();
        panel1.setSize(350, 230);
        panel1.setBackground(new Color(255, 234, 202));
        panel1.setVisible(true);
        
        //Label
        String VA = "00112345678";        
        labelInfo = new JLabel("<html>2 cara top up saldo :<br><br>- Top up mandiri dengan transfer Virtual Account BCA "+VA+"<br><br>- Top up via admin</html>");
        labelInfo.setBounds(30, 0, 200, 200);
        
        //Button
        btnCopyText = new JButton("Salin");
        btnCopyText.setBounds(240, 80, 80, 30);
        btnCopyText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection stringSelection = new StringSelection(VA);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
        });
        btnBack = new JButton("Kembali");
        btnBack.setBounds(240, 120, 80, 30);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new OutputInfo().backToPreviousMenu();
                new Profile();
            }
        });
        
        //Add
        panel1.add(labelInfo);
        panel1.add(btnCopyText);
        panel1.add(btnBack);
        frame.add(panel1);
        
        //Initialize
        panel1.setLayout(null);
        frame.setLayout(null);
        frame.setVisible(true);
    } 
}