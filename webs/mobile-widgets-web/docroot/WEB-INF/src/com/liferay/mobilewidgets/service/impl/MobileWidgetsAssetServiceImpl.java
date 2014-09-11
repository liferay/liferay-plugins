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

package com.liferay.mobilewidgets.service.impl;

import com.liferay.mobilewidgets.service.base.MobileWidgetsAssetServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.persistence.AssetEntryQuery;

import java.util.List;
import java.util.Locale;

/**
 * The implementation of the mobile widgets asset remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.mobilewidgets.service.MobileWidgetsAssetService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author José Manuel Navarro
 * @see com.liferay.mobilewidgets.service.base.MobileWidgetsAssetServiceBaseImpl
 * @see com.liferay.mobilewidgets.service.MobileWidgetsAssetServiceUtil
 */
public class MobileWidgetsAssetServiceImpl
		extends MobileWidgetsAssetServiceBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.mobilewidgets.service.MobileWidgetsAssetServiceUtil} to access the mobile widgets asset remote service.
	 */

	@Override
	public List<AssetEntry> getEntries(
				AssetEntryQuery entryQuery, Locale locale)
			throws PortalException, SystemException {

		List<AssetEntry> assetEntries = assetEntryLocalService.getEntries(
			entryQuery);

		String languageId = LanguageUtil.getLanguageId(locale);

		for (AssetEntry assetEntry : assetEntries) {
			assetEntry.setTitleCurrentLanguageId(languageId);
		}

		return assetEntries;
	}

}