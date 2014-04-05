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

package com.liferay.customsql.user;

import com.liferay.customsql.CustomSQLUtil;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.InfrastructureUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class UserFinder extends BasePersistenceImpl<User> {

	public static final String FIND_BY_NO_CONTACTS =
		UserFinder.class.getName() + ".findByNoContacts";

	public UserFinder() {
		setDataSource(InfrastructureUtil.getDataSource());
		setSessionFactory(
			(SessionFactory)PortalBeanLocatorUtil.locate(
				"liferaySessionFactory"));

		ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

		try {
			_userImplClass = classLoader.loadClass(
				"com.liferay.portal.model.impl.UserImpl");
		}
		catch (ClassNotFoundException cnfe) {
			throw new RuntimeException(cnfe.getMessage(), cnfe);
		}
	}

	public List<User> findByNoContacts() throws SystemException {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_NO_CONTACTS);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("User_", _userImplClass);

			return q.list(true);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private Class<?> _userImplClass;

}