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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.elasticsearch.util.PortletPropsValues;

import java.net.InetAddress;

import java.util.HashSet;
import java.util.Set;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * @author Michael C. Han
 */
public class RemoteElasticsearchConnection extends BaseElasticsearchConnection {

	public void setTransportAddresses(Set<String> transportAddresses) {
		_transportAddresses = transportAddresses;
	}

	@Override
	protected Client createClient(ImmutableSettings.Builder builder) {
		if (_transportAddresses.isEmpty()) {
			throw new IllegalStateException(
				"There must be at least one transport address");
		}

		builder.loadFromClasspath(
			PortletPropsValues.ELASTICSEARCH_REMOTE_CONFIG_LOCATION);

		TransportClient transportClient = new TransportClient(builder);

		builder.put("client.transport.sniff", true);
		builder.put("cluster.name", getClusterName());

		for (String transportAddress : _transportAddresses) {
			String[] transportAddressParts = StringUtil.split(
				transportAddress, StringPool.COLON);

			try {
				InetAddress inetAddress = InetAddress.getByName(
					transportAddressParts[0]);

				int port = GetterUtil.getInteger(transportAddressParts[1]);

				transportClient.addTransportAddress(
					new InetSocketTransportAddress(inetAddress, port));
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to add transport address " + transportAddress,
						e);
				}
			}
		}

		return transportClient;
	}

	private static Log _log = LogFactoryUtil.getLog(
		RemoteElasticsearchConnection.class);

	private Set<String> _transportAddresses = new HashSet<String>();

}