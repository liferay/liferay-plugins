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
import com.liferay.asset.entry.set.participant.AssetEntrySetParticipantInfoUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
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
import java.util.List;

/**
 * @author Calvin Keum
 * @author Sherry Yang
 */
public class AssetEntrySetFinderImpl
	extends BasePersistenceImpl<AssetEntrySet>
	implements AssetEntrySetFinder {

	public static final String FIND_BY_CT_PAESI =
		AssetEntrySetFinder.class.getName() + ".findByCT_PAESI";

	public List<AssetEntrySet> findByCT_PAESI(
			long classNameId, long classPK, long createTime,
			boolean gtCreateTime, long parentAssetEntrySetId,
			JSONArray sharedToJSONArray, int start, int end)
		throws SystemException {

		if ((sharedToJSONArray == null) || (sharedToJSONArray.length() == 0)) {
			return Collections.emptyList();
		}

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_CT_PAESI);

			if (gtCreateTime) {
				sql = StringUtil.replace(
					sql, "[$CREATE_TIME_COMPARATOR$]", ">");
			}
			else {
				sql = StringUtil.replace(
					sql, "[$CREATE_TIME_COMPARATOR$]", "<=");
			}

			sql = StringUtil.replace(
				sql, "[$SHARED_TO$]",
				getSharedTo(classNameId, classPK, sharedToJSONArray));

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

	protected String getSharedTo(
		long classNameId, long classPK, JSONArray sharedToJSONArray) {

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

		StringBundler sb = new StringBundler(15);

		sb.append("(((AssetSharingEntry1.sharedToClassNameId = ");
		sb.append(sharedToClassNameId);
		sb.append(") AND (AssetSharingEntry1.sharedToClassPK = ");
		sb.append(sharedToClassPK);
		sb.append(StringPool.CLOSE_PARENTHESIS);
		sb.append(StringPool.CLOSE_PARENTHESIS);

		if (!AssetEntrySetParticipantInfoUtil.isMember(
				classNameId, classPK, sharedToClassNameId, sharedToClassPK)) {

			sb.append(" AND ((AssetEntrySet.privateAssetEntrySet = [$FALSE$])");
			sb.append(" OR ((AssetSharingEntry2.sharedToClassNameId = ");
			sb.append(classNameId);
			sb.append(") AND (AssetSharingEntry2.sharedToClassPK = ");
			sb.append(classPK);
			sb.append(StringPool.CLOSE_PARENTHESIS);
			sb.append(StringPool.CLOSE_PARENTHESIS);
			sb.append(StringPool.CLOSE_PARENTHESIS);
		}

		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	private static final long _ASSET_ENTRY_SET_CLASS_NAME_ID =
		ClassNameLocalServiceUtil.getClassNameId(AssetEntrySet.class);

}