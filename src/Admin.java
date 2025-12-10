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


    // getter
    public String getAdminId() { return adminId; }
}