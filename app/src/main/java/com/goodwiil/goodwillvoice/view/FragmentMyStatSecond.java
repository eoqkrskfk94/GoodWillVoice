package com.goodwiil.goodwillvoice.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.adapter.CardAdapter;
import com.goodwiil.goodwillvoice.adapter.CardPagerAdapter;
import com.goodwiil.goodwillvoice.databinding.FragmentMyStatFirstBinding;
import com.goodwiil.goodwillvoice.databinding.FragmentMyStatSecondBinding;
import com.goodwiil.goodwillvoice.util.ShadowTransformer;


public class FragmentMyStatSecond extends Fragment {


    private CardView mCardView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_stat_second, container, false);
        mCardView = (CardView) view.findViewById(R.id.cardView);
        mCardView.setMaxCardElevation(mCardView.getCardElevation()
                * CardAdapter.MAX_ELEVATION_FACTOR);
        return view;
    }


    public CardView getCardView() { return mCardView; }



}