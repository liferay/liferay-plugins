/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
import com.liferay.mail.service.persistence.MessageActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeIndexerUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Scott Lee
 */
public class MessageIndexer extends BaseIndexer {

	public static final String CLASS_NAME = Message.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		SearchContext searchContext = new SearchContext();

		searchContext.setSearchEngineId(getSearchEngineId());

		if (obj instanceof Account) {
			Account account = (Account)obj;

			searchContext.setCompanyId(account.getCompanyId());
			searchContext.setEnd(QueryUtil.ALL_POS);
			searchContext.setSorts(SortFactoryUtil.getDefaultSorts());
			searchContext.setStart(QueryUtil.ALL_POS);

			BooleanQuery booleanQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

			booleanQuery.addRequiredTerm("accountId", account.getAccountId());

			Hits hits = SearchEngineUtil.search(searchContext, booleanQuery);

			List<String> uids = new ArrayList<>(hits.getLength());

			for (int i = 0; i < hits.getLength(); i++) {
				Document document = hits.doc(i);

				uids.add(document.get(Field.UID));
			}

			SearchEngineUtil.deleteDocuments(
				getSearchEngineId(), account.getCompanyId(), uids,
				isCommitImmediately());
		}
		else if (obj instanceof Folder) {
			Folder folder = (Folder)obj;

			searchContext.setCompanyId(folder.getCompanyId());
			searchContext.setEnd(QueryUtil.ALL_POS);
			searchContext.setSorts(SortFactoryUtil.getDefaultSorts());
			searchContext.setStart(QueryUtil.ALL_POS);

			BooleanQuery booleanQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

			booleanQuery.addRequiredTerm("folderId", folder.getFolderId());

			Hits hits = SearchEngineUtil.search(searchContext, booleanQuery);

			List<String> uids = new ArrayList<>(hits.getLength());

			for (int i = 0; i < hits.getLength(); i++) {
				Document document = hits.doc(i);

				uids.add(document.get(Field.UID));
			}

			SearchEngineUtil.deleteDocuments(
				getSearchEngineId(), folder.getCompanyId(), uids,
				isCommitImmediately());
		}
		else if (obj instanceof Message) {
			Message message = (Message)obj;

			Document document = new DocumentImpl();

			document.addUID(CLASS_NAME, message.getMessageId());

			SearchEngineUtil.deleteDocument(
				getSearchEngineId(), message.getCompanyId(),
				document.get(Field.UID), isCommitImmediately());
		}
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		Message message = (Message)obj;

		Document document = getBaseModelDocument(CLASS_NAME, message);

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
		Document doc, Locale locale, String snippet,
		PortletRequest portletRequest, PortletResponse portletResponse) {

		return null;
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		Message message = (Message)obj;

		Document document = getDocument(message);

		SearchEngineUtil.updateDocument(
			getSearchEngineId(), message.getCompanyId(), document,
			isCommitImmediately());
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

	protected void reindexMessages(long companyId) throws PortalException {
		ActionableDynamicQuery actionableDynamicQuery =
			new MessageActionableDynamicQuery() {

			@Override
			protected void performAction(Object object) throws PortalException {
				Message message = (Message)object;

				Document document = getDocument(message);

				addDocument(document);
			}

		};

		actionableDynamicQuery.setCompanyId(companyId);
		actionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		actionableDynamicQuery.performActions();
	}

}