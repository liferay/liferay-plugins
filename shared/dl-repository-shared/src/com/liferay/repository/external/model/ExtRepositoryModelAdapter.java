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

package com.liferay.repository.external.model;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.RepositoryModel;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.repository.external.ExtRepositoryAdapter;
import com.liferay.repository.external.ExtRepositoryModel;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * @author Iván Zaera
 * @author Sergio González
 */
public abstract class ExtRepositoryModelAdapter<T>
	implements RepositoryModel<T>, Cloneable {

	@Override
	@SuppressWarnings("unchecked")
	public T clone() {
		try {
			return (T)super.clone();
		}
		catch (CloneNotSupportedException cnse) {
			throw new RuntimeException(cnse);
		}
	}

	@Override
	public Map<String, Serializable> getAttributes() {
		return Collections.emptyMap();
	}

	@Override
	public long getCompanyId() {
		return _extRepositoryAdapter.getCompanyId();
	}

	@Override
	public Date getCreateDate() {
		return _extRepositoryModel.getCreateDate();
	}

	public String getDescription() {
		return StringPool.BLANK;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), getModelClassName(), getPrimaryKey());
	}

	public ExtRepositoryModel getExtRepositoryModel() {
		return _extRepositoryModel;
	}

	@Override
	public long getGroupId() {
		return _extRepositoryAdapter.getGroupId();
	}

	@Override
	public Date getLastPublishDate() {
		return null;
	}

	@Override
	public Object getModel() {
		return _extRepositoryModel;
	}

	@Override
	public String getModelClassName() {
		Class<?> modelClass = getModelClass();

		return modelClass.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _extRepositoryObjectId;
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return getPrimaryKey();
	}

	public long getRepositoryId() {
		return _extRepositoryAdapter.getRepositoryId();
	}

	public long getSize() {
		return _extRepositoryModel.getSize();
	}

	@Override
	public long getUserId() {
		User user = getUser(_extRepositoryModel.getOwner());

		return user.getUserId();
	}

	@Override
	public String getUserName() {
		User user = getUser(_extRepositoryModel.getOwner());

		return user.getFullName();
	}

	@Override
	public String getUserUuid() {
		User user = getUser(_extRepositoryModel.getOwner());

		try {
			return user.getUserUuid();
		}
		catch (Exception e) {
		}

		return StringPool.BLANK;
	}

	@Override
	public String getUuid() {
		return _uuid;
	}

	public boolean isDefaultRepository() {
		return false;
	}

	@Override
	public boolean isEscapedModel() {
		return false;
	}

	@Override
	public void setCompanyId(long companyId) {
		throw new UnsupportedOperationException("Model is read only");
	}

	@Override
	public void setCreateDate(Date date) {
		throw new UnsupportedOperationException("Model is read only");
	}

	@Override
	public void setGroupId(long groupId) {
		throw new UnsupportedOperationException("Model is read only");
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		throw new UnsupportedOperationException("Model is read only");
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		throw new UnsupportedOperationException("Model is read only");
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		throw new UnsupportedOperationException("Model is read only");
	}

	@Override
	public void setUserId(long userId) {
		throw new UnsupportedOperationException("Model is read only");
	}

	@Override
	public void setUserName(String userName) {
		throw new UnsupportedOperationException("Model is read only");
	}

	@Override
	public void setUserUuid(String userUuid) {
		throw new UnsupportedOperationException("Model is read only");
	}

	@Override
	public void setUuid(String uuid) {
		throw new UnsupportedOperationException("Model is read only");
	}

	@Override
	public T toEscapedModel() {
		return (T)this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T toUnescapedModel() {
		return (T)this;
	}

	protected ExtRepositoryModelAdapter(
		ExtRepositoryAdapter extRepositoryAdapter, long extRepositoryObjectId,
		String uuid, ExtRepositoryModel extRepositoryModel) {

		_extRepositoryAdapter = extRepositoryAdapter;
		_extRepositoryObjectId = extRepositoryObjectId;
		_uuid = uuid;
		_extRepositoryModel = extRepositoryModel;
	}

	protected ExtRepositoryAdapter getRepository() {
		return _extRepositoryAdapter;
	}

	protected User getUser(String extRepositoryUserName) {
		User user = null;

		if (Validator.isNotNull(extRepositoryUserName)) {
			String liferayLogin = _extRepositoryAdapter.getLiferayLogin(
				extRepositoryUserName);

			try {
				String authType = _extRepositoryAdapter.getAuthType();

				if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
					user = UserLocalServiceUtil.getUser(
						GetterUtil.getLong(liferayLogin));
				}
				else if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
					user = UserLocalServiceUtil.getUserByEmailAddress(
						getCompanyId(), liferayLogin);
				}
				else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
					user = UserLocalServiceUtil.getUserByScreenName(
						getCompanyId(), liferayLogin);
				}
			}
			catch (Exception e) {
			}
		}

		if (user == null) {
			try {
				user = UserLocalServiceUtil.getDefaultUser(getCompanyId());
			}
			catch (Exception e) {
			}
		}

		return user;
	}

	private ExtRepositoryAdapter _extRepositoryAdapter;
	private ExtRepositoryModel _extRepositoryModel;
	private long _extRepositoryObjectId;
	private String _uuid;

}