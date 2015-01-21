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

package com.liferay.knowledgebase.model.impl;

import com.liferay.knowledgebase.article.util.KBArticleAttachmentsUtil;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBArticleConstants;
import com.liferay.knowledgebase.model.KBFolder;
import com.liferay.knowledgebase.model.KBFolderConstants;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.KBArticleServiceUtil;
import com.liferay.knowledgebase.service.KBFolderServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.util.PortalUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class KBArticleImpl extends KBArticleBaseImpl {

	public KBArticleImpl() {
	}

	@Override
	public List<Long> getAncestorResourcePrimaryKeys()
		throws PortalException, SystemException {

		List<Long> ancestorResourcePrimaryKeys = new ArrayList<Long>();

		ancestorResourcePrimaryKeys.add(getResourcePrimKey());

		KBArticle kbArticle = this;

		while (!kbArticle.isRoot()) {
			kbArticle = kbArticle.getParentKBArticle();

			if (kbArticle == null) {
				break;
			}

			ancestorResourcePrimaryKeys.add(kbArticle.getResourcePrimKey());
		}

		return ancestorResourcePrimaryKeys;
	}

	@Override
	public List<FileEntry> getAttachmentsFileEntries()
		throws PortalException, SystemException {

		return PortletFileRepositoryUtil.getPortletFileEntries(
			getGroupId(), getAttachmentsFolderId(),
			WorkflowConstants.STATUS_APPROVED);
	}

	@Override
	public long getAttachmentsFolderId()
		throws PortalException, SystemException {

		if (_attachmentsFolderId > 0) {
			return _attachmentsFolderId;
		}

		_attachmentsFolderId = KBArticleAttachmentsUtil.getFolderId(
			getGroupId(), getUserId(), getResourcePrimKey());

		return _attachmentsFolderId;
	}

	@Override
	public long getClassNameId() {
		if (_classNameId == 0) {
			_classNameId = PortalUtil.getClassNameId(
				KBArticleConstants.getClassName());
		}

		return _classNameId;
	}

	@Override
	public long getClassPK() {
		if (isApproved() || isPending()) {
			return getResourcePrimKey();
		}

		return getKbArticleId();
	}

	@Override
	public KBArticle getParentKBArticle()
		throws PortalException, SystemException {

		long parentResourcePrimKey = getParentResourcePrimKey();

		if ((parentResourcePrimKey <= 0) ||
			(getParentResourceClassNameId() != getClassNameId())) {

			return null;
		}

		return KBArticleLocalServiceUtil.getLatestKBArticle(
			parentResourcePrimKey, WorkflowConstants.STATUS_APPROVED);
	}

	@Override
	public String getParentTitle(Locale locale, int status)
		throws PortalException, SystemException {

		if (isRoot()) {
			return "(" + LanguageUtil.get(locale, "none") + ")";
		}

		if (getParentResourceClassNameId() == getClassNameId()) {
			KBArticle kbArticle = KBArticleServiceUtil.getLatestKBArticle(
				getParentResourcePrimKey(), status);

			return kbArticle.getTitle();
		}
		else {
			KBFolder kbFolder = KBFolderServiceUtil.getKBFolder(
				getParentResourcePrimKey());

			return kbFolder.getName();
		}
	}

	@Override
	public boolean isFirstVersion() {
		if (getVersion() == KBArticleConstants.DEFAULT_VERSION) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isResourceMain() {
		return isMain();
	}

	@Override
	public boolean isRoot() {
		if (getParentResourcePrimKey() ==
				KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			return true;
		}

		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(KBArticleImpl.class);

	private long _attachmentsFolderId;
	private long _classNameId;

}