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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="TemplateLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * {@link TemplateLocalService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TemplateLocalService
 * @generated
 */
public class TemplateLocalServiceUtil {
	public static com.liferay.knowledgebase.model.Template addTemplate(
		com.liferay.knowledgebase.model.Template template)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addTemplate(template);
	}

	public static com.liferay.knowledgebase.model.Template createTemplate(
		long templateId) {
		return getService().createTemplate(templateId);
	}

	public static void deleteTemplate(long templateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteTemplate(templateId);
	}

	public static void deleteTemplate(
		com.liferay.knowledgebase.model.Template template)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteTemplate(template);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static int dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.knowledgebase.model.Template getTemplate(
		long templateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTemplate(templateId);
	}

	public static com.liferay.knowledgebase.model.Template getTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTemplateByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<com.liferay.knowledgebase.model.Template> getTemplates(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTemplates(start, end);
	}

	public static int getTemplatesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTemplatesCount();
	}

	public static com.liferay.knowledgebase.model.Template updateTemplate(
		com.liferay.knowledgebase.model.Template template)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTemplate(template);
	}

	public static com.liferay.knowledgebase.model.Template updateTemplate(
		com.liferay.knowledgebase.model.Template template, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTemplate(template, merge);
	}

	public static void clearService() {
		_service = null;
	}

	public static TemplateLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					TemplateLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					TemplateLocalService.class.getName(), portletClassLoader);

			_service = new TemplateLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(TemplateLocalService service) {
		_service = service;
	}

	private static TemplateLocalService _service;
}