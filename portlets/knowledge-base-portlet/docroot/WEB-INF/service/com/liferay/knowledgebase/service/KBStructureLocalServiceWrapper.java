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

/**
 * <p>
 * This class is a wrapper for {@link KBStructureLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KBStructureLocalService
 * @generated
 */
public class KBStructureLocalServiceWrapper implements KBStructureLocalService {
	public KBStructureLocalServiceWrapper(
		KBStructureLocalService kbStructureLocalService) {
		_kbStructureLocalService = kbStructureLocalService;
	}

	/**
	* Adds the k b structure to the database. Also notifies the appropriate model listeners.
	*
	* @param kbStructure the k b structure
	* @return the k b structure that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBStructure addKBStructure(
		com.liferay.knowledgebase.model.KBStructure kbStructure)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbStructureLocalService.addKBStructure(kbStructure);
	}

	/**
	* Creates a new k b structure with the primary key. Does not add the k b structure to the database.
	*
	* @param kbStructureId the primary key for the new k b structure
	* @return the new k b structure
	*/
	public com.liferay.knowledgebase.model.KBStructure createKBStructure(
		long kbStructureId) {
		return _kbStructureLocalService.createKBStructure(kbStructureId);
	}

	/**
	* Deletes the k b structure with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbStructureId the primary key of the k b structure
	* @throws PortalException if a k b structure with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKBStructure(long kbStructureId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbStructureLocalService.deleteKBStructure(kbStructureId);
	}

	/**
	* Deletes the k b structure from the database. Also notifies the appropriate model listeners.
	*
	* @param kbStructure the k b structure
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKBStructure(
		com.liferay.knowledgebase.model.KBStructure kbStructure)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbStructureLocalService.deleteKBStructure(kbStructure);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbStructureLocalService.dynamicQuery(dynamicQuery);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _kbStructureLocalService.dynamicQuery(dynamicQuery, start, end);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbStructureLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbStructureLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the k b structure with the primary key.
	*
	* @param kbStructureId the primary key of the k b structure
	* @return the k b structure
	* @throws PortalException if a k b structure with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBStructure getKBStructure(
		long kbStructureId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbStructureLocalService.getKBStructure(kbStructureId);
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
	public com.liferay.knowledgebase.model.KBStructure getKBStructureByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbStructureLocalService.getKBStructureByUuidAndGroupId(uuid,
			groupId);
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
	public java.util.List<com.liferay.knowledgebase.model.KBStructure> getKBStructures(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbStructureLocalService.getKBStructures(start, end);
	}

	/**
	* Returns the number of k b structures.
	*
	* @return the number of k b structures
	* @throws SystemException if a system exception occurred
	*/
	public int getKBStructuresCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbStructureLocalService.getKBStructuresCount();
	}

	/**
	* Updates the k b structure in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kbStructure the k b structure
	* @return the k b structure that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBStructure updateKBStructure(
		com.liferay.knowledgebase.model.KBStructure kbStructure)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbStructureLocalService.updateKBStructure(kbStructure);
	}

	/**
	* Updates the k b structure in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kbStructure the k b structure
	* @param merge whether to merge the k b structure with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the k b structure that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBStructure updateKBStructure(
		com.liferay.knowledgebase.model.KBStructure kbStructure, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbStructureLocalService.updateKBStructure(kbStructure, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _kbStructureLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_kbStructureLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.knowledgebase.model.KBStructure addKBStructure(
		long userId, java.lang.String localizedLanguageId,
		java.lang.String title,
		java.util.List<com.liferay.knowledgebase.model.KBStructureField> kbStructureFields,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbStructureLocalService.addKBStructure(userId,
			localizedLanguageId, title, kbStructureFields, serviceContext);
	}

	public void addKBStructureResources(
		com.liferay.knowledgebase.model.KBStructure kbStructure,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbStructureLocalService.addKBStructureResources(kbStructure,
			addCommunityPermissions, addGuestPermissions);
	}

	public void addKBStructureResources(
		com.liferay.knowledgebase.model.KBStructure kbStructure,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbStructureLocalService.addKBStructureResources(kbStructure,
			communityPermissions, guestPermissions);
	}

	public void deleteGroupKBStructures(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbStructureLocalService.deleteGroupKBStructures(groupId);
	}

	public void deleteKBStructureLocalization(long kbStructureId,
		java.lang.String localizedLanguageId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbStructureLocalService.deleteKBStructureLocalization(kbStructureId,
			localizedLanguageId, serviceContext);
	}

	public void deleteKBStructures(long[] kbStructureIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbStructureLocalService.deleteKBStructures(kbStructureIds);
	}

	public java.util.List<com.liferay.knowledgebase.model.KBStructure> getGroupKBStructures(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbStructureLocalService.getGroupKBStructures(groupId, start,
			end, orderByComparator);
	}

	public int getGroupKBStructuresCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbStructureLocalService.getGroupKBStructuresCount(groupId);
	}

	public java.util.List<com.liferay.knowledgebase.model.KBStructure> search(
		long groupId, java.lang.String title, java.lang.String content,
		java.util.Date startDate, java.util.Date endDate, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbStructureLocalService.search(groupId, title, content,
			startDate, endDate, andOperator, start, end, orderByComparator);
	}

	public com.liferay.knowledgebase.model.KBStructure updateKBStructure(
		long kbStructureId, java.lang.String localizedLanguageId,
		java.lang.String title,
		java.util.List<com.liferay.knowledgebase.model.KBStructureField> kbStructureFields,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbStructureLocalService.updateKBStructure(kbStructureId,
			localizedLanguageId, title, kbStructureFields, serviceContext);
	}

	public void updateKBStructureResources(
		com.liferay.knowledgebase.model.KBStructure kbStructure,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbStructureLocalService.updateKBStructureResources(kbStructure,
			communityPermissions, guestPermissions);
	}

	public KBStructureLocalService getWrappedKBStructureLocalService() {
		return _kbStructureLocalService;
	}

	public void setWrappedKBStructureLocalService(
		KBStructureLocalService kbStructureLocalService) {
		_kbStructureLocalService = kbStructureLocalService;
	}

	private KBStructureLocalService _kbStructureLocalService;
}