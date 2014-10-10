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

package com.liferay.asset.sharing.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.sharing.model.AssetSharingEntry;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the asset sharing entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetSharingEntryPersistenceImpl
 * @see AssetSharingEntryUtil
 * @generated
 */
@ProviderType
public interface AssetSharingEntryPersistence extends BasePersistence<AssetSharingEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetSharingEntryUtil} to access the asset sharing entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the asset sharing entries where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching asset sharing entries
	*/
	public java.util.List<com.liferay.asset.sharing.model.AssetSharingEntry> findByC_C(
		long classNameId, long classPK);

	/**
	* Returns a range of all the asset sharing entries where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.sharing.model.impl.AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of asset sharing entries
	* @param end the upper bound of the range of asset sharing entries (not inclusive)
	* @return the range of matching asset sharing entries
	*/
	public java.util.List<com.liferay.asset.sharing.model.AssetSharingEntry> findByC_C(
		long classNameId, long classPK, int start, int end);

	/**
	* Returns an ordered range of all the asset sharing entries where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.sharing.model.impl.AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of asset sharing entries
	* @param end the upper bound of the range of asset sharing entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset sharing entries
	*/
	public java.util.List<com.liferay.asset.sharing.model.AssetSharingEntry> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator);

	/**
	* Returns the first asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset sharing entry
	* @throws com.liferay.asset.sharing.NoSuchEntryException if a matching asset sharing entry could not be found
	*/
	public com.liferay.asset.sharing.model.AssetSharingEntry findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator)
		throws com.liferay.asset.sharing.NoSuchEntryException;

	/**
	* Returns the first asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset sharing entry, or <code>null</code> if a matching asset sharing entry could not be found
	*/
	public com.liferay.asset.sharing.model.AssetSharingEntry fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator);

	/**
	* Returns the last asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset sharing entry
	* @throws com.liferay.asset.sharing.NoSuchEntryException if a matching asset sharing entry could not be found
	*/
	public com.liferay.asset.sharing.model.AssetSharingEntry findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator)
		throws com.liferay.asset.sharing.NoSuchEntryException;

	/**
	* Returns the last asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset sharing entry, or <code>null</code> if a matching asset sharing entry could not be found
	*/
	public com.liferay.asset.sharing.model.AssetSharingEntry fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator);

	/**
	* Returns the asset sharing entries before and after the current asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param assetSharingEntryPK the primary key of the current asset sharing entry
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset sharing entry
	* @throws com.liferay.asset.sharing.NoSuchEntryException if a asset sharing entry with the primary key could not be found
	*/
	public com.liferay.asset.sharing.model.AssetSharingEntry[] findByC_C_PrevAndNext(
		com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK assetSharingEntryPK,
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator)
		throws com.liferay.asset.sharing.NoSuchEntryException;

	/**
	* Removes all the asset sharing entries where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	*/
	public void removeByC_C(long classNameId, long classPK);

	/**
	* Returns the number of asset sharing entries where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching asset sharing entries
	*/
	public int countByC_C(long classNameId, long classPK);

	/**
	* Returns all the asset sharing entries where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	*
	* @param sharedToClassNameId the shared to class name ID
	* @param sharedToClassPK the shared to class p k
	* @return the matching asset sharing entries
	*/
	public java.util.List<com.liferay.asset.sharing.model.AssetSharingEntry> findByS_S(
		long sharedToClassNameId, long sharedToClassPK);

	/**
	* Returns a range of all the asset sharing entries where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.sharing.model.impl.AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sharedToClassNameId the shared to class name ID
	* @param sharedToClassPK the shared to class p k
	* @param start the lower bound of the range of asset sharing entries
	* @param end the upper bound of the range of asset sharing entries (not inclusive)
	* @return the range of matching asset sharing entries
	*/
	public java.util.List<com.liferay.asset.sharing.model.AssetSharingEntry> findByS_S(
		long sharedToClassNameId, long sharedToClassPK, int start, int end);

	/**
	* Returns an ordered range of all the asset sharing entries where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.sharing.model.impl.AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sharedToClassNameId the shared to class name ID
	* @param sharedToClassPK the shared to class p k
	* @param start the lower bound of the range of asset sharing entries
	* @param end the upper bound of the range of asset sharing entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset sharing entries
	*/
	public java.util.List<com.liferay.asset.sharing.model.AssetSharingEntry> findByS_S(
		long sharedToClassNameId, long sharedToClassPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator);

	/**
	* Returns the first asset sharing entry in the ordered set where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	*
	* @param sharedToClassNameId the shared to class name ID
	* @param sharedToClassPK the shared to class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset sharing entry
	* @throws com.liferay.asset.sharing.NoSuchEntryException if a matching asset sharing entry could not be found
	*/
	public com.liferay.asset.sharing.model.AssetSharingEntry findByS_S_First(
		long sharedToClassNameId, long sharedToClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator)
		throws com.liferay.asset.sharing.NoSuchEntryException;

	/**
	* Returns the first asset sharing entry in the ordered set where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	*
	* @param sharedToClassNameId the shared to class name ID
	* @param sharedToClassPK the shared to class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset sharing entry, or <code>null</code> if a matching asset sharing entry could not be found
	*/
	public com.liferay.asset.sharing.model.AssetSharingEntry fetchByS_S_First(
		long sharedToClassNameId, long sharedToClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator);

	/**
	* Returns the last asset sharing entry in the ordered set where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	*
	* @param sharedToClassNameId the shared to class name ID
	* @param sharedToClassPK the shared to class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset sharing entry
	* @throws com.liferay.asset.sharing.NoSuchEntryException if a matching asset sharing entry could not be found
	*/
	public com.liferay.asset.sharing.model.AssetSharingEntry findByS_S_Last(
		long sharedToClassNameId, long sharedToClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator)
		throws com.liferay.asset.sharing.NoSuchEntryException;

	/**
	* Returns the last asset sharing entry in the ordered set where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	*
	* @param sharedToClassNameId the shared to class name ID
	* @param sharedToClassPK the shared to class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset sharing entry, or <code>null</code> if a matching asset sharing entry could not be found
	*/
	public com.liferay.asset.sharing.model.AssetSharingEntry fetchByS_S_Last(
		long sharedToClassNameId, long sharedToClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator);

	/**
	* Returns the asset sharing entries before and after the current asset sharing entry in the ordered set where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	*
	* @param assetSharingEntryPK the primary key of the current asset sharing entry
	* @param sharedToClassNameId the shared to class name ID
	* @param sharedToClassPK the shared to class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset sharing entry
	* @throws com.liferay.asset.sharing.NoSuchEntryException if a asset sharing entry with the primary key could not be found
	*/
	public com.liferay.asset.sharing.model.AssetSharingEntry[] findByS_S_PrevAndNext(
		com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK assetSharingEntryPK,
		long sharedToClassNameId, long sharedToClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator)
		throws com.liferay.asset.sharing.NoSuchEntryException;

	/**
	* Removes all the asset sharing entries where sharedToClassNameId = &#63; and sharedToClassPK = &#63; from the database.
	*
	* @param sharedToClassNameId the shared to class name ID
	* @param sharedToClassPK the shared to class p k
	*/
	public void removeByS_S(long sharedToClassNameId, long sharedToClassPK);

	/**
	* Returns the number of asset sharing entries where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	*
	* @param sharedToClassNameId the shared to class name ID
	* @param sharedToClassPK the shared to class p k
	* @return the number of matching asset sharing entries
	*/
	public int countByS_S(long sharedToClassNameId, long sharedToClassPK);

	/**
	* Returns all the asset sharing entries where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharedToClassNameId the shared to class name ID
	* @return the matching asset sharing entries
	*/
	public java.util.List<com.liferay.asset.sharing.model.AssetSharingEntry> findByC_C_S(
		long classNameId, long classPK, long sharedToClassNameId);

	/**
	* Returns a range of all the asset sharing entries where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.sharing.model.impl.AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharedToClassNameId the shared to class name ID
	* @param start the lower bound of the range of asset sharing entries
	* @param end the upper bound of the range of asset sharing entries (not inclusive)
	* @return the range of matching asset sharing entries
	*/
	public java.util.List<com.liferay.asset.sharing.model.AssetSharingEntry> findByC_C_S(
		long classNameId, long classPK, long sharedToClassNameId, int start,
		int end);

	/**
	* Returns an ordered range of all the asset sharing entries where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.sharing.model.impl.AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharedToClassNameId the shared to class name ID
	* @param start the lower bound of the range of asset sharing entries
	* @param end the upper bound of the range of asset sharing entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset sharing entries
	*/
	public java.util.List<com.liferay.asset.sharing.model.AssetSharingEntry> findByC_C_S(
		long classNameId, long classPK, long sharedToClassNameId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator);

	/**
	* Returns the first asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharedToClassNameId the shared to class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset sharing entry
	* @throws com.liferay.asset.sharing.NoSuchEntryException if a matching asset sharing entry could not be found
	*/
	public com.liferay.asset.sharing.model.AssetSharingEntry findByC_C_S_First(
		long classNameId, long classPK, long sharedToClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator)
		throws com.liferay.asset.sharing.NoSuchEntryException;

	/**
	* Returns the first asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharedToClassNameId the shared to class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset sharing entry, or <code>null</code> if a matching asset sharing entry could not be found
	*/
	public com.liferay.asset.sharing.model.AssetSharingEntry fetchByC_C_S_First(
		long classNameId, long classPK, long sharedToClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator);

	/**
	* Returns the last asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharedToClassNameId the shared to class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset sharing entry
	* @throws com.liferay.asset.sharing.NoSuchEntryException if a matching asset sharing entry could not be found
	*/
	public com.liferay.asset.sharing.model.AssetSharingEntry findByC_C_S_Last(
		long classNameId, long classPK, long sharedToClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator)
		throws com.liferay.asset.sharing.NoSuchEntryException;

	/**
	* Returns the last asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharedToClassNameId the shared to class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset sharing entry, or <code>null</code> if a matching asset sharing entry could not be found
	*/
	public com.liferay.asset.sharing.model.AssetSharingEntry fetchByC_C_S_Last(
		long classNameId, long classPK, long sharedToClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator);

	/**
	* Returns the asset sharing entries before and after the current asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	*
	* @param assetSharingEntryPK the primary key of the current asset sharing entry
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharedToClassNameId the shared to class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset sharing entry
	* @throws com.liferay.asset.sharing.NoSuchEntryException if a asset sharing entry with the primary key could not be found
	*/
	public com.liferay.asset.sharing.model.AssetSharingEntry[] findByC_C_S_PrevAndNext(
		com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK assetSharingEntryPK,
		long classNameId, long classPK, long sharedToClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator)
		throws com.liferay.asset.sharing.NoSuchEntryException;

	/**
	* Removes all the asset sharing entries where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharedToClassNameId the shared to class name ID
	*/
	public void removeByC_C_S(long classNameId, long classPK,
		long sharedToClassNameId);

	/**
	* Returns the number of asset sharing entries where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharedToClassNameId the shared to class name ID
	* @return the number of matching asset sharing entries
	*/
	public int countByC_C_S(long classNameId, long classPK,
		long sharedToClassNameId);

	/**
	* Returns all the asset sharing entries where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param sharedToClassNameId the shared to class name ID
	* @param sharedToClassPK the shared to class p k
	* @return the matching asset sharing entries
	*/
	public java.util.List<com.liferay.asset.sharing.model.AssetSharingEntry> findByC_S_S(
		long classNameId, long sharedToClassNameId, long sharedToClassPK);

	/**
	* Returns a range of all the asset sharing entries where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.sharing.model.impl.AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param sharedToClassNameId the shared to class name ID
	* @param sharedToClassPK the shared to class p k
	* @param start the lower bound of the range of asset sharing entries
	* @param end the upper bound of the range of asset sharing entries (not inclusive)
	* @return the range of matching asset sharing entries
	*/
	public java.util.List<com.liferay.asset.sharing.model.AssetSharingEntry> findByC_S_S(
		long classNameId, long sharedToClassNameId, long sharedToClassPK,
		int start, int end);

	/**
	* Returns an ordered range of all the asset sharing entries where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.sharing.model.impl.AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param sharedToClassNameId the shared to class name ID
	* @param sharedToClassPK the shared to class p k
	* @param start the lower bound of the range of asset sharing entries
	* @param end the upper bound of the range of asset sharing entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset sharing entries
	*/
	public java.util.List<com.liferay.asset.sharing.model.AssetSharingEntry> findByC_S_S(
		long classNameId, long sharedToClassNameId, long sharedToClassPK,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator);

	/**
	* Returns the first asset sharing entry in the ordered set where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param sharedToClassNameId the shared to class name ID
	* @param sharedToClassPK the shared to class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset sharing entry
	* @throws com.liferay.asset.sharing.NoSuchEntryException if a matching asset sharing entry could not be found
	*/
	public com.liferay.asset.sharing.model.AssetSharingEntry findByC_S_S_First(
		long classNameId, long sharedToClassNameId, long sharedToClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator)
		throws com.liferay.asset.sharing.NoSuchEntryException;

	/**
	* Returns the first asset sharing entry in the ordered set where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param sharedToClassNameId the shared to class name ID
	* @param sharedToClassPK the shared to class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset sharing entry, or <code>null</code> if a matching asset sharing entry could not be found
	*/
	public com.liferay.asset.sharing.model.AssetSharingEntry fetchByC_S_S_First(
		long classNameId, long sharedToClassNameId, long sharedToClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator);

	/**
	* Returns the last asset sharing entry in the ordered set where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param sharedToClassNameId the shared to class name ID
	* @param sharedToClassPK the shared to class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset sharing entry
	* @throws com.liferay.asset.sharing.NoSuchEntryException if a matching asset sharing entry could not be found
	*/
	public com.liferay.asset.sharing.model.AssetSharingEntry findByC_S_S_Last(
		long classNameId, long sharedToClassNameId, long sharedToClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator)
		throws com.liferay.asset.sharing.NoSuchEntryException;

	/**
	* Returns the last asset sharing entry in the ordered set where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param sharedToClassNameId the shared to class name ID
	* @param sharedToClassPK the shared to class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset sharing entry, or <code>null</code> if a matching asset sharing entry could not be found
	*/
	public com.liferay.asset.sharing.model.AssetSharingEntry fetchByC_S_S_Last(
		long classNameId, long sharedToClassNameId, long sharedToClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator);

	/**
	* Returns the asset sharing entries before and after the current asset sharing entry in the ordered set where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	*
	* @param assetSharingEntryPK the primary key of the current asset sharing entry
	* @param classNameId the class name ID
	* @param sharedToClassNameId the shared to class name ID
	* @param sharedToClassPK the shared to class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset sharing entry
	* @throws com.liferay.asset.sharing.NoSuchEntryException if a asset sharing entry with the primary key could not be found
	*/
	public com.liferay.asset.sharing.model.AssetSharingEntry[] findByC_S_S_PrevAndNext(
		com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK assetSharingEntryPK,
		long classNameId, long sharedToClassNameId, long sharedToClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator)
		throws com.liferay.asset.sharing.NoSuchEntryException;

	/**
	* Removes all the asset sharing entries where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param sharedToClassNameId the shared to class name ID
	* @param sharedToClassPK the shared to class p k
	*/
	public void removeByC_S_S(long classNameId, long sharedToClassNameId,
		long sharedToClassPK);

	/**
	* Returns the number of asset sharing entries where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param sharedToClassNameId the shared to class name ID
	* @param sharedToClassPK the shared to class p k
	* @return the number of matching asset sharing entries
	*/
	public int countByC_S_S(long classNameId, long sharedToClassNameId,
		long sharedToClassPK);

	/**
	* Caches the asset sharing entry in the entity cache if it is enabled.
	*
	* @param assetSharingEntry the asset sharing entry
	*/
	public void cacheResult(
		com.liferay.asset.sharing.model.AssetSharingEntry assetSharingEntry);

	/**
	* Caches the asset sharing entries in the entity cache if it is enabled.
	*
	* @param assetSharingEntries the asset sharing entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.asset.sharing.model.AssetSharingEntry> assetSharingEntries);

	/**
	* Creates a new asset sharing entry with the primary key. Does not add the asset sharing entry to the database.
	*
	* @param assetSharingEntryPK the primary key for the new asset sharing entry
	* @return the new asset sharing entry
	*/
	public com.liferay.asset.sharing.model.AssetSharingEntry create(
		com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK assetSharingEntryPK);

	/**
	* Removes the asset sharing entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetSharingEntryPK the primary key of the asset sharing entry
	* @return the asset sharing entry that was removed
	* @throws com.liferay.asset.sharing.NoSuchEntryException if a asset sharing entry with the primary key could not be found
	*/
	public com.liferay.asset.sharing.model.AssetSharingEntry remove(
		com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK assetSharingEntryPK)
		throws com.liferay.asset.sharing.NoSuchEntryException;

	public com.liferay.asset.sharing.model.AssetSharingEntry updateImpl(
		com.liferay.asset.sharing.model.AssetSharingEntry assetSharingEntry);

	/**
	* Returns the asset sharing entry with the primary key or throws a {@link com.liferay.asset.sharing.NoSuchEntryException} if it could not be found.
	*
	* @param assetSharingEntryPK the primary key of the asset sharing entry
	* @return the asset sharing entry
	* @throws com.liferay.asset.sharing.NoSuchEntryException if a asset sharing entry with the primary key could not be found
	*/
	public com.liferay.asset.sharing.model.AssetSharingEntry findByPrimaryKey(
		com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK assetSharingEntryPK)
		throws com.liferay.asset.sharing.NoSuchEntryException;

	/**
	* Returns the asset sharing entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetSharingEntryPK the primary key of the asset sharing entry
	* @return the asset sharing entry, or <code>null</code> if a asset sharing entry with the primary key could not be found
	*/
	public com.liferay.asset.sharing.model.AssetSharingEntry fetchByPrimaryKey(
		com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK assetSharingEntryPK);

	@Override
	public java.util.Map<java.io.Serializable, com.liferay.asset.sharing.model.AssetSharingEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the asset sharing entries.
	*
	* @return the asset sharing entries
	*/
	public java.util.List<com.liferay.asset.sharing.model.AssetSharingEntry> findAll();

	/**
	* Returns a range of all the asset sharing entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.sharing.model.impl.AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset sharing entries
	* @param end the upper bound of the range of asset sharing entries (not inclusive)
	* @return the range of asset sharing entries
	*/
	public java.util.List<com.liferay.asset.sharing.model.AssetSharingEntry> findAll(
		int start, int end);

	/**
	* Returns an ordered range of all the asset sharing entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.sharing.model.impl.AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset sharing entries
	* @param end the upper bound of the range of asset sharing entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset sharing entries
	*/
	public java.util.List<com.liferay.asset.sharing.model.AssetSharingEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.sharing.model.AssetSharingEntry> orderByComparator);

	/**
	* Removes all the asset sharing entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of asset sharing entries.
	*
	* @return the number of asset sharing entries
	*/
	public int countAll();
}