package ideatc.jinxiang.bean.crm;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ccb on 2015/8/19.
 */
public class ProjectDetailBean implements Parcelable {


    /**
     * message :
     * model : {"SignDateStr":"2015-08-11","Matters":[{"ModifyByNo":null,"ModifyByName":null,"CreateTimeStr":"0001-01-01 00:00:00","TotalCost":112000,"ModifyTime":"/Date(-62135596800000)/","OfficialCost":100000,"CreateByNo":null,"MatterId":9,"CreateByName":null,"ModifyTimeStr":"0001-01-01 00:00:00","AgencyCost":10000,"Files":[],"MatterName":"商标异议申请","CreateTime":"/Date(-62135596800000)/","OtherCost":2000,"Id":2,"Remark":"备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注"}],"ModifyByNo":"admin","CustManagerNo":"admin","PayFlag":1,"CreateByNo":"admin","CustManagerName":"管理员","Code":2,"Schedules":[{"CreateByName":"管理员","Schedule":20,"FinishDateStr":"2015-08-19","CreateTime":"/Date(1439968917000)/","CreateTimeStr":"2015-08-19 15:21","FinishDate":"/Date(1439913600000)/","Id":1,"CreateByNo":"admin","Remark":"  这个项目已经进行到百分之20了啊"}],"CreateByName":"管理员","ModifyTimeStr":"2015-08-19 09:28:09","Mark":null,"CustomerId":2,"Contacts":[{"ModifyByNo":null,"ContactName":"陈陈陈","ModifyByName":null,"CreateTimeStr":"0001-01-01 00:00:00","ModifyTime":"/Date(-62135596800000)/","ContactPhone":"18659980279","CreateByNo":null,"ContactTitle":"先生","CreateByName":null,"ModifyTimeStr":"0001-01-01 00:00:00","Support":1,"CreateTime":"/Date(-62135596800000)/","RoleStr":"关键使用者","IsMain":true,"Role":5,"SupportStr":"主动推进","Id":2,"ContactId":3}],"CompletionDate":"/Date(1440950400000)/","CompletionDateStr":"2015-08-31","ModifyByName":"管理员","CreateTimeStr":"2015-08-19 09:27:35","ContractNo":null,"ModifyTime":"/Date(1439947689000)/","CustomerName":"aaaa","Title":"APP开发","PayFlagStr":"全额付款","SignDate":"/Date(1439222400000)/","Status":2,"CreateTime":"/Date(1439947655000)/","Price":112000,"StatusStr":"正在执行","Id":"20150819090001","Remark":" 合同备注啊啊啊啊 "}
     * success : true
     */
    private String message;
    private ModelEntity model;
    private boolean success;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setModel(ModelEntity model) {
        this.model = model;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public ModelEntity getModel() {
        return model;
    }

    public boolean isSuccess() {
        return success;
    }

    public static class ModelEntity implements Parcelable {
        /**
         * SignDateStr : 2015-08-11
         * Matters : [{"ModifyByNo":null,"ModifyByName":null,"CreateTimeStr":"0001-01-01 00:00:00","TotalCost":112000,"ModifyTime":"/Date(-62135596800000)/","OfficialCost":100000,"CreateByNo":null,"MatterId":9,"CreateByName":null,"ModifyTimeStr":"0001-01-01 00:00:00","AgencyCost":10000,"Files":[],"MatterName":"商标异议申请","CreateTime":"/Date(-62135596800000)/","OtherCost":2000,"Id":2,"Remark":"备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注"}]
         * ModifyByNo : admin
         * CustManagerNo : admin
         * PayFlag : 1
         * CreateByNo : admin
         * CustManagerName : 管理员
         * Code : 2
         * Schedules : [{"CreateByName":"管理员","Schedule":20,"FinishDateStr":"2015-08-19","CreateTime":"/Date(1439968917000)/","CreateTimeStr":"2015-08-19 15:21","FinishDate":"/Date(1439913600000)/","Id":1,"CreateByNo":"admin","Remark":"  这个项目已经进行到百分之20了啊"}]
         * CreateByName : 管理员
         * ModifyTimeStr : 2015-08-19 09:28:09
         * Mark : null
         * CustomerId : 2
         * Contacts : [{"ModifyByNo":null,"ContactName":"陈陈陈","ModifyByName":null,"CreateTimeStr":"0001-01-01 00:00:00","ModifyTime":"/Date(-62135596800000)/","ContactPhone":"18659980279","CreateByNo":null,"ContactTitle":"先生","CreateByName":null,"ModifyTimeStr":"0001-01-01 00:00:00","Support":1,"CreateTime":"/Date(-62135596800000)/","RoleStr":"关键使用者","IsMain":true,"Role":5,"SupportStr":"主动推进","Id":2,"ContactId":3}]
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
        private List<MattersEntity> Matters;
        private String ModifyByNo;
        private String CustManagerNo;
        private int PayFlag;
        private String CreateByNo;
        private String CustManagerName;
        private int Code;
        private List<SchedulesEntity> Schedules;
        private String CreateByName;
        private String ModifyTimeStr;
        private String Mark;
        private int CustomerId;
        private List<ContactsEntity> Contacts;
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

        public void setMatters(List<MattersEntity> Matters) {
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

        public void setSchedules(List<SchedulesEntity> Schedules) {
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

        public void setContacts(List<ContactsEntity> Contacts) {
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

        public List<MattersEntity> getMatters() {
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

        public List<SchedulesEntity> getSchedules() {
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

        public List<ContactsEntity> getContacts() {
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

        public static class MattersEntity implements Parcelable {
            /**
             * ModifyByNo : null
             * ModifyByName : null
             * CreateTimeStr : 0001-01-01 00:00:00
             * TotalCost : 112000.0
             * ModifyTime : /Date(-62135596800000)/
             * OfficialCost : 100000.0
             * CreateByNo : null
             * MatterId : 9
             * CreateByName : null
             * ModifyTimeStr : 0001-01-01 00:00:00
             * AgencyCost : 10000.0
             * Files : []
             * MatterName : 商标异议申请
             * CreateTime : /Date(-62135596800000)/
             * OtherCost : 2000.0
             * Id : 2
             * Remark : 备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注
             */
            private String ModifyByNo;
            private String ModifyByName;
            private String CreateTimeStr;
            private double TotalCost;
            private String ModifyTime;
            private double OfficialCost;
            private String CreateByNo;
            private int MatterId;
            private String CreateByName;
            private String ModifyTimeStr;
            private double AgencyCost;
            private List<?> Files;
            private String MatterName;
            private String CreateTime;
            private double OtherCost;
            private int Id;
            private String Remark;

            public void setModifyByNo(String ModifyByNo) {
                this.ModifyByNo = ModifyByNo;
            }

            public void setModifyByName(String ModifyByName) {
                this.ModifyByName = ModifyByName;
            }

            public void setCreateTimeStr(String CreateTimeStr) {
                this.CreateTimeStr = CreateTimeStr;
            }

            public void setTotalCost(double TotalCost) {
                this.TotalCost = TotalCost;
            }

            public void setModifyTime(String ModifyTime) {
                this.ModifyTime = ModifyTime;
            }

            public void setOfficialCost(double OfficialCost) {
                this.OfficialCost = OfficialCost;
            }

            public void setCreateByNo(String CreateByNo) {
                this.CreateByNo = CreateByNo;
            }

            public void setMatterId(int MatterId) {
                this.MatterId = MatterId;
            }

            public void setCreateByName(String CreateByName) {
                this.CreateByName = CreateByName;
            }

            public void setModifyTimeStr(String ModifyTimeStr) {
                this.ModifyTimeStr = ModifyTimeStr;
            }

            public void setAgencyCost(double AgencyCost) {
                this.AgencyCost = AgencyCost;
            }

            public void setFiles(List<?> Files) {
                this.Files = Files;
            }

            public void setMatterName(String MatterName) {
                this.MatterName = MatterName;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public void setOtherCost(double OtherCost) {
                this.OtherCost = OtherCost;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public void setRemark(String Remark) {
                this.Remark = Remark;
            }

            public String getModifyByNo() {
                return ModifyByNo;
            }

            public String getModifyByName() {
                return ModifyByName;
            }

            public String getCreateTimeStr() {
                return CreateTimeStr;
            }

            public double getTotalCost() {
                return TotalCost;
            }

            public String getModifyTime() {
                return ModifyTime;
            }

            public double getOfficialCost() {
                return OfficialCost;
            }

            public String getCreateByNo() {
                return CreateByNo;
            }

            public int getMatterId() {
                return MatterId;
            }

            public String getCreateByName() {
                return CreateByName;
            }

            public String getModifyTimeStr() {
                return ModifyTimeStr;
            }

            public double getAgencyCost() {
                return AgencyCost;
            }

            public List<?> getFiles() {
                return Files;
            }

            public String getMatterName() {
                return MatterName;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public double getOtherCost() {
                return OtherCost;
            }

            public int getId() {
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
                dest.writeString(this.ModifyByNo);
                dest.writeString(this.ModifyByName);
                dest.writeString(this.CreateTimeStr);
                dest.writeDouble(this.TotalCost);
                dest.writeString(this.ModifyTime);
                dest.writeDouble(this.OfficialCost);
                dest.writeString(this.CreateByNo);
                dest.writeInt(this.MatterId);
                dest.writeString(this.CreateByName);
                dest.writeString(this.ModifyTimeStr);
                dest.writeDouble(this.AgencyCost);
                dest.writeList(this.Files);
                dest.writeString(this.MatterName);
                dest.writeString(this.CreateTime);
                dest.writeDouble(this.OtherCost);
                dest.writeInt(this.Id);
                dest.writeString(this.Remark);
            }

            public MattersEntity() {
            }

            protected MattersEntity(Parcel in) {
                this.ModifyByNo = in.readString();
                this.ModifyByName = in.readString();
                this.CreateTimeStr = in.readString();
                this.TotalCost = in.readDouble();
                this.ModifyTime = in.readString();
                this.OfficialCost = in.readDouble();
                this.CreateByNo = in.readString();
                this.MatterId = in.readInt();
                this.CreateByName = in.readString();
                this.ModifyTimeStr = in.readString();
                this.AgencyCost = in.readDouble();
                this.Files = new ArrayList<>();
                in.readList(this.Files, List.class.getClassLoader());
                this.MatterName = in.readString();
                this.CreateTime = in.readString();
                this.OtherCost = in.readDouble();
                this.Id = in.readInt();
                this.Remark = in.readString();
            }

            public static final Creator<MattersEntity> CREATOR = new Creator<MattersEntity>() {
                public MattersEntity createFromParcel(Parcel source) {
                    return new MattersEntity(source);
                }

                public MattersEntity[] newArray(int size) {
                    return new MattersEntity[size];
                }
            };
        }

        public static class SchedulesEntity implements Parcelable {
            /**
             * CreateByName : 管理员
             * Schedule : 20.0
             * FinishDateStr : 2015-08-19
             * CreateTime : /Date(1439968917000)/
             * CreateTimeStr : 2015-08-19 15:21
             * FinishDate : /Date(1439913600000)/
             * Id : 1
             * CreateByNo : admin
             * Remark :   这个项目已经进行到百分之20了啊
             */
            private String CreateByName;
            private double Schedule;
            private String FinishDateStr;
            private String CreateTime;
            private String CreateTimeStr;
            private String FinishDate;
            private int Id;
            private String CreateByNo;
            private String Remark;

            public void setCreateByName(String CreateByName) {
                this.CreateByName = CreateByName;
            }

            public void setSchedule(double Schedule) {
                this.Schedule = Schedule;
            }

            public void setFinishDateStr(String FinishDateStr) {
                this.FinishDateStr = FinishDateStr;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public void setCreateTimeStr(String CreateTimeStr) {
                this.CreateTimeStr = CreateTimeStr;
            }

            public void setFinishDate(String FinishDate) {
                this.FinishDate = FinishDate;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public void setCreateByNo(String CreateByNo) {
                this.CreateByNo = CreateByNo;
            }

            public void setRemark(String Remark) {
                this.Remark = Remark;
            }

            public String getCreateByName() {
                return CreateByName;
            }

            public double getSchedule() {
                return Schedule;
            }

            public String getFinishDateStr() {
                return FinishDateStr;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public String getCreateTimeStr() {
                return CreateTimeStr;
            }

            public String getFinishDate() {
                return FinishDate;
            }

            public int getId() {
                return Id;
            }

            public String getCreateByNo() {
                return CreateByNo;
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
                dest.writeString(this.CreateByName);
                dest.writeDouble(this.Schedule);
                dest.writeString(this.FinishDateStr);
                dest.writeString(this.CreateTime);
                dest.writeString(this.CreateTimeStr);
                dest.writeString(this.FinishDate);
                dest.writeInt(this.Id);
                dest.writeString(this.CreateByNo);
                dest.writeString(this.Remark);
            }

            public SchedulesEntity() {
            }

            protected SchedulesEntity(Parcel in) {
                this.CreateByName = in.readString();
                this.Schedule = in.readDouble();
                this.FinishDateStr = in.readString();
                this.CreateTime = in.readString();
                this.CreateTimeStr = in.readString();
                this.FinishDate = in.readString();
                this.Id = in.readInt();
                this.CreateByNo = in.readString();
                this.Remark = in.readString();
            }

            public static final Creator<SchedulesEntity> CREATOR = new Creator<SchedulesEntity>() {
                public SchedulesEntity createFromParcel(Parcel source) {
                    return new SchedulesEntity(source);
                }

                public SchedulesEntity[] newArray(int size) {
                    return new SchedulesEntity[size];
                }
            };
        }

        public static class ContactsEntity implements Parcelable {
            /**
             * ModifyByNo : null
             * ContactName : 陈陈陈
             * ModifyByName : null
             * CreateTimeStr : 0001-01-01 00:00:00
             * ModifyTime : /Date(-62135596800000)/
             * ContactPhone : 18659980279
             * CreateByNo : null
             * ContactTitle : 先生
             * CreateByName : null
             * ModifyTimeStr : 0001-01-01 00:00:00
             * Support : 1
             * CreateTime : /Date(-62135596800000)/
             * RoleStr : 关键使用者
             * IsMain : true
             * Role : 5
             * SupportStr : 主动推进
             * Id : 2
             * ContactId : 3
             */
            private String ModifyByNo;
            private String ContactName;
            private String ModifyByName;
            private String CreateTimeStr;
            private String ModifyTime;
            private String ContactPhone;
            private String CreateByNo;
            private String ContactTitle;
            private String CreateByName;
            private String ModifyTimeStr;
            private int Support;
            private String CreateTime;
            private String RoleStr;
            private boolean IsMain;
            private int Role;
            private String SupportStr;
            private int Id;
            private int ContactId;

            public void setModifyByNo(String ModifyByNo) {
                this.ModifyByNo = ModifyByNo;
            }

            public void setContactName(String ContactName) {
                this.ContactName = ContactName;
            }

            public void setModifyByName(String ModifyByName) {
                this.ModifyByName = ModifyByName;
            }

            public void setCreateTimeStr(String CreateTimeStr) {
                this.CreateTimeStr = CreateTimeStr;
            }

            public void setModifyTime(String ModifyTime) {
                this.ModifyTime = ModifyTime;
            }

            public void setContactPhone(String ContactPhone) {
                this.ContactPhone = ContactPhone;
            }

            public void setCreateByNo(String CreateByNo) {
                this.CreateByNo = CreateByNo;
            }

            public void setContactTitle(String ContactTitle) {
                this.ContactTitle = ContactTitle;
            }

            public void setCreateByName(String CreateByName) {
                this.CreateByName = CreateByName;
            }

            public void setModifyTimeStr(String ModifyTimeStr) {
                this.ModifyTimeStr = ModifyTimeStr;
            }

            public void setSupport(int Support) {
                this.Support = Support;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public void setRoleStr(String RoleStr) {
                this.RoleStr = RoleStr;
            }

            public void setIsMain(boolean IsMain) {
                this.IsMain = IsMain;
            }

            public void setRole(int Role) {
                this.Role = Role;
            }

            public void setSupportStr(String SupportStr) {
                this.SupportStr = SupportStr;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public void setContactId(int ContactId) {
                this.ContactId = ContactId;
            }

            public String getModifyByNo() {
                return ModifyByNo;
            }

            public String getContactName() {
                return ContactName;
            }

            public String getModifyByName() {
                return ModifyByName;
            }

            public String getCreateTimeStr() {
                return CreateTimeStr;
            }

            public String getModifyTime() {
                return ModifyTime;
            }

            public String getContactPhone() {
                return ContactPhone;
            }

            public String getCreateByNo() {
                return CreateByNo;
            }

            public String getContactTitle() {
                return ContactTitle;
            }

            public String getCreateByName() {
                return CreateByName;
            }

            public String getModifyTimeStr() {
                return ModifyTimeStr;
            }

            public int getSupport() {
                return Support;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public String getRoleStr() {
                return RoleStr;
            }

            public boolean isIsMain() {
                return IsMain;
            }

            public int getRole() {
                return Role;
            }

            public String getSupportStr() {
                return SupportStr;
            }

            public int getId() {
                return Id;
            }

            public int getContactId() {
                return ContactId;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.ModifyByNo);
                dest.writeString(this.ContactName);
                dest.writeString(this.ModifyByName);
                dest.writeString(this.CreateTimeStr);
                dest.writeString(this.ModifyTime);
                dest.writeString(this.ContactPhone);
                dest.writeString(this.CreateByNo);
                dest.writeString(this.ContactTitle);
                dest.writeString(this.CreateByName);
                dest.writeString(this.ModifyTimeStr);
                dest.writeInt(this.Support);
                dest.writeString(this.CreateTime);
                dest.writeString(this.RoleStr);
                dest.writeByte(IsMain ? (byte) 1 : (byte) 0);
                dest.writeInt(this.Role);
                dest.writeString(this.SupportStr);
                dest.writeInt(this.Id);
                dest.writeInt(this.ContactId);
            }

            public ContactsEntity() {
            }

            protected ContactsEntity(Parcel in) {
                this.ModifyByNo = in.readString();
                this.ContactName = in.readString();
                this.ModifyByName = in.readString();
                this.CreateTimeStr = in.readString();
                this.ModifyTime = in.readString();
                this.ContactPhone = in.readString();
                this.CreateByNo = in.readString();
                this.ContactTitle = in.readString();
                this.CreateByName = in.readString();
                this.ModifyTimeStr = in.readString();
                this.Support = in.readInt();
                this.CreateTime = in.readString();
                this.RoleStr = in.readString();
                this.IsMain = in.readByte() != 0;
                this.Role = in.readInt();
                this.SupportStr = in.readString();
                this.Id = in.readInt();
                this.ContactId = in.readInt();
            }

            public static final Creator<ContactsEntity> CREATOR = new Creator<ContactsEntity>() {
                public ContactsEntity createFromParcel(Parcel source) {
                    return new ContactsEntity(source);
                }

                public ContactsEntity[] newArray(int size) {
                    return new ContactsEntity[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.SignDateStr);
            dest.writeList(this.Matters);
            dest.writeString(this.ModifyByNo);
            dest.writeString(this.CustManagerNo);
            dest.writeInt(this.PayFlag);
            dest.writeString(this.CreateByNo);
            dest.writeString(this.CustManagerName);
            dest.writeInt(this.Code);
            dest.writeList(this.Schedules);
            dest.writeString(this.CreateByName);
            dest.writeString(this.ModifyTimeStr);
            dest.writeString(this.Mark);
            dest.writeInt(this.CustomerId);
            dest.writeList(this.Contacts);
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

        public ModelEntity() {
        }

        protected ModelEntity(Parcel in) {
            this.SignDateStr = in.readString();
            this.Matters = new ArrayList<MattersEntity>();
            in.readList(this.Matters, List.class.getClassLoader());
            this.ModifyByNo = in.readString();
            this.CustManagerNo = in.readString();
            this.PayFlag = in.readInt();
            this.CreateByNo = in.readString();
            this.CustManagerName = in.readString();
            this.Code = in.readInt();
            this.Schedules = new ArrayList<SchedulesEntity>();
            in.readList(this.Schedules, List.class.getClassLoader());
            this.CreateByName = in.readString();
            this.ModifyTimeStr = in.readString();
            this.Mark = in.readString();
            this.CustomerId = in.readInt();
            this.Contacts = new ArrayList<ContactsEntity>();
            in.readList(this.Contacts, List.class.getClassLoader());
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

        public static final Creator<ModelEntity> CREATOR = new Creator<ModelEntity>() {
            public ModelEntity createFromParcel(Parcel source) {
                return new ModelEntity(source);
            }

            public ModelEntity[] newArray(int size) {
                return new ModelEntity[size];
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
        dest.writeParcelable(this.model, flags);
        dest.writeByte(success ? (byte) 1 : (byte) 0);
    }

    public ProjectDetailBean() {
    }

    protected ProjectDetailBean(Parcel in) {
        this.message = in.readString();
        this.model = in.readParcelable(ModelEntity.class.getClassLoader());
        this.success = in.readByte() != 0;
    }

    public static final Parcelable.Creator<ProjectDetailBean> CREATOR = new Parcelable.Creator<ProjectDetailBean>() {
        public ProjectDetailBean createFromParcel(Parcel source) {
            return new ProjectDetailBean(source);
        }

        public ProjectDetailBean[] newArray(int size) {
            return new ProjectDetailBean[size];
        }
    };
}
