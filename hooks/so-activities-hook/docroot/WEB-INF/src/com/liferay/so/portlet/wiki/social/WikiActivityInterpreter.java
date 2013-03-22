/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.so.portlet.wiki.social;

import com.liferay.compat.portal.service.ServiceContext;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityConstants;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.model.WikiPageResource;
import com.liferay.portlet.wiki.service.WikiNodeLocalServiceUtil;
import com.liferay.portlet.wiki.service.WikiPageResourceLocalServiceUtil;
import com.liferay.so.activities.model.BaseSocialActivityInterpreter;
import com.liferay.so.util.Time;

import java.text.Format;

import java.util.Date;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Jonathan Lee
 */
public class WikiActivityInterpreter extends BaseSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected String getBody(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		AssetRenderer assetRenderer = getAssetRenderer(activity);

		String linkURL = getLinkURL(activity, serviceContext);

		String pageTitle = wrapLink(
			linkURL,
			HtmlUtil.escape(
				assetRenderer.getTitle(serviceContext.getLocale())));

		LiferayPortletRequest liferayPortletRequest =
			serviceContext.getLiferayPortletRequest();

		if (Validator.isNotNull(
				assetRenderer.getIconPath(liferayPortletRequest))) {

			pageTitle = wrapLink(
				linkURL, assetRenderer.getIconPath(liferayPortletRequest),
				HtmlUtil.escape(
					assetRenderer.getTitle(serviceContext.getLocale())));
		}

		StringBundler sb = new StringBundler(5);

		sb.append("<div class=\"activity-body\"><div class=\"title\">");
		sb.append(pageTitle);
		sb.append("</div><div class='wiki-page-content'>");
		sb.append(
			StringUtil.shorten(
				assetRenderer.getSummary(serviceContext.getLocale()), 200));
		sb.append("</div></div>");

		return sb.toString();
	}

	@Override
	protected String getLink(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		return wrapLink(
			getLinkURL(activity, serviceContext),
			serviceContext.translate("view-wiki"));
	}

	@Override
	protected String getTitle(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		String userName = getUserName(activity.getUserId(), serviceContext);

		Format dateFormatDate = getFormatDateTime(
			serviceContext.getLocale(), serviceContext.getTimeZone());

		StringBundler sb = new StringBundler(10);

		sb.append("<div class=\"activity-header\">");
		sb.append("<div class=\"activity-time\" title=\"");
		sb.append(dateFormatDate.format(new Date(activity.getCreateDate())));
		sb.append("\">");
		sb.append(
			Time.getRelativeTimeSpan(
				activity.getCreateDate(), serviceContext.getLocale(),
				serviceContext.getTimeZone()));
		sb.append("</div><div class=\"activity-user-name\">");

		if (activity.getGroupId() != serviceContext.getScopeGroupId()) {
			String groupName = getGroupName(
				activity.getGroupId(), serviceContext);

			Object[] titleArguments = new Object[] {userName, groupName};

			sb.append(serviceContext.translate("x-in-x", titleArguments));
		}
		else {
			sb.append(userName);
		}

		sb.append("</div></div><div class=\"activity-action\">");

		int activityType = activity.getType();

		String actionPattern = null;

		if ((activityType == _ADD_COMMENT) ||
			(activityType == SocialActivityConstants.TYPE_ADD_COMMENT)) {

			actionPattern = "commented-on-a-wiki-page";
		}
		else if (activityType == _ADD_PAGE) {
			actionPattern = "created-a-new-wiki-page";
		}
		else if (activityType == _UPDATE_PAGE) {
			actionPattern = "updated-a-wiki-page";
		}

		WikiPageResource pageResource =
			WikiPageResourceLocalServiceUtil.getPageResource(
				activity.getClassPK());

		WikiNode node = WikiNodeLocalServiceUtil.getNode(
			pageResource.getNodeId());

		if (Validator.isNotNull(node)) {
			actionPattern += "-in-the-x-wiki";

			String nodeTitle = HtmlUtil.escape(node.getName());

			PortletURL nodeURL = null;

			long portletPlid = PortalUtil.getPlidFromPortletId(
				activity.getGroupId(), false, PortletKeys.WIKI);

			if (portletPlid != 0) {
				nodeURL = PortletURLFactoryUtil.create(
					serviceContext.getLiferayPortletRequest(), PortletKeys.WIKI,
					portletPlid, PortletRequest.RENDER_PHASE);

				nodeURL.setParameter("struts_action", "/wiki/view");
				nodeURL.setParameter(
					"nodeId", String.valueOf(node.getNodeId()));

				nodeTitle = wrapLink(nodeURL.toString(), node.getName());
			}

			sb.append(serviceContext.translate(actionPattern, nodeTitle));
		}
		else {
			sb.append(serviceContext.translate(actionPattern));
		}

		sb.append("</div>");

		return sb.toString();
	}

	private static final int _ADD_COMMENT = 3;

	private static final int _ADD_PAGE = 1;

	private static final String[] _CLASS_NAMES = new String[] {
		WikiPage.class.getName()
	};

	private static final int _UPDATE_PAGE = 2;

}