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

package com.liferay.testtransaction.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for Bar. This utility wraps
 * {@link com.liferay.testtransaction.service.impl.BarLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see BarLocalService
 * @see com.liferay.testtransaction.service.base.BarLocalServiceBaseImpl
 * @see com.liferay.testtransaction.service.impl.BarLocalServiceImpl
 * @generated
 */
@ProviderType
public class BarLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.testtransaction.service.impl.BarLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the bar to the database. Also notifies the appropriate model listeners.
	*
	* @param bar the bar
	* @return the bar that was added
	*/
	public static com.liferay.testtransaction.model.Bar addBar(
		com.liferay.testtransaction.model.Bar bar) {
		return getService().addBar(bar);
	}

	public static void addBarAndClassName_PortalRollback(java.lang.String text) {
		getService().addBarAndClassName_PortalRollback(text);
	}

	public static void addBarAndClassName_PortletRollback(java.lang.String text) {
		getService().addBarAndClassName_PortletRollback(text);
	}

	public static void addBar_Rollback(java.lang.String text) {
		getService().addBar_Rollback(text);
	}

	public static com.liferay.testtransaction.model.Bar addBar_Success(
		java.lang.String text) {
		return getService().addBar_Success(text);
	}

	/**
	* Creates a new bar with the primary key. Does not add the bar to the database.
	*
	* @param barId the primary key for the new bar
	* @return the new bar
	*/
	public static com.liferay.testtransaction.model.Bar createBar(long barId) {
		return getService().createBar(barId);
	}

	/**
	* Deletes the bar from the database. Also notifies the appropriate model listeners.
	*
	* @param bar the bar
	* @return the bar that was removed
	*/
	public static com.liferay.testtransaction.model.Bar deleteBar(
		com.liferay.testtransaction.model.Bar bar) {
		return getService().deleteBar(bar);
	}

	/**
	* Deletes the bar with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param barId the primary key of the bar
	* @return the bar that was removed
	* @throws PortalException if a bar with the primary key could not be found
	*/
	public static com.liferay.testtransaction.model.Bar deleteBar(long barId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteBar(barId);
	}

	public static void deleteBarAndClassName(
		com.liferay.testtransaction.model.Bar bar)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteBarAndClassName(bar);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testtransaction.model.impl.BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testtransaction.model.impl.BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.testtransaction.model.Bar fetchBar(long barId) {
		return getService().fetchBar(barId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the bar with the primary key.
	*
	* @param barId the primary key of the bar
	* @return the bar
	* @throws PortalException if a bar with the primary key could not be found
	*/
	public static com.liferay.testtransaction.model.Bar getBar(long barId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getBar(barId);
	}

	public static com.liferay.testtransaction.model.Bar getBar(
		java.lang.String text)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getBar(text);
	}

	/**
	* Returns a range of all the bars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testtransaction.model.impl.BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @return the range of bars
	*/
	public static java.util.List<com.liferay.testtransaction.model.Bar> getBars(
		int start, int end) {
		return getService().getBars(start, end);
	}

	/**
	* Returns the number of bars.
	*
	* @return the number of bars
	*/
	public static int getBarsCount() {
		return getService().getBarsCount();
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static boolean hasBar(java.lang.String text) {
		return getService().hasBar(text);
	}

	public static boolean hasClassName() {
		return getService().hasClassName();
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static void testAddClassNameAndBar_Success(java.lang.String text) {
		getService().testAddClassNameAndBar_Success(text);
	}

	/**
	* Updates the bar in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param bar the bar
	* @return the bar that was updated
	*/
	public static com.liferay.testtransaction.model.Bar updateBar(
		com.liferay.testtransaction.model.Bar bar) {
		return getService().updateBar(bar);
	}

	public static void clearService() {
		_service = null;
	}

	public static BarLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					BarLocalService.class.getName());

			if (invokableLocalService instanceof BarLocalService) {
				_service = (BarLocalService)invokableLocalService;
			}
			else {
				_service = new BarLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(BarLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(BarLocalService service) {
	}

	private static BarLocalService _service;
}