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

import org.springframework.web.portlet.bind.PortletRequestUtils;
import org.springframework.web.portlet.mvc.AbstractController;
import org.springframework.web.portlet.ModelAndView;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * Save the preferences for this portlet and redisplays the preferences.
 *
 * @author Jian Cao
 * @version $Revision$
 */
public class CreateTicketSavePreferencesController
    extends CreateTicketPreferencesDisplayController {


    
    protected void handleActionRequestInternal(final ActionRequest request,
                                               final ActionResponse response)
        throws Exception {
        final PortletPreferences prefs = request.getPreferences();
        final String url =
            PortletRequestUtils.getStringParameter(request,
                                                   JiraPortletConstants.URL_PREFERENCE);
        prefs.setValue(JiraPortletConstants.URL_PREFERENCE, url);

        final String userName =
            PortletRequestUtils.getStringParameter(request,
                                                   JiraPortletConstants.USER_NAME_PREFERENCE);
        prefs.setValue(JiraPortletConstants.USER_NAME_PREFERENCE, userName);

        final String password =
            PortletRequestUtils.getStringParameter(request,
                                                   JiraPortletConstants.PASSWORD_PREFERENCE);
        prefs.setValue(JiraPortletConstants.PASSWORD_PREFERENCE, password);

        prefs.store();

        
    }
}