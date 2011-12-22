/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.workflow.kaleo.definition.Condition;
import com.liferay.portal.workflow.kaleo.model.KaleoCondition;
import com.liferay.portal.workflow.kaleo.service.base.KaleoConditionLocalServiceBaseImpl;

import java.util.Date;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class KaleoConditionLocalServiceImpl
	extends KaleoConditionLocalServiceBaseImpl {

	public KaleoCondition addKaleoCondition(
			long kaleoDefinitionId, long kaleoNodeId, Condition condition,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getGuestOrUserId());
		Date now = new Date();

		long kaleoConditionId = counterLocalService.increment();

		KaleoCondition kaleoCondition = kaleoConditionPersistence.create(
			kaleoConditionId);

		kaleoCondition.setCompanyId(user.getCompanyId());
		kaleoCondition.setUserId(user.getUserId());
		kaleoCondition.setUserName(user.getFullName());
		kaleoCondition.setCreateDate(now);
		kaleoCondition.setModifiedDate(now);
		kaleoCondition.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoCondition.setKaleoNodeId(kaleoNodeId);
		kaleoCondition.setScript(condition.getScript());
		kaleoCondition.setScriptLanguage(
			condition.getScriptLanguage().getValue());

		kaleoConditionPersistence.update(kaleoCondition, false);

		return kaleoCondition;
	}

	public void deleteCompanyKaleoConditions(long companyId)
		throws SystemException {

		kaleoConditionPersistence.removeByCompanyId(companyId);
	}

	public void deleteKaleoDefinitionKaleoCondition(long kaleoDefinitionId)
		throws SystemException {

		kaleoConditionPersistence.removeByKaleoDefinitionId(kaleoDefinitionId);
	}

	public KaleoCondition getKaleoNodeKaleoCondition(long kaleoNodeId)
		throws PortalException, SystemException {

		return kaleoConditionPersistence.findByKaleoNodeId(kaleoNodeId);
	}

}