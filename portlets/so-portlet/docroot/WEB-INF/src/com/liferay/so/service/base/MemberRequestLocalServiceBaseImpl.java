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

package com.liferay.so.service.base;

import com.liferay.counter.service.CounterLocalService;
import com.liferay.counter.service.CounterService;

import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.so.model.MemberRequest;
import com.liferay.so.service.MemberRequestLocalService;
import com.liferay.so.service.ProjectsEntryLocalService;
import com.liferay.so.service.persistence.MemberRequestPersistence;
import com.liferay.so.service.persistence.ProjectsEntryPersistence;

import java.util.List;

/**
 * <a href="MemberRequestLocalServiceBaseImpl.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 */
public abstract class MemberRequestLocalServiceBaseImpl
	implements MemberRequestLocalService {
	public MemberRequest addMemberRequest(MemberRequest memberRequest)
		throws SystemException {
		memberRequest.setNew(true);

		return memberRequestPersistence.update(memberRequest, false);
	}

	public MemberRequest createMemberRequest(long memberRequestId) {
		return memberRequestPersistence.create(memberRequestId);
	}

	public void deleteMemberRequest(long memberRequestId)
		throws PortalException, SystemException {
		memberRequestPersistence.remove(memberRequestId);
	}

	public void deleteMemberRequest(MemberRequest memberRequest)
		throws SystemException {
		memberRequestPersistence.remove(memberRequest);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return memberRequestPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return memberRequestPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	public MemberRequest getMemberRequest(long memberRequestId)
		throws PortalException, SystemException {
		return memberRequestPersistence.findByPrimaryKey(memberRequestId);
	}

	public List<MemberRequest> getMemberRequests(int start, int end)
		throws SystemException {
		return memberRequestPersistence.findAll(start, end);
	}

	public int getMemberRequestsCount() throws SystemException {
		return memberRequestPersistence.countAll();
	}

	public MemberRequest updateMemberRequest(MemberRequest memberRequest)
		throws SystemException {
		memberRequest.setNew(false);

		return memberRequestPersistence.update(memberRequest, true);
	}

	public MemberRequest updateMemberRequest(MemberRequest memberRequest,
		boolean merge) throws SystemException {
		memberRequest.setNew(false);

		return memberRequestPersistence.update(memberRequest, merge);
	}

	public MemberRequestLocalService getMemberRequestLocalService() {
		return memberRequestLocalService;
	}

	public void setMemberRequestLocalService(
		MemberRequestLocalService memberRequestLocalService) {
		this.memberRequestLocalService = memberRequestLocalService;
	}

	public MemberRequestPersistence getMemberRequestPersistence() {
		return memberRequestPersistence;
	}

	public void setMemberRequestPersistence(
		MemberRequestPersistence memberRequestPersistence) {
		this.memberRequestPersistence = memberRequestPersistence;
	}

	public ProjectsEntryLocalService getProjectsEntryLocalService() {
		return projectsEntryLocalService;
	}

	public void setProjectsEntryLocalService(
		ProjectsEntryLocalService projectsEntryLocalService) {
		this.projectsEntryLocalService = projectsEntryLocalService;
	}

	public ProjectsEntryPersistence getProjectsEntryPersistence() {
		return projectsEntryPersistence;
	}

	public void setProjectsEntryPersistence(
		ProjectsEntryPersistence projectsEntryPersistence) {
		this.projectsEntryPersistence = projectsEntryPersistence;
	}

	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	public CounterService getCounterService() {
		return counterService;
	}

	public void setCounterService(CounterService counterService) {
		this.counterService = counterService;
	}

	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	public ResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	protected void runSQL(String sql) throws SystemException {
		try {
			DB db = DBFactoryUtil.getDB();

			db.runSQL(sql);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(name = "com.liferay.so.service.MemberRequestLocalService")
	protected MemberRequestLocalService memberRequestLocalService;
	@BeanReference(name = "com.liferay.so.service.persistence.MemberRequestPersistence")
	protected MemberRequestPersistence memberRequestPersistence;
	@BeanReference(name = "com.liferay.so.service.ProjectsEntryLocalService")
	protected ProjectsEntryLocalService projectsEntryLocalService;
	@BeanReference(name = "com.liferay.so.service.persistence.ProjectsEntryPersistence")
	protected ProjectsEntryPersistence projectsEntryPersistence;
	@BeanReference(name = "com.liferay.counter.service.CounterLocalService")
	protected CounterLocalService counterLocalService;
	@BeanReference(name = "com.liferay.counter.service.CounterService")
	protected CounterService counterService;
	@BeanReference(name = "com.liferay.portal.service.ResourceLocalService")
	protected ResourceLocalService resourceLocalService;
	@BeanReference(name = "com.liferay.portal.service.ResourceService")
	protected ResourceService resourceService;
	@BeanReference(name = "com.liferay.portal.service.persistence.ResourcePersistence")
	protected ResourcePersistence resourcePersistence;
	@BeanReference(name = "com.liferay.portal.service.UserLocalService")
	protected UserLocalService userLocalService;
	@BeanReference(name = "com.liferay.portal.service.UserService")
	protected UserService userService;
	@BeanReference(name = "com.liferay.portal.service.persistence.UserPersistence")
	protected UserPersistence userPersistence;
}