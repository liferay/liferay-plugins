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

package com.liferay.google.drive.repository.model;

import com.google.api.client.util.DateTime;
import com.google.api.services.drive.model.File;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.repository.external.ExtRepositoryModel;

import java.util.Date;
import java.util.List;

/**
 * @author Sergio González
 */
public class GoogleDriveModel implements ExtRepositoryModel {

	public GoogleDriveModel(
		DateTime createDateTime, String extRepositoryModelKey, long size,
		String owner) {

		_createDate = new Date(createDateTime.getValue());
		_extRepositoryModelKey = extRepositoryModelKey;
		_size = size;
		_owner = owner;
	}

	public GoogleDriveModel(File file) {
		DateTime createDateTime = file.getCreatedDate();

		_createDate = new Date(createDateTime.getValue());

		_extRepositoryModelKey = file.getId();

		_size = GetterUtil.getLong(file.getFileSize());

		List<String> ownerNames = file.getOwnerNames();

		if (!ownerNames.isEmpty()) {
			_owner = ownerNames.get(0);
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public String getExtRepositoryModelKey() {
		return _extRepositoryModelKey;
	}

	@Override
	public String getOwner() {
		return _owner;
	}

	@Override
	public long getSize() {
		return _size;
	}

	private Date _createDate;
	private String _extRepositoryModelKey;
	private String _owner = StringPool.BLANK;
	private long _size;

}