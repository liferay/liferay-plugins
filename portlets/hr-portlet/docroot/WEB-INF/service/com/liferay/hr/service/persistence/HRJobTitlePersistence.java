/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.hr.service.persistence;

import com.liferay.hr.model.HRJobTitle;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r job title service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRJobTitlePersistenceImpl
 * @see HRJobTitleUtil
 * @generated
 */
public interface HRJobTitlePersistence extends BasePersistence<HRJobTitle> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HRJobTitleUtil} to access the h r job title persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r job title in the entity cache if it is enabled.
	*
	* @param hrJobTitle the h r job title to cache
	*/
	public void cacheResult(com.liferay.hr.model.HRJobTitle hrJobTitle);

	/**
	* Caches the h r job titles in the entity cache if it is enabled.
	*
	* @param hrJobTitles the h r job titles to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.hr.model.HRJobTitle> hrJobTitles);

	/**
	* Creates a new h r job title with the primary key. Does not add the h r job title to the database.
	*
	* @param hrJobTitleId the primary key for the new h r job title
	* @return the new h r job title
	*/
	public com.liferay.hr.model.HRJobTitle create(long hrJobTitleId);

	/**
	* Removes the h r job title with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrJobTitleId the primary key of the h r job title to remove
	* @return the h r job title that was removed
	* @throws com.liferay.hr.NoSuchJobTitleException if a h r job title with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRJobTitle remove(long hrJobTitleId)
		throws com.liferay.hr.NoSuchJobTitleException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HRJobTitle updateImpl(
		com.liferay.hr.model.HRJobTitle hrJobTitle, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r job title with the primary key or throws a {@link com.liferay.hr.NoSuchJobTitleException} if it could not be found.
	*
	* @param hrJobTitleId the primary key of the h r job title to find
	* @return the h r job title
	* @throws com.liferay.hr.NoSuchJobTitleException if a h r job title with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRJobTitle findByPrimaryKey(long hrJobTitleId)
		throws com.liferay.hr.NoSuchJobTitleException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r job title with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrJobTitleId the primary key of the h r job title to find
	* @return the h r job title, or <code>null</code> if a h r job title with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRJobTitle fetchByPrimaryKey(long hrJobTitleId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the h r job titles.
	*
	* @return the h r job titles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRJobTitle> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the h r job titles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r job titles to return
	* @param end the upper bound of the range of h r job titles to return (not inclusive)
	* @return the range of h r job titles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRJobTitle> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the h r job titles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r job titles to return
	* @param end the upper bound of the range of h r job titles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r job titles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRJobTitle> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r job titles from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the h r job titles.
	*
	* @return the number of h r job titles
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets all the h r branchs associated with the h r job title.
	*
	* @param pk the primary key of the h r job title to get the associated h r branchs for
	* @return the h r branchs associated with the h r job title
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRBranch> getHRBranchs(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets a range of all the h r branchs associated with the h r job title.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the h r job title to get the associated h r branchs for
	* @param start the lower bound of the range of h r job titles to return
	* @param end the upper bound of the range of h r job titles to return (not inclusive)
	* @return the range of h r branchs associated with the h r job title
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRBranch> getHRBranchs(long pk,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets an ordered range of all the h r branchs associated with the h r job title.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the h r job title to get the associated h r branchs for
	* @param start the lower bound of the range of h r job titles to return
	* @param end the upper bound of the range of h r job titles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r branchs associated with the h r job title
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRBranch> getHRBranchs(long pk,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets the number of h r branchs associated with the h r job title.
	*
	* @param pk the primary key of the h r job title to get the number of associated h r branchs for
	* @return the number of h r branchs associated with the h r job title
	* @throws SystemException if a system exception occurred
	*/
	public int getHRBranchsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Determines if the h r branch is associated with the h r job title.
	*
	* @param pk the primary key of the h r job title
	* @param hrBranchPK the primary key of the h r branch
	* @return <code>true</code> if the h r branch is associated with the h r job title; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsHRBranch(long pk, long hrBranchPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Determines if the h r job title has any h r branchs associated with it.
	*
	* @param pk the primary key of the h r job title to check for associations with h r branchs
	* @return <code>true</code> if the h r job title has any h r branchs associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsHRBranchs(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the h r job title and the h r branch. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r job title
	* @param hrBranchPK the primary key of the h r branch
	* @throws SystemException if a system exception occurred
	*/
	public void addHRBranch(long pk, long hrBranchPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the h r job title and the h r branch. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r job title
	* @param hrBranch the h r branch
	* @throws SystemException if a system exception occurred
	*/
	public void addHRBranch(long pk, com.liferay.hr.model.HRBranch hrBranch)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the h r job title and the h r branchs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r job title
	* @param hrBranchPKs the primary keys of the h r branchs
	* @throws SystemException if a system exception occurred
	*/
	public void addHRBranchs(long pk, long[] hrBranchPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the h r job title and the h r branchs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r job title
	* @param hrBranchs the h r branchs
	* @throws SystemException if a system exception occurred
	*/
	public void addHRBranchs(long pk,
		java.util.List<com.liferay.hr.model.HRBranch> hrBranchs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Clears all associations between the h r job title and its h r branchs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r job title to clear the associated h r branchs from
	* @throws SystemException if a system exception occurred
	*/
	public void clearHRBranchs(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the h r job title and the h r branch. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r job title
	* @param hrBranchPK the primary key of the h r branch
	* @throws SystemException if a system exception occurred
	*/
	public void removeHRBranch(long pk, long hrBranchPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the h r job title and the h r branch. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r job title
	* @param hrBranch the h r branch
	* @throws SystemException if a system exception occurred
	*/
	public void removeHRBranch(long pk, com.liferay.hr.model.HRBranch hrBranch)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the h r job title and the h r branchs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r job title
	* @param hrBranchPKs the primary keys of the h r branchs
	* @throws SystemException if a system exception occurred
	*/
	public void removeHRBranchs(long pk, long[] hrBranchPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the h r job title and the h r branchs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r job title
	* @param hrBranchs the h r branchs
	* @throws SystemException if a system exception occurred
	*/
	public void removeHRBranchs(long pk,
		java.util.List<com.liferay.hr.model.HRBranch> hrBranchs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the h r branchs associated with the h r job title, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r job title to set the associations for
	* @param hrBranchPKs the primary keys of the h r branchs to be associated with the h r job title
	* @throws SystemException if a system exception occurred
	*/
	public void setHRBranchs(long pk, long[] hrBranchPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the h r branchs associated with the h r job title, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r job title to set the associations for
	* @param hrBranchs the h r branchs to be associated with the h r job title
	* @throws SystemException if a system exception occurred
	*/
	public void setHRBranchs(long pk,
		java.util.List<com.liferay.hr.model.HRBranch> hrBranchs)
		throws com.liferay.portal.kernel.exception.SystemException;

	public HRJobTitle remove(HRJobTitle hrJobTitle) throws SystemException;
}