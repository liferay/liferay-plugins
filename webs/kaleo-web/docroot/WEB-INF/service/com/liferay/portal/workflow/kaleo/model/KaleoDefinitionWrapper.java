/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link KaleoDefinition}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoDefinition
 * @generated
 */
public class KaleoDefinitionWrapper implements KaleoDefinition,
	ModelWrapper<KaleoDefinition> {
	public KaleoDefinitionWrapper(KaleoDefinition kaleoDefinition) {
		_kaleoDefinition = kaleoDefinition;
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoDefinition.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoDefinition.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("content", getContent());
		attributes.put("version", getVersion());
		attributes.put("active", getActive());
		attributes.put("startKaleoNodeId", getStartKaleoNodeId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoDefinitionId = (Long)attributes.get("kaleoDefinitionId");

		if (kaleoDefinitionId != null) {
			setKaleoDefinitionId(kaleoDefinitionId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		Integer version = (Integer)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}

		Long startKaleoNodeId = (Long)attributes.get("startKaleoNodeId");

		if (startKaleoNodeId != null) {
			setStartKaleoNodeId(startKaleoNodeId);
		}
	}

	/**
	* Returns the primary key of this kaleo definition.
	*
	* @return the primary key of this kaleo definition
	*/
	@Override
	public long getPrimaryKey() {
		return _kaleoDefinition.getPrimaryKey();
	}

	/**
	* Sets the primary key of this kaleo definition.
	*
	* @param primaryKey the primary key of this kaleo definition
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_kaleoDefinition.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the kaleo definition ID of this kaleo definition.
	*
	* @return the kaleo definition ID of this kaleo definition
	*/
	@Override
	public long getKaleoDefinitionId() {
		return _kaleoDefinition.getKaleoDefinitionId();
	}

	/**
	* Sets the kaleo definition ID of this kaleo definition.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo definition
	*/
	@Override
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoDefinition.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the group ID of this kaleo definition.
	*
	* @return the group ID of this kaleo definition
	*/
	@Override
	public long getGroupId() {
		return _kaleoDefinition.getGroupId();
	}

	/**
	* Sets the group ID of this kaleo definition.
	*
	* @param groupId the group ID of this kaleo definition
	*/
	@Override
	public void setGroupId(long groupId) {
		_kaleoDefinition.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this kaleo definition.
	*
	* @return the company ID of this kaleo definition
	*/
	@Override
	public long getCompanyId() {
		return _kaleoDefinition.getCompanyId();
	}

	/**
	* Sets the company ID of this kaleo definition.
	*
	* @param companyId the company ID of this kaleo definition
	*/
	@Override
	public void setCompanyId(long companyId) {
		_kaleoDefinition.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this kaleo definition.
	*
	* @return the user ID of this kaleo definition
	*/
	@Override
	public long getUserId() {
		return _kaleoDefinition.getUserId();
	}

	/**
	* Sets the user ID of this kaleo definition.
	*
	* @param userId the user ID of this kaleo definition
	*/
	@Override
	public void setUserId(long userId) {
		_kaleoDefinition.setUserId(userId);
	}

	/**
	* Returns the user uuid of this kaleo definition.
	*
	* @return the user uuid of this kaleo definition
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinition.getUserUuid();
	}

	/**
	* Sets the user uuid of this kaleo definition.
	*
	* @param userUuid the user uuid of this kaleo definition
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoDefinition.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this kaleo definition.
	*
	* @return the user name of this kaleo definition
	*/
	@Override
	public java.lang.String getUserName() {
		return _kaleoDefinition.getUserName();
	}

	/**
	* Sets the user name of this kaleo definition.
	*
	* @param userName the user name of this kaleo definition
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_kaleoDefinition.setUserName(userName);
	}

	/**
	* Returns the create date of this kaleo definition.
	*
	* @return the create date of this kaleo definition
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _kaleoDefinition.getCreateDate();
	}

	/**
	* Sets the create date of this kaleo definition.
	*
	* @param createDate the create date of this kaleo definition
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_kaleoDefinition.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this kaleo definition.
	*
	* @return the modified date of this kaleo definition
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _kaleoDefinition.getModifiedDate();
	}

	/**
	* Sets the modified date of this kaleo definition.
	*
	* @param modifiedDate the modified date of this kaleo definition
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoDefinition.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this kaleo definition.
	*
	* @return the name of this kaleo definition
	*/
	@Override
	public java.lang.String getName() {
		return _kaleoDefinition.getName();
	}

	/**
	* Sets the name of this kaleo definition.
	*
	* @param name the name of this kaleo definition
	*/
	@Override
	public void setName(java.lang.String name) {
		_kaleoDefinition.setName(name);
	}

	/**
	* Returns the title of this kaleo definition.
	*
	* @return the title of this kaleo definition
	*/
	@Override
	public java.lang.String getTitle() {
		return _kaleoDefinition.getTitle();
	}

	/**
	* Returns the localized title of this kaleo definition in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this kaleo definition
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale) {
		return _kaleoDefinition.getTitle(locale);
	}

	/**
	* Returns the localized title of this kaleo definition in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this kaleo definition. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _kaleoDefinition.getTitle(locale, useDefault);
	}

	/**
	* Returns the localized title of this kaleo definition in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this kaleo definition
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId) {
		return _kaleoDefinition.getTitle(languageId);
	}

	/**
	* Returns the localized title of this kaleo definition in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this kaleo definition
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _kaleoDefinition.getTitle(languageId, useDefault);
	}

	@Override
	public java.lang.String getTitleCurrentLanguageId() {
		return _kaleoDefinition.getTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getTitleCurrentValue() {
		return _kaleoDefinition.getTitleCurrentValue();
	}

	/**
	* Returns a map of the locales and localized titles of this kaleo definition.
	*
	* @return the locales and localized titles of this kaleo definition
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _kaleoDefinition.getTitleMap();
	}

	/**
	* Sets the title of this kaleo definition.
	*
	* @param title the title of this kaleo definition
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_kaleoDefinition.setTitle(title);
	}

	/**
	* Sets the localized title of this kaleo definition in the language.
	*
	* @param title the localized title of this kaleo definition
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale) {
		_kaleoDefinition.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this kaleo definition in the language, and sets the default locale.
	*
	* @param title the localized title of this kaleo definition
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_kaleoDefinition.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(java.lang.String languageId) {
		_kaleoDefinition.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this kaleo definition from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this kaleo definition
	*/
	@Override
	public void setTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> titleMap) {
		_kaleoDefinition.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this kaleo definition from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this kaleo definition
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_kaleoDefinition.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Returns the description of this kaleo definition.
	*
	* @return the description of this kaleo definition
	*/
	@Override
	public java.lang.String getDescription() {
		return _kaleoDefinition.getDescription();
	}

	/**
	* Sets the description of this kaleo definition.
	*
	* @param description the description of this kaleo definition
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_kaleoDefinition.setDescription(description);
	}

	/**
	* Returns the content of this kaleo definition.
	*
	* @return the content of this kaleo definition
	*/
	@Override
	public java.lang.String getContent() {
		return _kaleoDefinition.getContent();
	}

	/**
	* Sets the content of this kaleo definition.
	*
	* @param content the content of this kaleo definition
	*/
	@Override
	public void setContent(java.lang.String content) {
		_kaleoDefinition.setContent(content);
	}

	/**
	* Returns the version of this kaleo definition.
	*
	* @return the version of this kaleo definition
	*/
	@Override
	public int getVersion() {
		return _kaleoDefinition.getVersion();
	}

	/**
	* Sets the version of this kaleo definition.
	*
	* @param version the version of this kaleo definition
	*/
	@Override
	public void setVersion(int version) {
		_kaleoDefinition.setVersion(version);
	}

	/**
	* Returns the active of this kaleo definition.
	*
	* @return the active of this kaleo definition
	*/
	@Override
	public boolean getActive() {
		return _kaleoDefinition.getActive();
	}

	/**
	* Returns <code>true</code> if this kaleo definition is active.
	*
	* @return <code>true</code> if this kaleo definition is active; <code>false</code> otherwise
	*/
	@Override
	public boolean isActive() {
		return _kaleoDefinition.isActive();
	}

	/**
	* Sets whether this kaleo definition is active.
	*
	* @param active the active of this kaleo definition
	*/
	@Override
	public void setActive(boolean active) {
		_kaleoDefinition.setActive(active);
	}

	/**
	* Returns the start kaleo node ID of this kaleo definition.
	*
	* @return the start kaleo node ID of this kaleo definition
	*/
	@Override
	public long getStartKaleoNodeId() {
		return _kaleoDefinition.getStartKaleoNodeId();
	}

	/**
	* Sets the start kaleo node ID of this kaleo definition.
	*
	* @param startKaleoNodeId the start kaleo node ID of this kaleo definition
	*/
	@Override
	public void setStartKaleoNodeId(long startKaleoNodeId) {
		_kaleoDefinition.setStartKaleoNodeId(startKaleoNodeId);
	}

	@Override
	public boolean isNew() {
		return _kaleoDefinition.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_kaleoDefinition.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _kaleoDefinition.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_kaleoDefinition.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _kaleoDefinition.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoDefinition.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoDefinition.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoDefinition.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_kaleoDefinition.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_kaleoDefinition.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoDefinition.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _kaleoDefinition.getAvailableLanguageIds();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _kaleoDefinition.getDefaultLanguageId();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.LocaleException {
		_kaleoDefinition.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_kaleoDefinition.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoDefinitionWrapper((KaleoDefinition)_kaleoDefinition.clone());
	}

	@Override
	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoDefinition kaleoDefinition) {
		return _kaleoDefinition.compareTo(kaleoDefinition);
	}

	@Override
	public int hashCode() {
		return _kaleoDefinition.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> toCacheModel() {
		return _kaleoDefinition.toCacheModel();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition toEscapedModel() {
		return new KaleoDefinitionWrapper(_kaleoDefinition.toEscapedModel());
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition toUnescapedModel() {
		return new KaleoDefinitionWrapper(_kaleoDefinition.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoDefinition.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _kaleoDefinition.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoDefinition.persist();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoNode getKaleoStartNode()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinition.getKaleoStartNode();
	}

	@Override
	public boolean hasIncompleteKaleoInstances()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinition.hasIncompleteKaleoInstances();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoDefinitionWrapper)) {
			return false;
		}

		KaleoDefinitionWrapper kaleoDefinitionWrapper = (KaleoDefinitionWrapper)obj;

		if (Validator.equals(_kaleoDefinition,
					kaleoDefinitionWrapper._kaleoDefinition)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public KaleoDefinition getWrappedKaleoDefinition() {
		return _kaleoDefinition;
	}

	@Override
	public KaleoDefinition getWrappedModel() {
		return _kaleoDefinition;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _kaleoDefinition.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _kaleoDefinition.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_kaleoDefinition.resetOriginalValues();
	}

	private KaleoDefinition _kaleoDefinition;
}