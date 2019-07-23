package org.gaozou.jimmy.vms.domain;

import org.gaozou.jimmy.base.IDCDomain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Author: george
 * Powered by GaoZou group.
 */
@Entity
@Table(name="PORT")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Port extends IDCDomain {
    private String  name;
    private String  url;
    private Integer seq;
    private Boolean open;
    private String  kind;
    private String  content;


    @Column(name="NAME")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name="URL")
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name="SEQ")
    public Integer getSeq() {
        return seq;
    }
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Column(name="OPEN")
    public Boolean getOpen() {
        return open;
    }
    public void setOpen(Boolean open) {
        this.open = open;
    }

    @Column(name="KIND")
    public String getKind() {
        return kind;
    }
    public void setKind(String kind) {
        this.kind = kind;
    }

    @Lob
    @Column(name="CONTENT")
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
