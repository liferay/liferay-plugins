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

import com.liferay.knowledgebase.model.KBTemplate;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the k b template service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBTemplatePersistenceImpl
 * @see KBTemplateUtil
 * @generated
 */
public interface KBTemplatePersistence extends BasePersistence<KBTemplate> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KBTemplateUtil} to access the k b template persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the k b templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching k b templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBTemplate> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the k b templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of k b templates
	* @param end the upper bound of the range of k b templates (not inclusive)
	* @return the range of matching k b templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBTemplate> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the k b templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of k b templates
	* @param end the upper bound of the range of k b templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBTemplate> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first k b template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b template
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a matching k b template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBTemplate findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first k b template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b template, or <code>null</code> if a matching k b template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBTemplate fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last k b template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b template
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a matching k b template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBTemplate findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last k b template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b template, or <code>null</code> if a matching k b template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBTemplate fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the k b templates before and after the current k b template in the ordered set where uuid = &#63;.
	*
	* @param kbTemplateId the primary key of the current k b template
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b template
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a k b template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBTemplate[] findByUuid_PrevAndNext(
		long kbTemplateId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the k b templates where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of k b templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching k b templates
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the k b template where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.knowledgebase.NoSuchTemplateException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching k b template
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a matching k b template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBTemplate findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the k b template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching k b template, or <code>null</code> if a matching k b template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBTemplate fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the k b template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching k b template, or <code>null</code> if a matching k b template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBTemplate fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the k b template where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the k b template that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBTemplate removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of k b templates where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching k b templates
	* @throws SystemException if a system exception occurred
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the k b templates where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching k b templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBTemplate> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the k b templates where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of k b templates
	* @param end the upper bound of the range of k b templates (not inclusive)
	* @return the range of matching k b templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBTemplate> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the k b templates where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of k b templates
	* @param end the upper bound of the range of k b templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBTemplate> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first k b template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b template
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a matching k b template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBTemplate findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first k b template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b template, or <code>null</code> if a matching k b template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBTemplate fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last k b template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b template
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a matching k b template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBTemplate findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last k b template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b template, or <code>null</code> if a matching k b template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBTemplate fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the k b templates before and after the current k b template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param kbTemplateId the primary key of the current k b template
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b template
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a k b template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBTemplate[] findByUuid_C_PrevAndNext(
		long kbTemplateId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the k b templates where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of k b templates where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching k b templates
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the k b templates where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching k b templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBTemplate> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the k b templates where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of k b templates
	* @param end the upper bound of the range of k b templates (not inclusive)
	* @return the range of matching k b templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBTemplate> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the k b templates where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of k b templates
	* @param end the upper bound of the range of k b templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBTemplate> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first k b template in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b template
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a matching k b template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBTemplate findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first k b template in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b template, or <code>null</code> if a matching k b template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBTemplate fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last k b template in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b template
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a matching k b template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBTemplate findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last k b template in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b template, or <code>null</code> if a matching k b template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBTemplate fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the k b templates before and after the current k b template in the ordered set where groupId = &#63;.
	*
	* @param kbTemplateId the primary key of the current k b template
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b template
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a k b template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBTemplate[] findByGroupId_PrevAndNext(
		long kbTemplateId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the k b templates that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching k b templates that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBTemplate> filterFindByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the k b templates that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of k b templates
	* @param end the upper bound of the range of k b templates (not inclusive)
	* @return the range of matching k b templates that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBTemplate> filterFindByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the k b templates that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of k b templates
	* @param end the upper bound of the range of k b templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b templates that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBTemplate> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the k b templates before and after the current k b template in the ordered set of k b templates that the user has permission to view where groupId = &#63;.
	*
	* @param kbTemplateId the primary key of the current k b template
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b template
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a k b template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBTemplate[] filterFindByGroupId_PrevAndNext(
		long kbTemplateId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the k b templates where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of k b templates where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching k b templates
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of k b templates that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching k b templates that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the k b template in the entity cache if it is enabled.
	*
	* @param kbTemplate the k b template
	*/
	public void cacheResult(
		com.liferay.knowledgebase.model.KBTemplate kbTemplate);

	/**
	* Caches the k b templates in the entity cache if it is enabled.
	*
	* @param kbTemplates the k b templates
	*/
	public void cacheResult(
		java.util.List<com.liferay.knowledgebase.model.KBTemplate> kbTemplates);

	/**
	* Creates a new k b template with the primary key. Does not add the k b template to the database.
	*
	* @param kbTemplateId the primary key for the new k b template
	* @return the new k b template
	*/
	public com.liferay.knowledgebase.model.KBTemplate create(long kbTemplateId);

	/**
	* Removes the k b template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbTemplateId the primary key of the k b template
	* @return the k b template that was removed
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a k b template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBTemplate remove(long kbTemplateId)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.knowledgebase.model.KBTemplate updateImpl(
		com.liferay.knowledgebase.model.KBTemplate kbTemplate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the k b template with the primary key or throws a {@link com.liferay.knowledgebase.NoSuchTemplateException} if it could not be found.
	*
	* @param kbTemplateId the primary key of the k b template
	* @return the k b template
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a k b template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBTemplate findByPrimaryKey(
		long kbTemplateId)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the k b template with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kbTemplateId the primary key of the k b template
	* @return the k b template, or <code>null</code> if a k b template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBTemplate fetchByPrimaryKey(
		long kbTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the k b templates.
	*
	* @return the k b templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBTemplate> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the k b templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of k b templates
	* @param end the upper bound of the range of k b templates (not inclusive)
	* @return the range of k b templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBTemplate> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the k b templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of k b templates
	* @param end the upper bound of the range of k b templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of k b templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBTemplate> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the k b templates from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of k b templates.
	*
	* @return the number of k b templates
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}