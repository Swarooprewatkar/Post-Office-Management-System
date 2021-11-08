import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class DisplayParcelData extends JFrame implements ActionListener {
    JFrame frame1;
    JLabel l0, l1;
    JButton b1;
    JTextField tf;
    PreparedStatement pst;
    ResultSet rs;

    static JTable table;
    String[] columnNames = {"Id", "Sender Name", "Sender Address",
            "Sender Pincode", "Sender District", "Sender State","Sender PostOffice","Amount/Weight","Receiver Name", "Receiver Address",
            "Receiver Pincode", "Receiver District", "Receiver State","Receiver PostOffice","Paid Amount","Service"};
    DisplayParcelData() {
        l0 = new JLabel("Fetch Parcel Information");
        l1 = new JLabel("Booking ID -");
        l1.setForeground(Color.BLUE);
        b1 = new JButton("Search");
        l0.setBounds(120, 0, 350, 40);
        l0.setFont(new Font("Verdana", Font.BOLD, 18));
        tf = new JTextField();
        tf.setBounds(176, 76, 200, 25);
        add(tf);
        l1.setBounds(65, 78, 160, 20);
        b1.setBounds(232, 135, 80, 25);
        b1.addActionListener(this);
        JButton b2 = new JButton("BACK");
        b2.setBounds(138, 135, 80, 25);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setTitle("Employee Details");
        setLayout(null);
        setVisible(true);
        setSize(500, 245);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(l0);
        add(l1);
        add(b1);
        add(b2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            showTableData();
        }
    }
    public void showTableData() {
        frame1 = new JFrame("Parcel Details");
        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroller = new JScrollPane(table);
        scroller.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        String iD = tf.getText();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post_office_data?",
                    "root","Sw@roop2001");
                String id,sName,sAddress,sDistrict,sState,sPostOffice,rName,rAddress,rDistrict,rState,rPostOffice;
            String service = "";
            long sPincode,rPincode;
            float amountOrWeight,paid;
            if(iD.charAt(0) == '1'){
                pst = con.prepareStatement("select * from parcel_data where Id='" + iD + "'");
                service = "Parcel";
            }else if(iD.charAt(0) == '2'){
                pst = con.prepareStatement("select * from book_post_data where Id='" + iD + "'");
                service = "Book Post";
            }else if(iD.charAt(0) == '5'){
                pst = con.prepareStatement("select * from speed_post_data where Id='" + iD + "'");
                service = "Speed Post";
            }else if(iD.charAt(0) == '4'){
                pst = con.prepareStatement("select * from moneyorder_data where Id='" + iD + "'");
                service = "Money Order";
            }
            rs = pst.executeQuery();
            int i = 0;
            if (rs.next()) {
                id = rs.getString("Id");
                sName = rs.getString("sendername");
                sAddress = rs.getString("sendersaddress");
                sPincode = rs.getLong("senderspincode");
                sDistrict = rs.getString("sendersdistrict");
                sState = rs.getString("sendersstate");
                sPostOffice = rs.getString("senderspostoffice");
                amountOrWeight = rs.getLong("weightOramount");
                rName = rs.getString("receivername");
                rAddress = rs.getString("receiversaddress");
                rPincode = rs.getLong("receiverspincode");
                rDistrict = rs.getString("receiversdistrict");
                rPostOffice = rs.getString("receiverspostoffice");
                rState = rs.getString("receiversstate");
                paid = rs.getFloat("amount_paid");

                model.addRow(new Object[]{id,sName,sAddress,sPincode,sDistrict, sState,sPostOffice,amountOrWeight,rName,rAddress,rPincode,rDistrict,rState,rPostOffice,paid,service});
                i++;
            }
            tf.setText("");

            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
                frame1.dispose();
            }
            else if (i == 1) {
                JOptionPane.showMessageDialog(null, "1 Record Found", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, i+" Record Found", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
            pst.close();
            rs.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        frame1.add(scroller);
        frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame1.setVisible(true);
        frame1.setSize(1500, 300);
    }
}
