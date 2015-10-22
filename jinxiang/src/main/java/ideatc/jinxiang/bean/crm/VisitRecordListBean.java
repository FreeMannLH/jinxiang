package ideatc.jinxiang.bean.crm;

import java.util.List;

/**
 * Created by Ccb on 2015/8/17.
 */
public class VisitRecordListBean {

    /**
     * message :
     * list : [{"ModifyByNo":"admin","EndTimeStr":"","ModifyByName":"管理员","CreateTimeStr":"2015-08-17 16:05:20","CustManagerNo":"admin","ModifyTime":"/Date(1439798720000)/","VisitTime":"/Date(1439798720000)/","CreateByNo":"admin","CustAddr":"丰泽街益达大厦","CustManagerName":"管理员","CustId":2,"Title":"擦擦擦擦拉锯","CustName":"aaaa","CreateByName":"管理员","ModifyTimeStr":"2015-08-17 16:05:20","CreateTime":"/Date(1439798720000)/","Latitude":24.906573,"EndTime":null,"Id":5,"Longitude":118.622129,"Remark":null,"VisitResult":null,"Addr":"中国福建省泉州市丰泽区丰泽街390号","VisitTimeStr":"2015-08-17 16:20"},{"ModifyByNo":"admin","EndTimeStr":"","ModifyByName":"管理员","CreateTimeStr":"2015-08-17 15:46:48","CustManagerNo":"admin","ModifyTime":"/Date(1439797608000)/","VisitTime":"/Date(1439797608000)/","CreateByNo":"admin","CustAddr":"丰泽街益达大厦","CustManagerName":"管理员","CustId":2,"Title":"拜访主题:","CustName":"aaaa","CreateByName":"管理员","ModifyTimeStr":"2015-08-17 15:46:48","CreateTime":"/Date(1439797608000)/","Latitude":24.906479,"EndTime":null,"Id":4,"Longitude":118.622119,"Remark":null,"VisitResult":null,"Addr":"中国福建省泉州市丰泽区丰泽街388号","VisitTimeStr":"2015-08-17 15:48"},{"ModifyByNo":"admin","EndTimeStr":"","ModifyByName":"管理员","CreateTimeStr":"2015-08-17 15:46:01","CustManagerNo":"admin","ModifyTime":"/Date(1439797561000)/","VisitTime":"/Date(1439797561000)/","CreateByNo":"admin","CustAddr":"丰泽街益达大厦","CustManagerName":"管理员","CustId":2,"Title":"拜访主题:","CustName":"aaaa","CreateByName":"管理员","ModifyTimeStr":"2015-08-17 15:46:01","CreateTime":"/Date(1439797561000)/","Latitude":24.906479,"EndTime":null,"Id":3,"Longitude":118.622118,"Remark":null,"VisitResult":null,"Addr":"中国福建省泉州市丰泽区丰泽街388号","VisitTimeStr":"2015-08-17 15:01"},{"ModifyByNo":"admin","EndTimeStr":"","ModifyByName":"管理员","CreateTimeStr":"2015-08-17 15:42:42","CustManagerNo":"admin","ModifyTime":"/Date(1439797362000)/","VisitTime":"/Date(1439797362000)/","CreateByNo":"admin","CustAddr":"丰泽街益达大厦","CustManagerName":"管理员","CustId":2,"Title":"拜访主题:","CustName":"aaaa","CreateByName":"管理员","ModifyTimeStr":"2015-08-17 15:42:42","CreateTime":"/Date(1439797362000)/","Latitude":24.906552,"EndTime":null,"Id":2,"Longitude":118.622107,"Remark":null,"VisitResult":null,"Addr":"中国福建省泉州市丰泽区丰泽街390号","VisitTimeStr":"2015-08-17 15:42"},{"ModifyByNo":"admin","EndTimeStr":"","ModifyByName":"管理员","CreateTimeStr":"2015-08-17 15:41:19","CustManagerNo":"admin","ModifyTime":"/Date(1439797279000)/","VisitTime":"/Date(1439797279000)/","CreateByNo":"admin","CustAddr":"丰泽街益达大厦","CustManagerName":"管理员","CustId":2,"Title":"拜访主题:","CustName":"aaaa","CreateByName":"管理员","ModifyTimeStr":"2015-08-17 15:41:19","CreateTime":"/Date(1439797279000)/","Latitude":24.906552,"EndTime":null,"Id":1,"Longitude":118.622107,"Remark":null,"VisitResult":null,"Addr":"中国福建省泉州市丰泽区丰泽街390号","VisitTimeStr":"2015-08-17 15:19"}]
     * success : true
     */
    private String message;
    private List<VisitRecordBean> list;
    private boolean success;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setList(List<VisitRecordBean> list) {
        this.list = list;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public List<VisitRecordBean> getList() {
        return list;
    }

    public boolean isSuccess() {
        return success;
    }

    public static class VisitRecordBean {
        /**
         * ModifyByNo : admin
         * EndTimeStr :
         * ModifyByName : 管理员
         * CreateTimeStr : 2015-08-17 16:05:20
         * CustManagerNo : admin
         * ModifyTime : /Date(1439798720000)/
         * VisitTime : /Date(1439798720000)/
         * CreateByNo : admin
         * CustAddr : 丰泽街益达大厦
         * CustManagerName : 管理员
         * CustId : 2
         * Title : 擦擦擦擦拉锯
         * CustName : aaaa
         * CreateByName : 管理员
         * ModifyTimeStr : 2015-08-17 16:05:20
         * CreateTime : /Date(1439798720000)/
         * Latitude : 24.906573
         * EndTime : null
         * Id : 5
         * Longitude : 118.622129
         * Remark : null
         * VisitResult : null
         * Addr : 中国福建省泉州市丰泽区丰泽街390号
         * VisitTimeStr : 2015-08-17 16:20
         */
        private String ModifyByNo;
        private String EndTimeStr;
        private String ModifyByName;
        private String CreateTimeStr;
        private String CustManagerNo;
        private String ModifyTime;
        private String VisitTime;
        private String CreateByNo;
        private String CustAddr;
        private String CustManagerName;
        private int CustId;
        private String Title;
        private String CustName;
        private String CreateByName;
        private String ModifyTimeStr;
        private String CreateTime;
        private double Latitude;
        private String EndTime;
        private int Id;
        private double Longitude;
        private String Remark;
        private String VisitResult;
        private String Addr;
        private String VisitTimeStr;

        public void setModifyByNo(String ModifyByNo) {
            this.ModifyByNo = ModifyByNo;
        }

        public void setEndTimeStr(String EndTimeStr) {
            this.EndTimeStr = EndTimeStr;
        }

        public void setModifyByName(String ModifyByName) {
            this.ModifyByName = ModifyByName;
        }

        public void setCreateTimeStr(String CreateTimeStr) {
            this.CreateTimeStr = CreateTimeStr;
        }

        public void setCustManagerNo(String CustManagerNo) {
            this.CustManagerNo = CustManagerNo;
        }

        public void setModifyTime(String ModifyTime) {
            this.ModifyTime = ModifyTime;
        }

        public void setVisitTime(String VisitTime) {
            this.VisitTime = VisitTime;
        }

        public void setCreateByNo(String CreateByNo) {
            this.CreateByNo = CreateByNo;
        }

        public void setCustAddr(String CustAddr) {
            this.CustAddr = CustAddr;
        }

        public void setCustManagerName(String CustManagerName) {
            this.CustManagerName = CustManagerName;
        }

        public void setCustId(int CustId) {
            this.CustId = CustId;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public void setCustName(String CustName) {
            this.CustName = CustName;
        }

        public void setCreateByName(String CreateByName) {
            this.CreateByName = CreateByName;
        }

        public void setModifyTimeStr(String ModifyTimeStr) {
            this.ModifyTimeStr = ModifyTimeStr;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public void setLatitude(double Latitude) {
            this.Latitude = Latitude;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public void setLongitude(double Longitude) {
            this.Longitude = Longitude;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public void setVisitResult(String VisitResult) {
            this.VisitResult = VisitResult;
        }

        public void setAddr(String Addr) {
            this.Addr = Addr;
        }

        public void setVisitTimeStr(String VisitTimeStr) {
            this.VisitTimeStr = VisitTimeStr;
        }

        public String getModifyByNo() {
            return ModifyByNo;
        }

        public String getEndTimeStr() {
            return EndTimeStr;
        }

        public String getModifyByName() {
            return ModifyByName;
        }

        public String getCreateTimeStr() {
            return CreateTimeStr;
        }

        public String getCustManagerNo() {
            return CustManagerNo;
        }

        public String getModifyTime() {
            return ModifyTime;
        }

        public String getVisitTime() {
            return VisitTime;
        }

        public String getCreateByNo() {
            return CreateByNo;
        }

        public String getCustAddr() {
            return CustAddr;
        }

        public String getCustManagerName() {
            return CustManagerName;
        }

        public int getCustId() {
            return CustId;
        }

        public String getTitle() {
            return Title;
        }

        public String getCustName() {
            return CustName;
        }

        public String getCreateByName() {
            return CreateByName;
        }

        public String getModifyTimeStr() {
            return ModifyTimeStr;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public double getLatitude() {
            return Latitude;
        }

        public String getEndTime() {
            return EndTime;
        }

        public int getId() {
            return Id;
        }

        public double getLongitude() {
            return Longitude;
        }

        public String getRemark() {
            return Remark;
        }

        public String getVisitResult() {
            return VisitResult;
        }

        public String getAddr() {
            return Addr;
        }

        public String getVisitTimeStr() {
            return VisitTimeStr;
        }
    }
}
