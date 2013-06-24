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

package com.liferay.testpacl.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link FooLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FooLocalService
 * @generated
 */
public class FooLocalServiceWrapper implements FooLocalService,
	ServiceWrapper<FooLocalService> {
	public FooLocalServiceWrapper(FooLocalService fooLocalService) {
		_fooLocalService = fooLocalService;
	}

	/**
	* Adds the foo to the database. Also notifies the appropriate model listeners.
	*
	* @param foo the foo
	* @return the foo that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testpacl.model.Foo addFoo(
		com.liferay.testpacl.model.Foo foo)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.addFoo(foo);
	}

	/**
	* Creates a new foo with the primary key. Does not add the foo to the database.
	*
	* @param fooId the primary key for the new foo
	* @return the new foo
	*/
	public com.liferay.testpacl.model.Foo createFoo(long fooId) {
		return _fooLocalService.createFoo(fooId);
	}

	/**
	* Deletes the foo with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fooId the primary key of the foo
	* @return the foo that was removed
	* @throws PortalException if a foo with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testpacl.model.Foo deleteFoo(long fooId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.deleteFoo(fooId);
	}

	/**
	* Deletes the foo from the database. Also notifies the appropriate model listeners.
	*
	* @param foo the foo
	* @return the foo that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testpacl.model.Foo deleteFoo(
		com.liferay.testpacl.model.Foo foo)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.deleteFoo(foo);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _fooLocalService.dynamicQuery();
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
		return _fooLocalService.dynamicQuery(dynamicQuery);
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
		return _fooLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _fooLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _fooLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.testpacl.model.Foo fetchFoo(long fooId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.fetchFoo(fooId);
	}

	/**
	* Returns the foo with the primary key.
	*
	* @param fooId the primary key of the foo
	* @return the foo
	* @throws PortalException if a foo with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testpacl.model.Foo getFoo(long fooId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.getFoo(fooId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the foos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of foos
	* @param end the upper bound of the range of foos (not inclusive)
	* @return the range of foos
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.testpacl.model.Foo> getFoos(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.getFoos(start, end);
	}

	/**
	* Returns the number of foos.
	*
	* @return the number of foos
	* @throws SystemException if a system exception occurred
	*/
	public int getFoosCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.getFoosCount();
	}

	/**
	* Updates the foo in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param foo the foo
	* @return the foo that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testpacl.model.Foo updateFoo(
		com.liferay.testpacl.model.Foo foo)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.updateFoo(foo);
	}

	/**
	* Updates the foo in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param foo the foo
	* @param merge whether to merge the foo with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the foo that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testpacl.model.Foo updateFoo(
		com.liferay.testpacl.model.Foo foo, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.updateFoo(foo, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _fooLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_fooLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _fooLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.liferay.portal.model.Company getCompanyPersistence_FindByPrimaryKey(
		long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.getCompanyPersistence_FindByPrimaryKey(companyId);
	}

	public com.liferay.portal.model.Company getCompanyUtil_FindByPrimaryKey(
		long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.getCompanyUtil_FindByPrimaryKey(companyId);
	}

	public java.util.List<com.liferay.chat.model.Entry> getEntryLocalServiceUtil_GetEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.getEntryLocalServiceUtil_GetEntries(start, end);
	}

	public com.liferay.chat.model.Entry getEntryLocalServiceUtil_GetEntry(
		long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.getEntryLocalServiceUtil_GetEntry(entryId);
	}

	public com.liferay.portal.model.Group getGroupPersistence_FindByPrimaryKey(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.getGroupPersistence_FindByPrimaryKey(groupId);
	}

	public com.liferay.portal.model.Group getGroupUtil_FindByPrimaryKey(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.getGroupUtil_FindByPrimaryKey(groupId);
	}

	public int getPortalService_GetBuildNumber() {
		return _fooLocalService.getPortalService_GetBuildNumber();
	}

	public int getPortalService_TestGetBuildNumber() {
		return _fooLocalService.getPortalService_TestGetBuildNumber();
	}

	public boolean getPortalService_TestHasClassName()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.getPortalService_TestHasClassName();
	}

	public int getPortalServiceUtil_GetBuildNumber() {
		return _fooLocalService.getPortalServiceUtil_GetBuildNumber();
	}

	public int getPortalServiceUtil_TestGetBuildNumber() {
		return _fooLocalService.getPortalServiceUtil_TestGetBuildNumber();
	}

	public boolean getPortalServiceUtil_TestHasClassName()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.getPortalServiceUtil_TestHasClassName();
	}

	public int getReleaseInfo_GetBuildNumber() {
		return _fooLocalService.getReleaseInfo_GetBuildNumber();
	}

	public com.liferay.chat.model.Status getStatusLocalServiceUtil_GetStatus(
		long statusId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.getStatusLocalServiceUtil_GetStatus(statusId);
	}

	public java.util.List<com.liferay.chat.model.Status> getStatusLocalServiceUtil_GetStatuses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.getStatusLocalServiceUtil_GetStatuses(start, end);
	}

	public com.liferay.portal.model.User getUserPersistence_FindByPrimaryKey(
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.getUserPersistence_FindByPrimaryKey(userId);
	}

	public com.liferay.portal.model.User getUserUtil_FindByPrimaryKey(
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.getUserUtil_FindByPrimaryKey(userId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public FooLocalService getWrappedFooLocalService() {
		return _fooLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedFooLocalService(FooLocalService fooLocalService) {
		_fooLocalService = fooLocalService;
	}

	public FooLocalService getWrappedService() {
		return _fooLocalService;
	}

	public void setWrappedService(FooLocalService fooLocalService) {
		_fooLocalService = fooLocalService;
	}

	private FooLocalService _fooLocalService;
}