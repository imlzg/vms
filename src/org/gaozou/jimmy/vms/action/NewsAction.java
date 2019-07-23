package org.gaozou.jimmy.vms.action;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import org.gaozou.jimmy.base.Helper;
import org.gaozou.jimmy.vms.domain.Ad;
import org.gaozou.jimmy.vms.domain.News;
import org.gaozou.jimmy.vms.manager.NewsManager;

import java.util.List;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class NewsAction extends Action<News> {
    private NewsManager newsManager;
    private News news;

    private List<News> newz;
    private List<Ad>   adverts;

    public NewsManager getNewsManager() {
        return newsManager;
    }
    public void setNewsManager(NewsManager newsManager) {
        this.newsManager = newsManager;
    }

    public News getNews() {
        return news;
    }
    public void setNews(News news) {
        this.news = news;
    }

    public List<News> getNewz() {
        return newz;
    }
    public void setNewz(List<News> newz) {
        this.newz = newz;
    }


    public List<Ad> getAdverts() {
        return adverts;
    }
    public void setAdverts(List<Ad> adverts) {
        this.adverts = adverts;
    }



    public void doPrepare() throws Exception {
        if (! Helper.isEmpty(news, "id")) news = newsManager.get(news.getId());
    }

    public String search() {
        page       = newsManager.search(q, page);
        return SUCCESS;
    }

    public String list() {
        page    = newsManager.get(page);
        adverts = newsManager.getAdverts("news-list");
        return SUCCESS;
    }
    public String view() {
        //adverts = collectManager.getAds("collect-view");
        return SUCCESS;
    }

    public String add() {
        redirect   = referer;
        //hints = collectManager.getHints("add-collect");
        return SUCCESS;
    }
    @Validations(
            requiredStrings={
                    @RequiredStringValidator(fieldName="news.title",   message="You must enter a value for title", trim=true, shortCircuit=true),
                    @RequiredStringValidator(fieldName="news.content", message="You must enter a value for content.", shortCircuit=true)
                    }
    )
    public String save() {
        news.setUser(oper);

        news.setAddTime(now);
        news.setEditTime(now);

        newsManager.store(news);
        return SUCCESS;
    }

    public String edit() {
        redirect   = referer;
        return SUCCESS;
    }

    public String persist() {
        newsManager.save(news);
        return SUCCESS;
    }

    public String remove() {
        newsManager.remove(news);
        return SUCCESS;
    }


    public String url;

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
/*
    public String fetchSina() {
        news = newsManager.fetchSina(oper, url);
        redirect = StringUtil.append(options.getUrl(), "/") + "news";
        return NONE;
    }

    public String batchSina() {

        URL u = URLUtil.getURL(url);
        if (null != u) {
            log.debug("sina url is not null.");
            Document xml;
            try {
                xml = XMLUtil.read(u);
            } catch (SimpleException e) {
                log.debug("problem get sina rss.");
                return SUCCESS;
            }

            List<Element> items = RssUtil.items(xml);
            for (Element e : items) {
                String url = RegexUtil.get(e.element("guid").getText(), "url=(.*)", 1);
                System.out.println(":: "+ url);
                newsManager.fetchSina(oper, url);
            }

        }
        redirect = StringUtil.append(options.getUrl(), "/") + "news";

        return NONE;
    }
*/


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



    public String recommend() {
//        newsManager.recommend(news);
        return SUCCESS;
    }
    public String unrecommend() {
//        newsManager.unrecommend(news);
        return SUCCESS;
    }



    public String index() {
        newsManager.index(begId, endId);
        return SUCCESS;
    }
}
