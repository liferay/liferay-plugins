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

import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.AbstractController;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * Retrieve the preferences for this portlet and prepare for display on the page.
 *
 * @author Jian Cao
 * @version $Revision$
 */
public class CreateTicketPreferencesDisplayController
    extends AbstractController {

    protected ModelAndView handleRenderRequestInternal(final RenderRequest req,
                                                       final RenderResponse response)
        throws Exception {
        ModelAndView modelView =
            new ModelAndView(JiraPortletConstants.PREFS_VIEW);
        PortletPreferences prefs = req.getPreferences();
        final String url = prefs.getValue(JiraPortletConstants.URL_PREFERENCE,
                                          "");
        final String userName = prefs.getValue(
            JiraPortletConstants.USER_NAME_PREFERENCE, "");
        final String password = prefs.getValue(
            JiraPortletConstants.PASSWORD_PREFERENCE, "");
        modelView.addObject(JiraPortletConstants.URL_PREFERENCE, url);
        modelView.addObject(JiraPortletConstants.USER_NAME_PREFERENCE,
                            userName);
        modelView.addObject(JiraPortletConstants.PASSWORD_PREFERENCE, password);
        return modelView;
    }
}
