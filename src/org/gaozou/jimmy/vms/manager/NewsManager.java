package org.gaozou.jimmy.vms.manager;

import org.gaozou.jimmy.base.Page;
import org.gaozou.jimmy.vms.domain.*;
import org.gaozou.jimmy.vms.searcher.NewsSearcher;

import java.util.List;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class NewsManager extends Manager<News> {
//    private CategoryManager categoryDAO;
//    private PortManager homeDAO;
//    private ImageManager    imageDAO;
    private AdManager adDAO;
    private NewsSearcher    newsSearcher;

//    public CategoryManager getCategoryDAO() {
//        return categoryDAO;
//    }
//    public void setCategoryDAO(CategoryManager categoryDAO) {
//        this.categoryDAO = categoryDAO;
//    }
//
//    public PortManager getHomeDAO() {
//        return homeDAO;
//    }
//    public void setHomeDAO(PortManager homeDAO) {
//        this.homeDAO = homeDAO;
//    }


//    public ImageManager getImageDAO() {
//        return imageDAO;
//    }
//    public void setImageDAO(ImageManager imageDAO) {
//        this.imageDAO = imageDAO;
//    }

    public AdManager getAdvertDAO() {
        return adDAO;
    }
    public void setAdvertDAO(AdManager adDAO) {
        this.adDAO = adDAO;
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


    public List<News> get(Integer count) {
        return query("select n from News n order by n.editTime desc", 0, count);
    }

    public Page<News> get(Page<News> page) {
        if (null == page) page = new Page<News>(1l);
        return query(page, "select n from News n order by n.editTime desc");
    }

    public List<News> tops(Integer c) {
        return query("select n from News n order by n.hit desc", 0, c);
    }






    public News getByPurl(String url) {
        List<News> ns = query("select n from News n where n.purl = ?1", url);
        if (ns.size() == 0) return null;
        return ns.get(0);
    }

    public Page<News> search(String q, Page<News> page) {
        return newsSearcher.search(q, page);
    }

    public News store(News news) {
        return save(news);
    }

/*

    public News fetchSina(User user, String url) {
//        "http://(.+?)\.(jpg|gif|png|bmp)"

        News news = getByPurl(url);
        if (null != news) return null;   // This url exits.

        URL u = URLUtil.getURL(url);
        if (! URLUtil.exists(u)) return null;  // This url does not exist.

        String c = URLUtil.getContent(u, "GB2312");
        if (StringUtil.isEmpty(c)) return null;

        StringBuffer content = new StringBuffer(RegexUtil.get(c, "<!-- 正文内容 begin -->(.*?)<!-- 正文内容 end -->", 1));
        System.out.println(": "+ content);



        news = new News();
        news.setUser(user);
        news.setAddTime(Helper.now());
        news.setEditTime(Helper.now());
        news.setTitle(RegexUtil.filtHTML(RegexUtil.get(c, "<h1 id=\"artibodyTitle\">(.*?)</h1>", 1)));
        news.setProvider("新浪网");
        news.setPurl("url");

        super.save(news);

        System.out.println(": "+ news.getTitle());

        List<String> is = RegexUtil.collect(content.toString(), "<img\\s+style=\"border:1px #000 solid;\"\\s+src=(http://(.+?)\\.(jpg|gif|png|bmp)).*?>", true);
        for (String i : is) {
            System.out.println(":::: "+ i);
            imageDAO.store(user, news, url);
            content.insert(0, "[img]" + i + "[/img]\n");
        }
        news.setContent(RegexUtil.filtHTML(content.toString().replaceAll("</p>|<p>|<center>.*?</center>|<table.*?</table>|<!--.*?-->|<style.*?</style>", "\n")));


        return news;
    }
*/



//    public News recommend(News news) {
//        news.setRec(Boolean.TRUE);
//        return save(news);
//    }
//    public News unrecommend(News news) {
//        news.setRec(Boolean.FALSE);
//        return save(news);
//    }


    public List<News> recs(Integer c) {
        return query("select n from News n where n.rec=true", 0, c);
    }



    public void index(Long begId, Long endId) {
        newsSearcher.index("select n from News n where n.id between ?1 and ?2", begId, endId);
    }
}
