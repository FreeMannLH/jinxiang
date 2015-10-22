package ideatc.jinxiang.bean.crm;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ccb on 2015/8/20.
 * 客户详情实体
 */
public class CustomerDetailBean implements Parcelable {

    /**
     * message :
     * model : {"ModifyByNo":"admin","FoundingTime":"/Date(1439568000000)/","FoundingTimeStr":"2015-08-15","Intent":9,"CustManagerNo":"admin","CreateByNo":"admin","CustManagerName":"管理员","CreateByName":"管理员","CustSource":"公开招标","FlagStr":"潜在客户","TradeId":9,"ModifyTimeStr":"2015-08-20 17:01:31","Mark":"FFA400","CustArea":"石狮市","LatencyLevel":"营销失败客户","RegisteredCapital":50000000,"Contacts":[{"ModifyByNo":null,"Phone":"13314954102","Hobbies":"打球","Fax":"123-4532543","ModifyByName":null,"CreateTimeStr":"0001-01-01 00:00:00","ModifyTime":"/Date(-62135596800000)/","CreateByNo":null,"OfficeTelephone":"059582135228","Title":"女士","Name":"陈奕迅","Birth":"/Date(1440432000000)/","CreateByName":null,"ModifyTimeStr":"0001-01-01 00:00:00","Mark":"FF2121","HomeTelephone":"","Email":"22222@qq.com","Post":"霸道总裁","CreateTime":"/Date(-62135596800000)/","Id":4,"Remark":"我是周杰伦","Addr":"丰泽街益达大厦","BirthStr":"2015-08-25"}],"StaffNum":2000,"IntentName":"商标异议申请","Addr":"石狮市石狮市石狮市","LegalAddr":"泉州街丰泽区益达大厦A110","AnnualTurnover":220000000,"CustType":2,"TradeName":"非金属矿物制品业","Honors":[],"Flag":1,"Fax":null,"ModifyByName":"管理员","CreateTimeStr":"2015-08-20 17:01:31","CustLevel":"大客户","ModifyTime":"/Date(1440061291000)/","CustTypeStr":"公司客户","Name":"周杰伦","CreateTime":"/Date(1440061291000)/","Id":3,"Remark":"我我我我我娃娃"}
     * success : true
     */
    private String message;
    private CustomerBean model;
    private boolean success;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setModel(CustomerBean model) {
        this.model = model;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public CustomerBean getModel() {
        return model;
    }

    public boolean isSuccess() {
        return success;
    }

    public static class CustomerBean implements Parcelable {
        /**
         * ModifyByNo : admin
         * FoundingTime : /Date(1439568000000)/
         * FoundingTimeStr : 2015-08-15
         * Intent : 9
         * CustManagerNo : admin
         * CreateByNo : admin
         * CustManagerName : 管理员
         * CreateByName : 管理员
         * CustSource : 公开招标
         * FlagStr : 潜在客户
         * TradeId : 9
         * ModifyTimeStr : 2015-08-20 17:01:31
         * Mark : FFA400
         * CustArea : 石狮市
         * LatencyLevel : 营销失败客户
         * RegisteredCapital : 50000000
         * Contacts : [{"ModifyByNo":null,"Phone":"13314954102","Hobbies":"打球","Fax":"123-4532543","ModifyByName":null,"CreateTimeStr":"0001-01-01 00:00:00","ModifyTime":"/Date(-62135596800000)/","CreateByNo":null,"OfficeTelephone":"059582135228","Title":"女士","Name":"陈奕迅","Birth":"/Date(1440432000000)/","CreateByName":null,"ModifyTimeStr":"0001-01-01 00:00:00","Mark":"FF2121","HomeTelephone":"","Email":"22222@qq.com","Post":"霸道总裁","CreateTime":"/Date(-62135596800000)/","Id":4,"Remark":"我是周杰伦","Addr":"丰泽街益达大厦","BirthStr":"2015-08-25"}]
         * StaffNum : 2000
         * IntentName : 商标异议申请
         * Addr : 石狮市石狮市石狮市
         * LegalAddr : 泉州街丰泽区益达大厦A110
         * AnnualTurnover : 220000000
         * CustType : 2
         * TradeName : 非金属矿物制品业
         * Honors : []
         * Flag : 1
         * Fax : null
         * ModifyByName : 管理员
         * CreateTimeStr : 2015-08-20 17:01:31
         * CustLevel : 大客户
         * ModifyTime : /Date(1440061291000)/
         * CustTypeStr : 公司客户
         * Name : 周杰伦
         * CreateTime : /Date(1440061291000)/
         * Id : 3
         * Remark : 我我我我我娃娃
         */
        private String ModifyByNo;
        private String FoundingTime;
        private String FoundingTimeStr;
        private int Intent;
        private String CustManagerNo;
        private String CreateByNo;
        private String CustManagerName;
        private String CreateByName;
        private String CustSource;
        private String FlagStr;
        private int TradeId;
        private String ModifyTimeStr;
        private String Mark;
        private String CustArea;
        private String LatencyLevel;
        private int RegisteredCapital;
        private List<ContactsEntity> Contacts;
        private int StaffNum;
        private String IntentName;
        private String Addr;
        private String LegalAddr;
        private int AnnualTurnover;
        private int CustType;
        private String TradeName;
        private List<?> Honors;
        private int Flag;
        private String Fax;
        private String ModifyByName;
        private String CreateTimeStr;
        private String CustLevel;
        private String ModifyTime;
        private String CustTypeStr;
        private String Name;
        private String CreateTime;
        private int Id;
        private String Remark;

        public void setModifyByNo(String ModifyByNo) {
            this.ModifyByNo = ModifyByNo;
        }

        public void setFoundingTime(String FoundingTime) {
            this.FoundingTime = FoundingTime;
        }

        public void setFoundingTimeStr(String FoundingTimeStr) {
            this.FoundingTimeStr = FoundingTimeStr;
        }

        public void setIntent(int Intent) {
            this.Intent = Intent;
        }

        public void setCustManagerNo(String CustManagerNo) {
            this.CustManagerNo = CustManagerNo;
        }

        public void setCreateByNo(String CreateByNo) {
            this.CreateByNo = CreateByNo;
        }

        public void setCustManagerName(String CustManagerName) {
            this.CustManagerName = CustManagerName;
        }

        public void setCreateByName(String CreateByName) {
            this.CreateByName = CreateByName;
        }

        public void setCustSource(String CustSource) {
            this.CustSource = CustSource;
        }

        public void setFlagStr(String FlagStr) {
            this.FlagStr = FlagStr;
        }

        public void setTradeId(int TradeId) {
            this.TradeId = TradeId;
        }

        public void setModifyTimeStr(String ModifyTimeStr) {
            this.ModifyTimeStr = ModifyTimeStr;
        }

        public void setMark(String Mark) {
            this.Mark = Mark;
        }

        public void setCustArea(String CustArea) {
            this.CustArea = CustArea;
        }

        public void setLatencyLevel(String LatencyLevel) {
            this.LatencyLevel = LatencyLevel;
        }

        public void setRegisteredCapital(int RegisteredCapital) {
            this.RegisteredCapital = RegisteredCapital;
        }

        public void setContacts(List<ContactsEntity> Contacts) {
            this.Contacts = Contacts;
        }

        public void setStaffNum(int StaffNum) {
            this.StaffNum = StaffNum;
        }

        public void setIntentName(String IntentName) {
            this.IntentName = IntentName;
        }

        public void setAddr(String Addr) {
            this.Addr = Addr;
        }

        public void setLegalAddr(String LegalAddr) {
            this.LegalAddr = LegalAddr;
        }

        public void setAnnualTurnover(int AnnualTurnover) {
            this.AnnualTurnover = AnnualTurnover;
        }

        public void setCustType(int CustType) {
            this.CustType = CustType;
        }

        public void setTradeName(String TradeName) {
            this.TradeName = TradeName;
        }

        public void setHonors(List<?> Honors) {
            this.Honors = Honors;
        }

        public void setFlag(int Flag) {
            this.Flag = Flag;
        }

        public void setFax(String Fax) {
            this.Fax = Fax;
        }

        public void setModifyByName(String ModifyByName) {
            this.ModifyByName = ModifyByName;
        }

        public void setCreateTimeStr(String CreateTimeStr) {
            this.CreateTimeStr = CreateTimeStr;
        }

        public void setCustLevel(String CustLevel) {
            this.CustLevel = CustLevel;
        }

        public void setModifyTime(String ModifyTime) {
            this.ModifyTime = ModifyTime;
        }

        public void setCustTypeStr(String CustTypeStr) {
            this.CustTypeStr = CustTypeStr;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
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

        public String getFoundingTime() {
            return FoundingTime;
        }

        public String getFoundingTimeStr() {
            return FoundingTimeStr;
        }

        public int getIntent() {
            return Intent;
        }

        public String getCustManagerNo() {
            return CustManagerNo;
        }

        public String getCreateByNo() {
            return CreateByNo;
        }

        public String getCustManagerName() {
            return CustManagerName;
        }

        public String getCreateByName() {
            return CreateByName;
        }

        public String getCustSource() {
            return CustSource;
        }

        public String getFlagStr() {
            return FlagStr;
        }

        public int getTradeId() {
            return TradeId;
        }

        public String getModifyTimeStr() {
            return ModifyTimeStr;
        }

        public String getMark() {
            return Mark;
        }

        public String getCustArea() {
            return CustArea;
        }

        public String getLatencyLevel() {
            return LatencyLevel;
        }

        public int getRegisteredCapital() {
            return RegisteredCapital;
        }

        public List<ContactsEntity> getContacts() {
            return Contacts;
        }

        public int getStaffNum() {
            return StaffNum;
        }

        public String getIntentName() {
            return IntentName;
        }

        public String getAddr() {
            return Addr;
        }

        public String getLegalAddr() {
            return LegalAddr;
        }

        public int getAnnualTurnover() {
            return AnnualTurnover;
        }

        public int getCustType() {
            return CustType;
        }

        public String getTradeName() {
            return TradeName;
        }

        public List<?> getHonors() {
            return Honors;
        }

        public int getFlag() {
            return Flag;
        }

        public String getFax() {
            return Fax;
        }

        public String getModifyByName() {
            return ModifyByName;
        }

        public String getCreateTimeStr() {
            return CreateTimeStr;
        }

        public String getCustLevel() {
            return CustLevel;
        }

        public String getModifyTime() {
            return ModifyTime;
        }

        public String getCustTypeStr() {
            return CustTypeStr;
        }

        public String getName() {
            return Name;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public int getId() {
            return Id;
        }

        public String getRemark() {
            return Remark;
        }

        public static class ContactsEntity implements Parcelable {
            /**
             * ModifyByNo : null
             * Phone : 13314954102
             * Hobbies : 打球
             * Fax : 123-4532543
             * ModifyByName : null
             * CreateTimeStr : 0001-01-01 00:00:00
             * ModifyTime : /Date(-62135596800000)/
             * CreateByNo : null
             * OfficeTelephone : 059582135228
             * Title : 女士
             * Name : 陈奕迅
             * Birth : /Date(1440432000000)/
             * CreateByName : null
             * ModifyTimeStr : 0001-01-01 00:00:00
             * Mark : FF2121
             * HomeTelephone :
             * Email : 22222@qq.com
             * Post : 霸道总裁
             * CreateTime : /Date(-62135596800000)/
             * Id : 4
             * Remark : 我是周杰伦
             * Addr : 丰泽街益达大厦
             * BirthStr : 2015-08-25
             */
            private String ModifyByNo;
            private String Phone;
            private String Hobbies;
            private String Fax;
            private String ModifyByName;
            private String CreateTimeStr;
            private String ModifyTime;
            private String CreateByNo;
            private String OfficeTelephone;
            private String Title;
            private String Name;
            private String Birth;
            private String CreateByName;
            private String ModifyTimeStr;
            private String Mark;
            private String HomeTelephone;
            private String Email;
            private String Post;
            private String CreateTime;
            private int Id;
            private String Remark;
            private String Addr;
            private String BirthStr;

            public void setModifyByNo(String ModifyByNo) {
                this.ModifyByNo = ModifyByNo;
            }

            public void setPhone(String Phone) {
                this.Phone = Phone;
            }

            public void setHobbies(String Hobbies) {
                this.Hobbies = Hobbies;
            }

            public void setFax(String Fax) {
                this.Fax = Fax;
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

            public void setCreateByNo(String CreateByNo) {
                this.CreateByNo = CreateByNo;
            }

            public void setOfficeTelephone(String OfficeTelephone) {
                this.OfficeTelephone = OfficeTelephone;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public void setBirth(String Birth) {
                this.Birth = Birth;
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

            public void setHomeTelephone(String HomeTelephone) {
                this.HomeTelephone = HomeTelephone;
            }

            public void setEmail(String Email) {
                this.Email = Email;
            }

            public void setPost(String Post) {
                this.Post = Post;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public void setRemark(String Remark) {
                this.Remark = Remark;
            }

            public void setAddr(String Addr) {
                this.Addr = Addr;
            }

            public void setBirthStr(String BirthStr) {
                this.BirthStr = BirthStr;
            }

            public String getModifyByNo() {
                return ModifyByNo;
            }

            public String getPhone() {
                return Phone;
            }

            public String getHobbies() {
                return Hobbies;
            }

            public String getFax() {
                return Fax;
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

            public String getCreateByNo() {
                return CreateByNo;
            }

            public String getOfficeTelephone() {
                return OfficeTelephone;
            }

            public String getTitle() {
                return Title;
            }

            public String getName() {
                return Name;
            }

            public String getBirth() {
                return Birth;
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

            public String getHomeTelephone() {
                return HomeTelephone;
            }

            public String getEmail() {
                return Email;
            }

            public String getPost() {
                return Post;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public int getId() {
                return Id;
            }

            public String getRemark() {
                return Remark;
            }

            public String getAddr() {
                return Addr;
            }

            public String getBirthStr() {
                return BirthStr;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.ModifyByNo);
                dest.writeString(this.Phone);
                dest.writeString(this.Hobbies);
                dest.writeString(this.Fax);
                dest.writeString(this.ModifyByName);
                dest.writeString(this.CreateTimeStr);
                dest.writeString(this.ModifyTime);
                dest.writeString(this.CreateByNo);
                dest.writeString(this.OfficeTelephone);
                dest.writeString(this.Title);
                dest.writeString(this.Name);
                dest.writeString(this.Birth);
                dest.writeString(this.CreateByName);
                dest.writeString(this.ModifyTimeStr);
                dest.writeString(this.Mark);
                dest.writeString(this.HomeTelephone);
                dest.writeString(this.Email);
                dest.writeString(this.Post);
                dest.writeString(this.CreateTime);
                dest.writeInt(this.Id);
                dest.writeString(this.Remark);
                dest.writeString(this.Addr);
                dest.writeString(this.BirthStr);
            }

            public ContactsEntity() {
            }

            protected ContactsEntity(Parcel in) {
                this.ModifyByNo = in.readString();
                this.Phone = in.readString();
                this.Hobbies = in.readString();
                this.Fax = in.readString();
                this.ModifyByName = in.readString();
                this.CreateTimeStr = in.readString();
                this.ModifyTime = in.readString();
                this.CreateByNo = in.readString();
                this.OfficeTelephone = in.readString();
                this.Title = in.readString();
                this.Name = in.readString();
                this.Birth = in.readString();
                this.CreateByName = in.readString();
                this.ModifyTimeStr = in.readString();
                this.Mark = in.readString();
                this.HomeTelephone = in.readString();
                this.Email = in.readString();
                this.Post = in.readString();
                this.CreateTime = in.readString();
                this.Id = in.readInt();
                this.Remark = in.readString();
                this.Addr = in.readString();
                this.BirthStr = in.readString();
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
            dest.writeString(this.ModifyByNo);
            dest.writeString(this.FoundingTime);
            dest.writeString(this.FoundingTimeStr);
            dest.writeInt(this.Intent);
            dest.writeString(this.CustManagerNo);
            dest.writeString(this.CreateByNo);
            dest.writeString(this.CustManagerName);
            dest.writeString(this.CreateByName);
            dest.writeString(this.CustSource);
            dest.writeString(this.FlagStr);
            dest.writeInt(this.TradeId);
            dest.writeString(this.ModifyTimeStr);
            dest.writeString(this.Mark);
            dest.writeString(this.CustArea);
            dest.writeString(this.LatencyLevel);
            dest.writeInt(this.RegisteredCapital);
            dest.writeList(this.Contacts);
            dest.writeInt(this.StaffNum);
            dest.writeString(this.IntentName);
            dest.writeString(this.Addr);
            dest.writeString(this.LegalAddr);
            dest.writeInt(this.AnnualTurnover);
            dest.writeInt(this.CustType);
            dest.writeString(this.TradeName);
            dest.writeList(this.Honors);
            dest.writeInt(this.Flag);
            dest.writeString(this.Fax);
            dest.writeString(this.ModifyByName);
            dest.writeString(this.CreateTimeStr);
            dest.writeString(this.CustLevel);
            dest.writeString(this.ModifyTime);
            dest.writeString(this.CustTypeStr);
            dest.writeString(this.Name);
            dest.writeString(this.CreateTime);
            dest.writeInt(this.Id);
            dest.writeString(this.Remark);
        }

        public CustomerBean() {
        }

        protected CustomerBean(Parcel in) {
            this.ModifyByNo = in.readString();
            this.FoundingTime = in.readString();
            this.FoundingTimeStr = in.readString();
            this.Intent = in.readInt();
            this.CustManagerNo = in.readString();
            this.CreateByNo = in.readString();
            this.CustManagerName = in.readString();
            this.CreateByName = in.readString();
            this.CustSource = in.readString();
            this.FlagStr = in.readString();
            this.TradeId = in.readInt();
            this.ModifyTimeStr = in.readString();
            this.Mark = in.readString();
            this.CustArea = in.readString();
            this.LatencyLevel = in.readString();
            this.RegisteredCapital = in.readInt();
            this.Contacts = new ArrayList<ContactsEntity>();
            in.readList(this.Contacts, List.class.getClassLoader());
            this.StaffNum = in.readInt();
            this.IntentName = in.readString();
            this.Addr = in.readString();
            this.LegalAddr = in.readString();
            this.AnnualTurnover = in.readInt();
            this.CustType = in.readInt();
            this.TradeName = in.readString();
            this.Honors = new ArrayList<>();
            in.readList(this.Honors, List.class.getClassLoader());
            this.Flag = in.readInt();
            this.Fax = in.readString();
            this.ModifyByName = in.readString();
            this.CreateTimeStr = in.readString();
            this.CustLevel = in.readString();
            this.ModifyTime = in.readString();
            this.CustTypeStr = in.readString();
            this.Name = in.readString();
            this.CreateTime = in.readString();
            this.Id = in.readInt();
            this.Remark = in.readString();
        }

        public static final Creator<CustomerBean> CREATOR = new Creator<CustomerBean>() {
            public CustomerBean createFromParcel(Parcel source) {
                return new CustomerBean(source);
            }

            public CustomerBean[] newArray(int size) {
                return new CustomerBean[size];
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

    public CustomerDetailBean() {
    }

    protected CustomerDetailBean(Parcel in) {
        this.message = in.readString();
        this.model = in.readParcelable(CustomerBean.class.getClassLoader());
        this.success = in.readByte() != 0;
    }

    public static final Parcelable.Creator<CustomerDetailBean> CREATOR = new Parcelable.Creator<CustomerDetailBean>() {
        public CustomerDetailBean createFromParcel(Parcel source) {
            return new CustomerDetailBean(source);
        }

        public CustomerDetailBean[] newArray(int size) {
            return new CustomerDetailBean[size];
        }
    };
}
