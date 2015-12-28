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

package com.liferay.knowledgebase.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.knowledgebase.model.KBFolder;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the k b folder service. This utility wraps {@link com.liferay.knowledgebase.service.persistence.impl.KBFolderPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBFolderPersistence
 * @see com.liferay.knowledgebase.service.persistence.impl.KBFolderPersistenceImpl
 * @generated
 */
@ProviderType
public class KBFolderUtil {
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
	public static void clearCache(KBFolder kbFolder) {
		getPersistence().clearCache(kbFolder);
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
	public static List<KBFolder> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KBFolder> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KBFolder> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<KBFolder> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static KBFolder update(KBFolder kbFolder) {
		return getPersistence().update(kbFolder);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static KBFolder update(KBFolder kbFolder,
		ServiceContext serviceContext) {
		return getPersistence().update(kbFolder, serviceContext);
	}

	/**
	* Returns all the k b folders where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching k b folders
	*/
	public static List<KBFolder> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the k b folders where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of k b folders
	* @param end the upper bound of the range of k b folders (not inclusive)
	* @return the range of matching k b folders
	*/
	public static List<KBFolder> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the k b folders where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of k b folders
	* @param end the upper bound of the range of k b folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b folders
	*/
	public static List<KBFolder> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<KBFolder> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the k b folders where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of k b folders
	* @param end the upper bound of the range of k b folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching k b folders
	*/
	public static List<KBFolder> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<KBFolder> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first k b folder in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b folder
	* @throws NoSuchFolderException if a matching k b folder could not be found
	*/
	public static KBFolder findByUuid_First(java.lang.String uuid,
		OrderByComparator<KBFolder> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchFolderException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first k b folder in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public static KBFolder fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<KBFolder> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last k b folder in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b folder
	* @throws NoSuchFolderException if a matching k b folder could not be found
	*/
	public static KBFolder findByUuid_Last(java.lang.String uuid,
		OrderByComparator<KBFolder> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchFolderException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last k b folder in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public static KBFolder fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<KBFolder> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the k b folders before and after the current k b folder in the ordered set where uuid = &#63;.
	*
	* @param kbFolderId the primary key of the current k b folder
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b folder
	* @throws NoSuchFolderException if a k b folder with the primary key could not be found
	*/
	public static KBFolder[] findByUuid_PrevAndNext(long kbFolderId,
		java.lang.String uuid, OrderByComparator<KBFolder> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchFolderException {
		return getPersistence()
				   .findByUuid_PrevAndNext(kbFolderId, uuid, orderByComparator);
	}

	/**
	* Removes all the k b folders where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of k b folders where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching k b folders
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the k b folder where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchFolderException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching k b folder
	* @throws NoSuchFolderException if a matching k b folder could not be found
	*/
	public static KBFolder findByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchFolderException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the k b folder where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public static KBFolder fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the k b folder where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public static KBFolder fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the k b folder where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the k b folder that was removed
	*/
	public static KBFolder removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchFolderException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of k b folders where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching k b folders
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the k b folders where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching k b folders
	*/
	public static List<KBFolder> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the k b folders where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of k b folders
	* @param end the upper bound of the range of k b folders (not inclusive)
	* @return the range of matching k b folders
	*/
	public static List<KBFolder> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the k b folders where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of k b folders
	* @param end the upper bound of the range of k b folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b folders
	*/
	public static List<KBFolder> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<KBFolder> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the k b folders where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of k b folders
	* @param end the upper bound of the range of k b folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching k b folders
	*/
	public static List<KBFolder> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<KBFolder> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first k b folder in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b folder
	* @throws NoSuchFolderException if a matching k b folder could not be found
	*/
	public static KBFolder findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<KBFolder> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchFolderException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first k b folder in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public static KBFolder fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<KBFolder> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last k b folder in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b folder
	* @throws NoSuchFolderException if a matching k b folder could not be found
	*/
	public static KBFolder findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<KBFolder> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchFolderException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last k b folder in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public static KBFolder fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<KBFolder> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the k b folders before and after the current k b folder in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param kbFolderId the primary key of the current k b folder
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b folder
	* @throws NoSuchFolderException if a k b folder with the primary key could not be found
	*/
	public static KBFolder[] findByUuid_C_PrevAndNext(long kbFolderId,
		java.lang.String uuid, long companyId,
		OrderByComparator<KBFolder> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchFolderException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(kbFolderId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the k b folders where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of k b folders where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching k b folders
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the k b folders where groupId = &#63; and parentKBFolderId = &#63;.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @return the matching k b folders
	*/
	public static List<KBFolder> findByG_P(long groupId, long parentKBFolderId) {
		return getPersistence().findByG_P(groupId, parentKBFolderId);
	}

	/**
	* Returns a range of all the k b folders where groupId = &#63; and parentKBFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param start the lower bound of the range of k b folders
	* @param end the upper bound of the range of k b folders (not inclusive)
	* @return the range of matching k b folders
	*/
	public static List<KBFolder> findByG_P(long groupId, long parentKBFolderId,
		int start, int end) {
		return getPersistence().findByG_P(groupId, parentKBFolderId, start, end);
	}

	/**
	* Returns an ordered range of all the k b folders where groupId = &#63; and parentKBFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param start the lower bound of the range of k b folders
	* @param end the upper bound of the range of k b folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b folders
	*/
	public static List<KBFolder> findByG_P(long groupId, long parentKBFolderId,
		int start, int end, OrderByComparator<KBFolder> orderByComparator) {
		return getPersistence()
				   .findByG_P(groupId, parentKBFolderId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the k b folders where groupId = &#63; and parentKBFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param start the lower bound of the range of k b folders
	* @param end the upper bound of the range of k b folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching k b folders
	*/
	public static List<KBFolder> findByG_P(long groupId, long parentKBFolderId,
		int start, int end, OrderByComparator<KBFolder> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_P(groupId, parentKBFolderId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first k b folder in the ordered set where groupId = &#63; and parentKBFolderId = &#63;.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b folder
	* @throws NoSuchFolderException if a matching k b folder could not be found
	*/
	public static KBFolder findByG_P_First(long groupId, long parentKBFolderId,
		OrderByComparator<KBFolder> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchFolderException {
		return getPersistence()
				   .findByG_P_First(groupId, parentKBFolderId, orderByComparator);
	}

	/**
	* Returns the first k b folder in the ordered set where groupId = &#63; and parentKBFolderId = &#63;.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public static KBFolder fetchByG_P_First(long groupId,
		long parentKBFolderId, OrderByComparator<KBFolder> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_First(groupId, parentKBFolderId,
			orderByComparator);
	}

	/**
	* Returns the last k b folder in the ordered set where groupId = &#63; and parentKBFolderId = &#63;.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b folder
	* @throws NoSuchFolderException if a matching k b folder could not be found
	*/
	public static KBFolder findByG_P_Last(long groupId, long parentKBFolderId,
		OrderByComparator<KBFolder> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchFolderException {
		return getPersistence()
				   .findByG_P_Last(groupId, parentKBFolderId, orderByComparator);
	}

	/**
	* Returns the last k b folder in the ordered set where groupId = &#63; and parentKBFolderId = &#63;.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public static KBFolder fetchByG_P_Last(long groupId, long parentKBFolderId,
		OrderByComparator<KBFolder> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_Last(groupId, parentKBFolderId, orderByComparator);
	}

	/**
	* Returns the k b folders before and after the current k b folder in the ordered set where groupId = &#63; and parentKBFolderId = &#63;.
	*
	* @param kbFolderId the primary key of the current k b folder
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b folder
	* @throws NoSuchFolderException if a k b folder with the primary key could not be found
	*/
	public static KBFolder[] findByG_P_PrevAndNext(long kbFolderId,
		long groupId, long parentKBFolderId,
		OrderByComparator<KBFolder> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchFolderException {
		return getPersistence()
				   .findByG_P_PrevAndNext(kbFolderId, groupId,
			parentKBFolderId, orderByComparator);
	}

	/**
	* Returns all the k b folders that the user has permission to view where groupId = &#63; and parentKBFolderId = &#63;.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @return the matching k b folders that the user has permission to view
	*/
	public static List<KBFolder> filterFindByG_P(long groupId,
		long parentKBFolderId) {
		return getPersistence().filterFindByG_P(groupId, parentKBFolderId);
	}

	/**
	* Returns a range of all the k b folders that the user has permission to view where groupId = &#63; and parentKBFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param start the lower bound of the range of k b folders
	* @param end the upper bound of the range of k b folders (not inclusive)
	* @return the range of matching k b folders that the user has permission to view
	*/
	public static List<KBFolder> filterFindByG_P(long groupId,
		long parentKBFolderId, int start, int end) {
		return getPersistence()
				   .filterFindByG_P(groupId, parentKBFolderId, start, end);
	}

	/**
	* Returns an ordered range of all the k b folders that the user has permissions to view where groupId = &#63; and parentKBFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param start the lower bound of the range of k b folders
	* @param end the upper bound of the range of k b folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b folders that the user has permission to view
	*/
	public static List<KBFolder> filterFindByG_P(long groupId,
		long parentKBFolderId, int start, int end,
		OrderByComparator<KBFolder> orderByComparator) {
		return getPersistence()
				   .filterFindByG_P(groupId, parentKBFolderId, start, end,
			orderByComparator);
	}

	/**
	* Returns the k b folders before and after the current k b folder in the ordered set of k b folders that the user has permission to view where groupId = &#63; and parentKBFolderId = &#63;.
	*
	* @param kbFolderId the primary key of the current k b folder
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b folder
	* @throws NoSuchFolderException if a k b folder with the primary key could not be found
	*/
	public static KBFolder[] filterFindByG_P_PrevAndNext(long kbFolderId,
		long groupId, long parentKBFolderId,
		OrderByComparator<KBFolder> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchFolderException {
		return getPersistence()
				   .filterFindByG_P_PrevAndNext(kbFolderId, groupId,
			parentKBFolderId, orderByComparator);
	}

	/**
	* Removes all the k b folders where groupId = &#63; and parentKBFolderId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	*/
	public static void removeByG_P(long groupId, long parentKBFolderId) {
		getPersistence().removeByG_P(groupId, parentKBFolderId);
	}

	/**
	* Returns the number of k b folders where groupId = &#63; and parentKBFolderId = &#63;.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @return the number of matching k b folders
	*/
	public static int countByG_P(long groupId, long parentKBFolderId) {
		return getPersistence().countByG_P(groupId, parentKBFolderId);
	}

	/**
	* Returns the number of k b folders that the user has permission to view where groupId = &#63; and parentKBFolderId = &#63;.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @return the number of matching k b folders that the user has permission to view
	*/
	public static int filterCountByG_P(long groupId, long parentKBFolderId) {
		return getPersistence().filterCountByG_P(groupId, parentKBFolderId);
	}

	/**
	* Returns the k b folder where groupId = &#63; and parentKBFolderId = &#63; and name = &#63; or throws a {@link NoSuchFolderException} if it could not be found.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param name the name
	* @return the matching k b folder
	* @throws NoSuchFolderException if a matching k b folder could not be found
	*/
	public static KBFolder findByG_P_N(long groupId, long parentKBFolderId,
		java.lang.String name)
		throws com.liferay.knowledgebase.NoSuchFolderException {
		return getPersistence().findByG_P_N(groupId, parentKBFolderId, name);
	}

	/**
	* Returns the k b folder where groupId = &#63; and parentKBFolderId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param name the name
	* @return the matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public static KBFolder fetchByG_P_N(long groupId, long parentKBFolderId,
		java.lang.String name) {
		return getPersistence().fetchByG_P_N(groupId, parentKBFolderId, name);
	}

	/**
	* Returns the k b folder where groupId = &#63; and parentKBFolderId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public static KBFolder fetchByG_P_N(long groupId, long parentKBFolderId,
		java.lang.String name, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_P_N(groupId, parentKBFolderId, name,
			retrieveFromCache);
	}

	/**
	* Removes the k b folder where groupId = &#63; and parentKBFolderId = &#63; and name = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param name the name
	* @return the k b folder that was removed
	*/
	public static KBFolder removeByG_P_N(long groupId, long parentKBFolderId,
		java.lang.String name)
		throws com.liferay.knowledgebase.NoSuchFolderException {
		return getPersistence().removeByG_P_N(groupId, parentKBFolderId, name);
	}

	/**
	* Returns the number of k b folders where groupId = &#63; and parentKBFolderId = &#63; and name = &#63;.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param name the name
	* @return the number of matching k b folders
	*/
	public static int countByG_P_N(long groupId, long parentKBFolderId,
		java.lang.String name) {
		return getPersistence().countByG_P_N(groupId, parentKBFolderId, name);
	}

	/**
	* Returns the k b folder where groupId = &#63; and parentKBFolderId = &#63; and urlTitle = &#63; or throws a {@link NoSuchFolderException} if it could not be found.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param urlTitle the url title
	* @return the matching k b folder
	* @throws NoSuchFolderException if a matching k b folder could not be found
	*/
	public static KBFolder findByG_P_UT(long groupId, long parentKBFolderId,
		java.lang.String urlTitle)
		throws com.liferay.knowledgebase.NoSuchFolderException {
		return getPersistence().findByG_P_UT(groupId, parentKBFolderId, urlTitle);
	}

	/**
	* Returns the k b folder where groupId = &#63; and parentKBFolderId = &#63; and urlTitle = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param urlTitle the url title
	* @return the matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public static KBFolder fetchByG_P_UT(long groupId, long parentKBFolderId,
		java.lang.String urlTitle) {
		return getPersistence()
				   .fetchByG_P_UT(groupId, parentKBFolderId, urlTitle);
	}

	/**
	* Returns the k b folder where groupId = &#63; and parentKBFolderId = &#63; and urlTitle = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param urlTitle the url title
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public static KBFolder fetchByG_P_UT(long groupId, long parentKBFolderId,
		java.lang.String urlTitle, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_P_UT(groupId, parentKBFolderId, urlTitle,
			retrieveFromCache);
	}

	/**
	* Removes the k b folder where groupId = &#63; and parentKBFolderId = &#63; and urlTitle = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param urlTitle the url title
	* @return the k b folder that was removed
	*/
	public static KBFolder removeByG_P_UT(long groupId, long parentKBFolderId,
		java.lang.String urlTitle)
		throws com.liferay.knowledgebase.NoSuchFolderException {
		return getPersistence()
				   .removeByG_P_UT(groupId, parentKBFolderId, urlTitle);
	}

	/**
	* Returns the number of k b folders where groupId = &#63; and parentKBFolderId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param urlTitle the url title
	* @return the number of matching k b folders
	*/
	public static int countByG_P_UT(long groupId, long parentKBFolderId,
		java.lang.String urlTitle) {
		return getPersistence()
				   .countByG_P_UT(groupId, parentKBFolderId, urlTitle);
	}

	/**
	* Caches the k b folder in the entity cache if it is enabled.
	*
	* @param kbFolder the k b folder
	*/
	public static void cacheResult(KBFolder kbFolder) {
		getPersistence().cacheResult(kbFolder);
	}

	/**
	* Caches the k b folders in the entity cache if it is enabled.
	*
	* @param kbFolders the k b folders
	*/
	public static void cacheResult(List<KBFolder> kbFolders) {
		getPersistence().cacheResult(kbFolders);
	}

	/**
	* Creates a new k b folder with the primary key. Does not add the k b folder to the database.
	*
	* @param kbFolderId the primary key for the new k b folder
	* @return the new k b folder
	*/
	public static KBFolder create(long kbFolderId) {
		return getPersistence().create(kbFolderId);
	}

	/**
	* Removes the k b folder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbFolderId the primary key of the k b folder
	* @return the k b folder that was removed
	* @throws NoSuchFolderException if a k b folder with the primary key could not be found
	*/
	public static KBFolder remove(long kbFolderId)
		throws com.liferay.knowledgebase.NoSuchFolderException {
		return getPersistence().remove(kbFolderId);
	}

	public static KBFolder updateImpl(KBFolder kbFolder) {
		return getPersistence().updateImpl(kbFolder);
	}

	/**
	* Returns the k b folder with the primary key or throws a {@link NoSuchFolderException} if it could not be found.
	*
	* @param kbFolderId the primary key of the k b folder
	* @return the k b folder
	* @throws NoSuchFolderException if a k b folder with the primary key could not be found
	*/
	public static KBFolder findByPrimaryKey(long kbFolderId)
		throws com.liferay.knowledgebase.NoSuchFolderException {
		return getPersistence().findByPrimaryKey(kbFolderId);
	}

	/**
	* Returns the k b folder with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kbFolderId the primary key of the k b folder
	* @return the k b folder, or <code>null</code> if a k b folder with the primary key could not be found
	*/
	public static KBFolder fetchByPrimaryKey(long kbFolderId) {
		return getPersistence().fetchByPrimaryKey(kbFolderId);
	}

	public static java.util.Map<java.io.Serializable, KBFolder> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the k b folders.
	*
	* @return the k b folders
	*/
	public static List<KBFolder> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the k b folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of k b folders
	* @param end the upper bound of the range of k b folders (not inclusive)
	* @return the range of k b folders
	*/
	public static List<KBFolder> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the k b folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of k b folders
	* @param end the upper bound of the range of k b folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of k b folders
	*/
	public static List<KBFolder> findAll(int start, int end,
		OrderByComparator<KBFolder> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the k b folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of k b folders
	* @param end the upper bound of the range of k b folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of k b folders
	*/
	public static List<KBFolder> findAll(int start, int end,
		OrderByComparator<KBFolder> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the k b folders from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of k b folders.
	*
	* @return the number of k b folders
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static KBFolderPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KBFolderPersistence)PortletBeanLocatorUtil.locate(com.liferay.knowledgebase.service.ClpSerializer.getServletContextName(),
					KBFolderPersistence.class.getName());

			ReferenceRegistry.registerReference(KBFolderUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static KBFolderPersistence _persistence;
}