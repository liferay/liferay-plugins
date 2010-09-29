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
import com.liferay.portal.workflow.kaleo.definition.Condition;
import com.liferay.portal.workflow.kaleo.model.KaleoCondition;
import com.liferay.portal.workflow.kaleo.service.base.KaleoConditionLocalServiceBaseImpl;

import java.util.Date;

/**
 * The implementation of the kaleo condition local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.portal.workflow.kaleo.service.KaleoConditionLocalService} interface.
 * </p>
 *
 * <p>
 * Never reference this interface directly. Always use {@link com.liferay.portal.workflow.kaleo.service.KaleoConditionLocalServiceUtil} to access the kaleo condition local service.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.workflow.kaleo.service.base.KaleoConditionLocalServiceBaseImpl
 * @see com.liferay.portal.workflow.kaleo.service.KaleoConditionLocalServiceUtil
 */
public class KaleoConditionLocalServiceImpl
	extends KaleoConditionLocalServiceBaseImpl {

	public KaleoCondition addKaleoCondition(
			Condition condition, String className, long classPK,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getUserId());
		Date now = new Date();

		long kaleoConditionId = counterLocalService.increment();

		KaleoCondition kaleoCondition = kaleoConditionPersistence.create(
			kaleoConditionId);

		kaleoCondition.setCompanyId(user.getCompanyId());
		kaleoCondition.setUserId(user.getUserId());
		kaleoCondition.setUserName(user.getFullName());
		kaleoCondition.setCreateDate(now);
		kaleoCondition.setModifiedDate(now);

		kaleoCondition.setKaleoClassName(className);
		kaleoCondition.setKaleoClassPK(classPK);
		kaleoCondition.setDescription(condition.getDescription());
		kaleoCondition.setScript(condition.getScript());
		kaleoCondition.setScriptLanguage(
			condition.getScriptLanguage().getValue());


		kaleoConditionPersistence.update(kaleoCondition, false);

		return kaleoCondition;
	}

	public KaleoCondition getKaleoCondition(String className, long classPK)
		throws PortalException, SystemException {

		return kaleoConditionPersistence.fetchByKaleoCN_PK(className, classPK);
	}
}