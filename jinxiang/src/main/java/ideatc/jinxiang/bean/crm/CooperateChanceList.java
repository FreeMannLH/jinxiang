package ideatc.jinxiang.bean.crm;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ccb
 * create at 2015/9/15 10:07
 * 合作机会列表
 */
public class CooperateChanceList {


    /**
     * success : true
     * message :
     * list : [{"Id":"20150915100001","Title":"啊大大","CustManagerNo":"admin","CustManagerName":"管理员","CustomerId":17,"CustomerName":"陈奕迅","Stage":1,"StageStr":"初步接触","Mark":null,"Remark":"当然是无法","Contacts":[{"Id":7,"ContactId":18,"ContactName":"打发","ContactTitle":"小姐","ContactPhone":"13312341221","IsMain":false,"Role":2,"RoleStr":"辅助决策者","Support":3,"SupportStr":"乐意接待","CreateByNo":null,"CreateByName":null,"CreateTime":"/Date(-62135596800000)/","CreateTimeStr":"0001-01-01 00:00:00","ModifyByNo":null,"ModifyByName":null,"ModifyTime":"/Date(-62135596800000)/","ModifyTimeStr":"0001-01-01 00:00:00"}],"Matters":[{"Id":7,"MatterId":5,"MatterName":"商标注销","OfficialCost":1222,"AgencyCost":2222,"OtherCost":2222,"TotalCost":5666,"Remark":"倒萨","Files":[{"Id":1}],"CreateByNo":null,"CreateByName":null,"CreateTime":"/Date(-62135596800000)/","CreateTimeStr":"0001-01-01 00:00:00","ModifyByNo":null,"ModifyByName":null,"ModifyTime":"/Date(-62135596800000)/","ModifyTimeStr":"0001-01-01 00:00:00"}],"StageLogs":[{"Id":13,"Stage":1,"StageStr":"初步接触","Reason":"初始化阶段","CreateByNo":"admin","CreateByName":"管理员","CreateTime":"/Date(1442284896000)/","CreateTimeStr":"2015-09-15 10:41"}],"CreateByNo":"admin","CreateByName":"管理员","CreateTime":"/Date(1442284896000)/","CreateTimeStr":"2015-09-15 10:41:36","ModifyByNo":"admin","ModifyByName":"管理员","ModifyTime":"/Date(1442284896000)/","ModifyTimeStr":"2015-09-15 10:41:36"},{"Id":"20150915090001","Title":"aaaaaa","CustManagerNo":"admin","CustManagerName":"管理员","CustomerId":17,"CustomerName":"陈奕迅","Stage":4,"StageStr":"方案报价","Mark":"44CEF6","Remark":"大大","Contacts":[{"Id":6,"ContactId":17,"ContactName":"aaaax","ContactTitle":"小姐","ContactPhone":"13312322121","IsMain":false,"Role":2,"RoleStr":"辅助决策者","Support":4,"SupportStr":"不冷不热","CreateByNo":" a","CreateByName":"a ","CreateTime":"/Date(-62135596800000)/","CreateTimeStr":"0001-01-01 00:00:00","ModifyByNo":" a","ModifyByName":" a","ModifyTime":"/Date(-62135596800000)/","ModifyTimeStr":"0001-01-01 00:00:00"}],"Matters":[{"Id":6,"MatterId":3,"MatterName":"商标续展","OfficialCost":222222,"AgencyCost":111111,"OtherCost":33334,"TotalCost":366667,"Remark":"的撒的撒的撒","Files":[{"Id":11}],"CreateByNo":" a","CreateByName":" a","CreateTime":"/Date(-62135596800000)/","CreateTimeStr":"0001-01-01 00:00:00","ModifyByNo":"a ","ModifyByName":" a","ModifyTime":"/Date(-62135596800000)/","ModifyTimeStr":"0001-01-01 00:00:00"}],"StageLogs":[{"Id":12,"Stage":4,"StageStr":"方案报价","Reason":"初始化阶段","CreateByNo":"admin","CreateByName":"管理员","CreateTime":"/Date(1442280774000)/","CreateTimeStr":"2015-09-15 09:32"}],"CreateByNo":"admin","CreateByName":"管理员","CreateTime":"/Date(1442280774000)/","CreateTimeStr":"2015-09-15 09:32:54","ModifyByNo":"admin","ModifyByName":"管理员","ModifyTime":"/Date(1442284560000)/","ModifyTimeStr":"2015-09-15 10:36:00"}]
     */

