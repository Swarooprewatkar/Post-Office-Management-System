import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.*;
import java.util.Random;

public class Registry extends RegistrationForm{
    @Override
    public void changes() {
        lbl2.setText("REGISTRY");
        lbl2.setBounds(264,3,120,30);
        lbl15.setText("WEIGHT");
        proofOfDelivery.setBounds(0,0,0,0);
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
                if (tf1.getText().isEmpty() || text1.getText().isEmpty() || tf3.getText().isEmpty() ||
                        tf5.getText().isEmpty() || tf7.getText().isEmpty() || tf9.getText().isEmpty() ||
                        tf2.getText().isEmpty() || text2.getText().isEmpty() || tf4.getText().isEmpty() ||
                        tf6.getText().isEmpty() || tf8.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Input isEmpty", "Message", JOptionPane.INFORMATION_MESSAGE);
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
                    proofOfDelivery.setSelected(false);
                    c.setBounds(0,0,0,0);
                    c1.setBounds(0,0,0,0);
                } else {
                    try {
                        String Id = String.format("3%03d", new Random().nextInt(1000));
                        String senderName = tf1.getText();
                        String senderAddress = text1.getText();
                        long senderPincode = Long.parseLong(tf3.getText());
                        String senderDistrict = tf5.getText();
                        String senderState = tf7.getText();
                        String senderPostOffice = (String) c.getSelectedItem();
                        float parcelWeight = Float.parseFloat(tf9.getText());
                        String receiverName = tf2.getText();
                        String receiverAddress = text2.getText();
                        long receiverPincode = Long.parseLong(tf4.getText());
                        String receiverDistrict = tf6.getText();
                        String receiverState = tf8.getText();
                        String receiverPostOffice = (String) c1.getSelectedItem();
                        float cost =0;
                        cost = ((parcelWeight - 500) / 500) * 16 + 19;
                        cost *= 1.5;
                        if (parcelWeight <= 20000) {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post_office_data?",
                                    "root", "Sw@roop2001");
                            String str = "insert into registry_data values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                            PreparedStatement statement = con.prepareStatement(str);
                            statement.setString(1, Id);
                            statement.setString(2, senderName);
                            statement.setString(3, senderAddress);
                            statement.setLong(4, senderPincode);
                            statement.setString(5,senderDistrict);
                            statement.setString(6, senderState);
                            statement.setString(7,senderPostOffice);
                            statement.setFloat(8, parcelWeight);
                            statement.setString(9, receiverName);
                            statement.setString(10, receiverAddress);
                            statement.setLong(11, receiverPincode);
                            statement.setString(12, receiverDistrict);
                            statement.setString(13, receiverState);
                            statement.setString(14,receiverPostOffice);
                            statement.setFloat(15, cost);
                            statement.executeUpdate();
                            con.close();
                            proofOfDelivery.setSelected(false);
                            BillTest bill = new BillTest();
                            bill.bookingId = Id;
                            bill.senderName = senderName;
                            bill.senderPincode = senderPincode;
                            bill.senderDistrict = senderDistrict;
                            bill.senderPostOffice = senderPostOffice;
                            bill.weight = parcelWeight;
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
                        } else {
                            JOptionPane.showMessageDialog(null, "Weight limit exceeded", "Error", JOptionPane.ERROR_MESSAGE);
                            tf9.setText("");
                        }
                    } catch (ClassNotFoundException | SQLException | IOException ex) {
                        ex.getStackTrace();
                    }
                }
            }
        });
    }
}

