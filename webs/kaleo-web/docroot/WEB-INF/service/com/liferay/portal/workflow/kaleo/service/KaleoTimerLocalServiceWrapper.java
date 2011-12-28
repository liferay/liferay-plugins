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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link KaleoTimerLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTimerLocalService
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
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer addKaleoTimer(
		com.liferay.portal.workflow.kaleo.model.KaleoTimer kaleoTimer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTimerLocalService.addKaleoTimer(kaleoTimer);
	}

	/**
	* Creates a new kaleo timer with the primary key. Does not add the kaleo timer to the database.
	*
	* @param kaleoTimerId the primary key for the new kaleo timer
	* @return the new kaleo timer
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer createKaleoTimer(
		long kaleoTimerId) {
		return _kaleoTimerLocalService.createKaleoTimer(kaleoTimerId);
	}

	/**
	* Deletes the kaleo timer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTimerId the primary key of the kaleo timer
	* @throws PortalException if a kaleo timer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKaleoTimer(long kaleoTimerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoTimerLocalService.deleteKaleoTimer(kaleoTimerId);
	}

	/**
	* Deletes the kaleo timer from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTimer the kaleo timer
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKaleoTimer(
		com.liferay.portal.workflow.kaleo.model.KaleoTimer kaleoTimer)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTimerLocalService.deleteKaleoTimer(kaleoTimer);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTimerLocalService.dynamicQuery(dynamicQuery);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTimerLocalService.dynamicQuery(dynamicQuery, start, end);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTimerLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTimerLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTimer fetchKaleoTimer(
		long kaleoTimerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTimerLocalService.fetchKaleoTimer(kaleoTimerId);
	}

	/**
	* Returns the kaleo timer with the primary key.
	*
	* @param kaleoTimerId the primary key of the kaleo timer
	* @return the kaleo timer
	* @throws PortalException if a kaleo timer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer getKaleoTimer(
		long kaleoTimerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTimerLocalService.getKaleoTimer(kaleoTimerId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTimerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the kaleo timers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo timers
	* @param end the upper bound of the range of kaleo timers (not inclusive)
	* @return the range of kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> getKaleoTimers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTimerLocalService.getKaleoTimers(start, end);
	}

	/**
	* Returns the number of kaleo timers.
	*
	* @return the number of kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public int getKaleoTimersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTimerLocalService.getKaleoTimersCount();
	}

	/**
	* Updates the kaleo timer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoTimer the kaleo timer
	* @return the kaleo timer that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer updateKaleoTimer(
		com.liferay.portal.workflow.kaleo.model.KaleoTimer kaleoTimer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTimerLocalService.updateKaleoTimer(kaleoTimer);
	}

	/**
	* Updates the kaleo timer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoTimer the kaleo timer
	* @param merge whether to merge the kaleo timer with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the kaleo timer that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer updateKaleoTimer(
		com.liferay.portal.workflow.kaleo.model.KaleoTimer kaleoTimer,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTimerLocalService.updateKaleoTimer(kaleoTimer, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _kaleoTimerLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_kaleoTimerLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTimer addKaleoTimer(
		java.lang.String kaleoClassName, long kaleoClassPK,
		long kaleoDefinitionId,
		com.liferay.portal.workflow.kaleo.definition.Timer timer,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTimerLocalService.addKaleoTimer(kaleoClassName,
			kaleoClassPK, kaleoDefinitionId, timer, serviceContext);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> getKaleoTimers(
		java.lang.String kaleoClassName, long kaleoClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTimerLocalService.getKaleoTimers(kaleoClassName,
			kaleoClassPK);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> getKaleoTimers(
		java.lang.String kaleoClassName, long kaleoClassPK, boolean blocking)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTimerLocalService.getKaleoTimers(kaleoClassName,
			kaleoClassPK, blocking);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public KaleoTimerLocalService getWrappedKaleoTimerLocalService() {
		return _kaleoTimerLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedKaleoTimerLocalService(
		KaleoTimerLocalService kaleoTimerLocalService) {
		_kaleoTimerLocalService = kaleoTimerLocalService;
	}

	public KaleoTimerLocalService getWrappedService() {
		return _kaleoTimerLocalService;
	}

	public void setWrappedService(KaleoTimerLocalService kaleoTimerLocalService) {
		_kaleoTimerLocalService = kaleoTimerLocalService;
	}

	private KaleoTimerLocalService _kaleoTimerLocalService;
}