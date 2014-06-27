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

import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.service.base.KaleoDefinitionServiceBaseImpl;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoDefinitionServiceImpl extends KaleoDefinitionServiceBaseImpl {

	@Override
	public List<KaleoDefinition> getKaleoDefinitions(int start, int end) {
		return kaleoDefinitionPersistence.findAll(start, end);
	}

	@Override
	public List<KaleoDefinition> getKaleoDefinitions(
		long companyId, int start, int end) {

		return kaleoDefinitionPersistence.findByCompanyId(
			companyId, start, end);
	}

}