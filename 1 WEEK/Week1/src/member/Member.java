package member;

public class Member {

    private Long id;
    private String name;
    private String password;
    private String grade; // NORMAL, VIP



    //getter setter
    public void setAll(String name, String password, String grade) {
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
