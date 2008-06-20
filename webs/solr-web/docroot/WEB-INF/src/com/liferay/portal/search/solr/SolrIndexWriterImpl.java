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
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;

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

	public String getServerURL() {
		return _serverURL;
	}

	public void setServerURL(String serverURL) {
		_serverURL = serverURL;
	}

	public void addDocument(long companyId, Document doc)
		throws SearchException {

		org.dom4j.Document xml = DocumentHelper.createDocument();

		Element root = xml.addElement("add");
		_addDocumentEl(root, doc);

		try {
			_write(companyId, xml);
		}
		catch (IOException ioe) {
			throw new SearchException(ioe);
		}
	}

	public void deleteDocument(long companyId, String uid)
		throws SearchException {

		org.dom4j.Document xml = DocumentHelper.createDocument();

		Element root = xml.addElement("delete");

		root.addElement("id").setText(uid);

		try {
			_write(companyId, xml);
		}
		catch (IOException ioe) {
			throw new SearchException(ioe);
		}
	}

	public void deletePortletDocuments(long companyId, String portletId)
		throws SearchException {

		org.dom4j.Document xml = DocumentHelper.createDocument();

		Element root = xml.addElement("delete");

		root.addElement("query").setText(Field.PORTLET_ID + StringPool.COLON +
			portletId);

		try {
			_write(companyId, xml);
		}
		catch (IOException ioe) {
			throw new SearchException(ioe);
		}
	}

	public void updateDocument(long companyId, String uid, Document doc)
		throws SearchException {

		org.dom4j.Document xml = DocumentHelper.createDocument();

		Element root = xml.addElement("update");
		_addDocumentEl(root, doc);

		try {
			_write(companyId, xml);
		}
		catch (IOException ioe) {
			throw new SearchException(ioe);
		}
	}

	private void _addDocumentEl(Element root, Document doc) {
		Element docEl = root.addElement("doc");

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

	private void _sendPostCommand(String url, String xml) throws IOException {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);

		try {
			RequestEntity entity = new StringRequestEntity(
				xml, "text/xml", "UTF-8");
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

	private void _write(long companyId, org.dom4j.Document xml)
		throws IOException {

		String url = _serverURL;

		_sendPostCommand(url, xml.asXML());
		_sendPostCommand(url, "<commit/>");
	}

	private static Log _log = LogFactory.getLog(SolrIndexWriterImpl.class);

	private String _serverURL;

}