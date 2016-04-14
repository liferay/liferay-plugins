/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.testpacl.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Foo}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Foo
 * @generated
 */
@ProviderType
public class FooWrapper implements Foo, ModelWrapper<Foo> {
	public FooWrapper(Foo foo) {
		_foo = foo;
	}

	@Override
	public Class<?> getModelClass() {
		return Foo.class;
	}

	@Override
	public String getModelClassName() {
		return Foo.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("fooId", getFooId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("field1", getField1());
		attributes.put("field2", getField2());
		attributes.put("field3", getField3());
		attributes.put("field4", getField4());
		attributes.put("field5", getField5());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long fooId = (Long)attributes.get("fooId");

		if (fooId != null) {
			setFooId(fooId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String field1 = (String)attributes.get("field1");

		if (field1 != null) {
			setField1(field1);
		}

		Boolean field2 = (Boolean)attributes.get("field2");

		if (field2 != null) {
			setField2(field2);
		}

		Integer field3 = (Integer)attributes.get("field3");

		if (field3 != null) {
			setField3(field3);
		}

		Date field4 = (Date)attributes.get("field4");

		if (field4 != null) {
			setField4(field4);
		}

		String field5 = (String)attributes.get("field5");

		if (field5 != null) {
			setField5(field5);
		}
	}

	/**
	* Returns the field2 of this foo.
	*
	* @return the field2 of this foo
	*/
	@Override
	public boolean getField2() {
		return _foo.getField2();
	}

	@Override
	public boolean isCachedModel() {
		return _foo.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _foo.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this foo is field2.
	*
	* @return <code>true</code> if this foo is field2; <code>false</code> otherwise
	*/
	@Override
	public boolean isField2() {
		return _foo.isField2();
	}

	@Override
	public boolean isNew() {
		return _foo.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _foo.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<com.liferay.testpacl.model.Foo> toCacheModel() {
		return _foo.toCacheModel();
	}

	@Override
	public com.liferay.testpacl.model.Foo toEscapedModel() {
		return new FooWrapper(_foo.toEscapedModel());
	}

	@Override
	public com.liferay.testpacl.model.Foo toUnescapedModel() {
		return new FooWrapper(_foo.toUnescapedModel());
	}

	@Override
	public int compareTo(com.liferay.testpacl.model.Foo foo) {
		return _foo.compareTo(foo);
	}

	/**
	* Returns the field3 of this foo.
	*
	* @return the field3 of this foo
	*/
	@Override
	public int getField3() {
		return _foo.getField3();
	}

	@Override
	public int hashCode() {
		return _foo.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _foo.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new FooWrapper((Foo)_foo.clone());
	}

	/**
	* Returns the field1 of this foo.
	*
	* @return the field1 of this foo
	*/
	@Override
	public java.lang.String getField1() {
		return _foo.getField1();
	}

	/**
	* Returns the field5 of this foo.
	*
	* @return the field5 of this foo
	*/
	@Override
	public java.lang.String getField5() {
		return _foo.getField5();
	}

	/**
	* Returns the user name of this foo.
	*
	* @return the user name of this foo
	*/
	@Override
	public java.lang.String getUserName() {
		return _foo.getUserName();
	}

	/**
	* Returns the user uuid of this foo.
	*
	* @return the user uuid of this foo
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _foo.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _foo.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _foo.toXmlString();
	}

	/**
	* Returns the create date of this foo.
	*
	* @return the create date of this foo
	*/
	@Override
	public Date getCreateDate() {
		return _foo.getCreateDate();
	}

	/**
	* Returns the field4 of this foo.
	*
	* @return the field4 of this foo
	*/
	@Override
	public Date getField4() {
		return _foo.getField4();
	}

	/**
	* Returns the modified date of this foo.
	*
	* @return the modified date of this foo
	*/
	@Override
	public Date getModifiedDate() {
		return _foo.getModifiedDate();
	}

	/**
	* Returns the company ID of this foo.
	*
	* @return the company ID of this foo
	*/
	@Override
	public long getCompanyId() {
		return _foo.getCompanyId();
	}

	/**
	* Returns the foo ID of this foo.
	*
	* @return the foo ID of this foo
	*/
	@Override
	public long getFooId() {
		return _foo.getFooId();
	}

	/**
	* Returns the group ID of this foo.
	*
	* @return the group ID of this foo
	*/
	@Override
	public long getGroupId() {
		return _foo.getGroupId();
	}

	/**
	* Returns the primary key of this foo.
	*
	* @return the primary key of this foo
	*/
	@Override
	public long getPrimaryKey() {
		return _foo.getPrimaryKey();
	}

	/**
	* Returns the user ID of this foo.
	*
	* @return the user ID of this foo
	*/
	@Override
	public long getUserId() {
		return _foo.getUserId();
	}

	@Override
	public void persist() {
		_foo.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_foo.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this foo.
	*
	* @param companyId the company ID of this foo
	*/
	@Override
	public void setCompanyId(long companyId) {
		_foo.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this foo.
	*
	* @param createDate the create date of this foo
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_foo.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_foo.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_foo.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_foo.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the field1 of this foo.
	*
	* @param field1 the field1 of this foo
	*/
	@Override
	public void setField1(java.lang.String field1) {
		_foo.setField1(field1);
	}

	/**
	* Sets whether this foo is field2.
	*
	* @param field2 the field2 of this foo
	*/
	@Override
	public void setField2(boolean field2) {
		_foo.setField2(field2);
	}

	/**
	* Sets the field3 of this foo.
	*
	* @param field3 the field3 of this foo
	*/
	@Override
	public void setField3(int field3) {
		_foo.setField3(field3);
	}

	/**
	* Sets the field4 of this foo.
	*
	* @param field4 the field4 of this foo
	*/
	@Override
	public void setField4(Date field4) {
		_foo.setField4(field4);
	}

	/**
	* Sets the field5 of this foo.
	*
	* @param field5 the field5 of this foo
	*/
	@Override
	public void setField5(java.lang.String field5) {
		_foo.setField5(field5);
	}

	/**
	* Sets the foo ID of this foo.
	*
	* @param fooId the foo ID of this foo
	*/
	@Override
	public void setFooId(long fooId) {
		_foo.setFooId(fooId);
	}

	/**
	* Sets the group ID of this foo.
	*
	* @param groupId the group ID of this foo
	*/
	@Override
	public void setGroupId(long groupId) {
		_foo.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this foo.
	*
	* @param modifiedDate the modified date of this foo
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_foo.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_foo.setNew(n);
	}

	/**
	* Sets the primary key of this foo.
	*
	* @param primaryKey the primary key of this foo
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_foo.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_foo.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this foo.
	*
	* @param userId the user ID of this foo
	*/
	@Override
	public void setUserId(long userId) {
		_foo.setUserId(userId);
	}

	/**
	* Sets the user name of this foo.
	*
	* @param userName the user name of this foo
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_foo.setUserName(userName);
	}

	/**
	* Sets the user uuid of this foo.
	*
	* @param userUuid the user uuid of this foo
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_foo.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FooWrapper)) {
			return false;
		}

		FooWrapper fooWrapper = (FooWrapper)obj;

		if (Validator.equals(_foo, fooWrapper._foo)) {
			return true;
		}

		return false;
	}

	@Override
	public Foo getWrappedModel() {
		return _foo;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _foo.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _foo.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_foo.resetOriginalValues();
	}

	private final Foo _foo;
}