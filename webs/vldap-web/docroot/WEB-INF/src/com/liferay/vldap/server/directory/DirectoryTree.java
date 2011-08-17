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

package com.liferay.vldap.server.directory;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserGroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.comparator.UserScreenNameComparator;
import com.liferay.vldap.server.directory.builder.CommunitiesBuilder;
import com.liferay.vldap.server.directory.builder.CommunityBuilder;
import com.liferay.vldap.server.directory.builder.CompanyBuilder;
import com.liferay.vldap.server.directory.builder.DirectoryBuilder;
import com.liferay.vldap.server.directory.builder.OrganizationBuilder;
import com.liferay.vldap.server.directory.builder.OrganizationsBuilder;
import com.liferay.vldap.server.directory.builder.RoleBuilder;
import com.liferay.vldap.server.directory.builder.RolesBuilder;
import com.liferay.vldap.server.directory.builder.RootBuilder;
import com.liferay.vldap.server.directory.builder.TopBuilder;
import com.liferay.vldap.server.directory.builder.UserBuilder;
import com.liferay.vldap.server.directory.builder.UserGroupBuilder;
import com.liferay.vldap.server.directory.builder.UserGroupsBuilder;
import com.liferay.vldap.server.directory.builder.UsersBuilder;
import com.liferay.vldap.server.directory.ldap.CommunitiesDirectory;
import com.liferay.vldap.server.directory.ldap.CommunityDirectory;
import com.liferay.vldap.server.directory.ldap.CompanyDirectory;
import com.liferay.vldap.server.directory.ldap.Directory;
import com.liferay.vldap.server.directory.ldap.OrganizationDirectory;
import com.liferay.vldap.server.directory.ldap.OrganizationsDirectory;
import com.liferay.vldap.server.directory.ldap.RoleDirectory;
import com.liferay.vldap.server.directory.ldap.RolesDirectory;
import com.liferay.vldap.server.directory.ldap.RootDirectory;
import com.liferay.vldap.server.directory.ldap.TopDirectory;
import com.liferay.vldap.server.directory.ldap.UserDirectory;
import com.liferay.vldap.server.directory.ldap.UserGroupDirectory;
import com.liferay.vldap.server.directory.ldap.UserGroupsDirectory;
import com.liferay.vldap.server.directory.ldap.UsersDirectory;
import com.liferay.vldap.util.LdapUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.directory.shared.ldap.model.entry.Value;
import org.apache.directory.shared.ldap.model.filter.AndNode;
import org.apache.directory.shared.ldap.model.filter.BranchNode;
import org.apache.directory.shared.ldap.model.filter.EqualityNode;
import org.apache.directory.shared.ldap.model.filter.ExprNode;
import org.apache.directory.shared.ldap.model.filter.GreaterEqNode;
import org.apache.directory.shared.ldap.model.filter.LeafNode;
import org.apache.directory.shared.ldap.model.filter.LessEqNode;
import org.apache.directory.shared.ldap.model.filter.NotNode;
import org.apache.directory.shared.ldap.model.filter.OrNode;
import org.apache.directory.shared.ldap.model.filter.PresenceNode;
import org.apache.directory.shared.ldap.model.filter.SubstringNode;
import org.apache.directory.shared.ldap.model.message.SearchScope;
import org.apache.directory.shared.ldap.model.name.Dn;

/**
 * @author Jonathan Potter
 */
public class DirectoryTree {

	public DirectoryTree() {
		_rootBuilder.addDirectoryBuilder(_topBuilder);
		_topBuilder.addDirectoryBuilder(_companyBuilder);

		_companyBuilder.addDirectoryBuilder(_communitiesBuilder);
		_companyBuilder.addDirectoryBuilder(_organizationsBuilder);
		_companyBuilder.addDirectoryBuilder(_rolesBuilder);
		_companyBuilder.addDirectoryBuilder(_userGroupsBuilder);
		_companyBuilder.addDirectoryBuilder(_usersBuilder);

		_communitiesBuilder.addDirectoryBuilder(_communityBuilder);
		_organizationsBuilder.addDirectoryBuilder(_organizationBuilder);
		_rolesBuilder.addDirectoryBuilder(_roleBuilder);
		_userGroupsBuilder.addDirectoryBuilder(_userGroupBuilder);
		_usersBuilder.addDirectoryBuilder(_userBuilder);

		_communityBuilder.addDirectoryBuilder(_userBuilder);
		_organizationBuilder.addDirectoryBuilder(_userBuilder);
		_roleBuilder.addDirectoryBuilder(_userBuilder);
		_userGroupBuilder.addDirectoryBuilder(_userBuilder);
	}

