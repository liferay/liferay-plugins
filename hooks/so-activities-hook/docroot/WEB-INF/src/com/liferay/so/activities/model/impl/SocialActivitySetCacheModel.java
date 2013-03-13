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

package com.liferay.so.activities.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import com.liferay.so.activities.model.SocialActivitySet;

import java.io.Serializable;

/**
 * The cache model class for representing SocialActivitySet in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivitySet
 * @generated
 */
public class SocialActivitySetCacheModel implements CacheModel<SocialActivitySet>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{activitySetId=");
		sb.append(activitySetId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", type=");
		sb.append(type);
		sb.append(", activityCount=");
		sb.append(activityCount);
		sb.append("}");

		return sb.toString();
	}

	public SocialActivitySet toEntityModel() {
		SocialActivitySetImpl socialActivitySetImpl = new SocialActivitySetImpl();

		socialActivitySetImpl.setActivitySetId(activitySetId);
		socialActivitySetImpl.setGroupId(groupId);
		socialActivitySetImpl.setCompanyId(companyId);
		socialActivitySetImpl.setUserId(userId);
		socialActivitySetImpl.setCreateDate(createDate);
		socialActivitySetImpl.setModifiedDate(modifiedDate);
		socialActivitySetImpl.setClassNameId(classNameId);
		socialActivitySetImpl.setClassPK(classPK);
		socialActivitySetImpl.setType(type);
		socialActivitySetImpl.setActivityCount(activityCount);

		socialActivitySetImpl.resetOriginalValues();

		return socialActivitySetImpl;
	}

	public long activitySetId;
	public long groupId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public int type;
	public int activityCount;
}