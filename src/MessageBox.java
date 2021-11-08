import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;

public class MessageBox {
    JFrame frame;
    public MessageBox(){
        frame = new JFrame("POST OFFICE MANAGEMENT SYSTEM");
        JPanel panel = new JPanel();

        JLabel label = new JLabel("Id");
        label.setBounds(40,50,50,20);
        label.setForeground(Color.BLUE);
        label.setFont(new Font("Verdana",Font.BOLD,12));

        JLabel label1 = new JLabel("First Name");
        label1.setBounds(108,50,80,20);
        label1.setForeground(Color.BLUE);
        label1.setFont(new Font("Verdana",Font.BOLD,12));

        JLabel label2 = new JLabel("Role");
        label2.setBounds(220,50,80,20);
        label2.setForeground(Color.BLUE);
        label2.setFont(new Font("Verdana",Font.BOLD,12));

        JLabel label3 = new JLabel("Status");
        label3.setBounds(336,50,100,20);
        label3.setForeground(Color.BLUE);
        label3.setFont(new Font("Verdana",Font.BOLD,12));

        JButton backBtn = new JButton("BACK");
        backBtn.setBounds(340,300,70,25);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post_office_data?",
                    "root","Sw@roop2001");
            String str = "select * from request";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(str);
            int y = 100;
            while (rs.next()){
                String Id = rs.getString("Id");
                String role = rs.getString("role");
                String query;
                if(Objects.equals(role, "Member")){
                    query = "select * from members where empId = '"+Id+"'";
                }else {
                    query = "select * from admin where adminId = '"+Id+"'";
                }
                Statement statement1 = con.createStatement();
                ResultSet rs1 = statement1.executeQuery(query);
                while (rs1.next()){
                    JLabel lblId = new JLabel(Id);
                    lblId.setBounds(28,y,60,20);
                    String firstName = rs1.getString("firstname");
                    String lastName = rs1.getString("lastname");
                    String name = firstName+lastName;
                    JLabel lblName = new JLabel(name);
                    lblName.setBounds(90,y,130,20);
                    JLabel lblRole = new JLabel(role);
                    lblRole.setBounds(218,y,60,20);
                    JButton btn = new JButton("Approve");
                    btn.setBounds(310,y,100,20);
                    btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String query2 = "delete from request where Id = '"+Id+"'";
                            try {
                                PreparedStatement statement2 =  con.prepareStatement(query2);
                                statement2.executeUpdate();
                                String query3;
                                if(role == "Member"){
                                    query3 = "update members set password = DEFAULT where empId = '"+Id+"'";
                                }else{
                                    query3 = "update admin set password = DEFAULT where adminId = '"+Id+"'";
                                }
                                PreparedStatement statement3 =  con.prepareStatement(query3);
                                statement3.executeUpdate();
                                btn.setText("Accepted");
                                btn.setForeground(Color.GRAY);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                    panel.add(lblId);
                    panel.add(lblName);
                    panel.add(lblRole);
                    panel.add(btn);
                }
                y += 50;
            }
        }catch (Exception ex){
            ex.getStackTrace();
        }



        panel.add(label);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(backBtn);
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(465, 200));
        panel.setMaximumSize(new Dimension(465, 200));
        panel.setSize(435,350);
        TitledBorder border = new TitledBorder("Password Change Request");
        border.setTitleFont(new Font("Arial", Font.BOLD, 16));
        panel.setBorder(border);
        panel.setLocation(75,50);


        frame.getContentPane().add(panel);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(600,500);
        frame.setVisible(true);
    }
}