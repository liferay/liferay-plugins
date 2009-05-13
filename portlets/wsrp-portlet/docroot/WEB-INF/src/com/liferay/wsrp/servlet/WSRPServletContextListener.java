/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.wsrp.servlet;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalInitable;
import com.liferay.portal.kernel.util.PortalInitableUtil;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.wsrp.WSRPFactoryUtil;
import com.liferay.util.portlet.PortletProps;

import com.liferay.wsrp.WSRPFactoryImpl;
import com.liferay.wsrp.consumer.admin.WSRPPersistenceHelper;
import com.liferay.wsrp.producer.usermanager.UserManagerImpl;

import com.sun.portal.container.service.Service;
import com.sun.portal.container.service.ServiceManager;
import com.sun.portal.wsrp.common.WSRPConfig;
import com.sun.portal.wsrp.consumer.common.DeploymentServiceRemoteImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * <a href="WSRPServletContextListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPServletContextListener
	implements PortalInitable, ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {
		try {
			doContextDestroyed();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public void contextInitialized(ServletContextEvent event) {
		PortalInitableUtil.init(this);
	}

	public void portalInit() {
		try {
			doPortalInit();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void doContextDestroyed() throws Exception {
		WSRPFactoryUtil wsrpFactoryUtil =
			(WSRPFactoryUtil)PortalBeanLocatorUtil.locate(
				com.liferay.portal.wsrp.WSRPFactoryUtil.class.getName());

		wsrpFactoryUtil.setWSRPFactory(null);

		WSRPPersistenceHelper wsrpPersistence =
			WSRPPersistenceHelper.getInstance();

		List<Portlet> portlets = wsrpPersistence.getWSRPPortlets();

		for (Portlet portlet : portlets) {
			PortletLocalServiceUtil.destroyPortlet(portlet);
		}

		ServiceManager serviceManager = ServiceManager.getServiceManager();

		serviceManager.removeService(Service.DEPLOYMENT_SERVICE_REMOTE);
	}

	protected void doPortalInit() throws Exception {
		WSRPFactoryUtil wsrpFactoryUtil =
			(WSRPFactoryUtil)PortalBeanLocatorUtil.locate(
				com.liferay.portal.wsrp.WSRPFactoryUtil.class.getName());

		wsrpFactoryUtil.setWSRPFactory(new WSRPFactoryImpl());

		Properties properties = PortletProps.getProperties();

		ServiceManager serviceManager = ServiceManager.getServiceManager();

		serviceManager.addService(
			Service.DEPLOYMENT_SERVICE_REMOTE,
			new DeploymentServiceRemoteImpl());

		WSRPConfig.init(properties, "portal1");

		WSRPPersistenceHelper wsrpPersistence =
			WSRPPersistenceHelper.getInstance();

		//create WSRP company, if not existing
		try {
			UserManagerImpl.getWSRPCompany();
		}
		catch (Exception e) {
			_log.error(e);
		}

		if (isInitialDepoy()) {

			// WSRPPersistenceHelper assumes the WSRP tables have been created,
			// but on the first deploy, this listener is fired before
			// PortletContextListener, which is responsible for creating the
			// tables. Since the tables will initially not contain any data, it
			// is safe to breakt out of the method.

			return;
		}

		List<Portlet> portlets = wsrpPersistence.getWSRPPortlets();

		for (Portlet portlet : portlets) {
			PortletLocalServiceUtil.deployRemotePortlet(portlet);
		}
	}

	protected boolean isInitialDepoy() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement("select * from WSRP_WSRPPortlet");

			rs = ps.executeQuery();
		}
		catch (Exception e) {
			return true;
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		return false;
	}

	private static Log _log =
		LogFactoryUtil.getLog(WSRPServletContextListener.class);

}