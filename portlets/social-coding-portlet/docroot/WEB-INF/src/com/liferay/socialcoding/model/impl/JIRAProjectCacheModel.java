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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.liferay.socialcoding.model.JIRAProject;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing JIRAProject in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see JIRAProject
 * @generated
 */
@ProviderType
public class JIRAProjectCacheModel implements CacheModel<JIRAProject>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JIRAProjectCacheModel)) {
			return false;
		}

		JIRAProjectCacheModel jiraProjectCacheModel = (JIRAProjectCacheModel)obj;

		if (jiraProjectId == jiraProjectCacheModel.jiraProjectId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, jiraProjectId);
	}

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

	@Override
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

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		jiraProjectId = objectInput.readLong();
		key = objectInput.readUTF();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(jiraProjectId);

		if (key == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(key);
		}

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long jiraProjectId;
	public String key;
	public String name;
}