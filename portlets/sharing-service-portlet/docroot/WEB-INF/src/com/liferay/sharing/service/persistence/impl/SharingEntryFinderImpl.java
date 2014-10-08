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

package com.liferay.sharing.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.ClassNameServiceUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portlet.social.model.SocialRelation;
import com.liferay.sharing.model.SharingEntry;
import com.liferay.sharing.service.persistence.SharingEntryFinder;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Sherry Yang
 */
public class SharingEntryFinderImpl
	extends BasePersistenceImpl<SharingEntry>
	implements SharingEntryFinder {

	public static final String COUNT_ENTRIES_BY_USER_ID =
		SharingEntryFinder.class.getName() + ".countEntriesByUserId";

	public static final String FIND_ENTRIES_BY_USER_ID =
		SharingEntryFinder.class.getName() + ".findEntrisByUserId";

	public int countEntriesByUserId(
		long userId, long[] classNameIds, Map<Long, long[]> scopes) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_ENTRIES_BY_USER_ID);

			sql = StringUtil.replace(
				sql, "[$CLASS_NAME_IDS_CLASS_PKS]",
				getClassNameIDsClassPKs(classNameIds, scopes));

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setClassNameIdsClassPKs(qPos, userId, classNameIds, scopes);

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

	public List<Object[]> findEntriesByUserId(
		long userId, long[] classNameIds, Map<Long, long[]> scopes, int start,
		int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_ENTRIES_BY_USER_ID);

			sql = StringUtil.replace(
				sql, "[$CLASS_NAME_IDS_CLASS_PKS]",
				getClassNameIDsClassPKs(classNameIds, scopes));

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar("classNameId", Type.LONG);
			q.addScalar("classPK", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setClassNameIdsClassPKs(qPos, userId, classNameIds, scopes);

			return (List<Object[]>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getClassNameIds(long[] classNameIds) {
		if (classNameIds.length == 0) {
			return StringPool.SPACE;
		}

		StringBundler sb = new StringBundler();

		sb.append(StringPool.OPEN_PARENTHESIS);

		for (int i = 0; i < classNameIds.length; i++) {
			sb.append("(Sharing_SharingEntry.classNameId = ? )");

			if ((i + 1) < classNameIds.length) {
				sb.append(" OR ");
			}
		}

		sb.append(StringPool.CLOSE_PARENTHESIS);

		sb.append("AND");

		return sb.toString();
	}

	protected String getClassNameIDsClassPKs(
		long[] classNameIds, Map<Long, long[]> scopes) {

		StringBundler sb = new StringBundler(1);

		if (classNameIds != null) {
			sb.append(getClassNameIds(classNameIds));
		}

		sb.append(getClassPKs(scopes));

		return sb.toString();
	}

	protected String getClassPKs(
		long sharingClassNameId, long[] sharingClassPKs) {

		StringBundler sb = new StringBundler(sharingClassPKs.length * 2 + 1);

		sb.append("(Sharing_SharingEntry.sharingClassNameId = ?) AND (");

		for (int i = 0; i < sharingClassPKs.length; i++) {
			sb.append("(Sharing_SharingEntry.sharingClassPK = ?)");

			if ((i + 1) < sharingClassPKs.length) {
				sb.append(" OR ");
			}
			else {
				sb.append(StringPool.CLOSE_PARENTHESIS);
			}
		}

		return sb.toString();
	}

	protected String getClassPKs(Map<Long, long[]> scopes) {
		StringBundler sb = new StringBundler();

		sb.append(StringPool.OPEN_PARENTHESIS);

		if (scopes != null) {
			for (Map.Entry<Long, long[]> entry : scopes.entrySet()) {
				Long sharingClassNameId = entry.getKey();
				long[] sharingClassPKs = entry.getValue();

				if ((sharingClassPKs == null) ||
					(sharingClassPKs.length == 0)) {

					continue;
				}

				if (sharingClassNameId == _SOCIAL_RELATION_CLASS_NAME_ID) {
					sb.append(getSocialRelationClassPKS(sharingClassPKs));
				}
				else {
					sb.append(StringPool.OPEN_PARENTHESIS);
					sb.append(getClassPKs(sharingClassNameId, sharingClassPKs));
					sb.append(StringPool.CLOSE_PARENTHESIS);
					sb.append(" OR ");
				}
			}
		}

		sb.append("(SocialActivitySet.userId = ?)");
		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	protected String getSocialRelationClassPKS(long[] sharingClassPKs) {
		StringBundler sb = null;

		Arrays.sort(sharingClassPKs);

		if (sharingClassPKs.length == 1) {
			if (sharingClassPKs[0] == 0) {
				sb = new StringBundler(3);

				sb.append("(Sharing_SharingEntry.sharingClassNameId = ?) AND ");
				sb.append("(Sharing_SharingEntry.sharingClassPK = ?)");
			}
			else {
				sb = new StringBundler(6);

				sb.append("(Sharing_SharingEntry.sharingClassNameId = ?) AND ");
				sb.append(StringPool.OPEN_PARENTHESIS);
				sb.append("(Sharing_SharingEntry.sharingClassPK = ?) AND ");
				sb.append("(SocialRelation.userId1 = ?)");
				sb.append(StringPool.CLOSE_PARENTHESIS);
			}
		}
		else if (sharingClassPKs.length == 2) {
			if (sharingClassPKs[0] == 0) {
				sb = new StringBundler(9);

				sb.append("(Sharing_SharingEntry.sharingClassNameId = ?) AND ");
				sb.append(StringPool.OPEN_PARENTHESIS);
				sb.append("(Sharing_SharingEntry.sharingClassPK = ?) OR ");
				sb.append(StringPool.OPEN_PARENTHESIS);
				sb.append("(Sharing_SharingEntry.sharingClassPK = ?) AND ");
				sb.append("(SocialRelation.userId1 = ?)");
				sb.append(StringPool.CLOSE_PARENTHESIS);
				sb.append(StringPool.CLOSE_PARENTHESIS);
			}
			else {
				sb = new StringBundler(10);

				sb.append("(Sharing_SharingEntry.sharingClassNameId = ?) AND ");
				sb.append(StringPool.OPEN_PARENTHESIS);
				sb.append(StringPool.OPEN_PARENTHESIS);
				sb.append("(Sharing_SharingEntry.sharingClassPK = ?) OR ");
				sb.append("(Sharing_SharingEntry.sharingClassPK = ?)");
				sb.append(StringPool.CLOSE_PARENTHESIS);
				sb.append(" AND ");
				sb.append("(SocialRelation.userId1 = ?)");
				sb.append(StringPool.CLOSE_PARENTHESIS);
			}
		}
		else {
			sb = new StringBundler(15);

			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append("(Sharing_SharingEntry.sharingClassNameId = ?) AND ");
			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append("(Sharing_SharingEntry.sharingClassPK = ?) OR ");
			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append("(Sharing_SharingEntry.sharingClassPK = ?) OR ");
			sb.append("(Sharing_SharingEntry.sharingClassPK = ?)");
			sb.append(StringPool.CLOSE_PARENTHESIS);
			sb.append(" AND ");
			sb.append("(SocialRelation.userId1 = ?)");
			sb.append(StringPool.CLOSE_PARENTHESIS);
			sb.append(StringPool.CLOSE_PARENTHESIS);
			sb.append(StringPool.CLOSE_PARENTHESIS);
		}

		sb.append(" OR ");

		return sb.toString();
	}

	protected void setClassNameIdsClassPKs(
		QueryPos qPos, long userId, long[] classNameIds,
		Map<Long, long[]> scopes) {

		if (classNameIds != null) {
			for (long classNameId : classNameIds) {
				qPos.add(classNameId);
			}
		}

		if (scopes != null) {
			for (Long sharingClassNameId : scopes.keySet()) {
				qPos.add(sharingClassNameId);

				long[] sharingClassPKs = scopes.get(sharingClassNameId);

				if (sharingClassNameId == _SOCIAL_RELATION_CLASS_NAME_ID) {
					Arrays.sort(sharingClassPKs);

					if (sharingClassPKs.length == 1) {
						if (sharingClassPKs[0] == 0) {
							qPos.add(sharingClassPKs[0]);
						}
						else {
							qPos.add(sharingClassPKs[0]);
							qPos.add(userId);
						}
					}
					else {
						for (long sharingClassPK : sharingClassPKs) {
							qPos.add(sharingClassPK);
						}

						qPos.add(userId);
					}
				}
				else {
					for (long sharingClassPK : sharingClassPKs) {
						qPos.add(sharingClassPK);
					}
				}
			}
		}

		qPos.add(userId);
	}

	private static final long _SOCIAL_RELATION_CLASS_NAME_ID =
		ClassNameServiceUtil.fetchClassNameId(SocialRelation.class);

}