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

package com.liferay.so.portlet.documentlibrary.social;

import com.liferay.compat.portal.service.ServiceContext;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.util.DLUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.so.activities.model.BaseSocialActivityInterpreter;
import com.liferay.so.util.Time;

import java.lang.String;

import java.text.Format;

import java.util.Date;

/**
 * @author Evan Thibodeau
 */
public class DLActivityInterpreter extends BaseSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected String getBody(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		AssetRenderer assetRenderer = getAssetRenderer(activity);

		FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
			activity.getClassPK());

		String pageTitle = wrapLink(
			getLinkURL(activity, serviceContext),
			HtmlUtil.escape(
				assetRenderer.getTitle(serviceContext.getLocale())));

		FileVersion fileVersion = fileEntry.getFileVersion();

		String thumbnailSrc = DLUtil.getThumbnailSrc(
			fileEntry, fileVersion, null, serviceContext.getThemeDisplay());

		StringBundler sb = new StringBundler(11);

		sb.append("<div class=\"activity-body document\">");
		sb.append("<span class=\"document-thumbnail\"><img src=\"");
		sb.append(thumbnailSrc);
		sb.append("\"></span>");
		sb.append("<div class=\"document-container\"><div class=\"title\">");
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

		FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
			activity.getClassPK());

		String documentLink = wrapLink(
			getLinkURL(activity, serviceContext),
			serviceContext.translate("view-document"));

		StringBundler sb = new StringBundler(8);

		sb.append(serviceContext.getPortalURL());
		sb.append(serviceContext.getPathMain());
		sb.append("/document_library/get_file?groupId=");
		sb.append(fileEntry.getRepositoryId());
		sb.append("&folderId=");
		sb.append(fileEntry.getFolderId());
		sb.append("&title=");
		sb.append(HttpUtil.encodeURL(fileEntry.getTitle()));

		String downloadLink = wrapLink(
			sb.toString(), serviceContext.translate("download"));

		sb = new StringBundler(5);

		sb.append("<span>");
		sb.append(documentLink);
		sb.append("</span><span>");
		sb.append(downloadLink);
		sb.append("</span>");

		return sb.toString();
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

		if (activityType == _ADD_FILE_ENTRY) {
			actionPattern = "uploaded-a-new-document";
		}
		else if (activityType == _UPDATE_FILE_ENTRY) {
			actionPattern = "updated-a-document";
		}

		FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
			activity.getClassPK());

		if (Validator.isNotNull(fileEntry.getFolderId())) {
			actionPattern += "-in-the-x-folder";

			String folderTitle = HtmlUtil.escape(
				fileEntry.getFolder().getName());

			StringBundler folderURL = new StringBundler(6);

			folderURL.append(serviceContext.getPortalURL());
			folderURL.append(serviceContext.getPathMain());
			folderURL.append("/document_library/find_folder?groupId=");
			folderURL.append(fileEntry.getRepositoryId());
			folderURL.append("&folderId=");
			folderURL.append(fileEntry.getFolderId());

			String folderLink = wrapLink(folderURL.toString(), folderTitle);

			sb.append(serviceContext.translate(actionPattern, folderLink));
		}
		else {
			sb.append(serviceContext.translate(actionPattern));
		}

		sb.append("</div>");

		return sb.toString();
	}

	private static final int _ADD_FILE_ENTRY = 1;

	private static final String[] _CLASS_NAMES = new String[] {
		DLFileEntry.class.getName()
	};

	private static final int _UPDATE_FILE_ENTRY = 2;

}