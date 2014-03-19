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
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.search.elasticsearch.connection.ElasticsearchConnectionManager;
import com.liferay.portal.search.elasticsearch.document.ElasticsearchDocumentFactory;
import com.liferay.portal.search.elasticsearch.util.LogUtil;

import java.io.IOException;

import java.util.Collection;
import java.util.concurrent.Future;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;

/**
 * @author Michael C. Han
 */
public class ElasticsearchUpdateDocumentCommandImpl
	implements ElasticsearchUpdateDocumentCommand {

	public void setElasticsearchDocumentFactory(
		ElasticsearchDocumentFactory elasticsearchDocumentFactory) {

		_elasticsearchDocumentFactory = elasticsearchDocumentFactory;
	}

	@Override
	public void updateDocument(
			String documentType, SearchContext searchContext, Document document)
		throws SearchException {

		try {
			UpdateRequestBuilder updateRequestBuilder =
				buildUpdateRequestBuilder(
					documentType, searchContext, document);

			Future<UpdateResponse> future = updateRequestBuilder.execute();

			UpdateResponse updateResponse = future.get();

			LogUtil.logActionResponse(_log, updateResponse);
		}
		catch (Exception e) {
			throw new SearchException(
				"Unable to update document " + document, e);
		}
	}

	@Override
	public void updateDocuments(
			String documentType, SearchContext searchContext,
			Collection<Document> documents)
		throws SearchException {

		try {
			Client client = getClient();

			BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();

			for (Document document : documents) {
				UpdateRequestBuilder updateRequestBuilder =
					buildUpdateRequestBuilder(
						documentType, searchContext, document);

				bulkRequestBuilder.add(updateRequestBuilder);
			}

			Future<BulkResponse> future = bulkRequestBuilder.execute();

			BulkResponse bulkResponse = future.get();

			LogUtil.logActionResponse(_log, bulkResponse);
		}
		catch (Exception e) {
			throw new SearchException(
				"Unable to update documents " + documents, e);
		}
	}

	protected UpdateRequestBuilder buildUpdateRequestBuilder(
			String documentType, SearchContext searchContext, Document document)
		throws IOException {

		Client client = getClient();

		UpdateRequestBuilder updateRequestBuilder = client.prepareUpdate(
			String.valueOf(searchContext.getCompanyId()), documentType,
			document.getUID());

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

	private static Log _log = LogFactoryUtil.getLog(
		ElasticsearchUpdateDocumentCommandImpl.class);

	private ElasticsearchDocumentFactory _elasticsearchDocumentFactory;

}