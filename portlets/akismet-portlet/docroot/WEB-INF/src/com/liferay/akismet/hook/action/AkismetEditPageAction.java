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

package com.liferay.akismet.hook.action;

import com.liferay.akismet.util.AkismetConstants;
import com.liferay.akismet.util.AkismetUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;
import com.liferay.portlet.wiki.util.comparator.PageVersionComparator;

import java.util.List;

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

		if (cmd.equals("updateStatus")) {
			try {
				updateStatus(actionRequest, actionResponse);

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

	protected WikiPage getWikiPage(WikiPage wikiPage, boolean previous)
		throws SystemException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			WikiPage.class);

		Property nodeIdProperty = PropertyFactoryUtil.forName("nodeId");

		dynamicQuery.add(nodeIdProperty.eq(wikiPage.getNodeId()));

		Property titleProperty = PropertyFactoryUtil.forName("title");

		dynamicQuery.add(titleProperty.eq(wikiPage.getTitle()));

		Property statusProperty = PropertyFactoryUtil.forName("status");

		dynamicQuery.add(statusProperty.eq(WorkflowConstants.STATUS_APPROVED));

		Property versionProperty = PropertyFactoryUtil.forName("version");

		if (previous) {
			dynamicQuery.add(versionProperty.lt(wikiPage.getVersion()));
		}
		else {
			dynamicQuery.add(versionProperty.ge(wikiPage.getVersion()));
		}

		OrderFactoryUtil.addOrderByComparator(
			dynamicQuery, new PageVersionComparator());

		List<WikiPage> wikiPages = WikiPageLocalServiceUtil.dynamicQuery(
			dynamicQuery, 0, 1);

		if (wikiPages.isEmpty()) {
			return null;
		}

		return wikiPages.get(0);
	}

	protected void updateStatus(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException, SystemException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		checkPermission(themeDisplay.getScopeGroupId());

		long pageId = ParamUtil.getLong(actionRequest, "pageId");
		boolean spam = ParamUtil.getBoolean(actionRequest, "spam");

		WikiPage wikiPage = WikiPageLocalServiceUtil.getPageByPageId(pageId);

		WikiPage latestVersionWikiPage = getWikiPage(wikiPage, false);

		String latestContent = null;
		double latestVersion = -1;

		if (latestVersionWikiPage != null) {
			latestContent = latestVersionWikiPage.getContent();
			latestVersion = latestVersionWikiPage.getVersion();
		}

		WikiPage previousVersionWikiPage = getWikiPage(wikiPage, true);

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

			// Latest version

			if ((wikiPage.getVersion() >= latestVersion) &&
				 wikiPage.isApproved()) {

				if (previousVersionWikiPage != null) {
					WikiPageLocalServiceUtil.revertPage(
						themeDisplay.getUserId(), wikiPage.getNodeId(),
						wikiPage.getTitle(), previousVersion, serviceContext);
				}
				else {
					WikiPageLocalServiceUtil.updatePage(
						themeDisplay.getUserId(), wikiPage.getNodeId(),
						wikiPage.getTitle(), latestVersion, null,
						StringPool.BLANK, true, wikiPage.getFormat(),
						wikiPage.getParentTitle(), wikiPage.getRedirectTitle(),
						serviceContext);
				}
			}

			// Selected version

			wikiPage.setStatus(WorkflowConstants.STATUS_DENIED);
			wikiPage.setSummary(AkismetConstants.WIKI_PAGE_PENDING_APPROVAL);

			wikiPage = WikiPageLocalServiceUtil.updateWikiPage(wikiPage);

			// Akismet

			pattern = "version-x-was-marked-as-spam";

			if (AkismetUtil.isWikiEnabled(themeDisplay.getCompanyId())) {
				AkismetUtil.submitSpam(wikiPage);
			}
		}
		else {

			// Latest version

			if ((latestContent != null) && (previousContent != null) &&
				 latestContent.equals(previousContent)) {

				WikiPageLocalServiceUtil.revertPage(
					themeDisplay.getUserId(), wikiPage.getNodeId(),
					wikiPage.getTitle(), wikiPage.getVersion(), serviceContext);
			}
			else {
				SessionMessages.add(actionRequest, "anotherUserHasMadeChanges");
			}

			// Selected version

			wikiPage.setStatus(WorkflowConstants.STATUS_APPROVED);
			wikiPage.setSummary(StringPool.BLANK);

			wikiPage = WikiPageLocalServiceUtil.updateWikiPage(wikiPage);

			// Akismet

			pattern = "version-x-was-marked-as-not-spam";

			if (AkismetUtil.isWikiEnabled(themeDisplay.getCompanyId())) {
				AkismetUtil.submitHam(wikiPage);
			}
		}

		String value = LanguageUtil.format(
			themeDisplay.getLocale(), pattern, wikiPage.getVersion());

		SessionMessages.add(actionRequest, "requestProcessed", value);
	}

}