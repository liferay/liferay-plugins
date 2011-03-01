/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

/**
 * <p>
 * This class is a wrapper for {@link TemplateService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TemplateService
 * @generated
 */
public class TemplateServiceWrapper implements TemplateService {
	public TemplateServiceWrapper(TemplateService templateService) {
		_templateService = templateService;
	}

	public com.liferay.knowledgebase.model.Template addTemplate(
		java.lang.String title, java.lang.String content,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _templateService.addTemplate(title, content, description,
			serviceContext);
	}

	public void deleteTemplate(long templateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_templateService.deleteTemplate(templateId);
	}

	public java.util.List<com.liferay.knowledgebase.model.Template> getGroupTemplates(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _templateService.getGroupTemplates(groupId, start, end,
			orderByComparator);
	}

	public int getGroupTemplatesCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _templateService.getGroupTemplatesCount(groupId);
	}

	public com.liferay.knowledgebase.model.Template getTemplate(long templateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _templateService.getTemplate(templateId);
	}

	public com.liferay.knowledgebase.model.TemplateSearchDisplay getTemplateSearchDisplay(
		long groupId, java.lang.String title, java.lang.String content,
		java.util.Date startDate, java.util.Date endDate, boolean andOperator,
		int[] curStartValues, int cur, int delta,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _templateService.getTemplateSearchDisplay(groupId, title,
			content, startDate, endDate, andOperator, curStartValues, cur,
			delta, orderByComparator);
	}

	public com.liferay.knowledgebase.model.Template updateTemplate(
		long templateId, java.lang.String title, java.lang.String content,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _templateService.updateTemplate(templateId, title, content,
			description, serviceContext);
	}

	public TemplateService getWrappedTemplateService() {
		return _templateService;
	}

	public void setWrappedTemplateService(TemplateService templateService) {
		_templateService = templateService;
	}

	private TemplateService _templateService;
}