package com.example.karim.rxjava.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.karim.rxjava.Adapter.Adapter;
import com.example.karim.rxjava.Model.Post;
import com.example.karim.rxjava.R;
import com.example.karim.rxjava.Retrofit.IMyAPI;
import com.example.karim.rxjava.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    IMyAPI myAPI;
    RecyclerView rv;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb=findViewById(R.id.pb);
        Retrofit retrofit=RetrofitClient.getInstance();
        myAPI=retrofit.create(IMyAPI.class);
        rv=findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        FetchData();
        List<Post>p=new ArrayList<>();
        Adapter adapter=new Adapter(this,p);
        rv.setAdapter(adapter);
    }
    private void FetchData(){
        compositeDisposable.add(myAPI.getPosts()
        .subscribeOn(Schedulers.io())
        .subscribe(new Consumer<List<Post>>() {
            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }

            @Override
            public void accept(final List<Post> posts) throws Exception {
                        adapter=new Adapter(MainActivity.this,posts);
                        displayData();
            }
        }));    
    }
Adapter adapter;
private void displayData(){
    this.runOnUiThread(new Runnable() {
        @Override
        public void run() {
            pb.setVisibility(View.GONE);
            rv.setAdapter(adapter);
        }
    }
    );

}

    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }
}
