package ideatc.jinxiang.fragment.home_activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sevenheaven.segmentcontrol.SegmentControl;

import ideatc.jinxiang.R;
import ideatc.jinxiang.base.fragment.BaseFragment;
import ideatc.jinxiang.fragment.matters_fragment.DaiBanFragment;
import ideatc.jinxiang.fragment.matters_fragment.YiBanFragment;

public class MattersFragment extends BaseFragment {

    private FragmentManager mFragmentManager;
    private FragmentTransaction ft;
    private SegmentControl mSegmentControl;

    public static MattersFragment instance() {
        Bundle bundle = new Bundle();
        MattersFragment fragment = new MattersFragment();
        fragment.setUserVisibleHint(true);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_matters, null);
        mFragmentManager = getChildFragmentManager();
        mSegmentControl = (SegmentControl) view.findViewById(R.id.id_seg);
        mSegmentControl.setSelected(true);
        initRadioGroup();
        return view;
    }

    private void initRadioGroup() {

        mSegmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                ft = mFragmentManager.beginTransaction();
                switch (index) {
                    case 0:
                        ft.replace(R.id.frameLayout, DaiBanFragment.newInstance());
                        ft.commit();
                        break;
                    case 1:
                        ft.replace(R.id.frameLayout, YiBanFragment.newInstance());
                        ft.commit();
                        break;
                }
            }
        });
        mSegmentControl.setSelectedIndex(0);
    }

}
