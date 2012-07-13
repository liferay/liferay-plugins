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

package com.liferay.mail.service.persistence;

import com.liferay.mail.model.Folder;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the folder service. This utility wraps {@link FolderPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FolderPersistence
 * @see FolderPersistenceImpl
 * @generated
 */
public class FolderUtil {
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
	public static void clearCache(Folder folder) {
		getPersistence().clearCache(folder);
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
	public static List<Folder> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Folder> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Folder> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static Folder update(Folder folder, boolean merge)
		throws SystemException {
		return getPersistence().update(folder, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static Folder update(Folder folder, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(folder, merge, serviceContext);
	}

	/**
	* Caches the folder in the entity cache if it is enabled.
	*
	* @param folder the folder
	*/
	public static void cacheResult(com.liferay.mail.model.Folder folder) {
		getPersistence().cacheResult(folder);
	}

	/**
	* Caches the folders in the entity cache if it is enabled.
	*
	* @param folders the folders
	*/
	public static void cacheResult(
		java.util.List<com.liferay.mail.model.Folder> folders) {
		getPersistence().cacheResult(folders);
	}

	/**
	* Creates a new folder with the primary key. Does not add the folder to the database.
	*
	* @param folderId the primary key for the new folder
	* @return the new folder
	*/
	public static com.liferay.mail.model.Folder create(long folderId) {
		return getPersistence().create(folderId);
	}

	/**
	* Removes the folder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param folderId the primary key of the folder
	* @return the folder that was removed
	* @throws com.liferay.mail.NoSuchFolderException if a folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Folder remove(long folderId)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(folderId);
	}

	public static com.liferay.mail.model.Folder updateImpl(
		com.liferay.mail.model.Folder folder, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(folder, merge);
	}

	/**
	* Returns the folder with the primary key or throws a {@link com.liferay.mail.NoSuchFolderException} if it could not be found.
	*
	* @param folderId the primary key of the folder
	* @return the folder
	* @throws com.liferay.mail.NoSuchFolderException if a folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Folder findByPrimaryKey(long folderId)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(folderId);
	}

	/**
	* Returns the folder with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param folderId the primary key of the folder
	* @return the folder, or <code>null</code> if a folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Folder fetchByPrimaryKey(long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(folderId);
	}

	/**
	* Returns all the folders where accountId = &#63;.
	*
	* @param accountId the account ID
	* @return the matching folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.mail.model.Folder> findByAccountId(
		long accountId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAccountId(accountId);
	}

	/**
	* Returns a range of all the folders where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of folders
	* @param end the upper bound of the range of folders (not inclusive)
	* @return the range of matching folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.mail.model.Folder> findByAccountId(
		long accountId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAccountId(accountId, start, end);
	}

	/**
	* Returns an ordered range of all the folders where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of folders
	* @param end the upper bound of the range of folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.mail.model.Folder> findByAccountId(
		long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountId(accountId, start, end, orderByComparator);
	}

	/**
	* Returns the first folder in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching folder
	* @throws com.liferay.mail.NoSuchFolderException if a matching folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Folder findByAccountId_First(
		long accountId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountId_First(accountId, orderByComparator);
	}

	/**
	* Returns the first folder in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching folder, or <code>null</code> if a matching folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Folder fetchByAccountId_First(
		long accountId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAccountId_First(accountId, orderByComparator);
	}

	/**
	* Returns the last folder in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching folder
	* @throws com.liferay.mail.NoSuchFolderException if a matching folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Folder findByAccountId_Last(
		long accountId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountId_Last(accountId, orderByComparator);
	}

	/**
	* Returns the last folder in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching folder, or <code>null</code> if a matching folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Folder fetchByAccountId_Last(
		long accountId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAccountId_Last(accountId, orderByComparator);
	}

	/**
	* Returns the folders before and after the current folder in the ordered set where accountId = &#63;.
	*
	* @param folderId the primary key of the current folder
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next folder
	* @throws com.liferay.mail.NoSuchFolderException if a folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Folder[] findByAccountId_PrevAndNext(
		long folderId, long accountId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountId_PrevAndNext(folderId, accountId,
			orderByComparator);
	}

	/**
	* Returns the folder where accountId = &#63; and fullName = &#63; or throws a {@link com.liferay.mail.NoSuchFolderException} if it could not be found.
	*
	* @param accountId the account ID
	* @param fullName the full name
	* @return the matching folder
	* @throws com.liferay.mail.NoSuchFolderException if a matching folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Folder findByA_F(long accountId,
		java.lang.String fullName)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByA_F(accountId, fullName);
	}

	/**
	* Returns the folder where accountId = &#63; and fullName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param accountId the account ID
	* @param fullName the full name
	* @return the matching folder, or <code>null</code> if a matching folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Folder fetchByA_F(long accountId,
		java.lang.String fullName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByA_F(accountId, fullName);
	}

	/**
	* Returns the folder where accountId = &#63; and fullName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param accountId the account ID
	* @param fullName the full name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching folder, or <code>null</code> if a matching folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Folder fetchByA_F(long accountId,
		java.lang.String fullName, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByA_F(accountId, fullName, retrieveFromCache);
	}

	/**
	* Returns all the folders.
	*
	* @return the folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.mail.model.Folder> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of folders
	* @param end the upper bound of the range of folders (not inclusive)
	* @return the range of folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.mail.model.Folder> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of folders
	* @param end the upper bound of the range of folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of folders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.mail.model.Folder> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the folders where accountId = &#63; from the database.
	*
	* @param accountId the account ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAccountId(long accountId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAccountId(accountId);
	}

	/**
	* Removes the folder where accountId = &#63; and fullName = &#63; from the database.
	*
	* @param accountId the account ID
	* @param fullName the full name
	* @return the folder that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Folder removeByA_F(long accountId,
		java.lang.String fullName)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByA_F(accountId, fullName);
	}

	/**
	* Removes all the folders from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of folders where accountId = &#63;.
	*
	* @param accountId the account ID
	* @return the number of matching folders
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAccountId(long accountId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAccountId(accountId);
	}

	/**
	* Returns the number of folders where accountId = &#63; and fullName = &#63;.
	*
	* @param accountId the account ID
	* @param fullName the full name
	* @return the number of matching folders
	* @throws SystemException if a system exception occurred
	*/
	public static int countByA_F(long accountId, java.lang.String fullName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByA_F(accountId, fullName);
	}

	/**
	* Returns the number of folders.
	*
	* @return the number of folders
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static FolderPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (FolderPersistence)PortletBeanLocatorUtil.locate(com.liferay.mail.service.ClpSerializer.getServletContextName(),
					FolderPersistence.class.getName());

			ReferenceRegistry.registerReference(FolderUtil.class, "_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(FolderPersistence persistence) {
	}

	private static FolderPersistence _persistence;
}