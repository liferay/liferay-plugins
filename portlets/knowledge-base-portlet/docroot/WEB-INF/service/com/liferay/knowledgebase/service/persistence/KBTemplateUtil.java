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

import com.liferay.knowledgebase.model.KBTemplate;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the k b template service. This utility wraps {@link com.liferay.knowledgebase.service.persistence.impl.KBTemplatePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBTemplatePersistence
 * @see com.liferay.knowledgebase.service.persistence.impl.KBTemplatePersistenceImpl
 * @generated
 */
@ProviderType
public class KBTemplateUtil {
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
	public static void clearCache(KBTemplate kbTemplate) {
		getPersistence().clearCache(kbTemplate);
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
	public static List<KBTemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KBTemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KBTemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<KBTemplate> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static KBTemplate update(KBTemplate kbTemplate) {
		return getPersistence().update(kbTemplate);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static KBTemplate update(KBTemplate kbTemplate,
		ServiceContext serviceContext) {
		return getPersistence().update(kbTemplate, serviceContext);
	}

	/**
	* Returns all the k b templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching k b templates
	*/
	public static List<KBTemplate> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the k b templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of k b templates
	* @param end the upper bound of the range of k b templates (not inclusive)
	* @return the range of matching k b templates
	*/
	public static List<KBTemplate> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the k b templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of k b templates
	* @param end the upper bound of the range of k b templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b templates
	*/
	public static List<KBTemplate> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<KBTemplate> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first k b template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b template
	* @throws NoSuchTemplateException if a matching k b template could not be found
	*/
	public static KBTemplate findByUuid_First(java.lang.String uuid,
		OrderByComparator<KBTemplate> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first k b template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b template, or <code>null</code> if a matching k b template could not be found
	*/
	public static KBTemplate fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<KBTemplate> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last k b template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b template
	* @throws NoSuchTemplateException if a matching k b template could not be found
	*/
	public static KBTemplate findByUuid_Last(java.lang.String uuid,
		OrderByComparator<KBTemplate> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last k b template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b template, or <code>null</code> if a matching k b template could not be found
	*/
	public static KBTemplate fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<KBTemplate> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the k b templates before and after the current k b template in the ordered set where uuid = &#63;.
	*
	* @param kbTemplateId the primary key of the current k b template
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b template
	* @throws NoSuchTemplateException if a k b template with the primary key could not be found
	*/
	public static KBTemplate[] findByUuid_PrevAndNext(long kbTemplateId,
		java.lang.String uuid, OrderByComparator<KBTemplate> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException {
		return getPersistence()
				   .findByUuid_PrevAndNext(kbTemplateId, uuid, orderByComparator);
	}

	/**
	* Removes all the k b templates where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of k b templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching k b templates
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the k b template where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchTemplateException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching k b template
	* @throws NoSuchTemplateException if a matching k b template could not be found
	*/
	public static KBTemplate findByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchTemplateException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the k b template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching k b template, or <code>null</code> if a matching k b template could not be found
	*/
	public static KBTemplate fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the k b template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching k b template, or <code>null</code> if a matching k b template could not be found
	*/
	public static KBTemplate fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the k b template where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the k b template that was removed
	*/
	public static KBTemplate removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchTemplateException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of k b templates where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching k b templates
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the k b templates where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching k b templates
	*/
	public static List<KBTemplate> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the k b templates where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of k b templates
	* @param end the upper bound of the range of k b templates (not inclusive)
	* @return the range of matching k b templates
	*/
	public static List<KBTemplate> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the k b templates where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of k b templates
	* @param end the upper bound of the range of k b templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b templates
	*/
	public static List<KBTemplate> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<KBTemplate> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first k b template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b template
	* @throws NoSuchTemplateException if a matching k b template could not be found
	*/
	public static KBTemplate findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<KBTemplate> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first k b template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b template, or <code>null</code> if a matching k b template could not be found
	*/
	public static KBTemplate fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<KBTemplate> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last k b template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b template
	* @throws NoSuchTemplateException if a matching k b template could not be found
	*/
	public static KBTemplate findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<KBTemplate> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last k b template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b template, or <code>null</code> if a matching k b template could not be found
	*/
	public static KBTemplate fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<KBTemplate> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the k b templates before and after the current k b template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param kbTemplateId the primary key of the current k b template
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b template
	* @throws NoSuchTemplateException if a k b template with the primary key could not be found
	*/
	public static KBTemplate[] findByUuid_C_PrevAndNext(long kbTemplateId,
		java.lang.String uuid, long companyId,
		OrderByComparator<KBTemplate> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(kbTemplateId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the k b templates where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of k b templates where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching k b templates
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the k b templates where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching k b templates
	*/
	public static List<KBTemplate> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the k b templates where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of k b templates
	* @param end the upper bound of the range of k b templates (not inclusive)
	* @return the range of matching k b templates
	*/
	public static List<KBTemplate> findByGroupId(long groupId, int start,
		int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the k b templates where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of k b templates
	* @param end the upper bound of the range of k b templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b templates
	*/
	public static List<KBTemplate> findByGroupId(long groupId, int start,
		int end, OrderByComparator<KBTemplate> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the first k b template in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b template
	* @throws NoSuchTemplateException if a matching k b template could not be found
	*/
	public static KBTemplate findByGroupId_First(long groupId,
		OrderByComparator<KBTemplate> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first k b template in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b template, or <code>null</code> if a matching k b template could not be found
	*/
	public static KBTemplate fetchByGroupId_First(long groupId,
		OrderByComparator<KBTemplate> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last k b template in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b template
	* @throws NoSuchTemplateException if a matching k b template could not be found
	*/
	public static KBTemplate findByGroupId_Last(long groupId,
		OrderByComparator<KBTemplate> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last k b template in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b template, or <code>null</code> if a matching k b template could not be found
	*/
	public static KBTemplate fetchByGroupId_Last(long groupId,
		OrderByComparator<KBTemplate> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the k b templates before and after the current k b template in the ordered set where groupId = &#63;.
	*
	* @param kbTemplateId the primary key of the current k b template
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b template
	* @throws NoSuchTemplateException if a k b template with the primary key could not be found
	*/
	public static KBTemplate[] findByGroupId_PrevAndNext(long kbTemplateId,
		long groupId, OrderByComparator<KBTemplate> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(kbTemplateId, groupId,
			orderByComparator);
	}

	/**
	* Returns all the k b templates that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching k b templates that the user has permission to view
	*/
	public static List<KBTemplate> filterFindByGroupId(long groupId) {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	* Returns a range of all the k b templates that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of k b templates
	* @param end the upper bound of the range of k b templates (not inclusive)
	* @return the range of matching k b templates that the user has permission to view
	*/
	public static List<KBTemplate> filterFindByGroupId(long groupId, int start,
		int end) {
		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the k b templates that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of k b templates
	* @param end the upper bound of the range of k b templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b templates that the user has permission to view
	*/
	public static List<KBTemplate> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator<KBTemplate> orderByComparator) {
		return getPersistence()
				   .filterFindByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the k b templates before and after the current k b template in the ordered set of k b templates that the user has permission to view where groupId = &#63;.
	*
	* @param kbTemplateId the primary key of the current k b template
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b template
	* @throws NoSuchTemplateException if a k b template with the primary key could not be found
	*/
	public static KBTemplate[] filterFindByGroupId_PrevAndNext(
		long kbTemplateId, long groupId,
		OrderByComparator<KBTemplate> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException {
		return getPersistence()
				   .filterFindByGroupId_PrevAndNext(kbTemplateId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the k b templates where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of k b templates where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching k b templates
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the number of k b templates that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching k b templates that the user has permission to view
	*/
	public static int filterCountByGroupId(long groupId) {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	* Caches the k b template in the entity cache if it is enabled.
	*
	* @param kbTemplate the k b template
	*/
	public static void cacheResult(KBTemplate kbTemplate) {
		getPersistence().cacheResult(kbTemplate);
	}

	/**
	* Caches the k b templates in the entity cache if it is enabled.
	*
	* @param kbTemplates the k b templates
	*/
	public static void cacheResult(List<KBTemplate> kbTemplates) {
		getPersistence().cacheResult(kbTemplates);
	}

	/**
	* Creates a new k b template with the primary key. Does not add the k b template to the database.
	*
	* @param kbTemplateId the primary key for the new k b template
	* @return the new k b template
	*/
	public static KBTemplate create(long kbTemplateId) {
		return getPersistence().create(kbTemplateId);
	}

	/**
	* Removes the k b template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbTemplateId the primary key of the k b template
	* @return the k b template that was removed
	* @throws NoSuchTemplateException if a k b template with the primary key could not be found
	*/
	public static KBTemplate remove(long kbTemplateId)
		throws com.liferay.knowledgebase.NoSuchTemplateException {
		return getPersistence().remove(kbTemplateId);
	}

	public static KBTemplate updateImpl(KBTemplate kbTemplate) {
		return getPersistence().updateImpl(kbTemplate);
	}

	/**
	* Returns the k b template with the primary key or throws a {@link NoSuchTemplateException} if it could not be found.
	*
	* @param kbTemplateId the primary key of the k b template
	* @return the k b template
	* @throws NoSuchTemplateException if a k b template with the primary key could not be found
	*/
	public static KBTemplate findByPrimaryKey(long kbTemplateId)
		throws com.liferay.knowledgebase.NoSuchTemplateException {
		return getPersistence().findByPrimaryKey(kbTemplateId);
	}

	/**
	* Returns the k b template with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kbTemplateId the primary key of the k b template
	* @return the k b template, or <code>null</code> if a k b template with the primary key could not be found
	*/
	public static KBTemplate fetchByPrimaryKey(long kbTemplateId) {
		return getPersistence().fetchByPrimaryKey(kbTemplateId);
	}

	public static java.util.Map<java.io.Serializable, KBTemplate> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the k b templates.
	*
	* @return the k b templates
	*/
	public static List<KBTemplate> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the k b templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of k b templates
	* @param end the upper bound of the range of k b templates (not inclusive)
	* @return the range of k b templates
	*/
	public static List<KBTemplate> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the k b templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of k b templates
	* @param end the upper bound of the range of k b templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of k b templates
	*/
	public static List<KBTemplate> findAll(int start, int end,
		OrderByComparator<KBTemplate> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the k b templates from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of k b templates.
	*
	* @return the number of k b templates
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static KBTemplatePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KBTemplatePersistence)PortletBeanLocatorUtil.locate(com.liferay.knowledgebase.service.ClpSerializer.getServletContextName(),
					KBTemplatePersistence.class.getName());

			ReferenceRegistry.registerReference(KBTemplateUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(KBTemplatePersistence persistence) {
	}

	private static KBTemplatePersistence _persistence;
}