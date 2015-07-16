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
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.service.persistence.SyncDLObjectFinder;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Collections;
import java.util.List;

/**
 * @author Shinn Lok
 */
public class SyncDLObjectFinderImpl
	extends BasePersistenceImpl<SyncDLObject> implements SyncDLObjectFinder {

	public static final String FIND_BY_TYPE_PKS =
		SyncDLObjectFinder.class.getName() + ".findByTypePKs";

	@Override
	public List<Long> filterFindByC_R_U_T(
		long companyId, long groupId, long userId, long[] typePKs) {

		if (ArrayUtil.isEmpty(typePKs)) {
			return Collections.emptyList();
		}

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_TYPE_PKS);

			sql = StringUtil.replace(
				sql, new String[] {"[$TYPE_PKS$]", "[$ROLE_IDS_OR_OWNER_ID$]"},
				new String[] {
					getTypePKsSQL(typePKs), getRoleOwnerIdsSQL(groupId, userId)
				});

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar("primKey", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(sqlQuery);

			qPos.add(companyId);
			qPos.add(ResourceConstants.SCOPE_INDIVIDUAL);

			return (List<Long>)sqlQuery.list();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getRoleOwnerIdsSQL(long groupId, long userId) {
		StringBundler sb = new StringBundler(8);

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		long[] roleIds = permissionChecker.getRoleIds(userId, groupId);

		sb.append(StringPool.OPEN_PARENTHESIS);

		if (roleIds.length != 0) {
			sb.append("roleId IN (");
			sb.append(StringUtil.merge(roleIds));
			sb.append(StringPool.CLOSE_PARENTHESIS);
			sb.append(WHERE_OR);
		}

		sb.append("ownerId = ");
		sb.append(userId);
		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	protected String getTypePKsSQL(long[] typePKs) {
		StringBundler sb = new StringBundler(typePKs.length * 4 + 1);

		sb.append("primKey IN (");

		for (int i = 0; i < typePKs.length; i++) {
			sb.append("CAST_TEXT(");
			sb.append(String.valueOf(typePKs[i]).trim());
			sb.append(StringPool.CLOSE_PARENTHESIS);

			if ((i + 1) != typePKs.length) {
				sb.append(StringPool.COMMA);
			}
		}

		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

}