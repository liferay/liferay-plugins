/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.portal.search.solr.internal;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.suggest.BaseGenericSpellCheckIndexWriter;
import com.liferay.portal.kernel.search.suggest.SpellCheckIndexWriter;
import com.liferay.portal.kernel.search.suggest.SuggestionConstants;
import com.liferay.portal.kernel.util.PortalRunMode;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.search.solr.document.SolrUpdateDocumentCommand;
import com.liferay.portal.search.solr.internal.util.LogUtil;

import java.util.Collection;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Daniela Zapata Riesco
 * @author David Gonzalez
 * @author Michael C. Han
 */
@Component(
	immediate = true, property = {"search.engine.impl=Solr"},
	service = SpellCheckIndexWriter.class
)
public class SolrSpellCheckIndexWriter
	extends BaseGenericSpellCheckIndexWriter {

	@Override
	public void clearQuerySuggestionDictionaryIndexes(
			SearchContext searchContext)
		throws SearchException {

		String deleteQuery = buildDeleteQuery(
			searchContext, SuggestionConstants.TYPE_QUERY_SUGGESTION);

		deleteByQuery(searchContext, deleteQuery);
	}

	@Override
	public void clearSpellCheckerDictionaryIndexes(SearchContext searchContext)
		throws SearchException {

		String deleteQuery = buildDeleteQuery(
			searchContext, SuggestionConstants.TYPE_SPELL_CHECKER);

		deleteByQuery(searchContext, deleteQuery);
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		setDocumentPrototype(new DocumentImpl());
	}

	@Override
	protected void addDocument(
			String documentType, SearchContext searchContext, Document document)
		throws SearchException {

		_solrUpdateDocumentCommand.updateDocument(
			searchContext, document, false);
	}

	@Override
	protected void addDocuments(
			String documentType, SearchContext searchContext,
			Collection<Document> documents)
		throws SearchException {

		_solrUpdateDocumentCommand.updateDocuments(
			searchContext, documents, false);
	}

	protected void addQuerySeparator(StringBundler sb) {
		sb.append(StringPool.SPACE);
		sb.append(StringPool.PLUS);
	}

	protected void addQueryType(StringBundler sb, String type) {
		sb.append(Field.TYPE);
		sb.append(StringPool.COLON);
		sb.append(type);
	}

	protected String buildDeleteQuery(
		SearchContext searchContext, String type) {

		StringBundler sb = new StringBundler(14);

		sb.append(StringPool.PLUS);
		sb.append(Field.COMPANY_ID);
		sb.append(StringPool.COLON);
		sb.append(searchContext.getCompanyId());

		addQuerySeparator(sb);

		sb.append(Field.SPELL_CHECK_WORD);
		sb.append(StringPool.COLON);
		sb.append(Boolean.TRUE.toString());

		addQuerySeparator(sb);

		addQueryType(sb, type);

		return sb.toString();
	}

	protected void deleteByQuery(
			SearchContext searchContext, String deleteQuery)
		throws SearchException {

		try {
			UpdateResponse updateResponse = _solrServer.deleteByQuery(
				deleteQuery);

			if (PortalRunMode.isTestMode() ||
				searchContext.isCommitImmediately()) {

				_solrServer.commit();
			}

			LogUtil.logSolrResponseBase(_log, updateResponse);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			throw new SearchException(e.getMessage(), e);
		}
	}

	@Reference(unbind = "-")
	protected void setSolrServer(SolrServer solrServer) {
		_solrServer = solrServer;
	}

	@Reference(unbind = "-")
	protected void setSolrUpdateDocumentCommand(
		SolrUpdateDocumentCommand solrUpdateDocumentCommand) {

		_solrUpdateDocumentCommand = solrUpdateDocumentCommand;
	}

	private static Log _log = LogFactoryUtil.getLog(
		SolrSpellCheckIndexWriter.class);

	private SolrServer _solrServer;
	private SolrUpdateDocumentCommand _solrUpdateDocumentCommand;

}