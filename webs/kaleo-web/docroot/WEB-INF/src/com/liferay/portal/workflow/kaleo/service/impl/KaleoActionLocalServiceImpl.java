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
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.definition.Action;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.service.base.KaleoActionLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoActionLocalServiceImpl
	extends KaleoActionLocalServiceBaseImpl {

	@Override
	public KaleoAction addKaleoAction(
			String kaleoClassName, long kaleoClassPK, long kaleoDefinitionId,
			String kaleoNodeName, Action action, ServiceContext serviceContext)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getGuestOrUserId());
		Date now = new Date();

		long kaleoActionId = counterLocalService.increment();

		KaleoAction kaleoAction = kaleoActionPersistence.create(kaleoActionId);

		kaleoAction.setCompanyId(user.getCompanyId());
		kaleoAction.setUserId(user.getUserId());
		kaleoAction.setUserName(user.getFullName());
		kaleoAction.setCreateDate(now);
		kaleoAction.setModifiedDate(now);
		kaleoAction.setKaleoClassName(kaleoClassName);
		kaleoAction.setKaleoClassPK(kaleoClassPK);
		kaleoAction.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoAction.setKaleoNodeName(kaleoNodeName);
		kaleoAction.setName(action.getName());
		kaleoAction.setDescription(action.getDescription());
		kaleoAction.setExecutionType(action.getExecutionType().getValue());
		kaleoAction.setScript(action.getScript());
		kaleoAction.setScriptLanguage(action.getScriptLanguage().getValue());
		kaleoAction.setScriptRequiredContexts(
			action.getScriptRequiredContexts());
		kaleoAction.setPriority(action.getPriority());

		kaleoActionPersistence.update(kaleoAction);

		return kaleoAction;
	}

	@Override
	public void deleteCompanyKaleoActions(long companyId) {
		kaleoActionPersistence.removeByCompanyId(companyId);
	}

	@Override
	public void deleteKaleoDefinitionKaleoActions(long kaleoDefinitionId) {
		kaleoActionPersistence.removeByKaleoDefinitionId(kaleoDefinitionId);
	}

	@Override
	public List<KaleoAction> getKaleoActions(
		String kaleoClassName, long kaleoClassPK) {

		return kaleoActionPersistence.findByKCN_KCPK(
			kaleoClassName, kaleoClassPK);
	}

	@Override
	public List<KaleoAction> getKaleoActions(
		String kaleoClassName, long kaleoClassPK, String executionType) {

		return kaleoActionPersistence.findByKCN_KCPK_ET(
			kaleoClassName, kaleoClassPK, executionType);
	}

}