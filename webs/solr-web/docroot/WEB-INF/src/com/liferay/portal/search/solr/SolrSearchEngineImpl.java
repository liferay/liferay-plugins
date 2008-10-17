/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.search.solr;

import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.search.IndexSearcher;
import com.liferay.portal.kernel.search.IndexWriter;
import com.liferay.portal.kernel.search.SearchEngine;
import com.liferay.portal.kernel.search.SearchEngineUtil;

/**
 * <a href="SolrSearchEngineImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 *
 */
public class SolrSearchEngineImpl implements SearchEngine {

	public void init() {
		SearchEngineUtil.unregister(_name);
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public IndexSearcher getSearcher() {
		return _searcher;
	}

	public void setSearcher(IndexSearcher searcher) {
		_searcher = searcher;
	}

	public IndexWriter getWriter() {
		return _writer;
	}

	public void setWriter(IndexWriter writer) {
		_writer = writer;
	}

	public boolean isIndexReadOnly() {
		return _indexReadOnly;
	}

	public void setIndexReadOnly(boolean indexReadOnly) {
		_indexReadOnly = indexReadOnly;
	}

	public void setSearchReaderMessageListener(
			MessageListener searchReaderMessageListener) {

		_searchReaderMessageListener = searchReaderMessageListener;
	}

	public void setSearchWriterMessageListener(
			MessageListener searchWriterMessageListener) {

		_searchWriterMessageListener = searchWriterMessageListener;
	}

	public void unregister(String id) {
		if (!_name.equals(id)) {
			MessageBusUtil.unregisterMessageListener(
				DestinationNames.SEARCH_READER, _searchReaderMessageListener);

			MessageBusUtil.unregisterMessageListener(
				DestinationNames.SEARCH_WRITER, _searchWriterMessageListener);
		}
	}

	private String _name;
	private IndexSearcher _searcher;
	private IndexWriter _writer;
	private boolean _indexReadOnly;
	private MessageListener _searchReaderMessageListener;
	private MessageListener _searchWriterMessageListener;

}