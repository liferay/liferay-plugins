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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the h r branch service. This utility wraps {@link HRBranchPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRBranchPersistence
 * @see HRBranchPersistenceImpl
 * @generated
 */
public class HRBranchUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(HRBranch hrBranch) {
		getPersistence().clearCache(hrBranch);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<HRBranch> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HRBranch> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HRBranch> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static HRBranch remove(HRBranch hrBranch) throws SystemException {
		return getPersistence().remove(hrBranch);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HRBranch update(HRBranch hrBranch, boolean merge)
		throws SystemException {
		return getPersistence().update(hrBranch, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HRBranch update(HRBranch hrBranch, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(hrBranch, merge, serviceContext);
	}

	/**
	* Caches the h r branch in the entity cache if it is enabled.
	*
	* @param hrBranch the h r branch to cache
	*/
	public static void cacheResult(com.liferay.hr.model.HRBranch hrBranch) {
		getPersistence().cacheResult(hrBranch);
	}

	/**
	* Caches the h r branchs in the entity cache if it is enabled.
	*
	* @param hrBranchs the h r branchs to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.hr.model.HRBranch> hrBranchs) {
		getPersistence().cacheResult(hrBranchs);
	}

	/**
	* Creates a new h r branch with the primary key. Does not add the h r branch to the database.
	*
	* @param hrBranchId the primary key for the new h r branch
	* @return the new h r branch
	*/
	public static com.liferay.hr.model.HRBranch create(long hrBranchId) {
		return getPersistence().create(hrBranchId);
	}

	/**
	* Removes the h r branch with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrBranchId the primary key of the h r branch to remove
	* @return the h r branch that was removed
	* @throws com.liferay.hr.NoSuchBranchException if a h r branch with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRBranch remove(long hrBranchId)
		throws com.liferay.hr.NoSuchBranchException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(hrBranchId);
	}

	public static com.liferay.hr.model.HRBranch updateImpl(
		com.liferay.hr.model.HRBranch hrBranch, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(hrBranch, merge);
	}

	/**
	* Finds the h r branch with the primary key or throws a {@link com.liferay.hr.NoSuchBranchException} if it could not be found.
	*
	* @param hrBranchId the primary key of the h r branch to find
	* @return the h r branch
	* @throws com.liferay.hr.NoSuchBranchException if a h r branch with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRBranch findByPrimaryKey(
		long hrBranchId)
		throws com.liferay.hr.NoSuchBranchException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(hrBranchId);
	}

	/**
	* Finds the h r branch with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrBranchId the primary key of the h r branch to find
	* @return the h r branch, or <code>null</code> if a h r branch with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRBranch fetchByPrimaryKey(
		long hrBranchId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(hrBranchId);
	}

	/**
	* Finds all the h r branchs.
	*
	* @return the h r branchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRBranch> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Finds a range of all the h r branchs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r branchs to return
	* @param end the upper bound of the range of h r branchs to return (not inclusive)
	* @return the range of h r branchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRBranch> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Finds an ordered range of all the h r branchs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r branchs to return
	* @param end the upper bound of the range of h r branchs to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r branchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRBranch> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the h r branchs from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the h r branchs.
	*
	* @return the number of h r branchs
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	/**
	* Gets all the h r job titles associated with the h r branch.
	*
	* @param pk the primary key of the h r branch to get the associated h r job titles for
	* @return the h r job titles associated with the h r branch
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRJobTitle> getHRJobTitles(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getHRJobTitles(pk);
	}

	/**
	* Gets a range of all the h r job titles associated with the h r branch.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the h r branch to get the associated h r job titles for
	* @param start the lower bound of the range of h r branchs to return
	* @param end the upper bound of the range of h r branchs to return (not inclusive)
	* @return the range of h r job titles associated with the h r branch
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRJobTitle> getHRJobTitles(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getHRJobTitles(pk, start, end);
	}

	/**
	* Gets an ordered range of all the h r job titles associated with the h r branch.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the h r branch to get the associated h r job titles for
	* @param start the lower bound of the range of h r branchs to return
	* @param end the upper bound of the range of h r branchs to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r job titles associated with the h r branch
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRJobTitle> getHRJobTitles(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getHRJobTitles(pk, start, end, orderByComparator);
	}

	/**
	* Gets the number of h r job titles associated with the h r branch.
	*
	* @param pk the primary key of the h r branch to get the number of associated h r job titles for
	* @return the number of h r job titles associated with the h r branch
	* @throws SystemException if a system exception occurred
	*/
	public static int getHRJobTitlesSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getHRJobTitlesSize(pk);
	}

	/**
	* Determines if the h r job title is associated with the h r branch.
	*
	* @param pk the primary key of the h r branch
	* @param hrJobTitlePK the primary key of the h r job title
	* @return <code>true</code> if the h r job title is associated with the h r branch; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsHRJobTitle(long pk, long hrJobTitlePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsHRJobTitle(pk, hrJobTitlePK);
	}

	/**
	* Determines if the h r branch has any h r job titles associated with it.
	*
	* @param pk the primary key of the h r branch to check for associations with h r job titles
	* @return <code>true</code> if the h r branch has any h r job titles associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsHRJobTitles(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsHRJobTitles(pk);
	}

	/**
	* Adds an association between the h r branch and the h r job title. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r branch
	* @param hrJobTitlePK the primary key of the h r job title
	* @throws SystemException if a system exception occurred
	*/
	public static void addHRJobTitle(long pk, long hrJobTitlePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addHRJobTitle(pk, hrJobTitlePK);
	}

	/**
	* Adds an association between the h r branch and the h r job title. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r branch
	* @param hrJobTitle the h r job title
	* @throws SystemException if a system exception occurred
	*/
	public static void addHRJobTitle(long pk,
		com.liferay.hr.model.HRJobTitle hrJobTitle)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addHRJobTitle(pk, hrJobTitle);
	}

	/**
	* Adds an association between the h r branch and the h r job titles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r branch
	* @param hrJobTitlePKs the primary keys of the h r job titles
	* @throws SystemException if a system exception occurred
	*/
	public static void addHRJobTitles(long pk, long[] hrJobTitlePKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addHRJobTitles(pk, hrJobTitlePKs);
	}

	/**
	* Adds an association between the h r branch and the h r job titles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r branch
	* @param hrJobTitles the h r job titles
	* @throws SystemException if a system exception occurred
	*/
	public static void addHRJobTitles(long pk,
		java.util.List<com.liferay.hr.model.HRJobTitle> hrJobTitles)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addHRJobTitles(pk, hrJobTitles);
	}

	/**
	* Clears all associations between the h r branch and its h r job titles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r branch to clear the associated h r job titles from
	* @throws SystemException if a system exception occurred
	*/
	public static void clearHRJobTitles(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().clearHRJobTitles(pk);
	}

	/**
	* Removes the association between the h r branch and the h r job title. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r branch
	* @param hrJobTitlePK the primary key of the h r job title
	* @throws SystemException if a system exception occurred
	*/
	public static void removeHRJobTitle(long pk, long hrJobTitlePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeHRJobTitle(pk, hrJobTitlePK);
	}

	/**
	* Removes the association between the h r branch and the h r job title. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r branch
	* @param hrJobTitle the h r job title
	* @throws SystemException if a system exception occurred
	*/
	public static void removeHRJobTitle(long pk,
		com.liferay.hr.model.HRJobTitle hrJobTitle)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeHRJobTitle(pk, hrJobTitle);
	}

	/**
	* Removes the association between the h r branch and the h r job titles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r branch
	* @param hrJobTitlePKs the primary keys of the h r job titles
	* @throws SystemException if a system exception occurred
	*/
	public static void removeHRJobTitles(long pk, long[] hrJobTitlePKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeHRJobTitles(pk, hrJobTitlePKs);
	}

	/**
	* Removes the association between the h r branch and the h r job titles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r branch
	* @param hrJobTitles the h r job titles
	* @throws SystemException if a system exception occurred
	*/
	public static void removeHRJobTitles(long pk,
		java.util.List<com.liferay.hr.model.HRJobTitle> hrJobTitles)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeHRJobTitles(pk, hrJobTitles);
	}

	/**
	* Sets the h r job titles associated with the h r branch, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r branch to set the associations for
	* @param hrJobTitlePKs the primary keys of the h r job titles to be associated with the h r branch
	* @throws SystemException if a system exception occurred
	*/
	public static void setHRJobTitles(long pk, long[] hrJobTitlePKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setHRJobTitles(pk, hrJobTitlePKs);
	}

	/**
	* Sets the h r job titles associated with the h r branch, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r branch to set the associations for
	* @param hrJobTitles the h r job titles to be associated with the h r branch
	* @throws SystemException if a system exception occurred
	*/
	public static void setHRJobTitles(long pk,
		java.util.List<com.liferay.hr.model.HRJobTitle> hrJobTitles)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setHRJobTitles(pk, hrJobTitles);
	}

	public static HRBranchPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HRBranchPersistence)PortletBeanLocatorUtil.locate(com.liferay.hr.service.ClpSerializer.getServletContextName(),
					HRBranchPersistence.class.getName());

			ReferenceRegistry.registerReference(HRBranchUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(HRBranchPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(HRBranchUtil.class, "_persistence");
	}

	private static HRBranchPersistence _persistence;
}