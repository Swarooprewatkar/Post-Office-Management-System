import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class RegistrationForm {
    JFrame frame;
    JPanel panel,panel1;
    JComboBox c,c1;
    JCheckBox proofOfDelivery;
    JTextField tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8,tf9;
    JTextArea text1,text2;
    JLabel lbl15,weightLimit;
    JButton btn3;
    JLabel lbl2;
    RegistrationForm() {
        frame = new JFrame("POST OFFICE MANAGEMENT SYSTEM");
        lbl2 = new JLabel("");
        lbl2.setFont(new Font("Verdana", Font.BOLD, 18));

        //Senders Information
        panel = new JPanel();

        JLabel lbl5 = new JLabel("NAME");
        lbl5.setBounds(13, 35, 100, 20);
        tf1 = new JTextField();
        tf1.setBounds(91, 35, 150, 20);

        JLabel lbl7 = new JLabel("ADDRESS");
        lbl7.setBounds(13, 90, 60, 20);
        text1 = new JTextArea(10, 10);
        text1.setEnabled(true);
        text1.setLineWrap(true);

        JScrollPane scroller1 = new JScrollPane(text1);
        scroller1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller1.setBounds(91, 68, 144, 65);

        JLabel lbl9 = new JLabel("PIN CODE");
        lbl9.setBounds(13, 148, 70, 20);
        tf3 = new JTextField();
        tf3.setBounds(91, 148, 150, 20);
        tf3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!(Character.isDigit(e.getKeyChar()))) {
                    e.consume();
                }
            }
        });

        c = new JComboBox();

        JLabel lbl11 = new JLabel("DISTRICT");
        lbl11.setBounds(13, 178, 70, 20);
        tf5 = new JTextField();
        tf5.setBounds(91, 178, 150, 20);


        JLabel lbl13 = new JLabel("STATE");
        lbl13.setBounds(13, 208, 70, 20);
        tf7 = new JTextField();
        tf7.setBounds(91, 208, 150, 20);

        JLabel lbl16 = new JLabel("POSTOFFICE");
        lbl16.setBounds(13, 246, 90, 20);

        lbl15 = new JLabel("");
        lbl15.setBounds(13, 285, 70, 20);
        tf9 = new JTextField();
        tf9.setBounds(91, 285, 150, 20);
        tf9.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                if (!(Character.isDigit(evt.getKeyChar()))) {
                    evt.consume();
                }
            }
        });

        weightLimit = new JLabel("");
        weightLimit.setFont(new Font("Verdana", Font.PLAIN, 8));
        weightLimit.setBounds(45, 306, 180, 20);

        panel.add(lbl5);
        panel.add(tf1);
        panel.add(lbl7);
        panel.add(scroller1);
        panel.add(lbl9);
        panel.add(tf3);
        panel.add(lbl11);
        panel.add(tf5);
        panel.add(lbl13);
        panel.add(tf7);
        panel.add(c);
        panel.add(lbl15);
        panel.add(tf9);
        panel.add(lbl16);
        panel.add(weightLimit);
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(350, 450));
        panel.setMaximumSize(new Dimension(400, 500));
        panel.setSize(265, 335);
        TitledBorder border = new TitledBorder("Sender's");
        border.setTitleFont(new Font("Arial", Font.BOLD, 16));
        panel.setBorder(border);
        panel.setLocation(50, 85);


        //Reciever's information
        panel1 = new JPanel();

        JLabel lbl6 = new JLabel("NAME");
        lbl6.setBounds(13, 35, 100, 20);
        tf2 = new JTextField();
        tf2.setBounds(91, 35, 150, 20);


        JLabel lbl8 = new JLabel("ADDRESS");
        lbl8.setBounds(13, 90, 60, 20);
        text2 = new JTextArea(10, 10);
        text2.setEnabled(true);
        text2.setLineWrap(true);

        JScrollPane scroller2 = new JScrollPane(text2);
        scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller2.setBounds(91, 68, 144, 65);

        JLabel lbl10 = new JLabel("PIN CODE");
        lbl10.setBounds(13, 148, 70, 20);
        tf4 = new JTextField();
        tf4.setBounds(91, 148, 150, 20);
        tf4.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                if (!(Character.isDigit(evt.getKeyChar()))) {
                    evt.consume();
                }
            }
        });

        c1 = new JComboBox();

        JLabel lbl12 = new JLabel("DISTRICT");
        lbl12.setBounds(13, 178, 70, 20);
        tf6 = new JTextField();
        tf6.setBounds(91, 178, 150, 20);
        tf6.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {

                if (!(Character.isLetter(evt.getKeyChar()))) {
                    evt.consume();
                }
            }
        });

        JLabel lbl14 = new JLabel("STATE");
        lbl14.setBounds(13, 208, 70, 20);
        tf8 = new JTextField();
        tf8.setBounds(91, 208, 150, 20);
        tf8.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {

                if (!(Character.isLetter(evt.getKeyChar()))) {
                    evt.consume();
                }
            }
        });
        JLabel lbl17 = new JLabel("POSTOFFICE");
        lbl17.setBounds(13, 246, 90, 20);

        proofOfDelivery = new JCheckBox("proof of delivery");
        proofOfDelivery.setBounds(75,285,150,20);


        panel1.add(lbl6);
        panel1.add(tf2);
        panel1.add(lbl8);
        panel1.add(scroller2);
        panel1.add(lbl10);
        panel1.add(tf4);
        panel1.add(lbl12);
        panel1.add(tf6);
        panel1.add(c1);
        panel1.add(tf8);
        panel1.add(lbl14);
        panel1.add(lbl17);
        panel1.add(proofOfDelivery);
        panel1.setLayout(null);
        panel1.setPreferredSize(new Dimension(350, 450));
        panel1.setMaximumSize(new Dimension(400, 500));
        panel1.setSize(265, 335);
        TitledBorder rborder = new TitledBorder("Reciever's");
        rborder.setTitleFont(new Font("Arial", Font.BOLD, 16));
        panel1.setBorder(rborder);
        panel1.setLocation(318, 85);


        JButton btn2 = new JButton("BACK");
        btn2.setBounds(410, 450, 80, 30);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        btn3 = new JButton("PRINT");
        btn3.setBounds(505, 450, 80, 30);


        frame.add(lbl2);
        frame.add(btn2);
        frame.add(btn3);
        frame.getContentPane().add(panel);
        frame.getContentPane().add(panel1);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(650, 600);
        frame.setVisible(true);
    }
    public void changes(){

    }
}