    private boolean success;
    private String message;
    private List<CooperateChanceEntity> list;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setList(List<CooperateChanceEntity> list) {
        this.list = list;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<CooperateChanceEntity> getList() {
        return list;
    }

    public static class CooperateChanceEntity implements Parcelable {

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

        public static class ContactsEntity {
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
            private Object CreateByNo;
            private Object CreateByName;
            private String CreateTime;
            private String CreateTimeStr;
            private Object ModifyByNo;
            private Object ModifyByName;
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

            public void setCreateByNo(Object CreateByNo) {
                this.CreateByNo = CreateByNo;
            }

            public void setCreateByName(Object CreateByName) {
                this.CreateByName = CreateByName;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public void setCreateTimeStr(String CreateTimeStr) {
                this.CreateTimeStr = CreateTimeStr;
            }

            public void setModifyByNo(Object ModifyByNo) {
                this.ModifyByNo = ModifyByNo;
            }

            public void setModifyByName(Object ModifyByName) {
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
        }

        public static class MattersEntity {
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
            private Object CreateByNo;
            private Object CreateByName;
            private String CreateTime;
            private String CreateTimeStr;
            private Object ModifyByNo;
            private Object ModifyByName;
            private String ModifyTime;
            private String ModifyTimeStr;
            private transient List<FilesEntity> Files;

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

            public void setCreateByNo(Object CreateByNo) {
                this.CreateByNo = CreateByNo;
            }

            public void setCreateByName(Object CreateByName) {
                this.CreateByName = CreateByName;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public void setCreateTimeStr(String CreateTimeStr) {
                this.CreateTimeStr = CreateTimeStr;
            }

            public void setModifyByNo(Object ModifyByNo) {
                this.ModifyByNo = ModifyByNo;
            }

            public void setModifyByName(Object ModifyByName) {
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
        }

        public static class StageLogsEntity {
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

        public CooperateChanceEntity() {
        }

        protected CooperateChanceEntity(Parcel in) {
            this.Id = in.readString();
            this.Title = in.readString();
            this.CustManagerNo = in.readString();
            this.CustManagerName = in.readString();
            this.CustomerId = in.readInt();
            this.CustomerName = in.readString();
            this.Stage = in.readInt();
            this.StageStr = in.readString();
            this.Mark = in.readParcelable(Object.class.getClassLoader());
            this.Remark = in.readString();
            this.CreateByNo = in.readString();
            this.CreateByName = in.readString();
            this.CreateTime = in.readString();
            this.CreateTimeStr = in.readString();
            this.ModifyByNo = in.readString();
            this.ModifyByName = in.readString();
            this.ModifyTime = in.readString();
            this.ModifyTimeStr = in.readString();
            this.Contacts = new ArrayList<ContactsEntity>();
            in.readList(this.Contacts, List.class.getClassLoader());
            this.Matters = new ArrayList<MattersEntity>();
            in.readList(this.Matters, List.class.getClassLoader());
            this.StageLogs = new ArrayList<StageLogsEntity>();
            in.readList(this.StageLogs, List.class.getClassLoader());
        }

        public static final Parcelable.Creator<CooperateChanceEntity> CREATOR = new Parcelable.Creator<CooperateChanceEntity>() {
            public CooperateChanceEntity createFromParcel(Parcel source) {
                return new CooperateChanceEntity(source);
            }

            public CooperateChanceEntity[] newArray(int size) {
                return new CooperateChanceEntity[size];
            }
        };
    }
}
