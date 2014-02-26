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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.elasticsearch.index.IndexFactory;

import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;

/**
 * @author Michael C. Han
 */
public abstract class BaseElasticsearchConnection
	implements ElasticsearchConnection {

	@Override
	public void close() {
		if (_client != null) {
			_client.close();
		}
	}

	@Override
	public Client getClient() {
		return _client;
	}

	public String getClusterName() {
		if (Validator.isNull(_clusterName)) {
			_clusterName = CLUSTER_NAME;
		}

		return _clusterName;
	}

	@Override
	public void initialize() {
		ImmutableSettings.Builder builder = ImmutableSettings.settingsBuilder();

		_client = createClient(builder);

		initializeIndices();
	}

	public void setClusterName(String clusterName) {
		_clusterName = clusterName;
	}

	public void setIndexFactory(IndexFactory indexFactory) {
		_indexFactory = indexFactory;
	}

	protected abstract Client createClient(ImmutableSettings.Builder builder);

	protected IndexFactory getIndexFactory() {
		return _indexFactory;
	}

	protected void initializeIndices() {
		try {
			AdminClient adminClient = getClient().admin();

			_indexFactory.createIndices(adminClient);
		}
		catch (Exception e) {
			if (_log.isErrorEnabled()) {
				_log.error("Unable to initialize indices", e);
			}
		}
	}

	protected void setClient(Client client) {
		_client = client;
	}

	private static Log _log = LogFactoryUtil.getLog(
		BaseElasticsearchConnection.class);

	private Client _client;
	private String _clusterName;
	private IndexFactory _indexFactory;

}