import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.*;
import java.util.Random;

public class MoneyOrder extends RegistrationForm{

    @Override
    public void changes() {
        lbl2.setText("MONEY ORDER SERVICE");
        lbl2.setBounds(210,0,400,30);
        lbl15.setText("AMOUNT");
        weightLimit.setText("Amount Range 1000-50000");
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
                            c.addItem(postOffice);
                        }
                        c.setBounds(91,246,150,20);
                    } catch (SQLException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
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
                            c1.addItem(postOffice);
                        }
                        c1.setBounds(91,246,150,20);
                    } catch (SQLException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tf1.getText().isEmpty() || text1.getText().isEmpty() || tf3.getText().isEmpty() ||
                        tf5.getText().isEmpty() || tf7.getText().isEmpty() || tf9.getText().isEmpty() ||
                        tf2.getText().isEmpty() || text2.getText().isEmpty() || tf4.getText().isEmpty() ||
                        tf6.getText().isEmpty() || tf8.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame,"Input isEmpty","Message",JOptionPane.INFORMATION_MESSAGE);
                } else {
                    try {
                        String MoneyOrderId = String.format("4%03d", new Random().nextInt(1000));
                        String senderName = tf1.getText();
                        String senderAddress = text1.getText();
                        long senderPincode = Long.parseLong(tf3.getText());
                        String senderDistrict = tf5.getText();
                        String senderState = tf7.getText();
                        String senderPostOffice = (String) c.getSelectedItem();
                        float amount = Float.parseFloat(tf9.getText());
                        String receiverName = tf2.getText();
                        String receiverAddress = text2.getText();
                        long receiverPincode = Long.parseLong(tf4.getText());
                        String receiverDistrict = tf6.getText();
                        String receiverState = tf8.getText();
                        String receiverPostOffice = (String) c1.getSelectedItem();
                        float cost =0;
                        if (proofOfDelivery.isSelected()){
                            cost +=10;
                        }
                        cost = (amount/20);
                        if (1000 < amount && amount <= 50000){
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post_office_data?",
                                    "root","Sw@roop2001");
                            String str = "insert into moneyorder_data values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                            PreparedStatement statement = con.prepareStatement(str);
                            statement.setString(1,MoneyOrderId);
                            statement.setString(2,senderName);
                            statement.setString(3,senderAddress);
                            statement.setLong(4,senderPincode);
                            statement.setString(5,senderDistrict);
                            statement.setString(6,senderState);
                            statement.setString(7,senderPostOffice);
                            statement.setDouble(8,amount);
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
                            bill.bookingId = MoneyOrderId;
                            bill.senderName = senderName;
                            bill.senderPincode = senderPincode;
                            bill.senderDistrict = senderDistrict;
                            bill.senderPostOffice = senderPostOffice;
                            bill.weight = amount;
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
                            c.setBounds(0,0,0,0);
                            c1.setBounds(0,0,0,0);
                        }else if (amount>50000){
                            JOptionPane.showMessageDialog(frame,"Amount limit exceeded","Message",JOptionPane.ERROR_MESSAGE);
                            tf9.setText("");
                        }else {
                            JOptionPane.showMessageDialog(frame,"Minimum amount should be 1000","Message",JOptionPane.ERROR_MESSAGE);
                            tf9.setText("");
                        }
                    } catch (ClassNotFoundException | SQLException | IOException ex) {
                        System.out.println("Unable to load driver");
                    }
                }
                proofOfDelivery.setSelected(false);
            }
        });
    }
}

