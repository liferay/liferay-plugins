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

import com.liferay.portal.NoSuchRoleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManager;
import com.liferay.portal.kernel.workflow.comparator.WorkflowComparatorFactoryUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.model.WorkflowDefinitionLink;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.BaseKaleoBean;

import java.io.InputStream;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class DefaultPortalKaleoManager
	extends BaseKaleoBean implements PortalKaleoManager {

	@Override
	public void deleteKaleoData(long companyId) throws Exception {
		kaleoDefinitionLocalService.deleteCompanyKaleoDefinitions(companyId);

		kaleoLogLocalService.deleteCompanyKaleoLogs(companyId);
	}

	@Override
	public void deployDefaultDefinitionLink(String assetClassName)
		throws Exception {

		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		for (Company company : companies) {
			long companyId = company.getCompanyId();

			User defaultUser = UserLocalServiceUtil.getDefaultUser(companyId);

			Group companyGroup = GroupLocalServiceUtil.getCompanyGroup(
				companyId);

			String definitionName = _defaultDefinitionName;

			if (_definitionAssets.containsKey(assetClassName)) {
				definitionName = _definitionAssets.get(assetClassName);
			}

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);

			deployDefaultDefinitionLink(
				defaultUser, companyId, companyGroup, assetClassName,
				definitionName);
		}
	}

	@Override
	public void deployDefaultDefinitionLinks() throws Exception {
		List<Company> companies = CompanyLocalServiceUtil.getCompanies(false);

		for (Company company : companies) {
			PortalKaleoManagerUtil.deployDefaultDefinitionLinks(
				company.getCompanyId());
		}
	}

	@Override
	public void deployDefaultDefinitionLinks(long companyId) throws Exception {
		User defaultUser = UserLocalServiceUtil.getDefaultUser(companyId);

		Group companyGroup = GroupLocalServiceUtil.getCompanyGroup(companyId);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);

		for (Map.Entry<String, String> entry : _definitionAssets.entrySet()) {
			String assetClassName = entry.getKey();
			String definitionName = entry.getValue();

			deployDefaultDefinitionLink(
				defaultUser, companyId, companyGroup, assetClassName,
				definitionName);
		}
	}

	@Override
	public void deployDefaultDefinitions() throws Exception {
		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		for (Company company : companies) {
			PortalKaleoManagerUtil.deployDefaultDefinitions(
				company.getCompanyId());
		}
	}

	@Override
	public void deployDefaultDefinitions(long companyId) throws Exception {
		for (Map.Entry<String, String> entry : _definitionFiles.entrySet()) {
			String definitionName = entry.getKey();
			String fileName = entry.getValue();

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);

			int kaleoDefinitionsCount =
				kaleoDefinitionLocalService.getKaleoDefinitionsCount(
					definitionName, serviceContext);

			if (kaleoDefinitionsCount > 0) {
				return;
			}

			ClassLoader classLoader = getClass().getClassLoader();

			InputStream inputStream = classLoader.getResourceAsStream(fileName);

			if (inputStream == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to find definition file for " + definitionName +
							" with file name " + fileName);
				}

				return;
			}

			User defaultUser = UserLocalServiceUtil.getDefaultUser(companyId);

			_workflowDefinitionManager.deployWorkflowDefinition(
				serviceContext.getCompanyId(), defaultUser.getUserId(),
				definitionName, FileUtil.getBytes(inputStream));
		}
	}

	@Override
	public void deployDefaultRoles() throws Exception {
		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		for (Company company : companies) {
			PortalKaleoManagerUtil.deployDefaultRoles(company.getCompanyId());
		}
	}

	@Override
	public void deployDefaultRoles(long companyId) throws Exception {
		User defaultUser = UserLocalServiceUtil.getDefaultUser(companyId);

		for (Map.Entry<String, String> entry : _defaultRoles.entrySet()) {
			String name = entry.getKey();

			try {
				RoleLocalServiceUtil.getRole(companyId, name);
			}
			catch (NoSuchRoleException nsre) {
				Map<Locale, String> descriptionMap = new HashMap<>();

				descriptionMap.put(LocaleUtil.getDefault(), entry.getValue());

				RoleLocalServiceUtil.addRole(
					defaultUser.getUserId(), null, 0, name, null,
					descriptionMap, RoleConstants.TYPE_REGULAR, null, null);
			}
		}
	}

	@Override
	public void deployKaleoDefaults() throws Exception {
		deployDefaultRoles();
		deployDefaultDefinitions();
		deployDefaultDefinitionLinks();
	}

	@Override
	public void deployKaleoDefaults(long companyId) throws Exception {
		deployDefaultRoles(companyId);
		deployDefaultDefinitions(companyId);
		deployDefaultDefinitionLinks(companyId);
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
			User defaultUser, long companyId, Group companyGroup,
			String assetClassName, String workflowDefinitionName)
		throws PortalException {

		WorkflowDefinitionLink workflowDefinitionLink =
			WorkflowDefinitionLinkLocalServiceUtil.
				fetchDefaultWorkflowDefinitionLink(
					companyId, assetClassName, 0, 0);

		if (workflowDefinitionLink != null) {
			return;
		}

		List<WorkflowDefinition> workflowDefinitions =
			_workflowDefinitionManager.getActiveWorkflowDefinitions(
				companyId, workflowDefinitionName, 0, 20,
				WorkflowComparatorFactoryUtil.getDefinitionNameComparator(
					false));

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
			defaultUser.getUserId(), companyId, companyGroup.getGroupId(),
			assetClassName, 0, 0, workflowDefinition.getName(),
			workflowDefinition.getVersion());
	}

	private static Log _log = LogFactoryUtil.getLog(
		DefaultPortalKaleoManager.class);

	private String _defaultDefinitionName;
	private Map<String, String> _defaultRoles = new HashMap<>();
	private Map<String, String> _definitionAssets = new HashMap<>();
	private Map<String, String> _definitionFiles = new HashMap<>();
	private WorkflowDefinitionManager _workflowDefinitionManager;

}