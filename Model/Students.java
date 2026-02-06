package Model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import Control.DBManager;

public class Students {
    private String id;
    private String prefix;
    private String fname;
    private String lname;
    private String bdate;
    private String curSch;
    private String email;
    private String regId;

    private DBManager db;

    public Students() {}

    public Students(String id, String pf, String fname, String lname, String bdate, String curSch, String email, String regId) {
        setId(id);
        this.prefix = pf;
        this.fname = fname;
        this.lname = lname;
        this.bdate = bdate; // format: yyyy-MM-dd
        this.curSch = curSch;
        this.email = email;
        this.regId = regId;
    }

    // Logics
    private long calStdAge(String bdate) {
        LocalDate bd = LocalDate.parse(bdate);
        long age = ChronoUnit.YEARS.between(bd, LocalDate.now());

        return age;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getBdate() {
        return bdate;
    }

    public String getCurSch() {
        return curSch;
    }

    public String getEmail() {
        return email;
    }

    public String getRegId() {
        return regId;
    }

    public String getName() {
        return getPrefix() + getFname() + " " + getLname();
    }

    public int getAge() {
        return (int) calStdAge(bdate);
    }

    public String getRegName() {
        for (SubjectStructure s : db.structures) {
            if (s.getId().equals(this.regId)) { return s.getName();  }
        }

        return "ไม่พบหลักสูตร";
    }

    // Setters
    public void setId(String id) {
        if (id.length() == 8 && id.startsWith("69")) {
            this.id = id;
        }
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public void setCurSch(String curSch) {
        this.curSch = curSch;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

}
