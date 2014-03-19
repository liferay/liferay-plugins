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

package com.liferay.portal.search.elasticsearch.connection;

import org.elasticsearch.client.Client;

/**
 * @author Michael C. Han
 */
public class ElasticsearchConnectionManager {

	public static ElasticsearchConnectionManager getInstance() {
		return _instance;
	}

	public Client getClient() {
		if (_elasticsearchConnection == null) {
			throw new IllegalStateException(
				"Elasticsearch connection not initialized");
		}

		return _elasticsearchConnection.getClient();
	}

	public ElasticsearchConnection getElasticsearchConnection() {
		return _elasticsearchConnection;
	}

	public void setElasticsearchConnection(
		ElasticsearchConnection elasticsearchConnection) {

		_elasticsearchConnection = elasticsearchConnection;
	}

	private static ElasticsearchConnectionManager _instance =
		new ElasticsearchConnectionManager();

	private static ElasticsearchConnection _elasticsearchConnection;

}