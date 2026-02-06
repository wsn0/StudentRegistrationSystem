package Model;

public class SubjectStructure {
    private String id;
    private String name;
    private String dept;
    private String preqId;
    private String semester;

    public SubjectStructure() {}

    public SubjectStructure(String id, String name, String dept, String preqId, String semester) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.preqId = preqId;
        this.semester = semester;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public String getPreqId() {
        return preqId;
    }

    public String getSemester() {
        return semester;
    }

    // Setters
    public void setId(String id) {
        if (id.length() == 8 && (!id.startsWith("0"))) { this.id = id; }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setPreqId(String preqId) {
        this.preqId = preqId;
    }

    public void setSemester(String semester) {
        if (semester.equals("1") || semester.equals("2")) { this.semester = semester; }
    }
    
}
