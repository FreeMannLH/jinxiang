package ideatc.jinxiang.bean.crm;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ccb on 2015/8/17.
 */
public class CustomerListBean implements Parcelable {

    /**
     * message :
     * list : [{"ModifyByNo":"admin","FoundingTime":null,"FoundingTimeStr":"","Intent":2,"CustManagerNo":"admin","CreateByNo":"admin","CustManagerName":"管理员","CreateByName":"管理员","CustSource":"电话来访","FlagStr":"潜在客户","TradeId":0,"ModifyTimeStr":"2015-08-17 14:28:25","Mark":"FF2121","CustArea":"丰泽区","LatencyLevel":"即将签订合同","RegisteredCapital":null,"Contacts":null,"StaffNum":null,"IntentName":"商标变更","Addr":"丰泽街益达大厦","LegalAddr":"丰泽街益达大厦A-110","AnnualTurnover":null,"CustType":1,"TradeName":"信息传输、计算机服务业和软件业","Honors":null,"Flag":1,"Fax":null,"ModifyByName":"管理员","CreateTimeStr":"2015-08-17 14:28:25","CustLevel":"中客户","ModifyTime":"/Date(1439792905000)/","CustTypeStr":"个人客户","Name":"aaaa","CreateTime":"/Date(1439792905000)/","Id":2,"Remark":null}]
     * success : true
     */
    private String message;
    private List<CustomerBean> list;
    private boolean success;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setList(List<CustomerBean> list) {
        this.list = list;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public List<CustomerBean> getList() {
        return list;
    }

    public boolean isSuccess() {
        return success;
    }

    public static class CustomerBean implements Parcelable {
        /**
         * ModifyByNo : admin
         * FoundingTime : null
         * FoundingTimeStr :
         * Intent : 2
         * CustManagerNo : admin
         * CreateByNo : admin
         * CustManagerName : 管理员
         * CreateByName : 管理员
         * CustSource : 电话来访
         * FlagStr : 潜在客户
         * TradeId : 0
         * ModifyTimeStr : 2015-08-17 14:28:25
         * Mark : FF2121
         * CustArea : 丰泽区
         * LatencyLevel : 即将签订合同
         * RegisteredCapital : null
         * Contacts : null
         * StaffNum : null
         * IntentName : 商标变更
         * Addr : 丰泽街益达大厦
         * LegalAddr : 丰泽街益达大厦A-110
         * AnnualTurnover : null
         * CustType : 1
         * TradeName : 信息传输、计算机服务业和软件业
         * Honors : null
         * Flag : 1
         * Fax : null
         * ModifyByName : 管理员
         * CreateTimeStr : 2015-08-17 14:28:25
         * CustLevel : 中客户
         * ModifyTime : /Date(1439792905000)/
         * CustTypeStr : 个人客户
         * Name : aaaa
         * CreateTime : /Date(1439792905000)/
         * Id : 2
         * Remark : null
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
        private String RegisteredCapital;
        private String Contacts;
        private String StaffNum;
        private String IntentName;
        private String Addr;
        private String LegalAddr;
        private String AnnualTurnover;
        private int CustType;
        private String TradeName;
        private String Honors;
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

        public void setRegisteredCapital(String RegisteredCapital) {
            this.RegisteredCapital = RegisteredCapital;
        }

        public void setContacts(String Contacts) {
            this.Contacts = Contacts;
        }

        public void setStaffNum(String StaffNum) {
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

        public void setAnnualTurnover(String AnnualTurnover) {
            this.AnnualTurnover = AnnualTurnover;
        }

        public void setCustType(int CustType) {
            this.CustType = CustType;
        }

        public void setTradeName(String TradeName) {
            this.TradeName = TradeName;
        }

        public void setHonors(String Honors) {
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

        public String getRegisteredCapital() {
            return RegisteredCapital;
        }

        public String getContacts() {
            return Contacts;
        }

        public String getStaffNum() {
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

        public String getAnnualTurnover() {
            return AnnualTurnover;
        }

        public int getCustType() {
            return CustType;
        }

        public String getTradeName() {
            return TradeName;
        }

        public String getHonors() {
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
            dest.writeString(this.RegisteredCapital);
            dest.writeString(this.Contacts);
            dest.writeString(this.StaffNum);
            dest.writeString(this.IntentName);
            dest.writeString(this.Addr);
            dest.writeString(this.LegalAddr);
            dest.writeString(this.AnnualTurnover);
            dest.writeInt(this.CustType);
            dest.writeString(this.TradeName);
            dest.writeString(this.Honors);
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
            this.RegisteredCapital = in.readString();
            this.Contacts = in.readString();
            this.StaffNum = in.readString();
            this.IntentName = in.readString();
            this.Addr = in.readString();
            this.LegalAddr = in.readString();
            this.AnnualTurnover = in.readString();
            this.CustType = in.readInt();
            this.TradeName = in.readString();
            this.Honors = in.readString();
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
        dest.writeList(this.list);
        dest.writeByte(success ? (byte) 1 : (byte) 0);
    }

    public CustomerListBean() {
    }

    protected CustomerListBean(Parcel in) {
        this.message = in.readString();
        this.list = new ArrayList<CustomerBean>();
        in.readList(this.list, List.class.getClassLoader());
        this.success = in.readByte() != 0;
    }

    public static final Parcelable.Creator<CustomerListBean> CREATOR = new Parcelable.Creator<CustomerListBean>() {
        public CustomerListBean createFromParcel(Parcel source) {
            return new CustomerListBean(source);
        }

        public CustomerListBean[] newArray(int size) {
            return new CustomerListBean[size];
        }
    };
}
