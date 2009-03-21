/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.tms.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.util.PortalUtil;
import com.liferay.tms.model.TasksEntry;
import com.liferay.tms.model.impl.TasksEntryImpl;
import com.liferay.tms.tasks.util.TasksConstants;

import java.util.Iterator;
import java.util.List;

/**
 * <a href="TasksEntryFinderImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Ryan Park
 *
 */
public class TasksEntryFinderImpl
	extends BasePersistenceImpl implements TasksEntryFinder {

	public TasksEntryFinderImpl() {
		try {
			_tagsAssetClassPKFinderSQL = (String)PortalClassInvoker.invoke(
				"com.liferay.util.dao.orm.CustomSQLUtil",
				"get",
				"com.liferay.portlet.tags.service.persistence." +
					"TagsAssetClassPKFinder.findByE_C");
		}
		catch (Exception e) {
			_log.error(e,e);
		}
	}

	public int countByG_P_A_R_S_T_N(
			long groupId, int priority, long assigneeUserId,
			long reporterUserId, int status, long[] tagsEntryIds,
			long[] notTagsEntryIds)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			StringBuilder sb = new StringBuilder();

			sb.append("SELECT COUNT(DISTINCT tasksEntryId) AS COUNT_VALUE ");
			sb.append("FROM TasksEntry WHERE");

			if (groupId > 0) {
				sb.append(" AND TasksEntry.groupId = ?");
			}

			if (priority > 0) {
				sb.append(" AND TasksEntry.priority = ?");
			}

			if (assigneeUserId > 0) {
				sb.append(" AND TasksEntry.assigneeUserId = ?");
			}

			if (reporterUserId > 0) {
				sb.append(" AND TasksEntry.reporterUserId = ?");
			}

			if (status == TasksConstants.STATUS_ALL) {
			}
			else if (status == TasksConstants.STATUS_RESOLVED) {
				sb.append(" AND TasksEntry.finishDate IS NOT NULL");
			}
			else {
				sb.append(" AND TasksEntry.finishDate IS NULL");
			}

			if (tagsEntryIds.length > 0) {
				sb.append(" AND TasksEntry.entryId IN (");

				for (int i = 0; i < tagsEntryIds.length; i++) {
					sb.append(_tagsAssetClassPKFinderSQL);

					if ((i + 1) < tagsEntryIds.length) {
						sb.append(" AND TagsAsset.classPK IN (");
					}
				}

				for (int i = 0; i < tagsEntryIds.length; i++) {
					if ((i + 1) < tagsEntryIds.length) {
						sb.append(StringPool.CLOSE_PARENTHESIS);
					}
				}
			}

			if (notTagsEntryIds.length > 0) {
				sb.append(" AND (");

				for (int i = 0; i < notTagsEntryIds.length; i++) {
					sb.append("TasksEntry.entryId NOT IN (");
					sb.append(_tagsAssetClassPKFinderSQL);
					sb.append(StringPool.CLOSE_PARENTHESIS);

					if ((i + 1) < notTagsEntryIds.length) {
						sb.append(" OR ");
					}
				}

				sb.append(StringPool.CLOSE_PARENTHESIS);
			}

			String sql = sb.toString();

			sql = StringUtil.replaceFirst(sql, " WHERE AND ", " WHERE ");

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("TasksEntry", TasksEntryImpl.class);

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

			setTagsEntryIds(qPos, tagsEntryIds);
			setTagsEntryIds(qPos, notTagsEntryIds);

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
			long reporterUserId, int status, long[] tagsEntryIds,
			long[] notTagsEntryIds, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			StringBuilder sb = new StringBuilder();

			sb.append("SELECT DISTINCT {tasksEntry.*} ");
			sb.append("FROM TasksEntry WHERE");

			if (groupId > 0) {
				sb.append(" AND TasksEntry.groupId = ?");
			}

			if (priority > 0) {
				sb.append(" AND TasksEntry.priority = ?");
			}

			if (assigneeUserId > 0) {
				sb.append(" AND TasksEntry.assigneeUserId = ?");
			}

			if (reporterUserId > 0) {
				sb.append(" AND TasksEntry.reporterUserId = ?");
			}

			if (status == TasksConstants.STATUS_ALL) {
			}
			else if (status == TasksConstants.STATUS_RESOLVED) {
				sb.append(" AND TasksEntry.finishDate IS NOT NULL");
			}
			else {
				sb.append(" AND TasksEntry.finishDate IS NULL");
			}

			if (tagsEntryIds.length > 0) {
				sb.append(" AND TasksEntry.entryId IN (");

				for (int i = 0; i < tagsEntryIds.length; i++) {
					sb.append(_tagsAssetClassPKFinderSQL);

					if ((i + 1) < tagsEntryIds.length) {
						sb.append(" AND TagsAsset.classPK IN (");
					}
				}

				for (int i = 0; i < tagsEntryIds.length; i++) {
					if ((i + 1) < tagsEntryIds.length) {
						sb.append(StringPool.CLOSE_PARENTHESIS);
					}
				}
			}

			if (notTagsEntryIds.length > 0) {
				sb.append(" AND (");

				for (int i = 0; i < notTagsEntryIds.length; i++) {
					sb.append("TasksEntry.entryId NOT IN (");
					sb.append(_tagsAssetClassPKFinderSQL);
					sb.append(StringPool.CLOSE_PARENTHESIS);

					if ((i + 1) < notTagsEntryIds.length) {
						sb.append(" OR ");
					}
				}

				sb.append(StringPool.CLOSE_PARENTHESIS);
			}

			String sql = sb.toString();

			sql = StringUtil.replaceFirst(sql, " WHERE AND ", " WHERE ");

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("TasksEntry", TasksEntryImpl.class);

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

			setTagsEntryIds(qPos, tagsEntryIds);
			setTagsEntryIds(qPos, notTagsEntryIds);

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

	protected void setTagsEntryIds(QueryPos qPos, long[] tagsEntryIds) {
		for (int i = 0; i < tagsEntryIds.length; i++) {
			qPos.add(tagsEntryIds[i]);
			qPos.add(PortalUtil.getClassNameId(TasksEntry.class.getName()));
		}
	}

	private String _tagsAssetClassPKFinderSQL;

}