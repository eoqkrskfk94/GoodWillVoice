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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.adapter.CardAdapter;
import com.goodwiil.goodwillvoice.util.ScreenManager;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class FragmentMyStatSecond extends Fragment {


    private CardView mCardView;
    private BarChart barChart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_stat_second, container, false);
        mCardView = (CardView) view.findViewById(R.id.cardView);
        mCardView.setMaxCardElevation(mCardView.getCardElevation()
                * CardAdapter.MAX_ELEVATION_FACTOR);

        barChart = (BarChart)view.findViewById(R.id.bc_callLog);

        setBarChart();


        return view;
    }




    public CardView getCardView() { return mCardView; }

    private void setBarChart(){
        barChart.getDescription().setEnabled(false);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(false);
        //barChart.setBackgroundColor(Color.TRANSPARENT);
        //barChart.setDrawGridBackground(false);
        barChart.animateY(1000, Easing.EaseInOutCubic);
        barChart.getLegend().setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawGridLines(false);
        //xAxis.setDrawLabels(false);
        xAxis.setDrawAxisLine(false);


        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setDrawAxisLine(false);
        rightAxis.setDrawLabels(false);
        rightAxis.setDrawGridLines(false);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(1, 40f));
        barEntries.add(new BarEntry(2, 50f));
        barEntries.add(new BarEntry(3, 30f));
        barEntries.add(new BarEntry(4, 80f));
        barEntries.add(new BarEntry(5, 20f));

        BarDataSet barDataSet = new BarDataSet(barEntries, "");
        barDataSet.setColors(ScreenManager.BAR_CHART_COLOR);

        BarData data = new BarData(barDataSet);
        data.setBarWidth(0.5f);

        String[] types = new String[]{"지인", "대출", "광고", "학교", "기타"};
        xAxis.setValueFormatter(new MyXAxisValueFormatter(types));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        barChart.setData(data);

    }

    public class MyXAxisValueFormatter extends ValueFormatter {

        private String[] mValues;
        public MyXAxisValueFormatter(String[] values){
            this.mValues = values;
        }


        @Override
        public String getFormattedValue(float value) {
            return mValues[(int)value];
        }
    }






}