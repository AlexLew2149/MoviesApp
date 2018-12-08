package com.techexchange.mobileapps.movieapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    public static ArrayList<Movie> movieList = new ArrayList<>();
    public static final String MOVIE_URL="https://image.tmdb.org/t/p/w185";
    private Context context;
    static final String MOVIE_INDEX = "Movie";

    public RecyclerViewAdapter(Context context, ArrayList<Movie> movieList) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_layout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.with(context)
                .load(MOVIE_URL+movieList.get(i).getPoster())
                .placeholder(R.drawable.image_placeholder)
                .into(viewHolder.movie);

        viewHolder.parentLayout.setOnClickListener(v -> OnMovieClicked(i));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    private void OnMovieClicked(int index)
    {
        Intent intent = new Intent(context, MovieInfo.class);
        intent.putExtra(MOVIE_INDEX, index);
        context.startActivity(intent);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView movie;
        FrameLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            movie = itemView.findViewById((R.id.movie_image));
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
