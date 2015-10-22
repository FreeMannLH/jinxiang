package ideatc.jinxiang.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

/**
 * Created by Ccb on 2015/8/14.
 * 派车模型
 */
public class SendCarModel implements Parcelable {
    private int Id;
    private String OrderNo;
    private String Employee;
    private String EmployeeId;
    private String EndAddress;
    private String ApplyDateStr;
    private String usage;
    private String SendDateStr;
    private String SendTime;
    private String Content;
    private BigDecimal BeforeKm;
    private String BackDateStr;
    private String BackTime;
    private BigDecimal AfterKm;
    private String Remark;
    private int MakerId;
    private String Maker;
    private String MakeDateStr;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public String getEmployee() {
        return Employee;
    }

    public void setEmployee(String employee) {
        Employee = employee;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }

    public String getEndAddress() {
        return EndAddress;
    }

    public void setEndAddress(String endAddress) {
        EndAddress = endAddress;
    }

    public String getApplyDateStr() {
        return ApplyDateStr;
    }

    public void setApplyDateStr(String applyDateStr) {
        ApplyDateStr = applyDateStr;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getSendDateStr() {
        return SendDateStr;
    }

    public void setSendDateStr(String sendDateStr) {
        SendDateStr = sendDateStr;
    }

    public String getSendTime() {
        return SendTime;
    }

    public void setSendTime(String sendTime) {
        SendTime = sendTime;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public BigDecimal getBeforeKm() {
        return BeforeKm;
    }

    public void setBeforeKm(BigDecimal beforeKm) {
        BeforeKm = beforeKm;
    }

    public String getBackDateStr() {
        return BackDateStr;
    }

    public void setBackDateStr(String backDateStr) {
        BackDateStr = backDateStr;
    }

    public String getBackTime() {
        return BackTime;
    }

    public void setBackTime(String backTime) {
        BackTime = backTime;
    }

    public BigDecimal getAfterKm() {
        return AfterKm;
    }

    public void setAfterKm(BigDecimal afterKm) {
        AfterKm = afterKm;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public int getMakerId() {
        return MakerId;
    }

    public void setMakerId(int makerId) {
        MakerId = makerId;
    }

    public String getMaker() {
        return Maker;
    }

    public void setMaker(String maker) {
        Maker = maker;
    }

    public String getMakeDateStr() {
        return MakeDateStr;
    }

    public void setMakeDateStr(String makeDateStr) {
        MakeDateStr = makeDateStr;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeString(this.OrderNo);
        dest.writeString(this.Employee);
        dest.writeString(this.EmployeeId);
        dest.writeString(this.EndAddress);
        dest.writeString(this.ApplyDateStr);
        dest.writeString(this.usage);
        dest.writeString(this.SendDateStr);
        dest.writeString(this.SendTime);
        dest.writeString(this.Content);
        dest.writeSerializable(this.BeforeKm);
        dest.writeString(this.BackDateStr);
        dest.writeString(this.BackTime);
        dest.writeSerializable(this.AfterKm);
        dest.writeString(this.Remark);
        dest.writeInt(this.MakerId);
        dest.writeString(this.Maker);
        dest.writeString(this.MakeDateStr);
    }

    public SendCarModel() {
    }

    protected SendCarModel(Parcel in) {
        this.Id = in.readInt();
        this.OrderNo = in.readString();
        this.Employee = in.readString();
        this.EmployeeId = in.readString();
        this.EndAddress = in.readString();
        this.ApplyDateStr = in.readString();
        this.usage = in.readString();
        this.SendDateStr = in.readString();
        this.SendTime = in.readString();
        this.Content = in.readString();
        this.BeforeKm = (BigDecimal) in.readSerializable();
        this.BackDateStr = in.readString();
        this.BackTime = in.readString();
        this.AfterKm = (BigDecimal) in.readSerializable();
        this.Remark = in.readString();
        this.MakerId = in.readInt();
        this.Maker = in.readString();
        this.MakeDateStr = in.readString();
    }

    public static final Parcelable.Creator<SendCarModel> CREATOR = new Parcelable.Creator<SendCarModel>() {
        public SendCarModel createFromParcel(Parcel source) {
            return new SendCarModel(source);
        }

        public SendCarModel[] newArray(int size) {
            return new SendCarModel[size];
        }
    };
}
