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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the k b template service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.knowledgebase.service.persistence.impl.KBTemplatePersistenceImpl
 * @see KBTemplateUtil
 * @generated
 */
@ProviderType
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
	*/
	public java.util.List<KBTemplate> findByUuid(java.lang.String uuid);

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
	public java.util.List<KBTemplate> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<KBTemplate> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBTemplate> orderByComparator);

	/**
	* Returns the first k b template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b template
	* @throws NoSuchTemplateException if a matching k b template could not be found
	*/
	public KBTemplate findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<KBTemplate> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException;

	/**
	* Returns the first k b template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b template, or <code>null</code> if a matching k b template could not be found
	*/
	public KBTemplate fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<KBTemplate> orderByComparator);

	/**
	* Returns the last k b template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b template
	* @throws NoSuchTemplateException if a matching k b template could not be found
	*/
	public KBTemplate findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<KBTemplate> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException;

	/**
	* Returns the last k b template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b template, or <code>null</code> if a matching k b template could not be found
	*/
	public KBTemplate fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<KBTemplate> orderByComparator);

	/**
	* Returns the k b templates before and after the current k b template in the ordered set where uuid = &#63;.
	*
	* @param kbTemplateId the primary key of the current k b template
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b template
	* @throws NoSuchTemplateException if a k b template with the primary key could not be found
	*/
	public KBTemplate[] findByUuid_PrevAndNext(long kbTemplateId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<KBTemplate> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException;

	/**
	* Removes all the k b templates where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of k b templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching k b templates
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the k b template where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchTemplateException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching k b template
	* @throws NoSuchTemplateException if a matching k b template could not be found
	*/
	public KBTemplate findByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchTemplateException;

	/**
	* Returns the k b template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching k b template, or <code>null</code> if a matching k b template could not be found
	*/
	public KBTemplate fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the k b template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching k b template, or <code>null</code> if a matching k b template could not be found
	*/
	public KBTemplate fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the k b template where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the k b template that was removed
	*/
	public KBTemplate removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchTemplateException;

	/**
	* Returns the number of k b templates where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching k b templates
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the k b templates where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching k b templates
	*/
	public java.util.List<KBTemplate> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<KBTemplate> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<KBTemplate> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBTemplate> orderByComparator);

	/**
	* Returns the first k b template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b template
	* @throws NoSuchTemplateException if a matching k b template could not be found
	*/
	public KBTemplate findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KBTemplate> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException;

	/**
	* Returns the first k b template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b template, or <code>null</code> if a matching k b template could not be found
	*/
	public KBTemplate fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KBTemplate> orderByComparator);

	/**
	* Returns the last k b template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b template
	* @throws NoSuchTemplateException if a matching k b template could not be found
	*/
	public KBTemplate findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KBTemplate> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException;

	/**
	* Returns the last k b template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b template, or <code>null</code> if a matching k b template could not be found
	*/
	public KBTemplate fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KBTemplate> orderByComparator);

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
	public KBTemplate[] findByUuid_C_PrevAndNext(long kbTemplateId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KBTemplate> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException;

	/**
	* Removes all the k b templates where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of k b templates where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching k b templates
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the k b templates where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching k b templates
	*/
	public java.util.List<KBTemplate> findByGroupId(long groupId);

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
	public java.util.List<KBTemplate> findByGroupId(long groupId, int start,
		int end);

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
	public java.util.List<KBTemplate> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBTemplate> orderByComparator);

	/**
	* Returns the first k b template in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b template
	* @throws NoSuchTemplateException if a matching k b template could not be found
	*/
	public KBTemplate findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<KBTemplate> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException;

	/**
	* Returns the first k b template in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b template, or <code>null</code> if a matching k b template could not be found
	*/
	public KBTemplate fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<KBTemplate> orderByComparator);

	/**
	* Returns the last k b template in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b template
	* @throws NoSuchTemplateException if a matching k b template could not be found
	*/
	public KBTemplate findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<KBTemplate> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException;

	/**
	* Returns the last k b template in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b template, or <code>null</code> if a matching k b template could not be found
	*/
	public KBTemplate fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<KBTemplate> orderByComparator);

	/**
	* Returns the k b templates before and after the current k b template in the ordered set where groupId = &#63;.
	*
	* @param kbTemplateId the primary key of the current k b template
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b template
	* @throws NoSuchTemplateException if a k b template with the primary key could not be found
	*/
	public KBTemplate[] findByGroupId_PrevAndNext(long kbTemplateId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<KBTemplate> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException;

	/**
	* Returns all the k b templates that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching k b templates that the user has permission to view
	*/
	public java.util.List<KBTemplate> filterFindByGroupId(long groupId);

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
	public java.util.List<KBTemplate> filterFindByGroupId(long groupId,
		int start, int end);

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
	public java.util.List<KBTemplate> filterFindByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBTemplate> orderByComparator);

	/**
	* Returns the k b templates before and after the current k b template in the ordered set of k b templates that the user has permission to view where groupId = &#63;.
	*
	* @param kbTemplateId the primary key of the current k b template
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b template
	* @throws NoSuchTemplateException if a k b template with the primary key could not be found
	*/
	public KBTemplate[] filterFindByGroupId_PrevAndNext(long kbTemplateId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<KBTemplate> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException;

	/**
	* Removes all the k b templates where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of k b templates where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching k b templates
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns the number of k b templates that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching k b templates that the user has permission to view
	*/
	public int filterCountByGroupId(long groupId);

	/**
	* Caches the k b template in the entity cache if it is enabled.
	*
	* @param kbTemplate the k b template
	*/
	public void cacheResult(KBTemplate kbTemplate);

	/**
	* Caches the k b templates in the entity cache if it is enabled.
	*
	* @param kbTemplates the k b templates
	*/
	public void cacheResult(java.util.List<KBTemplate> kbTemplates);

	/**
	* Creates a new k b template with the primary key. Does not add the k b template to the database.
	*
	* @param kbTemplateId the primary key for the new k b template
	* @return the new k b template
	*/
	public KBTemplate create(long kbTemplateId);

	/**
	* Removes the k b template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbTemplateId the primary key of the k b template
	* @return the k b template that was removed
	* @throws NoSuchTemplateException if a k b template with the primary key could not be found
	*/
	public KBTemplate remove(long kbTemplateId)
		throws com.liferay.knowledgebase.NoSuchTemplateException;

	public KBTemplate updateImpl(KBTemplate kbTemplate);

	/**
	* Returns the k b template with the primary key or throws a {@link NoSuchTemplateException} if it could not be found.
	*
	* @param kbTemplateId the primary key of the k b template
	* @return the k b template
	* @throws NoSuchTemplateException if a k b template with the primary key could not be found
	*/
	public KBTemplate findByPrimaryKey(long kbTemplateId)
		throws com.liferay.knowledgebase.NoSuchTemplateException;

	/**
	* Returns the k b template with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kbTemplateId the primary key of the k b template
	* @return the k b template, or <code>null</code> if a k b template with the primary key could not be found
	*/
	public KBTemplate fetchByPrimaryKey(long kbTemplateId);

	@Override
	public java.util.Map<java.io.Serializable, KBTemplate> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the k b templates.
	*
	* @return the k b templates
	*/
	public java.util.List<KBTemplate> findAll();

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
	public java.util.List<KBTemplate> findAll(int start, int end);

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
	public java.util.List<KBTemplate> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBTemplate> orderByComparator);

	/**
	* Removes all the k b templates from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of k b templates.
	*
	* @return the number of k b templates
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}