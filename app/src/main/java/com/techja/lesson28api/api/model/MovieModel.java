package com.techja.lesson28api.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieModel implements Serializable {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Result> listResult;

    @Getter
    @Setter
    public static class Result implements Serializable{
        @SerializedName("poster_path")
        private String posterPath;
        @SerializedName("original_title")
        private String title;
        @SerializedName("overview")
        private String overview;
        @SerializedName("release_date")
        private String releaseDate;
        @SerializedName("vote_average")
        private String vote;

        private boolean selectedMovie;
    }
}
