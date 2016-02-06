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

package com.liferay.so.activities.hook.social;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.MathUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.so.activities.util.SocialActivityKeyConstants;
import com.liferay.social.kernel.model.SocialActivity;
import com.liferay.social.kernel.model.SocialActivityConstants;
import com.liferay.social.kernel.model.SocialActivitySet;
import com.liferay.social.kernel.service.SocialActivityLocalServiceUtil;
import com.liferay.social.kernel.service.SocialActivitySetLocalServiceUtil;
import com.liferay.wiki.constants.WikiPortletKeys;
import com.liferay.wiki.model.WikiNode;
import com.liferay.wiki.model.WikiPage;
import com.liferay.wiki.model.WikiPageResource;
import com.liferay.wiki.service.WikiNodeLocalServiceUtil;
import com.liferay.wiki.service.WikiPageLocalServiceUtil;
import com.liferay.wiki.service.WikiPageResourceLocalServiceUtil;

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
	public void updateActivitySet(long activityId) throws PortalException {
		SocialActivity activity =
			SocialActivityLocalServiceUtil.fetchSocialActivity(activityId);

		if ((activity == null) || (activity.getActivitySetId() > 0)) {
			return;
		}

		long activitySetId = getActivitySetId(activityId);

		if (activitySetId > 0) {
			SocialActivitySetLocalServiceUtil.incrementActivityCount(
				activitySetId, activityId);

			if (activity.getType() ==
					SocialActivityKeyConstants.WIKI_UPDATE_PAGE) {

				SocialActivitySet activitySet =
					SocialActivitySetLocalServiceUtil.fetchSocialActivitySet(
						activitySetId);

				WikiPageResource pageResource =
					WikiPageResourceLocalServiceUtil.fetchWikiPageResource(
						activity.getClassPK());

				WikiPage wikiPage = WikiPageLocalServiceUtil.getPage(
					pageResource.getResourcePrimKey());

				JSONObject extraDataJSONObject = null;

				if (Validator.isNull(activitySet.getExtraData())) {
					extraDataJSONObject = JSONFactoryUtil.createJSONObject();

					extraDataJSONObject.put(
						"sourceVersion",
						MathUtil.format(wikiPage.getVersion() - 0.1, 1, 1));
				}
				else {
					extraDataJSONObject = JSONFactoryUtil.createJSONObject(
						activitySet.getExtraData());
				}

				extraDataJSONObject.put("targetVersion", wikiPage.getVersion());

				activitySet.setExtraData(extraDataJSONObject.toString());

				SocialActivitySetLocalServiceUtil.updateSocialActivitySet(
					activitySet);
			}
		}
		else {
			SocialActivitySet activitySet =
				SocialActivitySetLocalServiceUtil.addActivitySet(activityId);

			if (activity.getType() ==
					SocialActivityKeyConstants.WIKI_UPDATE_PAGE) {

				WikiPageResource pageResource =
					WikiPageResourceLocalServiceUtil.fetchWikiPageResource(
						activity.getClassPK());

				WikiPage wikiPage = WikiPageLocalServiceUtil.getPage(
					pageResource.getResourcePrimKey());

				JSONObject extraDataJSONObject =
					JSONFactoryUtil.createJSONObject();

				extraDataJSONObject.put(
					"sourceVersion",
					MathUtil.format(wikiPage.getVersion() - 0.1, 1, 1));
				extraDataJSONObject.put("targetVersion", wikiPage.getVersion());

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

			boolean comment = false;

			if ((activity.getType() ==
					SocialActivityKeyConstants.WIKI_ADD_COMMENT) ||
				(activity.getType() ==
					SocialActivityConstants.TYPE_ADD_COMMENT)) {

				activitySet =
					SocialActivitySetLocalServiceUtil.getClassActivitySet(
						activity.getClassNameId(), activity.getClassPK(),
						activity.getType());

				comment = true;
			}
			else if (activity.getType() ==
						SocialActivityKeyConstants.WIKI_UPDATE_PAGE) {

				activitySet =
					SocialActivitySetLocalServiceUtil.getClassActivitySet(
						activity.getUserId(), activity.getClassNameId(),
						activity.getClassPK(), activity.getType());
			}

			if ((activitySet != null) && !isExpired(activitySet, comment)) {
				return activitySet.getActivitySetId();
			}
		}
		catch (Exception e) {
		}

		return 0;
	}

	@Override
	protected String getAttachmentTitle(
			SocialActivity activity, WikiPageResource pageResource,
			ServiceContext serviceContext)
		throws Exception {

		int activityType = activity.getType();

		if ((activityType == SocialActivityConstants.TYPE_ADD_ATTACHMENT) ||
			(activityType ==
				SocialActivityConstants.TYPE_MOVE_ATTACHMENT_TO_TRASH) ||
			(activityType ==
				SocialActivityConstants.TYPE_RESTORE_ATTACHMENT_FROM_TRASH)) {

			String link = null;

			FileEntry fileEntry = null;

			try {
				long fileEntryId = GetterUtil.getLong(
					activity.getExtraDataValue("fileEntryId"));

				fileEntry = PortletFileRepositoryUtil.getPortletFileEntry(
					fileEntryId);
			}
			catch (NoSuchModelException nsme) {
			}

			String fileEntryTitle = activity.getExtraDataValue(
				"fileEntryTitle");

			if ((fileEntry != null) && !fileEntry.isInTrash()) {
				StringBundler sb = new StringBundler(9);

				sb.append(serviceContext.getPathMain());
				sb.append("/wiki/get_page_attachment?p_l_id=");
				sb.append(serviceContext.getPlid());
				sb.append("&nodeId=");
				sb.append(pageResource.getNodeId());
				sb.append("&title=");
				sb.append(HttpUtil.encodeURL(pageResource.getTitle()));
				sb.append("&fileName=");
				sb.append(fileEntryTitle);

				link = sb.toString();
			}

			return wrapLink(link, fileEntryTitle);
		}

		return StringPool.BLANK;
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

		if ((activitySet.getType() ==
				SocialActivityKeyConstants.WIKI_ADD_COMMENT) ||
			(activitySet.getType() ==
				SocialActivityKeyConstants.WIKI_UPDATE_PAGE) ||
			(activitySet.getType() ==
				SocialActivityConstants.TYPE_ADD_COMMENT)) {

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
				HtmlUtil.escape(assetRenderer.getSummary(), 200)));

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
			groupId, WikiPortletKeys.WIKI);

		if (plid == LayoutConstants.DEFAULT_PLID) {
			return null;
		}

		PortletURL diffsURL = PortletURLFactoryUtil.create(
			serviceContext.getLiferayPortletRequest(), WikiPortletKeys.WIKI,
			plid, PortletRequest.RENDER_PHASE);

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

		if (activity.getType() != SocialActivityKeyConstants.WIKI_UPDATE_PAGE) {
			return null;
		}

		SocialActivity socialActivity =
			SocialActivityLocalServiceUtil.fetchSocialActivity(
				activity.getActivityId());

		SocialActivitySet activitySet =
			SocialActivitySetLocalServiceUtil.fetchSocialActivitySet(
				socialActivity.getActivitySetId());

		if (Validator.isNull(activitySet.getExtraData())) {
			return null;
		}

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

		if (activitySet.getType() !=
				SocialActivityKeyConstants.WIKI_UPDATE_PAGE) {

			return null;
		}

		if (Validator.isNull(activitySet.getExtraData())) {
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
			groupId, WikiPortletKeys.WIKI);

		if (plid == LayoutConstants.DEFAULT_PLID) {
			return HtmlUtil.escape(node.getName());
		}

		PortletURL nodeURL = PortletURLFactoryUtil.create(
			serviceContext.getLiferayPortletRequest(), WikiPortletKeys.WIKI,
			plid, PortletRequest.RENDER_PHASE);

		nodeURL.setParameter("struts_action", "/wiki/view");
		nodeURL.setParameter("nodeId", String.valueOf(node.getNodeId()));

		return wrapLink(nodeURL.toString(), node.getName());
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivity activity, String link,
			String title, ServiceContext serviceContext)
		throws Exception {

		WikiPageResource pageResource =
			WikiPageResourceLocalServiceUtil.fetchWikiPageResource(
				activity.getClassPK());

		String nodeTitle = getNodeTitle(
			activity.getClassPK(), activity.getGroupId(), serviceContext);

		return new Object[] {
			nodeTitle,
			getAttachmentTitle(activity, pageResource, serviceContext)
		};
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivitySet activitySet, String link,
			String title, ServiceContext serviceContext)
		throws Exception {

		String nodeTitle = getNodeTitle(
			activitySet.getClassPK(), activitySet.getGroupId(), serviceContext);

		if ((activitySet.getType() ==
				SocialActivityKeyConstants.WIKI_ADD_COMMENT) ||
			(activitySet.getType() ==
				SocialActivityConstants.TYPE_ADD_COMMENT)) {

			return new Object[] {nodeTitle};
		}

		int activityCount = activitySet.getActivityCount();

		return new Object[] {activityCount, nodeTitle};
	}

	@Override
	protected String getTitlePattern(String groupName, SocialActivity activity)
		throws Exception {

		String titlePattern = null;

		if ((activity.getType() == SocialActivityConstants.TYPE_ADD_COMMENT) ||
			(activity.getType() ==
				SocialActivityKeyConstants.WIKI_ADD_COMMENT)) {

			titlePattern = "commented-on-a-wiki-page";
		}
		else if (activity.getType() ==
					SocialActivityConstants.TYPE_ADD_ATTACHMENT) {

			titlePattern = "added-an-attachment-x-to-a-wiki-page";
		}
		else if (activity.getType() ==
					SocialActivityConstants.TYPE_MOVE_ATTACHMENT_TO_TRASH) {

			titlePattern = "removed-an-attachment-x-from-a-wiki-page";
		}
		else if (activity.getType() ==
					SocialActivityConstants.
						TYPE_RESTORE_ATTACHMENT_FROM_TRASH) {

			titlePattern = "restored-an-attachment-x-to-a-wiki-page";
		}
		else if (activity.getType() ==
					SocialActivityKeyConstants.WIKI_ADD_PAGE) {

			titlePattern = "created-a-new-wiki-page";
		}
		else if (activity.getType() ==
					SocialActivityKeyConstants.WIKI_UPDATE_PAGE) {

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

		if ((activitySet.getType() ==
				SocialActivityConstants.TYPE_ADD_COMMENT) ||
			(activitySet.getType() ==
				SocialActivityKeyConstants.WIKI_ADD_COMMENT)) {

			titlePattern = "commented-on-a-wiki-page";
		}
		else if (activitySet.getType() ==
					SocialActivityConstants.TYPE_ADD_ATTACHMENT) {

			titlePattern = "added-x-attachments-to-a-wiki-page";
		}
		else if (activitySet.getType() ==
					SocialActivityConstants.TYPE_MOVE_ATTACHMENT_TO_TRASH) {

			titlePattern = "removed-x-attachments-from-a-wiki-page";
		}
		else if (activitySet.getType() ==
					SocialActivityConstants.
						TYPE_RESTORE_ATTACHMENT_FROM_TRASH) {

			titlePattern = "restored-x-attachments-to-a-wiki-page";
		}
		else if (activitySet.getType() ==
					SocialActivityKeyConstants.WIKI_ADD_PAGE) {

			titlePattern = "created-x-new-wiki-pages";
		}
		else if (activitySet.getType() ==
					SocialActivityKeyConstants.WIKI_UPDATE_PAGE) {

			titlePattern = "made-x-updates-to-a-wiki-page";
		}
		else {
			return StringPool.BLANK;
		}

		return appendNodeTitlePattern(titlePattern, activitySet.getClassPK());
	}

	private static final String[] _CLASS_NAMES = {WikiPage.class.getName()};

}