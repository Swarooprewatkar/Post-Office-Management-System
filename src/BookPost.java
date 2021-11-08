import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.*;
import java.util.Hashtable;
import java.util.Random;

class BookPost{
    JFrame frame1;
    JComboBox c, c1,c3,c4;
    private Hashtable<Object, Object> subItems = new Hashtable<Object, Object>();
    float cost, bookPostWeight;
    BookPost() {
        frame1 = new JFrame("POST OFFICE MANAGEMENT SYSTEM");
        JLabel lbl = new JLabel("BOOK POST");
        lbl.setFont(new Font("Verdana", Font.BOLD, 18));
        lbl.setBounds(260,0,150,30);

        JLabel lbl1 = new JLabel("Select Book Post Type -");
        lbl1.setBounds(150,45,160,20);

        String [] items = {"select option","POST CARD","LETTER CARD"};
        c = new JComboBox(items);
        c.setBounds(318,45,150,20);
        c.setSelectedIndex(0);
        String[] subItems1 = {"select option"};
        subItems.put(items[0], subItems1);
        String[] subItems2 = {"select option","MEGHDOOT","SINGLE","REPLY","PRINTED","COMPETITION"};
        subItems.put(items[1], subItems2);
        String[] subItems3 = {"select option","LETTER"};
        subItems.put(items[2], subItems3);
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = (String) c.getSelectedItem();
                Object o = subItems.get(item);
                if (o == null) {
                    c1.setModel(new DefaultComboBoxModel());
                } else {
                    c1.setModel(new DefaultComboBoxModel((String[]) o));
                }
            }
        });

        JLabel lbl2 = new JLabel("Select Card Type -");
        lbl2.setBounds(150,73,160,20);
        c1 = new JComboBox(subItems1);
        c1.setBounds(318,73,150,20);


        //Senders Information
        JPanel panel = new JPanel();

        JLabel lbl5 = new JLabel("NAME");
        lbl5.setBounds(13,35,100,20);
        JTextField tf1 = new JTextField();
        tf1.setBounds(91, 35, 150, 20);

        JLabel lbl7 = new JLabel("ADDRESS");
        lbl7.setBounds(13,90,60,20);
        JTextArea text1 = new JTextArea(10,10);
        text1.setEnabled(true);
        text1.setLineWrap(true);

        JScrollPane scroller1 = new JScrollPane(text1);
        scroller1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller1.setBounds(91,68,144,65);

        JLabel lbl9 = new JLabel("PIN CODE");
        lbl9.setBounds(13,148,70,20);
        JTextField tf3 = new JTextField();
        tf3.setBounds(91,148,150,20);
        tf3.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                if(!(Character.isDigit(evt.getKeyChar()))){
                    evt.consume();
                }
            }
        });

        JLabel lbl11 = new JLabel("DISTRICT");
        lbl11.setBounds(13,178,70,20);
        JTextField tf5 = new JTextField();
        tf5.setBounds(91,178,150,20);
        tf5.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {

                if(!(Character.isLetter(evt.getKeyChar()))){
                    evt.consume();
                }
            }
        });

        JLabel lbl13 = new JLabel("STATE");
        lbl13.setBounds(13,208,70,20);
        JTextField tf7 = new JTextField();
        tf7.setBounds(91,208,150,20);
        tf7.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {

                if(!(Character.isLetter(evt.getKeyChar()))){
                    evt.consume();
                }
            }
        });

        JLabel lbl16 = new JLabel("POSTOFFICE");
        lbl16.setBounds(13,246,90,20);

        c3 = new JComboBox();

        tf3.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    String pinCode = tf3.getText();
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post_office_data?",
                                "root","Sw@roop2001");
                        String str = "select * from pincodes_tb where pincode ='"+pinCode+"'";
                        PreparedStatement statement = con.prepareStatement(str);
                        ResultSet rs = statement.executeQuery();
                        while (rs.next()){
                            String state = rs.getString("state");
                            String district = rs.getString("district");
                            String postOffice = rs.getString("office");
                            tf5.setText(district);
                            tf7.setText(state);
                            c3.addItem(postOffice);
                        }
                        c3.setBounds(91,246,150,20);
                    } catch (SQLException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        JLabel lbl15 = new JLabel("WEIGHT");
        lbl15.setBounds(13,285,70,20);
        JTextField tf9 = new JTextField();
        tf9.setBounds(91,285,150,20);
        tf9.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                if(!(Character.isDigit(evt.getKeyChar()))){
                    evt.consume();
                }
            }
        });
        JLabel weightLimit = new JLabel("Maximum weight allowed: 20000 gram");
        weightLimit.setFont(new Font("Verdana", Font.PLAIN, 8));
        weightLimit.setBounds(45,306,180,20);

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
        panel.add(lbl15);
        panel.add(tf9);
        panel.add(lbl16);
        panel.add(c3);
        panel.add(weightLimit);
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(350, 450));
        panel.setMaximumSize(new Dimension(400, 500));
        panel.setSize(265,335);
        TitledBorder border = new TitledBorder("Sender's");
        border.setTitleFont(new Font("Arial", Font.BOLD, 16));
        panel.setBorder(border);
        panel.setLocation(50,111);





        //Reciever's information
        JPanel panel1 = new JPanel();

        JLabel lbl6 = new JLabel("NAME");
        lbl6.setBounds(13,35,100,20);
        JTextField tf2 = new JTextField();
        tf2.setBounds(91,35,150,20);

        JLabel lbl8 = new JLabel("ADDRESS");
        lbl8.setBounds(13,90,60,20);
        JTextArea text2 = new JTextArea(10,10);
        text2.setEnabled(true);
        text2.setLineWrap(true);

        JScrollPane scroller2 = new JScrollPane(text2);
        scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller2.setBounds(91,68,144,65);

        JLabel lbl10 = new JLabel("PIN CODE");
        lbl10.setBounds(13,148,70,20);
        JTextField tf4 = new JTextField();
        tf4.setBounds(91,148,150,20);
        tf4.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                if(!(Character.isDigit(evt.getKeyChar()))){
                    evt.consume();
                }
            }
        });

        JLabel lbl12 = new JLabel("DISTRICT");
        lbl12.setBounds(13,178,70,20);
        JTextField tf6 = new JTextField();
        tf6.setBounds(91,178,150,20);
        tf6.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {

                if(!(Character.isLetter(evt.getKeyChar()))){
                    evt.consume();
                }
            }
        });

        JLabel lbl14 = new JLabel("STATE");
        lbl14.setBounds(13,208,70,20);
        JTextField tf8 = new JTextField();
        tf8.setBounds(91,208,150,20);
        tf8.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {

                if(!(Character.isLetter(evt.getKeyChar()))){
                    evt.consume();
                }
            }
        });
        JLabel lbl17 = new JLabel("POSTOFFICE");
        lbl17.setBounds(13,246,90,20);

        JCheckBox proofOfDelivery = new JCheckBox("proof of delivery");
        proofOfDelivery.setBounds(75,285,150,20);

        c4 = new JComboBox();
        tf4.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    String pinCode = tf4.getText();
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post_office_data?",
                                "root","Sw@roop2001");
                        String str = "select * from pincodes_tb where pincode ='"+pinCode+"'";
                        PreparedStatement statement = con.prepareStatement(str);
                        ResultSet rs = statement.executeQuery();
                        while (rs.next()){
                            String state = rs.getString("state");
                            String district = rs.getString("district");
                            String postOffice = rs.getString("office");
                            tf8.setText(state);
                            tf6.setText(district);
                            c4.addItem(postOffice);
                        }
                        c4.setBounds(91,246,150,20);
                    } catch (SQLException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        panel1.add(lbl6);
        panel1.add(tf2);
        panel1.add(lbl8);
        panel1.add(scroller2);
        panel1.add(lbl10);
        panel1.add(tf4);
        panel1.add(lbl12);
        panel1.add(tf6);
        panel1.add(tf8);
        panel1.add(lbl14);
        panel1.add(lbl17);
        panel1.add(c4);
        panel1.add(proofOfDelivery);
        panel1.setLayout(null);
        panel1.setPreferredSize(new Dimension(350, 450));
        panel1.setMaximumSize(new Dimension(400, 500));
        panel1.setSize(265,335);
        TitledBorder rborder = new TitledBorder("Reciever's");
        rborder.setTitleFont(new Font("Arial", Font.BOLD, 16));
        panel1.setBorder(rborder);
        panel1.setLocation(318,111);


        JButton btn2 = new JButton("BACK");
        btn2.setBounds(410,470,80,30);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.dispose();
            }
        });
        c1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (e.getSource() == c1) {
                        if (c1.getSelectedItem() == "MEGHDOOT") {
                            cost = 0.2f;
                            weightLimit.setText("Maximum weight allowed: 5 gram");
                            weightLimit.setFont(new Font("Verdana", Font.PLAIN, 8));
                        } else if (c1.getSelectedItem() == "SINGLE") {
                            cost = 0.5f;
                            weightLimit.setText("Maximum weight allowed: 5 gram");
                            weightLimit.setFont(new Font("Verdana", Font.PLAIN, 8));
                        } else if (c1.getSelectedItem() == "REPLY") {
                            cost = 1.0f;
                            weightLimit.setText("Maximum weight allowed: 5 gram");
                            weightLimit.setFont(new Font("Verdana", Font.PLAIN, 8));
                        } else if (c1.getSelectedItem() == "PRINTED") {
                            cost = 6.0f;
                            weightLimit.setText("Maximum weight allowed: 5 gram");
                            weightLimit.setFont(new Font("Verdana", Font.PLAIN, 8));
                        } else if (c1.getSelectedItem() == "COMPETITION") {
                            cost = 10.0f;
                            weightLimit.setText("Maximum weight allowed: 5 gram");
                            weightLimit.setFont(new Font("Verdana", Font.PLAIN, 8));
                        }else if(c1.getSelectedItem() == "LETTER") {
                            weightLimit.setText("Maximum weight allowed: 2000 gram");
                            weightLimit.setFont(new Font("Verdana", Font.PLAIN, 8));
                        } else {
                            weightLimit.setText("");
                            JOptionPane.showMessageDialog(frame1, "Select your card type", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        JButton btn3 = new JButton("OK");
        btn3.setBounds(505,470,80,30);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tf1.getText().isEmpty() || text1.getText().isEmpty() || tf3.getText().isEmpty() ||
                        tf5.getText().isEmpty() || tf7.getText().isEmpty() || tf9.getText().isEmpty() ||
                        tf2.getText().isEmpty() || text2.getText().isEmpty() || tf4.getText().isEmpty() ||
                        tf6.getText().isEmpty() || tf8.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame1,"Input isEmpty","Message",JOptionPane.INFORMATION_MESSAGE);
                } else {
                    try {
                        if(bookPostWeight<=2000){
                            String bookPostId = String.format("2%03d", new Random().nextInt(1000));
                            String senderName = tf1.getText();
                            String senderAddress = text1.getText();
                            long senderPincode = Long.parseLong(tf3.getText());
                            String senderDistrict = tf5.getText();
                            String senderState = tf7.getText();
                            String senderPostOffice = (String) c3.getSelectedItem();
                            bookPostWeight = Float.parseFloat(tf9.getText());
                            String receiverName = tf2.getText();
                            String receiverAddress = text2.getText();
                            long receiverPincode = Long.parseLong(tf4.getText());
                            String receiverDistrict = tf6.getText();
                            String receiverState = tf8.getText();
                            String receiverPostOffice = (String) c4.getSelectedItem();
                            if (proofOfDelivery.isSelected()){
                                cost += 10;
                            }
                            if(c1.getSelectedItem() == "LETTER") {
                                weightLimit.setText("Maximum weight allowed: 2000 gram");
                                weightLimit.setFont(new Font("Verdana", Font.PLAIN, 8));
                                cost = (bookPostWeight / 20) * 5;
                            }
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post_office_data?",
                                    "root","Sw@roop2001");
                            String str = "insert into book_post_data values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                            PreparedStatement statement = con.prepareStatement(str);
                            statement.setString(1,bookPostId);
                            statement.setString(2,senderName);
                            statement.setString(3,senderAddress);
                            statement.setLong(4,senderPincode);
                            statement.setString(5,senderDistrict);
                            statement.setString(6,senderState);
                            statement.setString(7,senderPostOffice);
                            statement.setDouble(8,bookPostWeight);
                            statement.setString(9,receiverName);
                            statement.setString(10,receiverAddress);
                            statement.setLong(11,receiverPincode);
                            statement.setString(12,receiverDistrict);
                            statement.setString(13,receiverState);
                            statement.setString(14,receiverPostOffice);
                            statement.setDouble(15,cost);
                            statement.executeUpdate();
                            con.close();
                            BillTest bill = new BillTest();
                            bill.bookingId = bookPostId;
                            bill.senderName = senderName;
                            bill.senderPincode = senderPincode;
                            bill.senderDistrict = senderDistrict;
                            bill.senderPostOffice = senderPostOffice;
                            bill.weight = bookPostWeight;
                            bill.receiverName = receiverName;
                            bill.receiverDistrict = receiverDistrict;
                            bill.receiverPincode = receiverPincode;
                            bill.receiverPostOffice = receiverPostOffice;
                            bill.totalAmount = cost;
                            bill.run();
                            tf1.setText("");
                            text1.setText("");
                            tf3.setText("");
                            tf5.setText("");
                            tf7.setText("");
                            tf9.setText("");
                            tf2.setText("");
                            text2.setText("");
                            tf4.setText("");
                            tf6.setText("");
                            tf8.setText("");
                            c3.setBounds(0,0,0,0);
                            c4.setBounds(0,0,0,0);
                        }else {
                            JOptionPane.showMessageDialog(frame1,"Weight limit crossed","Error",JOptionPane.ERROR_MESSAGE);
                            tf9.setText("");
                        }
                    } catch (ClassNotFoundException | SQLException | IOException ex) {
                        System.out.println("Unable to load driver");
                    }
                }
                proofOfDelivery.setSelected(false);
            }
        });

        frame1.add(lbl);
        frame1.add(c);
        frame1.add(lbl1);
        frame1.add(c1);
        frame1.add(lbl2);
        frame1.add(btn2);
        frame1.add(btn3);
        frame1.getContentPane().add(panel);
        frame1.getContentPane().add(panel1);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);
        frame1.setLayout(null);
        frame1.setSize(650,600);
        frame1.setVisible(true);

    }
}
