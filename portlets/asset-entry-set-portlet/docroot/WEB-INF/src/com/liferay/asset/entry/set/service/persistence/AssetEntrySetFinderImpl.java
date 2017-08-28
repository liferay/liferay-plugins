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
import com.liferay.asset.entry.set.util.AssetEntrySetConstants;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.ArrayList;
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

}