package org.gaozou.jimmy.vms.searcher;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.gaozou.jimmy.base.Page;
import org.gaozou.jimmy.base.Searcher;
import org.gaozou.jimmy.vms.domain.Movie;
import org.gaozou.kevin.utility.StringUtil;

import java.util.List;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class MovieSearcher extends Searcher<Movie> {
    private String[] fields = new String[]{"title", "chinese", "pinyin", "aka", "cast", "writer", "director", "lang", "date", "country", "company"};

    public List<Movie> search(String q, Integer start, Integer size) {
        q = StringUtil.urlDecode(q);
        if (StringUtil.isEmpty(q)) return null;

        setAnalyzer(new StandardAnalyzer());
        return search(StringUtil.urlDecode(q), fields, start, size);
    }
    public List<Movie> search(String q, Integer start, Integer size, String[] fields) {
        q = StringUtil.urlDecode(q);
        if (StringUtil.isEmpty(q)) return null;

        setAnalyzer(new StandardAnalyzer());
        return search(StringUtil.urlDecode(q), fields, start, size);
    }

    public Page<Movie> search(String q, Page<Movie> page) {
        if (null == page) page = new Page<Movie>(1l);
        q = StringUtil.urlDecode(q);
        if (StringUtil.isEmpty(q)) return page;

//        setAnalyzer(new StandardAnalyzer());
        search(page, q, fields);
        return page;
    }
    public Page<Movie> search(String q, Page<Movie> page, String[] fields) {
        if (null == page) page = new Page<Movie>(1l);
        q = StringUtil.urlDecode(q);
        if (StringUtil.isEmpty(q)) return page;

        setAnalyzer(new StandardAnalyzer());
        search(page, q, fields);
        return page;
    }


}
