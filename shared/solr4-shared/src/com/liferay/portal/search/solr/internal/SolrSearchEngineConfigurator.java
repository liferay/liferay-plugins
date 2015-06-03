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

package com.liferay.portal.search.solr.internal;

import com.liferay.portal.kernel.search.AbstractSearchEngineConfigurator;
import com.liferay.portal.kernel.search.IndexSearcher;
import com.liferay.portal.kernel.search.IndexWriter;
import com.liferay.portal.kernel.search.SearchEngine;
import com.liferay.portal.kernel.search.SearchEngineConfigurator;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true, property = {"search.engine.impl=Solr"},
	service = SearchEngineConfigurator.class
)
public class SolrSearchEngineConfigurator
	extends AbstractSearchEngineConfigurator {

	@Override
	public void destroy() {
		super.destroy();
	}

	@Activate
	protected void activate() {
		setSearchEngines(_searchEngines);
	}

	@Override
	protected String getDefaultSearchEngineId() {
		return SearchEngineUtil.SYSTEM_ENGINE_ID;
	}

	@Override
	protected IndexSearcher getIndexSearcher() {
		return _indexSearcher;
	}

	@Override
	protected IndexWriter getIndexWriter() {
		return _indexWriter;
	}

	@Override
	protected ClassLoader getOperatingClassloader() {
		Class<?> clazz = getClass();

		return clazz.getClassLoader();
	}

	@Reference(target = "(!(search.engine.impl=*))", unbind = "-")
	protected void setIndexSearcher(IndexSearcher indexSearcher) {
		_indexSearcher = indexSearcher;
	}

	@Reference(target = "(!(search.engine.impl=*))", unbind = "-")
	protected void setIndexWriter(IndexWriter indexWriter) {
		_indexWriter = indexWriter;
	}

	@Reference(
		target = "(&(search.engine.id=SYSTEM_ENGINE)(search.engine.impl=Solr))"
	)
	protected void setSearchEngine(
		SearchEngine searchEngine, Map<String, Object> properties) {

		String searchEngineId = MapUtil.getString(
			properties, "search.engine.id");

		_searchEngines.put(searchEngineId, searchEngine);
	}

	protected void unsetSearchEngine(
		SearchEngine searchEngine, Map<String, Object> properties) {

		String searchEngineId = MapUtil.getString(
			properties, "search.engine.id");

		if (Validator.isNull(searchEngineId)) {
			return;
		}

		_searchEngines.remove(searchEngineId);
	}

	private IndexSearcher _indexSearcher;
	private IndexWriter _indexWriter;
	private final Map<String, SearchEngine> _searchEngines =
		new ConcurrentHashMap<>();

}