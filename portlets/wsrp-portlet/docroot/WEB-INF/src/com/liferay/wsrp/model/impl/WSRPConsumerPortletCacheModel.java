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

import com.liferay.wsrp.model.WSRPConsumerPortlet;

import java.util.Date;

/**
 * The cache model class for representing WSRPConsumerPortlet in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumerPortlet
 * @generated
 */
public class WSRPConsumerPortletCacheModel implements CacheModel<WSRPConsumerPortlet> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", wsrpConsumerPortletId=");
		sb.append(wsrpConsumerPortletId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", wsrpConsumerId=");
		sb.append(wsrpConsumerId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", portletHandle=");
		sb.append(portletHandle);
		sb.append("}");

		return sb.toString();
	}

	public WSRPConsumerPortlet toEntityModel() {
		WSRPConsumerPortletImpl wsrpConsumerPortletImpl = new WSRPConsumerPortletImpl();

		if (uuid == null) {
			wsrpConsumerPortletImpl.setUuid(StringPool.BLANK);
		}
		else {
			wsrpConsumerPortletImpl.setUuid(uuid);
		}

		wsrpConsumerPortletImpl.setWsrpConsumerPortletId(wsrpConsumerPortletId);
		wsrpConsumerPortletImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			wsrpConsumerPortletImpl.setCreateDate(null);
		}
		else {
			wsrpConsumerPortletImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			wsrpConsumerPortletImpl.setModifiedDate(null);
		}
		else {
			wsrpConsumerPortletImpl.setModifiedDate(new Date(modifiedDate));
		}

		wsrpConsumerPortletImpl.setWsrpConsumerId(wsrpConsumerId);

		if (name == null) {
			wsrpConsumerPortletImpl.setName(StringPool.BLANK);
		}
		else {
			wsrpConsumerPortletImpl.setName(name);
		}

		if (portletHandle == null) {
			wsrpConsumerPortletImpl.setPortletHandle(StringPool.BLANK);
		}
		else {
			wsrpConsumerPortletImpl.setPortletHandle(portletHandle);
		}

		wsrpConsumerPortletImpl.resetOriginalValues();

		return wsrpConsumerPortletImpl;
	}

	public String uuid;
	public long wsrpConsumerPortletId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public long wsrpConsumerId;
	public String name;
	public String portletHandle;
}