	public List<Directory> getDirectories(
		SearchBase searchBase, ExprNode exprNode, SearchScope searchScope) {

		List<Directory> directories = new ArrayList<Directory>();

		List<FilterConstraint> filterConstraints = toFilterConstraints(
			exprNode);

		boolean subtree = searchScope.equals(SearchScope.SUBTREE);

		DirectoryBuilder searchBaseDirectoryBuilder =
			searchBase.getDirectoryBuilder();

		for (DirectoryBuilder directoryBuilder :
				searchBaseDirectoryBuilder.getDirectoryBuilders()) {

			directories.addAll(
				directoryBuilder.buildDirectories(
					searchBase, filterConstraints, subtree));
		}

		return directories;
	}

	public SearchBase getSearchBase(Dn dn, long sizeLimit) throws Exception {
		if (dn == null) {
			return null;
		}

		String top = LdapUtil.getRdnValue(dn, 0);
		String companyWebId = LdapUtil.getRdnValue(dn, 1);
		String type = LdapUtil.getRdnValue(dn, 2);
		String typeValue = LdapUtil.getRdnValue(dn, 3);
		String screenName = LdapUtil.getRdnValue(dn, 4);

		if (Validator.isNull(top)) {
			return new SearchBase(new RootDirectory(), _rootBuilder);
		}

		if (!top.equalsIgnoreCase("Liferay")) {
			return null;
		}

		if (companyWebId == null) {
			return new SearchBase(new TopDirectory(top), _topBuilder, top);
		}

		Company company = CompanyLocalServiceUtil.getCompanyByWebId(
			companyWebId);

		if (company == null) {
			return null;
		}

		if (type == null) {
			return new SearchBase(
				new CompanyDirectory(top, company), _companyBuilder, top,
				company);
		}

		if (typeValue == null) {
			if (type.equalsIgnoreCase("Communities")) {
				return new SearchBase(
					new CommunitiesDirectory(top, company),
					_communitiesBuilder, top, company);
			}
			else if (type.equalsIgnoreCase("Organizations")) {
				return new SearchBase(
					new OrganizationsDirectory(top, company),
					_organizationsBuilder, top, company);
			}
			else if (type.equalsIgnoreCase("Roles")) {
				return new SearchBase(
					new RolesDirectory(top, company), _rolesBuilder, top,
					company);
			}
			else if (type.equalsIgnoreCase("User Groups")) {
				return new SearchBase(
					new UserGroupsDirectory(top, company), _userGroupsBuilder,
					top, company);
			}
			else if (type.equalsIgnoreCase("Users")) {
				return new SearchBase(
					new UsersDirectory(top, company), _usersBuilder, top,
					company);
			}
		}

		Group community = null;
		Organization organization = null;
		Role role = null;
		UserGroup userGroup = null;

		if (type.equalsIgnoreCase("Communities")) {
			community = GroupLocalServiceUtil.getGroup(
				company.getCompanyId(), typeValue);
		}
		else if (type.equalsIgnoreCase("Organizations")) {
			organization = OrganizationLocalServiceUtil.getOrganization(
				company.getCompanyId(), typeValue);
		}
		else if (type.equalsIgnoreCase("Roles")) {
			role = RoleLocalServiceUtil.getRole(
				company.getCompanyId(), typeValue);
		}
		else if (type.equalsIgnoreCase("User Groups")) {
			userGroup = UserGroupLocalServiceUtil.getUserGroup(
				company.getCompanyId(), typeValue);
		}

		if (screenName == null) {
			if (type.equalsIgnoreCase("Communities")) {
				return new SearchBase(
					new CommunityDirectory(top, company, community),
					_communityBuilder, top, company, community);
			}
			else if (type.equalsIgnoreCase("Organizations")) {
				return new SearchBase(
					new OrganizationDirectory(top, company, organization),
					_organizationBuilder, top, company, organization);
			}
			else if (type.equalsIgnoreCase("Roles")) {
				return new SearchBase(
					new RoleDirectory(top, company, role), _roleBuilder, top,
					company, role);
			}
			else if (type.equalsIgnoreCase("User Groups")) {
				return new SearchBase(
					new UserGroupDirectory(top, company, userGroup),
					_userGroupBuilder, top, company, userGroup);
			}
			else if (type.equalsIgnoreCase("Users")) {
				User user = UserLocalServiceUtil.getUserByScreenName(
					company.getCompanyId(), typeValue);

				return new SearchBase(
					new UserDirectory(top, company, user), _userBuilder, top,
					company, user);
			}
		}

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		if (community != null) {
			params.put("usersGroups", community.getGroupId());
		}
		else if (organization != null) {
			params.put("usersOrgs", organization.getOrganizationId());
		}
		else if (role != null) {
			params.put("usersRoles", role.getRoleId());
		}
		else if (userGroup != null) {
			params.put("usersUserGroups", userGroup.getUserGroupId());
		}

		OrderByComparator orderByComparator = new UserScreenNameComparator();

		List<User> users = UserLocalServiceUtil.search(
			company.getCompanyId(), null, null, null, screenName, null,
			WorkflowConstants.STATUS_APPROVED, params, true, 0, (int)sizeLimit,
			orderByComparator);

		if (users.isEmpty()) {
			return null;
		}

		return new SearchBase(
			new UserDirectory(top, company, users.get(0)), _userBuilder, top,
			company, users.get(0));
	}

