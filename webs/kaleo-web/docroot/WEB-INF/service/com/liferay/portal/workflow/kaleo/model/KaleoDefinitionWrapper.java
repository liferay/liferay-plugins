/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.model;

/**
 * <p>
 * This class is a wrapper for {@link KaleoDefinition}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoDefinition
 * @generated
 */
public class KaleoDefinitionWrapper implements KaleoDefinition {
	public KaleoDefinitionWrapper(KaleoDefinition kaleoDefinition) {
		_kaleoDefinition = kaleoDefinition;
	}

	/**
	* Gets the primary key of this kaleo definition.
	*
	* @return the primary key of this kaleo definition
	*/
	public long getPrimaryKey() {
		return _kaleoDefinition.getPrimaryKey();
	}

	/**
	* Sets the primary key of this kaleo definition
	*
	* @param pk the primary key of this kaleo definition
	*/
	public void setPrimaryKey(long pk) {
		_kaleoDefinition.setPrimaryKey(pk);
	}

	/**
	* Gets the kaleo definition id of this kaleo definition.
	*
	* @return the kaleo definition id of this kaleo definition
	*/
	public long getKaleoDefinitionId() {
		return _kaleoDefinition.getKaleoDefinitionId();
	}

	/**
	* Sets the kaleo definition id of this kaleo definition.
	*
	* @param kaleoDefinitionId the kaleo definition id of this kaleo definition
	*/
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoDefinition.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Gets the group id of this kaleo definition.
	*
	* @return the group id of this kaleo definition
	*/
	public long getGroupId() {
		return _kaleoDefinition.getGroupId();
	}

	/**
	* Sets the group id of this kaleo definition.
	*
	* @param groupId the group id of this kaleo definition
	*/
	public void setGroupId(long groupId) {
		_kaleoDefinition.setGroupId(groupId);
	}

	/**
	* Gets the company id of this kaleo definition.
	*
	* @return the company id of this kaleo definition
	*/
	public long getCompanyId() {
		return _kaleoDefinition.getCompanyId();
	}

	/**
	* Sets the company id of this kaleo definition.
	*
	* @param companyId the company id of this kaleo definition
	*/
	public void setCompanyId(long companyId) {
		_kaleoDefinition.setCompanyId(companyId);
	}

	/**
	* Gets the user id of this kaleo definition.
	*
	* @return the user id of this kaleo definition
	*/
	public long getUserId() {
		return _kaleoDefinition.getUserId();
	}

	/**
	* Sets the user id of this kaleo definition.
	*
	* @param userId the user id of this kaleo definition
	*/
	public void setUserId(long userId) {
		_kaleoDefinition.setUserId(userId);
	}

	/**
	* Gets the user uuid of this kaleo definition.
	*
	* @return the user uuid of this kaleo definition
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinition.getUserUuid();
	}

	/**
	* Sets the user uuid of this kaleo definition.
	*
	* @param userUuid the user uuid of this kaleo definition
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoDefinition.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this kaleo definition.
	*
	* @return the user name of this kaleo definition
	*/
	public java.lang.String getUserName() {
		return _kaleoDefinition.getUserName();
	}

	/**
	* Sets the user name of this kaleo definition.
	*
	* @param userName the user name of this kaleo definition
	*/
	public void setUserName(java.lang.String userName) {
		_kaleoDefinition.setUserName(userName);
	}

	/**
	* Gets the create date of this kaleo definition.
	*
	* @return the create date of this kaleo definition
	*/
	public java.util.Date getCreateDate() {
		return _kaleoDefinition.getCreateDate();
	}

	/**
	* Sets the create date of this kaleo definition.
	*
	* @param createDate the create date of this kaleo definition
	*/
	public void setCreateDate(java.util.Date createDate) {
		_kaleoDefinition.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this kaleo definition.
	*
	* @return the modified date of this kaleo definition
	*/
	public java.util.Date getModifiedDate() {
		return _kaleoDefinition.getModifiedDate();
	}

	/**
	* Sets the modified date of this kaleo definition.
	*
	* @param modifiedDate the modified date of this kaleo definition
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoDefinition.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the name of this kaleo definition.
	*
	* @return the name of this kaleo definition
	*/
	public java.lang.String getName() {
		return _kaleoDefinition.getName();
	}

	/**
	* Sets the name of this kaleo definition.
	*
	* @param name the name of this kaleo definition
	*/
	public void setName(java.lang.String name) {
		_kaleoDefinition.setName(name);
	}

	/**
	* Gets the title of this kaleo definition.
	*
	* @return the title of this kaleo definition
	*/
	public java.lang.String getTitle() {
		return _kaleoDefinition.getTitle();
	}

	/**
	* Gets the localized title of this kaleo definition. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale to get the localized title for
	* @return the localized title of this kaleo definition
	*/
	public java.lang.String getTitle(java.util.Locale locale) {
		return _kaleoDefinition.getTitle(locale);
	}

	/**
	* Gets the localized title of this kaleo definition, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local to get the localized title for
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this kaleo definition. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _kaleoDefinition.getTitle(locale, useDefault);
	}

	/**
	* Gets the localized title of this kaleo definition. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the id of the language to get the localized title for
	* @return the localized title of this kaleo definition
	*/
	public java.lang.String getTitle(java.lang.String languageId) {
		return _kaleoDefinition.getTitle(languageId);
	}

	/**
	* Gets the localized title of this kaleo definition, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the id of the language to get the localized title for
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this kaleo definition
	*/
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _kaleoDefinition.getTitle(languageId, useDefault);
	}

