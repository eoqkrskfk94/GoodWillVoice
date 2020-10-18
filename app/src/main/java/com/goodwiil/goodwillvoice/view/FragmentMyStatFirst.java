package com.goodwiil.goodwillvoice.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
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
import com.goodwiil.goodwillvoice.model.CallLogInfo;
import com.goodwiil.goodwillvoice.model.CardItem;
import com.goodwiil.goodwillvoice.util.CallLogDataManager;
import com.goodwiil.goodwillvoice.util.ScreenManager;
import com.goodwiil.goodwillvoice.util.ShadowTransformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class FragmentMyStatFirst extends Fragment {

    private FragmentMyStatFirstBinding mBinding;


    private CardView mCardView;
    private PieChart pieChart;
    private ArrayList<CallLogInfo> callLogInfos;
    private ArrayList<CallLogInfo> callLogInfos30Days;

    private TextView tvUnknownCount;
    private TextView tvFirstCount;
    private TextView tvSecondCount;
    private TextView tvThirdCount;

    private int firstWarningCount = 0;
    private int secondWarningCount = 0;
    private int thirdWarningCount = 0;

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

        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED){
            callLogInfos = CallLogDataManager.getCallLog(getContext(), 1);
        }

        System.out.println(callLogInfos.size());
        try {
            checkRecentCallLogData();
        } catch (ParseException e) {
            e.printStackTrace();
        }


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


    private void checkRecentCallLogData() throws ParseException {

        callLogInfos30Days = new ArrayList<>();
        firstWarningCount = 0;
        secondWarningCount = 0;
        thirdWarningCount = 0;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.KOREA);
        Date currentDate = new Date();


        for(int i = 0; i < callLogInfos.size(); i++){
            Date callLogDate = formatter.parse((callLogInfos.get(i).getDate()));


            long diffInMillies = Math.abs(currentDate.getTime() - callLogDate.getTime());
            long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            if(diffInDays <= 30) {
                callLogInfos30Days.add(callLogInfos.get(i));
                if(callLogInfos.get(i).getDuration() >= 600) thirdWarningCount++;
                else if(callLogInfos.get(i).getDuration() >= 480) secondWarningCount++;
                //else if(callLogInfos.get(i).getDuration() >= 300) firstWarningCount++;
                else firstWarningCount++;
            }

            else{
                break;
            }
        }



        ScreenManager.startCountAnimation(callLogInfos30Days.size(), tvUnknownCount);
        ScreenManager.startCountAnimation(firstWarningCount, tvFirstCount);
        ScreenManager.startCountAnimation(secondWarningCount, tvSecondCount);
        ScreenManager.startCountAnimation(thirdWarningCount, tvThirdCount);


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

        if(firstWarningCount > 0) yValues.add(new PieEntry(firstWarningCount/callLogInfos30Days.size(), ""));
        if(secondWarningCount > 0) yValues.add(new PieEntry(secondWarningCount/callLogInfos30Days.size(), ""));
        if(thirdWarningCount > 0) yValues.add(new PieEntry(thirdWarningCount/callLogInfos30Days.size(), ""));

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
            return "" + ((int) value) * 100 + " %";
        }
    }

}