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

package com.liferay.portlet.atlassian.jira.action;

import com.liferay.portlet.atlassian.jira.JiraProxy;
import com.liferay.portlet.atlassian.jira.SystemException;
import com.liferay.portlet.atlassian.jira.IssueTrackerSecurityException;
import com.liferay.portlet.atlassian.jira.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.portlet.handler.HandlerInterceptorAdapter;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Authenticate
 *
 * @author Michael C. Han
 * @version $Revision$
 */
public class JiraAuthenticationInterceptor
    extends HandlerInterceptorAdapter {

    public boolean preHandleRender(final RenderRequest request,
                                   final RenderResponse response, final Object handler)
        throws Exception {
        final PortletSession session = request.getPortletSession();
        final PortletPreferences prefs = request.getPreferences();

        _initializeJiraConnection(session, prefs);
        return true;
    }

    public boolean preHandleAction(final ActionRequest request,
                                   final ActionResponse response,
                                   final Object handler)
        throws Exception {
        final PortletSession session = request.getPortletSession();
        final PortletPreferences prefs = request.getPreferences();
        _initializeJiraConnection(session, prefs);
        
        return true;
    }

    private void _initializeJiraConnection(final PortletSession session,
                                           final PortletPreferences prefs) {
        JiraProxy proxy =
            (JiraProxy) session.getAttribute(JiraPortletConstants.PROXY_KEY);
        if (proxy == null) {
            //create new proxy with the preference settings...
            final String url =
                prefs.getValue(JiraPortletConstants.URL_PREFERENCE,
                               StringUtil.EMPTY_STRING);
            if (StringUtil.isEmpty(url)) {
                _createSimulator(session);
            }
            proxy = _prototype.newInstance();
            try {
                proxy.setServiceURL(url);
                session.setAttribute(JiraPortletConstants.PROXY_KEY, proxy);
            }
            catch (SystemException e) {
                if (log.isErrorEnabled()) {
                    log.error("Unable to connect to server: " + url, e);
                }
                _createSimulator(session);
            }
        }
        String securityToken =
            (String) session.getAttribute(
                JiraPortletConstants.SECURITY_TOKEN_KEY);
        if (StringUtil.isEmpty(securityToken)) {
            final String userName =
                prefs.getValue(JiraPortletConstants.USER_NAME_PREFERENCE,
                               StringUtil.EMPTY_STRING);
            final String password =
                prefs.getValue(JiraPortletConstants.PASSWORD_PREFERENCE,
                               StringUtil.EMPTY_STRING);
            try {
                securityToken = proxy.login(userName, password);
                session.setAttribute(JiraPortletConstants.SECURITY_TOKEN_KEY,
                                     securityToken);
            }
            catch (Exception e) {
                if (log.isErrorEnabled()) {
                    log.error("Unable log into with user: " + userName, e);
                }
                _createSimulator(session);
            }
        }
    }

    public void setJiraProxy(JiraProxy prototype) {
        _prototype = prototype;
    }

    public void setSimulator(JiraProxy prototype) {
        _simulator = prototype;
    }

    public void afterPropertiesSet() throws Exception {
        if (_prototype == null) {
            throw new IllegalStateException("Jira proxy not initialized.");
        }
    }

    private void _createSimulator(PortletSession session) {
        if (log.isErrorEnabled()) {
            log.error("Entering simulator mode.");
        }
        JiraProxy proxy = _simulator.newInstance();
        session.setAttribute(JiraPortletConstants.PROXY_KEY, proxy);
        session.setAttribute(JiraPortletConstants.MODE_KEY, Boolean.TRUE);
        try {
            session.setAttribute(JiraPortletConstants.SECURITY_TOKEN_KEY,
                             proxy.login("",""));
        }
        catch (Exception e) {
            //simulator so no errors can occur
        }

    }

    private static final Log log =
        LogFactory.getLog(JiraAuthenticationInterceptor.class);

    private JiraProxy _prototype;
    private JiraProxy _simulator;
}