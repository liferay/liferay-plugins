/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.samplehibernate.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Charles May
 */
public class HibernateUtil {

	public static final String COUNT_COLUMN_NAME = "COUNT_VALUE";

	public static void closeSession(Session session) {
		try {
			if ((session != null) && session.isOpen()) {
				session.close();
			}
		}
		catch (HibernateException he) {
			_log.error(he.getMessage());
		}
	}

	public static String getCountColumnName() {
		return COUNT_COLUMN_NAME;
	}

	public static SessionFactory getSessionFactory() {
		return _instance._sessionFactory;
	}

	public static Session openSession() throws HibernateException {
		return openSession(getSessionFactory());
	}

	public static Session openSession(SessionFactory sessionFactory)
		throws HibernateException {

		return sessionFactory.getCurrentSession();
	}

	private HibernateUtil() {
		try {
			Configuration configuration = new Configuration();

			configuration = configuration.configure();

			_sessionFactory = configuration.buildSessionFactory();
		}
		catch (Exception e) {
		   _log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(HibernateUtil.class);

	private static HibernateUtil _instance = new HibernateUtil();

	private SessionFactory _sessionFactory;

}