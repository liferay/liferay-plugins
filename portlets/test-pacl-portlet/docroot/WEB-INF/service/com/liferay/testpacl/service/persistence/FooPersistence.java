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

package com.liferay.testpacl.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.testpacl.model.Foo;

/**
 * The persistence interface for the foo service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FooPersistenceImpl
 * @see FooUtil
 * @generated
 */
public interface FooPersistence extends BasePersistence<Foo> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FooUtil} to access the foo persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the foo in the entity cache if it is enabled.
	*
	* @param foo the foo
	*/
	public void cacheResult(com.liferay.testpacl.model.Foo foo);

	/**
	* Caches the foos in the entity cache if it is enabled.
	*
	* @param foos the foos
	*/
	public void cacheResult(java.util.List<com.liferay.testpacl.model.Foo> foos);

	/**
	* Creates a new foo with the primary key. Does not add the foo to the database.
	*
	* @param fooId the primary key for the new foo
	* @return the new foo
	*/
	public com.liferay.testpacl.model.Foo create(long fooId);

	/**
	* Removes the foo with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fooId the primary key of the foo
	* @return the foo that was removed
	* @throws com.liferay.testpacl.NoSuchFooException if a foo with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testpacl.model.Foo remove(long fooId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testpacl.NoSuchFooException;

	public com.liferay.testpacl.model.Foo updateImpl(
		com.liferay.testpacl.model.Foo foo)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the foo with the primary key or throws a {@link com.liferay.testpacl.NoSuchFooException} if it could not be found.
	*
	* @param fooId the primary key of the foo
	* @return the foo
	* @throws com.liferay.testpacl.NoSuchFooException if a foo with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testpacl.model.Foo findByPrimaryKey(long fooId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testpacl.NoSuchFooException;

	/**
	* Returns the foo with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param fooId the primary key of the foo
	* @return the foo, or <code>null</code> if a foo with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testpacl.model.Foo fetchByPrimaryKey(long fooId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the foos where field2 = &#63;.
	*
	* @param field2 the field2
	* @return the matching foos
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.testpacl.model.Foo> findByField2(
		boolean field2)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the foos where field2 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param field2 the field2
	* @param start the lower bound of the range of foos
	* @param end the upper bound of the range of foos (not inclusive)
	* @return the range of matching foos
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.testpacl.model.Foo> findByField2(
		boolean field2, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the foos where field2 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param field2 the field2
	* @param start the lower bound of the range of foos
	* @param end the upper bound of the range of foos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching foos
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.testpacl.model.Foo> findByField2(
		boolean field2, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first foo in the ordered set where field2 = &#63;.
	*
	* @param field2 the field2
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching foo
	* @throws com.liferay.testpacl.NoSuchFooException if a matching foo could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testpacl.model.Foo findByField2_First(boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testpacl.NoSuchFooException;

	/**
	* Returns the first foo in the ordered set where field2 = &#63;.
	*
	* @param field2 the field2
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching foo, or <code>null</code> if a matching foo could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testpacl.model.Foo fetchByField2_First(boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last foo in the ordered set where field2 = &#63;.
	*
	* @param field2 the field2
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching foo
	* @throws com.liferay.testpacl.NoSuchFooException if a matching foo could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testpacl.model.Foo findByField2_Last(boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testpacl.NoSuchFooException;

	/**
	* Returns the last foo in the ordered set where field2 = &#63;.
	*
	* @param field2 the field2
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching foo, or <code>null</code> if a matching foo could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testpacl.model.Foo fetchByField2_Last(boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the foos before and after the current foo in the ordered set where field2 = &#63;.
	*
	* @param fooId the primary key of the current foo
	* @param field2 the field2
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next foo
	* @throws com.liferay.testpacl.NoSuchFooException if a foo with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testpacl.model.Foo[] findByField2_PrevAndNext(
		long fooId, boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testpacl.NoSuchFooException;

	/**
	* Returns all the foos.
	*
	* @return the foos
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.testpacl.model.Foo> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.testpacl.model.Foo> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the foos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of foos
	* @param end the upper bound of the range of foos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of foos
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.testpacl.model.Foo> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the foos where field2 = &#63; from the database.
	*
	* @param field2 the field2
	* @throws SystemException if a system exception occurred
	*/
	public void removeByField2(boolean field2)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the foos from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of foos where field2 = &#63;.
	*
	* @param field2 the field2
	* @return the number of matching foos
	* @throws SystemException if a system exception occurred
	*/
	public int countByField2(boolean field2)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of foos.
	*
	* @return the number of foos
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}