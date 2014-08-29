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

package com.liferay.socialcoding.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.socialcoding.model.JIRAProject;

import java.io.Serializable;

/**
 * The cache model class for representing JIRAProject in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see JIRAProject
 * @generated
 */
public class JIRAProjectCacheModel implements CacheModel<JIRAProject>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{jiraProjectId=");
		sb.append(jiraProjectId);
		sb.append(", key=");
		sb.append(key);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	public JIRAProject toEntityModel() {
		JIRAProjectImpl jiraProjectImpl = new JIRAProjectImpl();

		jiraProjectImpl.setJiraProjectId(jiraProjectId);

		if (key == null) {
			jiraProjectImpl.setKey(StringPool.BLANK);
		}
		else {
			jiraProjectImpl.setKey(key);
		}

		if (name == null) {
			jiraProjectImpl.setName(StringPool.BLANK);
		}
		else {
			jiraProjectImpl.setName(name);
		}

		jiraProjectImpl.resetOriginalValues();

		return jiraProjectImpl;
	}

	public long jiraProjectId;
	public String key;
	public String name;
}