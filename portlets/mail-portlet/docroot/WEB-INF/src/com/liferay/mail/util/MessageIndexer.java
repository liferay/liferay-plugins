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

import com.liferay.mail.model.MessageEntry;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.search.BaseIndexer;
import com.liferay.portal.util.PortletKeys;

import javax.portlet.PortletURL;

/**
 * <a href="MessageIndexer.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 * @author Alexander Chow
 *
 */
public class MessageIndexer extends BaseIndexer {

	public static final String[] CLASS_NAMES = {MessageEntry.class.getName()};

	public static final String EMAIL_ADDRESS = "emailAddress";

	public static final String FOLDER_NAME = "folderName";

	public static final String PORTLET_ID = PortletKeys.MAIL;

	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	public Summary getSummary(
		Document doc, String snippet, PortletURL portletURL) {

		return null;
	}

	protected void doDelete(Object obj) throws Exception {
		MessageEntry entry = (MessageEntry)obj;

		SearchEngineUtil.deleteDocument(
			entry.getCompanyId(),
			getUID(
				entry.getUserId(), entry.getEmailAddress(),
				entry.getFolderName(), entry.getMessageUid()));
	}

	protected Document doGetDocument(Object obj) throws Exception {
		MessageEntry entry = (MessageEntry)obj;

		long companyId = entry.getCompanyId();
		long groupId = entry.getGroupId();
		long userId = entry.getUserId();
		String emailAddress = entry.getEmailAddress();
		String folderName = entry.getFolderName();
		long messageUid = entry.getMessageUid();
		String title = entry.getTitle();
		String content = entry.getContent();

		content = HtmlUtil.extractText(content);

		Document document = new DocumentImpl();

		document.addKeyword(
			Field.UID, getUID(userId, emailAddress, folderName, messageUid));

		document.addModifiedDate();

		document.addKeyword(Field.COMPANY_ID, companyId);
		document.addKeyword(Field.PORTLET_ID, PORTLET_ID);
		document.addKeyword(Field.GROUP_ID, groupId);
		document.addKeyword(Field.USER_ID, userId);

		document.addKeyword(EMAIL_ADDRESS, emailAddress);
		document.addKeyword(FOLDER_NAME, folderName);
		document.addText(Field.TITLE, title);
		document.addText(Field.CONTENT, content);

		document.addKeyword(
			Field.ENTRY_CLASS_NAME, MessageEntry.class.getName());
		document.addKeyword(Field.ENTRY_CLASS_PK, messageUid);

		return document;
	}

	protected void doReindex(Object obj) throws Exception {
		MessageEntry entry = (MessageEntry)obj;

		Document document = getDocument(entry);

		SearchEngineUtil.updateDocument(
			entry.getCompanyId(), document.get(Field.UID), document);
	}

	protected void doReindex(String className, long classPK) throws Exception {
		throw new UnsupportedOperationException();
	}

	protected void doReindex(String[] ids) throws Exception {
		try {
			MailDiskManager.reIndex(ids);
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	protected String getPortletId(SearchContext searchContext) {
		return PORTLET_ID;
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

	public static String getUID(
		long userId, String emailAddress, String folderName, long messageUid) {

		Document doc = new DocumentImpl();

		doc.addUID(
			PORTLET_ID, String.valueOf(userId), emailAddress, folderName,
			String.valueOf(messageUid));

		return doc.get(Field.UID);
	}

}