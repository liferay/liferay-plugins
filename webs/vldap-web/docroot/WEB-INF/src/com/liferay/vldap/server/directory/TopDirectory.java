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

import com.liferay.portal.model.Company;
import com.liferay.portal.service.CompanyLocalServiceUtil;

import java.util.List;

import org.apache.directory.shared.ldap.message.internal.InternalRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class TopDirectory extends BaseDirectory {

	public TopDirectory(InternalRequest internalRequest) throws Exception {
		super("o=Liferay");

		_internalRequest = internalRequest;

		initAttributes();
	}

	@Override
	public InternalRequest getInternalRequest() {
		return _internalRequest;
	}

	@Override
	protected void initAttributes() {
		addAttribute("objectclass", "organizationalUnit");
		addAttribute("objectclass", "top");
		addAttribute("o", "Liferay");
	}

	@Override
	protected List<Directory> initDirectories() throws Exception {
		List<Company> companies = CompanyLocalServiceUtil.getCompanies(false);

		for (Company company : companies) {
			Directory companyDirectory = new CompanyDirectory(company, this);

			_directories.add(companyDirectory);
		}

		return _directories;
	}

	private InternalRequest _internalRequest;

}