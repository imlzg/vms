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
@Table(name="USERTAGNEWS")
@Cache(usage= CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserTagNews extends Domain {
    private User user;
    private TagNews tagNews;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="USER", nullable=false)
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="TAGNEWS", nullable=false)
    public TagNews getTagNews() {
        return tagNews;
    }
    public void setTagNews(TagNews tagNews) {
        this.tagNews = tagNews;
    }
}
