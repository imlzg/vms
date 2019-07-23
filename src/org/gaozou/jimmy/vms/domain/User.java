package org.gaozou.jimmy.vms.domain;

import org.gaozou.jimmy.base.IDCDomain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author: george
 * Powered by GaoZou group.
 */
@Entity
@Table(name="USER")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Indexed
public class User extends IDCDomain {
    private String name;
    private String icon;
    private String intro;
    private String passwd;
    private String roles;
    private Long   regTime;


    @Column(name="NAME", unique=true, nullable=false)
    @Fields({@Field(index=Index.TOKENIZED, store=Store.YES),
             @Field(index=Index.UN_TOKENIZED, store=Store.YES, name="name_sort")})
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name="ICON")
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Column(name="INTRO")
    public String getIntro() {
        return intro;
    }
    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Column(name="PASSWD")
    public String getPasswd() {
        return passwd;
    }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Column(name="ROLES")
    public String getRoles() {
        return roles;
    }
    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Column(name="REGTIME")
    public Long getRegTime() {
        return regTime;
    }
    public void setRegTime(Long rTime) {
        this.regTime = rTime;
    }
}
