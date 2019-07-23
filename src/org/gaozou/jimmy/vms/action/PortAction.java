package org.gaozou.jimmy.vms.action;

import org.gaozou.jimmy.vms.domain.*;
import org.gaozou.jimmy.vms.manager.PortManager;
import org.gaozou.jimmy.vms.manager.OptionsManager;
import org.gaozou.jimmy.base.Helper;

import java.util.List;

import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class PortAction extends Action<Port> {
    private PortManager  portManager;
    private Port         port;
    private User         admin;

    private List<Port>   ports;
    private List<Ad>     ads;

    private List<Movie>  recMovies;
    private List<Star>   recStars;
    private List<News>   recNewz;

    private List<Movie>  topMovies;
    private List<Star>   topStars;
    private List<News>   topNewz;


    public PortManager getPortManager() {
        return portManager;
    }
    public void setPortManager(PortManager portManager) {
        this.portManager = portManager;
    }

    public Port getPort() {
        return port;
    }
    public void setPort(Port port) {
        this.port = port;
    }

    public User getAdmin() {
        return admin;
    }
    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public List<Port> getPorts() {
        return ports;
    }
    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }

    public List<Ad> getAds() {
        return ads;
    }
    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }


    public List<Movie> getRecMovies() {
        return recMovies;
    }
    public void setRecMovies(List<Movie> recMovies) {
        this.recMovies = recMovies;
    }

    public List<Star> getRecStars() {
        return recStars;
    }
    public void setRecStars(List<Star> recStars) {
        this.recStars = recStars;
    }

    public List<News> getRecNewz() {
        return recNewz;
    }
    public void setRecNewz(List<News> recNewz) {
        this.recNewz = recNewz;
    }

    public List<Movie> getTopMovies() {
        return topMovies;
    }
    public void setTopMovies(List<Movie> topMovies) {
        this.topMovies = topMovies;
    }

    public List<Star> getTopStars() {
        return topStars;
    }
    public void setTopStars(List<Star> topStars) {
        this.topStars = topStars;
    }

    public List<News> getTopNewz() {
        return topNewz;
    }
    public void setTopNewz(List<News> topNewz) {
        this.topNewz = topNewz;
    }




/////
    public void doPrepare() throws Exception {
        if (! Helper.isEmpty(port,  "id"))      port = portManager.get(port.getId());
        else if (! Helper.isEmpty(port, "idc")) port = portManager.get(port.getIdc());
    }


    public String home() {
        System.out.println("*** index ***");
//        recMovies  = portManager.getRecMovies(5);
/*        recStars   = portManager.getRecStars(5);
        recNewz    = portManager.getRecNews(5);

        topMovies  = portManager.getTopMovies(8);
        topStars   = portManager.getTopStars(8);*/

        ads = portManager.getAds("home");

        return SUCCESS;
    }

    public String people() {
        return SUCCESS;
    }
    public String tag() {
        return SUCCESS;
    }

/* */
    public String about() {
        ports = abouts;
        return SUCCESS;
    }





    /* */
    public String admin() {
        return SUCCESS;
    }

    public String index() {
        return SUCCESS;
    }


    public String manage() {
        ports = portManager.get();
        return SUCCESS;
    }

    @Validations(
            requiredStrings={
                    @RequiredStringValidator(fieldName="port.idc", message="You must enter a value for port idc", trim=true, shortCircuit=true),
                    @RequiredStringValidator(fieldName="port.name", message="You must enter a value for port name.", shortCircuit=true)
                    }
    )
    public String persist() {
        port.setOpen(open);
        portManager.save(port);
        return SUCCESS;
    }

    public String remove() {
        portManager.remove(port);
        return SUCCESS;
    }



    public String setup() {
        return SUCCESS;
    }
    public String install() throws IllegalAccessException {
        portManager.install(options);
        return SUCCESS;
    }


    // override to auth
    public Boolean doAuthz(String action) {
        if ("/manage/setup".equalsIgnoreCase(action) || "/manage/install".equalsIgnoreCase(action)) {
            if (null == options.getStage() || OptionsManager.STAGE_NEW.equalsIgnoreCase(options.getStage())) return true;
        }
        return false;
    }
}
