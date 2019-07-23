package org.gaozou.jimmy.vms.action;

import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import org.gaozou.jimmy.base.Helper;
import org.gaozou.jimmy.vms.domain.Ad;
import org.gaozou.jimmy.vms.domain.Movie;
import org.gaozou.jimmy.vms.domain.Tag;
import org.gaozou.jimmy.vms.manager.TagManager;

import java.util.List;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class TagAction extends Action<Tag> {
    private TagManager tagManager;
    private Tag   tag;
    private Movie movie;

    private String tagNames;
    private String tagDescr;

    private List<Tag>      tags;
    private List<Ad>   adverts;


    public TagManager getTagManager() {
        return tagManager;
    }
    public void setTagManager(TagManager tagManager) {
        this.tagManager = tagManager;
    }

    public Tag getTag() {
        return tag;
    }
    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getTagNames() {
        return tagNames;
    }
    public void setTagNames(String tagNames) {
        this.tagNames = tagNames;
    }

    public String getTagDescr() {
        return tagDescr;
    }
    public void setTagDescr(String tagDescr) {
        this.tagDescr = tagDescr;
    }

    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }



    public List<Ad> getAdverts() {
        return adverts;
    }
    public void setAdverts(List<Ad> adverts) {
        this.adverts = adverts;
    }

    public void doPrepare() throws Exception {
        if (! Helper.isEmpty(tag, "id"))   tag   = tagManager.get(tag.getId());
        if (! Helper.isEmpty(movie, "id")) movie = tagManager.getMovie(movie.getId());
    }


    public String list() {
        return SUCCESS;
    }

    @Validations(
            requiredStrings={
                    @RequiredStringValidator(fieldName="tagNames", message="You must enter a value for tag name.", trim=true, shortCircuit=true)
                    },
            fieldExpressions={
                    @FieldExpressionValidator(fieldName="movie.id", expression="movie.id > 0", message="You must select a movie.", shortCircuit=true)
                    }
    )
    public String save() {
        tagManager.store(tagNames, oper, movie, tagDescr);
        return SUCCESS;
    }





//    // override to auth
//    public Boolean hasAuthz(String action) {
//        return true;
//    }
}
