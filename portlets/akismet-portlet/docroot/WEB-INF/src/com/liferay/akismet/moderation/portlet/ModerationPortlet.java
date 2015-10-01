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

package com.liferay.akismet.moderation.portlet;

import com.liferay.akismet.util.AkismetConstants;
import com.liferay.akismet.util.AkismetUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.messageboards.NoSuchMessageException;
import com.liferay.portlet.messageboards.RequiredMessageException;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageServiceUtil;
import com.liferay.wiki.constants.WikiPortletKeys;
import com.liferay.wiki.exception.NoSuchPageException;
import com.liferay.wiki.model.WikiNode;
import com.liferay.wiki.model.WikiPage;
import com.liferay.wiki.service.WikiPageLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

/**
 * @author Amos Fong
 * @author Peter Shin
 */
public class ModerationPortlet extends MVCPortlet {

	public void deleteDiscussionMBMessages(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] mbMessageIds = ParamUtil.getLongValues(
			actionRequest, "deleteMBMessageIds");

		for (long mbMessageId : mbMessageIds) {
			MBMessageLocalServiceUtil.deleteDiscussionMessage(mbMessageId);
		}
	}

	public void deleteMBMessages(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] mbMessageIds = ParamUtil.getLongValues(
			actionRequest, "deleteMBMessageIds");

		for (long mbMessageId : mbMessageIds) {
			MBMessageServiceUtil.deleteMessage(mbMessageId);
		}
	}

	public void markNotSpamMBMessages(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] mbMessageIds = ParamUtil.getLongValues(
			actionRequest, "notSpamMBMessageIds");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		for (long mbMessageId : mbMessageIds) {
			MBMessage mbMessage = MBMessageLocalServiceUtil.updateStatus(
				themeDisplay.getUserId(), mbMessageId,
				WorkflowConstants.STATUS_APPROVED, serviceContext);

			if (AkismetUtil.isMessageBoardsEnabled(mbMessage.getCompanyId())) {
				AkismetUtil.submitHam(mbMessage);
			}
		}
	}

	public void markNotSpamWikiPages(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] wikiPageIds = ParamUtil.getLongValues(
			actionRequest, "notSpamWikiPageIds");

		List<String> wikiPageLinks = new ArrayList<>();

		for (long wikiPageId : wikiPageIds) {
			WikiPage wikiPage = WikiPageLocalServiceUtil.getPageByPageId(
				wikiPageId);

			WikiPage latestVersionWikiPage = AkismetUtil.getWikiPage(
				wikiPage.getNodeId(), wikiPage.getTitle(),
				wikiPage.getVersion(), false);

			String latestContent = null;

			if (latestVersionWikiPage != null) {
				latestContent = latestVersionWikiPage.getContent();
			}

			WikiPage previousVersionWikiPage = AkismetUtil.getWikiPage(
				wikiPage.getNodeId(), wikiPage.getTitle(),
				wikiPage.getVersion(), true);

			String previousContent = null;

			if (previousVersionWikiPage != null) {
				previousContent = previousVersionWikiPage.getContent();
			}

			// Selected version

			wikiPage.setStatus(WorkflowConstants.STATUS_APPROVED);
			wikiPage.setSummary(StringPool.BLANK);

			wikiPage = WikiPageLocalServiceUtil.updateWikiPage(wikiPage);

			// Latest version

			if ((latestContent != null) &&
				((previousContent == null) ||
				 latestContent.equals(previousContent))) {

				ServiceContext serviceContext =
					ServiceContextFactory.getInstance(actionRequest);

				WikiPageLocalServiceUtil.revertPage(
					themeDisplay.getUserId(), wikiPage.getNodeId(),
					wikiPage.getTitle(), wikiPage.getVersion(), serviceContext);
			}
			else {
				StringBundler sb = new StringBundler(5);

				sb.append("<a href=\"");

				long plid = PortalUtil.getPlidFromPortletId(
					wikiPage.getGroupId(), WikiPortletKeys.WIKI);

				LiferayPortletURL liferayPortletURL =
					PortletURLFactoryUtil.create(
						actionRequest, WikiPortletKeys.WIKI, plid,
						PortletRequest.RENDER_PHASE);

				WikiNode wikiNode = wikiPage.getNode();

				liferayPortletURL.setParameter("struts_action", "/wiki/view");
				liferayPortletURL.setParameter("nodeName", wikiNode.getName());
				liferayPortletURL.setParameter("title", wikiPage.getTitle());
				liferayPortletURL.setParameter(
					"version", String.valueOf(wikiPage.getVersion()));

				sb.append(liferayPortletURL.toString());
				sb.append("\" target=\"_blank\">");
				sb.append(HtmlUtil.escape(wikiPage.getTitle()));
				sb.append("</a>");

				wikiPageLinks.add(sb.toString());
			}

			// Akismet

			if (AkismetUtil.isWikiEnabled(wikiPage.getCompanyId())) {
				AkismetUtil.submitHam(wikiPage);
			}
		}

		if (!wikiPageLinks.isEmpty()) {
			SessionMessages.add(
				actionRequest, "anotherUserHasMadeChangesToThesePages",
				StringUtil.merge(wikiPageLinks, "<br />"));

			addSuccessMessage(actionRequest, actionResponse);

			super.sendRedirect(actionRequest, actionResponse);
		}
	}

	public void spamWikiPages(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] wikiPageIds = ParamUtil.getLongValues(
			actionRequest, "spamWikiPageIds");

		for (long wikiPageId : wikiPageIds) {
			WikiPage wikiPage = WikiPageLocalServiceUtil.getPageByPageId(
				wikiPageId);

			wikiPage.setSummary(AkismetConstants.WIKI_PAGE_MARKED_AS_SPAM);

			WikiPageLocalServiceUtil.updateWikiPage(wikiPage);
		}
	}

	@Override
	protected boolean isProcessPortletRequest(PortletRequest portletRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (permissionChecker.isCompanyAdmin()) {
			return true;
		}

		Group group = themeDisplay.getScopeGroup();

		if (group.isSite() &&
			permissionChecker.isGroupAdmin(themeDisplay.getScopeGroupId())) {

			return true;
		}

		return false;
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof NoSuchMessageException ||
			cause instanceof NoSuchPageException ||
			cause instanceof PrincipalException ||
			cause instanceof RequiredMessageException) {

			return true;
		}

		return false;
	}

}