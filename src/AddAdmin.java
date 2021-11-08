import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.Random;

public class AddAdmin{
    JFrame frame1;
    JPanel panel;
    JButton createBtn,deleteBtn;
    JLabel firstName,lastName,email,age,contact,pass;
    JTextField fnameInput,lnameInput,emailInput,ageInput,contactInput;
    JPasswordField passInput;
    public AddAdmin(){
        frame1 = new JFrame("Create New Admin");
        JLabel heading = new JLabel("ADMIN");
        heading.setFont(new Font("Verdana", Font.BOLD, 16));
        heading.setBounds(200,0,300,20);


        panel = new JPanel();
        firstName  = new JLabel("First Name -");
        firstName.setBounds(20,28,110,20);
        fnameInput = new JTextField();
        fnameInput.setBounds(100,26,116,25);

        lastName  = new JLabel("Last Name - ");
        lastName.setBounds(249,28,110,20);
        lnameInput = new JTextField();
        lnameInput.setBounds(331,26,116,25);

        email  = new JLabel("Email -");
        email.setBounds(20,68,50,20);
        emailInput = new JTextField();
        emailInput.setBounds(100,66,210,25);

        age  = new JLabel("Age -");
        age.setBounds(331,68,50,20);
        ageInput = new JTextField();
        ageInput.setBounds(380,66,68,25);
        ageInput.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                if(!(Character.isDigit(evt.getKeyChar()))){
                    evt.consume();
                }
            }
        });

        contact  = new JLabel("Contact - ");
        contact.setBounds(20,108,80,20);
        contactInput = new JTextField();
        contactInput.setBounds(100,106,116,25);
        contactInput.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                if(!(Character.isDigit(evt.getKeyChar()))){
                    evt.consume();
                }
            }
        });

        pass  = new JLabel("Password -");
        pass.setBounds(249,108,80,20);
        passInput = new JPasswordField();
        passInput.setBounds(331,106,116,25);

        panel.add(firstName);
        panel.add(fnameInput);
        panel.add(lastName);
        panel.add(lnameInput);
        panel.add(email);
        panel.add(emailInput);
        panel.add(age);
        panel.add(ageInput);
        panel.add(contact);
        panel.add(contactInput);
        panel.add(pass);
        panel.add(passInput);
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(465, 150));
        panel.setMaximumSize(new Dimension(465, 160));
        panel.setSize(465,150);
        TitledBorder border = new TitledBorder("Add Admins");
        border.setTitleFont(new Font("Arial", Font.BOLD, 16));
        panel.setBorder(border);
        panel.setLocation(60,125);


        JButton adminbtn = new JButton("Admins");
        adminbtn.setBounds(60,350,80,25);
        adminbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                class DisplayAdminData extends JFrame implements ActionListener {
                    JLabel l0, l1, l2;
                    JTextField tf1,tf2;
                    JButton b1,b2;
                    PreparedStatement pst;
                    static JTable table;
                    String[] columnNames = { "FirstName", "LastName",
                            "Age", "Contact", "Email"};
                    DisplayAdminData() {
                        l0 = new JLabel("Fetch Admin Information");
                        l0.setBounds(100, 0, 350, 40);
                        l0.setFont(new Font("Verdana", Font.BOLD, 18));
                        l1 = new JLabel("First Name");
                        l1.setBounds(110, 55, 100, 20);
                        l1.setForeground(Color.BLUE);
                        tf1 = new JTextField();
                        tf1.setBounds(220, 53, 150, 25);
                        l2 = new JLabel("Last Name");
                        l2.setBounds(110, 95, 100, 20);
                        l2.setForeground(Color.BLUE);
                        tf2 = new JTextField();
                        tf2.setBounds(220, 93, 150, 25);
                        b1 = new JButton("Search");
                        b1.setBounds(245, 140, 80, 25);
                        b1.addActionListener(this);
                        b2 = new JButton("Back");
                        b2.setBounds(150, 140, 80, 25);
                        b2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                dispose();
                            }
                        });
                        setTitle("Admin Details");
                        setLayout(null);
                        setVisible(true);
                        setSize(500, 240);
                        add(l0);
                        add(l1);
                        add(tf1);
                        add(l2);
                        add(tf2);
                        add(b1);
                        add(b2);
                        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    }
                    public void actionPerformed(ActionEvent ae) {
                        if (ae.getSource() == b1) {
                            showTableData();
                        }
                    }
                    public void showTableData() {
                        JFrame frame1 = new JFrame("Admin Details");
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
                        String firstName = tf1.getText();
                        String lastName = tf2.getText();
                        int age;
                        Long contact;
                        String email = "";
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post_office_data?",
                                    "root","Sw@roop2001");
                            pst = con.prepareStatement("select * from admin where firstname='" + firstName + "' and lastname='" + lastName + "'" );
                            ResultSet rs = pst.executeQuery();
                            int i = 0;
                            while (rs.next()) {
                                age = rs.getInt("age");
                                contact = rs.getLong("contact");
                                email = rs.getString("email");
                                model.addRow(new Object[]{firstName,lastName,age,contact, email});
                                i++;
                            }
                            tf1.setText("");
                            tf2.setText("");
                            if (i < 1) {
                                JOptionPane.showMessageDialog(frame1, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            else if (i == 1) {
                                JOptionPane.showMessageDialog(frame1, "1 Record Found", "Message", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(frame1, i+" Record Found", "Message", JOptionPane.INFORMATION_MESSAGE);
                            }
                            pst.close();
                            rs.close();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        frame1.add(scroller);
                        frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        frame1.setVisible(true);
                        frame1.setSize(1200, 300);
                    }
                }
                new DisplayAdminData();
            }
        });

        createBtn = new JButton("CREATE");
        createBtn.setBounds(469,350,80,25);
        createBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(fnameInput.getText().isEmpty() || ageInput.getText().isEmpty() || emailInput.getText().isEmpty() ||
                        contactInput.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame1,"Text field isEmpty");
                } else {
                    try {
                        String adminId = String.format("1%03d", new Random().nextInt(1000));
                        String fName = fnameInput.getText();
                        String lName = lnameInput.getText();
                        int age = Integer.parseInt(ageInput.getText());
                        long contact = Long.parseLong(contactInput.getText());
                        String email = emailInput.getText();
                        String password = String.copyValueOf(passInput.getPassword());
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post_office_data?",
                                "root","Sw@roop2001");
                        String str = "insert into admin values (?,?,?,?,?,?,?)";
                        PreparedStatement statement = con.prepareStatement(str);
                        statement.setString(1,adminId);
                        statement.setString(2,fName);
                        statement.setString(3,lName);
                        statement.setInt(4,age);
                        statement.setLong(5,contact);
                        statement.setString(6,email);
                        statement.setString(7,password);


                        statement.executeUpdate();
                        con.close();
                        JOptionPane.showMessageDialog(null,"Admin Id - "+adminId,"Message",JOptionPane.INFORMATION_MESSAGE);
                        fnameInput.setText("");
                        lnameInput.setText("");
                        emailInput.setText("");
                        ageInput.setText("");
                        contactInput.setText("");
                        passInput.setText("");
                        JOptionPane.showMessageDialog(null,"Admin's Id -"+adminId,"Message",JOptionPane.INFORMATION_MESSAGE);
                    } catch (ClassNotFoundException | SQLException ex) {
                        System.out.println("Unable to load driver");
                    }
                }
            }
        });


        //delete acc
        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(416,30,80,25);
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                class DeleteAcc  {
                    JFrame frame1;
                    JTextField nameInput;
                    JButton btn1,btn2;
                    JLabel lbl1 = new JLabel("");
                    JLabel lbl3 = new JLabel("");
                    JLabel lbl4 = new JLabel("");
                    public DeleteAcc(){
                        frame1 = new JFrame("Delete Account");
                        lbl1.setText("Delete Account");
                        lbl1.setFont(new Font("Verdana", Font.BOLD, 18));
                        lbl1.setBounds(163,0,300,20);

                        lbl3.setText("Admin's ID -");
                        lbl3.setBounds(65,95,90,20);

                        nameInput = new JTextField();
                        nameInput.setBounds(177,92,220,26);


                        btn2 = new JButton("BACK");
                        btn2.setBounds(255,160,80,25);
                        btn2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                frame1.dispose();
                                UserIdGUI.checkBox.setSelected(false);
                            }
                        });

                        btn1 = new JButton("DELETE");
                        btn1.setBounds(344,160,80,25);
                        btn1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                if(nameInput.getText().isEmpty()){
                                    JOptionPane.showMessageDialog(frame1,"Text field isEmpty");
                                } else {
                                    try {
                                        String name = nameInput.getText();
                                        Class.forName("com.mysql.cj.jdbc.Driver");
                                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post_office_data?",
                                                "root","Sw@roop2001");
                                        String str = "delete from admin where adminId =?";
                                        PreparedStatement statement = con.prepareStatement(str);
                                        statement.setString(1,name);


                                        statement.executeUpdate();
                                        con.close();

                                    } catch (ClassNotFoundException | SQLException ex) {
                                        System.out.println("Unable to load driver");
                                    }
                                    JOptionPane.showMessageDialog(frame1,"Are you sure?");
                                    JOptionPane.showMessageDialog(frame1,"Account Deleted","Message",JOptionPane.INFORMATION_MESSAGE);
                                    nameInput.setText("" +
                                            "");
                                }

                            }
                        });


                        frame1.add(lbl1);
                        frame1.add(lbl3);
                        frame1.add(nameInput);
                        frame1.add(lbl4);
                        frame1.add(btn2);
                        frame1.add(btn1);
                        frame1.setResizable(false);
                        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame1.setLayout(null);
                        frame1.setSize(495,343);
                        frame1.setVisible(true);
                    }
                }
                new DeleteAcc();
            }
        });


        JButton backbtn = new JButton("BACK");
        backbtn.setBounds(374,350,80,25);
        backbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.dispose();
            }
        });


        frame1.add(heading);
        frame1.add(adminbtn);
        frame1.add(deleteBtn);
        frame1.add(backbtn);
        frame1.add(createBtn);
        frame1.getContentPane().add(panel);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setResizable(false);
        frame1.setLayout(null);
        frame1.setSize(600,500);
        frame1.setVisible(true);
    }

}