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

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriter;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Collection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * <a href="SolrIndexWriterImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 *
 */
public class SolrIndexWriterImpl implements IndexWriter {

	public void addDocument(long companyId, Document doc)
		throws SearchException {

		org.dom4j.Document xml = DocumentHelper.createDocument();

		Element addEl = xml.addElement("add");

		addFieldEls(addEl, doc);

		try {
			write(xml);
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	public void deleteDocument(long companyId, String uid)
		throws SearchException {

		org.dom4j.Document xml = DocumentHelper.createDocument();

		Element deleteEl = xml.addElement("delete");

		Element idEl = deleteEl.addElement("id");

		idEl.setText(uid);

		try {
			write(xml);
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	public void deletePortletDocuments(long companyId, String portletId)
		throws SearchException {

		org.dom4j.Document xml = DocumentHelper.createDocument();

		Element deleteEl = xml.addElement("delete");

		Element queryEl = deleteEl.addElement("query");

		queryEl.setText(Field.PORTLET_ID + StringPool.COLON + portletId);

		try {
			write(xml);
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	public String getServerURL() {
		return _serverURL;
	}

	public void setServerURL(String serverURL) {
		_serverURL = serverURL;
	}

	public void updateDocument(long companyId, String uid, Document doc)
		throws SearchException {

		org.dom4j.Document xml = DocumentHelper.createDocument();

		Element updateEl = xml.addElement("update");

		addFieldEls(updateEl, doc);

		try {
			write(xml);
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	protected void addFieldEls(Element el, Document doc) {
		Element docEl = el.addElement("doc");

		Collection<Field> fields = doc.getFields().values();

		for (Field field : fields) {
			String name = field.getName();
			String value = field.getValue();

			if (value != null) {
				Element fieldEl = docEl.addElement("field");

				fieldEl.addAttribute("name", name);
				fieldEl.addText(value);
			}
		}
	}

	protected void submitRequest(String xml) throws Exception {
		PostMethod method = null;

		try {
			HttpClient client = new HttpClient();

			method = new PostMethod(_serverURL);

			RequestEntity entity = new StringRequestEntity(
				xml, ContentTypes.TEXT_XML, StringPool.UTF8);

			method.setRequestEntity(entity);

			client.executeMethod(method);
		}
		finally {
			try {
				if (method != null) {
					method.releaseConnection();
				}
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
	}

	protected void write(org.dom4j.Document xml) throws Exception {
		submitRequest(xml.asXML());
		submitRequest(_COMMIT_XML);
	}

	private static final String _COMMIT_XML = "<commit />";

	private static Log _log = LogFactory.getLog(SolrIndexWriterImpl.class);

	private String _serverURL;

}