package org.gaozou.jimmy.vms.manager.tag;

import org.gaozou.jimmy.vms.domain.tag.TagMovie;
import org.gaozou.jimmy.vms.domain.Tag;
import org.gaozou.jimmy.vms.domain.User;
import org.gaozou.jimmy.vms.domain.Movie;
import org.gaozou.jimmy.vms.manager.Manager;

import java.util.List;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class TagMovieManager extends Manager<TagMovie> {
//    private TagManager      tagDAO;
//
//    public TagManager getTagDAO() {
//        return tagDAO;
//    }
//    public void setTagDAO(TagManager tagDAO) {
//        this.tagDAO = tagDAO;
//    }



    public TagMovie get(Tag tag, User user, Movie movie) {
        List<TagMovie> tums = query("select tum from TagUserMovie tum where tum.tag = ?1 and tum.user = ?2 and tum.movie = ?3", tag, user, movie);
        if (tums.size() == 0) return null;
        return tums.get(0);
    }

    public List<TagMovie> get(User user, Movie movie) {
        return query("select tum from TagUserMovie tum where tum.user = ?1 and tum.movie = ?2", user, movie);
    }


    public TagMovie store(TagMovie tag) {
        return save(tag);
    }


    public TagMovie store(Tag tag, User user, Movie movie, String des) {
        TagMovie tm = new TagMovie();
//        tm.setUser(user);
        tm.setMovie(movie);
//        movie.addTum(tm);
//        tm.setDscr(des);
//        tm.setAddTime(Helper.now());
//        tm.setEditTime(Helper.now());

        tag.addTagMovie(tm);
        return save(tm);
    }

/*

    public void store(String tags, User user, Entry entry, String des) {
        List<TagUserEntry> tues = get(user, entry);
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
                    tagDAO.remove(tue.getTag());
                } else {
                    remove(tue);
                }
            }
        }

        // save new
        Tag support;
        for (String t : ns) {
            support = tagDAO.get(t);
            if (null == support) {
                support = new Tag();
                support.setName(t);
            }
            store(support, user, entry, des);
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
