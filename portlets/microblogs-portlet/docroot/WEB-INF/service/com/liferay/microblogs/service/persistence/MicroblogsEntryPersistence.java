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

package com.liferay.microblogs.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.microblogs.model.MicroblogsEntry;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the microblogs entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MicroblogsEntryPersistenceImpl
 * @see MicroblogsEntryUtil
 * @generated
 */
@ProviderType
public interface MicroblogsEntryPersistence extends BasePersistence<MicroblogsEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MicroblogsEntryUtil} to access the microblogs entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the microblogs entries where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCompanyId(
		long companyId);

	/**
	* Returns a range of all the microblogs entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCompanyId(
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the microblogs entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the first microblogs entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns the first microblogs entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the last microblogs entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns the last microblogs entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set where companyId = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] findByCompanyId_PrevAndNext(
		long microblogsEntryId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns all the microblogs entries that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByCompanyId(
		long companyId);

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByCompanyId(
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the microblogs entries that the user has permissions to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set of microblogs entries that the user has permission to view where companyId = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] filterFindByCompanyId_PrevAndNext(
		long microblogsEntryId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Removes all the microblogs entries where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of microblogs entries where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching microblogs entries
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns the number of microblogs entries that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching microblogs entries that the user has permission to view
	*/
	public int filterCountByCompanyId(long companyId);

	/**
	* Returns all the microblogs entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByUserId(
		long userId);

	/**
	* Returns a range of all the microblogs entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByUserId(
		long userId, int start, int end);

	/**
	* Returns an ordered range of all the microblogs entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the first microblogs entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns the first microblogs entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the last microblogs entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns the last microblogs entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set where userId = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] findByUserId_PrevAndNext(
		long microblogsEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns all the microblogs entries that the user has permission to view where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByUserId(
		long userId);

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByUserId(
		long userId, int start, int end);

	/**
	* Returns an ordered range of all the microblogs entries that the user has permissions to view where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set of microblogs entries that the user has permission to view where userId = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] filterFindByUserId_PrevAndNext(
		long microblogsEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Removes all the microblogs entries where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of microblogs entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching microblogs entries
	*/
	public int countByUserId(long userId);

	/**
	* Returns the number of microblogs entries that the user has permission to view where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching microblogs entries that the user has permission to view
	*/
	public int filterCountByUserId(long userId);

	/**
	* Returns all the microblogs entries where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByU_T(
		long userId, int type);

	/**
	* Returns a range of all the microblogs entries where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByU_T(
		long userId, int type, int start, int end);

	/**
	* Returns an ordered range of all the microblogs entries where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByU_T(
		long userId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the first microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByU_T_First(
		long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns the first microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry fetchByU_T_First(
		long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the last microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByU_T_Last(
		long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns the last microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry fetchByU_T_Last(
		long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] findByU_T_PrevAndNext(
		long microblogsEntryId, long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns all the microblogs entries that the user has permission to view where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByU_T(
		long userId, int type);

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByU_T(
		long userId, int type, int start, int end);

	/**
	* Returns an ordered range of all the microblogs entries that the user has permissions to view where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByU_T(
		long userId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set of microblogs entries that the user has permission to view where userId = &#63; and type = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] filterFindByU_T_PrevAndNext(
		long microblogsEntryId, long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Removes all the microblogs entries where userId = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param type the type
	*/
	public void removeByU_T(long userId, int type);

	/**
	* Returns the number of microblogs entries where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the number of matching microblogs entries
	*/
	public int countByU_T(long userId, int type);

	/**
	* Returns the number of microblogs entries that the user has permission to view where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the number of matching microblogs entries that the user has permission to view
	*/
	public int filterCountByU_T(long userId, int type);

	/**
	* Returns all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @return the matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCCNI_CCPK(
		long creatorClassNameId, long creatorClassPK);

	/**
	* Returns a range of all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCCNI_CCPK(
		long creatorClassNameId, long creatorClassPK, int start, int end);

	/**
	* Returns an ordered range of all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCCNI_CCPK(
		long creatorClassNameId, long creatorClassPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the first microblogs entry in the ordered set where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByCCNI_CCPK_First(
		long creatorClassNameId, long creatorClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns the first microblogs entry in the ordered set where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry fetchByCCNI_CCPK_First(
		long creatorClassNameId, long creatorClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the last microblogs entry in the ordered set where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByCCNI_CCPK_Last(
		long creatorClassNameId, long creatorClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns the last microblogs entry in the ordered set where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry fetchByCCNI_CCPK_Last(
		long creatorClassNameId, long creatorClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] findByCCNI_CCPK_PrevAndNext(
		long microblogsEntryId, long creatorClassNameId, long creatorClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns all the microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @return the matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByCCNI_CCPK(
		long creatorClassNameId, long creatorClassPK);

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByCCNI_CCPK(
		long creatorClassNameId, long creatorClassPK, int start, int end);

	/**
	* Returns an ordered range of all the microblogs entries that the user has permissions to view where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByCCNI_CCPK(
		long creatorClassNameId, long creatorClassPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set of microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] filterFindByCCNI_CCPK_PrevAndNext(
		long microblogsEntryId, long creatorClassNameId, long creatorClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns all the microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = any &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @return the matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByCCNI_CCPK(
		long creatorClassNameId, long[] creatorClassPKs);

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByCCNI_CCPK(
		long creatorClassNameId, long[] creatorClassPKs, int start, int end);

	/**
	* Returns an ordered range of all the microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByCCNI_CCPK(
		long creatorClassNameId, long[] creatorClassPKs, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @return the matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCCNI_CCPK(
		long creatorClassNameId, long[] creatorClassPKs);

	/**
	* Returns a range of all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCCNI_CCPK(
		long creatorClassNameId, long[] creatorClassPKs, int start, int end);

	/**
	* Returns an ordered range of all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCCNI_CCPK(
		long creatorClassNameId, long[] creatorClassPKs, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Removes all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = &#63; from the database.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	*/
	public void removeByCCNI_CCPK(long creatorClassNameId, long creatorClassPK);

	/**
	* Returns the number of microblogs entries where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @return the number of matching microblogs entries
	*/
	public int countByCCNI_CCPK(long creatorClassNameId, long creatorClassPK);

	/**
	* Returns the number of microblogs entries where creatorClassNameId = &#63; and creatorClassPK = any &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @return the number of matching microblogs entries
	*/
	public int countByCCNI_CCPK(long creatorClassNameId, long[] creatorClassPKs);

	/**
	* Returns the number of microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @return the number of matching microblogs entries that the user has permission to view
	*/
	public int filterCountByCCNI_CCPK(long creatorClassNameId,
		long creatorClassPK);

	/**
	* Returns the number of microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = any &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @return the number of matching microblogs entries that the user has permission to view
	*/
	public int filterCountByCCNI_CCPK(long creatorClassNameId,
		long[] creatorClassPKs);

	/**
	* Returns all the microblogs entries where creatorClassNameId = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @return the matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCCNI_T(
		long creatorClassNameId, int type);

	/**
	* Returns a range of all the microblogs entries where creatorClassNameId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCCNI_T(
		long creatorClassNameId, int type, int start, int end);

	/**
	* Returns an ordered range of all the microblogs entries where creatorClassNameId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCCNI_T(
		long creatorClassNameId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the first microblogs entry in the ordered set where creatorClassNameId = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByCCNI_T_First(
		long creatorClassNameId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns the first microblogs entry in the ordered set where creatorClassNameId = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry fetchByCCNI_T_First(
		long creatorClassNameId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the last microblogs entry in the ordered set where creatorClassNameId = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByCCNI_T_Last(
		long creatorClassNameId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns the last microblogs entry in the ordered set where creatorClassNameId = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry fetchByCCNI_T_Last(
		long creatorClassNameId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set where creatorClassNameId = &#63; and type = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] findByCCNI_T_PrevAndNext(
		long microblogsEntryId, long creatorClassNameId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns all the microblogs entries that the user has permission to view where creatorClassNameId = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @return the matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByCCNI_T(
		long creatorClassNameId, int type);

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where creatorClassNameId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByCCNI_T(
		long creatorClassNameId, int type, int start, int end);

	/**
	* Returns an ordered range of all the microblogs entries that the user has permissions to view where creatorClassNameId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByCCNI_T(
		long creatorClassNameId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set of microblogs entries that the user has permission to view where creatorClassNameId = &#63; and type = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] filterFindByCCNI_T_PrevAndNext(
		long microblogsEntryId, long creatorClassNameId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Removes all the microblogs entries where creatorClassNameId = &#63; and type = &#63; from the database.
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	*/
	public void removeByCCNI_T(long creatorClassNameId, int type);

	/**
	* Returns the number of microblogs entries where creatorClassNameId = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @return the number of matching microblogs entries
	*/
	public int countByCCNI_T(long creatorClassNameId, int type);

	/**
	* Returns the number of microblogs entries that the user has permission to view where creatorClassNameId = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @return the number of matching microblogs entries that the user has permission to view
	*/
	public int filterCountByCCNI_T(long creatorClassNameId, int type);

	/**
	* Returns all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @return the matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByT_R(
		int type, long receiverUserId);

	/**
	* Returns a range of all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByT_R(
		int type, long receiverUserId, int start, int end);

	/**
	* Returns an ordered range of all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByT_R(
		int type, long receiverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the first microblogs entry in the ordered set where type = &#63; and receiverUserId = &#63;.
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByT_R_First(
		int type, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns the first microblogs entry in the ordered set where type = &#63; and receiverUserId = &#63;.
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry fetchByT_R_First(
		int type, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the last microblogs entry in the ordered set where type = &#63; and receiverUserId = &#63;.
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByT_R_Last(
		int type, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns the last microblogs entry in the ordered set where type = &#63; and receiverUserId = &#63;.
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry fetchByT_R_Last(
		int type, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set where type = &#63; and receiverUserId = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] findByT_R_PrevAndNext(
		long microblogsEntryId, int type, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns all the microblogs entries that the user has permission to view where type = &#63; and receiverUserId = &#63;.
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @return the matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByT_R(
		int type, long receiverUserId);

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where type = &#63; and receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByT_R(
		int type, long receiverUserId, int start, int end);

	/**
	* Returns an ordered range of all the microblogs entries that the user has permissions to view where type = &#63; and receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByT_R(
		int type, long receiverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set of microblogs entries that the user has permission to view where type = &#63; and receiverUserId = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] filterFindByT_R_PrevAndNext(
		long microblogsEntryId, int type, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Removes all the microblogs entries where type = &#63; and receiverUserId = &#63; from the database.
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	*/
	public void removeByT_R(int type, long receiverUserId);

	/**
	* Returns the number of microblogs entries where type = &#63; and receiverUserId = &#63;.
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @return the number of matching microblogs entries
	*/
	public int countByT_R(int type, long receiverUserId);

	/**
	* Returns the number of microblogs entries that the user has permission to view where type = &#63; and receiverUserId = &#63;.
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @return the number of matching microblogs entries that the user has permission to view
	*/
	public int filterCountByT_R(int type, long receiverUserId);

	/**
	* Returns all the microblogs entries where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @return the matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByT_RMEI(
		int type, long receiverMicroblogsEntryId);

	/**
	* Returns a range of all the microblogs entries where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByT_RMEI(
		int type, long receiverMicroblogsEntryId, int start, int end);

	/**
	* Returns an ordered range of all the microblogs entries where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByT_RMEI(
		int type, long receiverMicroblogsEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the first microblogs entry in the ordered set where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByT_RMEI_First(
		int type, long receiverMicroblogsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns the first microblogs entry in the ordered set where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry fetchByT_RMEI_First(
		int type, long receiverMicroblogsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the last microblogs entry in the ordered set where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByT_RMEI_Last(
		int type, long receiverMicroblogsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns the last microblogs entry in the ordered set where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry fetchByT_RMEI_Last(
		int type, long receiverMicroblogsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] findByT_RMEI_PrevAndNext(
		long microblogsEntryId, int type, long receiverMicroblogsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns all the microblogs entries that the user has permission to view where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @return the matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByT_RMEI(
		int type, long receiverMicroblogsEntryId);

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByT_RMEI(
		int type, long receiverMicroblogsEntryId, int start, int end);

	/**
	* Returns an ordered range of all the microblogs entries that the user has permissions to view where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByT_RMEI(
		int type, long receiverMicroblogsEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set of microblogs entries that the user has permission to view where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] filterFindByT_RMEI_PrevAndNext(
		long microblogsEntryId, int type, long receiverMicroblogsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Removes all the microblogs entries where type = &#63; and receiverMicroblogsEntryId = &#63; from the database.
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	*/
	public void removeByT_RMEI(int type, long receiverMicroblogsEntryId);

	/**
	* Returns the number of microblogs entries where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @return the number of matching microblogs entries
	*/
	public int countByT_RMEI(int type, long receiverMicroblogsEntryId);

	/**
	* Returns the number of microblogs entries that the user has permission to view where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @return the number of matching microblogs entries that the user has permission to view
	*/
	public int filterCountByT_RMEI(int type, long receiverMicroblogsEntryId);

	/**
	* Returns all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @return the matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCCNI_CCPK_T(
		long creatorClassNameId, long creatorClassPK, int type);

	/**
	* Returns a range of all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCCNI_CCPK_T(
		long creatorClassNameId, long creatorClassPK, int type, int start,
		int end);

	/**
	* Returns an ordered range of all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCCNI_CCPK_T(
		long creatorClassNameId, long creatorClassPK, int type, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the first microblogs entry in the ordered set where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByCCNI_CCPK_T_First(
		long creatorClassNameId, long creatorClassPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns the first microblogs entry in the ordered set where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry fetchByCCNI_CCPK_T_First(
		long creatorClassNameId, long creatorClassPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the last microblogs entry in the ordered set where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByCCNI_CCPK_T_Last(
		long creatorClassNameId, long creatorClassPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns the last microblogs entry in the ordered set where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry fetchByCCNI_CCPK_T_Last(
		long creatorClassNameId, long creatorClassPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] findByCCNI_CCPK_T_PrevAndNext(
		long microblogsEntryId, long creatorClassNameId, long creatorClassPK,
		int type,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns all the microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @return the matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByCCNI_CCPK_T(
		long creatorClassNameId, long creatorClassPK, int type);

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByCCNI_CCPK_T(
		long creatorClassNameId, long creatorClassPK, int type, int start,
		int end);

	/**
	* Returns an ordered range of all the microblogs entries that the user has permissions to view where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByCCNI_CCPK_T(
		long creatorClassNameId, long creatorClassPK, int type, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set of microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] filterFindByCCNI_CCPK_T_PrevAndNext(
		long microblogsEntryId, long creatorClassNameId, long creatorClassPK,
		int type,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns all the microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = any &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @param type the type
	* @return the matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByCCNI_CCPK_T(
		long creatorClassNameId, long[] creatorClassPKs, int type);

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = any &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByCCNI_CCPK_T(
		long creatorClassNameId, long[] creatorClassPKs, int type, int start,
		int end);

	/**
	* Returns an ordered range of all the microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = any &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByCCNI_CCPK_T(
		long creatorClassNameId, long[] creatorClassPKs, int type, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = any &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @param type the type
	* @return the matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCCNI_CCPK_T(
		long creatorClassNameId, long[] creatorClassPKs, int type);

	/**
	* Returns a range of all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = any &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCCNI_CCPK_T(
		long creatorClassNameId, long[] creatorClassPKs, int type, int start,
		int end);

	/**
	* Returns an ordered range of all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = any &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCCNI_CCPK_T(
		long creatorClassNameId, long[] creatorClassPKs, int type, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Removes all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63; from the database.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	*/
	public void removeByCCNI_CCPK_T(long creatorClassNameId,
		long creatorClassPK, int type);

	/**
	* Returns the number of microblogs entries where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @return the number of matching microblogs entries
	*/
	public int countByCCNI_CCPK_T(long creatorClassNameId, long creatorClassPK,
		int type);

	/**
	* Returns the number of microblogs entries where creatorClassNameId = &#63; and creatorClassPK = any &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @param type the type
	* @return the number of matching microblogs entries
	*/
	public int countByCCNI_CCPK_T(long creatorClassNameId,
		long[] creatorClassPKs, int type);

	/**
	* Returns the number of microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @return the number of matching microblogs entries that the user has permission to view
	*/
	public int filterCountByCCNI_CCPK_T(long creatorClassNameId,
		long creatorClassPK, int type);

	/**
	* Returns the number of microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = any &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @param type the type
	* @return the number of matching microblogs entries that the user has permission to view
	*/
	public int filterCountByCCNI_CCPK_T(long creatorClassNameId,
		long[] creatorClassPKs, int type);

	/**
	* Returns all the microblogs entries where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @return the matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByU_C_T_S(
		long userId, java.util.Date createDate, int type, int socialRelationType);

	/**
	* Returns a range of all the microblogs entries where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByU_C_T_S(
		long userId, java.util.Date createDate, int type,
		int socialRelationType, int start, int end);

	/**
	* Returns an ordered range of all the microblogs entries where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByU_C_T_S(
		long userId, java.util.Date createDate, int type,
		int socialRelationType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the first microblogs entry in the ordered set where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByU_C_T_S_First(
		long userId, java.util.Date createDate, int type,
		int socialRelationType,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns the first microblogs entry in the ordered set where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry fetchByU_C_T_S_First(
		long userId, java.util.Date createDate, int type,
		int socialRelationType,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the last microblogs entry in the ordered set where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByU_C_T_S_Last(
		long userId, java.util.Date createDate, int type,
		int socialRelationType,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns the last microblogs entry in the ordered set where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry fetchByU_C_T_S_Last(
		long userId, java.util.Date createDate, int type,
		int socialRelationType,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] findByU_C_T_S_PrevAndNext(
		long microblogsEntryId, long userId, java.util.Date createDate,
		int type, int socialRelationType,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns all the microblogs entries that the user has permission to view where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @return the matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByU_C_T_S(
		long userId, java.util.Date createDate, int type, int socialRelationType);

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByU_C_T_S(
		long userId, java.util.Date createDate, int type,
		int socialRelationType, int start, int end);

	/**
	* Returns an ordered range of all the microblogs entries that the user has permissions to view where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByU_C_T_S(
		long userId, java.util.Date createDate, int type,
		int socialRelationType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set of microblogs entries that the user has permission to view where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] filterFindByU_C_T_S_PrevAndNext(
		long microblogsEntryId, long userId, java.util.Date createDate,
		int type, int socialRelationType,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Removes all the microblogs entries where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63; from the database.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	*/
	public void removeByU_C_T_S(long userId, java.util.Date createDate,
		int type, int socialRelationType);

	/**
	* Returns the number of microblogs entries where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @return the number of matching microblogs entries
	*/
	public int countByU_C_T_S(long userId, java.util.Date createDate, int type,
		int socialRelationType);

	/**
	* Returns the number of microblogs entries that the user has permission to view where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @return the number of matching microblogs entries that the user has permission to view
	*/
	public int filterCountByU_C_T_S(long userId, java.util.Date createDate,
		int type, int socialRelationType);

	/**
	* Caches the microblogs entry in the entity cache if it is enabled.
	*
	* @param microblogsEntry the microblogs entry
	*/
	public void cacheResult(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry);

	/**
	* Caches the microblogs entries in the entity cache if it is enabled.
	*
	* @param microblogsEntries the microblogs entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.microblogs.model.MicroblogsEntry> microblogsEntries);

	/**
	* Creates a new microblogs entry with the primary key. Does not add the microblogs entry to the database.
	*
	* @param microblogsEntryId the primary key for the new microblogs entry
	* @return the new microblogs entry
	*/
	public com.liferay.microblogs.model.MicroblogsEntry create(
		long microblogsEntryId);

	/**
	* Removes the microblogs entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param microblogsEntryId the primary key of the microblogs entry
	* @return the microblogs entry that was removed
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry remove(
		long microblogsEntryId)
		throws com.liferay.microblogs.NoSuchEntryException;

	public com.liferay.microblogs.model.MicroblogsEntry updateImpl(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry);

	/**
	* Returns the microblogs entry with the primary key or throws a {@link com.liferay.microblogs.NoSuchEntryException} if it could not be found.
	*
	* @param microblogsEntryId the primary key of the microblogs entry
	* @return the microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByPrimaryKey(
		long microblogsEntryId)
		throws com.liferay.microblogs.NoSuchEntryException;

	/**
	* Returns the microblogs entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param microblogsEntryId the primary key of the microblogs entry
	* @return the microblogs entry, or <code>null</code> if a microblogs entry with the primary key could not be found
	*/
	public com.liferay.microblogs.model.MicroblogsEntry fetchByPrimaryKey(
		long microblogsEntryId);

	@Override
	public java.util.Map<java.io.Serializable, com.liferay.microblogs.model.MicroblogsEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the microblogs entries.
	*
	* @return the microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findAll();

	/**
	* Returns a range of all the microblogs entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findAll(
		int start, int end);

	/**
	* Returns an ordered range of all the microblogs entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of microblogs entries
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator);

	/**
	* Removes all the microblogs entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of microblogs entries.
	*
	* @return the number of microblogs entries
	*/
	public int countAll();
}