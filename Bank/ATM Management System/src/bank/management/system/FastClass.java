package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class FastClass extends JFrame implements ActionListener {
    JButton b1,b2,b3,b4,b5,b6,b7;
    String pin;

    FastClass(String pin){
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1550,830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new  JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label = new JLabel("Select Withdrawal Amount");
        label.setBounds(430,240,700,35);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Raleway",Font.BOLD,28));
        l3.add(label);

        b1 = new JButton("Rs. 100");
        b1.setBounds(410,338,150,35);
        b1.setBackground( Color.WHITE);
        b1.setForeground(Color.BLACK);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("Rs. 500");
        b2.setBounds(700,338,150,35);
        b2.setBackground( Color.WHITE);
        b2.setForeground(Color.BLACK);
        b2.addActionListener(this);
        l3.add(b2);

        b3 = new JButton("Rs. 1000");
        b3.setBounds(410,400,150,35);
        b3.setBackground( Color.WHITE);
        b3.setForeground(Color.BLACK);
        b3.addActionListener(this);
        l3.add(b3);

        b4 = new JButton("Rs. 2000");
        b4.setBounds(700,400,150,35);
        b4.setBackground( Color.WHITE);
        b4.setForeground(Color.BLACK);
        b4.addActionListener(this);
        l3.add(b4);

        b5 = new JButton("Rs. 5000");
        b5.setBounds(410,462,150,35);
        b5.setBackground( Color.WHITE);
        b5.setForeground(Color.BLACK);
        b5.addActionListener(this);
        l3.add(b5);

        b6 = new JButton("Rs. 10,000");
        b6.setBounds(700,462,150,35);
        b6.setBackground( Color.WHITE);
        b6.setForeground(Color.BLACK);
        b6.addActionListener(this);
        l3.add(b6);

        b7 = new JButton("BACK");
        b7.setBounds(560,524,150,35);
        b7.setBackground( Color.WHITE);
        b7.setForeground(Color.BLACK);
        b7.addActionListener(this);
        l3.add(b7);

        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b7){
            setVisible(false);
            new main_Class(pin);
        }else{
            String amount = ((JButton)e.getSource()).getText().substring(4);
            Con c = new Con();
            Date date = new Date();
            try{
                ResultSet resultSet = c.statement.executeQuery("select * from bank where pin = '"+pin+"'");
                int balance = 0;
                while(resultSet.next()){
                    if(resultSet.getString("type").equals("Deposit")){
                        balance += Integer.parseInt(resultSet.getString("amount"));
                    }else{
                        balance -= Integer.parseInt(resultSet.getString("amount"));
                    }
                }
                if(e.getSource() != b7 && balance < Integer.parseInt(amount)){
                    JOptionPane.showMessageDialog(null,"Insufficient balance");
                    return;
                }
                c.statement.executeUpdate("insert into bank values('"+pin+"','"+date+"','withdrawal','"+amount+"')");
                JOptionPane.showMessageDialog(null,"Rs."+amount+" Debited Successfully");
            }catch(Exception E){
                E.printStackTrace();
            }
            setVisible(false);
            new main_Class(pin);
        }

    }

    public static void main(String [] args){
        new FastClass("");
    }
}
