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

package com.liferay.sync.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.model.impl.SyncDLObjectImpl;
import com.liferay.sync.service.persistence.SyncDLObjectFinder;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.List;

/**
 * @author Michael Young
 * @author Shinn Lok
 */
public class SyncDLObjectFinderImpl
	extends BasePersistenceImpl<SyncDLObject> implements SyncDLObjectFinder {

	public static final String FIND_BY_DELETE_EVENT =
		SyncDLObjectFinder.class.getName() + ".findByDeleteEvent";

	public static final String FIND_BY_FILE_OR_PWC_TYPE =
		SyncDLObjectFinder.class.getName() + ".findByFileOrPWCType";

	public static final String FIND_BY_FOLDER_TYPE =
		SyncDLObjectFinder.class.getName() + ".findByFolderType";

	@Override
	public List<SyncDLObject> filterFindByC_R(
		long companyId, long repositoryId) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_FOLDER_TYPE);

			sql = sql.concat(_EVENT_SQL);

			sql = InlineSQLHelperUtil.replacePermissionCheck(
				sql, DLFolder.class.getName(), "SyncDLObject.typePK", null,
				"SyncDLObject.repositoryId", new long[] {repositoryId}, null);

			sql = StringUtil.replace(
				sql, _PARENT_FOLDER_ID_SQL, StringPool.BLANK);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("SyncDLObject", SyncDLObjectImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(0);
			qPos.add(repositoryId);

			return q.list();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<SyncDLObject> filterFindByC_M_R_P(
		long companyId, long modifiedTime, long repositoryId,
		long parentFolderId) {

		Session session = null;

		try {
			session = openSession();

			StringBundler sb = new StringBundler(5);

			String sql = StringPool.BLANK;

			if (modifiedTime != -1) {
				sql = CustomSQLUtil.get(FIND_BY_DELETE_EVENT);

				sb.append(sql);
				sb.append(" UNION ALL ");
			}

			sql = CustomSQLUtil.get(FIND_BY_FOLDER_TYPE);

			if (modifiedTime == -1) {
				sql = sql + " AND (SyncDLObject.event != 'trash') ";
			}

			sql = InlineSQLHelperUtil.replacePermissionCheck(
				sql, DLFolder.class.getName(), "SyncDLObject.typePK", null,
				"SyncDLObject.repositoryId", new long[] {repositoryId},
				null);

			sb.append(sql);
			sb.append(" UNION ALL ");

			sql = CustomSQLUtil.get(FIND_BY_FILE_OR_PWC_TYPE);

			if (modifiedTime == -1) {
				sql = sql + " AND (SyncDLObject.event != 'trash') ";
			}

			sql = InlineSQLHelperUtil.replacePermissionCheck(
				sql, DLFileEntry.class.getName(), "SyncDLObject.typePK", null,
				"SyncDLObject.repositoryId", new long[] {repositoryId}, null);

			sb.append(sql);

			sql = sb.toString();

			if (parentFolderId == -1) {
				sql = StringUtil.replace(
					sql, _PARENT_FOLDER_ID_SQL, StringPool.BLANK);
			}

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("SyncDLObject", SyncDLObjectImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			if (modifiedTime != -1) {
				qPos.add(companyId);
				qPos.add(modifiedTime);
				qPos.add(repositoryId);

				if (parentFolderId != -1) {
					qPos.add(parentFolderId);
				}
			}

			qPos.add(companyId);
			qPos.add(modifiedTime);
			qPos.add(repositoryId);

			if (parentFolderId != -1) {
				qPos.add(parentFolderId);
			}

			qPos.add(companyId);
			qPos.add(modifiedTime);
			qPos.add(repositoryId);

			if (parentFolderId != -1) {
				qPos.add(parentFolderId);
			}

			qPos.add(PrincipalThreadLocal.getUserId());

			return q.list();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _EVENT_SQL =
		" AND (SyncDLObject.event != 'trash')";

	private static final String _PARENT_FOLDER_ID_SQL =
		"(SyncDLObject.parentFolderId = ?) AND";

}