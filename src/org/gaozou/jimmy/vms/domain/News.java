package org.gaozou.jimmy.vms.domain;

import org.gaozou.jimmy.base.Domain;
import org.gaozou.jimmy.vms.domain.tag.TagNews;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Author: george
 * Powered by GaoZou group.
 */
@Entity
@Table(name="NEWS")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Indexed
public class News extends Domain {
    private String title;
    private String content;
    private String provider;
    private String purl;
    private Long   addTime;
    private Long   editTime;
//    private List<Image> images;

//    private Integer hit;

    private User user;

//    private Boolean rec; //recommend
    private List<TagNews> tagNewz;


    @Column(name="TITLE")
    @Field(index=Index.TOKENIZED, store=Store.YES)
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Lob
    @Column(name="CONTENT")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @Column(name="PROVIDER")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getProvider() {
        return provider;
    }
    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Column(name="LINK")
    public String getPurl() {
        return purl;
    }
    public void setPurl(String purl) {
        this.purl = purl;
    }

    @Column(name="ADDTIME")
    public Long getAddTime() {
        return addTime;
    }
    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    @Column(name="EDITTIME")
    public Long getEditTime() {
        return editTime;
    }
    public void setEditTime(Long editTime) {
        this.editTime = editTime;
    }

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="USER", nullable=false)
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy="news", cascade=CascadeType.REMOVE)
    @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Field(index=Index.TOKENIZED, store=Store.NO, termVector=TermVector.YES,
            analyzer=@Analyzer(impl=org.gaozou.jimmy.base.support.lucene.CommaAnalyzer.class),
            bridge=@FieldBridge(impl=org.gaozou.jimmy.vms.support.hsearch.TagNewsBridge.class))
    public List<TagNews> getTagNewz() {
        return null == tagNewz ? tagNewz = new ArrayList<TagNews>() : tagNewz;
    }
    public void setTagNewz(List<TagNews> tagNewz) {
        this.tagNewz = tagNewz;
    }
    public Boolean containsTagNews(TagNews tn) {
        return this.getTagNewz().contains(tn);
    }
    public void addTagNews(TagNews tn) {
        tn.setNews(this);
        this.getTagNewz().add(tn);
    }
    public void removeTagNews(TagNews tn) {
        tn.setNews(null);
        this.getTagNewz().remove(tn);
    }
}
