package com.sub.foodapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sub.foodapp.adapter.AllMenuAdapter;
import com.sub.foodapp.adapter.PopularAdapter;
import com.sub.foodapp.adapter.RecommendedAdapter;
import com.sub.foodapp.model.Allmenu;
import com.sub.foodapp.model.FoodData;
import com.sub.foodapp.model.Popular;
import com.sub.foodapp.model.Recommended;
import com.sub.foodapp.retrofit.ApiInterface;
import com.sub.foodapp.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;

    RecyclerView popularRecyclerView,recommendedRecyclerView, allMenuRecyclerView;

    PopularAdapter popularAdapter;
    RecommendedAdapter recommendedAdapter;
    AllMenuAdapter allMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);

        Call<List<FoodData>> call=apiInterface.getAllData();
        call.enqueue(new Callback<List<FoodData>>() {
            @Override
            public void onResponse(Call<List<FoodData>> call, Response<List<FoodData>> response) {

                List<FoodData> foodataList=response.body();

                getPopularData(foodataList.get(0).getPopular());
                getRecommendedData(foodataList.get(0).getRecommended());
                getAllMenu(foodataList.get(0).getAllmenu());


            }

            @Override
            public void onFailure(Call<List<FoodData>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Servier ist not responding.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPopularData(List<Popular> popularList){

        popularRecyclerView=findViewById(R.id.popular_recyler);
        popularAdapter=new PopularAdapter(this, popularList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        popularRecyclerView.setLayoutManager(layoutManager);
        popularRecyclerView.setAdapter(popularAdapter);

    }

    private void getRecommendedData(List<Recommended> recommendedList){

        recommendedRecyclerView=findViewById(R.id.recommanded_recycler);
        recommendedAdapter=new RecommendedAdapter(this, recommendedList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recommendedRecyclerView.setLayoutManager(layoutManager);
        recommendedRecyclerView.setAdapter(recommendedAdapter);

    }

    private void getAllMenu(List<Allmenu> allmenuList){

        allMenuRecyclerView=findViewById(R.id.all_menu_recycler);
        allMenuAdapter=new AllMenuAdapter(this, allmenuList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        allMenuRecyclerView.setLayoutManager(layoutManager);
        allMenuRecyclerView.setAdapter(allMenuAdapter);

    }



}