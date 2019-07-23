package org.gaozou.jimmy.vms.searcher;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.gaozou.jimmy.base.Page;
import org.gaozou.jimmy.base.Searcher;
import org.gaozou.jimmy.vms.domain.News;
import org.gaozou.kevin.utility.StringUtil;

import java.util.List;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class NewsSearcher extends Searcher<News> {
    private String[] fields = new String[]{"title", "content"};

    public List<News> search(String q, Integer start, Integer size) {
        q = StringUtil.urlDecode(q);
        if (StringUtil.isEmpty(q)) return null;

        setAnalyzer(new StandardAnalyzer());
        return search(StringUtil.urlDecode(q), fields, start, size);
    }
    public List<News> search(String q, Integer start, Integer size, String[] fields) {
        q = StringUtil.urlDecode(q);
        if (StringUtil.isEmpty(q)) return null;

        setAnalyzer(new StandardAnalyzer());
        return search(StringUtil.urlDecode(q), fields, start, size);
    }

    public Page<News> search(String q, Page<News> page) {
        if (null == page) page = new Page<News>(1l);
        q = StringUtil.urlDecode(q);
        if (StringUtil.isEmpty(q)) return page;

        setAnalyzer(new StandardAnalyzer());
        search(page, q, fields);
        return page;
    }
}
