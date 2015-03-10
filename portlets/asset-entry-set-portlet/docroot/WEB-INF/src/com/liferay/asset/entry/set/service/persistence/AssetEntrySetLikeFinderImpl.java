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

import com.liferay.asset.entry.set.model.AssetEntrySetLike;
import com.liferay.asset.entry.set.model.impl.AssetEntrySetLikeImpl;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.List;

/**
 * @author Sherry Yang
 */
public class AssetEntrySetLikeFinderImpl
	extends BasePersistenceImpl<AssetEntrySetLike>
	implements AssetEntrySetLikeFinder {

	public static final String FIND_BY_AESI_NOTC_C =
		AssetEntrySetLikeFinder.class.getName() + ".findByAESI_NotC_C";

	public List<AssetEntrySetLike> findByAESI_NotC_C(
			long assetEntrySetId, long classNameId, long classPK, int start,
			int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_AESI_NOTC_C);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("AssetEntrySetLike", AssetEntrySetLikeImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(assetEntrySetId);
			qPos.add(classNameId);
			qPos.add(classPK);

			return (List<AssetEntrySetLike>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}