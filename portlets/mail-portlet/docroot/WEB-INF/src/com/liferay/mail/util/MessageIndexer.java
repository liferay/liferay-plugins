/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeIndexerUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

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

	public String getPortletId() {
		return PORTLET_ID;
	}

	protected void addReindexCriteria(
		DynamicQuery dynamicQuery, long companyId) {

		Property property = PropertyFactoryUtil.forName("companyId");

		dynamicQuery.add(property.eq(companyId));
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		SearchContext searchContext = new SearchContext();

		searchContext.setSearchEngineId(getSearchEngineId());

		if (obj instanceof Account) {
			Account account = (Account)obj;

			BooleanQuery booleanQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			booleanQuery.addRequiredTerm(Field.PORTLET_ID, PORTLET_ID);

			booleanQuery.addRequiredTerm("accountId", account.getAccountId());

			Hits hits = SearchEngineUtil.search(
				getSearchEngineId(), account.getCompanyId(), booleanQuery,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (int i = 0; i < hits.getLength(); i++) {
				Document document = hits.doc(i);

				SearchEngineUtil.deleteDocument(
					getSearchEngineId(), account.getCompanyId(),
					document.get(Field.UID));
			}
		}
		else if (obj instanceof Folder) {
			Folder folder = (Folder)obj;

			BooleanQuery booleanQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			booleanQuery.addRequiredTerm(Field.PORTLET_ID, PORTLET_ID);

			booleanQuery.addRequiredTerm("folderId", folder.getFolderId());

			Hits hits = SearchEngineUtil.search(
				getSearchEngineId(), folder.getCompanyId(), booleanQuery,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (int i = 0; i < hits.getLength(); i++) {
				Document document = hits.doc(i);

				SearchEngineUtil.deleteDocument(
					getSearchEngineId(), folder.getCompanyId(),
					document.get(Field.UID));
			}
		}
		else if (obj instanceof Message) {
			Message message = (Message)obj;

			Document document = new DocumentImpl();

			document.addUID(PORTLET_ID, message.getMessageId());

			SearchEngineUtil.deleteDocument(
				getSearchEngineId(), message.getCompanyId(),
				document.get(Field.UID));
		}
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		Message message = (Message)obj;

		Document document = getBaseModelDocument(PORTLET_ID, message);

		ExpandoBridge expandoBridge = message.getExpandoBridge();

		document.addText(
			Field.CONTENT, HtmlUtil.extractText(message.getBody()));
		document.addKeyword(Field.FOLDER_ID, message.getFolderId());
		document.addText(Field.TITLE, message.getSubject());

		document.addKeyword("accountId", message.getAccountId());
		document.addKeyword("remoteMessageId", message.getRemoteMessageId());

		ExpandoBridgeIndexerUtil.addAttributes(document, expandoBridge);

		return document;
	}

	@Override
	protected Summary doGetSummary(
		Document doc, Locale locale, String snippet, PortletURL portletURL) {

		return null;
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		Message message = (Message)obj;

		Document document = getDocument(message);

		SearchEngineUtil.updateDocument(
			getSearchEngineId(), message.getCompanyId(), document);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		Message message = MessageLocalServiceUtil.getMessage(classPK);

		doReindex(message);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexMessages(companyId);
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		return PORTLET_ID;
	}

	protected void reindexMessages(long companyId) throws Exception {
		Class<?> clazz = getClass();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Message.class, clazz.getClassLoader());

		Projection minMessageIdProjection = ProjectionFactoryUtil.min(
			"messageId");
		Projection maxMessageIdProjection = ProjectionFactoryUtil.max(
			"messageId");

		ProjectionList projectionList = ProjectionFactoryUtil.projectionList();

		projectionList.add(minMessageIdProjection);
		projectionList.add(maxMessageIdProjection);

		dynamicQuery.setProjection(projectionList);

		addReindexCriteria(dynamicQuery, companyId);

		List<Object[]> results = MessageLocalServiceUtil.dynamicQuery(
			dynamicQuery);

		Object[] minAndMaxMessageIds = results.get(0);

		if ((minAndMaxMessageIds[0] == null) ||
			(minAndMaxMessageIds[1] == null)) {

			return;
		}

		long minMessageId = (Long)minAndMaxMessageIds[0];
		long maxMessageId = (Long)minAndMaxMessageIds[1];

		long startMessageId = minMessageId;
		long endMessageId = startMessageId + DEFAULT_INTERVAL;

		while (startMessageId <= maxMessageId) {
			reindexMessages(companyId, startMessageId, endMessageId);

			startMessageId = endMessageId;
			endMessageId += DEFAULT_INTERVAL;
		}
	}

	protected void reindexMessages(
			long companyId, long startMessageId, long endMessageId)
		throws Exception {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Message.class, PortalClassLoaderUtil.getClassLoader());

		Property property = PropertyFactoryUtil.forName("messageId");

		dynamicQuery.add(property.ge(startMessageId));
		dynamicQuery.add(property.lt(endMessageId));

		addReindexCriteria(dynamicQuery, companyId);

		List<Message> messages = MessageLocalServiceUtil.dynamicQuery(
			dynamicQuery);

		if (messages.isEmpty()) {
			return;
		}

		Collection<Document> documents = new ArrayList<Document>();

		for (Message message : messages) {
			Document document = getDocument(message);

			documents.add(document);
		}

		SearchEngineUtil.updateDocuments(
			getSearchEngineId(), companyId, documents);
	}

}