/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.scriptingexecutor.scripts.groovy

import com.liferay.portal.NoSuchTeamException
import com.liferay.portal.model.Group
import com.liferay.portal.model.GroupConstants
import com.liferay.portal.service.GroupLocalServiceUtil
import com.liferay.portal.service.TeamLocalServiceUtil
import com.liferay.portal.service.UserLocalServiceUtil

/**
 * @author Michael C. Han
 */
class GroovySite {

	static GroovySite openSite(String name, String description) {
		def groovySite = new GroovySite();

		groovySite.name = name;
		groovySite.description = description;
		groovySite.type = GroupConstants.TYPE_SITE_OPEN;

		return groovySite;
	}

	static GroovySite privateSite(String name, String description) {
		def groovySite = new GroovySite();

		groovySite.name = name;
		groovySite.description = description;
		groovySite.type = GroupConstants.TYPE_SITE_PRIVATE;

		return groovySite;
	}

	static GroovySite restrictedSite(String name, String description) {
		def groovySite = new GroovySite();

		groovySite.name = name;
		groovySite.description = description;
		groovySite.type = GroupConstants.TYPE_SITE_RESTRICTED;

		return groovySite;
	}

	void addOrganizations(
		GroovyScriptingContext scriptingContext, String... organizationNames) {

		for (String organizationName : organizationNames) {
			def organization = GroovyOrganization.fetchOrganization(
					scriptingContext, organizationName);

			if (organization != null) {
				GroupLocalServiceUtil.addOrganizationGroup(
					organization.getOrganizationId(), site);
			}
		}
	}

	void addTeamUserGroupMembers(
		GroovyScriptingContext scriptingContext, String teamName,
		String... userGroupNames) {

		def team = null;

		try {
			team = TeamLocalServiceUtil.getTeam(
				site.getGroupId(), teamName);
		}
		catch (NoSuchTeamException nste) {
			team = TeamLocalServiceUtil.addTeam(
				scriptingContext.defaultUserId, site.getGroupId(),
				teamName, null);
		}

		for (String userGroupName : userGroupNames) {
			def userGroup = GroovyUserGroup.fetchUserGroup(
				scriptingContext, userGroupName);

			if (userGroup != null) {
				TeamLocalServiceUtil.addUserGroupTeam(
					userGroup.getUserGroupId(), team);
			}
		}
	}

	void addTeamUserMembers(
		GroovyScriptingContext scriptingContext, String teamName,
		String... screenNames) {

		def team = null;

		try {
			team = TeamLocalServiceUtil.getTeam(
				site.getGroupId(), teamName);
		}
		catch (NoSuchTeamException nste) {
			team = TeamLocalServiceUtil.addTeam(
				scriptingContext.defaultUserId, site.getGroupId(),
				teamName, null);
		}

		for (String screenName : screenNames) {
			def user = UserLocalServiceUtil.fetchUserByScreenName(
				scriptingContext.companyId, screenName);

			if (user != null) {
				TeamLocalServiceUtil.addUserTeam(user.getUserId(), team);
			}
		}
	}

	void create(GroovyScriptingContext scriptingContext) {
		site = GroupLocalServiceUtil.fetchGroup(
			scriptingContext.companyId, name);

		if (site != null) {
			return;
		}

		site = _addSite(scriptingContext)
	}

	private Group _addSite(GroovyScriptingContext scriptingContext) {

		return GroupLocalServiceUtil.addGroup(
			scriptingContext.defaultUserId, null, 0, 0, name, description, type,
			null, true, true, scriptingContext.serviceContext);
	}

	String description;
	Group site;
	String name;
	int type;

}
