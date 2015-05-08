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

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.socialcoding.model.SVNRevision;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SVNRevision in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SVNRevision
 * @generated
 */
@ProviderType
public class SVNRevisionCacheModel implements CacheModel<SVNRevision>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SVNRevisionCacheModel)) {
			return false;
		}

		SVNRevisionCacheModel svnRevisionCacheModel = (SVNRevisionCacheModel)obj;

		if (svnRevisionId == svnRevisionCacheModel.svnRevisionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, svnRevisionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{svnRevisionId=");
		sb.append(svnRevisionId);
		sb.append(", svnUserId=");
		sb.append(svnUserId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", svnRepositoryId=");
		sb.append(svnRepositoryId);
		sb.append(", revisionNumber=");
		sb.append(revisionNumber);
		sb.append(", comments=");
		sb.append(comments);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SVNRevision toEntityModel() {
		SVNRevisionImpl svnRevisionImpl = new SVNRevisionImpl();

		svnRevisionImpl.setSvnRevisionId(svnRevisionId);

		if (svnUserId == null) {
			svnRevisionImpl.setSvnUserId(StringPool.BLANK);
		}
		else {
			svnRevisionImpl.setSvnUserId(svnUserId);
		}

		if (createDate == Long.MIN_VALUE) {
			svnRevisionImpl.setCreateDate(null);
		}
		else {
			svnRevisionImpl.setCreateDate(new Date(createDate));
		}

		svnRevisionImpl.setSvnRepositoryId(svnRepositoryId);
		svnRevisionImpl.setRevisionNumber(revisionNumber);

		if (comments == null) {
			svnRevisionImpl.setComments(StringPool.BLANK);
		}
		else {
			svnRevisionImpl.setComments(comments);
		}

		svnRevisionImpl.resetOriginalValues();

		return svnRevisionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		svnRevisionId = objectInput.readLong();
		svnUserId = objectInput.readUTF();
		createDate = objectInput.readLong();
		svnRepositoryId = objectInput.readLong();
		revisionNumber = objectInput.readLong();
		comments = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(svnRevisionId);

		if (svnUserId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(svnUserId);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(svnRepositoryId);
		objectOutput.writeLong(revisionNumber);

		if (comments == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(comments);
		}
	}

	public long svnRevisionId;
	public String svnUserId;
	public long createDate;
	public long svnRepositoryId;
	public long revisionNumber;
	public String comments;
}