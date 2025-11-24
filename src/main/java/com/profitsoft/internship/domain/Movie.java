package com.profitsoft.internship.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


/**
 * The entity of the domain area representing a Movie.
 * This class is used for deserializing data from JSON files.
 * */
public class Movie implements Serializable {

    private String title;

    @JsonProperty(value = "director_name")
    private String directorName;

    @JsonProperty(value = "release_year")
    private short releaseYear;

    private String description;

    @JsonProperty(value = "minutes_duration")
    private int minutesDuration;

    private List<String> comments;

    public Movie(){ }

    public Movie(String title, List<String> comments, int minutesDuration,
                 String description, short releaseYear, String directorName
    ) {
        this.title = title;
        this.comments = comments;
        this.minutesDuration = minutesDuration;
        this.description = description;
        this.releaseYear = releaseYear;
        this.directorName = directorName;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
         this.title = title;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public short getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(short releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMinutesDuration() {
        return minutesDuration;
    }

    public void setMinutesDuration(int minutesDuration) {
        this.minutesDuration = minutesDuration;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", directorName='" + directorName + '\'' +
                ", releaseYear=" + releaseYear +
                ", description='" + description + '\'' +
                ", minutesDuration=" + minutesDuration +
                ", comments=" + comments +
                '}';
    }

    @Override
    public boolean equals(Object obj){
        if (obj == this){
            return true;
        }
        if (obj == null || !(obj.getClass() == getClass())){
            return false;
        }

        Movie movie = (Movie) obj;
        return Objects.equals(movie.title, this.title) &&
                Objects.equals(movie.directorName, this.directorName) &&
                movie.minutesDuration == this.minutesDuration &&
                movie.releaseYear == this.releaseYear &&
                Objects.equals(movie.description, this.description) &&
                Objects.equals(movie.comments, comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, directorName, releaseYear, description, minutesDuration, comments);
    }
}
