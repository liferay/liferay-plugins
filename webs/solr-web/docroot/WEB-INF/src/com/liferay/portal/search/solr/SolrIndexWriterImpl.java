/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriter;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;

/**
 * @author Bruno Farache
 */
public class SolrIndexWriterImpl implements IndexWriter {

	public void addDocument(SearchContext searchContext, Document document)
		throws SearchException {

		try {
			_solrServer.add(getSolrInputDocument(document));

			if (_commit) {
				_solrServer.commit();
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new SearchException(e.getMessage());
		}
	}

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

			if (_commit) {
				_solrServer.commit();
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new SearchException(e.getMessage());
		}
	}

	public void deleteDocument(SearchContext searchContext, String uid)
		throws SearchException {

		try {
			_solrServer.deleteById(uid);

			if (_commit) {
				_solrServer.commit();
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new SearchException(e.getMessage());
		}
	}

	public void deleteDocuments(
			SearchContext searchContext, Collection<String> uids)
		throws SearchException {

		for (String uid : uids) {
			deleteDocument(searchContext, uid);
		}
	}

	public void deletePortletDocuments(
			SearchContext searchContext, String portletId)
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
			sb.append(Field.PORTLET_ID);
			sb.append(StringPool.COLON);
			sb.append(portletId);

			_solrServer.deleteByQuery(sb.toString());

			if (_commit) {
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

	public void setSolrServer(SolrServer solrServer) {
		_solrServer = solrServer;
	}

	public void updateDocument(SearchContext searchContext, Document document)
		throws SearchException {

		deleteDocument(searchContext, document.getUID());

		addDocument(searchContext, document);
	}

	public void updateDocuments(
			SearchContext searchContext, Collection<Document> documents)
		throws SearchException {

		for (Document document : documents) {
			deleteDocument(searchContext, document.getUID());
		}

		addDocuments(searchContext, documents);
	}

	protected SolrInputDocument getSolrInputDocument(Document document) {
		SolrInputDocument solrInputDocument = new SolrInputDocument();

		Collection<Field> fields = document.getFields().values();

		for (Field field : fields) {
			String name = field.getName();
			float boost = field.getBoost();

			if (!field.isLocalized()) {
				for (String value : field.getValues()) {
					if (Validator.isNull(value)) {
						continue;
					}

					solrInputDocument.addField(name, value.trim(), boost);
				}
			}
			else {
				Map<Locale, String> localizedValues =
					field.getLocalizedValues();

				for (Map.Entry<Locale, String> entry :
						localizedValues.entrySet()) {

					Locale locale = entry.getKey();
					String value = entry.getValue();

					if (Validator.isNull(value)) {
						continue;
					}

					name = name.concat(StringPool.UNDERLINE).concat(
						locale.getDisplayName());

					solrInputDocument.addField(name, value.trim(), boost);
				}
			}
		}

		return solrInputDocument;
	}

	protected Collection<SolrInputDocument> getSolrInputDocuments(
		Collection<Document> documents) {

		List<SolrInputDocument> solrInputDocuments =
			new ArrayList<SolrInputDocument>(documents.size());

		for (Document document : documents) {
			SolrInputDocument solrInputDocument = getSolrInputDocument(
				document);

			solrInputDocuments.add(solrInputDocument);
		}

		return solrInputDocuments;
	}

	private static Log _log = LogFactoryUtil.getLog(SolrIndexWriterImpl.class);

	private boolean _commit;
	private SolrServer _solrServer;

}