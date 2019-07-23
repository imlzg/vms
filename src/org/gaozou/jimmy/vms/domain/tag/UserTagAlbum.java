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
@Table(name="USERTAGALBUM")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserTagAlbum extends Domain {
    private User user;
    private TagAlbum tagAlbum;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="USER", nullable=false)
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="TAGALBUM", nullable=false)
    public TagAlbum getTagAlbum() {
        return tagAlbum;
    }
    public void setTagAlbum(TagAlbum tagAlbum) {
        this.tagAlbum = tagAlbum;
    }
}
