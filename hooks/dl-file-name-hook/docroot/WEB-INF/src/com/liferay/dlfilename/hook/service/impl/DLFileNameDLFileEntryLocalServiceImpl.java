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

package com.liferay.dlfilename.hook.service.impl;

import com.liferay.dlfilename.hook.model.impl.DLFileNameFileEntryImpl;
import com.liferay.dlfilename.hook.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalService;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceWrapper;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

import java.util.Map;

/**
 * @author Preston Crary
 */
public class DLFileNameDLFileEntryLocalServiceImpl
	extends DLFileEntryLocalServiceWrapper {

	public DLFileNameDLFileEntryLocalServiceImpl(
		DLFileEntryLocalService dlFileEntryLocalService) {

		super(dlFileEntryLocalService);
	}

	@Override
	public DLFileEntry addFileEntry(
			long userId, long groupId, long repositoryId, long folderId,
			String sourceFileName, String mimeType, String title,
			String description, String changeLog, long fileEntryTypeId,
			Map<String, Fields> fieldsMap, File file, InputStream is, long size,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (Validator.isNull(title)) {
			title = sourceFileName;
		}

		Map<String, Serializable> expandoBridgeAttributes =
			serviceContext.getExpandoBridgeAttributes();

		if (!expandoBridgeAttributes.containsKey(
				DLFileNameFileEntryImpl.EXPAND_COLUMN_NAME_DISPLAY_TITLE)) {

			expandoBridgeAttributes.put(
				DLFileNameFileEntryImpl.EXPAND_COLUMN_NAME_DISPLAY_TITLE,
				title);

			serviceContext.setExpandoBridgeAttributes(expandoBridgeAttributes);

			title = removeWhitelistCharacters(title);
			sourceFileName = removeWhitelistCharacters(sourceFileName);
		}

		return super.addFileEntry(
			userId, groupId, repositoryId, folderId, sourceFileName, mimeType,
			title, description, changeLog, fileEntryTypeId, fieldsMap, file, is,
			size, serviceContext);
	}

	@Override
	public DLFileEntry updateFileEntry(
			long userId, long fileEntryId, String sourceFileName,
			String mimeType, String title, String description, String changeLog,
			boolean majorVersion, long fileEntryTypeId,
			Map<String, Fields> fieldsMap, File file, InputStream is, long size,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (Validator.isNull(title)) {
			title = sourceFileName;
		}

		Map<String, Serializable> expandoBridgeAttributes =
			serviceContext.getExpandoBridgeAttributes();

		expandoBridgeAttributes.put(
			DLFileNameFileEntryImpl.EXPAND_COLUMN_NAME_DISPLAY_TITLE, title);

		serviceContext.setExpandoBridgeAttributes(expandoBridgeAttributes);

		title = removeWhitelistCharacters(title);
		sourceFileName = removeWhitelistCharacters(sourceFileName);

		return super.updateFileEntry(
			userId, fileEntryId, sourceFileName, mimeType, title, description,
			changeLog, majorVersion, fileEntryTypeId, fieldsMap, file, is, size,
			serviceContext);
	}

	protected String removeWhitelistCharacters(String name) {
		for (String whitelistCharacter : PortletPropsValues.DL_CHAR_WHITELIST) {
			name = StringUtil.replace(
				name, whitelistCharacter, StringPool.BLANK);
		}

		return name;
	}

}