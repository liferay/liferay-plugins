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

import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.util.DLUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivitySet;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivitySetLocalServiceUtil;

/**
 * @author Evan Thibodeau
 * @author Matthew Kong
 */
public class DLActivityInterpreter extends SOSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected long getActivitySetId(long activityId) {
		try {
			SocialActivity activity =
				SocialActivityLocalServiceUtil.getActivity(activityId);

			if (activity.getType() == _ACTIVITY_KEY_ADD_FILE_ENTRY) {
				SocialActivitySet activitySet =
					SocialActivitySetLocalServiceUtil.getUserActivitySet(
						activity.getGroupId(), activity.getUserId(),
						activity.getType());

				if ((activitySet != null) && !isExpired(activitySet)) {
					return activitySet.getActivitySetId();
				}
			}
			else if (activity.getType() == _ACTIVITY_KEY_UPDATE_FILE_ENTRY) {
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

		StringBundler sb = new StringBundler(11);

		sb.append("<div class=\"activity-body document\">");
		sb.append("<span class=\"document-thumbnail\"><img src=\"");

		FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
			activity.getClassPK());

		FileVersion fileVersion = fileEntry.getFileVersion();

		String thumbnailSrc = DLUtil.getThumbnailSrc(
			fileEntry, fileVersion, null, serviceContext.getThemeDisplay());

		sb.append(thumbnailSrc);
		sb.append("\"></span>");
		sb.append("<div class=\"document-container\"><div class=\"title\">");

		AssetRenderer assetRenderer = getAssetRenderer(activity);

		String pageTitle = wrapLink(
			getLinkURL(activity, serviceContext),
			HtmlUtil.escape(
				assetRenderer.getTitle(serviceContext.getLocale())));

		sb.append(pageTitle);
		sb.append("</div><div class=\"version\">");
		sb.append(
			serviceContext.translate("version-x", fileVersion.getVersion()));
		sb.append("</div><div class=\"document-content\">");
		sb.append(
			StringUtil.shorten(
				assetRenderer.getSummary(serviceContext.getLocale()), 200));
		sb.append("</div></div></div>");

		return sb.toString();
	}

	@Override
	protected String getLink(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		StringBundler sb = new StringBundler(5);

		sb.append("<span>");

		String documentLink = wrapLink(
			getLinkURL(activity, serviceContext),
			serviceContext.translate("view-document"));

		sb.append(documentLink);

		sb.append("</span><span>");

		StringBundler downloadLink = new StringBundler(8);

		downloadLink.append(serviceContext.getPortalURL());
		downloadLink.append(serviceContext.getPathMain());
		downloadLink.append("/document_library/get_file?groupId=");

		FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
			activity.getClassPK());

		downloadLink.append(fileEntry.getRepositoryId());

		downloadLink.append("&folderId=");
		downloadLink.append(fileEntry.getFolderId());
		downloadLink.append("&title=");
		downloadLink.append(HttpUtil.encodeURL(fileEntry.getTitle()));

		sb.append(
			wrapLink(
				downloadLink.toString(), serviceContext.translate("download")));

		sb.append("</span>");

		return sb.toString();
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivity activity, String link,
			String title, ServiceContext serviceContext)
		throws Exception {

		FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
			activity.getClassPK());

		if (fileEntry.getFolderId() > 0) {
			StringBundler sb = new StringBundler(6);

			sb.append(serviceContext.getPortalURL());
			sb.append(serviceContext.getPathMain());
			sb.append("/document_library/find_folder?groupId=");
			sb.append(fileEntry.getRepositoryId());
			sb.append("&folderId=");
			sb.append(fileEntry.getFolderId());

			Folder folder = fileEntry.getFolder();

			String folderName = HtmlUtil.escape(folder.getName());

			String folderURL = wrapLink(sb.toString(), folderName);

			return new Object[] {folderURL};
		}

		return null;
	}

	@Override
	protected String getTitlePattern(String groupName, SocialActivity activity)
		throws Exception {

		String titlePattern = StringPool.BLANK;

		if (activity.getType() == _ACTIVITY_KEY_ADD_FILE_ENTRY) {
			titlePattern = "uploaded-a-new-document";
		}
		else if (activity.getType() == _ACTIVITY_KEY_UPDATE_FILE_ENTRY) {
			titlePattern = "updated-a-document";
		}
		else {
			return StringPool.BLANK;
		}

		FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
			activity.getClassPK());

		if (fileEntry.getFolderId() > 0) {
			titlePattern = titlePattern.concat("-in-the-x-folder");
		}

		return titlePattern;
	}

	/**
	 * {@link
	 * com.liferay.portlet.documentlibrary.social.DLActivityKeys#ADD_FILE_ENTRY}
	 */
	private static final int _ACTIVITY_KEY_ADD_FILE_ENTRY = 1;

	/**
	 * {@link
	 * com.liferay.portlet.documentlibrary.social.DLActivityKeys#UPDATE_FILE_ENTRY}
	 */
	private static final int _ACTIVITY_KEY_UPDATE_FILE_ENTRY = 2;

	private static final String[] _CLASS_NAMES = {DLFileEntry.class.getName()};

}