import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DistanceCalculator {

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

        public static void main(String[] args) throws Exception
        {
            String jsonString = getHTML("https://www.distance24.org/route.json?stops=Nagpur|Saoner");
            JSONObject obj = new JSONObject(jsonString);
            Object pageName = obj.get("distance");
            System.out.println(pageName);
        }
}


//    static final double RadianConstant = 0.0174532925199433;
//    static final int KM_RATIO = 6371;
//    public static double toRad(double value){
//        return value*RadianConstant;
//    }
//    public static double calculateGeoDistance(double start,double end){
//        try {
//            double dLat = toRad(end.endlat - start.startLad );
//            double dLon = toRad(end.lng - start.lng);
//            double lat1Rad = toRad(startLad);
//            double lat2Rad = toRad(endlat);
//            double a = Math.sin(dLat/2)*Math.sin(dLat/2) + Math.sin(dLon/2)*Math.sin(dLon/2)*Math.cos(lat1Rad)*Math.cos(lat2Rad);
//            double c = 2*Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
//            double dMtrs = KM_RATIO*c;
//            return dMtrs;
//        }catch (Exception ex){
//            return -1;
//        }
//    }
//
//    public static void main(String[] args) {
//        new DistanceCalculator();
//    }

//if(tf1.getText().isEmpty() || text1.getText().isEmpty() || tf3.getText().isEmpty() ||
//        tf5.getText().isEmpty() || tf7.getText().isEmpty() || tf9.getText().isEmpty() ||
//        tf2.getText().isEmpty() || text2.getText().isEmpty() || tf4.getText().isEmpty() ||
//        tf6.getText().isEmpty() || tf8.getText().isEmpty()){
//        //add password as a default "Pass123" inside databse table or give an option to an admin to keep as pass
//        JOptionPane.showMessageDialog(frame,"Text field isEmpty","Message",JOptionPane.INFORMATION_MESSAGE);
//        } else {
//        try {
//        String parcelId = String.format("1%03d", new Random().nextInt(1000));
//        String senderName = tf1.getText();
//        String senderAddress = text1.getText();
//        long senderPincode = Long.parseLong(tf3.getText());
//        String senderCity = tf5.getText();
//        String senderState = tf7.getText();
//        long parcelWeight = Long.parseLong(tf9.getText());
//        String receiverName = tf2.getText();
//        String receiverAddress = text2.getText();
//        long receiverPincode = Long.parseLong(tf4.getText());
//        String receiverCity = tf6.getText();
//        String receiverState = tf8.getText();
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post_office_data?",
//        "root","Sw@roop2001");
//        String str = "insert into parcel_data values (?,?,?,?,?,?,?,?,?,?,?,?)";
//        PreparedStatement statement = con.prepareStatement(str);
//        statement.setString(1,parcelId);
//        statement.setString(2,senderName);
//        statement.setString(3,senderAddress);
//        statement.setLong(4,senderPincode);
//        statement.setString(5,senderCity);
//        statement.setString(6,senderState);
//        statement.setDouble(7,parcelWeight);
//        statement.setString(8,receiverName);
//        statement.setString(9,receiverAddress);
//        statement.setLong(10,receiverPincode);
//        statement.setString(11,receiverCity);
//        statement.setString(12,receiverState);
//        statement.executeUpdate();
//        con.close();
//        tf1.setText("");
//        text1.setText("");
//        tf3.setText("");
//        tf5.setText("");
//        tf7.setText("");
//        tf9.setText("");
//        tf2.setText("");
//        text2.setText("");
//        tf4.setText("");
//        tf6.setText("");
//        tf8.setText("");
//
//        } catch (ClassNotFoundException | SQLException ex) {
//        System.out.println("Unable to load driver");
//        }
////                    String message = "Parcel Id -"+par;
//        JOptionPane.showMessageDialog(frame,"Parcel Id","Message",JOptionPane.INFORMATION_MESSAGE);
//        }


