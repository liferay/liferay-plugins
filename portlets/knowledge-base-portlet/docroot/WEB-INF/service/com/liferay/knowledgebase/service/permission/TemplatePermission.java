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

package com.liferay.knowledgebase.service.permission;

import com.liferay.knowledgebase.model.Template;
import com.liferay.knowledgebase.service.TemplateLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class TemplatePermission {

	public static void check(
			PermissionChecker permissionChecker, long templateId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, templateId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, Template template,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, template, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long templateId,
			String actionId)
		throws PortalException, SystemException {

		Template template = TemplateLocalServiceUtil.getTemplate(templateId);

		return contains(permissionChecker, template, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, Template template,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				template.getCompanyId(), Template.class.getName(),
				template.getTemplateId(), template.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			template.getGroupId(), Template.class.getName(),
			template.getTemplateId(), actionId);
	}

}