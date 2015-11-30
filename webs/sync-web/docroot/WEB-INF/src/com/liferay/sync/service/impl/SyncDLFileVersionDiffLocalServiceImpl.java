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

package com.liferay.sync.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.sync.model.SyncDLFileVersionDiff;
import com.liferay.sync.service.base.SyncDLFileVersionDiffLocalServiceBaseImpl;
import com.liferay.sync.util.PortletPropsValues;

import java.io.File;

import java.util.Date;
import java.util.List;

/**
 * @author Dennis Ju
 */
public class SyncDLFileVersionDiffLocalServiceImpl
	extends SyncDLFileVersionDiffLocalServiceBaseImpl {

	@Override
	public SyncDLFileVersionDiff addSyncDLFileVersionDiff(
			long fileEntryId, long sourceFileVersionId,
			long targetFileVersionId, File file)
		throws PortalException {

		long syncDLFileVersionDiffId = counterLocalService.increment();

		SyncDLFileVersionDiff syncDLFileVersionDiff =
			syncDLFileVersionDiffPersistence.create(syncDLFileVersionDiffId);

		syncDLFileVersionDiff.setFileEntryId(fileEntryId);
		syncDLFileVersionDiff.setSourceFileVersionId(sourceFileVersionId);
		syncDLFileVersionDiff.setTargetFileVersionId(targetFileVersionId);

		Company company = companyLocalService.getCompanyByMx(
			PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));

		Group group = company.getGroup();

		FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

		String dataFileName = getDataFileName(
			fileEntryId, sourceFileVersionId, targetFileVersionId);

		FileEntry dataFileEntry = PortletFileRepositoryUtil.addPortletFileEntry(
			group.getGroupId(), fileEntry.getUserId(),
			SyncDLFileVersionDiff.class.getName(),
			syncDLFileVersionDiff.getSyncDLFileVersionDiffId(),
			PortletKeys.DOCUMENT_LIBRARY,
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, file, dataFileName,
			fileEntry.getMimeType(), false);

		syncDLFileVersionDiff.setDataFileEntryId(
			dataFileEntry.getFileEntryId());

		syncDLFileVersionDiff.setSize(file.length());

		Date expirationDate = new Date();

		expirationDate.setTime(
			expirationDate.getTime() +
				PortletPropsValues.SYNC_FILE_DIFF_CACHE_EXPIRATION_TIME *
					3600000);

		syncDLFileVersionDiff.setExpirationDate(expirationDate);

		syncDLFileVersionDiffPersistence.update(syncDLFileVersionDiff);

		return syncDLFileVersionDiff;
	}

	@Override
	public void deleteExpiredSyncDLFileVersionDiffs() throws PortalException {
		List<SyncDLFileVersionDiff> syncDLFileVersionDiffs =
			syncDLFileVersionDiffPersistence.findByExpirationDate(new Date());

		for (SyncDLFileVersionDiff syncDLFileVersionDiff :
				syncDLFileVersionDiffs) {

			deleteSyncDLFileVersionDiff(syncDLFileVersionDiff);
		}
	}

	@Override
	public SyncDLFileVersionDiff deleteSyncDLFileVersionDiff(
			SyncDLFileVersionDiff syncDLFileVersionDiff)
		throws PortalException {

		try {
			PortletFileRepositoryUtil.deletePortletFileEntry(
				syncDLFileVersionDiff.getDataFileEntryId());
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to delete file entry " +
						syncDLFileVersionDiff.getDataFileEntryId());
			}
		}

		return super.deleteSyncDLFileVersionDiff(syncDLFileVersionDiff);
	}

	@Override
	public void deleteSyncDLFileVersionDiffs(long fileEntryId)
		throws PortalException {

		List<SyncDLFileVersionDiff> syncDLFileVersionDiffs =
			syncDLFileVersionDiffPersistence.findByFileEntryId(fileEntryId);

		for (SyncDLFileVersionDiff syncDLFileVersionDiff :
				syncDLFileVersionDiffs) {

			deleteSyncDLFileVersionDiff(syncDLFileVersionDiff);
		}
	}

	@Override
	public SyncDLFileVersionDiff fetchSyncDLFileVersionDiff(
		long fileEntryId, long sourceFileVersionId, long targetFileVersionId) {

		return syncDLFileVersionDiffPersistence.fetchByF_S_T(
			fileEntryId, sourceFileVersionId, targetFileVersionId);
	}

	@Override
	public void refreshExpirationDate(long syncDLFileVersionDiffId)
		throws PortalException {

		SyncDLFileVersionDiff syncDLFileVersionDiff =
			syncDLFileVersionDiffPersistence.findByPrimaryKey(
				syncDLFileVersionDiffId);

		Date expirationDate = new Date();

		expirationDate.setTime(
			expirationDate.getTime() +
				PortletPropsValues.SYNC_FILE_DIFF_CACHE_EXPIRATION_TIME *
					3600000);

		syncDLFileVersionDiff.setExpirationDate(expirationDate);

		updateSyncDLFileVersionDiff(syncDLFileVersionDiff);
	}

	protected String getDataFileName(
		long fileEntryId, long sourceFileVersionId, long targetFileVersionId) {

		StringBundler sb = new StringBundler(5);

		sb.append(fileEntryId);
		sb.append(StringPool.UNDERLINE);
		sb.append(sourceFileVersionId);
		sb.append(StringPool.UNDERLINE);
		sb.append(targetFileVersionId);

		return sb.toString();
	}

	private static Log _log = LogFactoryUtil.getLog(
		SyncDLFileVersionDiffLocalServiceImpl.class);

}