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
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author Calvin Keum
 * @author Sherry Yang
 */
public class AssetEntrySetFinderImpl
	extends BasePersistenceImpl<AssetEntrySet>
	implements AssetEntrySetFinder {

	public static final String COUNT_BY_SHARED_TO =
		AssetEntrySetFinder.class.getName() + ".countBySharedTo";

	public static final String COUNT_BY_CCNI_ATN =
		AssetEntrySetFinder.class.getName() + ".countByCCNI_ATN";

	public static final String COUNT_BY_CCNI_CCPK_ATN =
		AssetEntrySetFinder.class.getName() + ".countByCCNI_CCPK_ATN";

	public static final String FIND_BY_SHARED_TO =
		AssetEntrySetFinder.class.getName() + ".findBySharedTo";

	public static final String FIND_BY_CT_PASEI =
		AssetEntrySetFinder.class.getName() + ".findByCT_PASEI";

	public static final String FIND_BY_CCNI_ATN =
		AssetEntrySetFinder.class.getName() + ".findByCCNI_ATN";

	public static final String FIND_BY_CCNI_CCPK_ATN =
		AssetEntrySetFinder.class.getName() + ".findByCCNI_CCPK_ATN";

	@Override
	public int countBySharedTo(JSONArray sharedToJSONArray)
		throws SystemException {

		if ((sharedToJSONArray == null) || (sharedToJSONArray.length() == 0)) {
			return 0;
		}

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_SHARED_TO);

			sql = StringUtil.replace(
				sql, "[$SHARED_TO$]", getSharedTo(sharedToJSONArray));

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(_ASSET_ENTRY_SET_CLASS_NAME_ID);

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
			JSONArray sharedToJSONArray)
		throws SystemException {

		if ((sharedToJSONArray == null) || (sharedToJSONArray.length() == 0)) {
			return 0;
		}

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_CCNI_ATN);

			sql = StringUtil.replace(
				sql, "[$SHARED_TO$]", getSharedTo(sharedToJSONArray));

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(creatorClassNameId);
			qPos.add(assetTagName);
			qPos.add(_ASSET_ENTRY_SET_CLASS_NAME_ID);

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
			JSONArray sharedToJSONArray, boolean andOperator)
		throws SystemException {

		if ((sharedToJSONArray == null) || (sharedToJSONArray.length() == 0)) {
			return 0;
		}

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_CCNI_CCPK_ATN);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			sql = StringUtil.replace(
				sql, "[$SHARED_TO$]", getSharedTo(sharedToJSONArray));

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(creatorClassNameId);
			qPos.add(creatorClassPK);
			qPos.add(assetTagName);
			qPos.add(_ASSET_ENTRY_SET_CLASS_NAME_ID);

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
	public List<AssetEntrySet> findBySharedTo(
			JSONArray sharedToJSONArray, int start, int end)
		throws SystemException {

		if ((sharedToJSONArray == null) || (sharedToJSONArray.length() == 0)) {
			return Collections.emptyList();
		}

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_SHARED_TO);

			sql = StringUtil.replace(
				sql, "[$SHARED_TO$]", getSharedTo(sharedToJSONArray));

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("AssetEntrySet", AssetEntrySetImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(_ASSET_ENTRY_SET_CLASS_NAME_ID);

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

	public List<AssetEntrySet> findByCT_PASEI(
			long createTime, boolean gtCreateTime, long parentAssetEntrySetId,
			JSONArray sharedToJSONArray, int start, int end)
		throws SystemException {

		if ((sharedToJSONArray == null) || (sharedToJSONArray.length() == 0)) {
			return Collections.emptyList();
		}

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_CT_PASEI);

			if (gtCreateTime) {
				sql = StringUtil.replace(
					sql, "[$CREATE_TIME_COMPARATOR$]", ">");
			}
			else {
				sql = StringUtil.replace(
					sql, "[$CREATE_TIME_COMPARATOR$]", "<=");
			}

			sql = StringUtil.replace(
				sql, "[$SHARED_TO$]", getSharedTo(sharedToJSONArray));

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("AssetEntrySet", AssetEntrySetImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(createTime);
			qPos.add(parentAssetEntrySetId);
			qPos.add(_ASSET_ENTRY_SET_CLASS_NAME_ID);

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
			JSONArray sharedToJSONArray, int start, int end)
		throws SystemException {

		if ((sharedToJSONArray == null) || (sharedToJSONArray.length() == 0)) {
			return Collections.emptyList();
		}

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_CCNI_ATN);

			sql = StringUtil.replace(
				sql, "[$SHARED_TO$]", getSharedTo(sharedToJSONArray));

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("AssetEntrySet", AssetEntrySetImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(creatorClassNameId);
			qPos.add(assetTagName);
			qPos.add(_ASSET_ENTRY_SET_CLASS_NAME_ID);

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
			JSONArray sharedToJSONArray, boolean andOperator, int start,
			int end)
		throws SystemException {

		if ((sharedToJSONArray == null) || (sharedToJSONArray.length() == 0)) {
			return Collections.emptyList();
		}

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_CCNI_CCPK_ATN);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			sql = StringUtil.replace(
				sql, "[$SHARED_TO$]", getSharedTo(sharedToJSONArray));

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("AssetEntrySet", AssetEntrySetImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(creatorClassNameId);
			qPos.add(creatorClassPK);
			qPos.add(assetTagName);
			qPos.add(_ASSET_ENTRY_SET_CLASS_NAME_ID);

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

	protected String getSharedTo(JSONArray sharedToJSONArray) {
		StringBundler sb = new StringBundler(
			(sharedToJSONArray.length() * 2) + 2);

		sb.append(StringPool.OPEN_PARENTHESIS);

		for (int i = 0; i < sharedToJSONArray.length(); i++) {
			JSONObject jsonObject = sharedToJSONArray.getJSONObject(i);

			long sharedToClassNameId = jsonObject.getLong("classNameId");
			long sharedToClassPK = jsonObject.getLong("classPK");

			sb.append(getSharedTo(sharedToClassNameId, sharedToClassPK));
			sb.append(" OR ");
		}

		sb.setIndex(sb.index() - 1);

		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	protected String getSharedTo(
		long sharedToClassNameId, long sharedToClassPK) {

		StringBundler sb = new StringBundler(5);

		sb.append("(AssetSharingEntry.sharedToClassNameId = ");
		sb.append(sharedToClassNameId);
		sb.append(" AND AssetSharingEntry.sharedToClassPK = ");
		sb.append(sharedToClassPK);
		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	private static final long _ASSET_ENTRY_SET_CLASS_NAME_ID =
		ClassNameLocalServiceUtil.getClassNameId(AssetEntrySet.class);

}