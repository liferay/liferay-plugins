/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
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

package com.sample.hibernate.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * <a href="HibernateUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Charles May
 *
 */
public class HibernateUtil {

    public static final String COUNT_COLUMN_NAME = "COUNT_VALUE";

	public static String getCountColumnName() {
		return COUNT_COLUMN_NAME;
	}

    public static SessionFactory getSessionFactory() {
        return _instance._sessionFactory;
    }

	public static void closeSession(Session session) {
		try {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		catch (HibernateException he) {
			_log.error(he.getMessage());
		}
	}

	public static Session openSession() throws HibernateException {
		return openSession(getSessionFactory());
	}

	public static Session openSession(SessionFactory sessionFactory)
		throws HibernateException {

		return _instance._sessionFactory.getCurrentSession();
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

	private static Log _log = LogFactory.getLog(HibernateUtil.class);

	private static HibernateUtil _instance = new HibernateUtil();

	private SessionFactory _sessionFactory;

}