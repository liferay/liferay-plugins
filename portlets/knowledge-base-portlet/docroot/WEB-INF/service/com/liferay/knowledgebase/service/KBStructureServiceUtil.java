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
 * The utility for the k b structure remote service. This utility wraps {@link com.liferay.knowledgebase.service.impl.KBStructureServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBStructureService
 * @see com.liferay.knowledgebase.service.base.KBStructureServiceBaseImpl
 * @see com.liferay.knowledgebase.service.impl.KBStructureServiceImpl
 * @generated
 */
public class KBStructureServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.knowledgebase.service.impl.KBStructureServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.knowledgebase.model.KBStructure addKBStructure(
		java.lang.String portletId, java.lang.String localizedLanguageId,
		java.lang.String title,
		java.util.List<com.liferay.knowledgebase.model.KBStructureField> kbStructureFields,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKBStructure(portletId, localizedLanguageId, title,
			kbStructureFields, serviceContext);
	}

	public static void deleteKBStructure(long kbStructureId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKBStructure(kbStructureId);
	}

	public static void deleteKBStructureLocalization(long kbStructureId,
		java.lang.String localizedLanguageId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteKBStructureLocalization(kbStructureId, localizedLanguageId,
			serviceContext);
	}

	public static void deleteKBStructures(long groupId, long[] kbStructureIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKBStructures(groupId, kbStructureIds);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBStructure> getGroupKBStructures(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getGroupKBStructures(groupId, start, end, orderByComparator);
	}

	public static int getGroupKBStructuresCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupKBStructuresCount(groupId);
	}

	public static com.liferay.knowledgebase.model.KBStructure getKBStructure(
		long kbStructureId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKBStructure(kbStructureId);
	}

	public static com.liferay.knowledgebase.model.KBStructureSearchDisplay getKBStructureSearchDisplay(
		long groupId, java.lang.String title, java.lang.String content,
		java.util.Date startDate, java.util.Date endDate, boolean andOperator,
		int[] curStartValues, int cur, int delta,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKBStructureSearchDisplay(groupId, title, content,
			startDate, endDate, andOperator, curStartValues, cur, delta,
			orderByComparator);
	}

	public static com.liferay.knowledgebase.model.KBStructure updateKBStructure(
		long kbStructureId, java.lang.String localizedLanguageId,
		java.lang.String title,
		java.util.List<com.liferay.knowledgebase.model.KBStructureField> kbStructureFields,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateKBStructure(kbStructureId, localizedLanguageId,
			title, kbStructureFields, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static KBStructureService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					KBStructureService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					KBStructureService.class.getName(), portletClassLoader);

			_service = new KBStructureServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(KBStructureServiceUtil.class,
				"_service");
			MethodCache.remove(KBStructureService.class);
		}

		return _service;
	}

	public void setService(KBStructureService service) {
		MethodCache.remove(KBStructureService.class);

		_service = service;

		ReferenceRegistry.registerReference(KBStructureServiceUtil.class,
			"_service");
		MethodCache.remove(KBStructureService.class);
	}

	private static KBStructureService _service;
}