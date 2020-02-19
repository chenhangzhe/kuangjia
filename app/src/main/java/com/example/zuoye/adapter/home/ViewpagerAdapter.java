package com.example.zuoye.adapter.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ViewpagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> lists;

    public ViewpagerAdapter(@NonNull FragmentManager fm,List<Fragment> lists) {
        super(fm);

        this.lists = lists;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return lists.get(position);
    }

    @Override
    public int getCount() {
        return lists.size();
    }
}
