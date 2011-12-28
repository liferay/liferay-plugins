/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.opensocial.model.impl;

import com.liferay.opensocial.model.Gadget;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Gadget in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Gadget
 * @generated
 */
public class GadgetCacheModel implements CacheModel<Gadget>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", gadgetId=");
		sb.append(gadgetId);
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
		sb.append(", portletCategoryNames=");
		sb.append(portletCategoryNames);
		sb.append("}");

		return sb.toString();
	}

	public Gadget toEntityModel() {
		GadgetImpl gadgetImpl = new GadgetImpl();

		if (uuid == null) {
			gadgetImpl.setUuid(StringPool.BLANK);
		}
		else {
			gadgetImpl.setUuid(uuid);
		}

		gadgetImpl.setGadgetId(gadgetId);
		gadgetImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			gadgetImpl.setCreateDate(null);
		}
		else {
			gadgetImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			gadgetImpl.setModifiedDate(null);
		}
		else {
			gadgetImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			gadgetImpl.setName(StringPool.BLANK);
		}
		else {
			gadgetImpl.setName(name);
		}

		if (url == null) {
			gadgetImpl.setUrl(StringPool.BLANK);
		}
		else {
			gadgetImpl.setUrl(url);
		}

		if (portletCategoryNames == null) {
			gadgetImpl.setPortletCategoryNames(StringPool.BLANK);
		}
		else {
			gadgetImpl.setPortletCategoryNames(portletCategoryNames);
		}

		gadgetImpl.resetOriginalValues();

		return gadgetImpl;
	}

	public String uuid;
	public long gadgetId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String url;
	public String portletCategoryNames;
}