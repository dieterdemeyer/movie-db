package be.dieterdemeyer.moviedb.domain;


public class MovieTestBuilder {

    public static final String IMDB = "123456";

    public static final String TITLE = "The Matrix";

    private String imdb = IMDB;

    private String title = TITLE;

    public Movie build() {
        return new Movie(imdb, title);
    }

    public MovieTestBuilder withImdb(String imdb) {
        this.imdb = imdb;
        return this;
    }

    public MovieTestBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

}
