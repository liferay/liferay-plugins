/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link KBTemplateService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KBTemplateService
 * @generated
 */
public class KBTemplateServiceWrapper implements KBTemplateService,
	ServiceWrapper<KBTemplateService> {
	public KBTemplateServiceWrapper(KBTemplateService kbTemplateService) {
		_kbTemplateService = kbTemplateService;
	}

	public com.liferay.knowledgebase.model.KBTemplate addKBTemplate(
		java.lang.String portletId, java.lang.String title,
		java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateService.addKBTemplate(portletId, title, content,
			serviceContext);
	}

	public void deleteKBTemplate(long kbTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbTemplateService.deleteKBTemplate(kbTemplateId);
	}

	public void deleteKBTemplates(long groupId, long[] kbTemplateIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbTemplateService.deleteKBTemplates(groupId, kbTemplateIds);
	}

	public java.util.List<com.liferay.knowledgebase.model.KBTemplate> getGroupKBTemplates(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateService.getGroupKBTemplates(groupId, start, end,
			orderByComparator);
	}

	public int getGroupKBTemplatesCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateService.getGroupKBTemplatesCount(groupId);
	}

	public com.liferay.knowledgebase.model.KBTemplate getKBTemplate(
		long kbTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateService.getKBTemplate(kbTemplateId);
	}

	public com.liferay.knowledgebase.model.KBTemplateSearchDisplay getKBTemplateSearchDisplay(
		long groupId, java.lang.String title, java.lang.String content,
		java.util.Date startDate, java.util.Date endDate, boolean andOperator,
		int[] curStartValues, int cur, int delta,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateService.getKBTemplateSearchDisplay(groupId, title,
			content, startDate, endDate, andOperator, curStartValues, cur,
			delta, orderByComparator);
	}

	public com.liferay.knowledgebase.model.KBTemplate updateKBTemplate(
		long kbTemplateId, java.lang.String title, java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateService.updateKBTemplate(kbTemplateId, title,
			content, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public KBTemplateService getWrappedKBTemplateService() {
		return _kbTemplateService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedKBTemplateService(KBTemplateService kbTemplateService) {
		_kbTemplateService = kbTemplateService;
	}

	public KBTemplateService getWrappedService() {
		return _kbTemplateService;
	}

	public void setWrappedService(KBTemplateService kbTemplateService) {
		_kbTemplateService = kbTemplateService;
	}

	private KBTemplateService _kbTemplateService;
}