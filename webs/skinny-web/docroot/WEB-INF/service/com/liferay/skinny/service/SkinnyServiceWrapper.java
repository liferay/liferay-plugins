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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link SkinnyService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SkinnyService
 * @generated
 */
public class SkinnyServiceWrapper implements SkinnyService,
	ServiceWrapper<SkinnyService> {
	public SkinnyServiceWrapper(SkinnyService skinnyService) {
		_skinnyService = skinnyService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _skinnyService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_skinnyService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _skinnyService.invokeMethod(name, parameterTypes, arguments);
	}

	public java.util.List<com.liferay.skinny.model.SkinnyDDLRecord> getSkinnyDDLRecords(
		long ddlRecordSetId) throws java.lang.Exception {
		return _skinnyService.getSkinnyDDLRecords(ddlRecordSetId);
	}

	public java.util.List<com.liferay.skinny.model.SkinnyJournalArticle> getSkinnyJournalArticles(
		long companyId, java.lang.String groupName,
		java.lang.String journalStructureId, java.lang.String locale)
		throws java.lang.Exception {
		return _skinnyService.getSkinnyJournalArticles(companyId, groupName,
			journalStructureId, locale);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SkinnyService getWrappedSkinnyService() {
		return _skinnyService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSkinnyService(SkinnyService skinnyService) {
		_skinnyService = skinnyService;
	}

	public SkinnyService getWrappedService() {
		return _skinnyService;
	}

	public void setWrappedService(SkinnyService skinnyService) {
		_skinnyService = skinnyService;
	}

	private SkinnyService _skinnyService;
}