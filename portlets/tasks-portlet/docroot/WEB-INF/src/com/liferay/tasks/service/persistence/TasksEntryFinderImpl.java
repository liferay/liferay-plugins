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

package com.liferay.tasks.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.util.PortalUtil;
import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.model.TasksEntryConstants;
import com.liferay.tasks.model.impl.TasksEntryImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author Ryan Park
 */
public class TasksEntryFinderImpl
	extends BasePersistenceImpl<TasksEntry> implements TasksEntryFinder {

	public static final String FIND_BY_C_T =
		TasksEntryFinder.class.getName() + ".findByC_T";

	public int countByG_P_A_R_S_T_N(
			long groupId, int priority, long assigneeUserId,
			long reporterUserId, int status, long[] assetTagIds,
			long[] notAssetTagIds)
		throws SystemException {

		if ((assetTagIds.length == 0) && (notAssetTagIds.length == 0) &&
			(priority <= 0)) {

			if ((assigneeUserId > 0) && (reporterUserId <= 0)) {
				return countByG_A_S(groupId, assigneeUserId, status);
			}

			if ((reporterUserId > 0) && (assigneeUserId <= 0)) {
				return countByG_R_S(groupId, reporterUserId, status);
			}
		}

		Session session = null;

		try {
			session = openSession();

			StringBuilder sb = new StringBuilder();

			sb.append("SELECT COUNT(DISTINCT tasksEntryId) AS COUNT_VALUE ");
			sb.append("FROM TMS_TasksEntry WHERE");

			if (groupId > 0) {
				sb.append(" AND TMS_TasksEntry.groupId = ?");
			}

			if (priority > 0) {
				sb.append(" AND TMS_TasksEntry.priority = ?");
			}

			if (assigneeUserId > 0) {
				sb.append(" AND TMS_TasksEntry.assigneeUserId = ?");
			}

			if (reporterUserId > 0) {
				sb.append(" AND TMS_TasksEntry.userId = ?");
			}

			if (status == TasksEntryConstants.STATUS_ALL) {
			}
			else if (status == TasksEntryConstants.STATUS_RESOLVED) {
				sb.append(" AND TMS_TasksEntry.finishDate IS NOT NULL");
			}
			else {
				sb.append(" AND TMS_TasksEntry.finishDate IS NULL");
			}

			if (assetTagIds.length > 0) {
				sb.append(" AND TMS_TasksEntry.tasksEntryId IN (");

				for (int i = 0; i < assetTagIds.length; i++) {
					sb.append(CustomSQLUtil.get(FIND_BY_C_T));

					if ((i + 1) < assetTagIds.length) {
						sb.append(" OR AssetEntry.classPK IN (");
					}
				}

				for (int i = 0; i < assetTagIds.length; i++) {
					sb.append(StringPool.CLOSE_PARENTHESIS);
				}
			}

			if (notAssetTagIds.length > 0) {
				sb.append(" AND (");

				for (int i = 0; i < notAssetTagIds.length; i++) {
					sb.append("TMS_TasksEntry.tasksEntryId NOT IN (");
					sb.append(CustomSQLUtil.get(FIND_BY_C_T));
					sb.append(StringPool.CLOSE_PARENTHESIS);

					if ((i + 1) < notAssetTagIds.length) {
						sb.append(" OR ");
					}
				}

				sb.append(StringPool.CLOSE_PARENTHESIS);
			}

			String sql = sb.toString();

			sql = StringUtil.replaceFirst(sql, " WHERE AND ", " WHERE ");

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (groupId > 0) {
				qPos.add(groupId);
			}

			if (priority > 0) {
				qPos.add(priority);
			}

			if (assigneeUserId > 0) {
				qPos.add(assigneeUserId);
			}

			if (reporterUserId > 0) {
				qPos.add(reporterUserId);
			}

			setTagsEntryIds(qPos, assetTagIds);
			setTagsEntryIds(qPos, notAssetTagIds);

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

	public List<TasksEntry> findByG_P_A_R_S_T_N(
			long groupId, int priority, long assigneeUserId,
			long reporterUserId, int status, long[] assetTagIds,
			long[] notAssetTagIds, int start, int end)
		throws SystemException {

		if ((assetTagIds.length == 0) && (notAssetTagIds.length == 0) &&
			(priority <= 0)) {

			if ((assigneeUserId > 0) && (reporterUserId <= 0)) {
				return findByG_A_S(groupId, assigneeUserId, status, start, end);
			}

			if ((reporterUserId > 0) && (assigneeUserId <= 0)) {
				return findByG_R_S(groupId, reporterUserId, status, start, end);
			}
		}

		Session session = null;

		try {
			session = openSession();

			StringBuilder sb = new StringBuilder();

			sb.append("SELECT DISTINCT {TMS_TasksEntry.*} ");
			sb.append("FROM TMS_TasksEntry WHERE");

			if (groupId > 0) {
				sb.append(" AND TMS_TasksEntry.groupId = ?");
			}

			if (priority > 0) {
				sb.append(" AND TMS_TasksEntry.priority = ?");
			}

			if (assigneeUserId > 0) {
				sb.append(" AND TMS_TasksEntry.assigneeUserId = ?");
			}

			if (reporterUserId > 0) {
				sb.append(" AND TMS_TasksEntry.userId = ?");
			}

			if (status == TasksEntryConstants.STATUS_ALL) {
			}
			else if (status == TasksEntryConstants.STATUS_RESOLVED) {
				sb.append(" AND TMS_TasksEntry.finishDate IS NOT NULL");
			}
			else {
				sb.append(" AND TMS_TasksEntry.finishDate IS NULL");
			}

			if (assetTagIds.length > 0) {
				sb.append(" AND TMS_TasksEntry.tasksEntryId IN (");

				for (int i = 0; i < assetTagIds.length; i++) {
					sb.append(CustomSQLUtil.get(FIND_BY_C_T));

					if ((i + 1) < assetTagIds.length) {
						sb.append(" OR AssetEntry.classPK IN (");
					}
				}

				for (int i = 0; i < assetTagIds.length; i++) {
					sb.append(StringPool.CLOSE_PARENTHESIS);
				}
			}

			if (notAssetTagIds.length > 0) {
				sb.append(" AND (");

				for (int i = 0; i < notAssetTagIds.length; i++) {
					sb.append("TMS_TasksEntry.tasksEntryId NOT IN (");
					sb.append(CustomSQLUtil.get(FIND_BY_C_T));
					sb.append(StringPool.CLOSE_PARENTHESIS);

					if ((i + 1) < notAssetTagIds.length) {
						sb.append(" OR ");
					}
				}

				sb.append(StringPool.CLOSE_PARENTHESIS);
			}

			sb.append(" ORDER BY ");

			sb.append("priority ASC, ");
			sb.append("dueDate ASC, ");
			sb.append("createDate ASC");

			String sql = sb.toString();

			sql = StringUtil.replaceFirst(sql, " WHERE AND ", " WHERE ");

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("TMS_TasksEntry", TasksEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			if (groupId > 0) {
				qPos.add(groupId);
			}

			if (priority > 0) {
				qPos.add(priority);
			}

			if (assigneeUserId > 0) {
				qPos.add(assigneeUserId);
			}

			if (reporterUserId > 0) {
				qPos.add(reporterUserId);
			}

			setTagsEntryIds(qPos, assetTagIds);
			setTagsEntryIds(qPos, notAssetTagIds);

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

	protected int countByG_A_S(long groupId, long assigneeUserId, int status)
		throws SystemException {

		if (status != TasksEntryConstants.STATUS_ALL) {
			if (groupId > 0) {
				return TasksEntryUtil.countByG_A_S(
					groupId, assigneeUserId, getStatusArray(status));
			}

			return TasksEntryUtil.countByA_S(
				assigneeUserId, getStatusArray(status));
		}

		if (groupId > 0) {
			return TasksEntryUtil.countByG_A(groupId, assigneeUserId);
		}

		return TasksEntryUtil.countByAssigneeUserId(assigneeUserId);
	}

	protected int countByG_R_S(long groupId, long reporterUserId, int status)
		throws SystemException {

		if (status != TasksEntryConstants.STATUS_ALL) {
			if (groupId > 0) {
				return TasksEntryUtil.countByG_U_S(
					groupId, reporterUserId, getStatusArray(status));
			}

			return TasksEntryUtil.countByU_S(
				reporterUserId, getStatusArray(status));
		}

		if (groupId > 0) {
			return TasksEntryUtil.countByG_U(groupId, reporterUserId);
		}

		return TasksEntryUtil.countByUserId(reporterUserId);
	}

	protected List<TasksEntry> findByG_A_S(
			long groupId, long assigneeUserId, int status, int start, int end)
		throws SystemException {

		if (status != TasksEntryConstants.STATUS_ALL) {
			if (groupId > 0) {
				return TasksEntryUtil.findByG_A_S(
					groupId, assigneeUserId, getStatusArray(status), start,
					end);
			}

			return TasksEntryUtil.findByA_S(
				assigneeUserId, getStatusArray(status), start, end);
		}

		if (groupId > 0) {
			return TasksEntryUtil.findByG_A(
				groupId, assigneeUserId, start, end);
		}

		return TasksEntryUtil.findByAssigneeUserId(assigneeUserId, start, end);
	}

	protected List<TasksEntry> findByG_R_S(
			long groupId, long reporterUserId, int status, int start, int end)
		throws SystemException {

		if (status != TasksEntryConstants.STATUS_ALL) {
			if (groupId > 0) {
				return TasksEntryUtil.findByG_U_S(
					groupId, reporterUserId, getStatusArray(status), start,
					end);
			}

			return TasksEntryUtil.findByU_S(
				reporterUserId, getStatusArray(status), start, end);
		}

		if (groupId > 0) {
			return TasksEntryUtil.findByG_U(
				groupId, reporterUserId, start, end);
		}

		return TasksEntryUtil.findByUserId(reporterUserId, start, end);
	}

	protected int[] getStatusArray(int status) {
		if (status == TasksEntryConstants.STATUS_ALL) {
			return null;
		}

		if (status == TasksEntryConstants.STATUS_RESOLVED) {
			return _RESOLVED_STATUS_ARRAY;
		}

		return _OPEN_STATUSES_ARRAY;
	}

	protected void setTagsEntryIds(QueryPos qPos, long[] assetTagIds) {
		for (int i = 0; i < assetTagIds.length; i++) {
			qPos.add(PortalUtil.getClassNameId(TasksEntry.class.getName()));
			qPos.add(assetTagIds[i]);
		}
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