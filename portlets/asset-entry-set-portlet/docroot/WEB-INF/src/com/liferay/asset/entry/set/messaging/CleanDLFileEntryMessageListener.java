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

package com.liferay.asset.entry.set.messaging;

import com.liferay.asset.entry.set.util.AssetEntrySetConstants;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Calvin Keum
 */
public class CleanDLFileEntryMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		Date date = new Date(System.currentTimeMillis() - Time.DAY);

		DynamicQuery dynamicQuery = DLFileEntryLocalServiceUtil.dynamicQuery();

		Property classNameIdProperty = PropertyFactoryUtil.forName(
			"classNameId");

		dynamicQuery.add(
			classNameIdProperty.eq(
				AssetEntrySetConstants.ASSET_ENTRY_SET_CLASS_NAME_ID));

		Property classPKProperty = PropertyFactoryUtil.forName("classPK");

		dynamicQuery.add(classPKProperty.eq(0L));

		List<DLFileEntry> dlFileEntries =
			DLFileEntryLocalServiceUtil.dynamicQuery(dynamicQuery);

		for (DLFileEntry fileEntry : dlFileEntries) {
			if (DateUtil.compareTo(date, fileEntry.getCreateDate()) > 0) {
				DLFileEntryLocalServiceUtil.deleteDLFileEntry(fileEntry);
			}
		}
	}

}