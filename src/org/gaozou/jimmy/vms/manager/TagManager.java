package org.gaozou.jimmy.vms.manager;

import org.gaozou.jimmy.vms.domain.*;
import org.gaozou.jimmy.vms.domain.tag.TagMovie;
import org.gaozou.jimmy.vms.domain.Tag;
import org.gaozou.jimmy.vms.manager.tag.TagMovieManager;
import org.gaozou.kevin.utility.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: george
 * Powered by GaoZou group.
 */
@SuppressWarnings("unchecked")
public class TagManager extends Manager<Tag> {
//    private CategoryManager categoryDAO;
    private MovieManager    movieDAO;
    private AdManager adDAO;

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


    ////
//    public List<Category> getCategories() {
//        return categoryDAO.get();
//    }
    public Movie getMovie(Serializable movieId) {
        return movieDAO.get(movieId);
    }
    public List<Ad> getAdverts(String idc) {
        return adDAO.query(idc);
    }


    public Tag get(String name) {
        List<Tag> tags = query("select t from Tag t where t.name = ?1", StringUtil.urlDecode(name));
        if (tags.size() == 0) return null;
        return tags.get(0);
    }

    public List<Tag> query(String name) {
        return query("select t from Tag t where t.name = ?1", StringUtil.urlDecode(name));
    }

    public List<String> get(Integer count) {
        return createQuery("select distinct(t.name) from Tag t group by t.name order by count(t.name)").setFirstResult(0).setMaxResults(count).getResultList();
    }
//    public List<String> get(Category category, Integer count) {
//        return createQuery("select distinct(t.name) from Tag t where t.entry.category = ?1 group by t.name order by count(t.name)", category).setFirstResult(0).setMaxResults(count).getResultList();
//    }
    public List<String> get(Movie movie, Integer count) {
        return createQuery("select distinct(t.name) from Tag t where t.movie = ?1 group by t.name order by count(t.name)", movie).setFirstResult(0).setMaxResults(count).getResultList();
    }
//    public List<String> get(User user, Integer count) {
//        return createQuery("select distinct(t.name) from Tag t where t.user = ?1 group by t.name order by count(t.name)", user).setFirstResult(0).setMaxResults(count).getResultList();
//    }
    public List<Tag> get(User user, Integer c) {
        return query("select distinct(tum.tag) from TagUserMovie tum where tum.user = ?1 group by tum.tag order by count(tum.tag)", 0, c, user);
    }

//    public List<Tag> get(User user, Entry entry) {
//        return query("select t from Tag t where t.user = ?1 and t.entry = ?2", user, entry);
//    }
    public List<Tag> get(User user, Movie movie) {
        return query("select distinct(tum.tag) from TagUserMovie tum where tum.user = ?1 and tum.movie = ?2", user, movie);
    }


/*
    public List<Object[]> getHot(Integer count) {
        return createQuery("select distinct(t.name), count(t.name) from Tag t group by t.name").setFirstResult(0).setMaxResults(count).getResultList();
    }
//    public List<Object[]> getHot(Integer count) {
//        return createQuery("select distinct(t.name), count(t.name) from Tag t group by t.name order by count(t.name) desc").setFirstResult(0).setMaxResults(count).getResultList();
//    }
    public List<Object[]> getHot(Entry entry, Integer count) {
        return createQuery("select distinct(t.name), count(t.name) from Tag t where t.entry = ?1 group by t.name order by count(t.name)", entry).setFirstResult(0).setMaxResults(count).getResultList();
    }
    public List<Object[]> getHot(Genus genus, Integer count) {
        return createQuery("select distinct(t.name), count(t.name) from Tag t where t.entry.genus = ?1 group by t.name order by count(t.name)", genus).setFirstResult(0).setMaxResults(count).getResultList();
    }
    public List<Object[]> getHot(Region region, Integer count) {
        return createQuery("select distinct(t.name), count(t.name) from Tag t where t.entry.region = ?1 group by t.name order by count(t.name)", region).setFirstResult(0).setMaxResults(count).getResultList();
    }
    public List<Object[]> getHot(Category category, Integer count) {
        return createQuery("select distinct(t.name), count(t.name) from Tag t where t.entry.category = ?1 group by t.name order by count(t.name)", category).setFirstResult(0).setMaxResults(count).getResultList();
    }
*/

    public List<Tag> getHot1(Integer c) {
        return query("select t from Tag t order by t.hit desc", 0, c);
    }
    public List<Tag> getHot(Integer c) {
        return query("select t from Tag t order by size(t.tums) desc, t.hit desc", 0, c);
    }
//    public List<Tag> getHot(Category category, Integer c) {
//        return query("select distinct t from Tag t, Entry e where e member of t.entries and e.category = ?1 order by size(t.entries), size(t.users)", 0, c, category);
//    }
//    public List<Tag> getHot(Entry entry, Integer c) {
//        return query("select t from Tag t where ?1 member of t.entries order by size(t.entries), size(t.users)", 0, c, entry);
//    }
    public List<Tag> getHot(Movie movie, Integer c) {
        return query("select distinct(tum.tag) from TagUserMovie tum where tum.movie = ?1 group by tum.tag order by count(tum.tag)", 0, c, movie);
    }


