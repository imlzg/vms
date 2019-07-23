package org.gaozou.jimmy.vms.manager;

import org.gaozou.jimmy.base.Helper;
import org.gaozou.jimmy.base.Page;
import org.gaozou.jimmy.vms.domain.*;
import org.gaozou.jimmy.vms.searcher.MovieSearcher;
import org.gaozou.jimmy.vms.searcher.StarSearcher;
import org.gaozou.jimmy.vms.searcher.NewsSearcher;
import org.gaozou.kevin.utility.RegexUtil;
import org.gaozou.kevin.utility.StringUtil;
import org.gaozou.kevin.utility.URLUtil;

import java.io.Serializable;
import java.util.List;
import java.net.URL;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class StarManager extends Manager<Star> {
//    private CategoryManager categoryDAO;
    private MovieManager    movieDAO;
    private AdManager adDAO;
    private StarSearcher    starSearcher;
    private MovieSearcher   movieSearcher;
    private NewsSearcher    newsSearcher;

//    public CategoryManager getCategoryDAO() {
//        return categoryDAO;
//    }
//    public void setCategoryDAO(CategoryManager categoryDAO) {
//        this.categoryDAO = categoryDAO;
//    }



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

    public StarSearcher getStarSearcher() {
        return starSearcher;
    }
    public void setStarSearcher(StarSearcher starSearcher) {
        this.starSearcher = starSearcher;
    }

    public MovieSearcher getMovieSearcher() {
        return movieSearcher;
    }
    public void setMovieSearcher(MovieSearcher movieSearcher) {
        this.movieSearcher = movieSearcher;
    }

    public NewsSearcher getNewsSearcher() {
        return newsSearcher;
    }
    public void setNewsSearcher(NewsSearcher newsSearcher) {
        this.newsSearcher = newsSearcher;
    }



///
//    public List<Category> getCategories() {
//        return categoryDAO.get();
//    }
    public List<Ad> getAdverts(String idc) {
        return adDAO.query(idc);
    }
    public Movie getMovie(Serializable movieId) {
        return movieDAO.get(movieId);
    }



    public List<Movie> getMovies(Star star, Integer count) {
        StringBuffer k = new StringBuffer();
        k.append(presearch(star.getName())).append(" ");
//        k.append(presearch(star.getEnglish())).append(" ");
        k.append(presearch(star.getAka()));
        return movieSearcher.search(k.toString(), 0, count, new String[]{"cast", "writer", "director"});
    }
    public List<Movie> randomMovies(Integer count) {
        return movieDAO.getRandoms(count);
    }

    public List<News> getNews(Star star, Integer count) {
        StringBuffer k = new StringBuffer();
        k.append(presearch(star.getName())).append(" ");
//        k.append(presearch(star.getEnglish())).append(" ");
        k.append(presearch(star.getAka()));
        return newsSearcher.search(k.toString(), 0, count, new String[]{"title", "content"});
    }




    public Star getByQQid(String qqId) {
        List<Star> stars = query("select s from Star s where s.qqent = ?1", qqId);
        if (stars.size() == 0) return null;
        return stars.get(0);
    }

    public List<Star> tops(Integer count) {
        return query("select s from Star s order by s.hit desc", 0, count);
    }

    public List<Star> get(Integer count) {
        return query("select s from Star s order by s.editTime desc", 0, count);
    }

    public Page<Star> search(String k, Page<Star> page) {
        return starSearcher.search(k, page);
    }

    public Page<Star> page(Page<Star> page) {
        if (null == page) page = new Page<Star>(1l);
        page.setSize(25);
        return query(page, "select s from Star s");
    }




    public Star fetchQqent(User user, String qqid) {
        Star star = getByQQid(qqid);
        if (null != star) return null;   // This qqid exits.

//        URL indx = URLUtil.getURL(Helper.getOptions("qq.star.indx", "").replaceAll("\\{ID\\}", qqid));
        URL indx = URLUtil.getURL(options().getQqIndex().replaceAll("\\{ID\\}", qqid));
        if (! URLUtil.exists(indx)) return null;  // QQ ent has not this qqid.

        String c = URLUtil.getContent(indx, "GB2312");
        if (StringUtil.isEmpty(c) || c.indexOf("ent_pub_catch") == -1) return null;

        String name = RegexUtil.filtHTML(RegexUtil.get(c, "姓名：(.*?)</td>", 1)).replaceAll("\\s*[·|・]\\s*", "-");
        if (StringUtil.isEmpty(name)) return null;

        String english = RegexUtil.filtHTML(RegexUtil.get(c, "英文名：(.*?)</td>", 1));

        List<Star> s = starSearcher.search(presearch(name) + " " + presearch(english), 0, 1);
        if (s.size() > 0) return null;  // This star name exits.


        star = new Star();
        star.setUser(user);
        star.setAddTime(Helper.now());
        star.setEditTime(Helper.now());
        star.setQqent(qqid);

        star.setName(name);
//        star.setEnglish(english);
        star.setAutonym(RegexUtil.filtHTML(RegexUtil.get(c, "原名：(.*?)</td>", 1)));
        star.setNation(RegexUtil.filtHTML(RegexUtil.get(c, "国籍：(.*?)</td>", 1)));
        star.setBirthday(RegexUtil.filtHTML(RegexUtil.get(c, "出生年：(.*?)</td>", 1) + RegexUtil.get(c, "生日：(.*?)</td>", 1)));
        star.setAstro(RegexUtil.filtHTML(RegexUtil.get(c, "星座：(.*?)</td>", 1)));
        star.setBlood(RegexUtil.filtHTML(RegexUtil.get(c, "血型：(.*?)</td>", 1)));
        star.setHeight(RegexUtil.filtHTML(RegexUtil.get(c, "身高：(.*?)</td>", 1)));
//        star.setWeight(RegexUtil.filtHTML(RegexUtil.get(c, "体重：(.*?)</td>", 1)));
        star.setJob(RegexUtil.filtHTML(RegexUtil.get(c, "职业：(.*?)</td>", 1)));
//        star.setPhoto(storePhoto(user, star, "http://datalib.ent.qq.com" + RegexUtil.filtHTML(RegexUtil.get(c, "id=\"star_face\">.*?<img src=\"(.*?)\"", 1))).getUrl());

//        c = URLUtil.getContent(Helper.getOptions("qq.star.info", "").replaceAll("\\{ID\\}", qqid), "GB2312");
        c = URLUtil.getContent(options().getQqInfor().replaceAll("\\{ID\\}", qqid), "GB2312");

        star.setFigure(RegexUtil.filtHTML(RegexUtil.get(c, "三围：(.*?)</td>", 1)));
        star.setHobby(RegexUtil.filtHTML(RegexUtil.get(c, "嗜好：(.*?)</td>", 1)));
        star.setDescription(RegexUtil.filtHTML(RegexUtil.get(c, "个人简介(.*?)其它", 1).replaceAll("<br\\s*/?>", "\n") + RegexUtil.get(c, "其它(.*?)</table>", 1).replaceAll("<br\\s*/?>", "\n")).replaceAll("[\\f\\r\\t\\v ]{2,}", ""));

        return save(star);
    }



    public Star updateHit(Star star) {
        Integer h = star.getHit();
        if (null == h) h = 0;
        star.setHit(h + 1);
        return save(star);
    }



    public List<Star> recs(Integer c) {
        return query("select s from Star s where s.rec=true", 0, c);
    }




    public void index(Long begId, Long endId) {
        starSearcher.index("select s from Star s where s.id between ?1 and ?2", begId, endId);
    }



    public static String presearch(String kw) {
        if (StringUtil.isEmpty(kw)) return "";
        kw = kw.trim();
        kw = kw.replaceAll("\\s+", "-");
        kw = kw.replaceAll("-?[/|(|)|（|）]-?", " ");
        kw = kw.replaceAll("([\\w])-([^\\w])", RegexUtil.get(kw, "([\\w])-([^\\w])", 1) + " " + RegexUtil.get(kw, "([\\w])-([^\\w])", 2));
        kw = kw.replaceAll("([^\\w])-([\\w])", RegexUtil.get(kw, "([^\\w])-([\\w])", 1) + " " + RegexUtil.get(kw, "([^\\w])-([\\w])", 2));
        return kw;
    }
}
