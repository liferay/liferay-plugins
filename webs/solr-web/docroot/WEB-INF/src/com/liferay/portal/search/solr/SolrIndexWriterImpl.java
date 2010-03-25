/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;

/**
 * <a href="SolrIndexWriterImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 *
 */
public class SolrIndexWriterImpl implements IndexWriter {

	public void addDocument(long companyId, Document document)
		throws SearchException {

		try {
			_solrServer.add(getSolrDocument(document));

			if (_commit) {
				_solrServer.commit();
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new SearchException(e.getMessage());
		}
	}

	public void addDocuments(long companyId, Collection<Document> documents)
		throws SearchException {

		try {
			Collection<SolrInputDocument> solrDocuments = getSolrDocuments(
				documents);

			if (solrDocuments.isEmpty()) {
				return;
			}

			_solrServer.add(solrDocuments);

			if (_commit) {
				_solrServer.commit();
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new SearchException(e.getMessage());
		}		

	}

	public void deleteDocument(long companyId, String uid)
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

	public void deleteDocuments(long companyId, Collection<String> uids)
		throws SearchException {

		for (String uid : uids) {
			deleteDocument(companyId, uid);
		}
	}

	public void deletePortletDocuments(long companyId, String portletId)
		throws SearchException {

		try {
			_solrServer.deleteByQuery(
				Field.PORTLET_ID + StringPool.COLON + portletId);

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

	public void updateDocument(long companyId, String uid, Document document)
		throws SearchException {

		deleteDocument(companyId, uid);

		addDocument(companyId, document);
	}

	public void updateDocuments(long companyId, Map<String, Document> documents)
		throws SearchException {

		for (String uid : documents.keySet()) {
			deleteDocument(companyId, uid);
		}

		addDocuments(companyId, documents.values());
	}
	
	protected SolrInputDocument getSolrDocument(Document document) {
		SolrInputDocument solrInputDocument = new SolrInputDocument();

		Collection<Field> fields = document.getFields().values();

		for (Field field : fields) {
			String name = field.getName();
			float boost = field.getBoost();

			for (String value : field.getValues()) {
				if (Validator.isNull(value)) {
					continue;
				}

				solrInputDocument.addField(name, value.trim(), boost);
			}
		}

		return solrInputDocument;
	}

	protected Collection<SolrInputDocument> getSolrDocuments(
		Collection<Document> documents) {

		List<SolrInputDocument> solrInputDocuments =
			new ArrayList<SolrInputDocument>(documents.size());

		for (Document document : documents) {
			SolrInputDocument solrInputDocument = getSolrDocument(document);

			solrInputDocuments.add(solrInputDocument);
		}

		return solrInputDocuments;
	}

	private static Log _log = LogFactoryUtil.getLog(SolrIndexWriterImpl.class);

	private boolean _commit;
	private SolrServer _solrServer;

}