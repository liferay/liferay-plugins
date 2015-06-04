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
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.PortalRunMode;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.search.solr.document.SolrDocumentFactory;
import com.liferay.portal.search.solr.document.SolrUpdateDocumentCommand;
import com.liferay.portal.search.solr.internal.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = SolrUpdateDocumentCommand.class)
public class SolrUpdateDocumentCommandImpl
	implements SolrUpdateDocumentCommand {

	@Override
	public String updateDocument(
			SearchContext searchContext, Document document, boolean deleteFirst)
		throws SearchException {

		UpdateResponse updateResponse = doUpdateDocuments(
			searchContext, Arrays.asList(document), deleteFirst);

		// TODO

		return StringPool.BLANK;
	}

	@Override
	public void updateDocuments(
			SearchContext searchContext, Collection<Document> documents,
			boolean deleteFirst)
		throws SearchException {

		if (documents.isEmpty()) {
			return;
		}

		doUpdateDocuments(searchContext, documents, deleteFirst);
	}

	protected UpdateResponse doUpdateDocuments(
			SearchContext searchContext, Collection<Document> documents,
			boolean deleteFirst)
		throws SearchException {

		try {
			List<SolrInputDocument> solrInputDocuments = new ArrayList<>(
				documents.size());

			List<String> uids = new ArrayList<>(documents.size());

			for (Document document : documents) {
				SolrInputDocument solrInputDocument =
					_solrDocumentFactory.getSolrInputDocument(document);

				solrInputDocuments.add(solrInputDocument);

				uids.add(document.getUID());
			}

			if (deleteFirst) {
				UpdateResponse updateResponse = _solrServer.deleteById(uids);

				LogUtil.logSolrResponseBase(_log, updateResponse);
			}

			UpdateResponse updateResponse = _solrServer.add(solrInputDocuments);

			if (PortalRunMode.isTestMode() ||
				searchContext.isCommitImmediately()) {

				_solrServer.commit();
			}

			LogUtil.logSolrResponseBase(_log, updateResponse);

			return updateResponse;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new SearchException(e);
		}
	}

	@Reference(unbind = "-")
	protected void setSolrDocumentFactory(
		SolrDocumentFactory solrDocumentFactory) {

		_solrDocumentFactory = solrDocumentFactory;
	}

	@Reference(unbind = "-")
	protected void setSolrServer(SolrServer solrServer) {
		_solrServer = solrServer;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SolrUpdateDocumentCommandImpl.class);

	private SolrDocumentFactory _solrDocumentFactory;
	private SolrServer _solrServer;

}