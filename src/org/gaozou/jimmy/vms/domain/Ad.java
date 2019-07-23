package org.gaozou.jimmy.vms.domain;

import org.gaozou.jimmy.base.IDCDomain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Author: george
 * Powered by GaoZou group.
 */
@Entity
@Table(name="AD")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Ad extends IDCDomain {
    private String  name;
    private Integer seq;
    private Boolean open;
    private String  code;


    @Column(name="NAME")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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

    @Lob
    @Column(name="CODE")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}
