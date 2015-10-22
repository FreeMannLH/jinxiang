package ideatc.jinxiang.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ccb on 2015/8/14.
 */
public class ExpressMailModel implements Parcelable {
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getEmployee() {
        return Employee;
    }

    public void setEmployee(String employee) {
        Employee = employee;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMaker() {
        return Maker;
    }

    public void setMaker(String maker) {
        Maker = maker;
    }

    public int getMakerId() {
        return MakerId;
    }

    public void setMakerId(int makerId) {
        MakerId = makerId;
    }

    public String getMakeDateStr() {
        return MakeDateStr;
    }

    public void setMakeDateStr(String makeDateStr) {
        MakeDateStr = makeDateStr;
    }

    public String getExpressDateStr() {
        return ExpressDateStr;
    }

    public void setExpressDateStr(String expressDateStr) {
        ExpressDateStr = expressDateStr;
    }

    public String getExpressNo() {
        return ExpressNo;
    }

    public void setExpressNo(String expressNo) {
        ExpressNo = expressNo;
    }

    public String getReciveAddress() {
        return ReciveAddress;
    }

    public void setReciveAddress(String reciveAddress) {
        ReciveAddress = reciveAddress;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getDelivery() {
        return Delivery;
    }

    public void setDelivery(String delivery) {
        Delivery = delivery;
    }

    private int Id;//单据id
    private String OrderNo;//单号
    private String EmployeeId;//寄件人工号
    private String Employee;//寄件人姓名
    private String Content;//寄件事由
    private String MakeDateStr;//创建日期
    private String Maker;//创建人姓名
    private int MakerId;//创建人ID
    private String ExpressDateStr;//寄件日期
    private String ExpressNo;//快递单号
    private String ReciveAddress;//收货地址
    private String Remark;//备注
    private String Delivery;//快递公司

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeString(this.OrderNo);
        dest.writeString(this.EmployeeId);
        dest.writeString(this.Employee);
        dest.writeString(this.Content);
        dest.writeString(this.MakeDateStr);
        dest.writeString(this.Maker);
        dest.writeInt(this.MakerId);
        dest.writeString(this.ExpressDateStr);
        dest.writeString(this.ExpressNo);
        dest.writeString(this.ReciveAddress);
        dest.writeString(this.Remark);
        dest.writeString(this.Delivery);
    }

    public ExpressMailModel() {
    }

    protected ExpressMailModel(Parcel in) {
        this.Id = in.readInt();
        this.OrderNo = in.readString();
        this.EmployeeId = in.readString();
        this.Employee = in.readString();
        this.Content = in.readString();
        this.MakeDateStr = in.readString();
        this.Maker = in.readString();
        this.MakerId = in.readInt();
        this.ExpressDateStr = in.readString();
        this.ExpressNo = in.readString();
        this.ReciveAddress = in.readString();
        this.Remark = in.readString();
        this.Delivery = in.readString();
    }

    public static final Parcelable.Creator<ExpressMailModel> CREATOR = new Parcelable.Creator<ExpressMailModel>() {
        public ExpressMailModel createFromParcel(Parcel source) {
            return new ExpressMailModel(source);
        }

        public ExpressMailModel[] newArray(int size) {
            return new ExpressMailModel[size];
        }
    };
}
