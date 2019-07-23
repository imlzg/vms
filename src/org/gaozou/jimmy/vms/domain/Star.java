package org.gaozou.jimmy.vms.domain;

import org.gaozou.jimmy.base.Domain;
import org.gaozou.jimmy.vms.domain.tag.TagStar;
import org.gaozou.jimmy.vms.domain.tag.TagAlbum;
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
@Table(name="STAR")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Indexed
public class Star extends Domain {
    private String name;
    private String autonym;
    private String nick;
    private String aka;
    private String birthday;
    private String birthplace;
    private String deadday;
    private String deadplace;
    private String address;
    private String nation;
    private String nativeplace;
    private String zodiac;
    private String astro;
    private String blood;
    private String height;
    private String weight;
    private String figure;
    private String education;
    private String school;
    private String major;
    private String job;
    private String hobby;
    private String represent;
    private String family;

    private String photo;
    private String description;

    private String qqent;

    private Long addTime;
    private Long editTime;

    private Double  hot;
    private Integer hit;


    private User user;

    private List<TagStar> tagStars;


    @Column(name="NAME")
    @Fields({@Field(index=Index.TOKENIZED, store=Store.YES),
             @Field(index=Index.UN_TOKENIZED, store=Store.YES, name="name_sort")})
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name="AUTONYM")
    @Field(index=Index.TOKENIZED, store=Store.YES)
    public String getAutonym() {
        return autonym;
    }
    public void setAutonym(String autonym) {
        this.autonym = autonym;
    }

    @Column(name="NICK")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getNick() {
        return nick;
    }
    public void setNick(String nick) {
        this.nick = nick;
    }

    @Column(name="ALIAS")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getAka() {
        return aka;
    }
    public void setAka(String aka) {
        this.aka = aka;
    }

    @Column(name="BIRTHDAY")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Column(name="BIRTHPLACE")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getBirthplace() {
        return birthplace;
    }
    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    @Column(name="DEADDAY")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getDeadday() {
        return deadday;
    }
    public void setDeadday(String deadday) {
        this.deadday = deadday;
    }

    @Column(name="DEADPLACE")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getDeadplace() {
        return deadplace;
    }
    public void setDeadplace(String deadplace) {
        this.deadplace = deadplace;
    }

    @Column(name="ADDRESS")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name="NATION")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getNation() {
        return nation;
    }
    public void setNation(String nation) {
        this.nation = nation;
    }

    @Column(name="NATIVEPLACE")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getNativeplace() {
        return nativeplace;
    }
    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace;
    }

    @Column(name="ZODIAC")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getZodiac() {
        return zodiac;
    }
    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }

    @Column(name="ASTRO")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getAstro() {
        return astro;
    }
    public void setAstro(String astro) {
        this.astro = astro;
    }

    @Column(name="BLOOD")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getBlood() {
        return blood;
    }
    public void setBlood(String blood) {
        this.blood = blood;
    }

    @Column(name="HEIGHT")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getHeight() {
        return height;
    }
    public void setHeight(String height) {
        this.height = height;
    }

    @Column(name="WEIGHT")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Column(name="FIGURE")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getFigure() {
        return figure;
    }
    public void setFigure(String figure) {
        this.figure = figure;
    }

    @Column(name="EDUCATION")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getEducation() {
        return education;
    }
    public void setEducation(String education) {
        this.education = education;
    }

    @Column(name="SCHOOL")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }

    @Column(name="MAJOR")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }

    @Column(name="JOB")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }

    @Column(name="HOBBY")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getHobby() {
        return hobby;
    }
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Column(name="REPRESENT")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getRepresent() {
        return represent;
    }
    public void setRepresent(String represent) {
        this.represent = represent;
    }

    @Column(name="FAMILY")
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getFamily() {
        return family;
    }
    public void setFamily(String family) {
        this.family = family;
    }

    @Column(name="PHOTO")
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }


    @Lob
    @Column(name="SUMMARY")
    @Field(index=Index.TOKENIZED, store= Store.NO)
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    @Column(name="QQENT")
    public String getQqent() {
        return qqent;
    }
    public void setQqent(String qqent) {
        this.qqent = qqent;
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


    @Column(name="HOT")
    @Field(index=Index.UN_TOKENIZED, store=Store.YES)
    public Double getHot() {
        return hot;
    }
    public void setHot(Double hot) {
        this.hot = hot;
    }

    @Column(name="HIT")
    @Field(index=Index.UN_TOKENIZED, store=Store.YES)
    public Integer getHit() {
        return hit;
    }
    public void setHit(Integer hit) {
        this.hit = hit;
    }

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="USER", nullable=false)
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy="star", cascade=CascadeType.REMOVE)
    @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Field(index= Index.TOKENIZED, store= Store.NO, termVector= TermVector.YES,
            analyzer=@Analyzer(impl=org.gaozou.jimmy.base.support.lucene.CommaAnalyzer.class),
            bridge=@FieldBridge(impl=org.gaozou.jimmy.vms.support.hsearch.TagStarBridge.class))
    public List<TagStar> getTagStars() {
        return tagStars;
    }
    public void setTagStars(List<TagStar> tagStars) {
        this.tagStars = tagStars;
    }
    public Boolean containsTagStar(TagStar t) {
        return this.getTagStars().contains(t);
    }
    public void addTagStar(TagStar t) {
        t.setStar(this);
        this.getTagStars().add(t);
    }
    public void removeTagStar(TagStar t) {
        t.setStar(null);
        this.getTagStars().remove(t);
    }
}
