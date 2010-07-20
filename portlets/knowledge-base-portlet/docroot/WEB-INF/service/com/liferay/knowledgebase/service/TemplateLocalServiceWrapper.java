/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
 * This class is a wrapper for {@link TemplateLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TemplateLocalService
 * @generated
 */
public class TemplateLocalServiceWrapper implements TemplateLocalService {
	public TemplateLocalServiceWrapper(
		TemplateLocalService templateLocalService) {
		_templateLocalService = templateLocalService;
	}

	public com.liferay.knowledgebase.model.Template addTemplate(
		com.liferay.knowledgebase.model.Template template)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.addTemplate(template);
	}

	public com.liferay.knowledgebase.model.Template createTemplate(
		long templateId) {
		return _templateLocalService.createTemplate(templateId);
	}

	public void deleteTemplate(long templateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_templateLocalService.deleteTemplate(templateId);
	}

	public void deleteTemplate(
		com.liferay.knowledgebase.model.Template template)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_templateLocalService.deleteTemplate(template);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.knowledgebase.model.Template getTemplate(long templateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.getTemplate(templateId);
	}

	public com.liferay.knowledgebase.model.Template getTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.getTemplateByUuidAndGroupId(uuid, groupId);
	}

	public java.util.List<com.liferay.knowledgebase.model.Template> getTemplates(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.getTemplates(start, end);
	}

	public int getTemplatesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.getTemplatesCount();
	}

	public com.liferay.knowledgebase.model.Template updateTemplate(
		com.liferay.knowledgebase.model.Template template)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.updateTemplate(template);
	}

	public com.liferay.knowledgebase.model.Template updateTemplate(
		com.liferay.knowledgebase.model.Template template, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.updateTemplate(template, merge);
	}

	public com.liferay.knowledgebase.model.Template addTemplate(long userId,
		java.lang.String title, java.lang.String content,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.addTemplate(userId, title, content,
			description, serviceContext);
	}

	public void addTemplateResources(
		com.liferay.knowledgebase.model.Template template,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_templateLocalService.addTemplateResources(template,
			addCommunityPermissions, addGuestPermissions);
	}

	public void addTemplateResources(
		com.liferay.knowledgebase.model.Template template,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_templateLocalService.addTemplateResources(template,
			communityPermissions, guestPermissions);
	}

	public void deleteGroupTemplates(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_templateLocalService.deleteGroupTemplates(groupId);
	}

	public java.util.List<com.liferay.knowledgebase.model.Template> getGroupTemplates(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.getGroupTemplates(groupId, start, end,
			orderByComparator);
	}

	public int getGroupTemplatesCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.getGroupTemplatesCount(groupId);
	}

	public com.liferay.knowledgebase.model.Template updateTemplate(
		long templateId, java.lang.String title, java.lang.String content,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.updateTemplate(templateId, title, content,
			description, serviceContext);
	}

	public void updateTemplateResources(
		com.liferay.knowledgebase.model.Template template,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_templateLocalService.updateTemplateResources(template,
			communityPermissions, guestPermissions);
	}

	public TemplateLocalService getWrappedTemplateLocalService() {
		return _templateLocalService;
	}

	private TemplateLocalService _templateLocalService;
}