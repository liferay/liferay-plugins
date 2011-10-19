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

package com.liferay.socialcoding.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.socialcoding.model.JIRAChangeItem;

import java.io.Serializable;

/**
 * The cache model class for representing JIRAChangeItem in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see JIRAChangeItem
 * @generated
 */
public class JIRAChangeItemCacheModel implements CacheModel<JIRAChangeItem>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{jiraChangeItemId=");
		sb.append(jiraChangeItemId);
		sb.append(", jiraChangeGroupId=");
		sb.append(jiraChangeGroupId);
		sb.append(", field=");
		sb.append(field);
		sb.append(", oldValue=");
		sb.append(oldValue);
		sb.append(", oldString=");
		sb.append(oldString);
		sb.append(", newValue=");
		sb.append(newValue);
		sb.append(", newString=");
		sb.append(newString);
		sb.append("}");

		return sb.toString();
	}

	public JIRAChangeItem toEntityModel() {
		JIRAChangeItemImpl jiraChangeItemImpl = new JIRAChangeItemImpl();

		jiraChangeItemImpl.setJiraChangeItemId(jiraChangeItemId);
		jiraChangeItemImpl.setJiraChangeGroupId(jiraChangeGroupId);

		if (field == null) {
			jiraChangeItemImpl.setField(StringPool.BLANK);
		}
		else {
			jiraChangeItemImpl.setField(field);
		}

		if (oldValue == null) {
			jiraChangeItemImpl.setOldValue(StringPool.BLANK);
		}
		else {
			jiraChangeItemImpl.setOldValue(oldValue);
		}

		if (oldString == null) {
			jiraChangeItemImpl.setOldString(StringPool.BLANK);
		}
		else {
			jiraChangeItemImpl.setOldString(oldString);
		}

		if (newValue == null) {
			jiraChangeItemImpl.setNewValue(StringPool.BLANK);
		}
		else {
			jiraChangeItemImpl.setNewValue(newValue);
		}

		if (newString == null) {
			jiraChangeItemImpl.setNewString(StringPool.BLANK);
		}
		else {
			jiraChangeItemImpl.setNewString(newString);
		}

		jiraChangeItemImpl.resetOriginalValues();

		return jiraChangeItemImpl;
	}

	public long jiraChangeItemId;
	public long jiraChangeGroupId;
	public String field;
	public String oldValue;
	public String oldString;
	public String newValue;
	public String newString;
}