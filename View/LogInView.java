package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LogInView extends JPanel {
    private JTextField username;
    private JButton btn;

    public LogInView() {
        setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        JLabel lblTitle = new JLabel("Student Registration System", JLabel.CENTER);
        username = new JTextField(15);

        btn = new JButton("Log in");

        panel.add(lblTitle);
        panel.add(username);
        panel.add(btn);
        
        add(panel);
    }

    public String getUsn() {
        return username.getText();
    }
    
    public void setLoginBtn(ActionListener l) {
        btn.addActionListener(l);
    }
}   
