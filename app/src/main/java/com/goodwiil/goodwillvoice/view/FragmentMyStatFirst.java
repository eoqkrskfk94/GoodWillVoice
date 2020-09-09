package com.goodwiil.goodwillvoice.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.adapter.CardAdapter;
import com.goodwiil.goodwillvoice.adapter.CardPagerAdapter;
import com.goodwiil.goodwillvoice.databinding.FragmentMyStatBinding;
import com.goodwiil.goodwillvoice.databinding.FragmentMyStatFirstBinding;
import com.goodwiil.goodwillvoice.model.CardItem;
import com.goodwiil.goodwillvoice.util.ShadowTransformer;

import java.util.ArrayList;


public class FragmentMyStatFirst extends Fragment {

    private FragmentMyStatFirstBinding mBinding;


    private CardView mCardView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_stat_first, container, false);
        mCardView = (CardView) view.findViewById(R.id.cardView);
        mCardView.setMaxCardElevation(mCardView.getCardElevation()
                * CardAdapter.MAX_ELEVATION_FACTOR);

        mBinding = FragmentMyStatFirstBinding.inflate(inflater, container, false);
        //mBinding.setViewModel(new SplashViewModel());
        mBinding.setLifecycleOwner(getActivity());


        mBinding.pcCallLog.getDescription().setEnabled(false);
        mBinding.pcCallLog.setExtraOffsets(5,0,5,0);
        mBinding.pcCallLog.setTouchEnabled(false);
        mBinding.pcCallLog.animateY(1000, Easing.EaseInOutCubic);
        //mBinding.pcCallLog.setDragDecelerationFrictionCoef(0.95f);

        mBinding.pcCallLog.setDrawHoleEnabled(true);
        mBinding.pcCallLog.setHoleColor(Color.WHITE);
        mBinding.pcCallLog.setTransparentCircleRadius(61f);
        mBinding.pcCallLog.getLegend().setEnabled(false);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(4.0f, ""));
        yValues.add(new PieEntry(36.0f, ""));
        yValues.add(new PieEntry(60.0f, ""));

        PieDataSet dataSet = new PieDataSet(yValues, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);


        PieData data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.WHITE);
        data.setValueFormatter(new MyValueFormatter());

        mBinding.pcCallLog.setData(data);


        return mBinding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    public CardView getCardView() { return mCardView; }


    private class MyValueFormatter extends ValueFormatter {


        @Override
        public String getFormattedValue(float value) {
            return super.getFormattedValue(value) + " %";
        }
    }

}