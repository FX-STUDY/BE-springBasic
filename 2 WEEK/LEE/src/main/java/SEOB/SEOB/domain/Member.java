package SEOB.SEOB.domain;

public class Member {

    private Long id;
    private String name;
    private GradeType grade;




    //getter setter
    public void Member(String name, GradeType grade) {
        this.name = name;
        this.grade = grade;
    }

    public Member(String name, GradeType grade) {
        this.name = name;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GradeType getGrade() {
        return grade;
    }

    public void setGrade(GradeType grade) {
        this.grade = grade;
    }

}
