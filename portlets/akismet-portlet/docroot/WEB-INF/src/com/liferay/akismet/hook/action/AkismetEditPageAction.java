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

package com.liferay.akismet.hook.action;

import com.liferay.akismet.util.AkismetConstants;
import com.liferay.akismet.util.AkismetUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.wiki.model.WikiPage;
import com.liferay.wiki.service.WikiPageLocalServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Peter Shin
 */
public class AkismetEditPageAction extends BaseStrutsPortletAction {

	@Override
	public void processAction(
			StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (cmd.equals("updateSummary")) {
			try {
				updateSummary(actionRequest, actionResponse);

				String redirect = PortalUtil.escapeRedirect(
					ParamUtil.getString(actionRequest, "redirect"));

				actionResponse.sendRedirect(redirect);
			}
			catch (PrincipalException pe) {
				throw pe;
			}
			catch (Exception e) {
				SessionErrors.add(actionRequest, e.getClass());
			}
		}
		else {
			originalStrutsPortletAction.processAction(
				portletConfig, actionRequest, actionResponse);
		}
	}

	@Override
	public String render(
			StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		return originalStrutsPortletAction.render(
			portletConfig, renderRequest, renderResponse);
	}

	@Override
	public void serveResource(
			StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ResourceRequest resourceRequest,
			ResourceResponse resourceResponse)
		throws Exception {

		originalStrutsPortletAction.serveResource(
			portletConfig, resourceRequest, resourceResponse);
	}

	protected void checkPermission(long scopeGroupId) throws PortalException {
		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (!permissionChecker.hasPermission(
				scopeGroupId, "com.liferay.portlet.wiki", scopeGroupId,
				ActionKeys.ADD_NODE)) {

			throw new PrincipalException();
		}
	}

	protected void updateSummary(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		checkPermission(themeDisplay.getScopeGroupId());

		long pageId = ParamUtil.getLong(actionRequest, "pageId");
		boolean spam = ParamUtil.getBoolean(actionRequest, "spam");

		WikiPage wikiPage = WikiPageLocalServiceUtil.getPageByPageId(pageId);

		WikiPage latestVersionWikiPage = AkismetUtil.getWikiPage(
			wikiPage.getNodeId(), wikiPage.getTitle(), wikiPage.getVersion(),
			false);

		String latestContent = null;
		double latestVersion = -1;

		if (latestVersionWikiPage != null) {
			latestContent = latestVersionWikiPage.getContent();
			latestVersion = latestVersionWikiPage.getVersion();
		}

		WikiPage previousVersionWikiPage = AkismetUtil.getWikiPage(
			wikiPage.getNodeId(), wikiPage.getTitle(), wikiPage.getVersion(),
			true);

		String previousContent = null;
		double previousVersion = -1;

		if (previousVersionWikiPage != null) {
			previousContent = previousVersionWikiPage.getContent();
			previousVersion = previousVersionWikiPage.getVersion();
		}

		String pattern = StringPool.BLANK;

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		if (spam) {
			pattern = "version-x-was-marked-as-spam";

			// Selected version

			wikiPage.setStatus(WorkflowConstants.STATUS_APPROVED);
			wikiPage.setSummary(AkismetConstants.WIKI_PAGE_PENDING_APPROVAL);

			wikiPage = WikiPageLocalServiceUtil.updateWikiPage(wikiPage);

			// Latest version

			if ((wikiPage.getVersion() >= latestVersion) &&
				(previousVersionWikiPage != null)) {

				WikiPageLocalServiceUtil.revertPage(
					themeDisplay.getUserId(), wikiPage.getNodeId(),
					wikiPage.getTitle(), previousVersion, serviceContext);
			}

			// Akismet

			if (AkismetUtil.isWikiEnabled(themeDisplay.getCompanyId())) {
				AkismetUtil.submitSpam(wikiPage);
			}
		}
		else {
			pattern = "version-x-was-marked-as-not-spam";

			// Selected version

			wikiPage.setStatus(WorkflowConstants.STATUS_APPROVED);
			wikiPage.setSummary(StringPool.BLANK);

			wikiPage = WikiPageLocalServiceUtil.updateWikiPage(wikiPage);

			// Latest version

			if ((latestContent != null) &&
				((previousContent == null) ||
				 latestContent.equals(previousContent))) {

				WikiPageLocalServiceUtil.revertPage(
					themeDisplay.getUserId(), wikiPage.getNodeId(),
					wikiPage.getTitle(), wikiPage.getVersion(), serviceContext);
			}
			else {
				SessionMessages.add(actionRequest, "anotherUserHasMadeChanges");
			}

			// Akismet

			if (AkismetUtil.isWikiEnabled(themeDisplay.getCompanyId())) {
				AkismetUtil.submitHam(wikiPage);
			}
		}

		String value = LanguageUtil.format(
			themeDisplay.getLocale(), pattern,
			String.valueOf(wikiPage.getVersion()), false);

		SessionMessages.add(actionRequest, "requestProcessed", value);
	}

}