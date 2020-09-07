package com.goodwiil.goodwillvoice.adapter;

import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.goodwiil.goodwillvoice.view.FragmentMyStatFirst;
import com.goodwiil.goodwillvoice.view.FragmentMyStatSecond;
import com.goodwiil.goodwillvoice.view.FragmentMyStatThird;

import java.util.ArrayList;
import java.util.List;

public class FragmentPagerAdapter extends FragmentStatePagerAdapter implements CardAdapter {

    private List<Fragment> mFragments;
    private FragmentMyStatFirst fragmentMyStatFirst;
    private FragmentMyStatSecond fragmentMyStatSecond;
    private FragmentMyStatThird fragmentMyStatThird;
    private float mBaseElevation;

    public FragmentPagerAdapter(FragmentManager fm, float baseElevation) {
        super(fm);
        mFragments = new ArrayList<>();
        mBaseElevation = baseElevation;

        fragmentMyStatFirst = new FragmentMyStatFirst();
        fragmentMyStatSecond = new FragmentMyStatSecond();
        fragmentMyStatThird = new FragmentMyStatThird();

        addFragment(new FragmentMyStatFirst());
        addFragment(new FragmentMyStatSecond());
        addFragment(new FragmentMyStatThird());
    }

    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        switch (position){
            case 0:
                return fragmentMyStatFirst.getCardView();
            case 1:
                return fragmentMyStatSecond.getCardView();
            case 2:
                return fragmentMyStatThird.getCardView();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        mFragments.set(position, (Fragment) fragment);
        return fragment;
    }

    public void addFragment(Fragment fragment) {
        mFragments.add(fragment);
    }

}
