package org.gaozou.jimmy.vms.domain;

import org.gaozou.jimmy.base.Domain;
import org.gaozou.jimmy.vms.domain.tag.TagMovie;
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
@Table(name="MOVIE")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Indexed
public class Movie extends Domain {
    private String title;
    private String aka;

    private String cast;
    private String writer;
    private String director;
    private String lang;
    private String date;
    private String size;
    private String site;
    private String poster;
    private String company;
    private String country;
    private String summary;

    private String imdb;
    private String douban;

    private List<TagMovie> tagMovies;


    @Column(name="TITLE", nullable=false)
    @Fields({@Field(index=Index.TOKENIZED, store=Store.YES),
             @Field(index=Index.UN_TOKENIZED, store=Store.YES, name="title_sort")})
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Lob
    @Column(name="AKA")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getAka() {
        return aka;
    }
    public void setAka(String aka) {
        this.aka = aka;
    }

    @Lob
    @Column(name="CAST")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getCast() {
        return cast;
    }
    public void setCast(String cast) {
        this.cast = cast;
    }

    @Lob
    @Column(name="WRITER")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getWriter() {
        return writer;
    }
    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Lob
    @Column(name="DIRECTOR")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }

    @Column(name="LANG")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getLang() {
        return lang;
    }
    public void setLang(String lang) {
        this.lang = lang;
    }

    @Column(name="DATE")
    @Field(index=Index.UN_TOKENIZED, store=Store.YES)
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    @Column(name="SIZE")
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }

    @Column(name="SITE")
    public String getSite() {
        return site;
    }
    public void setSite(String site) {
        this.site = site;
    }

    @Column(name="POSTER")
    public String getPoster() {
        return poster;
    }
    public void setPoster(String poster) {
        this.poster = poster;
    }


    @Column(name="COUNTRY")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name="COMPANY")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }

    @Lob
    @Column(name="SUMMARY")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Column(name="IMDB")
    public String getImdb() {
        return imdb;
    }
    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    @Column(name="DOUBAN")
    public String getDouban() {
        return douban;
    }
    public void setDouban(String douban) {
        this.douban = douban;
    }

    @OneToMany(mappedBy="movie", cascade=CascadeType.REMOVE)
    @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Field(index=Index.TOKENIZED, store=Store.NO, termVector=TermVector.YES,
            analyzer=@Analyzer(impl=org.gaozou.jimmy.base.support.lucene.CommaAnalyzer.class),
            bridge=@FieldBridge(impl=org.gaozou.jimmy.vms.support.hsearch.TagMovieBridge.class))
    public List<TagMovie> getTagMovies() {
        return null == tagMovies ? tagMovies = new ArrayList<TagMovie>() : tagMovies;
    }
    public void setTagMovies(List<TagMovie> tagMovies) {
        this.tagMovies = tagMovies;
    }
    public Boolean containsTagMovie(TagMovie tm) {
        return this.getTagMovies().contains(tm);
    }
    public void addTagMovie(TagMovie tm) {
        tm.setMovie(this);
        this.getTagMovies().add(tm);
    }
    public void removeTagMovie(TagMovie tm) {
        tm.setMovie(null);
        this.getTagMovies().remove(tm);
    }
}
