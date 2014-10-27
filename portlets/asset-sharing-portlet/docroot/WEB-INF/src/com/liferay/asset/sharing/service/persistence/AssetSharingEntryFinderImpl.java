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
import com.liferay.asset.sharing.util.AssetSharingEntryConstants;
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

	public static final String COUNT_ASSET_ENTRIES_BY_USER_ID =
		AssetSharingEntryFinder.class.getName() + ".countByUserId";

	public static final String FIND_ASSET_ENTRIES_BY_USER_ID =
		AssetSharingEntryFinder.class.getName() + ".findByUserId";

	public int countByUserId(
			long userId, long[] classNameIds,
			Map<Long, long[]> sharedToClassPKsMap)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_ASSET_ENTRIES_BY_USER_ID);

			sql = StringUtil.replace(
				sql, "[$CLASS_NAME_IDS_CLASS_PKS]",
				getClassNameIdsSharedToClassPKsMap(
					classNameIds, sharedToClassPKsMap));

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setClassNameIdsSharedToClassPKsMap(
				qPos, userId, classNameIds, sharedToClassPKsMap);

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

	public List<Object[]> findByUserId(
			long userId, long[] classNameIds,
			Map<Long, long[]> sharedToClassPKsMap, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_ASSET_ENTRIES_BY_USER_ID);

			sql = StringUtil.replace(
				sql, "[$CLASS_NAME_IDS_CLASS_PKS]",
				getClassNameIdsSharedToClassPKsMap(
					classNameIds, sharedToClassPKsMap));

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar("classNameId", Type.LONG);
			q.addScalar("classPK", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setClassNameIdsSharedToClassPKsMap(
				qPos, userId, classNameIds, sharedToClassPKsMap);

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

		StringBundler sb = new StringBundler((classNameIds.length + 1) * 2);

		sb.append(StringPool.OPEN_PARENTHESIS);

		for (int i = 0; i < classNameIds.length; i++) {
			sb.append("(AssetSharing_AssetSharingEntry.classNameId = ? )");

			if ((i + 1) < classNameIds.length) {
				sb.append(" OR ");
			}
		}

		sb.append(StringPool.CLOSE_PARENTHESIS);

		sb.append("AND");

		return sb.toString();
	}

	protected String getClassNameIdsSharedToClassPKsMap(
		long[] classNameIds, Map<Long, long[]> sharedToClassPKsMap) {

		StringBundler sb = new StringBundler();

		if (classNameIds != null) {
			sb.append(getClassNameIds(classNameIds));
		}

		sb.append(getSharedToClassPKsMap(sharedToClassPKsMap));

		return sb.toString();
	}

	protected String getSharedToClassPKsMap(long[] sharedToClassPKs) {

		StringBundler sb = new StringBundler(sharedToClassPKs.length * 2 + 3);

		sb.append("(AssetSharing_AssetSharingEntry.sharedToClassNameId = ?)");
		sb.append(" AND ");
		sb.append(StringPool.OPEN_PARENTHESIS);

		for (int i = 0; i < sharedToClassPKs.length; i++) {
			sb.append("(AssetSharing_AssetSharingEntry.sharedToClassPK = ?)");

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
					sb.append(
						getSharedToClassPKsMap(sharedToClassPKs));
				}

				sb.append(StringPool.CLOSE_PARENTHESIS);
				sb.append(" OR ");
			}
		}

		sb.append("(SocialActivitySet.userId = ?)");
		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	protected String getSocialRelationClassPKs(long[] sharedToClassPKs) {
		StringBundler sb = null;

		Arrays.sort(sharedToClassPKs);

		if (sharedToClassPKs.length == 1) {
			if (sharedToClassPKs[0] ==
					AssetSharingEntryConstants.TYPE_EVERYONE) {

				sb = new StringBundler(2);

				sb.append(
					"(AssetSharing_AssetSharingEntry.sharedToClassNameId = ?)" +
					" AND ");
				sb.append(
					"(AssetSharing_AssetSharingEntry.sharedToClassPK = ?)");
			}
			else {
				sb = new StringBundler(5);

				sb.append(
					"(AssetSharing_AssetSharingEntry.sharedToClassNameId = ?)" +
					" AND ");
				sb.append(StringPool.OPEN_PARENTHESIS);
				sb.append(
					"(AssetSharing_AssetSharingEntry.sharedToClassPK = ?)" +
					" AND ");
				sb.append("(SocialRelation.userId1 = ?)");
				sb.append(StringPool.CLOSE_PARENTHESIS);
			}
		}
		else if (sharedToClassPKs.length == 2) {
			if (sharedToClassPKs[0] ==
					AssetSharingEntryConstants.TYPE_EVERYONE) {

				sb = new StringBundler(8);

				sb.append(
					"(AssetSharing_AssetSharingEntry.sharedToClassNameId = ?)" +
					" AND ");
				sb.append(StringPool.OPEN_PARENTHESIS);
				sb.append(
					"(AssetSharing_AssetSharingEntry.sharedToClassPK = ?) OR ");
				sb.append(StringPool.OPEN_PARENTHESIS);
				sb.append(
					"(AssetSharing_AssetSharingEntry.sharedToClassPK = ?)" +
					" AND ");
				sb.append("(SocialRelation.userId1 = ?)");
				sb.append(StringPool.CLOSE_PARENTHESIS);
				sb.append(StringPool.CLOSE_PARENTHESIS);
			}
			else {
				sb = new StringBundler(9);

				sb.append(
					"(AssetSharing_AssetSharingEntry.sharedToClassNameId = ?)" +
					" AND ");
				sb.append(StringPool.OPEN_PARENTHESIS);
				sb.append(StringPool.OPEN_PARENTHESIS);
				sb.append(
					"(AssetSharing_AssetSharingEntry.sharedToClassPK = ?) OR ");
				sb.append(
					"(AssetSharing_AssetSharingEntry.sharedToClassPK = ?)");
				sb.append(StringPool.CLOSE_PARENTHESIS);
				sb.append(" AND ");
				sb.append("(SocialRelation.userId1 = ?)");
				sb.append(StringPool.CLOSE_PARENTHESIS);
			}
		}
		else {
			sb = new StringBundler(12);

			sb.append(
				"(AssetSharing_AssetSharingEntry.sharedToClassNameId = ?)" +
				" AND ");
			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(
				"(AssetSharing_AssetSharingEntry.sharedToClassPK = ?) OR ");
			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(
				"(AssetSharing_AssetSharingEntry.sharedToClassPK = ?) OR ");
			sb.append("(AssetSharing_AssetSharingEntry.sharedToClassPK = ?)");
			sb.append(StringPool.CLOSE_PARENTHESIS);
			sb.append(" AND ");
			sb.append("(SocialRelation.userId1 = ?)");
			sb.append(StringPool.CLOSE_PARENTHESIS);
			sb.append(StringPool.CLOSE_PARENTHESIS);
		}

		return sb.toString();
	}

	protected void setClassNameIdsSharedToClassPKsMap(
		QueryPos qPos, long userId, long[] classNameIds,
		Map<Long, long[]> sharedToClassPKsMap) {

		if (classNameIds != null) {
			for (long classNameId : classNameIds) {
				qPos.add(classNameId);
			}
		}

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

	private static final long _SOCIAL_RELATION_CLASS_NAME_ID =
		ClassNameServiceUtil.fetchClassNameId(SocialRelation.class);

}