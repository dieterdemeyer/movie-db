package be.dieterdemeyer.moviedb.domain;

import javax.persistence.Entity;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Movie extends be.dieterdemeyer.moviedb.infrastructure.Entity {

    private String imdb;

    private String title;

    public Movie() {
    }

    public Movie(String imdb, String title) {
        this.imdb = imdb;
        this.title = title;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
}