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

package com.liferay.portal.workflow.kaleo.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the kaleo notification recipient local service. This utility wraps {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoNotificationRecipientLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNotificationRecipientLocalService
 * @see com.liferay.portal.workflow.kaleo.service.base.KaleoNotificationRecipientLocalServiceBaseImpl
 * @see com.liferay.portal.workflow.kaleo.service.impl.KaleoNotificationRecipientLocalServiceImpl
 * @generated
 */
public class KaleoNotificationRecipientLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoNotificationRecipientLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the kaleo notification recipient to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotificationRecipient the kaleo notification recipient
	* @return the kaleo notification recipient that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient addKaleoNotificationRecipient(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKaleoNotificationRecipient(kaleoNotificationRecipient);
	}

	/**
	* Creates a new kaleo notification recipient with the primary key. Does not add the kaleo notification recipient to the database.
	*
	* @param kaleoNotificationRecipientId the primary key for the new kaleo notification recipient
	* @return the new kaleo notification recipient
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient createKaleoNotificationRecipient(
		long kaleoNotificationRecipientId) {
		return getService()
				   .createKaleoNotificationRecipient(kaleoNotificationRecipientId);
	}

	/**
	* Deletes the kaleo notification recipient with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotificationRecipientId the primary key of the kaleo notification recipient
	* @throws PortalException if a kaleo notification recipient with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteKaleoNotificationRecipient(
		long kaleoNotificationRecipientId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteKaleoNotificationRecipient(kaleoNotificationRecipientId);
	}

	/**
	* Deletes the kaleo notification recipient from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotificationRecipient the kaleo notification recipient
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteKaleoNotificationRecipient(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKaleoNotificationRecipient(kaleoNotificationRecipient);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
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
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
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
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
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
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient fetchKaleoNotificationRecipient(
		long kaleoNotificationRecipientId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchKaleoNotificationRecipient(kaleoNotificationRecipientId);
	}

	/**
	* Returns the kaleo notification recipient with the primary key.
	*
	* @param kaleoNotificationRecipientId the primary key of the kaleo notification recipient
	* @return the kaleo notification recipient
	* @throws PortalException if a kaleo notification recipient with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient getKaleoNotificationRecipient(
		long kaleoNotificationRecipientId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoNotificationRecipient(kaleoNotificationRecipientId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the kaleo notification recipients.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo notification recipients
	* @param end the upper bound of the range of kaleo notification recipients (not inclusive)
	* @return the range of kaleo notification recipients
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> getKaleoNotificationRecipients(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoNotificationRecipients(start, end);
	}

	/**
	* Returns the number of kaleo notification recipients.
	*
	* @return the number of kaleo notification recipients
	* @throws SystemException if a system exception occurred
	*/
	public static int getKaleoNotificationRecipientsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoNotificationRecipientsCount();
	}

	/**
	* Updates the kaleo notification recipient in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotificationRecipient the kaleo notification recipient
	* @return the kaleo notification recipient that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient updateKaleoNotificationRecipient(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateKaleoNotificationRecipient(kaleoNotificationRecipient);
	}

	/**
	* Updates the kaleo notification recipient in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotificationRecipient the kaleo notification recipient
	* @param merge whether to merge the kaleo notification recipient with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the kaleo notification recipient that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient updateKaleoNotificationRecipient(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateKaleoNotificationRecipient(kaleoNotificationRecipient,
			merge);
	}

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
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					KaleoNotificationRecipientLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					KaleoNotificationRecipientLocalService.class.getName(),
					portletClassLoader);

			_service = new KaleoNotificationRecipientLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(KaleoNotificationRecipientLocalServiceUtil.class,
				"_service");
			MethodCache.remove(KaleoNotificationRecipientLocalService.class);
		}

		return _service;
	}

	public void setService(KaleoNotificationRecipientLocalService service) {
		MethodCache.remove(KaleoNotificationRecipientLocalService.class);

		_service = service;

		ReferenceRegistry.registerReference(KaleoNotificationRecipientLocalServiceUtil.class,
			"_service");
		MethodCache.remove(KaleoNotificationRecipientLocalService.class);
	}

	private static KaleoNotificationRecipientLocalService _service;
}