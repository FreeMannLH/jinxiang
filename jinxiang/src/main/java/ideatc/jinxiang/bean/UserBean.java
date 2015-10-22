package ideatc.jinxiang.bean;

import com.lidroid.xutils.db.annotation.Id;

import java.util.List;

/**
 * Created by Ccb on 2015/8/12.
 */
public class UserBean  {

    /**
     * Id : 20120701
     * Name : 黄小珍
     * Sex : false
     * IDCard :
     * Birth : /Date(1436198400000)/
     * BirthStr : 2015-07-07
     * Phone :
     * Telephone :
     * Email :
     * TakeOffice : /Date(1436198400000)/
     * TakeOfficeStr : 2015-07-07
     * Status : true
     * LeaveOffice : null
     * LeaveOfficeStr :
     * RegNum : 00000013
     * DepartmentId : [3]
     * Department : ["专利部"]
     * PostId : [4]
     * Post : ["专利部主管"]
     * IsUseCrm : false
     * MakerId : 0
     */


    @Id
    private String Id;

    private String Name;
    private boolean Sex;
    private String IDCard;
    private String Birth;
    private String BirthStr;
    private String Phone;
    private String Telephone;
    private String Email;
    private String TakeOffice;
    private String TakeOfficeStr;
    private boolean Status;
    private Object LeaveOffice;
    private String LeaveOfficeStr;
    private String RegNum;
    private boolean IsUseCrm;
    private int MakerId;
    private List<Integer> DepartmentId;
    private List<String> Department;
    private List<Integer> PostId;
    private List<String> Post;

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setSex(boolean Sex) {
        this.Sex = Sex;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public void setBirth(String Birth) {
        this.Birth = Birth;
    }

    public void setBirthStr(String BirthStr) {
        this.BirthStr = BirthStr;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setTakeOffice(String TakeOffice) {
        this.TakeOffice = TakeOffice;
    }

    public void setTakeOfficeStr(String TakeOfficeStr) {
        this.TakeOfficeStr = TakeOfficeStr;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public void setLeaveOffice(Object LeaveOffice) {
        this.LeaveOffice = LeaveOffice;
    }

    public void setLeaveOfficeStr(String LeaveOfficeStr) {
        this.LeaveOfficeStr = LeaveOfficeStr;
    }

    public void setRegNum(String RegNum) {
        this.RegNum = RegNum;
    }

    public void setIsUseCrm(boolean IsUseCrm) {
        this.IsUseCrm = IsUseCrm;
    }

    public void setMakerId(int MakerId) {
        this.MakerId = MakerId;
    }

    public void setDepartmentId(List<Integer> DepartmentId) {
        this.DepartmentId = DepartmentId;
    }

    public void setDepartment(List<String> Department) {
        this.Department = Department;
    }

    public void setPostId(List<Integer> PostId) {
        this.PostId = PostId;
    }

    public void setPost(List<String> Post) {
        this.Post = Post;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public boolean getSex() {
        return Sex;
    }

    public String getIDCard() {
        return IDCard;
    }

    public String getBirth() {
        return Birth;
    }

    public String getBirthStr() {
        return BirthStr;
    }

    public String getPhone() {
        return Phone;
    }

    public String getTelephone() {
        return Telephone;
    }

    public String getEmail() {
        return Email;
    }

    public String getTakeOffice() {
        return TakeOffice;
    }

    public String getTakeOfficeStr() {
        return TakeOfficeStr;
    }

    public boolean getStatus() {
        return Status;
    }

    public Object getLeaveOffice() {
        return LeaveOffice;
    }

    public String getLeaveOfficeStr() {
        return LeaveOfficeStr;
    }

    public String getRegNum() {
        return RegNum;
    }

    public boolean getIsUseCrm() {
        return IsUseCrm;
    }

    public int getMakerId() {
        return MakerId;
    }

    public List<Integer> getDepartmentId() {
        return DepartmentId;
    }

    public List<String> getDepartment() {
        return Department;
    }

    public List<Integer> getPostId() {
        return PostId;
    }

    public List<String> getPost() {
        return Post;
    }
}
