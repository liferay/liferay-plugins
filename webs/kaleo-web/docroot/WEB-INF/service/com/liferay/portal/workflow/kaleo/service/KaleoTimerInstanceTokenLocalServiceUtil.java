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

package com.liferay.portal.workflow.kaleo.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for KaleoTimerInstanceToken. This utility wraps
 * {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoTimerInstanceTokenLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTimerInstanceTokenLocalService
 * @see com.liferay.portal.workflow.kaleo.service.base.KaleoTimerInstanceTokenLocalServiceBaseImpl
 * @see com.liferay.portal.workflow.kaleo.service.impl.KaleoTimerInstanceTokenLocalServiceImpl
 * @generated
 */
public class KaleoTimerInstanceTokenLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoTimerInstanceTokenLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the kaleo timer instance token to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTimerInstanceToken the kaleo timer instance token
	* @return the kaleo timer instance token that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken addKaleoTimerInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken kaleoTimerInstanceToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addKaleoTimerInstanceToken(kaleoTimerInstanceToken);
	}

	/**
	* Creates a new kaleo timer instance token with the primary key. Does not add the kaleo timer instance token to the database.
	*
	* @param kaleoTimerInstanceTokenId the primary key for the new kaleo timer instance token
	* @return the new kaleo timer instance token
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken createKaleoTimerInstanceToken(
		long kaleoTimerInstanceTokenId) {
		return getService()
				   .createKaleoTimerInstanceToken(kaleoTimerInstanceTokenId);
	}

	/**
	* Deletes the kaleo timer instance token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTimerInstanceTokenId the primary key of the kaleo timer instance token
	* @return the kaleo timer instance token that was removed
	* @throws PortalException if a kaleo timer instance token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken deleteKaleoTimerInstanceToken(
		long kaleoTimerInstanceTokenId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .deleteKaleoTimerInstanceToken(kaleoTimerInstanceTokenId);
	}

	/**
	* Deletes the kaleo timer instance token from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTimerInstanceToken the kaleo timer instance token
	* @return the kaleo timer instance token that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken deleteKaleoTimerInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken kaleoTimerInstanceToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .deleteKaleoTimerInstanceToken(kaleoTimerInstanceToken);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken fetchKaleoTimerInstanceToken(
		long kaleoTimerInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchKaleoTimerInstanceToken(kaleoTimerInstanceTokenId);
	}

	/**
	* Returns the kaleo timer instance token with the primary key.
	*
	* @param kaleoTimerInstanceTokenId the primary key of the kaleo timer instance token
	* @return the kaleo timer instance token
	* @throws PortalException if a kaleo timer instance token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken getKaleoTimerInstanceToken(
		long kaleoTimerInstanceTokenId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTimerInstanceToken(kaleoTimerInstanceTokenId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the kaleo timer instance tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo timer instance tokens
	* @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	* @return the range of kaleo timer instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken> getKaleoTimerInstanceTokens(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTimerInstanceTokens(start, end);
	}

	/**
	* Returns the number of kaleo timer instance tokens.
	*
	* @return the number of kaleo timer instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public static int getKaleoTimerInstanceTokensCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTimerInstanceTokensCount();
	}

	/**
	* Updates the kaleo timer instance token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoTimerInstanceToken the kaleo timer instance token
	* @return the kaleo timer instance token that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken updateKaleoTimerInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken kaleoTimerInstanceToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateKaleoTimerInstanceToken(kaleoTimerInstanceToken);
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

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken addKaleoTimerInstanceToken(
		long kaleoInstanceTokenId, long kaleoTaskInstanceTokenId,
		long kaleoTimerId, java.lang.String kaleoTimerName,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKaleoTimerInstanceToken(kaleoInstanceTokenId,
			kaleoTaskInstanceTokenId, kaleoTimerId, kaleoTimerName,
			workflowContext, serviceContext);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken> addKaleoTimerInstanceTokens(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.util.Collection<com.liferay.portal.workflow.kaleo.model.KaleoTimer> kaleoTimers,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKaleoTimerInstanceTokens(kaleoInstanceToken,
			kaleoTaskInstanceToken, kaleoTimers, workflowContext, serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken completeKaleoTimerInstanceToken(
		long kaleoTimerInstanceTokenId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .completeKaleoTimerInstanceToken(kaleoTimerInstanceTokenId,
			serviceContext);
	}

	public static void completeKaleoTimerInstanceTokens(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken> kaleoTimerInstanceTokens,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.completeKaleoTimerInstanceTokens(kaleoTimerInstanceTokens,
			serviceContext);
	}

	public static void completeKaleoTimerInstanceTokens(
		long kaleoInstanceTokenId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.completeKaleoTimerInstanceTokens(kaleoInstanceTokenId,
			serviceContext);
	}

	public static void deleteKaleoTimerInstanceToken(
		long kaleoInstanceTokenId, long kaleoTimerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteKaleoTimerInstanceToken(kaleoInstanceTokenId, kaleoTimerId);
	}

	public static void deleteKaleoTimerInstanceTokens(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKaleoTimerInstanceTokens(kaleoInstanceId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken getKaleoTimerInstanceToken(
		long kaleoInstanceTokenId, long kaleoTimerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoTimerInstanceToken(kaleoInstanceTokenId,
			kaleoTimerId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken> getKaleoTimerInstanceTokens(
		long kaleoInstanceTokenId, boolean completed, boolean blocking,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoTimerInstanceTokens(kaleoInstanceTokenId,
			completed, blocking, serviceContext);
	}

	public static int getKaleoTimerInstanceTokensCount(
		long kaleoInstanceTokenId, boolean completed, boolean blocking,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoTimerInstanceTokensCount(kaleoInstanceTokenId,
			completed, blocking, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static KaleoTimerInstanceTokenLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					KaleoTimerInstanceTokenLocalService.class.getName());

			if (invokableLocalService instanceof KaleoTimerInstanceTokenLocalService) {
				_service = (KaleoTimerInstanceTokenLocalService)invokableLocalService;
			}
			else {
				_service = new KaleoTimerInstanceTokenLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(KaleoTimerInstanceTokenLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(KaleoTimerInstanceTokenLocalService service) {
	}

	private static KaleoTimerInstanceTokenLocalService _service;
}