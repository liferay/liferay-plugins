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

package com.liferay.resourcesimporter.util;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.User;
import com.liferay.portal.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public abstract class BaseImporter implements Importer {

	public void afterPropertiesSet() throws Exception {
		User user = UserLocalServiceUtil.getDefaultUser(companyId);

		userId = user.getUserId();

		LayoutSetPrototype layoutSetPrototype =
			LayoutSetPrototypeLocalServiceUtil.addLayoutSetPrototype(
				userId, companyId, layoutSetPrototypeNameMap, StringPool.BLANK,
				true, true, new ServiceContext());

		Group group = layoutSetPrototype.getGroup();

		groupId = group.getGroupId();
		layoutSetPrototypeId = layoutSetPrototype.getLayoutSetPrototypeId();
	}

	public long getGroupId() {
		return groupId;
	}

	public long getLayoutSetPrototypeId() {
		return layoutSetPrototypeId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public void setLayoutSetPrototypeNameMap(
		Map<Locale, String> layoutSetPrototypeNameMap) {

		this.layoutSetPrototypeNameMap = layoutSetPrototypeNameMap;
	}

	public void setResourcesDir(String resourcesDir) {
		this.resourcesDir = resourcesDir;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public void setServletContextName(String servletContextName) {
		this.servletContextName = servletContextName;
	}

	protected long companyId;
	protected long groupId;
	protected long layoutSetPrototypeId;
	protected Map<Locale, String> layoutSetPrototypeNameMap;
	protected String resourcesDir;
	protected ServletContext servletContext;
	protected String servletContextName;
	protected long userId;

}