	/**
	* Gets a map of the locales and localized title of this kaleo definition.
	*
	* @return the locales and localized title
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _kaleoDefinition.getTitleMap();
	}

	/**
	* Sets the title of this kaleo definition.
	*
	* @param title the title of this kaleo definition
	*/
	public void setTitle(java.lang.String title) {
		_kaleoDefinition.setTitle(title);
	}

	/**
	* Sets the localized title of this kaleo definition.
	*
	* @param locale the locale to set the localized title for
	* @param title the localized title of this kaleo definition
	*/
	public void setTitle(java.util.Locale locale, java.lang.String title) {
		_kaleoDefinition.setTitle(locale, title);
	}

	/**
	* Sets the localized titles of this kaleo definition from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this kaleo definition
	*/
	public void setTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> titleMap) {
		_kaleoDefinition.setTitleMap(titleMap);
	}

	/**
	* Gets the description of this kaleo definition.
	*
	* @return the description of this kaleo definition
	*/
	public java.lang.String getDescription() {
		return _kaleoDefinition.getDescription();
	}

	/**
	* Sets the description of this kaleo definition.
	*
	* @param description the description of this kaleo definition
	*/
	public void setDescription(java.lang.String description) {
		_kaleoDefinition.setDescription(description);
	}

	/**
	* Gets the version of this kaleo definition.
	*
	* @return the version of this kaleo definition
	*/
	public int getVersion() {
		return _kaleoDefinition.getVersion();
	}

	/**
	* Sets the version of this kaleo definition.
	*
	* @param version the version of this kaleo definition
	*/
	public void setVersion(int version) {
		_kaleoDefinition.setVersion(version);
	}

	/**
	* Gets the active of this kaleo definition.
	*
	* @return the active of this kaleo definition
	*/
	public boolean getActive() {
		return _kaleoDefinition.getActive();
	}

	/**
	* Determines if this kaleo definition is active.
	*
	* @return <code>true</code> if this kaleo definition is active; <code>false</code> otherwise
	*/
	public boolean isActive() {
		return _kaleoDefinition.isActive();
	}

	/**
	* Sets whether this kaleo definition is active.
	*
	* @param active the active of this kaleo definition
	*/
	public void setActive(boolean active) {
		_kaleoDefinition.setActive(active);
	}

	/**
	* Gets the start kaleo node id of this kaleo definition.
	*
	* @return the start kaleo node id of this kaleo definition
	*/
	public long getStartKaleoNodeId() {
		return _kaleoDefinition.getStartKaleoNodeId();
	}

	/**
	* Sets the start kaleo node id of this kaleo definition.
	*
	* @param startKaleoNodeId the start kaleo node id of this kaleo definition
	*/
	public void setStartKaleoNodeId(long startKaleoNodeId) {
		_kaleoDefinition.setStartKaleoNodeId(startKaleoNodeId);
	}

	public boolean isNew() {
		return _kaleoDefinition.isNew();
	}

	public void setNew(boolean n) {
		_kaleoDefinition.setNew(n);
	}

	public boolean isCachedModel() {
		return _kaleoDefinition.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kaleoDefinition.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kaleoDefinition.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_kaleoDefinition.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoDefinition.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoDefinition.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoDefinition.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new KaleoDefinitionWrapper((KaleoDefinition)_kaleoDefinition.clone());
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoDefinition kaleoDefinition) {
		return _kaleoDefinition.compareTo(kaleoDefinition);
	}

	public int hashCode() {
		return _kaleoDefinition.hashCode();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition toEscapedModel() {
		return new KaleoDefinitionWrapper(_kaleoDefinition.toEscapedModel());
	}

	public java.lang.String toString() {
		return _kaleoDefinition.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoDefinition.toXmlString();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNode getKaleoStartNode()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinition.getKaleoStartNode();
	}

	public boolean hasIncompleteKaleoInstances()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinition.hasIncompleteKaleoInstances();
	}

	public KaleoDefinition getWrappedKaleoDefinition() {
		return _kaleoDefinition;
	}

	private KaleoDefinition _kaleoDefinition;
}