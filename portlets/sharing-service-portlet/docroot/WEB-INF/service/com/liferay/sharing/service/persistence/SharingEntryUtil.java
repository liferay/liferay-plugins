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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.sharing.model.SharingEntry;

import java.util.List;

/**
 * The persistence utility for the sharing entry service. This utility wraps {@link SharingEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SharingEntryPersistence
 * @see SharingEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class SharingEntryUtil {
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
	public static void clearCache(SharingEntry sharingEntry) {
		getPersistence().clearCache(sharingEntry);
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
	public static List<SharingEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SharingEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SharingEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SharingEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static SharingEntry update(SharingEntry sharingEntry) {
		return getPersistence().update(sharingEntry);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static SharingEntry update(SharingEntry sharingEntry,
		ServiceContext serviceContext) {
		return getPersistence().update(sharingEntry, serviceContext);
	}

	/**
	* Returns all the sharing entries where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching sharing entries
	*/
	public static java.util.List<com.liferay.sharing.model.SharingEntry> findByC_C(
		long classNameId, long classPK) {
		return getPersistence().findByC_C(classNameId, classPK);
	}

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
	public static java.util.List<com.liferay.sharing.model.SharingEntry> findByC_C(
		long classNameId, long classPK, int start, int end) {
		return getPersistence().findByC_C(classNameId, classPK, start, end);
	}

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
	public static java.util.List<com.liferay.sharing.model.SharingEntry> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator) {
		return getPersistence()
				   .findByC_C(classNameId, classPK, start, end,
			orderByComparator);
	}

	/**
	* Returns the first sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sharing entry
	* @throws com.liferay.sharing.NoSuchEntryException if a matching sharing entry could not be found
	*/
	public static com.liferay.sharing.model.SharingEntry findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator)
		throws com.liferay.sharing.NoSuchEntryException {
		return getPersistence()
				   .findByC_C_First(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the first sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	*/
	public static com.liferay.sharing.model.SharingEntry fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_First(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sharing entry
	* @throws com.liferay.sharing.NoSuchEntryException if a matching sharing entry could not be found
	*/
	public static com.liferay.sharing.model.SharingEntry findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator)
		throws com.liferay.sharing.NoSuchEntryException {
		return getPersistence()
				   .findByC_C_Last(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	*/
	public static com.liferay.sharing.model.SharingEntry fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_Last(classNameId, classPK, orderByComparator);
	}

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
	public static com.liferay.sharing.model.SharingEntry[] findByC_C_PrevAndNext(
		com.liferay.sharing.service.persistence.SharingEntryPK sharingEntryPK,
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator)
		throws com.liferay.sharing.NoSuchEntryException {
		return getPersistence()
				   .findByC_C_PrevAndNext(sharingEntryPK, classNameId, classPK,
			orderByComparator);
	}

	/**
	* Removes all the sharing entries where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	*/
	public static void removeByC_C(long classNameId, long classPK) {
		getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	* Returns the number of sharing entries where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching sharing entries
	*/
	public static int countByC_C(long classNameId, long classPK) {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	* Returns all the sharing entries where sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @return the matching sharing entries
	*/
	public static java.util.List<com.liferay.sharing.model.SharingEntry> findByS_S(
		long sharingClassNameId, long sharingClassPK) {
		return getPersistence().findByS_S(sharingClassNameId, sharingClassPK);
	}

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
	public static java.util.List<com.liferay.sharing.model.SharingEntry> findByS_S(
		long sharingClassNameId, long sharingClassPK, int start, int end) {
		return getPersistence()
				   .findByS_S(sharingClassNameId, sharingClassPK, start, end);
	}

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
	public static java.util.List<com.liferay.sharing.model.SharingEntry> findByS_S(
		long sharingClassNameId, long sharingClassPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator) {
		return getPersistence()
				   .findByS_S(sharingClassNameId, sharingClassPK, start, end,
			orderByComparator);
	}

	/**
	* Returns the first sharing entry in the ordered set where sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sharing entry
	* @throws com.liferay.sharing.NoSuchEntryException if a matching sharing entry could not be found
	*/
	public static com.liferay.sharing.model.SharingEntry findByS_S_First(
		long sharingClassNameId, long sharingClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator)
		throws com.liferay.sharing.NoSuchEntryException {
		return getPersistence()
				   .findByS_S_First(sharingClassNameId, sharingClassPK,
			orderByComparator);
	}

	/**
	* Returns the first sharing entry in the ordered set where sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	*/
	public static com.liferay.sharing.model.SharingEntry fetchByS_S_First(
		long sharingClassNameId, long sharingClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator) {
		return getPersistence()
				   .fetchByS_S_First(sharingClassNameId, sharingClassPK,
			orderByComparator);
	}

	/**
	* Returns the last sharing entry in the ordered set where sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sharing entry
	* @throws com.liferay.sharing.NoSuchEntryException if a matching sharing entry could not be found
	*/
	public static com.liferay.sharing.model.SharingEntry findByS_S_Last(
		long sharingClassNameId, long sharingClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator)
		throws com.liferay.sharing.NoSuchEntryException {
		return getPersistence()
				   .findByS_S_Last(sharingClassNameId, sharingClassPK,
			orderByComparator);
	}

	/**
	* Returns the last sharing entry in the ordered set where sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	*/
	public static com.liferay.sharing.model.SharingEntry fetchByS_S_Last(
		long sharingClassNameId, long sharingClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator) {
		return getPersistence()
				   .fetchByS_S_Last(sharingClassNameId, sharingClassPK,
			orderByComparator);
	}

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
	public static com.liferay.sharing.model.SharingEntry[] findByS_S_PrevAndNext(
		com.liferay.sharing.service.persistence.SharingEntryPK sharingEntryPK,
		long sharingClassNameId, long sharingClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator)
		throws com.liferay.sharing.NoSuchEntryException {
		return getPersistence()
				   .findByS_S_PrevAndNext(sharingEntryPK, sharingClassNameId,
			sharingClassPK, orderByComparator);
	}

	/**
	* Removes all the sharing entries where sharingClassNameId = &#63; and sharingClassPK = &#63; from the database.
	*
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	*/
	public static void removeByS_S(long sharingClassNameId, long sharingClassPK) {
		getPersistence().removeByS_S(sharingClassNameId, sharingClassPK);
	}

	/**
	* Returns the number of sharing entries where sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @return the number of matching sharing entries
	*/
	public static int countByS_S(long sharingClassNameId, long sharingClassPK) {
		return getPersistence().countByS_S(sharingClassNameId, sharingClassPK);
	}

	/**
	* Returns all the sharing entries where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharingClassNameId the sharing class name ID
	* @return the matching sharing entries
	*/
	public static java.util.List<com.liferay.sharing.model.SharingEntry> findByC_C_S(
		long classNameId, long classPK, long sharingClassNameId) {
		return getPersistence()
				   .findByC_C_S(classNameId, classPK, sharingClassNameId);
	}

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
	public static java.util.List<com.liferay.sharing.model.SharingEntry> findByC_C_S(
		long classNameId, long classPK, long sharingClassNameId, int start,
		int end) {
		return getPersistence()
				   .findByC_C_S(classNameId, classPK, sharingClassNameId,
			start, end);
	}

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
	public static java.util.List<com.liferay.sharing.model.SharingEntry> findByC_C_S(
		long classNameId, long classPK, long sharingClassNameId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator) {
		return getPersistence()
				   .findByC_C_S(classNameId, classPK, sharingClassNameId,
			start, end, orderByComparator);
	}

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
	public static com.liferay.sharing.model.SharingEntry findByC_C_S_First(
		long classNameId, long classPK, long sharingClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator)
		throws com.liferay.sharing.NoSuchEntryException {
		return getPersistence()
				   .findByC_C_S_First(classNameId, classPK, sharingClassNameId,
			orderByComparator);
	}

	/**
	* Returns the first sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharingClassNameId the sharing class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	*/
	public static com.liferay.sharing.model.SharingEntry fetchByC_C_S_First(
		long classNameId, long classPK, long sharingClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_S_First(classNameId, classPK,
			sharingClassNameId, orderByComparator);
	}

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
	public static com.liferay.sharing.model.SharingEntry findByC_C_S_Last(
		long classNameId, long classPK, long sharingClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator)
		throws com.liferay.sharing.NoSuchEntryException {
		return getPersistence()
				   .findByC_C_S_Last(classNameId, classPK, sharingClassNameId,
			orderByComparator);
	}

	/**
	* Returns the last sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharingClassNameId the sharing class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	*/
	public static com.liferay.sharing.model.SharingEntry fetchByC_C_S_Last(
		long classNameId, long classPK, long sharingClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_S_Last(classNameId, classPK, sharingClassNameId,
			orderByComparator);
	}

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
	public static com.liferay.sharing.model.SharingEntry[] findByC_C_S_PrevAndNext(
		com.liferay.sharing.service.persistence.SharingEntryPK sharingEntryPK,
		long classNameId, long classPK, long sharingClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator)
		throws com.liferay.sharing.NoSuchEntryException {
		return getPersistence()
				   .findByC_C_S_PrevAndNext(sharingEntryPK, classNameId,
			classPK, sharingClassNameId, orderByComparator);
	}

	/**
	* Removes all the sharing entries where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharingClassNameId the sharing class name ID
	*/
	public static void removeByC_C_S(long classNameId, long classPK,
		long sharingClassNameId) {
		getPersistence().removeByC_C_S(classNameId, classPK, sharingClassNameId);
	}

	/**
	* Returns the number of sharing entries where classNameId = &#63; and classPK = &#63; and sharingClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param sharingClassNameId the sharing class name ID
	* @return the number of matching sharing entries
	*/
	public static int countByC_C_S(long classNameId, long classPK,
		long sharingClassNameId) {
		return getPersistence()
				   .countByC_C_S(classNameId, classPK, sharingClassNameId);
	}

	/**
	* Returns all the sharing entries where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @return the matching sharing entries
	*/
	public static java.util.List<com.liferay.sharing.model.SharingEntry> findByC_S_S(
		long classNameId, long sharingClassNameId, long sharingClassPK) {
		return getPersistence()
				   .findByC_S_S(classNameId, sharingClassNameId, sharingClassPK);
	}

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
	public static java.util.List<com.liferay.sharing.model.SharingEntry> findByC_S_S(
		long classNameId, long sharingClassNameId, long sharingClassPK,
		int start, int end) {
		return getPersistence()
				   .findByC_S_S(classNameId, sharingClassNameId,
			sharingClassPK, start, end);
	}

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
	public static java.util.List<com.liferay.sharing.model.SharingEntry> findByC_S_S(
		long classNameId, long sharingClassNameId, long sharingClassPK,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator) {
		return getPersistence()
				   .findByC_S_S(classNameId, sharingClassNameId,
			sharingClassPK, start, end, orderByComparator);
	}

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
	public static com.liferay.sharing.model.SharingEntry findByC_S_S_First(
		long classNameId, long sharingClassNameId, long sharingClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator)
		throws com.liferay.sharing.NoSuchEntryException {
		return getPersistence()
				   .findByC_S_S_First(classNameId, sharingClassNameId,
			sharingClassPK, orderByComparator);
	}

	/**
	* Returns the first sharing entry in the ordered set where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	*/
	public static com.liferay.sharing.model.SharingEntry fetchByC_S_S_First(
		long classNameId, long sharingClassNameId, long sharingClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator) {
		return getPersistence()
				   .fetchByC_S_S_First(classNameId, sharingClassNameId,
			sharingClassPK, orderByComparator);
	}

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
	public static com.liferay.sharing.model.SharingEntry findByC_S_S_Last(
		long classNameId, long sharingClassNameId, long sharingClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator)
		throws com.liferay.sharing.NoSuchEntryException {
		return getPersistence()
				   .findByC_S_S_Last(classNameId, sharingClassNameId,
			sharingClassPK, orderByComparator);
	}

	/**
	* Returns the last sharing entry in the ordered set where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	*/
	public static com.liferay.sharing.model.SharingEntry fetchByC_S_S_Last(
		long classNameId, long sharingClassNameId, long sharingClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator) {
		return getPersistence()
				   .fetchByC_S_S_Last(classNameId, sharingClassNameId,
			sharingClassPK, orderByComparator);
	}

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
	public static com.liferay.sharing.model.SharingEntry[] findByC_S_S_PrevAndNext(
		com.liferay.sharing.service.persistence.SharingEntryPK sharingEntryPK,
		long classNameId, long sharingClassNameId, long sharingClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator)
		throws com.liferay.sharing.NoSuchEntryException {
		return getPersistence()
				   .findByC_S_S_PrevAndNext(sharingEntryPK, classNameId,
			sharingClassNameId, sharingClassPK, orderByComparator);
	}

	/**
	* Removes all the sharing entries where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	*/
	public static void removeByC_S_S(long classNameId, long sharingClassNameId,
		long sharingClassPK) {
		getPersistence()
			.removeByC_S_S(classNameId, sharingClassNameId, sharingClassPK);
	}

	/**
	* Returns the number of sharing entries where classNameId = &#63; and sharingClassNameId = &#63; and sharingClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param sharingClassNameId the sharing class name ID
	* @param sharingClassPK the sharing class p k
	* @return the number of matching sharing entries
	*/
	public static int countByC_S_S(long classNameId, long sharingClassNameId,
		long sharingClassPK) {
		return getPersistence()
				   .countByC_S_S(classNameId, sharingClassNameId, sharingClassPK);
	}

	/**
	* Caches the sharing entry in the entity cache if it is enabled.
	*
	* @param sharingEntry the sharing entry
	*/
	public static void cacheResult(
		com.liferay.sharing.model.SharingEntry sharingEntry) {
		getPersistence().cacheResult(sharingEntry);
	}

	/**
	* Caches the sharing entries in the entity cache if it is enabled.
	*
	* @param sharingEntries the sharing entries
	*/
	public static void cacheResult(
		java.util.List<com.liferay.sharing.model.SharingEntry> sharingEntries) {
		getPersistence().cacheResult(sharingEntries);
	}

	/**
	* Creates a new sharing entry with the primary key. Does not add the sharing entry to the database.
	*
	* @param sharingEntryPK the primary key for the new sharing entry
	* @return the new sharing entry
	*/
	public static com.liferay.sharing.model.SharingEntry create(
		com.liferay.sharing.service.persistence.SharingEntryPK sharingEntryPK) {
		return getPersistence().create(sharingEntryPK);
	}

	/**
	* Removes the sharing entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param sharingEntryPK the primary key of the sharing entry
	* @return the sharing entry that was removed
	* @throws com.liferay.sharing.NoSuchEntryException if a sharing entry with the primary key could not be found
	*/
	public static com.liferay.sharing.model.SharingEntry remove(
		com.liferay.sharing.service.persistence.SharingEntryPK sharingEntryPK)
		throws com.liferay.sharing.NoSuchEntryException {
		return getPersistence().remove(sharingEntryPK);
	}

	public static com.liferay.sharing.model.SharingEntry updateImpl(
		com.liferay.sharing.model.SharingEntry sharingEntry) {
		return getPersistence().updateImpl(sharingEntry);
	}

	/**
	* Returns the sharing entry with the primary key or throws a {@link com.liferay.sharing.NoSuchEntryException} if it could not be found.
	*
	* @param sharingEntryPK the primary key of the sharing entry
	* @return the sharing entry
	* @throws com.liferay.sharing.NoSuchEntryException if a sharing entry with the primary key could not be found
	*/
	public static com.liferay.sharing.model.SharingEntry findByPrimaryKey(
		com.liferay.sharing.service.persistence.SharingEntryPK sharingEntryPK)
		throws com.liferay.sharing.NoSuchEntryException {
		return getPersistence().findByPrimaryKey(sharingEntryPK);
	}

	/**
	* Returns the sharing entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param sharingEntryPK the primary key of the sharing entry
	* @return the sharing entry, or <code>null</code> if a sharing entry with the primary key could not be found
	*/
	public static com.liferay.sharing.model.SharingEntry fetchByPrimaryKey(
		com.liferay.sharing.service.persistence.SharingEntryPK sharingEntryPK) {
		return getPersistence().fetchByPrimaryKey(sharingEntryPK);
	}

	public static java.util.Map<java.io.Serializable, com.liferay.sharing.model.SharingEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the sharing entries.
	*
	* @return the sharing entries
	*/
	public static java.util.List<com.liferay.sharing.model.SharingEntry> findAll() {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.sharing.model.SharingEntry> findAll(
		int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.sharing.model.SharingEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sharing.model.SharingEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the sharing entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of sharing entries.
	*
	* @return the number of sharing entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SharingEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SharingEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.sharing.service.ClpSerializer.getServletContextName(),
					SharingEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(SharingEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(SharingEntryPersistence persistence) {
	}

	private static SharingEntryPersistence _persistence;
}