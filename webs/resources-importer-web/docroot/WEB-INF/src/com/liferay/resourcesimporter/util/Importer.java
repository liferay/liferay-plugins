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

package com.liferay.resourcesimporter.util;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public interface Importer {

	public void afterPropertiesSet() throws Exception;

	public long getGroupId();

	public String getTargetClassName();

	public long getTargetClassPK();

	public void importResources() throws Exception;

	public boolean isCompanyGroup() throws Exception;

	public boolean isDeveloperModeEnabled();

	public boolean isExisting();

	public void setAppendVersion(boolean appendVersion);

	public void setCompanyId(long companyId);

	public void setDeveloperModeEnabled(boolean developerModeEnabled);

	public void setGroupId(long groupId);

	public void setResourcesDir(String resourcesDir);

	public void setServletContext(ServletContext servletContext);

	public void setServletContextName(String servletContextName);

	public void setTargetClassName(String className);

	public void setTargetValue(String targetValue);

	public void setUpdateModeEnabled(boolean updateModeEnabled);

	public void setVersion(String version);

}