package org.gaozou.jimmy.vms.domain;

import org.gaozou.jimmy.base.Domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Author: george
 * Powered by GaoZou group.
 */
@Entity
@Table(name="PIC")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pic extends Domain {
    private String url;
    private String title;
    private String dscr;
    private User  user;
    private Album album;



    @Column(name="URL")
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name="TITLE")
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Lob
    @Column(name="DSCR")
    public String getDscr() {
        return dscr;
    }
    public void setDscr(String dscr) {
        this.dscr = dscr;
    }


    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="USER", nullable=false)
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="ALBUM", nullable=false)
    public Album getAlbum() {
        return album;
    }
    public void setAlbum(Album album) {
        this.album = album;
    }
}
