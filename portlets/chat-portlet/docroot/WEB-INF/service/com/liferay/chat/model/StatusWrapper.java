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
 * <a href="StatusWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class StatusWrapper implements Status {
	public StatusWrapper(Status status) {
		_status = status;
	}

	public long getPrimaryKey() {
		return _status.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_status.setPrimaryKey(pk);
	}

	public long getStatusId() {
		return _status.getStatusId();
	}

	public void setStatusId(long statusId) {
		_status.setStatusId(statusId);
	}

	public long getUserId() {
		return _status.getUserId();
	}

	public void setUserId(long userId) {
		_status.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.SystemException {
		return _status.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_status.setUserUuid(userUuid);
	}

	public long getModifiedDate() {
		return _status.getModifiedDate();
	}

	public void setModifiedDate(long modifiedDate) {
		_status.setModifiedDate(modifiedDate);
	}

	public boolean getOnline() {
		return _status.getOnline();
	}

	public boolean isOnline() {
		return _status.isOnline();
	}

	public void setOnline(boolean online) {
		_status.setOnline(online);
	}

	public boolean getAwake() {
		return _status.getAwake();
	}

	public boolean isAwake() {
		return _status.isAwake();
	}

	public void setAwake(boolean awake) {
		_status.setAwake(awake);
	}

	public java.lang.String getActivePanelId() {
		return _status.getActivePanelId();
	}

	public void setActivePanelId(java.lang.String activePanelId) {
		_status.setActivePanelId(activePanelId);
	}

	public java.lang.String getMessage() {
		return _status.getMessage();
	}

	public void setMessage(java.lang.String message) {
		_status.setMessage(message);
	}

	public boolean getPlaySound() {
		return _status.getPlaySound();
	}

	public boolean isPlaySound() {
		return _status.isPlaySound();
	}

	public void setPlaySound(boolean playSound) {
		_status.setPlaySound(playSound);
	}

	public com.liferay.chat.model.Status toEscapedModel() {
		return _status.toEscapedModel();
	}

	public boolean isNew() {
		return _status.isNew();
	}

	public boolean setNew(boolean n) {
		return _status.setNew(n);
	}

	public boolean isCachedModel() {
		return _status.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_status.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _status.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_status.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _status.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _status.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_status.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _status.clone();
	}

	public int compareTo(com.liferay.chat.model.Status status) {
		return _status.compareTo(status);
	}

	public int hashCode() {
		return _status.hashCode();
	}

	public java.lang.String toString() {
		return _status.toString();
	}

	public java.lang.String toXmlString() {
		return _status.toXmlString();
	}

	public Status getWrappedStatus() {
		return _status;
	}

	private Status _status;
}