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

	public static String FIND_BY_C_T =
		TasksEntryFinder.class.getName() + ".findByC_T";

	public int countByG_P_A_R_S_T_N(
			long groupId, int priority, long assigneeUserId,
			long reporterUserId, int status, long[] assetTagIds,
			long[] notAssetTagIds)
		throws SystemException {

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

			SQLQuery q = session.createSQLQuery(sql);

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

			Iterator<Long> itr = q.list().iterator();

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

			SQLQuery q = session.createSQLQuery(sql);

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

	protected void setTagsEntryIds(QueryPos qPos, long[] assetTagIds) {
		for (int i = 0; i < assetTagIds.length; i++) {
			qPos.add(PortalUtil.getClassNameId(TasksEntry.class.getName()));
			qPos.add(assetTagIds[i]);
		}
	}

}