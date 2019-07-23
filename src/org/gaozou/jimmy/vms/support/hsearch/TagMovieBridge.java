package org.gaozou.jimmy.vms.support.hsearch;

import org.hibernate.search.bridge.FieldBridge;
import org.hibernate.search.bridge.LuceneOptions;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.gaozou.jimmy.vms.domain.tag.TagMovie;
import org.gaozou.kevin.utility.StringUtil;

import java.util.List;

/**
 * Author: george
 * Powered by GaoZou group.
 */
@SuppressWarnings("unchecked")
public class TagMovieBridge implements FieldBridge {
    public void set(String s, Object o, Document document, LuceneOptions luceneOptions) {
        List<TagMovie> ts = (List<TagMovie>) o;
        StringBuffer sb = new StringBuffer();

        for (TagMovie t : ts) {
            if (sb.length() > 0) sb.append(',');
            sb.append(t.getTag().getName());
        }

        document.add(new Field(s, sb.toString(), luceneOptions.getStore(), luceneOptions.getIndex(), luceneOptions.getTermVector()));
    }
}
