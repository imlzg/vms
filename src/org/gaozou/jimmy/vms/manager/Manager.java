package org.gaozou.jimmy.vms.manager;

import org.gaozou.jimmy.base.Domain;
import org.gaozou.jimmy.base.Helper;
import org.gaozou.jimmy.vms.domain.Options;
import org.gaozou.jimmy.vms.domain.User;
import org.gaozou.kevin.utility.NumberUtil;

import java.util.List;
import java.io.Serializable;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class Manager<T extends Domain> extends org.gaozou.jimmy.base.Manager<T> {
    private Options options;

    public Options options() {
        if (null == options) {
            options = (Options) singleQuery("select o from Options as o where o.idc = ?1", OptionsManager.DEFAULT);
        }
        return options;
    }

    public User oper(Serializable userId) {
        return (User) singleQuery("select o from User as o where o.id = ?1", userId);
    }
}
