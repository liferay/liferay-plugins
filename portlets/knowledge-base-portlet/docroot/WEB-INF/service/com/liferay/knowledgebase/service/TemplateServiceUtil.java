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
 * The utility for the template remote service. This utility wraps {@link com.liferay.knowledgebase.service.impl.TemplateServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TemplateService
 * @see com.liferay.knowledgebase.service.base.TemplateServiceBaseImpl
 * @see com.liferay.knowledgebase.service.impl.TemplateServiceImpl
 * @generated
 */
public class TemplateServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.knowledgebase.service.impl.TemplateServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.knowledgebase.model.Template addTemplate(
		java.lang.String title, java.lang.String content,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addTemplate(title, content, description, serviceContext);
	}

	public static void deleteTemplate(long templateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteTemplate(templateId);
	}

	public static void deleteTemplates(long groupId, long[] templateIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteTemplates(groupId, templateIds);
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

	public static com.liferay.knowledgebase.model.Template getTemplate(
		long templateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTemplate(templateId);
	}

	public static com.liferay.knowledgebase.model.TemplateSearchDisplay getTemplateSearchDisplay(
		long groupId, java.lang.String title, java.lang.String content,
		java.util.Date startDate, java.util.Date endDate, boolean andOperator,
		int[] curStartValues, int cur, int delta,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getTemplateSearchDisplay(groupId, title, content,
			startDate, endDate, andOperator, curStartValues, cur, delta,
			orderByComparator);
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

	public static void clearService() {
		_service = null;
	}

	public static TemplateService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TemplateService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					TemplateService.class.getName(), portletClassLoader);

			_service = new TemplateServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(TemplateServiceUtil.class,
				"_service");
			MethodCache.remove(TemplateService.class);
		}

		return _service;
	}

	public void setService(TemplateService service) {
		MethodCache.remove(TemplateService.class);

		_service = service;

		ReferenceRegistry.registerReference(TemplateServiceUtil.class,
			"_service");
		MethodCache.remove(TemplateService.class);
	}

	private static TemplateService _service;
}