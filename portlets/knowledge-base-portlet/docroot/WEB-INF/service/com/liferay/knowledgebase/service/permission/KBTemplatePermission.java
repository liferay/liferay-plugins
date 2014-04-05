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

package com.liferay.knowledgebase.service.permission;

import com.liferay.knowledgebase.model.KBTemplate;
import com.liferay.knowledgebase.service.KBTemplateLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class KBTemplatePermission {

	public static void check(
			PermissionChecker permissionChecker, KBTemplate kbTemplate,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, kbTemplate, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long kbTemplateId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, kbTemplateId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, KBTemplate kbTemplate,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				kbTemplate.getCompanyId(), KBTemplate.class.getName(),
				kbTemplate.getKbTemplateId(), kbTemplate.getUserId(),
				actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			kbTemplate.getGroupId(), KBTemplate.class.getName(),
			kbTemplate.getKbTemplateId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long kbTemplateId,
			String actionId)
		throws PortalException, SystemException {

		KBTemplate kbTemplate = KBTemplateLocalServiceUtil.getKBTemplate(
			kbTemplateId);

		return contains(permissionChecker, kbTemplate, actionId);
	}

}