//using latitude longitude distance calculator
//try {
//        String speedPostId = String.format("5%03d", new Random().nextInt(1000));
//        String senderName = tf1.getText();
//        String senderAddress = text1.getText();
//        long senderPincode = Long.parseLong(tf3.getText());
//        String senderDistrict = tf5.getText();
//        String senderState = tf7.getText();
//        String senderPostOffice = (String) c.getSelectedItem();
//        float speedPostWeight = Float.parseFloat(tf9.getText());
//        String receiverName = tf2.getText();
//        String receiverAddress = text2.getText();
//        long receiverPincode = Long.parseLong(tf4.getText());
//        String receiverDistrict = tf6.getText();
//        String receiverState = tf8.getText();
//        String receiverPostOffice = (String) c1.getSelectedItem();
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post_office_data?",
//        "root","Sw@roop2001");
//        String str = "select * from pincodes where pincode ="+senderPincode;
//        PreparedStatement statement = con.prepareStatement(str);
//        ResultSet rs = statement.executeQuery();
//        String str1 = "select * from pincodes where pincode ="+receiverPincode;
//        PreparedStatement statement1 = con.prepareStatement(str1);
//        ResultSet rs1 = statement1.executeQuery();
//        if (rs.next() &&rs1.next()){
//        double startLat = rs.getDouble("lat");
//        double startLng = rs.getDouble("lng");
//        double endLat = rs1.getDouble("lat");
//        double endLng = rs1.getDouble("lng");
//        float distance = (float) SpeedPost.calculateGeoDistance(startLat,startLng,endLat,endLng);
//        float cost;
//        String query = "insert into speed_post_data values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
//        PreparedStatement statement2 = con.prepareStatement(query);
//
//        statement2.setString(1,speedPostId);
//        statement2.setString(2,senderName);
//        statement2.setString(3,senderAddress);
//        statement2.setLong(4,senderPincode);
//        statement2.setString(5,senderDistrict);
//        statement2.setString(6,senderState);
//        statement2.setFloat(7,speedPostWeight);
//        statement2.setString(8,receiverName);
//        statement2.setString(9,receiverAddress);
//        statement2.setLong(10,receiverPincode);
//        statement2.setString(11,receiverDistrict);
//        statement2.setString(12,receiverState);
//        if (speedPostWeight <= 50){
//        if (senderPincode == receiverPincode){
//        cost = 15;
//        } else {
//        cost = 35;
//        }
//        statement2.setFloat(13,cost);
//        statement2.executeUpdate();
//        JOptionPane.showMessageDialog(frame,"Speed PostID -"+speedPostId+"  Cost -"+cost,"Message",JOptionPane.INFORMATION_MESSAGE);
//        }else  if(speedPostWeight <= 200){
//        if (senderPincode == receiverPincode){
//        cost = 25;
//        } else if(distance <= 200) {
//        cost = 35;
//        } else if(distance <= 1000) {
//        cost = 40;
//        } else if(distance <= 2000) {
//        cost = 60;
//        } else {
//        cost = 70;
//        }
//        statement2.setFloat(13,cost);
//        statement2.executeUpdate();
//        JOptionPane.showMessageDialog(frame,"Speed PostID -"+speedPostId+"  Cost -"+cost,"Message",JOptionPane.INFORMATION_MESSAGE);
//        }else  if(speedPostWeight <= 500){
//        if (senderPincode == receiverPincode){
//        cost = 30;
//        } else if(distance <= 200) {
//        cost = 50;
//        } else if(distance <= 1000) {
//        cost = 60;
//        } else if(distance <= 2000) {
//        cost = 80;
//        } else {
//        cost = 90;
//        }
//        statement2.setFloat(13,cost);
//        statement2.executeUpdate();
//        JOptionPane.showMessageDialog(frame,"Speed PostID -"+speedPostId+"  Cost -"+cost,"Message",JOptionPane.INFORMATION_MESSAGE);
//        }else  if(speedPostWeight < 20000){
//        if (senderPincode == receiverPincode){
//        cost = (speedPostWeight/500)*10+30;
//        } else if(distance <= 200) {
//        cost = (speedPostWeight/500)*15+50;
//        } else if(distance <= 1000) {
//        cost = (speedPostWeight/500)*30+60;
//        } else if(distance <= 2000) {
//        cost = (speedPostWeight/500)*40+80;
//        } else {
//        cost = (speedPostWeight/500)*50+90;
//        }
//        statement2.setFloat(13,cost);
//        statement2.executeUpdate();
//        JOptionPane.showMessageDialog(frame,"Speed PostID -"+speedPostId+"  Cost -"+cost,"Message",JOptionPane.INFORMATION_MESSAGE);
//        }else  {
//        JOptionPane.showMessageDialog(frame,"Weight exceeded","Error",JOptionPane.ERROR_MESSAGE);
//        }
//        tf1.setText("");
//        text1.setText("");
//        tf3.setText("");
//        tf5.setText("");
//        tf7.setText("");
//        tf9.setText("");
//        tf2.setText("");
//        text2.setText("");
//        tf4.setText("");
//        tf6.setText("");
//        tf8.setText("");
//        c.setBounds(0,0,0,0);
//        c1.setBounds(0,0,0,0);
//        }else {
//        JOptionPane.showMessageDialog(frame,"Incorrect Pincode","ERROR",JOptionPane.ERROR_MESSAGE);
//        }
//        con.close();
//
//
//
//        } catch (ClassNotFoundException | SQLException ex) {
//        ex.printStackTrace();
//        }