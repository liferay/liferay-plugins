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

package com.liferay.portal.workflow.kaleo.manager;

/**
 * @author Michael C. Han
 */
public interface PortalKaleoManager {

	public void deleteKaleoData(long companyId) throws Exception;

	public void deployDefaultDefinitionLink(String assetClassName)
		throws Exception;

	public void deployDefaultDefinitionLinks() throws Exception;

	public void deployDefaultDefinitionLinks(long companyId) throws Exception;

	public void deployDefaultDefinitions() throws Exception;

	public void deployDefaultDefinitions(long companyId) throws Exception;

	public void deployDefaultRoles() throws Exception;

	public void deployDefaultRoles(long companyId) throws Exception;

	public void deployKaleoDefaults() throws Exception;

	public void deployKaleoDefaults(long companyId) throws Exception;

}