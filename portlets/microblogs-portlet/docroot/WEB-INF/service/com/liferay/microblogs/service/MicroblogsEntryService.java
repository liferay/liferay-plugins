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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseService;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service interface for MicroblogsEntry. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see MicroblogsEntryServiceUtil
 * @see com.liferay.microblogs.service.base.MicroblogsEntryServiceBaseImpl
 * @see com.liferay.microblogs.service.impl.MicroblogsEntryServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface MicroblogsEntryService extends BaseService, InvokableService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MicroblogsEntryServiceUtil} to access the microblogs entry remote service. Add custom service methods to {@link com.liferay.microblogs.service.impl.MicroblogsEntryServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public com.liferay.microblogs.model.MicroblogsEntry addMicroblogsEntry(
		long userId, java.lang.String content, int type,
		long parentMicroblogsEntryId, int socialRelationType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws PortalException;

	public com.liferay.microblogs.model.MicroblogsEntry deleteMicroblogsEntry(
		long microblogsEntryId) throws PortalException;

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		java.lang.String assetTagName, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		int start, int end) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getMicroblogsEntriesCount() throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getMicroblogsEntriesCount(java.lang.String assetTagName)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.microblogs.model.MicroblogsEntry getMicroblogsEntry(
		long microblogsEntryId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getUserMicroblogsEntries(
		long microblogsEntryUserId, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getUserMicroblogsEntries(
		long microblogsEntryUserId, int type, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUserMicroblogsEntriesCount(long microblogsEntryUserId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUserMicroblogsEntriesCount(long microblogsEntryUserId,
		int type) throws PortalException;

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	public com.liferay.microblogs.model.MicroblogsEntry updateMicroblogsEntry(
		long microblogsEntryId, java.lang.String content,
		int socialRelationType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws PortalException;
}