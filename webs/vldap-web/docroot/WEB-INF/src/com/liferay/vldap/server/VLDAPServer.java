/**
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

package com.liferay.vldap.server;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.vldap.server.codec.LdapCodecFactory;
import com.liferay.vldap.server.handler.BindLdapHandler;
import com.liferay.vldap.server.handler.util.Directory;
import com.liferay.vldap.util.OIDConstants;
import com.liferay.vldap.util.PortletPropsValues;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import java.util.List;

import org.apache.directory.shared.ldap.schema.SchemaManager;
import org.apache.directory.shared.ldap.schema.loader.ldif.DynamicJarLdifSchemaLoader;
import org.apache.directory.shared.ldap.schema.manager.impl.DefaultSchemaManager;
import org.apache.directory.shared.ldap.schema.registries.SchemaLoader;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class VLDAPServer {

	public void destroy() throws Exception {
		destroyIoAcceptor();
	}

	public Directory getDirectory() {
		return _directory;
	}

	public SchemaManager getSchemaManager() {
		return _schemaManager;
	}

	public void init() throws Exception {
		initSchemaManager();
		initIoAcceptor();
	}

	protected void destroyIoAcceptor() {
		if (_ioAcceptor != null) {
			_ioAcceptor.unbind();
			_ioAcceptor.dispose();
		}
	}

	protected void initCodec() {
		DefaultIoFilterChainBuilder defaultIoFilterChainBuilder =
			_ioAcceptor.getFilterChain();

		ProtocolCodecFactory protocolCodecFactory = new LdapCodecFactory(
			_schemaManager);

		IoFilterAdapter ioFilterAdapter = new ProtocolCodecFilter(
			protocolCodecFactory);

		defaultIoFilterChainBuilder.addLast("codec", ioFilterAdapter);
	}

	protected void initDirectory() throws Exception {
		Directory rootDirectory = new Directory(StringPool.BLANK);

		rootDirectory.addAttribute("namingcontexts", "o=Liferay");
		rootDirectory.addAttribute("objectclass", "extensibleObject");
		rootDirectory.addAttribute("objectclass", "top");
		//rootDirectory.addAttribute("subschemasubentry", "cn=schema");
		rootDirectory.addAttribute(
			"supportedfeatures", OIDConstants.ALL_OPERATIONAL_ATTRIBUTES);
		rootDirectory.addAttribute("supportedldapversion", "3");
		rootDirectory.addAttribute(
			"supportedsaslmechanisms", BindLdapHandler.DIGEST_MD5);
		rootDirectory.addAttribute("vendorname", "Liferay, Inc.");
		rootDirectory.addAttribute("vendorversion", ReleaseInfo.getVersion());

		/*Directory schemaDirectory = new Directory("cn=schema");

		schemaDirectory.addAttribute("createtimestamp", "20090818022733Z");
		schemaDirectory.addAttribute("modifytimestamp", "20090818022733Z");
		schemaDirectory.addAttribute("objectclass", "subschema");
		schemaDirectory.addAttribute("objectclass", "subentry");
		schemaDirectory.addAttribute("objectclass", "top");

		rootDirectory.addDirectory(schemaDirectory);*/

		Directory topDirectory = new Directory("o=Liferay");

		topDirectory.addAttribute("objectclass", "organizationalUnit");
		topDirectory.addAttribute("objectclass", "top");
		topDirectory.addAttribute("o", "Liferay");

		rootDirectory.addDirectory(topDirectory);

		List<Company> companies = CompanyLocalServiceUtil.getCompanies(false);

		for (Company company : companies) {
			Directory companyDirectory = new Directory(
				"ou=" + company.getWebId() + ",o=Liferay");

			companyDirectory.addAttribute("objectclass", "organizationalUnit");
			companyDirectory.addAttribute("objectclass", "top");
			companyDirectory.addAttribute("ou", company.getWebId());

			topDirectory.addDirectory(companyDirectory);

			Directory communitiesDirectory = new Directory(
				"ou=Communities,ou=" + company.getWebId() + ",o=Liferay");

			communitiesDirectory.addAttribute("objectclass", "organizationalUnit");
			communitiesDirectory.addAttribute("objectclass", "top");
			communitiesDirectory.addAttribute("ou", "Communities");

			companyDirectory.addDirectory(communitiesDirectory);

			Directory rolesDirectory = new Directory(
				"ou=Roles,ou=" + company.getWebId() + ",o=Liferay");

			rolesDirectory.addAttribute("objectclass", "organizationalUnit");
			rolesDirectory.addAttribute("objectclass", "top");
			rolesDirectory.addAttribute("ou", "Roles");

			List<Role> roles = RoleLocalServiceUtil.getRoles(company.getCompanyId());

			for (Role role : roles) {
				Directory roleDirectory = new Directory("ou=" + role.getName() + ",ou=Roles,ou=" + company.getWebId() + ",o=Liferay");

				roleDirectory.addAttribute("objectclass", "organizationalUnit");
				roleDirectory.addAttribute("objectclass", "top");
				roleDirectory.addAttribute("ou", role.getName());

				rolesDirectory.addDirectory(roleDirectory);

				if (PortalUtil.isSystemRole(role.getName())) {
					Directory usersDirectory = new Directory(
						"ou=Users,ou=" + role.getName() + ",ou=Roles,ou=" + company.getWebId() + ",o=Liferay");

					usersDirectory.addAttribute("objectclass", "organizationalUnit");
					usersDirectory.addAttribute("objectclass", "top");
					usersDirectory.addAttribute("ou", "Roles");

					roleDirectory.addDirectory(usersDirectory);

					List<User> users = UserLocalServiceUtil.getRoleUsers(role.getRoleId());

					for (User user : users) {
						Directory userDirectory = new Directory("cn=" + user.getScreenName() + ",ou=Users,ou=" + role.getName() + ",ou=Roles,ou=" + company.getWebId() + ",o=Liferay");

						userDirectory.addAttribute("cn", user.getScreenName());
						userDirectory.addAttribute("displayName", user.getFullName());
						userDirectory.addAttribute("givenName", user.getFirstName());
						userDirectory.addAttribute("mail", user.getEmailAddress());
						userDirectory.addAttribute("sn", user.getLastName());
						userDirectory.addAttribute("objectclass", "inetOrgPerson");
						userDirectory.addAttribute("objectclass", "top");
						userDirectory.addAttribute("uid", String.valueOf(user.getUserId()));

						usersDirectory.addDirectory(userDirectory);
					}
				}
			}

			companyDirectory.addDirectory(rolesDirectory);

			Directory usersDirectory = new Directory(
				"ou=Users,ou=" + company.getWebId() + ",o=Liferay");

			usersDirectory.addAttribute("objectclass", "organizationalUnit");
			usersDirectory.addAttribute("objectclass", "top");
			usersDirectory.addAttribute("ou", "Roles");

			List<User> users = UserLocalServiceUtil.getCompanyUsers(company.getCompanyId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (User user : users) {
				Directory userDirectory = new Directory("cn=" + user.getScreenName() + ",ou=Users,ou=" + company.getWebId() + ",o=Liferay");

				userDirectory.addAttribute("cn", user.getScreenName());
				userDirectory.addAttribute("displayName", user.getFullName());
				userDirectory.addAttribute("givenName", user.getFirstName());
				userDirectory.addAttribute("mail", user.getEmailAddress());
				userDirectory.addAttribute("sn", user.getLastName());
				userDirectory.addAttribute("objectclass", "inetOrgPerson");
				userDirectory.addAttribute("objectclass", "top");
				userDirectory.addAttribute("uid", String.valueOf(user.getUserId()));

				usersDirectory.addDirectory(userDirectory);
			}

			companyDirectory.addDirectory(usersDirectory);
		}

		_directory = rootDirectory;
	}

	protected void initIoAcceptor() throws Exception {
		_ioAcceptor = new NioSocketAcceptor();

		initDirectory();
		initIoHandler();
		initCodec();
		initLogging();

		SocketAddress socketAddress = new InetSocketAddress(
			PortletPropsValues.BIND_PORT);

		_ioAcceptor.bind(socketAddress);
	}

	protected void initIoHandler() {
		DispatchIoHandler dispatchIoHandler = new DispatchIoHandler();

		dispatchIoHandler.setVLDAPServer(this);

		_ioAcceptor.setHandler(dispatchIoHandler);
	}

	protected  void initLogging() {
		DefaultIoFilterChainBuilder defaultIoFilterChainBuilder =
			_ioAcceptor.getFilterChain();

		defaultIoFilterChainBuilder.addLast("logger", new LoggingFilter());
	}

	protected void initSchemaManager() throws Exception {
		SchemaLoader schemaLoader = new DynamicJarLdifSchemaLoader();

		_schemaManager = new DefaultSchemaManager(schemaLoader);

		_schemaManager.loadWithDeps("inetorgperson");
	}

	private Directory _directory;
	private IoAcceptor _ioAcceptor;
	private SchemaManager _schemaManager;

}