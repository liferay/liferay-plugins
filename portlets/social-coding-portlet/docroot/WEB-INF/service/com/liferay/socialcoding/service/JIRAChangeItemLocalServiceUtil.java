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

package com.liferay.socialcoding.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * The utility for the j i r a change item local service. This utility wraps {@link com.liferay.socialcoding.service.impl.JIRAChangeItemLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * Never modify this class directly. Add custom service methods to {@link com.liferay.socialcoding.service.impl.JIRAChangeItemLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAChangeItemLocalService
 * @see com.liferay.socialcoding.service.base.JIRAChangeItemLocalServiceBaseImpl
 * @see com.liferay.socialcoding.service.impl.JIRAChangeItemLocalServiceImpl
 * @generated
 */
public class JIRAChangeItemLocalServiceUtil {
	/**
	* Adds the j i r a change item to the database. Also notifies the appropriate model listeners.
	*
	* @param jiraChangeItem the j i r a change item to add
	* @return the j i r a change item that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAChangeItem addJIRAChangeItem(
		com.liferay.socialcoding.model.JIRAChangeItem jiraChangeItem)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addJIRAChangeItem(jiraChangeItem);
	}

	/**
	* Creates a new j i r a change item with the primary key. Does not add the j i r a change item to the database.
	*
	* @param jiraChangeItemId the primary key for the new j i r a change item
	* @return the new j i r a change item
	*/
	public static com.liferay.socialcoding.model.JIRAChangeItem createJIRAChangeItem(
		long jiraChangeItemId) {
		return getService().createJIRAChangeItem(jiraChangeItemId);
	}

	/**
	* Deletes the j i r a change item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraChangeItemId the primary key of the j i r a change item to delete
	* @throws PortalException if a j i r a change item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteJIRAChangeItem(long jiraChangeItemId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteJIRAChangeItem(jiraChangeItemId);
	}

	/**
	* Deletes the j i r a change item from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraChangeItem the j i r a change item to delete
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteJIRAChangeItem(
		com.liferay.socialcoding.model.JIRAChangeItem jiraChangeItem)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteJIRAChangeItem(jiraChangeItem);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("unchecked")
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
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("unchecked")
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
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the j i r a change item with the primary key.
	*
	* @param jiraChangeItemId the primary key of the j i r a change item to get
	* @return the j i r a change item
	* @throws PortalException if a j i r a change item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAChangeItem getJIRAChangeItem(
		long jiraChangeItemId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getJIRAChangeItem(jiraChangeItemId);
	}

	/**
	* Gets a range of all the j i r a change items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of j i r a change items to return
	* @param end the upper bound of the range of j i r a change items to return (not inclusive)
	* @return the range of j i r a change items
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> getJIRAChangeItems(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getJIRAChangeItems(start, end);
	}

	/**
	* Gets the number of j i r a change items.
	*
	* @return the number of j i r a change items
	* @throws SystemException if a system exception occurred
	*/
	public static int getJIRAChangeItemsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getJIRAChangeItemsCount();
	}

	/**
	* Updates the j i r a change item in the database. Also notifies the appropriate model listeners.
	*
	* @param jiraChangeItem the j i r a change item to update
	* @return the j i r a change item that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAChangeItem updateJIRAChangeItem(
		com.liferay.socialcoding.model.JIRAChangeItem jiraChangeItem)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateJIRAChangeItem(jiraChangeItem);
	}

	/**
	* Updates the j i r a change item in the database. Also notifies the appropriate model listeners.
	*
	* @param jiraChangeItem the j i r a change item to update
	* @param merge whether to merge the j i r a change item with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the j i r a change item that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAChangeItem updateJIRAChangeItem(
		com.liferay.socialcoding.model.JIRAChangeItem jiraChangeItem,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateJIRAChangeItem(jiraChangeItem, merge);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> getJIRAChangeItems(
		long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getJIRAChangeItems(jiraChangeGroupId);
	}

	public static void clearService() {
		_service = null;
	}

	public static JIRAChangeItemLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					JIRAChangeItemLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					JIRAChangeItemLocalService.class.getName(),
					portletClassLoader);

			_service = new JIRAChangeItemLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(JIRAChangeItemLocalService service) {
		_service = service;
	}

	private static JIRAChangeItemLocalService _service;
}