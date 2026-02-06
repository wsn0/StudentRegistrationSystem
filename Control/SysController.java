package Control;

import View.MainView;
import Model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SysController {
    private MainView view;
    private DBManager db;

    public SysController() {
        view = new MainView();
        db = new DBManager();

        initLogin();
        initAdmin();
        initStudent();

        view.setVisible(true);
    }

    // Log in view Controller
    private void initLogin() {
        view.setLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = view.getUsername();
                String psw = view.getPassword();

                if (id.equals("admin") && psw.equals("1234")) {
                    Session.setAdm();
                    loadStudentsTable();
                    view.showAdminList();
                } 
                else {
                    Students s = db.searchStudent(id);
                    if (s != null && psw.equals("5678")) {
                        Session.setStd(s);
                    } else {
                        view.showError("เกิดข้อผิดพลาด!");
                    }
                }
            }  
        });

        view.setLoginListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                Session.logOut();
                view.showLogin();
           } 
        });
    }

    // Admin view Controller
    private void initAdmin() {
        view.setAdminGoGradeListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> sj = new ArrayList<>();

                for (Subjects s : db.subjects) {
                    sj.add(s.getId() + " " + s.getName());
                }
                view.updateSubjectCombo(sj);
                view.showAdminGrading();
            }
        });

        view.setAdminSubjectComboListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGradingInfo();
            }
        });

        view.setAdminSaveGradeListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                javax.swing.JTable table = view.getTableGrading();
                 int rows = table.getRowCount();
                 
                 for(int i=0; i<rows; i++) {
                     String sId = table.getValueAt(i, 0).toString();
                     String sjId = table.getValueAt(i, 1).toString();
                     String grade = table.getValueAt(i, 2).toString();
                     
                     for(RegisteredSubject r : db.regis) {
                         if(r.getStdId().equals(sId) && r.getSjId().equals(sjId)) {
                             r.setGrade(grade);
                         }
                     }
                 }
                 
                 view.showMessage("บันทึกเกรดลงฐานข้อมูลเรียบร้อยแล้ว!");
            }
        });

        view.setAdminBackListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.showAdminList();
            }
        });

        view.setAdminSortNameListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 java.util.Collections.sort(db.students, new java.util.Comparator<Students>() {
                     public int compare(Students s1, Students s2) {
                         return s1.getFname().compareTo(s2.getFname());
                     }
                 });
                 loadStudentsTable();
             }
        });
        
    }

    private void loadStudentsTable() {
        String[] col = {"ID", "Name", "Program", "Birthdate"};
        ArrayList<Students> lst = db.students;
        Object[][] data = new Object[lst.size()][4];

        for (int i = 0; i < lst.size(); i++) {
            Students s = lst.get(i);
            data[i][0] = s.getId();
            data[i][1] = s.getName();
            data[i][2] = s.getRegId();
            data[i][3] = s.getBdate();
        }
        view.updateTable(view.getTableAllStudents(), col, data);
    }

    private void loadGradingInfo() {
        String sjId = view.getSelectedSubjectIdInAdmin();
        if (sjId == null) { return; }

        ArrayList<RegisteredSubject> reg = new ArrayList<>();
        for (RegisteredSubject r : db.regis) {
            if (r.getSjId().equals(sjId)) { reg.add(r); }
        }

        view.setTotalStudentCount(reg.size());

        String[] col = {"Student ID", "Subject ID", "Grade"};
        Object[][] data = new Object[reg.size()][3];
        for(int i = 0; i < reg.size(); i++) {
            data[i][0] = reg.get(i).getStdId();
            data[i][1] = reg.get(i).getSjId();
            data[i][2] = reg.get(i).getGrade();
        }
        view.updateTable(view.getTableGrading(), col, data);
    }

    // Student view Controller
    private void initStudent() {
        view.setStudentGoRegisterListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadOpenSubjects(); 
                view.showStudentRegister();
            }
        });

        view.setStudentConfirmRegListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = view.getSelectedRowOpenSubject();
                if (row == -1) {
                    view.showError("กรุณาเลือกวิชาที่ต้องการลงทะเบียนเรียน");
                }

                String sjId = view.getValueFromOpenSubjectTable(row, 0);
                Students cur = Session.getStudent();

                if (cur.getAge() < 15) {
                    view.showError("ขออภัย อายุของคุณไม่ถึงเกณฑ์");
                    return;
                }

                db.regis.add(new RegisteredSubject(cur.getId(), sjId));

                view.showMessage("ลงงทะเบียนวิชา " + sjId + " สำเร็จ");
                loadStudentsProfile();
                view.showStudentProfile();
            }
        });

        view.setStudentBackListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.showStudentProfile();
            }
        });
    }

    private void loadStudentsProfile() {
        Students cur = Session.getStudent();
        String stdInfo = "รหัสนักเรียน: " + cur.getId() + "\n" + "ชื่อ-สกุล: " + cur.getName() + "\n" +
        "วันเกิด: " + cur.getBdate() + "\n" + "โรงเรียน: " + cur.getCurSch() + "\n" + "อีเมล: " + cur.getEmail() + "\n" + "หลักสูตร: " + cur.getRegName();
        view.setStudentInfoText(stdInfo);

        ArrayList<RegisteredSubject> reg = new ArrayList<>();
        for (RegisteredSubject r : db.regis) {
            if (r.getStdId().equals(cur.getId())) { reg.add(r); }
        }

        String[] col = {"Subject ID", "Grade"};
        Object[][] data = new Object[reg.size()][2];
        for(int i = 0; i < reg.size(); i++) {
            data[i][0] = reg.get(i).getSjId();
            data[i][1] = reg.get(i).getGrade();
        }

        view.updateTable(view.getTableGradeHistory(), col, data);
    }

    private void loadOpenSubjects() {
        ArrayList<Subjects> list = db.subjects;
        String[] cols = {"ID", "Name", "Credit"};
        Object[][] data = new Object[list.size()][3];
        
        for(int i=0; i<list.size(); i++) {
            data[i][0] = list.get(i).getId();
            data[i][1] = list.get(i).getName();
            data[i][2] = list.get(i).getCredit();
        }
        view.updateTable(view.getTableOpenSubjects(), cols, data);
    }

}
