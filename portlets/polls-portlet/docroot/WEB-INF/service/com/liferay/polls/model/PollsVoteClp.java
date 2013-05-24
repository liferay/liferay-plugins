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

package com.liferay.polls.model;

import com.liferay.polls.service.ClpSerializer;
import com.liferay.polls.service.PollsVoteLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Juan Fernández
 */
public class PollsVoteClp extends BaseModelImpl<PollsVote> implements PollsVote {
	public PollsVoteClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return PollsVote.class;
	}

	@Override
	public String getModelClassName() {
		return PollsVote.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _pollsVoteId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setPollsVoteId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _pollsVoteId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("pollsVoteId", getPollsVoteId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("pollsQuestionId", getPollsQuestionId());
		attributes.put("pollsChoiceId", getPollsChoiceId());
		attributes.put("voteDate", getVoteDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long pollsVoteId = (Long)attributes.get("pollsVoteId");

		if (pollsVoteId != null) {
			setPollsVoteId(pollsVoteId);
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

		Long pollsQuestionId = (Long)attributes.get("pollsQuestionId");

		if (pollsQuestionId != null) {
			setPollsQuestionId(pollsQuestionId);
		}

		Long pollsChoiceId = (Long)attributes.get("pollsChoiceId");

		if (pollsChoiceId != null) {
			setPollsChoiceId(pollsChoiceId);
		}

		Date voteDate = (Date)attributes.get("voteDate");

		if (voteDate != null) {
			setVoteDate(voteDate);
		}
	}

	@Override
	public long getPollsVoteId() {
		return _pollsVoteId;
	}

	@Override
	public void setPollsVoteId(long pollsVoteId) {
		_pollsVoteId = pollsVoteId;

		if (_pollsVoteRemoteModel != null) {
			try {
				Class<?> clazz = _pollsVoteRemoteModel.getClass();

				Method method = clazz.getMethod("setPollsVoteId", long.class);

				method.invoke(_pollsVoteRemoteModel, pollsVoteId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_pollsVoteRemoteModel != null) {
			try {
				Class<?> clazz = _pollsVoteRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_pollsVoteRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_pollsVoteRemoteModel != null) {
			try {
				Class<?> clazz = _pollsVoteRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_pollsVoteRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	@Override
	public String getUserName() {
		return _userName;
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;

		if (_pollsVoteRemoteModel != null) {
			try {
				Class<?> clazz = _pollsVoteRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_pollsVoteRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_pollsVoteRemoteModel != null) {
			try {
				Class<?> clazz = _pollsVoteRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_pollsVoteRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_pollsVoteRemoteModel != null) {
			try {
				Class<?> clazz = _pollsVoteRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_pollsVoteRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getPollsQuestionId() {
		return _pollsQuestionId;
	}

	@Override
	public void setPollsQuestionId(long pollsQuestionId) {
		_pollsQuestionId = pollsQuestionId;

		if (_pollsVoteRemoteModel != null) {
			try {
				Class<?> clazz = _pollsVoteRemoteModel.getClass();

				Method method = clazz.getMethod("setPollsQuestionId", long.class);

				method.invoke(_pollsVoteRemoteModel, pollsQuestionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getPollsChoiceId() {
		return _pollsChoiceId;
	}

	@Override
	public void setPollsChoiceId(long pollsChoiceId) {
		_pollsChoiceId = pollsChoiceId;

		if (_pollsVoteRemoteModel != null) {
			try {
				Class<?> clazz = _pollsVoteRemoteModel.getClass();

				Method method = clazz.getMethod("setPollsChoiceId", long.class);

				method.invoke(_pollsVoteRemoteModel, pollsChoiceId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getVoteDate() {
		return _voteDate;
	}

	@Override
	public void setVoteDate(Date voteDate) {
		_voteDate = voteDate;

		if (_pollsVoteRemoteModel != null) {
			try {
				Class<?> clazz = _pollsVoteRemoteModel.getClass();

				Method method = clazz.getMethod("setVoteDate", Date.class);

				method.invoke(_pollsVoteRemoteModel, voteDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public com.liferay.polls.model.PollsChoice getPollsChoice() {
		try {
			String methodName = "getPollsChoice";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.polls.model.PollsChoice returnObj = (com.liferay.polls.model.PollsChoice)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getPollsVoteRemoteModel() {
		return _pollsVoteRemoteModel;
	}

	public void setPollsVoteRemoteModel(BaseModel<?> pollsVoteRemoteModel) {
		_pollsVoteRemoteModel = pollsVoteRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _pollsVoteRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_pollsVoteRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			PollsVoteLocalServiceUtil.addPollsVote(this);
		}
		else {
			PollsVoteLocalServiceUtil.updatePollsVote(this);
		}
	}

	@Override
	public PollsVote toEscapedModel() {
		return (PollsVote)ProxyUtil.newProxyInstance(PollsVote.class.getClassLoader(),
			new Class[] { PollsVote.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		PollsVoteClp clone = new PollsVoteClp();

		clone.setPollsVoteId(getPollsVoteId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setPollsQuestionId(getPollsQuestionId());
		clone.setPollsChoiceId(getPollsChoiceId());
		clone.setVoteDate(getVoteDate());

		return clone;
	}

	@Override
	public int compareTo(PollsVote pollsVote) {
		long primaryKey = pollsVote.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PollsVoteClp)) {
			return false;
		}

		PollsVoteClp pollsVote = (PollsVoteClp)obj;

		long primaryKey = pollsVote.getPrimaryKey();

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
		StringBundler sb = new StringBundler(19);

		sb.append("{pollsVoteId=");
		sb.append(getPollsVoteId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", pollsQuestionId=");
		sb.append(getPollsQuestionId());
		sb.append(", pollsChoiceId=");
		sb.append(getPollsChoiceId());
		sb.append(", voteDate=");
		sb.append(getVoteDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.polls.model.PollsVote");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>pollsVoteId</column-name><column-value><![CDATA[");
		sb.append(getPollsVoteId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>pollsQuestionId</column-name><column-value><![CDATA[");
		sb.append(getPollsQuestionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>pollsChoiceId</column-name><column-value><![CDATA[");
		sb.append(getPollsChoiceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>voteDate</column-name><column-value><![CDATA[");
		sb.append(getVoteDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _pollsVoteId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _pollsQuestionId;
	private long _pollsChoiceId;
	private Date _voteDate;
	private BaseModel<?> _pollsVoteRemoteModel;
}