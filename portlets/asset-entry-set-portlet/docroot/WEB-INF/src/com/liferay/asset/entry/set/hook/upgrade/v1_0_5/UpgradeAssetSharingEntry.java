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

package com.liferay.asset.entry.set.hook.upgrade.v1_0_5;

import com.liferay.asset.entry.set.util.AssetEntrySetConstants;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Matthew Kong
 */
public class UpgradeAssetSharingEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgradeAssetSharingEntry();
	}

	protected void upgradeAssetSharingEntry() throws Exception {
		StringBundler sb = new StringBundler(6);

		sb.append("delete AssetSharingEntry from AssetSharingEntry left join ");
		sb.append("AssetEntrySet on AssetSharingEntry.classPK = ");
		sb.append("AssetEntrySet.assetEntrySetId where ");
		sb.append("AssetSharingEntry.classNameId = ");
		sb.append(AssetEntrySetConstants.ASSET_ENTRY_SET_CLASS_NAME_ID);
		sb.append(" and AssetEntrySet.assetEntrySetId is null");

		runSQL(sb.toString());
	}

}