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

package com.liferay.gadgets.model;

/**
 * <a href="GadgetsEntryWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class GadgetsEntryWrapper implements GadgetsEntry {
	public GadgetsEntryWrapper(GadgetsEntry gadgetsEntry) {
		_gadgetsEntry = gadgetsEntry;
	}

	public long getPrimaryKey() {
		return _gadgetsEntry.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_gadgetsEntry.setPrimaryKey(pk);
	}

	public long getGadgetsEntryId() {
		return _gadgetsEntry.getGadgetsEntryId();
	}

	public void setGadgetsEntryId(long gadgetsEntryId) {
		_gadgetsEntry.setGadgetsEntryId(gadgetsEntryId);
	}

	public long getCompanyId() {
		return _gadgetsEntry.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_gadgetsEntry.setCompanyId(companyId);
	}

	public java.util.Date getCreateDate() {
		return _gadgetsEntry.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_gadgetsEntry.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _gadgetsEntry.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_gadgetsEntry.setModifiedDate(modifiedDate);
	}

	public java.lang.String getName() {
		return _gadgetsEntry.getName();
	}

	public void setName(java.lang.String name) {
		_gadgetsEntry.setName(name);
	}

	public java.lang.String getUrl() {
		return _gadgetsEntry.getUrl();
	}

	public void setUrl(java.lang.String url) {
		_gadgetsEntry.setUrl(url);
	}

	public java.lang.String getXml() {
		return _gadgetsEntry.getXml();
	}

	public void setXml(java.lang.String xml) {
		_gadgetsEntry.setXml(xml);
	}

	public com.liferay.gadgets.model.GadgetsEntry toEscapedModel() {
		return _gadgetsEntry.toEscapedModel();
	}

	public boolean isNew() {
		return _gadgetsEntry.isNew();
	}

	public boolean setNew(boolean n) {
		return _gadgetsEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _gadgetsEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_gadgetsEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _gadgetsEntry.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_gadgetsEntry.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _gadgetsEntry.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _gadgetsEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_gadgetsEntry.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _gadgetsEntry.clone();
	}

	public int compareTo(com.liferay.gadgets.model.GadgetsEntry gadgetsEntry) {
		return _gadgetsEntry.compareTo(gadgetsEntry);
	}

	public int hashCode() {
		return _gadgetsEntry.hashCode();
	}

	public java.lang.String toString() {
		return _gadgetsEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _gadgetsEntry.toXmlString();
	}

	public GadgetsEntry getWrappedGadgetsEntry() {
		return _gadgetsEntry;
	}

	private GadgetsEntry _gadgetsEntry;
}