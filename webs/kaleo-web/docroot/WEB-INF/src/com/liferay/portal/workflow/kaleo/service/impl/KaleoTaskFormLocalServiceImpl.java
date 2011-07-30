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

package com.liferay.portal.workflow.kaleo.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.definition.Form;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskForm;
import com.liferay.portal.workflow.kaleo.service.base.KaleoTaskFormLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoTaskFormLocalServiceImpl
	extends KaleoTaskFormLocalServiceBaseImpl {

	public KaleoTaskForm addKaleoTaskForm(
			long kaleoDefintionId, long kaleoTaskId,
			Form form, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getUserId());
		Date now = new Date();

		long kaleoTaskFormId = counterLocalService.increment();

		KaleoTaskForm kaleoTaskForm = kaleoTaskFormPersistence.create(
			kaleoTaskFormId);

		kaleoTaskForm.setCompanyId(user.getCompanyId());
		kaleoTaskForm.setUserId(user.getUserId());
		kaleoTaskForm.setUserName(user.getFullName());
		kaleoTaskForm.setCreateDate(now);
		kaleoTaskForm.setModifiedDate(now);
		kaleoTaskForm.setKaleoDefinitionId(kaleoDefintionId);
		kaleoTaskForm.setKaleoTaskId(kaleoTaskId);
		kaleoTaskForm.setDescription(form.getDescription());
		kaleoTaskForm.setFormTemplateId(form.getFormTemplateId());

		kaleoTaskFormPersistence.update(kaleoTaskForm, false);

		return kaleoTaskForm;
	}

	public void deleteCompanyKaleoTaskForms(long companyId)
		throws SystemException {

		kaleoTaskFormPersistence.removeByCompanyId(companyId);
	}

	public void deleteKaleoDefinitionKaleoTaskForms(long kaleoDefintionId)
		throws SystemException {

		kaleoTaskFormPersistence.removeByKaleoDefinitionId(kaleoDefintionId);
	}

	public List<KaleoTaskForm> getKaleoTaskForms(long kaleoTaskId)
		throws SystemException {

		return kaleoTaskFormPersistence.findByKaleoTaskId(kaleoTaskId);
	}

}