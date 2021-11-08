import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAcc  {
    JFrame frame;
    JTextField idInput;
    JButton btn1,btn2;
    JLabel lbl1 = new JLabel("");
    JLabel lbl3 = new JLabel("");
    public DeleteAcc(){
        frame = new JFrame("Delete Account");
        lbl1.setText("Delete Account");
        lbl1.setFont(new Font("Verdana", Font.BOLD, 18));
        lbl1.setBounds(163,0,300,20);

        lbl3.setText("Employee's ID -");
        lbl3.setBounds(65,95,90,20);

        idInput = new JTextField();
        idInput.setBounds(177,92,220,26);


        btn2 = new JButton("BACK");
        btn2.setBounds(255,160,80,25);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                UserIdGUI.checkBox.setSelected(false);
            }
        });

        btn1 = new JButton("DELETE");
        btn1.setBounds(344,160,80,25);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(idInput.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame,"Text field isEmpty");
                } else {
                    try {
                        String id = idInput.getText();
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post_office_data?",
                                "root","Sw@roop2001");
                        String str = "delete from members where empId =?";
                        PreparedStatement statement = con.prepareStatement(str);
                        statement.setString(1,id);


                        statement.executeUpdate();
                        con.close();

                    } catch (ClassNotFoundException | SQLException ex) {
                        System.out.println("Unable to load driver");
                    }
                    JOptionPane.showMessageDialog(frame,"Are you sure?");
                    JOptionPane.showMessageDialog(frame,"Account Deleted","Message",JOptionPane.INFORMATION_MESSAGE);

                }

            }
        });


        frame.add(lbl1);
        frame.add(lbl3);
        frame.add(idInput);
        frame.add(btn2);
        frame.add(btn1);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(495,343);
        frame.setVisible(true);
    }
}