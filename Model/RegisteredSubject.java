package Model;

public class RegisteredSubject {
    private String stdId;
    private String sjId;
    private String grade = "X";

    public RegisteredSubject() {}

    public RegisteredSubject(String stdId, String sjId, String grade) {
        this.stdId = stdId;
        this.sjId = sjId;
        this.grade = grade;
    }

    public RegisteredSubject(String stdId, String sjId) {
        this.stdId = stdId;
        this.sjId = sjId;
    }

    // Getters
    public String getStdId() {
        return stdId;
    }

    public String getSjId() {
        return sjId;
    }

    public String getGrade() {
        return grade;
    }

    // Setters
    public void setStdId(String stdId) {
        this.stdId = stdId;
    }

    public void setSjId(String sjId) {
        this.sjId = sjId;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    
}
