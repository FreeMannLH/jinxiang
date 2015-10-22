package ideatc.jinxiang.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/8/27.
 */
public class StockGoodsBean implements Parcelable {
    private int Key;
    private int Value;

    public int getKey() {
        return Key;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }

    public void setKey(int key) {
        Key = key;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Key);
        dest.writeInt(this.Value);
    }

    public StockGoodsBean() {
    }

    protected StockGoodsBean(Parcel in) {
        this.Key = in.readInt();
        this.Value = in.readInt();
    }

    public static final Parcelable.Creator<StockGoodsBean> CREATOR = new Parcelable.Creator<StockGoodsBean>() {
        public StockGoodsBean createFromParcel(Parcel source) {
            return new StockGoodsBean(source);
        }

        public StockGoodsBean[] newArray(int size) {
            return new StockGoodsBean[size];
        }
    };
}
