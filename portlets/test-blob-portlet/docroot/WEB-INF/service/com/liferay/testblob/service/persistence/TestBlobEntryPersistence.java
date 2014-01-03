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

package com.liferay.testblob.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.testblob.model.TestBlobEntry;

/**
 * The persistence interface for the test blob entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TestBlobEntryPersistenceImpl
 * @see TestBlobEntryUtil
 * @generated
 */
public interface TestBlobEntryPersistence extends BasePersistence<TestBlobEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestBlobEntryUtil} to access the test blob entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the test blob entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching test blob entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.testblob.model.TestBlobEntry> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the test blob entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testblob.model.impl.TestBlobEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of test blob entries
	* @param end the upper bound of the range of test blob entries (not inclusive)
	* @return the range of matching test blob entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.testblob.model.TestBlobEntry> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the test blob entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testblob.model.impl.TestBlobEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of test blob entries
	* @param end the upper bound of the range of test blob entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching test blob entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.testblob.model.TestBlobEntry> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first test blob entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching test blob entry
	* @throws com.liferay.testblob.NoSuchEntryException if a matching test blob entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testblob.model.TestBlobEntry findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testblob.NoSuchEntryException;

	/**
	* Returns the first test blob entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching test blob entry, or <code>null</code> if a matching test blob entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testblob.model.TestBlobEntry fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last test blob entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching test blob entry
	* @throws com.liferay.testblob.NoSuchEntryException if a matching test blob entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testblob.model.TestBlobEntry findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testblob.NoSuchEntryException;

	/**
	* Returns the last test blob entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching test blob entry, or <code>null</code> if a matching test blob entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testblob.model.TestBlobEntry fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the test blob entries before and after the current test blob entry in the ordered set where uuid = &#63;.
	*
	* @param testBlobEntryId the primary key of the current test blob entry
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next test blob entry
	* @throws com.liferay.testblob.NoSuchEntryException if a test blob entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testblob.model.TestBlobEntry[] findByUuid_PrevAndNext(
		long testBlobEntryId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testblob.NoSuchEntryException;

	/**
	* Removes all the test blob entries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of test blob entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching test blob entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the test blob entry in the entity cache if it is enabled.
	*
	* @param testBlobEntry the test blob entry
	*/
	public void cacheResult(
		com.liferay.testblob.model.TestBlobEntry testBlobEntry);

	/**
	* Caches the test blob entries in the entity cache if it is enabled.
	*
	* @param testBlobEntries the test blob entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.testblob.model.TestBlobEntry> testBlobEntries);

	/**
	* Creates a new test blob entry with the primary key. Does not add the test blob entry to the database.
	*
	* @param testBlobEntryId the primary key for the new test blob entry
	* @return the new test blob entry
	*/
	public com.liferay.testblob.model.TestBlobEntry create(long testBlobEntryId);

	/**
	* Removes the test blob entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testBlobEntryId the primary key of the test blob entry
	* @return the test blob entry that was removed
	* @throws com.liferay.testblob.NoSuchEntryException if a test blob entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testblob.model.TestBlobEntry remove(long testBlobEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testblob.NoSuchEntryException;

	public com.liferay.testblob.model.TestBlobEntry updateImpl(
		com.liferay.testblob.model.TestBlobEntry testBlobEntry)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the test blob entry with the primary key or throws a {@link com.liferay.testblob.NoSuchEntryException} if it could not be found.
	*
	* @param testBlobEntryId the primary key of the test blob entry
	* @return the test blob entry
	* @throws com.liferay.testblob.NoSuchEntryException if a test blob entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testblob.model.TestBlobEntry findByPrimaryKey(
		long testBlobEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testblob.NoSuchEntryException;

	/**
	* Returns the test blob entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param testBlobEntryId the primary key of the test blob entry
	* @return the test blob entry, or <code>null</code> if a test blob entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testblob.model.TestBlobEntry fetchByPrimaryKey(
		long testBlobEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the test blob entries.
	*
	* @return the test blob entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.testblob.model.TestBlobEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.testblob.model.TestBlobEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the test blob entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testblob.model.impl.TestBlobEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of test blob entries
	* @param end the upper bound of the range of test blob entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of test blob entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.testblob.model.TestBlobEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the test blob entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of test blob entries.
	*
	* @return the number of test blob entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}