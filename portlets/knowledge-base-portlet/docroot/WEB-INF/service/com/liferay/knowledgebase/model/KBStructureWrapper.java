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

package com.liferay.knowledgebase.model;

/**
 * <p>
 * This class is a wrapper for {@link KBStructure}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KBStructure
 * @generated
 */
public class KBStructureWrapper implements KBStructure {
	public KBStructureWrapper(KBStructure kbStructure) {
		_kbStructure = kbStructure;
	}

	public Class<?> getModelClass() {
		return KBStructure.class;
	}

	public String getModelClassName() {
		return KBStructure.class.getName();
	}

	/**
	* Gets the primary key of this k b structure.
	*
	* @return the primary key of this k b structure
	*/
	public long getPrimaryKey() {
		return _kbStructure.getPrimaryKey();
	}

	/**
	* Sets the primary key of this k b structure
	*
	* @param pk the primary key of this k b structure
	*/
	public void setPrimaryKey(long pk) {
		_kbStructure.setPrimaryKey(pk);
	}

	/**
	* Gets the uuid of this k b structure.
	*
	* @return the uuid of this k b structure
	*/
	public java.lang.String getUuid() {
		return _kbStructure.getUuid();
	}

	/**
	* Sets the uuid of this k b structure.
	*
	* @param uuid the uuid of this k b structure
	*/
	public void setUuid(java.lang.String uuid) {
		_kbStructure.setUuid(uuid);
	}

	/**
	* Gets the kb structure ID of this k b structure.
	*
	* @return the kb structure ID of this k b structure
	*/
	public long getKbStructureId() {
		return _kbStructure.getKbStructureId();
	}

	/**
	* Sets the kb structure ID of this k b structure.
	*
	* @param kbStructureId the kb structure ID of this k b structure
	*/
	public void setKbStructureId(long kbStructureId) {
		_kbStructure.setKbStructureId(kbStructureId);
	}

	/**
	* Gets the group ID of this k b structure.
	*
	* @return the group ID of this k b structure
	*/
	public long getGroupId() {
		return _kbStructure.getGroupId();
	}

