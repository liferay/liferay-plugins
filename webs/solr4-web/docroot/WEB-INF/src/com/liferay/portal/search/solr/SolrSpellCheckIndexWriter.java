/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.search.solr;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseGenericSpellCheckIndexWriter;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.PortletKeys;

import org.apache.solr.client.solrj.SolrServer;

/**
 * @author Daniela Zapata Riesco
 * @author David Gonzalez
 * @author Michael C. Han
 */
public class SolrSpellCheckIndexWriter
	extends BaseGenericSpellCheckIndexWriter {

	@Override
	public void clearQuerySuggestionDictionaryIndexes(
			SearchContext searchContext)
		throws SearchException {

		String deleteQuery = createDeleteQuery(
			searchContext, QUERY_SUGGESTION_TYPE);

		try {
			_solrServer.deleteByQuery(deleteQuery);

			if (_commit) {
				_solrServer.commit();
			}
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			throw new SearchException(e.getMessage(), e);
		}
	}

	@Override
	public void clearSpellCheckerDictionaryIndexes(SearchContext searchContext)
		throws SearchException {

		String deleteQuery = createDeleteQuery(
			searchContext, SPELL_CHECKER_TYPE);

		try {
			_solrServer.deleteByQuery(deleteQuery);

			if (_commit) {
				_solrServer.commit();
			}
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			throw new SearchException(e.getMessage(), e);
		}

	}

	public void setCommit(boolean commit) {
		_commit = commit;
	}

	public void setSolrServer(SolrServer solrServer) {
		_solrServer = solrServer;
	}

	protected StringBundler addQuerySeparator(StringBundler sb) {
		sb.append(StringPool.SPACE);
		sb.append(StringPool.PLUS);

		return sb;
	}

	protected StringBundler addTypeQuery(StringBundler sb, String type) {
		sb.append(Field.TYPE);
		sb.append(StringPool.COLON);
		sb.append(type);

		return sb;
	}

	protected String createDeleteQuery(
		SearchContext searchContext, String type) {

		StringBundler sb = new StringBundler(14);

		sb.append(StringPool.PLUS);
		sb.append(Field.COMPANY_ID);
		sb.append(StringPool.COLON);
		sb.append(searchContext.getCompanyId());

		addQuerySeparator(sb);

		sb.append(Field.PORTLET_ID);
		sb.append(StringPool.COLON);
		sb.append(PortletKeys.SEARCH);

		addQuerySeparator(sb);

		addTypeQuery(sb, type);

		return sb.toString();
	}

	private static Log _log = LogFactoryUtil.getLog(
		SolrSpellCheckIndexWriter.class);

	private boolean _commit;
	private SolrServer _solrServer;

}