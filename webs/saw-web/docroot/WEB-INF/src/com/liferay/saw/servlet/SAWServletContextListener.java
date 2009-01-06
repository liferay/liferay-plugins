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

/**
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.sun.com/cddl/cddl.html and
 * legal/CDDLv1.0.txt. See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at legal/CDDLv1.0.txt.
 *
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Copyright 2009 Sun Microsystems Inc. All rights reserved.
 */

package com.liferay.saw.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.AggregateClassLoader;
import com.liferay.portal.kernel.util.DatabaseUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PortalInitable;
import com.liferay.portal.kernel.util.PortalInitableUtil;

/**
 * <a href="SAWServletContextListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Ganesh Ram
 *
 */

public class SAWServletContextListener
	implements PortalInitable, ServletContextListener{

	public void contextDestroyed(ServletContextEvent event){
		
	}

	public void contextInitialized(ServletContextEvent event){
		PortalInitableUtil.init(this);
	}

	public void portalInit(){

		boolean tableAlreadyExists = _checkIfTablesExist();
		if(tableAlreadyExists){
			if (_log.isInfoEnabled()) {
				_log.info("JBPM Tables already exists.");
			}
		}
		else{
			if (_log.isInfoEnabled()) {
				_log.info(
						"JBPM Tables does not exist. So have to create them.");
			}
			String dialect = _getDialect();
			ClassLoader currentClassLoader =_getCurrentClassLoader();
			ClassLoader portletClassLoader =_getPortletClassLoader();
			_setAggregateClassLoader(portletClassLoader);
			try{
			_runSQLQuery(dialect);
			}
			catch (IOException e) {
				_log.error(e, e);
			}
			catch (NamingException e) {
				_log.error(e, e);
			}
			catch (SQLException e) {
				_log.error(e, e);
			}
			_restoreClassLoader(currentClassLoader);
		}
	
	}
	
	private boolean _checkIfTablesExist(){
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean tableExists = false;

		try {
			con = DataAccess.getConnection();
			ps = con.prepareStatement("select ID_ from JBPM_PROCESSDEFINITION");
			rs = ps.executeQuery();
			tableExists = true;
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e.getMessage());
			}
			tableExists = false;
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
		return tableExists;
	}
	
	private ClassLoader _getCurrentClassLoader(){
		return Thread.currentThread().getContextClassLoader();
		
	}
	
	private String _getDialect(){
		
		String dialect = DatabaseUtil.getType();
		return dialect;
	}
	
	private ClassLoader _getPortletClassLoader(){
		return PortletClassLoaderUtil.getClassLoader();
	}
	
	private void _restoreClassLoader(ClassLoader cl){
		Thread.currentThread().setContextClassLoader(cl);
	}
	
	private void _runSQLQuery(String dialect) 
		throws IOException, NamingException, SQLException{

		if(dialect.equals("mysql")){
			DatabaseUtil.runSQLTemplate("sql/mysql.create.sql");
		}
		else if (dialect.equals("derby")){
			DatabaseUtil.runSQLTemplate("sql/derby.create.sql");
		}
		else if (dialect.equals("firebird")){
			DatabaseUtil.runSQLTemplate("sql/firebird.create.sql");
		}
		else if (dialect.equals("hypersonic")){
			DatabaseUtil.runSQLTemplate("sql/hsqldb.create.sql");
		}
		else if (dialect.equals("oracle")){
			DatabaseUtil.runSQLTemplate("sql/oracle.create.sql");
		}
		else if (dialect.equals("postgresql")){
			DatabaseUtil.runSQLTemplate("sql/postgresql.create.sql");
		}
		else if (dialect.equals("sybase")){
			DatabaseUtil.runSQLTemplate("sql/sybase.create.sql");
		}

	}

	private void _setAggregateClassLoader(ClassLoader portletClassLoader){

		AggregateClassLoader cl =new AggregateClassLoader(
				portletClassLoader, PortalClassLoaderUtil.getClassLoader());

		Thread.currentThread().setContextClassLoader(cl);
		
	}

	private static Log _log = LogFactory.getLog(
		SAWServletContextListener.class);

}