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
import com.liferay.portal.search.elasticsearch.util.PortletPropsValues;

import org.apache.commons.lang.time.StopWatch;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

/**
 * @author Michael C. Han
 */
public class EmbeddedElasticsearchConnection
	extends BaseElasticsearchConnection {

	@Override
	public void close() {
		super.close();

		if (_node != null) {
			_node.close();
		}
	}

	@Override
	protected Client createClient(ImmutableSettings.Builder settingsBuilder) {
		NodeBuilder nodeBuilder = NodeBuilder.nodeBuilder();

		settingsBuilder.loadFromClasspath(
			PortletPropsValues.ELASTICSEARCH_EMBEDDED_CONFIG_LOCATION);

		nodeBuilder.settings(settingsBuilder);

		nodeBuilder.clusterName(getClusterName());
		nodeBuilder.client(false);
		nodeBuilder.data(true);

		_node = nodeBuilder.node();

		StopWatch stopWatch = null;

		if (_log.isDebugEnabled()) {
			stopWatch = new StopWatch();
			stopWatch.start();

			_log.debug(
				"Starting embedded elasticsearch cluster: " + getClusterName());
		}

		_node.start();

		Client client = _node.client();

		if (_log.isDebugEnabled()) {
			stopWatch.stop();

			_log.debug(
				"Completed startup: " + getClusterName() + " in " +
					stopWatch.getTime());
		}

		return client;
	}

	private static Log _log = LogFactoryUtil.getLog(
		EmbeddedElasticsearchConnection.class);

	private Node _node;

}