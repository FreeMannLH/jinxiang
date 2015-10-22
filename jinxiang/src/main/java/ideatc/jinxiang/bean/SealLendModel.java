package ideatc.jinxiang.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ccb on 2015/8/14.
 * 公章借出模型
 */
public class SealLendModel implements Parcelable {
   private int Id;
    private String ForecastLendDateStr;
    private String ActualLenDateStr;
    private  String ActualPickDateStr;
    private String BackDateStr;
    private String  ActualBackDateStr;
    private String OrderNo;
    private String Content;
    private  String Employee;
    private  String EmployeeId;
    private  String Maker;
    private String MakeDateStr;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getForecastLendDateStr() {
        return ForecastLendDateStr;
    }

    public void setForecastLendDateStr(String forecastLendDateStr) {
        ForecastLendDateStr = forecastLendDateStr;
    }

    public String getActualLenDateStr() {
        return ActualLenDateStr;
    }

    public void setActualLenDateStr(String actualLenDateStr) {
        ActualLenDateStr = actualLenDateStr;
    }

    public String getActualPickDateStr() {
        return ActualPickDateStr;
    }

    public void setActualPickDateStr(String actualPickDateStr) {
        ActualPickDateStr = actualPickDateStr;
    }

    public String getBackDateStr() {
        return BackDateStr;
    }

    public void setBackDateStr(String backDateStr) {
        BackDateStr = backDateStr;
    }

    public String getActualBackDateStr() {
        return ActualBackDateStr;
    }

    public void setActualBackDateStr(String actualBackDateStr) {
        ActualBackDateStr = actualBackDateStr;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
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
        dest.writeString(this.ForecastLendDateStr);
        dest.writeString(this.ActualLenDateStr);
        dest.writeString(this.ActualPickDateStr);
        dest.writeString(this.BackDateStr);
        dest.writeString(this.ActualBackDateStr);
        dest.writeString(this.OrderNo);
        dest.writeString(this.Content);
        dest.writeString(this.Employee);
        dest.writeString(this.EmployeeId);
        dest.writeString(this.Maker);
        dest.writeString(this.MakeDateStr);
    }

    public SealLendModel() {
    }

    protected SealLendModel(Parcel in) {
        this.Id = in.readInt();
        this.ForecastLendDateStr = in.readString();
        this.ActualLenDateStr = in.readString();
        this.ActualPickDateStr = in.readString();
        this.BackDateStr = in.readString();
        this.ActualBackDateStr = in.readString();
        this.OrderNo = in.readString();
        this.Content = in.readString();
        this.Employee = in.readString();
        this.EmployeeId = in.readString();
        this.Maker = in.readString();
        this.MakeDateStr = in.readString();
    }

    public static final Parcelable.Creator<SealLendModel> CREATOR = new Parcelable.Creator<SealLendModel>() {
        public SealLendModel createFromParcel(Parcel source) {
            return new SealLendModel(source);
        }

        public SealLendModel[] newArray(int size) {
            return new SealLendModel[size];
        }
    };
}
