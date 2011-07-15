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

package com.liferay.portal.workflow.kaleo.manager;

import com.liferay.portal.NoSuchRoleException;
import com.liferay.portal.NoSuchWorkflowDefinitionLinkException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManager;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.BaseKaleoBean;
import com.liferay.portal.workflow.kaleo.comparator.WorkflowDefinitionNameComparator;

import java.io.InputStream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class DefaultPortalKaleoManager
	extends BaseKaleoBean implements PortalKaleoManager {

	public void deleteKaleoData(Company company) throws Exception {
		long companyId = company.getCompanyId();

		kaleoDefinitionLocalService.deleteCompanyKaleoDefinitions(companyId);

		kaleoLogLocalService.deleteCompanyKaleoLogs(companyId);
	}

	public void deployDefaultDefinitionLink(String assetClassName)
		throws Exception {

		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		for (Company company : companies) {
			User defaultUser = UserLocalServiceUtil.getDefaultUser(
				company.getCompanyId());

			Group companyGroup = GroupLocalServiceUtil.getCompanyGroup(
				company.getCompanyId());

			String definitionName = _defaultDefinitionName;

			if (_definitionAssets.containsKey(assetClassName)) {
				definitionName = _definitionAssets.get(assetClassName);
			}

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(company.getCompanyId());

			deployDefaultDefinitionLink(
				defaultUser, company, companyGroup, assetClassName,
				definitionName);
		}
	}

	public void deployDefaultDefinitionLinks() throws Exception {
		List<Company> companies = CompanyLocalServiceUtil.getCompanies(false);

		for (Company company : companies) {
			deployDefaultDefinitionLinks(company);
		}
	}

	public void deployDefaultDefinitionLinks(Company company) throws Exception {
		User defaultUser = UserLocalServiceUtil.getDefaultUser(
			company.getCompanyId());

		Group companyGroup = GroupLocalServiceUtil.getCompanyGroup(
			company.getCompanyId());

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(company.getCompanyId());

		for (Map.Entry<String, String> entry : _definitionAssets.entrySet()) {
			String assetClassName = entry.getKey();
			String definitionName = entry.getValue();

			deployDefaultDefinitionLink(
				defaultUser, company, companyGroup, assetClassName,
				definitionName);
		}
	}

	public void deployDefaultDefinitions() throws Exception {
		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		for (Company company : companies) {
			deployDefaultDefinitions(company);
		}
	}

	public void deployDefaultDefinitions(Company company) throws Exception {
		for (Map.Entry<String, String> entry : _definitionFiles.entrySet()) {
			String definitionName = entry.getKey();
			String fileName = entry.getValue();

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(company.getCompanyId());

			int kaleoDefinitionsCount =
				kaleoDefinitionLocalService.getKaleoDefinitionsCount(
					definitionName, serviceContext);

			if (kaleoDefinitionsCount > 0) {
				return;
			}

			ClassLoader classLoader = getClass().getClassLoader();

			InputStream inputStream = classLoader.getResourceAsStream(
				fileName);

			if (inputStream == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to find definition file for " + definitionName +
							" with file name " + fileName);
				}

				return;
			}

			User defaultUser = UserLocalServiceUtil.getDefaultUser(
				company.getCompanyId());

			_workflowDefinitionManager.deployWorkflowDefinition(
				serviceContext.getCompanyId(), defaultUser.getUserId(),
				definitionName, inputStream);
		}
	}

	public void deployDefaultRoles() throws Exception {
		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		for (Company company : companies) {
			deployDefaultRoles(company);
		}
	}

	public void deployDefaultRoles(Company company) throws Exception {
		User defaultUser = UserLocalServiceUtil.getDefaultUser(
			company.getCompanyId());

		for (Map.Entry<String, String> entry : _defaultRoles.entrySet()) {
			String name = entry.getKey();
			String description = entry.getValue();

			try {
				RoleLocalServiceUtil.getRole(company.getCompanyId(), name);
			}
			catch (NoSuchRoleException nsre) {
				RoleLocalServiceUtil.addRole(
					defaultUser.getUserId(), company.getCompanyId(), name, null,
					description, RoleConstants.TYPE_REGULAR);
			}
		}
	}

	public void deployKaleoDefaults() throws Exception {
		deployDefaultRoles();
		deployDefaultDefinitions();
		deployDefaultDefinitionLinks();
	}

	public void deployKaleoDefaults(Company company) throws Exception {
		deployDefaultRoles(company);
		deployDefaultDefinitions(company);
		deployDefaultDefinitionLinks(company);
	}

	public void setDefaultDefinitionName(String defaultDefinitionName) {
		_defaultDefinitionName = defaultDefinitionName;
	}

	public void setDefaultRoles(Map<String, String> defaultRoles) {
		_defaultRoles.putAll(defaultRoles);
	}

	public void setDefinitionAssets(Map<String, String> definitionAssets) {
		_definitionAssets.putAll(definitionAssets);
	}

	public void setDefinitionFiles(Map<String, String> definitionFiles) {
		_definitionFiles.putAll(definitionFiles);
	}

	public void setWorkflowDefinitionManager(
		WorkflowDefinitionManager workflowDefinitionManager) {

		_workflowDefinitionManager = workflowDefinitionManager;
	}

	protected void deployDefaultDefinitionLink(
			User defaultUser, Company company, Group companyGroup,
			String assetClassName, String workflowDefinitionName)
		throws PortalException, SystemException {

		try {
			WorkflowDefinitionLinkLocalServiceUtil.
				getDefaultWorkflowDefinitionLink(
					company.getCompanyId(), assetClassName, 0, 0);

			return;
		}
		catch (NoSuchWorkflowDefinitionLinkException nswdle) {
		}

		List<WorkflowDefinition> workflowDefinitions =
			_workflowDefinitionManager.getActiveWorkflowDefinitions(
				company.getCompanyId(), workflowDefinitionName, 0, 20,
				new WorkflowDefinitionNameComparator(false));

		if (workflowDefinitions.isEmpty()) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"No workflow definitions found for " +
						workflowDefinitionName);
			}

			return;
		}

		WorkflowDefinition workflowDefinition = workflowDefinitions.get(0);

		WorkflowDefinitionLinkLocalServiceUtil.addWorkflowDefinitionLink(
			defaultUser.getUserId(), company.getCompanyId(),
			companyGroup.getGroupId(), assetClassName, 0, 0,
			workflowDefinition.getName(), workflowDefinition.getVersion());
	}

	private static Log _log = LogFactoryUtil.getLog(
		DefaultPortalKaleoManager.class);

	private String _defaultDefinitionName;
	private Map<String, String> _defaultRoles = new HashMap<String, String>();
	private Map<String, String> _definitionAssets =
		new HashMap<String, String>();
	private Map<String, String> _definitionFiles =
		new HashMap<String, String>();
	private WorkflowDefinitionManager _workflowDefinitionManager;

}