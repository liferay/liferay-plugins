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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for KBFolder. This utility wraps
 * {@link com.liferay.knowledgebase.service.impl.KBFolderLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see KBFolderLocalService
 * @see com.liferay.knowledgebase.service.base.KBFolderLocalServiceBaseImpl
 * @see com.liferay.knowledgebase.service.impl.KBFolderLocalServiceImpl
 * @generated
 */
@ProviderType
public class KBFolderLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.knowledgebase.service.impl.KBFolderLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the k b folder to the database. Also notifies the appropriate model listeners.
	*
	* @param kbFolder the k b folder
	* @return the k b folder that was added
	*/
	public static com.liferay.knowledgebase.model.KBFolder addKBFolder(
		com.liferay.knowledgebase.model.KBFolder kbFolder) {
		return getService().addKBFolder(kbFolder);
	}

	public static com.liferay.knowledgebase.model.KBFolder addKBFolder(
		long userId, long groupId, long parentResourceClassNameId,
		long parentResourcePrimKey, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addKBFolder(userId, groupId, parentResourceClassNameId,
			parentResourcePrimKey, name, description, serviceContext);
	}

	/**
	* Creates a new k b folder with the primary key. Does not add the k b folder to the database.
	*
	* @param kbFolderId the primary key for the new k b folder
	* @return the new k b folder
	*/
	public static com.liferay.knowledgebase.model.KBFolder createKBFolder(
		long kbFolderId) {
		return getService().createKBFolder(kbFolderId);
	}

	/**
	* Deletes the k b folder from the database. Also notifies the appropriate model listeners.
	*
	* @param kbFolder the k b folder
	* @return the k b folder that was removed
	*/
	public static com.liferay.knowledgebase.model.KBFolder deleteKBFolder(
		com.liferay.knowledgebase.model.KBFolder kbFolder) {
		return getService().deleteKBFolder(kbFolder);
	}

	/**
	* Deletes the k b folder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbFolderId the primary key of the k b folder
	* @return the k b folder that was removed
	* @throws PortalException if a k b folder with the primary key could not be found
	*/
	public static com.liferay.knowledgebase.model.KBFolder deleteKBFolder(
		long kbFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteKBFolder(kbFolderId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.knowledgebase.model.KBFolder fetchFirstChildKBFolder(
		long groupId, long kbFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().fetchFirstChildKBFolder(groupId, kbFolderId);
	}

	public static com.liferay.knowledgebase.model.KBFolder fetchKBFolder(
		long kbFolderId) {
		return getService().fetchKBFolder(kbFolderId);
	}

	public static com.liferay.knowledgebase.model.KBFolder fetchKBFolderByUrlTitle(
		long groupId, long parentKbFolderId, java.lang.String urlTitle)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .fetchKBFolderByUrlTitle(groupId, parentKbFolderId, urlTitle);
	}

	/**
	* Returns the k b folder matching the UUID and group.
	*
	* @param uuid the k b folder's UUID
	* @param groupId the primary key of the group
	* @return the matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	public static com.liferay.knowledgebase.model.KBFolder fetchKBFolderByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchKBFolderByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.portlet.exportimport.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the k b folder with the primary key.
	*
	* @param kbFolderId the primary key of the k b folder
	* @return the k b folder
	* @throws PortalException if a k b folder with the primary key could not be found
	*/
	public static com.liferay.knowledgebase.model.KBFolder getKBFolder(
		long kbFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getKBFolder(kbFolderId);
	}

	public static com.liferay.knowledgebase.model.KBFolder getKBFolderByUrlTitle(
		long groupId, long parentKbFolderId, java.lang.String urlTitle)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getKBFolderByUrlTitle(groupId, parentKbFolderId, urlTitle);
	}

	/**
	* Returns the k b folder matching the UUID and group.
	*
	* @param uuid the k b folder's UUID
	* @param groupId the primary key of the group
	* @return the matching k b folder
	* @throws PortalException if a matching k b folder could not be found
	*/
	public static com.liferay.knowledgebase.model.KBFolder getKBFolderByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getKBFolderByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFolder> getKBFolders(
		long groupId, long parentKBFolderId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getKBFolders(groupId, parentKBFolderId, start, end);
	}

	/**
	* Returns a range of all the k b folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of k b folders
	* @param end the upper bound of the range of k b folders (not inclusive)
	* @return the range of k b folders
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBFolder> getKBFolders(
		int start, int end) {
		return getService().getKBFolders(start, end);
	}

	/**
	* Returns all the k b folders matching the UUID and company.
	*
	* @param uuid the UUID of the k b folders
	* @param companyId the primary key of the company
	* @return the matching k b folders, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBFolder> getKBFoldersByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getKBFoldersByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of k b folders matching the UUID and company.
	*
	* @param uuid the UUID of the k b folders
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of k b folders
	* @param end the upper bound of the range of k b folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching k b folders, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBFolder> getKBFoldersByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledgebase.model.KBFolder> orderByComparator) {
		return getService()
				   .getKBFoldersByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of k b folders.
	*
	* @return the number of k b folders
	*/
	public static int getKBFoldersCount() {
		return getService().getKBFoldersCount();
	}

	public static int getKBFoldersCount(long groupId, long parentKBFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getKBFoldersCount(groupId, parentKBFolderId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static void moveKBFolder(long kbFolderId, long parentKBFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().moveKBFolder(kbFolderId, parentKBFolderId);
	}

	/**
	* Updates the k b folder in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kbFolder the k b folder
	* @return the k b folder that was updated
	*/
	public static com.liferay.knowledgebase.model.KBFolder updateKBFolder(
		com.liferay.knowledgebase.model.KBFolder kbFolder) {
		return getService().updateKBFolder(kbFolder);
	}

	public static com.liferay.knowledgebase.model.KBFolder updateKBFolder(
		long parentResourceClassNameId, long parentResourcePrimKey,
		long kbFolderId, java.lang.String name, java.lang.String description)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateKBFolder(parentResourceClassNameId,
			parentResourcePrimKey, kbFolderId, name, description);
	}

	public static void clearService() {
		_service = null;
	}

	public static KBFolderLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					KBFolderLocalService.class.getName());

			if (invokableLocalService instanceof KBFolderLocalService) {
				_service = (KBFolderLocalService)invokableLocalService;
			}
			else {
				_service = new KBFolderLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(KBFolderLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(KBFolderLocalService service) {
	}

	private static KBFolderLocalService _service;
}