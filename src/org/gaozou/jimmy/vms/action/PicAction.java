package org.gaozou.jimmy.vms.action;

import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import org.gaozou.jimmy.base.Helper;
import org.gaozou.jimmy.base.MultiUpload;
import org.gaozou.jimmy.base.Upload;
import org.gaozou.jimmy.vms.domain.Ad;
import org.gaozou.jimmy.vms.domain.Movie;
import org.gaozou.jimmy.vms.domain.Pic;
import org.gaozou.jimmy.vms.manager.PicManager;
import org.gaozou.kevin.utility.RegexUtil;
import org.gaozou.kevin.utility.StringUtil;
import org.gaozou.kevin.utility.URLUtil;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: george
 * Powered by GaoZou group.
 */
@SuppressWarnings("unchecked")
public class PicAction extends Action<Pic> implements Upload, MultiUpload {
    private PicManager picManager;
    private Pic pict;
    private Movie movie;

    private List<Pic>     picts;
    private List<Movie>    movies;
//    private List<Category> categories;
    private List<Ad>   adverts;


    private File   upload;
    private String uploadContentType;
    private String uploadFileName;


    private List<File>   uploads;
    private List<String> uploadsFileName;
    private List<String> uploadsContentType;

    private List<String> urls;

    private String surl;


    public PicManager getPictManager() {
        return picManager;
    }
    public void setPictManager(PicManager picManager) {
        this.picManager = picManager;
    }

    public Pic getPict() {
        return pict;
    }
    public void setPict(Pic pict) {
        this.pict = pict;
    }

    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Pic> getPicts() {
        return picts;
    }
    public void setPicts(List<Pic> picts) {
        this.picts = picts;
    }

    public List<Movie> getMovies() {
        return movies;
    }
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }


    public List<Ad> getAdverts() {
        return adverts;
    }
    public void setAdverts(List<Ad> adverts) {
        this.adverts = adverts;
    }


    public File getUpload() {
        return upload;
    }
    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }
    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }



    public List<File> getUploads() {
        return null == uploads ? uploads = new ArrayList<File>() : uploads;
    }
    public void setUploads(List<File> uploads) {
        this.uploads = uploads;
    }

    public List<String> getUploadsFileName() {
        return null == uploadsFileName ? uploadsFileName = new ArrayList<String>() : uploadsFileName;
    }
    public void setUploadsFileName(List<String> uploadsFileName) {
        this.uploadsFileName = uploadsFileName;
    }

    public List<String> getUploadsContentType() {
        return null == uploadsContentType ? uploadsContentType = new ArrayList<String>() : uploadsContentType;
    }
    public void setUploadsContentType(List<String> uploadsContentType) {
        this.uploadsContentType = uploadsContentType;
    }

    public List<String> getUrls() {
        return urls;
    }
    public void setUrls(List<String> urls) {
        this.urls = urls;
    }


    public String getSurl() {
        return surl;
    }
    public void setSurl(String surl) {
        this.surl = surl;
    }


    public void doPrepare() throws Exception {
        if (! Helper.isEmpty(pict, "id"))  pict  = picManager.get(pict.getId());
        if (! Helper.isEmpty(movie, "id")) movie = picManager.getMovie(movie.getId());
    }



    public String list() {
//        categories = picManager.getCategories();
        if (Helper.isTransient(movie)) {
            page = picManager.page(page);
            movies = picManager.randomMovies(10);
            redirect = "picts";
        } else {
            page = picManager.page(page, movie);
            //"^/[A-Za-z0-9]+/(\d+)_picts_(\d+)/*$"
            redirect = StringUtil.append(options.getUrl(), "/") + "movie/" + movie.getId() + "_picts";
        }
        adverts = picManager.getAdverts("pict-list");
        return SUCCESS;
    }

    public String view() {
//        categories = picManager.getCategories();
        adverts    = picManager.getAdverts("pict-view");
        return SUCCESS;
    }
    public String add() {
        redirect = referer;
//        categories = picManager.getCategories();
        //hints = collectManager.getHints("add-collect");
        return SUCCESS;
    }
    @Validations(
            fieldExpressions={
                    @FieldExpressionValidator(fieldName="movie.id", expression="movie.id > 0", message="You must select a movie.", shortCircuit=true)
                    }
    )
    public String fancy() throws IOException {
//        picManager.store(oper, movie, upload);

        JSONObject jo = new JSONObject();
        jo.put("result", "success");
        jo.put("size",   upload.length());

        getResponse().setContentType("application/json");
        PrintWriter out = getResponse().getWriter();
        out.print(jo);

        return NONE;
    }
    @Validations(
            fieldExpressions={
                    @FieldExpressionValidator(fieldName="movie.id", expression="movie.id > 0", message="You must select a movie.", shortCircuit=true)
                    }
    )
    public String upload() {
        for (File file : uploads) {
//            picManager.store(oper, movie, file);
        }

        return SUCCESS;
    }
    @Validations(
            fieldExpressions={
                    @FieldExpressionValidator(fieldName="movie.id", expression="movie.id > 0", message="You must select a movie.", shortCircuit=true)
                    }
    )
    public String fetch() {
        for (String url : urls) {
            if (URLUtil.exists(url)) {
//                picManager.store(oper, movie, url);
            }
        }

        return SUCCESS;
    }
    @Validations(
            requiredStrings={
                    @RequiredStringValidator(fieldName="surl", message="You must enter a value for sina url", trim=true, shortCircuit=true)
                    },
            fieldExpressions={
                    @FieldExpressionValidator(fieldName="movie.id", expression="movie.id > 0", message="You must select a movie.", shortCircuit=true)
                    }
    )
    public String batchSina() {
        String ent = "http://ent.sina.com.cn";

        List<String> l = RegexUtil.collect(URLUtil.getContent(surl.startsWith("http://") ? surl : ent + surl), "<td\\s+width=82\\s+align=center><a href=\"?([^\"\\s>]+)\"?\\s*[^>]*>", true);

        List<String> is = new ArrayList<String>();
        for (String u : l) {
            if (StringUtil.isEmpty(u)) continue;
            is.add(RegexUtil.get(URLUtil.getContent(u.startsWith("http://") ? u : ent + u), "<center><img.*?src=\"?([^\"\\s>]+)\"?\\s*[^>]*>", 1));
        }
        for (String i : is) {
            if (StringUtil.isEmpty(i)) continue;
//            picManager.store(oper, movie, i.startsWith("http://") ? i : ent + i);
        }

        redirect = StringUtil.append(options.getUrl(), "/") + "movie/" + movie.getId();
        return SUCCESS;
    }


    public String edit() {
        redirect   = referer;
//        categories = picManager.getCategories();
        return SUCCESS;
    }

    public String persist() {
        picManager.save(pict);
        return SUCCESS;
    }
    public String remove() {
//        movie = pict.getMovie();
        redirect = StringUtil.append(options.getUrl(), "/") + "movie/" + movie.getId() + "_picts";
        picManager.remove(pict);
        return SUCCESS;
    }


    public String manage() {
        redirect = referer;
        return SUCCESS;
    }




//    // override to auth
//    public Boolean doAuthz(String action) {
//        return true;
//    }

}
