package org.gaozou.jimmy.vms.domain;

import org.gaozou.jimmy.base.Domain;
import org.gaozou.jimmy.vms.domain.tag.TagMovie;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Author: george
 * Powered by GaoZou group.
 */
@Entity
@Table(name="TAG")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tag extends Domain {
    private String  name;
    private Integer hit;
    private List<TagMovie> tagMovies;


    @Column(name="NAME", unique=true, nullable=false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name="HIT")
    public Integer getHit() {
        return hit;
    }
    public void setHit(Integer hit) {
        this.hit = hit;
    }

    @OneToMany(mappedBy="tag", cascade=CascadeType.REMOVE)
    @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    public List<TagMovie> getTagMovies() {
        return null == tagMovies ? tagMovies = new ArrayList<TagMovie>() : tagMovies;
    }
    public void setTagMovies(List<TagMovie> tagMovies) {
        this.tagMovies = tagMovies;
    }
    public Boolean containsTagMovie(TagMovie tum) {
        return this.getTagMovies().contains(tum);
    }
    public void addTagMovie(TagMovie tum) {
        tum.setTag(this);
        this.getTagMovies().add(tum);
    }
    public void removeTagMovie(TagMovie tum) {
        tum.setTag(null);
        this.getTagMovies().remove(tum);
    }

}
