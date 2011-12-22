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

package com.liferay.marketplace.model.impl;

import com.liferay.marketplace.model.Module;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing Module in entity cache.
 *
 * @author Ryan Park
 * @see Module
 * @generated
 */
public class ModuleCacheModel implements CacheModel<Module>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", moduleId=");
		sb.append(moduleId);
		sb.append(", appId=");
		sb.append(appId);
		sb.append(", contextName=");
		sb.append(contextName);
		sb.append("}");

		return sb.toString();
	}

	public Module toEntityModel() {
		ModuleImpl moduleImpl = new ModuleImpl();

		if (uuid == null) {
			moduleImpl.setUuid(StringPool.BLANK);
		}
		else {
			moduleImpl.setUuid(uuid);
		}

		moduleImpl.setModuleId(moduleId);
		moduleImpl.setAppId(appId);

		if (contextName == null) {
			moduleImpl.setContextName(StringPool.BLANK);
		}
		else {
			moduleImpl.setContextName(contextName);
		}

		moduleImpl.resetOriginalValues();

		return moduleImpl;
	}

	public String uuid;
	public long moduleId;
	public long appId;
	public String contextName;
}