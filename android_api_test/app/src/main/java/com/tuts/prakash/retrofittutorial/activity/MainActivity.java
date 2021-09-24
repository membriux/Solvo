package com.tuts.prakash.retrofittutorial.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.tuts.prakash.retrofittutorial.R;
import com.tuts.prakash.retrofittutorial.adapter.CustomAdapter;
import com.tuts.prakash.retrofittutorial.model.Problem;
import com.tuts.prakash.retrofittutorial.network.GetDataService;
import com.tuts.prakash.retrofittutorial.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<List<Problem>> call = service.getAllProblems();
        call.enqueue(new Callback<List<Problem>>() {

            @Override
            public void onResponse(Call<List<Problem>> call, Response<List<Problem>> response) {
                Log.e("NETWORK OBJECTS:", response.body().toString());
                progressDoalog.dismiss();
//                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Problem>> call, Throwable t) {
                progressDoalog.dismiss();
                Log.e("ERROR with API", t.getLocalizedMessage());
                Toast.makeText(MainActivity.this, "ERORR" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Problem> photoList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(this,photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
