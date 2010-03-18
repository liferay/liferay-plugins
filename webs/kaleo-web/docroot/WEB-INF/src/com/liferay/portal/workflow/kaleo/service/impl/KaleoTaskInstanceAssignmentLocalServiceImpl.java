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

package com.liferay.portal.workflow.kaleo.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.service.base.KaleoTaskInstanceAssignmentLocalServiceBaseImpl;
import com.liferay.portal.workflow.kaleo.util.ContextUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.Map;

/**
 * <a href="KaleoTaskInstanceAssignmentLocalServiceImpl.java.html"><b><i>View
 * Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class KaleoTaskInstanceAssignmentLocalServiceImpl
	extends KaleoTaskInstanceAssignmentLocalServiceBaseImpl {

	public KaleoTaskInstanceAssignment addKaleoTaskInstanceAssignment(
			KaleoTaskInstanceToken kaleoTaskInstanceToken,
			long kaleoTaskAssignmentId, Map<String, Serializable> context)
		throws PortalException, SystemException {

		KaleoTaskAssignment kaleoTaskAssignment =
			kaleoTaskAssignmentPersistence.findByPrimaryKey(
				kaleoTaskAssignmentId);
		User user = userPersistence.findByPrimaryKey(
			kaleoTaskInstanceToken.getUserId());
		Date now = new Date();

		long kaleoTaskInstanceAssignmentId = counterLocalService.increment();

		KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment =
			kaleoTaskInstanceAssignmentPersistence.create(
				kaleoTaskInstanceAssignmentId);

		kaleoTaskInstanceAssignment.setCompanyId(user.getCompanyId());
		kaleoTaskInstanceAssignment.setUserId(user.getUserId());
		kaleoTaskInstanceAssignment.setUserName(user.getFullName());
		kaleoTaskInstanceAssignment.setCreateDate(now);
		kaleoTaskInstanceAssignment.setModifiedDate(now);
		kaleoTaskInstanceAssignment.setKaleoTaskInstanceTokenId(
			kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId());
		kaleoTaskInstanceAssignment.setKaleoTaskId(
			kaleoTaskAssignment.getKaleoTaskId());
		kaleoTaskInstanceAssignment.setAssigneeClassName(
			kaleoTaskAssignment.getAssigneeClassName());
		kaleoTaskInstanceAssignment.setAssigneeClassPK(
			kaleoTaskAssignment.getAssigneeClassPK());
		kaleoTaskInstanceAssignment.setContext(ContextUtil.convert(context));

		kaleoTaskInstanceAssignmentPersistence.update(
			kaleoTaskInstanceAssignment, false);

		return kaleoTaskInstanceAssignment;
	}

	public KaleoTaskInstanceAssignment assignKaleoTaskInstanceAssignment(
			long kaleoTaskInstanceAssignmentId, String assigneeClassName,
			long assigneeClassPK, Map<String, Serializable> context,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment =
			kaleoTaskInstanceAssignmentPersistence.findByPrimaryKey(
				kaleoTaskInstanceAssignmentId);

		kaleoTaskInstanceAssignment.setModifiedDate(new Date());
		kaleoTaskInstanceAssignment.setAssigneeClassName(assigneeClassName);
		kaleoTaskInstanceAssignment.setAssigneeClassPK(assigneeClassPK);
		kaleoTaskInstanceAssignment.setContext(ContextUtil.convert(context));

		kaleoTaskInstanceAssignmentPersistence.update(
			kaleoTaskInstanceAssignment, false);

		return kaleoTaskInstanceAssignment;
	}

	public KaleoTaskInstanceAssignment completeKaleoTaskInstanceAssignment(
			long kaleoTaskInstanceAssignmentId)
		throws PortalException, SystemException {

		KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment =
			kaleoTaskInstanceAssignmentPersistence.findByPrimaryKey(
				kaleoTaskInstanceAssignmentId);

		kaleoTaskInstanceAssignment.setCompletionDate(new Date());

		kaleoTaskInstanceAssignmentPersistence.update(
			kaleoTaskInstanceAssignment, false);

		return kaleoTaskInstanceAssignment;
	}

	public KaleoTaskInstanceAssignment getKaleoTaskInstanceAssignment(
			long kaleoTaskInstanceTokenId, String assigneeClassName,
			long assigneeClassPK)
		throws PortalException, SystemException {

		return kaleoTaskInstanceAssignmentPersistence.findByKTITI_ACPK_ACN(
			kaleoTaskInstanceTokenId, assigneeClassName, assigneeClassPK);
	}

	public KaleoTaskInstanceAssignment
		getKaleoTaskInstanceAssignmentByKaleoTaskInstanceTokenId(
			long kaleoTaskInstanceTokenId)
		throws PortalException, SystemException {

		return kaleoTaskInstanceAssignmentPersistence.
			findByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

}