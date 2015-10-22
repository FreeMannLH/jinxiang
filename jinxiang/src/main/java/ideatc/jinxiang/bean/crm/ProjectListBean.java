package ideatc.jinxiang.bean.crm;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ccb on 2015/8/19.
 * 项目合同集合实体
 */
public class ProjectListBean implements Parcelable {

    /**
     * message :
     * list : [{"SignDateStr":"2015-08-11","Matters":null,"ModifyByNo":"admin","CustManagerNo":"admin","PayFlag":1,"CreateByNo":"admin","CustManagerName":"管理员","Code":2,"Schedules":null,"CreateByName":"管理员","ModifyTimeStr":"2015-08-19 09:28:09","Mark":null,"CustomerId":2,"Contacts":null,"CompletionDate":"/Date(1440950400000)/","CompletionDateStr":"2015-08-31","ModifyByName":"管理员","CreateTimeStr":"2015-08-19 09:27:35","ContractNo":null,"ModifyTime":"/Date(1439947689000)/","CustomerName":"aaaa","Title":"APP开发","PayFlagStr":"全额付款","SignDate":"/Date(1439222400000)/","Status":2,"CreateTime":"/Date(1439947655000)/","Price":112000,"StatusStr":"正在执行","Id":"20150819090001","Remark":" 合同备注啊啊啊啊 "}]
     * success : true
     */
    private String message;
    private List<ProjectBean> list;
    private boolean success;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setList(List<ProjectBean> list) {
        this.list = list;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public List<ProjectBean> getList() {
        return list;
    }

    public boolean isSuccess() {
        return success;
    }

    public static class ProjectBean implements Parcelable {
        /**
         * SignDateStr : 2015-08-11
         * Matters : null
         * ModifyByNo : admin
         * CustManagerNo : admin
         * PayFlag : 1
         * CreateByNo : admin
         * CustManagerName : 管理员
         * Code : 2
         * Schedules : null
         * CreateByName : 管理员
         * ModifyTimeStr : 2015-08-19 09:28:09
         * Mark : null
         * CustomerId : 2
         * Contacts : null
         * CompletionDate : /Date(1440950400000)/
         * CompletionDateStr : 2015-08-31
         * ModifyByName : 管理员
         * CreateTimeStr : 2015-08-19 09:27:35
         * ContractNo : null
         * ModifyTime : /Date(1439947689000)/
         * CustomerName : aaaa
         * Title : APP开发
         * PayFlagStr : 全额付款
         * SignDate : /Date(1439222400000)/
         * Status : 2
         * CreateTime : /Date(1439947655000)/
         * Price : 112000.0
         * StatusStr : 正在执行
         * Id : 20150819090001
         * Remark :  合同备注啊啊啊啊
         */
        private String SignDateStr;
        private String Matters;
        private String ModifyByNo;
        private String CustManagerNo;
        private int PayFlag;
        private String CreateByNo;
        private String CustManagerName;
        private int Code;
        private String Schedules;
        private String CreateByName;
        private String ModifyTimeStr;
        private String Mark;
        private int CustomerId;
        private String Contacts;
        private String CompletionDate;
        private String CompletionDateStr;
        private String ModifyByName;
        private String CreateTimeStr;
        private String ContractNo;
        private String ModifyTime;
        private String CustomerName;
        private String Title;
        private String PayFlagStr;
        private String SignDate;
        private int Status;
        private String CreateTime;
        private double Price;
        private String StatusStr;
        private String Id;
        private String Remark;

        public void setSignDateStr(String SignDateStr) {
            this.SignDateStr = SignDateStr;
        }

        public void setMatters(String Matters) {
            this.Matters = Matters;
        }

        public void setModifyByNo(String ModifyByNo) {
            this.ModifyByNo = ModifyByNo;
        }

        public void setCustManagerNo(String CustManagerNo) {
            this.CustManagerNo = CustManagerNo;
        }

        public void setPayFlag(int PayFlag) {
            this.PayFlag = PayFlag;
        }

        public void setCreateByNo(String CreateByNo) {
            this.CreateByNo = CreateByNo;
        }

        public void setCustManagerName(String CustManagerName) {
            this.CustManagerName = CustManagerName;
        }

        public void setCode(int Code) {
            this.Code = Code;
        }

        public void setSchedules(String Schedules) {
            this.Schedules = Schedules;
        }

        public void setCreateByName(String CreateByName) {
            this.CreateByName = CreateByName;
        }

        public void setModifyTimeStr(String ModifyTimeStr) {
            this.ModifyTimeStr = ModifyTimeStr;
        }

        public void setMark(String Mark) {
            this.Mark = Mark;
        }

        public void setCustomerId(int CustomerId) {
            this.CustomerId = CustomerId;
        }

        public void setContacts(String Contacts) {
            this.Contacts = Contacts;
        }

        public void setCompletionDate(String CompletionDate) {
            this.CompletionDate = CompletionDate;
        }

        public void setCompletionDateStr(String CompletionDateStr) {
            this.CompletionDateStr = CompletionDateStr;
        }

        public void setModifyByName(String ModifyByName) {
            this.ModifyByName = ModifyByName;
        }

        public void setCreateTimeStr(String CreateTimeStr) {
            this.CreateTimeStr = CreateTimeStr;
        }

        public void setContractNo(String ContractNo) {
            this.ContractNo = ContractNo;
        }

        public void setModifyTime(String ModifyTime) {
            this.ModifyTime = ModifyTime;
        }

        public void setCustomerName(String CustomerName) {
            this.CustomerName = CustomerName;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public void setPayFlagStr(String PayFlagStr) {
            this.PayFlagStr = PayFlagStr;
        }

        public void setSignDate(String SignDate) {
            this.SignDate = SignDate;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public void setPrice(double Price) {
            this.Price = Price;
        }

        public void setStatusStr(String StatusStr) {
            this.StatusStr = StatusStr;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public String getSignDateStr() {
            return SignDateStr;
        }

        public String getMatters() {
            return Matters;
        }

        public String getModifyByNo() {
            return ModifyByNo;
        }

        public String getCustManagerNo() {
            return CustManagerNo;
        }

        public int getPayFlag() {
            return PayFlag;
        }

        public String getCreateByNo() {
            return CreateByNo;
        }

        public String getCustManagerName() {
            return CustManagerName;
        }

        public int getCode() {
            return Code;
        }

        public String getSchedules() {
            return Schedules;
        }

        public String getCreateByName() {
            return CreateByName;
        }

        public String getModifyTimeStr() {
            return ModifyTimeStr;
        }

        public String getMark() {
            return Mark;
        }

        public int getCustomerId() {
            return CustomerId;
        }

        public String getContacts() {
            return Contacts;
        }

        public String getCompletionDate() {
            return CompletionDate;
        }

        public String getCompletionDateStr() {
            return CompletionDateStr;
        }

        public String getModifyByName() {
            return ModifyByName;
        }

        public String getCreateTimeStr() {
            return CreateTimeStr;
        }

        public String getContractNo() {
            return ContractNo;
        }

        public String getModifyTime() {
            return ModifyTime;
        }

        public String getCustomerName() {
            return CustomerName;
        }

        public String getTitle() {
            return Title;
        }

        public String getPayFlagStr() {
            return PayFlagStr;
        }

        public String getSignDate() {
            return SignDate;
        }

        public int getStatus() {
            return Status;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public double getPrice() {
            return Price;
        }

        public String getStatusStr() {
            return StatusStr;
        }

        public String getId() {
            return Id;
        }

        public String getRemark() {
            return Remark;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.SignDateStr);
            dest.writeString(this.Matters);
            dest.writeString(this.ModifyByNo);
            dest.writeString(this.CustManagerNo);
            dest.writeInt(this.PayFlag);
            dest.writeString(this.CreateByNo);
            dest.writeString(this.CustManagerName);
            dest.writeInt(this.Code);
            dest.writeString(this.Schedules);
            dest.writeString(this.CreateByName);
            dest.writeString(this.ModifyTimeStr);
            dest.writeString(this.Mark);
            dest.writeInt(this.CustomerId);
            dest.writeString(this.Contacts);
            dest.writeString(this.CompletionDate);
            dest.writeString(this.CompletionDateStr);
            dest.writeString(this.ModifyByName);
            dest.writeString(this.CreateTimeStr);
            dest.writeString(this.ContractNo);
            dest.writeString(this.ModifyTime);
            dest.writeString(this.CustomerName);
            dest.writeString(this.Title);
            dest.writeString(this.PayFlagStr);
            dest.writeString(this.SignDate);
            dest.writeInt(this.Status);
            dest.writeString(this.CreateTime);
            dest.writeDouble(this.Price);
            dest.writeString(this.StatusStr);
            dest.writeString(this.Id);
            dest.writeString(this.Remark);
        }

        public ProjectBean() {
        }

        protected ProjectBean(Parcel in) {
            this.SignDateStr = in.readString();
            this.Matters = in.readString();
            this.ModifyByNo = in.readString();
            this.CustManagerNo = in.readString();
            this.PayFlag = in.readInt();
            this.CreateByNo = in.readString();
            this.CustManagerName = in.readString();
            this.Code = in.readInt();
            this.Schedules = in.readString();
            this.CreateByName = in.readString();
            this.ModifyTimeStr = in.readString();
            this.Mark = in.readString();
            this.CustomerId = in.readInt();
            this.Contacts = in.readString();
            this.CompletionDate = in.readString();
            this.CompletionDateStr = in.readString();
            this.ModifyByName = in.readString();
            this.CreateTimeStr = in.readString();
            this.ContractNo = in.readString();
            this.ModifyTime = in.readString();
            this.CustomerName = in.readString();
            this.Title = in.readString();
            this.PayFlagStr = in.readString();
            this.SignDate = in.readString();
            this.Status = in.readInt();
            this.CreateTime = in.readString();
            this.Price = in.readDouble();
            this.StatusStr = in.readString();
            this.Id = in.readString();
            this.Remark = in.readString();
        }

        public static final Creator<ProjectBean> CREATOR = new Creator<ProjectBean>() {
            public ProjectBean createFromParcel(Parcel source) {
                return new ProjectBean(source);
            }

            public ProjectBean[] newArray(int size) {
                return new ProjectBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.message);
        dest.writeList(this.list);
        dest.writeByte(success ? (byte) 1 : (byte) 0);
    }

    public ProjectListBean() {
    }

    protected ProjectListBean(Parcel in) {
        this.message = in.readString();
        this.list = new ArrayList<ProjectBean>();
        in.readList(this.list, List.class.getClassLoader());
        this.success = in.readByte() != 0;
    }

    public static final Parcelable.Creator<ProjectListBean> CREATOR = new Parcelable.Creator<ProjectListBean>() {
        public ProjectListBean createFromParcel(Parcel source) {
            return new ProjectListBean(source);
        }

        public ProjectListBean[] newArray(int size) {
            return new ProjectListBean[size];
        }
    };
}
