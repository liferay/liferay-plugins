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

package com.liferay.asset.entry.set.service.persistence;

import com.liferay.asset.entry.set.model.AssetEntrySet;
import com.liferay.asset.entry.set.model.impl.AssetEntrySetImpl;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Calvin Keum
 * @author Sherry Yang
 */
public class AssetEntrySetFinderImpl
	extends BasePersistenceImpl<AssetEntrySet>
	implements AssetEntrySetFinder {

	public static final String COUNT_BY_CCNI_ATN =
		AssetEntrySetFinder.class.getName() + ".countByCCNI_ATN";

	public static final String COUNT_BY_CCNI_CCPK_ATN =
		AssetEntrySetFinder.class.getName() + ".countByCCNI_CCPK_ATN";

	public static final String COUNT_BY_USER_ID =
		AssetEntrySetFinder.class.getName() + ".countByUserId";

	public static final String FIND_BY_CCNI_ATN =
		AssetEntrySetFinder.class.getName() + ".findByCCNI_ATN";

	public static final String FIND_BY_CCNI_CCPK_ATN =
		AssetEntrySetFinder.class.getName() + ".findByCCNI_CCPK_ATN";

	public static final String FIND_BY_USER_ID =
		AssetEntrySetFinder.class.getName() + ".findByUserId";

	@Override
	public int countBySharedToClassPKsMap(Map<Long, long[]> sharedToClassPKsMap)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_USER_ID);

			sql = StringUtil.replace(
				sql, "[$SHARED_TO_CLASS_PKS_MAP]",
				getSharedToClassPKsMap(sharedToClassPKsMap));

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(_ASSET_ENTRY_SET_CLASS_NAME_ID);

			setSharedToClassPKsMap(qPos, sharedToClassPKsMap);

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

	public int countByCCNI_ATN(
			long creatorClassNameId, String assetTagName,
			Map<Long, long[]> sharedToClassPKsMap)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_CCNI_ATN);

			sql = StringUtil.replace(
				sql, "[$SHARED_TO_CLASS_PKS_MAP]",
				getSharedToClassPKsMap(sharedToClassPKsMap));

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(creatorClassNameId);
			qPos.add(assetTagName);
			qPos.add(_ASSET_ENTRY_SET_CLASS_NAME_ID);

			setSharedToClassPKsMap(qPos, sharedToClassPKsMap);

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

	public int countByCCNI_CCPK_ATN(
			long creatorClassNameId, long creatorClassPK, String assetTagName,
			Map<Long, long[]> sharedToClassPKsMap, boolean andOperator)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_CCNI_CCPK_ATN);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			sql = StringUtil.replace(
				sql, "[$SHARED_TO_CLASS_PKS_MAP]",
				getSharedToClassPKsMap(sharedToClassPKsMap));

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(creatorClassNameId);
			qPos.add(creatorClassPK);
			qPos.add(assetTagName);
			qPos.add(_ASSET_ENTRY_SET_CLASS_NAME_ID);

			setSharedToClassPKsMap(qPos, sharedToClassPKsMap);

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
	public List<AssetEntrySet> findBySharedToClassPKsMap(
			Map<Long, long[]> sharedToClassPKsMap, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_USER_ID);

			sql = StringUtil.replace(
				sql, "[$SHARED_TO_CLASS_PKS_MAP]",
				getSharedToClassPKsMap(sharedToClassPKsMap));

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("AssetEntrySet", AssetEntrySetImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(_ASSET_ENTRY_SET_CLASS_NAME_ID);

			setSharedToClassPKsMap(qPos, sharedToClassPKsMap);

			return (List<AssetEntrySet>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<AssetEntrySet> findByCCNI_ATN(
			long creatorClassNameId, String assetTagName,
			Map<Long, long[]> sharedToClassPKsMap, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_CCNI_ATN);

			sql = StringUtil.replace(
				sql, "[$SHARED_TO_CLASS_PKS_MAP]",
				getSharedToClassPKsMap(sharedToClassPKsMap));

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("AssetEntrySet", AssetEntrySetImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(creatorClassNameId);
			qPos.add(assetTagName);
			qPos.add(_ASSET_ENTRY_SET_CLASS_NAME_ID);

			setSharedToClassPKsMap(qPos, sharedToClassPKsMap);

			return (List<AssetEntrySet>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<AssetEntrySet> findByCCNI_CCPK_ATN(
			long creatorClassNameId, long creatorClassPK, String assetTagName,
			Map<Long, long[]> sharedToClassPKsMap, boolean andOperator,
			int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_CCNI_CCPK_ATN);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			sql = StringUtil.replace(
				sql, "[$SHARED_TO_CLASS_PKS_MAP]",
				getSharedToClassPKsMap(sharedToClassPKsMap));

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("AssetEntrySet", AssetEntrySetImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(creatorClassNameId);
			qPos.add(creatorClassPK);
			qPos.add(assetTagName);
			qPos.add(_ASSET_ENTRY_SET_CLASS_NAME_ID);

			setSharedToClassPKsMap(qPos, sharedToClassPKsMap);

			return (List<AssetEntrySet>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getSharedToClassPKs(long[] sharedToClassPKs) {
		StringBundler sb = new StringBundler(
			(sharedToClassPKs.length + 1) * 2 );

		sb.append(_SHARED_TO_CLASS_NAME_ID_SQL);
		sb.append(" AND (");

		for (int i = 0; i < sharedToClassPKs.length; i++) {
			sb.append(_SHARED_TO_CLASS_PK_SQL);
			sb.append(" OR ");
		}

		sb.setIndex(sb.index() - 1);

		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	protected String getSharedToClassPKsMap(
		Map<Long, long[]> sharedToClassPKsMap) {

		StringBundler sb = new StringBundler();

		for (Map.Entry<Long, long[]> entry : sharedToClassPKsMap.entrySet()) {
			long[] sharedToClassPKs = entry.getValue();

			if (ArrayUtil.isEmpty(sharedToClassPKs)) {
				continue;
			}

			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(getSharedToClassPKs(sharedToClassPKs));
			sb.append(") OR ");
		}

		sb.setIndex(sb.index() - 1);

		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	protected void setSharedToClassPKsMap(
		QueryPos qPos, Map<Long, long[]> sharedToClassPKsMap) {

		if (sharedToClassPKsMap == null) {
			return;
		}

		for (Long sharedToClassNameId : sharedToClassPKsMap.keySet()) {
			qPos.add(sharedToClassNameId);

			long[] sharedToClassPKs = sharedToClassPKsMap.get(
				sharedToClassNameId);

			for (long sharedToClassPK : sharedToClassPKs) {
				qPos.add(sharedToClassPK);
			}
		}
	}

	private static final long _ASSET_ENTRY_SET_CLASS_NAME_ID =
		ClassNameLocalServiceUtil.getClassNameId(AssetEntrySet.class);

	private static final String _SHARED_TO_CLASS_NAME_ID_SQL =
		"(AssetSharing_AssetSharingEntry.sharedToClassNameId = ?)";

	private static final String _SHARED_TO_CLASS_PK_SQL =
		"(AssetSharing_AssetSharingEntry.sharedToClassPK = ?)";

}