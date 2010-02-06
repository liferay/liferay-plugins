/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.so.service.base;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

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
}