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
