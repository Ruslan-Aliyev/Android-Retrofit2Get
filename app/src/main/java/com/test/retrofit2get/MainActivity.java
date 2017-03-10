package com.test.retrofit2get;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.test.retrofit2get.Model.Model;
import com.test.retrofit2get.Service.Service;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends Activity {

    private Button btnGetAsync, btnGetSync;
    private TextView subjectInfo, infoInfo;
    private String version = "2.0";
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Service service;
    private String errorPlaceHolder = "No Info Obtained";
	private static final String ROOT_URL = "http://ruslancode.net23.net/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        subjectInfo = (TextView) findViewById(R.id.subjectInfo);
        infoInfo = (TextView) findViewById(R.id.infoInfo);

        radioGroup=(RadioGroup)findViewById(R.id.choice);

        btnGetSync = (Button) findViewById(R.id.btnGetSync);
        btnGetSync.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getRequestSync();
            }
        });
        btnGetAsync = (Button) findViewById(R.id.btnGetAsync);
        btnGetAsync.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getRequestAsync();
            }
        });

		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

		service = new Retrofit.Builder().baseUrl(ROOT_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build().create(Service.class);
	}

    private void getRequestSync() {
        final Call<Model> call = service.get(version, getTest() );

        final Handler handler = new Handler();
        new Thread(new Runnable(){
            @Override
            public void run() {

                try {
                    final Model model = call.execute().body();

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            updateUi(model.getSubject(), model.getInfo());
                            Log.i("Result", "Success");
                        }
                    });
                } catch (IOException e) {
                    updateUi(errorPlaceHolder, errorPlaceHolder);
                    Log.i("Result", e.getMessage() );
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void getRequestAsync() {
        Call<Model> call = service.get(version, getTest() );
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                updateUi(response.body().getSubject(), response.body().getInfo());
                Log.i("Result", response.toString() );
            }
            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                updateUi(errorPlaceHolder, errorPlaceHolder);
                Log.i("Result", t.getMessage() );
            }
        });
    }

    private String getTest(){
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton)findViewById(selectedId);
        return radioButton.getTag().toString();
    }

    private void updateUi(String subject, String info){
        subjectInfo.setText(subject);
        infoInfo.setText(info);
    }
}
