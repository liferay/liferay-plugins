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

package com.liferay.akismet.model.impl;

import com.liferay.akismet.model.AkismetData;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AkismetData in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AkismetData
 * @generated
 */
public class AkismetDataCacheModel implements CacheModel<AkismetData>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{akismetDataId=");
		sb.append(akismetDataId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", type=");
		sb.append(type);
		sb.append(", permalink=");
		sb.append(permalink);
		sb.append(", referrer=");
		sb.append(referrer);
		sb.append(", userAgent=");
		sb.append(userAgent);
		sb.append(", userIP=");
		sb.append(userIP);
		sb.append(", userURL=");
		sb.append(userURL);
		sb.append("}");

		return sb.toString();
	}

	public AkismetData toEntityModel() {
		AkismetDataImpl akismetDataImpl = new AkismetDataImpl();

		akismetDataImpl.setAkismetDataId(akismetDataId);

		if (modifiedDate == Long.MIN_VALUE) {
			akismetDataImpl.setModifiedDate(null);
		}
		else {
			akismetDataImpl.setModifiedDate(new Date(modifiedDate));
		}

		akismetDataImpl.setClassNameId(classNameId);
		akismetDataImpl.setClassPK(classPK);

		if (type == null) {
			akismetDataImpl.setType(StringPool.BLANK);
		}
		else {
			akismetDataImpl.setType(type);
		}

		if (permalink == null) {
			akismetDataImpl.setPermalink(StringPool.BLANK);
		}
		else {
			akismetDataImpl.setPermalink(permalink);
		}

		if (referrer == null) {
			akismetDataImpl.setReferrer(StringPool.BLANK);
		}
		else {
			akismetDataImpl.setReferrer(referrer);
		}

		if (userAgent == null) {
			akismetDataImpl.setUserAgent(StringPool.BLANK);
		}
		else {
			akismetDataImpl.setUserAgent(userAgent);
		}

		if (userIP == null) {
			akismetDataImpl.setUserIP(StringPool.BLANK);
		}
		else {
			akismetDataImpl.setUserIP(userIP);
		}

		if (userURL == null) {
			akismetDataImpl.setUserURL(StringPool.BLANK);
		}
		else {
			akismetDataImpl.setUserURL(userURL);
		}

		akismetDataImpl.resetOriginalValues();

		return akismetDataImpl;
	}

	public long akismetDataId;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public String type;
	public String permalink;
	public String referrer;
	public String userAgent;
	public String userIP;
	public String userURL;
}