package org.gaozou.jimmy.vms.action;

import org.gaozou.jimmy.vms.domain.*;
import org.gaozou.jimmy.vms.domain.Tag;
import org.gaozou.jimmy.vms.manager.MovieManager;

import java.util.List;
import java.util.Map;

/**
 * Author: george
 * Powered by GaoZou group.
 */
@SuppressWarnings("unchecked")
public class MovieAction extends Action<Movie> {
    private MovieManager movieManager;
    private Movie    movie;
    private Tag      tag;
    private User     user;
    private Pic pict;


//    private Intent   intent;
//
//    private Rating   rating;
//    private Collect  collect;



    private List<String> akas;
    private List<String> casts;
    private List<String> langs;
    private List<String> writers;
    private List<String> directors;
    private List<String> countries;


    private List<Movie> movies;
    private List<Movie> tops;
    private List<Movie> newz;
    private List<Movie> retros;
    private List<Movie> relates;
    private List<Movie> randoms;
    private List<Movie> recommends;
    private List<Movie> wants;
    private List<Movie> seens;

    

    private Map<String, List<Movie>> newzMap;
    private Map<String, List<Movie>> topsMap;
    private Map<String, List<Movie>> indexMap;

/*
    private List<Plot>     plots;
    private List<Pic>     picts;
    private List<Source>   sources;
    private List<Topic>    topics;

    private List<News>     news;
    private List<Star>     stars;
    private List<Star>     topStars;
    private List<Photo>    photos;

    private List<Collect>  collects;
    private List<Tag>      tags;

    private List<Tag>      toTags;
    private List<Tag>      myTags;
    private List<Tag>      hotTags;


    private List<Ad>   adverts;


    private String field;

    private String  doubanTag;
    private Integer beg;
    private Integer num;


    public MovieManager getMovieManager() {
        return movieManager;
    }
    public void setMovieManager(MovieManager movieManager) {
        this.movieManager = movieManager;
    }

    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }



    public Tag getTag() {
        return tag;
    }
    public void setTag(Tag tag) {
        this.tag = tag;
    }


    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Pic getPict() {
        return pict;
    }
    public void setPict(Pic pict) {
        this.pict = pict;
    }


//    public Intent getIntent() {
//        return intent;
//    }
//    public void setIntent(Intent intent) {
//        this.intent = intent;
//    }



    public Rating getRating() {
        return rating;
    }
    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Collect getCollect() {
        return collect;
    }
    public void setCollect(Collect collect) {
        this.collect = collect;
    }


    public List<String> getAkas() {
        return akas;
    }
    public void setAkas(List<String> akas) {
        this.akas = akas;
    }

    public List<String> getCasts() {
        return casts;
    }
    public void setCasts(List<String> casts) {
        this.casts = casts;
    }

    public List<String> getLangs() {
        return langs;
    }
    public void setLangs(List<String> langs) {
        this.langs = langs;
    }

    public List<String> getWriters() {
        return writers;
    }
    public void setWriters(List<String> writers) {
        this.writers = writers;
    }

    public List<String> getDirectors() {
        return directors;
    }
    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public List<String> getCountries() {
        return countries;
    }
    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<Movie> getMovies() {
        return movies;
    }
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getTops() {
        return tops;
    }
    public void setTops(List<Movie> tops) {
        this.tops = tops;
    }

    public List<Movie> getNewz() {
        return newz;
    }
    public void setNewz(List<Movie> newz) {
        this.newz = newz;
    }

    public List<Movie> getRetros() {
        return retros;
    }
    public void setRetros(List<Movie> retros) {
        this.retros = retros;
    }

    public List<Movie> getRelates() {
        return relates;
    }
    public void setRelates(List<Movie> relates) {
        this.relates = relates;
    }

    public List<Movie> getRandoms() {
        return randoms;
    }
    public void setRandoms(List<Movie> randoms) {
        this.randoms = randoms;
    }

    public List<Movie> getRecommends() {
        return recommends;
    }
    public void setRecommends(List<Movie> recommends) {
        this.recommends = recommends;
    }

    public List<Movie> getWants() {
        return wants;
    }
    public void setWants(List<Movie> wants) {
        this.wants = wants;
    }

    public List<Movie> getSeens() {
        return seens;
    }
    public void setSeens(List<Movie> seens) {
        this.seens = seens;
    }


    public Map<String, List<Movie>> getNewzMap() {
        return newzMap;
    }
    public void setNewzMap(Map<String, List<Movie>> newzMap) {
        this.newzMap = newzMap;
    }

    public Map<String, List<Movie>> getTopsMap() {
        return topsMap;
    }
    public void setTopsMap(Map<String, List<Movie>> topsMap) {
        this.topsMap = topsMap;
    }

    public Map<String, List<Movie>> getIndexMap() {
        return indexMap;
    }
    public void setIndexMap(Map<String, List<Movie>> indexMap) {
        this.indexMap = indexMap;
    }



    public List<Plot> getPlots() {
        return plots;
    }
    public void setPlots(List<Plot> plots) {
        this.plots = plots;
    }



    public List<Pic> getPicts() {
        return picts;
    }
    public void setPicts(List<Pic> picts) {
        this.picts = picts;
    }

    public List<Source> getSources() {
        return sources;
    }
    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    public List<Topic> getTopics() {
        return topics;
    }
    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }


    public List<News> getNews() {
        return news;
    }
    public void setNews(List<News> news) {
        this.news = news;
    }

    public List<Star> getStars() {
        return stars;
    }
    public void setStars(List<Star> stars) {
        this.stars = stars;
    }

    public List<Star> getTopStars() {
        return topStars;
    }
    public void setTopStars(List<Star> topStars) {
        this.topStars = topStars;
    }

    public List<Photo> getPhotos() {
        return photos;
    }
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }



    public List<Collect> getCollects() {
        return collects;
    }
    public void setCollects(List<Collect> collects) {
        this.collects = collects;
    }


    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }


    public List<Tag> getToTags() {
        return toTags;
    }
    public void setToTags(List<Tag> toTags) {
        this.toTags = toTags;
    }

    public List<Tag> getMyTags() {
        return myTags;
    }
    public void setMyTags(List<Tag> myTags) {
        this.myTags = myTags;
    }

    public List<Tag> getHotTags() {
        return hotTags;
    }
    public void setHotTags(List<Tag> hotTags) {
        this.hotTags = hotTags;
    }


    public List<Ad> getAds() {
        return adverts;
    }
    public void setAds(List<Ad> adverts) {
        this.adverts = adverts;
    }


    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }



    public String getDoubanTag() {
        return doubanTag;
    }
    public void setDoubanTag(String doubanTag) {
        this.doubanTag = doubanTag;
    }

    public Integer getBeg() {
        return beg;
    }
    public void setBeg(Integer beg) {
        this.beg = beg;
    }

    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }



    ////
    public void doPrepare() throws Exception {
        if (! Helper.isEmpty(movie,         "id"))   movie    = movieManager.get(movie.getId());

//        if (! Helper.isEmpty(intent,        "id"))   intent   = movieManager.getIntent(intent.getId());

        if (! Helper.isEmpty(tag,           "id"))   tag      = movieManager.getTag(tag.getId());
        else if (! Helper.isEmpty(tag,      "name")) tag      = movieManager.getTag(tag.getName());

        if (! Helper.isEmpty(user,          "id"))   user     = movieManager.getUser(user.getId());
        else if (! Helper.isEmpty(user,     "name")) user     = movieManager.getUser(user.getName());

        if (! Helper.isEmpty(pict,          "id"))   pict     = movieManager.getPict(pict.getId());
    }

*/
/*    public String home() {
//        categories = movieManager.getCategories();
//        regions    = movieManager.getRegions();
//        genera     = movieManager.getGenera();

//        recommends = movieManager.getRecommends(8);
//        newzMap    = movieManager.getNewzMap(10, new String[]{"tv", "movie", "cartoon"});

        movies    = movieManager.getNewz(5);
        stars      = movieManager.getStars(5);
        news       = movieManager.getNews(12);

        tops       = movieManager.tops(8);
        topStars   = movieManager.getTopStars(8);

//        tags       = movieManager.getHotTags(20);

        adverts    = movieManager.getAds("home");

        return SUCCESS;
    }
*/
/*

    public String search() {
        page = movieManager.search(q, page);
        return SUCCESS;
    }

    public String field() {
        page = movieManager.search(q, page, new String[] {field});
        return SUCCESS;
    }


    public String list() {
        page = movieManager.page(page);
        tags = movieManager.getHotTags(20);
        return SUCCESS;
    }


//    public String category() {
////        categories = movieManager.getCategories();
//        if (Helper.isTransient(category)) {
//            page       = movieManager.pageNew(page);
//            recommends = movieManager.getRecommends(16);
//            tags       = movieManager.getHotTags(20);
//        } else {
//            page       = movieManager.pageNew(page, category);
//            recommends = movieManager.getRecommends(category, 16);
////            tags       = movieManager.getHotTags(category, 20);
//        }
//        adverts    = movieManager.getAds("home");
//        return SUCCESS;
//    }
*/
/*
    public String genus() {
        categories = movieManager.getCategories();
        page       = movieManager.pageNew(page, genus);
        recommends = movieManager.getRecommends(genus, 16);
//        tags       = movieManager.getHotTags(genus, 20);

        adverts    = movieManager.getAds("home");
        return SUCCESS;
    }
    public String region() {
        categories = movieManager.getCategories();
        page       = movieManager.pageNew(page, region);
        recommends = movieManager.getRecommends(region, 16);
//        tags       = movieManager.getHotTags(region, 20);

        return SUCCESS;
    }
    public String library() {
        categories = movieManager.getCategories();
        regions    = movieManager.getRegions();
        genera     = movieManager.getGenera();

        if (Helper.isTransient(category)) {
            if(! Helper.isTransient(region) && ! Helper.isTransient(genus)) {
                indexMap   = movieManager.indexMap(region, genus);
                c          = movieManager.count(region, genus);
                recommends = movieManager.getRecommends(region, genus, 10);
                newz       = movieManager.getNewz(region, genus, 10);
            } else if (! Helper.isTransient(genus)) {
                indexMap   = movieManager.indexMap(genus);
                c          = movieManager.count(genus);
                recommends = movieManager.getRecommends(genus, 10);
                newz       = movieManager.getNewz(genus, 10);
            } else if (! Helper.isTransient(region)) {
                indexMap   = movieManager.indexMap(region);
                c          = movieManager.count(region);
                recommends = movieManager.getRecommends(region, 10);
                newz       = movieManager.getNewz(region, 10);
            } else {
                indexMap   = movieManager.indexMap();
                c          = movieManager.count();
                recommends = movieManager.getRecommends(10);
                newz       = movieManager.getNewz(10);
            }
        } else {
            if(! Helper.isTransient(region) && ! Helper.isTransient(genus)) {
                indexMap   = movieManager.indexMap(category, region, genus);
                c          = movieManager.count(category, region, genus);
                recommends = movieManager.getRecommends(category, region, genus, 10);
                newz       = movieManager.getNewz(category, region, genus, 10);
            } else if (! Helper.isTransient(genus)) {
                indexMap   = movieManager.indexMap(category, genus);
                c          = movieManager.count(category, genus);
                recommends = movieManager.getRecommends(category, genus, 10);
                newz       = movieManager.getNewz(category, genus, 10);
            } else if (! Helper.isTransient(region)) {
                indexMap   = movieManager.indexMap(category, region);
                c          = movieManager.count(category, region);
                recommends = movieManager.getRecommends(category, region, 10);
                newz       = movieManager.getNewz(category, region, 10);
            } else {
                indexMap   = movieManager.indexMap(category);
                c          = movieManager.count(category);
                recommends = movieManager.getRecommends(category, 10);
                newz       = movieManager.getNewz(category, 10);
            }
        }

        return SUCCESS;
    }


    public String newz() {
        categories = movieManager.getCategories();
        if (Helper.isTransient(category)) {
            newz = movieManager.getNewz();
        } else {
            newz = movieManager.getNewz(category);
        }
        adverts  = movieManager.getAds("home");

        return SUCCESS;
    }
    public String tops() {
        categories = movieManager.getCategories();
        if (Helper.isTransient(category)) {
            tops = movieManager.getTops();
        } else {
            tops = movieManager.getTops(category);
        }
        adverts  = movieManager.getAds("home");

        return SUCCESS;
    }*/
/*
    public String people() {
        seens      = movieManager.getSeens(user, 20);
        wants      = movieManager.getWants(user, 20);
        myTags     = movieManager.getTags(user, 12);
        return SUCCESS;
    }
    public String tag() {
        movies = movieManager.getByTag(tag);

        movieManager.updateTagHit(tag);
        return SUCCESS;
    }


    public String view() {
        redirect   = referer;

        plots      = movieManager.getPlots(movie, 10);
        picts      = movieManager.getPicts(movie, 8);
        sources    = movieManager.getSources(movie, 10);
        topics     = movieManager.getTopics(movie, 10);

//        if (! isAnonymous()) {
//            rating  = movieManager.getRating(oper, movie);
//            collect = movieManager.getCollect(oper, movie);
//            toTags  = movieManager.getTags(oper, movie);
//            myTags  = movieManager.getMyTags(oper, 8);
//            hotTags = movieManager.getHotTags(8);


//            intent = movieManager.getIntent(oper, movie);

//        }
        tags       = movieManager.getHotTags(movie, 8);

        stars      = movieManager.getStars(movie, 6);
        adverts    = movieManager.getAds("view");

        movieManager.updateHit(movie);
        return SUCCESS;
    }
    public String summary() {
        adverts    = movieManager.getAds("summary");
        return SUCCESS;
    }


    public String rssTops() throws IOException {
        Document config = movieManager.rssTops(20);

        if (config != null) {
            getResponse().setContentType("application/rss+xml");
            PrintWriter out = getResponse().getWriter();
            out.print(config.asXML());
        }
        return NONE;
    }
    public String rssNewz() throws IOException {
        Document config = movieManager.rssNewz(20);

        if (config != null) {
            getResponse().setContentType("application/rss+xml");
            PrintWriter out = getResponse().getWriter();
            out.print(config.asXML());
        }
        return NONE;
    }


    public String rssWants() throws IOException {
        if (Helper.isTransient(user)) return NONE;
        Document config = movieManager.rssWants(20, user);

        if (config != null) {
            getResponse().setContentType("application/rss+xml");
            PrintWriter out = getResponse().getWriter();
            out.print(config.asXML());
        }
        return NONE;
    }
    public String rssSeens() throws IOException {
        if (Helper.isTransient(user)) return NONE;
        Document config = movieManager.rssSeens(20, user);

        if (config != null) {
            getResponse().setContentType("application/rss+xml");
            PrintWriter out = getResponse().getWriter();
            out.print(config.asXML());
        }
        return NONE;
    }




    public String add() {
        redirect   = referer;
        return SUCCESS;
    }

    @Validations(
            requiredStrings={
                    @RequiredStringValidator(fieldName="movie.title", message="You must enter a value for title", trim=true, shortCircuit=true)
                    },
            fieldExpressions={
                    @FieldExpressionValidator(fieldName="category.id", expression="category.id > 0", message="You must select a category.", shortCircuit=true)
                    }
    )
    public String save() {
        movie.setUser(oper);

        movie.setEditTime(now);
        if (Helper.isTransient(movie)) {
            movie.setAddTime(now);
            movie.setViewTime(now);
        }

        movie.setAka(StringUtil.concat(akas, " / ", null));
        movie.setCast(StringUtil.concat(casts, " / ", null));
        movie.setLang(StringUtil.concat(langs, " / ", null));
        movie.setWriter(StringUtil.concat(writers, " / ", null));
        movie.setCountry(StringUtil.concat(countries, " / ", null));
        movie.setDirector(StringUtil.concat(directors, " / ", null));

        movieManager.store(movie);

        redirect = StringUtil.append(options.getUrl(), "/") + "movie/" + movie.getId();
        return SUCCESS;
    }
    @Validations(
            requiredStrings={
                    @RequiredStringValidator(fieldName="movie.douban", message="You must enter a value for douban id", trim=true, shortCircuit=true)
                    }
    )
    public String fetchDouban() {
        movie.setUser(oper);

        movie.setAddTime(now);
        movie.setEditTime(now);
        movie.setViewTime(now);

        movie = movieManager.storeDouban(movie);

        if (Helper.isTransient(movie)) {
            redirect = StringUtil.append(options.getUrl(), "/");
        } else {
            redirect = StringUtil.append(options.getUrl(), "/") + "movie/" + movie.getId();
        }
        return SUCCESS;
    }
    @Validations(
            requiredStrings={
                    @RequiredStringValidator(fieldName="doubanTag", message="You must enter a value for douban tag", trim=true, shortCircuit=true)
                    },
            fieldExpressions={
                    @FieldExpressionValidator(fieldName="beg", expression="beg > 0", message="You must enter a value for begin.", shortCircuit=true)
                    }
    )
    public String batchDouban() throws IOException {
        if (null == num) num = 100;

        StringBuffer us = new StringBuffer();
        us.append(options.getDoubanSearch()).append("?tag=").append(doubanTag);
        us.append("&start-index=").append(beg);
        us.append("&max-results=").append(num);

        URL url = URLUtil.getURL(us.toString());
        if (null != url) {
            log.debug("douban: douban search is not null");
            Document xml;
            try {
                xml = XMLUtil.read(url);
            } catch (SimpleException e) {
                log.debug("problem get douban search");
                return SUCCESS;
            }

            List<String> ss = XMLUtil.getTexts(xml, "atom", "http://www.w3.org/2005/Atom", "//atom:id", true);
            if (null == ss) return SUCCESS;
            for (String s : ss) {
                String did = RegexUtil.get(s, options.getDoubanApi() + "(\\d+)", 1);

                Movie e = movieManager.getByDouban(did);
                if (null == e) {
                    e = new Movie();
                    e.setUser(oper);
//                    e.setCategory(category);
                    e.setAddTime(now);
                    e.setEditTime(now);
                    e.setViewTime(now);

                    e.setDouban(did);
                    movieManager.storeDouban(e);
                }
            }

        }
//        redirect = StringUtil.append(options.getUrl(), "/") + StringUtil.urlEncode(category.getIdc());

        return SUCCESS;
    }

    public String edit() {
        redirect   = referer;

        return SUCCESS;
    }
    public String saveEditDouban() {
        movie.setEditTime(now);

        movie = movieManager.fetchDouban(movie);

        redirect = StringUtil.append(options.getUrl(), "/") + "movie/" + movie.getId();
        return SUCCESS;
    }
    public String saveEdit() {
        movie.setEditTime(now);

        movie.setAka(StringUtil.concat(akas, " / ", null));
        movie.setCast(StringUtil.concat(casts, " / ", null));
        movie.setLang(StringUtil.concat(langs, " / ", null));
        movie.setWriter(StringUtil.concat(writers, " / ", null));
        movie.setCountry(StringUtil.concat(countries, " / ", null));
        movie.setDirector(StringUtil.concat(directors, " / ", null));

        movieManager.savePinyin(movie);

        redirect = StringUtil.append(options.getUrl(), "/") + "movie/" + movie.getId();
        return SUCCESS;
    }

    public String saveGenusRegion() {
        movieManager.save(movie);

        redirect = StringUtil.append(options.getUrl(), "/") + "movie/" + movie.getId();
        return SUCCESS;
    }

    public String persist() {
        movieManager.savePinyin(movie);
        return SUCCESS;
    }
    public String remove() {
        movieManager.remove(movie);

//        redirect = StringUtil.append(options.getUrl(), "/") + StringUtil.urlEncode(movie.getCategory().getIdc());
        return SUCCESS;
    }

    public String makePoster() {
        movieManager.makePoster(pict);
        return SUCCESS;
    }


    public String sitemaps() throws IOException {
        Document config = movieManager.sitemaps();

        if (config != null) {
            getResponse().setContentType("text/xml");
            PrintWriter out = getResponse().getWriter();
            out.print(config.asXML());
        }
        return NONE;
    }
    public String sitemap() throws IOException {
        Document config;
//        if (Helper.isTransient(category)) {
            config = movieManager.sitemap();
//        } else {
//            config = movieManager.sitemap(category);
//        }

        if (config != null) {
            getResponse().setContentType("text/xml");
            PrintWriter out = getResponse().getWriter();
            out.print(config.asXML());
        }
        return NONE;
    }

    public String urllist() throws IOException {
        String urllist = movieManager.urllist();

        if (! StringUtil.isEmpty(urllist)) {
            getResponse().setContentType("text/plain");
            PrintWriter out = getResponse().getWriter();
            out.print(urllist);
        }
        return NONE;
    }



    private Long begId;
    private Long endId;

    public Long getBegId() {
        return begId;
    }
    public void setBegId(Long begId) {
        this.begId = begId;
    }

    public Long getEndId() {
        return endId;
    }
    public void setEndId(Long endId) {
        this.endId = endId;
    }


    public String index() {
        movieManager.index(begId, endId);
        return SUCCESS;
    }



    public String recommend() {
        movieManager.recommend(movie);
        return SUCCESS;
    }

    public String unrecommend() {
        movieManager.unrecommend(movie);
        return SUCCESS;
    }



    ////////
    public void validateSave() {
        Helper.makeStringNull(movie);
    }
    public void validateFetchDouban() {
        if (! Helper.isTransient(movieManager.getByDouban(movie.getDouban()))) {
            addActionError(getText("mail.used", "This movie has existed"));
        }
    }
*/


//    // override to auth
//    public Boolean doAuthz(String action) {
//        return true;
//    }


}
