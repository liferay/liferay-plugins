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
 * <a href="SVNRepositoryWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class SVNRepositoryWrapper implements SVNRepository {
	public SVNRepositoryWrapper(SVNRepository svnRepository) {
		_svnRepository = svnRepository;
	}

	public long getPrimaryKey() {
		return _svnRepository.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_svnRepository.setPrimaryKey(pk);
	}

	public long getSvnRepositoryId() {
		return _svnRepository.getSvnRepositoryId();
	}

	public void setSvnRepositoryId(long svnRepositoryId) {
		_svnRepository.setSvnRepositoryId(svnRepositoryId);
	}

	public java.lang.String getUrl() {
		return _svnRepository.getUrl();
	}

	public void setUrl(java.lang.String url) {
		_svnRepository.setUrl(url);
	}

	public long getRevisionNumber() {
		return _svnRepository.getRevisionNumber();
	}

	public void setRevisionNumber(long revisionNumber) {
		_svnRepository.setRevisionNumber(revisionNumber);
	}

	public com.liferay.socialcoding.model.SVNRepository toEscapedModel() {
		return _svnRepository.toEscapedModel();
	}

	public boolean isNew() {
		return _svnRepository.isNew();
	}

	public boolean setNew(boolean n) {
		return _svnRepository.setNew(n);
	}

	public boolean isCachedModel() {
		return _svnRepository.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_svnRepository.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _svnRepository.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_svnRepository.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _svnRepository.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _svnRepository.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_svnRepository.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _svnRepository.clone();
	}

	public int compareTo(
		com.liferay.socialcoding.model.SVNRepository svnRepository) {
		return _svnRepository.compareTo(svnRepository);
	}

	public int hashCode() {
		return _svnRepository.hashCode();
	}

	public java.lang.String toString() {
		return _svnRepository.toString();
	}

	public java.lang.String toXmlString() {
		return _svnRepository.toXmlString();
	}

	public java.lang.String getName() {
		return _svnRepository.getName();
	}

	public java.lang.String getShortURL() {
		return _svnRepository.getShortURL();
	}

	public SVNRepository getWrappedSVNRepository() {
		return _svnRepository;
	}

	private SVNRepository _svnRepository;
}