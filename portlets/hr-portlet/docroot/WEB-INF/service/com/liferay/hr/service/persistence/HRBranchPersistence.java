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

import com.liferay.hr.model.HRBranch;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r branch service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRBranchPersistenceImpl
 * @see HRBranchUtil
 * @generated
 */
public interface HRBranchPersistence extends BasePersistence<HRBranch> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HRBranchUtil} to access the h r branch persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r branch in the entity cache if it is enabled.
	*
	* @param hrBranch the h r branch
	*/
	public void cacheResult(com.liferay.hr.model.HRBranch hrBranch);

	/**
	* Caches the h r branchs in the entity cache if it is enabled.
	*
	* @param hrBranchs the h r branchs
	*/
	public void cacheResult(
		java.util.List<com.liferay.hr.model.HRBranch> hrBranchs);

	/**
	* Creates a new h r branch with the primary key. Does not add the h r branch to the database.
	*
	* @param hrBranchId the primary key for the new h r branch
	* @return the new h r branch
	*/
	public com.liferay.hr.model.HRBranch create(long hrBranchId);

	/**
	* Removes the h r branch with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrBranchId the primary key of the h r branch
	* @return the h r branch that was removed
	* @throws com.liferay.hr.NoSuchBranchException if a h r branch with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRBranch remove(long hrBranchId)
		throws com.liferay.hr.NoSuchBranchException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HRBranch updateImpl(
		com.liferay.hr.model.HRBranch hrBranch, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r branch with the primary key or throws a {@link com.liferay.hr.NoSuchBranchException} if it could not be found.
	*
	* @param hrBranchId the primary key of the h r branch
	* @return the h r branch
	* @throws com.liferay.hr.NoSuchBranchException if a h r branch with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRBranch findByPrimaryKey(long hrBranchId)
		throws com.liferay.hr.NoSuchBranchException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r branch with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrBranchId the primary key of the h r branch
	* @return the h r branch, or <code>null</code> if a h r branch with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRBranch fetchByPrimaryKey(long hrBranchId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the h r branchs.
	*
	* @return the h r branchs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRBranch> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the h r branchs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r branchs
	* @param end the upper bound of the range of h r branchs (not inclusive)
	* @return the range of h r branchs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRBranch> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the h r branchs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r branchs
	* @param end the upper bound of the range of h r branchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r branchs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRBranch> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r branchs from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of h r branchs.
	*
	* @return the number of h r branchs
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the h r job titles associated with the h r branch.
	*
	* @param pk the primary key of the h r branch
	* @return the h r job titles associated with the h r branch
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRJobTitle> getHRJobTitles(
		long pk) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the h r job titles associated with the h r branch.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the h r branch
	* @param start the lower bound of the range of h r branchs
	* @param end the upper bound of the range of h r branchs (not inclusive)
	* @return the range of h r job titles associated with the h r branch
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRJobTitle> getHRJobTitles(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the h r job titles associated with the h r branch.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the h r branch
	* @param start the lower bound of the range of h r branchs
	* @param end the upper bound of the range of h r branchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r job titles associated with the h r branch
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRJobTitle> getHRJobTitles(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of h r job titles associated with the h r branch.
	*
	* @param pk the primary key of the h r branch
	* @return the number of h r job titles associated with the h r branch
	* @throws SystemException if a system exception occurred
	*/
	public int getHRJobTitlesSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the h r job title is associated with the h r branch.
	*
	* @param pk the primary key of the h r branch
	* @param hrJobTitlePK the primary key of the h r job title
	* @return <code>true</code> if the h r job title is associated with the h r branch; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsHRJobTitle(long pk, long hrJobTitlePK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the h r branch has any h r job titles associated with it.
	*
	* @param pk the primary key of the h r branch to check for associations with h r job titles
	* @return <code>true</code> if the h r branch has any h r job titles associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsHRJobTitles(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the h r branch and the h r job title. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r branch
	* @param hrJobTitlePK the primary key of the h r job title
	* @throws SystemException if a system exception occurred
	*/
	public void addHRJobTitle(long pk, long hrJobTitlePK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the h r branch and the h r job title. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r branch
	* @param hrJobTitle the h r job title
	* @throws SystemException if a system exception occurred
	*/
	public void addHRJobTitle(long pk,
		com.liferay.hr.model.HRJobTitle hrJobTitle)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the h r branch and the h r job titles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r branch
	* @param hrJobTitlePKs the primary keys of the h r job titles
	* @throws SystemException if a system exception occurred
	*/
	public void addHRJobTitles(long pk, long[] hrJobTitlePKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the h r branch and the h r job titles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r branch
	* @param hrJobTitles the h r job titles
	* @throws SystemException if a system exception occurred
	*/
	public void addHRJobTitles(long pk,
		java.util.List<com.liferay.hr.model.HRJobTitle> hrJobTitles)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Clears all associations between the h r branch and its h r job titles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r branch to clear the associated h r job titles from
	* @throws SystemException if a system exception occurred
	*/
	public void clearHRJobTitles(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the h r branch and the h r job title. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r branch
	* @param hrJobTitlePK the primary key of the h r job title
	* @throws SystemException if a system exception occurred
	*/
	public void removeHRJobTitle(long pk, long hrJobTitlePK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the h r branch and the h r job title. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r branch
	* @param hrJobTitle the h r job title
	* @throws SystemException if a system exception occurred
	*/
	public void removeHRJobTitle(long pk,
		com.liferay.hr.model.HRJobTitle hrJobTitle)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the h r branch and the h r job titles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r branch
	* @param hrJobTitlePKs the primary keys of the h r job titles
	* @throws SystemException if a system exception occurred
	*/
	public void removeHRJobTitles(long pk, long[] hrJobTitlePKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the h r branch and the h r job titles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r branch
	* @param hrJobTitles the h r job titles
	* @throws SystemException if a system exception occurred
	*/
	public void removeHRJobTitles(long pk,
		java.util.List<com.liferay.hr.model.HRJobTitle> hrJobTitles)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the h r job titles associated with the h r branch, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r branch
	* @param hrJobTitlePKs the primary keys of the h r job titles to be associated with the h r branch
	* @throws SystemException if a system exception occurred
	*/
	public void setHRJobTitles(long pk, long[] hrJobTitlePKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the h r job titles associated with the h r branch, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r branch
	* @param hrJobTitles the h r job titles to be associated with the h r branch
	* @throws SystemException if a system exception occurred
	*/
	public void setHRJobTitles(long pk,
		java.util.List<com.liferay.hr.model.HRJobTitle> hrJobTitles)
		throws com.liferay.portal.kernel.exception.SystemException;

	public HRBranch remove(HRBranch hrBranch) throws SystemException;
}