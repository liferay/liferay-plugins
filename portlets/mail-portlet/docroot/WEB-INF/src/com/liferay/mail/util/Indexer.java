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

package com.liferay.mail.util;

import com.liferay.mail.model.MailAccount;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.DocumentSummary;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.util.PortletKeys;

import javax.portlet.PortletURL;

/**
 * <a href="Indexer.java.html"><b><i>View Source</i></b></a>
 *
 * @author Alexander Chow
 *
 */
public class Indexer implements com.liferay.portal.kernel.search.Indexer {

	public static final String FOLDER_NAME = "folderName";

	public static final String EMAIL_ADDRESS = "emailAddress";

	public static final String PORTLET_ID = PortletKeys.MAIL;

	public static void addMessage(
			long companyId, long groupId, long userId, String emailAddress,
			String folderName, long messageUid, String subject, String content)
		throws SearchException {

		Document doc = getMessageDocument(
			companyId, groupId, userId, emailAddress, folderName, messageUid,
			subject, content);

		SearchEngineUtil.addDocument(companyId, doc);
	}

	public static void deleteMessage(
			long companyId, long userId, String emailAddress, String folderName,
			long messageUid)
		throws SearchException {

		String uid = getUID(userId, emailAddress, folderName, messageUid);

		SearchEngineUtil.deleteDocument(companyId, uid);
	}

	public static void deleteMessages(
			long companyId, long userId, String emailAddress)
		throws SearchException {

		BooleanQuery booleanQuery = BooleanQueryFactoryUtil.create();

		booleanQuery.addRequiredTerm(Field.PORTLET_ID, PORTLET_ID);
		booleanQuery.addRequiredTerm(Field.USER_ID, userId);
		booleanQuery.addRequiredTerm(EMAIL_ADDRESS, emailAddress);

		Hits hits = SearchEngineUtil.search(
			companyId, booleanQuery, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (int i = 0; i < hits.getLength(); i++) {
			Document doc = hits.doc(i);

			SearchEngineUtil.deleteDocument(companyId, doc.get(Field.UID));
		}
	}

	public static Document getMessageDocument(
		long companyId, long groupId, long userId, String emailAddress,
		String folderName, long messageUid, String subject, String content) {

		content = HtmlUtil.extractText(content);

		Document doc = new DocumentImpl();

		String uid = getUID(userId, emailAddress, folderName, messageUid);

		doc.addUID(PORTLET_ID, uid);

		doc.addKeyword(Field.COMPANY_ID, companyId);
		doc.addKeyword(Field.GROUP_ID, groupId);
		doc.addKeyword(Field.PORTLET_ID, PORTLET_ID);
		doc.addKeyword(Field.USER_ID, userId);
		doc.addKeyword(EMAIL_ADDRESS, emailAddress);
		doc.addKeyword(FOLDER_NAME, folderName);
		doc.addKeyword(Field.ENTRY_CLASS_NAME, MailAccount.class.getName());
		doc.addKeyword(Field.ENTRY_CLASS_PK, messageUid);

		doc.addModifiedDate();

		doc.addText(Field.TITLE, subject);
		doc.addText(Field.CONTENT, content);

		return doc;
	}

	public static String getUID(
		long userId, String emailAddress, String folderName, long messageUid) {

		Document doc = new DocumentImpl();

		doc.addUID(
			PORTLET_ID, String.valueOf(userId), emailAddress, folderName,
			String.valueOf(messageUid));

		return doc.get(Field.UID);
	}

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	public DocumentSummary getDocumentSummary(
		Document doc, PortletURL portletURL) {

		return null;
	}

	public void reIndex(String[] ids) throws SearchException {
		try {
			MailDiskManager.reIndex(ids);
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	public void reIndex(String className, long classPK) throws SearchException {
	}

	private static final String[] _CLASS_NAMES = new String[] {
		MailAccount.class.getName()
	};

}