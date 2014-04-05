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

package com.liferay.knowledgebase.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link KBTemplateLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see KBTemplateLocalService
 * @generated
 */
public class KBTemplateLocalServiceWrapper implements KBTemplateLocalService,
	ServiceWrapper<KBTemplateLocalService> {
	public KBTemplateLocalServiceWrapper(
		KBTemplateLocalService kbTemplateLocalService) {
		_kbTemplateLocalService = kbTemplateLocalService;
	}

	/**
	* Adds the k b template to the database. Also notifies the appropriate model listeners.
	*
	* @param kbTemplate the k b template
	* @return the k b template that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.knowledgebase.model.KBTemplate addKBTemplate(
		com.liferay.knowledgebase.model.KBTemplate kbTemplate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateLocalService.addKBTemplate(kbTemplate);
	}

	/**
	* Creates a new k b template with the primary key. Does not add the k b template to the database.
	*
	* @param kbTemplateId the primary key for the new k b template
	* @return the new k b template
	*/
	@Override
	public com.liferay.knowledgebase.model.KBTemplate createKBTemplate(
		long kbTemplateId) {
		return _kbTemplateLocalService.createKBTemplate(kbTemplateId);
	}

	/**
	* Deletes the k b template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbTemplateId the primary key of the k b template
	* @return the k b template that was removed
	* @throws PortalException if a k b template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.knowledgebase.model.KBTemplate deleteKBTemplate(
		long kbTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateLocalService.deleteKBTemplate(kbTemplateId);
	}

	/**
	* Deletes the k b template from the database. Also notifies the appropriate model listeners.
	*
	* @param kbTemplate the k b template
	* @return the k b template that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.knowledgebase.model.KBTemplate deleteKBTemplate(
		com.liferay.knowledgebase.model.KBTemplate kbTemplate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateLocalService.deleteKBTemplate(kbTemplate);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _kbTemplateLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.knowledgebase.model.KBTemplate fetchKBTemplate(
		long kbTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateLocalService.fetchKBTemplate(kbTemplateId);
	}

	/**
	* Returns the k b template with the matching UUID and company.
	*
	* @param uuid the k b template's UUID
	* @param companyId the primary key of the company
	* @return the matching k b template, or <code>null</code> if a matching k b template could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.knowledgebase.model.KBTemplate fetchKBTemplateByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateLocalService.fetchKBTemplateByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the k b template matching the UUID and group.
	*
	* @param uuid the k b template's UUID
	* @param groupId the primary key of the group
	* @return the matching k b template, or <code>null</code> if a matching k b template could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.knowledgebase.model.KBTemplate fetchKBTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateLocalService.fetchKBTemplateByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the k b template with the primary key.
	*
	* @param kbTemplateId the primary key of the k b template
	* @return the k b template
	* @throws PortalException if a k b template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.knowledgebase.model.KBTemplate getKBTemplate(
		long kbTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateLocalService.getKBTemplate(kbTemplateId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the k b template with the matching UUID and company.
	*
	* @param uuid the k b template's UUID
	* @param companyId the primary key of the company
	* @return the matching k b template
	* @throws PortalException if a matching k b template could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.knowledgebase.model.KBTemplate getKBTemplateByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateLocalService.getKBTemplateByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the k b template matching the UUID and group.
	*
	* @param uuid the k b template's UUID
	* @param groupId the primary key of the group
	* @return the matching k b template
	* @throws PortalException if a matching k b template could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.knowledgebase.model.KBTemplate getKBTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateLocalService.getKBTemplateByUuidAndGroupId(uuid,
			groupId);
	}

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
	@Override
	public java.util.List<com.liferay.knowledgebase.model.KBTemplate> getKBTemplates(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateLocalService.getKBTemplates(start, end);
	}

	/**
	* Returns the number of k b templates.
	*
	* @return the number of k b templates
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getKBTemplatesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateLocalService.getKBTemplatesCount();
	}

	/**
	* Updates the k b template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kbTemplate the k b template
	* @return the k b template that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.knowledgebase.model.KBTemplate updateKBTemplate(
		com.liferay.knowledgebase.model.KBTemplate kbTemplate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateLocalService.updateKBTemplate(kbTemplate);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _kbTemplateLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_kbTemplateLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _kbTemplateLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.knowledgebase.model.KBTemplate addKBTemplate(
		long userId, java.lang.String title, java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateLocalService.addKBTemplate(userId, title, content,
			serviceContext);
	}

	@Override
	public void deleteGroupKBTemplates(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbTemplateLocalService.deleteGroupKBTemplates(groupId);
	}

	@Override
	public void deleteKBTemplates(long[] kbTemplateIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbTemplateLocalService.deleteKBTemplates(kbTemplateIds);
	}

	@Override
	public java.util.List<com.liferay.knowledgebase.model.KBTemplate> getGroupKBTemplates(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateLocalService.getGroupKBTemplates(groupId, start, end,
			orderByComparator);
	}

	@Override
	public int getGroupKBTemplatesCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateLocalService.getGroupKBTemplatesCount(groupId);
	}

	@Override
	public java.util.List<com.liferay.knowledgebase.model.KBTemplate> search(
		long groupId, java.lang.String title, java.lang.String content,
		java.util.Date startDate, java.util.Date endDate, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateLocalService.search(groupId, title, content,
			startDate, endDate, andOperator, start, end, orderByComparator);
	}

	@Override
	public com.liferay.knowledgebase.model.KBTemplate updateKBTemplate(
		long kbTemplateId, java.lang.String title, java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplateLocalService.updateKBTemplate(kbTemplateId, title,
			content, serviceContext);
	}

	@Override
	public void updateKBTemplateResources(
		com.liferay.knowledgebase.model.KBTemplate kbTemplate,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbTemplateLocalService.updateKBTemplateResources(kbTemplate,
			groupPermissions, guestPermissions);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public KBTemplateLocalService getWrappedKBTemplateLocalService() {
		return _kbTemplateLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedKBTemplateLocalService(
		KBTemplateLocalService kbTemplateLocalService) {
		_kbTemplateLocalService = kbTemplateLocalService;
	}

	@Override
	public KBTemplateLocalService getWrappedService() {
		return _kbTemplateLocalService;
	}

	@Override
	public void setWrappedService(KBTemplateLocalService kbTemplateLocalService) {
		_kbTemplateLocalService = kbTemplateLocalService;
	}

	private KBTemplateLocalService _kbTemplateLocalService;
}