/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.wol.model.impl;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;

import com.liferay.portlet.service.BaseModelImpl;
import com.liferay.portlet.service.PropsUtil;

import com.liferay.wol.model.SVNRevision;
import com.liferay.wol.model.SVNRevisionSoap;

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
public class SVNRevisionModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "WOL_SVNRevision";
	public static final Object[][] TABLE_COLUMNS = {
			{ "svnRevisionId", new Integer(Types.BIGINT) },
			

			{ "svnRepositoryId", new Integer(Types.BIGINT) },
			

			{ "revisionNumber", new Integer(Types.BIGINT) },
			

			{ "date_", new Integer(Types.TIMESTAMP) },
			

			{ "author", new Integer(Types.VARCHAR) },
			

			{ "comments", new Integer(Types.CLOB) }
		};
	public static final String TABLE_SQL_CREATE = "create table WOL_SVNRevision (svnRevisionId LONG not null primary key,svnRepositoryId LONG,revisionNumber LONG,date_ DATE null,author VARCHAR(75) null,comments TEXT null)";
	public static final String TABLE_SQL_DROP = "drop table WOL_SVNRevision";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.wol.model.SVNRevision"),
			true);

	public static SVNRevision toModel(SVNRevisionSoap soapModel) {
		SVNRevision model = new SVNRevisionImpl();

		model.setSvnRevisionId(soapModel.getSvnRevisionId());
		model.setSvnRepositoryId(soapModel.getSvnRepositoryId());
		model.setRevisionNumber(soapModel.getRevisionNumber());
		model.setDate(soapModel.getDate());
		model.setAuthor(soapModel.getAuthor());
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

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(PropsUtil.get(
				"lock.expiration.time.com.liferay.wol.model.SVNRevision"));

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

	public Date getDate() {
		return _date;
	}

	public void setDate(Date date) {
		if (((date == null) && (_date != null)) ||
				((date != null) && (_date == null)) ||
				((date != null) && (_date != null) && !date.equals(_date))) {
			_date = date;
		}
	}

	public String getAuthor() {
		return GetterUtil.getString(_author);
	}

	public void setAuthor(String author) {
		if (((author == null) && (_author != null)) ||
				((author != null) && (_author == null)) ||
				((author != null) && (_author != null) &&
				!author.equals(_author))) {
			_author = author;
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

			model.setEscapedModel(true);

			model.setSvnRevisionId(getSvnRevisionId());
			model.setSvnRepositoryId(getSvnRepositoryId());
			model.setRevisionNumber(getRevisionNumber());
			model.setDate(getDate());
			model.setAuthor(HtmlUtil.escape(getAuthor()));
			model.setComments(HtmlUtil.escape(getComments()));

			model = (SVNRevision)Proxy.newProxyInstance(SVNRevision.class.getClassLoader(),
					new Class[] { SVNRevision.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		SVNRevisionImpl clone = new SVNRevisionImpl();

		clone.setSvnRevisionId(getSvnRevisionId());
		clone.setSvnRepositoryId(getSvnRepositoryId());
		clone.setRevisionNumber(getRevisionNumber());
		clone.setDate(getDate());
		clone.setAuthor(getAuthor());
		clone.setComments(getComments());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		SVNRevisionImpl svnRevision = (SVNRevisionImpl)obj;

		int value = 0;

		if (getSvnRepositoryId() < svnRevision.getSvnRepositoryId()) {
			value = -1;
		}
		else if (getSvnRepositoryId() > svnRevision.getSvnRepositoryId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (getRevisionNumber() < svnRevision.getRevisionNumber()) {
			value = -1;
		}
		else if (getRevisionNumber() > svnRevision.getRevisionNumber()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		SVNRevisionImpl svnRevision = null;

		try {
			svnRevision = (SVNRevisionImpl)obj;
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
	private long _svnRepositoryId;
	private long _revisionNumber;
	private Date _date;
	private String _author;
	private String _comments;
}