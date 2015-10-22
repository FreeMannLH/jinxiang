package ideatc.jinxiang.bean;

import java.util.List;

/**
 * Created by chencongbo on 2015/10/19 0019.
 */
public class WaitHandleBean {


    /**
     * Flow : 盖章管理
     * Item : [{"Priority":"正常","Count":23}]
     */

    private String Flow;
    private List<ItemEntity> Item;

    public void setFlow(String Flow) {
        this.Flow = Flow;
    }

    public void setItem(List<ItemEntity> Item) {
        this.Item = Item;
    }

    public String getFlow() {
        return Flow;
    }

    public List<ItemEntity> getItem() {
        return Item;
    }

    public static class ItemEntity {
        /**
         * Priority : 正常
         * Count : 23
         */

        private String Priority;
        private int Count;

        public void setPriority(String Priority) {
            this.Priority = Priority;
        }

        public void setCount(int Count) {
            this.Count = Count;
        }

        public String getPriority() {
            return Priority;
        }

        public int getCount() {
            return Count;
        }
    }
}
