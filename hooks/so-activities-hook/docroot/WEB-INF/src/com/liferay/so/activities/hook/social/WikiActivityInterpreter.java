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

package com.liferay.so.activities.hook.social;

import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityConstants;
import com.liferay.portlet.social.model.SocialActivitySet;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivitySetLocalServiceUtil;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.model.WikiPageResource;
import com.liferay.portlet.wiki.service.WikiNodeLocalServiceUtil;
import com.liferay.portlet.wiki.service.WikiPageResourceLocalServiceUtil;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Jonathan Lee
 * @author Matthew Kong
 */
public class WikiActivityInterpreter extends SOSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected long getActivitySetId(long activityId) {
		try {
			SocialActivity activity =
				SocialActivityLocalServiceUtil.getActivity(activityId);

			if (activity.getType() == _ACTIVITY_KEY_ADD_COMMENT) {
				SocialActivitySet activitySet =
					SocialActivitySetLocalServiceUtil.getClassActivitySet(
						activity.getClassNameId(), activity.getClassPK(),
						activity.getType());

				if ((activitySet != null) && !isExpired(activitySet)) {
					return activitySet.getActivitySetId();
				}
			}
			else if (activity.getType() == _ACTIVITY_KEY_UPDATE_PAGE) {
				SocialActivitySet activitySet =
					SocialActivitySetLocalServiceUtil.getClassActivitySet(
						activity.getUserId(), activity.getClassNameId(),
						activity.getClassPK(), activity.getType());

				if ((activitySet != null) && !isExpired(activitySet)) {
					return activitySet.getActivitySetId();
				}
			}
		}
		catch (Exception e) {
		}

		return 0;
	}

	@Override
	protected String getBody(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		StringBundler sb = new StringBundler(5);

		sb.append("<div class=\"activity-body\"><div class=\"title\">");

		String pageTitle = StringPool.BLANK;

		String linkURL = getLinkURL(activity, serviceContext);

		AssetRenderer assetRenderer = getAssetRenderer(activity);

		LiferayPortletRequest liferayPortletRequest =
			serviceContext.getLiferayPortletRequest();

		if (Validator.isNotNull(
				assetRenderer.getIconPath(liferayPortletRequest))) {

			pageTitle = wrapLink(
				linkURL, assetRenderer.getIconPath(liferayPortletRequest),
				HtmlUtil.escape(
					assetRenderer.getTitle(serviceContext.getLocale())));
		}
		else {
			pageTitle = wrapLink(
				linkURL,
				HtmlUtil.escape(
					assetRenderer.getTitle(serviceContext.getLocale())));
		}

		sb.append(pageTitle);

		sb.append("</div><div class=\"wiki-page-content\">");
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
	protected Object[] getTitleArguments(
			String groupName, SocialActivity activity, String link,
			String title, ServiceContext serviceContext)
		throws Exception {

		WikiPageResource pageResource =
			WikiPageResourceLocalServiceUtil.getPageResource(
				activity.getClassPK());

		WikiNode node = WikiNodeLocalServiceUtil.getNode(
			pageResource.getNodeId());

		if (node == null) {
			return null;
		}

		String nodeTitle = null;

		long plid = PortalUtil.getPlidFromPortletId(
			activity.getGroupId(), false, PortletKeys.WIKI);

		if (plid > 0) {
			PortletURL nodeURL = PortletURLFactoryUtil.create(
				serviceContext.getLiferayPortletRequest(), PortletKeys.WIKI,
				plid, PortletRequest.RENDER_PHASE);

			nodeURL.setParameter("struts_action", "/wiki/view");
			nodeURL.setParameter("nodeId", String.valueOf(node.getNodeId()));

			nodeTitle = wrapLink(
				nodeURL.toString(), HtmlUtil.escape(node.getName()));
		}
		else {
			nodeTitle = HtmlUtil.escape(node.getName());
		}

		return new Object[] {nodeTitle};
	}

	@Override
	protected String getTitlePattern(String groupName, SocialActivity activity)
		throws Exception {

		String titlePattern = null;

		if ((activity.getType() == _ACTIVITY_KEY_ADD_COMMENT) ||
			(activity.getType() == SocialActivityConstants.TYPE_ADD_COMMENT)) {

			titlePattern = "commented-on-a-wiki-page";
		}
		else if (activity.getType() == _ACTIVITY_KEY_ADD_PAGE) {
			titlePattern = "created-a-new-wiki-page";
		}
		else if (activity.getType() == _ACTIVITY_KEY_UPDATE_PAGE) {
			titlePattern = "updated-a-wiki-page";
		}
		else {
			return StringPool.BLANK;
		}

		WikiPageResource pageResource =
			WikiPageResourceLocalServiceUtil.getPageResource(
				activity.getClassPK());

		WikiNode node = WikiNodeLocalServiceUtil.getNode(
			pageResource.getNodeId());

		if (Validator.isNotNull(node)) {
			titlePattern = titlePattern.concat("-in-the-x-wiki");
		}

		return titlePattern;
	}

	/**
	 * {@link com.liferay.portlet.wiki.social.WikiActivityKeys#ADD_COMMENT}
	 */
	private static final int _ACTIVITY_KEY_ADD_COMMENT = 3;

	/**
	 * {@link com.liferay.portlet.wiki.social.WikiActivityKeys#ADD_PAGE}
	 */
	private static final int _ACTIVITY_KEY_ADD_PAGE = 1;

	/**
	 * {@link com.liferay.portlet.wiki.social.WikiActivityKeys#UPDATE_PAGE}
	 */
	private static final int _ACTIVITY_KEY_UPDATE_PAGE = 2;

	private static final String[] _CLASS_NAMES = {WikiPage.class.getName()};

}