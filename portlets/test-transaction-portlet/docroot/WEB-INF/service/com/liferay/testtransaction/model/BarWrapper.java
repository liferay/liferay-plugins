/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

/**
 * <p>
 * This class is a wrapper for {@link Bar}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Bar
 * @generated
 */
public class BarWrapper implements Bar {
	public BarWrapper(Bar bar) {
		_bar = bar;
	}

	/**
	* Gets the primary key of this bar.
	*
	* @return the primary key of this bar
	*/
	public long getPrimaryKey() {
		return _bar.getPrimaryKey();
	}

	/**
	* Sets the primary key of this bar
	*
	* @param pk the primary key of this bar
	*/
	public void setPrimaryKey(long pk) {
		_bar.setPrimaryKey(pk);
	}

	/**
	* Gets the bar ID of this bar.
	*
	* @return the bar ID of this bar
	*/
	public long getBarId() {
		return _bar.getBarId();
	}

	/**
	* Sets the bar ID of this bar.
	*
	* @param barId the bar ID of this bar
	*/
	public void setBarId(long barId) {
		_bar.setBarId(barId);
	}

	/**
	* Gets the text of this bar.
	*
	* @return the text of this bar
	*/
	public java.lang.String getText() {
		return _bar.getText();
	}

	/**
	* Sets the text of this bar.
	*
	* @param text the text of this bar
	*/
	public void setText(java.lang.String text) {
		_bar.setText(text);
	}

	public boolean isNew() {
		return _bar.isNew();
	}

	public void setNew(boolean n) {
		_bar.setNew(n);
	}

	public boolean isCachedModel() {
		return _bar.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_bar.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _bar.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_bar.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _bar.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _bar.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_bar.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new BarWrapper((Bar)_bar.clone());
	}

	public int compareTo(com.liferay.testtransaction.model.Bar bar) {
		return _bar.compareTo(bar);
	}

	public int hashCode() {
		return _bar.hashCode();
	}

	public com.liferay.testtransaction.model.Bar toEscapedModel() {
		return new BarWrapper(_bar.toEscapedModel());
	}

	public java.lang.String toString() {
		return _bar.toString();
	}

	public java.lang.String toXmlString() {
		return _bar.toXmlString();
	}

	public Bar getWrappedBar() {
		return _bar;
	}

	private Bar _bar;
}