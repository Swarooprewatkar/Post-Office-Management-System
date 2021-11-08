import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;

public class UserIdGUI {
    static JFrame frame;
    static JCheckBox checkBox;
    static JTextField tf1;
    static JPasswordField tf2;
    static String userFirstName,userLastName,userEmail,userId;
    static int userAge;
    static long userContact;
    public UserIdGUI(){
        frame = new JFrame("POST OFFICE MANAGEMENT SYSTEM");
        ImageIcon icon = new ImageIcon("C:\\Users\\SWAROOP\\IdeaProjects\\Post Office Management System\\indiapostlogo.png");
        JLabel lbl = new JLabel(icon);
        lbl.setBounds(110,0,343,117);

        JPanel panel = new JPanel();


        JLabel lbl3 = new JLabel("Email ID -");
        lbl3.setBounds(54,36,150,20);

        tf1 = new JTextField();
        tf1.setBounds(134,32,250,26);

        JLabel lbl4 = new JLabel("Password -");
        lbl4.setBounds(54,71,150,20);

        tf2 = new JPasswordField();
        tf2.setBounds(134,71,250,26);

        JRadioButton admin = new JRadioButton("admin");
        admin.setBounds(134,128,70,20);
        admin.setSelected(true);
        admin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf1.setText("");
                tf2.setText("");
            }
        });
        admin.setSelected(true);
        JRadioButton member = new JRadioButton("member");
        member.setBounds(214,128,100,20);
        member.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf1.setText("");
                tf2.setText("");
            }
        });

        ButtonGroup radiogroup = new ButtonGroup();
        radiogroup.add(admin);
        radiogroup.add(member);

        checkBox = new JCheckBox("Forgot Password?",false);
        checkBox.setBounds(136,154,150,30);
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (member.isSelected()){
                    class memberForgetPass {
                        JFrame frame;
                        JTextField tf1;
                        JLabel lbl1 = new JLabel("");
                        JLabel lbl3 = new JLabel("");
                        JLabel lbl4 = new JLabel("");
                        public memberForgetPass(){
                            frame = new JFrame("Forget Password");
                            lbl1.setText("Employee's ID confirmation");
                            lbl1.setFont(new Font("Verdana", Font.BOLD, 18));
                            lbl1.setBounds(100,0,300,30);

                            lbl3.setText("Employee's ID -");
                            lbl3.setBounds(55,97,90,20);

                            tf1 = new JTextField();
                            tf1.setBounds(159,93,230,26);


                            JButton btn2 = new JButton("BACK");
                            btn2.setBounds(255,160,80,25);
                            btn2.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    frame.dispose();
                                    UserIdGUI.checkBox.setSelected(false);
                                }
                            });

                            JButton btn1 = new JButton("SUBMIT");
                            btn1.setBounds(344,160,80,25);
                            btn1.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String forgetPassId = (String) tf1.getText();
                                    try {
                                        Class.forName("com.mysql.cj.jdbc.Driver");
                                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post_office_data?",
                                                "root","Sw@roop2001");
                                        String str = "select * from members where empId ='"+forgetPassId+"'";
                                        Statement statement = con.createStatement();
                                        ResultSet rs = statement.executeQuery(str);

                                        if(rs.next()){

                                            lbl4.setText("Request Sent!!");
                                            lbl4.setBounds(200,128,150,20);
                                            JOptionPane.showMessageDialog(frame,"Request sent for confimation","Message",JOptionPane.INFORMATION_MESSAGE);
                                            Timer t = new Timer(2000, new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    lbl4.setText(null);
                                                    tf1.setText("");
                                                }
                                            });
                                            t.setRepeats(false);
                                            t.start();
                                            String query1 = "insert into request (Id,requeststatus,role) values (?,?,'Member')";
                                            PreparedStatement s1 = con.prepareStatement(query1);
                                            s1.setString(1,forgetPassId);
                                            s1.setInt(2,1);
                                            s1.executeUpdate();

                                        }else {
                                            lbl4.setText("Invalid Id");
                                            lbl4.setBounds(200,128,150,20);
                                            JOptionPane.showMessageDialog(frame,"Invalid ID","Message",JOptionPane.INFORMATION_MESSAGE);
                                            Timer t = new Timer(2000, new ActionListener() {

                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    lbl4.setText(null);
                                                }
                                            });
                                            t.setRepeats(false);
                                            t.start();
                                        }
                                        con.close();

                                    } catch (ClassNotFoundException | SQLException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            });


                            frame.add(lbl1);
                            frame.add(lbl3);
                            frame.add(tf1);
                            frame.add(lbl4);
                            frame.add(btn2);
                            frame.add(btn1);
                            frame.setResizable(false);

                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.setLayout(null);
                            frame.setSize(495,283);
                            frame.setVisible(true);
                        }
                    }
                    new memberForgetPass();
                }else {
                    class adminForgetPass {
                        JFrame frame;
                        JTextField tf1;
                        JLabel lbl1 = new JLabel("");
                        JLabel lbl3 = new JLabel("");
                        JLabel lbl4 = new JLabel("");
                        public adminForgetPass(){
                            frame = new JFrame("Forget Password");
                            lbl1.setText("Admin's ID confirmation");
                            lbl1.setFont(new Font("Verdana", Font.BOLD, 18));
                            lbl1.setBounds(100,0,300,30);

                            lbl3.setText("Admin's ID -");
                            lbl3.setBounds(55,97,90,20);

                            tf1 = new JTextField();
                            tf1.setBounds(159,93,230,26);


                            JButton btn2 = new JButton("BACK");
                            btn2.setBounds(255,160,80,25);
                            btn2.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    frame.dispose();
                                    UserIdGUI.checkBox.setSelected(false);
                                }
                            });

                            JButton btn1 = new JButton("SUBMIT");
                            btn1.setBounds(344,160,80,25);
                            btn1.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String forgetPassId = (String) tf1.getText();
                                    try {
                                        Class.forName("com.mysql.cj.jdbc.Driver");
                                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post_office_data?",
                                                "root","Sw@roop2001");
                                        String str = "select * from admin where adminId ='"+forgetPassId+"'";
                                        Statement statement = con.createStatement();
                                        ResultSet rs = statement.executeQuery(str);

                                        if(rs.next()){
                                            lbl4.setText("Request Sent!!");
                                            lbl4.setBounds(200,128,150,20);
                                            JOptionPane.showMessageDialog(frame,"Request sent for confimation","Message",JOptionPane.INFORMATION_MESSAGE);
                                            Timer t = new Timer(2000, new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    lbl4.setText(null);
                                                    tf1.setText("");
                                                }
                                            });
                                            t.setRepeats(false);
                                            t.start();
                                            String query1 = "insert into request (Id,requeststatus,role) values (?,?,'Admin')";
                                            PreparedStatement s1 = con.prepareStatement(query1);
                                            s1.setString(1,forgetPassId);
                                            s1.setInt(2,1);
                                            s1.executeUpdate();
                                        }else {
                                            lbl4.setText("Invalid Id");
                                            lbl4.setBounds(200,128,150,20);
                                            JOptionPane.showMessageDialog(frame,"Invalid ID","Message",JOptionPane.INFORMATION_MESSAGE);
                                            Timer t = new Timer(2000, new ActionListener() {

                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    lbl4.setText(null);
                                                }
                                            });
                                            t.setRepeats(false);
                                            t.start();
                                        }
                                        con.close();

                                    } catch (ClassNotFoundException | SQLException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            });


                            frame.add(lbl1);
                            frame.add(lbl3);
                            frame.add(tf1);
                            frame.add(lbl4);
                            frame.add(btn2);
                            frame.add(btn1);
                            frame.setResizable(false);

                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.setLayout(null);
                            frame.setSize(495,283);
                            frame.setVisible(true);
                        }
                    }
                    new adminForgetPass();
                }


            }
        });



        panel.add(lbl3);
        panel.add(tf1);
        panel.add(lbl4);
        panel.add(tf2);
        panel.add(admin);
        panel.add(member);
        panel.add(checkBox);
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(465, 200));
        panel.setMaximumSize(new Dimension(465, 200));
        panel.setSize(435,200);
        TitledBorder border = new TitledBorder("SIGN IN");
        border.setTitleFont(new Font("Arial", Font.BOLD, 16));
        panel.setBorder(border);
        panel.setLocation(75,140);

        JLabel invalidCred = new JLabel("");
        JButton btn1 = new JButton("OK");
        btn1.setBounds(380,360,60,25);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = (String) tf1.getText();
                String pass = String.valueOf(tf2.getPassword());
                boolean flag = Objects.equals(email, "") && pass.equals("");
                if(member.isSelected()){
                    if(flag){
                        JOptionPane.showMessageDialog(frame,"Both field isEmpty");
                    } else if(Objects.equals(email, "")){
                        JOptionPane.showMessageDialog(frame,"Email field isEmpty");
                    } else  if(pass.equals("")){
                        JOptionPane.showMessageDialog(frame,"Password field isEmpty");
                    }
                    else {
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post_office_data?",
                                    "root","Sw@roop2001");
                            String str = "select * from members where email ='"+email+"' and password = '"+pass +"'";
                            Statement statement = con.createStatement();
                            ResultSet rs = statement.executeQuery(str);
                            if(rs.next()){
                                userFirstName = rs.getString("firstname");
                                userLastName = rs.getString("lastname");
                                userEmail = rs.getString("email");
                                userAge = rs.getInt("age");
                                userContact = rs.getLong("contact");
                                userId = rs.getString("empId");
                                new DashBoard();

                            }else {
                                invalidCred.setText("Invalid User");
                                invalidCred.setFont(new Font("Verdana", Font.PLAIN, 12));
                                invalidCred.setBounds(240,207,200,20);
                                Timer t = new Timer(2000, new ActionListener() {

                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        invalidCred.setText(null);
                                    }
                                });
                                t.setRepeats(false);
                                t.start();
                                UserIdGUI.tf1.setText("");
                                UserIdGUI.tf1.getCursor();
                                UserIdGUI.tf2.setText("");
                            }
                            con.close();

                        } catch (ClassNotFoundException | SQLException ex) {
                            ex.printStackTrace();
                        }

                    }
                } else if (admin.isSelected()){
                    if(flag){
                        JOptionPane.showMessageDialog(frame,"Both field isEmpty");
                    } else if(Objects.equals(email, "")){
                        JOptionPane.showMessageDialog(frame,"Email field isEmpty");
                    } else  if(pass.equals("")){
                        JOptionPane.showMessageDialog(frame,"Password field isEmpty");
                    }
                    else {
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post_office_data?",
                                    "root","Sw@roop2001");
                            String str = "select * from admin where email ='"+email+"' and password = '"+pass +"'";
                            Statement statement = con.createStatement();
                            ResultSet rs = statement.executeQuery(str);
                            if(rs.next()){
                                userFirstName = rs.getString("firstname");
                                userLastName = rs.getString("lastname");
                                userEmail = rs.getString("email");
                                userAge = rs.getInt("age");
                                userContact = rs.getLong("contact");
                                userId = rs.getString("adminId");
                                new Admin();
                            }else {
                                invalidCred.setText("Invalid Admin");
                                invalidCred.setFont(new Font("Verdana", Font.PLAIN, 12));
                                invalidCred.setBounds(240,207,200,20);
                                Timer t = new Timer(2000, new ActionListener() {

                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        invalidCred.setText(null);
                                    }
                                });
                                t.setRepeats(false);
                                t.start();
                                UserIdGUI.tf1.setText("");
                                UserIdGUI.tf2.setText("");
                            }
                            con.close();

                        } catch (ClassNotFoundException | SQLException ex) {
                            ex.printStackTrace();
                        }

                    }
                }
            }
        });
        JButton btn2 = new JButton("EXIT");
        btn2.setBounds(449,360,60,25);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.add(lbl);
        frame.add(invalidCred);
        frame.add(btn1);
        frame.add(btn2);
        frame.getContentPane().add(panel);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(600,500);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new UserIdGUI();
    }
}
