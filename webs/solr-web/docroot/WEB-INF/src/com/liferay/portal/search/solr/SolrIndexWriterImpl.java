/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriter;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Collection;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;

/**
 * <a href="SolrIndexWriterImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 *
 */
public class SolrIndexWriterImpl implements IndexWriter {

	public void addDocument(long companyId, Document doc)
		throws SearchException {

		try {
			_solrServer.add(getSolrDocument(doc));

			_solrServer.commit();
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	public void deleteDocument(long companyId, String uid)
		throws SearchException {

		try {
			_solrServer.deleteById(uid);

			_solrServer.commit();
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	public void deletePortletDocuments(long companyId, String portletId)
		throws SearchException {

		try {
			_solrServer.deleteByQuery(
				Field.PORTLET_ID + StringPool.COLON + portletId);

			_solrServer.commit();
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	public void setSolrServer(SolrServer solrServer) {
		_solrServer = solrServer;
	}

	public void updateDocument(long companyId, String uid, Document doc)
		throws SearchException {

		deleteDocument(companyId, uid);

		addDocument(companyId, doc);
	}

	protected SolrInputDocument getSolrDocument(Document doc) {
		SolrInputDocument solrDoc = new SolrInputDocument();

		Collection<Field> fields = doc.getFields().values();

		for (Field field : fields) {
			String name = field.getName();
			String value = field.getValue();
			float boost = field.getBoost();

			if (value != null) {
				solrDoc.addField(name, value, boost);
			}
		}

		return solrDoc;
	}

	private SolrServer _solrServer;

}