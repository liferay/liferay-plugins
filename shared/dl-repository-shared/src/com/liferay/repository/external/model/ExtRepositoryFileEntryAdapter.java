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

package com.liferay.repository.external.model;

import com.liferay.document.library.kernel.exception.NoSuchFileVersionException;
import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lock.Lock;
import com.liferay.portal.kernel.lock.LockManagerUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.Repository;
import com.liferay.portal.kernel.repository.capabilities.Capability;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileShortcut;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.repository.model.RepositoryModelOperation;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.repository.external.ExtRepositoryAdapter;
import com.liferay.repository.external.ExtRepositoryFileEntry;

import java.io.InputStream;

import java.util.Collections;
import java.util.List;

/**
 * @author Iván Zaera
 * @author Sergio González
 */
public class ExtRepositoryFileEntryAdapter
	extends ExtRepositoryObjectAdapter<FileEntry> implements FileEntry {

	public ExtRepositoryFileEntryAdapter(
		ExtRepositoryAdapter extRepositoryAdapter, long extRepositoryObjectId,
		String uuid, ExtRepositoryFileEntry extRepositoryFileEntry) {

		super(
			extRepositoryAdapter, extRepositoryObjectId, uuid,
			extRepositoryFileEntry);

		_extRepositoryFileEntry = extRepositoryFileEntry;
	}

	@Override
	public void execute(RepositoryModelOperation repositoryModelOperation)
		throws PortalException {

		repositoryModelOperation.execute(this);
	}

	@Override
	public InputStream getContentStream() throws PortalException {
		ExtRepositoryAdapter extRepositoryAdapter = getRepository();

		return extRepositoryAdapter.getContentStream(this);
	}

	@Override
	public InputStream getContentStream(String version) throws PortalException {
		ExtRepositoryAdapter extRepositoryAdapter = getRepository();

		FileVersion fileVersion = getFileVersion(version);

		return extRepositoryAdapter.getContentStream(
			(ExtRepositoryFileVersionAdapter)fileVersion);
	}

	@Override
	public ExtRepositoryFileEntry getExtRepositoryModel() {
		return _extRepositoryFileEntry;
	}

	@Override
	public long getFileEntryId() {
		return getPrimaryKey();
	}

	@Override
	public String getFileName() {
		return DLUtil.getSanitizedFileName(getTitle(), getExtension());
	}

	@Override
	public List<FileShortcut> getFileShortcuts() {
		return Collections.emptyList();
	}

	@Override
	public FileVersion getFileVersion() {
		try {
			List<ExtRepositoryFileVersionAdapter>
				extRepositoryFileVersionAdapters =
					_getExtRepositoryFileVersionAdapters();

			return extRepositoryFileVersionAdapters.get(0);
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}
	}

	@Override
	public FileVersion getFileVersion(String version) throws PortalException {
		List<ExtRepositoryFileVersionAdapter> extRepositoryFileVersionAdapters =
			_getExtRepositoryFileVersionAdapters();

		for (ExtRepositoryFileVersionAdapter extRepositoryFileVersionAdapter :
				extRepositoryFileVersionAdapters) {

			String curVersion = extRepositoryFileVersionAdapter.getVersion();

			if (curVersion.equals(version)) {
				return extRepositoryFileVersionAdapter;
			}
		}

		throw new NoSuchFileVersionException(
			"No file version with {fileEntryId=" + getFileEntryId() +
				", version: " + version + "}");
	}

	@Override
	@SuppressWarnings({"rawtypes"})
	public List<FileVersion> getFileVersions(int status) {
		if ((status == WorkflowConstants.STATUS_ANY) ||
			(status == WorkflowConstants.STATUS_APPROVED)) {

			try {
				return (List)_getExtRepositoryFileVersionAdapters();
			}
			catch (PortalException pe) {
				throw new SystemException(pe);
			}
		}
		else {
			return Collections.emptyList();
		}
	}

	@Override
	public int getFileVersionsCount(int status) {
		List<FileVersion> fileVersions = getFileVersions(status);

		return fileVersions.size();
	}

	@Override
	public Folder getFolder() {
		Folder parentFolder = null;

		try {
			parentFolder = getParentFolder();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return parentFolder;
	}

	@Override
	public long getFolderId() {
		Folder folder = getFolder();

		return folder.getFolderId();
	}

	@Override
	public String getIcon() {
		return DLUtil.getFileIcon(getExtension());
	}

	@Override
	public String getIconCssClass() {
		return DLUtil.getFileIconCssClass(getExtension());
	}

	@Override
	@SuppressWarnings("unused")
	public FileVersion getLatestFileVersion() throws PortalException {
		return getFileVersion();
	}

	@Override
	@SuppressWarnings("unused")
	public FileVersion getLatestFileVersion(boolean trusted)
		throws PortalException {

		return getFileVersion();
	}

	@Override
	public Lock getLock() {
		if (!isCheckedOut()) {
			return null;
		}

		String checkedOutBy = _extRepositoryFileEntry.getCheckedOutBy();

		User user = getUser(checkedOutBy);

		long userId = 0;
		String userName = null;

		if (user != null) {
			userId = user.getUserId();
			userName = user.getFullName();
		}

		return LockManagerUtil.createLock(0, getCompanyId(), userId, userName);
	}

	@Override
	public String getMimeType() {
		String mimeType = _extRepositoryFileEntry.getMimeType();

		if (Validator.isNull(mimeType)) {
			mimeType = MimeTypesUtil.getContentType(getTitle());
		}

		return mimeType;
	}

	@Override
	public String getMimeType(String version) {
		FileVersion fileVersion = null;

		try {
			fileVersion = getFileVersion(version);
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to obtain version " + version + " for external " +
						"repository file entry " + getTitle(),
					pe);
			}
		}
		catch (SystemException se) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to obtain version " + version + " for external " +
						"repository file entry " + getTitle(),
					se);
			}
		}

		if (fileVersion != null) {
			String mimeType = fileVersion.getMimeType();

			if (Validator.isNotNull(mimeType)) {
				return mimeType;
			}
		}

		return MimeTypesUtil.getContentType(getTitle());
	}

	@Override
	public Class<?> getModelClass() {
		return FileEntry.class;
	}

	@Override
	public String getName() {
		return getTitle();
	}

	@Override
	public int getReadCount() {
		return 0;
	}

	@Override
	public <T extends Capability> T getRepositoryCapability(
		Class<T> capabilityClass) {

		Repository repository = getRepository();

		return repository.getCapability(capabilityClass);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(DLFileEntryConstants.getClassName());
	}

	@Override
	public String getTitle() {
		return _extRepositoryFileEntry.getTitle();
	}

	@Override
	public String getVersion() {
		try {
			List<ExtRepositoryFileVersionAdapter>
				extRepositoryFileVersionAdapters =
					_getExtRepositoryFileVersionAdapters();

			FileVersion fileVersion = extRepositoryFileVersionAdapters.get(0);

			return fileVersion.getVersion();
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	public long getVersionUserId() {
		return getUserId();
	}

	@Override
	public String getVersionUserName() {
		return getUserName();
	}

	@Override
	public String getVersionUserUuid() {
		return getUserUuid();
	}

	@Override
	public boolean hasLock() {
		if (!isCheckedOut()) {
			return false;
		}

		User checkedOutByUser = getUser(
			_extRepositoryFileEntry.getCheckedOutBy());

		if (checkedOutByUser.getUserId() != PrincipalThreadLocal.getUserId()) {
			return false;
		}

		return true;
	}

	@Override
	public boolean isCheckedOut() {
		if (Validator.isNull(_extRepositoryFileEntry.getCheckedOutBy())) {
			return false;
		}

		return true;
	}

	@Override
	public boolean isManualCheckInRequired() {
		return true;
	}

	@Override
	public <T extends Capability> boolean isRepositoryCapabilityProvided(
		Class<T> capabilityClass) {

		Repository repository = getRepository();

		return repository.isCapabilityProvided(capabilityClass);
	}

	@Override
	public boolean isSupportsLocking() {
		return true;
	}

	private List<ExtRepositoryFileVersionAdapter>
			_getExtRepositoryFileVersionAdapters()
		throws PortalException {

		ExtRepositoryAdapter extRepositoryAdapter = getRepository();

		return extRepositoryAdapter.getExtRepositoryFileVersionAdapters(this);
	}

	private static Log _log = LogFactoryUtil.getLog(
		ExtRepositoryFileEntryAdapter.class);

	private ExtRepositoryFileEntry _extRepositoryFileEntry;

}