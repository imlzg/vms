package org.gaozou.jimmy.vms.domain.tag;

import org.gaozou.jimmy.base.Domain;
import org.gaozou.jimmy.vms.domain.Tag;
import org.gaozou.jimmy.vms.domain.Star;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

/**
 * Author: george
 * Powered by GaoZou group.
 */
@Entity
@Table(name="TAGSTAR")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TagStar extends Domain {
    private Tag tag;
    private Star star;
    private List<UserTagStar> userTagStars;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="TAG", nullable=false)
    public Tag getTag() {
        return tag;
    }
    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="STAR", nullable=false)
    public Star getStar() {
        return star;
    }
    public void setStar(Star star) {
        this.star = star;
    }

    @OneToMany(mappedBy="tagStar", cascade=CascadeType.REMOVE)
    @Cache(usage= CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    public List<UserTagStar> getUserTagStars() {
        return userTagStars;
    }
    public void setUserTagStars(List<UserTagStar> userTagStars) {
        this.userTagStars = userTagStars;
    }
}
