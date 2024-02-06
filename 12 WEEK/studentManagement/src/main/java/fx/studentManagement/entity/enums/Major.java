package fx.studentManagement.entity.enums;

public enum Major {
    SoftwareDept("소프트웨어학과"),
    ComputerEngineeringDept("컴퓨터공학과"),
    IndustrialDesignDept("산업디자인학과");




    private String dept;

    Major(String dept) {
        this.dept = dept;
    }

    public String getDept() {
        return dept;
    }
}
