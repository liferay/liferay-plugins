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

package com.liferay.socialnetworking.model;

/**
 * <a href="MeetupsEntryWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class MeetupsEntryWrapper implements MeetupsEntry {
	public MeetupsEntryWrapper(MeetupsEntry meetupsEntry) {
		_meetupsEntry = meetupsEntry;
	}

	public long getPrimaryKey() {
		return _meetupsEntry.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_meetupsEntry.setPrimaryKey(pk);
	}

	public long getMeetupsEntryId() {
		return _meetupsEntry.getMeetupsEntryId();
	}

	public void setMeetupsEntryId(long meetupsEntryId) {
		_meetupsEntry.setMeetupsEntryId(meetupsEntryId);
	}

	public long getCompanyId() {
		return _meetupsEntry.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_meetupsEntry.setCompanyId(companyId);
	}

	public long getUserId() {
		return _meetupsEntry.getUserId();
	}

	public void setUserId(long userId) {
		_meetupsEntry.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.SystemException {
		return _meetupsEntry.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_meetupsEntry.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _meetupsEntry.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_meetupsEntry.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _meetupsEntry.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_meetupsEntry.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _meetupsEntry.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_meetupsEntry.setModifiedDate(modifiedDate);
	}

	public java.lang.String getTitle() {
		return _meetupsEntry.getTitle();
	}

	public void setTitle(java.lang.String title) {
		_meetupsEntry.setTitle(title);
	}

	public java.lang.String getDescription() {
		return _meetupsEntry.getDescription();
	}

	public void setDescription(java.lang.String description) {
		_meetupsEntry.setDescription(description);
	}

	public java.util.Date getStartDate() {
		return _meetupsEntry.getStartDate();
	}

	public void setStartDate(java.util.Date startDate) {
		_meetupsEntry.setStartDate(startDate);
	}

	public java.util.Date getEndDate() {
		return _meetupsEntry.getEndDate();
	}

	public void setEndDate(java.util.Date endDate) {
		_meetupsEntry.setEndDate(endDate);
	}

	public int getTotalAttendees() {
		return _meetupsEntry.getTotalAttendees();
	}

	public void setTotalAttendees(int totalAttendees) {
		_meetupsEntry.setTotalAttendees(totalAttendees);
	}

	public int getMaxAttendees() {
		return _meetupsEntry.getMaxAttendees();
	}

	public void setMaxAttendees(int maxAttendees) {
		_meetupsEntry.setMaxAttendees(maxAttendees);
	}

	public double getPrice() {
		return _meetupsEntry.getPrice();
	}

	public void setPrice(double price) {
		_meetupsEntry.setPrice(price);
	}

	public long getThumbnailId() {
		return _meetupsEntry.getThumbnailId();
	}

	public void setThumbnailId(long thumbnailId) {
		_meetupsEntry.setThumbnailId(thumbnailId);
	}

	public com.liferay.socialnetworking.model.MeetupsEntry toEscapedModel() {
		return _meetupsEntry.toEscapedModel();
	}

	public boolean isNew() {
		return _meetupsEntry.isNew();
	}

	public boolean setNew(boolean n) {
		return _meetupsEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _meetupsEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_meetupsEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _meetupsEntry.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_meetupsEntry.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _meetupsEntry.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _meetupsEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_meetupsEntry.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _meetupsEntry.clone();
	}

	public int compareTo(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry) {
		return _meetupsEntry.compareTo(meetupsEntry);
	}

	public int hashCode() {
		return _meetupsEntry.hashCode();
	}

	public java.lang.String toString() {
		return _meetupsEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _meetupsEntry.toXmlString();
	}

	public MeetupsEntry getWrappedMeetupsEntry() {
		return _meetupsEntry;
	}

	private MeetupsEntry _meetupsEntry;
}