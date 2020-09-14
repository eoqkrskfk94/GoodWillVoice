package com.goodwiil.goodwillvoice.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.adapter.CallRankAdapter;
import com.goodwiil.goodwillvoice.adapter.CardAdapter;
import com.goodwiil.goodwillvoice.adapter.CardPagerAdapter;
import com.goodwiil.goodwillvoice.databinding.FragmentMyStatFirstBinding;
import com.goodwiil.goodwillvoice.databinding.FragmentMyStatThirdBinding;
import com.goodwiil.goodwillvoice.model.TopMaxCallItem;
import com.goodwiil.goodwillvoice.util.ShadowTransformer;

import java.util.ArrayList;


public class FragmentMyStatThird extends Fragment {

    private CardView mCardView;
    private CallRankAdapter callRankAdapter;
    private RecyclerView topCallLogList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_stat_third, container, false);
        mCardView = (CardView) view.findViewById(R.id.cardView);
        mCardView.setMaxCardElevation(mCardView.getCardElevation()
                * CardAdapter.MAX_ELEVATION_FACTOR);
        topCallLogList = (RecyclerView) view.findViewById(R.id.rv_top_call);

        setRecyclerView();

        return view;
    }


    public CardView getCardView() { return mCardView; }

    public void setRecyclerView(){

        topCallLogList.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<TopMaxCallItem> topMaxCallItems = new ArrayList<>();
        topMaxCallItems.add(new TopMaxCallItem(1,"054-260-2222","학교", "00:30:12"));
        topMaxCallItems.add(new TopMaxCallItem(2,"054-260-2222","보험", "00:30:12"));
        topMaxCallItems.add(new TopMaxCallItem(3,"054-260-2222","경찰", "00:30:12"));
        topMaxCallItems.add(new TopMaxCallItem(4,"054-260-2222","통신사", "00:30:12"));
        topMaxCallItems.add(new TopMaxCallItem(5,"054-260-2222","학교", "00:30:12"));
        topMaxCallItems.add(new TopMaxCallItem(6,"054-260-2222","학교", "00:30:12"));
        topMaxCallItems.add(new TopMaxCallItem(7,"054-260-2222","학교", "00:30:12"));
        topMaxCallItems.add(new TopMaxCallItem(8,"054-260-2222","학교", "00:30:12"));
        topMaxCallItems.add(new TopMaxCallItem(9,"054-260-2222","알수없음", "00:30:12"));
        topMaxCallItems.add(new TopMaxCallItem(10,"054-260-2222","학교", "00:30:12"));

        callRankAdapter = new CallRankAdapter(topMaxCallItems);
        topCallLogList.setAdapter(callRankAdapter);


    }

}