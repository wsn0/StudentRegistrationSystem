package Control;

import Model.*;
import View.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private DBManager db;
    private LogInView login;
    private JFrame frame;

    public Controller() {
        db = new DBManager();
        login = new LogInView();
        
        initView();
        initController();
    }

    private void initView() {
        frame = new JFrame("Student Registration System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        frame.add(login);
        frame.setVisible(true);
    }

    private void initController() {
        login.setLoginBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateLogin();
            }
        });
    }

    private void validateLogin() {
        String usn = login.getUsn();

        if (usn.equals("admin")) {
            Session.setAdm();
        }
        else {
            Students s = db.searchStudent(usn);

            if (s != null) {
                Session.setStd(s);
            }
            else {
                JOptionPane.showMessageDialog(frame, "Unknown Account");
            }
        }

    }
}
