package com.example.karim.rxjava.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.karim.rxjava.Model.Post;
import com.example.karim.rxjava.R;
import com.example.karim.rxjava.Retrofit.IMyAPI;
import com.example.karim.rxjava.Retrofit.RetrofitClient;


import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class SendDataActivity extends AppCompatActivity {
    IMyAPI myAPI;
    Button btnSubmit;
    EditText titleText,authorTxt,contentTxt;
    Post post;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    private void __int__(){
        btnSubmit=findViewById(R.id.btnSubmit);
        titleText=findViewById(R.id.txt_title);
        authorTxt=findViewById(R.id.txt_Author);
        contentTxt=findViewById(R.id.txt_content);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data);
        __int__();
        Retrofit retrofit=RetrofitClient.getInstance();
        myAPI=retrofit.create(IMyAPI.class);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              getData();
            }
        });
    }

    private void getData() {
        post=new Post();
        post.setBody(contentTxt.getText().toString());
        post.setUserId(Integer.parseInt(authorTxt.getText().toString()));
        post.setTitle(titleText.getText().toString());
        SendData();
    }

    private void SendData() {
        myAPI.sendData(post)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Post> posts) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        SendDataActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(SendDataActivity.this, "Error", Toast.LENGTH_SHORT).show();

                            }
                        });
                        Log.i("error",e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        SendDataActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(SendDataActivity.this, "Done", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }
}
