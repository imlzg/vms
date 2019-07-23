package org.gaozou.jimmy.vms.action;

import org.gaozou.jimmy.base.Domain;
import org.gaozou.jimmy.base.Page;
import org.gaozou.jimmy.base.RichAction;
import org.gaozou.jimmy.base.Helper;
import org.gaozou.jimmy.vms.domain.User;
import org.gaozou.jimmy.vms.domain.Options;
import org.gaozou.jimmy.vms.domain.Port;
import org.gaozou.jimmy.vms.manager.UserManager;
import org.gaozou.jimmy.vms.manager.OptionsManager;
import org.gaozou.jimmy.vms.manager.PortManager;
import org.gaozou.kevin.utility.DESUtil;
import org.gaozou.kevin.utility.HTTPUtil;
import org.gaozou.kevin.utility.StringUtil;

import java.util.List;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class Action<T extends Domain> extends RichAction<T> {
    protected OptionsManager optionsDAO;
    protected PortManager    portDAO;
    protected UserManager    userDAO;

    protected Options options;
    protected User    oper;

    protected List<Port> heads;
    protected List<Port> foots;
    protected List<Port> abouts;


    protected Long    c;
    protected String  q;
    protected Page<T> page;
    protected Boolean open = Boolean.FALSE;


    public OptionsManager getOptionsDAO() {
        return optionsDAO;
    }
    public void setOptionsDAO(OptionsManager optionsDAO) {
        this.optionsDAO = optionsDAO;
    }

    public PortManager getPortDAO() {
        return portDAO;
    }
    public void setPortDAO(PortManager portDAO) {
        this.portDAO = portDAO;
    }

    public UserManager getUserDAO() {
        return userDAO;
    }
    public void setUserDAO(UserManager userDAO) {
        this.userDAO = userDAO;
    }



    /* */
    public Options getOptions() {
        return options;
    }
    public void setOptions(Options options) {
        this.options = options;
    }

    public User getOper() {
        return oper;
    }
    public void setOper(User oper) {
        this.oper = oper;
    }



    public List<Port> getHeads() {
        return heads;
    }
    public void setHeads(List<Port> heads) {
        this.heads = heads;
    }

    public List<Port> getFoots() {
        return foots;
    }
    public void setFoots(List<Port> foots) {
        this.foots = foots;
    }

    public List<Port> getAbouts() {
        return abouts;
    }
    public void setAbouts(List<Port> abouts) {
        this.abouts = abouts;
    }




    public Long getC() {
        return c;
    }
    public void setC(Long c) {
        this.c = c;
    }

    public String getQ() {
        return q;
    }
    public void setQ(String q) {
        this.q = q;
    }

    public Page<T> getPage() {
        return page;
    }
    public void setPage(Page<T> page) {
        this.page = page;
    }

    public Boolean isOpen() {
        return open;
    }
    public void setOpen(Boolean open) {
        this.open = open;
    }


    /* */
    public String[] getCookies() {
        String cookie = HTTPUtil.getCookieValue(getRequest(), options.getCookie());
        if (StringUtil.isEmpty(cookie)) return null;
        cookie = DESUtil.urlDecrypt(cookie);
        String[] values = cookie.split("\\"+ String.valueOf(StringUtil.BAR));
        if (null == values || values.length != 3) return null;
        return values;
    }

    public Long getUserId() {
        Object userId = getSession().getAttribute("userId");
        if (null != userId) {
            return (Long) userId;
        }

        String[] cookies = getCookies();
        if (null == cookies) return null;
        Long uid = Long.parseLong(cookies[0]);
        getSession().setAttribute("userId", uid);
        return uid;
    }

    public Boolean isAnonymous() {
//        return userDAO.isAnonymous(oper);
        return Helper.isTransient(oper);
    }
    public String[] roles() {
        if (Helper.isTransient(oper) || StringUtil.isEmpty(oper.getRoles())) return null;
        System.out.println("$$ "+oper.getRoles());
        return oper.getRoles().split(String.valueOf(StringUtil.COMMA));
    }


    public void prepare() throws Exception {
        super.prepare();
        options = optionsDAO.getDefault();
        if (! Helper.isTransient(options)) {
//            System.out.println(":: "+ options.getStage());
//            if (StringUtil.isEmpty(options.getStage()) || OptionsManager.STAGE_NEW.equalsIgnoreCase(options.getStage())) {
//                System.out.println("===");
//                getResponse().sendRedirect("/setup");
//            }
            DESUtil.setPassPhrase(options.getPhrase());
        }
        oper    = userDAO.getOper(getUserId());

        heads   = portDAO.kinds("head");
        foots   = portDAO.kinds("foot");
        abouts  = portDAO.kinds("about");

        doPrepare();
    }
    public void doPrepare() throws Exception {
        // for sub-classes to implement
    }

    public Boolean doAuthz(String action) {
        // for sub-classes to implement
        log.debug("do authz: {}", action);
        return false;
    }
}
