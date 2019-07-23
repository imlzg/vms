package org.gaozou.jimmy.vms.domain.tag;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.gaozou.jimmy.base.Domain;
import org.gaozou.jimmy.vms.domain.User;
import org.gaozou.jimmy.vms.domain.Movie;
import org.gaozou.jimmy.vms.domain.Tag;

import javax.persistence.*;
import java.util.List;

/**
 * Author: george
 * Powered by GaoZou group.
 */
@Entity
@Table(name="TAGMOVIE")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TagMovie extends Domain {
    private Tag tag;
    private Movie movie;
    private List<UserTagMovie> userTagMovies;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="TAG", nullable=false)
    public Tag getTag() {
        return tag;
    }
    public void setTag(Tag tag) {
        this.tag = tag;
    }


    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="MOVIE", nullable=false)
    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @OneToMany(mappedBy="tagMovie", cascade=CascadeType.REMOVE)
    @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    public List<UserTagMovie> getUserTagMovies() {
        return userTagMovies;
    }
    public void setUserTagMovies(List<UserTagMovie> userTagMovies) {
        this.userTagMovies = userTagMovies;
    }
}
