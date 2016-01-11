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

import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.util.DLUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivitySet;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivitySetLocalServiceUtil;
import com.liferay.so.activities.util.SocialActivityKeyConstants;

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
			SocialActivitySet activitySet = null;

			SocialActivity activity =
				SocialActivityLocalServiceUtil.getActivity(activityId);

			if (activity.getType() ==
					SocialActivityKeyConstants.DL_ADD_FILE_ENTRY) {

				activitySet =
					SocialActivitySetLocalServiceUtil.getUserActivitySet(
						activity.getGroupId(), activity.getUserId(),
						activity.getClassNameId(), activity.getType());
			}
			else if (activity.getType() ==
						SocialActivityKeyConstants.DL_UPDATE_FILE_ENTRY) {

				activitySet =
					SocialActivitySetLocalServiceUtil.getClassActivitySet(
						activity.getUserId(), activity.getClassNameId(),
						activity.getClassPK(), activity.getType());
			}

			if ((activitySet != null) && !isExpired(activitySet, false)) {
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

		if (activitySet.getType() ==
				SocialActivityKeyConstants.DL_UPDATE_FILE_ENTRY) {

			return getBody(
				activitySet.getClassName(), activitySet.getClassPK(),
				serviceContext);
		}

		return super.getBody(activitySet, serviceContext);
	}

	protected String getBody(
			String className, long classPK, ServiceContext serviceContext)
		throws Exception {

		StringBundler sb = new StringBundler(11);

		sb.append("<div class=\"activity-body document\">");
		sb.append("<span class=\"document-thumbnail\"><img src=\"");

		FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(classPK);

		FileVersion fileVersion = fileEntry.getFileVersion();

		String thumbnailSrc = DLUtil.getThumbnailSrc(
			fileEntry, fileVersion, null, serviceContext.getThemeDisplay());

		sb.append(thumbnailSrc);
		sb.append("\"></span>");
		sb.append("<div class=\"document-container\"><div class=\"title\">");
		sb.append(getPageTitle(className, classPK, serviceContext));
		sb.append("</div><div class=\"version\">");
		sb.append(
			serviceContext.translate("version-x", fileVersion.getVersion()));
		sb.append("</div><div class=\"document-content\">");

		AssetRenderer assetRenderer = getAssetRenderer(className, classPK);

		sb.append(
			StringUtil.shorten(
				HtmlUtil.escape(assetRenderer.getSummary(), 200)));

		sb.append("</div></div></div>");

		return sb.toString();
	}

	protected String getFolderLink(long classPK, ServiceContext serviceContext)
		throws Exception {

		FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(classPK);

		if (fileEntry.getFolderId() <= 0) {
			return null;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(serviceContext.getPortalURL());
		sb.append(serviceContext.getPathMain());
		sb.append("/document_library/find_folder?groupId=");
		sb.append(fileEntry.getRepositoryId());
		sb.append("&folderId=");
		sb.append(fileEntry.getFolderId());

		Folder folder = fileEntry.getFolder();

		return wrapLink(sb.toString(), folder.getName());
	}

	@Override
	protected String getLink(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		StringBundler sb = new StringBundler(8);

		sb.append(serviceContext.getPortalURL());
		sb.append(serviceContext.getPathMain());
		sb.append("/document_library/get_file?groupId=");

		FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
			activity.getClassPK());

		sb.append(fileEntry.getRepositoryId());

		sb.append("&folderId=");
		sb.append(fileEntry.getFolderId());
		sb.append("&title=");
		sb.append(HttpUtil.encodeURL(fileEntry.getTitle()));

		String downloadLink = wrapLinkWithIcon(
			sb.toString(), serviceContext.translate("download"));

		return "<span class=\"download-link\">" + downloadLink + "</span>";
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivity activity, String link,
			String title, ServiceContext serviceContext)
		throws Exception {

		String folderLink = getFolderLink(
			activity.getClassPK(), serviceContext);

		if (Validator.isNull(folderLink)) {
			return null;
		}

		return new Object[] {folderLink};
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivitySet activitySet, String link,
			String title, ServiceContext serviceContext)
		throws Exception {

		if (activitySet.getType() ==
				SocialActivityKeyConstants.DL_UPDATE_FILE_ENTRY) {

			String folderLink = getFolderLink(
				activitySet.getClassPK(), serviceContext);

			if (Validator.isNotNull(folderLink)) {
				return new Object[] {
					activitySet.getActivityCount(), folderLink
				};
			}
		}

		return super.getTitleArguments(
			groupName, activitySet, link, title, serviceContext);
	}

	@Override
	protected String getTitlePattern(String groupName, SocialActivity activity)
		throws Exception {

		String titlePattern = StringPool.BLANK;

		if (activity.getType() ==
				SocialActivityKeyConstants.DL_ADD_FILE_ENTRY) {

			titlePattern = "uploaded-a-new-document";
		}
		else if (activity.getType() ==
					SocialActivityKeyConstants.DL_UPDATE_FILE_ENTRY) {

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

	@Override
	protected String getTitlePattern(
			String groupName, SocialActivitySet activitySet)
		throws Exception {

		String titlePattern = StringPool.BLANK;

		if (activitySet.getType() ==
				SocialActivityKeyConstants.DL_ADD_FILE_ENTRY) {

			titlePattern = "uploaded-x-new-documents";
		}
		else if (activitySet.getType() ==
					SocialActivityKeyConstants.DL_UPDATE_FILE_ENTRY) {

			titlePattern = "made-x-updates-to-a-document";

			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
				activitySet.getClassPK());

			if (fileEntry.getFolderId() > 0) {
				titlePattern = titlePattern.concat("-in-the-x-folder");
			}
		}
		else {
			return StringPool.BLANK;
		}

		return titlePattern;
	}

	protected String wrapLinkWithIcon(String link, String text) {
		StringBundler sb = new StringBundler(6);

		sb.append("<a href=\"");
		sb.append(link);
		sb.append("\">");
		sb.append("<i class=\"icon-download\"></i>");
		sb.append(text);
		sb.append("</a>");

		return sb.toString();
	}

	private static final String[] _CLASS_NAMES = {DLFileEntry.class.getName()};

}