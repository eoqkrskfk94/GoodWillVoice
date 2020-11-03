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
        newsItems.add(new NewsItem("sample", "반사회적 범죄 ‘보이스피싱’ 척결 종합방안, 어떤 대책 담겼나", "디지털 경제·금융의 신뢰 기반 조성을 위해 ‘보이스피싱 척결 종합방안’ 마련\n" +
                "[보안뉴스 원병철 기자] 중대한 반사회적 민생침해 범죄행위인 보이스피싱을 척결하기 위해, 9개 정부부처 및 기관들이 손잡고 전방위적인 예방·차단시스템 구축, 강력한 단속과 엄정한 처벌, 실효성 있는 피해구제 등을 담은 종합방안을 마련해 시행한다고 밝혔다.",
                "https://www.boannews.com/media/view.asp?idx=89206&kind=2"));

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