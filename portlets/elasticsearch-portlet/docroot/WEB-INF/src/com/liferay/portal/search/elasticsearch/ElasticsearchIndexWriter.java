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

package com.liferay.portal.search.elasticsearch;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexWriter;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.search.elasticsearch.connection.ElasticsearchConnection;
import com.liferay.portal.search.elasticsearch.connection.ElasticsearchConnectionManager;
import com.liferay.portal.search.elasticsearch.document.ElasticsearchDocumentFactory;
import com.liferay.portal.search.elasticsearch.io.StringOutputStream;

import java.io.IOException;

import java.util.Collection;
import java.util.concurrent.Future;

import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.deletebyquery.DeleteByQueryRequestBuilder;
import org.elasticsearch.action.deletebyquery.DeleteByQueryResponse;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.io.stream.OutputStreamStreamOutput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * @author Michael C. Han
 * @author Milen Dyankov
 */
public class ElasticsearchIndexWriter extends BaseIndexWriter {

	@Override
	public void addDocument(SearchContext searchContext, Document document)
		throws SearchException {

		updateDocument(searchContext, document);
	}

	@Override
	public void addDocuments(
			SearchContext searchContext, Collection<Document> documents)
		throws SearchException {

		updateDocuments(searchContext, documents);
	}

	@Override
	public void deleteDocument(SearchContext searchContext, String uid)
		throws SearchException {

		try {
			Client client = getClient();

			DeleteRequestBuilder deleteRequestBuilder = client.prepareDelete(
				String.valueOf(searchContext.getCompanyId()),
				ElasticsearchConnection.LIFERAY_DOCUMENT_TYPE, uid);

			Future<DeleteResponse> future = deleteRequestBuilder.execute();

			DeleteResponse deleteResponse = future.get();

			logActionResponse(deleteResponse);
		}
		catch (Exception e) {
			throw new SearchException("Unable to delete document " + uid, e);
		}
	}

	@Override
	public void deleteDocuments(
			SearchContext searchContext, Collection<String> uids)
		throws SearchException {

		try {
			Client client = getClient();

			BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();

			for (String uid : uids) {
				DeleteRequestBuilder deleteRequestBuilder =
					client.prepareDelete(
						String.valueOf(searchContext.getCompanyId()),
						ElasticsearchConnection.LIFERAY_DOCUMENT_TYPE, uid);

				bulkRequestBuilder.add(deleteRequestBuilder);
			}

			Future<BulkResponse> future = bulkRequestBuilder.execute();

			BulkResponse bulkResponse = future.get();

			logActionResponse(bulkResponse);
		}
		catch (Exception e) {
			throw new SearchException("Unable to delete documents " + uids, e);
		}
	}

	@Override
	public void deletePortletDocuments(
			SearchContext searchContext, String portletId)
		throws SearchException {

		try {
			Client client = getClient();

			DeleteByQueryRequestBuilder deleteByQueryRequestBuilder =
				client.prepareDeleteByQuery(
					String.valueOf(searchContext.getCompanyId()));

			BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

			boolQueryBuilder.must(
				QueryBuilders.termQuery(Field.PORTLET_ID, portletId));

			deleteByQueryRequestBuilder.setQuery(boolQueryBuilder);

			Future<DeleteByQueryResponse> future =
				deleteByQueryRequestBuilder.execute();

			DeleteByQueryResponse deleteByQueryResponse = future.get();

			logActionResponse(deleteByQueryResponse);
		}
		catch (Exception e) {
			throw new SearchException(
				"Unable to delete data for portlet " + portletId, e);
		}
	}

	public void setElasticsearchDocumentFactory(
		ElasticsearchDocumentFactory elasticsearchDocumentFactory) {

		_elasticsearchDocumentFactory = elasticsearchDocumentFactory;
	}

	@Override
	public void updateDocument(SearchContext searchContext, Document document)
		throws SearchException {

		try {
			Client client = getClient();

			UpdateRequestBuilder updateRequestBuilder =
				buildUpdateRequestBuilder(searchContext, client, document);

			Future<UpdateResponse> future = updateRequestBuilder.execute();

			UpdateResponse updateResponse = future.get();

			logActionResponse(updateResponse);
		}
		catch (Exception e) {
			throw new SearchException(
				"Unable to update document " + document, e);
		}
	}

	@Override
	public void updateDocuments(
			SearchContext searchContext, Collection<Document> documents)
		throws SearchException {

		try {
			Client client = getClient();

			BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();

			for (Document document : documents) {
				UpdateRequestBuilder updateRequestBuilder =
					buildUpdateRequestBuilder(searchContext, client, document);

				bulkRequestBuilder.add(updateRequestBuilder);
			}

			Future<BulkResponse> future = bulkRequestBuilder.execute();

			BulkResponse bulkResponse = future.get();

			logActionResponse(bulkResponse);
		}
		catch (Exception e) {
			throw new SearchException(
				"Unable to update documents " + documents, e);
		}
	}

	protected UpdateRequestBuilder buildUpdateRequestBuilder(
			SearchContext searchContext, Client client, Document document)
		throws IOException {

		UpdateRequestBuilder updateRequestBuilder = client.prepareUpdate(
			String.valueOf(searchContext.getCompanyId()),
			ElasticsearchConnection.LIFERAY_DOCUMENT_TYPE, document.getUID());

		String elasticSearchDocument =
			_elasticsearchDocumentFactory.getElasticsearchDocument(document);

		updateRequestBuilder.setDoc(elasticSearchDocument);
		updateRequestBuilder.setDocAsUpsert(true);

		return updateRequestBuilder;
	}

	protected Client getClient() {
		ElasticsearchConnectionManager elasticsearchConnectionManager =
			ElasticsearchConnectionManager.getInstance();

		return elasticsearchConnectionManager.getClient();
	}

	protected void logActionResponse(ActionResponse actionResponse)
		throws IOException {

		if (!_log.isInfoEnabled()) {
			return;
		}

		StringOutputStream stringOutputStream = new StringOutputStream();

		actionResponse.writeTo(
			new OutputStreamStreamOutput(stringOutputStream));

		_log.info(stringOutputStream);
	}

	protected void logActionResponse(BulkResponse bulkResponse)
		throws IOException {

		if (bulkResponse.hasFailures()) {
			if (_log.isErrorEnabled()) {
				_log.error(bulkResponse.buildFailureMessage());
			}
		}

		logActionResponse((ActionResponse)bulkResponse);
	}

	private static Log _log = LogFactoryUtil.getLog(
		ElasticsearchIndexWriter.class);

	private ElasticsearchDocumentFactory _elasticsearchDocumentFactory;

}