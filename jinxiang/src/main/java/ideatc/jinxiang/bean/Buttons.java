package ideatc.jinxiang.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ccb on 2015/8/14.
 *
 */
public class Buttons implements Parcelable {

    /**
     * total : 2
     * rows : [{"id":"发起申请","text":"发起申请","iconCls":"easyui-icon-edit","handler":"onSend()"},{"id":"撤回","text":"撤回","iconCls":"easyui-icon-edit","handler":"onBack()"}]
     */
    private int total;
    private List<RowsEntity> rows;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setRows(List<RowsEntity> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public List<RowsEntity> getRows() {
        return rows;
    }

    public static class RowsEntity implements Parcelable {
        /**
         * id : 发起申请
         * text : 发起申请
         * iconCls : easyui-icon-edit
         * handler : onSend()
         */
        private String id;
        private String text;
        private String iconCls;
        private String handler;

        public void setId(String id) {
            this.id = id;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setIconCls(String iconCls) {
            this.iconCls = iconCls;
        }

        public void setHandler(String handler) {
            this.handler = handler;
        }

        public String getId() {
            return id;
        }

        public String getText() {
            return text;
        }

        public String getIconCls() {
            return iconCls;
        }

        public String getHandler() {
            return handler;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.text);
            dest.writeString(this.iconCls);
            dest.writeString(this.handler);
        }

        public RowsEntity() {
        }

        protected RowsEntity(Parcel in) {
            this.id = in.readString();
            this.text = in.readString();
            this.iconCls = in.readString();
            this.handler = in.readString();
        }

        public static final Creator<RowsEntity> CREATOR = new Creator<RowsEntity>() {
            public RowsEntity createFromParcel(Parcel source) {
                return new RowsEntity(source);
            }

            public RowsEntity[] newArray(int size) {
                return new RowsEntity[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.total);
        dest.writeList(this.rows);
    }

    public Buttons() {
    }

    protected Buttons(Parcel in) {
        this.total = in.readInt();
        this.rows = new ArrayList<RowsEntity>();
        in.readList(this.rows, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<Buttons> CREATOR = new Parcelable.Creator<Buttons>() {
        public Buttons createFromParcel(Parcel source) {
            return new Buttons(source);
        }

        public Buttons[] newArray(int size) {
            return new Buttons[size];
        }
    };
}
