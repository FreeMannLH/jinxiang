package ideatc.jinxiang.bean;

/**
 * Created by Ccb on 2015/8/29.
 */
public class ExpressDeliveryModel {

    /**
     * Id : 1
     * OrderNo : SJD20150827001
     * ExpressType : 也很有意义
     * ExpressNo : 6764549
     * Content : 进可规划局
     * EmployeeId : admin
     * Employee : 管理员
     * Remark : vfytvchb
     * MakerId : 0
     * Maker : 管理员
     * MakeDate : /Date(1440635290000)/
     * MakeDateStr : 2015-08-27
     * DeliveryType : 已付
     * Money : 80.0
     * SysFlowId : 0
     */

    private int Id;
    private String OrderNo;
    private String ExpressType;
    private String ExpressNo;
    private String Content;
    private String EmployeeId;
    private String Employee;
    private String Remark;
    private int MakerId;
    private String Maker;
    private String MakeDate;
    private String MakeDateStr;
    private String DeliveryType;
    private double Money;
    private int SysFlowId;

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setOrderNo(String OrderNo) {
        this.OrderNo = OrderNo;
    }

    public void setExpressType(String ExpressType) {
        this.ExpressType = ExpressType;
    }

    public void setExpressNo(String ExpressNo) {
        this.ExpressNo = ExpressNo;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public void setEmployeeId(String EmployeeId) {
        this.EmployeeId = EmployeeId;
    }

    public void setEmployee(String Employee) {
        this.Employee = Employee;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public void setMakerId(int MakerId) {
        this.MakerId = MakerId;
    }

    public void setMaker(String Maker) {
        this.Maker = Maker;
    }

    public void setMakeDate(String MakeDate) {
        this.MakeDate = MakeDate;
    }

    public void setMakeDateStr(String MakeDateStr) {
        this.MakeDateStr = MakeDateStr;
    }

    public void setDeliveryType(String DeliveryType) {
        this.DeliveryType = DeliveryType;
    }

    public void setMoney(double Money) {
        this.Money = Money;
    }

    public void setSysFlowId(int SysFlowId) {
        this.SysFlowId = SysFlowId;
    }

    public int getId() {
        return Id;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public String getExpressType() {
        return ExpressType;
    }

    public String getExpressNo() {
        return ExpressNo;
    }

    public String getContent() {
        return Content;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public String getEmployee() {
        return Employee;
    }

    public String getRemark() {
        return Remark;
    }

    public int getMakerId() {
        return MakerId;
    }

    public String getMaker() {
        return Maker;
    }

    public String getMakeDate() {
        return MakeDate;
    }

    public String getMakeDateStr() {
        return MakeDateStr;
    }

    public String getDeliveryType() {
        return DeliveryType;
    }

    public double getMoney() {
        return Money;
    }

    public int getSysFlowId() {
        return SysFlowId;
    }
}
