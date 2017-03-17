package com.powtronic.rxjava;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Response;

import static android.R.attr.value;

public class ScrollingActivity extends AppCompatActivity {

    @InjectView(R.id.btn_send)
    Button btnSend;
    @InjectView(R.id.tv_response)
    TextView tvResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        ButterKnife.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_send)
    public void onClick() {
//        base();
//        form();


        test();
    }

    private void test() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Response execute = OkGo.get("http://10.0.0.199:80/getData").execute();
                if (execute.code() == 200) {
                    e.onNext(execute.body().string());
                } else {
                    e.onError(new Throwable("连接失败！"));
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        System.out.println("onNext");
                        System.out.println("Thread=======" + Thread.currentThread().getName());
                        System.out.println(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError");
                        System.out.println(e.getMessage());
                        System.out.println("Thread=======" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void form() {
        List<String> list = new ArrayList<>();
        list.add("from1");
        list.add("from2");
        list.add("from3");

        Observable.range(1, 10)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribe");
                        System.out.println("Thread=======" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(Integer value) {
                        System.out.println("onNext==" + value);

                        System.out.println("Thread=======" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError");
                        System.out.println("Thread=======" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                        System.out.println("Thread=======" + Thread.currentThread().getName());
                    }
                });
    }

    private void base() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                //执行一些其他操作
                //执行完毕，触发回调，通知观察者
                e.onNext("1");
                e.onNext("2");
                e.onNext("3");
                e.onNext("4");
                e.onNext("5");
                System.out.println("subscribe");
                System.out.println("Thread=======" + Thread.currentThread().getName());


            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribe");
                        System.out.println("Thread=======" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String value) {
                        System.out.println("onNext==" + value);

                        System.out.println("Thread=======" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError");
                        System.out.println("Thread=======" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                        System.out.println("Thread=======" + Thread.currentThread().getName());
                    }
                });
    }
}
