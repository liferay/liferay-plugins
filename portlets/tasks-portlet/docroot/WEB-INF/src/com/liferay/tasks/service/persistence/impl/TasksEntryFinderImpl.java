/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.tasks.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.model.TasksEntryConstants;
import com.liferay.tasks.model.impl.TasksEntryImpl;
import com.liferay.tasks.service.persistence.TasksEntryFinder;
import com.liferay.tasks.service.persistence.TasksEntryUtil;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author Ryan Park
 */
public class TasksEntryFinderImpl
	extends TasksEntryFinderBaseImpl implements TasksEntryFinder {

	public static final String COUNT_BY_G_U_P_A_S_T_N =
		TasksEntryFinder.class.getName() + ".countByG_U_P_A_S_T_N";

	public static final String FIND_BY_G_U_P_A_S_T_N =
		TasksEntryFinder.class.getName() + ".findByG_U_P_A_S_T_N";

	public static final String JOIN_BY_ASSET_TAGS =
		TasksEntryFinder.class.getName() + ".joinByAssetTags";

	public static final String JOIN_BY_NOT_ASSET_TAGS =
		TasksEntryFinder.class.getName() + ".joinByNotAssetTags";

	public int countByG_U_P_A_S_T_N(
		long groupId, long userId, int priority, long assigneeUserId,
		int status, long[] assetTagIds, long[] notAssetTagIds) {

		if ((priority <= 0) && (assetTagIds.length == 0) &&
			(notAssetTagIds.length == 0)) {

			if ((userId > 0) && (assigneeUserId <= 0)) {
				return countByG_U_S(groupId, userId, status);
			}

			if ((userId <= 0) && (assigneeUserId > 0)) {
				return countByG_A_S(groupId, assigneeUserId, status);
			}
		}

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_G_U_P_A_S_T_N);

			sql = StringUtil.replace(
				sql, "[$JOIN$]", getJoin(assetTagIds, notAssetTagIds));
			sql = StringUtil.replace(
				sql, "[$ASSET_TAG_TAG_IDS$]",
				getAssetTagTagIds(assetTagIds, notAssetTagIds));

			if (assetTagIds.length > 0) {
				sql = StringUtil.replaceLast(sql, "WHERE", StringPool.BLANK);
			}

			sql = StringUtil.replace(sql, "[$GROUP_ID$]", getGroupId(groupId));
			sql = StringUtil.replace(sql, "[$USER_ID$]", getUserId(userId));
			sql = StringUtil.replace(
				sql, "[$PRIORITY$]", getPriority(priority));
			sql = StringUtil.replace(
				sql, "[$ASSIGNEE_USER_ID$]", getAssigneeUserId(assigneeUserId));

			int[] statuses = getStatuses(status);

			sql = StringUtil.replace(sql, "[$STATUS$]", getStatus(statuses));

			sql = StringUtil.replaceLast(sql, "AND", StringPool.BLANK);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if ((assetTagIds.length > 0) || (notAssetTagIds.length > 0)) {
				qPos.add(PortalUtil.getClassNameId(TasksEntry.class.getName()));
				qPos.add(assetTagIds);
				qPos.add(notAssetTagIds);
			}

			if (groupId > 0) {
				qPos.add(groupId);
			}

			if (userId > 0) {
				qPos.add(userId);
			}

			if (priority > 0) {
				qPos.add(priority);
			}

			if (assigneeUserId > 0) {
				qPos.add(assigneeUserId);
			}

			qPos.add(statuses);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<TasksEntry> findByG_U_P_A_S_T_N(
		long groupId, long userId, int priority, long assigneeUserId,
		int status, long[] assetTagIds, long[] notAssetTagIds, int start,
		int end) {

		if ((priority <= 0) && (assetTagIds.length == 0) &&
			(notAssetTagIds.length == 0)) {

			if ((userId > 0) && (assigneeUserId <= 0)) {
				return findByG_U_S(groupId, userId, status, start, end);
			}

			if ((userId <= 0) && (assigneeUserId > 0)) {
				return findByG_A_S(groupId, assigneeUserId, status, start, end);
			}
		}

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_G_U_P_A_S_T_N);

			sql = StringUtil.replace(
				sql, "[$JOIN$]", getJoin(assetTagIds, notAssetTagIds));
			sql = StringUtil.replace(
				sql, "[$ASSET_TAG_TAG_IDS$]",
				getAssetTagTagIds(assetTagIds, notAssetTagIds));

			if (assetTagIds.length > 0) {
				sql = StringUtil.replaceLast(sql, "WHERE", StringPool.BLANK);
			}

			sql = StringUtil.replace(sql, "[$GROUP_ID$]", getGroupId(groupId));
			sql = StringUtil.replace(sql, "[$USER_ID$]", getUserId(userId));
			sql = StringUtil.replace(
				sql, "[$PRIORITY$]", getPriority(priority));
			sql = StringUtil.replace(
				sql, "[$ASSIGNEE_USER_ID$]", getAssigneeUserId(assigneeUserId));

			int[] statuses = getStatuses(status);

			sql = StringUtil.replace(sql, "[$STATUS$]", getStatus(statuses));

			sql = StringUtil.replaceLast(sql, "AND", StringPool.BLANK);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("TMS_TasksEntry", TasksEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			if ((assetTagIds.length > 0) || (notAssetTagIds.length > 0)) {
				qPos.add(PortalUtil.getClassNameId(TasksEntry.class.getName()));
				qPos.add(assetTagIds);
				qPos.add(notAssetTagIds);
			}

			if (groupId > 0) {
				qPos.add(groupId);
			}

			if (userId > 0) {
				qPos.add(userId);
			}

			if (priority > 0) {
				qPos.add(priority);
			}

			if (assigneeUserId > 0) {
				qPos.add(assigneeUserId);
			}

			qPos.add(statuses);

			return (List<TasksEntry>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected int countByG_U_S(long groupId, long userId, int status) {
		if (status != TasksEntryConstants.STATUS_ALL) {
			if (groupId > 0) {
				return TasksEntryUtil.countByG_U_S(
					groupId, userId, getStatuses(status));
			}

			return TasksEntryUtil.countByU_S(userId, getStatuses(status));
		}

		if (groupId > 0) {
			return TasksEntryUtil.countByG_U(groupId, userId);
		}

		return TasksEntryUtil.countByUserId(userId);
	}

	protected int countByG_A_S(long groupId, long assigneeUserId, int status) {
		if (status != TasksEntryConstants.STATUS_ALL) {
			if (groupId > 0) {
				return TasksEntryUtil.countByG_A_S(
					groupId, assigneeUserId, getStatuses(status));
			}

			return TasksEntryUtil.countByA_S(
				assigneeUserId, getStatuses(status));
		}

		if (groupId > 0) {
			return TasksEntryUtil.countByG_A(groupId, assigneeUserId);
		}

		return TasksEntryUtil.countByAssigneeUserId(assigneeUserId);
	}

	protected List<TasksEntry> findByG_U_S(
		long groupId, long userId, int status, int start, int end) {

		if (status != TasksEntryConstants.STATUS_ALL) {
			if (groupId > 0) {
				return TasksEntryUtil.findByG_U_S(
					groupId, userId, getStatuses(status), start, end);
			}

			return TasksEntryUtil.findByU_S(
				userId, getStatuses(status), start, end);
		}

		if (groupId > 0) {
			return TasksEntryUtil.findByG_U(groupId, userId, start, end);
		}

		return TasksEntryUtil.findByUserId(userId, start, end);
	}

	protected List<TasksEntry> findByG_A_S(
		long groupId, long assigneeUserId, int status, int start, int end) {

		if (status != TasksEntryConstants.STATUS_ALL) {
			if (groupId > 0) {
				return TasksEntryUtil.findByG_A_S(
					groupId, assigneeUserId, getStatuses(status), start, end);
			}

			return TasksEntryUtil.findByA_S(
				assigneeUserId, getStatuses(status), start, end);
		}

		if (groupId > 0) {
			return TasksEntryUtil.findByG_A(
				groupId, assigneeUserId, start, end);
		}

		return TasksEntryUtil.findByAssigneeUserId(assigneeUserId, start, end);
	}

	protected String getAssetTagTagIds(
		long[] assetTagIds, boolean equalsOperator) {

		StringBundler sb = new StringBundler((assetTagIds.length * 4) + 1);

		sb.append(" (");

		for (int i = 0; i < assetTagIds.length; i++) {
			sb.append("AssetEntries_AssetTags.tagId ");

			if (equalsOperator) {
				sb.append(StringPool.EQUAL);
			}
			else {
				sb.append(StringPool.NOT_EQUAL);
			}

			sb.append(" ? ");

			if ((i + 1) != assetTagIds.length) {
				if (equalsOperator) {
					sb.append("OR ");
				}
				else {
					sb.append("AND ");
				}
			}
		}

		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	protected String getAssetTagTagIds(
		long[] assetTagIds, long[] notAssetTagIds) {

		if ((assetTagIds != null) && (assetTagIds.length > 0)) {
			return getAssetTagTagIds(assetTagIds, true);
		}

		if ((notAssetTagIds != null) && (notAssetTagIds.length > 0)) {
			return getAssetTagTagIds(notAssetTagIds, false);
		}

		return StringPool.BLANK;
	}

	protected String getAssigneeUserId(long assigneeUserId) {
		if (assigneeUserId > 0) {
			return "TMS_TasksEntry.assigneeUserId = ? AND";
		}

		return StringPool.BLANK;
	}

	protected String getGroupId(long groupId) {
		if (groupId > 0) {
			return "TMS_TasksEntry.groupId = ? AND";
		}

		return StringPool.BLANK;
	}

	protected String getJoin(long[] assetTagIds, long[] notAssetTagIds) {
		if ((assetTagIds != null) && (assetTagIds.length > 0)) {
			return CustomSQLUtil.get(JOIN_BY_ASSET_TAGS);
		}

		if ((notAssetTagIds != null) && (notAssetTagIds.length > 0)) {
			return CustomSQLUtil.get(JOIN_BY_NOT_ASSET_TAGS);
		}

		return StringPool.BLANK;
	}

	protected String getPriority(int priority) {
		if (priority > 0) {
			return "TMS_TasksEntry.priority = ? AND";
		}

		return StringPool.BLANK;
	}

	protected String getStatus(int[] statuses) {
		if (statuses.length == 0) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler((statuses.length * 2) + 1);

		sb.append(" (");

		for (int i = 0; i < statuses.length; i++) {
			sb.append("TMS_TasksEntry.status = ? ");
			sb.append("OR ");
		}

		sb.setIndex(sb.index() - 1);

		sb.append(") AND ");

		return sb.toString();
	}

	protected int[] getStatuses(int status) {
		if (status == TasksEntryConstants.STATUS_ALL) {
			return new int[0];
		}

		if (status == TasksEntryConstants.STATUS_RESOLVED) {
			return _RESOLVED_STATUS_ARRAY;
		}

		return _OPEN_STATUSES_ARRAY;
	}

	protected String getUserId(long userId) {
		if (userId > 0) {
			return "TMS_TasksEntry.userId = ? AND";
		}

		return StringPool.BLANK;
	}

	private static final int[] _OPEN_STATUSES_ARRAY = {
		TasksEntryConstants.STATUS_OPEN,
		TasksEntryConstants.STATUS_PERCENT_TWENTY,
		TasksEntryConstants.STATUS_PERCENT_SIXTY,
		TasksEntryConstants.STATUS_PERCENT_FORTY,
		TasksEntryConstants.STATUS_PERCENT_EIGHTY,
		TasksEntryConstants.STATUS_REOPENED
	};

	private static final int[] _RESOLVED_STATUS_ARRAY = {
		TasksEntryConstants.STATUS_RESOLVED
	};

}