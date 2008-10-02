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
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.util.Collection;

/**
 * <a href="SolrIndexWriterImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 *
 */
public class SolrIndexWriterImpl implements IndexWriter {

	public void addDocument(long companyId, Document doc)
		throws SearchException {

		com.liferay.portal.kernel.xml.Document xmlDoc =
			SAXReaderUtil.createDocument();

		Element addEl = xmlDoc.addElement("add");

		addFieldEls(addEl, doc);

		try {
			write(xmlDoc);
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	public void deleteDocument(long companyId, String uid)
		throws SearchException {

		com.liferay.portal.kernel.xml.Document xmlDoc =
			SAXReaderUtil.createDocument();

		Element deleteEl = xmlDoc.addElement("delete");

		Element idEl = deleteEl.addElement("id");

		idEl.setText(uid);

		try {
			write(xmlDoc);
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	public void deletePortletDocuments(long companyId, String portletId)
		throws SearchException {

		com.liferay.portal.kernel.xml.Document xmlDoc =
			SAXReaderUtil.createDocument();

		Element deleteEl = xmlDoc.addElement("delete");

		Element queryEl = deleteEl.addElement("query");

		queryEl.setText(Field.PORTLET_ID + StringPool.COLON + portletId);

		try {
			write(xmlDoc);
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

		deleteDocument(companyId, uid);

		addDocument(companyId, doc);
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
		Http.Body httpBody = new Http.Body(
			xml, ContentTypes.TEXT_XML, StringPool.UTF8);

		HttpUtil.submit(_serverURL, null, httpBody, true);
	}

	protected void write(com.liferay.portal.kernel.xml.Document xmlDoc)
		throws Exception {

		submitRequest(xmlDoc.asXML());
		submitRequest(_COMMIT_XML);
	}

	private static final String _COMMIT_XML = "<commit />";

	private String _serverURL;

}