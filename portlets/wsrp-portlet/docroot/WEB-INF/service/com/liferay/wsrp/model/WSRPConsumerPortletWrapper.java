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

package com.liferay.wsrp.model;

/**
 * <a href="WSRPConsumerPortletWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WSRPConsumerPortletWrapper implements WSRPConsumerPortlet {
	public WSRPConsumerPortletWrapper(WSRPConsumerPortlet wsrpConsumerPortlet) {
		_wsrpConsumerPortlet = wsrpConsumerPortlet;
	}

	public long getPrimaryKey() {
		return _wsrpConsumerPortlet.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_wsrpConsumerPortlet.setPrimaryKey(pk);
	}

	public long getWsrpConsumerPortletId() {
		return _wsrpConsumerPortlet.getWsrpConsumerPortletId();
	}

	public void setWsrpConsumerPortletId(long wsrpConsumerPortletId) {
		_wsrpConsumerPortlet.setWsrpConsumerPortletId(wsrpConsumerPortletId);
	}

	public long getCompanyId() {
		return _wsrpConsumerPortlet.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_wsrpConsumerPortlet.setCompanyId(companyId);
	}

	public java.util.Date getCreateDate() {
		return _wsrpConsumerPortlet.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_wsrpConsumerPortlet.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _wsrpConsumerPortlet.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_wsrpConsumerPortlet.setModifiedDate(modifiedDate);
	}

	public long getWsrpConsumerId() {
		return _wsrpConsumerPortlet.getWsrpConsumerId();
	}

	public void setWsrpConsumerId(long wsrpConsumerId) {
		_wsrpConsumerPortlet.setWsrpConsumerId(wsrpConsumerId);
	}

	public java.lang.String getName() {
		return _wsrpConsumerPortlet.getName();
	}

	public void setName(java.lang.String name) {
		_wsrpConsumerPortlet.setName(name);
	}

	public java.lang.String getPortletHandle() {
		return _wsrpConsumerPortlet.getPortletHandle();
	}

	public void setPortletHandle(java.lang.String portletHandle) {
		_wsrpConsumerPortlet.setPortletHandle(portletHandle);
	}

	public com.liferay.wsrp.model.WSRPConsumerPortlet toEscapedModel() {
		return _wsrpConsumerPortlet.toEscapedModel();
	}

	public boolean isNew() {
		return _wsrpConsumerPortlet.isNew();
	}

	public boolean setNew(boolean n) {
		return _wsrpConsumerPortlet.setNew(n);
	}

	public boolean isCachedModel() {
		return _wsrpConsumerPortlet.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_wsrpConsumerPortlet.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _wsrpConsumerPortlet.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_wsrpConsumerPortlet.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _wsrpConsumerPortlet.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _wsrpConsumerPortlet.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_wsrpConsumerPortlet.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _wsrpConsumerPortlet.clone();
	}

	public int compareTo(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet) {
		return _wsrpConsumerPortlet.compareTo(wsrpConsumerPortlet);
	}

	public int hashCode() {
		return _wsrpConsumerPortlet.hashCode();
	}

	public java.lang.String toString() {
		return _wsrpConsumerPortlet.toString();
	}

	public java.lang.String toXmlString() {
		return _wsrpConsumerPortlet.toXmlString();
	}

	public WSRPConsumerPortlet getWrappedWSRPConsumerPortlet() {
		return _wsrpConsumerPortlet;
	}

	private WSRPConsumerPortlet _wsrpConsumerPortlet;
}