/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.socialnetworking.model;

/**
 * <a href="WallEntryWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WallEntryWrapper implements WallEntry {
	public WallEntryWrapper(WallEntry wallEntry) {
		_wallEntry = wallEntry;
	}

	public long getPrimaryKey() {
		return _wallEntry.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_wallEntry.setPrimaryKey(pk);
	}

	public long getWallEntryId() {
		return _wallEntry.getWallEntryId();
	}

	public void setWallEntryId(long wallEntryId) {
		_wallEntry.setWallEntryId(wallEntryId);
	}

	public long getGroupId() {
		return _wallEntry.getGroupId();
	}

	public void setGroupId(long groupId) {
		_wallEntry.setGroupId(groupId);
	}

	public long getCompanyId() {
		return _wallEntry.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_wallEntry.setCompanyId(companyId);
	}

	public long getUserId() {
		return _wallEntry.getUserId();
	}

	public void setUserId(long userId) {
		_wallEntry.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.SystemException {
		return _wallEntry.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_wallEntry.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _wallEntry.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_wallEntry.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _wallEntry.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_wallEntry.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _wallEntry.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_wallEntry.setModifiedDate(modifiedDate);
	}

	public java.lang.String getComments() {
		return _wallEntry.getComments();
	}

	public void setComments(java.lang.String comments) {
		_wallEntry.setComments(comments);
	}

	public com.liferay.socialnetworking.model.WallEntry toEscapedModel() {
		return _wallEntry.toEscapedModel();
	}

	public boolean isNew() {
		return _wallEntry.isNew();
	}

	public boolean setNew(boolean n) {
		return _wallEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _wallEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_wallEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _wallEntry.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_wallEntry.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _wallEntry.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _wallEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_wallEntry.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _wallEntry.clone();
	}

	public int compareTo(com.liferay.socialnetworking.model.WallEntry wallEntry) {
		return _wallEntry.compareTo(wallEntry);
	}

	public int hashCode() {
		return _wallEntry.hashCode();
	}

	public java.lang.String toString() {
		return _wallEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _wallEntry.toXmlString();
	}

	public WallEntry getWrappedWallEntry() {
		return _wallEntry;
	}

	private WallEntry _wallEntry;
}