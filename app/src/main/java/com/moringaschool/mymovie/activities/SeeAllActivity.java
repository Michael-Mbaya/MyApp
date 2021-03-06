package com.moringaschool.mymovie.activities;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.moringaschool.mymovie.R;
import com.moringaschool.mymovie.adapters.TrailerAdapter;
import com.moringaschool.mymovie.models.Trailer;
import com.moringaschool.mymovie.network.TrailerViewModel;

import java.util.List;

public class SeeAllActivity extends AppCompatActivity {

    private ActivitySeeAllBinding binding;
    private TrailerAdapter trailerAdapter;
    private TrailerViewModel trailerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_see_all);

        trailerViewModel = ViewModelProviders.of(this).get(TrailerViewModel.class);

        setupRecyclerViews();

        getTrailers();
    }

    private void setupRecyclerViews() {
        // Trailers
        binding.listOfTrailers.setHasFixedSize(true);
        binding.listOfTrailers.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    public void getTrailers() {
        trailerViewModel.getAllTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(@Nullable List<Trailer> trailers) {
                trailerAdapter = new TrailerAdapter(getApplicationContext(), trailers);

                if (trailers != null && trailers.isEmpty()) {
                    binding.listOfTrailers.setVisibility(View.GONE);
                    binding.noTrailers.setVisibility(View.VISIBLE);
                }

                binding.listOfTrailers.setAdapter(trailerAdapter);
            }
        });
    }

}
