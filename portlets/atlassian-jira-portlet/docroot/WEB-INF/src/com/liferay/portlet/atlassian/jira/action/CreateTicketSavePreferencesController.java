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