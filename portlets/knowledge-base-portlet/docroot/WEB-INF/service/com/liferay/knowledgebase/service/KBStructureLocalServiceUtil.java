/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the k b structure local service. This utility wraps {@link com.liferay.knowledgebase.service.impl.KBStructureLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBStructureLocalService
 * @see com.liferay.knowledgebase.service.base.KBStructureLocalServiceBaseImpl
 * @see com.liferay.knowledgebase.service.impl.KBStructureLocalServiceImpl
 * @generated
 */
public class KBStructureLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.knowledgebase.service.impl.KBStructureLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the k b structure to the database. Also notifies the appropriate model listeners.
	*
	* @param kbStructure the k b structure
	* @return the k b structure that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBStructure addKBStructure(
		com.liferay.knowledgebase.model.KBStructure kbStructure)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addKBStructure(kbStructure);
	}

	/**
	* Creates a new k b structure with the primary key. Does not add the k b structure to the database.
	*
	* @param kbStructureId the primary key for the new k b structure
	* @return the new k b structure
	*/
	public static com.liferay.knowledgebase.model.KBStructure createKBStructure(
		long kbStructureId) {
		return getService().createKBStructure(kbStructureId);
	}

	/**
	* Deletes the k b structure with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbStructureId the primary key of the k b structure
	* @throws PortalException if a k b structure with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteKBStructure(long kbStructureId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKBStructure(kbStructureId);
	}

	/**
	* Deletes the k b structure from the database. Also notifies the appropriate model listeners.
	*
	* @param kbStructure the k b structure
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteKBStructure(
		com.liferay.knowledgebase.model.KBStructure kbStructure)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKBStructure(kbStructure);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
	* Returns the k b structure with the primary key.
	*
	* @param kbStructureId the primary key of the k b structure
	* @return the k b structure
	* @throws PortalException if a k b structure with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBStructure getKBStructure(
		long kbStructureId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKBStructure(kbStructureId);
	}

	/**
	* Returns the k b structure with the UUID in the group.
	*
	* @param uuid the UUID of k b structure
	* @param groupId the group id of the k b structure
	* @return the k b structure
	* @throws PortalException if a k b structure with the UUID in the group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBStructure getKBStructureByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKBStructureByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the k b structures.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of k b structures
	* @param end the upper bound of the range of k b structures (not inclusive)
	* @return the range of k b structures
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBStructure> getKBStructures(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKBStructures(start, end);
	}

	/**
	* Returns the number of k b structures.
	*
	* @return the number of k b structures
	* @throws SystemException if a system exception occurred
	*/
	public static int getKBStructuresCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKBStructuresCount();
	}

	/**
	* Updates the k b structure in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kbStructure the k b structure
	* @return the k b structure that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBStructure updateKBStructure(
		com.liferay.knowledgebase.model.KBStructure kbStructure)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateKBStructure(kbStructure);
	}

	/**
	* Updates the k b structure in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kbStructure the k b structure
	* @param merge whether to merge the k b structure with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the k b structure that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBStructure updateKBStructure(
		com.liferay.knowledgebase.model.KBStructure kbStructure, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateKBStructure(kbStructure, merge);
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

	public static com.liferay.knowledgebase.model.KBStructure addKBStructure(
		long userId, java.lang.String localizedLanguageId,
		java.lang.String title,
		java.util.List<com.liferay.knowledgebase.model.KBStructureField> kbStructureFields,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKBStructure(userId, localizedLanguageId, title,
			kbStructureFields, serviceContext);
	}

	public static void addKBStructureResources(
		com.liferay.knowledgebase.model.KBStructure kbStructure,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addKBStructureResources(kbStructure, addCommunityPermissions,
			addGuestPermissions);
	}

	public static void addKBStructureResources(
		com.liferay.knowledgebase.model.KBStructure kbStructure,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addKBStructureResources(kbStructure, communityPermissions,
			guestPermissions);
	}

	public static void deleteGroupKBStructures(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteGroupKBStructures(groupId);
	}

	public static void deleteKBStructureLocalization(long kbStructureId,
		java.lang.String localizedLanguageId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteKBStructureLocalization(kbStructureId, localizedLanguageId,
			serviceContext);
	}

	public static void deleteKBStructures(long[] kbStructureIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKBStructures(kbStructureIds);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBStructure> getGroupKBStructures(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getGroupKBStructures(groupId, start, end, orderByComparator);
	}

	public static int getGroupKBStructuresCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupKBStructuresCount(groupId);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBStructure> search(
		long groupId, java.lang.String title, java.lang.String content,
		java.util.Date startDate, java.util.Date endDate, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(groupId, title, content, startDate, endDate,
			andOperator, start, end, orderByComparator);
	}

	public static com.liferay.knowledgebase.model.KBStructure updateKBStructure(
		long kbStructureId, java.lang.String localizedLanguageId,
		java.lang.String title,
		java.util.List<com.liferay.knowledgebase.model.KBStructureField> kbStructureFields,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateKBStructure(kbStructureId, localizedLanguageId,
			title, kbStructureFields, serviceContext);
	}

	public static void updateKBStructureResources(
		com.liferay.knowledgebase.model.KBStructure kbStructure,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.updateKBStructureResources(kbStructure, communityPermissions,
			guestPermissions);
	}

	public static void clearService() {
		_service = null;
	}

	public static KBStructureLocalService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					KBStructureLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					KBStructureLocalService.class.getName(), portletClassLoader);

			_service = new KBStructureLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(KBStructureLocalServiceUtil.class,
				"_service");
			MethodCache.remove(KBStructureLocalService.class);
		}

		return _service;
	}

	public void setService(KBStructureLocalService service) {
		MethodCache.remove(KBStructureLocalService.class);

		_service = service;

		ReferenceRegistry.registerReference(KBStructureLocalServiceUtil.class,
			"_service");
		MethodCache.remove(KBStructureLocalService.class);
	}

	private static KBStructureLocalService _service;
}