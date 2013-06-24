package com.liferay.portal.search.solr;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;

import java.lang.reflect.Field;

public class QueryTranslatorUtil {

    public static Object translateForSolr(Query query) {
        Object queryObject = query.getWrappedQuery();

        if (queryObject instanceof org.apache.lucene.search.Query) {
            adjustQuery((org.apache.lucene.search.Query)queryObject);
        }

        return query;
    }

    private static void adjustQuery(org.apache.lucene.search.Query query) {
        if (query instanceof org.apache.lucene.search.BooleanQuery) {
            org.apache.lucene.search.BooleanQuery booleanQuery = (org.apache.lucene.search.BooleanQuery)query;

            for (BooleanClause booleanClause : booleanQuery.getClauses()) {
                adjustQuery(booleanClause.getQuery());
            }
        }
        else if (query instanceof org.apache.lucene.search.TermQuery) {
            org.apache.lucene.search.TermQuery termQuery = (org.apache.lucene.search.TermQuery)query;

            Term term = termQuery.getTerm();

            try {
                String text = term.text();

                if (text.matches("^\\s*[^\"].*\\s+.*[^\"]\\s*$(?m)")) {
                    text = StringPool.QUOTE.concat(text).concat(
                            StringPool.QUOTE);

                    _textField.set(term, text);
                }
            }
            catch (Exception e) {
                _log.error(e, e);
            }
        }
        else if (query instanceof org.apache.lucene.search.WildcardQuery) {
            org.apache.lucene.search.WildcardQuery wildcardQuery = (org.apache.lucene.search.WildcardQuery)query;

            Term term = wildcardQuery.getTerm();

            try {
                String text = term.text();

                if (text.matches("^\\s*\\*.*(?m)") && !ArrayUtil.contains(PropsUtil.getArray("index.solr.like.search.fields"), term.field())) {
                    text = text.replaceFirst("\\*", StringPool.BLANK);

                    _textField.set(term, text);
                }
            }
            catch (Exception e) {
                _log.error(e, e);
            }
        }
    }

    private static Log _log = LogFactoryUtil.getLog(QueryTranslatorUtil.class);

    private static Field _textField = null;

    static {
        try {
            _textField = Term.class.getDeclaredField("text");

            _textField.setAccessible(true);
        }
        catch (Exception e) {
            _log.error(e, e);
        }
    }

}