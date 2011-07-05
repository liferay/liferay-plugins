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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the h r job title service. This utility wraps {@link HRJobTitlePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRJobTitlePersistence
 * @see HRJobTitlePersistenceImpl
 * @generated
 */
public class HRJobTitleUtil {
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
	public static void clearCache(HRJobTitle hrJobTitle) {
		getPersistence().clearCache(hrJobTitle);
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
	public static List<HRJobTitle> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HRJobTitle> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HRJobTitle> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static HRJobTitle remove(HRJobTitle hrJobTitle)
		throws SystemException {
		return getPersistence().remove(hrJobTitle);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HRJobTitle update(HRJobTitle hrJobTitle, boolean merge)
		throws SystemException {
		return getPersistence().update(hrJobTitle, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HRJobTitle update(HRJobTitle hrJobTitle, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(hrJobTitle, merge, serviceContext);
	}

	/**
	* Caches the h r job title in the entity cache if it is enabled.
	*
	* @param hrJobTitle the h r job title
	*/
	public static void cacheResult(com.liferay.hr.model.HRJobTitle hrJobTitle) {
		getPersistence().cacheResult(hrJobTitle);
	}

	/**
	* Caches the h r job titles in the entity cache if it is enabled.
	*
	* @param hrJobTitles the h r job titles
	*/
	public static void cacheResult(
		java.util.List<com.liferay.hr.model.HRJobTitle> hrJobTitles) {
		getPersistence().cacheResult(hrJobTitles);
	}

	/**
	* Creates a new h r job title with the primary key. Does not add the h r job title to the database.
	*
	* @param hrJobTitleId the primary key for the new h r job title
	* @return the new h r job title
	*/
	public static com.liferay.hr.model.HRJobTitle create(long hrJobTitleId) {
		return getPersistence().create(hrJobTitleId);
	}

	/**
	* Removes the h r job title with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrJobTitleId the primary key of the h r job title
	* @return the h r job title that was removed
	* @throws com.liferay.hr.NoSuchJobTitleException if a h r job title with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRJobTitle remove(long hrJobTitleId)
		throws com.liferay.hr.NoSuchJobTitleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(hrJobTitleId);
	}

	public static com.liferay.hr.model.HRJobTitle updateImpl(
		com.liferay.hr.model.HRJobTitle hrJobTitle, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(hrJobTitle, merge);
	}

	/**
	* Returns the h r job title with the primary key or throws a {@link com.liferay.hr.NoSuchJobTitleException} if it could not be found.
	*
	* @param hrJobTitleId the primary key of the h r job title
	* @return the h r job title
	* @throws com.liferay.hr.NoSuchJobTitleException if a h r job title with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRJobTitle findByPrimaryKey(
		long hrJobTitleId)
		throws com.liferay.hr.NoSuchJobTitleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(hrJobTitleId);
	}

	/**
	* Returns the h r job title with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrJobTitleId the primary key of the h r job title
	* @return the h r job title, or <code>null</code> if a h r job title with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRJobTitle fetchByPrimaryKey(
		long hrJobTitleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(hrJobTitleId);
	}

	/**
	* Returns all the h r job titles.
	*
	* @return the h r job titles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRJobTitle> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the h r job titles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r job titles
	* @param end the upper bound of the range of h r job titles (not inclusive)
	* @return the range of h r job titles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRJobTitle> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the h r job titles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r job titles
	* @param end the upper bound of the range of h r job titles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r job titles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRJobTitle> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the h r job titles from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of h r job titles.
	*
	* @return the number of h r job titles
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	/**
	* Returns all the h r branchs associated with the h r job title.
	*
	* @param pk the primary key of the h r job title
	* @return the h r branchs associated with the h r job title
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRBranch> getHRBranchs(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getHRBranchs(pk);
	}

	/**
	* Returns a range of all the h r branchs associated with the h r job title.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the h r job title
	* @param start the lower bound of the range of h r job titles
	* @param end the upper bound of the range of h r job titles (not inclusive)
	* @return the range of h r branchs associated with the h r job title
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRBranch> getHRBranchs(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getHRBranchs(pk, start, end);
	}

	/**
	* Returns an ordered range of all the h r branchs associated with the h r job title.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the h r job title
	* @param start the lower bound of the range of h r job titles
	* @param end the upper bound of the range of h r job titles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r branchs associated with the h r job title
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRBranch> getHRBranchs(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getHRBranchs(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of h r branchs associated with the h r job title.
	*
	* @param pk the primary key of the h r job title
	* @return the number of h r branchs associated with the h r job title
	* @throws SystemException if a system exception occurred
	*/
	public static int getHRBranchsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getHRBranchsSize(pk);
	}

	/**
	* Returns <code>true</code> if the h r branch is associated with the h r job title.
	*
	* @param pk the primary key of the h r job title
	* @param hrBranchPK the primary key of the h r branch
	* @return <code>true</code> if the h r branch is associated with the h r job title; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsHRBranch(long pk, long hrBranchPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsHRBranch(pk, hrBranchPK);
	}

	/**
	* Returns <code>true</code> if the h r job title has any h r branchs associated with it.
	*
	* @param pk the primary key of the h r job title to check for associations with h r branchs
	* @return <code>true</code> if the h r job title has any h r branchs associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsHRBranchs(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsHRBranchs(pk);
	}

	/**
	* Adds an association between the h r job title and the h r branch. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r job title
	* @param hrBranchPK the primary key of the h r branch
	* @throws SystemException if a system exception occurred
	*/
	public static void addHRBranch(long pk, long hrBranchPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addHRBranch(pk, hrBranchPK);
	}

	/**
	* Adds an association between the h r job title and the h r branch. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r job title
	* @param hrBranch the h r branch
	* @throws SystemException if a system exception occurred
	*/
	public static void addHRBranch(long pk,
		com.liferay.hr.model.HRBranch hrBranch)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addHRBranch(pk, hrBranch);
	}

	/**
	* Adds an association between the h r job title and the h r branchs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r job title
	* @param hrBranchPKs the primary keys of the h r branchs
	* @throws SystemException if a system exception occurred
	*/
	public static void addHRBranchs(long pk, long[] hrBranchPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addHRBranchs(pk, hrBranchPKs);
	}

	/**
	* Adds an association between the h r job title and the h r branchs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r job title
	* @param hrBranchs the h r branchs
	* @throws SystemException if a system exception occurred
	*/
	public static void addHRBranchs(long pk,
		java.util.List<com.liferay.hr.model.HRBranch> hrBranchs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addHRBranchs(pk, hrBranchs);
	}

	/**
	* Clears all associations between the h r job title and its h r branchs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r job title to clear the associated h r branchs from
	* @throws SystemException if a system exception occurred
	*/
	public static void clearHRBranchs(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().clearHRBranchs(pk);
	}

	/**
	* Removes the association between the h r job title and the h r branch. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r job title
	* @param hrBranchPK the primary key of the h r branch
	* @throws SystemException if a system exception occurred
	*/
	public static void removeHRBranch(long pk, long hrBranchPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeHRBranch(pk, hrBranchPK);
	}

	/**
	* Removes the association between the h r job title and the h r branch. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r job title
	* @param hrBranch the h r branch
	* @throws SystemException if a system exception occurred
	*/
	public static void removeHRBranch(long pk,
		com.liferay.hr.model.HRBranch hrBranch)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeHRBranch(pk, hrBranch);
	}

	/**
	* Removes the association between the h r job title and the h r branchs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r job title
	* @param hrBranchPKs the primary keys of the h r branchs
	* @throws SystemException if a system exception occurred
	*/
	public static void removeHRBranchs(long pk, long[] hrBranchPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeHRBranchs(pk, hrBranchPKs);
	}

	/**
	* Removes the association between the h r job title and the h r branchs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r job title
	* @param hrBranchs the h r branchs
	* @throws SystemException if a system exception occurred
	*/
	public static void removeHRBranchs(long pk,
		java.util.List<com.liferay.hr.model.HRBranch> hrBranchs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeHRBranchs(pk, hrBranchs);
	}

	/**
	* Sets the h r branchs associated with the h r job title, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r job title
	* @param hrBranchPKs the primary keys of the h r branchs to be associated with the h r job title
	* @throws SystemException if a system exception occurred
	*/
	public static void setHRBranchs(long pk, long[] hrBranchPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setHRBranchs(pk, hrBranchPKs);
	}

	/**
	* Sets the h r branchs associated with the h r job title, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r job title
	* @param hrBranchs the h r branchs to be associated with the h r job title
	* @throws SystemException if a system exception occurred
	*/
	public static void setHRBranchs(long pk,
		java.util.List<com.liferay.hr.model.HRBranch> hrBranchs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setHRBranchs(pk, hrBranchs);
	}

	public static HRJobTitlePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HRJobTitlePersistence)PortletBeanLocatorUtil.locate(com.liferay.hr.service.ClpSerializer.getServletContextName(),
					HRJobTitlePersistence.class.getName());

			ReferenceRegistry.registerReference(HRJobTitleUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(HRJobTitlePersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(HRJobTitleUtil.class, "_persistence");
	}

	private static HRJobTitlePersistence _persistence;
}