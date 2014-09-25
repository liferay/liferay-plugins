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

package com.liferay.share.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import com.liferay.share.model.EntitySocialFeed;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing EntitySocialFeed in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see EntitySocialFeed
 * @generated
 */
@ProviderType
public class EntitySocialFeedCacheModel implements CacheModel<EntitySocialFeed>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", feedClassNameId=");
		sb.append(feedClassNameId);
		sb.append(", feedClassPK=");
		sb.append(feedClassPK);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public EntitySocialFeed toEntityModel() {
		EntitySocialFeedImpl entitySocialFeedImpl = new EntitySocialFeedImpl();

		entitySocialFeedImpl.setClassNameId(classNameId);
		entitySocialFeedImpl.setClassPK(classPK);
		entitySocialFeedImpl.setFeedClassNameId(feedClassNameId);
		entitySocialFeedImpl.setFeedClassPK(feedClassPK);

		entitySocialFeedImpl.resetOriginalValues();

		return entitySocialFeedImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		classNameId = objectInput.readLong();
		classPK = objectInput.readLong();
		feedClassNameId = objectInput.readLong();
		feedClassPK = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(classNameId);
		objectOutput.writeLong(classPK);
		objectOutput.writeLong(feedClassNameId);
		objectOutput.writeLong(feedClassPK);
	}

	public long classNameId;
	public long classPK;
	public long feedClassNameId;
	public long feedClassPK;
}