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

package com.liferay.mail.util;

import com.liferay.mail.model.MailAccount;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Summary;
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

	public Summary getSummary(
		Document doc, String snippet, PortletURL portletURL) {

		return null;
	}

	public void reindex(String className, long classPK) throws SearchException {
	}

	public void reindex(String[] ids) throws SearchException {
		try {
			MailDiskManager.reIndex(ids);
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	private static final String[] _CLASS_NAMES = new String[0];

}