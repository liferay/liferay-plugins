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

package com.liferay.dlfilename.hook.asset;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.asset.model.BaseAssetRendererFactory;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletURL;
import javax.portlet.WindowState;

/**
 * @author Preston Crary
 */
public class DLFileNameDLFileEntryAssetRendererFactory
		extends BaseAssetRendererFactory {

	public DLFileNameDLFileEntryAssetRendererFactory(
			AssetRendererFactory assetRendererFactory) {

		_dlFileEntryAssetRendererFactory = assetRendererFactory;

		setClassName(assetRendererFactory.getClassName());
		setPortletId(assetRendererFactory.getPortletId());
	}

	@Override
	public AssetRenderer getAssetRenderer(long classPK, int type)
		throws PortalException, SystemException {

		FileEntry fileEntry = null;
		FileVersion fileVersion = null;

		if (type == TYPE_LATEST) {
			fileVersion = DLAppLocalServiceUtil.getFileVersion(classPK);

			fileEntry = fileVersion.getFileEntry();
		}
		else {
			fileEntry = DLAppLocalServiceUtil.getFileEntry(classPK);

			fileVersion = fileEntry.getFileVersion();
		}

		DLFileNameDLFileEntryAssetRenderer fileNameDLFileEntryAssetRenderer =
			new DLFileNameDLFileEntryAssetRenderer(fileEntry, fileVersion);

		fileNameDLFileEntryAssetRenderer.setAssetRendererType(type);

		return fileNameDLFileEntryAssetRenderer;
	}

	@Override
	public String getClassName() {
		return _dlFileEntryAssetRendererFactory.getClassName();
	}

	@Override
	public List<Tuple> getClassTypeFieldNames(
			long classTypeId, Locale locale, int start, int end)
		throws Exception {

		return _dlFileEntryAssetRendererFactory.getClassTypeFieldNames(
			classTypeId, locale, start, end);
	}

	@Override
	public int getClassTypeFieldNamesCount(long classTypeId, Locale locale)
		throws Exception {

		return _dlFileEntryAssetRendererFactory.getClassTypeFieldNamesCount(
			classTypeId, locale);
	}

	@Override
	public Map<Long, String> getClassTypes(long[] groupIds, Locale locale)
		throws Exception {

		return _dlFileEntryAssetRendererFactory.getClassTypes(groupIds, locale);
	}

	@Override
	public String getType() {
		return _dlFileEntryAssetRendererFactory.getType();
	}

	@Override
	public String getTypeName(Locale locale, boolean hasSubtypes) {
		return _dlFileEntryAssetRendererFactory.getTypeName(
			locale, hasSubtypes);
	}

	@Override
	public PortletURL getURLAdd(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws PortalException, SystemException {

		return _dlFileEntryAssetRendererFactory.getURLAdd(
			liferayPortletRequest, liferayPortletResponse);
	}

	@Override
	public PortletURL getURLView(
			LiferayPortletResponse liferayPortletResponse,
			WindowState windowState)
		throws PortalException, SystemException {

		return _dlFileEntryAssetRendererFactory.getURLView(
			liferayPortletResponse, windowState);
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws Exception {

		return _dlFileEntryAssetRendererFactory.hasPermission(
			permissionChecker, classPK, actionId);
	}

	@Override
	public boolean isLinkable() {
		return _dlFileEntryAssetRendererFactory.isLinkable();
	}

	private AssetRendererFactory _dlFileEntryAssetRendererFactory;

}