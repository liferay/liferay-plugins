/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

import com.liferay.socialcoding.model.SVNRepository;

import java.io.Serializable;

/**
 * The cache model class for representing SVNRepository in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SVNRepository
 * @generated
 */
public class SVNRepositoryCacheModel implements CacheModel<SVNRepository>,
	Serializable {
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

	public long svnRepositoryId;
	public String url;
	public long revisionNumber;
}