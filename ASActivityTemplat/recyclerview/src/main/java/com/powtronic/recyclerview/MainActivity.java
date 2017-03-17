package com.powtronic.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.Rv)
    RecyclerView mRv;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //初始数据
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("第" + i + "条数据");
        }

        /*初始化RecycleView
        * LinearLayoutManager           现行管理器，支持横向、纵向。
        * GridLayoutManager             网格布局管理器
        * StaggeredGridLayoutManager    瀑布就式布局管理器
        */

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);       //设置为垂直布局，这也是默认的
        mRv.setLayoutManager(layoutManager);                            //设置布局管理器
        mRv.setItemAnimator(new DefaultItemAnimator());                  //设置增加或删除条目的动画
        mRv.addItemDecoration(new RecyclerViewDivider(this, OrientationHelper.HORIZONTAL));    //设置分割线

        adapter = new MainAdapter(this, list);
        mRv.setAdapter(adapter);
    }


    class MainAdapter extends BaseAdapter<String> {

        public MainAdapter(Context context, List<String> datas) {
            super(context, datas);
        }

        @Override
        protected int getItemViewForType(int viewType) {
            return R.layout.item_layout;
        }

        @Override
        protected void convert(ViewHolder holder, String s) {
            holder.setText(R.id.tv_item, s);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_h) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager .setOrientation(OrientationHelper.HORIZONTAL);
            mRv.addItemDecoration(new RecyclerViewDivider(this, OrientationHelper.HORIZONTAL));    //设置分割线
            mRv.setLayoutManager(linearLayoutManager);
            mRv.setAdapter(adapter);
        } else if (id == R.id.action_v) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager .setOrientation(OrientationHelper.VERTICAL);
            mRv.addItemDecoration(new RecyclerViewDivider(this, OrientationHelper.VERTICAL));
            mRv.setLayoutManager(linearLayoutManager);
            mRv.setAdapter(adapter);
        } else if (id == R.id.action_g) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            mRv.addItemDecoration(new RecyclerViewGridItemDecoration(this));
            mRv.setLayoutManager(gridLayoutManager);
            mRv.setAdapter(adapter);
        } else if (id == R.id.action_p) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
            mRv.addItemDecoration(new RecyclerViewGridItemDecoration(this));
            mRv.setLayoutManager(staggeredGridLayoutManager);

            mRv.setAdapter(adapter);
        }

        return true;
    }
}
