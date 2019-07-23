package org.gaozou.jimmy.vms.action;

import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import org.gaozou.jimmy.base.Helper;
import org.gaozou.jimmy.vms.domain.*;
import org.gaozou.jimmy.vms.manager.StarManager;
import org.gaozou.kevin.utility.RegexUtil;
import org.gaozou.kevin.utility.StringUtil;
import org.gaozou.kevin.utility.URLUtil;

import java.util.List;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class StarAction extends Action<Star> {
    private StarManager starManager;
    private Star  star;
//    private Photo photo;

    private List<String> akas;

    private List<Star>  stars;
//    private List<Photo> photos;
    private List<Movie> movies;
    private List<News>  news;

    private List<Ad>   adverts;


    public StarManager getStarManager() {
        return starManager;
    }
    public void setStarManager(StarManager starManager) {
        this.starManager = starManager;
    }

    public Star getStar() {
        return star;
    }
    public void setStar(Star star) {
        this.star = star;
    }
//
//    public Photo getPhoto() {
//        return photo;
//    }
//    public void setPhoto(Photo photo) {
//        this.photo = photo;
//    }


    public List<String> getAkas() {
        return akas;
    }
    public void setAkas(List<String> akas) {
        this.akas = akas;
    }

    public List<Star> getStars() {
        return stars;
    }
    public void setStars(List<Star> stars) {
        this.stars = stars;
    }

//    public List<Photo> getPhotos() {
//        return photos;
//    }
//    public void setPhotos(List<Photo> photos) {
//        this.photos = photos;
//    }

    public List<Movie> getMovies() {
        return movies;
    }
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<News> getNews() {
        return news;
    }
    public void setNews(List<News> news) {
        this.news = news;
    }



    public List<Ad> getAdverts() {
        return adverts;
    }
    public void setAdverts(List<Ad> adverts) {
        this.adverts = adverts;
    }

    public void doPrepare() throws Exception {
        if (! Helper.isEmpty(star, "id"))  star  = starManager.get(star.getId());
//        if (! Helper.isEmpty(photo, "id")) photo = starManager.getPhoto(photo.getId());
    }

    public String search() {
        page       = starManager.search(q, page);
        return SUCCESS;
    }

    public String list() {
        page       = starManager.page(page);
//        movies    = starManager.randomMovies(10);
        //adverts    = starManager.getAds("collect-list");
        redirect = "stars";
        return SUCCESS;
    }
    public String view() {
//        categories = starManager.getCategories();
        movies = starManager.getMovies(star, 20);
//        photos     = starManager.getPhotos(star, 8);
        news       = starManager.getNews(star, 16);

        starManager.updateHit(star);

        //adverts = collectManager.getAds("collect-view");
        return SUCCESS;
    }
    public String detail() {
//        categories = starManager.getCategories();
        movies = starManager.getMovies(star, 10);
//        photos     = starManager.getPhotos(star, 10);
        news       = starManager.getNews(star, 16);

        //adverts = collectManager.getAds("collect-view");
        return SUCCESS;
    }

    public String add() {
        redirect = referer;
//        categories = starManager.getCategories();
        //hints = collectManager.getHints("add-collect");
        return SUCCESS;
    }

    @Validations(
            requiredStrings={
                    @RequiredStringValidator(fieldName="star.name", message="You must enter a value for name.", shortCircuit=true)
                    }
    )
    public String save() {
        star.setUser(oper);

        star.setAddTime(now);
        star.setEditTime(now);

        star.setAka(StringUtil.concat(akas, " / ", null));

        starManager.save(star);

        redirect = StringUtil.append(options.getUrl(), "/") + "star/" + star.getId();
        return SUCCESS;
    }

    public String edit() {
        redirect   = referer;
        return SUCCESS;
    }

    public String persist() {
        star.setEditTime(now);
        star.setAka(StringUtil.concat(akas, " / ", null));

        starManager.save(star);

        redirect = StringUtil.append(options.getUrl(), "/") + "star/" + star.getId();
        return SUCCESS;
    }

    public String remove() {
        redirect = StringUtil.append(options.getUrl(), "/") + "stars";

        starManager.remove(star);
        return SUCCESS;
    }


    public String makePhoto() {
//        starManager.makePhoto(photo);

        return SUCCESS;
    }


    public String manage() {
        redirect = referer;
        return SUCCESS;
    }




    private Integer beg;
    private Integer num;

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
    @Validations(
            fieldExpressions={
                    @FieldExpressionValidator(fieldName="beg", expression="beg > 0", message="You must enter a value for begin.", shortCircuit=true)
                    }
    )
    public String batchQQ() {
//        String pics = Helper.getOptions("qq.star.pics", "");
//        String pict = Helper.getOptions("qq.star.pict", "");

        String pics = options.getQqPics();
        String pict = options.getQqPict();


        if (null == num) num = 100;

        int i = 0;
        while (i < num) {
            i++;
            star = starManager.fetchQqent(oper, String.valueOf(beg++));

            if (! Helper.isTransient(star)) {
                List<String> l = RegexUtil.collect(URLUtil.getContent(pics.replaceAll("\\{ID\\}", star.getQqent())), "sOriginalImgUrl:\"(.*?)\"", true);
                for (String u : l) {
//                    starManager.storePhoto(oper, star, pict + u);
                }
            }
        }

        return SUCCESS;
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
        starManager.index(begId, endId);
        return SUCCESS;
    }


//    public String recommend() {
//        starManager.recommend(star);
//        return SUCCESS;
//    }
//
//    public String unrecommend() {
//        starManager.unrecommend(star);
//        return SUCCESS;
//    }
}
