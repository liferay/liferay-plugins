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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the template service. This utility wraps {@link TemplatePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TemplatePersistence
 * @see TemplatePersistenceImpl
 * @generated
 */
public class TemplateUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(Template template) {
		getPersistence().clearCache(template);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Template> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Template> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Template> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static Template remove(Template template) throws SystemException {
		return getPersistence().remove(template);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static Template update(Template template, boolean merge)
		throws SystemException {
		return getPersistence().update(template, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static Template update(Template template, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(template, merge, serviceContext);
	}

	/**
	* Caches the template in the entity cache if it is enabled.
	*
	* @param template the template to cache
	*/
	public static void cacheResult(
		com.liferay.knowledgebase.model.Template template) {
		getPersistence().cacheResult(template);
	}

	/**
	* Caches the templates in the entity cache if it is enabled.
	*
	* @param templates the templates to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.knowledgebase.model.Template> templates) {
		getPersistence().cacheResult(templates);
	}

	/**
	* Creates a new template with the primary key. Does not add the template to the database.
	*
	* @param templateId the primary key for the new template
	* @return the new template
	*/
	public static com.liferay.knowledgebase.model.Template create(
		long templateId) {
		return getPersistence().create(templateId);
	}

	/**
	* Removes the template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param templateId the primary key of the template to remove
	* @return the template that was removed
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Template remove(
		long templateId)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(templateId);
	}

	public static com.liferay.knowledgebase.model.Template updateImpl(
		com.liferay.knowledgebase.model.Template template, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(template, merge);
	}

	/**
	* Finds the template with the primary key or throws a {@link com.liferay.knowledgebase.NoSuchTemplateException} if it could not be found.
	*
	* @param templateId the primary key of the template to find
	* @return the template
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Template findByPrimaryKey(
		long templateId)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(templateId);
	}

	/**
	* Finds the template with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param templateId the primary key of the template to find
	* @return the template, or <code>null</code> if a template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Template fetchByPrimaryKey(
		long templateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(templateId);
	}

	/**
	* Finds all the templates where uuid = &#63;.
	*
	* @param uuid the uuid to search with
	* @return the matching templates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Template> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

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
	public static java.util.List<com.liferay.knowledgebase.model.Template> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static java.util.List<com.liferay.knowledgebase.model.Template> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static com.liferay.knowledgebase.model.Template findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

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
	public static com.liferay.knowledgebase.model.Template findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

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
	public static com.liferay.knowledgebase.model.Template[] findByUuid_PrevAndNext(
		long templateId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(templateId, uuid, orderByComparator);
	}

	/**
	* Finds the template where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.knowledgebase.NoSuchTemplateException} if it could not be found.
	*
	* @param uuid the uuid to search with
	* @param groupId the group id to search with
	* @return the matching template
	* @throws com.liferay.knowledgebase.NoSuchTemplateException if a matching template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Template findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Finds the template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid to search with
	* @param groupId the group id to search with
	* @return the matching template, or <code>null</code> if a matching template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Template fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Finds the template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid to search with
	* @param groupId the group id to search with
	* @return the matching template, or <code>null</code> if a matching template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Template fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Finds all the templates where groupId = &#63;.
	*
	* @param groupId the group id to search with
	* @return the matching templates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Template> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

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
	public static java.util.List<com.liferay.knowledgebase.model.Template> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

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
	public static java.util.List<com.liferay.knowledgebase.model.Template> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

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
	public static com.liferay.knowledgebase.model.Template findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

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
	public static com.liferay.knowledgebase.model.Template findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

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
	public static com.liferay.knowledgebase.model.Template[] findByGroupId_PrevAndNext(
		long templateId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(templateId, groupId,
			orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the templates where groupId = &#63;.
	*
	* @param groupId the group id to search with
	* @return the matching templates that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Template> filterFindByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGroupId(groupId);
	}

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
	public static java.util.List<com.liferay.knowledgebase.model.Template> filterFindByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

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
	public static java.util.List<com.liferay.knowledgebase.model.Template> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Finds all the templates.
	*
	* @return the templates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Template> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.knowledgebase.model.Template> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.knowledgebase.model.Template> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the templates where uuid = &#63; from the database.
	*
	* @param uuid the uuid to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Removes the template where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid to search with
	* @param groupId the group id to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchTemplateException,
			com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Removes all the templates where groupId = &#63; from the database.
	*
	* @param groupId the group id to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Removes all the templates from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the templates where uuid = &#63;.
	*
	* @param uuid the uuid to search with
	* @return the number of matching templates
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Counts all the templates where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid to search with
	* @param groupId the group id to search with
	* @return the number of matching templates
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Counts all the templates where groupId = &#63;.
	*
	* @param groupId the group id to search with
	* @return the number of matching templates
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Filters by the user's permissions and counts all the templates where groupId = &#63;.
	*
	* @param groupId the group id to search with
	* @return the number of matching templates that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	* Counts all the templates.
	*
	* @return the number of templates
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static TemplatePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TemplatePersistence)PortletBeanLocatorUtil.locate(com.liferay.knowledgebase.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					TemplatePersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(TemplatePersistence persistence) {
		_persistence = persistence;
	}

	private static TemplatePersistence _persistence;
}