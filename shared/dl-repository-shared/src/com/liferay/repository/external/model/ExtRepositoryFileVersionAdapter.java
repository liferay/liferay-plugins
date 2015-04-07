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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.RepositoryModelOperation;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portlet.documentlibrary.util.DLUtil;
import com.liferay.repository.external.ExtRepositoryAdapter;
import com.liferay.repository.external.ExtRepositoryFileVersion;

import java.io.InputStream;

import java.util.Date;

/**
 * @author Iván Zaera
 * @author Sergio González
 */
public class ExtRepositoryFileVersionAdapter
	extends ExtRepositoryModelAdapter<FileVersion> implements FileVersion {

	public ExtRepositoryFileVersionAdapter(
		ExtRepositoryAdapter extRepositoryAdapter, long extRepositoryObjectId,
		String uuid,
		ExtRepositoryFileEntryAdapter extRepositoryFileEntryAdapter,
		ExtRepositoryFileVersion extRepositoryFileVersion) {

		super(
			extRepositoryAdapter, extRepositoryObjectId, uuid,
			extRepositoryFileVersion);

		_extRepositoryFileEntryAdapter = extRepositoryFileEntryAdapter;
		_extRepositoryFileVersion = extRepositoryFileVersion;
	}

	@Override
	public void execute(RepositoryModelOperation repositoryModelOperation)
		throws PortalException {

		repositoryModelOperation.execute(this);
	}

	@Override
	public String getChangeLog() {
		return _extRepositoryFileVersion.getChangeLog();
	}

	@Override
	public InputStream getContentStream(boolean incrementCounter)
		throws PortalException {

		ExtRepositoryAdapter extRepositoryAdapter = getRepository();

		return extRepositoryAdapter.getContentStream(this);
	}

	@Override
	public String getExtension() {
		return _extRepositoryFileEntryAdapter.getExtension();
	}

	@Override
	public String getExtraSettings() {
		return null;
	}

	@Override
	public ExtRepositoryFileVersion getExtRepositoryModel() {
		return _extRepositoryFileVersion;
	}

	@Override
	@SuppressWarnings("unused")
	public FileEntry getFileEntry() throws PortalException {
		return _extRepositoryFileEntryAdapter;
	}

	@Override
	public long getFileEntryId() {
		return _extRepositoryFileEntryAdapter.getFileEntryId();
	}

	@Override
	public String getFileName() {
		return DLUtil.getSanitizedFileName(getTitle(), getExtension());
	}

	@Override
	public long getFileVersionId() {
		return getPrimaryKey();
	}

	@Override
	public String getIcon() {
		return DLUtil.getFileIcon(getExtension());
	}

	@Override
	public String getMimeType() {
		String mimeType = _extRepositoryFileVersion.getMimeType();

		if (Validator.isNull(mimeType)) {
			mimeType = MimeTypesUtil.getContentType(getTitle());
		}

		return mimeType;
	}

	@Override
	public Class<?> getModelClass() {
		return FileVersion.class;
	}

	@Override
	public Date getModifiedDate() {
		return getCreateDate();
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(FileVersion.class);
	}

	@Override
	public int getStatus() {
		return WorkflowConstants.STATUS_APPROVED;
	}

	@Override
	public long getStatusByUserId() {
		return getUserId();
	}

	@Override
	public String getStatusByUserName() {
		return getUserName();
	}

	@Override
	public String getStatusByUserUuid() {
		return getUserUuid();
	}

	@Override
	public Date getStatusDate() {
		return getCreateDate();
	}

	@Override
	public String getTitle() {
		return _extRepositoryFileEntryAdapter.getTitle();
	}

	@Override
	public String getVersion() {
		return _extRepositoryFileVersion.getVersion();
	}

	@Override
	public boolean isApproved() {
		return false;
	}

	@Override
	public boolean isDraft() {
		return false;
	}

	@Override
	public boolean isExpired() {
		return false;
	}

	@Override
	public boolean isPending() {
		return false;
	}

	private ExtRepositoryFileEntryAdapter _extRepositoryFileEntryAdapter;
	private ExtRepositoryFileVersion _extRepositoryFileVersion;

}