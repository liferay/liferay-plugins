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

package com.liferay.knowledgebase.util;

import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBArticleConstants;
import com.liferay.knowledgebase.model.KBComment;
import com.liferay.knowledgebase.model.KBCommentConstants;
import com.liferay.knowledgebase.model.KBFolder;
import com.liferay.knowledgebase.model.KBFolderConstants;
import com.liferay.knowledgebase.service.KBCommentServiceUtil;
import com.liferay.knowledgebase.service.KBFolderLocalServiceUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;

/**
 * @author Adolfo Pérez
 */
public class KBSuggestionListDisplayContext {

	public KBSuggestionListDisplayContext(
		KBArticle kbArticle, String selectedNavItem) {

		_kbArticle = kbArticle;
		_selectedNavItem = selectedNavItem;
	}

	public KBSuggestionListDisplayContext(
		long groupId, String selectedNavItem) {

		_groupId = groupId;
		_selectedNavItem = selectedNavItem;
	}

	public int getCompletedKBCommentsCount()
		throws PortalException, SystemException {

		return getKBCommentsCount(KBCommentConstants.STATUS_COMPLETED);
	}

	public int getInProgressKBCommentsCount()
		throws PortalException, SystemException {

		return getKBCommentsCount(KBCommentConstants.STATUS_IN_PROGRESS);
	}

	public List<KBComment> getKBComments(
			int status, SearchContainer<KBComment> searchContainer)
		throws PortalException, SystemException {

		if (_kbArticle == null) {
			return KBCommentServiceUtil.getKBComments(
				_groupId, status, searchContainer.getStart(),
				searchContainer.getEnd());
		}
		else {
			return KBCommentServiceUtil.getKBComments(
				_groupId, KBArticleConstants.getClassName(),
				_kbArticle.getClassPK(), status, searchContainer.getStart(),
				searchContainer.getEnd());
		}
	}

	public int getKBCommentsCount(int status)
		throws PortalException, SystemException {

		if (_kbArticle == null) {
			return KBCommentServiceUtil.getKBCommentsCount(_groupId, status);
		}
		else {
			return KBCommentServiceUtil.getKBCommentsCount(
				_groupId, KBArticleConstants.getClassName(),
				_kbArticle.getClassPK(), status);
		}
	}

	public int getNewKBCommentsCount() throws PortalException, SystemException {
		return getKBCommentsCount(KBCommentConstants.STATUS_NEW);
	}

	public String getSelectedNavItem() {
		return _selectedNavItem;
	}

	public String getViewSuggestionURL(PortletURL portletURL, String navItem)
		throws PortalException, SystemException {

		portletURL.setParameter("navItem", navItem);
		portletURL.setParameter("expanded", Boolean.TRUE.toString());

		if (_kbArticle == null) {
			portletURL.setParameter("mvcPath", "/admin/view_suggestions.jsp");
		}
		else if (Validator.isNull(_kbArticle.getUrlTitle())) {
			portletURL.setParameter(
				"resourceClassNameId",
				String.valueOf(_kbArticle.getClassNameId()));
			portletURL.setParameter(
				"resourcePrimKey",
				String.valueOf(_kbArticle.getResourcePrimKey()));
		}
		else {
			portletURL.setParameter("urlTitle", _kbArticle.getUrlTitle());

			if (_kbArticle.getKbFolderId() !=
					KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

				KBFolder kbFolder = KBFolderLocalServiceUtil.getKBFolder(
					_kbArticle.getKbFolderId());

				portletURL.setParameter(
					"kbFolderUrlTitle", kbFolder.getUrlTitle());
			}
		}

		return portletURL.toString() + "#kbSuggestions";
	}

	public String getViewSuggestionURL(
			RenderResponse renderResponse, String navItem)
		throws PortalException, SystemException {

		return getViewSuggestionURL(renderResponse.createRenderURL(), navItem);
	}

	public boolean isShowKBArticleTitle() {
		return _kbArticle == null;
	}

	private long _groupId;
	private KBArticle _kbArticle;
	private String _selectedNavItem;

}