package org.gaozou.jimmy.vms.manager;

import org.gaozou.jimmy.base.Page;
import org.gaozou.jimmy.vms.domain.*;
import org.gaozou.kevin.utility.*;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class PicManager extends Manager<Pic> {
    private MovieManager    movieDAO;
    private AdManager adDAO;
//    private TopicManager    topicDAO;



    public MovieManager getMovieDAO() {
        return movieDAO;
    }
    public void setMovieDAO(MovieManager movieDAO) {
        this.movieDAO = movieDAO;
    }


    public AdManager getAdvertDAO() {
        return adDAO;
    }
    public void setAdvertDAO(AdManager adDAO) {
        this.adDAO = adDAO;
    }
//
//    public TopicManager getTopicDAO() {
//        return topicDAO;
//    }
//    public void setTopicDAO(TopicManager topicDAO) {
//        this.topicDAO = topicDAO;
//    }



    public Movie getMovie(Serializable movieId) {
        return movieDAO.get(movieId);
    }
    public List<Ad> getAdverts(String idc) {
        return adDAO.query(idc);
    }

    public List<Movie> randomMovies(Integer count) {
        return movieDAO.getRandoms(count);
    }


    public List<Pic> get(Movie movie) {
        return query("select p from Pict p where p.movie = ?1 order by p.editTime desc", movie);
    }
    public List<Pic> get(Integer count) {
        return query("select p from Pict p order by p.editTime desc", 0, count);
    }
    public List<Pic> get(Movie movie, Integer count) {
        return query("select p from Pict p where p.movie = ?1 order by p.editTime desc", 0, count, movie);
    }

    public Page<Pic> page(Page<Pic> page) {
        if (null == page) page = new Page<Pic>(1l);
        page.setSize(16);
        return query(page, "select p from Pict p order by p.editTime desc");
    }
    public Page<Pic> page(Page<Pic> page, Movie movie) {
        if (null == page) page = new Page<Pic>(1l);
        page.setSize(16);
        return query(page, "select p from Pict p where p.movie = ?1 order by p.editTime desc", movie);
    }



//    public Pic create(User user, Movie movie) {
//        Pic pict = new Pic();
//        pict.setAddTime(Helper.now());
//        pict.setEditTime(Helper.now());
//        pict.setUser(user);
//
//        movie.addPict(pict);
//
//        return save(pict);
//    }
//    public Pic store(User user, Movie movie, String url) {
//        if (! URLUtil.exists(url)) return null;
//        Pic pict = create(user, movie);
//        upload(pict, url);
//        return pict;
//    }
//    public Pic store(User user, Movie movie, File file) {
//        Pic pict = create(user, movie);
//        upload(pict, file);
//        return pict;
//    }

/*    public Pic upload(Pic pict, String url) {
        String picDir  = StringUtil.append(options().getPicDir(), "/");
//        String picName = pict.getMovie().getId() + "_" + pict.getId() + ".jpg";

        String opic = picDir + "/opic/" + picName;
        if (CopyUtil.copy(url, opic) != -1) {
            ImageUtil.thumbnail(opic, picDir + "/lpic/" + picName, 480);
            ImageUtil.thumbnail(opic, picDir + "/mpic/" + picName, 99);
            ImageUtil.thumbnail(opic, picDir + "/spic/" + picName, 66);

            pict.setUrl(picName);
            super.save(pict);
        }

        return pict;
    }
    public Pic upload(Pic pict, File file) {
        String picDir  = StringUtil.append(options().getPicDir(), "/");
        String picName = pict.getMovie().getId() + "_" + pict.getId() + ".jpg";

        String opic = picDir + "/opic/" + picName;
        if (CopyUtil.copy(file, opic) != -1) {
            ImageUtil.thumbnail(opic, picDir + "/lpic/" + picName, 480);
            ImageUtil.thumbnail(opic, picDir + "/mpic/" + picName, 99);
            ImageUtil.thumbnail(opic, picDir + "/spic/" + picName, 66);
        }
        pict.setUrl(picName);
        return save(pict);
    }

    public Pic upload(User user, Movie movie, String url) {
        Pic pict = create(user, movie);

        String picDir  = StringUtil.append(options().getPicDir(), "/");
        String picName = pict.getMovie().getId() + "_" + pict.getId() + ".jpg";

        String opic = picDir + "/opic/" + picName;
        if (CopyUtil.copy(url, opic) != -1) {
            ImageUtil.thumbnail(opic, picDir + "/lpic/" + picName, 480);
            ImageUtil.thumbnail(opic, picDir + "/mpic/" + picName, 99);
            ImageUtil.thumbnail(opic, picDir + "/spic/" + picName, 66);
        }
        pict.setUrl(picName);
        return save(pict);
    }



    public void remove(Pic pict) {
        pict.getMovie().removePict(pict);
        removePic(pict);
        super.remove(pict);
    }*/


    public void removePic(Pic pict) {
        String picDir  = StringUtil.append(options().getPicDir(), "/");

        String[] dirs = new String[]{"/opic/", "/lpic/", "/mpic/", "/spic/"};
        for (String d : dirs) {
            File f  = ResourceUtil.getFile(picDir + d + pict.getUrl());
            if (f.exists()) f.delete();
        }
    }

}
