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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the kaleo transition local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTransitionLocalServiceUtil
 * @see com.liferay.portal.workflow.kaleo.service.base.KaleoTransitionLocalServiceBaseImpl
 * @see com.liferay.portal.workflow.kaleo.service.impl.KaleoTransitionLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface KaleoTransitionLocalService extends PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KaleoTransitionLocalServiceUtil} to access the kaleo transition local service. Add custom service methods to {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoTransitionLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the kaleo transition to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTransition the kaleo transition
	* @return the kaleo transition that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition addKaleoTransition(
		com.liferay.portal.workflow.kaleo.model.KaleoTransition kaleoTransition)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new kaleo transition with the primary key. Does not add the kaleo transition to the database.
	*
	* @param kaleoTransitionId the primary key for the new kaleo transition
	* @return the new kaleo transition
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition createKaleoTransition(
		long kaleoTransitionId);

	/**
	* Deletes the kaleo transition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTransitionId the primary key of the kaleo transition
	* @throws PortalException if a kaleo transition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKaleoTransition(long kaleoTransitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the kaleo transition from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTransition the kaleo transition
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKaleoTransition(
		com.liferay.portal.workflow.kaleo.model.KaleoTransition kaleoTransition)
		throws com.liferay.portal.kernel.exception.SystemException;

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
		throws com.liferay.portal.kernel.exception.SystemException;

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
		int end) throws com.liferay.portal.kernel.exception.SystemException;

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
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchKaleoTransition(
		long kaleoTransitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo transition with the primary key.
	*
	* @param kaleoTransitionId the primary key of the kaleo transition
	* @return the kaleo transition
	* @throws PortalException if a kaleo transition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition getKaleoTransition(
		long kaleoTransitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo transitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo transitions
	* @param end the upper bound of the range of kaleo transitions (not inclusive)
	* @return the range of kaleo transitions
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> getKaleoTransitions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo transitions.
	*
	* @return the number of kaleo transitions
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKaleoTransitionsCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the kaleo transition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoTransition the kaleo transition
	* @return the kaleo transition that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition updateKaleoTransition(
		com.liferay.portal.workflow.kaleo.model.KaleoTransition kaleoTransition)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the kaleo transition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoTransition the kaleo transition
	* @param merge whether to merge the kaleo transition with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the kaleo transition that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition updateKaleoTransition(
		com.liferay.portal.workflow.kaleo.model.KaleoTransition kaleoTransition,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	public com.liferay.portal.workflow.kaleo.model.KaleoTransition addKaleoTransition(
		long kaleoDefinitionId, long kaleoNodeId,
		com.liferay.portal.workflow.kaleo.definition.Transition transition,
		com.liferay.portal.workflow.kaleo.model.KaleoNode sourceKaleoNode,
		com.liferay.portal.workflow.kaleo.model.KaleoNode targetKaleoNode,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void deleteCompanyKaleoTransitions(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void deleteKaleoDefinitionKaleoTransitions(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition getDefaultKaleoTransition(
		long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition getKaleoTransition(
		long kaleoNodeId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> getKaleoTransitions(
		long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKaleoTransitionsCount(long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException;
}