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

package com.liferay.share.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.share.service.persistence.EntitySocialFeedPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.share.service.http.EntitySocialFeedServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.share.service.http.EntitySocialFeedServiceSoap
 * @generated
 */
@ProviderType
public class EntitySocialFeedSoap implements Serializable {
	public static EntitySocialFeedSoap toSoapModel(EntitySocialFeed model) {
		EntitySocialFeedSoap soapModel = new EntitySocialFeedSoap();

		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setFeedClassNameId(model.getFeedClassNameId());
		soapModel.setFeedClassPK(model.getFeedClassPK());

		return soapModel;
	}

	public static EntitySocialFeedSoap[] toSoapModels(EntitySocialFeed[] models) {
		EntitySocialFeedSoap[] soapModels = new EntitySocialFeedSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EntitySocialFeedSoap[][] toSoapModels(
		EntitySocialFeed[][] models) {
		EntitySocialFeedSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EntitySocialFeedSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EntitySocialFeedSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EntitySocialFeedSoap[] toSoapModels(
		List<EntitySocialFeed> models) {
		List<EntitySocialFeedSoap> soapModels = new ArrayList<EntitySocialFeedSoap>(models.size());

		for (EntitySocialFeed model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EntitySocialFeedSoap[soapModels.size()]);
	}

	public EntitySocialFeedSoap() {
	}

	public EntitySocialFeedPK getPrimaryKey() {
		return new EntitySocialFeedPK(_classNameId, _classPK, _feedClassNameId,
			_feedClassPK);
	}

	public void setPrimaryKey(EntitySocialFeedPK pk) {
		setClassNameId(pk.classNameId);
		setClassPK(pk.classPK);
		setFeedClassNameId(pk.feedClassNameId);
		setFeedClassPK(pk.feedClassPK);
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public long getFeedClassNameId() {
		return _feedClassNameId;
	}

	public void setFeedClassNameId(long feedClassNameId) {
		_feedClassNameId = feedClassNameId;
	}

	public long getFeedClassPK() {
		return _feedClassPK;
	}

	public void setFeedClassPK(long feedClassPK) {
		_feedClassPK = feedClassPK;
	}

	private long _classNameId;
	private long _classPK;
	private long _feedClassNameId;
	private long _feedClassPK;
}