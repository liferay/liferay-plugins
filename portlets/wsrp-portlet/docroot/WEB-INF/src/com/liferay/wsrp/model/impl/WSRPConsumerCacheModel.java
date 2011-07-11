/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.wsrp.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.wsrp.model.WSRPConsumer;

import java.util.Date;

/**
 * The cache model class for representing WSRPConsumer in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumer
 * @generated
 */
public class WSRPConsumerCacheModel implements CacheModel<WSRPConsumer> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", wsrpConsumerId=");
		sb.append(wsrpConsumerId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", url=");
		sb.append(url);
		sb.append(", wsdl=");
		sb.append(wsdl);
		sb.append(", registrationContextString=");
		sb.append(registrationContextString);
		sb.append(", registrationPropertiesString=");
		sb.append(registrationPropertiesString);
		sb.append(", forwardCookies=");
		sb.append(forwardCookies);
		sb.append("}");

		return sb.toString();
	}

	public WSRPConsumer toEntityModel() {
		WSRPConsumerImpl wsrpConsumerImpl = new WSRPConsumerImpl();

		if (uuid == null) {
			wsrpConsumerImpl.setUuid(StringPool.BLANK);
		}
		else {
			wsrpConsumerImpl.setUuid(uuid);
		}

		wsrpConsumerImpl.setWsrpConsumerId(wsrpConsumerId);
		wsrpConsumerImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			wsrpConsumerImpl.setCreateDate(null);
		}
		else {
			wsrpConsumerImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			wsrpConsumerImpl.setModifiedDate(null);
		}
		else {
			wsrpConsumerImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			wsrpConsumerImpl.setName(StringPool.BLANK);
		}
		else {
			wsrpConsumerImpl.setName(name);
		}

		if (url == null) {
			wsrpConsumerImpl.setUrl(StringPool.BLANK);
		}
		else {
			wsrpConsumerImpl.setUrl(url);
		}

		if (wsdl == null) {
			wsrpConsumerImpl.setWsdl(StringPool.BLANK);
		}
		else {
			wsrpConsumerImpl.setWsdl(wsdl);
		}

		if (registrationContextString == null) {
			wsrpConsumerImpl.setRegistrationContextString(StringPool.BLANK);
		}
		else {
			wsrpConsumerImpl.setRegistrationContextString(registrationContextString);
		}

		if (registrationPropertiesString == null) {
			wsrpConsumerImpl.setRegistrationPropertiesString(StringPool.BLANK);
		}
		else {
			wsrpConsumerImpl.setRegistrationPropertiesString(registrationPropertiesString);
		}

		if (forwardCookies == null) {
			wsrpConsumerImpl.setForwardCookies(StringPool.BLANK);
		}
		else {
			wsrpConsumerImpl.setForwardCookies(forwardCookies);
		}

		wsrpConsumerImpl.resetOriginalValues();

		return wsrpConsumerImpl;
	}

	public String uuid;
	public long wsrpConsumerId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String url;
	public String wsdl;
	public String registrationContextString;
	public String registrationPropertiesString;
	public String forwardCookies;
}