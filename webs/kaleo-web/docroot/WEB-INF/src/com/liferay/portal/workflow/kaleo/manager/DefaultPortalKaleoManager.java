/*
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManager;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.comparator.WorkflowDefinitionNameComparator;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalService;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="DefaultPortalKaleoManager.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class DefaultPortalKaleoManager
	implements PortalKaleoManager {

	public void deleteKaleoData(Company company) throws Exception {

		//for now do nothing...
	}

	public void deployDefaultRoles() throws Exception {

		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		for (Company company : companies) {
			deployDefaultRoles(company);
		}
	}

	public void deployDefaultDefinitions() throws Exception {

		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		for (Company company : companies) {
			deployDefaultDefinitions(company);
		}
	}

	public void deployDefaultDefinitionLinks() throws Exception {

		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		for (Company company : companies) {
			deployDefaultDefinitionLinks(company);
		}
	}

	public void deployDefaultDefinitions(Company company) throws Exception {

		for (Map.Entry<String, String> definitionEntry :
			_definitionFilesMap.entrySet()) {

			try {
				String definitionName = definitionEntry.getKey();

				ServiceContext serviceContext = new ServiceContext();
				serviceContext.setCompanyId(company.getCompanyId());

				int definitionCount =
					_kaleoDefinitionLocalService.getKaleoDefinitionsCount(
						definitionName, serviceContext);

				if (definitionCount > 0) {
					return;
				}

				ClassLoader classLoader = getClass().getClassLoader();

				InputStream inputStream =
					classLoader.getResourceAsStream(definitionEntry.getValue());

				if (inputStream == null) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to find definition file for: " +
							definitionName + " file name: " +
							definitionEntry.getValue());
					}
					return;
				}

				User defaultUser = UserLocalServiceUtil.getDefaultUser(
					company.getCompanyId());

				_workflowDefinitionManager.deployWorkflowDefinition(
					serviceContext.getCompanyId(), defaultUser.getUserId(),
					definitionName, inputStream);

			}
			catch (Exception e) {
				if (_log.isErrorEnabled()) {
					_log.error(
						"Unable to deploy default definition: " +
						definitionEntry.getKey(), e);
				}
			}
		}
	}

	public void deployDefaultDefinitionLinks(Company company) throws Exception {

		LinkedHashMap groupParams = new LinkedHashMap();

		List types = new ArrayList();

		types.add(new Integer(GroupConstants.TYPE_COMMUNITY_OPEN));
		types.add(new Integer(GroupConstants.TYPE_COMMUNITY_PRIVATE));
		types.add(new Integer(GroupConstants.TYPE_COMMUNITY_RESTRICTED));

		groupParams.put("types", types);
		groupParams.put("active", Boolean.TRUE);

		List<Group> communities = GroupLocalServiceUtil.search(
			company.getCompanyId(), null, null, groupParams, 0, 100);

		User defaultUser = UserLocalServiceUtil.getDefaultUser(
			company.getCompanyId());

		for (Group community : communities) {

			for (Map.Entry<String, String> definitionEntry :
				_definitionAssetMap.entrySet()) {

				try {
					String assetClassName = definitionEntry.getKey();
					String definitionName = definitionEntry.getValue();

					ServiceContext serviceContext = new ServiceContext();
					serviceContext.setCompanyId(company.getCompanyId());

					try {
						WorkflowDefinitionLinkLocalServiceUtil.
							getWorkflowDefinitionLink(
								company.getCompanyId(), community.getGroupId(),
								assetClassName);
					}
					catch (NoSuchWorkflowDefinitionLinkException e) {
						List<WorkflowDefinition> workflowDefinitions =
							_workflowDefinitionManager.getWorkflowDefinitions(
								company.getCompanyId(), definitionName, 0, 20,
								new WorkflowDefinitionNameComparator(false));

						if (workflowDefinitions.isEmpty()) {
							if (_log.isWarnEnabled()) {
								_log.warn("No definitions found for:" +
										  definitionName);
							}
							continue;
						}

						WorkflowDefinition workflowDefinition =
							workflowDefinitions.get(0);

						WorkflowDefinitionLinkLocalServiceUtil.
							addWorkflowDefinitionLink(
								defaultUser.getUserId(), company.getCompanyId(),
								community.getGroupId(), assetClassName,
								workflowDefinition.getName(),
								workflowDefinition.getVersion());
					}

				}
				catch (Exception e) {
					if (_log.isErrorEnabled()) {
						_log.error(
							"Unable to deploy default definition: " +
							definitionEntry.getKey(), e);
					}
				}
			}
		}
	}

	public void deployDefaultRoles(Company company) throws Exception {

		User defaultUser = UserLocalServiceUtil.getDefaultUser(
			company.getCompanyId());

		for (Map.Entry<String, String> defaultRole : _defaultRoles.entrySet()) {

			try {
				try {
					RoleLocalServiceUtil.getRole(
						company.getCompanyId(), defaultRole.getKey());
				}
				catch (NoSuchRoleException e) {
					RoleLocalServiceUtil.addRole(
						defaultUser.getUserId(), company.getCompanyId(),
						defaultRole.getKey(), null, defaultRole.getValue(),
						RoleConstants.TYPE_REGULAR);
				}
			}
			catch (Exception e) {
				if (_log.isErrorEnabled()) {
					_log.error(
						"Unable to add default role: " + defaultRole.getKey(),
						e);
				}
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

	public void setDefaultRoles(Map<String, String> defaultRoles) {
		_defaultRoles.putAll(defaultRoles);
	}

	public void setDefinitionAssetMap(Map<String, String> definitionAssetMap) {
		_definitionAssetMap.putAll(definitionAssetMap);
	}

	public void setDefinitionFilesMap(Map<String, String> definitionFilesMap) {
		_definitionFilesMap.putAll(definitionFilesMap);
	}

	public void setWorkflowDefinitionManager(
		WorkflowDefinitionManager workflowDefinitionManager) {

		_workflowDefinitionManager = workflowDefinitionManager;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DefaultPortalKaleoManager.class);

	private Map<String, String> _definitionFilesMap =
		new HashMap<String, String>();
	private Map<String, String> _defaultRoles = new HashMap<String, String>();
	private Map<String, String> _definitionAssetMap =
		new HashMap<String, String>();

	@BeanReference(type = KaleoDefinitionLocalService.class)
	private KaleoDefinitionLocalService _kaleoDefinitionLocalService;

	private WorkflowDefinitionManager _workflowDefinitionManager;
}