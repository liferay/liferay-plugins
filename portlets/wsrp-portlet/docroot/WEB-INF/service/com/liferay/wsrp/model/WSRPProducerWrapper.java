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
 * <a href="WSRPProducerWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WSRPProducerWrapper implements WSRPProducer {
	public WSRPProducerWrapper(WSRPProducer wsrpProducer) {
		_wsrpProducer = wsrpProducer;
	}

	public long getPrimaryKey() {
		return _wsrpProducer.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_wsrpProducer.setPrimaryKey(pk);
	}

	public long getWsrpProducerId() {
		return _wsrpProducer.getWsrpProducerId();
	}

	public void setWsrpProducerId(long wsrpProducerId) {
		_wsrpProducer.setWsrpProducerId(wsrpProducerId);
	}

	public long getCompanyId() {
		return _wsrpProducer.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_wsrpProducer.setCompanyId(companyId);
	}

	public java.util.Date getCreateDate() {
		return _wsrpProducer.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_wsrpProducer.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _wsrpProducer.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_wsrpProducer.setModifiedDate(modifiedDate);
	}

	public java.lang.String getName() {
		return _wsrpProducer.getName();
	}

	public void setName(java.lang.String name) {
		_wsrpProducer.setName(name);
	}

	public java.lang.String getPortletIds() {
		return _wsrpProducer.getPortletIds();
	}

	public void setPortletIds(java.lang.String portletIds) {
		_wsrpProducer.setPortletIds(portletIds);
	}

	public com.liferay.wsrp.model.WSRPProducer toEscapedModel() {
		return _wsrpProducer.toEscapedModel();
	}

	public boolean isNew() {
		return _wsrpProducer.isNew();
	}

	public boolean setNew(boolean n) {
		return _wsrpProducer.setNew(n);
	}

	public boolean isCachedModel() {
		return _wsrpProducer.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_wsrpProducer.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _wsrpProducer.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_wsrpProducer.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _wsrpProducer.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _wsrpProducer.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_wsrpProducer.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _wsrpProducer.clone();
	}

	public int compareTo(com.liferay.wsrp.model.WSRPProducer wsrpProducer) {
		return _wsrpProducer.compareTo(wsrpProducer);
	}

	public int hashCode() {
		return _wsrpProducer.hashCode();
	}

	public java.lang.String toString() {
		return _wsrpProducer.toString();
	}

	public java.lang.String toXmlString() {
		return _wsrpProducer.toXmlString();
	}

	public WSRPProducer getWrappedWSRPProducer() {
		return _wsrpProducer;
	}

	private WSRPProducer _wsrpProducer;
}