	protected List<FilterConstraint> toFilterConstraints(ExprNode exprNode) {
		if (exprNode.isLeaf()) {
			LeafNode leafNode = (LeafNode)exprNode;

			return toFilterConstraintsFromLeafNode(leafNode);
		}
		else {
			BranchNode branchNode = (BranchNode)exprNode;

			return toFilterConstraintsFromBranchNode(branchNode);
		}
	}

	protected List<FilterConstraint> toFilterConstraintsFromBranchNode(
		BranchNode branchNode) {

		if (branchNode instanceof AndNode) {
			List<FilterConstraint> filterConstraints =
				new ArrayList<FilterConstraint>();

			for (ExprNode exprNode : branchNode.getChildren()) {
				List<FilterConstraint> childFilterConstraints =
					toFilterConstraints(exprNode);

				if (childFilterConstraints == null) {
					continue;
				}

				filterConstraints = FilterConstraint.getCombinations(
					filterConstraints, childFilterConstraints);
			}

			return filterConstraints;
		}
		else if (branchNode instanceof NotNode) {
		}
		else if (branchNode instanceof OrNode) {
			List<FilterConstraint> filterConstraints =
				new ArrayList<FilterConstraint>();

			for (ExprNode exprNode : branchNode.getChildren()) {
				List<FilterConstraint> childFilterConstraints =
					toFilterConstraints(exprNode);

				if (childFilterConstraints == null) {
					continue;
				}

				filterConstraints.addAll(childFilterConstraints);
			}

			return filterConstraints;
		}

		if (_log.isWarnEnabled()) {
			_log.warn("Unsupported expression " + branchNode);
		}

		return null;
	}

	protected List<FilterConstraint> toFilterConstraintsFromLeafNode(
		LeafNode leafNode) {

		if (leafNode instanceof EqualityNode<?>) {
			EqualityNode<?> equalityNode = (EqualityNode<?>)leafNode;

			List<FilterConstraint> filterConstraints =
				new ArrayList<FilterConstraint>();

			FilterConstraint filterConstraint = new FilterConstraint();

			Value<?> value = equalityNode.getValue();

			filterConstraint.addAttribute(
				equalityNode.getAttribute(), value.getString());

			filterConstraints.add(filterConstraint);

			return filterConstraints;
		}
		else if (leafNode instanceof GreaterEqNode<?>) {
		}
		else if (leafNode instanceof LessEqNode<?>) {
		}
		else if (leafNode instanceof PresenceNode) {
			PresenceNode presenceNode = (PresenceNode)leafNode;

			List<FilterConstraint> filterConstraints =
				new ArrayList<FilterConstraint>();

			FilterConstraint filterConstraint = new FilterConstraint();

			filterConstraint.addAttribute(presenceNode.getAttribute(), "*");

			filterConstraints.add(filterConstraint);

			return filterConstraints;
		}
		else if (leafNode instanceof SubstringNode) {
		}

		if (_log.isWarnEnabled()) {
			_log.warn("Unsupported expression " + leafNode);
		}

		return null;
	}

	private static Log _log = LogFactoryUtil.getLog(DirectoryBuilder.class);

	private CommunitiesBuilder _communitiesBuilder = new CommunitiesBuilder();
	private CommunityBuilder _communityBuilder = new CommunityBuilder();
	private CompanyBuilder _companyBuilder = new CompanyBuilder();
	private OrganizationBuilder _organizationBuilder =
		new OrganizationBuilder();
	private OrganizationsBuilder _organizationsBuilder =
		new OrganizationsBuilder();
	private RoleBuilder _roleBuilder = new RoleBuilder();
	private RolesBuilder _rolesBuilder = new RolesBuilder();
	private RootBuilder _rootBuilder = new RootBuilder();
	private TopBuilder _topBuilder = new TopBuilder();
	private UserBuilder _userBuilder = new UserBuilder();
	private UserGroupBuilder _userGroupBuilder = new UserGroupBuilder();
	private UserGroupsBuilder _userGroupsBuilder = new UserGroupsBuilder();
	private UsersBuilder _usersBuilder = new UsersBuilder();

}