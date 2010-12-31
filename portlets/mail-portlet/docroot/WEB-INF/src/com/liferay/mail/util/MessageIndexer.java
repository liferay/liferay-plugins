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

package com.liferay.mail.util;

import com.liferay.mail.model.Account;
import com.liferay.mail.model.Folder;
import com.liferay.mail.model.Message;
import com.liferay.mail.service.MessageLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeIndexerUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.portlet.PortletURL;

/**
 * @author Scott Lee
 */
public class MessageIndexer extends BaseIndexer {

	public static final String[] CLASS_NAMES = {Message.class.getName()};

	public static final String PORTLET_ID = PortletKeys.MAIL;

	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	public Summary getSummary(
		Document doc, String snippet, PortletURL portletURL) {

		return null;
	}

	protected void doDelete(Object obj) throws Exception {
		if (obj instanceof Account) {
			Account account = (Account)obj;

			BooleanQuery booleanQuery = BooleanQueryFactoryUtil.create();

			booleanQuery.addRequiredTerm(Field.PORTLET_ID, PORTLET_ID);

			booleanQuery.addRequiredTerm("accountId", account.getAccountId());

			Hits hits = SearchEngineUtil.search(
				account.getCompanyId(), booleanQuery, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

			for (int i = 0; i < hits.getLength(); i++) {
				Document document = hits.doc(i);

				SearchEngineUtil.deleteDocument(
					account.getCompanyId(), document.get(Field.UID));
			}
		}
		else if (obj instanceof Folder) {
			Folder folder = (Folder)obj;

			BooleanQuery booleanQuery = BooleanQueryFactoryUtil.create();

			booleanQuery.addRequiredTerm(Field.PORTLET_ID, PORTLET_ID);

			booleanQuery.addRequiredTerm("folderId", folder.getFolderId());

			Hits hits = SearchEngineUtil.search(
				folder.getCompanyId(), booleanQuery, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

			for (int i = 0; i < hits.getLength(); i++) {
				Document document = hits.doc(i);

				SearchEngineUtil.deleteDocument(
					folder.getCompanyId(), document.get(Field.UID));
			}
		}
		else if (obj instanceof Message) {
			Message message = (Message)obj;

			Document document = new DocumentImpl();

			document.addUID(PORTLET_ID, message.getMessageId());

			SearchEngineUtil.deleteDocument(
				message.getCompanyId(), document.get(Field.UID));
		}
	}

	protected Document doGetDocument(Object obj) throws Exception {
		Message message = (Message)obj;

		long companyId = message.getCompanyId();
		long groupId = message.getGroupId();
		long scopeGroupId = groupId;
		long userId = message.getUserId();
		long accountId = message.getAccountId();
		long folderId = message.getFolderId();
		long messageId = message.getMessageId();
		String subject = message.getSubject();
		String body = HtmlUtil.extractText(message.getBody());
		long remoteMessageId = message.getRemoteMessageId();
		Date modifiedDate = message.getModifiedDate();

		ExpandoBridge expandoBridge = message.getExpandoBridge();

		Document document = new DocumentImpl();

		document.addUID(PORTLET_ID, message.getMessageId());

		document.addModifiedDate(modifiedDate);

		document.addKeyword(Field.COMPANY_ID, companyId);
		document.addKeyword(Field.PORTLET_ID, PORTLET_ID);
		document.addKeyword(Field.GROUP_ID, groupId);
		document.addKeyword(Field.SCOPE_GROUP_ID, scopeGroupId);
		document.addKeyword(Field.USER_ID, userId);

		document.addText(Field.TITLE, subject);
		document.addText(Field.CONTENT, body);

		document.addKeyword(
			Field.ENTRY_CLASS_NAME, Message.class.getName());
		document.addKeyword(Field.ENTRY_CLASS_PK, messageId);

		document.addKeyword("accountId", accountId);
		document.addKeyword("folderId", folderId);
		document.addKeyword("remoteMessageId", remoteMessageId);

		ExpandoBridgeIndexerUtil.addAttributes(document, expandoBridge);

		return document;
	}

	protected void doReindex(Object obj) throws Exception {
		Message message = (Message)obj;

		Document document = getDocument(message);

		SearchEngineUtil.updateDocument(message.getCompanyId(), document);
	}

	protected void doReindex(String className, long classPK) throws Exception {
		Message message = MessageLocalServiceUtil.getMessage(classPK);

		doReindex(message);
	}

	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexMessages(companyId);
	}

	protected String getPortletId(SearchContext searchContext) {
		return PORTLET_ID;
	}

	protected void reindexMessages(long companyId) throws Exception {
		int count = MessageLocalServiceUtil.getCompanyMessagesCount(companyId);

		int pages = count / Indexer.DEFAULT_INTERVAL;

		for (int i = 0; i <= pages; i++) {
			int start = (i * Indexer.DEFAULT_INTERVAL);
			int end = start + Indexer.DEFAULT_INTERVAL;

			reindexMessages(companyId, start, end);
		}
	}

	protected void reindexMessages(long companyId, int start, int end)
		throws Exception {

		List<Message> messages = MessageLocalServiceUtil.getCompanyMessages(
			companyId, start, end);

		if (messages.isEmpty()) {
			return;
		}

		Collection<Document> documents = new ArrayList<Document>();

		for (Message message : messages) {
			Document document = getDocument(message);

			documents.add(document);
		}

		SearchEngineUtil.updateDocuments(companyId, documents);
	}

}