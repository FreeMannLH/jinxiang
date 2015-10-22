package ideatc.jinxiang.bean;

import java.util.List;

/**
 * Created by Ccb on 2015/8/6.
 */
public class CustomerBean  {

    /**
     * total : 2
     * rows : [{"MakeDate":"/Date(1439172204000)/","EndDate":"/Date(-62135596800000)/","ShowDate":"/Date(1439049600000)/","EndDateStr":"0001-01-01 00:00","MakerId":0,"Id":2,"ShowDateStr":"2015-08-09 00:00","Content":null,"MakeDateStr":"2015-08-10 10:03","Title":"演示","Maker":"管理员"},{"MakeDate":"/Date(1438739931000)/","EndDate":"/Date(-62135596800000)/","ShowDate":"/Date(1438704000000)/","EndDateStr":"0001-01-01 00:00","MakerId":0,"Id":1,"ShowDateStr":"2015-08-05 00:00","Content":null,"MakeDateStr":"2015-08-05 09:58","Title":"劲翔OA上线啦","Maker":"管理员"}]
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

    public static class RowsEntity {
        /**
         * MakeDate : /Date(1439172204000)/
         * EndDate : /Date(-62135596800000)/
         * ShowDate : /Date(1439049600000)/
         * EndDateStr : 0001-01-01 00:00
         * MakerId : 0
         * Id : 2
         * ShowDateStr : 2015-08-09 00:00
         * Content : null
         * MakeDateStr : 2015-08-10 10:03
         * Title : 演示
         * Maker : 管理员
         */
        private String MakeDate;
        private String EndDate;
        private String ShowDate;
        private String EndDateStr;
        private int MakerId;
        private int Id;
        private String ShowDateStr;
        private String Content;
        private String MakeDateStr;
        private String Title;
        private String Maker;

        public void setMakeDate(String MakeDate) {
            this.MakeDate = MakeDate;
        }

        public void setEndDate(String EndDate) {
            this.EndDate = EndDate;
        }

        public void setShowDate(String ShowDate) {
            this.ShowDate = ShowDate;
        }

        public void setEndDateStr(String EndDateStr) {
            this.EndDateStr = EndDateStr;
        }

        public void setMakerId(int MakerId) {
            this.MakerId = MakerId;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public void setShowDateStr(String ShowDateStr) {
            this.ShowDateStr = ShowDateStr;
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

        public String getMakeDate() {
            return MakeDate;
        }

        public String getEndDate() {
            return EndDate;
        }

        public String getShowDate() {
            return ShowDate;
        }

        public String getEndDateStr() {
            return EndDateStr;
        }

        public int getMakerId() {
            return MakerId;
        }

        public int getId() {
            return Id;
        }

        public String getShowDateStr() {
            return ShowDateStr;
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
    }
}
