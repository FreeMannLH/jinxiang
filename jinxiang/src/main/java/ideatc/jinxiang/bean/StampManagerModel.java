package ideatc.jinxiang.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ccb on 2015/8/14.
 * 盖章模型
 */
public class StampManagerModel implements Parcelable {
    private int Id;
    private String OrderNo;
    private String StampDateStr;
    private String Content;
    private String Employee;
    private String EmployeeId;
    private String StampType;
    private String Number;
    private boolean Status;
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

    public String getStampDateStr() {
        return StampDateStr;
    }

    public void setStampDateStr(String stampDateStr) {
        StampDateStr = stampDateStr;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
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

    public String getStampType() {
        return StampType;
    }

    public void setStampType(String stampType) {
        StampType = stampType;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
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
        dest.writeString(this.StampDateStr);
        dest.writeString(this.Content);
        dest.writeString(this.Employee);
        dest.writeString(this.EmployeeId);
        dest.writeString(this.StampType);
        dest.writeString(this.Number);
        dest.writeByte(Status ? (byte) 1 : (byte) 0);
        dest.writeString(this.Maker);
        dest.writeString(this.MakeDateStr);
    }

    public StampManagerModel() {
    }

    protected StampManagerModel(Parcel in) {
        this.Id = in.readInt();
        this.OrderNo = in.readString();
        this.StampDateStr = in.readString();
        this.Content = in.readString();
        this.Employee = in.readString();
        this.EmployeeId = in.readString();
        this.StampType = in.readString();
        this.Number = in.readString();
        this.Status = in.readByte() != 0;
        this.Maker = in.readString();
        this.MakeDateStr = in.readString();
    }

    public static final Parcelable.Creator<StampManagerModel> CREATOR = new Parcelable.Creator<StampManagerModel>() {
        public StampManagerModel createFromParcel(Parcel source) {
            return new StampManagerModel(source);
        }

        public StampManagerModel[] newArray(int size) {
            return new StampManagerModel[size];
        }
    };
}
