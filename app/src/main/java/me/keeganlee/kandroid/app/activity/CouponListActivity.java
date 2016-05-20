package me.keeganlee.kandroid.app.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.keeganlee.kandroid.R;
import me.keeganlee.kandroid.app.KBaseActivity;
import me.keeganlee.kandroid.app.adapter.CouponListAdapter;
import me.keeganlee.kandroid.core.ActionCallbackListener;
import me.keeganlee.kandroid.model.CouponBO;

/**
 * Created by Tian on 2016/5/3.
 */
public class CouponListActivity extends KBaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.list_view)
    ListView listView;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;//谷歌官方下拉刷新组件
    private CouponListAdapter listAdapter;
    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_list);
        ButterKnife.bind(this);
        swipeRefreshLayout.setOnRefreshListener(this);
        listAdapter = new CouponListAdapter(this);
        listView.setAdapter(listAdapter);
        initViews();
        getData();



        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        listView = (ListView) findViewById(R.id.list_view);
        listAdapter = new CouponListAdapter(this);
        listView.setAdapter(listAdapter);
    }

    private void initViews() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        listView = (ListView) findViewById(R.id.list_view);
        listAdapter = new CouponListAdapter(this);
        listView.setAdapter(listAdapter);
    }
    private void getData() {
        this.appAction.listCoupon(currentPage, new ActionCallbackListener<List<CouponBO>>() {
            @Override
            public void onSuccess(List<CouponBO> data) {
                if (!data.isEmpty()) {
                    if (currentPage == 1) {
                        listAdapter.setItems(data);
                    } else {
                        listAdapter.addItems(data);
                    }
                }
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        listAdapter.clearItems();
        getData();
    }
}
