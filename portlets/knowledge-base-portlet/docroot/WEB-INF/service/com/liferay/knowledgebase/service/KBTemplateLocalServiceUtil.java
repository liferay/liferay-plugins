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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for KBTemplate. This utility wraps
 * {@link com.liferay.knowledgebase.service.impl.KBTemplateLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see KBTemplateLocalService
 * @see com.liferay.knowledgebase.service.base.KBTemplateLocalServiceBaseImpl
 * @see com.liferay.knowledgebase.service.impl.KBTemplateLocalServiceImpl
 * @generated
 */
public class KBTemplateLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.knowledgebase.service.impl.KBTemplateLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the k b template to the database. Also notifies the appropriate model listeners.
	*
	* @param kbTemplate the k b template
	* @return the k b template that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBTemplate addKBTemplate(
		com.liferay.knowledgebase.model.KBTemplate kbTemplate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addKBTemplate(kbTemplate);
	}

	/**
	* Creates a new k b template with the primary key. Does not add the k b template to the database.
	*
	* @param kbTemplateId the primary key for the new k b template
	* @return the new k b template
	*/
	public static com.liferay.knowledgebase.model.KBTemplate createKBTemplate(
		long kbTemplateId) {
		return getService().createKBTemplate(kbTemplateId);
	}

	/**
	* Deletes the k b template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbTemplateId the primary key of the k b template
	* @return the k b template that was removed
	* @throws PortalException if a k b template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBTemplate deleteKBTemplate(
		long kbTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteKBTemplate(kbTemplateId);
	}

	/**
	* Deletes the k b template from the database. Also notifies the appropriate model listeners.
	*
	* @param kbTemplate the k b template
	* @return the k b template that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBTemplate deleteKBTemplate(
		com.liferay.knowledgebase.model.KBTemplate kbTemplate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteKBTemplate(kbTemplate);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
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
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.knowledgebase.model.KBTemplate fetchKBTemplate(
		long kbTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchKBTemplate(kbTemplateId);
	}

	/**
	* Returns the k b template with the matching UUID and company.
	*
	* @param uuid the k b template's UUID
	* @param companyId the primary key of the company
	* @return the matching k b template, or <code>null</code> if a matching k b template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBTemplate fetchKBTemplateByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchKBTemplateByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the k b template matching the UUID and group.
	*
	* @param uuid the k b template's UUID
	* @param groupId the primary key of the group
	* @return the matching k b template, or <code>null</code> if a matching k b template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBTemplate fetchKBTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchKBTemplateByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the k b template with the primary key.
	*
	* @param kbTemplateId the primary key of the k b template
	* @return the k b template
	* @throws PortalException if a k b template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBTemplate getKBTemplate(
		long kbTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKBTemplate(kbTemplateId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static com.liferay.knowledgebase.model.KBTemplate getKBTemplateByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKBTemplateByUuidAndCompanyId(uuid, companyId);
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
	public static com.liferay.knowledgebase.model.KBTemplate getKBTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKBTemplateByUuidAndGroupId(uuid, groupId);
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
	public static java.util.List<com.liferay.knowledgebase.model.KBTemplate> getKBTemplates(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKBTemplates(start, end);
	}

	/**
	* Returns the number of k b templates.
	*
	* @return the number of k b templates
	* @throws SystemException if a system exception occurred
	*/
	public static int getKBTemplatesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKBTemplatesCount();
	}

	/**
	* Updates the k b template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kbTemplate the k b template
	* @return the k b template that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBTemplate updateKBTemplate(
		com.liferay.knowledgebase.model.KBTemplate kbTemplate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateKBTemplate(kbTemplate);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.knowledgebase.model.KBTemplate addKBTemplate(
		long userId, java.lang.String title, java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addKBTemplate(userId, title, content, serviceContext);
	}

	public static void deleteGroupKBTemplates(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteGroupKBTemplates(groupId);
	}

	public static void deleteKBTemplates(long[] kbTemplateIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKBTemplates(kbTemplateIds);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBTemplate> getGroupKBTemplates(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getGroupKBTemplates(groupId, start, end, orderByComparator);
	}

	public static int getGroupKBTemplatesCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupKBTemplatesCount(groupId);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBTemplate> search(
		long groupId, java.lang.String title, java.lang.String content,
		java.util.Date startDate, java.util.Date endDate, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(groupId, title, content, startDate, endDate,
			andOperator, start, end, orderByComparator);
	}

	public static com.liferay.knowledgebase.model.KBTemplate updateKBTemplate(
		long kbTemplateId, java.lang.String title, java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateKBTemplate(kbTemplateId, title, content,
			serviceContext);
	}

	public static void updateKBTemplateResources(
		com.liferay.knowledgebase.model.KBTemplate kbTemplate,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.updateKBTemplateResources(kbTemplate, groupPermissions,
			guestPermissions);
	}

	public static void clearService() {
		_service = null;
	}

	public static KBTemplateLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					KBTemplateLocalService.class.getName());

			if (invokableLocalService instanceof KBTemplateLocalService) {
				_service = (KBTemplateLocalService)invokableLocalService;
			}
			else {
				_service = new KBTemplateLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(KBTemplateLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(KBTemplateLocalService service) {
	}

	private static KBTemplateLocalService _service;
}