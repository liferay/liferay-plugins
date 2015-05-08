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

package com.liferay.testpacl.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FooLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see FooLocalService
 * @generated
 */
@ProviderType
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
	*/
	@Override
	public com.liferay.testpacl.model.Foo addFoo(
		com.liferay.testpacl.model.Foo foo) {
		return _fooLocalService.addFoo(foo);
	}

	/**
	* Creates a new foo with the primary key. Does not add the foo to the database.
	*
	* @param fooId the primary key for the new foo
	* @return the new foo
	*/
	@Override
	public com.liferay.testpacl.model.Foo createFoo(long fooId) {
		return _fooLocalService.createFoo(fooId);
	}

	/**
	* Deletes the foo from the database. Also notifies the appropriate model listeners.
	*
	* @param foo the foo
	* @return the foo that was removed
	*/
	@Override
	public com.liferay.testpacl.model.Foo deleteFoo(
		com.liferay.testpacl.model.Foo foo) {
		return _fooLocalService.deleteFoo(foo);
	}

	/**
	* Deletes the foo with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fooId the primary key of the foo
	* @return the foo that was removed
	* @throws PortalException if a foo with the primary key could not be found
	*/
	@Override
	public com.liferay.testpacl.model.Foo deleteFoo(long fooId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fooLocalService.deleteFoo(fooId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fooLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _fooLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _fooLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testpacl.model.impl.FooModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _fooLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testpacl.model.impl.FooModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _fooLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _fooLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _fooLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.testpacl.model.Foo fetchFoo(long fooId) {
		return _fooLocalService.fetchFoo(fooId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _fooLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _fooLocalService.getBeanIdentifier();
	}

	@Override
	public com.liferay.portal.model.Company getCompanyPersistence_FindByPrimaryKey(
		long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fooLocalService.getCompanyPersistence_FindByPrimaryKey(companyId);
	}

	@Override
	public com.liferay.portal.model.Company getCompanyUtil_FindByPrimaryKey(
		long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fooLocalService.getCompanyUtil_FindByPrimaryKey(companyId);
	}

	@Override
	public java.util.List<com.liferay.chat.model.Entry> getEntryLocalServiceUtil_GetEntries(
		int start, int end) {
		return _fooLocalService.getEntryLocalServiceUtil_GetEntries(start, end);
	}

	@Override
	public com.liferay.chat.model.Entry getEntryLocalServiceUtil_GetEntry(
		long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fooLocalService.getEntryLocalServiceUtil_GetEntry(entryId);
	}

	/**
	* Returns the foo with the primary key.
	*
	* @param fooId the primary key of the foo
	* @return the foo
	* @throws PortalException if a foo with the primary key could not be found
	*/
	@Override
	public com.liferay.testpacl.model.Foo getFoo(long fooId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fooLocalService.getFoo(fooId);
	}

	/**
	* Returns a range of all the foos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testpacl.model.impl.FooModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of foos
	* @param end the upper bound of the range of foos (not inclusive)
	* @return the range of foos
	*/
	@Override
	public java.util.List<com.liferay.testpacl.model.Foo> getFoos(int start,
		int end) {
		return _fooLocalService.getFoos(start, end);
	}

	/**
	* Returns the number of foos.
	*
	* @return the number of foos
	*/
	@Override
	public int getFoosCount() {
		return _fooLocalService.getFoosCount();
	}

	@Override
	public com.liferay.portal.model.Group getGroupPersistence_FindByPrimaryKey(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fooLocalService.getGroupPersistence_FindByPrimaryKey(groupId);
	}

	@Override
	public com.liferay.portal.model.Group getGroupUtil_FindByPrimaryKey(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fooLocalService.getGroupUtil_FindByPrimaryKey(groupId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fooLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public int getPortalServiceUtil_GetBuildNumber() {
		return _fooLocalService.getPortalServiceUtil_GetBuildNumber();
	}

	@Override
	public int getPortalServiceUtil_TestGetBuildNumber() {
		return _fooLocalService.getPortalServiceUtil_TestGetBuildNumber();
	}

	@Override
	public boolean getPortalServiceUtil_TestHasClassName() {
		return _fooLocalService.getPortalServiceUtil_TestHasClassName();
	}

	@Override
	public int getPortalService_GetBuildNumber() {
		return _fooLocalService.getPortalService_GetBuildNumber();
	}

	@Override
	public int getPortalService_TestGetBuildNumber() {
		return _fooLocalService.getPortalService_TestGetBuildNumber();
	}

	@Override
	public boolean getPortalService_TestHasClassName() {
		return _fooLocalService.getPortalService_TestHasClassName();
	}

	@Override
	public int getReleaseInfo_GetBuildNumber() {
		return _fooLocalService.getReleaseInfo_GetBuildNumber();
	}

	@Override
	public com.liferay.chat.model.Status getStatusLocalServiceUtil_GetStatus(
		long statusId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fooLocalService.getStatusLocalServiceUtil_GetStatus(statusId);
	}

	@Override
	public java.util.List<com.liferay.chat.model.Status> getStatusLocalServiceUtil_GetStatuses(
		int start, int end) {
		return _fooLocalService.getStatusLocalServiceUtil_GetStatuses(start, end);
	}

	@Override
	public com.liferay.portal.model.User getUserPersistence_FindByPrimaryKey(
		long userId) throws com.liferay.portal.kernel.exception.PortalException {
		return _fooLocalService.getUserPersistence_FindByPrimaryKey(userId);
	}

	@Override
	public com.liferay.portal.model.User getUserUtil_FindByPrimaryKey(
		long userId) throws com.liferay.portal.kernel.exception.PortalException {
		return _fooLocalService.getUserUtil_FindByPrimaryKey(userId);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _fooLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_fooLocalService.setBeanIdentifier(beanIdentifier);
	}

	/**
	* Updates the foo in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param foo the foo
	* @return the foo that was updated
	*/
	@Override
	public com.liferay.testpacl.model.Foo updateFoo(
		com.liferay.testpacl.model.Foo foo) {
		return _fooLocalService.updateFoo(foo);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public FooLocalService getWrappedFooLocalService() {
		return _fooLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedFooLocalService(FooLocalService fooLocalService) {
		_fooLocalService = fooLocalService;
	}

	@Override
	public FooLocalService getWrappedService() {
		return _fooLocalService;
	}

	@Override
	public void setWrappedService(FooLocalService fooLocalService) {
		_fooLocalService = fooLocalService;
	}

	private FooLocalService _fooLocalService;
}