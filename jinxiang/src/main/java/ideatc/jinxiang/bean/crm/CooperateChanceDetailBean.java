package ideatc.jinxiang.bean.crm;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ccb
 * create at 2015/9/17 14:22
 * 合作机会详情实体
 */
public class CooperateChanceDetailBean {

    /**
     * success : true
     * message :
     * model : {"Id":"20150915100001","Title":"啊大大","CustManagerNo":"admin","CustManagerName":"管理员","CustomerId":17,"CustomerName":"陈奕迅","Stage":1,"StageStr":"初步接触","Mark":null,"Remark":"当然是无法","Contacts":[{"Id":7,"ContactId":18,"ContactName":"打发","ContactTitle":"小姐","ContactPhone":"13312341221","IsMain":false,"Role":2,"RoleStr":"辅助决策者","Support":3,"SupportStr":"乐意接待","CreateByNo":null,"CreateByName":null,"CreateTime":"/Date(-62135596800000)/","CreateTimeStr":"0001-01-01 00:00:00","ModifyByNo":null,"ModifyByName":null,"ModifyTime":"/Date(-62135596800000)/","ModifyTimeStr":"0001-01-01 00:00:00"}],"Matters":[{"Id":7,"MatterId":5,"MatterName":"商标注销","OfficialCost":1222,"AgencyCost":2222,"OtherCost":2222,"TotalCost":5666,"Remark":"倒萨","Files":[{"Id":1}],"CreateByNo":null,"CreateByName":null,"CreateTime":"/Date(-62135596800000)/","CreateTimeStr":"0001-01-01 00:00:00","ModifyByNo":null,"ModifyByName":null,"ModifyTime":"/Date(-62135596800000)/","ModifyTimeStr":"0001-01-01 00:00:00"}],"StageLogs":[{"Id":13,"Stage":1,"StageStr":"初步接触","Reason":"初始化阶段","CreateByNo":"admin","CreateByName":"管理员","CreateTime":"/Date(1442284896000)/","CreateTimeStr":"2015-09-15 10:41"}],"CreateByNo":"admin","CreateByName":"管理员","CreateTime":"/Date(1442284896000)/","CreateTimeStr":"2015-09-15 10:41:36","ModifyByNo":"admin","ModifyByName":"管理员","ModifyTime":"/Date(1442284896000)/","ModifyTimeStr":"2015-09-15 10:41:36"}
     */

