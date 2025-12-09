public class Admin extends User {
    private String adminId;  // 工号

    public Admin(String adminId, String name, String phone, String password) {
        super(name, phone, password);
        this.adminId = adminId;
    }

    // 管理员登录（验证密码）
    @Override
    public boolean login(String inputPwd) {
        return this.getPassword().equals(inputPwd);
    }

    // 查看管理员专属信息
    @Override
    public void showInfo() {
        System.out.println("【管理员信息】");
        System.out.println("工号：" + adminId);
        super.showInfo();
    }

    // 确认预约完成（管理员功能）
    public void confirmReservation(Reservation res) {
        if (res.getStatus().equals("待使用")) {
            res.setStatus("已完成");
            System.out.println("预约" + res.getReservationId() + "已标记为【已完成】");
        } else {
            System.out.println("仅可确认【待使用】状态的预约！");
        }
    }

    // getter
    public String getAdminId() { return adminId; }
}