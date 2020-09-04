package com.goodwiil.goodwillvoice.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.adapter.CardPagerAdapter;
import com.goodwiil.goodwillvoice.databinding.FragmentMyStatBinding;
import com.goodwiil.goodwillvoice.model.CardItem;
import com.goodwiil.goodwillvoice.util.ShadowTransformer;
import com.goodwiil.goodwillvoice.viewModel.SplashViewModel;


public class FragmentMyStat extends Fragment {

    private FragmentMyStatBinding mBinding;
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;


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


        setCard();
        mCardShadowTransformer = new ShadowTransformer(mBinding.viewPager, mCardAdapter);
        mCardShadowTransformer.enableScaling(true);

        mBinding.viewPager.setAdapter(mCardAdapter);
        mBinding.viewPager.setPageTransformer(false, mCardShadowTransformer);
        mBinding.viewPager.setOffscreenPageLimit(3);
        View view = mBinding.getRoot();
        return view;
    }


    private void setCard(){
        mCardAdapter = new CardPagerAdapter();
        mCardAdapter.addCardItem(new CardItem("first", "first"));
        mCardAdapter.addCardItem(new CardItem("second", "second"));
        mCardAdapter.addCardItem(new CardItem("third", "third"));
        mCardAdapter.addCardItem(new CardItem("fourth", "fourth"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }


}