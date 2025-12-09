public abstract class User {
    private String name;       // 姓名
    private String phone;      // 联系电话
    private String password;   // 登录密码

    public User(String name, String phone, String password) {
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    // 抽象登录方法（子类实现）
    public abstract boolean login(String inputPwd);

    // 查看用户信息
    public void showInfo() {
        System.out.println("姓名：" + name + "，联系电话：" + phone);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

