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

package com.liferay.screens.service.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.ClassResolverUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.ClassName;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.PortletItem;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.service.persistence.LayoutUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.persistence.AssetEntryQuery;
import com.liferay.portlet.assetpublisher.util.AssetPublisherUtil;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatamapping.NoSuchStructureException;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.journal.NoSuchArticleException;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalArticleResource;
import com.liferay.portlet.journal.service.JournalArticleResourceLocalServiceUtil;
import com.liferay.screens.service.base.ScreensAssetEntryServiceBaseImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletPreferences;

/**
 * @author José Manuel Navarro
 */
public class ScreensAssetEntryServiceImpl
	extends ScreensAssetEntryServiceBaseImpl {

	@Override
	public JSONArray getAssetEntries(
			AssetEntryQuery assetEntryQuery, Locale locale)
		throws PortalException, SystemException {

		List<AssetEntry> assetEntries = assetEntryService.getEntries(
			assetEntryQuery);

		assetEntries = filterAssetEntries(assetEntries);

		return toJSONArray(assetEntries, locale);
	}

	@Override
	public JSONArray getAssetEntries(
			long companyId, long groupId, String portletItemName, Locale locale,
			int max)
		throws PortalException, SystemException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			PortletItem.class);

		Property property = PropertyFactoryUtil.forName("name");

		dynamicQuery.add(property.eq(portletItemName));

		dynamicQuery.setLimit(0, 1);

		List<PortletItem> portletItems = portletItemLocalService.dynamicQuery(
			dynamicQuery);

		if (portletItems.isEmpty()) {
			throw new PortalException(
				"No portlet items associated with portlet item name " +
					portletItemName);
		}

		PortletItem portletItem = portletItems.get(0);

		PortletPreferences portletPreferences =
			portletPreferencesLocalService.getPreferences(
				portletItem.getCompanyId(), portletItem.getPortletItemId(),
				PortletKeys.PREFS_OWNER_TYPE_ARCHIVED, 0,
				portletItem.getPortletId());

		String selectionStyle = GetterUtil.getString(
			portletPreferences.getValue("selectionStyle", null), "dynamic");

		if (selectionStyle.equals("dynamic")) {
			if (max > 500) {
				max = 500;
			}

			Layout layout = LayoutUtil.fetchByCompanyId_First(companyId, null);

			if (layout != null) {
				List<AssetEntry> assetEntries =
					AssetPublisherUtil.getAssetEntries(
						portletPreferences, layout, groupId, max, false);

				assetEntries = filterAssetEntries(assetEntries);

				return toJSONArray(assetEntries, locale);
			}
			else {
				return JSONFactoryUtil.createJSONArray();
			}
		}
		else {
			try {
				PermissionChecker permissionChecker =
					PermissionCheckerFactoryUtil.create(getUser());

				List<AssetEntry> assetEntries =
					AssetPublisherUtil.getAssetEntries(
						null, portletPreferences, permissionChecker,
						new long[] {groupId},
						portletPreferences.getValues(
							"assetEntryXml", new String[0]),
						false, false);

				assetEntries = filterAssetEntries(assetEntries);

				return toJSONArray(assetEntries, locale);
			}
			catch (PortalException pe) {
				throw pe;
			}
			catch (SystemException se) {
				throw se;
			}
			catch (Exception e) {
				throw new PortalException(e);
			}
		}
	}

	protected boolean containsPermission(
			PermissionChecker permissionChecker, AssetEntry assetEntry,
			String actionId)
		throws PortalException {

		try {
			return (Boolean)PortalClassInvoker.invoke(
				false, _containsPermissionMethodKey, permissionChecker,
				assetEntry, actionId);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return false;
	}

	protected List<AssetEntry> filterAssetEntries(List<AssetEntry> assetEntries)
		throws PortalException {

		List<AssetEntry> filteredAssetEntries = new ArrayList<AssetEntry>(
			assetEntries.size());

		for (AssetEntry assetEntry : assetEntries) {
			if (containsPermission(
					getPermissionChecker(), assetEntry, ActionKeys.VIEW)) {

				filteredAssetEntries.add(assetEntry);
			}
		}

		return filteredAssetEntries;
	}

	protected JSONObject getAssetObjectJSONObject(
			AssetEntry assetEntry, Locale locale)
		throws PortalException, SystemException {

		String className = assetEntry.getClassName();

		if (className.equals(BlogsEntry.class.getName())) {
			return getBlogsEntryJSONObject(assetEntry);
		}
		else if (className.equals(DDLRecord.class.getName())) {
			return screensDDLRecordService.getDDLRecord(
				assetEntry.getClassPK(), locale);
		}
		else if (className.equals(DLFileEntry.class.getName())) {
			return getFileEntryJSONObject(assetEntry);
		}
		else if (className.equals(JournalArticle.class.getName())) {
			return getJournalArticleJSONObject(assetEntry);
		}
		else if (className.equals(User.class.getName())) {
			return getUserJSONObject(assetEntry);
		}

		return JSONFactoryUtil.createJSONObject();
	}

	protected JSONObject getBlogsEntryJSONObject(AssetEntry assetEntry)
		throws PortalException, SystemException {

		BlogsEntry blogsEntry = blogsEntryService.getEntry(
			assetEntry.getClassPK());

		JSONObject blogsEntryJSONObject = JSONFactoryUtil.createJSONObject();

		blogsEntryJSONObject.put(
			"blogsEntry",
			JSONFactoryUtil.createJSONObject(
				JSONFactoryUtil.looseSerialize(blogsEntry)));

		return blogsEntryJSONObject;
	}

	protected JSONObject getFileEntryJSONObject(AssetEntry assetEntry)
		throws PortalException, SystemException {

		FileEntry fileEntry = DLAppServiceUtil.getFileEntry(
			assetEntry.getClassPK());

		JSONObject fileEntryJSONObject = JSONFactoryUtil.createJSONObject();

		fileEntryJSONObject.put(
			"fileEntry",
			JSONFactoryUtil.createJSONObject(
				JSONFactoryUtil.looseSerialize(fileEntry)));
		fileEntryJSONObject.put("url", getFileEntryPreviewURL(fileEntry));

		return fileEntryJSONObject;
	}

	protected String getFileEntryPreviewURL(FileEntry fileEntry) {
		StringBundler sb = new StringBundler(9);

		sb.append(PortalUtil.getPathContext());
		sb.append("/documents/");
		sb.append(fileEntry.getRepositoryId());
		sb.append(StringPool.SLASH);
		sb.append(fileEntry.getFolderId());
		sb.append(StringPool.SLASH);
		sb.append(HttpUtil.encodeURL(HtmlUtil.unescape(fileEntry.getTitle())));
		sb.append(StringPool.SLASH);
		sb.append(fileEntry.getUuid());

		return sb.toString();
	}

	protected JSONObject getJournalArticleJSONObject(AssetEntry assetEntry)
		throws PortalException, SystemException {

		JournalArticle journalArticle = null;

		try {
			journalArticle = journalArticleService.getArticle(
				assetEntry.getClassPK());
		}
		catch (NoSuchArticleException nsae) {
			JournalArticleResource journalArticleResource =
				JournalArticleResourceLocalServiceUtil.getArticleResource(
					assetEntry.getClassPK());

			journalArticle = journalArticleService.getLatestArticle(
				journalArticleResource.getGroupId(),
				journalArticleResource.getArticleId(),
				WorkflowConstants.STATUS_APPROVED);
		}

		JSONObject journalArticleJSONObject =
			JSONFactoryUtil.createJSONObject();

		ClassName journalArticleClassName = classNameLocalService.getClassName(
			JournalArticle.class.getName());

		try {
			DDMStructure ddmStructure = ddmStructureLocalService.getStructure(
				journalArticle.getGroupId(),
				journalArticleClassName.getClassNameId(),
				journalArticle.getStructureId(), true);

			journalArticleJSONObject.put(
				"DDMStructure",
				JSONFactoryUtil.createJSONObject(
					JSONFactoryUtil.looseSerialize(ddmStructure)));
		}
		catch (NoSuchStructureException nsse) {
			_log.error(nsse, nsse);
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			JSONFactoryUtil.looseSerialize(journalArticle));

		journalArticleJSONObject.put("modelAttributes", jsonObject);
		journalArticleJSONObject.put(
			"modelValues", jsonObject.getString("content"));

		jsonObject.remove("content");

		return journalArticleJSONObject;
	}

	protected JSONObject getUserJSONObject(AssetEntry assetEntry)
		throws PortalException, SystemException {

		User user = userService.getUserById(assetEntry.getClassPK());

		JSONObject userJSONObject = JSONFactoryUtil.createJSONObject();

		userJSONObject.put(
			"user",
			JSONFactoryUtil.createJSONObject(
				JSONFactoryUtil.looseSerialize(user)));

		return userJSONObject;
	}

	protected JSONArray toJSONArray(
			List<AssetEntry> assetEntries, Locale locale)
		throws PortalException, SystemException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (AssetEntry assetEntry : assetEntries) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
				JSONFactoryUtil.looseSerialize(assetEntry));

			jsonObject.put("className", assetEntry.getClassName());
			jsonObject.put("description", assetEntry.getDescription(locale));
			jsonObject.put("locale", String.valueOf(locale));
			jsonObject.put(
				"object", getAssetObjectJSONObject(assetEntry, locale));
			jsonObject.put("summary", assetEntry.getSummary(locale));
			jsonObject.put("title", assetEntry.getTitle(locale));

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	private static final MethodKey _containsPermissionMethodKey =
		new MethodKey(
			ClassResolverUtil.resolveByPortalClassLoader(
				"com.liferay.portlet.asset.service.permission." +
					"AssetEntryPermission"),
			"contains", PermissionChecker.class, AssetEntry.class,
			String.class);

	private static Log _log = LogFactoryUtil.getLog(
		ScreensAssetEntryServiceImpl.class);

}