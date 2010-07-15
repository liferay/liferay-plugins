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

package com.liferay.portal.workflow.kaleo.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <p>
 * This class provides static methods for the
 * {@link KaleoNotificationRecipientLocalService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoNotificationRecipientLocalService
 * @generated
 */
public class KaleoNotificationRecipientLocalServiceUtil {
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient addKaleoNotificationRecipient(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKaleoNotificationRecipient(kaleoNotificationRecipient);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient createKaleoNotificationRecipient(
		long kaleoNotificationRecipientId) {
		return getService()
				   .createKaleoNotificationRecipient(kaleoNotificationRecipientId);
	}

	public static void deleteKaleoNotificationRecipient(
		long kaleoNotificationRecipientId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteKaleoNotificationRecipient(kaleoNotificationRecipientId);
	}

	public static void deleteKaleoNotificationRecipient(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKaleoNotificationRecipient(kaleoNotificationRecipient);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient getKaleoNotificationRecipient(
		long kaleoNotificationRecipientId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoNotificationRecipient(kaleoNotificationRecipientId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> getKaleoNotificationRecipients(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoNotificationRecipients(start, end);
	}

	public static int getKaleoNotificationRecipientsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoNotificationRecipientsCount();
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient updateKaleoNotificationRecipient(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateKaleoNotificationRecipient(kaleoNotificationRecipient);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient updateKaleoNotificationRecipient(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateKaleoNotificationRecipient(kaleoNotificationRecipient,
			merge);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient addKaleoNotificationRecipient(
		long kaleoDefinitionId, long kaleoNotificationId,
		com.liferay.portal.workflow.kaleo.definition.Recipient recipient,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKaleoNotificationRecipient(kaleoDefinitionId,
			kaleoNotificationId, recipient, serviceContext);
	}

	public static void deleteCompanyKaleoNotificationRecipients(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCompanyKaleoNotificationRecipients(companyId);
	}

	public static void deleteKaleoDefinitionKaleoNotificationRecipients(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteKaleoDefinitionKaleoNotificationRecipients(kaleoDefinitionId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> getKaleoNotificationRecipients(
		long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoNotificationRecipients(kaleoNotificationId);
	}

	public static void clearService() {
		_service = null;
	}

	public static KaleoNotificationRecipientLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					KaleoNotificationRecipientLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					KaleoNotificationRecipientLocalService.class.getName(),
					portletClassLoader);

			_service = new KaleoNotificationRecipientLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(KaleoNotificationRecipientLocalService service) {
		_service = service;
	}

	private static KaleoNotificationRecipientLocalService _service;
}