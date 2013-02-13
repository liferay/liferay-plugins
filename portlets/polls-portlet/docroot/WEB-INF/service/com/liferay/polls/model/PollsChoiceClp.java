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

import com.liferay.polls.service.PollsChoiceLocalServiceUtil;

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Juan Fernández
 */
public class PollsChoiceClp extends BaseModelImpl<PollsChoice>
	implements PollsChoice {
	public PollsChoiceClp() {
	}

	public Class<?> getModelClass() {
		return PollsChoice.class;
	}

	public String getModelClassName() {
		return PollsChoice.class.getName();
	}

	public long getPrimaryKey() {
		return _pollsChoiceId;
	}

	public void setPrimaryKey(long primaryKey) {
		setPollsChoiceId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return _pollsChoiceId;
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("pollsChoiceId", getPollsChoiceId());
		attributes.put("pollsQuestionId", getPollsQuestionId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());

		return attributes;
	}

	@Override
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

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getPollsChoiceId() {
		return _pollsChoiceId;
	}

	public void setPollsChoiceId(long pollsChoiceId) {
		_pollsChoiceId = pollsChoiceId;
	}

	public long getPollsQuestionId() {
		return _pollsQuestionId;
	}

	public void setPollsQuestionId(long pollsQuestionId) {
		_pollsQuestionId = pollsQuestionId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public String getDescription(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId);
	}

	public String getDescription(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId, useDefault);
	}

	public String getDescription(String languageId) {
		return LocalizationUtil.getLocalization(getDescription(), languageId);
	}

	public String getDescription(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getDescription(), languageId,
			useDefault);
	}

	public String getDescriptionCurrentLanguageId() {
		return _descriptionCurrentLanguageId;
	}

	public String getDescriptionCurrentValue() {
		Locale locale = getLocale(_descriptionCurrentLanguageId);

		return getDescription(locale);
	}

	public Map<Locale, String> getDescriptionMap() {
		return LocalizationUtil.getLocalizationMap(getDescription());
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setDescription(String description, Locale locale) {
		setDescription(description, locale, LocaleUtil.getDefault());
	}

	public void setDescription(String description, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(description)) {
			setDescription(LocalizationUtil.updateLocalization(
					getDescription(), "Description", description, languageId,
					defaultLanguageId));
		}
		else {
			setDescription(LocalizationUtil.removeLocalization(
					getDescription(), "Description", languageId));
		}
	}

	public void setDescriptionCurrentLanguageId(String languageId) {
		_descriptionCurrentLanguageId = languageId;
	}

	public void setDescriptionMap(Map<Locale, String> descriptionMap) {
		setDescriptionMap(descriptionMap, LocaleUtil.getDefault());
	}

	public void setDescriptionMap(Map<Locale, String> descriptionMap,
		Locale defaultLocale) {
		if (descriptionMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setDescription(LocalizationUtil.updateLocalization(descriptionMap,
					getDescription(), "Description",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	public int getPollsVotesCount() {
		throw new UnsupportedOperationException();
	}

	public BaseModel<?> getPollsChoiceRemoteModel() {
		return _pollsChoiceRemoteModel;
	}

	public void setPollsChoiceRemoteModel(BaseModel<?> pollsChoiceRemoteModel) {
		_pollsChoiceRemoteModel = pollsChoiceRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			PollsChoiceLocalServiceUtil.addPollsChoice(this);
		}
		else {
			PollsChoiceLocalServiceUtil.updatePollsChoice(this);
		}
	}

	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {
		setDescription(getDescription(defaultImportLocale),
			defaultImportLocale, defaultImportLocale);
	}

	@Override
	public PollsChoice toEscapedModel() {
		return (PollsChoice)ProxyUtil.newProxyInstance(PollsChoice.class.getClassLoader(),
			new Class[] { PollsChoice.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		PollsChoiceClp clone = new PollsChoiceClp();

		clone.setUuid(getUuid());
		clone.setPollsChoiceId(getPollsChoiceId());
		clone.setPollsQuestionId(getPollsQuestionId());
		clone.setName(getName());
		clone.setDescription(getDescription());

		return clone;
	}

	public int compareTo(PollsChoice pollsChoice) {
		int value = 0;

		if (getPollsQuestionId() < pollsChoice.getPollsQuestionId()) {
			value = -1;
		}
		else if (getPollsQuestionId() > pollsChoice.getPollsQuestionId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		value = getName().compareTo(pollsChoice.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		PollsChoiceClp pollsChoice = null;

		try {
			pollsChoice = (PollsChoiceClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = pollsChoice.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", pollsChoiceId=");
		sb.append(getPollsChoiceId());
		sb.append(", pollsQuestionId=");
		sb.append(getPollsQuestionId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.polls.model.PollsChoice");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>pollsChoiceId</column-name><column-value><![CDATA[");
		sb.append(getPollsChoiceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>pollsQuestionId</column-name><column-value><![CDATA[");
		sb.append(getPollsQuestionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _pollsChoiceId;
	private long _pollsQuestionId;
	private String _name;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private BaseModel<?> _pollsChoiceRemoteModel;
}