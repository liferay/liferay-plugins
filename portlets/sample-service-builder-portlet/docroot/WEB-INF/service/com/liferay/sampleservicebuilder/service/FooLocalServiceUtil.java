/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.sampleservicebuilder.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for Foo. This utility wraps
 * {@link com.liferay.sampleservicebuilder.service.impl.FooLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see FooLocalService
 * @see com.liferay.sampleservicebuilder.service.base.FooLocalServiceBaseImpl
 * @see com.liferay.sampleservicebuilder.service.impl.FooLocalServiceImpl
 * @generated
 */
public class FooLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.sampleservicebuilder.service.impl.FooLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the foo to the database. Also notifies the appropriate model listeners.
	*
	* @param foo the foo
	* @return the foo that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo addFoo(
		com.liferay.sampleservicebuilder.model.Foo foo)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addFoo(foo);
	}

	/**
	* Creates a new foo with the primary key. Does not add the foo to the database.
	*
	* @param fooId the primary key for the new foo
	* @return the new foo
	*/
	public static com.liferay.sampleservicebuilder.model.Foo createFoo(
		long fooId) {
		return getService().createFoo(fooId);
	}

	/**
	* Deletes the foo with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fooId the primary key of the foo
	* @return the foo that was removed
	* @throws PortalException if a foo with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo deleteFoo(
		long fooId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteFoo(fooId);
	}

	/**
	* Deletes the foo from the database. Also notifies the appropriate model listeners.
	*
	* @param foo the foo
	* @return the foo that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo deleteFoo(
		com.liferay.sampleservicebuilder.model.Foo foo)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteFoo(foo);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sampleservicebuilder.model.impl.FooModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sampleservicebuilder.model.impl.FooModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.sampleservicebuilder.model.Foo fetchFoo(
		long fooId) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchFoo(fooId);
	}

	/**
	* Returns the foo with the matching UUID and company.
	*
	* @param uuid the foo's UUID
	* @param companyId the primary key of the company
	* @return the matching foo, or <code>null</code> if a matching foo could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo fetchFooByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchFooByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the foo matching the UUID and group.
	*
	* @param uuid the foo's UUID
	* @param groupId the primary key of the group
	* @return the matching foo, or <code>null</code> if a matching foo could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo fetchFooByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchFooByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the foo with the primary key.
	*
	* @param fooId the primary key of the foo
	* @return the foo
	* @throws PortalException if a foo with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo getFoo(long fooId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getFoo(fooId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the foo with the matching UUID and company.
	*
	* @param uuid the foo's UUID
	* @param companyId the primary key of the company
	* @return the matching foo
	* @throws PortalException if a matching foo could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo getFooByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getFooByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the foo matching the UUID and group.
	*
	* @param uuid the foo's UUID
	* @param groupId the primary key of the group
	* @return the matching foo
	* @throws PortalException if a matching foo could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo getFooByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getFooByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the foos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sampleservicebuilder.model.impl.FooModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of foos
	* @param end the upper bound of the range of foos (not inclusive)
	* @return the range of foos
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> getFoos(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFoos(start, end);
	}

	/**
	* Returns the number of foos.
	*
	* @return the number of foos
	* @throws SystemException if a system exception occurred
	*/
	public static int getFoosCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFoosCount();
	}

	/**
	* Updates the foo in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param foo the foo
	* @return the foo that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo updateFoo(
		com.liferay.sampleservicebuilder.model.Foo foo)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateFoo(foo);
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

	public static void addFoo(java.lang.String field1, boolean field2,
		int field3, java.util.Date field4, java.lang.String field5,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addFoo(field1, field2, field3, field4, field5, serviceContext);
	}

	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> getFoos(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFoos(start, end, obc);
	}

	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> getFoos(
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFoos(obc);
	}

	public static java.lang.Object getLocalObject() throws java.lang.Exception {
		return getService().getLocalObject();
	}

	public static void updateAsset(long userId,
		com.liferay.sampleservicebuilder.model.Foo foo,
		long[] assetCategoryIds, java.lang.String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().updateAsset(userId, foo, assetCategoryIds, assetTagNames);
	}

	public static void updateFoo(long fooId, java.lang.String field1,
		boolean field2, int field3, java.util.Date field4,
		java.lang.String field5,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.updateFoo(fooId, field1, field2, field3, field4, field5,
			serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static FooLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					FooLocalService.class.getName());

			if (invokableLocalService instanceof FooLocalService) {
				_service = (FooLocalService)invokableLocalService;
			}
			else {
				_service = new FooLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(FooLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(FooLocalService service) {
	}

	private static FooLocalService _service;
}