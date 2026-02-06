package Model;

public class Subjects {
    private String id;
    private String name;
    private int credit = 0;
    private String lecturer;
    private String preq = "None";
    private String type = "Unknown";

    public Subjects() {}

    public Subjects(String id, String name, int credit, String lecturer, String preq) {
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.lecturer = lecturer;
        this.preq = preq;
    }

    public Subjects(String id, String name, int credit, String lecturer) {
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.lecturer = lecturer;
    }

    // logics
    private boolean subjectType(String id) {
        if (id.startsWith("0550")) { 
            this.type = "Core Subject";
            return true;
        }
        else if (id.startsWith("9069")) { 
            this.type = "General Education";
            return true; 
        } else { return false; }
    }


     //Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCredit() {
        return credit;
    }

    public String getLecturer() {
        return lecturer;
    }

    public String getPreq() {
        return preq;
    }

    public String getType() {
        return type;
    }

    // Setters
    public void setId(String id) {
        if (id.length() == 8 && subjectType(id) == true) { this.id = id; }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public void setPreq(String preq) {
        this.preq = preq;
    }
    
}
