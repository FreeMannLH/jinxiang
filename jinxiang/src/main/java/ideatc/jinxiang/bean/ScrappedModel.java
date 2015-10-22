package ideatc.jinxiang.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ccb on 2015/8/14.
 * 报废模型
 */
public class ScrappedModel implements Parcelable {
   private int Id;
    private String OrderNo;
    private String Content;
    private String EmployeeId;
    private String Employee;
    private int MakerId;
    private String Maker;
    private List<ScrappedItemModel> ScrappedItem;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }

    public String getEmployee() {
        return Employee;
    }

    public void setEmployee(String employee) {
        Employee = employee;
    }

    public int getMakerId() {
        return MakerId;
    }

    public void setMakerId(int makerId) {
        MakerId = makerId;
    }

    public List<ScrappedItemModel> getList() {
        return ScrappedItem;
    }

    public void setList(List<ScrappedItemModel> list) {
        this.ScrappedItem = list;
    }

    public String getMaker() {
        return Maker;
    }

    public void setMaker(String maker) {
        Maker = maker;
    }

    /**
     * 物品报废列表模型
     */
    public static class ScrappedItemModel implements Parcelable {
        private  int Id;
        private  int GoodsId;
        private  String Goods;
        private  String Spec;
        private int Quantity;
        private String Remark;
        private String ScrappedType;
        private  String Unit;

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public int getGoodsId() {
            return GoodsId;
        }

        public void setGoodsId(int goodsId) {
            GoodsId = goodsId;
        }

        public String getGoods() {
            return Goods;
        }

        public void setGoods(String goods) {
            Goods = goods;
        }

        public String getSpec() {
            return Spec;
        }

        public void setSpec(String spec) {
            Spec = spec;
        }

        public int getQuantity() {
            return Quantity;
        }

        public void setQuantity(int quantity) {
            Quantity = quantity;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String remark) {
            Remark = remark;
        }

        public String getScrappedType() {
            return ScrappedType;
        }

        public void setScrappedType(String scrappedType) {
            ScrappedType = scrappedType;
        }

        public String getUnit() {
            return Unit;
        }

        public void setUnit(String unit) {
            Unit = unit;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.Id);
            dest.writeInt(this.GoodsId);
            dest.writeString(this.Goods);
            dest.writeString(this.Spec);
            dest.writeInt(this.Quantity);
            dest.writeString(this.Remark);
            dest.writeString(this.ScrappedType);
            dest.writeString(this.Unit);
        }

        public ScrappedItemModel() {
        }

        protected ScrappedItemModel(Parcel in) {
            this.Id = in.readInt();
            this.GoodsId = in.readInt();
            this.Goods = in.readString();
            this.Spec = in.readString();
            this.Quantity = in.readInt();
            this.Remark = in.readString();
            this.ScrappedType = in.readString();
            this.Unit = in.readString();
        }

        public static final Creator<ScrappedItemModel> CREATOR = new Creator<ScrappedItemModel>() {
            public ScrappedItemModel createFromParcel(Parcel source) {
                return new ScrappedItemModel(source);
            }

            public ScrappedItemModel[] newArray(int size) {
                return new ScrappedItemModel[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeString(this.OrderNo);
        dest.writeString(this.Content);
        dest.writeString(this.EmployeeId);
        dest.writeString(this.Employee);
        dest.writeInt(this.MakerId);
        dest.writeString(this.Maker);
        dest.writeList(this.ScrappedItem);
    }

    public ScrappedModel() {
    }

    protected ScrappedModel(Parcel in) {
        this.Id = in.readInt();
        this.OrderNo = in.readString();
        this.Content = in.readString();
        this.EmployeeId = in.readString();
        this.Employee = in.readString();
        this.MakerId = in.readInt();
        this.Maker = in.readString();
        this.ScrappedItem = new ArrayList<>();
        in.readList(this.ScrappedItem, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<ScrappedModel> CREATOR = new Parcelable.Creator<ScrappedModel>() {
        public ScrappedModel createFromParcel(Parcel source) {
            return new ScrappedModel(source);
        }

        public ScrappedModel[] newArray(int size) {
            return new ScrappedModel[size];
        }
    };
}
