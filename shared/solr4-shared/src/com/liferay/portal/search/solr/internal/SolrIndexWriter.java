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
import com.liferay.portal.kernel.search.BaseIndexWriter;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriter;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.suggest.SpellCheckIndexWriter;
import com.liferay.portal.kernel.util.PortalRunMode;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.search.solr.document.SolrUpdateDocumentCommand;
import com.liferay.portal.search.solr.internal.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bruno Farache
 * @author Michael C. Han
 */
@Component(
	immediate = true, property = {"search.engine.impl=Solr"},
	service = IndexWriter.class
)
public class SolrIndexWriter extends BaseIndexWriter {

	@Override
	public void addDocument(SearchContext searchContext, Document document)
		throws SearchException {

		_solrUpdateDocumentCommand.updateDocument(
			searchContext, document, false);
	}

	@Override
	public void addDocuments(
			SearchContext searchContext, Collection<Document> documents)
		throws SearchException {

		_solrUpdateDocumentCommand.updateDocuments(
			searchContext, documents, false);
	}

	@Override
	public void deleteDocument(SearchContext searchContext, String uid)
		throws SearchException {

		deleteDocuments(searchContext, Arrays.asList(uid));
	}

	@Override
	public void deleteDocuments(
			SearchContext searchContext, Collection<String> uids)
		throws SearchException {

		List<String> uidsList = new ArrayList<>(uids);

		try {
			UpdateResponse updateResponse = _solrServer.deleteById(uidsList);

			if (PortalRunMode.isTestMode() ||
				searchContext.isCommitImmediately()) {

				_solrServer.commit();
			}

			LogUtil.logSolrResponseBase(_log, updateResponse);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new SearchException(e.getMessage());
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

			UpdateResponse updateResponse = _solrServer.deleteByQuery(
				sb.toString());

			if (PortalRunMode.isTestMode() ||
				searchContext.isCommitImmediately()) {

				_solrServer.commit();
			}

			LogUtil.logSolrResponseBase(_log, updateResponse);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new SearchException(e.getMessage());
		}
	}

	@Override
	@Reference(target = "(search.engine.impl=Solr)", unbind = "-")
	public void setSpellCheckIndexWriter(
		SpellCheckIndexWriter spellCheckIndexWriter) {

		super.setSpellCheckIndexWriter(spellCheckIndexWriter);
	}

	@Override
	public void updateDocument(SearchContext searchContext, Document document)
		throws SearchException {

		_solrUpdateDocumentCommand.updateDocument(
			searchContext, document, true);
	}

	@Override
	public void updateDocuments(
			SearchContext searchContext, Collection<Document> documents)
		throws SearchException {

		_solrUpdateDocumentCommand.updateDocuments(
			searchContext, documents, true);
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

	private static Log _log = LogFactoryUtil.getLog(SolrIndexWriter.class);

	private SolrServer _solrServer;
	private SolrUpdateDocumentCommand _solrUpdateDocumentCommand;

}