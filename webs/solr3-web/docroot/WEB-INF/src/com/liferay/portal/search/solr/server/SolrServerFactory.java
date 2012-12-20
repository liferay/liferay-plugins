/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import org.apache.solr.client.solrj.SolrServerException;

/**
 * @author Bruno Farache
 */
public interface SolrServerFactory {

	public List<SolrServerWrapper> getDeadServers();

	public SolrServerWrapper getLiveServer()
		throws SolrServerException;

	public List<SolrServerWrapper> getLiveServers();

	public void killServer(SolrServerWrapper serverWrapper);

	public void startServer(SolrServerWrapper serverWrapper);

}