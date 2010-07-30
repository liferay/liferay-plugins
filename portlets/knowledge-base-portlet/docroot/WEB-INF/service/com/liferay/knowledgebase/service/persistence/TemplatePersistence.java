/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

import com.liferay.knowledgebase.model.Template;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the template service.
 *
 * <p>
 * Never modify this interface directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regnerate this interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TemplatePersistenceImpl
 * @see TemplateUtil
 * @generated
 */
public interface TemplatePersistence extends BasePersistence<Template> {
	/**
	* Caches the template in the entity cache if it is enabled.
	*
	* @param template the template to cache
	*/
	public void cacheResult(com.liferay.knowledgebase.model.Template template);

	/**
	* Caches the templates in the entity cache if it is enabled.
	*
	* @param templates the templates to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.knowledgebase.model.Template> templates);

	/**
	* Creates a new template with the primary key.
	*
	* @param templateId the primary key for the new template
	* @return the new template
	*/
	public com.liferay.knowledgebase.model.Template create(long templateId);

	/**
	* Removes the template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param templateId the primary key of the template to remove
	* @return the template that was removed
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Template remove(long templateId)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.knowledgebase.model.Template updateImpl(
		com.liferay.knowledgebase.model.Template template, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the template with the primary key or throws a {@link com.liferay.knowledgebase.NoSuchTemplateException} if it could not be found.
	*
	* @param templateId the primary key of the template to find
	* @return the template
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Template findByPrimaryKey(
		long templateId)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the template with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param templateId the primary key of the template to find
	* @return the template, or <code>null</code> if a template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Template fetchByPrimaryKey(
		long templateId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the templates where uuid = &#63;.
	*
	* @param uuid the uuid to search with
	* @return the matching templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Template> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid to search with
	* @param start the lower bound of the range of templates to return
	* @param end the upper bound of the range of templates to return (not inclusive)
	* @return the range of matching templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Template> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid to search with
	* @param start the lower bound of the range of templates to return
	* @param end the upper bound of the range of templates to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Template> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first template in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching template
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a matching template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Template findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last template in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching template
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a matching template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Template findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the templates before and after the current template in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param templateId the primary key of the current template
	* @param uuid the uuid to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next template
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Template[] findByUuid_PrevAndNext(
		long templateId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the template where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.knowledgebase.NoSuchTemplateException} if it could not be found.
	*
	* @param uuid the uuid to search with
	* @param groupId the group id to search with
	* @return the matching template
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a matching template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Template findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid to search with
	* @param groupId the group id to search with
	* @return the matching template, or <code>null</code> if a matching template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Template fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid to search with
	* @param groupId the group id to search with
	* @return the matching template, or <code>null</code> if a matching template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Template fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the templates where groupId = &#63;.
	*
	* @param groupId the group id to search with
	* @return the matching templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Template> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the templates where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group id to search with
	* @param start the lower bound of the range of templates to return
	* @param end the upper bound of the range of templates to return (not inclusive)
	* @return the range of matching templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Template> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the templates where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group id to search with
	* @param start the lower bound of the range of templates to return
	* @param end the upper bound of the range of templates to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Template> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first template in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching template
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a matching template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Template findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last template in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching template
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a matching template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Template findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the templates before and after the current template in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param templateId the primary key of the current template
	* @param groupId the group id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next template
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Template[] findByGroupId_PrevAndNext(
		long templateId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds all the templates where groupId = &#63;.
	*
	* @param groupId the group id to search with
	* @return the matching templates that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Template> filterFindByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds a range of all the templates where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group id to search with
	* @param start the lower bound of the range of templates to return
	* @param end the upper bound of the range of templates to return (not inclusive)
	* @return the range of matching templates that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Template> filterFindByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds an ordered range of all the templates where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group id to search with
	* @param start the lower bound of the range of templates to return
	* @param end the upper bound of the range of templates to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching templates that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Template> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the templates.
	*
	* @return the templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Template> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of templates to return
	* @param end the upper bound of the range of templates to return (not inclusive)
	* @return the range of templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Template> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of templates to return
	* @param end the upper bound of the range of templates to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Template> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the templates where uuid = &#63; from the database.
	*
	* @param uuid the uuid to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the template where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid to search with
	* @param groupId the group id to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the templates where groupId = &#63; from the database.
	*
	* @param groupId the group id to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the templates from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the templates where uuid = &#63;.
	*
	* @param uuid the uuid to search with
	* @return the number of matching templates
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the templates where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid to search with
	* @param groupId the group id to search with
	* @return the number of matching templates
	* @throws SystemException if a system exception occurred
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the templates where groupId = &#63;.
	*
	* @param groupId the group id to search with
	* @return the number of matching templates
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and counts all the templates where groupId = &#63;.
	*
	* @param groupId the group id to search with
	* @return the number of matching templates that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the templates.
	*
	* @return the number of templates
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}