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

	/**
	* Adds the template to the database. Also notifies the appropriate model listeners.
	*
	* @param template the template to add
	* @return the template that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Template addTemplate(
		com.liferay.knowledgebase.model.Template template)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.addTemplate(template);
	}

	/**
	* Creates a new template with the primary key. Does not add the template to the database.
	*
	* @param templateId the primary key for the new template
	* @return the new template
	*/
	public com.liferay.knowledgebase.model.Template createTemplate(
		long templateId) {
		return _templateLocalService.createTemplate(templateId);
	}

	/**
	* Deletes the template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param templateId the primary key of the template to delete
	* @throws PortalException if a template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteTemplate(long templateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_templateLocalService.deleteTemplate(templateId);
	}

	/**
	* Deletes the template from the database. Also notifies the appropriate model listeners.
	*
	* @param template the template to delete
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public void deleteTemplate(
		com.liferay.knowledgebase.model.Template template)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_templateLocalService.deleteTemplate(template);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the template with the primary key.
	*
	* @param templateId the primary key of the template to get
	* @return the template
	* @throws PortalException if a template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Template getTemplate(long templateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.getTemplate(templateId);
	}

	/**
	* Gets the template with the UUID and group id.
	*
	* @param uuid the UUID of template to get
	* @param groupId the group id of the template to get
	* @return the template
	* @throws PortalException if a template with the UUID and group id could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Template getTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.getTemplateByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Gets a range of all the templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of templates to return
	* @param end the upper bound of the range of templates to return (not inclusive)
	* @return the range of templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Template> getTemplates(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.getTemplates(start, end);
	}

	/**
	* Gets the number of templates.
	*
	* @return the number of templates
	* @throws SystemException if a system exception occurred
	*/
	public int getTemplatesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.getTemplatesCount();
	}

	/**
	* Updates the template in the database. Also notifies the appropriate model listeners.
	*
	* @param template the template to update
	* @return the template that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Template updateTemplate(
		com.liferay.knowledgebase.model.Template template)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.updateTemplate(template);
	}

	/**
	* Updates the template in the database. Also notifies the appropriate model listeners.
	*
	* @param template the template to update
	* @param merge whether to merge the template with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the template that was updated
	* @throws SystemException if a system exception occurred
	*/
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

	public void setWrappedTemplateLocalService(
		TemplateLocalService templateLocalService) {
		_templateLocalService = templateLocalService;
	}

	private TemplateLocalService _templateLocalService;
}