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

package com.liferay.portal.search.solr;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexWriter;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.search.solr.document.SolrDocumentFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;

/**
 * @author Bruno Farache
 */
public class SolrIndexWriter extends BaseIndexWriter {

	@Override
	public void addDocument(SearchContext searchContext, Document document)
		throws SearchException {

		try {
			SolrInputDocument solrInputDocument =
				_solrDocumentFactory.getSolrInputDocument(document);

			_solrServer.add(solrInputDocument);

			if (_commit || searchContext.isCommitImmediately()) {
				_solrServer.commit();
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new SearchException(e.getMessage());
		}
	}

	@Override
	public void addDocuments(
			SearchContext searchContext, Collection<Document> documents)
		throws SearchException {

		try {
			Collection<SolrInputDocument> solrInputDocuments =
				getSolrInputDocuments(documents);

			if (solrInputDocuments.isEmpty()) {
				return;
			}

			_solrServer.add(solrInputDocuments);

			if (_commit || searchContext.isCommitImmediately()) {
				_solrServer.commit();
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new SearchException(e.getMessage());
		}
	}

	@Override
	public void deleteDocument(SearchContext searchContext, String uid)
		throws SearchException {

		try {
			_solrServer.deleteById(uid);

			if (_commit || searchContext.isCommitImmediately()) {
				_solrServer.commit();
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new SearchException(e.getMessage());
		}
	}

	@Override
	public void deleteDocuments(
			SearchContext searchContext, Collection<String> uids)
		throws SearchException {

		for (String uid : uids) {
			deleteDocument(searchContext, uid);
		}
	}

	@Override
	public void deleteEntityDocuments(
			SearchContext searchContext, String className)
		throws SearchException {

		try {
			long companyId = searchContext.getCompanyId();

			StringBundler sb = null;

			if (companyId > 0) {
				sb = new StringBundler(9);

				sb.append(StringPool.PLUS);
				sb.append(Field.COMPANY_ID);
				sb.append(StringPool.COLON);
				sb.append(companyId);
				sb.append(StringPool.SPACE);
			}

			if (sb == null) {
				sb = new StringBundler(4);
			}

			sb.append(StringPool.PLUS);
			sb.append(Field.ENTRY_CLASS_NAME);
			sb.append(StringPool.COLON);
			sb.append(className);

			_solrServer.deleteByQuery(sb.toString());

			if (_commit || searchContext.isCommitImmediately()) {
				_solrServer.commit();
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new SearchException(e.getMessage());
		}
	}

	public void setCommit(boolean commit) {
		_commit = commit;
	}

	public void setSolrDocumentFactory(
		SolrDocumentFactory solrDocumentFactory) {

		_solrDocumentFactory = solrDocumentFactory;
	}

	public void setSolrServer(SolrServer solrServer) {
		_solrServer = solrServer;
	}

	@Override
	public void updateDocument(SearchContext searchContext, Document document)
		throws SearchException {

		deleteDocument(searchContext, document.getUID());

		addDocument(searchContext, document);
	}

	@Override
	public void updateDocuments(
			SearchContext searchContext, Collection<Document> documents)
		throws SearchException {

		for (Document document : documents) {
			deleteDocument(searchContext, document.getUID());
		}

		addDocuments(searchContext, documents);
	}

	protected void doAddSolrField(
		SolrInputDocument solrInputDocument, Field field, float boost,
		String value, String localizedName) {

		solrInputDocument.addField(localizedName, value, boost);

		if (field.isSortable()) {
			String sortableFieldName = DocumentImpl.getSortableFieldName(
				localizedName);

			solrInputDocument.addField(sortableFieldName, value, boost);
		}
	}

	protected Collection<SolrInputDocument> getSolrInputDocuments(
		Collection<Document> documents) {

		List<SolrInputDocument> solrInputDocuments = new ArrayList<>(
			documents.size());

		for (Document document : documents) {
			SolrInputDocument solrInputDocument =
				_solrDocumentFactory.getSolrInputDocument(document);

			solrInputDocuments.add(solrInputDocument);
		}

		return solrInputDocuments;
	}

	private static Log _log = LogFactoryUtil.getLog(SolrIndexWriter.class);

	private boolean _commit;
	private SolrDocumentFactory _solrDocumentFactory;
	private SolrServer _solrServer;

}