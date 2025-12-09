import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private String studentId;  // 学号
    private List<Reservation> myReservations = new ArrayList<>(); // 个人预约列表

    public Student(String studentId, String name, String phone, String password) {
        super(name, phone, password);
        this.studentId = studentId;
    }

    // 学生登录（验证密码）
    @Override
    public boolean login(String inputPwd) {
        return this.getPassword().equals(inputPwd);
    }

    // 查看学生专属信息
    @Override
    public void showInfo() {
        System.out.println("【学生信息】");
        System.out.println("学号：" + studentId);
        super.showInfo();
    }

    // getter/setter
    public String getStudentId() { return studentId; }
    public List<Reservation> getMyReservations() { return myReservations; }
}