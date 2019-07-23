package org.gaozou.jimmy.vms.manager;

import org.gaozou.jimmy.vms.domain.Ad;

import java.util.List;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class AdManager extends Manager<Ad> {
    public List<Ad> query(String idc) {
        System.out.println(":: "+ idc);
        return query("select a from Ad a where a.open = true and a.idc = ?1 order by a.seq", idc);
    }
}
