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

package com.liferay.portal.workflow.kaleo.manager;

import com.liferay.portal.model.Company;

/**
 * @author Michael C. Han
 */
public interface PortalKaleoManager {

	public void deleteKaleoData(Company company) throws Exception;

	public void deployDefaultDefinitionLink(String assetClassName)
		throws Exception;

	public void deployDefaultDefinitionLinks() throws Exception;

	public void deployDefaultDefinitionLinks(Company company) throws Exception;

	public void deployDefaultDefinitions() throws Exception;

	public void deployDefaultDefinitions(Company company) throws Exception;

	public void deployDefaultRoles() throws Exception;

	public void deployDefaultRoles(Company company) throws Exception;

	public void deployKaleoDefaults() throws Exception;

	public void deployKaleoDefaults(Company company) throws Exception;

}