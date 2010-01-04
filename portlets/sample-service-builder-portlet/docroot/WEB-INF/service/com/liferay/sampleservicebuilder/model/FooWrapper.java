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

package com.liferay.sampleservicebuilder.model;

/**
 * <a href="FooWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class FooWrapper implements Foo {
	public FooWrapper(Foo foo) {
		_foo = foo;
	}

	public long getPrimaryKey() {
		return _foo.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_foo.setPrimaryKey(pk);
	}

	public long getFooId() {
		return _foo.getFooId();
	}

	public void setFooId(long fooId) {
		_foo.setFooId(fooId);
	}

	public long getCompanyId() {
		return _foo.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_foo.setCompanyId(companyId);
	}

	public long getUserId() {
		return _foo.getUserId();
	}

	public void setUserId(long userId) {
		_foo.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.SystemException {
		return _foo.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_foo.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _foo.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_foo.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _foo.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_foo.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _foo.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_foo.setModifiedDate(modifiedDate);
	}

	public java.lang.String getField1() {
		return _foo.getField1();
	}

	public void setField1(java.lang.String field1) {
		_foo.setField1(field1);
	}

	public boolean getField2() {
		return _foo.getField2();
	}

	public boolean isField2() {
		return _foo.isField2();
	}

	public void setField2(boolean field2) {
		_foo.setField2(field2);
	}

	public int getField3() {
		return _foo.getField3();
	}

	public void setField3(int field3) {
		_foo.setField3(field3);
	}

	public java.util.Date getField4() {
		return _foo.getField4();
	}

	public void setField4(java.util.Date field4) {
		_foo.setField4(field4);
	}

	public java.lang.String getField5() {
		return _foo.getField5();
	}

	public void setField5(java.lang.String field5) {
		_foo.setField5(field5);
	}

	public com.liferay.sampleservicebuilder.model.Foo toEscapedModel() {
		return _foo.toEscapedModel();
	}

	public boolean isNew() {
		return _foo.isNew();
	}

	public boolean setNew(boolean n) {
		return _foo.setNew(n);
	}

	public boolean isCachedModel() {
		return _foo.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_foo.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _foo.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_foo.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _foo.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _foo.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_foo.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _foo.clone();
	}

	public int compareTo(com.liferay.sampleservicebuilder.model.Foo foo) {
		return _foo.compareTo(foo);
	}

	public int hashCode() {
		return _foo.hashCode();
	}

	public java.lang.String toString() {
		return _foo.toString();
	}

	public java.lang.String toXmlString() {
		return _foo.toXmlString();
	}

	public Foo getWrappedFoo() {
		return _foo;
	}

	private Foo _foo;
}