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

package com.liferay.socialcoding.model;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

/**
 * <a href="SVNRepositoryClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class SVNRepositoryClp extends BaseModelImpl<SVNRepository>
	implements SVNRepository {
	public SVNRepositoryClp() {
	}

	public long getPrimaryKey() {
		return _svnRepositoryId;
	}

	public void setPrimaryKey(long pk) {
		setSvnRepositoryId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_svnRepositoryId);
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

	public SVNRepository toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			SVNRepository model = new SVNRepositoryClp();

			model.setEscapedModel(true);

			model.setSvnRepositoryId(getSvnRepositoryId());
			model.setUrl(HtmlUtil.escape(getUrl()));
			model.setRevisionNumber(getRevisionNumber());

			model = (SVNRepository)Proxy.newProxyInstance(SVNRepository.class.getClassLoader(),
					new Class[] { SVNRepository.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

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

		long pk = svnRepository.getPrimaryKey();

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
		StringBuilder sb = new StringBuilder();

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