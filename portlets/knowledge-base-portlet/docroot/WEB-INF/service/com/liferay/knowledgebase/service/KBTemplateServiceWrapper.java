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

package com.liferay.knowledgebase.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link KBTemplateService}.
 *
 * @author Brian Wing Shun Chan
 * @see KBTemplateService
 * @generated
 */
@ProviderType
public class KBTemplateServiceWrapper implements KBTemplateService,
	ServiceWrapper<KBTemplateService> {
	public KBTemplateServiceWrapper(KBTemplateService kbTemplateService) {
		_kbTemplateService = kbTemplateService;
	}

	@Override
	public com.liferay.knowledgebase.model.KBTemplate addKBTemplate(
		java.lang.String portletId, java.lang.String title,
		java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kbTemplateService.addKBTemplate(portletId, title, content,
			serviceContext);
	}

	@Override
	public com.liferay.knowledgebase.model.KBTemplate deleteKBTemplate(
		long kbTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kbTemplateService.deleteKBTemplate(kbTemplateId);
	}

	@Override
	public void deleteKBTemplates(long groupId, long[] kbTemplateIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_kbTemplateService.deleteKBTemplates(groupId, kbTemplateIds);
	}

	@Override
	public java.util.List<com.liferay.knowledgebase.model.KBTemplate> getGroupKBTemplates(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledgebase.model.KBTemplate> orderByComparator) {
		return _kbTemplateService.getGroupKBTemplates(groupId, start, end,
			orderByComparator);
	}

	@Override
	public int getGroupKBTemplatesCount(long groupId) {
		return _kbTemplateService.getGroupKBTemplatesCount(groupId);
	}

	@Override
	public com.liferay.knowledgebase.model.KBTemplate getKBTemplate(
		long kbTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kbTemplateService.getKBTemplate(kbTemplateId);
	}

	@Override
	public com.liferay.knowledgebase.model.KBTemplateSearchDisplay getKBTemplateSearchDisplay(
		long groupId, java.lang.String title, java.lang.String content,
		java.util.Date startDate, java.util.Date endDate, boolean andOperator,
		int[] curStartValues, int cur, int delta,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledgebase.model.KBTemplate> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kbTemplateService.getKBTemplateSearchDisplay(groupId, title,
			content, startDate, endDate, andOperator, curStartValues, cur,
			delta, orderByComparator);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _kbTemplateService.getOSGiServiceIdentifier();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _kbTemplateService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public com.liferay.knowledgebase.model.KBTemplate updateKBTemplate(
		long kbTemplateId, java.lang.String title, java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kbTemplateService.updateKBTemplate(kbTemplateId, title,
			content, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public KBTemplateService getWrappedKBTemplateService() {
		return _kbTemplateService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedKBTemplateService(KBTemplateService kbTemplateService) {
		_kbTemplateService = kbTemplateService;
	}

	@Override
	public KBTemplateService getWrappedService() {
		return _kbTemplateService;
	}

	@Override
	public void setWrappedService(KBTemplateService kbTemplateService) {
		_kbTemplateService = kbTemplateService;
	}

	private KBTemplateService _kbTemplateService;
}