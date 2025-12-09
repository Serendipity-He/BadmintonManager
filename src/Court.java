public class Court {
    private String courtId;    // 场地编号
    private String type;       // 类型：单打/双打
    private String status;     // 状态：可用/维修中

    public Court(String courtId, String type, String status) {
        this.courtId = courtId;
        this.type = type;
        this.status = status;
    }

    // 查看场地信息
    public void showCourtInfo() {
        System.out.println("场地编号：" + courtId + "，类型：" + type + "，状态：" + status);
    }

    public String getCourtId() {
        return courtId;
    }

    public void setCourtId(String courtId) {
        this.courtId = courtId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}