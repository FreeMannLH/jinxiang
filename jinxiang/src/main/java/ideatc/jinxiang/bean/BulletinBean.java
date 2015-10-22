package ideatc.jinxiang.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ccb on 2015/8/12.
 * 公告详情
 */
public class BulletinBean implements Parcelable {

    /**
     * Id : 1
     * Content : 请多测试，使用情况
     * MakeDateStr : 2015-08-05 09:58
     * Title : 劲翔OA上线啦
     * Maker : 管理员
     */
    private int Id;
    private String Content;
    private String MakeDateStr;
    private String Title;
    private String Maker;

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public void setMakeDateStr(String MakeDateStr) {
        this.MakeDateStr = MakeDateStr;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setMaker(String Maker) {
        this.Maker = Maker;
    }

    public int getId() {
        return Id;
    }

    public String getContent() {
        return Content;
    }

    public String getMakeDateStr() {
        return MakeDateStr;
    }

    public String getTitle() {
        return Title;
    }

    public String getMaker() {
        return Maker;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeString(this.Content);
        dest.writeString(this.MakeDateStr);
        dest.writeString(this.Title);
        dest.writeString(this.Maker);
    }

    public BulletinBean() {
    }

    protected BulletinBean(Parcel in) {
        this.Id = in.readInt();
        this.Content = in.readString();
        this.MakeDateStr = in.readString();
        this.Title = in.readString();
        this.Maker = in.readString();
    }

    public static final Parcelable.Creator<BulletinBean> CREATOR = new Parcelable.Creator<BulletinBean>() {
        public BulletinBean createFromParcel(Parcel source) {
            return new BulletinBean(source);
        }

        public BulletinBean[] newArray(int size) {
            return new BulletinBean[size];
        }
    };
}
