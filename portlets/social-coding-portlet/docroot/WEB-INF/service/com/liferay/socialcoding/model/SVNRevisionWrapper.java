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

/**
 * <a href="SVNRevisionWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class SVNRevisionWrapper implements SVNRevision {
	public SVNRevisionWrapper(SVNRevision svnRevision) {
		_svnRevision = svnRevision;
	}

	public long getPrimaryKey() {
		return _svnRevision.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_svnRevision.setPrimaryKey(pk);
	}

	public long getSvnRevisionId() {
		return _svnRevision.getSvnRevisionId();
	}

	public void setSvnRevisionId(long svnRevisionId) {
		_svnRevision.setSvnRevisionId(svnRevisionId);
	}

	public java.lang.String getSvnUserId() {
		return _svnRevision.getSvnUserId();
	}

	public void setSvnUserId(java.lang.String svnUserId) {
		_svnRevision.setSvnUserId(svnUserId);
	}

	public java.util.Date getCreateDate() {
		return _svnRevision.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_svnRevision.setCreateDate(createDate);
	}

	public long getSvnRepositoryId() {
		return _svnRevision.getSvnRepositoryId();
	}

	public void setSvnRepositoryId(long svnRepositoryId) {
		_svnRevision.setSvnRepositoryId(svnRepositoryId);
	}

	public long getRevisionNumber() {
		return _svnRevision.getRevisionNumber();
	}

	public void setRevisionNumber(long revisionNumber) {
		_svnRevision.setRevisionNumber(revisionNumber);
	}

	public java.lang.String getComments() {
		return _svnRevision.getComments();
	}

	public void setComments(java.lang.String comments) {
		_svnRevision.setComments(comments);
	}

	public com.liferay.socialcoding.model.SVNRevision toEscapedModel() {
		return _svnRevision.toEscapedModel();
	}

	public boolean isNew() {
		return _svnRevision.isNew();
	}

	public boolean setNew(boolean n) {
		return _svnRevision.setNew(n);
	}

	public boolean isCachedModel() {
		return _svnRevision.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_svnRevision.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _svnRevision.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_svnRevision.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _svnRevision.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _svnRevision.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_svnRevision.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _svnRevision.clone();
	}

	public int compareTo(com.liferay.socialcoding.model.SVNRevision svnRevision) {
		return _svnRevision.compareTo(svnRevision);
	}

	public int hashCode() {
		return _svnRevision.hashCode();
	}

	public java.lang.String toString() {
		return _svnRevision.toString();
	}

	public java.lang.String toXmlString() {
		return _svnRevision.toXmlString();
	}

	public com.liferay.socialcoding.model.SVNRepository getSVNRepository() {
		return _svnRevision.getSVNRepository();
	}

	public java.lang.String getWebRevisionNumberURL() {
		return _svnRevision.getWebRevisionNumberURL();
	}

	public java.lang.Object[] getJIRAIssueAndComments() {
		return _svnRevision.getJIRAIssueAndComments();
	}

	public SVNRevision getWrappedSVNRevision() {
		return _svnRevision;
	}

	private SVNRevision _svnRevision;
}