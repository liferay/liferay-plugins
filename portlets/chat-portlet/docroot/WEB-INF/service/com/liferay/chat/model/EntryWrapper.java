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

package com.liferay.chat.model;

/**
 * <a href="EntryWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class EntryWrapper implements Entry {
	public EntryWrapper(Entry entry) {
		_entry = entry;
	}

	public long getPrimaryKey() {
		return _entry.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_entry.setPrimaryKey(pk);
	}

	public long getEntryId() {
		return _entry.getEntryId();
	}

	public void setEntryId(long entryId) {
		_entry.setEntryId(entryId);
	}

	public long getCreateDate() {
		return _entry.getCreateDate();
	}

	public void setCreateDate(long createDate) {
		_entry.setCreateDate(createDate);
	}

	public long getFromUserId() {
		return _entry.getFromUserId();
	}

	public void setFromUserId(long fromUserId) {
		_entry.setFromUserId(fromUserId);
	}

	public java.lang.String getFromUserUuid()
		throws com.liferay.portal.SystemException {
		return _entry.getFromUserUuid();
	}

	public void setFromUserUuid(java.lang.String fromUserUuid) {
		_entry.setFromUserUuid(fromUserUuid);
	}

	public long getToUserId() {
		return _entry.getToUserId();
	}

	public void setToUserId(long toUserId) {
		_entry.setToUserId(toUserId);
	}

	public java.lang.String getToUserUuid()
		throws com.liferay.portal.SystemException {
		return _entry.getToUserUuid();
	}

	public void setToUserUuid(java.lang.String toUserUuid) {
		_entry.setToUserUuid(toUserUuid);
	}

	public java.lang.String getContent() {
		return _entry.getContent();
	}

	public void setContent(java.lang.String content) {
		_entry.setContent(content);
	}

	public com.liferay.chat.model.Entry toEscapedModel() {
		return _entry.toEscapedModel();
	}

	public boolean isNew() {
		return _entry.isNew();
	}

	public boolean setNew(boolean n) {
		return _entry.setNew(n);
	}

	public boolean isCachedModel() {
		return _entry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_entry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _entry.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_entry.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _entry.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _entry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_entry.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _entry.clone();
	}

	public int compareTo(com.liferay.chat.model.Entry entry) {
		return _entry.compareTo(entry);
	}

	public int hashCode() {
		return _entry.hashCode();
	}

	public java.lang.String toString() {
		return _entry.toString();
	}

	public java.lang.String toXmlString() {
		return _entry.toXmlString();
	}

	private Entry _entry;
}