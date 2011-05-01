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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * The interface for the k b structure local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBStructureLocalServiceUtil
 * @see com.liferay.knowledgebase.service.base.KBStructureLocalServiceBaseImpl
 * @see com.liferay.knowledgebase.service.impl.KBStructureLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface KBStructureLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KBStructureLocalServiceUtil} to access the k b structure local service. Add custom service methods to {@link com.liferay.knowledgebase.service.impl.KBStructureLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the k b structure to the database. Also notifies the appropriate model listeners.
	*
	* @param kbStructure the k b structure to add
	* @return the k b structure that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBStructure addKBStructure(
		com.liferay.knowledgebase.model.KBStructure kbStructure)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new k b structure with the primary key. Does not add the k b structure to the database.
	*
	* @param kbStructureId the primary key for the new k b structure
	* @return the new k b structure
	*/
	public com.liferay.knowledgebase.model.KBStructure createKBStructure(
		long kbStructureId);

	/**
	* Deletes the k b structure with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbStructureId the primary key of the k b structure to delete
	* @throws PortalException if a k b structure with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKBStructure(long kbStructureId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the k b structure from the database. Also notifies the appropriate model listeners.
	*
	* @param kbStructure the k b structure to delete
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKBStructure(
		com.liferay.knowledgebase.model.KBStructure kbStructure)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets the k b structure with the primary key.
	*
	* @param kbStructureId the primary key of the k b structure to get
	* @return the k b structure
	* @throws PortalException if a k b structure with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.knowledgebase.model.KBStructure getKBStructure(
		long kbStructureId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets the k b structure with the UUID and group id.
	*
	* @param uuid the UUID of k b structure to get
	* @param groupId the group id of the k b structure to get
	* @return the k b structure
	* @throws PortalException if a k b structure with the UUID and group id could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.knowledgebase.model.KBStructure getKBStructureByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets a range of all the k b structures.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of k b structures to return
	* @param end the upper bound of the range of k b structures to return (not inclusive)
	* @return the range of k b structures
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.knowledgebase.model.KBStructure> getKBStructures(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets the number of k b structures.
	*
	* @return the number of k b structures
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKBStructuresCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the k b structure in the database. Also notifies the appropriate model listeners.
	*
	* @param kbStructure the k b structure to update
	* @return the k b structure that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBStructure updateKBStructure(
		com.liferay.knowledgebase.model.KBStructure kbStructure)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the k b structure in the database. Also notifies the appropriate model listeners.
	*
	* @param kbStructure the k b structure to update
	* @param merge whether to merge the k b structure with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the k b structure that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBStructure updateKBStructure(
		com.liferay.knowledgebase.model.KBStructure kbStructure, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	public com.liferay.knowledgebase.model.KBStructure addKBStructure(
		long userId, java.lang.String localizedLanguageId,
		java.lang.String title,
		java.util.List<com.liferay.knowledgebase.model.KBStructureField> kbStructureFields,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void addKBStructureResources(
		com.liferay.knowledgebase.model.KBStructure kbStructure,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void addKBStructureResources(
		com.liferay.knowledgebase.model.KBStructure kbStructure,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void deleteGroupKBStructures(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void deleteKBStructureLocalization(long kbStructureId,
		java.lang.String localizedLanguageId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void deleteKBStructures(long[] kbStructureIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.knowledgebase.model.KBStructure> getGroupKBStructures(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getGroupKBStructuresCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.knowledgebase.model.KBStructure> search(
		long groupId, java.lang.String title, java.lang.String content,
		java.util.Date startDate, java.util.Date endDate, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.knowledgebase.model.KBStructure updateKBStructure(
		long kbStructureId, java.lang.String localizedLanguageId,
		java.lang.String title,
		java.util.List<com.liferay.knowledgebase.model.KBStructureField> kbStructureFields,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void updateKBStructureResources(
		com.liferay.knowledgebase.model.KBStructure kbStructure,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}