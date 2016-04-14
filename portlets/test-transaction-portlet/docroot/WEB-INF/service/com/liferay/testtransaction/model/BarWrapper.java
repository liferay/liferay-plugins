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

package com.liferay.testtransaction.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Bar}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Bar
 * @generated
 */
@ProviderType
public class BarWrapper implements Bar, ModelWrapper<Bar> {
	public BarWrapper(Bar bar) {
		_bar = bar;
	}

	@Override
	public Class<?> getModelClass() {
		return Bar.class;
	}

	@Override
	public String getModelClassName() {
		return Bar.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("barId", getBarId());
		attributes.put("text", getText());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long barId = (Long)attributes.get("barId");

		if (barId != null) {
			setBarId(barId);
		}

		String text = (String)attributes.get("text");

		if (text != null) {
			setText(text);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _bar.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _bar.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _bar.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _bar.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<com.liferay.testtransaction.model.Bar> toCacheModel() {
		return _bar.toCacheModel();
	}

	@Override
	public com.liferay.testtransaction.model.Bar toEscapedModel() {
		return new BarWrapper(_bar.toEscapedModel());
	}

	@Override
	public com.liferay.testtransaction.model.Bar toUnescapedModel() {
		return new BarWrapper(_bar.toUnescapedModel());
	}

	@Override
	public int compareTo(com.liferay.testtransaction.model.Bar bar) {
		return _bar.compareTo(bar);
	}

	@Override
	public int hashCode() {
		return _bar.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _bar.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new BarWrapper((Bar)_bar.clone());
	}

	/**
	* Returns the text of this bar.
	*
	* @return the text of this bar
	*/
	@Override
	public java.lang.String getText() {
		return _bar.getText();
	}

	@Override
	public java.lang.String toString() {
		return _bar.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _bar.toXmlString();
	}

	/**
	* Returns the bar ID of this bar.
	*
	* @return the bar ID of this bar
	*/
	@Override
	public long getBarId() {
		return _bar.getBarId();
	}

	/**
	* Returns the primary key of this bar.
	*
	* @return the primary key of this bar
	*/
	@Override
	public long getPrimaryKey() {
		return _bar.getPrimaryKey();
	}

	@Override
	public void persist() {
		_bar.persist();
	}

	/**
	* Sets the bar ID of this bar.
	*
	* @param barId the bar ID of this bar
	*/
	@Override
	public void setBarId(long barId) {
		_bar.setBarId(barId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_bar.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_bar.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_bar.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_bar.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_bar.setNew(n);
	}

	/**
	* Sets the primary key of this bar.
	*
	* @param primaryKey the primary key of this bar
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_bar.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_bar.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the text of this bar.
	*
	* @param text the text of this bar
	*/
	@Override
	public void setText(java.lang.String text) {
		_bar.setText(text);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BarWrapper)) {
			return false;
		}

		BarWrapper barWrapper = (BarWrapper)obj;

		if (Validator.equals(_bar, barWrapper._bar)) {
			return true;
		}

		return false;
	}

	@Override
	public Bar getWrappedModel() {
		return _bar;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _bar.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _bar.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_bar.resetOriginalValues();
	}

	private final Bar _bar;
}