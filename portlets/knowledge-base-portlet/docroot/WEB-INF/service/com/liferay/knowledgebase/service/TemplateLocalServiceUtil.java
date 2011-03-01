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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the template local service. This utility wraps {@link com.liferay.knowledgebase.service.impl.TemplateLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TemplateLocalService
 * @see com.liferay.knowledgebase.service.base.TemplateLocalServiceBaseImpl
 * @see com.liferay.knowledgebase.service.impl.TemplateLocalServiceImpl
 * @generated
 */
public class TemplateLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.knowledgebase.service.impl.TemplateLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the template to the database. Also notifies the appropriate model listeners.
	*
	* @param template the template to add
	* @return the template that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Template addTemplate(
		com.liferay.knowledgebase.model.Template template)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addTemplate(template);
	}

	/**
	* Creates a new template with the primary key. Does not add the template to the database.
	*
	* @param templateId the primary key for the new template
	* @return the new template
	*/
	public static com.liferay.knowledgebase.model.Template createTemplate(
		long templateId) {
		return getService().createTemplate(templateId);
	}

	/**
	* Deletes the template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param templateId the primary key of the template to delete
	* @throws PortalException if a template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTemplate(long templateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteTemplate(templateId);
	}

	/**
	* Deletes the template from the database. Also notifies the appropriate model listeners.
	*
	* @param template the template to delete
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTemplate(
		com.liferay.knowledgebase.model.Template template)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteTemplate(template);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the template with the primary key.
	*
	* @param templateId the primary key of the template to get
	* @return the template
	* @throws PortalException if a template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Template getTemplate(
		long templateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTemplate(templateId);
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
	public static com.liferay.knowledgebase.model.Template getTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTemplateByUuidAndGroupId(uuid, groupId);
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
	public static java.util.List<com.liferay.knowledgebase.model.Template> getTemplates(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTemplates(start, end);
	}

	/**
	* Gets the number of templates.
	*
	* @return the number of templates
	* @throws SystemException if a system exception occurred
	*/
	public static int getTemplatesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTemplatesCount();
	}

	/**
	* Updates the template in the database. Also notifies the appropriate model listeners.
	*
	* @param template the template to update
	* @return the template that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Template updateTemplate(
		com.liferay.knowledgebase.model.Template template)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTemplate(template);
	}

	/**
	* Updates the template in the database. Also notifies the appropriate model listeners.
	*
	* @param template the template to update
	* @param merge whether to merge the template with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the template that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Template updateTemplate(
		com.liferay.knowledgebase.model.Template template, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTemplate(template, merge);
	}

	public static com.liferay.knowledgebase.model.Template addTemplate(
		long userId, java.lang.String title, java.lang.String content,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addTemplate(userId, title, content, description,
			serviceContext);
	}

	public static void addTemplateResources(
		com.liferay.knowledgebase.model.Template template,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addTemplateResources(template, addCommunityPermissions,
			addGuestPermissions);
	}

	public static void addTemplateResources(
		com.liferay.knowledgebase.model.Template template,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addTemplateResources(template, communityPermissions,
			guestPermissions);
	}

	public static void deleteGroupTemplates(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteGroupTemplates(groupId);
	}

	public static java.util.List<com.liferay.knowledgebase.model.Template> getGroupTemplates(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getGroupTemplates(groupId, start, end, orderByComparator);
	}

	public static int getGroupTemplatesCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupTemplatesCount(groupId);
	}

	public static java.util.List<com.liferay.knowledgebase.model.Template> search(
		long groupId, java.lang.String title, java.lang.String content,
		java.util.Date startDate, java.util.Date endDate, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(groupId, title, content, startDate, endDate,
			andOperator, start, end, orderByComparator);
	}

	public static com.liferay.knowledgebase.model.Template updateTemplate(
		long templateId, java.lang.String title, java.lang.String content,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateTemplate(templateId, title, content, description,
			serviceContext);
	}

	public static void updateTemplateResources(
		com.liferay.knowledgebase.model.Template template,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.updateTemplateResources(template, communityPermissions,
			guestPermissions);
	}

	public static void clearService() {
		_service = null;
	}

	public static TemplateLocalService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TemplateLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					TemplateLocalService.class.getName(), portletClassLoader);

			_service = new TemplateLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(TemplateLocalServiceUtil.class,
				"_service");
			MethodCache.remove(TemplateLocalService.class);
		}

		return _service;
	}

	public void setService(TemplateLocalService service) {
		MethodCache.remove(TemplateLocalService.class);

		_service = service;

		ReferenceRegistry.registerReference(TemplateLocalServiceUtil.class,
			"_service");
		MethodCache.remove(TemplateLocalService.class);
	}

	private static TemplateLocalService _service;
}