	/**
	* Sets the group ID of this k b structure.
	*
	* @param groupId the group ID of this k b structure
	*/
	public void setGroupId(long groupId) {
		_kbStructure.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this k b structure.
	*
	* @return the company ID of this k b structure
	*/
	public long getCompanyId() {
		return _kbStructure.getCompanyId();
	}

	/**
	* Sets the company ID of this k b structure.
	*
	* @param companyId the company ID of this k b structure
	*/
	public void setCompanyId(long companyId) {
		_kbStructure.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this k b structure.
	*
	* @return the user ID of this k b structure
	*/
	public long getUserId() {
		return _kbStructure.getUserId();
	}

	/**
	* Sets the user ID of this k b structure.
	*
	* @param userId the user ID of this k b structure
	*/
	public void setUserId(long userId) {
		_kbStructure.setUserId(userId);
	}

	/**
	* Gets the user uuid of this k b structure.
	*
	* @return the user uuid of this k b structure
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbStructure.getUserUuid();
	}

	/**
	* Sets the user uuid of this k b structure.
	*
	* @param userUuid the user uuid of this k b structure
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_kbStructure.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this k b structure.
	*
	* @return the user name of this k b structure
	*/
	public java.lang.String getUserName() {
		return _kbStructure.getUserName();
	}

	/**
	* Sets the user name of this k b structure.
	*
	* @param userName the user name of this k b structure
	*/
	public void setUserName(java.lang.String userName) {
		_kbStructure.setUserName(userName);
	}

	/**
	* Gets the create date of this k b structure.
	*
	* @return the create date of this k b structure
	*/
	public java.util.Date getCreateDate() {
		return _kbStructure.getCreateDate();
	}

	/**
	* Sets the create date of this k b structure.
	*
	* @param createDate the create date of this k b structure
	*/
	public void setCreateDate(java.util.Date createDate) {
		_kbStructure.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this k b structure.
	*
	* @return the modified date of this k b structure
	*/
	public java.util.Date getModifiedDate() {
		return _kbStructure.getModifiedDate();
	}

	/**
	* Sets the modified date of this k b structure.
	*
	* @param modifiedDate the modified date of this k b structure
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kbStructure.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the title of this k b structure.
	*
	* @return the title of this k b structure
	*/
	public java.lang.String getTitle() {
		return _kbStructure.getTitle();
	}

	/**
	* Gets the localized title of this k b structure. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale to get the localized title for
	* @return the localized title of this k b structure
	*/
	public java.lang.String getTitle(java.util.Locale locale) {
		return _kbStructure.getTitle(locale);
	}

	/**
	* Gets the localized title of this k b structure, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local to get the localized title for
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this k b structure. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _kbStructure.getTitle(locale, useDefault);
	}

	/**
	* Gets the localized title of this k b structure. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the id of the language to get the localized title for
	* @return the localized title of this k b structure
	*/
	public java.lang.String getTitle(java.lang.String languageId) {
		return _kbStructure.getTitle(languageId);
	}

	/**
	* Gets the localized title of this k b structure, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the id of the language to get the localized title for
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this k b structure
	*/
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _kbStructure.getTitle(languageId, useDefault);
	}

	/**
	* Gets a map of the locales and localized title of this k b structure.
	*
	* @return the locales and localized title
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _kbStructure.getTitleMap();
	}

	/**
	* Sets the title of this k b structure.
	*
	* @param title the title of this k b structure
	*/
	public void setTitle(java.lang.String title) {
		_kbStructure.setTitle(title);
	}

	/**
	* Sets the localized title of this k b structure.
	*
	* @param title the localized title of this k b structure
	* @param locale the locale to set the localized title for
	*/
	public void setTitle(java.lang.String title, java.util.Locale locale) {
		_kbStructure.setTitle(title, locale);
	}

	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_kbStructure.setTitle(title, locale, defaultLocale);
	}

	/**
	* Sets the localized titles of this k b structure from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this k b structure
	*/
	public void setTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> titleMap) {
		_kbStructure.setTitleMap(titleMap);
	}

	public void setTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_kbStructure.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Gets the content of this k b structure.
	*
	* @return the content of this k b structure
	*/
	public java.lang.String getContent() {
		return _kbStructure.getContent();
	}

	/**
	* Sets the content of this k b structure.
	*
	* @param content the content of this k b structure
	*/
	public void setContent(java.lang.String content) {
		_kbStructure.setContent(content);
	}

	public boolean isNew() {
		return _kbStructure.isNew();
	}

	public void setNew(boolean n) {
		_kbStructure.setNew(n);
	}

	public boolean isCachedModel() {
		return _kbStructure.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kbStructure.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kbStructure.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_kbStructure.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kbStructure.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kbStructure.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kbStructure.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new KBStructureWrapper((KBStructure)_kbStructure.clone());
	}

	public int compareTo(
		com.liferay.knowledgebase.model.KBStructure kbStructure) {
		return _kbStructure.compareTo(kbStructure);
	}

	public int hashCode() {
		return _kbStructure.hashCode();
	}

	public com.liferay.knowledgebase.model.KBStructure toEscapedModel() {
		return new KBStructureWrapper(_kbStructure.toEscapedModel());
	}

	public java.lang.String toString() {
		return _kbStructure.toString();
	}

	public java.lang.String toXmlString() {
		return _kbStructure.toXmlString();
	}

	public java.lang.String[] getLanguageIds() {
		return _kbStructure.getLanguageIds();
	}

	public KBStructure getWrappedKBStructure() {
		return _kbStructure;
	}

	public void resetOriginalValues() {
		_kbStructure.resetOriginalValues();
	}

	private KBStructure _kbStructure;
}