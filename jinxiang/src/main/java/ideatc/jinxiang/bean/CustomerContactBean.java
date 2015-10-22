package ideatc.jinxiang.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ccb on 2015/8/18.
 * 客户联系人实体
 */
public class CustomerContactBean implements Parcelable {

    /**
     * ModifyByNo : null
     * Phone : 13314021351
     * Hobbies :
     * Fax :
     * ModifyByName : null
     * CreateTimeStr : 0001-01-01 00:00:00
     * ModifyTime : /Date(-62135596800000)/
     * CreateByNo : null
     * OfficeTelephone : 87680992
     * Title : 先生
     * Name : 陈陈陈
     * Birth : /Date(1439827200000)/
     * CreateByName : null
     * ModifyTimeStr : 0001-01-01 00:00:00
     * Mark : FF2121
     * HomeTelephone :
     * Email :
     * Post : 啊啊啊
     * CreateTime : /Date(-62135596800000)/
     * Id : 2
     * Remark :
     * Addr :
     * BirthStr : 2015-08-18
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

    public CustomerContactBean() {
    }

    protected CustomerContactBean(Parcel in) {
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

    public static final Parcelable.Creator<CustomerContactBean> CREATOR = new Parcelable.Creator<CustomerContactBean>() {
        public CustomerContactBean createFromParcel(Parcel source) {
            return new CustomerContactBean(source);
        }

        public CustomerContactBean[] newArray(int size) {
            return new CustomerContactBean[size];
        }
    };
}
