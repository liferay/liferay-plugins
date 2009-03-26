/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.sd.model.impl;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.impl.ExpandoBridgeImpl;

import com.liferay.sd.model.SVNRevision;
import com.liferay.sd.model.SVNRevisionSoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="SVNRevisionModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SVNRevisionModelImpl extends BaseModelImpl<SVNRevision> {
	public static final String TABLE_NAME = "WOL_SVNRevision";
	public static final Object[][] TABLE_COLUMNS = {
			{ "svnRevisionId", new Integer(Types.BIGINT) },
			

			{ "svnUserId", new Integer(Types.VARCHAR) },
			

			{ "createDate", new Integer(Types.TIMESTAMP) },
			

			{ "svnRepositoryId", new Integer(Types.BIGINT) },
			

			{ "revisionNumber", new Integer(Types.BIGINT) },
			

			{ "comments", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table WOL_SVNRevision (svnRevisionId LONG not null primary key,svnUserId VARCHAR(75) null,createDate DATE null,svnRepositoryId LONG,revisionNumber LONG,comments STRING null)";
	public static final String TABLE_SQL_DROP = "drop table WOL_SVNRevision";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.sd.model.SVNRevision"),
			true);

	public static SVNRevision toModel(SVNRevisionSoap soapModel) {
		SVNRevision model = new SVNRevisionImpl();

		model.setSvnRevisionId(soapModel.getSvnRevisionId());
		model.setSvnUserId(soapModel.getSvnUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setSvnRepositoryId(soapModel.getSvnRepositoryId());
		model.setRevisionNumber(soapModel.getRevisionNumber());
		model.setComments(soapModel.getComments());

		return model;
	}

	public static List<SVNRevision> toModels(SVNRevisionSoap[] soapModels) {
		List<SVNRevision> models = new ArrayList<SVNRevision>(soapModels.length);

		for (SVNRevisionSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.sd.model.SVNRevision"));

	public SVNRevisionModelImpl() {
	}

	public long getPrimaryKey() {
		return _svnRevisionId;
	}

	public void setPrimaryKey(long pk) {
		setSvnRevisionId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_svnRevisionId);
	}

	public long getSvnRevisionId() {
		return _svnRevisionId;
	}

	public void setSvnRevisionId(long svnRevisionId) {
		if (svnRevisionId != _svnRevisionId) {
			_svnRevisionId = svnRevisionId;
		}
	}

	public String getSvnUserId() {
		return GetterUtil.getString(_svnUserId);
	}

	public void setSvnUserId(String svnUserId) {
		if (((svnUserId == null) && (_svnUserId != null)) ||
				((svnUserId != null) && (_svnUserId == null)) ||
				((svnUserId != null) && (_svnUserId != null) &&
				!svnUserId.equals(_svnUserId))) {
			_svnUserId = svnUserId;
		}
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		if (((createDate == null) && (_createDate != null)) ||
				((createDate != null) && (_createDate == null)) ||
				((createDate != null) && (_createDate != null) &&
				!createDate.equals(_createDate))) {
			_createDate = createDate;
		}
	}

	public long getSvnRepositoryId() {
		return _svnRepositoryId;
	}

	public void setSvnRepositoryId(long svnRepositoryId) {
		if (svnRepositoryId != _svnRepositoryId) {
			_svnRepositoryId = svnRepositoryId;
		}
	}

	public long getRevisionNumber() {
		return _revisionNumber;
	}

	public void setRevisionNumber(long revisionNumber) {
		if (revisionNumber != _revisionNumber) {
			_revisionNumber = revisionNumber;
		}
	}

	public String getComments() {
		return GetterUtil.getString(_comments);
	}

	public void setComments(String comments) {
		if (((comments == null) && (_comments != null)) ||
				((comments != null) && (_comments == null)) ||
				((comments != null) && (_comments != null) &&
				!comments.equals(_comments))) {
			_comments = comments;
		}
	}

	public SVNRevision toEscapedModel() {
		if (isEscapedModel()) {
			return (SVNRevision)this;
		}
		else {
			SVNRevision model = new SVNRevisionImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setSvnRevisionId(getSvnRevisionId());
			model.setSvnUserId(HtmlUtil.escape(getSvnUserId()));
			model.setCreateDate(getCreateDate());
			model.setSvnRepositoryId(getSvnRepositoryId());
			model.setRevisionNumber(getRevisionNumber());
			model.setComments(HtmlUtil.escape(getComments()));

			model = (SVNRevision)Proxy.newProxyInstance(SVNRevision.class.getClassLoader(),
					new Class[] { SVNRevision.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(SVNRevision.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		SVNRevisionImpl clone = new SVNRevisionImpl();

		clone.setSvnRevisionId(getSvnRevisionId());
		clone.setSvnUserId(getSvnUserId());
		clone.setCreateDate(getCreateDate());
		clone.setSvnRepositoryId(getSvnRepositoryId());
		clone.setRevisionNumber(getRevisionNumber());
		clone.setComments(getComments());

		return clone;
	}

	public int compareTo(SVNRevision svnRevision) {
		int value = 0;

		if (getRevisionNumber() < svnRevision.getRevisionNumber()) {
			value = -1;
		}
		else if (getRevisionNumber() > svnRevision.getRevisionNumber()) {
			value = 1;
		}
		else {
			value = 0;
		}

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		SVNRevision svnRevision = null;

		try {
			svnRevision = (SVNRevision)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = svnRevision.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	private long _svnRevisionId;
	private String _svnUserId;
	private Date _createDate;
	private long _svnRepositoryId;
	private long _revisionNumber;
	private String _comments;
	private transient ExpandoBridge _expandoBridge;
}