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

package com.liferay.wsrp.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.wsrp.model.WSRPConsumer;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WSRPConsumer in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumer
 * @generated
 */
@ProviderType
public class WSRPConsumerCacheModel implements CacheModel<WSRPConsumer>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WSRPConsumerCacheModel)) {
			return false;
		}

		WSRPConsumerCacheModel wsrpConsumerCacheModel = (WSRPConsumerCacheModel)obj;

		if (wsrpConsumerId == wsrpConsumerCacheModel.wsrpConsumerId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, wsrpConsumerId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

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
		sb.append(", forwardHeaders=");
		sb.append(forwardHeaders);
		sb.append(", markupCharacterSets=");
		sb.append(markupCharacterSets);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
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

		if (forwardHeaders == null) {
			wsrpConsumerImpl.setForwardHeaders(StringPool.BLANK);
		}
		else {
			wsrpConsumerImpl.setForwardHeaders(forwardHeaders);
		}

		if (markupCharacterSets == null) {
			wsrpConsumerImpl.setMarkupCharacterSets(StringPool.BLANK);
		}
		else {
			wsrpConsumerImpl.setMarkupCharacterSets(markupCharacterSets);
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			wsrpConsumerImpl.setLastPublishDate(null);
		}
		else {
			wsrpConsumerImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		wsrpConsumerImpl.resetOriginalValues();

		return wsrpConsumerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		wsrpConsumerId = objectInput.readLong();
		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		url = objectInput.readUTF();
		wsdl = objectInput.readUTF();
		registrationContextString = objectInput.readUTF();
		registrationPropertiesString = objectInput.readUTF();
		forwardCookies = objectInput.readUTF();
		forwardHeaders = objectInput.readUTF();
		markupCharacterSets = objectInput.readUTF();
		lastPublishDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(wsrpConsumerId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (url == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(url);
		}

		if (wsdl == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(wsdl);
		}

		if (registrationContextString == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(registrationContextString);
		}

		if (registrationPropertiesString == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(registrationPropertiesString);
		}

		if (forwardCookies == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forwardCookies);
		}

		if (forwardHeaders == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forwardHeaders);
		}

		if (markupCharacterSets == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(markupCharacterSets);
		}

		objectOutput.writeLong(lastPublishDate);
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
	public String forwardHeaders;
	public String markupCharacterSets;
	public long lastPublishDate;
}