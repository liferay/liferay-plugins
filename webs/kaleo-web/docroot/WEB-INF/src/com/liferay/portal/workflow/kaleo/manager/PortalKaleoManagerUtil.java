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
public class PortalKaleoManagerUtil {

	public static void deleteKaleoData(long companyId) throws Exception {
		_portalKaleoManager.deleteKaleoData(companyId);
	}

	public static void deployDefaultDefinitionLink(String assetClassName)
		throws Exception {

		_portalKaleoManager.deployDefaultDefinitionLink(assetClassName);
	}

	public static void deployDefaultDefinitionLinks() throws Exception {
		_portalKaleoManager.deployDefaultDefinitionLinks();
	}

	public static void deployDefaultDefinitionLinks(long companyId)
		throws Exception {

		_portalKaleoManager.deployDefaultDefinitionLinks(companyId);
	}

	public static void deployDefaultDefinitions() throws Exception {
		_portalKaleoManager.deployDefaultDefinitions();
	}

	public static void deployDefaultDefinitions(long companyId)
		throws Exception {

		_portalKaleoManager.deployDefaultDefinitions(companyId);
	}

	public static void deployDefaultRoles() throws Exception {
		_portalKaleoManager.deployDefaultRoles();
	}

	public static void deployDefaultRoles(long companyId) throws Exception {
		_portalKaleoManager.deployDefaultRoles(companyId);
	}

	public static void deployKaleoDefaults() throws Exception {
		_portalKaleoManager.deployKaleoDefaults();
	}

	public static void deployKaleoDefaults(long companyId) throws Exception {
		_portalKaleoManager.deployKaleoDefaults(companyId);
	}

	public static PortalKaleoManager getPortalKaleoManager() {
		return _portalKaleoManager;
	}

	public void setPortalKaleoManager(PortalKaleoManager portalKaleoManager) {
		_portalKaleoManager = portalKaleoManager;
	}

	private static PortalKaleoManager _portalKaleoManager;

}