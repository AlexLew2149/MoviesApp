package com.techexchange.mobileapps.movieapp;

import java.io.Serializable;

public class Movie implements Serializable {
    private int ID;
    private String AverageVote;
    private String originalTitle;
    private String title;
    private String background;
    private String description;
    private String releaseDate;
    private String poster;
    private int votes;
    private double popularity;

    public int getID(){
        return ID;
    }

    public void setID(int id){
        this.ID = id;
    }

    public String getAverageVote() {
        return AverageVote;
    }

    public void setAverageVote(String AverageVote){
        this.AverageVote = AverageVote;
    }

    public int getVotes(){
        return votes;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
}
