package org.gaozou.jimmy.vms.domain.tag;

import org.gaozou.jimmy.base.Domain;
import org.gaozou.jimmy.vms.domain.Tag;
import org.gaozou.jimmy.vms.domain.News;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

/**
 * Author: george
 * Powered by GaoZou group.
 */
@Entity
@Table(name="TAGNEWS")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TagNews extends Domain {
    private Tag tag;
    private News news;
    private List<UserTagNews> userTagNewz;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="TAG", nullable=false)
    public Tag getTag() {
        return tag;
    }
    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="NEWS", nullable=false)
    public News getNews() {
        return news;
    }
    public void setNews(News news) {
        this.news = news;
    }

    @OneToMany(mappedBy="tagNews", cascade=CascadeType.REMOVE)
    @Cache(usage= CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    public List<UserTagNews> getUserTagNewz() {
        return userTagNewz;
    }
    public void setUserTagNewz(List<UserTagNews> userTagNewz) {
        this.userTagNewz = userTagNewz;
    }
}
