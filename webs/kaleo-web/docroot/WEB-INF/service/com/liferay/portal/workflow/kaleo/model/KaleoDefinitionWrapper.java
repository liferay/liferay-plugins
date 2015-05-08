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

package com.liferay.portal.workflow.kaleo.model;

import aQute.bnd.annotation.ProviderType;

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
@ProviderType
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

	@Override
	public java.lang.Object clone() {
		return new KaleoDefinitionWrapper((KaleoDefinition)_kaleoDefinition.clone());
	}

	@Override
	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoDefinition kaleoDefinition) {
		return _kaleoDefinition.compareTo(kaleoDefinition);
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

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _kaleoDefinition.getAvailableLanguageIds();
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
	* Returns the content of this kaleo definition.
	*
	* @return the content of this kaleo definition
	*/
	@Override
	public java.lang.String getContent() {
		return _kaleoDefinition.getContent();
	}

	/**
	* Returns the create date of this kaleo definition.
	*
	* @return the create date of this kaleo definition
	*/
	@Override
	public Date getCreateDate() {
		return _kaleoDefinition.getCreateDate();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _kaleoDefinition.getDefaultLanguageId();
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

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoDefinition.getExpandoBridge();
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
	* Returns the kaleo definition ID of this kaleo definition.
	*
	* @return the kaleo definition ID of this kaleo definition
	*/
	@Override
	public long getKaleoDefinitionId() {
		return _kaleoDefinition.getKaleoDefinitionId();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoNode getKaleoStartNode()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoDefinition.getKaleoStartNode();
	}

	/**
	* Returns the modified date of this kaleo definition.
	*
	* @return the modified date of this kaleo definition
	*/
	@Override
	public Date getModifiedDate() {
		return _kaleoDefinition.getModifiedDate();
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
	* Returns the primary key of this kaleo definition.
	*
	* @return the primary key of this kaleo definition
	*/
	@Override
	public long getPrimaryKey() {
		return _kaleoDefinition.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoDefinition.getPrimaryKeyObj();
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
	public Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _kaleoDefinition.getTitleMap();
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
	* Returns the user name of this kaleo definition.
	*
	* @return the user name of this kaleo definition
	*/
	@Override
	public java.lang.String getUserName() {
		return _kaleoDefinition.getUserName();
	}

	/**
	* Returns the user uuid of this kaleo definition.
	*
	* @return the user uuid of this kaleo definition
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _kaleoDefinition.getUserUuid();
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

	@Override
	public boolean hasIncompleteKaleoInstances() {
		return _kaleoDefinition.hasIncompleteKaleoInstances();
	}

	@Override
	public int hashCode() {
		return _kaleoDefinition.hashCode();
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

	@Override
	public boolean isCachedModel() {
		return _kaleoDefinition.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _kaleoDefinition.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _kaleoDefinition.isNew();
	}

	@Override
	public void persist() {
		_kaleoDefinition.persist();
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

	/**
	* Sets whether this kaleo definition is active.
	*
	* @param active the active of this kaleo definition
	*/
	@Override
	public void setActive(boolean active) {
		_kaleoDefinition.setActive(active);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_kaleoDefinition.setCachedModel(cachedModel);
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
	* Sets the content of this kaleo definition.
	*
	* @param content the content of this kaleo definition
	*/
	@Override
	public void setContent(java.lang.String content) {
		_kaleoDefinition.setContent(content);
	}

	/**
	* Sets the create date of this kaleo definition.
	*
	* @param createDate the create date of this kaleo definition
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_kaleoDefinition.setCreateDate(createDate);
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
	* Sets the kaleo definition ID of this kaleo definition.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo definition
	*/
	@Override
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoDefinition.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Sets the modified date of this kaleo definition.
	*
	* @param modifiedDate the modified date of this kaleo definition
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_kaleoDefinition.setModifiedDate(modifiedDate);
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

	@Override
	public void setNew(boolean n) {
		_kaleoDefinition.setNew(n);
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

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoDefinition.setPrimaryKeyObj(primaryKeyObj);
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
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap) {
		_kaleoDefinition.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this kaleo definition from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this kaleo definition
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_kaleoDefinition.setTitleMap(titleMap, defaultLocale);
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
	* Sets the user name of this kaleo definition.
	*
	* @param userName the user name of this kaleo definition
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_kaleoDefinition.setUserName(userName);
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
	* Sets the version of this kaleo definition.
	*
	* @param version the version of this kaleo definition
	*/
	@Override
	public void setVersion(int version) {
		_kaleoDefinition.setVersion(version);
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
	public java.lang.String toString() {
		return _kaleoDefinition.toString();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition toUnescapedModel() {
		return new KaleoDefinitionWrapper(_kaleoDefinition.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _kaleoDefinition.toXmlString();
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

	private final KaleoDefinition _kaleoDefinition;
}