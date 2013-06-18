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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.MathUtil;
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
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.model.SocialActivitySet;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivitySetLocalServiceUtil;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.model.WikiPageResource;
import com.liferay.portlet.wiki.service.WikiNodeLocalServiceUtil;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;
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
	public void updateActivitySet(long activityId)
		throws PortalException, SystemException {

		SocialActivity activity =
			SocialActivityLocalServiceUtil.fetchSocialActivity(activityId);

		if ((activity == null) || (activity.getActivitySetId() > 0)) {
			return;
		}

		long activitySetId = getActivitySetId(activityId);

		if (activitySetId > 0) {
			SocialActivitySetLocalServiceUtil.incrementActivityCount(
				activitySetId, activityId);

			if (activity.getType() == _ACTIVITY_KEY_UPDATE_PAGE) {
				SocialActivitySet activitySet =
					SocialActivitySetLocalServiceUtil.fetchSocialActivitySet(
						activitySetId);

				WikiPageResource pageResource =
					WikiPageResourceLocalServiceUtil.fetchWikiPageResource(
						activity.getClassPK());

				WikiPage wikiPage = WikiPageLocalServiceUtil.getPage(
					pageResource.getResourcePrimKey());

				JSONObject extraDataJSONObject =
					JSONFactoryUtil.createJSONObject(
						activitySet.getExtraData());

				extraDataJSONObject.put(
					"targetVersion",
					MathUtil.format(wikiPage.getVersion() + 0.1, 1, 1));

				activitySet.setExtraData(extraDataJSONObject.toString());

				SocialActivitySetLocalServiceUtil.updateSocialActivitySet(
					activitySet);
			}
		}
		else {
			SocialActivitySet activitySet =
				SocialActivitySetLocalServiceUtil.addActivitySet(activityId);

			if (activity.getType() == _ACTIVITY_KEY_UPDATE_PAGE) {
				WikiPageResource pageResource =
					WikiPageResourceLocalServiceUtil.fetchWikiPageResource(
						activity.getClassPK());

				WikiPage wikiPage = WikiPageLocalServiceUtil.getPage(
					pageResource.getResourcePrimKey());

				JSONObject extraDataJSONObject =
					JSONFactoryUtil.createJSONObject();

				extraDataJSONObject.put("sourceVersion", wikiPage.getVersion());
				extraDataJSONObject.put(
					"targetVersion",
					MathUtil.format(wikiPage.getVersion() + 0.1, 1, 1));

				activitySet.setExtraData(extraDataJSONObject.toString());

				SocialActivitySetLocalServiceUtil.updateSocialActivitySet(
					activitySet);
			}
		}
	}

	protected String appendNodeTitlePattern(String titlePattern, long classPK) {
		try {
			WikiPageResource pageResource =
				WikiPageResourceLocalServiceUtil.fetchWikiPageResource(classPK);

			if (pageResource == null) {
				return titlePattern;
			}

			WikiNode node = WikiNodeLocalServiceUtil.fetchWikiNode(
				pageResource.getNodeId());

			if (Validator.isNotNull(node)) {
				titlePattern = titlePattern.concat("-in-the-x-wiki");
			}
		}
		catch (Exception e) {
		}

		return titlePattern;
	}

	@Override
	protected long getActivitySetId(long activityId) {
		try {
			SocialActivity activity =
				SocialActivityLocalServiceUtil.getActivity(activityId);

			SocialActivitySet activitySet = null;

			if ((activity.getType() == _ACTIVITY_KEY_ADD_COMMENT) ||
				(activity.getType() == _ACTIVITY_KEY_ADD_PAGE) ||
				(activity.getType() ==
					SocialActivityConstants.TYPE_ADD_COMMENT)) {

				activitySet =
					SocialActivitySetLocalServiceUtil.getUserActivitySet(
						activity.getGroupId(), activity.getUserId(),
						activity.getClassNameId(), activity.getType());
			}
			else if (activity.getType() == _ACTIVITY_KEY_UPDATE_PAGE) {
				activitySet =
					SocialActivitySetLocalServiceUtil.getClassActivitySet(
						activity.getUserId(), activity.getClassNameId(),
						activity.getClassPK(), activity.getType());
			}

			if ((activitySet != null) && !isExpired(activitySet)) {
				return activitySet.getActivitySetId();
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

		return getBody(
			activity.getClassName(), activity.getClassPK(), serviceContext);
	}

	@Override
	protected String getBody(
			SocialActivitySet activitySet, ServiceContext serviceContext)
		throws Exception {

		if (activitySet.getType() == _ACTIVITY_KEY_UPDATE_PAGE) {
			return getBody(
				activitySet.getClassName(), activitySet.getClassPK(),
				serviceContext);
		}

		return super.getBody(activitySet, serviceContext);
	}

	protected String getBody(
			String className, long classPK, ServiceContext serviceContext)
		throws Exception {

		StringBundler sb = new StringBundler(5);

		sb.append("<div class=\"activity-body\"><div class=\"title\">");
		sb.append(getPageTitle(className, classPK, serviceContext));
		sb.append("</div><div class=\"wiki-page-content\">");

		AssetRenderer assetRenderer = getAssetRenderer(className, classPK);

		sb.append(
			StringUtil.shorten(
				assetRenderer.getSummary(serviceContext.getLocale()), 200));

		sb.append("</div></div>");

		return sb.toString();
	}

	protected String getDiffsURL(
			long classPK, long groupId, String sourceVersion,
			String targetVersion, ServiceContext serviceContext)
		throws Exception {

		WikiPageResource pageResource =
			WikiPageResourceLocalServiceUtil.fetchWikiPageResource(classPK);

		if (pageResource == null) {
			return null;
		}

		long plid = PortalUtil.getPlidFromPortletId(
			groupId, false, PortletKeys.WIKI);

		if (plid <= 0) {
			return null;
		}

		PortletURL diffsURL = PortletURLFactoryUtil.create(
			serviceContext.getLiferayPortletRequest(), PortletKeys.WIKI, plid,
			PortletRequest.RENDER_PHASE);

		diffsURL.setParameter("struts_action", "/wiki/compare_versions");
		diffsURL.setParameter(
			"nodeId", String.valueOf(pageResource.getNodeId()));
		diffsURL.setParameter("title", pageResource.getTitle());
		diffsURL.setParameter("sourceVersion", sourceVersion);
		diffsURL.setParameter("targetVersion", targetVersion);
		diffsURL.setParameter("type", "html");

		return diffsURL.toString();
	}

	protected String getLink(
			long groupId, long classPK, String sourceVersion,
			String targetVersion, ServiceContext serviceContext)
		throws Exception {

		String diffsURL = wrapLink(
			getDiffsURL(
				classPK, groupId, sourceVersion, targetVersion, serviceContext),
			serviceContext.translate("view-changes"));

		return "<span>" + diffsURL + "</span>";
	}

	@Override
	protected String getLink(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		if (activity.getType() != _ACTIVITY_KEY_UPDATE_PAGE) {
			return null;
		}

		SocialActivity socialActivity =
			SocialActivityLocalServiceUtil.fetchSocialActivity(
				activity.getActivityId());

		SocialActivitySet activitySet =
			SocialActivitySetLocalServiceUtil.fetchSocialActivitySet(
				socialActivity.getActivitySetId());

		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject(
			activitySet.getExtraData());

		String sourceVersion = extraDataJSONObject.getString("sourceVersion");
		String targetVersion = extraDataJSONObject.getString("targetVersion");

		return getLink(
			activity.getGroupId(), activity.getClassPK(), sourceVersion,
			targetVersion, serviceContext);
	}

	@Override
	protected String getLink(
			SocialActivitySet activitySet, ServiceContext serviceContext)
		throws Exception {

		if (activitySet.getType() != _ACTIVITY_KEY_UPDATE_PAGE) {
			return null;
		}

		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject(
			activitySet.getExtraData());

		String sourceVersion = extraDataJSONObject.getString("sourceVersion");
		String targetVersion = extraDataJSONObject.getString("targetVersion");

		return getLink(
			activitySet.getGroupId(), activitySet.getClassPK(), sourceVersion,
			targetVersion, serviceContext);
	}

	protected String getNodeTitle(
			long classPK, long groupId, ServiceContext serviceContext)
		throws Exception {

		WikiPageResource pageResource =
			WikiPageResourceLocalServiceUtil.fetchWikiPageResource(classPK);

		if (pageResource == null) {
			return null;
		}

		WikiNode node = WikiNodeLocalServiceUtil.fetchWikiNode(
			pageResource.getNodeId());

		if (node == null) {
			return null;
		}

		long plid = PortalUtil.getPlidFromPortletId(
			groupId, false, PortletKeys.WIKI);

		if (plid <= 0) {
			return HtmlUtil.escape(node.getName());
		}

		PortletURL nodeURL = PortletURLFactoryUtil.create(
			serviceContext.getLiferayPortletRequest(), PortletKeys.WIKI, plid,
			PortletRequest.RENDER_PHASE);

		nodeURL.setParameter("struts_action", "/wiki/view");
		nodeURL.setParameter("nodeId", String.valueOf(node.getNodeId()));

		return wrapLink(nodeURL.toString(), HtmlUtil.escape(node.getName()));
	}

	protected String getPageTitle(
			String className, long classPK, ServiceContext serviceContext)
		throws Exception {

		String linkURL = getLinkURL(className, classPK, serviceContext);

		AssetRenderer assetRenderer = getAssetRenderer(className, classPK);

		LiferayPortletRequest liferayPortletRequest =
			serviceContext.getLiferayPortletRequest();

		if (Validator.isNotNull(
				assetRenderer.getIconPath(liferayPortletRequest))) {

			return wrapLink(
				linkURL, assetRenderer.getIconPath(liferayPortletRequest),
				HtmlUtil.escape(
					assetRenderer.getTitle(serviceContext.getLocale())));
		}

		return wrapLink(
			linkURL,
			HtmlUtil.escape(
				assetRenderer.getTitle(serviceContext.getLocale())));
	}

	@Override
	protected SocialActivityFeedEntry getSubfeedEntry(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		String title = getPageTitle(
			activity.getClassName(), activity.getClassPK(), serviceContext);

		AssetRenderer assetRenderer = getAssetRenderer(
			activity.getClassName(), activity.getClassPK());

		String body = StringUtil.shorten(
			assetRenderer.getSummary(serviceContext.getLocale()), 200);

		return new SocialActivityFeedEntry(title, body);
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivity activity, String link,
			String title, ServiceContext serviceContext)
		throws Exception {

		String nodeTitle = getNodeTitle(
			activity.getClassPK(), activity.getGroupId(), serviceContext);

		return new Object[] {nodeTitle};
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivitySet activitySet, String link,
			String title, ServiceContext serviceContext)
		throws Exception {

		int activityCount = activitySet.getActivityCount();
		String nodeTitle = getNodeTitle(
			activitySet.getClassPK(), activitySet.getGroupId(), serviceContext);

		return new Object[] {activityCount, nodeTitle};
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

		return appendNodeTitlePattern(titlePattern, activity.getClassPK());
	}

	@Override
	protected String getTitlePattern(
			String groupName, SocialActivitySet activitySet)
		throws Exception {

		String titlePattern = null;

		if ((activitySet.getType() == _ACTIVITY_KEY_ADD_COMMENT) ||
			(activitySet.getType() ==
				SocialActivityConstants.TYPE_ADD_COMMENT)) {

			titlePattern = "commented-on-x-wiki-pages";
		}
		else if (activitySet.getType() == _ACTIVITY_KEY_ADD_PAGE) {
			titlePattern = "created-x-new-wiki-pages";
		}
		else if (activitySet.getType() == _ACTIVITY_KEY_UPDATE_PAGE) {
			titlePattern = "made-x-updates-to-a-wiki-page";
		}
		else {
			return StringPool.BLANK;
		}

		return appendNodeTitlePattern(titlePattern, activitySet.getClassPK());
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