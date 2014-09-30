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

import com.liferay.dlfilename.hook.model.impl.DLFileNameWrapperFileEntryImpl;
import com.liferay.dlfilename.hook.model.impl.DLFileNameWrapperFileVersionImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.trash.TrashRenderer;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.asset.model.BaseAssetRenderer;

import java.lang.reflect.Constructor;

import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Preston Crary
 */
public class DLFileNameDLFileEntryAssetRenderer
		extends BaseAssetRenderer implements TrashRenderer {

	public DLFileNameDLFileEntryAssetRenderer(
			FileEntry fileEntry, FileVersion fileVersion)
		throws PortalException {

		ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

		try {
			Class<?> clazz = classLoader.loadClass(
				_DL_FILE_ENTRY_ASSET_RENDERER_CLASSPATH);

			Constructor<?> constructor = clazz.getConstructor(
				FileEntry.class, FileVersion.class);

			fileEntry = new DLFileNameWrapperFileEntryImpl(fileEntry);
			fileVersion = new DLFileNameWrapperFileVersionImpl(fileVersion);

			_dlFileEntryAssetRenderer = (AssetRenderer)constructor.newInstance(
				fileEntry, fileVersion);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	@Override
	public String getClassName() {
		return _dlFileEntryAssetRenderer.getClassName();
	}

	@Override
	public long getClassPK() {
		return _dlFileEntryAssetRenderer.getClassPK();
	}

	@Override
	public long getGroupId() {
		return _dlFileEntryAssetRenderer.getGroupId();
	}

	@Override
	public String getPortletId() {
		AssetRendererFactory assetRendererFactory = getAssetRendererFactory();

		return assetRendererFactory.getPortletId();
	}

	@Override
	public String getSummary(Locale locale) {
		return _dlFileEntryAssetRenderer.getSummary(locale);
	}

	@Override
	public String getTitle(Locale locale) {
		return _dlFileEntryAssetRenderer.getTitle(locale);
	}

	@Override
	public String getType() {
		AssetRendererFactory assetRendererFactory = getAssetRendererFactory();

		return assetRendererFactory.getType();
	}

	@Override
	public long getUserId() {
		return _dlFileEntryAssetRenderer.getUserId();
	}

	@Override
	public String getUserName() {
		return _dlFileEntryAssetRenderer.getUserName();
	}

	@Override
	public String getUuid() {
		return _dlFileEntryAssetRenderer.getUuid();
	}

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse,
			String template)
		throws Exception {

		return _dlFileEntryAssetRenderer.render(
			renderRequest, renderResponse, template);
	}

	private static final String _DL_FILE_ENTRY_ASSET_RENDERER_CLASSPATH =
		"com.liferay.portlet.documentlibrary.asset.DLFileEntryAssetRenderer";

	private AssetRenderer _dlFileEntryAssetRenderer;

}