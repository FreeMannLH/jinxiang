package ideatc.jinxiang.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Ccb on 2015/8/24.
 */
public class ContactBean implements Parcelable {

    /**
     * Phone :
     * RegNum : 00000001
     * Telephone :
     * IDCard :
     * PostId : 0
     * LeaveOfficeStr :
     * IsUseCrm : false
     * Name : 李劲松
     * Birth : /Date(1436198400000)/
     * TakeOffice : /Date(1436198400000)/
     * Status : true
     * Email :
     * Post : 总经理
     * TakeOfficeStr : 2015-07-07
     * DepartmentList : ["总经办"]
     * Id : 20031101
     * Department : 总经办
     * LeaveOffice : null
     * BirthStr : 2015-07-07
     * Sex : true
     */
    private String Phone;
    private String RegNum;
    private String Telephone;
    private String IDCard;
    private int PostId;
    private String LeaveOfficeStr;
    private boolean IsUseCrm;
    private String Name;
    private String Birth;
    private String TakeOffice;
    private boolean Status;
    private String Email;
    private String Post;
    private String TakeOfficeStr;
    private List<String> DepartmentList;
    private String Id;
    private String Department;
    private String LeaveOffice;
    private String BirthStr;
    private boolean Sex;

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public void setRegNum(String RegNum) {
        this.RegNum = RegNum;
    }

    public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public void setPostId(int PostId) {
        this.PostId = PostId;
    }

    public void setLeaveOfficeStr(String LeaveOfficeStr) {
        this.LeaveOfficeStr = LeaveOfficeStr;
    }

    public void setIsUseCrm(boolean IsUseCrm) {
        this.IsUseCrm = IsUseCrm;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setBirth(String Birth) {
        this.Birth = Birth;
    }

    public void setTakeOffice(String TakeOffice) {
        this.TakeOffice = TakeOffice;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPost(String Post) {
        this.Post = Post;
    }

    public void setTakeOfficeStr(String TakeOfficeStr) {
        this.TakeOfficeStr = TakeOfficeStr;
    }

    public void setDepartmentList(List<String> DepartmentList) {
        this.DepartmentList = DepartmentList;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public void setLeaveOffice(String LeaveOffice) {
        this.LeaveOffice = LeaveOffice;
    }

    public void setBirthStr(String BirthStr) {
        this.BirthStr = BirthStr;
    }

    public void setSex(boolean Sex) {
        this.Sex = Sex;
    }

    public String getPhone() {
        return Phone;
    }

    public String getRegNum() {
        return RegNum;
    }

    public String getTelephone() {
        return Telephone;
    }

    public String getIDCard() {
        return IDCard;
    }

    public int getPostId() {
        return PostId;
    }

    public String getLeaveOfficeStr() {
        return LeaveOfficeStr;
    }

    public boolean isIsUseCrm() {
        return IsUseCrm;
    }

    public String getName() {
        return Name;
    }

    public String getBirth() {
        return Birth;
    }

    public String getTakeOffice() {
        return TakeOffice;
    }

    public boolean isStatus() {
        return Status;
    }

    public String getEmail() {
        return Email;
    }

    public String getPost() {
        return Post;
    }

    public String getTakeOfficeStr() {
        return TakeOfficeStr;
    }

    public List<String> getDepartmentList() {
        return DepartmentList;
    }

    public String getId() {
        return Id;
    }

    public String getDepartment() {
        return Department;
    }

    public String getLeaveOffice() {
        return LeaveOffice;
    }

    public String getBirthStr() {
        return BirthStr;
    }

    public boolean isSex() {
        return Sex;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Phone);
        dest.writeString(this.RegNum);
        dest.writeString(this.Telephone);
        dest.writeString(this.IDCard);
        dest.writeInt(this.PostId);
        dest.writeString(this.LeaveOfficeStr);
        dest.writeByte(IsUseCrm ? (byte) 1 : (byte) 0);
        dest.writeString(this.Name);
        dest.writeString(this.Birth);
        dest.writeString(this.TakeOffice);
        dest.writeByte(Status ? (byte) 1 : (byte) 0);
        dest.writeString(this.Email);
        dest.writeString(this.Post);
        dest.writeString(this.TakeOfficeStr);
        dest.writeStringList(this.DepartmentList);
        dest.writeString(this.Id);
        dest.writeString(this.Department);
        dest.writeString(this.LeaveOffice);
        dest.writeString(this.BirthStr);
        dest.writeByte(Sex ? (byte) 1 : (byte) 0);
    }

    public ContactBean() {
    }

    protected ContactBean(Parcel in) {
        this.Phone = in.readString();
        this.RegNum = in.readString();
        this.Telephone = in.readString();
        this.IDCard = in.readString();
        this.PostId = in.readInt();
        this.LeaveOfficeStr = in.readString();
        this.IsUseCrm = in.readByte() != 0;
        this.Name = in.readString();
        this.Birth = in.readString();
        this.TakeOffice = in.readString();
        this.Status = in.readByte() != 0;
        this.Email = in.readString();
        this.Post = in.readString();
        this.TakeOfficeStr = in.readString();
        this.DepartmentList = in.createStringArrayList();
        this.Id = in.readString();
        this.Department = in.readString();
        this.LeaveOffice = in.readString();
        this.BirthStr = in.readString();
        this.Sex = in.readByte() != 0;
    }

    public static final Parcelable.Creator<ContactBean> CREATOR = new Parcelable.Creator<ContactBean>() {
        public ContactBean createFromParcel(Parcel source) {
            return new ContactBean(source);
        }

        public ContactBean[] newArray(int size) {
            return new ContactBean[size];
        }
    };
}
