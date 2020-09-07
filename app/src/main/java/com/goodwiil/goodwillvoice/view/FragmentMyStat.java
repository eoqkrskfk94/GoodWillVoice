package com.goodwiil.goodwillvoice.view;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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

    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private FragmentPagerAdapter mFragmentCardAdapter;
    private ShadowTransformer mFragmentCardShadowTransformer;

    private Fragment fragment_first;
    private Fragment fragment_second;
    private Fragment fragment_thrid;



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

        fragment_first = new FragmentMyStatFirst();
        fragment_second = new FragmentMyStatSecond();
        fragment_thrid = new FragmentMyStatThird();


        //setCard();
        mFragmentCardAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager(),
                dpToPixels(2, getContext()));
        mFragmentCardShadowTransformer = new ShadowTransformer(mBinding.viewPager, mFragmentCardAdapter);
        mFragmentCardShadowTransformer.enableScaling(true);

        //mBinding.viewPager.setAdapter(mCardAdapter);

        mBinding.viewPager.setAdapter(mFragmentCardAdapter);
        mBinding.viewPager.setPageTransformer(false, mFragmentCardShadowTransformer);
        mBinding.viewPager.setOffscreenPageLimit(3);
        View view = mBinding.getRoot();
        return view;
    }


    private void setCard(){
        mCardAdapter = new CardPagerAdapter();
        mCardAdapter.addCardItem(new CardItem(getActivity().getSupportFragmentManager(), fragment_first));
        mCardAdapter.addCardItem(new CardItem(getActivity().getSupportFragmentManager(), fragment_second));
        mCardAdapter.addCardItem(new CardItem(getActivity().getSupportFragmentManager(), fragment_thrid));
        //mCardAdapter.addCardItem(new CardItem(getChildFragmentManager(), "second"));
        //mCardAdapter.addCardItem(new CardItem(getChildFragmentManager(), "third"));
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