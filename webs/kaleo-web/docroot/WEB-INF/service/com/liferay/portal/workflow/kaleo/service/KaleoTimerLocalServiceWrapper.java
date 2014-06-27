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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link KaleoTimerLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTimerLocalService
 * @generated
 */
public class KaleoTimerLocalServiceWrapper implements KaleoTimerLocalService,
	ServiceWrapper<KaleoTimerLocalService> {
	public KaleoTimerLocalServiceWrapper(
		KaleoTimerLocalService kaleoTimerLocalService) {
		_kaleoTimerLocalService = kaleoTimerLocalService;
	}

	/**
	* Adds the kaleo timer to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTimer the kaleo timer
	* @return the kaleo timer that was added
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer addKaleoTimer(
		com.liferay.portal.workflow.kaleo.model.KaleoTimer kaleoTimer) {
		return _kaleoTimerLocalService.addKaleoTimer(kaleoTimer);
	}

	/**
	* Creates a new kaleo timer with the primary key. Does not add the kaleo timer to the database.
	*
	* @param kaleoTimerId the primary key for the new kaleo timer
	* @return the new kaleo timer
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer createKaleoTimer(
		long kaleoTimerId) {
		return _kaleoTimerLocalService.createKaleoTimer(kaleoTimerId);
	}

	/**
	* Deletes the kaleo timer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTimerId the primary key of the kaleo timer
	* @return the kaleo timer that was removed
	* @throws PortalException if a kaleo timer with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer deleteKaleoTimer(
		long kaleoTimerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoTimerLocalService.deleteKaleoTimer(kaleoTimerId);
	}

	/**
	* Deletes the kaleo timer from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTimer the kaleo timer
	* @return the kaleo timer that was removed
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer deleteKaleoTimer(
		com.liferay.portal.workflow.kaleo.model.KaleoTimer kaleoTimer) {
		return _kaleoTimerLocalService.deleteKaleoTimer(kaleoTimer);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _kaleoTimerLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _kaleoTimerLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _kaleoTimerLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return _kaleoTimerLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _kaleoTimerLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _kaleoTimerLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer fetchKaleoTimer(
		long kaleoTimerId) {
		return _kaleoTimerLocalService.fetchKaleoTimer(kaleoTimerId);
	}

	/**
	* Returns the kaleo timer with the primary key.
	*
	* @param kaleoTimerId the primary key of the kaleo timer
	* @return the kaleo timer
	* @throws PortalException if a kaleo timer with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer getKaleoTimer(
		long kaleoTimerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoTimerLocalService.getKaleoTimer(kaleoTimerId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _kaleoTimerLocalService.getActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoTimerLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoTimerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the kaleo timers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo timers
	* @param end the upper bound of the range of kaleo timers (not inclusive)
	* @return the range of kaleo timers
	*/
	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> getKaleoTimers(
		int start, int end) {
		return _kaleoTimerLocalService.getKaleoTimers(start, end);
	}

	/**
	* Returns the number of kaleo timers.
	*
	* @return the number of kaleo timers
	*/
	@Override
	public int getKaleoTimersCount() {
		return _kaleoTimerLocalService.getKaleoTimersCount();
	}

	/**
	* Updates the kaleo timer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoTimer the kaleo timer
	* @return the kaleo timer that was updated
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer updateKaleoTimer(
		com.liferay.portal.workflow.kaleo.model.KaleoTimer kaleoTimer) {
		return _kaleoTimerLocalService.updateKaleoTimer(kaleoTimer);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _kaleoTimerLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_kaleoTimerLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _kaleoTimerLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer addKaleoTimer(
		java.lang.String kaleoClassName, long kaleoClassPK,
		long kaleoDefinitionId,
		com.liferay.portal.workflow.kaleo.definition.Timer timer,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoTimerLocalService.addKaleoTimer(kaleoClassName,
			kaleoClassPK, kaleoDefinitionId, timer, serviceContext);
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> getKaleoTimers(
		java.lang.String kaleoClassName, long kaleoClassPK) {
		return _kaleoTimerLocalService.getKaleoTimers(kaleoClassName,
			kaleoClassPK);
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> getKaleoTimers(
		java.lang.String kaleoClassName, long kaleoClassPK, boolean blocking) {
		return _kaleoTimerLocalService.getKaleoTimers(kaleoClassName,
			kaleoClassPK, blocking);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public KaleoTimerLocalService getWrappedKaleoTimerLocalService() {
		return _kaleoTimerLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedKaleoTimerLocalService(
		KaleoTimerLocalService kaleoTimerLocalService) {
		_kaleoTimerLocalService = kaleoTimerLocalService;
	}

	@Override
	public KaleoTimerLocalService getWrappedService() {
		return _kaleoTimerLocalService;
	}

	@Override
	public void setWrappedService(KaleoTimerLocalService kaleoTimerLocalService) {
		_kaleoTimerLocalService = kaleoTimerLocalService;
	}

	private KaleoTimerLocalService _kaleoTimerLocalService;
}