import org.json.JSONObject;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.Random;

public class SpeedPost extends RegistrationForm{

    @Override
    public void changes() {
        lbl2.setText("SPEED POST");
        lbl2.setBounds(264,3,300,30);
        lbl15.setText("WEIGHT");
        weightLimit.setText("Maximum weight allowed: 20000 gram");
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
                        String speedPostId = String.format("5%03d", new Random().nextInt(1000));
                        String senderName = tf1.getText();
                        String senderAddress = text1.getText();
                        long senderPincode = Long.parseLong(tf3.getText());
                        String senderDistrict = tf5.getText();
                        String senderState = tf7.getText();
                        String senderPostOffice = (String) c.getSelectedItem();
                        float speedPostWeight = Float.parseFloat(tf9.getText());
                        String receiverName = tf2.getText();
                        String receiverAddress = text2.getText();
                        long receiverPincode = Long.parseLong(tf4.getText());
                        String receiverDistrict = tf6.getText();
                        String receiverState = tf8.getText();
                        String receiverPostOffice = (String) c1.getSelectedItem();
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post_office_data?",
                                "root","Sw@roop2001");
                        String str = "select * from pincodes_tb where pincode ="+senderPincode;
                        PreparedStatement statement = con.prepareStatement(str);
                        ResultSet rs = statement.executeQuery();
                        String str1 = "select * from pincodes_tb where pincode ="+receiverPincode;
                        PreparedStatement statement1 = con.prepareStatement(str1);
                        ResultSet rs1 = statement1.executeQuery();
                        String jsonString = getHTML("https://www.distance24.org/route.json?stops="+senderDistrict+"|"+receiverDistrict);
                        JSONObject obj = new JSONObject(jsonString);
                        int pageName = (Integer) obj.get("distance");
                        int distance =  pageName;
                        float cost = 0;
                        if (proofOfDelivery.isSelected()){
                            cost +=10;
                        }
                        String query = "insert into speed_post_data values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        PreparedStatement statement2 = con.prepareStatement(query);

                        statement2.setString(1,speedPostId);
                        statement2.setString(2,senderName);
                        statement2.setString(3,senderAddress);
                        statement2.setLong(4,senderPincode);
                        statement2.setString(5,senderDistrict);
                        statement2.setString(6,senderState);
                        statement2.setString(7,senderPostOffice);
                        statement2.setFloat(8,speedPostWeight);
                        statement2.setString(9,receiverName);
                        statement2.setString(10,receiverAddress);
                        statement2.setLong(11,receiverPincode);
                        statement2.setString(12,receiverDistrict);
                        statement2.setString(13,receiverState);
                        statement2.setString(14,receiverPostOffice);
                        if (speedPostWeight <= 50){
                            if (senderDistrict == receiverDistrict){
                                cost = 15;
                            } else {
                                cost = 35;
                            }
                            statement2.setFloat(15,cost);
                            statement2.executeUpdate();
                        }else  if(speedPostWeight <= 200){
                            if (senderDistrict == receiverDistrict){
                                cost = 25;
                            } else if(distance <= 200) {
                                cost = 35;
                            } else if(distance <= 1000) {
                                cost = 40;
                            } else if(distance <= 2000) {
                                cost = 60;
                            } else {
                                cost = 70;
                            }
                            statement2.setFloat(15,cost);
                            statement2.executeUpdate();
                        }else  if(speedPostWeight <= 500){
                            if (senderDistrict == receiverDistrict){
                                cost = 30;
                            } else if(distance <= 200) {
                                cost = 50;
                            } else if(distance <= 1000) {
                                cost = 60;
                            } else if(distance <= 2000) {
                                cost = 80;
                            } else {
                                cost = 90;
                            }
                            statement2.setFloat(15,cost);
                            statement2.executeUpdate();
                        }else  if(speedPostWeight < 20000){
                            if (senderDistrict == receiverDistrict){
                                cost = (speedPostWeight/500)*10+30;
                            } else if(distance <= 200) {
                                cost = (speedPostWeight/500)*15+50;
                            } else if(distance <= 1000) {
                                cost = (speedPostWeight/500)*30+60;
                            } else if(distance <= 2000) {
                                cost = (speedPostWeight/500)*40+80;
                            } else {
                                cost = (speedPostWeight/500)*50+90;
                            }
                            statement2.setFloat(15,cost);
                            statement2.executeUpdate();
                        }else  {
                            JOptionPane.showMessageDialog(frame,"Weight exceeded","Error",JOptionPane.ERROR_MESSAGE);
                        }
                        BillTest bill = new BillTest();
                        bill.bookingId = speedPostId;
                        bill.senderName = senderName;
                        bill.senderPincode = senderPincode;
                        bill.senderDistrict = senderDistrict;
                        bill.senderPostOffice = senderPostOffice;
                        bill.weight = speedPostWeight;
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
                        con.close();



                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                proofOfDelivery.setSelected(false);
            }
        });
    }
    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        return (result.toString());
    }
}


