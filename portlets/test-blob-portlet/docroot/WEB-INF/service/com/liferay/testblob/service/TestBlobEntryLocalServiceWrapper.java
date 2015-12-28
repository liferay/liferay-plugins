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

package com.liferay.testblob.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TestBlobEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TestBlobEntryLocalService
 * @generated
 */
@ProviderType
public class TestBlobEntryLocalServiceWrapper
	implements TestBlobEntryLocalService,
		ServiceWrapper<TestBlobEntryLocalService> {
	public TestBlobEntryLocalServiceWrapper(
		TestBlobEntryLocalService testBlobEntryLocalService) {
		_testBlobEntryLocalService = testBlobEntryLocalService;
	}

	/**
	* Adds the test blob entry to the database. Also notifies the appropriate model listeners.
	*
	* @param testBlobEntry the test blob entry
	* @return the test blob entry that was added
	*/
	@Override
	public com.liferay.testblob.model.TestBlobEntry addTestBlobEntry(
		com.liferay.testblob.model.TestBlobEntry testBlobEntry) {
		return _testBlobEntryLocalService.addTestBlobEntry(testBlobEntry);
	}

	/**
	* Creates a new test blob entry with the primary key. Does not add the test blob entry to the database.
	*
	* @param testBlobEntryId the primary key for the new test blob entry
	* @return the new test blob entry
	*/
	@Override
	public com.liferay.testblob.model.TestBlobEntry createTestBlobEntry(
		long testBlobEntryId) {
		return _testBlobEntryLocalService.createTestBlobEntry(testBlobEntryId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testBlobEntryLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the test blob entry from the database. Also notifies the appropriate model listeners.
	*
	* @param testBlobEntry the test blob entry
	* @return the test blob entry that was removed
	*/
	@Override
	public com.liferay.testblob.model.TestBlobEntry deleteTestBlobEntry(
		com.liferay.testblob.model.TestBlobEntry testBlobEntry) {
		return _testBlobEntryLocalService.deleteTestBlobEntry(testBlobEntry);
	}

	/**
	* Deletes the test blob entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testBlobEntryId the primary key of the test blob entry
	* @return the test blob entry that was removed
	* @throws PortalException if a test blob entry with the primary key could not be found
	*/
	@Override
	public com.liferay.testblob.model.TestBlobEntry deleteTestBlobEntry(
		long testBlobEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testBlobEntryLocalService.deleteTestBlobEntry(testBlobEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _testBlobEntryLocalService.dynamicQuery();
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
		return _testBlobEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testblob.model.impl.TestBlobEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _testBlobEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testblob.model.impl.TestBlobEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _testBlobEntryLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _testBlobEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _testBlobEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.testblob.model.TestBlobEntry fetchTestBlobEntry(
		long testBlobEntryId) {
		return _testBlobEntryLocalService.fetchTestBlobEntry(testBlobEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _testBlobEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.testblob.model.TestBlobEntryBlobFieldBlobModel getBlobFieldBlobModel(
		java.io.Serializable primaryKey) {
		return _testBlobEntryLocalService.getBlobFieldBlobModel(primaryKey);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _testBlobEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _testBlobEntryLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testBlobEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the test blob entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testblob.model.impl.TestBlobEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of test blob entries
	* @param end the upper bound of the range of test blob entries (not inclusive)
	* @return the range of test blob entries
	*/
	@Override
	public java.util.List<com.liferay.testblob.model.TestBlobEntry> getTestBlobEntries(
		int start, int end) {
		return _testBlobEntryLocalService.getTestBlobEntries(start, end);
	}

	/**
	* Returns the number of test blob entries.
	*
	* @return the number of test blob entries
	*/
	@Override
	public int getTestBlobEntriesCount() {
		return _testBlobEntryLocalService.getTestBlobEntriesCount();
	}

	/**
	* Returns the test blob entry with the primary key.
	*
	* @param testBlobEntryId the primary key of the test blob entry
	* @return the test blob entry
	* @throws PortalException if a test blob entry with the primary key could not be found
	*/
	@Override
	public com.liferay.testblob.model.TestBlobEntry getTestBlobEntry(
		long testBlobEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testBlobEntryLocalService.getTestBlobEntry(testBlobEntryId);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _testBlobEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Updates the test blob entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param testBlobEntry the test blob entry
	* @return the test blob entry that was updated
	*/
	@Override
	public com.liferay.testblob.model.TestBlobEntry updateTestBlobEntry(
		com.liferay.testblob.model.TestBlobEntry testBlobEntry) {
		return _testBlobEntryLocalService.updateTestBlobEntry(testBlobEntry);
	}

	@Override
	public TestBlobEntryLocalService getWrappedService() {
		return _testBlobEntryLocalService;
	}

	@Override
	public void setWrappedService(
		TestBlobEntryLocalService testBlobEntryLocalService) {
		_testBlobEntryLocalService = testBlobEntryLocalService;
	}

	private TestBlobEntryLocalService _testBlobEntryLocalService;
}