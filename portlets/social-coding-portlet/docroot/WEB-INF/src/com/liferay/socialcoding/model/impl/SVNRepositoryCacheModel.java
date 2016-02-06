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

package com.liferay.socialcoding.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.liferay.socialcoding.model.SVNRepository;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SVNRepository in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SVNRepository
 * @generated
 */
@ProviderType
public class SVNRepositoryCacheModel implements CacheModel<SVNRepository>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SVNRepositoryCacheModel)) {
			return false;
		}

		SVNRepositoryCacheModel svnRepositoryCacheModel = (SVNRepositoryCacheModel)obj;

		if (svnRepositoryId == svnRepositoryCacheModel.svnRepositoryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, svnRepositoryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{svnRepositoryId=");
		sb.append(svnRepositoryId);
		sb.append(", url=");
		sb.append(url);
		sb.append(", revisionNumber=");
		sb.append(revisionNumber);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SVNRepository toEntityModel() {
		SVNRepositoryImpl svnRepositoryImpl = new SVNRepositoryImpl();

		svnRepositoryImpl.setSvnRepositoryId(svnRepositoryId);

		if (url == null) {
			svnRepositoryImpl.setUrl(StringPool.BLANK);
		}
		else {
			svnRepositoryImpl.setUrl(url);
		}

		svnRepositoryImpl.setRevisionNumber(revisionNumber);

		svnRepositoryImpl.resetOriginalValues();

		return svnRepositoryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		svnRepositoryId = objectInput.readLong();
		url = objectInput.readUTF();

		revisionNumber = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(svnRepositoryId);

		if (url == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(url);
		}

		objectOutput.writeLong(revisionNumber);
	}

	public long svnRepositoryId;
	public String url;
	public long revisionNumber;
}