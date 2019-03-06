package com.example.clonehorizontalscrollview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.clonehorizontalscrollview.Model.Data4;
import com.example.clonehorizontalscrollview.presenter.LoaderPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.functions.Consumer;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {

    private MyHorizontalScrollView mHorizontalScrollView;
    private HorizontalScrollViewAdapter mAdapter;
    private ImageView mImg;
    private List<Data4.DataBean> mDatas = new ArrayList<>();
    private int rn = 20;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mImg = (ImageView) findViewById(R.id.id_content);

        LoaderPresenter loaderPresenter = new LoaderPresenter();
        loaderPresenter.LoaderPresenter("0","10").subscribe(new Consumer<Data4>() {
            @Override
            public void accept(Data4 data4) throws Exception {
                mDatas = data4.getData();
                for (Data4.DataBean i:mDatas) {
                    if (i.getImage_url() == null){
                        mDatas.remove(i);
                    }
                }
                Log.e(TAG, "accept: "+mDatas.get(0).getImage_url());
                initview();
            }
        });

    }
    public void initview(){
        mHorizontalScrollView = (MyHorizontalScrollView) findViewById(R.id.id_horizontalScrollView);
        mAdapter = new HorizontalScrollViewAdapter(this, mDatas);
        //添加滚动回调
        mHorizontalScrollView
                .setCurrentImageChangeListener(new MyHorizontalScrollView.CurrentImageChangeListener()
                {
                    @Override
                    public void onCurrentImgChanged(int position,
                                                    View viewIndicator)
                    {
                        Glide.with(viewIndicator).load(mDatas.get(position).getImage_url()).into(mImg);
                        viewIndicator.setBackgroundColor(Color
                                .parseColor("#AA024DA4"));
                    }
                });
        //添加点击回调
        mHorizontalScrollView.setOnItemClickListener(new MyHorizontalScrollView.OnItemClickListener()
        {

            @Override
            public void onClick(View view, int position)
            {
                Glide.with(view).load(mDatas.get(position).getImage_url()).into(mImg);
                view.setBackgroundColor(Color.parseColor("#AA024DA4"));
            }
        });
        mHorizontalScrollView.setrmListener(new MyHorizontalScrollView.SetmDatas() {
            @SuppressLint("CheckResult")
            @Override
            public List<Data4.DataBean> getmDatas() {
                Toast.makeText(getApplicationContext(),"更新中",Toast.LENGTH_LONG).show();
                LoaderPresenter loaderPresenter = new LoaderPresenter();
                    loaderPresenter.LoaderPresenter("" + (rn - 10), "" + rn).subscribe(new Consumer<Data4>() {
                        @Override
                        public void accept(Data4 data4) throws Exception {
                            mDatas.addAll(data4.getData());
                            for (Data4.DataBean i : mDatas) {
                                if (i.getImage_url() == null) {
                                    mDatas.remove(i);
                                }
                            }
                            Log.e(TAG, "accept: " + mDatas.get(0).getImage_url());
                            Toast.makeText(getApplicationContext(),"更新好了",Toast.LENGTH_LONG).show();
                        }
                    });
                    rn+=10;
                return mDatas;
            }
        });
        //设置适配器
        mHorizontalScrollView.initDatas(mAdapter);
    }
}
