import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;

public class Admin {
    JFrame frame;
    JButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8;
    public Admin(){
        frame = new JFrame("Admin Section");
        JLabel heading = new JLabel("ADMIN DASHBOARD");
        heading.setFont(new Font("Verdana", Font.BOLD, 16));
        heading.setBounds(200,0,300,20);

        btn1 = new JButton("MEMBERS");
        btn1.setBounds(160,87,120,50);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Members();
            }
        });
        btn2 = new JButton("MESSAGE BOX");
        btn2.setBounds(300,87,120,50);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MessageBox();
            }
        });
        btn3 = new JButton("SEARCH");
        btn3.setBounds(160,159,120,50);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DisplayParcelData();
            }
        });
        btn4 = new JButton("PROFILE");
        btn4.setBounds(300,159,120,50);
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                class AdminProfile{
                    JFrame frame;
                    public AdminProfile(){
                        frame = new JFrame("PROFILE");
                        JLabel heading  = new JLabel("PROFILE");
                        heading.setFont(new Font("Verdana", Font.BOLD, 18));
                        heading.setBounds(260,0,200,25);
                        JPanel panel = new JPanel();
                        JLabel firstName  = new JLabel("First Name -");
                        firstName.setBounds(20,28,110,20);
                        JLabel fNameInput = new JLabel("");
                        fNameInput.setFont(new Font("Verdana", Font.PLAIN, 14));
                        fNameInput.setText(UserIdGUI.userFirstName);
                        fNameInput.setBounds(120,26,116,25);

                        JLabel lastName  = new JLabel("Last Name - ");
                        lastName.setBounds(249,28,110,20);
                        JLabel lNameInput = new JLabel("");
                        lNameInput.setFont(new Font("Verdana", Font.PLAIN, 14));
                        lNameInput.setText(UserIdGUI.userLastName);
                        lNameInput.setBounds(351,26,116,25);

                        JLabel email  = new JLabel("Email -");
                        email.setBounds(20,68,70,20);
                        JLabel emailInput = new JLabel("");
                        emailInput.setFont(new Font("Verdana", Font.PLAIN, 14));
                        emailInput.setText(UserIdGUI.userEmail);
                        emailInput.setBounds(120,66,300,25);

                        JLabel contact  = new JLabel("Contact - ");
                        contact.setBounds(20,108,80,20);
                        JLabel contactInput = new JLabel("");
                        contactInput.setFont(new Font("Verdana", Font.PLAIN, 14));
                        contactInput.setText(String.valueOf(UserIdGUI.userContact));
                        contactInput.setBounds(120,106,116,25);

                        JLabel adminId  = new JLabel("User ID -");
                        adminId.setBounds(249,108,100,20);
                        JLabel idInput = new JLabel("");
                        idInput.setFont(new Font("Verdana", Font.PLAIN, 14));
                        idInput.setText(UserIdGUI.userId);
                        idInput.setBounds(351,106,116,25);

                        panel.add(firstName);
                        panel.add(fNameInput);
                        panel.add(lastName);
                        panel.add(lNameInput);
                        panel.add(email);
                        panel.add(emailInput);
                        panel.add(contact);
                        panel.add(contactInput);
                        panel.add(adminId);
                        panel.add(idInput);
                        panel.setLayout(null);
                        panel.setPreferredSize(new Dimension(465, 150));
                        panel.setMaximumSize(new Dimension(465, 160));
                        TitledBorder border = new TitledBorder("User Information");
                        border.setTitleFont(new Font("Arial", Font.BOLD, 16));
                        panel.setBorder(border);
                        panel.setSize(465,150);
                        panel.setLocation(60,126);

                        JButton resetBtn = new JButton("Reset Password");
                        resetBtn.setBounds(60,350,130,25);
                        resetBtn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                class adminNewPass {
                                    JFrame frame;

                                    public adminNewPass() {
                                        frame = new JFrame("Set Password");
                                        JLabel lbl1 = new JLabel("Change Password");
                                        lbl1.setFont(new Font("Verdana", Font.BOLD, 18));
                                        lbl1.setBounds(148, 0, 300, 25);
                                        JLabel lbl2 = new JLabel("Current Password -");
                                        lbl2.setBounds(50, 80, 150, 20);
                                        JTextField tf1 = new JTextField();
                                        tf1.setBounds(190, 75, 250, 26);

                                        JLabel lbl3 = new JLabel("New Password -");
                                        lbl3.setBounds(50, 130, 150, 20);
                                        JTextField tf2 = new JTextField();
                                        tf2.setBounds(190, 125, 250, 26);
                                        JLabel lbl4 = new JLabel("Confirm Password -");
                                        lbl4.setBounds(50, 180, 150, 20);
                                        JPasswordField tf3 = new JPasswordField();
                                        tf3.setBounds(190, 175, 250, 26);

                                        JButton btn1 = new JButton("SUBMIT");
                                        btn1.setBounds(300, 250, 80, 30);
                                        btn1.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                String currentPass = tf1.getText();
                                                String newPass = tf2.getText();
                                                String confirmPass = String.copyValueOf(tf3.getPassword());
                                                try {
                                                    Class.forName("com.mysql.cj.jdbc.Driver");
                                                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post_office_data?",
                                                            "root","Sw@roop2001");
                                                    String str = "select * from admin where password ='"+currentPass+"'";
                                                    Statement statement = con.createStatement();
                                                    ResultSet rs = statement.executeQuery(str);

                                                    if(rs.next()){
                                                        String adminId = rs.getString("adminId");
                                                        if (Objects.equals(newPass, confirmPass)){
                                                            String query1 = "update admin set password ='"+confirmPass+"' where adminId = '"+adminId+"'";
                                                            PreparedStatement s1 = con.prepareStatement(query1);
                                                            s1.executeUpdate();
                                                            JOptionPane.showMessageDialog(null,"Password changed successfully","Message",JOptionPane.INFORMATION_MESSAGE);
                                                        }else {
                                                            JOptionPane.showMessageDialog(null,"Confirm Password doesn't match","Message",JOptionPane.INFORMATION_MESSAGE);
                                                        }

                                                    }else {
                                                        JOptionPane.showMessageDialog(frame,"Invalid current password","Message",JOptionPane.INFORMATION_MESSAGE);

                                                    }
                                                    tf1.setText("");
                                                    tf2.setText("");
                                                    tf3.setText("");
                                                    con.close();

                                                } catch (ClassNotFoundException | SQLException ex) {
                                                    ex.printStackTrace();
                                                }
                                            }
                                        });
                                        JButton btn2 = new JButton("BACK");
                                        btn2.setBounds(330, 250, 80, 30);

                                        frame.add(lbl1);
                                        frame.add(lbl2);
                                        frame.add(lbl3);
                                        frame.add(tf1);
                                        frame.add(lbl4);
                                        frame.add(tf2);
                                        frame.add(tf3);
                                        frame.add(btn1);

                                        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                                        frame.setLayout(null);
                                        frame.setSize(500, 400);
                                        frame.setVisible(true);
                                    }
                                }
                                new adminNewPass();
                            }
                        });
                        JButton backBtn = new JButton("BACK");
                        backBtn.setBounds(445,350,80,25);
                        backBtn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                frame.dispose();
                            }
                        });

                        frame.add(heading);
                        frame.getContentPane().add(panel);
                        frame.add(resetBtn);
                        frame.add(backBtn);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setResizable(false);
                        frame.setLayout(null);
                        frame.setSize(600,500);
                        frame.setVisible(true);
                    }

                }
                new AdminProfile();
            }
        });
        btn5 = new JButton("ADD ADMIN");
        btn5.setBounds(160,231,120,50);
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddAdmin();
            }
        });
        btn6 = new JButton("SETTINGS");
        btn6.setBounds(300,231,120,50);
        btn7 = new JButton("BACK");
        btn7.setBounds(160,303,120,50);
        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                UserIdGUI.tf1.setText("");
                UserIdGUI.tf2.setText("");
            }
        });
        btn8 = new JButton("EXIT");
        btn8.setBounds(300,303,120,50);
        btn8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                UserIdGUI.frame.dispose();
            }
        });

        frame.add(heading);
        frame.add(btn1);
        frame.add(btn2);
        frame.add(btn3);
        frame.add(btn4);
        frame.add(btn5);
        frame.add(btn6);
        frame.add(btn7);
        frame.add(btn8);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(600,500);
        frame.setVisible(true);
        JOptionPane.showMessageDialog(frame,"Login Successful","Message",JOptionPane.INFORMATION_MESSAGE);
    }
}