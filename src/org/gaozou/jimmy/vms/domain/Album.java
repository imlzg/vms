package org.gaozou.jimmy.vms.domain;

import org.gaozou.jimmy.base.Domain;
import org.gaozou.jimmy.vms.domain.tag.TagAlbum;
import org.gaozou.jimmy.vms.domain.tag.TagNews;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.List;

/**
 * Author: george
 * Powered by GaoZou group.
 */
@Entity
@Indexed
@Table(name="ALBUM")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Album extends Domain {
    private String title;
    private String summary;
    private List<Pic> pics;

    private List<TagAlbum> tagAlbums;


    @Column(name="TITLE")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name="SUMMARY")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }

    @OneToMany(mappedBy="album", cascade=CascadeType.REMOVE)
    @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    public List<Pic> getPics() {
        return pics;
    }
    public void setPics(List<Pic> pics) {
        this.pics = pics;
    }

    @OneToMany(mappedBy="album", cascade=CascadeType.REMOVE)
    @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Field(index= Index.TOKENIZED, store= Store.NO, termVector= TermVector.YES,
            analyzer=@Analyzer(impl=org.gaozou.jimmy.base.support.lucene.CommaAnalyzer.class),
            bridge=@FieldBridge(impl=org.gaozou.jimmy.vms.support.hsearch.TagAlbumBridge.class))
    public List<TagAlbum> getTagAlbums() {
        return tagAlbums;
    }
    public void setTagAlbums(List<TagAlbum> tagAlbums) {
        this.tagAlbums = tagAlbums;
    }
    public Boolean containsTagAlbum(TagAlbum t) {
        return this.getTagAlbums().contains(t);
    }
    public void addTagAlbum(TagAlbum t) {
        t.setAlbum(this);
        this.getTagAlbums().add(t);
    }
    public void removeTagAlbum(TagAlbum t) {
        t.setAlbum(null);
        this.getTagAlbums().remove(t);
    }
}
