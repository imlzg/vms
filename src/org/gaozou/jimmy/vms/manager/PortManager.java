package org.gaozou.jimmy.vms.manager;

import org.gaozou.jimmy.vms.domain.*;
import org.gaozou.jimmy.base.Helper;
import org.gaozou.kevin.utility.PropertiesUtil;
import org.gaozou.kevin.utility.ReflectUtil;
import org.gaozou.kevin.utility.StringUtil;

import java.util.List;
import java.util.Properties;
import java.lang.reflect.Field;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class PortManager extends Manager<Port> {
    private static final String[] port_kinds = new String[]{"heads", "foots", "about"};
    private static final String CONFIG_FILE = "config.properties";

    private MovieManager   movieDAO;
    private StarManager    starDAO;
    private NewsManager    newsDAO;
    private AlbumManager   albumDAO;
    private OptionsManager optionsDAO;
    private UserManager    userDAO;
    private AdManager      adDAO;


    public MovieManager getMovieDAO() {
        return movieDAO;
    }
    public void setMovieDAO(MovieManager movieDAO) {
        this.movieDAO = movieDAO;
    }

    public StarManager getStarDAO() {
        return starDAO;
    }
    public void setStarDAO(StarManager starDAO) {
        this.starDAO = starDAO;
    }

    public NewsManager getNewsDAO() {
        return newsDAO;
    }
    public void setNewsDAO(NewsManager newsDAO) {
        this.newsDAO = newsDAO;
    }

    public AlbumManager getAlbumDAO() {
        return albumDAO;
    }
    public void setAlbumDAO(AlbumManager albumDAO) {
        this.albumDAO = albumDAO;
    }

    public OptionsManager getOptionsDAO() {
        return optionsDAO;
    }
    public void setOptionsDAO(OptionsManager optionsDAO) {
        this.optionsDAO = optionsDAO;
    }

    public UserManager getUserDAO() {
        return userDAO;
    }
    public void setUserDAO(UserManager userDAO) {
        this.userDAO = userDAO;
    }

    public AdManager getAdDAO() {
        return adDAO;
    }
    public void setAdDAO(AdManager adDAO) {
        this.adDAO = adDAO;
    }




//    public List<Port> heads() {
//        return query("select p from Port p where p.open=true and p.kind='head'");
//    }
//    public List<Port> foots() {
//        return query("select p from Port p where p.open=true and p.kind='foot'");
//    }
//    public List<Port> abouts() {
//    }

    public List<Port> kinds(String kind) {
        return query("select p from Port p where p.open = true and p.kind = ?1", kind);
    }


    public List<Ad> getAds(String idc) {
        System.out.println("-- "+ adDAO);
        return adDAO.query(idc);
    }


//    public List<Movie> getRecMovies(Integer c) {
////        return movieDAO.recs(c);
//    }
    public List<Star> getRecStars(Integer c) {
        return starDAO.recs(c);
    }
    public List<News> getRecNews(Integer c) {
        return newsDAO.recs(c);
    }

    public List<Movie> getTopMovies(Integer c) {
        return movieDAO.tops(c);
    }
    public List<Star> getTopStars(Integer c) {
        return starDAO.tops(c);
    }
    public List<News> getTopNewz(Integer c) {
        return newsDAO.tops(c);
    }


    public void install(Options options) throws IllegalAccessException {
        log.info("Installing...");

        Properties prop  = PropertiesUtil.load(CONFIG_FILE);

        log.info("    Setting administrator...");
//        admin.setRoles(UserManager.ROLE_ADMIN);
        String ad = prop.getProperty("admin");
        if (StringUtil.isEmpty(ad)) return;
        String[] as = ad.split(",");
        User admin = userDAO.get(as[0]);
        if (Helper.isTransient(admin)) admin = new User();
        admin.setIdc(as[0]);
        admin.setPasswd(as[1]);
        admin.setName(as[2]);
        admin.setRoles(UserManager.ROLE_ADMIN);
        userDAO.save(admin);

        log.info("    Setting options...");
        List<Field> fs = ReflectUtil.getDeclaredFields(options.getClass());
        for (Field f : fs) {
            f.setAccessible(true);
            System.out.println("... "+ f.getName());
            f.set(options, prop.getProperty(f.getName()));
        }
        options.setStage(OptionsManager.STAGE_USE);
        optionsDAO.save(options);


        log.info("    Setting ports...");
        for (String pk : port_kinds) {
            String[] ps = prop.getProperty(pk, "").split(";");
            for (String p : ps) {
                String[] hs = p.split(",");
                Port port = new Port();
                port.setIdc(hs[0]);
                port.setName(hs[1]);
                port.setUrl(hs[2]);
                port.setSeq(Integer.valueOf(hs[3]));
                port.setContent(hs[4]);
                port.setOpen(true);
                save(port);
            }
        }

        log.info("Finishing...");
    }
}
