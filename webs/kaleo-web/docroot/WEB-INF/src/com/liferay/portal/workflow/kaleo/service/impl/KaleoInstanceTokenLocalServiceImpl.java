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

package com.liferay.portal.workflow.kaleo.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenImpl;
import com.liferay.portal.workflow.kaleo.service.base.KaleoInstanceTokenLocalServiceBaseImpl;
import com.liferay.portlet.exportimport.staging.StagingUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoInstanceTokenLocalServiceImpl
	extends KaleoInstanceTokenLocalServiceBaseImpl {

	@Override
	public KaleoInstanceToken addKaleoInstanceToken(
			long parentKaleoInstanceTokenId,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getGuestOrUserId());
		KaleoInstanceToken parentKaleoInstanceToken =
			kaleoInstanceTokenPersistence.findByPrimaryKey(
				parentKaleoInstanceTokenId);
		Date now = new Date();

		long kaleoInstanceTokenId = counterLocalService.increment();

		KaleoInstanceToken kaleoInstanceToken =
			kaleoInstanceTokenPersistence.create(kaleoInstanceTokenId);

		long groupId = StagingUtil.getLiveGroupId(
			serviceContext.getScopeGroupId());

		kaleoInstanceToken.setGroupId(groupId);

		kaleoInstanceToken.setCompanyId(user.getCompanyId());
		kaleoInstanceToken.setUserId(user.getUserId());
		kaleoInstanceToken.setUserName(user.getFullName());
		kaleoInstanceToken.setCreateDate(now);
		kaleoInstanceToken.setModifiedDate(now);
		kaleoInstanceToken.setKaleoDefinitionId(
			parentKaleoInstanceToken.getKaleoDefinitionId());
		kaleoInstanceToken.setKaleoInstanceId(
			parentKaleoInstanceToken.getKaleoInstanceId());
		kaleoInstanceToken.setParentKaleoInstanceTokenId(
			parentKaleoInstanceToken.getKaleoInstanceTokenId());

		setCurrentKaleoNode(
			kaleoInstanceToken,
			parentKaleoInstanceToken.getCurrentKaleoNodeId());

		kaleoInstanceToken.setClassName(
			(String)workflowContext.get(
				WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME));

		if (workflowContext.containsKey(
				WorkflowConstants.CONTEXT_ENTRY_CLASS_PK)) {

			kaleoInstanceToken.setClassPK(
				GetterUtil.getLong(
					(String)workflowContext.get(
						WorkflowConstants.CONTEXT_ENTRY_CLASS_PK)));
		}

		kaleoInstanceToken.setCompleted(false);

		kaleoInstanceTokenPersistence.update(kaleoInstanceToken);

		return kaleoInstanceToken;
	}

	@Override
	public KaleoInstanceToken completeKaleoInstanceToken(
			long kaleoInstanceTokenId)
		throws PortalException {

		KaleoInstanceToken kaleoInstanceToken =
			kaleoInstanceTokenPersistence.findByPrimaryKey(
				kaleoInstanceTokenId);

		kaleoInstanceToken.setCompleted(true);
		kaleoInstanceToken.setCompletionDate(new Date());

		kaleoInstanceTokenPersistence.update(kaleoInstanceToken);

		return kaleoInstanceToken;
	}

	@Override
	public void deleteCompanyKaleoInstanceTokens(long companyId) {
		kaleoInstanceTokenPersistence.removeByCompanyId(companyId);
	}

	@Override
	public void deleteKaleoDefinitionKaleoInstanceTokens(
		long kaleoDefinitionId) {

		kaleoInstanceTokenPersistence.removeByKaleoDefinitionId(
			kaleoDefinitionId);
	}

	@Override
	public void deleteKaleoInstanceKaleoInstanceTokens(long kaleoInstanceId) {
		kaleoInstanceTokenPersistence.removeByKaleoInstanceId(kaleoInstanceId);
	}

	@Override
	public List<KaleoInstanceToken> getKaleoInstanceTokens(
		long parentKaleoInstanceTokenId, Date completionDate,
		ServiceContext serviceContext) {

		return kaleoInstanceTokenPersistence.findByC_PKITI_CD(
			serviceContext.getCompanyId(), parentKaleoInstanceTokenId,
			completionDate);
	}

	@Override
	public List<KaleoInstanceToken> getKaleoInstanceTokens(
		long parentKaleoInstanceTokenId, ServiceContext serviceContext) {

		return kaleoInstanceTokenPersistence.findByC_PKITI(
			serviceContext.getCompanyId(), parentKaleoInstanceTokenId);
	}

	@Override
	public int getKaleoInstanceTokensCount(
		long parentKaleoInstanceTokenId, Date completionDate,
		ServiceContext serviceContext) {

		return kaleoInstanceTokenPersistence.countByC_PKITI_CD(
			serviceContext.getCompanyId(), parentKaleoInstanceTokenId,
			completionDate);
	}

	@Override
	public int getKaleoInstanceTokensCount(
		long parentKaleoInstanceTokenId, ServiceContext serviceContext) {

		return kaleoInstanceTokenPersistence.countByC_PKITI(
			serviceContext.getCompanyId(), parentKaleoInstanceTokenId);
	}

	@Override
	public KaleoInstanceToken getRootKaleoInstanceToken(
			long kaleoInstanceId, Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException {

		KaleoInstance kaleoInstance = kaleoInstancePersistence.findByPrimaryKey(
			kaleoInstanceId);

		long rootKaleoInstanceTokenId =
			kaleoInstance.getRootKaleoInstanceTokenId();

		if (rootKaleoInstanceTokenId > 0) {
			return kaleoInstanceTokenPersistence.findByPrimaryKey(
				rootKaleoInstanceTokenId);
		}

		// Kaleo instance token

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getGuestOrUserId());
		Date now = new Date();

		rootKaleoInstanceTokenId = counterLocalService.increment();

		KaleoInstanceToken kaleoInstanceToken =
			kaleoInstanceTokenPersistence.create(rootKaleoInstanceTokenId);

		long groupId = StagingUtil.getLiveGroupId(
			serviceContext.getScopeGroupId());

		kaleoInstanceToken.setGroupId(groupId);

		kaleoInstanceToken.setCompanyId(user.getCompanyId());
		kaleoInstanceToken.setUserId(user.getUserId());
		kaleoInstanceToken.setUserName(user.getFullName());
		kaleoInstanceToken.setCreateDate(now);
		kaleoInstanceToken.setModifiedDate(now);
		kaleoInstanceToken.setKaleoDefinitionId(
			kaleoInstance.getKaleoDefinitionId());
		kaleoInstanceToken.setKaleoInstanceId(
			kaleoInstance.getKaleoInstanceId());
		kaleoInstanceToken.setParentKaleoInstanceTokenId(
			KaleoInstanceTokenImpl.DEFAULT_PARENT_KALEO_INSTANCE_TOKEN_ID);
		kaleoInstanceToken.setClassName(
			(String)workflowContext.get(
				WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME));

		if (workflowContext.containsKey(
				WorkflowConstants.CONTEXT_ENTRY_CLASS_PK)) {

			kaleoInstanceToken.setClassPK(
				GetterUtil.getLong(
					(String)workflowContext.get(
						WorkflowConstants.CONTEXT_ENTRY_CLASS_PK)));
		}

		kaleoInstanceTokenPersistence.update(kaleoInstanceToken);

		// Kaleo instance

		kaleoInstance.setRootKaleoInstanceTokenId(rootKaleoInstanceTokenId);

		kaleoInstancePersistence.update(kaleoInstance);

		return kaleoInstanceToken;
	}

	@Override
	public KaleoInstanceToken updateKaleoInstanceToken(
			long kaleoInstanceTokenId, long currentKaleoNodeId)
		throws PortalException {

		KaleoInstanceToken kaleoInstanceToken =
			kaleoInstanceTokenPersistence.findByPrimaryKey(
				kaleoInstanceTokenId);

		kaleoInstanceToken.setModifiedDate(new Date());

		setCurrentKaleoNode(kaleoInstanceToken, currentKaleoNodeId);

		kaleoInstanceTokenPersistence.update(kaleoInstanceToken);

		return kaleoInstanceToken;
	}

	protected void setCurrentKaleoNode(
			KaleoInstanceToken kaleoInstanceToken, long currentKaleoNodeId)
		throws PortalException {

		kaleoInstanceToken.setCurrentKaleoNodeId(currentKaleoNodeId);

		KaleoNode currentKaleoNode = kaleoNodeLocalService.getKaleoNode(
			currentKaleoNodeId);

		kaleoInstanceToken.setCurrentKaleoNodeName(currentKaleoNode.getName());
	}

}