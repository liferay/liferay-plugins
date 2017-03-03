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

package com.liferay.portal.servlet;

import com.liferay.portal.atom.AtomProvider;
import com.liferay.portal.atom.AtomUtil;
import com.liferay.portal.kernel.atom.AtomCollectionAdapter;
import com.liferay.portal.kernel.atom.AtomCollectionAdapterRegistryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.access.control.AccessControlThreadLocal;
import com.liferay.portal.kernel.security.access.control.AccessControlUtil;
import com.liferay.portal.kernel.security.auth.AccessControlContext;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifierResult;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.abdera.protocol.server.Provider;
import org.apache.abdera.protocol.server.servlet.AbderaServlet;

/**
 * @author Igor Spasic
 */
public class AtomServlet extends AbderaServlet {

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);

		if (_log.isInfoEnabled()) {
			_log.info("EXT_ATOM_SERVLET_INSTALLED");
		}
	}

	@Override
	protected Provider createProvider() {
		AtomProvider atomProvider = new AtomProvider();

		atomProvider.init(getAbdera(), null);

		List<AtomCollectionAdapter<?>> atomCollectionAdapters =
			AtomCollectionAdapterRegistryUtil.getAtomCollectionAdapters();

		for (AtomCollectionAdapter<?> atomCollectionAdapter :
				atomCollectionAdapters) {

			atomProvider.addCollection(atomCollectionAdapter);
		}

		return atomProvider;
	}

	@Override
	protected void service(
			HttpServletRequest request, HttpServletResponse response)
		throws ServletException {

		boolean remoteAccess = AccessControlThreadLocal.isRemoteAccess();

		try {
			AccessControlContext accessControlContext =
				AccessControlUtil.getAccessControlContext();

			AuthVerifierResult authVerifierResult =
				accessControlContext.getAuthVerifierResult();

			User user = UserLocalServiceUtil.getUser(
				authVerifierResult.getUserId());

			AtomUtil.saveUserInRequest(request, user);

			AccessControlThreadLocal.setRemoteAccess(true);

			super.service(request, response);
		}
		catch (Exception e) {
			throw new ServletException(e);
		}
		finally {
			AccessControlThreadLocal.setRemoteAccess(remoteAccess);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(AtomServlet.class);

}