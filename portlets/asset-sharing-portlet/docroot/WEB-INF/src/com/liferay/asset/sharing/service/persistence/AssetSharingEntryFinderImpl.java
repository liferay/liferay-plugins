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

package com.liferay.asset.sharing.service.persistence;

import com.liferay.asset.sharing.model.AssetSharingEntry;
import com.liferay.asset.sharing.model.AssetSharingEntryConstants;
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
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Sherry Yang
 */
public class AssetSharingEntryFinderImpl
	extends BasePersistenceImpl<AssetSharingEntry>
	implements AssetSharingEntryFinder {

	public static final String COUNT_BY_USER_ID =
		AssetSharingEntryFinder.class.getName() + ".countByUserId";

	public static final String FIND_BY_USER_ID =
		AssetSharingEntryFinder.class.getName() + ".findByUserId";

	@Override
	public int countByUserId(
			long userId, long[] classNameIds,
			Map<Long, long[]> sharedToClassPKsMap)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_USER_ID);

			sql = StringUtil.replace(
				sql, "[$CLASS_NAME_IDS]", getClassNameIds(classNameIds));
			sql = StringUtil.replace(
				sql, "[$SHARED_TO_CLASS_PKS_MAP]",
				getSharedToClassPKsMap(sharedToClassPKsMap));

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setClassNameIds(qPos, userId, classNameIds);
			setSharedToClassPKsMap(qPos, userId, sharedToClassPKsMap);

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

	@Override
	public List<Object[]> findByUserId(
			long userId, long[] classNameIds,
			Map<Long, long[]> sharedToClassPKsMap, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_USER_ID);

			sql = StringUtil.replace(
				sql, "[$CLASS_NAME_IDS]", getClassNameIds(classNameIds));
			sql = StringUtil.replace(
				sql, "[$SHARED_TO_CLASS_PKS_MAP]",
				getSharedToClassPKsMap(sharedToClassPKsMap));

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar("classNameId", Type.LONG);
			q.addScalar("classPK", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setClassNameIds(qPos, userId, classNameIds);
			setSharedToClassPKsMap(qPos, userId, sharedToClassPKsMap);

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
		if ((classNameIds == null) || (classNameIds.length == 0)) {
			return StringPool.SPACE;
		}

		StringBundler sb = new StringBundler();

		sb.append(StringPool.OPEN_PARENTHESIS);

		for (int i = 0; i < classNameIds.length; i++) {
			sb.append("(AssetSharing_AssetSharingEntry.classNameId = ?)");

			if ((i + 1) < classNameIds.length) {
				sb.append(" OR ");
			}
		}

		sb.append(") AND");

		return sb.toString();
	}

	protected String getSharedToClassPKs(long[] sharedToClassPKs) {
		StringBundler sb = new StringBundler();

		sb.append(_SHARED_TO_CLASS_NAME_ID_SQL);
		sb.append(" AND (");

		for (int i = 0; i < sharedToClassPKs.length; i++) {
			sb.append(_SHARED_TO_CLASS_PK_SQL);

			if ((i + 1) < sharedToClassPKs.length) {
				sb.append(" OR ");
			}
			else {
				sb.append(StringPool.CLOSE_PARENTHESIS);
			}
		}

		return sb.toString();
	}

	protected String getSharedToClassPKsMap(
		Map<Long, long[]> sharedToClassPKsMap) {

		StringBundler sb = new StringBundler();

		sb.append(StringPool.OPEN_PARENTHESIS);

		if (sharedToClassPKsMap != null) {
			for (Map.Entry<Long, long[]> entry :
					sharedToClassPKsMap.entrySet()) {

				Long sharedToClassNameId = entry.getKey();
				long[] sharedToClassPKs = entry.getValue();

				if ((sharedToClassPKs == null) ||
					(sharedToClassPKs.length == 0)) {

					continue;
				}

				sb.append(StringPool.OPEN_PARENTHESIS);

				if (sharedToClassNameId == _SOCIAL_RELATION_CLASS_NAME_ID) {
					sb.append(getSocialRelationClassPKs(sharedToClassPKs));
				}
				else {
					sb.append(getSharedToClassPKs(sharedToClassPKs));
				}

				sb.append(") OR ");
			}
		}

		sb.append("(SocialActivitySet.userId = ?))");

		return sb.toString();
	}

	protected String getSocialRelationClassPKs(long[] sharedToClassPKs) {
		StringBundler sb = null;

		Arrays.sort(sharedToClassPKs);

		if (sharedToClassPKs.length == 1) {
			if (sharedToClassPKs[0] ==
					AssetSharingEntryConstants.TYPE_EVERYONE) {

				sb = new StringBundler(3);

				sb.append(_SHARED_TO_CLASS_NAME_ID_SQL);
				sb.append(" AND ");
				sb.append(_SHARED_TO_CLASS_PK_SQL);
			}
			else {
				sb = new StringBundler(6);

				sb.append(_SHARED_TO_CLASS_NAME_ID_SQL);
				sb.append(" AND (");
				sb.append(_SHARED_TO_CLASS_PK_SQL);
				sb.append(" AND ");
				sb.append(_USER_ID_1_SQL);
				sb.append(StringPool.CLOSE_PARENTHESIS);
			}
		}
		else if (sharedToClassPKs.length == 2) {
			if (sharedToClassPKs[0] ==
					AssetSharingEntryConstants.TYPE_EVERYONE) {

				sb = new StringBundler(8);

				sb.append(_SHARED_TO_CLASS_NAME_ID_SQL);
				sb.append(" AND (");
				sb.append(_SHARED_TO_CLASS_PK_SQL);
				sb.append(" OR (");
				sb.append(_SHARED_TO_CLASS_PK_SQL);
				sb.append(" AND ");
				sb.append(_USER_ID_1_SQL);
				sb.append("))");
			}
			else {
				sb = new StringBundler(8);

				sb.append(_SHARED_TO_CLASS_NAME_ID_SQL);
				sb.append(" AND ((");
				sb.append(_SHARED_TO_CLASS_PK_SQL);
				sb.append(" OR ");
				sb.append(_SHARED_TO_CLASS_PK_SQL);
				sb.append(") AND ");
				sb.append(_USER_ID_1_SQL);
				sb.append(StringPool.CLOSE_PARENTHESIS);
			}
		}
		else {
			sb = new StringBundler(10);

			sb.append(_SHARED_TO_CLASS_NAME_ID_SQL);
			sb.append(" AND (");
			sb.append(_SHARED_TO_CLASS_PK_SQL);
			sb.append(" OR ((");
			sb.append(_SHARED_TO_CLASS_PK_SQL);
			sb.append(" OR ");
			sb.append(_SHARED_TO_CLASS_PK_SQL);
			sb.append(") AND ");
			sb.append(_USER_ID_1_SQL);
			sb.append("))");
		}

		return sb.toString();
	}

	protected void setClassNameIds(
		QueryPos qPos, long userId, long[] classNameIds) {

		if (classNameIds != null) {
			for (long classNameId : classNameIds) {
				qPos.add(classNameId);
			}
		}
	}

	protected void setSharedToClassPKsMap(
		QueryPos qPos, long userId, Map<Long, long[]> sharedToClassPKsMap) {

		if (sharedToClassPKsMap != null) {
			for (Long sharedToClassNameId : sharedToClassPKsMap.keySet()) {
				qPos.add(sharedToClassNameId);

				long[] sharedToClassPKs = sharedToClassPKsMap.get(
					sharedToClassNameId);

				if (sharedToClassNameId == _SOCIAL_RELATION_CLASS_NAME_ID) {
					Arrays.sort(sharedToClassPKs);

					if (sharedToClassPKs.length == 1) {
						if (sharedToClassPKs[0] ==
								AssetSharingEntryConstants.TYPE_EVERYONE) {

							qPos.add(sharedToClassPKs[0]);
						}
						else {
							qPos.add(sharedToClassPKs[0]);
							qPos.add(userId);
						}
					}
					else {
						for (long sharedToClasPK : sharedToClassPKs) {
							qPos.add(sharedToClasPK);
						}

						qPos.add(userId);
					}
				}
				else {
					for (long sharedToClassPK : sharedToClassPKs) {
						qPos.add(sharedToClassPK);
					}
				}
			}
		}

		qPos.add(userId);
	}

	private static final String _SHARED_TO_CLASS_NAME_ID_SQL =
		"(AssetSharing_AssetSharingEntry.sharedToClassNameId = ?)";

	private static final String _SHARED_TO_CLASS_PK_SQL =
		"(AssetSharing_AssetSharingEntry.sharedToClassPK = ?)";

	private static final long _SOCIAL_RELATION_CLASS_NAME_ID =
		ClassNameServiceUtil.fetchClassNameId(SocialRelation.class);

	private static final String _USER_ID_1_SQL = "(SocialRelation.userId1 = ?)";

}