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
import com.liferay.asset.entry.set.model.AssetEntrySetReference;
import com.liferay.asset.entry.set.model.impl.AssetEntrySetImpl;
import com.liferay.asset.entry.set.util.AssetEntrySetConstants;
import com.liferay.asset.entry.set.util.AssetEntrySetParticipantInfoUtil;
import com.liferay.asset.sharing.model.AssetSharingEntry;
import com.liferay.asset.sharing.service.AssetSharingEntryLocalServiceUtil;
import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.ArrayList;
import java.util.Arrays;
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

	public static final String
		FIND_ASSET_ENTRY_SET_REFERENCE_BY_PAESI_CNI =
			AssetEntrySetFinder.class.getName() +
				".findAssetEntrySetReferenceByPAESI_CNI";

	public static final String FIND_BY_CT_PAESI_ST_CNI =
		AssetEntrySetFinder.class.getName() + ".findByCT_PAESI_ST_CNI";

	public static final String FIND_BY_MT_PAESI_ST_CNI =
		AssetEntrySetFinder.class.getName() + ".findByMT_PAESI_ST_CNI";

	public static final String FIND_BY_CT_PAESI_ST_T_CNI =
		AssetEntrySetFinder.class.getName() + ".findByCT_PAESI_ST_T_CNI";

	public static final String JOIN_BY_ASSET_SHARING_ENTRY =
		AssetEntrySetFinder.class.getName() + ".joinByAssetSharingEntry";

	public static final String JOIN_BY_ASSET_TAG =
		AssetEntrySetFinder.class.getName() + ".joinByAssetTag";

	/**
	 * Pattern for finding reference objects {@link
	 * com.liferay.portal.service.persistence.LayoutFinderImpl#findByC_P_P}
	 */
	@Override
	public List<AssetEntrySetReference>findAssetEntrySetReferenceByPAESI_CNI(
			long parentAssetEntrySetId)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(
				FIND_ASSET_ENTRY_SET_REFERENCE_BY_PAESI_CNI);

			SQLQuery q = session.createSQLQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(parentAssetEntrySetId);
			qPos.add(AssetEntrySetConstants.ASSET_ENTRY_SET_CLASS_NAME_ID);

			List<AssetEntrySetReference> assetEntrySetReferences =
				new ArrayList<AssetEntrySetReference>();

			Iterator<Object[]> itr = q.iterate();

			while (itr.hasNext()) {
				Object[] array = itr.next();

				long sharedToClassNameId = GetterUtil.getLong(array[0]);
				long sharedToClassPK = GetterUtil.getLong(array[1]);

				assetEntrySetReferences.add(
					new AssetEntrySetReference(
						sharedToClassNameId, sharedToClassPK));
			}

			return assetEntrySetReferences;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<AssetEntrySet> findByCT_PAESI_ST_CNI(
			long classNameId, long classPK, long createTime,
			boolean gtCreateTime, long parentAssetEntrySetId,
			boolean privateAssetEntrySet, long stickyTime,
			JSONArray creatorJSONArray, JSONArray sharedToJSONArray,
			long[] excludeAssetEntrySetIds, long[] includeAssetEntrySetIds,
			String[] assetTagNames, int start, int end)
		throws SystemException {

		if (((sharedToJSONArray == null) ||
			 (sharedToJSONArray.length() == 0)) &&
			ArrayUtil.isEmpty(includeAssetEntrySetIds) &&
			ArrayUtil.isEmpty(assetTagNames)) {

			return Collections.emptyList();
		}

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_CT_PAESI_ST_CNI);

			sql = updateSQL(
				sql, classNameId, classPK, gtCreateTime, privateAssetEntrySet,
				creatorJSONArray, sharedToJSONArray, excludeAssetEntrySetIds,
				includeAssetEntrySetIds, assetTagNames);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("AssetEntrySet", AssetEntrySetImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(createTime);
			qPos.add(parentAssetEntrySetId);
			qPos.add(stickyTime);
			qPos.add(AssetEntrySetConstants.ASSET_ENTRY_SET_CLASS_NAME_ID);

			setAssetTagNames(qPos, assetTagNames);

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

	@Override
	public List<AssetEntrySet> findByMT_PAESI_ST_CNI(
			long classNameId, long classPK, long modifiedTime,
			boolean gtModifiedTime, long parentAssetEntrySetId,
			boolean privateAssetEntrySet, long stickyTime,
			JSONArray creatorJSONArray, JSONArray sharedToJSONArray,
			long[] excludeAssetEntrySetIds, long[] includeAssetEntrySetIds,
			String[] assetTagNames, int start, int end)
		throws SystemException {

		if (((sharedToJSONArray == null) ||
			 (sharedToJSONArray.length() == 0)) &&
			ArrayUtil.isEmpty(includeAssetEntrySetIds) &&
			ArrayUtil.isEmpty(assetTagNames)) {

			return Collections.emptyList();
		}

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_MT_PAESI_ST_CNI);

			SQLQuery q = session.createSQLQuery(
				updateSQL(
					sql, classNameId, classPK, gtModifiedTime,
					privateAssetEntrySet, creatorJSONArray, sharedToJSONArray,
					excludeAssetEntrySetIds, includeAssetEntrySetIds,
					assetTagNames));

			q.addEntity("AssetEntrySet", AssetEntrySetImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(modifiedTime);
			qPos.add(parentAssetEntrySetId);
			qPos.add(stickyTime);
			qPos.add(AssetEntrySetConstants.ASSET_ENTRY_SET_CLASS_NAME_ID);

			setAssetTagNames(qPos, assetTagNames);

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

	@Override
	public List<AssetEntrySet> findByCT_PAESI_ST_T_CNI(
			long classNameId, long classPK, long createTime,
			boolean gtCreateTime, long parentAssetEntrySetId, long stickyTime,
			int type, JSONArray creatorJSONArray, JSONArray sharedToJSONArray,
			long[] excludeAssetEntrySetIds, long[] includeAssetEntrySetIds,
			String[] assetTagNames, int start, int end)
		throws SystemException {

		if (((sharedToJSONArray == null) ||
			 (sharedToJSONArray.length() == 0)) &&
			ArrayUtil.isEmpty(assetTagNames)) {

			return Collections.emptyList();
		}

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_CT_PAESI_ST_T_CNI);

			SQLQuery q = session.createSQLQuery(
				updateSQL(
					sql, classNameId, classPK, gtCreateTime, false,
					creatorJSONArray, sharedToJSONArray,
					excludeAssetEntrySetIds, includeAssetEntrySetIds,
					assetTagNames));

			q.addEntity("AssetEntrySet", AssetEntrySetImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(createTime);
			qPos.add(parentAssetEntrySetId);
			qPos.add(stickyTime);
			qPos.add(type);
			qPos.add(AssetEntrySetConstants.ASSET_ENTRY_SET_CLASS_NAME_ID);

			setAssetTagNames(qPos, assetTagNames);

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

	protected String getAssetTagNames(
		long classNameId, long classPK, String[] assetTagNames) {

		if (ArrayUtil.isEmpty(assetTagNames)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(assetTagNames.length * 4);

		for (int i = 0; i < assetTagNames.length; i++) {
			sb.append("((AssetTag.name = ?) AND ");
			sb.append(getViewable(classNameId, classPK));
			sb.append(StringPool.CLOSE_PARENTHESIS);
			sb.append(" OR ");
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	protected String getCreator(
		long classNameId, long classPK, JSONArray creatorJSONArray) {

		if ((creatorJSONArray == null) || (creatorJSONArray.length() == 0)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(
			(creatorJSONArray.length() * 8) + 2);

		sb.append(StringPool.OPEN_PARENTHESIS);

		for (int i = 0; i < creatorJSONArray.length(); i++) {
			JSONObject jsonObject = creatorJSONArray.getJSONObject(i);

			long creatorClassNameId = jsonObject.getLong("classNameId");
			long creatorClassPK = jsonObject.getLong("classPK");

			sb.append("(AssetEntrySet.creatorClassNameId = ");
			sb.append(creatorClassNameId);
			sb.append(" AND AssetEntrySet.creatorClassPK = ");
			sb.append(creatorClassPK);

			if (!AssetEntrySetParticipantInfoUtil.isMember(
					classNameId, classPK, creatorClassNameId, creatorClassPK)) {

				sb.append(" AND ");
				sb.append(getViewable(classNameId, classPK));
			}

			sb.append(StringPool.CLOSE_PARENTHESIS);
			sb.append(" OR ");
		}

		sb.setIndex(sb.index() - 1);

		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	protected String getExcludeAssetEntrySetIds(long[] assetEntrySetIds) {
		if (ArrayUtil.isEmpty(assetEntrySetIds)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler((assetEntrySetIds.length * 2) + 3);

		sb.append(" AND ");
		sb.append("(AssetEntrySet.assetEntrySetId NOT IN (");

		for (long assetEntrySetId : assetEntrySetIds) {
			sb.append(assetEntrySetId);
			sb.append(StringPool.COMMA);
		}

		sb.setIndex(sb.index() - 1);

		sb.append("))");

		return sb.toString();
	}

	protected String getIncludeAssetEntrySetIds(
			long classNameId, long classPK, long[] assetEntrySetIds)
		throws SystemException {

		if (ArrayUtil.isEmpty(assetEntrySetIds)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler((assetEntrySetIds.length * 7) + 2);

		sb.append(StringPool.OPEN_PARENTHESIS);

		for (long assetEntrySetId : assetEntrySetIds) {
			sb.append("((AssetEntrySet.assetEntrySetId = ");
			sb.append(assetEntrySetId);
			sb.append(StringPool.CLOSE_PARENTHESIS);

			if (!isMember(classNameId, classPK, assetEntrySetId)) {
				sb.append(" AND ");
				sb.append(getViewable(classNameId, classPK));
			}

			sb.append(StringPool.CLOSE_PARENTHESIS);
			sb.append(" OR ");
		}

		sb.setIndex(sb.index() - 1);

		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	protected String getJoinBy(
		JSONArray sharedToJSONArray, String[] assetTagNames) {

		String joinBy = StringPool.BLANK;

		if ((sharedToJSONArray != null) && (sharedToJSONArray.length() > 0)) {
			joinBy = CustomSQLUtil.get(JOIN_BY_ASSET_SHARING_ENTRY);
		}

		if (ArrayUtil.isNotEmpty(assetTagNames)) {
			joinBy = joinBy + CustomSQLUtil.get(JOIN_BY_ASSET_TAG);
		}

		return joinBy;
	}

	protected String getSharedTo(
		long classNameId, long classPK, JSONArray sharedToJSONArray) {

		if ((sharedToJSONArray == null) || (sharedToJSONArray.length() == 0)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(
			(sharedToJSONArray.length() * 2) + 2);

		sb.append(StringPool.OPEN_PARENTHESIS);

		for (int i = 0; i < sharedToJSONArray.length(); i++) {
			JSONObject jsonObject = sharedToJSONArray.getJSONObject(i);

			long sharedToClassNameId = jsonObject.getLong("classNameId");
			long sharedToClassPK = jsonObject.getLong("classPK");

			sb.append(
				getSharedTo(
					classNameId, classPK, sharedToClassNameId,
					sharedToClassPK));
			sb.append(" OR ");
		}

		sb.setIndex(sb.index() - 1);

		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	protected String getSharedTo(
		long classNameId, long classPK, long sharedToClassNameId,
		long sharedToClassPK) {

		StringBundler sb = new StringBundler(8);

		sb.append("((AssetSharingEntry2.sharedToClassNameId = ");
		sb.append(sharedToClassNameId);
		sb.append(") AND (AssetSharingEntry2.sharedToClassPK = ");
		sb.append(sharedToClassPK);
		sb.append(StringPool.CLOSE_PARENTHESIS);

		if (!AssetEntrySetParticipantInfoUtil.isMember(
				classNameId, classPK, sharedToClassNameId, sharedToClassPK)) {

			sb.append(" AND ");
			sb.append(getViewable(classNameId, classPK));
		}

		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	protected String getViewable(long classNameId, long classPK) {
		StringBundler sb = new StringBundler(6);

		sb.append("((AssetEntrySet.privateAssetEntrySet = [$FALSE$])");
		sb.append(" OR ((AssetSharingEntry1.sharedToClassNameId = ");
		sb.append(classNameId);
		sb.append(") AND (AssetSharingEntry1.sharedToClassPK = ");
		sb.append(classPK);
		sb.append(")))");

		return sb.toString();
	}

	protected boolean isMember(
			long classNameId, long classPK, long assetEntrySetId)
		throws SystemException {

		List<AssetSharingEntry> assetSharingEntries =
			AssetSharingEntryLocalServiceUtil.getAssetSharingEntries(
				AssetEntrySetConstants.ASSET_ENTRY_SET_CLASS_NAME_ID,
				assetEntrySetId);

		for (AssetSharingEntry assetSharingEntry : assetSharingEntries) {
			if (AssetEntrySetParticipantInfoUtil.isMember(
					classNameId, classPK,
					assetSharingEntry.getSharedToClassNameId(),
					assetSharingEntry.getSharedToClassPK())) {

				return true;
			}
		}

		return false;
	}

	protected String replacePrivateAssetEntrySet(
		String sql, boolean privateAssetEntrySet) {

		String privateAssetEntrySetSQL = StringPool.BLANK;

		if (privateAssetEntrySet) {
			privateAssetEntrySetSQL = _PRIVATE_ASSET_ENTRY_SET_SQL;
		}

		return StringUtil.replace(
			sql, "[$PRIVATE_ASSET_ENTRY_SET]", privateAssetEntrySetSQL);
	}

	protected String replaceTimeComparator(String sql, boolean greaterThan) {
		String comparator = StringPool.LESS_THAN_OR_EQUAL;

		if (greaterThan) {
			comparator = StringPool.GREATER_THAN;
		}

		return StringUtil.replace(sql, "[$TIME_COMPARATOR$]", comparator);
	}

	protected void setAssetTagNames(QueryPos qPos, String[] assetTagNames) {
		if (ArrayUtil.isEmpty(assetTagNames)) {
			return;
		}

		for (String assetTagName : assetTagNames) {
			qPos.add(StringUtil.toLowerCase(assetTagName));
		}
	}

	protected String updateSQL(
			String sql, long classNameId, long classPK, boolean greaterThan,
			boolean privateAssetEntrySet, JSONArray creatorJSONArray,
			JSONArray sharedToJSONArray, long[] excludeAssetEntrySetIds,
			long[] includeAssetEntrySetIds, String[] assetTagNames)
		throws Exception {

		sql = StringUtil.replace(
			sql, "[$JOIN_BY$]",
			getJoinBy(sharedToJSONArray, assetTagNames));

		sql = replacePrivateAssetEntrySet(sql, privateAssetEntrySet);
		sql = replaceTimeComparator(sql, greaterThan);

		List<String> whereClauses = new ArrayList<String>();

		whereClauses.add(getAssetTagNames(classNameId, classPK, assetTagNames));
		whereClauses.add(getCreator(classNameId, classPK, creatorJSONArray));
		whereClauses.add(
			getIncludeAssetEntrySetIds(
				classNameId, classPK, includeAssetEntrySetIds));
		whereClauses.add(getSharedTo(classNameId, classPK, sharedToJSONArray));

		whereClauses.removeAll(_emptyList);

		sql = StringUtil.replace(
			sql, "[$WHERE$]",
			ListUtil.toString(whereClauses, StringPool.BLANK, " OR "));

		return StringUtil.replace(
			sql, "[$EXCLUDE_ASSET_ENTRY_SET_IDS$]",
			getExcludeAssetEntrySetIds(excludeAssetEntrySetIds));
	}

	private static final String _PRIVATE_ASSET_ENTRY_SET_SQL =
		"(AssetEntrySet.privateAssetEntrySet = 1) AND";

	private static final List<String> _emptyList = Arrays.asList(
		StringPool.BLANK);

}