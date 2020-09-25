package com.goodwiil.goodwillvoice.view;

import android.content.Context;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.adapter.CardPagerAdapter;
import com.goodwiil.goodwillvoice.adapter.FragmentPagerAdapter;
import com.goodwiil.goodwillvoice.databinding.FragmentMyStatBinding;
import com.goodwiil.goodwillvoice.model.CardItem;
import com.goodwiil.goodwillvoice.util.ShadowTransformer;
import com.goodwiil.goodwillvoice.viewModel.SplashViewModel;


public class FragmentMyStat extends Fragment {

    private FragmentMyStatBinding mBinding;

    private FragmentPagerAdapter mFragmentCardAdapter;
    private ShadowTransformer mFragmentCardShadowTransformer;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBinding = FragmentMyStatBinding.inflate(inflater, container, false);
        //mBinding.setViewModel(new SplashViewModel());
        mBinding.setLifecycleOwner(getActivity());


        mFragmentCardAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager(), dpToPixels(2, getContext()));
        mFragmentCardShadowTransformer = new ShadowTransformer(mBinding.viewPager, mFragmentCardAdapter);
        mFragmentCardShadowTransformer.enableScaling(true);

        mBinding.viewPager.setAdapter(mFragmentCardAdapter);
        mBinding.viewPager.setPageTransformer(false, mFragmentCardShadowTransformer);
        mBinding.viewPager.setOffscreenPageLimit(3);
        mBinding.idIndicator.setSelectedDotColor(ContextCompat.getColor(getContext(),R.color.colorBlue));
        mBinding.idIndicator.setSelectedDotDiameterDp(6);
        mBinding.idIndicator.setUnselectedDotDiameterDp(5);
        mBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBinding.idIndicator.setSelectedItem(position, true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        View view = mBinding.getRoot();
        return view;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }



}