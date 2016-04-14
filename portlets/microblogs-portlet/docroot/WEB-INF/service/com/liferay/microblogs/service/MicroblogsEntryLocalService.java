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

package com.liferay.microblogs.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.microblogs.model.MicroblogsEntry;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for MicroblogsEntry. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see MicroblogsEntryLocalServiceUtil
 * @see com.liferay.microblogs.service.base.MicroblogsEntryLocalServiceBaseImpl
 * @see com.liferay.microblogs.service.impl.MicroblogsEntryLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface MicroblogsEntryLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MicroblogsEntryLocalServiceUtil} to access the microblogs entry local service. Add custom service methods to {@link com.liferay.microblogs.service.impl.MicroblogsEntryLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the microblogs entry to the database. Also notifies the appropriate model listeners.
	*
	* @param microblogsEntry the microblogs entry
	* @return the microblogs entry that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public MicroblogsEntry addMicroblogsEntry(MicroblogsEntry microblogsEntry);

	public MicroblogsEntry addMicroblogsEntry(long userId,
		java.lang.String content, int type, long parentMicroblogsEntryId,
		int socialRelationType, ServiceContext serviceContext)
		throws PortalException;

	public MicroblogsEntry addMicroblogsEntry(long userId,
		long creatorClassNameId, long creatorClassPK, java.lang.String content,
		int type, long parentMicroblogsEntryId, int socialRelationType,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Creates a new microblogs entry with the primary key. Does not add the microblogs entry to the database.
	*
	* @param microblogsEntryId the primary key for the new microblogs entry
	* @return the new microblogs entry
	*/
	public MicroblogsEntry createMicroblogsEntry(long microblogsEntryId);

	/**
	* Deletes the microblogs entry from the database. Also notifies the appropriate model listeners.
	*
	* @param microblogsEntry the microblogs entry
	* @return the microblogs entry that was removed
	* @throws PortalException
	*/
	@Indexable(type = IndexableType.DELETE)
	public MicroblogsEntry deleteMicroblogsEntry(
		MicroblogsEntry microblogsEntry) throws PortalException;

	/**
	* Deletes the microblogs entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param microblogsEntryId the primary key of the microblogs entry
	* @return the microblogs entry that was removed
	* @throws PortalException if a microblogs entry with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public MicroblogsEntry deleteMicroblogsEntry(long microblogsEntryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MicroblogsEntry fetchMicroblogsEntry(long microblogsEntryId);

	/**
	* Returns the microblogs entry with the primary key.
	*
	* @param microblogsEntryId the primary key of the microblogs entry
	* @return the microblogs entry
	* @throws PortalException if a microblogs entry with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MicroblogsEntry getMicroblogsEntry(long microblogsEntryId)
		throws PortalException;

	/**
	* Updates the microblogs entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param microblogsEntry the microblogs entry
	* @return the microblogs entry that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public MicroblogsEntry updateMicroblogsEntry(
		MicroblogsEntry microblogsEntry);

	public MicroblogsEntry updateMicroblogsEntry(long microblogsEntryId,
		java.lang.String content, int socialRelationType,
		ServiceContext serviceContext) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCompanyMicroblogsEntriesCount(long companyId);

	/**
	* Returns the number of microblogs entries.
	*
	* @return the number of microblogs entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getMicroblogsEntriesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getMicroblogsEntriesCount(long creatorClassNameId,
		java.lang.String assetTagName);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getMicroblogsEntriesCount(long creatorClassNameId,
		long creatorClassPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getMicroblogsEntriesCount(long creatorClassNameId,
		long creatorClassPK, int type);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getMicroblogsEntriesCount(long creatorClassNameId,
		long creatorClassPK, java.lang.String assetTagName, boolean andOperator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getParentMicroblogsEntryMicroblogsEntriesCount(int type,
		long parentMicroblogsEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUserMicroblogsEntriesCount(long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUserMicroblogsEntriesCount(long userId, int type);

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MicroblogsEntry> getCompanyMicroblogsEntries(long companyId,
		int start, int end);

	/**
	* Returns a range of all the microblogs entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of microblogs entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MicroblogsEntry> getMicroblogsEntries(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MicroblogsEntry> getMicroblogsEntries(long creatorClassNameId,
		int type, int start, int end, OrderByComparator obc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MicroblogsEntry> getMicroblogsEntries(long creatorClassNameId,
		java.lang.String assetTagName, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MicroblogsEntry> getMicroblogsEntries(long creatorClassNameId,
		long creatorClassPK, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MicroblogsEntry> getMicroblogsEntries(long creatorClassNameId,
		long creatorClassPK, int type, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MicroblogsEntry> getMicroblogsEntries(long creatorClassNameId,
		long creatorClassPK, java.lang.String assetTagName,
		boolean andOperator, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MicroblogsEntry> getParentMicroblogsEntryMicroblogsEntries(
		int type, long parentMicroblogsEntryId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MicroblogsEntry> getParentMicroblogsEntryMicroblogsEntries(
		int type, long parentMicroblogsEntryId, int start, int end,
		OrderByComparator<MicroblogsEntry> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MicroblogsEntry> getUserMicroblogsEntries(long userId,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MicroblogsEntry> getUserMicroblogsEntries(long userId,
		int type, int start, int end);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	public void deleteMicroblogsEntries(long creatorClassNameId,
		long creatorClassPK) throws PortalException;

	public void deleteUserMicroblogsEntries(long userId)
		throws PortalException;

	public void updateAsset(MicroblogsEntry microblogsEntry,
		long[] assetCategoryIds, java.lang.String[] assetTagNames)
		throws PortalException;
}