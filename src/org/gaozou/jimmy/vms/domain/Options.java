package org.gaozou.jimmy.vms.domain;

import org.gaozou.jimmy.base.IDCDomain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

/**
 * Author: george
 * Powered by GaoZou group.
 */
@Entity
@Table(name="OPTIONS")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Options extends IDCDomain {
    private String name;
    private String domain;
    private String miibei;
    private String period;

    private String url;
    private String manage;

    private String cookie;
    private String phrase;


    private String picUrl;
    private String picDir;


    private String doubanApi;
    private String doubanKey;
    private String doubanSearch;

    private String qqIndex;
    private String qqInfor;
    private String qqPics;
    private String qqPict;

    private String stage;


    @Column(name="NAME"/*, nullable=false*/)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name="DOMAIN"/*, nullable=false*/)
    public String getDomain() {
        return domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Column(name="MIIBEI")
    public String getMiibei() {
        return miibei;
    }
    public void setMiibei(String miibei) {
        this.miibei = miibei;
    }

    @Column(name="PERIOD")
    public String getPeriod() {
        return period;
    }
    public void setPeriod(String period) {
        this.period = period;
    }

    @Column(name="URL")
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name="MANAGE")
    public String getManage() {
        return manage;
    }
    public void setManage(String manage) {
        this.manage = manage;
    }

    @Column(name="COOKIE")
    public String getCookie() {
        return cookie;
    }
    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    @Column(name="PHRASE")
    public String getPhrase() {
        return phrase;
    }
    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    @Column(name="PICURL")
    public String getPicUrl() {
        return picUrl;
    }
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Column(name="PICDIR")
    public String getPicDir() {
        return picDir;
    }
    public void setPicDir(String picDir) {
        this.picDir = picDir;
    }



    @Column(name="DOUBANAPI")
    public String getDoubanApi() {
        return doubanApi;
    }
    public void setDoubanApi(String doubanApi) {
        this.doubanApi = doubanApi;
    }

    @Column(name="DOUBANKEY")
    public String getDoubanKey() {
        return doubanKey;
    }
    public void setDoubanKey(String doubanKey) {
        this.doubanKey = doubanKey;
    }

    @Column(name="DOUBANSEARCH")
    public String getDoubanSearch() {
        return doubanSearch;
    }
    public void setDoubanSearch(String doubanSearch) {
        this.doubanSearch = doubanSearch;
    }

    @Column(name="QQINDEX")
    public String getQqIndex() {
        return qqIndex;
    }
    public void setQqIndex(String qqIndex) {
        this.qqIndex = qqIndex;
    }

    @Column(name="QQINFOR")
    public String getQqInfor() {
        return qqInfor;
    }
    public void setQqInfor(String qqInfor) {
        this.qqInfor = qqInfor;
    }

    @Column(name="QQPICS")
    public String getQqPics() {
        return qqPics;
    }
    public void setQqPics(String qqPics) {
        this.qqPics = qqPics;
    }

    @Column(name="QQPICT")
    public String getQqPict() {
        return qqPict;
    }
    public void setQqPict(String qqPict) {
        this.qqPict = qqPict;
    }

    public String getStage() {
        return stage;
    }
    public void setStage(String stage) {
        this.stage = stage;
    }
}
