/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portlet.service;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.dao.hibernate.LiferayClassicSession;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.impl.SessionImpl;

import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

/**
 * <a href="HibernateUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class HibernateUtil {

	public static final String COUNT_COLUMN_NAME = "COUNT_VALUE";

	public static final String SPRING_HIBERNATE_DATA_SOURCE =
		PropsUtil.get(PropsUtil.SPRING_HIBERNATE_DATA_SOURCE);

	public static final String SPRING_HIBERNATE_SESSION_FACTORY =
		PropsUtil.get(PropsUtil.SPRING_HIBERNATE_SESSION_FACTORY);

	public static void closeSession(Session session) {
		try {
			if (session != null) {

				// Let Spring manage sessions

				//session.close();
			}
		}
		catch (HibernateException he) {
			_log.error(he.getMessage());
		}
	}

	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}

	public static String getCountColumnName() {
		return COUNT_COLUMN_NAME;
	}

	public static DataSource getDataSource() {
		if (_dataSource == null) {
			_dataSource = (DataSource)BeanLocatorUtil.locate(
				SPRING_HIBERNATE_DATA_SOURCE);
		}

		return _dataSource;
	}

	public static Dialect getDialect() {
		return getDialect(SPRING_HIBERNATE_SESSION_FACTORY);
	}

	public static Dialect getDialect(String sessionFactoryName) {
		return getSessionFactory(sessionFactoryName).getDialect();
	}

	public static SessionFactoryImplementor getSessionFactory() {
		return getSessionFactory(SPRING_HIBERNATE_SESSION_FACTORY);
	}

	public static SessionFactoryImplementor getSessionFactory(
		String sessionFactoryName) {

		if (sessionFactoryName.equals(SPRING_HIBERNATE_SESSION_FACTORY)) {
			if (_sessionFactory == null) {
				LocalSessionFactoryBean lsfb =
					(LocalSessionFactoryBean)BeanLocatorUtil.locate(
						sessionFactoryName);

				_sessionFactory = (SessionFactoryImplementor)lsfb.getObject();
			}

			return _sessionFactory;
		}
		else {
			SessionFactoryImplementor sessionFactory = _sessionFactories.get(
				sessionFactoryName);

			if (sessionFactory == null) {
				LocalSessionFactoryBean lsfb =
					(LocalSessionFactoryBean)BeanLocatorUtil.locate(
						sessionFactoryName);

				sessionFactory = (SessionFactoryImplementor)lsfb.getObject();

				_sessionFactories.put(sessionFactoryName, sessionFactory);
			}

			return sessionFactory;
		}
	}

	public static Dialect getWrappedDialect() {
		return getWrappedDialect(SPRING_HIBERNATE_SESSION_FACTORY);
	}

	public static Dialect getWrappedDialect(String sessionFactoryName) {
		Dialect dialect = getSessionFactory(sessionFactoryName).getDialect();

		if (dialect instanceof DynamicDialect) {
			DynamicDialect dynamicDialect = (DynamicDialect)dialect;

			return dynamicDialect.getWrappedDialect();
		}
		else {
			return dialect;
		}
	}

	public static Session openSession() throws HibernateException {
		return openSession(SPRING_HIBERNATE_SESSION_FACTORY);
	}

	public static Session openSession(String sessionFactoryName)
		throws HibernateException {

		if (Validator.isNull(sessionFactoryName)) {
			return openSession();
		}

		SessionFactoryImplementor sessionFactory =
			getSessionFactory(sessionFactoryName);

		return openSession(sessionFactory);
	}

	public static Session openSession(SessionFactory sessionFactory)
		throws HibernateException {

		if (sessionFactory == null) {
			return openSession();
		}

		// Let Spring manage sessions

		Session session = sessionFactory.getCurrentSession();

		if (_log.isDebugEnabled()) {
			LiferayClassicSession classicSession =
				(LiferayClassicSession)session;

			SessionImpl sessionImpl =
				(SessionImpl)classicSession.getHibernateClassicSession();

			_log.debug(
				"Session is using connection release mode " +
					sessionImpl.getConnectionReleaseMode());
		}

		return session;
	}

	public static SystemException processException(Exception e) {
		if (e instanceof HibernateException) {
			_log.error("Caught HibernateException");
		}
		else {
			_log.error("Caught unexpected exception " + e.getClass().getName());
		}

		_log.error(e, e);

		return new SystemException(e);
	}

	private static Log _log = LogFactory.getLog(HibernateUtil.class);

	private static DataSource _dataSource;
	private static SessionFactoryImplementor _sessionFactory;
	private static Map<String, SessionFactoryImplementor> _sessionFactories =
		new HashMap<String, SessionFactoryImplementor>();

}