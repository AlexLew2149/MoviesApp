package com.techexchange.mobileapps.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.techexchange.mobileapps.movieapp.RecyclerViewAdapter.MOVIE_INDEX;
import static com.techexchange.mobileapps.movieapp.RecyclerViewAdapter.MOVIE_URL;
import static com.techexchange.mobileapps.movieapp.RecyclerViewAdapter.movieList;

public class MovieInfo extends AppCompatActivity {

    ImageView moviePoster;
    TextView movieTitle;
    TextView movieRating;
    TextView movieDescription;
    TextView movieDate;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        moviePoster = findViewById(R.id.movie_poster);
        movieTitle = findViewById(R.id.movie_title);
        movieRating = findViewById(R.id.movie_rating);
        movieDescription = findViewById(R.id.movie_description);
        movieDate = findViewById(R.id.movie_date);

        index = getIntent().getIntExtra(MOVIE_INDEX, 0);
        Movie movie = movieList.get(index);

        Picasso.with(this)
                .load(MOVIE_URL+ movie.getPoster())
                .placeholder(R.drawable.image_placeholder)
                .into(moviePoster);

        movieTitle.setText(String.valueOf(movie.getOriginalTitle()));
        movieRating.setText(String.valueOf(movie.getAverageVote()) + "/10");
        movieDescription.setText(String.valueOf(movie.getDescription()));
        movieDate.setText(String.valueOf(movie.getReleaseDate()));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("CurrentIndex", index);
    }
}
