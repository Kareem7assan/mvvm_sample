package ir.orangehat.movieinfo.application.ui.home;

import android.app.ProgressDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.MutableInt;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ir.orangehat.movieinfo.R;
import ir.orangehat.movieinfo.bussines.model.Movie;
import ir.orangehat.movieinfo.bussines.networking.api.ApiStates;
import ir.orangehat.movieinfo.bussines.networking.api.Resource;/*
import ir.orangehat.movieinfo.bussines.networking.api.Status;

import static ir.orangehat.movieinfo.bussines.networking.api.Status.ERROR_SERVER;
import static ir.orangehat.movieinfo.bussines.networking.api.Status.LOADING;*/

import static ir.orangehat.movieinfo.bussines.networking.api.Resource.Status.ERROR;
import static ir.orangehat.movieinfo.bussines.networking.api.Resource.Status.LOADING;

public class HomeActivity extends AppCompatActivity {

    private DiscreteScrollView discreteScrollView;
    private HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        discreteScrollView = findViewById(R.id.discreteScrollView);
        discreteScrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMaxScale(1.05f)
                .setMinScale(0.8f)
                .setPivotX(Pivot.X.CENTER) // CENTER is a default one
                .setPivotY(Pivot.Y.BOTTOM) // CENTER is a default one
                .build());

        ProgressDialog dialog = new ProgressDialog(HomeActivity.this);
        dialog.setMessage("wait");
        dialog.setCancelable(false);

     /*   LiveData<List<Movie>> movies = ViewModelProviders.of(this).get(HomeViewModel.class).getMovies();
        movies.observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                Log.e("resp",new Gson().toJson(movies)+".");
                homeAdapter=new HomeAdapter(getApplicationContext(),movies);
                discreteScrollView.setAdapter(homeAdapter);
            }
        });*/

        MutableLiveData<Resource> data = ViewModelProviders.of(this).get(HomeViewModel.class).getState();
        data.observe(this, new Observer<Resource>() {
            @Override
            public void onChanged(@Nullable Resource apiStates) {
                Log.e("states",new Gson().toJson(apiStates)+".");
                if (apiStates.status==LOADING){
                    dialog.show();
                }
                else {
                    dialog.hide();
                    if (apiStates.status==ERROR){
                        Toast.makeText(HomeActivity.this, apiStates.message, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        ArrayList<Movie> movies = (ArrayList<Movie>) apiStates.data;
                        Log.e("resp",new Gson().toJson(movies)+".");
                        homeAdapter=new HomeAdapter(getApplicationContext(),movies);
                        discreteScrollView.setAdapter(homeAdapter);
                    }
                }

            }
        });


    }
}
