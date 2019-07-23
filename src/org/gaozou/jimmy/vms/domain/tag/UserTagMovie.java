package org.gaozou.jimmy.vms.domain.tag;

import org.gaozou.jimmy.base.Domain;
import org.gaozou.jimmy.vms.domain.User;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Author: george
 * Powered by GaoZou group.
 */
@Entity
@Table(name="USERTAGMOVIE")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserTagMovie extends Domain {
    private User user;
    private TagMovie tagMovie;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="USER", nullable=false)
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="TAGMOVIE", nullable=false)
    public TagMovie getTagMovie() {
        return tagMovie;
    }
    public void setTagMovie(TagMovie tagMovie) {
        this.tagMovie = tagMovie;
    }
}
