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

package com.liferay.microblogs.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the microblogs entry remote service. This utility wraps {@link com.liferay.microblogs.service.impl.MicroblogsEntryServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MicroblogsEntryService
 * @see com.liferay.microblogs.service.base.MicroblogsEntryServiceBaseImpl
 * @see com.liferay.microblogs.service.impl.MicroblogsEntryServiceImpl
 * @generated
 */
public class MicroblogsEntryServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.microblogs.service.impl.MicroblogsEntryServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.microblogs.model.MicroblogsEntry addMicroblogsEntry(
		long userId, java.lang.String content, int type, long receiverUserId,
		long receiverMicroblogsEntryId, int socialRelationType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addMicroblogsEntry(userId, content, type, receiverUserId,
			receiverMicroblogsEntryId, socialRelationType, serviceContext);
	}

	public static void deleteMicroblogsEntry(long microblogsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteMicroblogsEntry(microblogsEntryId);
	}

	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getMicroblogsEntries(start, end);
	}

	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		java.lang.String assetTagName, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getMicroblogsEntries(assetTagName, start, end);
	}

	public static int getMicroblogsEntriesCount()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getMicroblogsEntriesCount();
	}

	public static int getMicroblogsEntriesCount(java.lang.String assetTagName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getMicroblogsEntriesCount(assetTagName);
	}

	public static com.liferay.microblogs.model.MicroblogsEntry getMicroblogsEntry(
		long microblogsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getMicroblogsEntry(microblogsEntryId);
	}

	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getUserMicroblogsEntries(
		long microblogsEntryUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getUserMicroblogsEntries(microblogsEntryUserId, start, end);
	}

	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getUserMicroblogsEntries(
		long microblogsEntryUserId, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getUserMicroblogsEntries(microblogsEntryUserId, type,
			start, end);
	}

	public static int getUserMicroblogsEntriesCount(long microblogsEntryUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserMicroblogsEntriesCount(microblogsEntryUserId);
	}

	public static int getUserMicroblogsEntriesCount(
		long microblogsEntryUserId, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getUserMicroblogsEntriesCount(microblogsEntryUserId, type);
	}

	public static com.liferay.microblogs.model.MicroblogsEntry updateMicroblogsEntry(
		long microblogsEntryId, java.lang.String content,
		int socialRelationType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateMicroblogsEntry(microblogsEntryId, content,
			socialRelationType, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static MicroblogsEntryService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					MicroblogsEntryService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					MicroblogsEntryService.class.getName(), portletClassLoader);

			_service = new MicroblogsEntryServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(MicroblogsEntryServiceUtil.class,
				"_service");
			MethodCache.remove(MicroblogsEntryService.class);
		}

		return _service;
	}

	public void setService(MicroblogsEntryService service) {
		MethodCache.remove(MicroblogsEntryService.class);

		_service = service;

		ReferenceRegistry.registerReference(MicroblogsEntryServiceUtil.class,
			"_service");
		MethodCache.remove(MicroblogsEntryService.class);
	}

	private static MicroblogsEntryService _service;
}