package com.techexchange.mobileapps.movieapp;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Taggefied";
    private boolean displayView = false;
    String popularMoviesURL, ratedMoviesURL;
    ArrayList<Movie> mPopularList = new ArrayList<>();
    ArrayList<Movie> mTopTopRatedList;
    boolean landscapeView = false;
    int savedList = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new MovieGetter().execute();
        while(!displayView) {
            Log.d(TAG, "Loading");
        }

        if(savedInstanceState != null)
        {
            savedList = savedInstanceState.getInt("CurrentList");
        }

        if(savedList == 0)
        {
            InitRecyclerView(mPopularList);
        }if(savedList == 1)
        {
            InitRecyclerView(mTopTopRatedList);
        }

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("CurrentList", savedList);
    }

    public class MovieGetter extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            popularMoviesURL = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=27e151a219f0a3a44542390fc3123cbe";

            ratedMoviesURL = "http://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&api_key=27e151a219f0a3a44542390fc3123cbe";

            mPopularList = new ArrayList<>();
            mTopTopRatedList = new ArrayList<>();
            try {
                if(MovieUtilities.networkStatus(MainActivity.this)){
                    mPopularList = MovieUtilities.fetchData(popularMoviesURL); //Get popular movies
                    mTopTopRatedList = MovieUtilities.fetchData(ratedMoviesURL); //Get top rated movies

                }else{
                    Toast.makeText(MainActivity.this,"No Internet Connection", Toast.LENGTH_LONG).show();

                }
            } catch (IOException e){
                e.printStackTrace();

            }
            displayView = true;
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void  s) {
            super.onPostExecute(s);
        }
    }

    private void InitRecyclerView(ArrayList<Movie> movieList)
    {
        int i = 0;
        RecyclerView recyclerView = findViewById(R.id.movie_list);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, movieList);

        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        else
        {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.pop_movies) {
            InitRecyclerView(mPopularList);
            savedList = 0;
        }
        if (id == R.id.top_movies) {
            InitRecyclerView(mTopTopRatedList);
            savedList = 1;
        }
        return super.onOptionsItemSelected(item);
    }
}