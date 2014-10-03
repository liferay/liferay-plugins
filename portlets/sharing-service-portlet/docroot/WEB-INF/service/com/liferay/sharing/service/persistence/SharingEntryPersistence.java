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

package com.liferay.sharing.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.sharing.model.SharingEntry;

/**
 * The persistence interface for the sharing entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SharingEntryPersistenceImpl
 * @see SharingEntryUtil
 * @generated
 */
@ProviderType
public interface SharingEntryPersistence extends BasePersistence<SharingEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SharingEntryUtil} to access the sharing entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the sharing entries where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching sharing entries
	*/
	public java.util.List<com.liferay.sharing.model.SharingEntry> findByC_C(
		long classNameId, long classPK);

	/**
	* Returns a range of all the sharing entries where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of sharing entries
	* @param end the upper bound of the range of sharing entries (not inclusive)
	* @return the range of matching sharing entries
	*/
	public java.util.List<com.liferay.sharing.model.SharingEntry> findByC_C(
		long classNameId, long classPK, int start, int end);

	/**
	* Returns an ordered range of all the sharing entries where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of sharing entries
	* @param end the upper bound of the range of sharing entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sharing entries
	*/
	public java.util.List<com.liferay.sharing.model.SharingEntry> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator);

	/**
	* Returns the first sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sharing entry
	* @throws com.liferay.sharing.NoSuchEntryException if a matching sharing entry could not be found
	*/
	public com.liferay.sharing.model.SharingEntry findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator)
		throws com.liferay.sharing.NoSuchEntryException;

	/**
	* Returns the first sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	*/
	public com.liferay.sharing.model.SharingEntry fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator);

	/**
	* Returns the last sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sharing entry
	* @throws com.liferay.sharing.NoSuchEntryException if a matching sharing entry could not be found
	*/
	public com.liferay.sharing.model.SharingEntry findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator)
		throws com.liferay.sharing.NoSuchEntryException;

	/**
	* Returns the last sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	*/
	public com.liferay.sharing.model.SharingEntry fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator);

	/**
	* Returns the sharing entries before and after the current sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param sharingEntryPK the primary key of the current sharing entry
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sharing entry
	* @throws com.liferay.sharing.NoSuchEntryException if a sharing entry with the primary key could not be found
	*/
	public com.liferay.sharing.model.SharingEntry[] findByC_C_PrevAndNext(
		com.liferay.sharing.service.persistence.SharingEntryPK sharingEntryPK,
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator)
		throws com.liferay.sharing.NoSuchEntryException;

	/**
	* Removes all the sharing entries where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	*/
	public void removeByC_C(long classNameId, long classPK);

	/**
	* Returns the number of sharing entries where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching sharing entries
	*/
	public int countByC_C(long classNameId, long classPK);

	/**
	* Returns all the sharing entries where sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @return the matching sharing entries
	*/
	public java.util.List<com.liferay.sharing.model.SharingEntry> findByS_S(
		long sharingClassNameId, long sharingClassPK);

	/**
	* Returns a range of all the sharing entries where sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @param start the lower bound of the range of sharing entries
	* @param end the upper bound of the range of sharing entries (not inclusive)
	* @return the range of matching sharing entries
	*/
	public java.util.List<com.liferay.sharing.model.SharingEntry> findByS_S(
		long sharingClassNameId, long sharingClassPK, int start, int end);

	/**
	* Returns an ordered range of all the sharing entries where sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @param start the lower bound of the range of sharing entries
	* @param end the upper bound of the range of sharing entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sharing entries
	*/
	public java.util.List<com.liferay.sharing.model.SharingEntry> findByS_S(
		long sharingClassNameId, long sharingClassPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator);

	/**
	* Returns the first sharing entry in the ordered set where sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sharing entry
	* @throws com.liferay.sharing.NoSuchEntryException if a matching sharing entry could not be found
	*/
	public com.liferay.sharing.model.SharingEntry findByS_S_First(
		long sharingClassNameId, long sharingClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator)
		throws com.liferay.sharing.NoSuchEntryException;

	/**
	* Returns the first sharing entry in the ordered set where sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	*/
	public com.liferay.sharing.model.SharingEntry fetchByS_S_First(
		long sharingClassNameId, long sharingClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator);

	/**
	* Returns the last sharing entry in the ordered set where sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sharing entry
	* @throws com.liferay.sharing.NoSuchEntryException if a matching sharing entry could not be found
	*/
	public com.liferay.sharing.model.SharingEntry findByS_S_Last(
		long sharingClassNameId, long sharingClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator)
		throws com.liferay.sharing.NoSuchEntryException;

	/**
	* Returns the last sharing entry in the ordered set where sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	*/
	public com.liferay.sharing.model.SharingEntry fetchByS_S_Last(
		long sharingClassNameId, long sharingClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator);

	/**
	* Returns the sharing entries before and after the current sharing entry in the ordered set where sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* @param sharingEntryPK the primary key of the current sharing entry
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sharing entry
	* @throws com.liferay.sharing.NoSuchEntryException if a sharing entry with the primary key could not be found
	*/
	public com.liferay.sharing.model.SharingEntry[] findByS_S_PrevAndNext(
		com.liferay.sharing.service.persistence.SharingEntryPK sharingEntryPK,
		long sharingClassNameId, long sharingClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator)
		throws com.liferay.sharing.NoSuchEntryException;

	/**
	* Removes all the sharing entries where sharingClassNameId = &#63; and sharingClassPK = &#63; from the database.
	*
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	*/
	public void removeByS_S(long sharingClassNameId, long sharingClassPK);

	/**
	* Returns the number of sharing entries where sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @return the number of matching sharing entries
	*/
	public int countByS_S(long sharingClassNameId, long sharingClassPK);

	/**
	* Returns all the sharing entries where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharingClassNameId the sharing class name ID
	* @return the matching sharing entries
	*/
	public java.util.List<com.liferay.sharing.model.SharingEntry> findByC_C_S(
		long classNameId, long classPK, long sharingClassNameId);

	/**
	* Returns a range of all the sharing entries where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharingClassNameId the sharing class name ID
	* @param start the lower bound of the range of sharing entries
	* @param end the upper bound of the range of sharing entries (not inclusive)
	* @return the range of matching sharing entries
	*/
	public java.util.List<com.liferay.sharing.model.SharingEntry> findByC_C_S(
		long classNameId, long classPK, long sharingClassNameId, int start,
		int end);

	/**
	* Returns an ordered range of all the sharing entries where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharingClassNameId the sharing class name ID
	* @param start the lower bound of the range of sharing entries
	* @param end the upper bound of the range of sharing entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sharing entries
	*/
	public java.util.List<com.liferay.sharing.model.SharingEntry> findByC_C_S(
		long classNameId, long classPK, long sharingClassNameId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator);

	/**
	* Returns the first sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharingClassNameId the sharing class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sharing entry
	* @throws com.liferay.sharing.NoSuchEntryException if a matching sharing entry could not be found
	*/
	public com.liferay.sharing.model.SharingEntry findByC_C_S_First(
		long classNameId, long classPK, long sharingClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator)
		throws com.liferay.sharing.NoSuchEntryException;

	/**
	* Returns the first sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharingClassNameId the sharing class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	*/
	public com.liferay.sharing.model.SharingEntry fetchByC_C_S_First(
		long classNameId, long classPK, long sharingClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator);

	/**
	* Returns the last sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharingClassNameId the sharing class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sharing entry
	* @throws com.liferay.sharing.NoSuchEntryException if a matching sharing entry could not be found
	*/
	public com.liferay.sharing.model.SharingEntry findByC_C_S_Last(
		long classNameId, long classPK, long sharingClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator)
		throws com.liferay.sharing.NoSuchEntryException;

	/**
	* Returns the last sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharingClassNameId the sharing class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	*/
	public com.liferay.sharing.model.SharingEntry fetchByC_C_S_Last(
		long classNameId, long classPK, long sharingClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator);

	/**
	* Returns the sharing entries before and after the current sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63;.
	*
	* @param sharingEntryPK the primary key of the current sharing entry
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharingClassNameId the sharing class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sharing entry
	* @throws com.liferay.sharing.NoSuchEntryException if a sharing entry with the primary key could not be found
	*/
	public com.liferay.sharing.model.SharingEntry[] findByC_C_S_PrevAndNext(
		com.liferay.sharing.service.persistence.SharingEntryPK sharingEntryPK,
		long classNameId, long classPK, long sharingClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator)
		throws com.liferay.sharing.NoSuchEntryException;

	/**
	* Removes all the sharing entries where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharingClassNameId the sharing class name ID
	*/
	public void removeByC_C_S(long classNameId, long classPK,
		long sharingClassNameId);

	/**
	* Returns the number of sharing entries where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharingClassNameId the sharing class name ID
	* @return the number of matching sharing entries
	*/
	public int countByC_C_S(long classNameId, long classPK,
		long sharingClassNameId);

	/**
	* Returns all the sharing entries where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @return the matching sharing entries
	*/
	public java.util.List<com.liferay.sharing.model.SharingEntry> findByC_S_S(
		long classNameId, long sharingClassNameId, long sharingClassPK);

	/**
	* Returns a range of all the sharing entries where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @param start the lower bound of the range of sharing entries
	* @param end the upper bound of the range of sharing entries (not inclusive)
	* @return the range of matching sharing entries
	*/
	public java.util.List<com.liferay.sharing.model.SharingEntry> findByC_S_S(
		long classNameId, long sharingClassNameId, long sharingClassPK,
		int start, int end);

	/**
	* Returns an ordered range of all the sharing entries where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @param start the lower bound of the range of sharing entries
	* @param end the upper bound of the range of sharing entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sharing entries
	*/
	public java.util.List<com.liferay.sharing.model.SharingEntry> findByC_S_S(
		long classNameId, long sharingClassNameId, long sharingClassPK,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator);

	/**
	* Returns the first sharing entry in the ordered set where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sharing entry
	* @throws com.liferay.sharing.NoSuchEntryException if a matching sharing entry could not be found
	*/
	public com.liferay.sharing.model.SharingEntry findByC_S_S_First(
		long classNameId, long sharingClassNameId, long sharingClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator)
		throws com.liferay.sharing.NoSuchEntryException;

	/**
	* Returns the first sharing entry in the ordered set where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	*/
	public com.liferay.sharing.model.SharingEntry fetchByC_S_S_First(
		long classNameId, long sharingClassNameId, long sharingClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator);

	/**
	* Returns the last sharing entry in the ordered set where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sharing entry
	* @throws com.liferay.sharing.NoSuchEntryException if a matching sharing entry could not be found
	*/
	public com.liferay.sharing.model.SharingEntry findByC_S_S_Last(
		long classNameId, long sharingClassNameId, long sharingClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator)
		throws com.liferay.sharing.NoSuchEntryException;

	/**
	* Returns the last sharing entry in the ordered set where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	*/
	public com.liferay.sharing.model.SharingEntry fetchByC_S_S_Last(
		long classNameId, long sharingClassNameId, long sharingClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator);

	/**
	* Returns the sharing entries before and after the current sharing entry in the ordered set where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* @param sharingEntryPK the primary key of the current sharing entry
	* @param classNameId the class name ID
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sharing entry
	* @throws com.liferay.sharing.NoSuchEntryException if a sharing entry with the primary key could not be found
	*/
	public com.liferay.sharing.model.SharingEntry[] findByC_S_S_PrevAndNext(
		com.liferay.sharing.service.persistence.SharingEntryPK sharingEntryPK,
		long classNameId, long sharingClassNameId, long sharingClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator)
		throws com.liferay.sharing.NoSuchEntryException;

	/**
	* Removes all the sharing entries where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	*/
	public void removeByC_S_S(long classNameId, long sharingClassNameId,
		long sharingClassPK);

	/**
	* Returns the number of sharing entries where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @return the number of matching sharing entries
	*/
	public int countByC_S_S(long classNameId, long sharingClassNameId,
		long sharingClassPK);

	/**
	* Caches the sharing entry in the entity cache if it is enabled.
	*
	* @param sharingEntry the sharing entry
	*/
	public void cacheResult(com.liferay.sharing.model.SharingEntry sharingEntry);

	/**
	* Caches the sharing entries in the entity cache if it is enabled.
	*
	* @param sharingEntries the sharing entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.sharing.model.SharingEntry> sharingEntries);

	/**
	* Creates a new sharing entry with the primary key. Does not add the sharing entry to the database.
	*
	* @param sharingEntryPK the primary key for the new sharing entry
	* @return the new sharing entry
	*/
	public com.liferay.sharing.model.SharingEntry create(
		com.liferay.sharing.service.persistence.SharingEntryPK sharingEntryPK);

	/**
	* Removes the sharing entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param sharingEntryPK the primary key of the sharing entry
	* @return the sharing entry that was removed
	* @throws com.liferay.sharing.NoSuchEntryException if a sharing entry with the primary key could not be found
	*/
	public com.liferay.sharing.model.SharingEntry remove(
		com.liferay.sharing.service.persistence.SharingEntryPK sharingEntryPK)
		throws com.liferay.sharing.NoSuchEntryException;

	public com.liferay.sharing.model.SharingEntry updateImpl(
		com.liferay.sharing.model.SharingEntry sharingEntry);

	/**
	* Returns the sharing entry with the primary key or throws a {@link com.liferay.sharing.NoSuchEntryException} if it could not be found.
	*
	* @param sharingEntryPK the primary key of the sharing entry
	* @return the sharing entry
	* @throws com.liferay.sharing.NoSuchEntryException if a sharing entry with the primary key could not be found
	*/
	public com.liferay.sharing.model.SharingEntry findByPrimaryKey(
		com.liferay.sharing.service.persistence.SharingEntryPK sharingEntryPK)
		throws com.liferay.sharing.NoSuchEntryException;

	/**
	* Returns the sharing entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param sharingEntryPK the primary key of the sharing entry
	* @return the sharing entry, or <code>null</code> if a sharing entry with the primary key could not be found
	*/
	public com.liferay.sharing.model.SharingEntry fetchByPrimaryKey(
		com.liferay.sharing.service.persistence.SharingEntryPK sharingEntryPK);

	@Override
	public java.util.Map<java.io.Serializable, com.liferay.sharing.model.SharingEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the sharing entries.
	*
	* @return the sharing entries
	*/
	public java.util.List<com.liferay.sharing.model.SharingEntry> findAll();

	/**
	* Returns a range of all the sharing entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sharing entries
	* @param end the upper bound of the range of sharing entries (not inclusive)
	* @return the range of sharing entries
	*/
	public java.util.List<com.liferay.sharing.model.SharingEntry> findAll(
		int start, int end);

	/**
	* Returns an ordered range of all the sharing entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sharing entries
	* @param end the upper bound of the range of sharing entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sharing entries
	*/
	public java.util.List<com.liferay.sharing.model.SharingEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator);

	/**
	* Removes all the sharing entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of sharing entries.
	*
	* @return the number of sharing entries
	*/
	public int countAll();
}