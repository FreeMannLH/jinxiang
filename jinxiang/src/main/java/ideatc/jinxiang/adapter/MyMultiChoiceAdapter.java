package ideatc.jinxiang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ideatc.jinxiang.R;
import ideatc.jinxiang.bean.StampManagerModel;

/**
 * Created by Ccb on 2015/8/26.
 */
public class MyMultiChoiceAdapter extends BaseAdapter {
    private List<StampManagerModel> mDatas;
    private Context context;

    //    public  List<Integer> list = new ArrayList<>();
    public Map<Integer, Integer> idMap = new HashMap<>();

    public MyMultiChoiceAdapter(Context context, List<StampManagerModel> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            int layout = R.layout.list_check_item;
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layout, parent, false);
        }
        final StampManagerModel model = getItem(position);
        TextView name = (TextView) convertView.findViewById(R.id.id_use_stamp_name);
        TextView oder_no = (TextView) convertView.findViewById(R.id.id_use_stamp_order_no);
        TextView type = (TextView) convertView.findViewById(R.id.id_use_stamp_type);
        TextView no = (TextView) convertView.findViewById(R.id.id_use_stamp_no);
        name.setText("用章人:" + model.getEmployee());
        oder_no.setText("单号:" + model.getOrderNo());
        type.setText("盖章类型:" + model.getStampType());
        no.setText("编号:" + model.getNumber());
        CheckBox cb = (CheckBox) convertView.findViewById(R.id.id_cb);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) idMap.put(position, model.getId());
                else idMap.remove(model.getId());

            }
        });

        return convertView;
    }

    public Map<Integer, Integer> getIdMap() {
        return idMap;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public StampManagerModel getItem(int position) {
        return mDatas.get(position);
    }

}
