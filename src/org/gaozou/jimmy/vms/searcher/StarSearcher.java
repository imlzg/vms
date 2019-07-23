package org.gaozou.jimmy.vms.searcher;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.gaozou.jimmy.base.Page;
import org.gaozou.jimmy.base.Searcher;
import org.gaozou.jimmy.vms.domain.Star;
import org.gaozou.kevin.utility.StringUtil;

import java.util.List;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class StarSearcher extends Searcher<Star> {
    private String[] fields = new String[]{"name", "english", "autonym", "aka"};

    public List<Star> search(String q, Integer start, Integer size) {
        q = StringUtil.urlDecode(q);
        if (StringUtil.isEmpty(q)) return null;

        setAnalyzer(new StandardAnalyzer());
        return search(StringUtil.urlDecode(q), fields, start, size);
    }
    public List<Star> search(String q, Integer start, Integer size, String[] fields) {
        q = StringUtil.urlDecode(q);
        if (StringUtil.isEmpty(q)) return null;

        setAnalyzer(new StandardAnalyzer());
        return search(StringUtil.urlDecode(q), fields, start, size);
    }

    public Page<Star> search(String q, Page<Star> page) {
        if (null == page) page = new Page<Star>(1l);
        q = StringUtil.urlDecode(q);
        if (StringUtil.isEmpty(q)) return page;

        setAnalyzer(new StandardAnalyzer());
        search(page, q, fields);
        return page;
    }
}
