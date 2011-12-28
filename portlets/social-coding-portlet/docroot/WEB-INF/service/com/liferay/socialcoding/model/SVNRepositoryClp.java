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

package com.liferay.socialcoding.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.socialcoding.service.SVNRepositoryLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

/**
 * @author Brian Wing Shun Chan
 */
public class SVNRepositoryClp extends BaseModelImpl<SVNRepository>
	implements SVNRepository {
	public SVNRepositoryClp() {
	}

	public Class<?> getModelClass() {
		return SVNRepository.class;
	}

	public String getModelClassName() {
		return SVNRepository.class.getName();
	}

	public long getPrimaryKey() {
		return _svnRepositoryId;
	}

	public void setPrimaryKey(long primaryKey) {
		setSvnRepositoryId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_svnRepositoryId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getSvnRepositoryId() {
		return _svnRepositoryId;
	}

	public void setSvnRepositoryId(long svnRepositoryId) {
		_svnRepositoryId = svnRepositoryId;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public long getRevisionNumber() {
		return _revisionNumber;
	}

	public void setRevisionNumber(long revisionNumber) {
		_revisionNumber = revisionNumber;
	}

	public java.lang.String getName() {
		throw new UnsupportedOperationException();
	}

	public java.lang.String getShortURL() {
		throw new UnsupportedOperationException();
	}

	public void persist() throws SystemException {
		SVNRepositoryLocalServiceUtil.updateSVNRepository(this);
	}

	@Override
	public SVNRepository toEscapedModel() {
		return (SVNRepository)Proxy.newProxyInstance(SVNRepository.class.getClassLoader(),
			new Class[] { SVNRepository.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SVNRepositoryClp clone = new SVNRepositoryClp();

		clone.setSvnRepositoryId(getSvnRepositoryId());
		clone.setUrl(getUrl());
		clone.setRevisionNumber(getRevisionNumber());

		return clone;
	}

	public int compareTo(SVNRepository svnRepository) {
		int value = 0;

		value = getUrl().compareTo(svnRepository.getUrl());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		SVNRepositoryClp svnRepository = null;

		try {
			svnRepository = (SVNRepositoryClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = svnRepository.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{svnRepositoryId=");
		sb.append(getSvnRepositoryId());
		sb.append(", url=");
		sb.append(getUrl());
		sb.append(", revisionNumber=");
		sb.append(getRevisionNumber());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.socialcoding.model.SVNRepository");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>svnRepositoryId</column-name><column-value><![CDATA[");
		sb.append(getSvnRepositoryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>url</column-name><column-value><![CDATA[");
		sb.append(getUrl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>revisionNumber</column-name><column-value><![CDATA[");
		sb.append(getRevisionNumber());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _svnRepositoryId;
	private String _url;
	private long _revisionNumber;
}