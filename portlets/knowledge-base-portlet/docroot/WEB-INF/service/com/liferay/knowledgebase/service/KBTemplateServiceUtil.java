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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for KBTemplate. This utility wraps
 * {@link com.liferay.knowledgebase.service.impl.KBTemplateServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see KBTemplateService
 * @see com.liferay.knowledgebase.service.base.KBTemplateServiceBaseImpl
 * @see com.liferay.knowledgebase.service.impl.KBTemplateServiceImpl
 * @generated
 */
public class KBTemplateServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.knowledgebase.service.impl.KBTemplateServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.knowledgebase.model.KBTemplate addKBTemplate(
		java.lang.String portletId, java.lang.String title,
		java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKBTemplate(portletId, title, content, serviceContext);
	}

	public static com.liferay.knowledgebase.model.KBTemplate deleteKBTemplate(
		long kbTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteKBTemplate(kbTemplateId);
	}

	public static void deleteKBTemplates(long groupId, long[] kbTemplateIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKBTemplates(groupId, kbTemplateIds);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBTemplate> getGroupKBTemplates(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getGroupKBTemplates(groupId, start, end, orderByComparator);
	}

	public static int getGroupKBTemplatesCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupKBTemplatesCount(groupId);
	}

	public static com.liferay.knowledgebase.model.KBTemplate getKBTemplate(
		long kbTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKBTemplate(kbTemplateId);
	}

	public static com.liferay.knowledgebase.model.KBTemplateSearchDisplay getKBTemplateSearchDisplay(
		long groupId, java.lang.String title, java.lang.String content,
		java.util.Date startDate, java.util.Date endDate, boolean andOperator,
		int[] curStartValues, int cur, int delta,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKBTemplateSearchDisplay(groupId, title, content,
			startDate, endDate, andOperator, curStartValues, cur, delta,
			orderByComparator);
	}

	public static com.liferay.knowledgebase.model.KBTemplate updateKBTemplate(
		long kbTemplateId, java.lang.String title, java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateKBTemplate(kbTemplateId, title, content,
			serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static KBTemplateService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					KBTemplateService.class.getName());

			if (invokableService instanceof KBTemplateService) {
				_service = (KBTemplateService)invokableService;
			}
			else {
				_service = new KBTemplateServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(KBTemplateServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(KBTemplateService service) {
	}

	private static KBTemplateService _service;
}