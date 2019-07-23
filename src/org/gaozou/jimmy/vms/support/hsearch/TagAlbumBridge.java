package org.gaozou.jimmy.vms.support.hsearch;

import org.hibernate.search.bridge.FieldBridge;
import org.hibernate.search.bridge.LuceneOptions;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.gaozou.jimmy.vms.domain.tag.TagMovie;
import org.gaozou.jimmy.vms.domain.tag.TagAlbum;

import java.util.List;

/**
 * Author: george
 * Powered by GaoZou group.
 */
@SuppressWarnings("unchecked")
public class TagAlbumBridge implements FieldBridge {
    @Override
    public void set(String s, Object o, Document document, LuceneOptions luceneOptions) {
        List<TagAlbum> ts = (List<TagAlbum>) o;
        StringBuffer sb = new StringBuffer();

        for (TagAlbum t : ts) {
            if (sb.length() > 0) sb.append(',');
            sb.append(t.getTag().getName());
        }

        document.add(new Field(s, sb.toString(), luceneOptions.getStore(), luceneOptions.getIndex(), luceneOptions.getTermVector()));
    }
}
