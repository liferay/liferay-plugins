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

package com.liferay.opensocial.model;

/**
 * <a href="OpenSocialGadgetWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class OpenSocialGadgetWrapper implements OpenSocialGadget {
	public OpenSocialGadgetWrapper(OpenSocialGadget openSocialGadget) {
		_openSocialGadget = openSocialGadget;
	}

	public long getPrimaryKey() {
		return _openSocialGadget.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_openSocialGadget.setPrimaryKey(pk);
	}

	public long getOpenSocialGadgetId() {
		return _openSocialGadget.getOpenSocialGadgetId();
	}

	public void setOpenSocialGadgetId(long openSocialGadgetId) {
		_openSocialGadget.setOpenSocialGadgetId(openSocialGadgetId);
	}

	public long getCompanyId() {
		return _openSocialGadget.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_openSocialGadget.setCompanyId(companyId);
	}

	public java.util.Date getCreateDate() {
		return _openSocialGadget.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_openSocialGadget.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _openSocialGadget.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_openSocialGadget.setModifiedDate(modifiedDate);
	}

	public java.lang.String getName() {
		return _openSocialGadget.getName();
	}

	public void setName(java.lang.String name) {
		_openSocialGadget.setName(name);
	}

	public java.lang.String getUrl() {
		return _openSocialGadget.getUrl();
	}

	public void setUrl(java.lang.String url) {
		_openSocialGadget.setUrl(url);
	}

	public java.lang.String getXml() {
		return _openSocialGadget.getXml();
	}

	public void setXml(java.lang.String xml) {
		_openSocialGadget.setXml(xml);
	}

	public com.liferay.opensocial.model.OpenSocialGadget toEscapedModel() {
		return _openSocialGadget.toEscapedModel();
	}

	public boolean isNew() {
		return _openSocialGadget.isNew();
	}

	public boolean setNew(boolean n) {
		return _openSocialGadget.setNew(n);
	}

	public boolean isCachedModel() {
		return _openSocialGadget.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_openSocialGadget.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _openSocialGadget.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_openSocialGadget.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _openSocialGadget.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _openSocialGadget.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_openSocialGadget.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _openSocialGadget.clone();
	}

	public int compareTo(
		com.liferay.opensocial.model.OpenSocialGadget openSocialGadget) {
		return _openSocialGadget.compareTo(openSocialGadget);
	}

	public int hashCode() {
		return _openSocialGadget.hashCode();
	}

	public java.lang.String toString() {
		return _openSocialGadget.toString();
	}

	public java.lang.String toXmlString() {
		return _openSocialGadget.toXmlString();
	}

	public OpenSocialGadget getWrappedOpenSocialGadget() {
		return _openSocialGadget;
	}

	private OpenSocialGadget _openSocialGadget;
}