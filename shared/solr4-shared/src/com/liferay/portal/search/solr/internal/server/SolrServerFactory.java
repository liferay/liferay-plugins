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

package com.liferay.portal.search.solr.internal.server;

import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.solr.server.SolrServerSelector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Bruno Farache
 */
@Component(immediate = true, service = SolrServerFactory.class)
public class SolrServerFactory {

	public List<SolrServerWrapper> getDeadServers() {
		synchronized (this) {
			return new ArrayList<>(_deadServers.values());
		}
	}

	public SolrServerWrapper getLiveServer() throws SolrServerException {
		List<SolrServerWrapper> liveServers = getLiveServers();

		SolrServerWrapper solrServerWrapper = _solrServerSelector.select(
			liveServers);

		if (solrServerWrapper != null) {
			return solrServerWrapper;
		}

		throw new SolrServerException("No server available");
	}

	public List<SolrServerWrapper> getLiveServers() {
		synchronized (this) {
			return new ArrayList<>(_liveServers.values());
		}
	}

	public void killServer(SolrServerWrapper solrServerWrapper) {
		synchronized (this) {
			if (_deadServers.containsKey(solrServerWrapper.getId())) {
				return;
			}

			_deadServers.put(solrServerWrapper.getId(), solrServerWrapper);
			_liveServers.remove(solrServerWrapper.getId());
		}
	}

	public void startServer(SolrServerWrapper solrServerWrapper) {
		synchronized (this) {
			if (_liveServers.containsKey(solrServerWrapper.getId())) {
				return;
			}

			_deadServers.remove(solrServerWrapper.getId());
			_liveServers.put(solrServerWrapper.getId(), solrServerWrapper);

			solrServerWrapper.resetInvocationCount();
		}
	}

	@Reference(
		cardinality = ReferenceCardinality.AT_LEAST_ONE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void setSolrServers(
		SolrServer solrServer, Map<String, Object> properties) {

		String url = MapUtil.getString(properties, "url");

		if (Validator.isNull(url)) {
			throw new IllegalArgumentException("Solr server must have a URL");
		}

		SolrServerWrapper solrServerWrapper = new SolrServerWrapper(
			url, solrServer);

		solrServerWrapper.setSolrServerFactory(this);

		_liveServers.put(url, solrServerWrapper);
	}

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY

	)
	protected void setSolrServerSelector(
		SolrServerSelector solrServerSelector) {

		_solrServerSelector = solrServerSelector;
	}

	protected void unsetSolrServers(
		SolrServer solrServer, Map<String, Object> properties) {

		String url = MapUtil.getString(properties, "url");

		_deadServers.remove(url);
		_liveServers.remove(url);
	}

	protected void unsetSolrServerSelector(
		SolrServerSelector solrServerSelector) {

		_solrServerSelector = new LoadBalancedSolrServerSelector();
	}

	private Map<String, SolrServerWrapper> _deadServers = new HashMap<>();
	private Map<String, SolrServerWrapper> _liveServers = new HashMap<>();
	private SolrServerSelector _solrServerSelector;

}