    private boolean success;
    private String message;
    @SerializedName("model")
    private CooperateModel Cooperatmodel;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCooperatmodel(CooperateModel Cooperatmodel) {
        this.Cooperatmodel = Cooperatmodel;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public CooperateModel getCooperatmodel() {
        return Cooperatmodel;
    }


    public static class CooperateModel implements Parcelable {

        /**
         * Id : 20150915100001
         * Title : 啊大大
         * CustManagerNo : admin
         * CustManagerName : 管理员
         * CustomerId : 17
         * CustomerName : 陈奕迅
         * Stage : 1
         * StageStr : 初步接触
         * Mark : null
         * Remark : 当然是无法
         * Contacts : [{"Id":7,"ContactId":18,"ContactName":"打发","ContactTitle":"小姐","ContactPhone":"13312341221","IsMain":false,"Role":2,"RoleStr":"辅助决策者","Support":3,"SupportStr":"乐意接待","CreateByNo":null,"CreateByName":null,"CreateTime":"/Date(-62135596800000)/","CreateTimeStr":"0001-01-01 00:00:00","ModifyByNo":null,"ModifyByName":null,"ModifyTime":"/Date(-62135596800000)/","ModifyTimeStr":"0001-01-01 00:00:00"}]
         * Matters : [{"Id":7,"MatterId":5,"MatterName":"商标注销","OfficialCost":1222,"AgencyCost":2222,"OtherCost":2222,"TotalCost":5666,"Remark":"倒萨","Files":[{"Id":1}],"CreateByNo":null,"CreateByName":null,"CreateTime":"/Date(-62135596800000)/","CreateTimeStr":"0001-01-01 00:00:00","ModifyByNo":null,"ModifyByName":null,"ModifyTime":"/Date(-62135596800000)/","ModifyTimeStr":"0001-01-01 00:00:00"}]
         * StageLogs : [{"Id":13,"Stage":1,"StageStr":"初步接触","Reason":"初始化阶段","CreateByNo":"admin","CreateByName":"管理员","CreateTime":"/Date(1442284896000)/","CreateTimeStr":"2015-09-15 10:41"}]
         * CreateByNo : admin
         * CreateByName : 管理员
         * CreateTime : /Date(1442284896000)/
         * CreateTimeStr : 2015-09-15 10:41:36
         * ModifyByNo : admin
         * ModifyByName : 管理员
         * ModifyTime : /Date(1442284896000)/
         * ModifyTimeStr : 2015-09-15 10:41:36
         */

        private String Id;
        private String Title;
        private String CustManagerNo;
        private String CustManagerName;
        private int CustomerId;
        private String CustomerName;
        private int Stage;
        private String StageStr;
        private String Mark;
        private String Remark;
        private String CreateByNo;
        private String CreateByName;
        private String CreateTime;
        private String CreateTimeStr;
        private String ModifyByNo;
        private String ModifyByName;
        private String ModifyTime;
        private String ModifyTimeStr;
        private List<ContactsEntity> Contacts;
        private List<MattersEntity> Matters;
        private List<StageLogsEntity> StageLogs;

        public void setId(String Id) {
            this.Id = Id;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public void setCustManagerNo(String CustManagerNo) {
            this.CustManagerNo = CustManagerNo;
        }

        public void setCustManagerName(String CustManagerName) {
            this.CustManagerName = CustManagerName;
        }

        public void setCustomerId(int CustomerId) {
            this.CustomerId = CustomerId;
        }

        public void setCustomerName(String CustomerName) {
            this.CustomerName = CustomerName;
        }

        public void setStage(int Stage) {
            this.Stage = Stage;
        }

        public void setStageStr(String StageStr) {
            this.StageStr = StageStr;
        }

        public void setMark(String Mark) {
            this.Mark = Mark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public void setCreateByNo(String CreateByNo) {
            this.CreateByNo = CreateByNo;
        }

        public void setCreateByName(String CreateByName) {
            this.CreateByName = CreateByName;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public void setCreateTimeStr(String CreateTimeStr) {
            this.CreateTimeStr = CreateTimeStr;
        }

        public void setModifyByNo(String ModifyByNo) {
            this.ModifyByNo = ModifyByNo;
        }

        public void setModifyByName(String ModifyByName) {
            this.ModifyByName = ModifyByName;
        }

        public void setModifyTime(String ModifyTime) {
            this.ModifyTime = ModifyTime;
        }

        public void setModifyTimeStr(String ModifyTimeStr) {
            this.ModifyTimeStr = ModifyTimeStr;
        }

        public void setContacts(List<ContactsEntity> Contacts) {
            this.Contacts = Contacts;
        }

        public void setMatters(List<MattersEntity> Matters) {
            this.Matters = Matters;
        }

        public void setStageLogs(List<StageLogsEntity> StageLogs) {
            this.StageLogs = StageLogs;
        }

        public String getId() {
            return Id;
        }

        public String getTitle() {
            return Title;
        }

        public String getCustManagerNo() {
            return CustManagerNo;
        }

        public String getCustManagerName() {
            return CustManagerName;
        }

        public int getCustomerId() {
            return CustomerId;
        }

        public String getCustomerName() {
            return CustomerName;
        }

        public int getStage() {
            return Stage;
        }

        public String getStageStr() {
            return StageStr;
        }

        public Object getMark() {
            return Mark;
        }

        public String getRemark() {
            return Remark;
        }

        public String getCreateByNo() {
            return CreateByNo;
        }

        public String getCreateByName() {
            return CreateByName;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public String getCreateTimeStr() {
            return CreateTimeStr;
        }

        public String getModifyByNo() {
            return ModifyByNo;
        }

        public String getModifyByName() {
            return ModifyByName;
        }

        public String getModifyTime() {
            return ModifyTime;
        }

        public String getModifyTimeStr() {
            return ModifyTimeStr;
        }

        public List<ContactsEntity> getContacts() {
            return Contacts;
        }

        public List<MattersEntity> getMatters() {
            return Matters;
        }

        public List<StageLogsEntity> getStageLogs() {
            return StageLogs;
        }

        public static class ContactsEntity implements Parcelable {
            /**
             * Id : 7
             * ContactId : 18
             * ContactName : 打发
             * ContactTitle : 小姐
             * ContactPhone : 13312341221
             * IsMain : false
             * Role : 2
             * RoleStr : 辅助决策者
             * Support : 3
             * SupportStr : 乐意接待
             * CreateByNo : null
             * CreateByName : null
             * CreateTime : /Date(-62135596800000)/
             * CreateTimeStr : 0001-01-01 00:00:00
             * ModifyByNo : null
             * ModifyByName : null
             * ModifyTime : /Date(-62135596800000)/
             * ModifyTimeStr : 0001-01-01 00:00:00
             */

            private int Id;
            private int ContactId;
            private String ContactName;
            private String ContactTitle;
            private String ContactPhone;
            private boolean IsMain;
            private int Role;
            private String RoleStr;
            private int Support;
            private String SupportStr;
            private String CreateByNo;
            private String CreateByName;
            private String CreateTime;
            private String CreateTimeStr;
            private String ModifyByNo;
            private String ModifyByName;
            private String ModifyTime;
            private String ModifyTimeStr;

            public void setId(int Id) {
                this.Id = Id;
            }

            public void setContactId(int ContactId) {
                this.ContactId = ContactId;
            }

            public void setContactName(String ContactName) {
                this.ContactName = ContactName;
            }

            public void setContactTitle(String ContactTitle) {
                this.ContactTitle = ContactTitle;
            }

            public void setContactPhone(String ContactPhone) {
                this.ContactPhone = ContactPhone;
            }

            public void setIsMain(boolean IsMain) {
                this.IsMain = IsMain;
            }

            public void setRole(int Role) {
                this.Role = Role;
            }

            public void setRoleStr(String RoleStr) {
                this.RoleStr = RoleStr;
            }

            public void setSupport(int Support) {
                this.Support = Support;
            }

            public void setSupportStr(String SupportStr) {
                this.SupportStr = SupportStr;
            }

            public void setCreateByNo(String CreateByNo) {
                this.CreateByNo = CreateByNo;
            }

            public void setCreateByName(String CreateByName) {
                this.CreateByName = CreateByName;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public void setCreateTimeStr(String CreateTimeStr) {
                this.CreateTimeStr = CreateTimeStr;
            }

            public void setModifyByNo(String ModifyByNo) {
                this.ModifyByNo = ModifyByNo;
            }

            public void setModifyByName(String ModifyByName) {
                this.ModifyByName = ModifyByName;
            }

            public void setModifyTime(String ModifyTime) {
                this.ModifyTime = ModifyTime;
            }

            public void setModifyTimeStr(String ModifyTimeStr) {
                this.ModifyTimeStr = ModifyTimeStr;
            }

            public int getId() {
                return Id;
            }

            public int getContactId() {
                return ContactId;
            }

            public String getContactName() {
                return ContactName;
            }

            public String getContactTitle() {
                return ContactTitle;
            }

            public String getContactPhone() {
                return ContactPhone;
            }

            public boolean getIsMain() {
                return IsMain;
            }

            public int getRole() {
                return Role;
            }

            public String getRoleStr() {
                return RoleStr;
            }

            public int getSupport() {
                return Support;
            }

            public String getSupportStr() {
                return SupportStr;
            }

            public Object getCreateByNo() {
                return CreateByNo;
            }

            public Object getCreateByName() {
                return CreateByName;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public String getCreateTimeStr() {
                return CreateTimeStr;
            }

            public Object getModifyByNo() {
                return ModifyByNo;
            }

            public Object getModifyByName() {
                return ModifyByName;
            }

            public String getModifyTime() {
                return ModifyTime;
            }

            public String getModifyTimeStr() {
                return ModifyTimeStr;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.Id);
                dest.writeInt(this.ContactId);
                dest.writeString(this.ContactName);
                dest.writeString(this.ContactTitle);
                dest.writeString(this.ContactPhone);
                dest.writeByte(IsMain ? (byte) 1 : (byte) 0);
                dest.writeInt(this.Role);
                dest.writeString(this.RoleStr);
                dest.writeInt(this.Support);
                dest.writeString(this.SupportStr);
                dest.writeString(this.CreateByNo);
                dest.writeString(this.CreateByName);
                dest.writeString(this.CreateTime);
                dest.writeString(this.CreateTimeStr);
                dest.writeString(this.ModifyByNo);
                dest.writeString(this.ModifyByName);
                dest.writeString(this.ModifyTime);
                dest.writeString(this.ModifyTimeStr);
            }

            public ContactsEntity() {
            }

            protected ContactsEntity(Parcel in) {
                this.Id = in.readInt();
                this.ContactId = in.readInt();
                this.ContactName = in.readString();
                this.ContactTitle = in.readString();
                this.ContactPhone = in.readString();
                this.IsMain = in.readByte() != 0;
                this.Role = in.readInt();
                this.RoleStr = in.readString();
                this.Support = in.readInt();
                this.SupportStr = in.readString();
                this.CreateByNo = in.readString();
                this.CreateByName = in.readString();
                this.CreateTime = in.readString();
                this.CreateTimeStr = in.readString();
                this.ModifyByNo = in.readString();
                this.ModifyByName =in.readString();
                this.ModifyTime = in.readString();
                this.ModifyTimeStr = in.readString();
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

        public static class MattersEntity implements Parcelable {

            /**
             * Id : 7
             * MatterId : 5
             * MatterName : 商标注销
             * OfficialCost : 1222
             * AgencyCost : 2222
             * OtherCost : 2222
             * TotalCost : 5666
             * Remark : 倒萨
             * Files : [{"Id":1}]
             * CreateByNo : null
             * CreateByName : null
             * CreateTime : /Date(-62135596800000)/
             * CreateTimeStr : 0001-01-01 00:00:00
             * ModifyByNo : null
             * ModifyByName : null
             * ModifyTime : /Date(-62135596800000)/
             * ModifyTimeStr : 0001-01-01 00:00:00
             */

            private int Id;
            private int MatterId;
            private String MatterName;
            private int OfficialCost;
            private int AgencyCost;
            private int OtherCost;
            private int TotalCost;
            private String Remark;
            private String CreateByNo;
            private String CreateByName;
            private String CreateTime;
            private String CreateTimeStr;
            private String ModifyByNo;
            private String ModifyByName;
            private String ModifyTime;
            private String ModifyTimeStr;
            private List<FilesEntity> Files;

            public void setId(int Id) {
                this.Id = Id;
            }

            public void setMatterId(int MatterId) {
                this.MatterId = MatterId;
            }

            public void setMatterName(String MatterName) {
                this.MatterName = MatterName;
            }

            public void setOfficialCost(int OfficialCost) {
                this.OfficialCost = OfficialCost;
            }

            public void setAgencyCost(int AgencyCost) {
                this.AgencyCost = AgencyCost;
            }

            public void setOtherCost(int OtherCost) {
                this.OtherCost = OtherCost;
            }

            public void setTotalCost(int TotalCost) {
                this.TotalCost = TotalCost;
            }

            public void setRemark(String Remark) {
                this.Remark = Remark;
            }

            public void setCreateByNo(String CreateByNo) {
                this.CreateByNo = CreateByNo;
            }

            public void setCreateByName(String CreateByName) {
                this.CreateByName = CreateByName;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public void setCreateTimeStr(String CreateTimeStr) {
                this.CreateTimeStr = CreateTimeStr;
            }

            public void setModifyByNo(String ModifyByNo) {
                this.ModifyByNo = ModifyByNo;
            }

            public void setModifyByName(String ModifyByName) {
                this.ModifyByName = ModifyByName;
            }

            public void setModifyTime(String ModifyTime) {
                this.ModifyTime = ModifyTime;
            }

            public void setModifyTimeStr(String ModifyTimeStr) {
                this.ModifyTimeStr = ModifyTimeStr;
            }

            public void setFiles(List<FilesEntity> Files) {
                this.Files = Files;
            }

            public int getId() {
                return Id;
            }

            public int getMatterId() {
                return MatterId;
            }

            public String getMatterName() {
                return MatterName;
            }

            public int getOfficialCost() {
                return OfficialCost;
            }

            public int getAgencyCost() {
                return AgencyCost;
            }

            public int getOtherCost() {
                return OtherCost;
            }

            public int getTotalCost() {
                return TotalCost;
            }

            public String getRemark() {
                return Remark;
            }

            public Object getCreateByNo() {
                return CreateByNo;
            }

            public Object getCreateByName() {
                return CreateByName;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public String getCreateTimeStr() {
                return CreateTimeStr;
            }

            public Object getModifyByNo() {
                return ModifyByNo;
            }

            public Object getModifyByName() {
                return ModifyByName;
            }

            public String getModifyTime() {
                return ModifyTime;
            }

            public String getModifyTimeStr() {
                return ModifyTimeStr;
            }

            public List<FilesEntity> getFiles() {
                return Files;
            }

            public static class FilesEntity {
                /**
                 * Id : 1
                 */

                private int Id;

                public void setId(int Id) {
                    this.Id = Id;
                }

                public int getId() {
                    return Id;
                }
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.Id);
                dest.writeInt(this.MatterId);
                dest.writeString(this.MatterName);
                dest.writeInt(this.OfficialCost);
                dest.writeInt(this.AgencyCost);
                dest.writeInt(this.OtherCost);
                dest.writeInt(this.TotalCost);
                dest.writeString(this.Remark);
                dest.writeString(this.CreateByNo);
                dest.writeString(this.CreateByName);
                dest.writeString(this.CreateTime);
                dest.writeString(this.CreateTimeStr);
                dest.writeString(this.ModifyByNo);
                dest.writeString(this.ModifyByName);
                dest.writeString(this.ModifyTime);
                dest.writeString(this.ModifyTimeStr);
                dest.writeList(this.Files);
            }

            public MattersEntity() {
            }

            protected MattersEntity(Parcel in) {
                this.Id = in.readInt();
                this.MatterId = in.readInt();
                this.MatterName = in.readString();
                this.OfficialCost = in.readInt();
                this.AgencyCost = in.readInt();
                this.OtherCost = in.readInt();
                this.TotalCost = in.readInt();
                this.Remark = in.readString();
                this.CreateByNo = in.readString();
                this.CreateByName = in.readString();
                this.CreateTime = in.readString();
                this.CreateTimeStr = in.readString();
                this.ModifyByNo = in.readString();
                this.ModifyByName = in.readString();
                this.ModifyTime = in.readString();
                this.ModifyTimeStr = in.readString();
                this.Files = new ArrayList<>();
                in.readList(this.Files, List.class.getClassLoader());
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

        public static class StageLogsEntity implements Parcelable {

            /**
             * Id : 13
             * Stage : 1
             * StageStr : 初步接触
             * Reason : 初始化阶段
             * CreateByNo : admin
             * CreateByName : 管理员
             * CreateTime : /Date(1442284896000)/
             * CreateTimeStr : 2015-09-15 10:41
             */

            private int Id;
            private int Stage;
            private String StageStr;
            private String Reason;
            private String CreateByNo;
            private String CreateByName;
            private String CreateTime;
            private String CreateTimeStr;

            public void setId(int Id) {
                this.Id = Id;
            }

            public void setStage(int Stage) {
                this.Stage = Stage;
            }

            public void setStageStr(String StageStr) {
                this.StageStr = StageStr;
            }

            public void setReason(String Reason) {
                this.Reason = Reason;
            }

            public void setCreateByNo(String CreateByNo) {
                this.CreateByNo = CreateByNo;
            }

            public void setCreateByName(String CreateByName) {
                this.CreateByName = CreateByName;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public void setCreateTimeStr(String CreateTimeStr) {
                this.CreateTimeStr = CreateTimeStr;
            }

            public int getId() {
                return Id;
            }

            public int getStage() {
                return Stage;
            }

            public String getStageStr() {
                return StageStr;
            }

            public String getReason() {
                return Reason;
            }

            public String getCreateByNo() {
                return CreateByNo;
            }

            public String getCreateByName() {
                return CreateByName;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public String getCreateTimeStr() {
                return CreateTimeStr;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.Id);
                dest.writeInt(this.Stage);
                dest.writeString(this.StageStr);
                dest.writeString(this.Reason);
                dest.writeString(this.CreateByNo);
                dest.writeString(this.CreateByName);
                dest.writeString(this.CreateTime);
                dest.writeString(this.CreateTimeStr);
            }

            public StageLogsEntity() {
            }

            protected StageLogsEntity(Parcel in) {
                this.Id = in.readInt();
                this.Stage = in.readInt();
                this.StageStr = in.readString();
                this.Reason = in.readString();
                this.CreateByNo = in.readString();
                this.CreateByName = in.readString();
                this.CreateTime = in.readString();
                this.CreateTimeStr = in.readString();
            }

            public static final Creator<StageLogsEntity> CREATOR = new Creator<StageLogsEntity>() {
                public StageLogsEntity createFromParcel(Parcel source) {
                    return new StageLogsEntity(source);
                }

                public StageLogsEntity[] newArray(int size) {
                    return new StageLogsEntity[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.Id);
            dest.writeString(this.Title);
            dest.writeString(this.CustManagerNo);
            dest.writeString(this.CustManagerName);
            dest.writeInt(this.CustomerId);
            dest.writeString(this.CustomerName);
            dest.writeInt(this.Stage);
            dest.writeString(this.StageStr);
            dest.writeString(this.Mark);
            dest.writeString(this.Remark);
            dest.writeString(this.CreateByNo);
            dest.writeString(this.CreateByName);
            dest.writeString(this.CreateTime);
            dest.writeString(this.CreateTimeStr);
            dest.writeString(this.ModifyByNo);
            dest.writeString(this.ModifyByName);
            dest.writeString(this.ModifyTime);
            dest.writeString(this.ModifyTimeStr);
            dest.writeList(this.Contacts);
            dest.writeList(this.Matters);
            dest.writeList(this.StageLogs);
        }

        public CooperateModel() {
        }

        protected CooperateModel(Parcel in) {
            this.Id = in.readString();
            this.Title = in.readString();
            this.CustManagerNo = in.readString();
            this.CustManagerName = in.readString();
            this.CustomerId = in.readInt();
            this.CustomerName = in.readString();
            this.Stage = in.readInt();
            this.StageStr = in.readString();
            this.Mark = in.readString();
            this.Remark = in.readString();
            this.CreateByNo = in.readString();
            this.CreateByName = in.readString();
            this.CreateTime = in.readString();
            this.CreateTimeStr = in.readString();
            this.ModifyByNo = in.readString();
            this.ModifyByName = in.readString();
            this.ModifyTime = in.readString();
            this.ModifyTimeStr = in.readString();
            this.Contacts = new ArrayList<>();
            in.readList(this.Contacts, List.class.getClassLoader());
            this.Matters = new ArrayList<>();
            in.readList(this.Matters, List.class.getClassLoader());
            this.StageLogs = new ArrayList<>();
            in.readList(this.StageLogs, List.class.getClassLoader());
        }

        public static final Parcelable.Creator<CooperateModel> CREATOR = new Parcelable.Creator<CooperateModel>() {
            public CooperateModel createFromParcel(Parcel source) {
                return new CooperateModel(source);
            }

            public CooperateModel[] newArray(int size) {
                return new CooperateModel[size];
            }
        };
    }
}
