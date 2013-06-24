/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.scriptingexecutor.scripts.groovy;

import com.liferay.portal.NoSuchOrganizationException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.OrganizationConstants;
import com.liferay.portal.service.OrganizationLocalServiceUtil;

/**
 * @author Michael C. Han
 */
class GroovyOrganization {

	static Organization fetchOrganization(
		GroovyScriptingContext scriptingContext, String name) {

		try {
			return OrganizationLocalServiceUtil.getOrganization(
				scriptingContext.companyId, name);
		}
		catch (NoSuchOrganizationException nsoe) {
		}

		return null;
	}

	GroovyOrganization(String name_) {
		name = name_;
	}

	GroovyOrganization(String name_, String parentOrganizationName_) {
		name = name_;
		parentOrganizationName = parentOrganizationName_;
	}

	void create(GroovyScriptingContext scriptingContext) {
		organization = fetchOrganization(scriptingContext, name);

		if (organization != null) {
			return;
		}

		long parentOrganizationId =
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID;

		if (Validator.isNotNull(parentOrganizationName)) {
			Organization parentOrganization = fetchOrganization(
				scriptingContext, parentOrganizationName);

			if (parentOrganization != null) {
				parentOrganizationId = parentOrganization.getOrganizationId();
			}
		}

		organization = OrganizationLocalServiceUtil.addOrganization(
			scriptingContext.defaultUserId, parentOrganizationId, name, false);
	}

	String name;
	Organization organization;
	String parentOrganizationName;

}