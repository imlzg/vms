package org.gaozou.jimmy.vms.manager;

import org.gaozou.jimmy.base.Helper;
import org.gaozou.jimmy.base.Page;
import org.gaozou.jimmy.vms.domain.User;
import org.gaozou.kevin.rbac.Authorization;
import org.gaozou.kevin.utility.DESUtil;
import org.gaozou.kevin.utility.NumberUtil;
import org.gaozou.kevin.utility.StringUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class UserManager extends Manager<User> {
    public static final String ANONYMOUS  = "anonymous";
    public static final String ROLE_ADMIN = "ADMIN";


    public Set<String> getRoleSet() {
        return Authorization.getAuthzRoles();
    }

//    public Boolean isAnonymous(User user) {
////        return ANONYMOUS.equalsIgnoreCase(user.getName());
//    }
//    public User getAnonymous() {
//        User anonymous = get(ANONYMOUS);
//        if (Helper.isTransient(anonymous)) {
//            anonymous = new User();
//            anonymous.setIdc(ANONYMOUS + "@" + options().getDomain());
//            anonymous.setName(ANONYMOUS);
//            anonymous.setIntro("This is an anonymous count.");
//            super.save(anonymous);
//        }
//        return anonymous;
//    }

    public User getOper(Serializable userId) {
        return NumberUtil.isEmpty(userId) ? null : super.get(userId);
    }
    public User get(String name) {
        List<User> users = query("select u from User u where u.name = ?1", StringUtil.urlDecode(name));
        if (users.size() == 0) return null;
        return users.get(0);
    }

    public void register(User user) {
        user.setRoles("user");
        save(user);
    }
    public User login(User user) {
//        if (ANONYMOUS.equalsIgnoreCase(user.getName())) return null;
        List<User> users = query("select u from User u where u.idc = ?1 and u.passwd = ?2", user.getIdc(), user.getPasswd());
        if (null == users || users.size() != 1) {
            return null;
        }
        return users.get(0);
    }
    public String genCookie(User user) {
        if (null == user || StringUtil.isEmpty(user.getRoles())) {
            return null;
        }
        String cookie = user.getId() + String.valueOf(StringUtil.BAR) + user.getIdc() + String.valueOf(StringUtil.BAR) +  user.getRoles();
        return DESUtil.urlEncrypt(cookie);
    }



    public Boolean isEmailUsed(String email) {
        List<User> users = query("select u from User u where u.idc = ?1", email);
        return null != users && users.size() > 0;
    }
    public Boolean isNameUsed(String name) {
//        if (ANONYMOUS.equalsIgnoreCase(name)) return true;
        List<User> users = query("select u from User u where u.name = ?1", StringUtil.urlDecode(name));
        return null != users && users.size() > 0;
    }

    public Page<User> page(Page<User> page) {
        if (null == page) page = new Page<User>(1l);
        return query(page, "select u from User u order by u.id, u.name");
    }
}