    public Tag updateHit(Tag tag) {
        Integer h = tag.getHit();
        if (null == h) h = 0;
        tag.setHit(h + 1);
        return save(tag);
    }


    public Tag store(Tag tag) {
        return save(tag);
    }



    private TagMovieManager tumDAO;

    public TagMovieManager getTumDAO() {
        return tumDAO;
    }
    public void setTumDAO(TagMovieManager tumDAO) {
        this.tumDAO = tumDAO;
    }


//    public void store(String tags, User user, Entry entry, String des) {
//        tumDAO.store(tags, user, entry, des);
//    }


    public void store(String tags, User user, Movie movie, String des) {
        List<TagMovie> tums = tumDAO.get(user, movie);
        List<String>       ns   = new ArrayList<String>();
        String[]           ts   = tags.split("\\s+");

        Boolean ex;

        // remove exits
        for (String t : ts) {
            ex = false;
            for (TagMovie tum : tums) {
                if (t.equals(tum.getTag().getName())) {
                    ex = true;
                    break;
                }
            }
            if (! ex) ns.add(t);
        }

        // delete exits in db but not exits in new tags
        for (TagMovie tum : tums) {
            ex = false;
            for (String t : ts) {
                if (tum.getTag().getName().equals(t)) {
                    ex = true;
                    break;
                }
            }
            if (! ex) {
                if (tum.getTag().getTagMovies().size() < 1) {
                    remove(tum.getTag());
                } else {
                    tumDAO.remove(tum);
                }
            }
        }

        // save new
        Tag tag;
        for (String t : ns) {
            tag = get(t);
            if (null == tag) {
                tag = new Tag();
                tag.setName(t);
            }
            tumDAO.store(tag, user, movie, des);
        }
    }





/////////////////////////////


/*
    public void store(String tags, User user, Entry entry, String des) {
        List<String> ns = new ArrayList<String>();
        String[]     ts = tags.split("\\s+");
        for (String t : ts) {
            Tag support = get(t);
            if (null == support) {
                support = new Tag();
                support.setName(t);
                save(support);

            } else {
                TagUserEntry tue = tumDAO.get(support, user, entry);
                if (null == tue) {

                }
            }
        }


        for (String t : ts) {

        }


    }
*/


/*

    public void store(String tags, User user, Entry entry, String des) {
        List<TagUserEntry> tues = tumDAO.get(user, entry);
        List<String>       ns   = new ArrayList<String>();
        String[]           ts   = tags.split("\\s+");

        Boolean ex;

        // remove exits
        for (String t : ts) {
            ex = false;
            for (TagUserEntry tue : tues) {
                if (t.equals(tue.getTag().getName())) {
                    ex = true;
                    break;
                }
            }
            if (! ex) ns.add(t);
        }

        // delete exits in db but not exits in new tags
        for (TagUserEntry tue : tues) {
            ex = false;
            for (String t : ts) {
                if (tue.getTag().getName().equals(t)) {
                    ex = true;
                    break;
                }
            }
            if (! ex) {
                if (tue.getTag().getTagMovies().size() < 1) {
                    remove(tue.getTag());
                } else {
                    tumDAO.remove(tue);
                }
            }
        }

        // save new
        Tag support;
        for (String t : ns) {
            support = get(t);
            if (null == support) {
                support = new Tag();
                support.setName(t);
                save(support);
            }
            tumDAO.store(support, user, entry, des);
        }
    }
*/

/*
    public void store(User user, Entry entry, String tagNames, String tagDescr) {
        List<Tag> tags = get(user, entry);
        List<String> ns = new ArrayList<String>();

        String[] ts = tagNames.split("\\s+");

        Boolean ex;
        // remove exits
        for (String t : ts) {
            ex = false;
            for (Tag tg : tags) {
                if (t.equals(tg.getName())) {
                    ex = true;
                    break;
                }
            }
            if (! ex) ns.add(t);
        }

        // delete exits in db but not exits in new tags
        for (Tag tg : tags) {
            ex = false;
            for (String t : ts) {
                if (tg.getName().equals(t)) {
                    ex = true;
                    break;
                }
            }
            if (! ex) remove(tg);
        }

        // save new
        Tag support;
        for (String t : ns) {
            support = new Tag();
            support.setName(t);
            support.setDscr(tagDescr);

            support.setAddTime(Helper.now());
            support.setEditTime(Helper.now());
            support.setUser(user);
            entry.addTag(support);

            save(support);
        }
    }
*/


}
