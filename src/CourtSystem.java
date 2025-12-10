import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourtSystem {
    // 系统存储的核心数据
    private List<Student> studentList = new ArrayList<>();
    private List<Admin> adminList = new ArrayList<>();
    private List<Court> courtList = new ArrayList<>();
    private List<Reservation> reservationList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private User currentUser; // 当前登录用户

    // 初始化系统（默认添加测试数据）
    public void initSystem() {
        // 添加管理员
        adminList.add(new Admin("admin", "何明阳", "18329664060", "admin"));
        // 添加场地
        courtList.add(new Court("court001", "单打", "可用"));
        courtList.add(new Court("court002", "双打", "可用"));
        courtList.add(new Court("court003", "双打", "维修中"));
        System.out.println("系统初始化完成！");
    }

    // 主菜单
    public void showMainMenu() {
        while (true) {
            System.out.println("\n===== 校园羽毛球馆场地预约管理系统 =====");
            System.out.println("1. 学生注册");
            System.out.println("2. 用户登录");
            System.out.println("3. 退出系统");
            System.out.print("请选择功能（1-3）：");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 吸收换行符

            switch (choice) {
                case 1:
                    studentRegister();
                    break;
                case 2:
                    userLogin();
                    break;
                case 3:
                    System.out.println("退出系统，感谢使用！");
                    System.exit(0);
                default:
                    System.out.println("输入错误，请重新选择！");
            }
        }
    }

    // 学生注册
    private void studentRegister() {
        System.out.println("\n===== 学生注册 =====");
        System.out.print("请输入学号：");
        String sid = scanner.nextLine();
        // 查重学号
        for (Student s : studentList) {
            if (s.getStudentId().equals(sid)) {
                System.out.println("该学号已注册！");
                return;
            }
        }
        System.out.print("请输入姓名：");
        String name = scanner.nextLine();
        System.out.print("请输入联系电话：");
        String phone = scanner.nextLine();
        System.out.print("请设置登录密码：");
        String pwd = scanner.nextLine();

        Student student = new Student(sid, name, phone, pwd);
        studentList.add(student);
        System.out.println("注册成功！");
    }

    // 用户登录（学生/管理员）
    private void userLogin() {
        System.out.println("\n===== 用户登录 =====");
        System.out.print("请选择用户类型（1-学生，2-管理员）：");
        int type = scanner.nextInt();
        scanner.nextLine();
        System.out.print("请输入账号（学号/工号）：");
        String account = scanner.nextLine();
        System.out.print("请输入密码：");
        String pwd = scanner.nextLine();

        if (type == 1) {
            // 学生登录
            for (Student s : studentList) {
                if (s.getStudentId().equals(account) && s.login(pwd)) {
                    currentUser = s;
                    System.out.println("学生登录成功！");
                    showStudentMenu((Student) currentUser);
                    return;
                }
            }
        } else if (type == 2) {
            // 管理员登录
            for (Admin a : adminList) {
                if (a.getAdminId().equals(account) && a.login(pwd)) {
                    currentUser = a;
                    System.out.println("管理员登录成功！");
                    showAdminMenu((Admin) currentUser);
                    return;
                }
            }
        }
        System.out.println("账号或密码错误！");
    }

    // 学生功能菜单
    private void showStudentMenu(Student student) {
        while (true) {
            System.out.println("\n===== 学生功能菜单 =====");
            System.out.println("1. 查看个人信息");
            System.out.println("2. 查询可用场地");
            System.out.println("3. 预约场地");
            System.out.println("4. 取消预约");
            System.out.println("5. 查看我的预约");
            System.out.println("6. 退出登录");
            System.out.print("请选择功能（1-6）：");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    student.showInfo();
                    break;
                case 2:
                    queryAvailableCourts();
                    break;
                case 3:
                    reserveCourt(student);
                    break;
                case 4:
                    cancelReservation(student);
                    break;
                case 5:
                    showMyReservations(student);
                    break;
                case 6:
                    currentUser = null;
                    System.out.println("退出登录成功！");
                    return;
                default:
                    System.out.println("输入错误，请重新选择！");
            }
        }
    }

    // 管理员功能菜单
    private void showAdminMenu(Admin admin) {
        while (true) {
            System.out.println("\n===== 管理员功能菜单 =====");
            System.out.println("1. 查看个人信息");
            System.out.println("2. 查看所有场地");
            System.out.println("3. 查看所有预约");
            System.out.println("4. 确认预约完成");
            System.out.println("5.增加场地 ");
            System.out.println("6. 减少场地");
            System.out.println("7. 修改场地状态");
            System.out.println("8. 退出登录");
            System.out.print("请选择功能（1-8）：");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    admin.showInfo();
                    break;
                case 2:
                    showAllCourts();
                    break;
                case 3:
                    showAllReservations();
                    break;
                case 4:
                    confirmReservationByAdmin(admin);
                    break;
                case 5:
                    addCourt();
                    break;
                case 6:
                    removeCourt();
                    break;
                case 7:
                    updateCourtStatus();
                    break;
                case 8:
                    currentUser = null;
                    System.out.println("退出登录成功！");
                    return;

                default:
                    System.out.println("输入错误，请重新选择！");
            }
        }
    }

    private void queryAvailableCourts() {
        System.out.println("\n===== 可用场地列表 =====");
        boolean hasAvailable = false;
        for (Court c : courtList) {
            if (c.getStatus().equals("可用")) {
                c.showCourtInfo();
                hasAvailable = true;
            }
        }
        if (!hasAvailable) {
            System.out.println("暂无可用场地！");
        }
    }


    private void reserveCourt(Student student) {
        System.out.println("\n===== 预约场地 =====");
        queryAvailableCourts();
        System.out.print("请输入要预约的场地编号：");
        String cid = scanner.nextLine();

        Court targetCourt = null;
        for (Court c : courtList) {
            if (c.getCourtId().equals(cid) && c.getStatus().equals("可用")) {
                targetCourt = c;
                break;
            }
        }
        if (targetCourt == null) {
            System.out.println("该场地不可用或不存在！");
            return;
        }

        System.out.print("请输入预约时段（如：14:00-16:00）：");
        String timeSlot = scanner.nextLine();
        // 冲突检测：检查该场地该时段是否已被预约
        for (Reservation res : reservationList) {
            if (res.getCourtId().equals(cid) && res.getTimeSlot().equals(timeSlot)
                    && res.getStatus().equals("待使用")) {
                System.out.println("该场地" + timeSlot + "时段已被预约，预约失败！");
                return;
            }
        }

        // 生成预约编号（简单规则：学号+场地号+时段）
        String resId = student.getStudentId() + "-" + cid + "-" + timeSlot.replace(":", "").replace("-", "");
        Reservation res = new Reservation(resId, student, cid, timeSlot);
        reservationList.add(res);
        student.getMyReservations().add(res);
        System.out.println("预约成功！预约编号：" + resId);
    }

    private void cancelReservation(Student student) {
        System.out.println("\n===== 取消预约 =====");
        showMyReservations(student);
        if (student.getMyReservations().isEmpty()) {
            return;
        }
        System.out.print("请输入要取消的预约编号：");
        String resId = scanner.nextLine();

        // 用于存储找到的预约（避免遍历中删除元素导致异常）
        Reservation targetRes = null;
        for (Reservation res : student.getMyReservations()) {
            if (res.getReservationId().equals(resId)) {
                targetRes = res;
                break;
            }
        }

        if (targetRes != null) {
            // 先校验并更新状态（原逻辑保留）
            targetRes.cancelReservation();
            // 从学生个人列表中删除
            student.getMyReservations().remove(targetRes);
            // 从系统全局列表中删除
            reservationList.remove(targetRes);
            System.out.println("预约信息已删除！");
        } else {
            System.out.println("未找到该预约编号！");
        }
    }
    // 辅助方法：查看我的预约
    private void showMyReservations(Student student) {
        System.out.println("\n===== 我的预约列表 =====");
        if (student.getMyReservations().isEmpty()) {
            System.out.println("暂无预约记录！");
            return;
        }
        for (Reservation res : student.getMyReservations()) {
            res.showReservationInfo();
        }
    }

    // 辅助方法：查看所有场地
    private void showAllCourts() {
        System.out.println("\n===== 所有场地列表 =====");
        if (courtList.isEmpty()) {
            System.out.println("暂无场地记录！");
            return;
        }
        for (Court c : courtList) {
            c.showCourtInfo();
        }
    }

    // 辅助方法：查看所有预约
    private void showAllReservations() {
        System.out.println("\n===== 所有预约列表 =====");
        if (reservationList.isEmpty()) {
            System.out.println("暂无预约记录！");
            return;
        }
        for (Reservation res : reservationList) {
            res.showReservationInfo();
        }
    }

    private void addCourt() {
        System.out.println("\n===== 增加场地 =====");
        System.out.print("请输入场地编号（如court004）：");
        String cid = scanner.nextLine();

        // 校验编号是否已存在
        for (Court c : courtList) {
            if (c.getCourtId().equals(cid)) {
                System.out.println("该场地编号已存在！");
                return;
            }
        }

        System.out.print("请输入场地类型（单打/双打）：");
        String type = scanner.nextLine();
        if (!type.equals("单打") && !type.equals("双打")) {
            System.out.println("类型只能是“单打”或“双打”！");
            return;
        }

        System.out.print("请输入初始状态（可用/维修中）：");
        String status = scanner.nextLine();
        if (!status.equals("可用") && !status.equals("维修中")) {
            System.out.println("状态只能是“可用”或“维修中”！");
            return;
        }

        // 添加新场地
        courtList.add(new Court(cid, type, status));
        System.out.println("场地" + cid + "添加成功！");
    }


    private void confirmReservationByAdmin(Admin admin) {
        System.out.println("\n===== 确认预约完成 =====");
        showAllReservations(); // 先展示所有预约供管理员选择
        if (reservationList.isEmpty()) {
            return; // 无预约记录则直接返回
        }

        System.out.print("请输入要确认完成的预约编号：");
        String resId = scanner.nextLine();

        // 查找目标预约（避免遍历中删除元素导致异常）
        Reservation targetRes = null;
        for (Reservation res : reservationList) {
            if (res.getReservationId().equals(resId)) {
                targetRes = res;
                break;
            }
        }

        if (targetRes != null) {
            // 仅允许确认“待使用”状态的预约
            if (targetRes.getStatus().equals("待使用")) {
                // 1. 标记状态为“已完成”（可选，用于即时反馈）
                targetRes.setStatus("已完成");
                System.out.println("预约" + resId + "已标记为【已完成】");

                // 2. 从系统全局预约列表中删除
                reservationList.remove(targetRes);

                // 3. 从对应学生的个人预约列表中删除
                Student student = targetRes.getStudent(); // 获取预约所属学生
                student.getMyReservations().remove(targetRes);

                System.out.println("预约信息已从列表中删除！");
            } else {
                System.out.println("仅可确认【待使用】状态的预约！");
            }
        } else {
            System.out.println("未找到该预约编号！");
        }
    }
    private void removeCourt() {
        System.out.println("\n===== 减少场地 =====");
        showAllCourts();  // 先展示所有场地
        if (courtList.isEmpty()) {
            System.out.println("暂无场地可删除！");
            return;
        }

        System.out.print("请输入要删除的场地编号：");
        String cid = scanner.nextLine();

        // 查找并删除场地
        Court targetCourt = null;
        for (Court c : courtList) {
            if (c.getCourtId().equals(cid)) {
                targetCourt = c;
                break;
            }
        }

        if (targetCourt != null) {
            courtList.remove(targetCourt);
            System.out.println("场地" + cid + "已删除！");
        } else {
            System.out.println("未找到该场地编号！");
        }
    }

    private void updateCourtStatus() {
        System.out.println("\n===== 修改场地状态 =====");
        showAllCourts();  // 先展示所有场地
        if (courtList.isEmpty()) {
            System.out.println("暂无场地可修改！");
            return;
        }

        System.out.print("请输入要修改的场地编号：");
        String cid = scanner.nextLine();

        // 查找场地
        Court targetCourt = null;
        for (Court c : courtList) {
            if (c.getCourtId().equals(cid)) {
                targetCourt = c;
                break;
            }
        }

        if (targetCourt == null) {
            System.out.println("未找到该场地编号！");
            return;
        }

        // 输入新状态
        System.out.print("请输入新状态（可用/维修中）：");
        String newStatus = scanner.nextLine();
        if (!newStatus.equals("可用") && !newStatus.equals("维修中")) {
            System.out.println("状态只能是“可用”或“维修中”！");
            return;
        }

        // 更新状态
        targetCourt.setStatus(newStatus);
        System.out.println("场地" + cid + "状态已更新为：" + newStatus);
    }

    // 主函数入口
    public static void main(String[] args) {
        CourtSystem system = new CourtSystem();
        system.initSystem();
        system.showMainMenu();
    }
}