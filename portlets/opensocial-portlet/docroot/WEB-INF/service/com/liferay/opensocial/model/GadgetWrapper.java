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

package com.liferay.opensocial.model;

/**
 * <a href="GadgetWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class GadgetWrapper implements Gadget {
	public GadgetWrapper(Gadget gadget) {
		_gadget = gadget;
	}

	public long getPrimaryKey() {
		return _gadget.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_gadget.setPrimaryKey(pk);
	}

	public long getGadgetId() {
		return _gadget.getGadgetId();
	}

	public void setGadgetId(long gadgetId) {
		_gadget.setGadgetId(gadgetId);
	}

	public long getCompanyId() {
		return _gadget.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_gadget.setCompanyId(companyId);
	}

	public java.util.Date getCreateDate() {
		return _gadget.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_gadget.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _gadget.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_gadget.setModifiedDate(modifiedDate);
	}

	public java.lang.String getName() {
		return _gadget.getName();
	}

	public void setName(java.lang.String name) {
		_gadget.setName(name);
	}

	public java.lang.String getUrl() {
		return _gadget.getUrl();
	}

	public void setUrl(java.lang.String url) {
		_gadget.setUrl(url);
	}

	public java.lang.String getXml() {
		return _gadget.getXml();
	}

	public void setXml(java.lang.String xml) {
		_gadget.setXml(xml);
	}

	public com.liferay.opensocial.model.Gadget toEscapedModel() {
		return _gadget.toEscapedModel();
	}

	public boolean isNew() {
		return _gadget.isNew();
	}

	public boolean setNew(boolean n) {
		return _gadget.setNew(n);
	}

	public boolean isCachedModel() {
		return _gadget.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_gadget.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _gadget.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_gadget.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _gadget.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _gadget.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_gadget.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _gadget.clone();
	}

	public int compareTo(com.liferay.opensocial.model.Gadget gadget) {
		return _gadget.compareTo(gadget);
	}

	public int hashCode() {
		return _gadget.hashCode();
	}

	public java.lang.String toString() {
		return _gadget.toString();
	}

	public java.lang.String toXmlString() {
		return _gadget.toXmlString();
	}

	public Gadget getWrappedGadget() {
		return _gadget;
	}

	private Gadget _gadget;
}