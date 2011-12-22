/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.socialcoding.model.SVNRevision;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing SVNRevision in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SVNRevision
 * @generated
 */
public class SVNRevisionCacheModel implements CacheModel<SVNRevision>,
	Serializable {
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

	public long svnRevisionId;
	public String svnUserId;
	public long createDate;
	public long svnRepositoryId;
	public long revisionNumber;
	public String comments;
}