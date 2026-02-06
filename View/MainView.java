package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainView extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    // log in components
    private JTextField txtUsername = new JTextField(15);
    private JPasswordField txtPassword = new JPasswordField(15);
    private JButton btnLogin = new JButton("Login");

    // student list components(Admin)
    private JTable tableAllStudents = new JTable();
    private JButton btnAdminSortName = new JButton("เรียงตามชื่อ");
    private JButton btnAdminSortAge = new JButton("เรียงตามอายุ");
    private JButton btnAdminGoGrade = new JButton("ไปหน้ากรอกเกรด >");

    // grading component(Admin)
    private JComboBox<String> cbSubjects = new JComboBox<>();
    private JLabel lblTotalStudents = new JLabel("จำนวนคนลงทะเบียน: 0 คน");
    private JTable tableGrading = new JTable();
    private JButton btnSaveGrade = new JButton("บันทึกเกรด");
    private JButton btnBackToAdminHome = new JButton("< กลับ");

    // student info component(Student)
    private JLabel lblStudentInfo = new JLabel("ข้อมูลนักเรียน ");
    private JTable tableGradeHistory = new JTable();
    private JButton btnGoRegister = new JButton("ลงทะเบียนเรียนเพิ่ม >");

    // registration component(Student)
    private JTable tableOpenSubjects = new JTable();
    private JButton btnConfirmRegister = new JButton("ยืนยันการลงทะเบียน");
    private JButton btnBackToProfile = new JButton("< กลับ");

    // log out button
    private JButton btnLogout = new JButton("Logout");

    public MainView() {
        setTitle("Student Registration System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel.add(createLoginPanel(), "LOGIN");
        mainPanel.add(createAdminListPanel(), "ADMIN_LIST");
        mainPanel.add(createAdminGradingPanel(), "ADMIN_GRADING");
        mainPanel.add(createStudentProfilePanel(), "STUDENT_PROFILE");
        mainPanel.add(createRegisterPanel(), "STUDENT_REGISTER");

        add(mainPanel);
    }

    //Create page
    private JPanel createLoginPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        JPanel box = new JPanel(new GridLayout(3, 2, 10, 10));
        
        box.add(new JLabel("Username (ID):"));
        box.add(txtUsername);
        box.add(new JLabel("Password:"));
        box.add(txtPassword);
        box.add(new JLabel(""));
        box.add(btnLogin);
        
        p.add(box);
        return p;
    }

    private JPanel createAdminListPanel() {
        JPanel p = new JPanel(new BorderLayout());
        
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
        top.add(new JLabel("=== ADMIN: รายชื่อนักเรียนทั้งหมด ==="));
        p.add(top, BorderLayout.NORTH);

        p.add(new JScrollPane(tableAllStudents), BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.add(btnAdminSortName);
        bottom.add(btnAdminSortAge);
        bottom.add(btnAdminGoGrade);
        bottom.add(btnLogout);
        p.add(bottom, BorderLayout.SOUTH);

        return p;
    }

    private JPanel createAdminGradingPanel() {
        JPanel p = new JPanel(new BorderLayout());

        JPanel top = new JPanel(new FlowLayout());
        top.add(new JLabel("เลือกวิชาที่จะตัดเกรด: "));
        top.add(cbSubjects);
        top.add(Box.createHorizontalStrut(20));
        top.add(lblTotalStudents);
        p.add(top, BorderLayout.NORTH);

        p.add(new JScrollPane(tableGrading), BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.add(btnBackToAdminHome);
        bottom.add(btnSaveGrade);
        p.add(bottom, BorderLayout.SOUTH);

        return p;
    }

    private JPanel createStudentProfilePanel() {
        JPanel p = new JPanel(new BorderLayout());

        JPanel infoPanel = new JPanel();
        infoPanel.setBorder(BorderFactory.createTitledBorder("ข้อมูลส่วนตัว"));
        lblStudentInfo.setFont(new Font("Serif", Font.BOLD, 16));
        infoPanel.add(lblStudentInfo);
        p.add(infoPanel, BorderLayout.NORTH);

        tableGradeHistory.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        p.add(new JScrollPane(tableGradeHistory), BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.add(btnGoRegister);
        bottom.add(btnLogout);
        p.add(bottom, BorderLayout.SOUTH);

        return p;
    }

    private JPanel createRegisterPanel() {
        JPanel p = new JPanel(new BorderLayout());
        
        p.add(new JLabel("รายวิชาที่สามารถลงทะเบียนได้ (เลือกแถวแล้วกดปุ่ม)"), BorderLayout.NORTH);
        p.add(new JScrollPane(tableOpenSubjects), BorderLayout.CENTER);
        
        JPanel bottom = new JPanel();
        bottom.add(btnBackToProfile);
        bottom.add(btnConfirmRegister);
        p.add(bottom, BorderLayout.SOUTH);

        return p;
    }

    public void showLogin() { cardLayout.show(mainPanel, "LOGIN"); }
    public void showAdminList() { cardLayout.show(mainPanel, "ADMIN_LIST"); }
    public void showAdminGrading() { cardLayout.show(mainPanel, "ADMIN_GRADING"); }
    public void showStudentProfile() { cardLayout.show(mainPanel, "STUDENT_PROFILE"); }
    public void showStudentRegister() { cardLayout.show(mainPanel, "STUDENT_REGISTER"); }

    // Getters
    public String getUsername() { return txtUsername.getText(); }
    public String getPassword() { return new String(txtPassword.getPassword()); }
    
    public String getSelectedSubjectIdInAdmin() {
        String selected = (String) cbSubjects.getSelectedItem();
        if (selected == null) return null;
        return selected.split(" ")[0];
    }

    public int getSelectedRowOpenSubject() {
        return tableOpenSubjects.getSelectedRow();
    }
    
    public String getValueFromOpenSubjectTable(int row, int col) {
        return tableOpenSubjects.getValueAt(row, col).toString();
    }

    // Setters
    public void setLoginListener(ActionListener a) { btnLogin.addActionListener(a); }
    public void setLogoutListener(ActionListener a) { btnLogout.addActionListener(a); }
    
    public void setAdminGoGradeListener(ActionListener a) { btnAdminGoGrade.addActionListener(a); }
    public void setAdminSaveGradeListener(ActionListener a) { btnSaveGrade.addActionListener(a); }
    public void setAdminSubjectComboListener(ActionListener a) { cbSubjects.addActionListener(a); }
    public void setAdminBackListener(ActionListener a) { btnBackToAdminHome.addActionListener(a); }
    public void setAdminSortNameListener(ActionListener a) { btnAdminSortName.addActionListener(a); }

    public void setStudentGoRegisterListener(ActionListener a) { btnGoRegister.addActionListener(a); }
    public void setStudentConfirmRegListener(ActionListener a) { btnConfirmRegister.addActionListener(a); }
    public void setStudentBackListener(ActionListener a) { btnBackToProfile.addActionListener(a); }

    
    public void setStudentInfoText(String text) {
        lblStudentInfo.setText(text);
    }
    
    public void setTotalStudentCount(int count) {
        lblTotalStudents.setText("จำนวนคนลงทะเบียน: " + count + " คน");
    }

    public void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void updateSubjectCombo(ArrayList<String> items) {
        cbSubjects.removeAllItems();
        for (String item : items) {
            cbSubjects.addItem(item);
        }
    }

    public void updateTable(JTable table, String[] colNames, Object[][] data) {
        DefaultTableModel model = new DefaultTableModel(data, colNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (table == tableGrading && column == 2) return true; 
                return false;
            }
        };
        table.setModel(model);
    }

    public JTable getTableAllStudents() { return tableAllStudents; }
    public JTable getTableGrading() { return tableGrading; }
    public JTable getTableGradeHistory() { return tableGradeHistory; }
    public JTable getTableOpenSubjects() { return tableOpenSubjects; }
}