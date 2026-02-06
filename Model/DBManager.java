package Control;

import Model.*;
import java.util.ArrayList;

public class DBManager {
    public ArrayList<Students> students = new ArrayList<>();
    public ArrayList<Subjects> subjects = new ArrayList<>();
    public ArrayList<RegisteredSubject> regis = new ArrayList<>();
    public ArrayList<SubjectStructure> structures = new ArrayList<>();

    public DBManager() {
        initData();
    }

    private void initData() {
        subjects.add(new Subjects("05500000", "Pro-Fund", 3, "Arj.Santana"));
        subjects.add(new Subjects("05500001", "SA", 3, "Arj.Wisan"));
        subjects.add(new Subjects("05500002", "OOP", 3, "Arj.Santana", "05500000"));
        subjects.add(new Subjects("90690005", "Reading & Writing", 2, "Arj.ABC"));
        subjects.add(new Subjects("05500010", "Stat 1", 3, "Arj.ZaZa"));

        students.add(new Students("69000001", "ด.ช.", "สมชาย", "เก่ง", "2010-01-15", "ชรอ.", "Somchai@email.com", "10001234"));
        students.add(new Students("69000002", "ด.ญ.", "สมหญิง", "เริ่ด", "2008-02-19", "ชน.", "Somying@email.com", "10001234"));
        students.add(new Students("69000015", "ด.ญ.", "สมใจ", "ปัง", "2008-12-01", "ชน.", "Somjai@email.com", "10001255"));

        structures.add(new SubjectStructure("10001234", "CS", "Computer Science", "05500000", "1"));
        structures.add(new SubjectStructure("10001234", "CS", "Computer Science", "05500001", "2"));
        structures.add(new SubjectStructure("10001234", "CS", "Computer Science", "05500002", "1"));

        structures.add(new SubjectStructure("10001255", "Stat", "Statistics", "05500010", "2"));

        regis.add(new RegisteredSubject("69000015", "05500010", "A"));
    }

    public void addStudent(Students s) {
        students.add(s);
    }

    public Students searchStudent(String id) {
        for (Students s : students) {
            if (s.getId().equals(id)) { return s; }
        }

        return null;
    }
}
