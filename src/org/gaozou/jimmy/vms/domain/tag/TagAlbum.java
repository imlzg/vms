package org.gaozou.jimmy.vms.domain.tag;

import org.gaozou.jimmy.base.Domain;
import org.gaozou.jimmy.vms.domain.Album;
import org.gaozou.jimmy.vms.domain.Tag;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

/**
 * Author: george
 * Powered by GaoZou group.
 */
@Entity
@Table(name="TAGALBUM")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TagAlbum extends Domain {
    private Tag tag;
    private Album album;
    private List<UserTagAlbum> userTagAlbums;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="TAG", nullable=false)
    public Tag getTag() {
        return tag;
    }
    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="ALBUM", nullable=false)
    public Album getAlbum() {
        return album;
    }
    public void setAlbum(Album album) {
        this.album = album;
    }

    @OneToMany(mappedBy="tagAlbum", cascade=CascadeType.REMOVE)
    @Cache(usage= CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    public List<UserTagAlbum> getUserTagAlbums() {
        return userTagAlbums;
    }
    public void setUserTagAlbums(List<UserTagAlbum> userTagAlbums) {
        this.userTagAlbums = userTagAlbums;
    }
}
