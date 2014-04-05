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
 * Provides the local service utility for KaleoTaskAssignment. This utility wraps
 * {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoTaskAssignmentLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskAssignmentLocalService
 * @see com.liferay.portal.workflow.kaleo.service.base.KaleoTaskAssignmentLocalServiceBaseImpl
 * @see com.liferay.portal.workflow.kaleo.service.impl.KaleoTaskAssignmentLocalServiceImpl
 * @generated
 */
public class KaleoTaskAssignmentLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoTaskAssignmentLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the kaleo task assignment to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskAssignment the kaleo task assignment
	* @return the kaleo task assignment that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment addKaleoTaskAssignment(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addKaleoTaskAssignment(kaleoTaskAssignment);
	}

	/**
	* Creates a new kaleo task assignment with the primary key. Does not add the kaleo task assignment to the database.
	*
	* @param kaleoTaskAssignmentId the primary key for the new kaleo task assignment
	* @return the new kaleo task assignment
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment createKaleoTaskAssignment(
		long kaleoTaskAssignmentId) {
		return getService().createKaleoTaskAssignment(kaleoTaskAssignmentId);
	}

	/**
	* Deletes the kaleo task assignment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskAssignmentId the primary key of the kaleo task assignment
	* @return the kaleo task assignment that was removed
	* @throws PortalException if a kaleo task assignment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment deleteKaleoTaskAssignment(
		long kaleoTaskAssignmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteKaleoTaskAssignment(kaleoTaskAssignmentId);
	}

	/**
	* Deletes the kaleo task assignment from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskAssignment the kaleo task assignment
	* @return the kaleo task assignment that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment deleteKaleoTaskAssignment(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteKaleoTaskAssignment(kaleoTaskAssignment);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment fetchKaleoTaskAssignment(
		long kaleoTaskAssignmentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchKaleoTaskAssignment(kaleoTaskAssignmentId);
	}

	/**
	* Returns the kaleo task assignment with the primary key.
	*
	* @param kaleoTaskAssignmentId the primary key of the kaleo task assignment
	* @return the kaleo task assignment
	* @throws PortalException if a kaleo task assignment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment getKaleoTaskAssignment(
		long kaleoTaskAssignmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTaskAssignment(kaleoTaskAssignmentId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the kaleo task assignments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo task assignments
	* @param end the upper bound of the range of kaleo task assignments (not inclusive)
	* @return the range of kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> getKaleoTaskAssignments(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTaskAssignments(start, end);
	}

	/**
	* Returns the number of kaleo task assignments.
	*
	* @return the number of kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static int getKaleoTaskAssignmentsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTaskAssignmentsCount();
	}

	/**
	* Updates the kaleo task assignment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskAssignment the kaleo task assignment
	* @return the kaleo task assignment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment updateKaleoTaskAssignment(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateKaleoTaskAssignment(kaleoTaskAssignment);
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

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment addKaleoTaskAssignment(
		java.lang.String kaleoClassName, long kaleoClassPK,
		long kaleoDefinitionId,
		com.liferay.portal.workflow.kaleo.definition.Assignment assignment,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKaleoTaskAssignment(kaleoClassName, kaleoClassPK,
			kaleoDefinitionId, assignment, serviceContext);
	}

	public static void deleteCompanyKaleoTaskAssignments(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCompanyKaleoTaskAssignments(companyId);
	}

	public static void deleteKaleoDefinitionKaleoTaskAssignments(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKaleoDefinitionKaleoTaskAssignments(kaleoDefinitionId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> getKaleoTaskAssignments(
		long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTaskAssignments(kaleoTaskId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> getKaleoTaskAssignments(
		long kaleoTaskId, java.lang.String assigneeClassName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoTaskAssignments(kaleoTaskId, assigneeClassName);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> getKaleoTaskAssignments(
		java.lang.String kaleoClassName, long kaleoClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTaskAssignments(kaleoClassName, kaleoClassPK);
	}

	public static int getKaleoTaskAssignmentsCount(long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTaskAssignmentsCount(kaleoTaskId);
	}

	public static int getKaleoTaskAssignmentsCount(long kaleoTaskId,
		java.lang.String assigneeClassName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoTaskAssignmentsCount(kaleoTaskId, assigneeClassName);
	}

	public static void clearService() {
		_service = null;
	}

	public static KaleoTaskAssignmentLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					KaleoTaskAssignmentLocalService.class.getName());

			if (invokableLocalService instanceof KaleoTaskAssignmentLocalService) {
				_service = (KaleoTaskAssignmentLocalService)invokableLocalService;
			}
			else {
				_service = new KaleoTaskAssignmentLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(KaleoTaskAssignmentLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(KaleoTaskAssignmentLocalService service) {
	}

	private static KaleoTaskAssignmentLocalService _service;
}