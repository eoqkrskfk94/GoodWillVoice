package com.goodwiil.goodwillvoice.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.adapter.NewsAdapter;
import com.goodwiil.goodwillvoice.databinding.FragmentCallLogBinding;
import com.goodwiil.goodwillvoice.databinding.FragmentNewsBinding;
import com.goodwiil.goodwillvoice.model.NewsItem;


public class FragmentNews extends Fragment {
    private FragmentNewsBinding mBinding;
    private NewsAdapter newsAdapter;
    private ObservableArrayList<NewsItem> newsItems;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false);




        newsItems = new ObservableArrayList<>();
        newsItems.add(new NewsItem("sample", "샘플 뉴스 타이틀", "이것은 샘플 뉴스의 내용입니다. 참고하세요."));

        //System.out.println(newsItems.size());
        mBinding.tvSample.setText(Integer.toString(newsItems.size()));

        mBinding.rvNews.setLayoutManager(
                new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false)
        );


        newsAdapter = new NewsAdapter();

        mBinding.rvNews.setAdapter(newsAdapter);
        mBinding.setNewsList(newsItems);


        return mBinding.getRoot();
    }


}