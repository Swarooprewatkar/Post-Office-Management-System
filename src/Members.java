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
public class Members{
    JFrame frame1;
    JPanel panel;
    JLabel firstName,lastName,email,age,contact,pass;
    JTextField fnameInput,lnameInput,emailInput,ageInput,contactInput;
    JPasswordField passInput;
    public Members(){
        frame1 = new JFrame("Create New User");
        JLabel heading = new JLabel("MEMBERS");
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
        TitledBorder border = new TitledBorder("Add Member");
        border.setTitleFont(new Font("Arial", Font.BOLD, 16));
        panel.setBorder(border);
        panel.setLocation(60,126);


        JButton memberbtn = new JButton("Employees");
        memberbtn.setBounds(60,350,120,25);
        memberbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                class DisplayEmpData extends JFrame implements ActionListener {
                    JLabel l0, l1, l2;
                    JTextField tf1,tf2;
                    JButton b1,b2;
                    PreparedStatement pst;
                    static JTable table;
                    String[] columnNames = {"EmployeeId", "FirstName", "LastName",
                                                       "Age", "Contact", "Email"};
                    DisplayEmpData() {
                        l0 = new JLabel("Fetch Employee Information");
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
                        setTitle("Employee Details");
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
                        JFrame frame1 = new JFrame("Employee Details");
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
                        String empID = "";
                        int age;
                        Long contact;
                        String email = "";
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post_office_data?",
                                    "root","Sw@roop2001");
                            pst = con.prepareStatement("select * from members where firstname='" + firstName + "' and lastname='" + lastName + "'" );
                            ResultSet rs = pst.executeQuery();
                            int i = 0;
                            while (rs.next()) {
                                empID = rs.getString("empId");
                                age = rs.getInt("age");
                                contact = rs.getLong("contact");
                                email = rs.getString("email");
                                model.addRow(new Object[]{empID,firstName,lastName,age,contact, email});
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
                new DisplayEmpData();
            }
        });

        JButton createBtn = new JButton("CREATE");
        createBtn.setBounds(469,350,80,25);
        createBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(fnameInput.getText().isEmpty() || ageInput.getText().isEmpty() || emailInput.getText().isEmpty() ||
                        contactInput.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame1,"Text field isEmpty");
                } else {
                    try {
                        String empId = String.format("7%07d", new Random().nextInt(10000000));
                        String fName = fnameInput.getText();
                        String lName = lnameInput.getText();
                        int age = Integer.parseInt(ageInput.getText());
                        long contact = Long.parseLong(contactInput.getText());
                        String email = emailInput.getText();
                        String password = String.copyValueOf(passInput.getPassword());
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post_office_data?",
                                "root","Sw@roop2001");
                        String str = "insert into members values (?,?,?,?,?,?,?)";
                        PreparedStatement statement = con.prepareStatement(str);
                        statement.setString(1,empId);
                        statement.setString(2,fName);
                        statement.setString(3,lName);
                        statement.setInt(4,age);
                        statement.setLong(5,contact);
                        statement.setString(6,email);
                        statement.setString(7,password);


                        statement.executeUpdate();
                        con.close();
                        fnameInput.setText("");
                        lnameInput.setText("");
                        emailInput.setText("");
                        ageInput.setText("");
                        contactInput.setText("");
                        passInput.setText("");
                        JOptionPane.showMessageDialog(null,"Employee's ID -"+empId,"Message",JOptionPane.INFORMATION_MESSAGE);
                    } catch (ClassNotFoundException | SQLException ex) {
                        System.out.println("Unable to load driver");
                    }
                }
            }
        });


        //delete acc
        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(416,30,80,25);
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        frame1.add(backbtn);
        frame1.add(memberbtn);
        frame1.add(createBtn);
        frame1.add(deleteBtn);
        frame1.getContentPane().add(panel);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setResizable(false);
        frame1.setLayout(null);
        frame1.setSize(600,500);
        frame1.setVisible(true);
    }

}