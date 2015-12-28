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

package com.liferay.mail.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.mail.model.Folder;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the folder service. This utility wraps {@link com.liferay.mail.service.persistence.impl.FolderPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FolderPersistence
 * @see com.liferay.mail.service.persistence.impl.FolderPersistenceImpl
 * @generated
 */
@ProviderType
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
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Folder> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Folder> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Folder> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Folder> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static Folder update(Folder folder) {
		return getPersistence().update(folder);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static Folder update(Folder folder, ServiceContext serviceContext) {
		return getPersistence().update(folder, serviceContext);
	}

	/**
	* Returns all the folders where accountId = &#63;.
	*
	* @param accountId the account ID
	* @return the matching folders
	*/
	public static List<Folder> findByAccountId(long accountId) {
		return getPersistence().findByAccountId(accountId);
	}

	/**
	* Returns a range of all the folders where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of folders
	* @param end the upper bound of the range of folders (not inclusive)
	* @return the range of matching folders
	*/
	public static List<Folder> findByAccountId(long accountId, int start,
		int end) {
		return getPersistence().findByAccountId(accountId, start, end);
	}

	/**
	* Returns an ordered range of all the folders where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of folders
	* @param end the upper bound of the range of folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching folders
	*/
	public static List<Folder> findByAccountId(long accountId, int start,
		int end, OrderByComparator<Folder> orderByComparator) {
		return getPersistence()
				   .findByAccountId(accountId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the folders where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of folders
	* @param end the upper bound of the range of folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching folders
	*/
	public static List<Folder> findByAccountId(long accountId, int start,
		int end, OrderByComparator<Folder> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAccountId(accountId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first folder in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching folder
	* @throws NoSuchFolderException if a matching folder could not be found
	*/
	public static Folder findByAccountId_First(long accountId,
		OrderByComparator<Folder> orderByComparator)
		throws com.liferay.mail.NoSuchFolderException {
		return getPersistence()
				   .findByAccountId_First(accountId, orderByComparator);
	}

	/**
	* Returns the first folder in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching folder, or <code>null</code> if a matching folder could not be found
	*/
	public static Folder fetchByAccountId_First(long accountId,
		OrderByComparator<Folder> orderByComparator) {
		return getPersistence()
				   .fetchByAccountId_First(accountId, orderByComparator);
	}

	/**
	* Returns the last folder in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching folder
	* @throws NoSuchFolderException if a matching folder could not be found
	*/
	public static Folder findByAccountId_Last(long accountId,
		OrderByComparator<Folder> orderByComparator)
		throws com.liferay.mail.NoSuchFolderException {
		return getPersistence()
				   .findByAccountId_Last(accountId, orderByComparator);
	}

	/**
	* Returns the last folder in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching folder, or <code>null</code> if a matching folder could not be found
	*/
	public static Folder fetchByAccountId_Last(long accountId,
		OrderByComparator<Folder> orderByComparator) {
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
	* @throws NoSuchFolderException if a folder with the primary key could not be found
	*/
	public static Folder[] findByAccountId_PrevAndNext(long folderId,
		long accountId, OrderByComparator<Folder> orderByComparator)
		throws com.liferay.mail.NoSuchFolderException {
		return getPersistence()
				   .findByAccountId_PrevAndNext(folderId, accountId,
			orderByComparator);
	}

	/**
	* Removes all the folders where accountId = &#63; from the database.
	*
	* @param accountId the account ID
	*/
	public static void removeByAccountId(long accountId) {
		getPersistence().removeByAccountId(accountId);
	}

	/**
	* Returns the number of folders where accountId = &#63;.
	*
	* @param accountId the account ID
	* @return the number of matching folders
	*/
	public static int countByAccountId(long accountId) {
		return getPersistence().countByAccountId(accountId);
	}

	/**
	* Returns the folder where accountId = &#63; and fullName = &#63; or throws a {@link NoSuchFolderException} if it could not be found.
	*
	* @param accountId the account ID
	* @param fullName the full name
	* @return the matching folder
	* @throws NoSuchFolderException if a matching folder could not be found
	*/
	public static Folder findByA_F(long accountId, java.lang.String fullName)
		throws com.liferay.mail.NoSuchFolderException {
		return getPersistence().findByA_F(accountId, fullName);
	}

	/**
	* Returns the folder where accountId = &#63; and fullName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param accountId the account ID
	* @param fullName the full name
	* @return the matching folder, or <code>null</code> if a matching folder could not be found
	*/
	public static Folder fetchByA_F(long accountId, java.lang.String fullName) {
		return getPersistence().fetchByA_F(accountId, fullName);
	}

	/**
	* Returns the folder where accountId = &#63; and fullName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param accountId the account ID
	* @param fullName the full name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching folder, or <code>null</code> if a matching folder could not be found
	*/
	public static Folder fetchByA_F(long accountId, java.lang.String fullName,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByA_F(accountId, fullName, retrieveFromCache);
	}

	/**
	* Removes the folder where accountId = &#63; and fullName = &#63; from the database.
	*
	* @param accountId the account ID
	* @param fullName the full name
	* @return the folder that was removed
	*/
	public static Folder removeByA_F(long accountId, java.lang.String fullName)
		throws com.liferay.mail.NoSuchFolderException {
		return getPersistence().removeByA_F(accountId, fullName);
	}

	/**
	* Returns the number of folders where accountId = &#63; and fullName = &#63;.
	*
	* @param accountId the account ID
	* @param fullName the full name
	* @return the number of matching folders
	*/
	public static int countByA_F(long accountId, java.lang.String fullName) {
		return getPersistence().countByA_F(accountId, fullName);
	}

	/**
	* Caches the folder in the entity cache if it is enabled.
	*
	* @param folder the folder
	*/
	public static void cacheResult(Folder folder) {
		getPersistence().cacheResult(folder);
	}

	/**
	* Caches the folders in the entity cache if it is enabled.
	*
	* @param folders the folders
	*/
	public static void cacheResult(List<Folder> folders) {
		getPersistence().cacheResult(folders);
	}

	/**
	* Creates a new folder with the primary key. Does not add the folder to the database.
	*
	* @param folderId the primary key for the new folder
	* @return the new folder
	*/
	public static Folder create(long folderId) {
		return getPersistence().create(folderId);
	}

	/**
	* Removes the folder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param folderId the primary key of the folder
	* @return the folder that was removed
	* @throws NoSuchFolderException if a folder with the primary key could not be found
	*/
	public static Folder remove(long folderId)
		throws com.liferay.mail.NoSuchFolderException {
		return getPersistence().remove(folderId);
	}

	public static Folder updateImpl(Folder folder) {
		return getPersistence().updateImpl(folder);
	}

	/**
	* Returns the folder with the primary key or throws a {@link NoSuchFolderException} if it could not be found.
	*
	* @param folderId the primary key of the folder
	* @return the folder
	* @throws NoSuchFolderException if a folder with the primary key could not be found
	*/
	public static Folder findByPrimaryKey(long folderId)
		throws com.liferay.mail.NoSuchFolderException {
		return getPersistence().findByPrimaryKey(folderId);
	}

	/**
	* Returns the folder with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param folderId the primary key of the folder
	* @return the folder, or <code>null</code> if a folder with the primary key could not be found
	*/
	public static Folder fetchByPrimaryKey(long folderId) {
		return getPersistence().fetchByPrimaryKey(folderId);
	}

	public static java.util.Map<java.io.Serializable, Folder> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the folders.
	*
	* @return the folders
	*/
	public static List<Folder> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of folders
	* @param end the upper bound of the range of folders (not inclusive)
	* @return the range of folders
	*/
	public static List<Folder> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of folders
	* @param end the upper bound of the range of folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of folders
	*/
	public static List<Folder> findAll(int start, int end,
		OrderByComparator<Folder> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of folders
	* @param end the upper bound of the range of folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of folders
	*/
	public static List<Folder> findAll(int start, int end,
		OrderByComparator<Folder> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the folders from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of folders.
	*
	* @return the number of folders
	*/
	public static int countAll() {
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

	private static FolderPersistence _persistence;
}