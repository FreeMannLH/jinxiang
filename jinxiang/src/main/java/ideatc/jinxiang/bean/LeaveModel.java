package ideatc.jinxiang.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

/**
 * Created by Ccb on 2015/8/14.
 * 请假单模型
 */
public class LeaveModel implements Parcelable {
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
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

    public String getStartDateStr() {
        return StartDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        StartDateStr = startDateStr;
    }

    public String getEndDateStr() {
        return EndDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        EndDateStr = endDateStr;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public BigDecimal getTotalDay() {
        return TotalDay;
    }

    public void setTotalDay(BigDecimal totalDay) {
        TotalDay = totalDay;
    }

    public String getMaker() {
        return Maker;
    }

    public void setMaker(String maker) {
        Maker = maker;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMakeDateStr() {
        return MakeDateStr;
    }

    public void setMakeDateStr(String makeDateStr) {
        MakeDateStr = makeDateStr;
    }

    public int getMakerId() {
        return MakerId;
    }

    public void setMakerId(int makerId) {
        MakerId = makerId;
    }

    private int Id;//单据id
    private String OrderNo;//单号
    private String EmployeeId;//工号
    private String Employee;//姓名
    private String StartDateStr;//请假开始日期
    private String EndDateStr;//请假结束日期
    private String StartTime;
    private String EndTime;
    private BigDecimal TotalDay;
    private String Content;
    private String MakeDateStr;
    private String Maker;
    private int MakerId;

    public String getEmployee() {
        return Employee;
    }

    public void setEmployee(String employee) {
        Employee = employee;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeString(this.OrderNo);
        dest.writeString(this.EmployeeId);
        dest.writeString(this.StartDateStr);
        dest.writeString(this.EndDateStr);
        dest.writeString(this.StartTime);
        dest.writeString(this.EndTime);
        dest.writeSerializable(this.TotalDay);
        dest.writeString(this.Content);
        dest.writeString(this.MakeDateStr);
        dest.writeString(this.Maker);
        dest.writeInt(this.MakerId);
        dest.writeString(this.Employee);
    }

    public LeaveModel() {
    }

    protected LeaveModel(Parcel in) {
        this.Id = in.readInt();
        this.OrderNo = in.readString();
        this.EmployeeId = in.readString();
        this.StartDateStr = in.readString();
        this.EndDateStr = in.readString();
        this.StartTime = in.readString();
        this.EndTime = in.readString();
        this.TotalDay = (BigDecimal) in.readSerializable();
        this.Content = in.readString();
        this.MakeDateStr = in.readString();
        this.Maker = in.readString();
        this.MakerId = in.readInt();
        this.Employee = in.readString();
    }

    public static final Parcelable.Creator<LeaveModel> CREATOR = new Parcelable.Creator<LeaveModel>() {
        public LeaveModel createFromParcel(Parcel source) {
            return new LeaveModel(source);
        }

        public LeaveModel[] newArray(int size) {
            return new LeaveModel[size];
        }
    };
}
