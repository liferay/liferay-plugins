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

package com.liferay.so.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.so.model.ProjectsEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ProjectsEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ProjectsEntry
 * @generated
 */
@ProviderType
public class ProjectsEntryCacheModel implements CacheModel<ProjectsEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProjectsEntryCacheModel)) {
			return false;
		}

		ProjectsEntryCacheModel projectsEntryCacheModel = (ProjectsEntryCacheModel)obj;

		if (projectsEntryId == projectsEntryCacheModel.projectsEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, projectsEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{projectsEntryId=");
		sb.append(projectsEntryId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", data=");
		sb.append(data);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProjectsEntry toEntityModel() {
		ProjectsEntryImpl projectsEntryImpl = new ProjectsEntryImpl();

		projectsEntryImpl.setProjectsEntryId(projectsEntryId);
		projectsEntryImpl.setCompanyId(companyId);
		projectsEntryImpl.setUserId(userId);

		if (userName == null) {
			projectsEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			projectsEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			projectsEntryImpl.setCreateDate(null);
		}
		else {
			projectsEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			projectsEntryImpl.setModifiedDate(null);
		}
		else {
			projectsEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (title == null) {
			projectsEntryImpl.setTitle(StringPool.BLANK);
		}
		else {
			projectsEntryImpl.setTitle(title);
		}

		if (description == null) {
			projectsEntryImpl.setDescription(StringPool.BLANK);
		}
		else {
			projectsEntryImpl.setDescription(description);
		}

		if (startDate == Long.MIN_VALUE) {
			projectsEntryImpl.setStartDate(null);
		}
		else {
			projectsEntryImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			projectsEntryImpl.setEndDate(null);
		}
		else {
			projectsEntryImpl.setEndDate(new Date(endDate));
		}

		if (data == null) {
			projectsEntryImpl.setData(StringPool.BLANK);
		}
		else {
			projectsEntryImpl.setData(data);
		}

		projectsEntryImpl.resetOriginalValues();

		return projectsEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		projectsEntryId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		title = objectInput.readUTF();
		description = objectInput.readUTF();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();
		data = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(projectsEntryId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);

		if (data == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(data);
		}
	}

	public long projectsEntryId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String title;
	public String description;
	public long startDate;
	public long endDate;
	public String data;
}