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
import com.liferay.knowledgebase.model.KBCommentConstants;
import com.liferay.knowledgebase.service.KBCommentLocalServiceUtil;

import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;

/**
 * @author Adolfo Pérez
 */
public class KBFeedbackListDisplayContext {

	public KBFeedbackListDisplayContext(
		KBArticle kbArticle, String selectedNavItem) {

		_kbArticle = kbArticle;
		_selectedNavItem = selectedNavItem;
	}

	public KBFeedbackListDisplayContext(long groupId, String selectedNavItem) {
		_groupId = groupId;
		_selectedNavItem = selectedNavItem;
	}

	public int getCompletedKBCommentsCount() {
		return getKBCommentsCountByStatus(KBCommentConstants.STATUS_COMPLETED);
	}

	public int getInProgressKBCommentsCount() {
		return getKBCommentsCountByStatus(
			KBCommentConstants.STATUS_IN_PROGRESS);
	}

	public int getNewKBCommentsCount() {
		return getKBCommentsCountByStatus(KBCommentConstants.STATUS_NEW);
	}

	public String getSelectedNavItem() {
		return _selectedNavItem;
	}

	public String getViewFeedbackURL(PortletURL portletURL, String navItem) {
		portletURL.setParameter("navItem", navItem);
		portletURL.setParameter("expanded", Boolean.TRUE.toString());

		if (_kbArticle == null) {
			portletURL.setParameter("mvcPath", "/admin/view_feedback.jsp");
		}

		return portletURL.toString() + "#kbFeedback";
	}

	public String getViewFeedbackURL(
		RenderResponse renderResponse, String navItem) {

		return getViewFeedbackURL(renderResponse.createRenderURL(), navItem);
	}

	public boolean isShowKBArticleTitle() {
		return _kbArticle == null;
	}

	protected int getKBCommentsCountByStatus(int status) {
		int kbCommentsCount = 0;

		if (_kbArticle == null) {
			kbCommentsCount = KBCommentLocalServiceUtil.getKBCommentsCount(
				_groupId, status);
		}
		else {
			kbCommentsCount = KBCommentLocalServiceUtil.getKBCommentsCount(
				KBArticle.class.getName(), _kbArticle.getClassPK(), status);
		}

		return kbCommentsCount;
	}

	private long _groupId;
	private KBArticle _kbArticle;
	private String _selectedNavItem;

}