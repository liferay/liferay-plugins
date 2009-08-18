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

package com.liferay.wol.model;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * <a href="SVNRevisionClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class SVNRevisionClp extends BaseModelImpl<SVNRevision>
	implements SVNRevision {
	public SVNRevisionClp() {
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
		_svnRevisionId = svnRevisionId;
	}

	public String getSvnUserId() {
		return _svnUserId;
	}

	public void setSvnUserId(String svnUserId) {
		_svnUserId = svnUserId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public long getSvnRepositoryId() {
		return _svnRepositoryId;
	}

	public void setSvnRepositoryId(long svnRepositoryId) {
		_svnRepositoryId = svnRepositoryId;
	}

	public long getRevisionNumber() {
		return _revisionNumber;
	}

	public void setRevisionNumber(long revisionNumber) {
		_revisionNumber = revisionNumber;
	}

	public String getComments() {
		return _comments;
	}

	public void setComments(String comments) {
		_comments = comments;
	}

	public com.liferay.wol.model.SVNRepository getSVNRepository() {
		throw new UnsupportedOperationException();
	}

	public java.lang.String getWebRevisionNumberURL() {
		throw new UnsupportedOperationException();
	}

	public java.lang.Object[] getJIRAIssueAndComments() {
		throw new UnsupportedOperationException();
	}

	public SVNRevision toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			SVNRevision model = new SVNRevisionClp();

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

	public Object clone() {
		SVNRevisionClp clone = new SVNRevisionClp();

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

		SVNRevisionClp svnRevision = null;

		try {
			svnRevision = (SVNRevisionClp)obj;
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

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("{svnRevisionId=");
		sb.append(getSvnRevisionId());
		sb.append(", svnUserId=");
		sb.append(getSvnUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", svnRepositoryId=");
		sb.append(getSvnRepositoryId());
		sb.append(", revisionNumber=");
		sb.append(getRevisionNumber());
		sb.append(", comments=");
		sb.append(getComments());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<model><model-name>");
		sb.append("com.liferay.wol.model.SVNRevision");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>svnRevisionId</column-name><column-value><![CDATA[");
		sb.append(getSvnRevisionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>svnUserId</column-name><column-value><![CDATA[");
		sb.append(getSvnUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>svnRepositoryId</column-name><column-value><![CDATA[");
		sb.append(getSvnRepositoryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>revisionNumber</column-name><column-value><![CDATA[");
		sb.append(getRevisionNumber());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>comments</column-name><column-value><![CDATA[");
		sb.append(getComments());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _svnRevisionId;
	private String _svnUserId;
	private Date _createDate;
	private long _svnRepositoryId;
	private long _revisionNumber;
	private String _comments;
}