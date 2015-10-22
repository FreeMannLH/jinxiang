package ideatc.jinxiang.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ccb on 2015/8/20.
 */
public class SysInfoLogBean implements Parcelable {
    /**
     * User : 管理员
     * Status : 1
     * OperateDateStr : 2015-08-10
     * Node : 直接上级审批
     * PrevNode : 员工请假申请
     * StatusStr : 提交
     * Id : 16
     * Content : 323
     */
    private String User;
    private int Status;
    private String OperateDateStr;
    private String Node;
    private String PrevNode;
    private String StatusStr;
    private int Id;
    private String Content;

    public void setUser(String User) {
        this.User = User;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public void setOperateDateStr(String OperateDateStr) {
        this.OperateDateStr = OperateDateStr;
    }

    public void setNode(String Node) {
        this.Node = Node;
    }

    public void setPrevNode(String PrevNode) {
        this.PrevNode = PrevNode;
    }

    public void setStatusStr(String StatusStr) {
        this.StatusStr = StatusStr;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getUser() {
        return User;
    }

    public int getStatus() {
        return Status;
    }

    public String getOperateDateStr() {
        return OperateDateStr;
    }

    public String getNode() {
        return Node;
    }

    public String getPrevNode() {
        return PrevNode;
    }

    public String getStatusStr() {
        return StatusStr;
    }

    public int getId() {
        return Id;
    }

    public String getContent() {
        return Content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.User);
        dest.writeInt(this.Status);
        dest.writeString(this.OperateDateStr);
        dest.writeString(this.Node);
        dest.writeString(this.PrevNode);
        dest.writeString(this.StatusStr);
        dest.writeInt(this.Id);
        dest.writeString(this.Content);
    }

    public SysInfoLogBean() {
    }

    protected SysInfoLogBean(Parcel in) {
        this.User = in.readString();
        this.Status = in.readInt();
        this.OperateDateStr = in.readString();
        this.Node = in.readString();
        this.PrevNode = in.readString();
        this.StatusStr = in.readString();
        this.Id = in.readInt();
        this.Content = in.readString();
    }

    public static final Parcelable.Creator<SysInfoLogBean> CREATOR = new Parcelable.Creator<SysInfoLogBean>() {
        public SysInfoLogBean createFromParcel(Parcel source) {
            return new SysInfoLogBean(source);
        }

        public SysInfoLogBean[] newArray(int size) {
            return new SysInfoLogBean[size];
        }
    };
}
