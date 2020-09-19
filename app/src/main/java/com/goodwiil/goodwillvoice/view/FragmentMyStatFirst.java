package com.goodwiil.goodwillvoice.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
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
import com.goodwiil.goodwillvoice.util.ScreenManager;
import com.goodwiil.goodwillvoice.util.ShadowTransformer;

import java.util.ArrayList;


public class FragmentMyStatFirst extends Fragment {

    private FragmentMyStatFirstBinding mBinding;


    private CardView mCardView;
    private PieChart pieChart;

    private TextView tvUnknownCount;
    private TextView tvFirstCount;
    private TextView tvSecondCount;
    private TextView tvThirdCount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_stat_first, container, false);
        mCardView = (CardView) view.findViewById(R.id.cardView);
        pieChart = (PieChart) view.findViewById(R.id.pc_callLog);
        mCardView.setMaxCardElevation(mCardView.getCardElevation()
                * CardAdapter.MAX_ELEVATION_FACTOR);

//        mBinding = FragmentMyStatFirstBinding.inflate(inflater, container, false);
//        mBinding.setLifecycleOwner(getActivity());
//        mBinding.cardView.setMaxCardElevation(mBinding.cardView.getCardElevation()
//                * CardAdapter.MAX_ELEVATION_FACTOR);
//
//        mCardView = (CardView) mBinding.getRoot().findViewById(R.id.cardView);

        tvUnknownCount = view.findViewById(R.id.tv_unknown_count);
        tvFirstCount = view.findViewById(R.id.tv_first_count);
        tvSecondCount = view.findViewById(R.id.tv_second_count);
        tvThirdCount = view.findViewById(R.id.tv_thrid_count);

        ScreenManager.startCountAnimation(25, tvUnknownCount);
        ScreenManager.startCountAnimation(19, tvFirstCount);
        ScreenManager.startCountAnimation(4, tvSecondCount);
        ScreenManager.startCountAnimation(2, tvThirdCount);

        setPieChard();


        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    public CardView getCardView() {

        return mCardView;
    }

    private void setPieChard(){

        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,0,5,0);
        pieChart.setTouchEnabled(false);
        pieChart.animateY(1000, Easing.EaseInOutCubic);
        //mBinding.pcCallLog.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setHoleRadius(60);
        pieChart.getLegend().setEnabled(false);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(4.0f, ""));
        yValues.add(new PieEntry(36.0f, ""));
        yValues.add(new PieEntry(60.0f, ""));

        PieDataSet dataSet = new PieDataSet(yValues, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ScreenManager.PIE_CHART_COLOR);



        PieData data = new PieData(dataSet);
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.BLACK);
        data.setValueFormatter(new MyValueFormatter());

        pieChart.setData(data);
    }


    private class MyValueFormatter extends ValueFormatter {


        @Override
        public String getFormattedValue(float value) {
            return "" + ((int) value)+ " %";
        }
    }

}