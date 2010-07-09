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

package com.liferay.portal.search.solr.server;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;

/**
 * @author Bruno Farache
 */
public interface SolrServerFactory {

	public SolrServer getDeadServer(SolrServerWrapper serverWrapper);

	public List<SolrServerWrapper> getDeadServers();

	public SolrServer getLiveServer(SolrServerWrapper serverWrapper);

	public List<SolrServerWrapper> getLiveServers();

	public void killServer(SolrServerWrapper serverWrapper);

	public void startServer(SolrServerWrapper serverWrapper);

}