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

package com.liferay.so.activities.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.so.activities.service.SocialActivityLocalServiceUtil;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class SocialActivityClp extends BaseModelImpl<SocialActivity>
	implements SocialActivity {
	public SocialActivityClp() {
	}

	public Class<?> getModelClass() {
		return SocialActivity.class;
	}

	public String getModelClassName() {
		return SocialActivity.class.getName();
	}

	public long getPrimaryKey() {
		return _activityId;
	}

	public void setPrimaryKey(long primaryKey) {
		setActivityId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_activityId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("activityId", getActivityId());
		attributes.put("activitySetId", getActivitySetId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long activityId = (Long)attributes.get("activityId");

		if (activityId != null) {
			setActivityId(activityId);
		}

		Long activitySetId = (Long)attributes.get("activitySetId");

		if (activitySetId != null) {
			setActivitySetId(activitySetId);
		}
	}

	public long getActivityId() {
		return _activityId;
	}

	public void setActivityId(long activityId) {
		_activityId = activityId;
	}

	public long getActivitySetId() {
		return _activitySetId;
	}

	public void setActivitySetId(long activitySetId) {
		_activitySetId = activitySetId;
	}

	public com.liferay.portlet.social.model.SocialActivity getPortalSocialActivity() {
		throw new UnsupportedOperationException();
	}

	public long getClassPK() {
		throw new UnsupportedOperationException();
	}

	public long getReceiverUserId() {
		throw new UnsupportedOperationException();
	}

	public long getCreateDate() {
		throw new UnsupportedOperationException();
	}

	public long getCompanyId() {
		throw new UnsupportedOperationException();
	}

	public long getUserId() {
		throw new UnsupportedOperationException();
	}

	public long getMirrorActivityId() {
		throw new UnsupportedOperationException();
	}

	public long getGroupId() {
		throw new UnsupportedOperationException();
	}

	public int getType() {
		throw new UnsupportedOperationException();
	}

	public java.lang.String getExtraData() {
		throw new UnsupportedOperationException();
	}

	public long getClassNameId() {
		throw new UnsupportedOperationException();
	}

	public BaseModel<?> getSocialActivityRemoteModel() {
		return _socialActivityRemoteModel;
	}

	public void setSocialActivityRemoteModel(
		BaseModel<?> socialActivityRemoteModel) {
		_socialActivityRemoteModel = socialActivityRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			SocialActivityLocalServiceUtil.addSocialActivity(this);
		}
		else {
			SocialActivityLocalServiceUtil.updateSocialActivity(this);
		}
	}

	@Override
	public SocialActivity toEscapedModel() {
		return (SocialActivity)ProxyUtil.newProxyInstance(SocialActivity.class.getClassLoader(),
			new Class[] { SocialActivity.class },
			new AutoEscapeBeanHandler(this));
	}

	public SocialActivity toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		SocialActivityClp clone = new SocialActivityClp();

		clone.setActivityId(getActivityId());
		clone.setActivitySetId(getActivitySetId());

		return clone;
	}

	public int compareTo(SocialActivity socialActivity) {
		long primaryKey = socialActivity.getPrimaryKey();

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
		if (obj == null) {
			return false;
		}

		SocialActivityClp socialActivity = null;

		try {
			socialActivity = (SocialActivityClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = socialActivity.getPrimaryKey();

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
		StringBundler sb = new StringBundler(5);

		sb.append("{activityId=");
		sb.append(getActivityId());
		sb.append(", activitySetId=");
		sb.append(getActivitySetId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(10);

		sb.append("<model><model-name>");
		sb.append("com.liferay.so.activities.model.SocialActivity");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>activityId</column-name><column-value><![CDATA[");
		sb.append(getActivityId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>activitySetId</column-name><column-value><![CDATA[");
		sb.append(getActivitySetId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _activityId;
	private long _activitySetId;
	private BaseModel<?> _socialActivityRemoteModel;
}