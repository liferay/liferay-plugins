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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the k b folder service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.knowledgebase.service.persistence.impl.KBFolderPersistenceImpl
 * @see KBFolderUtil
 * @generated
 */
@ProviderType
public interface KBFolderPersistence extends BasePersistence<KBFolder> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KBFolderUtil} to access the k b folder persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the k b folders where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching k b folders
	*/
	public java.util.List<KBFolder> findByUuid(java.lang.String uuid);

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
	public java.util.List<KBFolder> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<KBFolder> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBFolder> orderByComparator);

	/**
	* Returns the first k b folder in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b folder
	* @throws NoSuchFolderException if a matching k b folder could not be found
	*/
	public KBFolder findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<KBFolder> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchFolderException;

	/**
	* Returns the first k b folder in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public KBFolder fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<KBFolder> orderByComparator);

	/**
	* Returns the last k b folder in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b folder
	* @throws NoSuchFolderException if a matching k b folder could not be found
	*/
	public KBFolder findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<KBFolder> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchFolderException;

	/**
	* Returns the last k b folder in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public KBFolder fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<KBFolder> orderByComparator);

	/**
	* Returns the k b folders before and after the current k b folder in the ordered set where uuid = &#63;.
	*
	* @param kbFolderId the primary key of the current k b folder
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b folder
	* @throws NoSuchFolderException if a k b folder with the primary key could not be found
	*/
	public KBFolder[] findByUuid_PrevAndNext(long kbFolderId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<KBFolder> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchFolderException;

	/**
	* Removes all the k b folders where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of k b folders where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching k b folders
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the k b folder where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchFolderException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching k b folder
	* @throws NoSuchFolderException if a matching k b folder could not be found
	*/
	public KBFolder findByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchFolderException;

	/**
	* Returns the k b folder where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public KBFolder fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the k b folder where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public KBFolder fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the k b folder where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the k b folder that was removed
	*/
	public KBFolder removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchFolderException;

	/**
	* Returns the number of k b folders where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching k b folders
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the k b folders where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching k b folders
	*/
	public java.util.List<KBFolder> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<KBFolder> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<KBFolder> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBFolder> orderByComparator);

	/**
	* Returns the first k b folder in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b folder
	* @throws NoSuchFolderException if a matching k b folder could not be found
	*/
	public KBFolder findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KBFolder> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchFolderException;

	/**
	* Returns the first k b folder in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public KBFolder fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KBFolder> orderByComparator);

	/**
	* Returns the last k b folder in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b folder
	* @throws NoSuchFolderException if a matching k b folder could not be found
	*/
	public KBFolder findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KBFolder> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchFolderException;

	/**
	* Returns the last k b folder in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public KBFolder fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KBFolder> orderByComparator);

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
	public KBFolder[] findByUuid_C_PrevAndNext(long kbFolderId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KBFolder> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchFolderException;

	/**
	* Removes all the k b folders where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of k b folders where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching k b folders
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the k b folders where groupId = &#63; and parentKBFolderId = &#63;.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @return the matching k b folders
	*/
	public java.util.List<KBFolder> findByG_P(long groupId,
		long parentKBFolderId);

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
	public java.util.List<KBFolder> findByG_P(long groupId,
		long parentKBFolderId, int start, int end);

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
	public java.util.List<KBFolder> findByG_P(long groupId,
		long parentKBFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBFolder> orderByComparator);

	/**
	* Returns the first k b folder in the ordered set where groupId = &#63; and parentKBFolderId = &#63;.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b folder
	* @throws NoSuchFolderException if a matching k b folder could not be found
	*/
	public KBFolder findByG_P_First(long groupId, long parentKBFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<KBFolder> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchFolderException;

	/**
	* Returns the first k b folder in the ordered set where groupId = &#63; and parentKBFolderId = &#63;.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public KBFolder fetchByG_P_First(long groupId, long parentKBFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<KBFolder> orderByComparator);

	/**
	* Returns the last k b folder in the ordered set where groupId = &#63; and parentKBFolderId = &#63;.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b folder
	* @throws NoSuchFolderException if a matching k b folder could not be found
	*/
	public KBFolder findByG_P_Last(long groupId, long parentKBFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<KBFolder> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchFolderException;

	/**
	* Returns the last k b folder in the ordered set where groupId = &#63; and parentKBFolderId = &#63;.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public KBFolder fetchByG_P_Last(long groupId, long parentKBFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<KBFolder> orderByComparator);

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
	public KBFolder[] findByG_P_PrevAndNext(long kbFolderId, long groupId,
		long parentKBFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<KBFolder> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchFolderException;

	/**
	* Returns all the k b folders that the user has permission to view where groupId = &#63; and parentKBFolderId = &#63;.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @return the matching k b folders that the user has permission to view
	*/
	public java.util.List<KBFolder> filterFindByG_P(long groupId,
		long parentKBFolderId);

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
	public java.util.List<KBFolder> filterFindByG_P(long groupId,
		long parentKBFolderId, int start, int end);

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
	public java.util.List<KBFolder> filterFindByG_P(long groupId,
		long parentKBFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBFolder> orderByComparator);

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
	public KBFolder[] filterFindByG_P_PrevAndNext(long kbFolderId,
		long groupId, long parentKBFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<KBFolder> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchFolderException;

	/**
	* Removes all the k b folders where groupId = &#63; and parentKBFolderId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	*/
	public void removeByG_P(long groupId, long parentKBFolderId);

	/**
	* Returns the number of k b folders where groupId = &#63; and parentKBFolderId = &#63;.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @return the number of matching k b folders
	*/
	public int countByG_P(long groupId, long parentKBFolderId);

	/**
	* Returns the number of k b folders that the user has permission to view where groupId = &#63; and parentKBFolderId = &#63;.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @return the number of matching k b folders that the user has permission to view
	*/
	public int filterCountByG_P(long groupId, long parentKBFolderId);

	/**
	* Returns the k b folder where groupId = &#63; and parentKBFolderId = &#63; and name = &#63; or throws a {@link NoSuchFolderException} if it could not be found.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param name the name
	* @return the matching k b folder
	* @throws NoSuchFolderException if a matching k b folder could not be found
	*/
	public KBFolder findByG_P_N(long groupId, long parentKBFolderId,
		java.lang.String name)
		throws com.liferay.knowledgebase.NoSuchFolderException;

	/**
	* Returns the k b folder where groupId = &#63; and parentKBFolderId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param name the name
	* @return the matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public KBFolder fetchByG_P_N(long groupId, long parentKBFolderId,
		java.lang.String name);

	/**
	* Returns the k b folder where groupId = &#63; and parentKBFolderId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param name the name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public KBFolder fetchByG_P_N(long groupId, long parentKBFolderId,
		java.lang.String name, boolean retrieveFromCache);

	/**
	* Removes the k b folder where groupId = &#63; and parentKBFolderId = &#63; and name = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param name the name
	* @return the k b folder that was removed
	*/
	public KBFolder removeByG_P_N(long groupId, long parentKBFolderId,
		java.lang.String name)
		throws com.liferay.knowledgebase.NoSuchFolderException;

	/**
	* Returns the number of k b folders where groupId = &#63; and parentKBFolderId = &#63; and name = &#63;.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param name the name
	* @return the number of matching k b folders
	*/
	public int countByG_P_N(long groupId, long parentKBFolderId,
		java.lang.String name);

	/**
	* Returns the k b folder where groupId = &#63; and parentKBFolderId = &#63; and urlTitle = &#63; or throws a {@link NoSuchFolderException} if it could not be found.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param urlTitle the url title
	* @return the matching k b folder
	* @throws NoSuchFolderException if a matching k b folder could not be found
	*/
	public KBFolder findByG_P_UT(long groupId, long parentKBFolderId,
		java.lang.String urlTitle)
		throws com.liferay.knowledgebase.NoSuchFolderException;

	/**
	* Returns the k b folder where groupId = &#63; and parentKBFolderId = &#63; and urlTitle = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param urlTitle the url title
	* @return the matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public KBFolder fetchByG_P_UT(long groupId, long parentKBFolderId,
		java.lang.String urlTitle);

	/**
	* Returns the k b folder where groupId = &#63; and parentKBFolderId = &#63; and urlTitle = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param urlTitle the url title
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public KBFolder fetchByG_P_UT(long groupId, long parentKBFolderId,
		java.lang.String urlTitle, boolean retrieveFromCache);

	/**
	* Removes the k b folder where groupId = &#63; and parentKBFolderId = &#63; and urlTitle = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param urlTitle the url title
	* @return the k b folder that was removed
	*/
	public KBFolder removeByG_P_UT(long groupId, long parentKBFolderId,
		java.lang.String urlTitle)
		throws com.liferay.knowledgebase.NoSuchFolderException;

	/**
	* Returns the number of k b folders where groupId = &#63; and parentKBFolderId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param parentKBFolderId the parent k b folder ID
	* @param urlTitle the url title
	* @return the number of matching k b folders
	*/
	public int countByG_P_UT(long groupId, long parentKBFolderId,
		java.lang.String urlTitle);

	/**
	* Caches the k b folder in the entity cache if it is enabled.
	*
	* @param kbFolder the k b folder
	*/
	public void cacheResult(KBFolder kbFolder);

	/**
	* Caches the k b folders in the entity cache if it is enabled.
	*
	* @param kbFolders the k b folders
	*/
	public void cacheResult(java.util.List<KBFolder> kbFolders);

	/**
	* Creates a new k b folder with the primary key. Does not add the k b folder to the database.
	*
	* @param kbFolderId the primary key for the new k b folder
	* @return the new k b folder
	*/
	public KBFolder create(long kbFolderId);

	/**
	* Removes the k b folder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbFolderId the primary key of the k b folder
	* @return the k b folder that was removed
	* @throws NoSuchFolderException if a k b folder with the primary key could not be found
	*/
	public KBFolder remove(long kbFolderId)
		throws com.liferay.knowledgebase.NoSuchFolderException;

	public KBFolder updateImpl(KBFolder kbFolder);

	/**
	* Returns the k b folder with the primary key or throws a {@link NoSuchFolderException} if it could not be found.
	*
	* @param kbFolderId the primary key of the k b folder
	* @return the k b folder
	* @throws NoSuchFolderException if a k b folder with the primary key could not be found
	*/
	public KBFolder findByPrimaryKey(long kbFolderId)
		throws com.liferay.knowledgebase.NoSuchFolderException;

	/**
	* Returns the k b folder with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kbFolderId the primary key of the k b folder
	* @return the k b folder, or <code>null</code> if a k b folder with the primary key could not be found
	*/
	public KBFolder fetchByPrimaryKey(long kbFolderId);

	@Override
	public java.util.Map<java.io.Serializable, KBFolder> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the k b folders.
	*
	* @return the k b folders
	*/
	public java.util.List<KBFolder> findAll();

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
	public java.util.List<KBFolder> findAll(int start, int end);

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
	public java.util.List<KBFolder> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBFolder> orderByComparator);

	/**
	* Removes all the k b folders from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of k b folders.
	*
	* @return the number of k b folders
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}