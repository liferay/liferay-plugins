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

package com.liferay.so.activities.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class SocialActivitySoap implements Serializable {
	public static SocialActivitySoap toSoapModel(SocialActivity model) {
		SocialActivitySoap soapModel = new SocialActivitySoap();

		soapModel.setActivityId(model.getActivityId());
		soapModel.setActivitySetId(model.getActivitySetId());

		return soapModel;
	}

	public static SocialActivitySoap[] toSoapModels(SocialActivity[] models) {
		SocialActivitySoap[] soapModels = new SocialActivitySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SocialActivitySoap[][] toSoapModels(SocialActivity[][] models) {
		SocialActivitySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SocialActivitySoap[models.length][models[0].length];
		}
		else {
			soapModels = new SocialActivitySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SocialActivitySoap[] toSoapModels(List<SocialActivity> models) {
		List<SocialActivitySoap> soapModels = new ArrayList<SocialActivitySoap>(models.size());

		for (SocialActivity model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SocialActivitySoap[soapModels.size()]);
	}

	public SocialActivitySoap() {
	}

	public long getPrimaryKey() {
		return _activityId;
	}

	public void setPrimaryKey(long pk) {
		setActivityId(pk);
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

	private long _activityId;
	private long _activitySetId;
}