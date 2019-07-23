package org.gaozou.jimmy.vms.manager;

import org.gaozou.jimmy.base.Page;
import org.gaozou.jimmy.vms.domain.*;
import org.gaozou.jimmy.vms.domain.Tag;
import org.gaozou.jimmy.vms.searcher.MovieSearcher;
import org.gaozou.jimmy.vms.searcher.StarSearcher;
import org.gaozou.kevin.utility.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class MovieManager extends Manager<Movie> {
    private MovieSearcher   movieSearcher;
    private StarSearcher    starSearcher;
/*
    private PhotoSearcher   photoSearcher;
//    private CategoryManager categoryDAO;
//    private GenusManager    genusDAO;
//    private RegionManager   regionDAO;


//    private IntentManager intentDAO;

    private UserManager     userDAO;
    private CollectManager  collectDAO;
    private RatingManager   ratingDAO;
    private PlotManager     plotDAO;
    private PicManager picDAO;
    private SourceManager   sourceDAO;
    private TagManager      tagDAO;
    private AdManager   advertDAO;
    private TopicManager    topicDAO;
    private NewsManager     newsDAO;
    private StarManager     starDAO;
    private PhotoManager    photoDAO;


    private Integer days = 7 * 2;


    public String getUrl() {
        return "http://" + (options().getDomain().indexOf("www.") == -1 ? "www." : "") + options().getDomain() + "/";
    }








    public MovieSearcher getMovieSearcher() {
        return movieSearcher;
    }
    public void setMovieSearcher(MovieSearcher movieSearcher) {
        this.movieSearcher = movieSearcher;
    }

    public StarSearcher getStarSearcher() {
        return starSearcher;
    }
    public void setStarSearcher(StarSearcher starSearcher) {
        this.starSearcher = starSearcher;
    }

    public PhotoSearcher getPhotoSearcher() {
        return photoSearcher;
    }
    public void setPhotoSearcher(PhotoSearcher photoSearcher) {
        this.photoSearcher = photoSearcher;
    }
*/

//    public IntentManager getIntentDAO() {
//        return intentDAO;
//    }
//    public void setIntentDAO(IntentManager intentDAO) {
//        this.intentDAO = intentDAO;
//    }


//    public CategoryManager getCategoryDAO() {
//        return categoryDAO;
//    }
//    public void setCategoryDAO(CategoryManager categoryDAO) {
//        this.categoryDAO = categoryDAO;
//    }
//
//    public GenusManager getGenusDAO() {
//        return genusDAO;
//    }
//    public void setGenusDAO(GenusManager genusDAO) {
//        this.genusDAO = genusDAO;
//    }
//
//    public RegionManager getRegionDAO() {
//        return regionDAO;
//    }
//    public void setRegionDAO(RegionManager regionDAO) {
//        this.regionDAO = regionDAO;
//    }

//    public UserManager getUserDAO() {
//        return userDAO;
//    }
//    public void setUserDAO(UserManager userDAO) {
//        this.userDAO = userDAO;
//    }
//
//    public CollectManager getCollectDAO() {
//        return collectDAO;
//    }
//    public void setCollectDAO(CollectManager collectDAO) {
//        this.collectDAO = collectDAO;
//    }
//
//    public RatingManager getRatingDAO() {
//        return ratingDAO;
//    }
//    public void setRatingDAO(RatingManager ratingDAO) {
//        this.ratingDAO = ratingDAO;
//    }
//
//
//    public PlotManager getPlotDAO() {
//        return plotDAO;
//    }
//    public void setPlotDAO(PlotManager plotDAO) {
//        this.plotDAO = plotDAO;
//    }
//
//    public PicManager getPictDAO() {
//        return picDAO;
//    }
//    public void setPictDAO(PicManager picDAO) {
//        this.picDAO = picDAO;
//    }
//
//    public SourceManager getSourceDAO() {
//        return sourceDAO;
//    }
//    public void setSourceDAO(SourceManager sourceDAO) {
//        this.sourceDAO = sourceDAO;
//    }
//
//    public TagManager getTagDAO() {
//        return tagDAO;
//    }
//    public void setTagDAO(TagManager tagDAO) {
//        this.tagDAO = tagDAO;
//    }
//
//    public AdManager getAdvertDAO() {
//        return advertDAO;
//    }
//    public void setAdvertDAO(AdManager advertDAO) {
//        this.advertDAO = advertDAO;
//    }
//
//    public TopicManager getTopicDAO() {
//        return topicDAO;
//    }
//    public void setTopicDAO(TopicManager topicDAO) {
//        this.topicDAO = topicDAO;
//    }
//
//
//    public NewsManager getNewsDAO() {
//        return newsDAO;
//    }
//    public void setNewsDAO(NewsManager newsDAO) {
//        this.newsDAO = newsDAO;
//    }
//
//    public StarManager getStarDAO() {
//        return starDAO;
//    }
//    public void setStarDAO(StarManager starDAO) {
//        this.starDAO = starDAO;
//    }
//
//    public PhotoManager getPhotoDAO() {
//        return photoDAO;
//    }
//    public void setPhotoDAO(PhotoManager photoDAO) {
//        this.photoDAO = photoDAO;
//    }

//    public PortManager getPortDAO() {
//        return portDAO;
//    }
//    public void setPortDAO(PortManager portDAO) {
//        this.portDAO = portDAO;
//    }





    ///
//    public User getUser(Serializable userId) {
//        return userDAO.get(userId);
//    }
//    public User getUser(String userIdc) {
//        return userDAO.get(userIdc);
//    }
//    public List<User> getUsers() {
//        return userDAO.get();
//    }

//    public Genus getGenus(Serializable genusId) {
//        return genusDAO.get(genusId);
//    }
//    public Genus getGenus(String genusIdc) {
//        return genusDAO.get(genusIdc);
//    }
//    public List<Genus> getGenera() {
//        return genusDAO.get();
//    }
//
//    public Region getRegion(Serializable regionId) {
//        return regionDAO.get(regionId);
//    }
//    public Region getRegion(String regionIdc) {
//        return regionDAO.get(regionIdc);
//    }
//    public List<Region> getRegions() {
//        return regionDAO.get();
//    }
//
//    public Category getCategory(Serializable categoryId) {
//        return categoryDAO.get(categoryId);
//    }
//    public Category getCategory(String categoryIdc) {
//        return categoryDAO.get(categoryIdc);
//    }
//    public List<Category> getCategories() {
//        return categoryDAO.get();
//    }


//    public List<Collect> getCollects(Integer count) {
//        return collectDAO.getRandoms(count);
//    }
//    public List<Collect> getCollects(Movie movie, Integer count) {
//        return collectDAO.getRandoms(movie, count);
//    }
//    public List<Collect> getCollects(User user, Integer count) {
//        return collectDAO.get(user, count);
//    }
//
//    public List<Plot> getPlots(Integer count) {
//        return plotDAO.get(count);
//    }
//    public List<Plot> getPlots(Movie movie, Integer count) {
//        return plotDAO.get(movie, count);
//    }

//    public Pic getPict(Serializable pictId) {
//        return picDAO.get(pictId);
//    }
//    public List<Pic> getPicts(Integer count) {
//        return picDAO.get(count);
//    }
//    public List<Pic> getPicts(Movie movie, Integer count) {
//        return picDAO.get(movie, count);
//    }


//    public Intent getIntent(Serializable intentId) {
//        return intentDAO.get(intentId);
//    }
//    public Intent getIntent(User user, Movie movie) {
//        return intentDAO.get(user, movie);
//    }


//
//    public Tag getTag(Serializable tagId) {
//        return tagDAO.get(tagId);
//    }
//    public Tag getTag(String tagName) {
//        return tagDAO.get(tagName);
//    }
//
//    public List<String> getTags(Integer count) {
//        return tagDAO.get(count);
//    }
//    public List<String> getTags(Movie movie, Integer count) {
//        return tagDAO.get(movie, count);
//    }
//    public List<Tag> getTags(User user, Integer count) {
//        return tagDAO.get(user, count);
//    }

//    public Rating getRating(User user, Movie movie) {
//        return ratingDAO.get(user, movie);
//    }
//
//    public Collect getCollect(User user, Movie movie) {
//        return collectDAO.get(user, movie);
//    }
//
//
//
//
//    public List<Ad> getAds(String idc) {
//        return advertDAO.query(idc);
//    }
//
//
//    public List<Source> getSources(Integer count) {
//        return sourceDAO.get(count);
//    }
//    public List<Source> getSources(Movie movie, Integer count) {
//        return sourceDAO.get(movie, count);
//    }
//
//
//    public List<Topic> getTopics(Integer count) {
//        return topicDAO.get(count);
//    }
//    public List<Topic> getTopics(Movie movie, Integer count) {
//        return topicDAO.get(movie, count);
//    }


//    public List<News> getNews(Integer count) {
//        return newsDAO.get(count);
//    }
//
//    public List<Star> getTopStars(Integer count) {
//        return starDAO.tops(count);
//    }
//    public List<Star> getStars(Integer count) {
//        return starDAO.get(count);
//    }
    public List<Star> getStars(Movie movie, Integer count) {
        StringBuffer k = new StringBuffer();
        k.append(StarManager.presearch(movie.getCast())).append(" ");
        k.append(StarManager.presearch(movie.getDirector())).append(" ");
        k.append(StarManager.presearch(movie.getWriter()));
        return starSearcher.search(k.toString(), 0, count);
    }

//    public List<Photo> getPhotos(Integer count) {
//        return photoDAO.get(count);
//    }
//    public List<Photo> getPhotos(Movie movie, Integer count) {
//        StringBuffer k = new StringBuffer();
//        k.append(StarManager.presearch(movie.getCast())).append(" ");
//        k.append(StarManager.presearch(movie.getDirector())).append(" ");
//        k.append(StarManager.presearch(movie.getWriter()));
//        return photoSearcher.search(k.toString(), 0, count);
//    }


    public Page<Movie> search(String q, Page<Movie> page) {
        return movieSearcher.search(q, page);
    }
    public Page<Movie> search(String q, Page<Movie> page, String[] fields) {
        return movieSearcher.search(q, page,fields);
    }


//    public List<Movie> get(Category category) {
//        return query("select m from Movie m where m.category = ?1", category);
//    }


    public List<Movie> getByTagName(String tagName) {
        return query("select distinct(tum.movie) from TagUserMovie tum where tum.tag.name = ?1", tagName);
    }

    public List<Movie> getByTag(Tag tag) {
        return query("select distinct(tum.movie) from TagUserMovie tum where tum.tag = ?1 group by tum.movie", tag);
    }


//    public List<Tag> getTags(User user, Movie movie) {
//        return tagDAO.get(user, movie);
//    }
//    public List<Tag> getMyTags(User user, Integer count) {
//        return tagDAO.get(user, count);
//    }
//
//
//    public Tag updateTagHit(Tag tag) {
//        return tagDAO.updateHit(tag);
//    }


/*

    public List<Object[]> getHotTags(Integer count) {
        return tagDAO.getHot(count);
    }
    public List<Object[]> getHotTags(Entry entry, Integer count) {
        return tagDAO.getHot(entry, count);
    }
    public List<Object[]> getHotTags(Genus genus, Integer count) {
        return tagDAO.getHot(genus, count);
    }
    public List<Object[]> getHotTags(Region region, Integer count) {
        return tagDAO.getHot(region, count);
    }
    public List<Object[]> getHotTags(Category category, Integer count) {
        return tagDAO.getHot(category, count);
    }

*/

//    public List<Tag> getHotTags(Integer count) {
//        return tagDAO.getHot(count);
//    }
//    public List<Tag> getHotTags(Movie movie, Integer count) {
//        return tagDAO.getHot(movie, count);
//    }


    public Page<Movie> page(Page<Movie> page) {
        if (null == page) page = new Page<Movie>(1l);
        return query(page, "select m from Movie m order by m.id desc");
    }
//    public Page<Movie> page(Page<Movie> page, Category category) {
//        if (null == page) page = new Page<Movie>(1l);
//        return query(page, "select m from Movie m where e.category = ?1 order by m.id", category);
//    }

    public Page<Movie> pageHot(Page<Movie> page) {
        if (null == page) page = new Page<Movie>(1l);
        return query(page, "select m from Movie m order by m.hot desc");
    }
//    public Page<Movie> pageHot(Page<Movie> page, Category category) {
//        if (null == page) page = new Page<Movie>(1l);
//        return query(page, "select m from Movie m where m.category = ?1 order by m.hot desc", category);
//    }

    public Page<Movie> pageNew(Page<Movie> page) {
        if (null == page) page = new Page<Movie>(1l);
        return query(page, "select m from Movie m order by m.editTime desc");
    }
//    public Page<Movie> pageNew(Page<Movie> page, Category category) {
//        if (null == page) page = new Page<Movie>(1l);
//        return query(page, "select m from Movie m where m.category = ?1 order by m.editTime desc", category);
//    }
//    public Page<Movie> pageNew(Page<Movie> page, Genus genus) {
//        if (null == page) page = new Page<Movie>(1l);
//        return query(page, "select m from Movie m where m.genus = ?1 order by m.editTime desc", genus);
//    }
//    public Page<Movie> pageNew(Page<Movie> page, Region region) {
//        if (null == page) page = new Page<Movie>(1l);
//        return query(page, "select m from Movie m where m.region = ?1 order by m.editTime desc", region);
//    }



    public List<Movie> getNewz() {
        return query("select m from Movie m where m.category.open = true order by m.addTime desc");
    }
    public List<Movie> getNewz(Integer count) {
        return query("select m from Movie m where m.category.open = true order by m.date desc", 0, count);
    }
/*
    public List<Movie> getNewz(Category category) {
        return query("select m from Movie m where m.category = ?1 order by m.addTime desc", category);
    }
    public List<Movie> getNewz(Category category, Integer count) {
        return query("select m from Movie m where m.category = ?1 order by m.addTime desc", 0, count, category);
    }
    public List<Movie> getNewz(Category category, Genus genus) {
        return query("select m from Movie m where m.category = ?1 and m.genus = ?2 order by m.addTime desc", category, genus);
    }
    public List<Movie> getNewz(Category category, Genus genus, Integer count) {
        return query("select m from Movie m where m.category = ?1 and m.genus = ?2 order by m.addTime desc", 0, count, category, genus);
    }
    public List<Movie> getNewz(Category category, Region region) {
        return query("select m from Movie m where m.category = ?1 and m.region = ?2 order by m.addTime desc", category, region);
    }
    public List<Movie> getNewz(Category category, Region region, Integer count) {
        return query("select e from Entry e where e.category = ?1 and e.region = ?2 order by e.addTime desc", 0, count, category, region);
    }
    public List<Movie> getNewz(Category category, Region region, Genus genus, Integer count) {
        return query("select e from Entry e where e.category = ?1 and e.region = ?2 and e.genus = ?3 order by e.addTime desc", 0, count, category, region, genus);
    }


    public List<Movie> getNewz(Genus genus) {
        return query("select e from Entry e where e.category.open = true and e.genus = ?1 order by e.addTime desc", genus);
    }
    public List<Movie> getNewz(Genus genus, Integer count) {
        return query("select e from Entry e where e.category.open = true and e.genus = ?1 order by e.addTime desc", 0, count, genus);
    }
    public List<Movie> getNewz(Region region) {
        return query("select e from Entry e where e.category.open = true and e.region = ?1 order by e.addTime desc", region);
    }
    public List<Movie> getNewz(Region region, Integer count) {
        return query("select e from Entry e where e.category.open = true and e.region = ?1 order by e.addTime desc", 0, count, region);
    }
    public List<Movie> getNewz(Region region, Genus genus, Integer count) {
        return query("select e from Entry e where e.category.open = true and e.region = ?1 and e.genus = ?2 order by e.addTime desc", 0, count, region, genus);
    }
*/

    public List<Movie> getTops() {
        return query("select m from Movie m order by m.hot desc, m.hit desc, m.addTime");
    }
    public List<Movie> tops(Integer count) {
        return query("select m from Movie m order by m.hot desc, m.hit desc, m.addTime", 0, count);
    }
/*
    public List<Movie> getTops(Category category) {
        return query("select e from Entry e where e.category = ?1 order by e.hot desc, e.hit desc, e.addTime", category);
    }
    public List<Movie> getTops(Category category, Integer count) {
        return query("select e from Entry e where e.category = ?1 order by e.hot desc, e.hit desc, e.addTime", 0, count, category);
    }
    public List<Movie> getTops(Category category, Genus genus) {
        return query("select distinct(i.entry) from Intent i where i.entry.category = ?1 and i.entry.genus = ?2 group by i.entry order by count(i.entry)", category, genus);
    }
    public List<Movie> getTops(Category category, Genus genus, Integer count) {
        return query("select distinct(i.entry) from Intent i where i.entry.category = ?1 and i.entry.genus = ?2 group by i.entry order by count(i.entry)", 0, count, category, genus);
    }
    public List<Movie> getTops(Category category, Region region) {
        return query("select distinct(i.entry) from Intent i where i.entry.category = ?1 and i.entry.region = ?2 group by i.entry order by count(i.entry)", category, region);
    }
    public List<Movie> getTops(Category category, Region region, Integer count) {
        return query("select distinct(i.entry) from Intent i where i.entry.category = ?1 and i.entry.region = ?2 group by i.entry order by count(i.entry)", 0, count, category, region);
    }
    public List<Movie> getTops(Category category, Region region, Genus genus, Integer count) {
        return query("select distinct(i.entry) from Intent i where i.entry.category = ?1 and i.entry.region = ?2 and i.entry.genus = ?3 group by i.entry order by count(i.entry)", 0, count, category, region, genus);
    }
    public List<Movie> getTops(Genus genus) {
        return query("select distinct(i.entry) from Intent i where i.entry.category.open = true and i.entry.genus = ?1 group by i.entry order by count(i.entry)", genus);
    }
    public List<Movie> getTops(Genus genus, Integer count) {
        return query("select distinct(i.entry) from Intent i where i.entry.category.open = true and i.entry.genus = ?1 group by i.entry order by count(i.entry)", 0, count, genus);
    }
    public List<Movie> getTops(Region region) {
        return query("select distinct(i.entry) from Intent i where i.entry.category.open = true and i.entry.region = ?1 group by i.entry order by count(i.entry)", region);
    }
    public List<Movie> getTops(Region region, Integer count) {
        return query("select distinct(i.entry) from Intent i where i.entry.category.open = true and i.entry.region = ?1 group by i.entry order by count(i.entry)", 0, count, region);
    }
    public List<Movie> getTops(Region region, Genus genus, Integer count) {
        return query("select distinct(i.entry) from Intent i where i.entry.category.open = true and i.entry.region = ?1 and i.entry.genus = ?2  group by i.entry order by count(i.entry)", 0, count, region, genus);
    }
*/


    public List<Movie> getRandoms(Integer count) {
        String ql = "select m from Movie m";
        int bound = count(QueryUtil.getCountQLFromSelectQL(ql)).intValue() - count;
        return query(ql, bound > 0 ? NumberUtil.randomInt(bound) : 0, count);
    }


/*
    public List<Movie> getRandoms(Category category, Integer count) {
        String ql = "select e from Entry e where e.category = ?1";
        int bound = count(QueryUtil.getCountQLFromSelectQL(ql), category).intValue() - count;
        return query(ql, bound > 0 ? NumberUtil.randomInt(bound) : 0, count, category);
    }
    public List<Movie> getRandoms(Category category, Genus genus, Integer count) {
        String ql = "select e from Entry e where e.category = ?1 and e.genus = ?2";
        int bound = count(QueryUtil.getCountQLFromSelectQL(ql), category, genus).intValue() - count;
        return query(ql, bound > 0 ? NumberUtil.randomInt(bound) : 0, count, category, genus);
    }
    public List<Movie> getRandoms(Category category, Region region, Integer count) {
        String ql = "select e from Entry e where e.category = ?1 and e.region = ?2";
        int bound = count(QueryUtil.getCountQLFromSelectQL(ql), category, region).intValue() - count;
        return query(ql, bound > 0 ? NumberUtil.randomInt(bound) : 0, count, category, region);
    }
    public List<Movie> getRandoms(Genus genus, Integer count) {
        String ql = "select e from Entry e where e.category.open = true and e.genus = ?1";
        int bound = count(QueryUtil.getCountQLFromSelectQL(ql), genus).intValue() - count;
        return query(ql, bound > 0 ? NumberUtil.randomInt(bound) : 0, count, genus);
    }
    public List<Movie> getRandoms(Region region, Integer count) {
        String ql = "select e from Entry e where e.category.open = true and e.region = ?1";
        int bound = count(QueryUtil.getCountQLFromSelectQL(ql), region).intValue() - count;
        return query(ql, bound > 0 ? NumberUtil.randomInt(bound) : 0, count, region);
    }
*/



//    public List<Movie> getRecommends() {
//        return query("select m from Movie m.viewTime >= ?1 order by m.hot desc, m.hit desc, e.addTime", DateUtil.hoursAgo(24 * days));
//    }
//    public List<Movie> getRecommends(Integer count) {
//        return query("select m from Movie m where m.viewTime >= ?1 order by m.hot desc, m.hit desc, m.addTime", 0, count, DateUtil.hoursAgo(24 * days));
//    }
/*
    public List<Movie> getRecommends(Category category) {
        return query("select e from Entry e where e.category = ?1 and e.viewTime >= ?2 order by e.hot desc, e.hit desc, e.addTime", category, DateUtil.hoursAgo(24 * days));
    }
    public List<Movie> getRecommends(Category category, Integer count) {
        return query("select e from Entry e where e.category = ?1 and e.viewTime >= ?2 order by e.hot desc, e.hit desc, e.addTime", 0, count, category, DateUtil.hoursAgo(24 * days));
    }
    public List<Movie> getRecommends(Category category, Genus genus) {
        return query("select e from Entry e where e.category = ?1 and e.genus = ?2 and e.viewTime >= ?3 order by e.hot desc, e.hit desc, e.addTime", category, genus, DateUtil.hoursAgo(24 * days));
    }
    public List<Movie> getRecommends(Category category, Genus genus, Integer count) {
        return query("select e from Entry e where e.category = ?1 and e.genus = ?2 and e.viewTime >= ?3 order by e.hot desc, e.hit desc, e.addTime", 0, count, category, genus, DateUtil.hoursAgo(24 * days));
    }
    public List<Movie> getRecommends(Category category, Region region) {
        return query("select e from Entry e where e.category = ?1 and e.region = ?2 and e.viewTime >= ?3 order by e.hot desc, e.hit desc, e.addTime", category, region, DateUtil.hoursAgo(24 * days));
    }
    public List<Movie> getRecommends(Category category, Region region, Integer count) {
        return query("select e from Entry e where e.category = ?1 and e.region = ?2 and e.viewTime >= ?3 order by e.hot desc, e.hit desc, e.addTime", 0, count, category, region, DateUtil.hoursAgo(24 * days));
    }
    public List<Movie> getRecommends(Category category, Region region, Genus genus, Integer count) {
        return query("select e from Entry e where e.category = ?1 and e.region = ?2 and e.genus = ?3 and e.viewTime >= ?4 order by e.hot desc, e.hit desc, e.addTime", 0, count, category, region, genus, DateUtil.hoursAgo(24 * days));
    }
    public List<Movie> getRecommends(Genus genus) {
        return query("select e from Entry e where e.category.open = true and e.genus = ?1 and e.viewTime >= ?2 order by e.hot desc, e.hit desc, e.addTime", genus, DateUtil.hoursAgo(24 * days));
    }
    public List<Movie> getRecommends(Genus genus, Integer count) {
        return query("select e from Entry e where e.category.open = true and e.genus = ?1 and e.viewTime >= ?2 order by e.hot desc, e.hit desc, e.addTime", 0, count, genus, DateUtil.hoursAgo(24 * days));
    }
    public List<Movie> getRecommends(Region region) {
        return query("select e from Entry e where e.category.open = true and e.region = ?1 and e.viewTime >= ?2 order by e.hot desc, e.hit desc, e.addTime", region, DateUtil.hoursAgo(24 * days));
    }
    public List<Movie> getRecommends(Region region, Integer count) {
        return query("select e from Entry e where e.category.open = true and e.region = ?1 and e.viewTime >= ?2 order by e.hot desc, e.hit desc, e.addTime", 0, count, region, DateUtil.hoursAgo(24 * days));
    }
    public List<Movie> getRecommends(Region region, Genus genus, Integer count) {
        return query("select e from Entry e where e.category.open = true and e.region = ?1 and e.genus = ?2 and e.viewTime >= ?3 order by e.hot desc, e.hit desc, e.addTime", 0, count, region, genus, DateUtil.hoursAgo(24 * days));
    }



    public List<Movie> getRetros() {
        return query("select e from Entry e where e.category.open = true and e.viewTime > ?1 and e.viewTime < ?2 order by e.hot desc, e.hit desc, e.addTime", DateUtil.hoursAgo(24 * days * 2), DateUtil.hoursAgo(24 * days));
    }
    public List<Movie> getRetros(Integer count) {
        return query("select e from Entry e where e.category.open = true and e.viewTime > ?1 and e.viewTime < ?2 order by e.hot desc, e.hit desc, e.addTime", 0, count, DateUtil.hoursAgo(24 * days * 2), DateUtil.hoursAgo(24 * days));
    }
    public List<Movie> getRetros(Category category) {
        return query("select e from Entry e where e.category = ?1 and e.viewTime > ?2 and e.viewTime < ?3 order by e.hot desc, e.hit desc, e.addTime", category, DateUtil.hoursAgo(24 * days * 2), DateUtil.hoursAgo(24 * days));
    }


    public List<Movie> getTopRegions(Integer count, String[] regions) {
        return query("select distinct(i.entry) from Intent i where i.entry.region.idc in (" + QueryUtil.inClause(regions) + ") group by i.entry order by count(i.entry) desc", 0, count);
    }
    public List<Movie> getGenusEntries(Integer count, String[] genera) {
        return query("select distinct(i.entry) from Intent i where i.entry.genus.idc in (" + QueryUtil.inClause(genera) + ") group by i.entry order by count(i.entry) desc", 0, count);
    }
*/
/*

    public Map<String, List<Movie>> getNewzMap(Integer count) {
        Map<String, List<Movie>> newzMap = new HashMap<String, List<Movie>>();
        List<Category> cs =  getCategories();
        for (Category c : cs) {
            newzMap.put(c.getIdc(), getNewz(c, count));
        }
        return newzMap;
    }
    public Map<String, List<Movie>> getNewzMap(Integer count, String[] cates) {
        Map<String, List<Movie>> newzMap = new HashMap<String, List<Movie>>();
        for (String idc : cates) {
            newzMap.put(idc, getNewz(getCategory(idc), count));
        }
        return newzMap;
    }

    public Map<String, List<Movie>> getTopsMap(Integer count) {
        Map<String, List<Movie>> topsMap = new HashMap<String, List<Movie>>();
        List<Category> cs =  getCategories();
        for (Category c : cs) {
            topsMap.put(c.getIdc(), getTops(c, count));
        }
        return topsMap;
    }
    public Map<String, List<Movie>> getTopsMap(Integer count, String[] cates) {
        Map<String, List<Movie>> topsMap = new HashMap<String, List<Movie>>();
        for (String idc : cates) {
            topsMap.put(idc, getTops(getCategory(idc), count));
        }
        return topsMap;
    }


    public Map<String, List<Movie>> getTopGenusMap(Integer count, String[] genera) {
        Map<String, List<Movie>> genusMap = new HashMap<String, List<Movie>>();
        for (String idc : genera) {
            genusMap.put(idc, getTops(getGenus(idc), count));
        }
        return genusMap;
    }
    public Map<String, List<Movie>> getTopRegionMap(Integer count, String[] regions) {
        Map<String, List<Movie>> regionMap = new HashMap<String, List<Movie>>();
        for (String idc : regions) {
            regionMap.put(idc, getTops(getRegion(idc), count));
        }
        return regionMap;
    }

*/


    public Map<String, List<Movie>> indexMap() {
        Map<String, List<Movie>> indexHash = new HashMap<String, List<Movie>>();
        indexHash.put("#", query("select m from Movie m where substring(m.pinyin, 1, 1) >= '0' and substring(m.pinyin, 1, 1) <= '9'"));
        for (int i = 'a'; i <= 'z'; i++) {
            indexHash.put(String.valueOf((char)i).toUpperCase(), query("select m from Movie m where substring(m.pinyin, 1, 1) >= ?1 and substring(m.pinyin, 1, 1) < ?2", String.valueOf((char)i), String.valueOf((char)(i + 1))));
        }
        indexHash.put("?", query("select m from Movie m where '0' > substring(m.pinyin, 1, 1) or ('9' < substring(m.pinyin, 1, 1) and substring(m.pinyin, 1, 1) < 'a') or substring(m.pinyin, 1, 1) > 'z'"));
        return indexHash;
    }
/*
    public Map<String, List<Movie>> indexMap(Category category) {
        Map<String, List<Movie>> indexHash = new HashMap<String, List<Movie>>();
        indexHash.put("#", query("select e from Entry e where substring(e.pinyin, 1, 1) >= '0' and substring(e.pinyin, 1, 1) <= '9' and e.category = ?1 order by e.pinyin", category));
        for (int i = 'a'; i <= 'z'; i++) {
            indexHash.put(String.valueOf((char)i).toUpperCase(), query("select e from Entry e where substring(e.pinyin, 1, 1) = ?1 and e.category = ?2 order by e.pinyin", String.valueOf((char)i), category));
        }
        indexHash.put("?", query("select e from Entry e where ('0' > substring(e.pinyin, 1, 1) or ('9' < substring(e.pinyin, 1, 1) and substring(e.pinyin, 1, 1) < 'a') or substring(e.pinyin, 1, 1) > 'z') and e.category = ?1 order by e.pinyin", category));
        return indexHash;
    }
    public Map<String, List<Movie>> indexMap(Category category, Genus genus) {
        Map<String, List<Movie>> indexHash = new HashMap<String, List<Movie>>();
        indexHash.put("#", query("select e from Entry e where substring(e.pinyin, 1, 1) >= '0' and substring(e.pinyin, 1, 1) <= '9' and e.category = ?1 and e.genus = ?2 order by e.pinyin", category, genus));
        for (int i = 'a'; i <= 'z'; i++) {
            indexHash.put(String.valueOf((char)i).toUpperCase(), query("select e from Entry e where substring(e.pinyin, 1, 1) = ?1 and e.category = ?2 and e.genus = ?3 order by e.pinyin", String.valueOf((char)i), category, genus));
        }
        indexHash.put("?", query("select e from Entry e where ('0' > substring(e.pinyin, 1, 1) or ('9' < substring(e.pinyin, 1, 1) and substring(e.pinyin, 1, 1) < 'a') or substring(e.pinyin, 1, 1) > 'z') and e.category = ?1 and e.genus = ?2 order by e.pinyin", category, genus));
        return indexHash;
    }
    public Map<String, List<Movie>> indexMap(Category category, Region region) {
        Map<String, List<Movie>> indexHash = new HashMap<String, List<Movie>>();
        indexHash.put("#", query("select e from Entry e where substring(e.pinyin, 1, 1) >= '0' and substring(e.pinyin, 1, 1) <= '9' and e.category = ?1 and e.region = ?2 order by e.pinyin", category, region));
        for (int i = 'a'; i <= 'z'; i++) {
            indexHash.put(String.valueOf((char)i).toUpperCase(), query("select e from Entry e where substring(e.pinyin, 1, 1) = ?1 and e.category = ?2 and e.region = ?3 order by e.pinyin", String.valueOf((char)i), category, region));
        }
        indexHash.put("?", query("select e from Entry e where ('0' > substring(e.pinyin, 1, 1) or ('9' < substring(e.pinyin, 1, 1) and substring(e.pinyin, 1, 1) < 'a') or substring(e.pinyin, 1, 1) > 'z') and e.category = ?1 and e.region = ?2 order by e.pinyin", category, region));
        return indexHash;
    }
    public Map<String, List<Movie>> indexMap(Category category, Region region, Genus genus) {
        Map<String, List<Movie>> indexHash = new HashMap<String, List<Movie>>();
        indexHash.put("#", query("select e from Entry e where substring(e.pinyin, 1, 1) >= '0' and substring(e.pinyin, 1, 1) <= '9' and e.category = ?1 and e.region = ?2 and e.genus = ?3 order by e.pinyin", category, region, genus));
        for (int i = 'a'; i <= 'z'; i++) {
            indexHash.put(String.valueOf((char)i).toUpperCase(), query("select e from Entry e where substring(e.pinyin, 1, 1) = ?1 and e.category = ?2 and e.region = ?3 order by e.pinyin", String.valueOf((char)i), category, region));
        }
        indexHash.put("?", query("select e from Entry e where ('0' > substring(e.pinyin, 1, 1) or ('9' < substring(e.pinyin, 1, 1) and substring(e.pinyin, 1, 1) < 'a') or substring(e.pinyin, 1, 1) > 'z') and e.category = ?1 and e.region = ?2 and e.genus = ?3 order by e.pinyin", category, region, genus));
        return indexHash;
    }
    public Map<String, List<Movie>> indexMap(Genus genus) {
        Map<String, List<Movie>> indexHash = new HashMap<String, List<Movie>>();
        indexHash.put("#", query("select e from Entry e where substring(e.pinyin, 1, 1) >= '0' and substring(e.pinyin, 1, 1) <= '9' and e.genus = ?1 order by e.pinyin", genus));
        for (int i = 'a'; i <= 'z'; i++) {
            indexHash.put(String.valueOf((char)i).toUpperCase(), query("select e from Entry e where substring(e.pinyin, 1, 1) = ?1 and e.genus = ?2 order by e.pinyin", String.valueOf((char)i), genus));
        }
        indexHash.put("?", query("select e from Entry e where ('0' > substring(e.pinyin, 1, 1) or ('9' < substring(e.pinyin, 1, 1) and substring(e.pinyin, 1, 1) < 'a') or substring(e.pinyin, 1, 1) > 'z') and e.genus = ?1 order by e.pinyin", genus));
        return indexHash;
    }
    public Map<String, List<Movie>> indexMap(Region region) {
        Map<String, List<Movie>> indexHash = new HashMap<String, List<Movie>>();
        indexHash.put("#", query("select e from Entry e where substring(e.pinyin, 1, 1) >= '0' and substring(e.pinyin, 1, 1) <= '9' and e.region = ?1 order by e.pinyin", region));
        for (int i = 'a'; i <= 'z'; i++) {
            indexHash.put(String.valueOf((char)i).toUpperCase(), query("select e from Entry e where substring(e.pinyin, 1, 1) = ?1 and e.region = ?2 order by e.pinyin", String.valueOf((char)i), region));
        }
        indexHash.put("?", query("select e from Entry e where ('0' > substring(e.pinyin, 1, 1) or ('9' < substring(e.pinyin, 1, 1) and substring(e.pinyin, 1, 1) < 'a') or substring(e.pinyin, 1, 1) > 'z') and e.region = ?1 order by e.pinyin", region));
        return indexHash;
    }
    public Map<String, List<Movie>> indexMap(Region region, Genus genus) {
        Map<String, List<Movie>> indexHash = new HashMap<String, List<Movie>>();
        indexHash.put("#", query("select e from Entry e where substring(e.pinyin, 1, 1) >= '0' and substring(e.pinyin, 1, 1) <= '9' and e.region = ?1 and e.genus = ?2 order by e.pinyin", region, genus));
        for (int i = 'a'; i <= 'z'; i++) {
            indexHash.put(String.valueOf((char)i).toUpperCase(), query("select e from Entry e where substring(e.pinyin, 1, 1) = ?1 and e.region = ?2 and e.genus = ?3 order by e.pinyin", String.valueOf((char)i), region, genus));
        }
        indexHash.put("?", query("select e from Entry e where ('0' > substring(e.pinyin, 1, 1) or ('9' < substring(e.pinyin, 1, 1) and substring(e.pinyin, 1, 1) < 'a') or substring(e.pinyin, 1, 1) > 'z') and e.region = ?1 and e.genus = ?2 order by e.pinyin", region, genus));
        return indexHash;
    }
*/

/*

    public Long count(Category category) {
        return super.count("select count(e) from Entry as e where e.category = ?1", category);
    }
    public Long count(Category category, Genus genus) {
        return super.count("select count(e) from Entry as e where e.category = ?1 and e.genus = ?2", category, genus);
    }
    public Long count(Category category, Region region) {
        return super.count("select count(e) from Entry as e where e.category = ?1 and e.region = ?2", category, region);
    }
    public Long count(Category category, Region region, Genus genus) {
        return super.count("select count(e) from Entry as e where e.category = ?1 and e.region = ?2 and e.genus = ?3", category, region, genus);
    }
    public Long count(Genus genus) {
        return super.count("select count(e) from Entry as e where e.genus = ?1", genus);
    }
    public Long count(Region region) {
        return super.count("select count(e) from Entry as e where e.region = ?1", region);
    }
    public Long count(Region region, Genus genus) {
        return super.count("select count(e) from Entry as e where e.region = ?1 and e.region = ?2", region, genus);
    }

*/


//    public List<Movie> getRelates(Movie entry, Integer count) {
//        return query("select distinct(c.entry) from Collect c where c.entry != ?1 and c.entry.category = ?2 and c.user in (select distinct(cc.user) from Collect cc where cc.entry = ?1) group by c.entry order by count(c.entry) desc", 0, count, entry, entry.getCategory());
//    }

//    public List<Movie> getWants(User user, Integer count) {
//        return query("select c.movie from Collect c where c.type=" + CollectManager.TYPE_WANT + " and c.user = ?1 order by c.editTime desc", 0, count, user);
//    }
//    public List<Movie> getSeens(User user, Integer count) {
//        return query("select c.movie from Collect c where c.type=" + CollectManager.TYPE_SEEN + " and c.user = ?1 order by c.editTime desc", 0, count, user);
//    }

/*
    public Document sitemaps() {
        Document doc = SitemapUtil.createIndex();
        Element index = doc.getRootElement();

        SitemapUtil.addSitemap(index, getUrl() + "sitemap.xml", DateUtil.toString(DateUtil.currentDate(), "yyyy-MM-dd"));

//        List<Category> cates = getCategories();
//        for (Category cate : cates) {
//            SitemapUtil.addSitemap(index, getUrl() + "sitemap_" + cate.getIdc() + ".xml", DateUtil.toString(DateUtil.currentDate(), "yyyy-MM-dd"));
//        }
        return doc;
    }
    public Document sitemap() {
        Document doc = SitemapUtil.create();
        Element set = doc.getRootElement();

        SitemapUtil.addUrl(set, getUrl(), DateUtil.toString(DateUtil.currentDate(), "yyyy-MM-dd"), "1.00", "daily");
        SitemapUtil.addUrl(set, getUrl() + "new", DateUtil.toString(DateUtil.currentDate(), "yyyy-MM-dd"), "1.00", "daily");
        SitemapUtil.addUrl(set, getUrl() + "top", DateUtil.toString(DateUtil.currentDate(), "yyyy-MM-dd"), "1.00", "daily");

//        List<Category> cates = getCategories();
//        for (Category cate : cates) {
//            SitemapUtil.addUrl(set, getUrl() + cate.getIdc(), DateUtil.toString(DateUtil.currentDate(), "yyyy-MM-dd"), "0.80", "daily");
//        }
//        for (Category cate : cates) {
//            SitemapUtil.addUrl(set, getUrl() + "lib/" + cate.getIdc(), DateUtil.toString(DateUtil.currentDate(), "yyyy-MM-dd"), "0.80", "daily");
//        }
//
//        List<Genus> genera = getGenera();
//        for (Genus genus : genera) {
//            SitemapUtil.addUrl(set, getUrl() + "genus/"+ genus.getIdc(), DateUtil.toString(DateUtil.currentDate(), "yyyy-MM-dd"), "0.80", "daily");
//        }
//        List<Region> regions = getRegions();
//        for (Region region : regions) {
//            SitemapUtil.addUrl(set, getUrl() + "region/"+ region.getIdc(), DateUtil.toString(DateUtil.currentDate(), "yyyy-MM-dd"), "0.80", "daily");
//        }

        SitemapUtil.addUrl(set, getUrl() + "stars/", DateUtil.toString(DateUtil.currentDate(), "yyyy-MM-dd"), "1.00", "daily");
        SitemapUtil.addUrl(set, getUrl() + "photos/", DateUtil.toString(DateUtil.currentDate(), "yyyy-MM-dd"), "1.00", "daily");
        SitemapUtil.addUrl(set, getUrl() + "picts/", DateUtil.toString(DateUtil.currentDate(), "yyyy-MM-dd"), "1.00", "daily");
        SitemapUtil.addUrl(set, getUrl() + "plots/", DateUtil.toString(DateUtil.currentDate(), "yyyy-MM-dd"), "1.00", "daily");




        // if use sitemap_index.xml, then comment below
//        List<Entry> entries = get();
//        for (Entry entry : entries) {
//            Sitemap.addUrl(set, getUrl() + entry.getCategory().getIdc() + "/" + entry.getId(), DateUtil.toString(DateUtil.toDate(entry.getEditTime()), "yyyy-MM-dd"), "0.80", "daily");
//        }
        return doc;
    }
//    public Document sitemap(Category category) {
//        Document doc = SitemapUtil.create();
//        Element set = doc.getRootElement();
//
//        SitemapUtil.addUrl(set, getUrl(), DateUtil.toString(DateUtil.currentDate(), "yyyy-MM-dd"), "1.00", "daily");
//        SitemapUtil.addUrl(set, getUrl() + category.getIdc(), DateUtil.toString(DateUtil.currentDate(), "yyyy-MM-dd"), "1.00", "daily");
//
//        SitemapUtil.addUrl(set, getUrl() + category.getIdc() + "/new", DateUtil.toString(DateUtil.currentDate(), "yyyy-MM-dd"), "1.00", "daily");
//        SitemapUtil.addUrl(set, getUrl() + category.getIdc() + "/top", DateUtil.toString(DateUtil.currentDate(), "yyyy-MM-dd"), "1.00", "daily");
//
//        List<Movie> entries = get(category);
//        for (Movie entry : entries) {
//            SitemapUtil.addUrl(set, getUrl() + entry.getCategory().getIdc() + "/" + entry.getId(), DateUtil.toString(DateUtil.toDate(entry.getEditTime()), "yyyy-MM-dd"), "0.80", "daily");
//        }
//        return doc;
//    }

    public String urllist() {
        StringBuffer sb = new StringBuffer();
        sb.append(getUrl()).append("\n");

        sb.append(getUrl()).append("new").append("\n");
        sb.append(getUrl()).append("top").append("\n");

//        List<Category> cates = getCategories();
//        for (Category cate : cates) {
//            sb.append(getUrl()).append(cate.getIdc()).append("\n");
//            sb.append(getUrl()).append(cate.getIdc()).append("/new").append("\n");
//            sb.append(getUrl()).append(cate.getIdc()).append("/top").append("\n");
//        }
//        for (Category cate : cates) {
//            sb.append(getUrl()).append("lib/").append(cate.getIdc()).append("\n");
//        }
//
//        List<Genus> genera = getGenera();
//        for (Genus genus : genera) {
//            sb.append(getUrl()).append("genus/").append(genus.getIdc()).append("\n");
//        }
//        List<Region> regions = getRegions();
//        for (Region region : regions) {
//            sb.append(getUrl()).append("region/").append(region.getIdc()).append("\n");
//        }

        List<Movie> movies = get();
        for (Movie movie : movies) {
            sb.append(getUrl()).append("movie").append("/").append(movie.getId()).append("\n");
        }
        return sb.toString();
    }

*//* *//*
    public Document rssTops(Integer count) {
//        Document doc = RssUtil.create();
//        Element rss = doc.getRootElement();
//
//        Element ch = RssUtil.addChannel(rss, "热门推荐", null);
//        ch.addElement("link").setText(StringUtil.append(Helper.getOptions("url.base", ""), "/") + "tops");
//        ch.addElement("description").addCDATA("最近大家特别关注的影视");
//        ch.addElement("language").setText("zh-cn");
//        ch.addElement("copyright").setText("&amp;copy; 2007-2008, tvlian.com.");
//        ch.addElement("pubDate").setText(DateUtil.toString(DateUtil.currentDate(), "yyyy-MM-dd HH:mm"));
//
//        Element img = RssUtil.addImage(ch, StringUtil.append(Helper.getOptions("url.base", ""), "/") + "img/logo.gif", null);
//        img.addElement("title").setText("视联-电视剧情，海报剧照，明星写真，热播，最新电影");
//        img.addElement("link").setText(Helper.getOptions("url.base", ""));
//        img.addElement("description").addCDATA("视联为用户提供丰富的电视剧、电影信息，剧情、海报剧照、明星写真，推荐精彩热播的电影、电视剧");
//
//        List<Entry> rec = getRecommends(c);
//        for (Entry e : rec) {
//            Element item = RssUtil.addItem(ch, makeTitle(e), null);
//            item.addElement("link").setText(StringUtil.append(Helper.getOptions("url.base", ""), "/") + "entry/" + e.getId());
//            item.addElement("description").addCDATA(description(e));
//            item.addElement("pubDate").setText(DateUtil.toString(e.getEditTime(), "yyyy-MM-dd HH:mm"));
//            item.addElement("guid").setText(StringUtil.append(Helper.getOptions("url.base", ""), "/") + "entry/" + e.getId());
//        }
//        return doc;

//        return rss("热门推荐", StringUtil.append(Helper.getOptions("url.base", ""), "/") + "top", "最近大家特别关注的影视", getRecommends(count));
        return rss("热门推荐", StringUtil.append(options().getUrl(), "/") + "top", "最近大家特别关注的影视", getRecommends(count));
    }

    public Document rssNewz(Integer count) {
//        Document doc = RssUtil.create();
//        Element rss = doc.getRootElement();
//
//        Element ch = RssUtil.addChannel(rss, "最近更新", null);
//        ch.addElement("link").setText(StringUtil.append(Helper.getOptions("url.base", ""), "/") + "new");
//        ch.addElement("description").addCDATA("最近被更新过资料的影视");
//        ch.addElement("language").setText("zh-cn");
//        ch.addElement("copyright").setText("&amp;copy; 2007-2008, tvlian.com.");
//        ch.addElement("pubDate").setText(DateUtil.toString(DateUtil.currentDate(), "yyyy-MM-dd HH:mm"));
//
//        Element img = RssUtil.addImage(ch, StringUtil.append(Helper.getOptions("url.base", ""), "/") + "img/logo.gif", null);
//        img.addElement("title").setText("视联-电视剧情，海报剧照，明星写真，热播，最新电影");
//        img.addElement("link").setText(Helper.getOptions("url.base", ""));
//        img.addElement("description").addCDATA("视联为用户提供丰富的电视剧、电影信息，剧情、海报剧照、明星写真，推荐精彩热播的电影、电视剧");
//
//        List<Entry> rec = getNewz(c);
//        for (Entry e : rec) {
//            Element item = RssUtil.addItem(ch, makeTitle(e), null);
//            item.addElement("link").setText(StringUtil.append(Helper.getOptions("url.base", ""), "/") + "entry/" + e.getId());
//            item.addElement("description").addCDATA(description(e));
//            item.addElement("pubDate").setText(DateUtil.toString(e.getEditTime(), "yyyy-MM-dd HH:mm"));
//            item.addElement("guid").setText(StringUtil.append(Helper.getOptions("url.base", ""), "/") + "entry/" + e.getId());
//        }
//        return doc;
//        return rss("最近更新", StringUtil.append(Helper.getOptions("url.base", ""), "/") + "new", "最近被更新过资料的影视", getNewz(count));
        return rss("最近更新", StringUtil.append(options().getUrl(), "/") + "new", "最近被更新过资料的影视", getNewz(count));
    }

    public Document rssWants(Integer count, User user) {
//        return rss(user.getName() + "想看...", StringUtil.append(Helper.getOptions("url.base", ""), "/") + "people/" + StringUtil.urlEncode(user.getName()), user.getName() + "最近想看的影视", getWants(user, count));
        return rss(user.getName() + "想看...", StringUtil.append(options().getUrl(), "/") + "people/" + StringUtil.urlEncode(user.getName()), user.getName() + "最近想看的影视", getWants(user, count));
    }
    public Document rssSeens(Integer count, User user) {
//        return rss(user.getName() + "看过...", StringUtil.append(Helper.getOptions("url.base", ""), "/") + "people/" + StringUtil.urlEncode(user.getName()), user.getName() + "最近看过的影视", getSeens(user, count));
        return rss(user.getName() + "看过...", StringUtil.append(options().getUrl(), "/") + "people/" + StringUtil.urlEncode(user.getName()), user.getName() + "最近看过的影视", getSeens(user, count));
    }


    public String description(Movie movie) {
        StringBuffer sb = new StringBuffer();
//        sb.append("<a href=\"").append(StringUtil.append(Helper.getOptions("url.base", ""), "/")).append("entry/").append(movie.getId()).append("\">");
        sb.append("<a href=\"").append(StringUtil.append(options().getUrl(), "/")).append("movie/").append(movie.getId()).append("\">");
//        sb.append("<img src=\"").append(StringUtil.append(Helper.getOptions("pic.url", ""), "/")).append("spic/").append(movie.getPoster()).append("\" title=\"").append(movie.getTitle()).append("\" border=\"0\"").append("/>");
        sb.append("<img src=\"").append(StringUtil.append(options().getUrl(), "/")).append("spic/").append(movie.getPoster()).append("\" title=\"").append(movie.getTitle()).append("\" border=\"0\"").append("/>");
        sb.append("</a>");
        sb.append("<div>");
        sb.append(StringUtil.isEmpty(movie.getDate()) ? "" : movie.getDate());
        sb.append(StringUtil.isEmpty(movie.getCountry()) ? "" : " / " + movie.getCountry());
        sb.append(StringUtil.isEmpty(movie.getCast()) ? "" : " / " + movie.getCast());
        sb.append(StringUtil.isEmpty(movie.getDirector()) ? "" : " / " + movie.getDirector());
        sb.append(StringUtil.isEmpty(movie.getWriter()) ? "" : " / " + movie.getWriter());
        sb.append(StringUtil.isEmpty(movie.getCompany()) ? "" : " / " + movie.getCompany());
        sb.append(StringUtil.isEmpty(movie.getSite()) ? "" : " / " + movie.getSite());
        sb.append(StringUtil.isEmpty(movie.getLang()) ? "" : " / " + movie.getLang());
        sb.append("</div>");
        return sb.toString();
    }
//    public String makeTitle(Movie movie) {
//        return (StringUtil.isEmpty(movie.getChinese()) ? "" : movie.getChinese() + " ") + movie.getTitle();
//    }
    public Document rss(String channelTitle, String channelLink, String channelDesc, List<Movie> lst) {
        Document doc = RssUtil.create();
        Element rss = doc.getRootElement();

        Element ch = RssUtil.addChannel(rss, channelTitle, null);
        ch.addElement("link").setText(channelLink);
        ch.addElement("description").addCDATA(channelDesc);
        ch.addElement("language").setText("zh-cn");
        ch.addElement("copyright").setText("&amp;copy; " + options().getPeriod() + ", " + options().getDomain() + ".");
        ch.addElement("pubDate").setText(DateUtil.toString(DateUtil.currentDate(), "yyyy-MM-dd HH:mm"));

//        Element img = RssUtil.addImage(ch, StringUtil.append(Helper.getOptions("url.base", ""), "/") + "img/logo.gif", null);
        Element img = RssUtil.addImage(ch, StringUtil.append(options().getUrl(), "/") + "img/logo.gif", null);
        img.addElement("title").setText("视联-电视剧情，海报剧照，明星写真，热播，最新电影");
//        img.addElement("link").setText(Helper.getOptions("url.base", ""));
        img.addElement("link").setText(options().getUrl());
        img.addElement("description").addCDATA("视联为用户提供丰富的电视剧、电影信息，剧情、海报剧照、明星写真，推荐精彩热播的电影、电视剧");

        if (null == lst) return doc;

        for (Movie movie : lst) {
//            Element item = RssUtil.addItem(ch, makeTitle(movie), null);
//            item.addElement("link").setText(StringUtil.append(Helper.getOptions("url.base", ""), "/") + "entry/" + movie.getId());
//            item.addElement("link").setText(StringUtil.append(options().getUrl(), "/") + "movie/" + movie.getId());
//            item.addElement("description").addCDATA(description(movie));
//            item.addElement("pubDate").setText(DateUtil.toString(movie.getEditTime(), "yyyy-MM-dd HH:mm"));
//            item.addElement("guid").setText(StringUtil.append(Helper.getOptions("url.base", ""), "/") + "entry/" + movie.getId());
//            item.addElement("guid").setText(StringUtil.append(options().getUrl(), "/") + "movie/" + movie.getId());
        }
        return doc;
    }


    public void collect(Movie movie) {

    }



//
//    public Movie updateHit(Movie movie) {
//        Long h = movie.getHit();
//        if (null == h) h = 0l;
//        movie.setHit(h + 1);
//        movie.setViewTime(Helper.now());
//        return save(movie);
//    }
//
//    public Double compHot(Movie movie) {
//        Double z = 0.0;
//        for (Rating r : movie.getRatings()) {
//            z += r.getValue();
//        }
//        movie.setHot(NumberUtil.round(z/movie.getRatings().size(), 2));
//        return movie.getHot();
//    }
//
//
//
//
//
//    public void savePinyin(Movie movie) {
//        if (StringUtil.isEmpty(movie.getChinese())) {
//            movie.setPinyin(new Pinyin().output(movie.getTitle()));
//        } else {
//            movie.setPinyin(new Pinyin().output(movie.getChinese()));
//        }
//        save(movie);
//    }
//
//    public Movie store(Movie movie) {
//        savePinyin(movie);
//        return movie;
//    }
//
//    public Movie makePoster(Pic pict) {
//        Movie movie = pict.getMovie();
//        movie.setPoster(pict.getUrl());
//        return save(movie);
//    }


    public Movie fetchDouban(Movie movie) {
        if (null != movie && ! StringUtil.isEmpty(movie.getDouban())) {
            String link = StringUtil.append(options().getDoubanApi(), "/") + movie.getDouban() + "?apikey=" + options().getDoubanApi();

            URL url = URLUtil.getURL(link);
            if (null != url) {
                log.debug("douban: douban is not null");
                Document xml;
                try {
                    xml = XMLUtil.read(url);
                } catch (SimpleException e) {
                    log.debug("problem get douban");
                    return movie;
                }

                movie = fetchDouban(xml, movie);
            }
        }
        return movie;
    }
    public Movie fetchDouban(Document xml, Movie movie) {
        movie.setTitle(XMLUtil.getText(xml.getRootElement(), "title"));

//        movie.setChinese(XMLUtil.getText(xml, "//db:attribute[normalize-space(@name)='aka' and normalize-space(@lang)='zh_CN']"));
//        movie.setAka(StringUtil.concat(XMLUtil.getTexts(xml, "//db:attribute[normalize-space(@name)='aka' and normalize-space(@lang)!='zh_CN']", true), " / ", movie.getChinese()));

        movie.setDirector(XMLUtil.concatText(xml, "//db:attribute[normalize-space(@name)='director']", " / ", true));
        movie.setWriter(XMLUtil.concatText(xml, "//db:attribute[normalize-space(@name)='writer']", " / ", true));
        movie.setCast(XMLUtil.concatText(xml, "//db:attribute[normalize-space(@name)='cast']", " / ", true));
        movie.setLang(XMLUtil.concatText(xml, "//db:attribute[normalize-space(@name)='language']", " / ", true));
        movie.setCountry(XMLUtil.concatText(xml, "//db:attribute[normalize-space(@name)='country']", " / ", true));
        movie.setSummary(StringUtil.htmlProcess(XMLUtil.getText(xml.getRootElement(), "summary")));

        String date = XMLUtil.getText(xml, "//db:attribute[normalize-space(@name)='pubdate']");
        if (! StringUtil.isEmpty(date) && date.length() > 4) {
            date = date.substring(0, 4);
        }
        movie.setDate(date);

        movie.setSize(XMLUtil.getText(xml, "//db:attribute[normalize-space(@name)='episodes']"));
        movie.setImdb(XMLUtil.getText(xml, "//db:attribute[normalize-space(@name)='imdb']"));

        String site = XMLUtil.getText(xml, "//db:attribute[normalize-space(@name)='website']");
        if (! StringUtil.isEmpty(site) && site.indexOf("vvband.com") == -1) {
            movie.setSite(site);
        }

        String surl = XMLUtil.getAttributeValue(xml.getRootElement(), "link", "rel", "image", "href");
        if (! StringUtil.isEmpty(surl)) {
            String lurl = surl.replaceAll("/spic/", "/lpic/");

            if (URLUtil.exists(lurl)) {
//                Pic pict = picDAO.upload(movie.getUser(), movie, lurl);
//                movie.setPoster(pict.getUrl());
            }
        }

//        savePinyin(movie);

        return movie;
    }

    public Movie storeDouban(Movie movie) {
        return fetchDouban(movie);
    }


    public void remove(Movie movie) {
//        Category cate = movie.getCategory();
//        if (! Helper.isTransient(cate)) {
//            cate.removeMovie(movie);
//        }
//        Region region = movie.getRegion();
//        if (! Helper.isTransient(region)) {
//            region.removeMovie(movie);
//        }
//        Genus genus = movie.getGenus();
//        if (! Helper.isTransient(genus)) {
//            genus.removeEntry(movie);
//        }

//        for (Pic p : movie.getPicts()) {
//            picDAO.removePic(p);
//        }
        super.remove(movie);
    }



    public Movie getByDouban(String doubanId) {
        List<Movie> movies = query("select m from Movie m where m.douban = ?1", doubanId);
        if (movies.size() == 0) return null;
        return movies.get(0);
    }



//
//    public Movie recommend(Movie movie) {
//        movie.setRec(Boolean.TRUE);
//        return save(movie);
//    }
//    public Movie unrecommend(Movie movie) {
//        movie.setRec(Boolean.FALSE);
//        return save(movie);
//    }

    public List<Movie> recs(Integer c) {
        return query("select m from Movie m where m.rec=true", 0, c);
    }



    public void index(Long begId, Long endId) {
        movieSearcher.index("select m from Movie m where m.id between ?1 and ?2", begId, endId);
    }*/
}
