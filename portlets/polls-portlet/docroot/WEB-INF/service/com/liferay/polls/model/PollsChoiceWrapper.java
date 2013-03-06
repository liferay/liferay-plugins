/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.polls.model;

import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PollsChoice}.
 * </p>
 *
 * @author    Juan Fern√°ndez
 * @see       PollsChoice
 * @generated
 */
public class PollsChoiceWrapper implements PollsChoice,
	ModelWrapper<PollsChoice> {
	public PollsChoiceWrapper(PollsChoice pollsChoice) {
		_pollsChoice = pollsChoice;
	}

	public Class<?> getModelClass() {
		return PollsChoice.class;
	}

	public String getModelClassName() {
		return PollsChoice.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("pollsChoiceId", getPollsChoiceId());
		attributes.put("pollsQuestionId", getPollsQuestionId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long pollsChoiceId = (Long)attributes.get("pollsChoiceId");

		if (pollsChoiceId != null) {
			setPollsChoiceId(pollsChoiceId);
		}

		Long pollsQuestionId = (Long)attributes.get("pollsQuestionId");

		if (pollsQuestionId != null) {
			setPollsQuestionId(pollsQuestionId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}
	}

	/**
	* Returns the primary key of this polls choice.
	*
	* @return the primary key of this polls choice
	*/
	public long getPrimaryKey() {
		return _pollsChoice.getPrimaryKey();
	}

	/**
	* Sets the primary key of this polls choice.
	*
	* @param primaryKey the primary key of this polls choice
	*/
	public void setPrimaryKey(long primaryKey) {
		_pollsChoice.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this polls choice.
	*
	* @return the uuid of this polls choice
	*/
	public java.lang.String getUuid() {
		return _pollsChoice.getUuid();
	}

	/**
	* Sets the uuid of this polls choice.
	*
	* @param uuid the uuid of this polls choice
	*/
	public void setUuid(java.lang.String uuid) {
		_pollsChoice.setUuid(uuid);
	}

	/**
	* Returns the polls choice ID of this polls choice.
	*
	* @return the polls choice ID of this polls choice
	*/
	public long getPollsChoiceId() {
		return _pollsChoice.getPollsChoiceId();
	}

	/**
	* Sets the polls choice ID of this polls choice.
	*
	* @param pollsChoiceId the polls choice ID of this polls choice
	*/
	public void setPollsChoiceId(long pollsChoiceId) {
		_pollsChoice.setPollsChoiceId(pollsChoiceId);
	}

	/**
	* Returns the polls question ID of this polls choice.
	*
	* @return the polls question ID of this polls choice
	*/
	public long getPollsQuestionId() {
		return _pollsChoice.getPollsQuestionId();
	}

	/**
	* Sets the polls question ID of this polls choice.
	*
	* @param pollsQuestionId the polls question ID of this polls choice
	*/
	public void setPollsQuestionId(long pollsQuestionId) {
		_pollsChoice.setPollsQuestionId(pollsQuestionId);
	}

	/**
	* Returns the name of this polls choice.
	*
	* @return the name of this polls choice
	*/
	public java.lang.String getName() {
		return _pollsChoice.getName();
	}

	/**
	* Sets the name of this polls choice.
	*
	* @param name the name of this polls choice
	*/
	public void setName(java.lang.String name) {
		_pollsChoice.setName(name);
	}

	/**
	* Returns the description of this polls choice.
	*
	* @return the description of this polls choice
	*/
	public java.lang.String getDescription() {
		return _pollsChoice.getDescription();
	}

	/**
	* Returns the localized description of this polls choice in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this polls choice
	*/
	public java.lang.String getDescription(java.util.Locale locale) {
		return _pollsChoice.getDescription(locale);
	}

	/**
	* Returns the localized description of this polls choice in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this polls choice. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _pollsChoice.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this polls choice in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this polls choice
	*/
	public java.lang.String getDescription(java.lang.String languageId) {
		return _pollsChoice.getDescription(languageId);
	}

	/**
	* Returns the localized description of this polls choice in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this polls choice
	*/
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _pollsChoice.getDescription(languageId, useDefault);
	}

	public java.lang.String getDescriptionCurrentLanguageId() {
		return _pollsChoice.getDescriptionCurrentLanguageId();
	}

	public java.lang.String getDescriptionCurrentValue() {
		return _pollsChoice.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this polls choice.
	*
	* @return the locales and localized descriptions of this polls choice
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _pollsChoice.getDescriptionMap();
	}

	/**
	* Sets the description of this polls choice.
	*
	* @param description the description of this polls choice
	*/
	public void setDescription(java.lang.String description) {
		_pollsChoice.setDescription(description);
	}

	/**
	* Sets the localized description of this polls choice in the language.
	*
	* @param description the localized description of this polls choice
	* @param locale the locale of the language
	*/
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_pollsChoice.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this polls choice in the language, and sets the default locale.
	*
	* @param description the localized description of this polls choice
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_pollsChoice.setDescription(description, locale, defaultLocale);
	}

	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_pollsChoice.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this polls choice from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this polls choice
	*/
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap) {
		_pollsChoice.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this polls choice from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this polls choice
	* @param defaultLocale the default locale
	*/
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_pollsChoice.setDescriptionMap(descriptionMap, defaultLocale);
	}

	public boolean isNew() {
		return _pollsChoice.isNew();
	}

	public void setNew(boolean n) {
		_pollsChoice.setNew(n);
	}

	public boolean isCachedModel() {
		return _pollsChoice.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_pollsChoice.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _pollsChoice.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _pollsChoice.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_pollsChoice.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _pollsChoice.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_pollsChoice.setExpandoBridgeAttributes(baseModel);
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_pollsChoice.setExpandoBridgeAttributes(expandoBridge);
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_pollsChoice.setExpandoBridgeAttributes(serviceContext);
	}

	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_pollsChoice.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new PollsChoiceWrapper((PollsChoice)_pollsChoice.clone());
	}

	public int compareTo(com.liferay.polls.model.PollsChoice pollsChoice) {
		return _pollsChoice.compareTo(pollsChoice);
	}

	@Override
	public int hashCode() {
		return _pollsChoice.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.polls.model.PollsChoice> toCacheModel() {
		return _pollsChoice.toCacheModel();
	}

	public com.liferay.polls.model.PollsChoice toEscapedModel() {
		return new PollsChoiceWrapper(_pollsChoice.toEscapedModel());
	}

	public com.liferay.polls.model.PollsChoice toUnescapedModel() {
		return new PollsChoiceWrapper(_pollsChoice.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _pollsChoice.toString();
	}

	public java.lang.String toXmlString() {
		return _pollsChoice.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_pollsChoice.persist();
	}

	public int getPollsVotesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsChoice.getPollsVotesCount();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public PollsChoice getWrappedPollsChoice() {
		return _pollsChoice;
	}

	public PollsChoice getWrappedModel() {
		return _pollsChoice;
	}

	public void resetOriginalValues() {
		_pollsChoice.resetOriginalValues();
	}

	private PollsChoice _pollsChoice;
}