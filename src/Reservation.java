public class Reservation {
    private String reservationId;  // 预约编号
    private Student student;       // 预约学生
    private String courtId;        // 场地编号
    private String timeSlot;       // 预约时段（如：14:00-16:00）
    private String status;         // 状态：待使用/已完成/已取消

    public Reservation(String reservationId, Student student, String courtId, String timeSlot) {
        this.reservationId = reservationId;
        this.student = student;
        this.courtId = courtId;
        this.timeSlot = timeSlot;
        this.status = "待使用"; // 初始状态为待使用

    }

    // 取消预约（学生功能）
    public void cancelReservation() {
        if (!this.status.equals("待使用")) {
            System.out.println("仅可取消【待使用】状态的预约！");
            throw new IllegalArgumentException("无效的预约状态"); // 用于中断删除流程
        }
        this.status = "已取消"; // 状态更新仅作为临时标记
        System.out.println("预约" + reservationId + "已取消！");
    }

    // 查看预约信息
    public void showReservationInfo() {
        System.out.println("【预约信息】");
        System.out.println("预约编号：" + reservationId);
        System.out.println("学生：" + student.getStudentId() + "（" + student.getName() + "）");
        System.out.println("场地编号：" + courtId + "，时段：" + timeSlot);
    }

    // getter/setter
    public String getReservationId() { return reservationId; }
    public Student getStudent() { return student; }
    public String getCourtId() { return courtId; }
    public String getTimeSlot() { return timeSlot; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}