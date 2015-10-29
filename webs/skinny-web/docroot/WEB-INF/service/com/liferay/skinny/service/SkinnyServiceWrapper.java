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

package com.liferay.skinny.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SkinnyService}.
 *
 * @author Brian Wing Shun Chan
 * @see SkinnyService
 * @generated
 */
@ProviderType
public class SkinnyServiceWrapper implements SkinnyService,
	ServiceWrapper<SkinnyService> {
	public SkinnyServiceWrapper(SkinnyService skinnyService) {
		_skinnyService = skinnyService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _skinnyService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.skinny.model.SkinnyDDLRecord> getSkinnyDDLRecords(
		long ddlRecordSetId) throws java.lang.Exception {
		return _skinnyService.getSkinnyDDLRecords(ddlRecordSetId);
	}

	@Override
	public java.util.List<com.liferay.skinny.model.SkinnyJournalArticle> getSkinnyJournalArticles(
		long companyId, java.lang.String groupName, long ddmStructureId,
		java.lang.String locale) throws java.lang.Exception {
		return _skinnyService.getSkinnyJournalArticles(companyId, groupName,
			ddmStructureId, locale);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _skinnyService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public SkinnyService getWrappedSkinnyService() {
		return _skinnyService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedSkinnyService(SkinnyService skinnyService) {
		_skinnyService = skinnyService;
	}

	@Override
	public SkinnyService getWrappedService() {
		return _skinnyService;
	}

	@Override
	public void setWrappedService(SkinnyService skinnyService) {
		_skinnyService = skinnyService;
	}

	private SkinnyService _skinnyService;
}