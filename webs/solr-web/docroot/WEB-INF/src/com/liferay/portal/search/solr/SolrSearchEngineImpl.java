/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.search.solr;

import com.liferay.portal.kernel.search.IndexSearcher;
import com.liferay.portal.kernel.search.IndexWriter;
import com.liferay.portal.kernel.search.SearchEngine;

/**
 * @author Bruno Farache
 */
public class SolrSearchEngineImpl implements SearchEngine {

	public static final String NAME = "SOLR_LUCENE";

	public String getName() {
		return NAME;
	}

	public IndexSearcher getSearcher() {
		return _searcher;
	}

	public IndexWriter getWriter() {
		return _writer;
	}

	public void setSearcher(IndexSearcher searcher) {
		_searcher = searcher;
	}

	public void setWriter(IndexWriter writer) {
		_writer = writer;
	}

	private IndexSearcher _searcher;
	private IndexWriter _writer;

}